package com.meizu.cloud.pushsdk.handler.f.l;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.f.d;

/* loaded from: classes14.dex */
public class b extends d {
    public b(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
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
        DebugLogger.e("AbstractMessageHandler", "BrightNotificationHandler don't repeat upload receiver push event");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: T */
    public int C(MessageV3 messageV3) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: V */
    public MessageV3 D(Intent intent) {
        return (MessageV3) intent.getParcelableExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE);
    }

    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 524288;
    }

    @Override // com.meizu.cloud.pushsdk.handler.f.d, com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start BrightNotificationHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_BRIGHT_NOTIFICATION_MESSAGE.equals(B(intent));
    }
}
