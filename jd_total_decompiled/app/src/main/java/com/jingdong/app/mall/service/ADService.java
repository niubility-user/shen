package com.jingdong.app.mall.service;

import android.app.IntentService;
import android.content.Intent;
import com.jingdong.app.mall.ad.c;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes4.dex */
public class ADService extends IntentService {
    public ADService() {
        super("ADService");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        c.f();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            return;
        }
        if (Log.D) {
            Log.d("ADService", "do something... foreToBackTime: " + intent.getExtras().getLong("foreToBackTime"));
        }
        c.l().h();
    }
}
