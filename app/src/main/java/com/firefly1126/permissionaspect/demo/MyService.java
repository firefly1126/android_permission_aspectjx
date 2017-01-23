package com.firefly1126.permissionaspect.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.hujiang.permissiondispatcher.NeedPermission;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                requestPermission();
            }
        }, 2000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @NeedPermission(
            permissions = { android.Manifest.permission.READ_PHONE_STATE }
    )
    private static void requestPermission() {
        Log.i("MyService", "requestPermission");
    }
}
