package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0539a();
    public String a;
    public String b;

    /* renamed from: com.jingdong.manto.launch.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0539a implements Parcelable.Creator<a> {
        C0539a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public a() {
    }

    protected a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "AppAsyncUpdateEvent{eventName='" + this.a + "', eventData='" + this.b + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
