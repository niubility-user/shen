package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private String f15956g;

    /* renamed from: h  reason: collision with root package name */
    private com.meizu.cloud.pushsdk.handler.e.j.a f15957h;

    /* renamed from: i  reason: collision with root package name */
    private f f15958i;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i2) {
            return new b[i2];
        }
    }

    public b() {
    }

    protected b(Parcel parcel) {
        this.f15956g = parcel.readString();
        this.f15957h = (com.meizu.cloud.pushsdk.handler.e.j.a) parcel.readParcelable(com.meizu.cloud.pushsdk.handler.e.j.a.class.getClassLoader());
        this.f15958i = (f) parcel.readParcelable(f.class.getClassLoader());
    }

    public b(String str, String str2, String str3) {
        this.f15956g = str;
        if (TextUtils.isEmpty(str)) {
            this.f15957h = new com.meizu.cloud.pushsdk.handler.e.j.a();
            this.f15958i = new f();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f15957h = com.meizu.cloud.pushsdk.handler.e.j.a.b(jSONObject.getJSONObject("ctl"));
            f a2 = f.a(jSONObject.getJSONObject("statics"));
            this.f15958i = a2;
            a2.c(str2);
            this.f15958i.e(str3);
        } catch (JSONException e2) {
            this.f15957h = new com.meizu.cloud.pushsdk.handler.e.j.a();
            this.f15958i = new f();
            DebugLogger.e("ControlMessage", "parse control message error " + e2.getMessage());
        }
    }

    public static b b(String str) {
        b bVar = new b();
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar.c(com.meizu.cloud.pushsdk.handler.e.j.a.b(jSONObject.getJSONObject("ctl")));
            bVar.d(f.a(jSONObject.getJSONObject("statics")));
        } catch (Exception e2) {
            DebugLogger.e("ControlMessage", "parse control message error " + e2.getMessage());
            bVar.d(new f());
            bVar.c(new com.meizu.cloud.pushsdk.handler.e.j.a());
        }
        return bVar;
    }

    public com.meizu.cloud.pushsdk.handler.e.j.a a() {
        return this.f15957h;
    }

    public void c(com.meizu.cloud.pushsdk.handler.e.j.a aVar) {
        this.f15957h = aVar;
    }

    public void d(f fVar) {
        this.f15958i = fVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public f e() {
        return this.f15958i;
    }

    public String toString() {
        return "ControlMessage{controlMessage='" + this.f15956g + "', control=" + this.f15957h + ", statics=" + this.f15958i + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f15956g);
        parcel.writeParcelable(this.f15957h, i2);
        parcel.writeParcelable(this.f15958i, i2);
    }
}
