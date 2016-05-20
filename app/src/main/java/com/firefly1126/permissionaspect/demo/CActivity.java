/*
 * CActivity      2016-05-13
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.demo;

import android.app.Activity;
import android.os.Bundle;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-13
 */
public class CActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.c_activity_layout);
    }
}