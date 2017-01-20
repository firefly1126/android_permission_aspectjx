/*
 * PermissionAspect      2016-05-11
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect.aspect;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.firefly1126.permissionaspect.CheckPermissionItem;
import com.firefly1126.permissionaspect.PermissionCheckSDK;
import com.hujiang.permissiondispatcher.CheckPermission;
import com.hujiang.permissiondispatcher.NeedPermission;
import com.hujiang.permissiondispatcher.PermissionListener;

import junit.framework.Assert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-11
 */
@Aspect
public class PermissionAspect {

    private static Map<String, CheckPermissionItem> checkPermissionItems = new HashMap<String, CheckPermissionItem>();

    @Pointcut("execution(@com.hujiang.permissiondispatcher.NeedPermission * *(..)) && @annotation(needPermission)")
    public void pointcutOnNeedPermissionMethod(NeedPermission needPermission) {

    }

    @Pointcut("execution(* android.app.Activity.onCreate(..))")
    public void pointcutOnActivityCreate() {

    }

    @Around("pointcutOnNeedPermissionMethod(needPermission)")
    public void adviceOnNeedPermissionMethod(final ProceedingJoinPoint joinPoint, NeedPermission needPermission) throws Throwable {
        Log.i("AOP", joinPoint.getSignature().toString());

        if (needPermission != null) {
            String[] permissions = needPermission.permissions();
            if (permissions != null && permissions.length > 0) {
                CheckPermission checkPermission = CheckPermission.from(PermissionCheckSDK.application).setPermissions(permissions);
                if (!TextUtils.isEmpty(needPermission.rationalMessage())
                        && !TextUtils.isEmpty(needPermission.rationalButton())) {
                    checkPermission.setRationaleMsg(needPermission.rationalMessage())
                            .setRationaleConfirmText(needPermission.rationalButton());
                }

                if (!TextUtils.isEmpty(needPermission.deniedMessage())
                        && !TextUtils.isEmpty(needPermission.deniedButton())) {
                    checkPermission.setDeniedMsg(needPermission.deniedMessage())
                            .setDeniedCloseButtonText(needPermission.deniedButton());
                }
                checkPermission.check(new PermissionListener() {
                    @Override
                    public void permissionGranted() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void permissionDenied() {

                    }
                });
            }
        }
    }

    @Around("pointcutOnActivityCreate()")
    public void adviceOnActivityCreate(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("AOP", joinPoint.getSignature().toString());

        final Activity target = (Activity) joinPoint.getTarget();

        NeedPermission needPermission = target.getClass().getAnnotation(NeedPermission.class);
        if (needPermission != null) {
            String[] permissions = needPermission.permissions();
            if (permissions != null && permissions.length > 0) {
                processCheckPermissionOnActivity(target, permissions, needPermission.rationalMessage()
                        , needPermission.rationalButton()
                        , needPermission.deniedMessage()
                        , needPermission.deniedButton());
            }
        } else {
            String classPath = joinPoint.getSourceLocation().getWithinType().getName();

            if (checkPermissionItems.containsKey(classPath)) {
                CheckPermissionItem checkPermissionItem = checkPermissionItems.get(classPath);
                processCheckPermissionOnActivity(target, checkPermissionItem.permissions, checkPermissionItem.rationalMessage
                        , checkPermissionItem.rationalButton
                        , checkPermissionItem.deniedMessage
                        , checkPermissionItem.deniedButton);
            }
        }


        joinPoint.proceed();
    }

    private static void processCheckPermissionOnActivity(final Activity target, String[] permissions, String rationalMessage, String rationalButton
            , String deniedMessage, String deniedButton) {

        Assert.assertTrue(permissions != null && permissions.length > 0);

        CheckPermission checkPermission = CheckPermission.from(target).setPermissions(permissions);
        if (!TextUtils.isEmpty(rationalMessage)
                && !TextUtils.isEmpty(rationalButton)) {
            checkPermission.setRationaleMsg(rationalMessage)
                    .setRationaleConfirmText(rationalButton);
        }

        if (!TextUtils.isEmpty(deniedMessage)
                && !TextUtils.isEmpty(deniedButton)) {
            checkPermission.setDeniedMsg(deniedMessage)
                    .setDeniedCloseButtonText(deniedButton);
        }
        checkPermission.check(new PermissionListener() {
            @Override
            public void permissionGranted() {

            }

            @Override
            public void permissionDenied() {
                target.finish();
            }
        });
    }

    public static void addCheckPermissionItem(CheckPermissionItem item) {
        if (item != null && item.classPath != null) {
            checkPermissionItems.put(item.classPath, item);
        }
    }
}