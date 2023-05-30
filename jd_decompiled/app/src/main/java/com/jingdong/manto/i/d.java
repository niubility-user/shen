package com.jingdong.manto.i;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public String a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public String f13092c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public String f13093e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d() {
    }

    public d(Parcel parcel) {
        a(parcel);
    }

    public JSONObject a() {
        Object obj;
        try {
            obj = new JSONObject(this.f13093e);
        } catch (Exception unused) {
            obj = this.f13093e;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", this.a);
            jSONObject.put("extraData", obj);
        } catch (Exception unused2) {
        }
        if (jSONObject.length() == 0) {
            return null;
        }
        return jSONObject;
    }

    public void a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.f13092c = parcel.readString();
        this.d = parcel.readInt();
        this.f13093e = parcel.readString();
    }

    public final void a(d dVar) {
        if (dVar != null) {
            Parcel obtain = Parcel.obtain();
            obtain.setDataPosition(0);
            dVar.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            a(obtain);
            obtain.recycle();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MantoLaunchReferrer{launchScene=" + this.d + ", appId='" + this.a + "', extraData='" + this.f13093e + "', url='" + this.f13092c + "', sourceType='" + this.b + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeString(this.f13092c);
        parcel.writeInt(this.d);
        parcel.writeString(this.f13093e);
    }
}
