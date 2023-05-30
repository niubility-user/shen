package jd.wjlogin_sdk.c;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import java.util.List;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.p;
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 343
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.c.i.a.run():void");
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
