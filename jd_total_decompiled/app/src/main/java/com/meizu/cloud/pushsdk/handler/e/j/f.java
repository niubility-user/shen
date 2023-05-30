package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private String f15969g;

    /* renamed from: h  reason: collision with root package name */
    private String f15970h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f15971i;

    /* renamed from: j  reason: collision with root package name */
    private String f15972j;

    /* renamed from: k  reason: collision with root package name */
    private String f15973k;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<f> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i2) {
            return new f[i2];
        }
    }

    public f() {
        this.f15971i = false;
    }

    protected f(Parcel parcel) {
        this.f15971i = false;
        this.f15969g = parcel.readString();
        this.f15970h = parcel.readString();
        this.f15971i = parcel.readByte() != 0;
        this.f15972j = parcel.readString();
        this.f15973k = parcel.readString();
    }

    public static f a(JSONObject jSONObject) {
        String str;
        f fVar = new f();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(NotificationMessageSummary.TASK_ID)) {
                    fVar.h(jSONObject.getString(NotificationMessageSummary.TASK_ID));
                }
                if (!jSONObject.isNull("time")) {
                    fVar.j(jSONObject.getString("time"));
                }
                if (!jSONObject.isNull("pushExtra")) {
                    fVar.d(jSONObject.getInt("pushExtra") == 0);
                }
            } catch (JSONException e2) {
                str = " parse statics message error " + e2.getMessage();
            }
            return fVar;
        }
        str = "no control statics can parse ";
        DebugLogger.e("statics", str);
        return fVar;
    }

    public String b() {
        return this.f15972j;
    }

    public void c(String str) {
        this.f15972j = str;
    }

    public void d(boolean z) {
        this.f15971i = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(String str) {
        this.f15973k = str;
    }

    public boolean f() {
        return this.f15971i;
    }

    public String g() {
        return this.f15973k;
    }

    public void h(String str) {
        this.f15969g = str;
    }

    public String i() {
        return this.f15969g;
    }

    public void j(String str) {
        this.f15970h = str;
    }

    public String k() {
        return this.f15970h;
    }

    public String toString() {
        return "Statics{taskId='" + this.f15969g + "', time='" + this.f15970h + "', pushExtra=" + this.f15971i + ", deviceId='" + this.f15972j + "', seqId='" + this.f15973k + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f15969g);
        parcel.writeString(this.f15970h);
        parcel.writeByte(this.f15971i ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f15972j);
        parcel.writeString(this.f15973k);
    }
}
