package com.meizu.cloud.pushsdk.handler.f.k;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;

/* loaded from: classes14.dex */
public class e extends com.meizu.cloud.pushsdk.handler.f.a<SubTagsStatus> {
    public e(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void h(SubTagsStatus subTagsStatus, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (n() == null || subTagsStatus == null) {
            return;
        }
        n().j(t(), subTagsStatus);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    public SubTagsStatus D(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_MESSAGE_VALUE);
        return !TextUtils.isEmpty(stringExtra) ? com.meizu.cloud.pushsdk.platform.message.a.k(stringExtra) : (SubTagsStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_SUBTAGS_STATUS);
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 2048;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start SubScribeTagsStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBTAGS_STATUS.equals(B(intent));
    }
}
