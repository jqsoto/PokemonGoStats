package com.example.pokemongostats.pokemongostats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailviewActivity extends AppCompatActivity {

    private static final String TAG = "DetailviewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);
        Log.d(TAG, "onCreate started");
    }

}
