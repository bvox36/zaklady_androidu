package com.kurzandroidu.zakladyandroidu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

@SuppressWarnings("unchecked")
public class AddressAutocompleteAdapter extends ArrayAdapter<Address> implements
        Filterable {

    private LayoutInflater mInflater;
    private Geocoder       mGeocoder;
    private StringBuilder  mSb = new StringBuilder();

    public AddressAutocompleteAdapter(Context context) {
        super(context, -1);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGeocoder = new Geocoder(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) mInflater.inflate(
                android.R.layout.simple_dropdown_item_1line, parent, false);
        tv.setText(createFormattedAddressFromAddress(getItem(position)));
        return tv;
    }

    private String createFormattedAddressFromAddress(Address address) {
        mSb.setLength(0);
        int addressLineSize = address.getMaxAddressLineIndex();
        for (int i = 0; i < addressLineSize; i++) {
            mSb.append(address.getAddressLine(i));
            if (i != addressLineSize - 1) {
                mSb.append(", ");
            }
        }
        return mSb.toString();
    }

    public Filter getFilter() {
        Filter myFilter = new Filter() {

            protected FilterResults performFiltering(CharSequence constraint) {
                List<Address> addressList = new ArrayList<Address>();
                FilterResults filterResults = new FilterResults();
                List<Address> addressListFiltered = new ArrayList<Address>();

                if (constraint != null) {
                    try {
                        addressList = mGeocoder.getFromLocationName(
                                (String) constraint, 5);
                    }
                    catch (IOException e) {
                    }
                }

                for (Address address : addressList) {
                    if (address.getCountryCode().equals("CZ"))
                        addressListFiltered.add(address);
                }

                filterResults.values = addressListFiltered;
                filterResults.count = addressListFiltered.size();
                return filterResults;
            }

            protected void publishResults(CharSequence contraint,
                    FilterResults results) {

                if (results.values == null)
                    return;

                clear();
                for (Address address : (List<Address>) results.values) {
                    add(address);
                }
                if (results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }

            public CharSequence convertResultToString(Object resultValue) {
                return resultValue == null ? ""
                        : createFormattedAddressFromAddress((Address) resultValue);
            }
        };
        return myFilter;
    }
}
