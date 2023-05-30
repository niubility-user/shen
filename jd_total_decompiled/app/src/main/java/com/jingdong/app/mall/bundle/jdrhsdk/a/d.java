package com.jingdong.app.mall.bundle.jdrhsdk.a;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.verify.PreLoader;
import com.jd.verify.ShowCapCallWithLocalErrorback;
import com.jd.verify.Verify;
import com.jd.verify.VerifyExtendProxy;
import com.jd.verify.VerifyParamProxy;
import com.jd.verify.VerifyPrivacyInfoProxy;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.app.mall.bundle.jdrhsdk.R;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class d extends com.jingdong.app.mall.bundle.jdrhsdk.a.b {

    /* renamed from: f  reason: collision with root package name */
    private Verify f8143f;

    /* renamed from: g  reason: collision with root package name */
    private PreLoader f8144g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class a implements VerifyExtendProxy {
        a(d dVar) {
        }

        @Override // com.jd.verify.VerifyExtendProxy
        public String getElderUemps() {
            return com.jingdong.app.mall.bundle.jdrhsdk.d.a.B();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class b implements VerifyPrivacyInfoProxy {
        b(d dVar) {
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyAndroidId() {
            return com.jingdong.app.mall.bundle.jdrhsdk.d.a.D();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyDeviceBrand() {
            return BaseInfo.getDeviceBrand();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyDeviceModel() {
            return BaseInfo.getDeviceModel();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyLatitude() {
            return "" + com.jingdong.app.mall.bundle.jdrhsdk.d.a.v();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyLongitude() {
            return "" + com.jingdong.app.mall.bundle.jdrhsdk.d.a.w();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivacyScreen() {
            return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth();
        }

        @Override // com.jd.verify.VerifyPrivacyInfoProxy
        public String getPrivateOSRelease() {
            return BaseInfo.getAndroidVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class c implements VerifyParamProxy {
        c(d dVar) {
        }

        @Override // com.jd.verify.VerifyParamProxy
        public String getVerifyParams() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pin", com.jingdong.app.mall.bundle.jdrhsdk.d.a.z());
                jSONObject.put("eid", com.jingdong.app.mall.bundle.jdrhsdk.d.a.t());
                return jSONObject.toString();
            } catch (Exception unused) {
                return "";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.a.d$d  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0249d implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        C0249d() {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "createSid onFail errorCode=" + jDRiskHandleError.getCode() + " msg=" + jDRiskHandleError.getMsg());
            d dVar = d.this;
            StringBuilder sb = new StringBuilder();
            sb.append("\u63a5\u53e3\u9519\u8bef:");
            sb.append(jDRiskHandleError.getMsg());
            dVar.g(sb.toString());
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            if (bVar != null) {
                com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "createSid onSuccess sid=" + bVar.d());
                if (!TextUtils.isEmpty(bVar.d())) {
                    d.this.x(bVar.d());
                    return;
                }
                d.this.g("\u63a5\u53e3\u8fd4\u56desid\u4e3a\u7a7a");
                d.this.k(false);
                d.this.h(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class e implements ShowCapCallWithLocalErrorback {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // com.jd.verify.ShowCapCallWithLocalErrorback, com.jd.verify.InterceptCallback
        public void intercept() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify intercept");
        }

        @Override // com.jd.verify.CallBack
        public void invalidSessiongId() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify invalidSessiongId");
            d.this.g("\u9a8c\u8bc1\u7801sid\u5931\u6548");
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jd.verify.ShowCapCallback
        public void loadFail() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify loadFail");
            d.this.g("\u9a8c\u8bc1\u7801\u52a0\u8f7d\u5931\u8d25");
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jd.verify.ShowCapWithCancelCallback
        public void onDialogCancel() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify onDialogCancel");
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jd.verify.InnerCallBack
        public void onFail(String str) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify onFail");
            d.this.g("\u9a8c\u8bc1\u7801\u9519\u8bef:" + str);
            d.this.h(false);
            d.this.k(false);
        }

        @Override // com.jd.verify.SSLDialogCallback
        public void onSSLError() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify onSSLError");
            d.this.g("\u9a8c\u8bc1\u7801\u52a0\u8f7d\u5931\u8d25");
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jd.verify.InnerCallBack
        public void onSuccess(IninVerifyInfo ininVerifyInfo) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify onSuccess");
            if (ininVerifyInfo != null) {
                d.this.w(this.a, ininVerifyInfo.getVt());
                return;
            }
            d.this.g("\u9a8c\u8bc1\u7801\u8fd4\u56de\u4fe1\u606f\u4e3a\u7a7a");
            d.this.k(false);
            d.this.h(false);
        }

        @Override // com.jd.verify.CallBack
        public void showButton(int i2) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify showButton");
            d.this.g("\u9a8c\u8bc1\u7801\u901a\u7528\u62a5\u9519");
            d.this.h(false);
            d.this.k(false);
        }

        @Override // com.jd.verify.ShowCapCallback
        public void showCap() {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify showCap");
            d.this.r();
            d.this.k(false);
        }

        @Override // com.jd.verify.ShowCapCallWithLocalErrorback
        public void withLocalError(int i2) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "verify withLocalError=" + i2);
            d.this.g("\u9a8c\u8bc1\u7801\u672c\u5730\u5f02\u5e38");
            d.this.k(false);
            d.this.h(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class f implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        f() {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "checkToken errorCode=" + jDRiskHandleError.getCode() + " msg=" + jDRiskHandleError.getMsg());
            if (d.this.f8143f != null) {
                d.this.f8143f.free();
                d.this.f8143f = null;
            }
            d.this.c(jDRiskHandleError.getCode(), jDRiskHandleError.getMsg());
            d.this.h(false);
            d.this.k(false);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "checkToken data=" + bVar.d());
            if (d.this.f8143f != null) {
                d.this.f8143f.free();
                d.this.f8143f = null;
            }
            d.this.j(bVar.d());
            d.this.h(false);
            d.this.k(false);
        }
    }

    public d(Activity activity) {
        super(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(String str, String str2) {
        try {
            k(true);
            com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.e();
            com.jingdong.app.mall.bundle.jdrhsdk.b.a l2 = l();
            if (l2 != null) {
                eVar.i(l2.d());
                eVar.c(l2.a());
                eVar.e(l2.b());
            }
            eVar.o(str);
            eVar.q(str2);
            com.jingdong.app.mall.bundle.jdrhsdk.c.d.a().g(eVar, new f());
        } catch (Exception e2) {
            com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.VerifyRiskHandle", "checkToken exception=" + e2.getMessage());
            c(-1001, JDRiskHandleError.MSG_JAVA_EXCEPTION);
            h(false);
            k(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(String str) {
        try {
            z();
            this.f8143f.init("0", str, this.b, com.jingdong.app.mall.bundle.jdrhsdk.d.a.D(), "", com.jingdong.app.mall.bundle.jdrhsdk.d.a.z(), new e(str));
        } catch (Exception e2) {
            g(e2.getMessage());
            h(false);
            k(false);
        }
    }

    private void y() {
        try {
            k(true);
            com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.e();
            com.jingdong.app.mall.bundle.jdrhsdk.b.a l2 = l();
            if (l2 != null) {
                eVar.i(l2.d());
                eVar.c(l2.a());
                eVar.e(l2.b());
            }
            com.jingdong.app.mall.bundle.jdrhsdk.c.d.a().h(eVar, new C0249d());
        } catch (Exception e2) {
            e2.printStackTrace();
            g(e2.toString());
            k(false);
            h(false);
        }
    }

    private void z() {
        if (this.f8143f == null) {
            this.f8143f = Verify.getInstance();
        }
        this.f8143f.isShowToast(false);
        this.f8143f.setProxy(new a(this));
        this.f8143f.setPrivacyInfoProxy(new b(this));
        this.f8143f.setParamProxy(new c(this));
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public String a() {
        Activity activity = this.b;
        return activity == null ? "" : activity.getResources().getString(R.string.jdrhsdk_safe_verify);
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void i() {
        PreLoader preLoader = this.f8144g;
        if (preLoader != null) {
            preLoader.onDestroy();
            this.f8144g = null;
        }
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public int m() {
        return 101;
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void o() {
        if (com.jingdong.app.mall.bundle.jdrhsdk.d.d.a) {
            Verify.setLog(true);
        }
        this.f8144g = Verify.preLoad(this.b);
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public String p() {
        Activity activity = this.b;
        return activity == null ? "" : activity.getResources().getString(R.string.jdrhsdk_please_click_to_verify);
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b
    public void q() {
        h(true);
        y();
    }
}
