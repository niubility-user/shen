package jd.wjlogin_sdk.common.facerecognition;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.login.LoginConstans;
import jd.wjlogin_sdk.common.communion.WJLoginCommunion;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.CheckFaceLoginResp;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FLDeviceInfo;
import jd.wjlogin_sdk.model.FaceLoginSwitch;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.JumpResult;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.tlvtype.a0;
import jd.wjlogin_sdk.tlvtype.d0;
import jd.wjlogin_sdk.tlvtype.v;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.r;
import jd.wjlogin_sdk.util.y;

/* loaded from: classes.dex */
public class WJLoginFaceRecognition extends WJLoginCommunion {

    /* loaded from: classes.dex */
    class a implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        a(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.m(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 4);
        }
    }

    /* loaded from: classes.dex */
    class b implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        b(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.a(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    class c implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;
        final /* synthetic */ jd.wjlogin_sdk.d.c b;

        c(OnDataCallback onDataCallback, jd.wjlogin_sdk.d.c cVar) {
            this.a = onDataCallback;
            this.b = cVar;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.b(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, this.b.d(), this.b.o());
        }
    }

    /* loaded from: classes.dex */
    class d implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        d(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.b(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 17);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        e(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.n(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 1);
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
            WJLoginFaceRecognition.this.m(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<SuccessResult>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    class g implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        g(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.m(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 4);
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
            WJLoginFaceRecognition.this.k(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 5);
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
            WJLoginFaceRecognition.this.n(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, (OnDataCallback<CheckFaceLoginResp>) this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 6);
        }
    }

    /* loaded from: classes.dex */
    class j implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ String a;
        final /* synthetic */ OnCommonCallback b;

        j(String str, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.a(this.a, ((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.b;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 7);
        }
    }

    /* loaded from: classes.dex */
    class k implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        k(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.j(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 11);
        }
    }

    /* loaded from: classes.dex */
    class l implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnCommonCallback a;

        l(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.l(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, (short) 15, (short) 13);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m implements jd.wjlogin_sdk.c.f {
        final /* synthetic */ OnDataCallback a;
        final /* synthetic */ jd.wjlogin_sdk.d.c b;

        m(OnDataCallback onDataCallback, jd.wjlogin_sdk.d.c cVar) {
            this.a = onDataCallback;
            this.b = cVar;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginFaceRecognition.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a, this.b);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnDataCallback onDataCallback = this.a;
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(errorResult);
            }
            WJLoginFaceRecognition.this.b((byte) -1, this.b.d(), this.b.o());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void n(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, getUserAccount());
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 15, (short) 1);
                    return;
                }
                b((byte) -2, (short) 15, (short) 1);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            if (b2 >= Byte.MIN_VALUE && b2 <= -113) {
                a(onCommonCallback, failResult, b3, i2);
            } else if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 1);
        }
    }

    public void checkFaceSwitch(OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.a(cVar, getPin() == null ? "" : getPin());
            jd.wjlogin_sdk.d.e.d.f(cVar, getA2());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new f(onDataCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a("checkFaceSwitch");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void checkRealName(OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 11, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.a(cVar, getPin());
            jd.wjlogin_sdk.d.e.d.f(cVar, getA2());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new k(onCommonCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a("checkRealName");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void closeFaceSwitch(OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 5, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.a(cVar, getPin() == null ? "" : getPin());
            jd.wjlogin_sdk.d.e.d.f(cVar, getA2());
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new h(onCommonCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a("closeFaceSwitch");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void faceLogin(FLDeviceInfo fLDeviceInfo, String str, String str2, String str3, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.b(cVar, str == null ? "" : str);
            jd.wjlogin_sdk.d.e.d.a(cVar, fLDeviceInfo);
            jd.wjlogin_sdk.d.e.d.c(cVar, str3);
            jd.wjlogin_sdk.d.e.d.e(cVar, str2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new j(str, onCommonCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a(LoginConstans.FACELOGIN_FLAG);
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void faceLoginV2(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 15, (short) 15, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.d(bVar, TextUtils.isEmpty(str) ? "" : str);
            jd.wjlogin_sdk.d.d.s(bVar, str2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b(str, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(bVar.b()).a("faceLoginV2");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void faceSearchLogin(OnCommonCallback onCommonCallback, String str) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 13, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.c(cVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new l(onCommonCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a("faceSearchLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void getFaceLoginTokenV2(String str, OnDataCallback<SuccessResult> onDataCallback) {
        getFaceLoginTokenV2(str, getPin(), onDataCallback);
    }

    public void getUrlWithFaceVerifyToken(String str, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            jd.wjlogin_sdk.d.c a2 = jd.wjlogin_sdk.d.d.a((short) 41, (short) 7, jd.wjlogin_sdk.util.g.d(), this.seq);
            bVar.a(a2);
            jd.wjlogin_sdk.d.d.a(bVar);
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jd.wjlogin_sdk.d.d.b(bVar, str);
            this.a = System.currentTimeMillis();
            b(bVar, onDataCallback, new c(onDataCallback, a2), "getUrlWithFaceVerifyToken");
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void isOpenFaceLogin(String str, OnDataCallback<CheckFaceLoginResp> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 6, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            if (str == null) {
                str = "";
            }
            jd.wjlogin_sdk.d.e.d.b(cVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new i(onDataCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).b(1000).a(cVar.b()).a("isOpenFaceLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void openFaceSwitch(FLDeviceInfo fLDeviceInfo, String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.e.c cVar = new jd.wjlogin_sdk.d.e.c();
            cVar.a(jd.wjlogin_sdk.d.e.d.a((short) 15, (short) 4, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.e.d.a(cVar);
            jd.wjlogin_sdk.d.e.d.a(cVar, getPin() == null ? "" : getPin());
            jd.wjlogin_sdk.d.e.d.f(cVar, getA2());
            jd.wjlogin_sdk.d.e.d.a(cVar, fLDeviceInfo);
            jd.wjlogin_sdk.d.e.d.c(cVar, str2);
            jd.wjlogin_sdk.d.e.d.e(cVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new g(onCommonCallback));
            gVar.a(cVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(cVar.b()).a("openFaceSwitch");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void openFaceSwitchV2(String str, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 15, (short) 14, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            String pin = getPin();
            if (TextUtils.isEmpty(pin)) {
                pin = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, pin);
            jd.wjlogin_sdk.d.d.y(bVar, getA2());
            jd.wjlogin_sdk.d.d.s(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(bVar.b()).a("openFaceSwitchV2");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void openSwitchAndFaceLogin(String str, String str2, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 15, (short) 17, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar);
            jd.wjlogin_sdk.d.d.d(bVar, TextUtils.isEmpty(str) ? "" : str);
            jd.wjlogin_sdk.d.d.s(bVar, str2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new d(str, onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(bVar.b()).a("openSwitchAndFaceLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void peopleFaceLogin(String str, String str2, FLDeviceInfo fLDeviceInfo, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 15, (short) 1, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            if (!TextUtils.isEmpty(str2)) {
                jd.wjlogin_sdk.d.d.b(bVar, str2);
            }
            jd.wjlogin_sdk.d.d.a(bVar, fLDeviceInfo);
            jd.wjlogin_sdk.d.d.t(bVar, jd.wjlogin_sdk.common.h.a.c());
            jd.wjlogin_sdk.d.d.A(bVar, str);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new e(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("peopleFaceLogin");
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 15, (short) 11);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            if (i2 != null) {
                JumpResult jumpResult = new JumpResult();
                jumpResult.setUrl(i2.a());
                failResult.setJumpResult(jumpResult);
            }
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 15, (short) 5);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 5);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void l(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 15, (short) 13);
                    return;
                }
                b((byte) -1, (short) 15, (short) 13);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 13);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 13);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.b z = aVar.z();
                if (z == null) {
                    if (onDataCallback != null) {
                        onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(y.b)));
                        return;
                    }
                    return;
                }
                d0 B = aVar.B();
                if (onDataCallback != null) {
                    SuccessResult successResult = new SuccessResult();
                    successResult.setIntVal(z.a());
                    if (B != null) {
                        FaceLoginSwitch faceLoginSwitch = new FaceLoginSwitch();
                        faceLoginSwitch.setEggsSwitch(B.a());
                        faceLoginSwitch.setSpecialSwitch(B.b());
                        successResult.setFaceLoginSwitch(faceLoginSwitch);
                    }
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, (short) 15, (short) 3);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 3);
        } catch (Exception unused) {
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 3);
        }
    }

    public void getFaceLoginTokenV2(String str, String str2, OnDataCallback<SuccessResult> onDataCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            jd.wjlogin_sdk.d.c a2 = jd.wjlogin_sdk.d.d.a((short) 15, (short) 16, jd.wjlogin_sdk.util.g.d(), this.seq);
            bVar.a(a2);
            jd.wjlogin_sdk.d.d.a(bVar);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, str2);
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jd.wjlogin_sdk.d.d.d(bVar, str);
            this.a = System.currentTimeMillis();
            a(bVar, onDataCallback, new m(onDataCallback, a2), "getFaceLoginTokenV2");
        } catch (Exception e2) {
            if (onDataCallback != null) {
                onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(String str, byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 15, (short) 15);
                    a((byte) 9, (short) 15, (short) 15);
                    return;
                }
                b((byte) -2, (short) 15, (short) 15);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b3, i2));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 15);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback, jd.wjlogin_sdk.d.c cVar) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    String a2 = aVar.i().a();
                    p.b("reset pwd URL = " + a2);
                    SuccessResult successResult = new SuccessResult();
                    successResult.setStrVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, cVar.d(), cVar.o());
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, cVar.d(), cVar.o());
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -1, cVar.d(), cVar.o());
        }
    }

    public void peopleFaceLogin(String str, FLDeviceInfo fLDeviceInfo, OnCommonCallback onCommonCallback) {
        peopleFaceLogin("", str, fLDeviceInfo, onCommonCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
        r8.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(String str, byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                a(aVar, str);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 15, (short) 17);
                    a((byte) 9, (short) 16, (short) 1);
                    return;
                }
                b((byte) -2, (short) 15, (short) 17);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            jd.wjlogin_sdk.tlvtype.d b3 = aVar.b();
            jd.wjlogin_sdk.tlvtype.l i2 = aVar.i();
            jd.wjlogin_sdk.tlvtype.e c2 = aVar.c();
            if (c2 != null) {
                failResult.setIntVal(c2.a());
            }
            a(failResult, a(b3, i2));
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 17);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 17);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 15, (short) 4);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 4);
        } catch (Exception unused) {
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<CheckFaceLoginResp> onDataCallback) {
        try {
            if (b2 == 0) {
                jd.wjlogin_sdk.tlvtype.b z = aVar.z();
                if (z == null) {
                    if (onDataCallback != null) {
                        onDataCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new Exception(y.b)));
                    }
                    b(b2, (short) 15, (short) 6);
                    return;
                }
                a0 y = aVar.y();
                d0 B = aVar.B();
                String a2 = y != null ? y.a() : "";
                CheckFaceLoginResp checkFaceLoginResp = new CheckFaceLoginResp();
                checkFaceLoginResp.setStatus(z.a());
                checkFaceLoginResp.setUrl(a2);
                if (B != null) {
                    FaceLoginSwitch faceLoginSwitch = new FaceLoginSwitch();
                    faceLoginSwitch.setSpecialSwitch(B.b());
                    faceLoginSwitch.setEggsSwitch(B.a());
                    checkFaceLoginResp.setFaceLoginSwitch(faceLoginSwitch);
                }
                v t = aVar.t();
                if (t != null) {
                    checkFaceLoginResp.setStatusJson(t.a());
                }
                if (onDataCallback != null) {
                    onDataCallback.onSuccessHandleInner(checkFaceLoginResp);
                }
                b(b2, (short) 15, (short) 6);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 15, (short) 6);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -1, (short) 15, (short) 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<SuccessResult> onDataCallback, jd.wjlogin_sdk.d.c cVar) {
        try {
            if (b2 == 0) {
                if (onDataCallback != null) {
                    String a2 = aVar.A().a();
                    p.b("face token v2 = " + a2);
                    SuccessResult successResult = new SuccessResult();
                    successResult.setStrVal(a2);
                    onDataCallback.onSuccessHandleInner(successResult);
                }
                b(b2, cVar.d(), cVar.o());
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(failResult);
            }
            b(b2, cVar.d(), cVar.o());
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
            b((byte) -1, cVar.d(), cVar.o());
        }
    }
}
