package com.jingdong.common.utils;

/* loaded from: classes6.dex */
public class ShareValues {
    public static final String DEFAULT_ICON_URL = "https://img13.360buyimg.com/imagetools/jfs/t1/189368/3/15529/5234/61027be2E1839a80b/89359166c7a0c3aa.png";
    public static final String SHARE_DEFAULT_WX_CONTENT = "\u6211\u5728\u4eac\u4e1c\u53d1\u73b0\u4e86\u4e00\u4e2a\u4e0d\u9519\u7684\u5546\u54c1\uff0c\u8d76\u5feb\u6765\u770b\u770b\u5427";
    private static int fullScreenModeCheckValue = 10000;
    private static float fullScreenModeScaleValue = 1.0f;
    public static boolean isDev;
    private static boolean isFullScreenPhone1700;
    public static boolean isNewWeiXinShareUI;
    private static boolean isScreenOrientationLandscape;
    public static boolean newAddUrlQuerySwitch;
    public static String wxJsonParam;

    public static int getFullScreenModeCheckValue() {
        return fullScreenModeCheckValue;
    }

    public static float getFullScreenModeScaleValue() {
        return fullScreenModeScaleValue;
    }

    public static boolean isIsFullScreenPhone1700() {
        return isFullScreenPhone1700;
    }

    public static boolean isLandscapeMode() {
        return isScreenOrientationLandscape;
    }

    public static void reset() {
        isScreenOrientationLandscape = false;
    }

    public static void setFullScreenModeCheckValue(int i2) {
        fullScreenModeCheckValue = i2;
    }

    public static void setFullScreenModeScaleValue(float f2) {
        fullScreenModeScaleValue = f2;
    }

    public static void setIsFullScreenPhone1700(boolean z) {
        isFullScreenPhone1700 = z;
    }

    public static void updateScreenOrientation(boolean z) {
        isScreenOrientationLandscape = z;
    }
}
