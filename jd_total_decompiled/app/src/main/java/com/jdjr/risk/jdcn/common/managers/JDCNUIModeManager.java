package com.jdjr.risk.jdcn.common.managers;

/* loaded from: classes18.dex */
public final class JDCNUIModeManager {
    public static final int MODE_DARK = 1;
    public static final int MODE_LIGHT = 0;
    private static JDCNUIModeManager instance;
    private int mUiMode = 0;

    private JDCNUIModeManager() {
    }

    public static JDCNUIModeManager getInstance() {
        if (instance == null) {
            synchronized (JDCNUIModeManager.class) {
                if (instance == null) {
                    instance = new JDCNUIModeManager();
                }
            }
        }
        return instance;
    }

    public final synchronized int getUIMode() {
        return this.mUiMode;
    }

    public final synchronized void switch2DarkMode() {
        this.mUiMode = 1;
    }

    public final synchronized void switch2LightMode() {
        this.mUiMode = 0;
    }
}
