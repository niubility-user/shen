package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class JumpUtils {
    private static String jumpDes = "jdreactcommon";
    private static String jumpProtocalHeader = "openapp.jdmobile";

    public static String getJumpDes() {
        return jumpDes;
    }

    public static String getJumpProtocalHeader() {
        return jumpProtocalHeader;
    }

    public static boolean isJumpProtocalHeader(String str) {
        String lowerCase;
        return (TextUtils.isEmpty(str) || (lowerCase = str.toLowerCase()) == null || !lowerCase.startsWith(jumpProtocalHeader)) ? false : true;
    }

    public static void setJumpDes(String str) {
        jumpDes = str;
    }

    public static void setJumpProtocalHeader(String str) {
        jumpProtocalHeader = str;
    }
}
