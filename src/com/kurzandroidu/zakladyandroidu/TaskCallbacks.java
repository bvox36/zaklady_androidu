package com.kurzandroidu.zakladyandroidu;

public interface TaskCallbacks {

    void onPreExecute();

    void onProgressUpdate(int percent);

    void onCancelled();

    void onPostExecute();

}
