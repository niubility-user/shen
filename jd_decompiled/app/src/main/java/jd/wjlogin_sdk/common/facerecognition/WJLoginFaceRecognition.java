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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = 1
            r1 = 15
            if (r6 != 0) goto L44
            java.lang.String r2 = r5.getUserAccount()     // Catch: java.lang.Exception -> L7a
            r5.a(r7, r2)     // Catch: java.lang.Exception -> L7a
            java.lang.String r7 = r5.getA2()     // Catch: java.lang.Exception -> L7a
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L7a
            if (r7 != 0) goto L2a
            java.lang.String r7 = r5.getPin()     // Catch: java.lang.Exception -> L7a
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L7a
            if (r7 == 0) goto L21
            goto L2a
        L21:
            if (r8 == 0) goto L26
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L7a
        L26:
            r5.b(r6, r1, r0)     // Catch: java.lang.Exception -> L7a
            return
        L2a:
            if (r8 == 0) goto L3f
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r2 = new java.lang.Exception     // Catch: java.lang.Exception -> L7a
            java.lang.String r3 = "a2 or pin is null"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r2)     // Catch: java.lang.Exception -> L7a
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L7a
        L3f:
            r6 = -2
            r5.b(r6, r1, r0)     // Catch: java.lang.Exception -> L7a
            return
        L44:
            jd.wjlogin_sdk.tlvtype.x r2 = r7.p()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L7a
            r3.<init>()     // Catch: java.lang.Exception -> L7a
            r5.a(r3, r6, r2)     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.d r2 = r7.b()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.l r4 = r7.i()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.e r7 = r7.c()     // Catch: java.lang.Exception -> L7a
            if (r7 == 0) goto L65
            int r7 = r7.a()     // Catch: java.lang.Exception -> L7a
            r3.setIntVal(r7)     // Catch: java.lang.Exception -> L7a
        L65:
            r7 = -128(0xffffffffffffff80, float:NaN)
            if (r6 < r7) goto L71
            r7 = -113(0xffffffffffffff8f, float:NaN)
            if (r6 > r7) goto L71
            r5.a(r8, r3, r2, r4)     // Catch: java.lang.Exception -> L7a
            goto L76
        L71:
            if (r8 == 0) goto L76
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L7a
        L76:
            r5.b(r6, r1, r0)     // Catch: java.lang.Exception -> L7a
            goto L8b
        L7a:
            r6 = move-exception
            r6.printStackTrace()
            if (r8 == 0) goto L87
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L87:
            r6 = -1
            r5.b(r6, r1, r0)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.facerecognition.WJLoginFaceRecognition.n(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l(byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r5 = this;
            r0 = -1
            r1 = 13
            r2 = 15
            if (r6 != 0) goto L42
            r3 = 0
            r5.a(r7, r3)     // Catch: java.lang.Exception -> L57
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
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L57
            return
        L29:
            if (r8 == 0) goto L3e
            r6 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r7 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Exception -> L57
            java.lang.String r4 = "a2 or pin is null"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L57
            jd.wjlogin_sdk.model.ErrorResult r6 = jd.wjlogin_sdk.util.b0.a(r6, r7, r3)     // Catch: java.lang.Exception -> L57
            r8.onErrorHandleInner(r6)     // Catch: java.lang.Exception -> L57
        L3e:
            r5.b(r0, r2, r1)     // Catch: java.lang.Exception -> L57
            return
        L42:
            jd.wjlogin_sdk.tlvtype.x r7 = r7.p()     // Catch: java.lang.Exception -> L57
            jd.wjlogin_sdk.model.FailResult r3 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L57
            r3.<init>()     // Catch: java.lang.Exception -> L57
            r5.a(r3, r6, r7)     // Catch: java.lang.Exception -> L57
            if (r8 == 0) goto L53
            r8.onFailHandleInner(r3)     // Catch: java.lang.Exception -> L57
        L53:
            r5.b(r6, r2, r1)     // Catch: java.lang.Exception -> L57
            goto L67
        L57:
            r6 = move-exception
            r6.printStackTrace()
            if (r8 == 0) goto L64
            jd.wjlogin_sdk.model.FailResult r6 = r5.a()
            r8.onFailHandleInner(r6)
        L64:
            r5.b(r0, r2, r1)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.facerecognition.WJLoginFaceRecognition.l(byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r4, byte r5, jd.wjlogin_sdk.tlvtype.a r6, jd.wjlogin_sdk.common.listener.OnCommonCallback r7) {
        /*
            r3 = this;
            r0 = 15
            if (r5 != 0) goto L44
            r3.a(r6, r4)     // Catch: java.lang.Exception -> L75
            java.lang.String r4 = r3.getA2()     // Catch: java.lang.Exception -> L75
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L75
            if (r4 != 0) goto L2a
            java.lang.String r4 = r3.getPin()     // Catch: java.lang.Exception -> L75
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L75
            if (r4 == 0) goto L1c
            goto L2a
        L1c:
            if (r7 == 0) goto L21
            r7.onSuccessHandleInner()     // Catch: java.lang.Exception -> L75
        L21:
            r3.b(r5, r0, r0)     // Catch: java.lang.Exception -> L75
            r4 = 9
            r3.a(r4, r0, r0)     // Catch: java.lang.Exception -> L75
            return
        L2a:
            if (r7 == 0) goto L3f
            r4 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r5 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r6 = new java.lang.Exception     // Catch: java.lang.Exception -> L75
            java.lang.String r1 = "a2 or pin is null"
            r6.<init>(r1)     // Catch: java.lang.Exception -> L75
            jd.wjlogin_sdk.model.ErrorResult r4 = jd.wjlogin_sdk.util.b0.a(r4, r5, r6)     // Catch: java.lang.Exception -> L75
            r7.onErrorHandleInner(r4)     // Catch: java.lang.Exception -> L75
        L3f:
            r4 = -2
            r3.b(r4, r0, r0)     // Catch: java.lang.Exception -> L75
            return
        L44:
            jd.wjlogin_sdk.tlvtype.x r4 = r6.p()     // Catch: java.lang.Exception -> L75
            jd.wjlogin_sdk.model.FailResult r1 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L75
            r1.<init>()     // Catch: java.lang.Exception -> L75
            r3.a(r1, r5, r4)     // Catch: java.lang.Exception -> L75
            jd.wjlogin_sdk.tlvtype.d r4 = r6.b()     // Catch: java.lang.Exception -> L75
            jd.wjlogin_sdk.tlvtype.l r2 = r6.i()     // Catch: java.lang.Exception -> L75
            jd.wjlogin_sdk.tlvtype.e r6 = r6.c()     // Catch: java.lang.Exception -> L75
            if (r6 == 0) goto L65
            int r6 = r6.a()     // Catch: java.lang.Exception -> L75
            r1.setIntVal(r6)     // Catch: java.lang.Exception -> L75
        L65:
            jd.wjlogin_sdk.model.JumpResult r4 = r3.a(r4, r2)     // Catch: java.lang.Exception -> L75
            r3.a(r1, r4)     // Catch: java.lang.Exception -> L75
            if (r7 == 0) goto L71
            r7.onFailHandleInner(r1)     // Catch: java.lang.Exception -> L75
        L71:
            r3.b(r5, r0, r0)     // Catch: java.lang.Exception -> L75
            goto L83
        L75:
            if (r7 == 0) goto L7f
            jd.wjlogin_sdk.model.FailResult r4 = r3.a()
            r7.onFailHandleInner(r4)
        L7f:
            r4 = -1
            r3.b(r4, r0, r0)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.facerecognition.WJLoginFaceRecognition.a(java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r5, byte r6, jd.wjlogin_sdk.tlvtype.a r7, jd.wjlogin_sdk.common.listener.OnCommonCallback r8) {
        /*
            r4 = this;
            r0 = 17
            r1 = 15
            if (r6 != 0) goto L49
            r4.a(r7, r5)     // Catch: java.lang.Exception -> L7a
            java.lang.String r5 = r4.getA2()     // Catch: java.lang.Exception -> L7a
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L7a
            if (r5 != 0) goto L2f
            java.lang.String r5 = r4.getPin()     // Catch: java.lang.Exception -> L7a
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L7a
            if (r5 == 0) goto L1e
            goto L2f
        L1e:
            if (r8 == 0) goto L23
            r8.onSuccessHandleInner()     // Catch: java.lang.Exception -> L7a
        L23:
            r4.b(r6, r1, r0)     // Catch: java.lang.Exception -> L7a
            r5 = 9
            r6 = 16
            r7 = 1
            r4.a(r5, r6, r7)     // Catch: java.lang.Exception -> L7a
            return
        L2f:
            if (r8 == 0) goto L44
            r5 = -102(0xffffffffffffff9a, float:NaN)
            java.lang.String r6 = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L7a
            java.lang.String r2 = "a2 or pin is null"
            r7.<init>(r2)     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.model.ErrorResult r5 = jd.wjlogin_sdk.util.b0.a(r5, r6, r7)     // Catch: java.lang.Exception -> L7a
            r8.onErrorHandleInner(r5)     // Catch: java.lang.Exception -> L7a
        L44:
            r5 = -2
            r4.b(r5, r1, r0)     // Catch: java.lang.Exception -> L7a
            return
        L49:
            jd.wjlogin_sdk.tlvtype.x r5 = r7.p()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.model.FailResult r2 = new jd.wjlogin_sdk.model.FailResult     // Catch: java.lang.Exception -> L7a
            r2.<init>()     // Catch: java.lang.Exception -> L7a
            r4.a(r2, r6, r5)     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.d r5 = r7.b()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.l r3 = r7.i()     // Catch: java.lang.Exception -> L7a
            jd.wjlogin_sdk.tlvtype.e r7 = r7.c()     // Catch: java.lang.Exception -> L7a
            if (r7 == 0) goto L6a
            int r7 = r7.a()     // Catch: java.lang.Exception -> L7a
            r2.setIntVal(r7)     // Catch: java.lang.Exception -> L7a
        L6a:
            jd.wjlogin_sdk.model.JumpResult r5 = r4.a(r5, r3)     // Catch: java.lang.Exception -> L7a
            r4.a(r2, r5)     // Catch: java.lang.Exception -> L7a
            if (r8 == 0) goto L76
            r8.onFailHandleInner(r2)     // Catch: java.lang.Exception -> L7a
        L76:
            r4.b(r6, r1, r0)     // Catch: java.lang.Exception -> L7a
            goto L88
        L7a:
            if (r8 == 0) goto L84
            jd.wjlogin_sdk.model.FailResult r5 = r4.a()
            r8.onFailHandleInner(r5)
        L84:
            r5 = -1
            r4.b(r5, r1, r0)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.common.facerecognition.WJLoginFaceRecognition.b(java.lang.String, byte, jd.wjlogin_sdk.tlvtype.a, jd.wjlogin_sdk.common.listener.OnCommonCallback):void");
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
