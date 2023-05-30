package jd.wjlogin_sdk.common;

/* loaded from: classes.dex */
public final class DevelopType {
    public static final int BETA = 2;
    public static final int DEBUG = 1;
    public static final int PRODUCT = 0;
    private static int a;

    public static int getDebugModel() {
        return a;
    }

    public static void setDebugModel(int i2) {
        a = i2;
    }
}
