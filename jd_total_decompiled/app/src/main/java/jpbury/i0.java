package jpbury;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;

/* loaded from: classes11.dex */
public class i0 implements j0 {

    /* loaded from: classes11.dex */
    public static final class b {
        public static final i0 a = new i0();

        private b() {
        }
    }

    private i0() {
    }

    public static i0 d() {
        return b.a;
    }

    @Override // jpbury.j0
    public void a(int i2) {
    }

    @Override // jpbury.j0
    public void a(@NonNull Message message) {
    }

    @Override // jpbury.j0
    public void a(Runnable runnable) {
    }

    @Override // jpbury.j0
    public boolean a() {
        return false;
    }

    @Override // jpbury.j0
    public boolean a(int i2, long j2) {
        return false;
    }

    @Override // jpbury.j0
    public boolean a(Looper looper) {
        return false;
    }

    @Override // jpbury.j0
    public boolean a(Runnable runnable, long j2) {
        return true;
    }

    @Override // jpbury.j0
    public Looper b() {
        return null;
    }

    @Override // jpbury.j0
    public boolean b(Runnable runnable) {
        return false;
    }

    @Override // jpbury.j0
    public boolean c() {
        return false;
    }

    @Override // jpbury.j0
    public boolean c(Runnable runnable) {
        return true;
    }
}
