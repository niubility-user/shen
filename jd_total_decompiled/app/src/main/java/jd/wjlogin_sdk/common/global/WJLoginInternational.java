package jd.wjlogin_sdk.common.global;

import android.text.TextUtils;
import android.util.Pair;
import java.nio.ByteBuffer;
import java.util.Date;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.common.listener.OnLoginCallback;
import jd.wjlogin_sdk.common.lite.WJLoginLite;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FBTokenInfo;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.GlobalRegisterInfo;
import jd.wjlogin_sdk.model.GoogleTokenInfo;
import jd.wjlogin_sdk.model.JumpResult;
import jd.wjlogin_sdk.model.LineTokenInfo;
import jd.wjlogin_sdk.model.ReqJumpTokenResp;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.model.TwitterTokenInfo;
import jd.wjlogin_sdk.model.WXTokenInfo;
import jd.wjlogin_sdk.tlvtype.a0;
import jd.wjlogin_sdk.tlvtype.j0;
import jd.wjlogin_sdk.tlvtype.n0;
import jd.wjlogin_sdk.tlvtype.s0;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.tlvtype.z;
import jd.wjlogin_sdk.util.ByteUtil;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.v;
import jd.wjlogin_sdk.util.y;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WJLoginInternational extends WJLoginLite {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        a(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.f(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        b(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.g(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 11);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        c(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.i(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 9);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f19771c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19772e;

        d(String str, String str2, boolean z, String str3, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19771c = z;
            this.d = str3;
            this.f19772e = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19771c, this.d, this.f19772e);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19772e;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 19, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    class e implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        e(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.f(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 19, (short) 8);
        }
    }

    /* loaded from: classes.dex */
    class f implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        f(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.e(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 18, (short) 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnDataCallback b;

        g(String str, OnDataCallback onDataCallback) {
            this.a = str;
            this.b = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.a(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.b;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 18, (short) 9);
        }
    }

    /* loaded from: classes.dex */
    class h implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        h(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.h(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 28, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class i implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        i(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.k(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        j(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.j(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 24);
        }
    }

    /* loaded from: classes.dex */
    class k implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnLoginCallback b;

        k(String str, OnLoginCallback onLoginCallback) {
            this.a = str;
            this.b = onLoginCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnLoginCallback onLoginCallback = this.b;
            if (onLoginCallback != null) {
                onLoginCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.a(this.a, (byte) -1, (short) 18, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class l implements jd.wjlogin_sdk.c.f {
        l() {
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            WJLoginInternational.this.b((byte) -1, (short) 18, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class m implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        m(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.g(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 18, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class n implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        n(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 18, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    class o implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        o(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.c(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            WJLoginInternational.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class p implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        p(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.d(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 19, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class q implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        q(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.d(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 19, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class r implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        r(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.e(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 19, (short) 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        s(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInternational.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInternational.this.b((byte) -1, (short) 21, (short) 1);
        }
    }

    public void JDGlobalLoginWithPassword(String str, String str2, String str3, String str4, OnLoginCallback onLoginCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.b(bVar, str, str2);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                jd.wjlogin_sdk.d.d.a(bVar, str3, str4);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new k(str, onLoginCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("JDGlobalLoginWithPassword");
            hVar.b();
        } catch (Exception e2) {
            if (onLoginCallback != null) {
                onLoginCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalBindAccountLogin(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new n(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalBindAccountLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalCheckImageCodeAndAccount(String str, String str2, String str3, String str4, boolean z, boolean z2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str3);
            jd.wjlogin_sdk.d.d.p(bVar, str4);
            if (z2) {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 1);
            } else {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 2);
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                jd.wjlogin_sdk.d.d.a(bVar, str, str2);
            }
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new p(onCommonCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalCheckImageCodeAndAccount");
            hVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalCheckMessageCode(String str, String str2, String str3, boolean z, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            if (z) {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 1);
            } else {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 2);
            }
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new r(onCommonCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalCheckMessageCode");
            hVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalCheckPhoneLoginMessage(String str, String str2, String str3, OnDataCallback<SuccessResult> onDataCallback) {
        globalCheckPhoneLoginMessage(str, str2, str3, null, onDataCallback);
    }

    public void globalExitLogin() {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            String str = "";
            jd.wjlogin_sdk.d.d.a(bVar, getPin() == null ? "" : getPin());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            if (getA2() != null) {
                str = getA2();
            }
            jd.wjlogin_sdk.d.d.y(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            clearLocalOnlineState();
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new l());
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalExitLogin");
            hVar.b();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void globalFBLogin(FBTokenInfo fBTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        globalFBLogin(fBTokenInfo, null, onDataCallback);
    }

    public void globalGetMessageCode(String str, String str2, boolean z, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            if (z) {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 1);
            } else {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 2);
            }
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new q(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalGetMessageCode");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalGetPhoneLoginMessage(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 8, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                jd.wjlogin_sdk.d.d.a(bVar, str3, str4);
            }
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new f(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalGetSid");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalGetSid(int i2, JSONObject jSONObject, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 8, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.b(bVar, i2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new e(onCommonCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalGetSid");
            hVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalGoogleLogin(GoogleTokenInfo googleTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        globalGoogleLogin(googleTokenInfo, null, onDataCallback);
    }

    public void globalLineLogin(LineTokenInfo lineTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        globalLineLogin(lineTokenInfo, null, onDataCallback);
    }

    public void globalRefreshA2(OnCommonCallback onCommonCallback) {
        try {
            if (isExistsA2() && !c() && b()) {
                this.seq++;
                jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
                bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
                jd.wjlogin_sdk.d.d.y(bVar, getA2());
                jd.wjlogin_sdk.d.d.a(bVar, getPin());
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
                jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
                jd.wjlogin_sdk.d.d.u(bVar, p());
                this.a = System.currentTimeMillis();
                jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new m(onCommonCallback));
                hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalRefreshA2");
                hVar.b();
            }
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalRegister(String str, String str2, String str3, String str4, boolean z, GlobalRegisterInfo globalRegisterInfo, OnCommonCallback onCommonCallback) {
        globalRegister(str, str2, str3, str4, z, globalRegisterInfo, null, onCommonCallback);
    }

    public void globalReqJumpToken(String str, OnDataCallback<ReqJumpTokenResp> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 28, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            String str2 = "";
            jd.wjlogin_sdk.d.d.a(bVar, getPin() == null ? "" : getPin());
            if (getA2() != null) {
                str2 = getA2();
            }
            jd.wjlogin_sdk.d.d.y(bVar, str2);
            jd.wjlogin_sdk.d.d.c(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new h(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalReqJumpToken");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalSendGetCountryCodeList(OnDataCallback<SuccessResult> onDataCallback) {
        try {
            String e2 = v.e(jd.wjlogin_sdk.util.e.x);
            if (!TextUtils.isEmpty(e2) && b0.a(e2) <= 1) {
                String e3 = v.e(jd.wjlogin_sdk.util.e.v);
                if (!TextUtils.isEmpty(e3)) {
                    if (onDataCallback != null) {
                        SuccessResult successResult = new SuccessResult();
                        successResult.setStrVal(e3);
                        onDataCallback.onSuccessHandleInner(successResult);
                        return;
                    }
                    return;
                }
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new o(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalSendGetCountryCodeList");
            hVar.b();
        } catch (Exception unused) {
            a(onDataCallback);
        }
    }

    public void globalTwitterLogin(TwitterTokenInfo twitterTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        globalTwitterLogin(twitterTokenInfo, null, onDataCallback);
    }

    public void globalWxLogin(WXTokenInfo wXTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        globalWxLogin(wXTokenInfo, null, onDataCallback);
    }

    public void globalZeusLogin(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 20, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.g(bVar, str);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new i(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalFBLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalCheckPhoneLoginMessage(String str, String str2, String str3, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 18, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new g(str, onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalGetSid");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalFBLogin(FBTokenInfo fBTokenInfo, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, fBTokenInfo);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new s(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalFBLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalGoogleLogin(GoogleTokenInfo googleTokenInfo, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 10, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, googleTokenInfo);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new a(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalGoogleLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalLineLogin(LineTokenInfo lineTokenInfo, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 11, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.g(bVar, "");
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, lineTokenInfo);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new b(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalLineLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalRegister(String str, String str2, String str3, String str4, boolean z, GlobalRegisterInfo globalRegisterInfo, JSONObject jSONObject, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 19, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.j(bVar, str3);
            jd.wjlogin_sdk.d.d.p(bVar, str4);
            if (z) {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 1);
            } else {
                jd.wjlogin_sdk.d.d.a(bVar, (short) 2);
            }
            jd.wjlogin_sdk.d.d.f(bVar, str3);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.p(bVar, str4);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, globalRegisterInfo);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new d(str, str4, z, str3, onCommonCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalRegister");
            hVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalTwitterLogin(TwitterTokenInfo twitterTokenInfo, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, p());
            jd.wjlogin_sdk.d.d.a(bVar, twitterTokenInfo);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new c(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalTwitterLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void globalWxLogin(WXTokenInfo wXTokenInfo, JSONObject jSONObject, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 21, (short) 24, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, wXTokenInfo);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.h hVar = new jd.wjlogin_sdk.c.h(new j(onDataCallback));
            hVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("globalWxLogin");
            hVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 19, (short) 6);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 19, (short) 6);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 19, (short) 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void f(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 10);
                    return;
                }
                b((byte) -2, (short) 21, (short) 10);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 10);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                n0 J = aVar.J();
                s0 N = aVar.N();
                if (J != null && N != null) {
                    a(aVar);
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                } else if (onCommonCallback != null) {
                    onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(y.a)));
                }
                b(b2, (short) 18, (short) 4);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 18, (short) 4);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 18, (short) 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<ReqJumpTokenResp> onDataCallback) {
        String b3;
        try {
            String pin = getPin();
            short h2 = jd.wjlogin_sdk.util.g.d().h();
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.d b4 = aVar.b();
                String a2 = aVar.d().a();
                if (!TextUtils.isEmpty(pin)) {
                    ByteBuffer allocate = ByteBuffer.allocate(256);
                    byte[] bytes = pin.getBytes();
                    allocate.putShort((short) bytes.length);
                    allocate.put(bytes);
                    allocate.putShort(h2);
                    allocate.putShort((short) b4.a().length);
                    allocate.put(b4.a());
                    allocate.flip();
                    byte[] bArr = new byte[allocate.limit()];
                    allocate.get(bArr);
                    allocate.clear();
                    b3 = jd.wjlogin_sdk.util.b.b(bArr);
                } else {
                    ByteBuffer allocate2 = ByteBuffer.allocate(256);
                    allocate2.putShort((short) 0);
                    allocate2.putShort(h2);
                    allocate2.putShort((short) b4.a().length);
                    allocate2.put(b4.a());
                    allocate2.flip();
                    byte[] bArr2 = new byte[allocate2.limit()];
                    allocate2.get(bArr2);
                    allocate2.clear();
                    b3 = jd.wjlogin_sdk.util.b.b(bArr2);
                }
                if (onDataCallback != null) {
                    ReqJumpTokenResp reqJumpTokenResp = new ReqJumpTokenResp();
                    reqJumpTokenResp.setUrl(a2);
                    reqJumpTokenResp.setToken(b3);
                    onDataCallback.onSuccessHandleInner(reqJumpTokenResp);
                }
                b(b2, (short) 28, (short) 3);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 28, (short) 3);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 28, (short) 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void i(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 9);
                    return;
                }
                b((byte) -2, (short) 21, (short) 9);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 9);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void j(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onDataCallback != null) {
                        jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                        SuccessResult successResult = new SuccessResult();
                        if (t != null) {
                            successResult.setStrVal(t.a());
                        }
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 24);
                    a((byte) 6, (short) 21, (short) 24);
                    return;
                }
                b((byte) -2, (short) 21, (short) 24);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 24);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void k(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 20);
                    return;
                }
                b((byte) -2, (short) 21, (short) 20);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 20);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 20);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte b2, jd.wjlogin_sdk.tlvtype.a aVar) {
        try {
            if (b2 == 0) {
                b(b2, (short) 18, (short) 3);
                return;
            }
            a(new FailResult(), b2, aVar.p());
            b(b2, (short) 18, (short) 3);
        } catch (Exception unused) {
            b((byte) -2, (short) 18, (short) 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                z x = aVar.x();
                String str = "";
                if (x != null) {
                    String a2 = x.a();
                    v.a(jd.wjlogin_sdk.util.e.x, new Date().getTime() + "");
                    v.a(jd.wjlogin_sdk.util.e.v, a2);
                    str = a2;
                }
                if (onDataCallback != null) {
                    SuccessResult successResult = new SuccessResult();
                    if (TextUtils.isEmpty(str)) {
                        str = jd.wjlogin_sdk.util.f.a;
                    }
                    successResult.setStrVal(str);
                    onDataCallback.onSuccessHandleInner(successResult);
                    return;
                }
                return;
            }
            a(onDataCallback);
        } catch (Exception unused) {
            a(onDataCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 19, (short) 3);
                return;
            }
            x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            jd.wjlogin_sdk.tlvtype.c a2 = aVar.a();
            a0 y = aVar.y();
            JumpResult jumpResult = null;
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (a2 != null) {
                jumpResult = new JumpResult();
                if (!TextUtils.isEmpty(a2.a())) {
                    jumpResult.setToken(new String(ByteUtil.parseHexStr2Byte(a2.a())));
                }
            }
            if (y != null && jumpResult != null) {
                jumpResult.setUrl(y.a());
            }
            failResult.setJumpResult(jumpResult);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 19, (short) 3);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 19, (short) 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r11.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, OnLoginCallback onLoginCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onLoginCallback != null) {
                        onLoginCallback.onSuccessHandleInner();
                    }
                    a(str, b2, (short) 18, (short) 1);
                    return;
                }
                b((byte) -2, (short) 18, (short) 1);
                return;
            }
            x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b3, i2));
            if (onLoginCallback != null) {
                onLoginCallback.onFailHandleInner(failResult);
            }
            a(str, b2, (short) 18, (short) 1);
        } catch (Exception unused) {
            if (onLoginCallback != null) {
                onLoginCallback.onFailHandleInner(a());
            }
            a(str, (byte) -2, (short) 18, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 1);
                    return;
                }
                b((byte) -2, (short) 21, (short) 1);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 1);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 18, (short) 8);
                return;
            }
            x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 18, (short) 8);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 18, (short) 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void g(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    b(b2, (short) 21, (short) 1);
                    return;
                }
                b((byte) -2, (short) 21, (short) 1);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 21, (short) 1);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 21, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 19, (short) 4);
                return;
            }
            x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 19, (short) 4);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 19, (short) 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        jd.wjlogin_sdk.tlvtype.s q2;
        try {
            if (b2 == 0) {
                j0 F = aVar.F();
                if (F != null) {
                    jd.wjlogin_sdk.util.s.a(F.a());
                }
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 19, (short) 8);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (b2 == 3 && onCommonCallback != null && (q2 = aVar.q()) != null) {
                String a2 = q2.a();
                if (!TextUtils.isEmpty(a2)) {
                    failResult.setStrVal(a2);
                }
            }
            j0 F2 = aVar.F();
            if (F2 != null) {
                jd.wjlogin_sdk.util.s.a(F2.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 19, (short) 8);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 19, (short) 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    a(aVar, (String) null);
                    if (onDataCallback != null) {
                        jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                        SuccessResult successResult = new SuccessResult();
                        successResult.setStrVal(t.a() != null ? t.a() : "");
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                }
                b(b2, (short) 18, (short) 6);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 18, (short) 6);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 18, (short) 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OnDataCallback<SuccessResult> onDataCallback) {
        if (onDataCallback != null) {
            String e2 = v.e(jd.wjlogin_sdk.util.e.v);
            String str = TextUtils.isEmpty(e2) ? jd.wjlogin_sdk.util.f.a : e2;
            if (!TextUtils.equals(e2, str)) {
                v.a(jd.wjlogin_sdk.util.e.v, str);
            }
            SuccessResult successResult = new SuccessResult();
            successResult.setStrVal(str);
            onDataCallback.onSuccessHandleInner(successResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, String str2, boolean z, String str3, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 19, (short) 7);
                    return;
                }
                b((byte) -2, (short) 19, (short) 7);
                return;
            }
            x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 19, (short) 7);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 19, (short) 7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(String str, byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    jd.wjlogin_sdk.tlvtype.v t = aVar.t();
                    SuccessResult successResult = new SuccessResult();
                    if (t != null) {
                        successResult.setStrVal(t.a());
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                    a(str, b2, (short) 18, (short) 9);
                    return;
                }
                b((byte) -2, (short) 18, (short) 9);
                return;
            }
            x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 18, (short) 9);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 18, (short) 9);
        }
    }
}
