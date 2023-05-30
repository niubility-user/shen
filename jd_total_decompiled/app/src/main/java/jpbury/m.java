package jpbury;

import androidx.annotation.NonNull;
import com.jdpay.bury.proguard.SerializerFieldsKeep;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class m {
    private p commonMap;
    private long timestamp;
    private String version = "1";
    private int channel = 0;

    private m(long j2, @NonNull f fVar) {
        this.timestamp = j2;
        this.commonMap = new p(fVar.d(), fVar.e(), -1);
    }

    public static m a(@NonNull f fVar) {
        return new m(System.currentTimeMillis(), fVar);
    }

    public p a() {
        return this.commonMap;
    }

    public long b() {
        return this.timestamp;
    }
}
