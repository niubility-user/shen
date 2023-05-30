package jpbury;

import com.jdpay.bury.proguard.SerializerFieldsKeep;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class r {
    private final String ctp;
    private final String errorCode;
    private final String errorInfo;
    private final String eventContent;
    private final String eventId;
    private final long eventTime;
    private final String eventType;
    private final String stu;

    public r(String str, String str2, String str3, long j2, String str4, String str5, String str6, String str7) {
        this.eventType = str;
        this.ctp = str2;
        this.eventId = str3;
        this.eventTime = j2;
        this.eventContent = str4;
        this.stu = str5;
        this.errorCode = str6;
        this.errorInfo = str7;
    }

    public String a() {
        return this.ctp;
    }

    public String b() {
        return this.errorCode;
    }

    public String c() {
        return this.errorInfo;
    }

    public String d() {
        return this.eventContent;
    }

    public String e() {
        return this.eventId;
    }

    public long f() {
        return this.eventTime;
    }

    public String g() {
        return this.eventType;
    }

    public String h() {
        return this.stu;
    }
}
