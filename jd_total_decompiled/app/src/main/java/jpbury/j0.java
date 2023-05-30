package jpbury;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;

/* loaded from: classes11.dex */
public interface j0 {
    void a(int i2);

    void a(@NonNull Message message);

    void a(Runnable runnable);

    boolean a();

    boolean a(int i2, long j2);

    boolean a(Looper looper);

    boolean a(Runnable runnable, long j2);

    Looper b();

    boolean b(Runnable runnable);

    boolean c();

    boolean c(Runnable runnable);
}
