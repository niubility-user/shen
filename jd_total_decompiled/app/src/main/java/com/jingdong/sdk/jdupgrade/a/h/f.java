package com.jingdong.sdk.jdupgrade.a.h;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.sdk.jdupgrade.a.j.h;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();
    public e a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public d f15070c;
    public com.jingdong.sdk.jdupgrade.a.h.b d;

    /* renamed from: e  reason: collision with root package name */
    public c f15071e;

    /* renamed from: f  reason: collision with root package name */
    public String f15072f;

    /* renamed from: g  reason: collision with root package name */
    public String f15073g;

    /* renamed from: h  reason: collision with root package name */
    public String f15074h;

    /* renamed from: i  reason: collision with root package name */
    public String f15075i;

    /* renamed from: j  reason: collision with root package name */
    public String f15076j;

    /* loaded from: classes7.dex */
    static class a implements Parcelable.Creator<f> {
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

    /* loaded from: classes7.dex */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.values().length];
            a = iArr;
            try {
                iArr[e.UPGRADE_GRAYSCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.UPGRADE_ORDINARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[e.UPGRADE_FORCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[e.UPDATE_NO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[e.UPGRADE_SIGN_FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[e.UPGRADE_PARAM_ILLEGAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[e.UPGRADE_CLIENT_ILLEGAL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[e.UPGRADE_APP_KEY_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private f() {
    }

    protected f(Parcel parcel) {
        this.a = (e) parcel.readParcelable(e.class.getClassLoader());
        this.b = parcel.readString();
        this.f15073g = parcel.readString();
        this.f15074h = parcel.readString();
        this.f15070c = (d) parcel.readParcelable(d.class.getClassLoader());
        this.d = (com.jingdong.sdk.jdupgrade.a.h.b) parcel.readParcelable(com.jingdong.sdk.jdupgrade.a.h.b.class.getClassLoader());
        this.f15071e = (c) parcel.readParcelable(c.class.getClassLoader());
        this.f15072f = parcel.readString();
        this.f15075i = parcel.readString();
        this.f15076j = parcel.readString();
    }

    public static f a(String str) {
        if (TextUtils.isEmpty(str)) {
            h.c("UpgradeInfo", ">>>>> Json data is empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(XView2Constants.STATE);
            if (TextUtils.isEmpty(optString)) {
                h.c("UpgradeInfo", "state is empty");
                return null;
            }
            e a2 = e.a(optString);
            switch (b.a[a2.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    f fVar = new f();
                    fVar.a = a2;
                    fVar.f15076j = jSONObject.optString("extreme");
                    fVar.f15075i = jSONObject.optString(VerifyTracker.KEY_TIMESTAMP);
                    JSONArray optJSONArray = jSONObject.optJSONArray(NavigatorHolder.NaviEntity.TYPE_CUSTOM);
                    if (optJSONArray != null) {
                        fVar.f15073g = optJSONArray.toString();
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("customize");
                    if (optJSONObject != null) {
                        fVar.f15074h = optJSONObject.toString();
                    }
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("package");
                    JSONObject optJSONObject3 = jSONObject.optJSONObject(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD);
                    JSONObject optJSONObject4 = jSONObject.optJSONObject("install");
                    if (optJSONObject2 != null && optJSONObject3 != null && optJSONObject4 != null) {
                        fVar.f15070c = d.a(optJSONObject2);
                        fVar.d = com.jingdong.sdk.jdupgrade.a.h.b.a(optJSONObject3);
                        fVar.f15071e = c.a(optJSONObject4);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("version", fVar.f15070c.a);
                        jSONObject2.put(HybridSDK.APP_VERSION_CODE, fVar.f15070c.b);
                        jSONObject2.put(ApkDownloadTable.FIELD_SIZE, fVar.f15070c.d);
                        fVar.f15072f = jSONObject2.toString();
                        return fVar;
                    }
                    return null;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    f fVar2 = new f();
                    fVar2.a = a2;
                    fVar2.b = jSONObject.optString("message");
                    return fVar2;
                default:
                    return null;
            }
        } catch (Throwable th) {
            h.a("UpgradeInfo", ">>>>> parse json fail");
            th.printStackTrace();
            return null;
        }
    }

    public boolean a() {
        return b() && this.a.equals(e.UPGRADE_FORCE);
    }

    public boolean b() {
        d dVar;
        return this.a.compareTo(e.UPDATE_NO) > 0 && this.a.compareTo(e.UPGRADE_SIGN_FAILURE) < 0 && (dVar = this.f15070c) != null && dVar.a() && this.d != null && this.f15071e != null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return f.class.hashCode();
    }

    public String toString() {
        return "UpgradeInfo{state=" + this.a + ", message='" + this.b + "', packageEntity=" + this.f15070c + ", downloadEntity=" + this.d + ", installEntity=" + this.f15071e + ", packageCopyWriting='" + this.f15072f + "', custom='" + this.f15073g + "', customize='" + this.f15074h + "', timestamp='" + this.f15075i + "', extreme='" + this.f15076j + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.a, i2);
        parcel.writeString(this.b);
        parcel.writeString(this.f15073g);
        parcel.writeString(this.f15074h);
        parcel.writeParcelable(this.f15070c, i2);
        parcel.writeParcelable(this.d, i2);
        parcel.writeParcelable(this.f15071e, i2);
        parcel.writeString(this.f15072f);
        parcel.writeString(this.f15075i);
        parcel.writeString(this.f15076j);
    }
}
