package jpbury;

import com.google.gson.annotations.JsonAdapter;
import com.jdpay.bury.IdExtension;

@JsonAdapter(u.class)
/* loaded from: classes11.dex */
public class t {

    /* renamed from: e  reason: collision with root package name */
    public static final String f20140e = "BURY_UPLOAD_ERROR";

    /* renamed from: f  reason: collision with root package name */
    public static final String f20141f = "page";

    /* renamed from: g  reason: collision with root package name */
    public static final String f20142g = "click";

    /* renamed from: h  reason: collision with root package name */
    public static final String f20143h = "method";

    /* renamed from: i  reason: collision with root package name */
    public static final String f20144i = "event";

    /* renamed from: j  reason: collision with root package name */
    public static final String f20145j = "exception";
    private final r a;
    private final IdExtension b;

    /* renamed from: c  reason: collision with root package name */
    private final transient int f20146c;
    private final transient boolean d;

    public t(String str, String str2, String str3, long j2, String str4, String str5, String str6, String str7, IdExtension idExtension, int i2, boolean z) {
        this.a = new r(str, str2, str3, j2, str4, str5, str6, str7);
        this.b = idExtension;
        this.f20146c = i2;
        this.d = z;
    }

    public r a() {
        return this.a;
    }

    public IdExtension b() {
        return this.b;
    }

    public int c() {
        return this.f20146c;
    }

    public boolean d() {
        return this.d;
    }
}
