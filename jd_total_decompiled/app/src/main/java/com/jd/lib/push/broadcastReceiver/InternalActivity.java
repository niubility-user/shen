package com.jd.lib.push.broadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.l;

/* loaded from: classes16.dex */
public class InternalActivity extends Activity {
    private void parseIntent(Intent intent) {
        try {
            try {
                String string = intent.getExtras().getString("msg");
                if (!TextUtils.isEmpty(string)) {
                    g.a("onNotificationMessageClicked " + string);
                    ManufacturePushMessageHandler.handleMessage(this, string, l.a());
                } else {
                    g.c("\u9519\u8bef\uff1aintent\u91cc\u4e0d\u5305\u542bmsg\u5b57\u6bb5");
                }
            } catch (Exception e2) {
                g.f("\u4f20\u9012\u6570\u636e\u5f02\u5e38 ", e2);
                PushMessageHandler.jumpDefault(this);
            }
        } finally {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        g.d("InternalActivity", "onCreate");
        parseIntent(getIntent());
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        g.d("InternalActivity", "onNewIntent");
        parseIntent(intent);
        super.onNewIntent(intent);
    }
}
