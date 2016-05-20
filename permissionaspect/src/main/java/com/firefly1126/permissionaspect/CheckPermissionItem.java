/*
 * CheckPermissionItem      2016-05-16
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.firefly1126.permissionaspect;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-05-16
 */
public class CheckPermissionItem implements Serializable {

    public String classPath;
    public String[] permissions;
    public String rationalMessage;
    public String rationalButton;
    public String deniedMessage;
    public String deniedButton;

    public CheckPermissionItem(String classPath, String...permissions) {
        if (TextUtils.isEmpty(classPath)) {
            throw new IllegalArgumentException("classPath must not be null or empty");
        }

        if (permissions == null || permissions.length <= 0) {
            throw new IllegalArgumentException("permissions must have one content at least");
        }

        this.classPath = classPath;
        this.permissions = permissions;
    }

    public CheckPermissionItem rationalMessage(String rationalMessage) {
        this.rationalMessage = rationalMessage;

        return this;
    }

    public CheckPermissionItem rationalButton(String rationalButton) {
        this.rationalButton = rationalButton;

        return this;
    }

    public CheckPermissionItem deniedMessage(String deniedMessage) {
        this.deniedMessage = deniedMessage;

        return this;
    }

    public CheckPermissionItem deniedButton(String deniedButton) {
        this.deniedButton = deniedButton;

        return this;
    }
}