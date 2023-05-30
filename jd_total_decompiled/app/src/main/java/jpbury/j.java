package jpbury;

import android.os.Parcel;
import android.os.Parcelable;
import com.jdpay.bury.DynamicValue;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes11.dex */
public class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new a();
    private final transient ArrayList<l> a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private transient String f20133c;
    private transient String d;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<j> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public j[] newArray(int i2) {
            return new j[i2];
        }
    }

    private j(Parcel parcel) {
        this.a = parcel.createTypedArrayList(l.CREATOR);
        this.b = parcel.readString();
    }

    public /* synthetic */ j(Parcel parcel, a aVar) {
        this(parcel);
    }

    public j(String str) {
        this.b = str;
        this.a = new ArrayList<>();
    }

    public String a() {
        return this.f20133c;
    }

    public void a(String str) {
        a("appName", str);
        this.f20133c = str;
    }

    public void a(String str, Class<? extends DynamicValue> cls) {
        this.a.add(new l(1, str, cls.getName()));
    }

    public void a(String str, String str2) {
        this.a.add(new l(0, str, str2));
    }

    public void a(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            a(entry.getKey(), entry.getValue());
        }
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        a("appVersion", str);
        this.d = str;
    }

    public ArrayList<l> c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.a);
        parcel.writeString(this.b);
    }
}
