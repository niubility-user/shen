package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0772a();

    /* renamed from: g  reason: collision with root package name */
    private int f15998g;

    /* renamed from: h  reason: collision with root package name */
    private String f15999h;

    /* renamed from: com.meizu.cloud.pushsdk.notification.model.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class C0772a implements Parcelable.Creator<a> {
        C0772a() {
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
        this.f15998g = 0;
    }

    protected a(Parcel parcel) {
        this.f15998g = 0;
        this.f15998g = parcel.readInt();
        this.f15999h = parcel.readString();
    }

    public static int b(MessageV3 messageV3) {
        a f2 = f(messageV3);
        if (f2 != null) {
            return f2.a();
        }
        return 0;
    }

    private static a c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return g(new JSONObject(str).getString("no"));
            }
        } catch (JSONException e2) {
            DebugLogger.e("NotifyOption", "parse notificationMessage error " + e2.getMessage());
        }
        return null;
    }

    public static a d(JSONObject jSONObject) {
        String str;
        a aVar = new a();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("ni")) {
                    aVar.e(jSONObject.getInt("ni"));
                }
                if (!jSONObject.isNull("nk")) {
                    aVar.i(jSONObject.getString("nk"));
                }
            } catch (JSONException e2) {
                str = "parse json obj error " + e2.getMessage();
            }
            return aVar;
        }
        str = "no such tag NotifyOption";
        DebugLogger.e("NotifyOption", str);
        return aVar;
    }

    public static a f(MessageV3 messageV3) {
        a c2;
        try {
            c2 = !TextUtils.isEmpty(messageV3.getNotificationMessage()) ? d(new JSONObject(messageV3.getNotificationMessage()).getJSONObject("data").getJSONObject("extra").getJSONObject("no")) : null;
        } catch (Exception e2) {
            DebugLogger.e("NotifyOption", "parse flyme NotifyOption setting error " + e2.getMessage() + " so get from notificationMessage");
            c2 = c(messageV3.getNotificationMessage());
        }
        DebugLogger.i("NotifyOption", "current notify option is " + c2);
        return c2;
    }

    public static a g(String str) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                DebugLogger.e("NotifyOption", "parse json string error " + e2.getMessage());
            }
            return d(jSONObject);
        }
        jSONObject = null;
        return d(jSONObject);
    }

    public int a() {
        return this.f15998g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(int i2) {
        this.f15998g = i2;
    }

    public String h() {
        return this.f15999h;
    }

    public void i(String str) {
        this.f15999h = str;
    }

    public String toString() {
        return "NotifyOption{notifyId=" + this.f15998g + ", notifyKey='" + this.f15999h + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f15998g);
        parcel.writeString(this.f15999h);
    }
}
