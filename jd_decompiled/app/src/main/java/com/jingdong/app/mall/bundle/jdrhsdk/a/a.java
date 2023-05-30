package com.jingdong.app.mall.bundle.jdrhsdk.a;

import android.app.Activity;
import android.os.CountDownTimer;
import com.jingdong.app.mall.bundle.jdrhsdk.R;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.app.mall.bundle.jdrhsdk.c.e;
import jd.wjlogin_sdk.common.WJLoginAntiRpIdProxy;

/* loaded from: classes2.dex */
public class a extends com.jingdong.app.mall.bundle.jdrhsdk.a.b {

    /* renamed from: f  reason: collision with root package name */
    private CountDownTimer f8132f;

    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class CountDownTimerC0247a extends CountDownTimer {
        CountDownTimerC0247a(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            a.this.c(JDRiskHandleError.CODE_LOGIN_TIMEOUT, JDRiskHandleError.MSG_LOGIN_TIMEOUT);
            a.this.h(false);
            a.this.k(false);
            Activity activity = a.this.b;
            if (activity != null) {
                activity.finish();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
        }
    }

    /* loaded from: classes2.dex */
    class b implements WJLoginAntiRpIdProxy {
        b() {
        }

        @Override // jd.wjlogin_sdk.common.WJLoginAntiRpIdProxy
        public String getAntiCrawlerRpId() {
            return a.this.l() != null ? a.this.l().d() : "";
        }
    }

    /* loaded from: classes2.dex */
    class c implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        c() {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.LoginRiskHandle", "loginCheckToken onFail data=" + jDRiskHandleError.getJsonStr());
            a.this.j(com.jingdong.app.mall.bundle.jdrhsdk.d.a.f8172f);
            a.this.h(false);
            a.this.k(false);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.LoginRiskHandle", "loginCheckToken onSuccess data=" + bVar.d());
            a.this.j(bVar.d());
            a.this.h(false);
            a.this.k(false);
        }
    }

    public a(Activity activity) {
        super(activity);
        this.f8132f = new CountDownTimerC0247a(90000L, 1000L);
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public String a() {
        Activity activity = this.b;
        return activity == null ? "" : activity.getResources().getString(R.string.jdrhsdk_goto_login);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void c(int i2, String str) {
        super.c(i2, str);
        t();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void j(String str) {
        super.j(str);
        t();
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public String p() {
        Activity activity = this.b;
        return activity == null ? "" : activity.getResources().getString(R.string.jdrhsdk_please_login);
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void q() {
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.n(new b());
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.e(this.b, "");
            CountDownTimer countDownTimer = this.f8132f;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.f8132f.start();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void s(String str) {
        try {
            k(true);
            e eVar = new e();
            com.jingdong.app.mall.bundle.jdrhsdk.b.a l2 = l();
            if (l2 != null) {
                eVar.i(l2.d());
                eVar.c(l2.a());
                eVar.e(l2.b());
            }
            eVar.q(com.jingdong.app.mall.bundle.jdrhsdk.d.a.a());
            com.jingdong.app.mall.bundle.jdrhsdk.c.d.a().i(eVar, new c());
        } catch (Exception e2) {
            j(com.jingdong.app.mall.bundle.jdrhsdk.d.a.f8172f);
            h(false);
            k(false);
            e2.printStackTrace();
        }
    }

    public void t() {
        CountDownTimer countDownTimer = this.f8132f;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
