package com.meizu.cloud.pushsdk.handler.f.k;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;

/* loaded from: classes14.dex */
public class d extends com.meizu.cloud.pushsdk.handler.f.a<SubAliasStatus> {
    public d(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    private void L(String str) {
        com.meizu.cloud.pushsdk.util.b.G(t(), t().getPackageName(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void h(SubAliasStatus subAliasStatus, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (n() == null || subAliasStatus == null) {
            return;
        }
        n().d(t(), subAliasStatus);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public SubAliasStatus D(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_MESSAGE_VALUE);
        SubAliasStatus j2 = !TextUtils.isEmpty(stringExtra) ? com.meizu.cloud.pushsdk.platform.message.a.j(stringExtra) : (SubAliasStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_SUBALIAS_STATUS);
        if (BasicPushStatus.SUCCESS_CODE.equals(j2.getCode())) {
            L(j2.getAlias());
        }
        return j2;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 4096;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start SubScribeAliasStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBALIAS_STATUS.equals(B(intent));
    }
}
