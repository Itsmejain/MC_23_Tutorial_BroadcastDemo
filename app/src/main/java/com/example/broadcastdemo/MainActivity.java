package com.example.broadcastdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DynamicBroadcastReceiver dynamicBroadcastReceiver = new DynamicBroadcastReceiver();
    CustomBroadcastReceiver customBroadcastReceiver = new CustomBroadcastReceiver();
    Button broadcastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: MAIN ACTIVITY");
        broadcastButton = findViewById(R.id.broadcastButton);
        broadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "SEND BROADCAST INITITATED", Toast.LENGTH_SHORT).show();
                Intent customIntent = new Intent("com.example.broadcastdemo.CUSTOM_INTENT");
                customIntent.putExtra("MESSAGEVAL","THIS IS CUSTOM BROADCAST");
                sendBroadcast(customIntent);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(dynamicBroadcastReceiver,intentFilter);


        IntentFilter customIntentFilter = new IntentFilter("com.example.broadcastdemo.CUSTOM_INTENT");
        registerReceiver(customBroadcastReceiver,customIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(dynamicBroadcastReceiver);
    }

    private class CustomBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, ""+intent.getAction(), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, ""+intent.getStringExtra("MESSAGEVAL"), Toast.LENGTH_SHORT).show();
        }
    }
}