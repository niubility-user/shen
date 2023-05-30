package com.jingdong.manto.i;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private static final b f13072g;
    public int a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public C0514b f13073c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f13074e;

    /* renamed from: f  reason: collision with root package name */
    public int f13075f;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final b[] newArray(int i2) {
            return new b[i2];
        }
    }

    /* renamed from: com.jingdong.manto.i.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static final class C0514b implements Parcelable {
        public static final Parcelable.Creator<C0514b> CREATOR = new a();
        public int a;
        public int b;

        /* renamed from: c  reason: collision with root package name */
        public String f13076c;
        public ArrayList<String> d;

        /* renamed from: e  reason: collision with root package name */
        public ArrayList<String> f13077e;

        /* renamed from: f  reason: collision with root package name */
        public int f13078f;

        /* renamed from: g  reason: collision with root package name */
        public int f13079g;

        /* renamed from: h  reason: collision with root package name */
        public int f13080h;

        /* renamed from: com.jingdong.manto.i.b$b$a */
        /* loaded from: classes15.dex */
        class a implements Parcelable.Creator<C0514b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final C0514b createFromParcel(Parcel parcel) {
                return new C0514b(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final C0514b[] newArray(int i2) {
                return new C0514b[i2];
            }
        }

        public C0514b() {
        }

        C0514b(Parcel parcel) {
            this.f13080h = parcel.readInt();
            this.d = parcel.createStringArrayList();
            this.f13077e = parcel.createStringArrayList();
            this.f13078f = parcel.readInt();
            this.f13079g = parcel.readInt();
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.f13076c = parcel.readString();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f13080h);
            parcel.writeStringList(this.d);
            parcel.writeStringList(this.f13077e);
            parcel.writeInt(this.f13078f);
            parcel.writeInt(this.f13079g);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeString(this.f13076c);
        }
    }

    static {
        b bVar = new b();
        f13072g = bVar;
        bVar.a = 10485760;
        bVar.b = 1048576;
        bVar.d = 314572800;
        bVar.f13074e = 50;
        bVar.f13075f = 60;
    }

    private b() {
    }

    protected b(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.f13073c = (C0514b) parcel.readParcelable(C0514b.class.getClassLoader());
        this.d = parcel.readInt();
        this.f13074e = parcel.readInt();
        this.f13075f = parcel.readInt();
    }

    public static b a() {
        return f13072g;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.f13073c, i2);
        parcel.writeInt(this.d);
        parcel.writeInt(this.f13074e);
        parcel.writeInt(this.f13075f);
    }
}
