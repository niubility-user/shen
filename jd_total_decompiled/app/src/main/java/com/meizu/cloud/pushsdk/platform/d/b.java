package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class b extends c<RegisterStatus> {
    public b(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, aVar, scheduledExecutorService);
    }

    public b(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.f16023h = z;
    }

    public b(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
    }

    private boolean x(String str, String str2, int i2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str2.startsWith(str) || System.currentTimeMillis() / 1000 > ((long) i2);
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: A */
    public RegisterStatus n() {
        RegisterStatus registerStatus = new RegisterStatus();
        String B = com.meizu.cloud.pushsdk.util.b.B(this.b, this.f16020e);
        int D = com.meizu.cloud.pushsdk.util.b.D(this.b, this.f16020e);
        if (!w(B, D)) {
            registerStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            registerStatus.setMessage("already register PushId,don't register frequently");
            registerStatus.setPushId(B);
            registerStatus.setExpireTime((int) (D - (System.currentTimeMillis() / 1000)));
            return registerStatus;
        }
        com.meizu.cloud.pushsdk.util.b.A(this.b, "", this.f16020e);
        String b = com.meizu.cloud.pushsdk.d.c.b(this.b);
        String a = com.meizu.cloud.pushsdk.d.c.a(this.b);
        if (TextUtils.isEmpty(b) && TextUtils.isEmpty(a)) {
            registerStatus.setCode("20000");
            registerStatus.setMessage("deviceId is empty");
            return registerStatus;
        }
        com.meizu.cloud.pushsdk.e.b.c c2 = this.f16021f.c(this.f16019c, this.d, a, b);
        if (c2.f()) {
            RegisterStatus registerStatus2 = new RegisterStatus((String) c2.e());
            DebugLogger.e("Strategy", "registerStatus " + registerStatus2);
            if (!TextUtils.isEmpty(registerStatus2.getPushId())) {
                com.meizu.cloud.pushsdk.util.b.A(this.b, registerStatus2.getPushId(), this.f16020e);
                com.meizu.cloud.pushsdk.util.b.b(this.b, (int) ((System.currentTimeMillis() / 1000) + registerStatus2.getExpireTime()), this.f16020e);
            }
            return registerStatus2;
        }
        com.meizu.cloud.pushsdk.e.c.a c3 = c2.c();
        if (c3.c() != null) {
            DebugLogger.e("Strategy", "status code=" + c3.b() + " data=" + c3.c());
        }
        registerStatus.setCode(String.valueOf(c3.b()));
        registerStatus.setMessage(c3.a());
        DebugLogger.e("Strategy", "registerStatus " + registerStatus);
        return registerStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    public boolean m() {
        DebugLogger.e("Strategy", "isBrandMeizu " + MzSystemUtils.isBrandMeizu(this.b));
        return (TextUtils.isEmpty(this.f16019c) || TextUtils.isEmpty(this.d)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    public Intent q() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.f16019c);
        intent.putExtra("app_key", this.d);
        intent.putExtra("strategy_package_name", this.b.getPackageName());
        intent.putExtra("strategy_type", s());
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 2;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: v */
    public void h(RegisterStatus registerStatus) {
        PlatformMessageSender.d(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), registerStatus);
    }

    protected boolean w(String str, int i2) {
        String a = com.meizu.cloud.pushsdk.d.c.a(this.b);
        boolean x = x(a, str, i2);
        return x ? x(a, com.meizu.cloud.pushsdk.platform.a.a(str), i2) : x;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: y */
    public RegisterStatus a() {
        String str;
        RegisterStatus registerStatus = new RegisterStatus();
        registerStatus.setCode("20001");
        if (!TextUtils.isEmpty(this.f16019c)) {
            str = TextUtils.isEmpty(this.d) ? "appKey not empty" : "appId not empty";
            return registerStatus;
        }
        registerStatus.setMessage(str);
        return registerStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: z */
    public RegisterStatus k() {
        return null;
    }
}
