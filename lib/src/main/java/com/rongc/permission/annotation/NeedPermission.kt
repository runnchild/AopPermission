package com.rongc.permission.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NeedPermission(val permissions: Array<String>) {
}