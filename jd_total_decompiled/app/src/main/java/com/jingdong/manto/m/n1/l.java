package com.jingdong.manto.m.n1;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class l extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<l> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public String f13474c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f13475e;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<l> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final l createFromParcel(Parcel parcel) {
            l lVar = new l();
            lVar.a(parcel);
            return lVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final l[] newArray(int i2) {
            return new l[i2];
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = l.this;
            com.jingdong.manto.t.b.b(lVar.d, lVar.f13474c, lVar.f13475e);
        }
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.d = parcel.readString();
        this.f13474c = parcel.readString();
        this.f13475e = parcel.readString();
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
        com.jingdong.manto.b.d().diskIO().execute(new b());
    }

    @Override // com.jingdong.manto.message.c
    public void c() {
        super.c();
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.f13474c);
        parcel.writeString(this.f13475e);
    }
}
