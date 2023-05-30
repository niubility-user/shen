package jd.wjlogin_sdk.common.inland;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.unionpay.tsmservice.data.Constant;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jd.wjlogin_sdk.common.facerecognition.WJLoginFaceRecognition;
import jd.wjlogin_sdk.common.listener.OnA2RefreshCallback;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.common.listener.OnLoginCallback;
import jd.wjlogin_sdk.model.A4LoginInfo;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.JumpResult;
import jd.wjlogin_sdk.model.QQTokenInfo;
import jd.wjlogin_sdk.model.QRCodeScannedResult;
import jd.wjlogin_sdk.model.ReqJumpTokenResp;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.model.WJUserInfo;
import jd.wjlogin_sdk.model.WUserSigInfo;
import jd.wjlogin_sdk.model.WXTokenInfo;
import jd.wjlogin_sdk.tlvtype.n0;
import jd.wjlogin_sdk.tlvtype.s0;
import jd.wjlogin_sdk.util.ByteUtil;
import jd.wjlogin_sdk.util.ReplyCode;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WJLoginInland extends WJLoginFaceRecognition {
    private static final String t = "WJLogin.WJLoginInland";

    /* loaded from: classes.dex */
    public class a implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        a(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.s(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    public class a0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        a0(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.o(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<A4LoginInfo>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 9, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    public class b implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f19793c;
        final /* synthetic */ OnCommonCallback d;

        b(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19793c = str3;
            this.d = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(((Byte) pair.first).byteValue(), this.a, this.b, this.f19793c, (jd.wjlogin_sdk.tlvtype.a) pair.second, this.d);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.d;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    public class b0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ short a;
        final /* synthetic */ OnDataCallback b;

        b0(short s, OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = s;
            this.b = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.b;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, this.a);
        }
    }

    /* loaded from: classes.dex */
    public class c implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        c(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.y(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    class c0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        c0(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.s(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 11);
        }
    }

    /* loaded from: classes.dex */
    public class d implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        d(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.r(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class d0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        d0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.t(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 12);
        }
    }

    /* loaded from: classes.dex */
    class e implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        e(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.p(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<QRCodeScannedResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 7, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class e0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        e0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.A(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 13);
        }
    }

    /* loaded from: classes.dex */
    class f implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        f(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
        }
    }

    /* loaded from: classes.dex */
    class f0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        f0(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.u(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 54, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    public class g implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        g(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.x(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 7, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    public class g0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        g0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.B(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 3, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    public class h implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        h(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.z(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 2, (short) 12);
        }
    }

    /* loaded from: classes.dex */
    public class h0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        h0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.r(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 3, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class i implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        i(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.w(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 9);
        }
    }

    /* loaded from: classes.dex */
    class j implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        j(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.q(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            WJLoginInland.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class j0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        j0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.p(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 5, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class k implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnLoginCallback b;

        /* renamed from: c */
        final /* synthetic */ jd.wjlogin_sdk.d.c f19797c;

        k(String str, OnLoginCallback onLoginCallback, jd.wjlogin_sdk.d.c cVar) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = onLoginCallback;
            this.f19797c = cVar;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19797c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnLoginCallback onLoginCallback = this.b;
            if (onLoginCallback != null) {
                onLoginCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.a(this.a, (byte) -1, this.f19797c);
        }
    }

    /* loaded from: classes.dex */
    class k0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        k0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.o(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 5, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    class l implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        l(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.v(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 11, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    public class l0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        l0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.q(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 5, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    public class m implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        m(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.v(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class m0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        m0(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.u(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 4, (short) 11);
        }
    }

    /* loaded from: classes.dex */
    public class n implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        n(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.w(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    class o implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        o(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.t(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    public class p implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        p(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.E(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    public class q implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        q(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.F(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    public class r implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        r(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.C(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    public class s implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        s(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.D(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 33, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    public class t implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        t(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.y(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 39, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    public class u implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        u(OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.x(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 39, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class v implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        v(OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.B(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 3, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    public class w implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ OnCommonCallback f19798c;

        w(String str, String str2, OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19798c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19798c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19798c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 39, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    public class x implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ OnCommonCallback f19799c;

        x(String str, String str2, OnCommonCallback onCommonCallback) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19799c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19799c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19799c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 39, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    class y implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ OnCommonCallback f19800c;
        final /* synthetic */ jd.wjlogin_sdk.d.c d;

        y(String str, String str2, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.d.c cVar) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19800c = onCommonCallback;
            this.d = cVar;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19800c, this.d);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19800c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, this.d.d(), this.d.o());
        }
    }

    /* loaded from: classes.dex */
    class z implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ OnCommonCallback f19802c;
        final /* synthetic */ jd.wjlogin_sdk.d.c d;

        z(String str, String str2, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.d.c cVar) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19802c = onCommonCallback;
            this.d = cVar;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginInland.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19802c, this.d);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19802c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, this.d.d(), this.d.o());
        }
    }

    public void CheckA2(OnCommonCallback onCommonCallback) {
        a(getPin(), true, onCommonCallback);
    }

    public void JDLoginWithPasswordNew(String str, String str2, String str3, String str4, OnLoginCallback onLoginCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "JDLoginWithPasswordNew");
                    jSONObject.put("strAccount", str);
                    jSONObject.put("sid", str3);
                    jSONObject.put("token", str4);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            jd.wjlogin_sdk.d.c a2 = jd.wjlogin_sdk.d.d.a((short) 39, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq);
            bVar.a(a2);
            jd.wjlogin_sdk.d.d.b(bVar, str, str2);
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.k(bVar, str3);
            jd.wjlogin_sdk.d.d.b(bVar, str4);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new k(str, onLoginCallback, a2));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("JDLoginWithPasswordNew");
            gVar.b();
        } catch (Exception e3) {
            if (onLoginCallback != null) {
                onLoginCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void bindAccountLogin(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "bindAccountLogin");
                    jSONObject.put("token", str);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new l0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("bindAccountLogin");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void cancelQRCodeLogined(String str, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.i(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new f(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("cancelQRCodeLogined");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void checkA2(String str, OnCommonCallback onCommonCallback) {
        a(str, false, onCommonCallback);
    }

    public void checkHistory4JDPhoneNumLoginNew(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkHistory4JDPhoneNumLoginNew");
                    jSONObject.put("phoneNum", str);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                    jSONObject.put("historyPerson", str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.o(bVar, str3);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new x(str, str2, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkHistory4JDPhoneNumLoginNew");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    @Deprecated
    public void checkMessageCode(String str, String str2, OnCommonCallback onCommonCallback) {
        checkMessageCode(str, str2, jd.wjlogin_sdk.util.f.d, onCommonCallback);
    }

    public void checkMsgCodeForPhoneNumLogin4JD(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkMsgCodeForPhoneNumLogin4JD");
                    jSONObject.put("phoneNum", str);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (TextUtils.isEmpty(str3)) {
                jd.wjlogin_sdk.d.d.p(bVar, jd.wjlogin_sdk.util.f.d);
            } else {
                jd.wjlogin_sdk.d.d.p(bVar, str3);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new w(str, str3, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("checkMsgCodeForPhoneNumLogin4JD");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void checkOnekeyRegisterVoiceSms(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 12, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new d0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkOnekeyRegisterVoiceSms");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void checkSlideAndPhoneNum(String str, String str2, String str3, String str4, boolean z2, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkSlideAndPhoneNum");
                    jSONObject.put("token", str);
                    jSONObject.put("sid", str2);
                    jSONObject.put("phoneNumber", str3);
                    jSONObject.put("councryCode", str4);
                    jSONObject.put("isNeedFigureUrl", z2);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 11, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.e(bVar, str3);
            jd.wjlogin_sdk.d.d.k(bVar, str2);
            jd.wjlogin_sdk.d.d.p(bVar, str4);
            jd.wjlogin_sdk.d.d.a(bVar, z2 ? 1 : 0);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new m0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkSlideAndPhoneNum");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void chinaMobileOneKeyLogin(String str, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new m(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("chinaMobileOneKeyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void chinaMobileOneKeyRegister(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            int i2 = 2;
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new n(onCommonCallback));
            jd.wjlogin_sdk.c.g a2 = gVar.a(bVar.a());
            if (!x()) {
                i2 = 1;
            }
            a2.a(i2).a(true).a(bVar.b()).a("chinaMobileOneKeyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void confirmQRCodeLogined(String str, OnCommonCallback onCommonCallback) {
        confirmQRCodeLogined(str, getA2(), getPin(), onCommonCallback);
    }

    public void confirmQRCodeScanned(String str, OnDataCallback<QRCodeScannedResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.i(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new e(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("confirmQRCodeScanned");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void forceRefreshA2(OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 3, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            jd.wjlogin_sdk.d.d.a(bVar, getPin());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.w(bVar, jd.wjlogin_sdk.common.communion.b.b());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new v(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("forceRefreshA2");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getCaptchaSid(int i2, JSONObject jSONObject, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("callMethod", "getCaptchaSid");
                    jSONObject2.put("type", i2);
                    if (jSONObject != null) {
                        jSONObject2.put("params", jSONObject);
                    }
                    b(t, jSONObject2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            jd.wjlogin_sdk.d.d.b(bVar, i2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new t(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getCaptchaSid");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public String getLocalCountryCode() {
        String e2 = jd.wjlogin_sdk.util.v.e(jd.wjlogin_sdk.util.e.u);
        return TextUtils.isEmpty(e2) ? jd.wjlogin_sdk.util.f.a : e2;
    }

    @Deprecated
    public void getMessageCode(String str, OnDataCallback<SuccessResult> onDataCallback) {
        getMessageCode(str, jd.wjlogin_sdk.util.f.d, onDataCallback);
    }

    public void getOnekeyRegisterVoiceSms(String str, OnDataCallback onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 11, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new c0(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("onekeyRegisterSendVoiceSms");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public String getQRCodeKeyFromUrl(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    String queryParameter = Uri.parse(str).getQueryParameter("k");
                    return queryParameter == null ? "" : queryParameter;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public void getTelecomMobile(String str, int i2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            int i3 = i2 != 10 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? 15000 : 5000 : 4000 : 3000 : 2000 : 1500 : 10000;
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new o(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(i3).a(bVar.b()).a("getTelecomMobile");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getUsersInfo(OnDataCallback onDataCallback) {
        try {
            if (!hasLogin()) {
                if (onDataCallback == null || onDataCallback == null) {
                    return;
                }
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception("\u672a\u767b\u5f55\uff0c\u4e0d\u5141\u8bb8\u83b7\u53d6\u6635\u79f0")));
                return;
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 54, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, getPin());
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            jd.wjlogin_sdk.d.d.a(bVar, s());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new f0(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getUsersInfo");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public String getWJLoginSDKVersion() {
        return jd.wjlogin_sdk.util.e.a;
    }

    public void h5BackToApp(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "h5BackToApp");
                    jSONObject.put("jumpToken", str);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 2, (short) 12, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (!TextUtils.isEmpty(getPin()) && !TextUtils.isEmpty(getA2())) {
                jd.wjlogin_sdk.d.d.a(bVar, getPin());
                jd.wjlogin_sdk.d.d.y(bVar, getA2());
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new h(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("h5BackToApp");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void isNeedVerifyForPhoneNumLogin(String str, String str2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 11, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (TextUtils.isEmpty(str2)) {
                jd.wjlogin_sdk.d.d.p(bVar, jd.wjlogin_sdk.util.f.d);
            } else {
                jd.wjlogin_sdk.d.d.p(bVar, str2);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new l(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("isNeedVerifyForPhoneNumLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void onekeyRegisterPassword(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 13, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            if (!TextUtils.isEmpty(str2)) {
                jd.wjlogin_sdk.d.d.f(bVar, str2);
            }
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new e0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("onekeyRegisterPassword");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void qqLogin(QQTokenInfo qQTokenInfo, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "qqLogin");
                    jSONObject.put("QQToken", qQTokenInfo.getAccessToken());
                    jSONObject.put("QQopenId", qQTokenInfo.getOpenid());
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, qQTokenInfo);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new k0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("qqLogin");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void refreshA2(OnCommonCallback onCommonCallback) {
        try {
            if (isExistsA2() && !c() && b()) {
                this.seq++;
                jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
                bVar.a(jd.wjlogin_sdk.d.d.a((short) 3, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
                jd.wjlogin_sdk.d.d.y(bVar, getA2());
                jd.wjlogin_sdk.d.d.a(bVar, getPin());
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
                jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
                jd.wjlogin_sdk.d.d.u(bVar, q());
                jd.wjlogin_sdk.d.d.w(bVar, jd.wjlogin_sdk.common.communion.b.b());
                this.a = System.currentTimeMillis();
                jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new g0(onCommonCallback));
                gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("refreshA2");
                gVar.b();
            } else {
                jd.wjlogin_sdk.util.p.b(t, "no need refresh!");
                if (onCommonCallback != null && (onCommonCallback instanceof OnA2RefreshCallback)) {
                    ((OnA2RefreshCallback) onCommonCallback).onA2NoNeedRefresh();
                }
            }
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void registJumpToM(OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "registJumpToM");
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new i(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("registJumpToM");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void registerNonePassword(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "registerNonePassword");
                    jSONObject.put("phoneNum", str);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            jd.wjlogin_sdk.d.c a2 = jd.wjlogin_sdk.d.d.a((short) 4, (short) 12, jd.wjlogin_sdk.util.g.d(), this.seq);
            bVar.a(a2);
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            a(bVar, onCommonCallback, (jd.wjlogin_sdk.c.f) new y(str, str2, onCommonCallback, a2), "registerNonePassword", true);
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void reportLoginLog() {
        if (hasLogin()) {
            long a2 = jd.wjlogin_sdk.util.v.a(jd.wjlogin_sdk.util.e.F, 0L);
            long currentTimeMillis = System.currentTimeMillis();
            jd.wjlogin_sdk.util.p.b(t, "lastTime=" + a2);
            jd.wjlogin_sdk.util.p.b(t, "currentTime=" + currentTimeMillis);
            if (currentTimeMillis - a2 < jd.wjlogin_sdk.util.e.G) {
                return;
            }
            a((byte) 1, (short) 16, (short) 1);
            jd.wjlogin_sdk.util.v.b(jd.wjlogin_sdk.util.e.F, System.currentTimeMillis());
        }
    }

    public void reqJumpToken(String str, OnDataCallback<ReqJumpTokenResp> onDataCallback) {
        a("", "", str, onDataCallback, "");
    }

    public void sendGetA4(OnDataCallback<A4LoginInfo> onDataCallback) {
        try {
            if (!d()) {
                if (jd.wjlogin_sdk.util.p.b) {
                    jd.wjlogin_sdk.util.p.a(t, "use cached a4");
                }
                if (onDataCallback != null) {
                    A4LoginInfo a4LoginInfo = new A4LoginInfo();
                    a4LoginInfo.setA4(f().b());
                    onDataCallback.onSuccess(a4LoginInfo);
                    return;
                }
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 9, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            String str = "";
            jd.wjlogin_sdk.d.d.a(bVar, getPin() == null ? "" : getPin());
            if (getA2() != null) {
                str = getA2();
            }
            jd.wjlogin_sdk.d.d.y(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a0(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("sendGetA4");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void sendGetCountryCodeList(OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "sendGetCountryCodeList");
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String e3 = jd.wjlogin_sdk.util.v.e(jd.wjlogin_sdk.util.e.w);
            if (!TextUtils.isEmpty(e3) && jd.wjlogin_sdk.util.b0.a(e3) <= 1) {
                String e4 = jd.wjlogin_sdk.util.v.e(jd.wjlogin_sdk.util.e.u);
                if (!TextUtils.isEmpty(e4)) {
                    if (onDataCallback != null) {
                        SuccessResult successResult = new SuccessResult();
                        successResult.setStrVal(e4);
                        onDataCallback.onSuccessHandleInner(successResult);
                        return;
                    }
                    return;
                }
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 10, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new j(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("sendGetCountryCodeList");
            gVar.b();
        } catch (Exception unused) {
            a(onDataCallback);
        }
    }

    public void sendMsgCodeForPhoneNumLogin4JD(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "sendMsgCodeForPhoneNumLogin4JD");
                    jSONObject.put("phoneNum", str);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                    jSONObject.put("sid", str3);
                    jSONObject.put("token", str4);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (TextUtils.isEmpty(str2)) {
                jd.wjlogin_sdk.d.d.p(bVar, jd.wjlogin_sdk.util.f.d);
            } else {
                jd.wjlogin_sdk.d.d.p(bVar, str2);
            }
            jd.wjlogin_sdk.d.d.k(bVar, str3);
            jd.wjlogin_sdk.d.d.b(bVar, str4);
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new u(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("sendMsgCodeForPhoneNumLogin4JD");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    @Deprecated
    public void setLoginPassword(String str, String str2, OnCommonCallback onCommonCallback) {
        setLoginPassword(str, str2, jd.wjlogin_sdk.util.f.d, onCommonCallback);
    }

    public void setPasswordForPhoneNumLogin4JD(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "setPasswordForPhoneNumLogin4JD");
                    jSONObject.put("phoneNum", str);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            jd.wjlogin_sdk.d.c a2 = jd.wjlogin_sdk.d.d.a((short) 39, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq);
            bVar.a(a2);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d());
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            a(bVar, onCommonCallback, (jd.wjlogin_sdk.c.f) new z(str, str3, onCommonCallback, a2), "setPasswordForPhoneNumLogin4JD", true);
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void telecomMobileOneKeyRegister(String str, String str2, OnDataCallback onDataCallback) {
        a(str, str2, (short) 9, "telecomMobileOneKeyRegister", onDataCallback);
    }

    public void telecomOneKeyLogin(String str, String str2, OnCommonCallback onCommonCallback) {
        telecomOneKeyLogin(str, str2, "", onCommonCallback);
    }

    public void telecomOneKeyRegister(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new s(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("telecomOneKeyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    @Deprecated
    public void unBindPhoneNum(String str, OnDataCallback<SuccessResult> onDataCallback) {
        unBindPhoneNum(str, jd.wjlogin_sdk.util.f.d, onDataCallback);
    }

    public void unicomMobileOneKeyRegister(String str, OnDataCallback onDataCallback) {
        a(str, "", (short) 10, "unicomMobileOneKeyRegister", onDataCallback);
    }

    public void unicomOneKeyLogin(String str, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new p(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("unicomOneKeyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void unicomOneKeyRegister(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new q(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("unicomOneKeyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void wxLogin(WXTokenInfo wXTokenInfo, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "wxLogin");
                    jSONObject.put("WXTokenInfo", wXTokenInfo.getCode());
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, wXTokenInfo);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.d(bVar, i());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new j0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("wxLogin");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0031, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void A(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, "");
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 13);
                    return;
                }
                b((byte) -2, (short) 33, (short) 13);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 13);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 33, (short) 13);
        }
    }

    public void B(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
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
                    onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(jd.wjlogin_sdk.util.y.a)));
                }
                b(b2, (short) 3, (short) 2);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 3, (short) 2);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 3, (short) 2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0034, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void C(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 4);
                    a((byte) 14, (short) 33, (short) 4);
                    return;
                }
                b((byte) -2, (short) 33, (short) 4);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            jd.wjlogin_sdk.tlvtype.h f2 = aVar.f();
            if (f2 != null) {
                failResult.setStrVal(f2.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 4);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 4);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0034, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void D(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 5);
                    a((byte) 14, (short) 33, (short) 5);
                    return;
                }
                b((byte) -2, (short) 33, (short) 5);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 5);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 5);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0034, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void E(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 6);
                    a((byte) 13, (short) 33, (short) 6);
                    return;
                }
                b((byte) -2, (short) 33, (short) 6);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            jd.wjlogin_sdk.tlvtype.h f2 = aVar.f();
            if (f2 != null) {
                failResult.setStrVal(f2.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 6);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0034, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void F(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 7);
                    a((byte) 13, (short) 33, (short) 7);
                    return;
                }
                b((byte) -2, (short) 33, (short) 7);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 7);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 7);
        }
    }

    public void s(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 4, (short) 5);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 4, (short) 5);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 4, (short) 5);
        }
    }

    public void t(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.h f2 = aVar.f();
                String a2 = f2 != null ? f2.a() : "";
                SuccessResult successResult = new SuccessResult();
                successResult.setStrVal(a2);
                onDataCallback.onSuccessHandleInner(successResult);
                b(b2, (short) 33, (short) 3);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 3);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 3);
        }
    }

    public void u(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 4, (short) 11);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            jd.wjlogin_sdk.tlvtype.c a2 = aVar.a();
            jd.wjlogin_sdk.tlvtype.a0 y2 = aVar.y();
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
            if (y2 != null && jumpResult != null) {
                jumpResult.setUrl(y2.a());
            }
            failResult.setJumpResult(jumpResult);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 4, (short) 11);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 4, (short) 11);
        }
    }

    public void v(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    String a2 = aVar.q().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setStrVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 11, (short) 7);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 11, (short) 7);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 11, (short) 7);
        }
    }

    public void w(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
                if (i2 != null) {
                    String a2 = i2.a();
                    if (onDataCallback != null) {
                        SuccessResult successResult = new SuccessResult();
                        successResult.setStrVal(a2);
                        onDataCallback.onSuccessHandleInner(successResult);
                    }
                }
                b(b2, (short) 4, (short) 9);
            } else if (b2 == -96) {
                jd.wjlogin_sdk.tlvtype.l i3 = aVar.i();
                if (i3 != null) {
                    String a3 = i3.a();
                    JumpResult jumpResult = new JumpResult();
                    jumpResult.setUrl(a3);
                    FailResult failResult = new FailResult();
                    failResult.setReplyCode(ReplyCode.reply0xa0);
                    a(failResult, jumpResult);
                    if (onDataCallback != null) {
                        onDataCallback.onFailHandleInner(failResult);
                    }
                }
            } else {
                jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
                FailResult failResult2 = new FailResult();
                a(failResult2, b2, p2);
                onDataCallback.onFailHandleInner(failResult2);
                b(b2, (short) 4, (short) 9);
            }
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 4, (short) 9);
        }
    }

    public void x(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 7, (short) 4);
                return;
            }
            QRCodeScannedResult a2 = a(aVar.m(), aVar.j());
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setQrCodeScannedResult(a2);
            onCommonCallback.onFailHandleInner(failResult);
            b(b2, (short) 7, (short) 4);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 7, (short) 4);
        }
    }

    public void y(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 4, (short) 7);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 4, (short) 7);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 4, (short) 7);
        }
    }

    public void z(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    a(aVar, (String) null);
                    onCommonCallback.onSuccessHandleInner();
                }
                a((byte) 11, (short) 16, (short) 1);
                b(b2, (short) 2, (short) 12);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            onCommonCallback.onFailHandleInner(failResult);
            b(b2, (short) 2, (short) 12);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 2, (short) 12);
        }
    }

    public void checkMessageCode(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkMessageCode");
                    jSONObject.put("messageCode", str2);
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("councryCode", str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkMessageCode");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void confirmQRCodeLogined(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.y(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, str3);
            jd.wjlogin_sdk.d.d.i(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new g(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("confirmQRCodeLogined");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getMessageCode(String str, String str2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "getMessageCode");
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("councryCode", str2);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new d(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getMessageCode");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void reqJumpToken(String str, String str2, String str3, OnDataCallback<ReqJumpTokenResp> onDataCallback) {
        a(str, str2, str3, onDataCallback, "outParam");
    }

    public void setLoginPassword(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "setLoginPassword");
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("councryCode", str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b(str, str2, str3, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("setLoginPassword");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void telecomOneKeyLogin(String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.z(bVar, str3);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new r(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("telecomOneKeyLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void unBindPhoneNum(String str, String str2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "unBindPhoneNum");
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("councryCode", str2);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 4, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new c(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("unBindPhoneNum");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0034, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void o(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 5, (short) 7);
                    a((byte) 7, (short) 16, (short) 1);
                    return;
                }
                b((byte) -2, (short) 5, (short) 7);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 5, (short) 7);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 5, (short) 7);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0034, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void p(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 5, (short) 1);
                    a((byte) 6, (short) 16, (short) 1);
                    return;
                }
                b((byte) -2, (short) 5, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 5, (short) 1);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 5, (short) 1);
        }
    }

    public void q(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                a((byte) 10, (short) 16, (short) 1);
                b(b2, (short) 5, (short) 4);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 5, (short) 4);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 5, (short) 4);
        }
    }

    public void r(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 3, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 3, (short) 1);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 3, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    public class i0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ OnDataCallback f19796c;

        i0(String str, String str2, OnDataCallback onDataCallback) {
            WJLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f19796c = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            String pin = WJLoginInland.this.getPin();
            if ("outParam".equals(this.a)) {
                pin = this.b;
            }
            WJLoginInland.this.b(pin, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<ReqJumpTokenResp>) this.f19796c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.f19796c;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginInland.this.b((byte) -1, (short) 2, (short) 9);
        }
    }

    public void b(String str, byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<ReqJumpTokenResp> onDataCallback) {
        String b3;
        try {
            short h2 = jd.wjlogin_sdk.util.g.d().h();
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.d b4 = aVar.b();
                String a2 = aVar.d().a();
                if (!TextUtils.isEmpty(str)) {
                    ByteBuffer allocate = ByteBuffer.allocate(256);
                    byte[] bytes = str.getBytes();
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
                    try {
                        if (jd.wjlogin_sdk.util.p.b) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("callMethod", "reqJumpTokenSuccess");
                            jSONObject.put("responseUrl", a2);
                            jSONObject.put("responseToken", b3);
                            a(t, jSONObject);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    onDataCallback.onSuccessHandleInner(reqJumpTokenResp);
                }
                a(str, b2, (short) 2, (short) 9);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            a(str, b2, (short) 2, (short) 9);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            a(str, (byte) -2, (short) 2, (short) 9);
        }
    }

    public void s(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback onDataCallback) {
        try {
            if (b2 == 0) {
                SuccessResult successResult = new SuccessResult();
                successResult.setIntVal(aVar.g().a());
                if (onDataCallback != null) {
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 33, (short) 11);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 11);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 11);
        }
    }

    public void a(String str, byte b2, jd.wjlogin_sdk.d.c cVar) {
        a(str, b2, cVar.d(), cVar.o());
    }

    public void r(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 4, (short) 4);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 4, (short) 4);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 4, (short) 4);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0037, code lost:
        r10.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.d.c cVar) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    a(str, b2, cVar);
                    a((byte) 5, cVar.d(), cVar.o());
                    return;
                }
                a(str, (byte) -2, cVar);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            jd.wjlogin_sdk.tlvtype.i0 E = aVar.E();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b3, i2));
            if (E != null) {
                failResult.setIntVal(E.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            a(str, b2, cVar);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            a(str, (byte) -2, cVar);
        }
    }

    public void q(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.z x2 = aVar.x();
                String str = "";
                if (x2 != null) {
                    String a2 = x2.a();
                    jd.wjlogin_sdk.util.v.a(jd.wjlogin_sdk.util.e.w, new Date().getTime() + "");
                    jd.wjlogin_sdk.util.v.a(jd.wjlogin_sdk.util.e.u, a2);
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

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0034, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void v(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 1);
                    a((byte) 12, (short) 33, (short) 1);
                    return;
                }
                b((byte) -2, (short) 33, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            jd.wjlogin_sdk.tlvtype.h f2 = aVar.f();
            if (f2 != null) {
                failResult.setStrVal(f2.a());
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 1);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 1);
        }
    }

    public void chinaMobileOneKeyRegister(String str, OnDataCallback onDataCallback) {
        a(str, "", (short) 8, "chinaMobileOneKeyRegister", onDataCallback);
    }

    public void t(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 33, (short) 12);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 12);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 12);
        }
    }

    public void x(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 39, (short) 4);
                jd.wjlogin_sdk.util.p.b("sendMsgCodeForPhoneNumLogin4JD code" + ((int) b2) + "******sendMsgCodeForPhoneNumLogin4JD time" + System.currentTimeMillis());
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 39, (short) 4);
            jd.wjlogin_sdk.util.p.a("sendMsgCodeForPhoneNumLogin4JD code" + ((int) b2) + "******sendMsgCodeForPhoneNumLogin4JD time" + System.currentTimeMillis() + "******sendMsgCodeForPhoneNumLogin4JD msg" + failResult.getMessage());
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 39, (short) 4);
        }
    }

    public void y(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 39, (short) 2);
                return;
            }
            jd.wjlogin_sdk.tlvtype.s q2 = aVar.q();
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            failResult.setReplyCode(b2);
            if (p2 != null) {
                if (p2.b() != null) {
                    failResult.setMessage(p2.b());
                } else {
                    failResult.setMessage(p2.a());
                }
            }
            if (q2 != null && !TextUtils.isEmpty(q2.a())) {
                failResult.setStrVal(q2.a());
                try {
                    if (jd.wjlogin_sdk.util.p.b) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("callMethod", "getCaptchaSidSuccess");
                        jSONObject.put("reponseSid", q2.a());
                        a(t, jSONObject);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 39, (short) 2);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 39, (short) 2);
        }
    }

    public void o(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<A4LoginInfo> onDataCallback) {
        if (b2 == 0) {
            try {
                jd.wjlogin_sdk.tlvtype.c a2 = aVar.a();
                jd.wjlogin_sdk.tlvtype.r o2 = aVar.o();
                s0 N = aVar.N();
                jd.wjlogin_sdk.common.a aVar2 = new jd.wjlogin_sdk.common.a();
                if (o2 != null) {
                    aVar2.b(o2.a());
                }
                if (N != null) {
                    aVar2.a(N.a());
                    aVar2.b(N.b());
                }
                if (a2 != null) {
                    aVar2.c(new String(ByteUtil.parseHexStr2Byte(a2.a())));
                }
                aVar2.a(new Date());
                a(aVar2);
                if (onDataCallback != null) {
                    A4LoginInfo a4LoginInfo = new A4LoginInfo();
                    a4LoginInfo.setA4(aVar2.b());
                    onDataCallback.onSuccessHandleInner(a4LoginInfo);
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (onDataCallback != null) {
                    onDataCallback.onFailHandleInner(a());
                    return;
                }
                return;
            }
        }
        jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
        FailResult failResult = new FailResult();
        a(failResult, b2, p2);
        if (onDataCallback != null) {
            onDataCallback.onFailHandleInner(failResult);
        }
    }

    public void p(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<QRCodeScannedResult> onDataCallback) {
        try {
            jd.wjlogin_sdk.tlvtype.p m2 = aVar.m();
            jd.wjlogin_sdk.tlvtype.m j2 = aVar.j();
            jd.wjlogin_sdk.tlvtype.t r2 = aVar.r();
            jd.wjlogin_sdk.tlvtype.a0 y2 = aVar.y();
            jd.wjlogin_sdk.tlvtype.v t2 = aVar.t();
            String str = "";
            String a2 = y2 != null ? y2.a() : "";
            String a3 = m2 != null ? m2.a() : "";
            String a4 = r2 != null ? r2.a() : "";
            int i2 = 0;
            byte b3 = j2 != null ? j2.b() : (byte) 0;
            if (t2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(t2.a());
                    str = jSONObject.optString("qrLoginInfo", "");
                    i2 = jSONObject.optInt("qrLoginStatus", 0);
                } catch (Exception unused) {
                }
            }
            QRCodeScannedResult qRCodeScannedResult = new QRCodeScannedResult();
            qRCodeScannedResult.setType(b3);
            qRCodeScannedResult.setButtonTip(a4);
            qRCodeScannedResult.setQrCodeScannedTips(a3);
            qRCodeScannedResult.setUrl(a2);
            qRCodeScannedResult.setQrLoginInfo(str);
            qRCodeScannedResult.setQrLoginStatus(i2);
            if (onDataCallback != null) {
                if (b2 == 0) {
                    onDataCallback.onSuccessHandleInner(qRCodeScannedResult);
                    b(b2, (short) 7, (short) 3);
                    return;
                }
                jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
                FailResult failResult = new FailResult();
                a(failResult, b2, p2);
                failResult.setQrCodeScannedResult(qRCodeScannedResult);
                onDataCallback.onFailHandleInner(failResult);
                b(b2, (short) 7, (short) 3);
            }
        } catch (Exception unused2) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 7, (short) 3);
        }
    }

    public void u(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.v t2 = aVar.t();
                if (!TextUtils.isEmpty(t2.a())) {
                    JSONObject jSONObject = new JSONObject(t2.a());
                    List<WUserSigInfo> r2 = r();
                    if (!r2.isEmpty()) {
                        ArrayList<WJUserInfo> arrayList = new ArrayList<>();
                        for (int i2 = 0; i2 < r2.size(); i2++) {
                            WUserSigInfo wUserSigInfo = r2.get(i2);
                            String pin = wUserSigInfo.getPin();
                            if (!TextUtils.isEmpty(pin)) {
                                String str = new String(ByteUtil.parseHexStr2Byte(pin));
                                String optString = jSONObject.optString(str);
                                if (!TextUtils.isEmpty(optString)) {
                                    JSONObject jSONObject2 = new JSONObject(optString);
                                    String optString2 = jSONObject2.optString("nickName");
                                    String optString3 = jSONObject2.optString("mobile");
                                    String optString4 = jSONObject2.optString("imgUrl");
                                    String optString5 = jSONObject2.optString("reserve");
                                    WJUserInfo wJUserInfo = new WJUserInfo();
                                    wJUserInfo.setPin(str);
                                    wJUserInfo.setAccount(wUserSigInfo.getAccount());
                                    wJUserInfo.setNickName(optString2);
                                    wJUserInfo.setPhoneNum(optString3);
                                    wJUserInfo.setCountryCode(wUserSigInfo.getCountryCode());
                                    wJUserInfo.setCurrentMainUser(wUserSigInfo.isCurrentMainUser());
                                    wJUserInfo.setUserIconUrl(optString4);
                                    wJUserInfo.setJsonReserve(optString5);
                                    if (wUserSigInfo.isCurrentMainUser()) {
                                        arrayList.add(0, wJUserInfo);
                                    } else {
                                        arrayList.add(wJUserInfo);
                                    }
                                }
                            }
                        }
                        SuccessResult successResult = new SuccessResult();
                        successResult.setUserList(arrayList);
                        if (onDataCallback != null) {
                            onDataCallback.onSuccess(successResult);
                        }
                        b(b2, (short) 54, (short) 1);
                        return;
                    }
                    if (onDataCallback != null) {
                        onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception("\u8bfb\u53d6\u672c\u5730\u4fdd\u5b58\u7684\u767b\u5f55\u6001\u9519\u8bef")));
                    }
                    b((byte) -2, (short) 54, (short) 1);
                    return;
                }
                if (onDataCallback != null) {
                    onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception("\u8fd4\u56de\u7684\u6570\u636e\u662f\u7a7a\u7684\uff0c\u89e3\u6790\u4e0d\u51fa")));
                }
                b((byte) -2, (short) 54, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 54, (short) 1);
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 54, (short) 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0034, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void w(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 33, (short) 2);
                    a((byte) 12, (short) 33, (short) 2);
                    return;
                }
                b((byte) -2, (short) 33, (short) 2);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, (short) 2);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, (short) 2);
        }
    }

    private void a(String str, boolean z2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            if (TextUtils.isEmpty(str)) {
                if (onCommonCallback != null) {
                    onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception("pin is null")));
                    return;
                }
                return;
            }
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 3, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            if (z2) {
                jd.wjlogin_sdk.d.d.y(bVar, getA2());
                jd.wjlogin_sdk.d.d.a(bVar, getPin());
            } else {
                WUserSigInfo b2 = b(str);
                if (b2 != null && !TextUtils.isEmpty(b2.getA2())) {
                    jd.wjlogin_sdk.d.d.y(bVar, b2.getA2());
                    jd.wjlogin_sdk.d.d.a(bVar, str);
                }
            }
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new h0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19946h)).a(bVar.b()).a("CheckA2");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x002e, code lost:
        r12.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str, str2);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    a(str, b2, (short) 39, (short) 5);
                    return;
                }
                b((byte) -2, (short) 39, (short) 5);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            a(failResult, a(b3, i2));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            a(str, b2, (short) 39, (short) 5);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 39, (short) 5);
        }
    }

    private void a(String str, String str2, String str3, OnDataCallback<ReqJumpTokenResp> onDataCallback, String str4) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "reqJumpToken");
                    jSONObject.put("url", str3);
                    b(t, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 2, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            String str5 = "";
            if ("outParam".equals(str4)) {
                jd.wjlogin_sdk.d.d.a(bVar, str == null ? "" : str);
                if (str2 == null) {
                    str2 = "";
                }
                jd.wjlogin_sdk.d.d.y(bVar, str2);
            } else {
                jd.wjlogin_sdk.d.d.a(bVar, getPin() == null ? "" : getPin());
                if (getA2() != null) {
                    str5 = getA2();
                }
                jd.wjlogin_sdk.d.d.y(bVar, str5);
            }
            jd.wjlogin_sdk.d.d.c(bVar, str3);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new i0(str4, str, onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("reqJumpToken");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    private QRCodeScannedResult a(jd.wjlogin_sdk.tlvtype.p pVar, jd.wjlogin_sdk.tlvtype.m mVar) {
        QRCodeScannedResult qRCodeScannedResult = new QRCodeScannedResult();
        if (pVar != null) {
            qRCodeScannedResult.setQrCodeScannedTips(pVar.a());
        }
        if (mVar != null) {
            qRCodeScannedResult.setType(mVar.b());
        }
        return qRCodeScannedResult;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x006d, code lost:
        r14.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, String str, String str2, String str3, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (!TextUtils.isEmpty(str3) && !jd.wjlogin_sdk.util.f.d.equals(str3)) {
                    int length = str3.length();
                    if (length < 4 && length > 0) {
                        str3 = String.format("%04d", Integer.valueOf(Integer.parseInt(str3)));
                    }
                    str = str3 + str;
                }
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 4, (short) 6);
                    a((byte) 8, (short) 16, (short) 1);
                    return;
                }
                b((byte) -2, (short) 4, (short) 6);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 4, (short) 6);
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
            b((byte) -2, (short) 4, (short) 6);
        }
    }

    public void a(OnDataCallback<SuccessResult> onDataCallback) {
        if (onDataCallback != null) {
            String e2 = jd.wjlogin_sdk.util.v.e(jd.wjlogin_sdk.util.e.u);
            String str = TextUtils.isEmpty(e2) ? jd.wjlogin_sdk.util.f.a : e2;
            if (!TextUtils.equals(e2, str)) {
                jd.wjlogin_sdk.util.v.a(jd.wjlogin_sdk.util.e.u, str);
            }
            SuccessResult successResult = new SuccessResult();
            successResult.setStrVal(str);
            onDataCallback.onSuccessHandleInner(successResult);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x002e, code lost:
        r12.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str, str2);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    a(str, b2, (short) 39, (short) 6);
                    return;
                }
                b((byte) -2, (short) 39, (short) 6);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            a(failResult, a(b3, i2));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            a(str, b2, (short) 39, (short) 6);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 39, (short) 6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0033, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, String str, String str2, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.d.c cVar) {
        try {
            if (b2 == 0) {
                a(aVar, str, str2);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    a(str, b2, cVar.d(), cVar.o());
                    return;
                }
                b((byte) -2, cVar.d(), cVar.o());
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            a(failResult, a(aVar.b(), aVar.i()));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            a(str, b2, cVar.d(), cVar.o());
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, cVar.d(), cVar.o());
        }
    }

    private void a(String str, String str2, short s2, String str3, OnDataCallback onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, s2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (9 == s2) {
                jd.wjlogin_sdk.d.d.z(bVar, str2);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b0(s2, onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a(str3);
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void a(short s2, byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback onDataCallback) {
        try {
            if (b2 == 0) {
                SuccessResult successResult = new SuccessResult();
                successResult.setStrVal(new String(aVar.b().a()));
                if (onDataCallback != null) {
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 33, s2);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.c a2 = aVar.a();
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.a0 y2 = aVar.y();
            jd.wjlogin_sdk.tlvtype.f0 C = aVar.C();
            JumpResult jumpResult = null;
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (b3 != null) {
                failResult.setStrVal(new String(b3.a()));
            }
            if (C != null) {
                failResult.setIntVal(C.a());
            }
            if (a2 != null) {
                jumpResult = new JumpResult();
                if (!TextUtils.isEmpty(a2.a())) {
                    jumpResult.setToken(new String(ByteUtil.parseHexStr2Byte(a2.a())));
                }
            }
            if (y2 != null && jumpResult != null) {
                jumpResult.setUrl(y2.a());
            }
            failResult.setJumpResult(jumpResult);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 33, s2);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 33, s2);
        }
    }
}
