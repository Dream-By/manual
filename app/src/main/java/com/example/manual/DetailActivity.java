package com.example.manual;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView webView = findViewById(R.id.webView);

        Intent intent = getIntent();

        String resName = "n" + intent.getIntExtra("title",0);
        Log.i("name",resName);
        Context context = getBaseContext();

        String text = readRawTextFile(context,getResources().getIdentifier(resName,"raw","com.example.manual"));

        webView.loadDataWithBaseURL(null,text,"text/html","en_Us",null);

    }

    private String readRawTextFile (Context context, int resId) {

        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while (( line = buffReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }
}
