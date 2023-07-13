package com.tesis.fuzzybayes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class historysensor extends AppCompatActivity {
    public class SensorData  {
        private String rh;
        private String suhuUdara;
        private String suhuDaun;
        private String mediaTanam;
        private String ppm;
        private String ph;
        private String oksigen;
        private String time;

        public SensorData(String rh, String suhuUdara, String suhuDaun, String mediaTanam, String ppm, String ph, String oksigen, String time) {
            this.rh = rh;
            this.suhuUdara = suhuUdara;
            this.suhuDaun = suhuDaun;
            this.mediaTanam = mediaTanam;
            this.ppm = ppm;
            this.ph = ph;
            this.oksigen = oksigen;
            this.time = time;
        }

        public String getRh() {
            return rh;
        }

        public String getSuhuUdara() {
            return suhuUdara;
        }

        public String getSuhuDaun() {
            return suhuDaun;
        }

        public String getMediaTanam() {
            return mediaTanam;
        }

        public String getPpm() {
            return ppm;
        }

        public String getPh() {
            return ph;
        }

        public String getOksigen() {
            return oksigen;
        }

        public String getTime() {
            return time;
        }
    }
    public class SensorDataAdapter extends ArrayAdapter<SensorData> {

        private List<SensorData> dataList;

        public SensorDataAdapter(Context context, List<SensorData> dataList) {
            super(context, 0, dataList);
            this.dataList = dataList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            TextView timeTextView = convertView.findViewById(R.id.time_text_view);
            TextView rhTextView = convertView.findViewById(R.id.rh_text_view);
            TextView suhuUdaraTextView = convertView.findViewById(R.id.suhu_udara_text_view);
            TextView suhuDaunTextView = convertView.findViewById(R.id.suhu_daun_text_view);
            TextView mediaTanamTextView = convertView.findViewById(R.id.media_tanam_text_view);
            TextView ppmTextView = convertView.findViewById(R.id.ppm_text_view);
            TextView phTextView = convertView.findViewById(R.id.ph_text_view);
            TextView oksigenTextView = convertView.findViewById(R.id.oksigen_text_view);

            SensorData data = dataList.get(position);

            timeTextView.setText(data.getTime());
            rhTextView.setText(data.getRh());
            suhuUdaraTextView.setText(data.getSuhuUdara());
            suhuDaunTextView.setText(data.getSuhuDaun());
            mediaTanamTextView.setText(data.getMediaTanam());
            ppmTextView.setText(data.getPpm());
            phTextView.setText(data.getPh());
            oksigenTextView.setText(data.getOksigen());

            return convertView;
        }
    }
    Button pindahRumah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historysensor);
        pindahRumah = findViewById(R.id.pindahRumah);

        pindahRumah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String HWID = null;
                if (bundle != null) {
                    HWID = bundle.getString("HWID");
                }
                Intent intent = new Intent(historysensor.this, Home.class);
                intent.putExtra("HWID", HWID);
                startActivity(intent);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String HWID = intent.getStringExtra("HWID");
        String url = "https://myfarm.lactograin.id/rest/log_sensor/" + HWID;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<SensorData> data = new ArrayList<>();
                    String jsonData = response.toString();

                    try {
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray jsonArray = jsonObject.getJSONArray("log_sensor");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sensorJson = jsonArray.getJSONObject(i);
                        SensorData sensorData = new SensorData(
                                sensorJson.getString("rh"),
                                sensorJson.getString("suhu_udara"),
                                sensorJson.getString("suhu_daun"),
                                sensorJson.getString("media_tanam"),
                                sensorJson.getString("ppm"),
                                sensorJson.getString("ph"),
                                sensorJson.getString("oksigen"),
                                sensorJson.getString("time")
                        );
                        data.add(sensorData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SensorDataAdapter adapter = new SensorDataAdapter(historysensor.this, data);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(historysensor.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }

}