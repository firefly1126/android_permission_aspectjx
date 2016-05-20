/*
 * BActivity      2016-05-13
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hujiang.permissiondispatcher.NeedPermission;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-13
 */
@NeedPermission(permissions = {Manifest.permission.READ_CONTACTS
        , Manifest.permission.WRITE_CONTACTS})
public class BActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.b_activity_layout);

        findViewById(R.id.c_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BActivity.this, CActivity.class));
            }
        });
    }
}