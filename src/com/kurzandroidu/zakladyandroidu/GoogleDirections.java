package com.kurzandroidu.zakladyandroidu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoogleDirections {

    public static class JSONParser {

        public static String makeURLForDistanceAPI(double sourcelat,
                double sourcelog, double destlat, double destlog) {
            StringBuilder urlString = new StringBuilder();
            urlString
                    .append("https://maps.googleapis.com/maps/api/directions/json")
                    .append("?origin=").append(Double.toString(sourcelat))
                    .append(",").append(Double.toString(sourcelog))
                    .append("&destination=").append(Double.toString(destlat))
                    .append(",").append(Double.toString(destlog))
                    .append("&sensor=false&mode=driving&alternatives=true");
            return urlString.toString();
        }

        public static String getJSONStrFromUrl(String url) {
            InputStream inStream = null;
            String jsonStr = "";

            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inStream = httpEntity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                jsonStr = sb.toString();
                inStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return jsonStr;
        }

        private static List<LatLng> decodePoly(String encoded) {
            List<LatLng> poly = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                }
                while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                }
                while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }
            return poly;
        }

        public static void drawPathOnMap(GoogleMap mMap, String result) {
            try {
                final JSONObject json = new JSONObject(result);
                JSONArray routeArray = json.getJSONArray("routes");
                JSONObject routes = routeArray.getJSONObject(0);
                JSONObject overviewPolylines = routes
                        .getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");
                List<LatLng> list = decodePoly(encodedString);

                for (int z = 0; z < list.size() - 1; z++) {
                    mMap.addPolyline(new PolylineOptions()
                            .add(list.get(z), list.get(z + 1))
                            .color(Color.BLUE).width(5));
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
