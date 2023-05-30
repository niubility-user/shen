package jpbury;

import com.jdpay.bury.IdExtension;
import com.jdpay.bury.SessionPack;

/* loaded from: classes11.dex */
public class b0 implements c0 {

    /* loaded from: classes11.dex */
    public static final class b {
        public static final b0 a = new b0();

        private b() {
        }
    }

    private b0() {
    }

    public static b0 c() {
        return b.a;
    }

    @Override // jpbury.c0
    public SessionPack a() {
        return new SessionPack();
    }

    @Override // jpbury.c0
    public SessionPack a(String str) {
        return new SessionPack(str);
    }

    @Override // jpbury.c0
    public SessionPack a(String str, String str2, String str3) {
        return new SessionPack(str, str2, str3);
    }

    @Override // jpbury.c0
    public void a(String str, SessionPack sessionPack) {
    }

    @Override // jpbury.c0
    public void a(String str, String str2) {
    }

    @Override // jpbury.c0
    public void a(String str, String str2, IdExtension idExtension, String str3, String str4, int i2, boolean z) {
    }

    @Override // jpbury.c0
    public void a(String str, String str2, IdExtension idExtension, String str3, String str4, boolean z) {
    }

    @Override // jpbury.c0
    public void a(String str, String str2, String str3, String str4, String str5, String str6, int i2, boolean z) {
    }

    @Override // jpbury.c0
    public void a(String str, String str2, Throwable th, String str3, boolean z) {
    }

    @Override // jpbury.c0
    public SessionPack b(String str, String str2) {
        return new SessionPack(str, str2);
    }

    @Override // jpbury.c0
    public String b() {
        return "-1";
    }
}
