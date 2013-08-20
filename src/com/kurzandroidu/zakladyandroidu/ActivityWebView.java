package com.kurzandroidu.zakladyandroidu;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityWebView extends Activity {

    private WebView     mWebView;
    private EditText    mUrl;
    private ProgressBar mProgressBar;

    private class DocumentWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
            mUrl.setText(url);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.layout_webview);
        Utils.getOverflowMenu(this);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebViewClient(new DocumentWebViewClient());
        mUrl = (EditText) findViewById(R.id.editTextUrl);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (bundle != null)
            mWebView.loadUrl(bundle.getString("url"));
        else
            mWebView.loadUrl(getString(R.string.default_url));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", mUrl.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_web, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(1).setEnabled(mWebView.canGoBack());
        menu.getItem(2).setEnabled(mWebView.canGoForward());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLoad:
                try {
                    URL url = new URL(mUrl.getText().toString());
                    mWebView.loadUrl(url.toString());
                }
                catch (MalformedURLException e) {
                    Toast toast = Toast.makeText(this, R.string.malformed_url,
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                return true;

            case R.id.actionHome:
                mWebView.loadUrl(getString(R.string.default_url));
                return true;

            case R.id.actionBack:
                mWebView.goBack();
                return true;

            case R.id.actionForward:
                mWebView.goForward();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
