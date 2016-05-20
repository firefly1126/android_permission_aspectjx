/*
 * AOPApplication      2016-05-13
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.firefly1126.permissionaspect.PermissionCheckSDK;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-13
 */
public class AOPApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PermissionCheckSDK.init(this);
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}