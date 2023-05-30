package jpbury;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.jdpay.bury.DynamicValue;

/* loaded from: classes11.dex */
public class l implements Parcelable {
    public static final Parcelable.Creator<l> CREATOR = new a();
    public static final int d = 0;

    /* renamed from: e  reason: collision with root package name */
    public static final int f20137e = 1;
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f20138c;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<l> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public l[] newArray(int i2) {
            return new l[i2];
        }
    }

    public l(int i2, String str, String str2) {
        this.a = i2;
        this.b = str;
        this.f20138c = str2;
    }

    private l(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.f20138c = parcel.readString();
    }

    public /* synthetic */ l(Parcel parcel, a aVar) {
        this(parcel);
    }

    public String a() {
        return this.b;
    }

    @NonNull
    public String b() {
        int i2 = this.a;
        if (i2 != 0) {
            if (i2 == 1) {
                try {
                    return ((DynamicValue) Class.forName(this.f20138c).newInstance()).get();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return "";
        }
        return this.f20138c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f20138c);
    }
}
