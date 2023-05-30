package com.jingdong.jdpush_new.connect;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes12.dex */
public class MessageIntentService extends IntentService {
    private static String TAG = "MessageIntentService";
    private static ConcurrentLinkedQueue<a> msgQueue = new ConcurrentLinkedQueue<>();

    /* loaded from: classes12.dex */
    public static class a {
        private Intent a;

        public a(Intent intent) {
            this.a = intent;
        }

        public Intent a() {
            return this.a;
        }
    }

    public MessageIntentService() {
        super("MessageIntentService");
    }

    public static void addJob(a aVar) {
        if (aVar != null) {
            msgQueue.add(aVar);
        }
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        a poll;
        if (intent == null || (poll = msgQueue.poll()) == null) {
            return;
        }
        Intent a2 = poll.a();
        String action = a2.getAction();
        if ("com.jingdong.jdpush.MSG_CENTER".equals(action)) {
            int intExtra = a2.getIntExtra("bc_center_action_type", -1);
            if (TextUtils.equals(a2.getStringExtra("bc_center_action_packagename"), com.jingdong.jdpush_new.j.c.l(this)) && intExtra == 0) {
                com.jingdong.jdpush_new.j.g.b(TAG, "\u6536\u5230\u5fc3\u8df3\u5b9a\u65f6\u5668\u901a\u77e5");
                if (!c.c().f()) {
                    com.jingdong.jdpush_new.j.g.c("longConnection thread not started, start now");
                    c.c().k(getApplicationContext());
                    return;
                }
                f d = c.c().d();
                if (d == null) {
                    com.jingdong.jdpush_new.j.g.c("longConnection is null");
                    c.c().g();
                } else if (d.c() != null) {
                    d.c().k();
                }
            }
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            c.c().h();
        } else if ("android.intent.action.SCREEN_ON".equals(action)) {
            c.c().g();
        }
    }
}
