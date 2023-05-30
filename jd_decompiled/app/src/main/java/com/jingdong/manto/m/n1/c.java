package com.jingdong.manto.m.n1;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class c extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public String f13456c;
    public String d;

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

    /* loaded from: classes15.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = c.this;
            com.jingdong.manto.t.b.a(cVar.f13456c, cVar.d);
        }
    }

    public c() {
    }

    public c(Parcel parcel) {
        a(parcel);
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.f13456c = parcel.readString();
        this.d = parcel.readString();
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
        com.jingdong.manto.b.d().diskIO().execute(new b());
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f13456c);
        parcel.writeString(this.d);
    }
}
