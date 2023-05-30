package com.meizu.cloud.pushsdk.handler.f;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.MPushMessage;

/* loaded from: classes14.dex */
public class c extends d {
    public c(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K */
    public void h(MessageV3 messageV3, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (cVar != null) {
            cVar.a(messageV3);
            n().f(t(), MzPushMessage.fromMessageV3(messageV3));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: Q */
    public void v(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.util.d.x(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp(), messageV3.getDelayedReportMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: R */
    public void y(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.util.d.t(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp(), messageV3.getDelayedReportMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: V */
    public MessageV3 D(Intent intent) {
        MPushMessage mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
        return MessageV3.parse(E(intent), x(intent), mPushMessage.getTaskId(), mPushMessage);
    }

    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 2;
    }

    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start MessageV2Handler match");
        return i(0, E(intent)) && PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW.equals(B(intent));
    }
}
