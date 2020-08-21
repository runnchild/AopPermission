package com.rongc.aoppermission.app

import com.rongc.permission.IPermission

/**
 * @description 作用描述
 * @author rongc
 * @date 20-8-18
 * @update
 */
class PermissionC: IPermission {

    override fun isGranted(permission: Array<String>): Boolean {
        return false
    }

    override fun request(permission: Array<String>, granted: (Array<String>) -> Unit, denied: (Array<String>) -> Unit) {
        granted(permission)
        denied(permission)
    }
}