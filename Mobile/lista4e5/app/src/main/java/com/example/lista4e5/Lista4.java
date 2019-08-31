package com.example.lista4e5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.sql.Time;

public class Lista4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista4);
        long millis = System.currentTimeMillis();
        Time time = new Time(millis);
        int hour = time.getHours();
        if (hour >= 9)
            findViewById(R.id.HorarioDas9).setVisibility(View.VISIBLE);
        if (hour >= 10)
            findViewById(R.id.HorarioDas10).setVisibility(View.VISIBLE);
        if (hour >= 11)
            findViewById(R.id.HorarioDas11).setVisibility(View.VISIBLE);
        if (hour >= 13)
            findViewById(R.id.HorarioDas13).setVisibility(View.VISIBLE);
        if (hour >= 14)
            findViewById(R.id.HorarioDas14).setVisibility(View.VISIBLE);
        if (hour >= 15)
            findViewById(R.id.HorarioDas15).setVisibility(View.VISIBLE);
        if (hour >= 16)
            findViewById(R.id.HorarioDas16).setVisibility(View.VISIBLE);
        if (hour >= 17)
            findViewById(R.id.HorarioDas17).setVisibility(View.VISIBLE);
    }
}
