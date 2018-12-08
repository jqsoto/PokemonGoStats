package com.example.pokemongostats.pokemongostats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkData();

        Log.v("tag", "message");

    }


    private void checkData(){

        loadJSON();
    }

    private String loadJSON(){
            String json = null;
            try {
                InputStream is = getAssets().open("prueba.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
    }

    private void chargeJSON(){
        try {
            JSONObject obj = new JSONObject(loadJSON());
            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //console.log("pfnpsdnaf");
                //String formula_value = jo_inside.getString("formule");
                //String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                //m_li = new HashMap<String, String>();
                //m_li.put("formule", formula_value);
                //m_li.put("url", url_value);

                //formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
