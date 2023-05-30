package com.jingdong.jdpush_new;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.j;
import com.jingdong.jdpush_new.j.m;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public abstract class PushIntentService extends IntentService {
    private static ConcurrentLinkedQueue<a> msgQueue = new ConcurrentLinkedQueue<>();

    /* loaded from: classes12.dex */
    public static class a {
        private PushMessageReceiver a;
        private Intent b;

        public a(PushMessageReceiver pushMessageReceiver, Intent intent) {
            this.a = pushMessageReceiver;
            this.b = intent;
        }

        public Intent a() {
            return this.b;
        }

        public PushMessageReceiver b() {
            return this.a;
        }
    }

    public PushIntentService() {
        super("JDPushIntentServiceThread");
    }

    public static void addJob(a aVar) {
        if (aVar != null) {
            msgQueue.add(aVar);
        }
    }

    private boolean isJDMsg(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("sign");
            String optString2 = jSONObject.optString(NotificationMessageSummary.MSG_BODY);
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return false;
            }
            return j.b(optString2).equals(new String(j.a(m.g(optString), j.a), "UTF-8"));
        } catch (Exception e2) {
            g.g(e2);
            return false;
        }
    }

    @Override // android.app.IntentService
    @RequiresApi(api = 19)
    protected void onHandleIntent(Intent intent) {
        a poll;
        if (intent == null || (poll = msgQueue.poll()) == null) {
            return;
        }
        poll.b();
        Intent a2 = poll.a();
        if (com.jingdong.jdpush_new.j.c.d(this).equals(a2.getStringExtra("bc_app_id"))) {
            int intExtra = a2.getIntExtra("bc_app_action_type", -1);
            if (intExtra == 2) {
                String stringExtra = a2.getStringExtra("bc_app_action_msg");
                if (isJDMsg(stringExtra)) {
                    onMessageArrived(this, stringExtra);
                } else {
                    g.c("invalid msg.");
                }
            } else if (intExtra == 6) {
                String stringExtra2 = a2.getStringExtra("bc_app_action_msg");
                if (isJDMsg(stringExtra2)) {
                    com.jingdong.jdpush_new.j.b.a().b(this, stringExtra2);
                }
            } else if (intExtra != 100) {
            } else {
                String stringExtra3 = a2.getStringExtra("bc_app_action_msg");
                g.h("broadcast receive and set ua : " + stringExtra3);
                if (TextUtils.isEmpty(stringExtra3)) {
                    return;
                }
                BaseInfo.setUserAgent(stringExtra3);
            }
        }
    }

    public abstract void onMessageArrived(Context context, String str);
}
