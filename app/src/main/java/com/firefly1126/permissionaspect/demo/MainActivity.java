package com.firefly1126.permissionaspect.demo;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.hujiang.permissiondispatcher.NeedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String[] PERMISSION_GROUP_CONTACT = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        findViewById(R.id.add_contact_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDummyContact();
            }
        });
        findViewById(R.id.get_contact_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContacts(v);
            }
        });
        findViewById(R.id.b_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBActivity("dddd", 2324l);
            }
        });
        findViewById(R.id._fragment_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });
    }

    @NeedPermission(permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    private void startBActivity(String name, long id) {
        startActivity(new Intent(MainActivity.this, BActivity.class));
    }

    public void showContacts(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions();
        } else {
            showContactDetails();
        }
    }

    private void requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("xxx要访问你的通讯录，是否允许？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, PERMISSION_GROUP_CONTACT, 1);
                        }
                    }).create();
            alertDialog.show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_GROUP_CONTACT, 1);
        }
    }

    private void showContactDetails() {
        String[] projection = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        String order = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC";

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, order);
        if (cursor != null) {
            final int totalCount = cursor.getCount();
            if (totalCount > 0) {
                cursor.moveToFirst();
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this, "联系人：" + name, Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        }
    }

    private static String DUMMY_CONTACT_NAME = "__DUMMY CONTACT from runtime permissions sample";

    @NeedPermission(permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    private void insertDummyContact() {
        ArrayList<ContentProviderOperation> operations = new ArrayList<>(2);
        ContentProviderOperation.Builder op = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
        operations.add(op.build());

        op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, DUMMY_CONTACT_NAME);
        operations.add(op.build());

        ContentResolver resolver = getContentResolver();
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            boolean permissionEnable = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            Toast.makeText(this, permissionEnable ? "已经获取联系人权限" : "禁止获取联系人权限", Toast.LENGTH_SHORT).show();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
