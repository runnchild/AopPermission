package com.rongc.permission

/**
 * @description 权限请求
 * @author rongc
 * @date 20-8-18
 * @update
 */
class PermissionChecker {
    companion object {
        var iPermission: IPermission? = null

        fun isGranted(permission: Array<String>): Boolean {
            return iPermission?.isGranted(permission) ?:false
        }

        fun request(permission: Array<String>, granted: (Array<String>) -> Unit, denied: (Array<String>) -> Unit) {
            iPermission?.request(permission, granted, denied)
        }
    }
}