package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.sdk.jdupgrade.R;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b extends com.jingdong.sdk.jdupgrade.a.h.a {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* loaded from: classes7.dex */
    static class a implements Parcelable.Creator<b> {
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

    protected b() {
    }

    protected b(Parcel parcel) {
        super(parcel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(JSONObject jSONObject) {
        try {
            b bVar = new b();
            try {
                bVar.a = jSONObject.optString("type");
                bVar.b = jSONObject.optInt("interval", 0) * 60 * 1000;
                bVar.f15053c = jSONObject.optInt(VerifyTracker.KEY_TIMES, Integer.MAX_VALUE);
                JSONObject jSONObject2 = new JSONObject();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!next.equals("interval") && !next.equals(VerifyTracker.KEY_TIMES)) {
                        jSONObject2.put(next, jSONObject.opt(next));
                    }
                }
                bVar.d = jSONObject2.toString();
                bVar.f15054e = jSONObject.optString("title");
                bVar.f15055f = jSONObject.optString("subtitle");
                bVar.f15056g = jSONObject.optString("content");
                bVar.f15057h = jSONObject.optString("confirmButton", com.jingdong.sdk.jdupgrade.a.c.j().getResources().getString(R.string.upgrade_download));
                bVar.f15058i = jSONObject.optString("cancelButton", com.jingdong.sdk.jdupgrade.a.c.j().getResources().getString(R.string.upgrade_reject));
                return bVar;
            } catch (Throwable unused) {
                return bVar;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return b.class.hashCode();
    }

    public String toString() {
        return "Download{type='" + this.a + "', interval=" + this.b + ", times=" + this.f15053c + ", copyWriting='" + this.d + "', title='" + this.f15054e + "', subtitle='" + this.f15055f + "', content='" + this.f15056g + "', confirmButton='" + this.f15057h + "', cancelButton='" + this.f15058i + "'}";
    }
}
