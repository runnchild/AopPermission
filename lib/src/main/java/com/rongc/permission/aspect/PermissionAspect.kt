package com.rongc.permission.aspect

import android.util.Log
import com.rongc.permission.PermissionChecker
import com.rongc.permission.annotation.NeedPermission
import com.rongc.permission.annotation.OnDenied
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class PermissionAspect {

    companion object {
    }

    @Pointcut("execution(@com.rongc.permission.annotation.NeedPermission * *(..)) && @annotation(needPermission)")
    fun pointcutOnNeedPermissionMethod(needPermission: NeedPermission) {
    }

    /**
     * 用在返回为void的方法上，包括private, public , static等修饰的方法
     */
    @Around("pointcutOnNeedPermissionMethod(needPermission)")
    fun adviceOnNeedPermissionMethod(
        joinPoint: ProceedingJoinPoint,
        needPermission: NeedPermission
    ) {
        val permissions = needPermission.permissions
        Log.e("Aspect", "method before: ${permissions[0]}")
        println("Aspect method before: ${permissions[0]}")
        PermissionChecker.request(permissions, {
            joinPoint.proceed()
        }, {
            val deniedPermissions = it
            val clz = joinPoint.`this`
            println("${clz}Aspect method permission denied")
            clz::class.java.declaredMethods.forEach { method ->
                if (null != method.getAnnotation(OnDenied::class.java)) {
                    method.isAccessible = true
                    if (method.parameterAnnotations.isEmpty()) {
                        method.invoke(clz)
                    } else if (method.parameterAnnotations[0] is Array<*>) {
                        method.invoke(clz, deniedPermissions)
                    }
                }
            }
        })
    }
}

