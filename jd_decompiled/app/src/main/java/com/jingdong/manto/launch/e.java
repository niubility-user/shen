package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class e extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<e> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    LaunchParam f13251c;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<e> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    }

    protected e(Parcel parcel) {
        this.f13251c = (LaunchParam) parcel.readParcelable(LaunchParam.class.getClassLoader());
    }

    public e(LaunchParam launchParam) {
        this.f13251c = launchParam;
    }

    @Override // com.jingdong.manto.message.c
    public void b() {
        com.jingdong.manto.n.c.b(this.f13251c.appId);
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.f13251c, i2);
    }
}
