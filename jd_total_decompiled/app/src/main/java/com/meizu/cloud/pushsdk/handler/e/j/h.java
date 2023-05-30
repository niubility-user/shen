package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushinternal.DebugLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class h implements Parcelable {
    public static final Parcelable.Creator<h> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private b f15979g;

    /* renamed from: h  reason: collision with root package name */
    private String f15980h;

    /* renamed from: i  reason: collision with root package name */
    private int f15981i;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<h> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i2) {
            return new h[i2];
        }
    }

    protected h(Parcel parcel) {
        this.f15979g = (b) parcel.readParcelable(b.class.getClassLoader());
        this.f15980h = parcel.readString();
        this.f15981i = parcel.readInt();
    }

    public h(String str, String str2, String str3, String str4, String str5) {
        this.f15980h = str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(RemoteMessageConst.Notification.NOTIFY_ID)) {
                this.f15981i = jSONObject.getInt(RemoteMessageConst.Notification.NOTIFY_ID);
            }
        } catch (JSONException e2) {
            DebugLogger.e("WithDrawMessage", "parse WithDrawMessage error " + e2.getMessage());
        }
        this.f15979g = new b(str3, str4, str5);
    }

    public b a() {
        return this.f15979g;
    }

    public int b() {
        return this.f15981i;
    }

    public String c() {
        return this.f15980h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "WithDrawMessage{controlMessage=" + this.f15979g + ", revokePackageName='" + this.f15980h + "', notifyId=" + this.f15981i + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f15979g, i2);
        parcel.writeString(this.f15980h);
        parcel.writeInt(this.f15981i);
    }
}
