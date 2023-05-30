package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f15059c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f15060e;

    /* renamed from: f  reason: collision with root package name */
    public String f15061f;

    /* renamed from: g  reason: collision with root package name */
    public String f15062g;

    /* loaded from: classes7.dex */
    static class a implements Parcelable.Creator<d> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    private d() {
    }

    protected d(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.f15059c = parcel.readString();
        this.d = parcel.readString();
        this.f15060e = parcel.readString();
        this.f15061f = parcel.readString();
        this.f15062g = parcel.readString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(JSONObject jSONObject) {
        try {
            d dVar = new d();
            dVar.a = jSONObject.optString("version");
            dVar.b = jSONObject.optString(HybridSDK.APP_VERSION_CODE);
            dVar.f15059c = jSONObject.optString("url");
            dVar.d = jSONObject.optString(ApkDownloadTable.FIELD_SIZE);
            dVar.f15060e = jSONObject.optString("md5");
            dVar.f15061f = jSONObject.optString("sign");
            dVar.f15062g = jSONObject.optString("s1");
            return dVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean a() {
        return (TextUtils.isEmpty(this.a) || !TextUtils.isDigitsOnly(this.b) || TextUtils.isEmpty(this.f15059c) || !TextUtils.isDigitsOnly(this.d) || Integer.valueOf(this.d).intValue() <= 0 || TextUtils.isEmpty(this.f15060e) || TextUtils.isEmpty(this.f15061f)) ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Package{version='" + this.a + "', build='" + this.b + "', url='" + this.f15059c + "', size='" + this.d + "', md5='" + this.f15060e + "', sign='" + this.f15061f + "', s1='" + this.f15062g + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f15059c);
        parcel.writeString(this.d);
        parcel.writeString(this.f15060e);
        parcel.writeString(this.f15061f);
        parcel.writeString(this.f15062g);
    }
}
