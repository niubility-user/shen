package com.xiaomi.mipush.sdk.help;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.b1;

/* loaded from: classes11.dex */
public class HelpService extends IntentService {
    public HelpService() {
        super("intentService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("awake_info"))) {
            return;
        }
        b1.a(this, intent, null);
    }
}
