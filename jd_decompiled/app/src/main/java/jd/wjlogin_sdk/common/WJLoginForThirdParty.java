package jd.wjlogin_sdk.common;

import android.text.TextUtils;
import android.util.Pair;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import com.unionpay.tsmservice.data.Constant;
import jd.wjlogin_sdk.common.company.WJLoginForCompany;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.HuaweiTokenInfo;
import jd.wjlogin_sdk.model.JumpResult;
import jd.wjlogin_sdk.model.OneKeyTokenInfo;
import jd.wjlogin_sdk.model.ReqQRCodeResp;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.model.ZteAngelCareTokenInfo;
import jd.wjlogin_sdk.tlvtype.r0;
import jd.wjlogin_sdk.util.ByteUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WJLoginForThirdParty extends WJLoginForCompany {
    private static final String v = "WJLogin.WJLoginForThirdParty";

    /* loaded from: classes.dex */
    class a implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19733c;

        a(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19733c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.c(this.a, this.b, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.f19733c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19733c;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    class b implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        b(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.Q(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 39, (short) 8);
        }
    }

    /* loaded from: classes.dex */
    class b0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        b0(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.F(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class c implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        c(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.H(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 39, (short) 9);
        }
    }

    /* loaded from: classes.dex */
    class d implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        d(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.K(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 23);
        }
    }

    /* loaded from: classes.dex */
    class e implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19734c;

        e(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19734c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.d(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19734c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19734c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 24);
        }
    }

    /* loaded from: classes.dex */
    class f implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19735c;

        f(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19735c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.c(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b, this.f19735c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19735c;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 26);
        }
    }

    /* loaded from: classes.dex */
    class g implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        g(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 25);
        }
    }

    /* loaded from: classes.dex */
    class h implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        h(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.M(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 33, (short) 14);
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
            WJLoginForThirdParty.this.J(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 53, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class j implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        j(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.O(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 53, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    class k implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        k(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.H(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<ReqQRCodeResp>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 7, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class l implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        l(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.I(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 53, (short) 7);
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
            WJLoginForThirdParty.this.I(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 27);
        }
    }

    /* loaded from: classes.dex */
    class n implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        n(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.P(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 5, (short) 28);
        }
    }

    /* loaded from: classes.dex */
    class o implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        o(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.J(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class p implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19737c;

        p(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19737c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.a(this.a, this.b, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.f19737c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19737c;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class q implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        q(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.K(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 9);
        }
    }

    /* loaded from: classes.dex */
    class r implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        r(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.C(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 8);
        }
    }

    /* loaded from: classes.dex */
    class s implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19738c;

        s(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19738c = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.b(this.a, this.b, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.f19738c);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.f19738c;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 5);
        }
    }

    /* loaded from: classes.dex */
    class t implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        t(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.E(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 44, (short) 10);
        }
    }

    /* loaded from: classes.dex */
    class u implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        u(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.H(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<ReqQRCodeResp>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 7, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class v implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        v(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.R(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 7, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    class w implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        w(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.D(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 29, (short) 1);
        }
    }

    /* loaded from: classes.dex */
    class x implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        x(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.c(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 29, (short) 2);
        }
    }

    /* loaded from: classes.dex */
    class y implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        y(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.N(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onError(errorResult);
            }
            WJLoginForThirdParty.this.b((byte) -1, (short) 2, (short) 25);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.v t2 = aVar.t();
                SuccessResult successResult = new SuccessResult();
                if (t2 != null) {
                    successResult.setStrVal(t2.a());
                }
                if (onDataCallback != null) {
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 44, (short) 8);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 44, (short) 8);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 44, (short) 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 29, (short) 1);
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
            b(b2, (short) 29, (short) 29);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 29, (short) 29);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 44, (short) 10);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            jd.wjlogin_sdk.tlvtype.c a3 = aVar.a();
            FailResult failResult = new FailResult();
            JumpResult jumpResult = new JumpResult();
            a(failResult, b2, p2);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (a3 != null && !TextUtils.isEmpty(a3.a())) {
                jumpResult.setToken(new String(ByteUtil.parseHexStr2Byte(a3.a())));
            }
            failResult.setJumpResult(jumpResult);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 44, (short) 10);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 44, (short) 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 44, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.i g2 = aVar.g();
            jd.wjlogin_sdk.tlvtype.c a3 = aVar.a();
            FailResult failResult = new FailResult();
            JumpResult jumpResult = new JumpResult();
            a(failResult, b2, p2);
            if (g2 != null) {
                failResult.setIntVal(g2.a());
            }
            if (a3 != null && !TextUtils.isEmpty(a3.a())) {
                jumpResult.setToken(new String(ByteUtil.parseHexStr2Byte(a3.a())));
            }
            failResult.setJumpResult(jumpResult);
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 44, (short) 1);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 44, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void G(byte r5, jd.wjlogin_sdk.tlvtype.a r6, jd.wjlogin_sdk.common.listener.OnDataCallback<jd.wjlogin_sdk.model.SuccessResult> r7) {
        /*
            r4 = this;
            java.lang.String r0 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            r1 = -102(0xffffffffffffff9a, float:NaN)
            if (r5 != 0) goto L50
            r5 = 0
            r4.a(r6, r5)     // Catch: java.lang.Exception -> L71
            java.lang.String r5 = r4.getA2()     // Catch: java.lang.Exception -> L71
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L71
            if (r5 != 0) goto L38
            java.lang.String r5 = r4.getPin()     // Catch: java.lang.Exception -> L71
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L71
            if (r5 == 0) goto L20
            goto L38
        L20:
            jd.wjlogin_sdk.tlvtype.v r5 = r6.t()     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.model.SuccessResult r6 = new jd.wjlogin_sdk.model.SuccessResult     // Catch: java.lang.Exception -> L71
            r6.<init>()     // Catch: java.lang.Exception -> L71
            if (r5 == 0) goto L32
            java.lang.String r5 = r5.a()     // Catch: java.lang.Exception -> L71
            r6.setStrVal(r5)     // Catch: java.lang.Exception -> L71
        L32:
            if (r7 == 0) goto L37
            r7.onSuccessHandleInner(r6)     // Catch: java.lang.Exception -> L71
        L37:
            return
        L38:
            if (r7 == 0) goto L48
            java.lang.Exception r5 = new java.lang.Exception     // Catch: java.lang.Exception -> L71
            java.lang.String r6 = "a2 or pin is null"
            r5.<init>(r6)     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r1, r0, r5)     // Catch: java.lang.Exception -> L71
            r7.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L71
        L48:
            r5 = -2
            r6 = 5
            r2 = 19
            r4.b(r5, r6, r2)     // Catch: java.lang.Exception -> L71
            return
        L50:
            jd.wjlogin_sdk.tlvtype.x r2 = r6.p()     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L71
            r3.<init>()     // Catch: java.lang.Exception -> L71
            r4.a(r3, r5, r2)     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.tlvtype.d r5 = r6.b()     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.tlvtype.l r6 = r6.i()     // Catch: java.lang.Exception -> L71
            jd.wjlogin_sdk.model.JumpResult r5 = r4.a(r5, r6)     // Catch: java.lang.Exception -> L71
            r3.setJumpResult(r5)     // Catch: java.lang.Exception -> L71
            if (r7 == 0) goto L7b
            r7.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L71
            goto L7b
        L71:
            r5 = move-exception
            if (r7 == 0) goto L7b
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r1, r0, r5)
            r7.onErrorHandleInner(r5)
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.G(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnDataCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<ReqQRCodeResp> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    jd.wjlogin_sdk.tlvtype.o l2 = aVar.l();
                    String a2 = l2 != null ? l2.a() : "";
                    byte[] a3 = aVar.k() != null ? aVar.k().a() : null;
                    ReqQRCodeResp reqQRCodeResp = new ReqQRCodeResp();
                    reqQRCodeResp.setQrCodeKey(a2);
                    reqQRCodeResp.setQrCodeData(a3);
                    onDataCallback.onSuccessHandleInner(reqQRCodeResp);
                }
                b(b2, (short) 7, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            onDataCallback.onFailHandleInner(failResult);
            b(b2, (short) 7, (short) 1);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 7, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    SuccessResult successResult = new SuccessResult();
                    r0 O = aVar.O();
                    jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
                    jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
                    JSONObject jSONObject = new JSONObject();
                    if (O != null) {
                        jSONObject.put("yhdPin", O.a());
                    }
                    if (b3 != null) {
                        jSONObject.put("token", new String(b3.a()));
                    }
                    if (i2 != null) {
                        jSONObject.put("url", i2.a());
                    }
                    successResult.setStrVal(jSONObject.toString());
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 53, (short) 7);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 53, (short) 7);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 53, (short) 7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    SuccessResult successResult = new SuccessResult();
                    r0 O = aVar.O();
                    if (O != null) {
                        successResult.setStrVal(O.a());
                    }
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 53, (short) 1);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            failResult.setJumpResult(a(aVar.b(), aVar.i()));
            r0 O2 = aVar.O();
            if (O2 != null) {
                failResult.setStrVal(O2.a());
            }
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 53, (short) 1);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 53, (short) 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    int a2 = aVar.g().a();
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 5, (short) 23);
                jd.wjlogin_sdk.util.p.b("sendMsgCode4UnionBind code" + ((int) b2) + "******sendMsgCode4UnionBind time" + System.currentTimeMillis());
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
            b(b2, (short) 5, (short) 23);
            jd.wjlogin_sdk.util.p.a("sendMsgCode4UnionBind code" + ((int) b2) + "******sendMsgCode4UnionBind time" + System.currentTimeMillis() + "******sendMsgCode4UnionBind msg" + failResult.getMessage());
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 5, (short) 23);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void L(byte r5, jd.wjlogin_sdk.tlvtype.a r6, jd.wjlogin_sdk.common.listener.OnCommonCallback r7) {
        /*
            r4 = this;
            java.lang.String r0 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            r1 = -102(0xffffffffffffff9a, float:NaN)
            if (r5 != 0) goto L3f
            r5 = 0
            r4.a(r6, r5)     // Catch: java.lang.Exception -> L60
            java.lang.String r5 = r4.getA2()     // Catch: java.lang.Exception -> L60
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L60
            if (r5 != 0) goto L26
            java.lang.String r5 = r4.getPin()     // Catch: java.lang.Exception -> L60
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L60
            if (r5 == 0) goto L20
            goto L26
        L20:
            if (r7 == 0) goto L25
            r7.onSuccessHandleInner()     // Catch: java.lang.Exception -> L60
        L25:
            return
        L26:
            if (r7 == 0) goto L36
            java.lang.Exception r5 = new java.lang.Exception     // Catch: java.lang.Exception -> L60
            java.lang.String r6 = "a2 or pin is null"
            r5.<init>(r6)     // Catch: java.lang.Exception -> L60
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r1, r0, r5)     // Catch: java.lang.Exception -> L60
            r7.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L60
        L36:
            r5 = -2
            r6 = 35
            r2 = 18
            r4.b(r5, r6, r2)     // Catch: java.lang.Exception -> L60
            return
        L3f:
            jd.wjlogin_sdk.tlvtype.x r2 = r6.p()     // Catch: java.lang.Exception -> L60
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L60
            r3.<init>()     // Catch: java.lang.Exception -> L60
            r4.a(r3, r5, r2)     // Catch: java.lang.Exception -> L60
            jd.wjlogin_sdk.tlvtype.d r5 = r6.b()     // Catch: java.lang.Exception -> L60
            jd.wjlogin_sdk.tlvtype.l r6 = r6.i()     // Catch: java.lang.Exception -> L60
            jd.wjlogin_sdk.model.JumpResult r5 = r4.a(r5, r6)     // Catch: java.lang.Exception -> L60
            r3.setJumpResult(r5)     // Catch: java.lang.Exception -> L60
            if (r7 == 0) goto L6a
            r7.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L60
            goto L6a
        L60:
            r5 = move-exception
            if (r7 == 0) goto L6a
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r1, r0, r5)
            r7.onErrorHandleInner(r5)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.L(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -2
            r1 = 14
            r2 = 33
            if (r6 != 0) goto L42
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L66
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L66
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L66
            if (r7 != 0) goto L29
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L66
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L66
            if (r7 == 0) goto L20
            goto L29
        L20:
            if (r8 == 0) goto L25
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L66
        L25:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L66
            return
        L29:
            if (r8 == 0) goto L3e
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L66
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L66
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L66
        L3e:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L66
            return
        L42:
            jd.wjlogin_sdk.tlvtype.x r3 = r7.p()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.FailResult r4 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L66
            r4.<init>()     // Catch: java.lang.Exception -> L66
            r5.a(r4, r6, r3)     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.tlvtype.d r3 = r7.b()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.tlvtype.l r7 = r7.i()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.JumpResult r7 = r5.a(r3, r7)     // Catch: java.lang.Exception -> L66
            r4.setJumpResult(r7)     // Catch: java.lang.Exception -> L66
            if (r8 == 0) goto L62
            r8.onFailHandleInner(r4)     // Catch: java.lang.Exception -> L66
        L62:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L66
            goto L73
        L66:
            if (r8 == 0) goto L70
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L70:
            r5.b(r0, r2, r1)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.M(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void N(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -2
            r1 = 25
            r2 = 2
            if (r6 != 0) goto L41
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L65
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L65
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L65
            if (r7 != 0) goto L28
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L65
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L65
            if (r7 == 0) goto L1f
            goto L28
        L1f:
            if (r8 == 0) goto L24
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L65
        L24:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L65
            return
        L28:
            if (r8 == 0) goto L3d
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L65
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L65
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L65
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L65
        L3d:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L65
            return
        L41:
            jd.wjlogin_sdk.tlvtype.x r3 = r7.p()     // Catch: java.lang.Exception -> L65
            jd.wjlogin_sdk.model.FailResult r4 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L65
            r4.<init>()     // Catch: java.lang.Exception -> L65
            r5.a(r4, r6, r3)     // Catch: java.lang.Exception -> L65
            jd.wjlogin_sdk.tlvtype.d r3 = r7.b()     // Catch: java.lang.Exception -> L65
            jd.wjlogin_sdk.tlvtype.l r7 = r7.i()     // Catch: java.lang.Exception -> L65
            jd.wjlogin_sdk.model.JumpResult r7 = r5.a(r3, r7)     // Catch: java.lang.Exception -> L65
            r4.setJumpResult(r7)     // Catch: java.lang.Exception -> L65
            if (r8 == 0) goto L61
            r8.onFailHandleInner(r4)     // Catch: java.lang.Exception -> L65
        L61:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L65
            goto L72
        L65:
            if (r8 == 0) goto L6f
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L6f:
            r5.b(r0, r2, r1)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.N(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (TextUtils.isEmpty(getA2())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(jd.wjlogin_sdk.util.y.a)));
                    }
                    b((byte) -2, (short) 53, (short) 5);
                    return;
                }
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 53, (short) 5);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 53, (short) 5);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 53, (short) 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void P(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r1 = -2
            r2 = 28
            r3 = 5
            if (r6 != 0) goto L42
            r5.a(r7, r0, r0)     // Catch: java.lang.Exception -> L57
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L57
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L57
            if (r7 != 0) goto L29
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L57
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L57
            if (r7 == 0) goto L20
            goto L29
        L20:
            if (r8 == 0) goto L25
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L57
        L25:
            r5.b(r6, r3, r2)     // Catch: java.lang.Exception -> L57
            return
        L29:
            if (r8 == 0) goto L3e
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r0 = new java.lang.Exception     // Catch: java.lang.Exception -> L57
            java.lang.String r4 = "a2 or pin is null"
            r0.<init>(r4)     // Catch: java.lang.Exception -> L57
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r0)     // Catch: java.lang.Exception -> L57
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L57
        L3e:
            r5.b(r1, r3, r2)     // Catch: java.lang.Exception -> L57
            return
        L42:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L57
            jd.wjlogin_sdk.model.FailResult r0 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L57
            r0.<init>()     // Catch: java.lang.Exception -> L57
            r5.a(r0, r6, r7)     // Catch: java.lang.Exception -> L57
            if (r8 == 0) goto L53
            r8.onFailHandleInner(r0)     // Catch: java.lang.Exception -> L57
        L53:
            r5.b(r6, r3, r2)     // Catch: java.lang.Exception -> L57
            goto L64
        L57:
            if (r8 == 0) goto L61
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L61:
            r5.b(r1, r3, r2)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.P(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Q(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -1
            r1 = 8
            r2 = 39
            if (r6 != 0) goto L42
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L55
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L55
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L55
            if (r7 != 0) goto L29
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L55
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L55
            if (r7 == 0) goto L20
            goto L29
        L20:
            if (r8 == 0) goto L25
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L55
        L25:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L55
            return
        L29:
            if (r8 == 0) goto L3e
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L55
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L55
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L55
        L3e:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L55
            return
        L42:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L55
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L55
            r3.<init>()     // Catch: java.lang.Exception -> L55
            r5.a(r3, r6, r7)     // Catch: java.lang.Exception -> L55
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L55
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L55
            goto L65
        L55:
            r6 = move-exception
            r6.printStackTrace()
            if (r8 == 0) goto L62
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L62:
            r5.b(r0, r2, r1)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.Q(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void R(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -2
            r1 = 2
            r2 = 7
            if (r6 != 0) goto L40
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L53
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L53
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L53
            if (r7 != 0) goto L27
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L53
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L53
            if (r7 == 0) goto L1e
            goto L27
        L1e:
            if (r8 == 0) goto L23
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L53
        L23:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L53
            return
        L27:
            if (r8 == 0) goto L3c
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L53
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L53
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L53
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L53
        L3c:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L53
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L53
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L53
            r3.<init>()     // Catch: java.lang.Exception -> L53
            r5.a(r3, r6, r7)     // Catch: java.lang.Exception -> L53
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L53
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L53
            goto L60
        L53:
            if (r8 == 0) goto L5d
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L5d:
            r5.b(r0, r2, r1)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.R(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    public void JDPurseToken2Pin(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "JDPurseToken2Pin");
                    jSONObject.put("token", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 2, (short) 25, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new y(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("JDPurseToken2Pin");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void JdMeParseToken2Pin(String str, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "JdMeParseToken2Pin");
                jSONObject.put("token", str);
                b(v, jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new c(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("JdMeParseToken2Pin");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void accountLoginAndUnionBind(String str, String str2, String str3, String str4, String str5, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "accountLoginAndUnionBind");
                    jSONObject.put("account", str2);
                    jSONObject.put(DeepLink3DProductHelper.EXTRA_PASSWORD, str3);
                    jSONObject.put("sid", str4);
                    jSONObject.put("token", str5);
                    jSONObject.put("bindToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 25, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str2, str3);
            jd.wjlogin_sdk.d.d.a(bVar, str4, str5);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new g(str2, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("accountLoginAndUnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void bindedUnionAccountLogin(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "bindedUnionAccountLogin");
                    jSONObject.put("loginToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 27, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new m(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkMsgCode4UnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void checkHistory4UnionBind(String str, String str2, String str3, String str4, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkHistory4UnionBind");
                    jSONObject.put("phoneNum", str2);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
                    jSONObject.put("historyPerson", str4);
                    jSONObject.put("bindToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 26, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str2);
            jd.wjlogin_sdk.d.d.p(bVar, str3);
            jd.wjlogin_sdk.d.d.o(bVar, str4);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new f(str2, str3, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkHistory4UnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void checkMsgCode4UnionBind(String str, String str2, String str3, String str4, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "checkMsgCode4UnionBind");
                    jSONObject.put("phoneNum", str2);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
                    jSONObject.put("bindToken", str);
                    jSONObject.put(JshopConst.JSHOP_TIP_MESSAGE_CODE, str4);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 24, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str2);
            jd.wjlogin_sdk.d.d.f(bVar, str4);
            if (TextUtils.isEmpty(str3)) {
                jd.wjlogin_sdk.d.d.p(bVar, jd.wjlogin_sdk.util.f.d);
            } else {
                jd.wjlogin_sdk.d.d.p(bVar, str3);
            }
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new e(str2, str3, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("checkMsgCode4UnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void checkMsgForCompanyRegister(String str, String str2, String str3, String str4, JSONObject jSONObject, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("callMethod", "checkMsgForCompanyRegister");
                jSONObject2.put("phoneNumber", str);
                jSONObject2.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject2.put("messageCode", str3);
                jSONObject2.put("passWord", "xxxxxx");
                if (jSONObject != null) {
                    jSONObject2.put("otherParams", jSONObject);
                }
                b(v, jSONObject2);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.f(bVar, str3);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, TextUtils.isEmpty(str2) ? jd.wjlogin_sdk.util.f.d : str2);
            jd.wjlogin_sdk.d.d.j(bVar, str4);
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new p(str, str2, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("checkMsgForCompanyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void checkPhoneNumForCompanyRegister(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "checkPhoneNumForCompanyRegister");
                jSONObject.put("phoneNumber", str);
                jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject.put("sid", str3);
                jSONObject.put("token", str4);
                b(v, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 8, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            if (TextUtils.isEmpty(str2)) {
                str2 = jd.wjlogin_sdk.util.f.d;
            }
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.a(bVar, str3, str4);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new r(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkPhoneNumForCompanyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void checkUserNameForCompanyRegister(String str, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "checkUserNameForCompanyRegister");
                jSONObject.put("userName", str);
                b(v, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new o(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("checkUserNameForCompanyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void companyRegister(String str, String str2, int i2, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "companyRegister");
                jSONObject.put("phoneNumber", str);
                jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject.put("isMine", i2);
                b(v, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 9, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            if (TextUtils.isEmpty(str2)) {
                str2 = jd.wjlogin_sdk.util.f.d;
            }
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.c(bVar, i2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new q(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("companyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getMessageCodeForCompanyRegister(String str, String str2, int i2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "getMessageCodeForCompanyRegister");
                jSONObject.put("phoneNumber", str);
                jSONObject.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject.put("isMine", i2);
                b(v, jSONObject);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 10, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            if (TextUtils.isEmpty(str2)) {
                str2 = jd.wjlogin_sdk.util.f.d;
            }
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.c(bVar, i2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new t(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getMessageCodeForCompanyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getMessageCodeForEnterprise(String str, String str2, String str3, String str4, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, str3, str4);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b0(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getMessageCodeForEnterprise");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getTokenForCompanyRegister(String str, String str2, String str3, JSONObject jSONObject, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("callMethod", "getTokenForCompanyRegister");
                jSONObject2.put("phoneNumber", str);
                jSONObject2.put(Constant.KEY_COUNTRY_CODE, str2);
                jSONObject2.put(DeepLink3DProductHelper.EXTRA_PASSWORD, "xxxxx");
                if (jSONObject != null) {
                    jSONObject2.put("otherParams", jSONObject);
                }
                b(v, jSONObject2);
            }
        } catch (Exception unused) {
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.p(bVar, TextUtils.isEmpty(str2) ? jd.wjlogin_sdk.util.f.d : str2);
            jd.wjlogin_sdk.d.d.j(bVar, str3);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new s(str, str2, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("getTokenForCompanyRegister");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void huaweiAccountLogin(HuaweiTokenInfo huaweiTokenInfo, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 35, (short) 18, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.a(bVar, huaweiTokenInfo);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            String appId = huaweiTokenInfo.getAppId();
            if (appId == null) {
                appId = "";
            }
            jd.wjlogin_sdk.d.d.x(bVar, appId);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a0(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("huaweiAccountLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void loginForSuperExperienceShop(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 39, (short) 8, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.m(bVar, str);
            jd.wjlogin_sdk.d.d.n(bVar, str2);
            jd.wjlogin_sdk.d.d.l(bVar, o());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("loginForSuperExperienceShop");
            gVar.b();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 39, (short) 8);
        }
    }

    public void medicineRegist(String str, String str2, String str3, String str4, String str5, boolean z2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 29, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, str3);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.f(bVar, str2);
            jd.wjlogin_sdk.d.d.j(bVar, str4);
            jd.wjlogin_sdk.d.d.v(bVar, str5);
            jd.wjlogin_sdk.d.d.b(bVar, z2 ? (short) 1 : (short) 0);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new x(str, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("medicineRegist");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void oneKeyLoginAndUnionBind(String str, OneKeyTokenInfo oneKeyTokenInfo, OnCommonCallback onCommonCallback) {
        try {
            if (jd.wjlogin_sdk.util.p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", "oneKeyLoginAndUnionBind");
                jSONObject.put("bindToken", str);
                if (oneKeyTokenInfo != null) {
                    jSONObject.put("accessCode", oneKeyTokenInfo.getAccessToken());
                    jSONObject.put("operateType", (int) oneKeyTokenInfo.getOperateType());
                }
                b(v, jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 33, (short) 14, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, oneKeyTokenInfo);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new h(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("oneKeyLoginAndUnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void reFreshYHDA2(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "reFreshYHDA2");
                    jSONObject.put("token", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 53, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new j(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("reFreshYHDA2");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void refreshQRCodePicture(String str, int i2, byte b2, OnDataCallback<ReqQRCodeResp> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.a(bVar, b2, i2);
            jd.wjlogin_sdk.d.d.i(bVar, str);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new u(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("refreshQRCodePicture");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void registForEnterprise(String str, String str2, String str3, String str4, String str5, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 44, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.f(bVar, str3);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            if (str4 == null) {
                str4 = "";
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MobileCertConstants.USERNAME, str4);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            jd.wjlogin_sdk.d.d.a(bVar, jSONObject);
            jd.wjlogin_sdk.d.d.j(bVar, str5);
            jd.wjlogin_sdk.d.d.p(bVar, str2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(str, str2, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("registForEnterprise");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void registUnionBind(String str, OnCommonCallback onCommonCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "registUnionBind");
                    jSONObject.put("bindToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 28, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new n(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(true).a(bVar.b()).a("registUnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void reqQRCodePicture(int i2, byte b2, OnDataCallback<ReqQRCodeResp> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.a(bVar, b2, i2);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new k(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("reqQRCodePicture");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void reqYHDFirstBindToken(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "reqYHDFirstBindToken");
                    jSONObject.put("yhdToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 53, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.B(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new l(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("reqYHDFirstBindToken");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void reqYHDSecondBindoken(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "reqYHDSecondBindoken");
                    jSONObject.put("yhdToken", str);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 53, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            jd.wjlogin_sdk.d.d.B(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new i(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("reqYHDSecondBindoken");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void sendMsgCode4UnionBind(String str, String str2, String str3, String str4, String str5, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            try {
                if (jd.wjlogin_sdk.util.p.b) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callMethod", "sendMsgCode4UnionBind");
                    jSONObject.put("bindToken", str);
                    jSONObject.put("phoneNum", str2);
                    jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
                    jSONObject.put("sid", str4);
                    jSONObject.put("token", str5);
                    b(v, jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 23, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.b(bVar, str);
            jd.wjlogin_sdk.d.d.e(bVar, str2);
            if (TextUtils.isEmpty(str3)) {
                jd.wjlogin_sdk.d.d.p(bVar, jd.wjlogin_sdk.util.f.d);
            } else {
                jd.wjlogin_sdk.d.d.p(bVar, str3);
            }
            jd.wjlogin_sdk.d.d.a(bVar, str4, str5);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new d(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("sendMsgCode4UnionBind");
            gVar.b();
        } catch (Exception e3) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3));
            }
        }
    }

    public void sendMsgForMedicine(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 29, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.e(bVar, str);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new w(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("sendMsgForMedicine");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void verifyQRCode(String str, byte b2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 7, (short) 2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), jd.wjlogin_sdk.util.r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.i(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, b2, 0);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new v(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("verifyQRCode");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void zteAngelCarePhoneAccountLogin(ZteAngelCareTokenInfo zteAngelCareTokenInfo, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 5, (short) 19, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.a(bVar, zteAngelCareTokenInfo);
            jd.wjlogin_sdk.d.d.u(bVar, q());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new z(onDataCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("zteAngelCarePhoneAccountLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* loaded from: classes.dex */
    class a0 implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        a0(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.L(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
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
    class z implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;

        z(OnDataCallback onDataCallback) {
            this.a = onDataCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginForThirdParty.this.G(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r12.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(byte r8, jd.wjlogin_sdk.tlvtype.a r9, java.lang.String r10, java.lang.String r11, jd.wjlogin_sdk.common.listener.OnCommonCallback r12) {
        /*
            r7 = this;
            r0 = -2
            r1 = 24
            r2 = 5
            if (r8 != 0) goto L40
            r7.a(r9, r10, r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r9 = r7.getA2()     // Catch: java.lang.Exception -> L7e
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L7e
            if (r9 != 0) goto L27
            java.lang.String r9 = r7.getPin()     // Catch: java.lang.Exception -> L7e
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L7e
            if (r9 == 0) goto L1e
            goto L27
        L1e:
            if (r12 == 0) goto L23
            r12.onSuccessHandleInner()     // Catch: java.lang.Exception -> L7e
        L23:
            r7.a(r10, r8, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L27:
            if (r12 == 0) goto L3c
            r8 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r9 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Exception -> L7e
            java.lang.String r11 = "a2 or pin is null"
            r10.<init>(r11)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.ErrorResult r8 = jd.wjlogin_sdk.util.b0.a(r8, r9, r10)     // Catch: java.lang.Exception -> L7e
            r12.onErrorHandleInner(r8)     // Catch: java.lang.Exception -> L7e
        L3c:
            r7.b(r0, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r11 = r9.p()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.e r3 = r9.c()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.d r4 = r9.b()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.l r5 = r9.i()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.FailResult r6 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L7e
            r6.<init>()     // Catch: java.lang.Exception -> L7e
            r7.a(r6, r8, r11)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.i r9 = r9.g()     // Catch: java.lang.Exception -> L7e
            if (r3 == 0) goto L65
            int r11 = r3.a()     // Catch: java.lang.Exception -> L7e
            r6.setIntVal(r11)     // Catch: java.lang.Exception -> L7e
        L65:
            if (r9 == 0) goto L6e
            int r9 = r9.a()     // Catch: java.lang.Exception -> L7e
            r6.setIntVal(r9)     // Catch: java.lang.Exception -> L7e
        L6e:
            jd.wjlogin_sdk.model.JumpResult r9 = r7.a(r4, r5)     // Catch: java.lang.Exception -> L7e
            r7.a(r6, r9)     // Catch: java.lang.Exception -> L7e
            if (r12 == 0) goto L7a
            r12.onFailHandleInner(r6)     // Catch: java.lang.Exception -> L7e
        L7a:
            r7.a(r10, r8, r2, r1)     // Catch: java.lang.Exception -> L7e
            goto L8b
        L7e:
            if (r12 == 0) goto L88
            jd.wjlogin_sdk.model.FailResult r8 = r7.a()
            r12.onFailHandleInner(r8)
        L88:
            r7.b(r0, r2, r1)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.d(byte, jd.wjlogin_sdk.tlvtype.a, java.lang.String, java.lang.String, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.lang.String r5, byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r4 = this;
            r0 = -2
            r1 = 2
            r2 = 29
            if (r6 != 0) goto L40
            r4.a(r7, r5)     // Catch: java.lang.Exception -> L53
            java.lang.String r7 = r4.getA2()     // Catch: java.lang.Exception -> L53
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L53
            if (r7 != 0) goto L27
            java.lang.String r7 = r4.getPin()     // Catch: java.lang.Exception -> L53
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L53
            if (r7 == 0) goto L1e
            goto L27
        L1e:
            if (r8 == 0) goto L23
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L53
        L23:
            r4.a(r5, r6, r2, r1)     // Catch: java.lang.Exception -> L53
            return
        L27:
            if (r8 == 0) goto L3c
            r5 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r6 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L53
            java.lang.String r3 = "a2 or pin is null"
            r7.<init>(r3)     // Catch: java.lang.Exception -> L53
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r5, r6, r7)     // Catch: java.lang.Exception -> L53
            r8.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L53
        L3c:
            r4.b(r0, r2, r1)     // Catch: java.lang.Exception -> L53
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L53
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L53
            r3.<init>()     // Catch: java.lang.Exception -> L53
            r4.a(r3, r6, r7)     // Catch: java.lang.Exception -> L53
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L53
            r4.a(r5, r6, r2, r1)     // Catch: java.lang.Exception -> L53
            goto L60
        L53:
            if (r8 == 0) goto L5d
            jd.wjlogin_sdk.model.FailResult r5 = r4.a()
            r8.onFailHandleInner(r5)
        L5d:
            r4.b(r0, r2, r1)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.c(java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r12.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(byte r9, jd.wjlogin_sdk.tlvtype.a r10, java.lang.String r11, jd.wjlogin_sdk.common.listener.OnCommonCallback r12) {
        /*
            r8 = this;
            r0 = -2
            r1 = 25
            r2 = 5
            if (r9 != 0) goto L40
            r8.a(r10, r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r10 = r8.getA2()     // Catch: java.lang.Exception -> L7e
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L7e
            if (r10 != 0) goto L27
            java.lang.String r10 = r8.getPin()     // Catch: java.lang.Exception -> L7e
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L7e
            if (r10 == 0) goto L1e
            goto L27
        L1e:
            if (r12 == 0) goto L23
            r12.onSuccessHandleInner()     // Catch: java.lang.Exception -> L7e
        L23:
            r8.a(r11, r9, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L27:
            if (r12 == 0) goto L3c
            r9 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r10 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r11 = new java.lang.Exception     // Catch: java.lang.Exception -> L7e
            java.lang.String r3 = "a2 or pin is null"
            r11.<init>(r3)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.ErrorResult r9 = jd.wjlogin_sdk.util.b0.a(r9, r10, r11)     // Catch: java.lang.Exception -> L7e
            r12.onErrorHandleInner(r9)     // Catch: java.lang.Exception -> L7e
        L3c:
            r8.b(r0, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r3 = r10.p()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.e r4 = r10.c()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.d r5 = r10.b()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.l r6 = r10.i()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.FailResult r7 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L7e
            r7.<init>()     // Catch: java.lang.Exception -> L7e
            r8.a(r7, r9, r3)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.i r10 = r10.g()     // Catch: java.lang.Exception -> L7e
            if (r4 == 0) goto L65
            int r3 = r4.a()     // Catch: java.lang.Exception -> L7e
            r7.setIntVal(r3)     // Catch: java.lang.Exception -> L7e
        L65:
            if (r10 == 0) goto L6e
            int r10 = r10.a()     // Catch: java.lang.Exception -> L7e
            r7.setIntVal(r10)     // Catch: java.lang.Exception -> L7e
        L6e:
            jd.wjlogin_sdk.model.JumpResult r10 = r8.a(r5, r6)     // Catch: java.lang.Exception -> L7e
            r8.a(r7, r10)     // Catch: java.lang.Exception -> L7e
            if (r12 == 0) goto L7a
            r12.onFailHandleInner(r7)     // Catch: java.lang.Exception -> L7e
        L7a:
            r8.a(r11, r9, r2, r1)     // Catch: java.lang.Exception -> L7e
            goto L8b
        L7e:
            if (r12 == 0) goto L88
            jd.wjlogin_sdk.model.FailResult r9 = r8.a()
            r12.onFailHandleInner(r9)
        L88:
            r8.b(r0, r2, r1)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.b(byte, jd.wjlogin_sdk.tlvtype.a, java.lang.String, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r5, java.lang.String r6, byte r7, jd.wjlogin_sdk.tlvtype.a r8, jd.wjlogin_sdk.common.listener.OnCommonCallback r9) {
        /*
            r4 = this;
            r0 = -2
            r1 = 4
            r2 = 44
            if (r7 != 0) goto L40
            r4.a(r8, r5, r6)     // Catch: java.lang.Exception -> L64
            java.lang.String r5 = r4.getA2()     // Catch: java.lang.Exception -> L64
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L64
            if (r5 != 0) goto L27
            java.lang.String r5 = r4.getPin()     // Catch: java.lang.Exception -> L64
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L64
            if (r5 == 0) goto L1e
            goto L27
        L1e:
            if (r9 == 0) goto L23
            r9.onSuccessHandleInner()     // Catch: java.lang.Exception -> L64
        L23:
            r4.b(r7, r2, r1)     // Catch: java.lang.Exception -> L64
            return
        L27:
            if (r9 == 0) goto L3c
            r5 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r6 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L64
            java.lang.String r8 = "a2 or pin is null"
            r7.<init>(r8)     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r5, r6, r7)     // Catch: java.lang.Exception -> L64
            r9.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L64
        L3c:
            r4.b(r0, r2, r1)     // Catch: java.lang.Exception -> L64
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r5 = r8.p()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.FailResult r6 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L64
            r6.<init>()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.tlvtype.d r3 = r8.b()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.tlvtype.l r8 = r8.i()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.JumpResult r8 = r4.a(r3, r8)     // Catch: java.lang.Exception -> L64
            r6.setJumpResult(r8)     // Catch: java.lang.Exception -> L64
            r4.a(r6, r7, r5)     // Catch: java.lang.Exception -> L64
            if (r9 == 0) goto L60
            r9.onFailHandleInner(r6)     // Catch: java.lang.Exception -> L64
        L60:
            r4.b(r7, r2, r1)     // Catch: java.lang.Exception -> L64
            goto L71
        L64:
            if (r9 == 0) goto L6e
            jd.wjlogin_sdk.model.FailResult r5 = r4.a()
            r9.onFailHandleInner(r5)
        L6e:
            r4.b(r0, r2, r1)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.a(java.lang.String, java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006d, code lost:
        r11.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.lang.String r7, java.lang.String r8, byte r9, jd.wjlogin_sdk.tlvtype.a r10, jd.wjlogin_sdk.common.listener.OnCommonCallback r11) {
        /*
            r6 = this;
            r0 = -2
            r1 = 2
            r2 = 44
            if (r9 != 0) goto L7f
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L94
            if (r3 != 0) goto L40
            java.lang.String r3 = "86"
            boolean r3 = r3.equals(r7)     // Catch: java.lang.Exception -> L94
            if (r3 != 0) goto L40
            int r3 = r7.length()     // Catch: java.lang.Exception -> L94
            r4 = 4
            if (r3 >= r4) goto L31
            if (r3 <= 0) goto L31
            java.lang.String r3 = "%04d"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L94
            r5 = 0
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> L94
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L94
            r4[r5] = r7     // Catch: java.lang.Exception -> L94
            java.lang.String r7 = java.lang.String.format(r3, r4)     // Catch: java.lang.Exception -> L94
        L31:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L94
            r3.<init>()     // Catch: java.lang.Exception -> L94
            r3.append(r7)     // Catch: java.lang.Exception -> L94
            r3.append(r8)     // Catch: java.lang.Exception -> L94
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Exception -> L94
        L40:
            r6.a(r10, r8)     // Catch: java.lang.Exception -> L94
            java.lang.String r7 = r6.getA2()     // Catch: java.lang.Exception -> L94
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L94
            if (r7 != 0) goto L66
            java.lang.String r7 = r6.getPin()     // Catch: java.lang.Exception -> L94
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L94
            if (r7 == 0) goto L58
            goto L66
        L58:
            if (r11 == 0) goto L5d
            r11.onSuccessHandleInner()     // Catch: java.lang.Exception -> L94
        L5d:
            r7 = 8
            r6.a(r7, r2, r1)     // Catch: java.lang.Exception -> L94
            r6.b(r9, r2, r1)     // Catch: java.lang.Exception -> L94
            return
        L66:
            if (r11 == 0) goto L7b
            r7 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r8 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r9 = new java.lang.Exception     // Catch: java.lang.Exception -> L94
            java.lang.String r10 = "a2 or pin is null"
            r9.<init>(r10)     // Catch: java.lang.Exception -> L94
            jd.wjlogin_sdk.model.ErrorResult r7 = jd.wjlogin_sdk.util.b0.a(r7, r8, r9)     // Catch: java.lang.Exception -> L94
            r11.onErrorHandleInner(r7)     // Catch: java.lang.Exception -> L94
        L7b:
            r6.b(r0, r2, r1)     // Catch: java.lang.Exception -> L94
            return
        L7f:
            jd.wjlogin_sdk.tlvtype.x r7 = r10.p()     // Catch: java.lang.Exception -> L94
            jd.wjlogin_sdk.model.FailResult r8 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L94
            r8.<init>()     // Catch: java.lang.Exception -> L94
            r6.a(r8, r9, r7)     // Catch: java.lang.Exception -> L94
            if (r11 == 0) goto L90
            r11.onFailHandleInner(r8)     // Catch: java.lang.Exception -> L94
        L90:
            r6.b(r9, r2, r1)     // Catch: java.lang.Exception -> L94
            goto La1
        L94:
            if (r11 == 0) goto L9e
            jd.wjlogin_sdk.model.FailResult r7 = r6.a()
            r11.onFailHandleInner(r7)
        L9e:
            r6.b(r0, r2, r1)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.c(java.lang.String, java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void H(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -2
            r1 = 9
            r2 = 39
            if (r6 != 0) goto L42
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L66
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L66
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L66
            if (r7 != 0) goto L29
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L66
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L66
            if (r7 == 0) goto L20
            goto L29
        L20:
            if (r8 == 0) goto L25
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L66
        L25:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L66
            return
        L29:
            if (r8 == 0) goto L3e
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L66
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L66
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L66
        L3e:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L66
            return
        L42:
            jd.wjlogin_sdk.tlvtype.x r3 = r7.p()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.FailResult r4 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L66
            r4.<init>()     // Catch: java.lang.Exception -> L66
            r5.a(r4, r6, r3)     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.tlvtype.d r3 = r7.b()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.tlvtype.l r7 = r7.i()     // Catch: java.lang.Exception -> L66
            jd.wjlogin_sdk.model.JumpResult r7 = r5.a(r3, r7)     // Catch: java.lang.Exception -> L66
            r4.setJumpResult(r7)     // Catch: java.lang.Exception -> L66
            if (r8 == 0) goto L62
            r8.onFailHandleInner(r4)     // Catch: java.lang.Exception -> L66
        L62:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L66
            goto L73
        L66:
            if (r8 == 0) goto L70
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L70:
            r5.b(r0, r2, r1)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.H(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 44, (short) 3);
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 44, (short) 3);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 44, (short) 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                    return;
                }
                return;
            }
            jd.wjlogin_sdk.tlvtype.x p2 = aVar.p();
            jd.wjlogin_sdk.tlvtype.v t2 = aVar.t();
            FailResult failResult = new FailResult();
            if (t2 != null) {
                failResult.setStrVal(t2.a());
            }
            a(failResult, b2, p2);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 44, (short) 9);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 44, (short) 9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void I(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -2
            r1 = 27
            r2 = 5
            if (r6 != 0) goto L41
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L56
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L56
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L56
            if (r7 != 0) goto L28
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L56
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L56
            if (r7 == 0) goto L1f
            goto L28
        L1f:
            if (r8 == 0) goto L24
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L56
        L24:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L56
            return
        L28:
            if (r8 == 0) goto L3d
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L56
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L56
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L56
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L56
        L3d:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L56
            return
        L41:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L56
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L56
            r3.<init>()     // Catch: java.lang.Exception -> L56
            r5.a(r3, r6, r7)     // Catch: java.lang.Exception -> L56
            if (r8 == 0) goto L52
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L56
        L52:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L56
            goto L63
        L56:
            if (r8 == 0) goto L60
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L60:
            r5.b(r0, r2, r1)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.I(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r9.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r5, java.lang.String r6, byte r7, jd.wjlogin_sdk.tlvtype.a r8, jd.wjlogin_sdk.common.listener.OnCommonCallback r9) {
        /*
            r4 = this;
            r0 = -2
            r1 = 5
            r2 = 44
            if (r7 != 0) goto L40
            r4.a(r8, r5, r6)     // Catch: java.lang.Exception -> L64
            java.lang.String r5 = r4.getA2()     // Catch: java.lang.Exception -> L64
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L64
            if (r5 != 0) goto L27
            java.lang.String r5 = r4.getPin()     // Catch: java.lang.Exception -> L64
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L64
            if (r5 == 0) goto L1e
            goto L27
        L1e:
            if (r9 == 0) goto L23
            r9.onSuccessHandleInner()     // Catch: java.lang.Exception -> L64
        L23:
            r4.b(r7, r2, r1)     // Catch: java.lang.Exception -> L64
            return
        L27:
            if (r9 == 0) goto L3c
            r5 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r6 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L64
            java.lang.String r8 = "a2 or pin is null"
            r7.<init>(r8)     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r5, r6, r7)     // Catch: java.lang.Exception -> L64
            r9.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L64
        L3c:
            r4.b(r0, r2, r1)     // Catch: java.lang.Exception -> L64
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r5 = r8.p()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.FailResult r6 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L64
            r6.<init>()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.tlvtype.d r3 = r8.b()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.tlvtype.l r8 = r8.i()     // Catch: java.lang.Exception -> L64
            jd.wjlogin_sdk.model.JumpResult r8 = r4.a(r3, r8)     // Catch: java.lang.Exception -> L64
            r6.setJumpResult(r8)     // Catch: java.lang.Exception -> L64
            r4.a(r6, r7, r5)     // Catch: java.lang.Exception -> L64
            if (r9 == 0) goto L60
            r9.onFailHandleInner(r6)     // Catch: java.lang.Exception -> L64
        L60:
            r4.b(r7, r2, r1)     // Catch: java.lang.Exception -> L64
            goto L71
        L64:
            if (r9 == 0) goto L6e
            jd.wjlogin_sdk.model.FailResult r5 = r4.a()
            r9.onFailHandleInner(r5)
        L6e:
            r4.b(r0, r2, r1)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.b(java.lang.String, java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        r12.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(byte r8, jd.wjlogin_sdk.tlvtype.a r9, java.lang.String r10, java.lang.String r11, jd.wjlogin_sdk.common.listener.OnCommonCallback r12) {
        /*
            r7 = this;
            r0 = -2
            r1 = 26
            r2 = 5
            if (r8 != 0) goto L40
            r7.a(r9, r10, r11)     // Catch: java.lang.Exception -> L7e
            java.lang.String r9 = r7.getA2()     // Catch: java.lang.Exception -> L7e
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L7e
            if (r9 != 0) goto L27
            java.lang.String r9 = r7.getPin()     // Catch: java.lang.Exception -> L7e
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> L7e
            if (r9 == 0) goto L1e
            goto L27
        L1e:
            if (r12 == 0) goto L23
            r12.onSuccessHandleInner()     // Catch: java.lang.Exception -> L7e
        L23:
            r7.a(r10, r8, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L27:
            if (r12 == 0) goto L3c
            r8 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r9 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r10 = new java.lang.Exception     // Catch: java.lang.Exception -> L7e
            java.lang.String r11 = "a2 or pin is null"
            r10.<init>(r11)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.ErrorResult r8 = jd.wjlogin_sdk.util.b0.a(r8, r9, r10)     // Catch: java.lang.Exception -> L7e
            r12.onErrorHandleInner(r8)     // Catch: java.lang.Exception -> L7e
        L3c:
            r7.b(r0, r2, r1)     // Catch: java.lang.Exception -> L7e
            return
        L40:
            jd.wjlogin_sdk.tlvtype.x r11 = r9.p()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.e r3 = r9.c()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.d r4 = r9.b()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.l r5 = r9.i()     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.model.FailResult r6 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L7e
            r6.<init>()     // Catch: java.lang.Exception -> L7e
            r7.a(r6, r8, r11)     // Catch: java.lang.Exception -> L7e
            jd.wjlogin_sdk.tlvtype.i r9 = r9.g()     // Catch: java.lang.Exception -> L7e
            if (r3 == 0) goto L65
            int r11 = r3.a()     // Catch: java.lang.Exception -> L7e
            r6.setIntVal(r11)     // Catch: java.lang.Exception -> L7e
        L65:
            if (r9 == 0) goto L6e
            int r9 = r9.a()     // Catch: java.lang.Exception -> L7e
            r6.setIntVal(r9)     // Catch: java.lang.Exception -> L7e
        L6e:
            jd.wjlogin_sdk.model.JumpResult r9 = r7.a(r4, r5)     // Catch: java.lang.Exception -> L7e
            r7.a(r6, r9)     // Catch: java.lang.Exception -> L7e
            if (r12 == 0) goto L7a
            r12.onFailHandleInner(r6)     // Catch: java.lang.Exception -> L7e
        L7a:
            r7.a(r10, r8, r2, r1)     // Catch: java.lang.Exception -> L7e
            goto L8b
        L7e:
            if (r12 == 0) goto L88
            jd.wjlogin_sdk.model.FailResult r8 = r7.a()
            r12.onFailHandleInner(r8)
        L88:
            r7.b(r0, r2, r1)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.WJLoginForThirdParty.c(byte, jd.wjlogin_sdk.tlvtype.a, java.lang.String, java.lang.String, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
    }
}
