package com.jd.lib.cashier.sdk.app;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.jd.lib.cashier.sdk.core.utils.h;

/* loaded from: classes.dex */
public class CashierApplication extends Application {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        h.a(this);
    }
}
