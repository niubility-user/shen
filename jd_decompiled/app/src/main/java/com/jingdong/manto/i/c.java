package com.jingdong.manto.i;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new a();
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f13081c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public String f13082e;

    /* renamed from: f  reason: collision with root package name */
    public String f13083f;

    /* renamed from: g  reason: collision with root package name */
    public d f13084g;

    /* renamed from: h  reason: collision with root package name */
    public PkgDetailEntity f13085h;

    /* renamed from: i  reason: collision with root package name */
    public UIConfig f13086i;

    /* renamed from: j  reason: collision with root package name */
    public PkgDetailEntity f13087j;

    /* renamed from: k  reason: collision with root package name */
    public String f13088k;

    /* renamed from: l  reason: collision with root package name */
    public String f13089l;

    /* renamed from: m  reason: collision with root package name */
    public String f13090m;

    /* renamed from: n  reason: collision with root package name */
    public String f13091n;
    public String o;
    public String p;
    public String q;
    public String r;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<c> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i2) {
            return new c[i2];
        }
    }

    public c() {
    }

    protected c(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.f13081c = parcel.readString();
        this.d = parcel.readInt();
        this.f13082e = parcel.readString();
        this.f13083f = parcel.readString();
        this.f13084g = (d) parcel.readParcelable(d.class.getClassLoader());
        this.f13085h = (PkgDetailEntity) parcel.readParcelable(PkgDetailEntity.class.getClassLoader());
        this.f13086i = (UIConfig) parcel.readParcelable(UIConfig.class.getClassLoader());
        this.f13087j = (PkgDetailEntity) parcel.readParcelable(PkgDetailEntity.class.getClassLoader());
        this.f13088k = parcel.readString();
        this.f13090m = parcel.readString();
        this.f13091n = parcel.readString();
        this.o = parcel.readString();
        this.f13089l = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
    }

    public String a(String str) {
        PkgDetailEntity pkgDetailEntity = this.f13085h;
        if (pkgDetailEntity == null || pkgDetailEntity.configJson == null) {
            return "";
        }
        try {
            return new JSONObject(this.f13085h.configJson).optString(str);
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(PkgDetailEntity pkgDetailEntity) {
        this.f13087j = pkgDetailEntity;
    }

    public boolean a() {
        PkgDetailEntity pkgDetailEntity = this.f13085h;
        if (pkgDetailEntity != null) {
            return pkgDetailEntity.isSwitchOpen(6);
        }
        return false;
    }

    public void b(PkgDetailEntity pkgDetailEntity) {
        this.f13085h = pkgDetailEntity;
        this.a = pkgDetailEntity.appId;
        this.b = pkgDetailEntity.name;
        this.f13081c = pkgDetailEntity.logo;
    }

    public boolean b() {
        return 4 == this.d;
    }

    public boolean c() {
        return this.f13085h != null ? "13".equals(this.f13082e) || "13".equals(this.f13085h.type) : "13".equals(this.f13082e);
    }

    public boolean d() {
        PkgDetailEntity pkgDetailEntity = this.f13085h;
        if (pkgDetailEntity != null && pkgDetailEntity.configJson != null) {
            try {
                return "1".equals(new JSONObject(this.f13085h.configJson).optString("sameTask", ""));
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        PkgDetailEntity pkgDetailEntity = this.f13085h;
        if (pkgDetailEntity != null) {
            return pkgDetailEntity.isSwitchOpen(7);
        }
        return false;
    }

    public boolean f() {
        PkgDetailEntity pkgDetailEntity = this.f13085h;
        if (pkgDetailEntity != null) {
            return pkgDetailEntity.isSwitchOpen(8);
        }
        return false;
    }

    public String toString() {
        return "MantoInitConfig{, appId='" + this.a + "', appName='" + this.b + "', iconUrl='" + this.f13081c + "', appType=" + this.d + ", debugType=" + this.f13082e + ", enterPath='" + this.f13083f + "', referrer=" + this.f13084g + ", detailEntity=" + this.f13085h + ", cachedPkgEntityBeforeLaunch=" + this.f13087j + ", extras=" + this.f13090m + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f13081c);
        parcel.writeInt(this.d);
        parcel.writeString(this.f13082e);
        parcel.writeString(this.f13083f);
        parcel.writeParcelable(this.f13084g, i2);
        parcel.writeParcelable(this.f13085h, i2);
        parcel.writeParcelable(this.f13086i, i2);
        parcel.writeParcelable(this.f13087j, i2);
        parcel.writeString(this.f13088k);
        parcel.writeString(this.f13090m);
        parcel.writeString(this.f13091n);
        parcel.writeString(this.o);
        parcel.writeString(this.f13089l);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
    }
}
