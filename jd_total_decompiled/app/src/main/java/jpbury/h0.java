package jpbury;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;

/* loaded from: classes11.dex */
public class h0 implements j0 {
    private final Handler a;

    /* loaded from: classes11.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            h0.this.a(message);
        }
    }

    public h0(@NonNull Looper looper) {
        this.a = new a(looper);
    }

    @Override // jpbury.j0
    public void a(int i2) {
        this.a.removeMessages(i2);
    }

    @Override // jpbury.j0
    public void a(@NonNull Message message) {
    }

    @Override // jpbury.j0
    public void a(Runnable runnable) {
        this.a.removeCallbacks(runnable);
    }

    @Override // jpbury.j0
    public boolean a() {
        return Looper.myLooper() == b();
    }

    @Override // jpbury.j0
    public boolean a(int i2, long j2) {
        return this.a.sendEmptyMessageDelayed(i2, j2);
    }

    @Override // jpbury.j0
    public boolean a(Looper looper) {
        return looper == b();
    }

    @Override // jpbury.j0
    public boolean a(Runnable runnable, long j2) {
        return this.a.postDelayed(runnable, j2);
    }

    @Override // jpbury.j0
    public Looper b() {
        return this.a.getLooper();
    }

    @Override // jpbury.j0
    public boolean b(Runnable runnable) {
        return this.a.hasCallbacks(runnable);
    }

    @Override // jpbury.j0
    public boolean c() {
        return Looper.getMainLooper() == b();
    }

    @Override // jpbury.j0
    public boolean c(@NonNull Runnable runnable) {
        return this.a.post(runnable);
    }
}
