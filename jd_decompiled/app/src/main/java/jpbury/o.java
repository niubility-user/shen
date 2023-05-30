package jpbury;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.bury.proguard.SerializerFieldsKeep;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class o {
    @NonNull
    private String encrypt;

    private o(@NonNull String str) {
        this.encrypt = str;
    }

    @Nullable
    public static o a(@Nullable s sVar) {
        String str;
        String b;
        if (sVar == null) {
            return null;
        }
        try {
            str = x.a().toJson(sVar);
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        if (str == null || (b = w.b().b(str)) == null) {
            return null;
        }
        return new o(b);
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
