package jd.wjlogin_sdk.c;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import java.util.List;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.LanguageToast;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.y;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class i {

    /* renamed from: f  reason: collision with root package name */
    private static final String f19730f = "WJLogin.LGSController";
    private f a;
    private Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c  reason: collision with root package name */
    String f19731c = "";
    short d = 0;

    /* renamed from: e  reason: collision with root package name */
    short f19732e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class a implements Runnable {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            int i2;
            e eVar;
            e eVar2;
            long currentTimeMillis;
            x p;
            int i3 = 0;
            int i4 = jd.wjweblogin.d.c.f20052g;
            try {
                List<e> a = i.this.a();
                int size = a.size();
                i2 = 0;
                while (i3 < size) {
                    try {
                        eVar2 = a.get(i3);
                        try {
                            currentTimeMillis = System.currentTimeMillis();
                            i2 = eVar2.b();
                        } catch (Exception e2) {
                            e = e2;
                            eVar = eVar2;
                            try {
                                e.printStackTrace();
                                i.this.a(-1002, "general exception", eVar, i2, 0L);
                                i3++;
                                i4 = jd.wjweblogin.d.c.f20052g;
                            } catch (Exception e3) {
                                e = e3;
                                i3 = i2;
                                e.printStackTrace();
                                i2 = i3;
                                if (i2 == 200) {
                                }
                                i.this.a(b0.a(r4, LanguageToast.getToastMsg(r4), new Exception(y.f19996f)));
                                if (p.b) {
                                }
                            }
                        }
                    } catch (Exception e4) {
                        e = e4;
                        eVar = null;
                    }
                    if (i2 != 200) {
                        i.this.a(-1001, "http error", eVar2, i2, System.currentTimeMillis() - currentTimeMillis);
                        if (size == 2 && TextUtils.equals("V1Executor", eVar2.c())) {
                            i.this.a((byte) -2);
                        }
                        i3++;
                        i4 = jd.wjweblogin.d.c.f20052g;
                    } else {
                        Pair<Byte, jd.wjlogin_sdk.tlvtype.a> d = eVar2.d();
                        if (d != null) {
                            i.this.a(d);
                            jd.wjlogin_sdk.tlvtype.a aVar = (jd.wjlogin_sdk.tlvtype.a) d.second;
                            String str = "";
                            if (aVar != null && (p = aVar.p()) != null) {
                                String b = p.b();
                                String a2 = p.a();
                                if (!TextUtils.isEmpty(b)) {
                                    str = b;
                                } else if (!TextUtils.isEmpty(a2)) {
                                    str = a2;
                                }
                            }
                            i.this.a(((Byte) d.first).byteValue(), str, eVar2, i2, System.currentTimeMillis() - currentTimeMillis);
                            if (size == 2 && TextUtils.equals("V1Executor", eVar2.c())) {
                                i.this.a((byte) -3);
                                return;
                            }
                            return;
                        }
                        i.this.a(b0.a(i4, LanguageToast.getToastMsg(i4), new Exception(y.f19995e)));
                        i.this.a(-1000, "tlv is null", eVar2, i2, System.currentTimeMillis() - currentTimeMillis);
                        if (size == 2 && TextUtils.equals("V1Executor", eVar2.c())) {
                            i.this.a((byte) -2);
                            return;
                        }
                        return;
                    }
                }
            } catch (Exception e5) {
                e = e5;
            }
            int i5 = i2 == 200 ? jd.wjweblogin.d.c.f20052g : i2;
            i.this.a(b0.a(i5, LanguageToast.getToastMsg(i5), new Exception(y.f19996f)));
            if (p.b) {
                return;
            }
            p.b(i.f19730f, "<< " + i.this.f19731c + " >> error, httpStatusCode is " + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class b implements Runnable {
        final /* synthetic */ Pair a;

        b(Pair pair) {
            this.a = pair;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.a.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class c implements Runnable {
        final /* synthetic */ ErrorResult a;

        c(ErrorResult errorResult) {
            this.a = errorResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.a.a(this.a);
        }
    }

    public i(f fVar) {
        this.a = fVar;
    }

    abstract List<e> a();

    public void b() {
        jd.wjlogin_sdk.net.d.a().a(new a());
    }

    public i a(String str) {
        this.f19731c = str;
        return this;
    }

    public i a(jd.wjlogin_sdk.d.c cVar) {
        if (cVar != null) {
            this.d = cVar.d();
            this.f19732e = cVar.o();
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2) {
        try {
            jd.wjlogin_sdk.common.f.a().reportNewLoginResult(b2, this.d, this.f19732e);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, e eVar, int i3, long j2) {
        try {
            if (p.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callMethod", this.f19731c);
                jSONObject.put("replyCode", i2);
                jSONObject.put("errorMsg", str);
                jSONObject.put("executor", eVar.c());
                jSONObject.put("httpCode", i3);
                jSONObject.put("cosTime", j2);
                p.b(f19730f, "response: " + l.a(jSONObject));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
        if (this.a != null) {
            this.b.post(new b(pair));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ErrorResult errorResult) {
        if (this.a != null) {
            this.b.post(new c(errorResult));
        }
    }
}
