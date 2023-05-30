package com.jd.lib.push.broadcastReceiver;

import android.text.TextUtils;
import com.hihonor.push.sdk.HonorMessageService;
import com.hihonor.push.sdk.c;
import com.jd.lib.push.c.e;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.o;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MyHonorMsgService extends HonorMessageService {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(String str) {
        e.g();
        com.jd.lib.push.a.b(12, str);
    }

    @Override // com.hihonor.push.sdk.HonorMessageService
    public void onMessageReceived(c cVar) {
        g.b("HonorMessageService", "onPushMsg1");
        try {
            g.h("get Data: " + cVar.a() + "\n getMessageId: " + cVar.b() + "\n getVersion: " + cVar.d() + "\n getType: " + cVar.c());
            String a = cVar.a();
            if (TextUtils.isEmpty(a)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(a);
            g.a("onPushMsg " + jSONObject.toString());
            PushMessageHandler.parseManufacturerPushMsg(this, jSONObject, 12, 1);
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    @Override // com.hihonor.push.sdk.HonorMessageService
    public void onNewToken(final String str) {
        g.a("HonorMessageService onNewToken:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        o.b().a(new Runnable() { // from class: com.jd.lib.push.broadcastReceiver.a
            @Override // java.lang.Runnable
            public final void run() {
                MyHonorMsgService.b(str);
            }
        });
    }
}
