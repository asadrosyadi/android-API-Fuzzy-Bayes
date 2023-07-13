package com.tesis.fuzzybayes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Perhitungan extends AppCompatActivity {
    Button kembalihome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan);
        kembalihome = findViewById(R.id.kembalihome);

        kembalihome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String HWID = null;
                if (bundle != null) {
                    HWID = bundle.getString("HWID");
                }
                Intent intent = new Intent(Perhitungan.this, Home.class);
                intent.putExtra("HWID", HWID);
                startActivity(intent);
            }
        });

        WebView webView = findViewById(R.id.webview);
        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String HWID = intent.getStringExtra("HWID");
        webView.loadUrl("https://myfarm.lactograin.id/rest/perhitungan/" + HWID);
    }
}