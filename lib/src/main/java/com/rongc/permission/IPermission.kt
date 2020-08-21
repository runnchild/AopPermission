package com.rongc.permission

/**
 * @description 权限请求操作接口
 * @author rongc
 * @date 20-8-18
 * @update
 */
interface IPermission {
    fun isGranted(permission: Array<String>): Boolean
    fun request(permission: Array<String>, granted: (Array<String>) -> Unit, denied: (Array<String>) -> Unit)
}