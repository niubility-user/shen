package com.tencent.smtt.export.external;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class DexClassLoaderProviderService extends Service {
    private static final String LOGTAG = "dexloader";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DexClassLoaderProvider.setForceLoadDexFlag(true, this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        System.exit(0);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        ArrayList<String> stringArrayListExtra;
        String str = "DexClassLoaderProviderService -- onStartCommand(" + intent + ")";
        if (intent == null) {
            return 2;
        }
        try {
            stringArrayListExtra = intent.getStringArrayListExtra("dex2oat");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (stringArrayListExtra == null) {
            return 2;
        }
        String str2 = stringArrayListExtra.get(1);
        String str3 = stringArrayListExtra.get(2);
        String str4 = stringArrayListExtra.get(3);
        String str5 = "DexClassLoaderProviderService -- onStartCommand(" + stringArrayListExtra.get(0) + ")";
        ClassLoader classLoader = getClassLoader();
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        DexClassLoaderProvider.createDexClassLoader(str2, str3, str4, classLoader, getApplicationContext());
        return 2;
    }
}
