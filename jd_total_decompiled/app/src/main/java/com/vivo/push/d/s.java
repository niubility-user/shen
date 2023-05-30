package com.vivo.push.d;

import android.content.Context;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.HashMap;

/* loaded from: classes11.dex */
public final class s implements Runnable {
    final /* synthetic */ InsideNotificationItem a;
    final /* synthetic */ com.vivo.push.b.q b;

    /* renamed from: c */
    final /* synthetic */ r f18267c;

    public s(r rVar, InsideNotificationItem insideNotificationItem, com.vivo.push.b.q qVar) {
        this.f18267c = rVar;
        this.a = insideNotificationItem;
        this.b = qVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        Context context6;
        char c2;
        Context context7;
        Context context8;
        Context context9;
        Context context10;
        Context context11;
        Context context12;
        Context context13;
        Context context14;
        r rVar = this.f18267c;
        PushMessageCallback pushMessageCallback = ((z) rVar).b;
        context = ((com.vivo.push.l) rVar).a;
        if (pushMessageCallback.onNotificationMessageArrived(context, com.vivo.push.util.q.a(this.a))) {
            StringBuilder sb = new StringBuilder("pkg name : ");
            context11 = ((com.vivo.push.l) this.f18267c).a;
            sb.append(context11.getPackageName());
            sb.append(" \u5e94\u7528\u4e3b\u52a8\u62e6\u622a\u901a\u77e5");
            com.vivo.push.util.p.b("OnNotificationArrivedTask", sb.toString());
            context12 = ((com.vivo.push.l) this.f18267c).a;
            com.vivo.push.util.p.b(context12, "\u5e94\u7528\u4e3b\u52a8\u62e6\u622a\u901a\u77e5\uff0c\u5bfc\u81f4\u901a\u77e5\u65e0\u6cd5\u5c55\u793a\uff0c\u5982\u9700\u6253\u5f00\u8bf7\u5728onNotificationMessageArrived\u4e2d\u8fd4\u56defalse");
            HashMap hashMap = new HashMap();
            hashMap.put("messageID", String.valueOf(this.b.f()));
            context13 = ((com.vivo.push.l) this.f18267c).a;
            context14 = ((com.vivo.push.l) this.f18267c).a;
            String b = com.vivo.push.util.z.b(context13, context14.getPackageName());
            if (!TextUtils.isEmpty(b)) {
                hashMap.put("remoteAppId", b);
            }
            com.vivo.push.util.e.a(2120L, hashMap);
            return;
        }
        int b2 = this.f18267c.b();
        if (b2 <= 0) {
            context2 = ((com.vivo.push.l) this.f18267c).a;
            InsideNotificationItem insideNotificationItem = this.a;
            long f2 = this.b.f();
            r rVar2 = this.f18267c;
            PushMessageCallback pushMessageCallback2 = ((z) rVar2).b;
            context3 = ((com.vivo.push.l) rVar2).a;
            com.vivo.push.util.k kVar = new com.vivo.push.util.k(context2, insideNotificationItem, f2, pushMessageCallback2.isAllowNet(context3), new t(this));
            boolean isShowBigPicOnMobileNet = this.a.isShowBigPicOnMobileNet();
            String purePicUrl = this.a.getPurePicUrl();
            if (TextUtils.isEmpty(purePicUrl)) {
                purePicUrl = this.a.getCoverUrl();
            }
            if (!TextUtils.isEmpty(purePicUrl)) {
                com.vivo.push.util.p.c("OnNotificationArrivedTask", "showCode=".concat(String.valueOf(isShowBigPicOnMobileNet)));
                if (!isShowBigPicOnMobileNet) {
                    context5 = ((com.vivo.push.l) this.f18267c).a;
                    com.vivo.push.util.p.a(context5, "mobile net unshow");
                    context6 = ((com.vivo.push.l) this.f18267c).a;
                    NetworkInfo a = com.vivo.push.util.r.a(context6);
                    if (a != null && a.getState() == NetworkInfo.State.CONNECTED) {
                        int type = a.getType();
                        c2 = type == 1 ? (char) 2 : type == 0 ? (char) 1 : (char) 3;
                    } else {
                        c2 = 0;
                    }
                    if (c2 == 1) {
                        purePicUrl = null;
                        this.a.clearCoverUrl();
                        this.a.clearPurePicUrl();
                    }
                } else {
                    context4 = ((com.vivo.push.l) this.f18267c).a;
                    com.vivo.push.util.p.a(context4, "mobile net show");
                }
            }
            kVar.execute(this.a.getIconUrl(), purePicUrl);
            return;
        }
        StringBuilder sb2 = new StringBuilder("pkg name : ");
        context7 = ((com.vivo.push.l) this.f18267c).a;
        sb2.append(context7.getPackageName());
        sb2.append(" notify channel switch is ");
        sb2.append(b2);
        com.vivo.push.util.p.b("OnNotificationArrivedTask", sb2.toString());
        context8 = ((com.vivo.push.l) this.f18267c).a;
        com.vivo.push.util.p.b(context8, "\u5141\u8bb8\u901a\u77e5\u5f00\u5173\u6216\u8005\u63a8\u9001\u901a\u77e5\u6e20\u9053\u5f00\u5173\u5173\u95ed\uff0c\u5bfc\u81f4\u901a\u77e5\u65e0\u6cd5\u5c55\u793a\uff0c\u8bf7\u5230\u8bbe\u7f6e\u9875\u6253\u5f00\u5e94\u7528\u901a\u77e5\u5f00\u5173 ".concat(String.valueOf(b2)));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("messageID", String.valueOf(this.b.f()));
        context9 = ((com.vivo.push.l) this.f18267c).a;
        context10 = ((com.vivo.push.l) this.f18267c).a;
        String b3 = com.vivo.push.util.z.b(context9, context10.getPackageName());
        if (!TextUtils.isEmpty(b3)) {
            hashMap2.put("remoteAppId", b3);
        }
        com.vivo.push.util.e.a(b2, hashMap2);
    }
}
