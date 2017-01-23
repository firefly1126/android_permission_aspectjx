/*
 * BActivity      2016-05-13
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.demo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hujiang.permissiondispatcher.NeedPermission;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-13
 */

@NeedPermission(permissions = {Manifest.permission.CAMERA})
public class BActivity extends AppCompatActivity {

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