package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class f extends c<PushSwitchStatus> {

    /* renamed from: j */
    private String f16033j;

    /* renamed from: k */
    private int f16034k;

    /* renamed from: l */
    private boolean f16035l;

    /* renamed from: m */
    private final Map<String, Boolean> f16036m;

    public f(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, aVar, scheduledExecutorService);
    }

    public f(Context context, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, aVar, scheduledExecutorService);
        this.f16023h = z;
    }

    public f(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.f16034k = 0;
        this.f16036m = new HashMap();
    }

    public f(Context context, String str, String str2, String str3, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, aVar, scheduledExecutorService);
        this.f16033j = str3;
    }

    private void B(boolean z) {
        com.meizu.cloud.pushsdk.util.b.o(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), z);
        com.meizu.cloud.pushsdk.util.b.s(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), z);
    }

    private void C(boolean z) {
        com.meizu.cloud.pushsdk.util.b.o(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), z);
    }

    private void D(boolean z) {
        com.meizu.cloud.pushsdk.util.b.s(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), z);
    }

    private boolean F() {
        return com.meizu.cloud.pushsdk.util.b.I(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName());
    }

    private boolean G() {
        return com.meizu.cloud.pushsdk.util.b.J(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName());
    }

    private boolean H() {
        Boolean bool = this.f16036m.get(this.f16020e + CartConstant.KEY_YB_INFO_LINK + this.f16034k);
        boolean z = bool == null || bool.booleanValue();
        DebugLogger.e("Strategy", "isSyncPushStatus " + this.f16020e + " switch type->" + this.f16034k + " flag->" + z);
        return z;
    }

    private boolean K() {
        return com.meizu.cloud.pushsdk.util.b.y(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName());
    }

    private void L() {
        int i2 = this.f16034k;
        if (i2 == 0 || i2 == 1) {
            PlatformMessageSender.a(this.b, i2, this.f16035l, this.f16020e);
        } else if (i2 != 3) {
        } else {
            PlatformMessageSender.a(this.b, 0, this.f16035l, this.f16020e);
            PlatformMessageSender.a(this.b, 1, this.f16035l, this.f16020e);
        }
    }

    private boolean M() {
        return com.meizu.cloud.pushsdk.util.b.H(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName());
    }

    private com.meizu.cloud.pushsdk.e.b.c<String> v(PushSwitchStatus pushSwitchStatus) {
        boolean z;
        boolean M;
        boolean K;
        int i2 = this.f16034k;
        if (i2 != 0) {
            if (i2 == 1) {
                pushSwitchStatus.setMessage("SWITCH_THROUGH_MESSAGE");
                if (M() != this.f16035l || H()) {
                    y(true);
                    D(this.f16035l);
                    return this.f16021f.b(this.f16019c, this.d, this.f16033j, this.f16034k, this.f16035l);
                }
                K = K();
            } else if (i2 != 2) {
                if (i2 == 3) {
                    pushSwitchStatus.setMessage("SWITCH_ALL");
                    if (K() != this.f16035l || M() != this.f16035l || H()) {
                        y(true);
                        B(this.f16035l);
                        return this.f16021f.e(this.f16019c, this.d, this.f16033j, this.f16035l);
                    }
                    K = this.f16035l;
                }
                return null;
            } else {
                pushSwitchStatus.setMessage("CHECK_PUSH");
                if (!F() || !G() || H()) {
                    y(true);
                    return this.f16021f.a(this.f16019c, this.d, this.f16033j);
                }
                z = K();
                pushSwitchStatus.setSwitchNotificationMessage(z);
                M = M();
            }
            pushSwitchStatus.setSwitchNotificationMessage(K);
            M = this.f16035l;
        } else {
            pushSwitchStatus.setMessage("SWITCH_NOTIFICATION");
            if (K() != this.f16035l || H()) {
                y(true);
                C(this.f16035l);
                return this.f16021f.b(this.f16019c, this.d, this.f16033j, this.f16034k, this.f16035l);
            }
            z = this.f16035l;
            pushSwitchStatus.setSwitchNotificationMessage(z);
            M = M();
        }
        pushSwitchStatus.setSwitchThroughMessage(M);
        return null;
    }

    private void y(boolean z) {
        this.f16036m.put(this.f16020e + CartConstant.KEY_YB_INFO_LINK + this.f16034k, Boolean.valueOf(z));
    }

    public void A(String str) {
        this.f16033j = str;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: E */
    public PushSwitchStatus a() {
        String str;
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setCode("20001");
        if (TextUtils.isEmpty(this.f16019c)) {
            str = "appId not empty";
        } else if (!TextUtils.isEmpty(this.d)) {
            if (TextUtils.isEmpty(this.f16033j)) {
                str = "pushId not empty";
            }
            return pushSwitchStatus;
        } else {
            str = "appKey not empty";
        }
        pushSwitchStatus.setMessage(str);
        return pushSwitchStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: I */
    public PushSwitchStatus k() {
        int i2 = this.f16034k;
        if (i2 == 0) {
            C(this.f16035l);
            return null;
        } else if (i2 == 1) {
            D(this.f16035l);
            return null;
        } else if (i2 == 2 || i2 == 3) {
            B(this.f16035l);
            return null;
        } else {
            return null;
        }
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: J */
    public PushSwitchStatus n() {
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setPushId(this.f16033j);
        pushSwitchStatus.setCode(BasicPushStatus.SUCCESS_CODE);
        com.meizu.cloud.pushsdk.e.b.c<String> v = v(pushSwitchStatus);
        if (v != null) {
            if (v.f()) {
                PushSwitchStatus pushSwitchStatus2 = new PushSwitchStatus(v.e());
                DebugLogger.e("Strategy", "network pushSwitchStatus " + pushSwitchStatus2);
                if (BasicPushStatus.SUCCESS_CODE.equals(pushSwitchStatus.getCode())) {
                    y(false);
                    DebugLogger.e("Strategy", "update local switch preference");
                    pushSwitchStatus.setSwitchNotificationMessage(pushSwitchStatus2.isSwitchNotificationMessage());
                    pushSwitchStatus.setSwitchThroughMessage(pushSwitchStatus2.isSwitchThroughMessage());
                    C(pushSwitchStatus2.isSwitchNotificationMessage());
                    D(pushSwitchStatus2.isSwitchThroughMessage());
                }
            } else {
                com.meizu.cloud.pushsdk.e.c.a c2 = v.c();
                if (c2.c() != null) {
                    DebugLogger.e("Strategy", "status code=" + c2.b() + " data=" + c2.c());
                }
                pushSwitchStatus.setCode(String.valueOf(c2.b()));
                pushSwitchStatus.setMessage(c2.a());
                DebugLogger.e("Strategy", "pushSwitchStatus " + pushSwitchStatus);
            }
        }
        DebugLogger.e("Strategy", "enableRpc " + this.f16023h + " isSupportRemoteInvoke " + this.f16022g);
        if (this.f16023h && !this.f16022g) {
            L();
        }
        return pushSwitchStatus;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected boolean m() {
        return (TextUtils.isEmpty(this.f16019c) || TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.f16033j)) ? false : true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent q() {
        Intent intent = new Intent();
        intent.putExtra("app_id", this.f16019c);
        intent.putExtra("app_key", this.d);
        intent.putExtra("strategy_package_name", this.b.getPackageName());
        intent.putExtra(PushConstants.REGISTER_STATUS_PUSH_ID, this.f16033j);
        intent.putExtra("strategy_type", s());
        intent.putExtra("strategy_child_type", this.f16034k);
        intent.putExtra("strategy_params", this.f16035l ? "1" : "0");
        return intent;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 16;
    }

    public void w(int i2) {
        this.f16034k = i2;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    /* renamed from: x */
    public void h(PushSwitchStatus pushSwitchStatus) {
        PlatformMessageSender.c(this.b, !TextUtils.isEmpty(this.f16020e) ? this.f16020e : this.b.getPackageName(), pushSwitchStatus);
    }

    public void z(boolean z) {
        this.f16035l = z;
    }
}
