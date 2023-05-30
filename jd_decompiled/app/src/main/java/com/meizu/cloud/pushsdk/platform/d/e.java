package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class e extends c<SubTagsStatus> {

    /* renamed from: j */
    private String f16030j;

    /* renamed from: k */
    private int f16031k;

    /* renamed from: l */
    private String f16032l;

    public e(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public e(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.f16023h = z;
    }

    public e(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.f16031k = 3;
    }

    public e(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.f16030j = str3;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: A */
    public SubTagsStatus k() {
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: B */
    public SubTagsStatus n() {
        StringBuilder sb;
        String str;
        SubTagsStatus subTagsStatus = new SubTagsStatus();
        int i2 = this.f16031k;
        com.meizu.cloud.pushsdk.e.b.c f2 = i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? null : this.f16021f.f(this.f16019c, this.d, this.f16030j) : this.f16021f.h(this.f16019c, this.d, this.f16030j) : this.f16021f.l(this.f16019c, this.d, this.f16030j, this.f16032l) : this.f16021f.i(this.f16019c, this.d, this.f16030j, this.f16032l);
        if (f2 == null) {
            DebugLogger.e("Strategy", "network anResponse is null");
            return null;
        }
        if (f2.f()) {
            subTagsStatus = new SubTagsStatus((String) f2.e());
            sb = new StringBuilder();
            str = "network subTagsStatus ";
        } else {
            com.meizu.cloud.pushsdk.e.c.a c2 = f2.c();
            if (c2.c() != null) {
                DebugLogger.e("Strategy", "status code=" + c2.b() + " data=" + c2.c());
            }
            subTagsStatus.setCode(String.valueOf(c2.b()));
            subTagsStatus.setMessage(c2.a());
            sb = new StringBuilder();
            str = "subTagsStatus ";
        }
        sb.append(str);
        sb.append(subTagsStatus);
        DebugLogger.e("Strategy", sb.toString());
        return subTagsStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected boolean m() {
        return (TextUtils.isEmpty(this.f16019c) || TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.f16030j)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent q() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.f16019c);
        intent.putExtra("app_key", this.d);
        intent.putExtra("strategy_package_name", this.b.getPackageName());
        intent.putExtra(PushConstants.REGISTER_STATUS_PUSH_ID, this.f16030j);
        intent.putExtra("strategy_type", s());
        intent.putExtra("strategy_child_type", this.f16031k);
        intent.putExtra("strategy_params", this.f16032l);
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 4;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: v */
    public void h(SubTagsStatus subTagsStatus) {
        PlatformMessageSender.f(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), subTagsStatus);
    }

    public void w(int i2) {
        this.f16031k = i2;
    }

    public void x(String str) {
        this.f16030j = str;
    }

    public void y(String str) {
        this.f16032l = str;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: z */
    public SubTagsStatus a() {
        String str;
        SubTagsStatus subTagsStatus = new SubTagsStatus();
        subTagsStatus.setCode("20001");
        if (TextUtils.isEmpty(this.f16019c)) {
            str = "appId not empty";
        } else if (!TextUtils.isEmpty(this.d)) {
            if (TextUtils.isEmpty(this.f16030j)) {
                str = "pushId not empty";
            }
            return subTagsStatus;
        } else {
            str = "appKey not empty";
        }
        subTagsStatus.setMessage(str);
        return subTagsStatus;
    }
}
