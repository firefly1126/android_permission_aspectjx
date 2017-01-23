/*
 * AOPApplication      2016-05-13
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.demo

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.firefly1126.permissionaspect.PermissionCheckSDK

/**
 * class description here

 * @author simon
 * *
 * @version 1.0.0
 * *
 * @since 2016-05-13
 */
class AOPApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        PermissionCheckSDK.init(this@AOPApplication)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }

    //    @NeedPermission(permissions = {Manifest.permission.READ_PHONE_STATE})
    //    private void requestPermission() {
    //    }
}