package com.jingdong.manto.m.u0.o.k0.c0;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public com.jingdong.manto.m.u0.o.k0.c0.a a;
    public JSONObject b;

    /* renamed from: c */
    public int f13760c;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d() {
    }

    public d(Parcel parcel) {
        int readInt = parcel.readInt();
        this.f13760c = readInt;
        if (readInt == 1) {
            try {
                this.b = new JSONObject(parcel.readString());
            } catch (JSONException unused) {
            }
        } else if (readInt != 2) {
        } else {
            this.a = (com.jingdong.manto.m.u0.o.k0.c0.a) parcel.readParcelable(d.class.getClassLoader());
        }
    }

    public final String a() {
        int i2 = this.f13760c;
        return i2 != 1 ? i2 != 2 ? "" : this.a.a : this.b.optString("method");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("type %d ,method %s", Integer.valueOf(this.f13760c), a());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f13760c);
        int i3 = this.f13760c;
        if (i3 == 1) {
            parcel.writeString(this.b.toString());
        } else if (i3 != 2) {
        } else {
            parcel.writeParcelable(this.a, i2);
        }
    }
}
