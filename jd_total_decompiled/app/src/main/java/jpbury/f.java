package jpbury;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* renamed from: e  reason: collision with root package name */
    public static final String f20113e = "-1";

    /* renamed from: f  reason: collision with root package name */
    public static final String f20114f = "deviceId";
    private final HashMap<String, String> a;
    private transient j b;

    /* renamed from: c  reason: collision with root package name */
    private String f20115c;
    private transient String d;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<f> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i2) {
            return new f[i2];
        }
    }

    private f() {
        this.f20115c = "-1";
        this.a = new HashMap<>();
    }

    public f(Parcel parcel) {
        this.f20115c = "-1";
        this.a = (HashMap) parcel.readSerializable();
        this.f20115c = parcel.readString();
    }

    public static f a() {
        return new f();
    }

    public String a(String str) {
        return this.a.get(str);
    }

    public void a(String str, String str2) {
        this.a.put(str, str2);
    }

    public void a(Map<String, String> map) {
        this.a.putAll(map);
    }

    public void a(k kVar) {
        this.b = kVar.a();
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String c() {
        return a("deviceId");
    }

    public void c(String str) {
        a("deviceId", str);
    }

    public j d() {
        if (this.b == null) {
            this.b = new i();
        }
        return this.b;
    }

    public void d(String str) {
        this.f20115c = str;
        this.a.put("uuid", str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HashMap<String, String> e() {
        return this.a;
    }

    public String f() {
        return this.f20115c;
    }

    public void g() {
        this.a.clear();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.a);
        parcel.writeString(this.f20115c);
    }
}
