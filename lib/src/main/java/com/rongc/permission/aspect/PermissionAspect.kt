package com.rongc.permission.aspect

import android.util.Log
import com.rongc.permission.PermissionChecker
import com.rongc.permission.annotation.NeedPermission
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

    @Pointcut(
        "execution(* android.app.Activity.onCreate(..)) && !within(android.support.v7.app.AppCompatActivity)" +
                " && !within(android.support.v4.app.FragmentActivity)" +
                " && !within(android.support.v4.app.BaseFragmentActivityDonut)" +
                " && !within(com.hujiang.permissiondispatcher.ShadowPermissionActivity)"
    )
    fun pointcutOnActivityCreate() {
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
            println("Aspect method permission denied")
        })
    }
}

