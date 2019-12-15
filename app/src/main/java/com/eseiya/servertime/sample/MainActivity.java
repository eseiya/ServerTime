package com.eseiya.servertime.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.eseiya.servertime.ServerTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long serverTime = System.currentTimeMillis();
        ServerTimeUtils.setServerTime(serverTime);
        TextView timeTxt = findViewById(R.id.time_txt);
        Button updateTimeBtn = findViewById(R.id.update_time_btn);
        updateTimeBtn.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date(ServerTimeUtils.getServerTime());
            timeTxt.setText(simpleDateFormat.format(date));
            Log.i(TAG, "server time : " + ServerTimeUtils.getServerTime());
        });
        updateTimeBtn.performClick();
    }
}
