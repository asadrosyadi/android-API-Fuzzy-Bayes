package com.tesis.fuzzybayes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pengaturan extends AppCompatActivity {
    EditText minrh,midrh,mid2rh,maxrh,minudara,midudara,mid2udara,maxudara,mindaun,middaun,mid2daun,maxdaun,minmdatmn,midmdatmn,mid2mdatmn,maxmdatmn,
            minppm,midppm,mid2ppm,maxppm,minph,midph,mid2ph,maxph,oksigenmin,oksigenmid,oksigenmid2,oksigenmax,jedawaktu,penguraswaktu;
    TextView email1;
    Button BtnSubmit, BtnBack;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        getSupportActionBar().hide();
        minrh = findViewById((R.id.minrh));
        midrh = findViewById((R.id.midrh));
        mid2rh = findViewById((R.id.mid2rh));
        maxrh = findViewById((R.id.maxrh));
        minudara = findViewById((R.id.minudara));
        midudara = findViewById((R.id.midudara));
        mid2udara = findViewById((R.id.mid2udara));
        maxudara = findViewById((R.id.maxudara));
        mindaun = findViewById((R.id.mindaun));
        middaun = findViewById((R.id.middaun));
        mid2daun = findViewById((R.id.mid2daun));
        maxdaun = findViewById((R.id.maxdaun));
        minmdatmn = findViewById((R.id.minmdatmn));
        midmdatmn = findViewById((R.id.midmdatmn));
        mid2mdatmn = findViewById((R.id.mid2mdatmn));
        maxmdatmn = findViewById((R.id.maxmdatmn));
        minppm = findViewById((R.id.minppm));
        midppm = findViewById((R.id.midppm));
        mid2ppm = findViewById((R.id.mid2ppm));
        maxppm = findViewById((R.id.maxppm));
        minph = findViewById((R.id.minph));
        midph = findViewById((R.id.midph));
        mid2ph = findViewById((R.id.mid2ph));
        maxph = findViewById((R.id.maxph));
        oksigenmin = findViewById((R.id.oksigenmin));
        oksigenmid = findViewById((R.id.oksigenmid));
        oksigenmid2 = findViewById((R.id.oksigenmid2));
        oksigenmax = findViewById((R.id.oksigenmax));

        jedawaktu = findViewById((R.id.jedawaktu));
        penguraswaktu = findViewById((R.id.penguraswaktu));
        email1 = findViewById((R.id.email1));


        BtnSubmit = findViewById(R.id.BtnSubmit);
        BtnBack = findViewById(R.id.BtnBack);
        ambilData();

        BtnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = getIntent();
                String HWID = intent1.getStringExtra("HWID");
                Intent intent = new Intent(Pengaturan.this, Home.class);
                intent.putExtra("HWID", HWID);
                startActivity(intent);            }
        });

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
    }
    private void UpdateData(){
        final String min_rh = minrh.getText().toString().trim();
        final String mid_rh = midrh.getText().toString().trim();
        final String mid2_rh = mid2rh.getText().toString().trim();
        final String max_rh = maxrh.getText().toString().trim();
        final String min_suhu_udara = minudara.getText().toString().trim();
        final String mid_suhu_udara = midudara.getText().toString().trim();
        final String mid2_suhu_udara = mid2udara.getText().toString().trim();
        final String max_suhu_udara = maxudara.getText().toString().trim();
        final String min_suhu_daun = mindaun.getText().toString().trim();
        final String mid_suhu_daun = middaun.getText().toString().trim();
        final String mid2_suhu_daun = mid2daun.getText().toString().trim();
        final String max_suhu_daun = maxdaun.getText().toString().trim();
        final String min_media_tanam = minmdatmn.getText().toString().trim();
        final String mid_media_tanam = midmdatmn.getText().toString().trim();
        final String mid2_media_tanam = mid2mdatmn.getText().toString().trim();
        final String max_media_tanam = maxmdatmn.getText().toString().trim();
        final String min_ppm = minppm.getText().toString().trim();
        final String mid_ppm = midppm.getText().toString().trim();
        final String mid2_ppm = mid2ppm.getText().toString().trim();
        final String max_ppm = maxppm.getText().toString().trim();
        final String min_ph = minph.getText().toString().trim();
        final String mid_ph = midph.getText().toString().trim();
        final String mid2_ph = mid2ph.getText().toString().trim();
        final String max_ph = maxph.getText().toString().trim();
        final String min_oksigen = oksigenmin.getText().toString().trim();
        final String mid_oksigen = oksigenmid.getText().toString().trim();
        final String mid2_oksigen = oksigenmid2.getText().toString().trim();
        final String max_oksigen = oksigenmax.getText().toString().trim();

        final String jeda_pembacaan = jedawaktu.getText().toString().trim();
        final String waktu_penguras = penguraswaktu.getText().toString().trim();
        final String email = email1.getText().toString().trim();


        Intent intent = getIntent();
        String HWID = intent.getStringExtra("HWID");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://myfarm.lactograin.id/rest/updaterule/" + HWID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("sukses")) {
                            Intent intent1 = getIntent();
                            String HWID = intent1.getStringExtra("HWID");
                            Intent intent = new Intent(Pengaturan.this, Home.class);
                            Toast.makeText(getApplicationContext(), "Update Data Berhasil", Toast.LENGTH_LONG).show();
                            intent.putExtra("HWID", HWID);
                            startActivity(intent);
                            finish();
                        }
                        if (response.contains("gagal")) {

                            Toast.makeText(getApplicationContext(), "Update Data Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("min_rh", min_rh);
                params.put("mid_rh", mid_rh);
                params.put("mid2_rh", mid2_rh);
                params.put("max_rh", max_rh);
                params.put("min_suhu_udara", min_suhu_udara);
                params.put("mid_suhu_udara", mid_suhu_udara);
                params.put("mid2_suhu_udara", mid2_suhu_udara);
                params.put("max_suhu_udara", max_suhu_udara);
                params.put("min_suhu_daun", min_suhu_daun);
                params.put("mid_suhu_daun", mid_suhu_daun);
                params.put("mid2_suhu_daun", mid2_suhu_daun);
                params.put("max_suhu_daun", max_suhu_daun);
                params.put("min_media_tanam", min_media_tanam);
                params.put("mid_media_tanam", mid_media_tanam);
                params.put("mid2_media_tanam", mid2_media_tanam);
                params.put("max_media_tanam", max_media_tanam);
                params.put("min_ppm", min_ppm);
                params.put("mid_ppm", mid_ppm);
                params.put("mid2_ppm", mid2_ppm);
                params.put("max_ppm", max_ppm);
                params.put("min_ph", min_ph);
                params.put("mid_ph", mid_ph);
                params.put("mid2_ph", mid2_ph);
                params.put("max_ph", max_ph);
                params.put("min_oksigen", min_oksigen);
                params.put("mid_oksigen", mid_oksigen);
                params.put("mid2_oksigen", mid2_oksigen);
                params.put("max_oksigen", max_oksigen);

                params.put("jeda_pembacaan", jeda_pembacaan);
                params.put("waktu_penguras", waktu_penguras);
                params.put("email", email);

                params.put("HWID", HWID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void ambilData() {

        Intent intent = getIntent();
        String HWID = intent.getStringExtra("HWID");
        String url = "https://myfarm.lactograin.id/rest/rule/" + HWID;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pengaturan.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String min_rh = "";
        String mid_rh = "";
        String mid2_rh = "";
        String max_rh = "";
        String min_suhu_udara = "";
        String mid_suhu_udara = "";
        String mid2_suhu_udara = "";
        String max_suhu_udara = "";
        String min_suhu_daun = "";
        String mid_suhu_daun = "";
        String mid2_suhu_daun = "";
        String max_suhu_daun = "";
        String min_media_tanam = "";
        String mid_media_tanam = "";
        String mid2_media_tanam = "";
        String max_media_tanam = "";
        String min_ppm = "";
        String mid_ppm = "";
        String mid2_ppm = "";
        String max_ppm = "";
        String min_ph = "";
        String mid_ph = "";
        String mid2_ph = "";
        String max_ph = "";
        String min_oksigen = "";
        String mid_oksigen = "";
        String mid2_oksigen = "";
        String max_oksigen = "";

        String jeda_pembacaan = "";
        String waktu_penguras = "";
        String email = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("rule");
            JSONObject collegeData = result.getJSONObject(0);
            min_rh = collegeData.getString("min_rh");
            mid_rh = collegeData.getString("mid_rh");
            mid2_rh = collegeData.getString("mid2_rh");
            max_rh = collegeData.getString("max_rh");
            min_suhu_udara = collegeData.getString("min_suhu_udara");
            mid_suhu_udara = collegeData.getString("mid_suhu_udara");
            mid2_suhu_udara = collegeData.getString("mid2_suhu_udara");
            max_suhu_udara = collegeData.getString("max_suhu_udara");
            min_suhu_daun = collegeData.getString("min_suhu_daun");
            mid_suhu_daun = collegeData.getString("mid_suhu_daun");
            mid2_suhu_daun = collegeData.getString("mid2_suhu_daun");
            max_suhu_daun = collegeData.getString("max_suhu_daun");
            min_media_tanam = collegeData.getString("min_media_tanam");
            mid_media_tanam = collegeData.getString("mid_media_tanam");
            mid2_media_tanam = collegeData.getString("mid2_media_tanam");
            max_media_tanam = collegeData.getString("max_media_tanam");
            min_ppm = collegeData.getString("min_ppm");
            mid_ppm = collegeData.getString("mid_ppm");
            mid2_ppm = collegeData.getString("mid2_ppm");
            max_ppm = collegeData.getString("max_ppm");
            min_ph = collegeData.getString("min_ph");
            mid_ph = collegeData.getString("mid_ph");
            mid2_ph = collegeData.getString("mid2_ph");
            max_ph = collegeData.getString("max_ph");
            min_oksigen = collegeData.getString("min_oksigen");
            mid_oksigen = collegeData.getString("mid_oksigen");
            mid2_oksigen = collegeData.getString("mid2_oksigen");
            max_oksigen = collegeData.getString("max_oksigen");

            jeda_pembacaan = collegeData.getString("jeda_pembacaan");
            waktu_penguras = collegeData.getString("waktu_penguras");
            email = collegeData.getString("email");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        minrh.setText(min_rh);
        midrh.setText(mid_rh);
        mid2rh.setText(mid2_rh);
        maxrh.setText(max_rh);
        minudara.setText(min_suhu_udara);
        midudara.setText(mid_suhu_udara);
        mid2udara.setText(mid2_suhu_udara);
        maxudara.setText(max_suhu_udara);
        mindaun.setText(min_suhu_daun);
        middaun.setText(mid_suhu_daun);
        mid2daun.setText(mid2_suhu_daun);
        maxdaun.setText(max_suhu_daun);
        minmdatmn.setText(min_media_tanam);
        midmdatmn.setText(mid_media_tanam);
        mid2mdatmn.setText(mid2_media_tanam);
        maxmdatmn.setText(max_media_tanam);
        minppm.setText(min_ppm);
        midppm.setText(mid_ppm);
        mid2ppm.setText(mid2_ppm);
        maxppm.setText(max_ppm);
        minph.setText(min_ph);
        midph.setText(mid_ph);
        mid2ph.setText(mid2_ph);
        maxph.setText(max_ph);
        oksigenmin.setText(min_oksigen);
        oksigenmid.setText(mid_oksigen);
        oksigenmid2.setText(mid2_oksigen);
        oksigenmax.setText(max_oksigen);

        jedawaktu.setText(jeda_pembacaan);
        penguraswaktu.setText(waktu_penguras);
        email1.setText(email);


    }
}