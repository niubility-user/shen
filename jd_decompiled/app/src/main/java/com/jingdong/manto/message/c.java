package com.jingdong.manto.message;

import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;

/* loaded from: classes15.dex */
public abstract class c implements Parcelable {
    Messenger a;
    public String b = String.valueOf(Process.myPid() + hashCode());

    public final void a() {
        b.b(this);
    }

    public void a(Parcel parcel) {
    }

    public abstract void b();

    public void c() {
    }

    public final void d() {
        b.b(this, true);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final void e() {
        b.b(this, false);
    }

    public final boolean f() {
        return b.c(this);
    }

    public final boolean g() {
        return MantoAcrossProcessMain.a(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }
}
