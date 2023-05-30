package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0767a();

    /* renamed from: g  reason: collision with root package name */
    private int f15953g;

    /* renamed from: h  reason: collision with root package name */
    private int f15954h;

    /* renamed from: i  reason: collision with root package name */
    private int f15955i;

    /* renamed from: com.meizu.cloud.pushsdk.handler.e.j.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class C0767a implements Parcelable.Creator<a> {
        C0767a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public a() {
    }

    protected a(Parcel parcel) {
        this.f15953g = parcel.readInt();
        this.f15954h = parcel.readInt();
        this.f15955i = parcel.readInt();
    }

    public static a b(JSONObject jSONObject) {
        String str;
        a aVar = new a();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("pushType")) {
                    aVar.e(jSONObject.getInt("pushType"));
                }
                if (!jSONObject.isNull("cached")) {
                    aVar.d(jSONObject.getInt("cached"));
                }
                if (!jSONObject.isNull("cacheNum")) {
                    aVar.c(jSONObject.getInt("cacheNum"));
                }
            } catch (JSONException e2) {
                str = " parse control message error " + e2.getMessage();
            }
            return aVar;
        }
        str = "no control message can parse ";
        DebugLogger.e("ctl", str);
        return aVar;
    }

    public int a() {
        return this.f15953g;
    }

    public void c(int i2) {
        this.f15955i = i2;
    }

    public void d(int i2) {
        this.f15954h = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(int i2) {
        this.f15953g = i2;
    }

    public String toString() {
        return "Control{pushType=" + this.f15953g + ", cached=" + this.f15954h + ", cacheNum=" + this.f15955i + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f15953g);
        parcel.writeInt(this.f15954h);
        parcel.writeInt(this.f15955i);
    }
}
