package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import com.meizu.cloud.pushsdk.handler.MessageV3;

/* loaded from: classes14.dex */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private MessageV3 f15959g;

    /* renamed from: h  reason: collision with root package name */
    private String f15960h;

    /* renamed from: i  reason: collision with root package name */
    private int f15961i;

    /* renamed from: j  reason: collision with root package name */
    private int f15962j;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<c> {
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

    protected c(Parcel parcel) {
        this.f15959g = (MessageV3) parcel.readParcelable(MessageV3.class.getClassLoader());
        this.f15960h = parcel.readString();
        this.f15961i = parcel.readInt();
        this.f15962j = parcel.readInt();
    }

    public c(MessageV3 messageV3) {
        this.f15959g = messageV3;
    }

    public MessageV3 a() {
        return this.f15959g;
    }

    public void b(int i2) {
        this.f15961i = i2;
    }

    public void c(String str) {
        this.f15960h = str;
    }

    public int d() {
        return this.f15961i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(int i2) {
        this.f15962j = i2;
    }

    public int f() {
        return this.f15962j;
    }

    public String toString() {
        return "NotificationState{messageV3=" + this.f15959g + ", notificationPkg='" + this.f15960h + "', notificationId='" + this.f15961i + "', state='" + this.f15962j + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f15959g, i2);
        parcel.writeString(this.f15960h);
        parcel.writeInt(this.f15961i);
        parcel.writeInt(this.f15962j);
    }
}
