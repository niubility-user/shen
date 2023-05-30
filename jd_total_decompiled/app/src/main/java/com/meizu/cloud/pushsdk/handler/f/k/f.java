package com.meizu.cloud.pushsdk.handler.f.k;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

/* loaded from: classes14.dex */
public class f extends com.meizu.cloud.pushsdk.handler.f.a<UnRegisterStatus> {
    public f(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void h(UnRegisterStatus unRegisterStatus, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (n() == null || unRegisterStatus == null) {
            return;
        }
        n().k(t(), unRegisterStatus);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    public UnRegisterStatus D(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_MESSAGE_VALUE);
        UnRegisterStatus l2 = !TextUtils.isEmpty(stringExtra) ? com.meizu.cloud.pushsdk.platform.message.a.l(stringExtra) : (UnRegisterStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_UNREGISTER_STATUS);
        if (l2.isUnRegisterSuccess()) {
            com.meizu.cloud.pushsdk.util.b.A(t(), "", t().getPackageName());
        }
        return l2;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 1024;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start UnRegisterStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_UNREGISTER_STATUS.equals(B(intent));
    }
}
