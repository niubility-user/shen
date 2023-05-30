package com.jingdong.common.ui;

import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes6.dex */
public class JdDialogUtils {
    public static int halfDialogMaxHeight() {
        double screenHeight = UnDeviceInfo.getScreenHeight();
        Double.isNaN(screenHeight);
        return (int) (screenHeight * 0.85d);
    }
}
