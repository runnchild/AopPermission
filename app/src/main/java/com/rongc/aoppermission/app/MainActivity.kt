package com.rongc.aoppermission.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.rongc.permission.PermissionChecker
import com.rongc.permission.annotation.NeedPermission
import com.rongc.permission.annotation.OnDenied

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionChecker.iPermission = PermissionC()

        check()
    }

    @NeedPermission([PermissionConstants.CAMERA, PermissionConstants.PHONE])
    fun check() {
        Log.e("MainActivity", "Aspect check invoke")
    }

    @OnDenied
    fun onDenied(denied: Array<String>) {
        LogUtils.e("onDenied$denied")
    }
}