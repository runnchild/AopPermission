package com.rongc.aoppermission.app

import android.annotation.SuppressLint
import com.blankj.utilcode.util.PermissionUtils
import com.rongc.permission.IPermission

/**
 * @description 具体权限请求方法可以换成自己想要的
 * @author rongc
 * @date 20-8-18
 * @update
 */
class PermissionC : IPermission {

    override fun isGranted(permission: Array<String>): Boolean {
        return false
    }

    @SuppressLint("WrongConstant")
    override fun request(
        permission: Array<String>,
        granted: (Array<String>) -> Unit,
        denied: (Array<String>) -> Unit
    ) {
        PermissionUtils.permission(*permission)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {
                    granted(granted.toTypedArray())
                }

                override fun onDenied(
                    deniedForever: MutableList<String>,
                    denieds: MutableList<String>
                ) {
                    denied(denieds.toTypedArray())
                }
            })
    }
}