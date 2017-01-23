package com.firefly1126.permissionaspect.demo

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)
        findViewById(R.id.permission_btn)?.setOnClickListener { startActivity(Intent(this@MainActivity, PermissionActivity::class.java)) }
    }
}
