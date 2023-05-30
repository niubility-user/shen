package com.jingdong.pdj.libdjbasecore.app;

import android.app.Application;
import android.content.Context;

/* loaded from: classes7.dex */
public class BaseCoreHelper {
    private static volatile BaseCoreHelper mInstance;
    private Application mApplication;

    public static synchronized BaseCoreHelper getInstance() {
        BaseCoreHelper baseCoreHelper;
        synchronized (BaseCoreHelper.class) {
            if (mInstance == null) {
                mInstance = new BaseCoreHelper();
            }
            baseCoreHelper = mInstance;
        }
        return baseCoreHelper;
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

    public synchronized void setApplication(Application application) {
        if (this.mApplication == null) {
            this.mApplication = application;
        }
    }
}
