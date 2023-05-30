package c.t.m.g;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class k5 implements Parcelable {
    public static final Parcelable.Creator<k5> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    public String f528g;

    /* renamed from: h  reason: collision with root package name */
    public String f529h;

    /* renamed from: i  reason: collision with root package name */
    public String f530i;

    /* renamed from: j  reason: collision with root package name */
    public double f531j;

    /* renamed from: k  reason: collision with root package name */
    public double f532k;

    /* renamed from: l  reason: collision with root package name */
    public double f533l;

    /* renamed from: m  reason: collision with root package name */
    public String f534m;

    /* renamed from: n  reason: collision with root package name */
    public String f535n;

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<k5> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final k5 createFromParcel(Parcel parcel) {
            k5 k5Var = new k5();
            k5Var.f528g = parcel.readString();
            k5Var.f529h = parcel.readString();
            k5Var.f530i = parcel.readString();
            k5Var.f531j = parcel.readDouble();
            k5Var.f532k = parcel.readDouble();
            k5Var.f533l = parcel.readDouble();
            k5Var.f534m = parcel.readString();
            k5Var.f535n = parcel.readString();
            return k5Var;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final k5[] newArray(int i2) {
            return new k5[i2];
        }
    }

    public k5() {
    }

    public k5(JSONObject jSONObject) {
        this.f528g = jSONObject.optString("name");
        this.f529h = jSONObject.optString("dtype");
        this.f530i = jSONObject.optString(SignUpTable.TB_COLUMN_ADDR);
        this.f531j = jSONObject.optDouble("pointx");
        this.f532k = jSONObject.optDouble("pointy");
        this.f533l = jSONObject.optDouble(CartConstant.KEY_DIST);
        this.f534m = jSONObject.optString("direction");
        this.f535n = jSONObject.optString("tag");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "AddressData{name=" + this.f528g + DYConstants.DY_REGEX_COMMA + "dtype=" + this.f529h + DYConstants.DY_REGEX_COMMA + "pointx=" + this.f531j + DYConstants.DY_REGEX_COMMA + "pointy=" + this.f532k + DYConstants.DY_REGEX_COMMA + "dist=" + this.f533l + DYConstants.DY_REGEX_COMMA + "direction=" + this.f534m + DYConstants.DY_REGEX_COMMA + "tag=" + this.f535n + DYConstants.DY_REGEX_COMMA + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f528g);
        parcel.writeString(this.f529h);
        parcel.writeString(this.f530i);
        parcel.writeDouble(this.f531j);
        parcel.writeDouble(this.f532k);
        parcel.writeDouble(this.f533l);
        parcel.writeString(this.f534m);
        parcel.writeString(this.f535n);
    }
}
