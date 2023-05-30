package jd.wjlogin_sdk.common.lite;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import jd.wjlogin_sdk.c.f;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.d.d;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.JDUnionTokenInfo;
import jd.wjlogin_sdk.tlvtype.l;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.p;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WJLoginLite extends jd.wjlogin_sdk.common.h.c implements jd.wjlogin_sdk.common.lite.a {
    private static final String r = "WJLogin.WJLoginLite";

    /* loaded from: classes.dex */
    class a implements f {
        final /* synthetic */ OnCommonCallback a;

        a(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginLite.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements f {
        final /* synthetic */ OnCommonCallback a;

        b(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginLite.this.c(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginLite.this.b((byte) -1, (short) 14, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    class c implements f {
        final /* synthetic */ OnCommonCallback a;

        c(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginLite.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginLite.this.b((byte) -1, (short) 5, (short) 17);
        }
    }

    @Override // jd.wjlogin_sdk.common.lite.a
    public boolean isJDAppInstalled() {
        try {
            jd.wjlogin_sdk.common.b.a().getPackageManager().getPackageInfo(jd.wjlogin_sdk.util.f.f19954c, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // jd.wjlogin_sdk.common.lite.a
    public boolean isJDAppSupportAPI() {
        try {
            if (jd.wjlogin_sdk.common.b.a() == null) {
                p.b("wjlogin isJDAppSupportAPI GlobalContext.getApplicationContext()==null ");
                return false;
            }
            PackageInfo packageInfo = jd.wjlogin_sdk.common.b.a().getPackageManager().getPackageInfo(jd.wjlogin_sdk.util.f.f19954c, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("wjlogin isJDAppSupportAPI Build.VERSION= ");
            int i2 = Build.VERSION.SDK_INT;
            sb.append(i2);
            p.b(sb.toString());
            if (i2 < 28) {
                if (packageInfo != null && packageInfo.versionCode > 38118) {
                    p.b("wjlogin isJDAppSupportAPI info.versionCode= " + packageInfo.versionCode);
                    return true;
                }
            } else if (packageInfo != null && packageInfo.getLongVersionCode() > 38118) {
                p.b("wjlogin isJDAppSupportAPI info.getLongVersionCode()= " + packageInfo.getLongVersionCode());
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            p.b("wjlogin isJDAppSupportAPI NameNotFoundException ");
            e2.printStackTrace();
            return false;
        }
    }

    public void jdSCUnionLogin(JDUnionTokenInfo jDUnionTokenInfo, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(d.a((short) 5, (short) 17, g.d(), this.seq));
            d.a(bVar);
            d.a(bVar, jDUnionTokenInfo);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new c(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("jdUnionLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    @Override // jd.wjlogin_sdk.common.lite.a
    public void loginWithToken(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "loginWithToken");
                    jSONObject.put("token", str);
                    b(r, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(d.a((short) 14, (short) 5, g.d(), this.seq));
            d.a(bVar);
            if (!TextUtils.isEmpty(str)) {
                d.b(bVar, str);
            }
            String a2 = b0.a(jd.wjlogin_sdk.common.b.a());
            if (!TextUtils.isEmpty(a2)) {
                d.q(bVar, a2);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("loginWithToken");
            gVar.b();
        } catch (Exception e3) {
            e3.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    @Override // jd.wjlogin_sdk.common.lite.a
    public void openJDApp(Context context, String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "openJDApp");
                    jSONObject.put("returnUrl", str);
                    b(r, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(d.a((short) 14, (short) 1, g.d(), this.seq));
            d.a(bVar);
            if (!TextUtils.isEmpty(str)) {
                d.h(bVar, str);
            }
            String a2 = b0.a(jd.wjlogin_sdk.common.b.a());
            if (!TextUtils.isEmpty(a2)) {
                d.q(bVar, a2);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("openJDApp");
            gVar.b();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void c(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 14, (short) 5);
                    return;
                }
                b((byte) -2, (short) 14, (short) 5);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            a(failResult, a(aVar.b(), aVar.i()));
            onCommonCallback.onFailHandleInner(failResult);
            b(b2, (short) 14, (short) 5);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 14, (short) 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            FailResult failResult = new FailResult();
            if (b2 == 0) {
                l i2 = aVar.i();
                if (onCommonCallback == null || i2 == null) {
                    return;
                }
                if (TextUtils.isEmpty(i2.a())) {
                    a(failResult, b2, (x) null);
                    onCommonCallback.onFailHandleInner(failResult);
                    return;
                }
                b0.b(jd.wjlogin_sdk.common.b.a().getApplicationContext(), i2.a());
                try {
                    if (p.b) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("callMethod", "openJDAppSuccess");
                        jSONObject.put("responseUrl", i2.a());
                        a(r, jSONObject);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                onCommonCallback.onSuccessHandleInner();
                return;
            }
            a(failResult, b2, aVar.p());
            onCommonCallback.onFailHandleInner(failResult);
        } catch (Exception e3) {
            e3.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 5, (short) 17);
                    a((byte) 15, (short) 16, (short) 17);
                    return;
                }
                b((byte) -2, (short) 5, (short) 17);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 5, (short) 17);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 5, (short) 17);
        }
    }
}
