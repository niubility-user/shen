package jd.wjlogin_sdk.config;

/* loaded from: classes.dex */
public final class ConfigHostMode {
    public static final int BETA = 1;
    public static final int PRODUCT = 0;
    private static int a;

    public static int getConfigMode() {
        return a;
    }

    public static void setConfigMode(int i2) {
        a = i2;
    }
}
