package com.example.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class ShuttleWebView extends Activity {
    private WebView webView;
    private String urlText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuttle_web_view);
        webView = (WebView) findViewById(R.id.view_shuttle);
        webView.getSettings().setJavaScriptEnabled(true);

        urlText = getResources().getString(R.string.shuttle_link);
        ;

        webView.loadUrl(urlText);
    };


    //the back key navigates back to the previous web page
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "webLookup Finished", Toast.LENGTH_LONG).show();

    }

}
