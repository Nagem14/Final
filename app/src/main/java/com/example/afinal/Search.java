package com.example.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Toast;

import java.util.Locale;

public class Search extends Activity implements TextToSpeech.OnInitListener {

    private EditText search_term;
    private Button search_button;
    private Button back_button;
    private WebView webView;
    private String urlText;

    private TextToSpeech speaker;
    private static final String tag = "Widgets";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Get a handle to all user interface elements
        search_term = (EditText) findViewById(R.id.search_term);
        search_button = (Button) findViewById(R.id.search_button);
        back_button = (Button) findViewById(R.id.back_button);
        webView = (WebView) findViewById(R.id.web_view);



        //intercept URL loading and load in widget
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // Set button to open browser
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //initialize urlText with search term and bentley URL
                urlText = "https://www.bentley.edu/search?term=" + search_term.getText().toString();

                //Toast.makeText(getApplicationContext(),urlText, Toast.LENGTH_SHORT).show();

                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();
                    // else start speech
                } else {
                    Log.i(tag, "Speaker Not Already Speaking");
                    speak("you are searching for" + search_term.getText().toString());
                }

                webView.loadUrl(urlText);
            }
        });

        search_term.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    //initialize urlText with search term and bentley URL
                    urlText = "https://www.bentley.edu/search?term=" + search_term.getText().toString();

                    // if speaker is talking, stop it
                    if(speaker.isSpeaking()){
                        Log.i(tag, "Speaker Speaking");
                        speaker.stop();
                        // else start speech
                    } else {
                        Log.i(tag, "Speaker Not Already Speaking");
                        speak("you are searching for" + search_term.getText().toString());
                    }

                    webView.loadUrl(urlText);
                    return true;
                }
                return false;
            }
        });

        //Set back button to go back
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        //Initialize Text to Speech engine (context, listener object)
        speaker = new TextToSpeech(this, this);
    }

    // on destroy
    public void onDestroy(){

        // shut down TTS engine
        if(speaker != null){
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }

    //speak methods will send text to be spoken
    public void speak(String output){
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }

    // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            int result = speaker.setLanguage(Locale.US);
        } else {
            // Initialization failed.
            Log.e(tag, "Could not initialize TextToSpeech.");
        }
    }

    //the back key navigates back to the previous web page
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

