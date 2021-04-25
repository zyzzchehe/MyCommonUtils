package com.zc.mycommonutils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

import com.zc.commonutilslib.AlertDialogUtil;
import com.zc.commonutilslib.MLog;

import permison.PermissonUtil;
import permison.listener.PermissionListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissonUtil.checkPermission(this, new PermissionListener() {
            @Override
            public void havePermission() {
                MLog.i(TAG, "havePermission: ");
            }

            @Override
            public void requestPermissionFail() {
                MLog.i(TAG, "requestPermissionFail: ");
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}