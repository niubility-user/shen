package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class d extends c<SubAliasStatus> {

    /* renamed from: j */
    private String f16026j;

    /* renamed from: k */
    private int f16027k;

    /* renamed from: l */
    private String f16028l;

    /* renamed from: m */
    private final Map<String, Boolean> f16029m;

    public d(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public d(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.f16023h = z;
    }

    public d(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.f16029m = new HashMap();
    }

    public d(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.f16026j = str3;
    }

    private void A(String str) {
        com.meizu.cloud.pushsdk.util.b.G(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), str);
    }

    private boolean C() {
        return !this.f16022g && PushConstants.PUSH_PACKAGE_NAME.equals(this.f16020e);
    }

    private boolean D() {
        Boolean bool = this.f16029m.get(this.f16020e + CartConstant.KEY_YB_INFO_LINK + this.f16027k);
        return bool == null || bool.booleanValue();
    }

    private String E() {
        return com.meizu.cloud.pushsdk.util.b.a(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName());
    }

    private void x(boolean z) {
        this.f16029m.put(this.f16020e + CartConstant.KEY_YB_INFO_LINK + this.f16027k, Boolean.valueOf(z));
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: B */
    public SubAliasStatus a() {
        String str;
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setCode("20001");
        if (TextUtils.isEmpty(this.f16019c)) {
            str = "appId not empty";
        } else if (!TextUtils.isEmpty(this.d)) {
            if (TextUtils.isEmpty(this.f16026j)) {
                str = "pushId not empty";
            }
            return subAliasStatus;
        } else {
            str = "appKey not empty";
        }
        subAliasStatus.setMessage(str);
        return subAliasStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: F */
    public SubAliasStatus k() {
        if (this.f16027k == 2) {
            SubAliasStatus subAliasStatus = new SubAliasStatus();
            subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            subAliasStatus.setPushId(this.f16026j);
            subAliasStatus.setAlias(E());
            subAliasStatus.setMessage("check alias success");
            return subAliasStatus;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0090  */
    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: G */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SubAliasStatus n() {
        com.meizu.cloud.pushsdk.e.b.c g2;
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setPushId(this.f16026j);
        String str = "";
        subAliasStatus.setMessage("");
        int i2 = this.f16027k;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    subAliasStatus.setAlias(E());
                    subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
                }
            } else if (!TextUtils.isEmpty(E()) || D()) {
                x(true);
                if (C()) {
                    A("");
                }
                g2 = this.f16021f.k(this.f16019c, this.d, this.f16026j, this.f16028l);
                if (g2 != null) {
                    if (g2.f()) {
                        subAliasStatus = new SubAliasStatus((String) g2.e());
                        DebugLogger.e("Strategy", "network subAliasStatus " + subAliasStatus);
                        if (BasicPushStatus.SUCCESS_CODE.equals(subAliasStatus.getCode())) {
                            x(false);
                        }
                    } else {
                        com.meizu.cloud.pushsdk.e.c.a c2 = g2.c();
                        if (c2.c() != null) {
                            DebugLogger.e("Strategy", "status code=" + c2.b() + " data=" + c2.c());
                        }
                        subAliasStatus.setCode(String.valueOf(c2.b()));
                        subAliasStatus.setMessage(c2.a());
                        DebugLogger.e("Strategy", "subAliasStatus " + subAliasStatus);
                    }
                }
                return subAliasStatus;
            } else {
                subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
                subAliasStatus.setAlias(str);
            }
        } else if (!this.f16028l.equals(E()) || D()) {
            x(true);
            if (C()) {
                A(this.f16028l);
            }
            g2 = this.f16021f.g(this.f16019c, this.d, this.f16026j, this.f16028l);
            if (g2 != null) {
            }
            return subAliasStatus;
        } else {
            subAliasStatus.setCode(BasicPushStatus.SUCCESS_CODE);
            str = this.f16028l;
            subAliasStatus.setAlias(str);
        }
        g2 = null;
        if (g2 != null) {
        }
        return subAliasStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected boolean m() {
        return (TextUtils.isEmpty(this.f16019c) || TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.f16026j)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent q() {
        if (this.f16027k != 2) {
            Intent intent = new Intent();
            intent.putExtra("app_id", this.f16019c);
            intent.putExtra("app_key", this.d);
            intent.putExtra("strategy_package_name", this.b.getPackageName());
            intent.putExtra(PushConstants.REGISTER_STATUS_PUSH_ID, this.f16026j);
            intent.putExtra("strategy_type", s());
            intent.putExtra("strategy_child_type", this.f16027k);
            intent.putExtra("strategy_params", this.f16028l);
            return intent;
        }
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 8;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: v */
    public void h(SubAliasStatus subAliasStatus) {
        PlatformMessageSender.e(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), subAliasStatus);
    }

    public void w(int i2) {
        this.f16027k = i2;
    }

    public void y(String str) {
        this.f16028l = str;
    }

    public void z(String str) {
        this.f16026j = str;
    }
}
