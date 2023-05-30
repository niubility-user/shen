package jpbury;

import com.jdpay.bury.proguard.SerializerFieldsKeep;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class e {
    private String code;
    private a data;
    private String desc;
    private boolean success;

    @SerializerFieldsKeep
    /* loaded from: classes11.dex */
    public static final class a {
        private int logLevel;
        private boolean needLog;

        public int a() {
            return this.logLevel;
        }

        public boolean b() {
            return this.needLog;
        }
    }

    public String a() {
        return this.code;
    }

    public a b() {
        return this.data;
    }

    public String c() {
        return this.desc;
    }

    public boolean d() {
        return this.success;
    }
}
