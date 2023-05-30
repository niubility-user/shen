package com.jingdong.jdsdk;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class JdSdk {
    private static final String HARMONY_OS = "harmony";
    public static int PKG_TYPE_DEV = 0;
    public static int PKG_TYPE_FORMAL = 3;
    public static int PKG_TYPE_REGRESS = 1;
    public static int PKG_TYPE_TECH_GRAY = 4;
    public static int PKG_TYPE_VERSION_GRAY = 2;
    private static boolean hasLoadHMOsType;
    private static boolean isHarmonyOs;
    private static boolean isPrivacyOffline;
    private static volatile JdSdk mInstance;
    private Application mApplication;
    private boolean mBuildConfigDebug;
    private boolean isGoogleChannel = false;
    private boolean isArm64Only = false;
    private boolean appInitState = false;
    private int pkgType = PKG_TYPE_DEV;
    private AtomicBoolean mChannelStatus = new AtomicBoolean(false);
    private AtomicBoolean mArmStatus = new AtomicBoolean(false);
    private AtomicBoolean mPkgTypeStatus = new AtomicBoolean(false);
    private AtomicBoolean mPrivacyOfflineStatus = new AtomicBoolean(false);

    private JdSdk() {
    }

    public static synchronized JdSdk getInstance() {
        JdSdk jdSdk;
        synchronized (JdSdk.class) {
            if (mInstance == null) {
                mInstance = new JdSdk();
            }
            jdSdk = mInstance;
        }
        return jdSdk;
    }

    public static boolean isHarmonyOS() {
        if (!hasLoadHMOsType) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                isHarmonyOs = HARMONY_OS.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
            } catch (Throwable unused) {
            }
            hasLoadHMOsType = true;
        }
        return isHarmonyOs;
    }

    public boolean appInitialized() {
        return this.appInitState;
    }

    public Application getApplication() {
        Application application = this.mApplication;
        if (application != null) {
            return application;
        }
        throw new NullPointerException("mApplication is null, should call setApplication() when application init");
    }

    public Context getApplicationContext() {
        Application application = this.mApplication;
        if (application != null) {
            return application;
        }
        throw new NullPointerException("mApplication is null, should call setApplication() when application init");
    }

    public boolean getBuildConfigDebug() {
        return this.mBuildConfigDebug;
    }

    public int getPkgType() {
        return this.pkgType;
    }

    public boolean getPrivacyOffLineStatus() {
        return isPrivacyOffline;
    }

    public boolean isArm64Only() {
        return this.isArm64Only;
    }

    public boolean isGoogleChannel() {
        return this.isGoogleChannel;
    }

    public void setAppInitialized() {
        this.appInitState = true;
    }

    public synchronized void setApplication(Application application) {
        if (this.mApplication == null) {
            this.mApplication = application;
        }
    }

    public void setArm64Only(boolean z) {
        if (this.mArmStatus.compareAndSet(false, true)) {
            this.isArm64Only = z;
        }
    }

    public void setBuildConfigDebug(boolean z) {
        this.mBuildConfigDebug = z;
    }

    public void setGoogleChannel(boolean z) {
        if (this.mChannelStatus.compareAndSet(false, true)) {
            this.isGoogleChannel = z;
        }
    }

    public void setPkgType(int i2) {
        if (this.mPkgTypeStatus.compareAndSet(false, true)) {
            int i3 = PKG_TYPE_DEV;
            if (i2 < i3 || i2 > PKG_TYPE_TECH_GRAY) {
                i2 = i3;
            }
            this.pkgType = i2;
        }
    }

    public void setPrivacyOfflineStatus(boolean z) {
        if (this.mPrivacyOfflineStatus.compareAndSet(false, true)) {
            isPrivacyOffline = z;
        }
    }
}
