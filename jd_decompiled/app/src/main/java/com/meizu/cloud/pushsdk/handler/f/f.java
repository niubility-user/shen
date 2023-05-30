package com.meizu.cloud.pushsdk.handler.f;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.e.j.d;

/* loaded from: classes14.dex */
public class f extends a<MessageV3> {
    public f(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void h(MessageV3 messageV3, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (n() == null || messageV3 == null || TextUtils.isEmpty(messageV3.getThroughMessage())) {
            return;
        }
        n().a(t(), messageV3.getThroughMessage());
        com.meizu.cloud.pushsdk.handler.a n2 = n();
        Context t = t();
        String throughMessage = messageV3.getThroughMessage();
        d.a a = com.meizu.cloud.pushsdk.handler.e.j.d.a();
        a.h(messageV3.getTaskId());
        a.f(messageV3.getSeqId());
        a.d(messageV3.getPushTimestamp());
        a.a(messageV3.getDeviceId());
        n2.a(t, throughMessage, a.b().f());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    public void y(MessageV3 messageV3) {
        if (messageV3 == null || TextUtils.isEmpty(messageV3.getDeviceId()) || TextUtils.isEmpty(messageV3.getTaskId())) {
            return;
        }
        String d = d(messageV3.getThroughMessage());
        if (TextUtils.isEmpty(d)) {
            com.meizu.cloud.pushsdk.util.d.z(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
        } else {
            com.meizu.cloud.pushsdk.util.d.z(t(), d, messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public MessageV3 D(Intent intent) {
        MessageV3 messageV3 = new MessageV3();
        if (PushConstants.C2DM_INTENT.equals(intent.getAction())) {
            n().l(t(), intent);
            return null;
        }
        messageV3.setThroughMessage(intent.getStringExtra("message"));
        messageV3.setTaskId(I(intent));
        messageV3.setDeviceId(x(intent));
        messageV3.setSeqId(H(intent));
        messageV3.setPushTimestamp(G(intent));
        messageV3.setUploadDataPackageName(E(intent));
        return messageV3;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 8;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start ThroughMessageHandler match");
        if (i(1, E(intent))) {
            if (PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction())) {
                if ("message".equals(B(intent))) {
                    return true;
                }
                if (TextUtils.isEmpty(B(intent))) {
                    String stringExtra = intent.getStringExtra("message");
                    if (!TextUtils.isEmpty(stringExtra) && !q(stringExtra)) {
                        return true;
                    }
                }
            }
            return PushConstants.C2DM_INTENT.equals(intent.getAction());
        }
        return false;
    }
}
