package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.sdk.jdupgrade.R;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c extends com.jingdong.sdk.jdupgrade.a.h.a {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* loaded from: classes7.dex */
    static class a implements Parcelable.Creator<c> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i2) {
            return new c[i2];
        }
    }

    protected c() {
    }

    protected c(Parcel parcel) {
        super(parcel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c a(JSONObject jSONObject) {
        try {
            c cVar = new c();
            cVar.a = jSONObject.optString("type");
            cVar.b = jSONObject.optInt("interval", 0) * 60 * 1000;
            cVar.f15053c = jSONObject.optInt(VerifyTracker.KEY_TIMES, Integer.MAX_VALUE);
            JSONObject jSONObject2 = new JSONObject();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!next.equals("interval") && !next.equals(VerifyTracker.KEY_TIMES)) {
                    jSONObject2.put(next, jSONObject.opt(next));
                }
            }
            cVar.d = jSONObject2.toString();
            cVar.f15054e = jSONObject.optString("title");
            cVar.f15055f = jSONObject.optString("subtitle");
            cVar.f15056g = jSONObject.optString("content");
            cVar.f15057h = jSONObject.optString("confirmButton", com.jingdong.sdk.jdupgrade.a.c.j().getResources().getString(R.string.install_confirm));
            cVar.f15058i = jSONObject.optString("cancelButton", com.jingdong.sdk.jdupgrade.a.c.j().getResources().getString(R.string.upgrade_reject));
            return cVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return c.class.hashCode();
    }

    public String toString() {
        return "Install{type='" + this.a + "', interval=" + this.b + ", times=" + this.f15053c + ", copyWriting='" + this.d + "', title='" + this.f15054e + "', subtitle='" + this.f15055f + "', content='" + this.f15056g + "', confirmButton='" + this.f15057h + "', cancelButton='" + this.f15058i + "'}";
    }
}
