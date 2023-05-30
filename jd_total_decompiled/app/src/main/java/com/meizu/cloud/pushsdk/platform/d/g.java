package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class g extends c<UnRegisterStatus> {
    public g(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, aVar, scheduledExecutorService);
    }

    public g(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.f16023h = z;
    }

    public g(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected boolean m() {
        return (TextUtils.isEmpty(this.f16019c) || TextUtils.isEmpty(this.d)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent q() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.f16019c);
        intent.putExtra("app_key", this.d);
        intent.putExtra("strategy_package_name", this.b.getPackageName());
        intent.putExtra("strategy_type", s());
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 32;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: v */
    public void h(UnRegisterStatus unRegisterStatus) {
        PlatformMessageSender.g(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), unRegisterStatus);
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: w */
    public UnRegisterStatus a() {
        String str;
        UnRegisterStatus unRegisterStatus = new UnRegisterStatus();
        unRegisterStatus.setCode("20001");
        if (!TextUtils.isEmpty(this.f16019c)) {
            str = TextUtils.isEmpty(this.d) ? "appKey not empty" : "appId not empty";
            return unRegisterStatus;
        }
        unRegisterStatus.setMessage(str);
        return unRegisterStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: x */
    public UnRegisterStatus k() {
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: y */
    public UnRegisterStatus n() {
        UnRegisterStatus unRegisterStatus = new UnRegisterStatus();
        if (TextUtils.isEmpty(com.meizu.cloud.pushsdk.util.b.B(this.b, this.f16020e))) {
            unRegisterStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            unRegisterStatus.setMessage("already unRegister PushId,don't unRegister frequently");
            unRegisterStatus.setIsUnRegisterSuccess(true);
            return unRegisterStatus;
        }
        String b = com.meizu.cloud.pushsdk.d.c.b(this.b);
        String a = com.meizu.cloud.pushsdk.d.c.a(this.b);
        if (TextUtils.isEmpty(b) && TextUtils.isEmpty(a)) {
            unRegisterStatus.setCode("20000");
            unRegisterStatus.setMessage("deviceId is empty");
            return unRegisterStatus;
        }
        com.meizu.cloud.pushsdk.e.b.c j2 = this.f16021f.j(this.f16019c, this.d, a, b);
        if (j2.f()) {
            UnRegisterStatus unRegisterStatus2 = new UnRegisterStatus((String) j2.e());
            DebugLogger.e("Strategy", "network unRegisterStatus " + unRegisterStatus2);
            if (BasicPushStatus.SUCCESS_CODE.equals(unRegisterStatus2.getCode())) {
                com.meizu.cloud.pushsdk.util.b.A(this.b, "", this.f16020e);
            }
            return unRegisterStatus2;
        }
        com.meizu.cloud.pushsdk.e.c.a c2 = j2.c();
        if (c2.c() != null) {
            DebugLogger.e("Strategy", "status code=" + c2.b() + " data=" + c2.c());
        }
        unRegisterStatus.setCode(String.valueOf(c2.b()));
        unRegisterStatus.setMessage(c2.a());
        DebugLogger.e("Strategy", "unRegisterStatus " + unRegisterStatus);
        return unRegisterStatus;
    }
}
