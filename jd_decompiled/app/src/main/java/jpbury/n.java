package jpbury;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.bury.proguard.SerializerFieldsKeep;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class n {
    @NonNull
    private String encrypt;
    private long timestamp;
    private String version = "1";
    private int channel = 0;

    private n(long j2, @NonNull String str) {
        this.timestamp = j2;
        this.encrypt = str;
    }

    @Nullable
    public static n a(@Nullable m mVar) {
        String str;
        String b;
        if (mVar == null) {
            return null;
        }
        try {
            str = x.a().toJson(mVar.a());
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        if (str == null || (b = w.b().b(str)) == null) {
            return null;
        }
        return new n(mVar.b(), b);
    }

    @Nullable
    public String a() {
        try {
            return x.a().toJson(this);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
