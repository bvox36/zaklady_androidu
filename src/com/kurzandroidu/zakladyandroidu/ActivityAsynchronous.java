package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ActivityAsynchronous extends Activity implements
        LoaderCallbacks<List<String>> {

    private ListView         mViewTypesList;
    private ProgressBar      progressBarHoriz;
    private ProgressBar      mProgressBarCircle;
    private CustomAsyncTask  mCustomAsyncTask;

    private static final int LOADER_ID = 0;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_listview);
        Utils.getOverflowMenu(this);

        mViewTypesList = (ListView) findViewById(R.id.listView);
        progressBarHoriz = (ProgressBar) findViewById(R.id.progressBarHoriz);
        mProgressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);

        mCustomAsyncTask = (CustomAsyncTask) getLastNonConfigurationInstance();

        if (mCustomAsyncTask != null) {

            mCustomAsyncTask.attach(this);
            progressBarHoriz.setVisibility(View.VISIBLE);
            progressBarHoriz.setProgress(mCustomAsyncTask.getProgress());

            if (mCustomAsyncTask.getProgress() >= 100) {
                progressBarHoriz.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {

        if (mCustomAsyncTask != null)
            mCustomAsyncTask.detach();

        return mCustomAsyncTask;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_asynchronous, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionStartWorkerThread:
                runWorkerThread();
                return true;
            case R.id.actionStartAsyncTask:
                if (mCustomAsyncTask == null) {
                    mCustomAsyncTask = new CustomAsyncTask(this);
                    mCustomAsyncTask.execute();
                }
                return true;
            case R.id.actionStopAsyncTask:

                if (mCustomAsyncTask != null)
                    mCustomAsyncTask.cancel(true);

                return true;
            case R.id.actionStartLoader:

                Loader<Object> loader = getLoaderManager().getLoader(LOADER_ID);

                if (loader != null) {
                    if (!loader.isStarted())
                        loader.startLoading();

                    loader.forceLoad();
                }
                else
                    getLoaderManager().initLoader(LOADER_ID, null, this);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void runWorkerThread() {

        mProgressBarCircle.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    mViewTypesList.post(new Runnable() {
                        public void run() {

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    getApplicationContext(),
                                    android.R.layout.simple_list_item_1,
                                    getResources().getStringArray(
                                            R.array.view_types));

                            mViewTypesList.setAdapter(adapter);
                            mProgressBarCircle.setVisibility(View.GONE);
                        }
                    });
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static class CustomAsyncTask extends
            AsyncTask<String, Integer, Long> {

        ActivityAsynchronous activity = null;
        int                  progress = 0;

        CustomAsyncTask(ActivityAsynchronous activity) {
            attach(activity);
        }

        protected Long doInBackground(String... params) {

            int incr = 0;
            for (incr = 0; incr <= 100; incr += 5) {
                if (isCancelled())
                    return Long.valueOf(incr);

                publishProgress(incr);

                try {
                    Thread.sleep(5 * 300);
                }
                catch (InterruptedException e) {
                }
            }

            return Long.valueOf(incr);
        }

        protected void onProgressUpdate(Integer... progress) {
            activity.progressBarHoriz.setProgress(progress[0]);
        }

        protected void onPostExecute(Long result) {
            activity.progressBarHoriz.setVisibility(View.GONE);
            activity.mCustomAsyncTask = null;
        }

        protected void onCancelled() {
            activity.progressBarHoriz.setVisibility(View.GONE);
            activity.mCustomAsyncTask = null;
        }

        protected void onPreExecute() {
            activity.progressBarHoriz.setVisibility(View.VISIBLE);
            activity.progressBarHoriz.setMax(100);
        }

        void detach() {
            activity = null;
        }

        void attach(ActivityAsynchronous activity) {
            this.activity = activity;
        }

        int getProgress() {
            return (progress);
        }
    }

    private static class ViewTypesLoader extends AsyncTaskLoader<List<String>> {
        private List<String> mData;

        public ViewTypesLoader(Context context) {
            super(context);
        }

        public List<String> loadInBackground() {
            mData = new ArrayList<String>();

            String[] types = getContext().getResources().getStringArray(
                    R.array.view_types);

            for (String str : types) {
                mData.add(str);
            }
            return mData;
        }

        public void deliverResult(List<String> data) {
            if (isReset()) {
                mData = null;
                return;
            }

            List<String> oldData = mData;
            mData = data;

            if (isStarted()) {
                super.deliverResult(data);
            }

            if (oldData != null && oldData != data) {
                oldData = null;
            }
        }

        protected void onStartLoading() {
            if (mData != null) {
                deliverResult(mData);
            }

            if (takeContentChanged() || mData == null) {
                forceLoad();
            }
        }

        protected void onStopLoading() {
            cancelLoad();
        }

        protected void onReset() {
            onStopLoading();

            if (mData != null) {
                mData = null;
            }
        }

        public void onCanceled(List<String> data) {
            super.onCanceled(data);
            mData = null;
        }
    }

    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new ViewTypesLoader(this);
    }

    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                data);

        mViewTypesList.setAdapter(adapter);
    }

    public void onLoaderReset(Loader<List<String>> loader) {
    }
}
