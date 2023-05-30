package com.jingdong.manto.i;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();
    public String a;
    public String b;

    /* renamed from: c */
    public boolean f13094c;
    public int d;

    /* renamed from: e */
    public int f13095e;

    /* renamed from: f */
    public int f13096f;

    /* renamed from: g */
    public int f13097g;

    /* renamed from: h */
    public int f13098h;

    /* renamed from: i */
    public int f13099i;

    /* renamed from: j */
    public boolean f13100j;

    /* renamed from: k */
    public ArrayList<String> f13101k;

    /* renamed from: l */
    public ArrayList<String> f13102l;

    /* renamed from: m */
    public ArrayList<String> f13103m;

    /* renamed from: n */
    public ArrayList<String> f13104n;
    public boolean o;
    public b p;
    public int q;
    public boolean r;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<e> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    }

    public e() {
        this.f13094c = false;
        this.d = 12;
        this.f13095e = 10;
        this.f13096f = 15;
        this.f13097g = 15;
        this.f13098h = 10;
        this.f13099i = 1;
        this.f13101k = new ArrayList<>();
        this.f13102l = new ArrayList<>();
        this.f13103m = new ArrayList<>();
        this.f13104n = new ArrayList<>();
        this.q = 1;
        this.b = BaseInfo.getDeviceModel();
    }

    protected e(Parcel parcel) {
        this.f13094c = false;
        this.d = 12;
        this.f13095e = 10;
        this.f13096f = 15;
        this.f13097g = 15;
        this.f13098h = 10;
        this.f13099i = 1;
        this.f13101k = new ArrayList<>();
        this.f13102l = new ArrayList<>();
        this.f13103m = new ArrayList<>();
        this.f13104n = new ArrayList<>();
        this.q = 1;
        this.f13094c = false;
        this.b = parcel.readString();
        this.a = parcel.readString();
        this.f13094c = parcel.readByte() != 0;
        this.d = parcel.readInt();
        this.f13095e = parcel.readInt();
        this.f13096f = parcel.readInt();
        this.f13097g = parcel.readInt();
        this.f13098h = parcel.readInt();
        this.f13099i = parcel.readInt();
        this.o = parcel.readByte() != 0;
        this.f13100j = parcel.readByte() != 0;
        this.f13101k = parcel.createStringArrayList();
        this.f13102l = parcel.createStringArrayList();
        this.f13103m = parcel.createStringArrayList();
        this.f13104n = parcel.createStringArrayList();
        this.p = (b) parcel.readParcelable(b.class.getClassLoader());
        this.r = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.b);
        parcel.writeString(this.a);
        parcel.writeByte(this.f13094c ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.d);
        parcel.writeInt(this.f13095e);
        parcel.writeInt(this.f13096f);
        parcel.writeInt(this.f13097g);
        parcel.writeInt(this.f13098h);
        parcel.writeInt(this.f13099i);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13100j ? (byte) 1 : (byte) 0);
        parcel.writeStringList(this.f13101k);
        parcel.writeStringList(this.f13102l);
        parcel.writeStringList(this.f13103m);
        parcel.writeStringList(this.f13104n);
        parcel.writeParcelable(this.p, i2);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
    }
}
