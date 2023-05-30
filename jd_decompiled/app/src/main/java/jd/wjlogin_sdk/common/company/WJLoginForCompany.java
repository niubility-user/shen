package jd.wjlogin_sdk.common.company;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.utils.JDGameUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import com.unionpay.tsmservice.data.Constant;
import jd.wjlogin_sdk.c.f;
import jd.wjlogin_sdk.common.inland.WJLoginInland;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.CompanyLoginResult;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.tlvtype.l;
import jd.wjlogin_sdk.tlvtype.v;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.y;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WJLoginForCompany extends WJLoginInland {
    private static final String u = "WJLogin.WJLoginForCompany";

    /* loaded from: classes.dex */
    class a implements f {
        final /* synthetic */ OnDataCallback a;

        a(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForCompany.this.A(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForCompany.this.b((byte) -1, (short) 44, (short) 10);
        }
    }

    /* loaded from: classes.dex */
    class b implements f {
        final /* synthetic */ OnDataCallback a;

        b(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForCompany.this.z(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForCompany.this.b((byte) -1, (short) 64, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    class c implements f {
        final /* synthetic */ OnDataCallback a;

        c(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForCompany.this.B(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForCompany.this.b((byte) -1, (short) 64, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class d implements f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        d(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForCompany.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForCompany.this.b((byte) -1, (short) 64, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class e implements f {
        final /* synthetic */ OnCommonCallback a;

        e(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForCompany.this.G(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForCompany.this.b((byte) -1, (short) 67, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g() != null ? aVar.g().a() : 120;
                    jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
                    CompanyLoginResult companyLoginResult = new CompanyLoginResult();
                    companyLoginResult.setToken(b3 != null ? new String(b3.a()) : "");
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    successResult.setCompanyLoginResult(companyLoginResult);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 64, (short) 1);
                return;
            }
            x p = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 64, (short) 1);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 64, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    v t = aVar.t();
                    jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
                    CompanyLoginResult companyLoginResult = new CompanyLoginResult();
                    String str = b3 != null ? new String(b3.a()) : "";
                    if (t != null) {
                        companyLoginResult.setJsonStr(t.a());
                    }
                    companyLoginResult.setToken(str);
                    SuccessResult successResult = new SuccessResult();
                    successResult.setCompanyLoginResult(companyLoginResult);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 64, (short) 3);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 64, (short) 3);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 64, (short) 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    a(aVar, (String) null);
                    onCommonCallback.onSuccessHandleInner();
                }
                a((byte) 16, (short) 67, (short) 1);
                b(b2, (short) 67, (short) 1);
                return;
            }
            x p = aVar.p();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            a(failResult, a(b3, i2));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 67, (short) 1);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 67, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    v t = aVar.t();
                    jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
                    CompanyLoginResult companyLoginResult = new CompanyLoginResult();
                    String str = b3 != null ? new String(b3.a()) : "";
                    if (t != null) {
                        companyLoginResult.setJsonStr(t.a());
                    }
                    companyLoginResult.setToken(str);
                    SuccessResult successResult = new SuccessResult();
                    successResult.setCompanyLoginResult(companyLoginResult);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 64, (short) 2);
                return;
            }
            x p = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b4 = aVar.b();
            l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b4, i2));
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 64, (short) 2);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 64, (short) 2);
        }
    }

    public void checkMessageCodeForCompanyLogin(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "checkMessageCodeForCompanyLogin");
                jSONObject.put("phoneNumber", str);
                jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject.put(JshopConst.JSHOP_TIP_MESSAGE_CODE, str3);
                jSONObject.put("companyLoginToken", str4);
                b(u, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 64, (short) 2, g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            if (TextUtils.isEmpty(str2)) {
                str2 = jd.wjlogin_sdk.util.f.d;
            }
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.b(bVar, str4);
            jd.wjlogin_sdk.d.d.f(bVar, str3);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkMessageCodeForCompanyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getMessageCodeForCompanyLogin(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "getMessageCodeForCompanyLogin");
                jSONObject.put("phoneNumber", str);
                jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject.put("sid", str3);
                jSONObject.put("token", str4);
                b(u, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 64, (short) 1, g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            if (TextUtils.isEmpty(str2)) {
                str2 = jd.wjlogin_sdk.util.f.d;
            }
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, str3, str4);
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getMessageCodeForCompanyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void h5Back2AppForCompanyLogin(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "h5Back2AppForCompanyLogin");
                jSONObject.put("safeToken", str);
                b(u, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 64, (short) 3, g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new c(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("h5Back2AppForCompanyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void loginWithNameForCompanyLogin(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            if (p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "loginWithNameForCompanyLogin");
                jSONObject.put(JDGameUtil.KEY_LOGIN_NAME, str);
                jSONObject.put("companyLoginToken", str2);
                b(u, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 64, (short) 4, g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new d(str, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("useLoginNameForCompanyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void switchUserWithAdditionalForCompany(OnCommonCallback onCommonCallback, JSONObject jSONObject) {
        try {
            if (!hasLogin()) {
                if (onCommonCallback == null || onCommonCallback == null) {
                    return;
                }
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception("\u672a\u767b\u5f55\uff0c\u4e0d\u5141\u8bb8\u83b7\u53d6\u6635\u79f0")));
                return;
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 67, (short) 1, g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, getPin());
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new e(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getUsersInfo");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0 && onCommonCallback != null) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        p.a(u, "loginWithNameForCompanyLogin  \u8c03\u7528\u767b\u9646\u6210\u529f\u56de\u8c03");
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(getPin(), str, b2, (short) 64, (short) 4);
                    return;
                }
                p.a(u, "loginWithNameForCompanyLogin  \u4fdd\u5b58\u767b\u5f55\u6001\u5931\u8d25");
                if (onCommonCallback != null) {
                    onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(y.a)));
                }
                b(b2, (short) 64, (short) 4);
                return;
            }
            x p = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b3, i2));
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 64, (short) 4);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 64, (short) 4);
        }
    }
}
