package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.utils.MantoProcessUtil;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<g> CREATOR = new a();

    /* renamed from: c */
    LaunchParam f13253c;
    MantoPreLaunchProcess.LaunchError d;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<g> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public g createFromParcel(Parcel parcel) {
            g gVar = new g();
            gVar.a(parcel);
            return gVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public g[] newArray(int i2) {
            return new g[i2];
        }
    }

    public g() {
    }

    public g(LaunchParam launchParam) {
        this.f13253c = launchParam;
    }

    @Override // com.jingdong.manto.message.c
    public void a(Parcel parcel) {
        super.a(parcel);
        this.f13253c = (LaunchParam) parcel.readParcelable(LaunchParam.class.getClassLoader());
        this.d = (MantoPreLaunchProcess.LaunchError) parcel.readParcelable(MantoPreLaunchProcess.LaunchError.class.getClassLoader());
    }

    @Override // com.jingdong.manto.message.c
    public void b() {
        i.a(MantoProcessUtil.getContext(), this.f13253c, this);
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.f13253c, i2);
        parcel.writeParcelable(this.d, i2);
    }
}
