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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(byte r5, jd.wjlogin_sdk.tlvtype.a r6, jd.wjlogin_sdk.common.listener.OnCommonCallback r7) {
        /*
            r4 = this;
            r0 = 5
            r1 = 14
            if (r5 != 0) goto L41
            r2 = 0
            r4.a(r6, r2)     // Catch: java.lang.Exception -> L63
            java.lang.String r6 = r4.getA2()     // Catch: java.lang.Exception -> L63
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L63
            if (r6 != 0) goto L27
            java.lang.String r6 = r4.getPin()     // Catch: java.lang.Exception -> L63
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L63
            if (r6 == 0) goto L1e
            goto L27
        L1e:
            if (r7 == 0) goto L23
            r7.onSuccessHandleInner()     // Catch: java.lang.Exception -> L63
        L23:
            r4.b(r5, r1, r0)     // Catch: java.lang.Exception -> L63
            return
        L27:
            if (r7 == 0) goto L3c
            r5 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r6 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r2 = new java.lang.Exception     // Catch: java.lang.Exception -> L63
            java.lang.String r3 = "a2 or pin is null"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L63
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r5, r6, r2)     // Catch: java.lang.Exception -> L63
            r7.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L63
        L3c:
            r5 = -2
            r4.b(r5, r1, r0)     // Catch: java.lang.Exception -> L63
            return
        L41:
            jd.wjlogin_sdk.tlvtype.x r2 = r6.p()     // Catch: java.lang.Exception -> L63
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L63
            r3.<init>()     // Catch: java.lang.Exception -> L63
            r4.a(r3, r5, r2)     // Catch: java.lang.Exception -> L63
            jd.wjlogin_sdk.tlvtype.d r2 = r6.b()     // Catch: java.lang.Exception -> L63
            jd.wjlogin_sdk.tlvtype.l r6 = r6.i()     // Catch: java.lang.Exception -> L63
            jd.wjlogin_sdk.model.JumpResult r6 = r4.a(r2, r6)     // Catch: java.lang.Exception -> L63
            r4.a(r3, r6)     // Catch: java.lang.Exception -> L63
            r7.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L63
            r4.b(r5, r1, r0)     // Catch: java.lang.Exception -> L63
            goto L74
        L63:
            r5 = move-exception
            r5.printStackTrace()
            if (r7 == 0) goto L70
            jd.wjlogin_sdk.model.FailResult r5 = r4.a()
            r7.onFailHandleInner(r5)
        L70:
            r5 = -1
            r4.b(r5, r1, r0)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.lite.WJLoginLite.c(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(byte r8, jd.wjlogin_sdk.tlvtype.a r9, jd.wjlogin_sdk.common.listener.OnCommonCallback r10) {
        /*
            r7 = this;
            java.lang.String r0 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            r1 = -102(0xffffffffffffff9a, float:NaN)
            r2 = -2
            r3 = 5
            r4 = 17
            if (r8 != 0) goto L48
            r5 = 0
            r7.a(r9, r5)     // Catch: java.lang.Exception -> L6c
            java.lang.String r9 = r7.getA2()     // Catch: java.lang.Exception -> L6c
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L6c
            if (r9 != 0) goto L34
            java.lang.String r9 = r7.getPin()     // Catch: java.lang.Exception -> L6c
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L6c
            if (r9 == 0) goto L24
            goto L34
        L24:
            if (r10 == 0) goto L29
            r10.onSuccessHandleInner()     // Catch: java.lang.Exception -> L6c
        L29:
            r7.b(r8, r3, r4)     // Catch: java.lang.Exception -> L6c
            r8 = 15
            r9 = 16
            r7.a(r8, r9, r4)     // Catch: java.lang.Exception -> L6c
            return
        L34:
            if (r10 == 0) goto L44
            java.lang.Exception r8 = new java.lang.Exception     // Catch: java.lang.Exception -> L6c
            java.lang.String r9 = "a2 or pin is null"
            r8.<init>(r9)     // Catch: java.lang.Exception -> L6c
            jd.wjlogin_sdk.model.ErrorResult r8 = jd.wjlogin_sdk.util.b0.a(r1, r0, r8)     // Catch: java.lang.Exception -> L6c
            r10.onErrorHandleInner(r8)     // Catch: java.lang.Exception -> L6c
        L44:
            r7.b(r2, r3, r4)     // Catch: java.lang.Exception -> L6c
            return
        L48:
            jd.wjlogin_sdk.tlvtype.x r5 = r9.p()     // Catch: java.lang.Exception -> L6c
            jd.wjlogin_sdk.model.FailResult r6 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L6c
            r6.<init>()     // Catch: java.lang.Exception -> L6c
            r7.a(r6, r8, r5)     // Catch: java.lang.Exception -> L6c
            jd.wjlogin_sdk.tlvtype.d r5 = r9.b()     // Catch: java.lang.Exception -> L6c
            jd.wjlogin_sdk.tlvtype.l r9 = r9.i()     // Catch: java.lang.Exception -> L6c
            jd.wjlogin_sdk.model.JumpResult r9 = r7.a(r5, r9)     // Catch: java.lang.Exception -> L6c
            r6.setJumpResult(r9)     // Catch: java.lang.Exception -> L6c
            if (r10 == 0) goto L68
            r10.onFailHandleInner(r6)     // Catch: java.lang.Exception -> L6c
        L68:
            r7.b(r8, r3, r4)     // Catch: java.lang.Exception -> L6c
            goto L79
        L6c:
            r8 = move-exception
            if (r10 == 0) goto L76
            jd.wjlogin_sdk.model.ErrorResult r8 = jd.wjlogin_sdk.util.b0.a(r1, r0, r8)
            r10.onErrorHandleInner(r8)
        L76:
            r7.b(r2, r3, r4)
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.lite.WJLoginLite.b(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }
}
