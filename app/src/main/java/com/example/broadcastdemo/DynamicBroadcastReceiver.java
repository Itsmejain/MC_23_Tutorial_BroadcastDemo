package com.example.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class DynamicBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive: DYNAMIC BROADCAST RECEIVER WORKING ");
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            boolean conntectivityStatus = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if(!conntectivityStatus){
                Toast.makeText(context, "CONNECTIVITY CHANGED- CONNECTED", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "CONNECTIVITY CHANGED - DISCONNECTED", Toast.LENGTH_LONG).show();
            }
        }
    }
}
