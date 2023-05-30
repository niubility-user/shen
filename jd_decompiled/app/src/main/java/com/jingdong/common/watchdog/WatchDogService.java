package com.jingdong.common.watchdog;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes6.dex */
public class WatchDogService extends IntentService {
    public WatchDogService() {
        super("WatchDogService");
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || !JDPrivacyHelper.isAcceptPrivacy(this)) {
            return;
        }
        String stringExtra = intent.getStringExtra("source");
        String name = getClass().getName();
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "";
        }
        JDMtaUtils.onClick(this, "App_BeWaked", name, stringExtra);
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return super.onStartCommand(intent, i2, i3);
    }
}
