package jpbury;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.wangyin.platform.CryptoUtils;
import java.nio.charset.Charset;

/* loaded from: classes11.dex */
public class w {

    /* renamed from: c  reason: collision with root package name */
    private static final Charset f20148c = Charset.forName("UTF-8");
    private static final String d = "00000";
    private CryptoUtils a;
    private final Object b;

    /* loaded from: classes11.dex */
    public static final class b {
        public static final w a = new w();

        private b() {
        }
    }

    private w() {
        this.b = new Object();
    }

    @Nullable
    private CryptoUtils a() {
        if (this.a == null) {
            synchronized (this.b) {
                if (this.a == null) {
                    Context b2 = a0.b();
                    if (b2 == null) {
                        return null;
                    }
                    CryptoUtils newInstance = CryptoUtils.newInstance(b2);
                    this.a = newInstance;
                    newInstance.startAutoHandshake();
                }
            }
        }
        return this.a;
    }

    public static w b() {
        return b.a;
    }

    @Nullable
    public String a(@NonNull String str) {
        CryptoUtils a2 = a();
        if (a2 == null) {
            return null;
        }
        try {
            return a(a2.decodeDataFromServer(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String a(@NonNull byte[] bArr) {
        String str = new String(bArr, f20148c);
        if (str.startsWith("00000")) {
            return str.substring(5);
        }
        return null;
    }

    @Nullable
    public String b(@NonNull String str) {
        CryptoUtils a2 = a();
        if (a2 == null) {
            return null;
        }
        try {
            return a(a2.encodeDataToServer(str, System.currentTimeMillis()));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
