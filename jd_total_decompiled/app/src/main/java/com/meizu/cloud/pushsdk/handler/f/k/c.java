package com.meizu.cloud.pushsdk.handler.f.k;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: classes14.dex */
public class c extends com.meizu.cloud.pushsdk.handler.f.a<RegisterStatus> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.N();
        }
    }

    public c(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        String mzPushServicePackageName = MzSystemUtils.getMzPushServicePackageName(t());
        if (com.meizu.cloud.pushsdk.util.b.u(t(), mzPushServicePackageName)) {
            com.meizu.cloud.pushsdk.util.b.i(t(), mzPushServicePackageName, false);
            if (TextUtils.isEmpty(com.meizu.cloud.pushsdk.util.b.z(t(), mzPushServicePackageName))) {
                String A = A();
                if (TextUtils.isEmpty(A)) {
                    return;
                }
                com.meizu.cloud.pushsdk.util.b.x(t(), mzPushServicePackageName, A);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    public void v(RegisterStatus registerStatus) {
        com.meizu.cloud.pushsdk.d.m.a.a().execute(new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public void h(RegisterStatus registerStatus, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (n() == null || registerStatus == null) {
            return;
        }
        n().b(t(), registerStatus);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: O  reason: merged with bridge method [inline-methods] */
    public RegisterStatus D(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_MESSAGE_VALUE);
        RegisterStatus i2 = !TextUtils.isEmpty(stringExtra) ? com.meizu.cloud.pushsdk.platform.message.a.i(stringExtra) : (RegisterStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_REGISTER_STATUS);
        if (!TextUtils.isEmpty(i2.getPushId())) {
            com.meizu.cloud.pushsdk.util.b.A(t(), i2.getPushId(), t().getPackageName());
            com.meizu.cloud.pushsdk.util.b.b(t(), (int) ((System.currentTimeMillis() / 1000) + i2.getExpireTime()), t().getPackageName());
        }
        return i2;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 512;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start RegisterStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_REGISTER_STATUS.equals(B(intent));
    }
}
