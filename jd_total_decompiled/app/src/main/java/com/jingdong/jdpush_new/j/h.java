package com.jingdong.jdpush_new.j;

import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes12.dex */
public class h {
    private static boolean a;

    public static /* synthetic */ boolean a(Context context) {
        try {
            if (a) {
                return false;
            }
            String userAgent = CoreInfo.Device.getUserAgent(context);
            if (TextUtils.isEmpty(userAgent)) {
                return false;
            }
            a = true;
            n.a(context, 100, userAgent, c.d(context), BaseInfo.getAppPackageName());
            g.h("send ua event to broadcast : " + userAgent);
            return false;
        } catch (Exception e2) {
            g.g(e2);
            return false;
        }
    }

    public static void b(final Context context) {
        try {
            if (a || !com.jd.lib.push.utils.d.l()) {
                return;
            }
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.jingdong.jdpush_new.j.a
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return h.a(context);
                }
            });
        } catch (Exception e2) {
            g.g(e2);
        }
    }
}
