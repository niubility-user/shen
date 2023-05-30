package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.sdk.threadpool.ThreadManager;

/* loaded from: classes15.dex */
public class j extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<j> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    private String f13271c;
    private c d;

    /* renamed from: e  reason: collision with root package name */
    private com.jingdong.manto.i.c f13272e;

    /* renamed from: f  reason: collision with root package name */
    private String f13273f;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<j> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final j createFromParcel(Parcel parcel) {
            return new j(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final j[] newArray(int i2) {
            return new j[i2];
        }
    }

    /* loaded from: classes15.dex */
    class b implements MantoPreLaunchProcess.b {
        b() {
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void a(com.jingdong.manto.i.c cVar) {
            j.this.f13272e = cVar;
            j.this.g();
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            j.this.f13272e = null;
            j.this.g();
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a(com.jingdong.manto.i.c cVar);
    }

    private j(Parcel parcel) {
        a(parcel);
    }

    /* synthetic */ j(Parcel parcel, a aVar) {
        this(parcel);
    }

    public j(String str, String str2, c cVar) {
        this.f13271c = str;
        this.f13273f = str2;
        this.d = cVar;
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.f13271c = parcel.readString();
        this.f13273f = parcel.readString();
        this.f13272e = (com.jingdong.manto.i.c) parcel.readParcelable(com.jingdong.manto.i.c.class.getClassLoader());
    }

    @Override // com.jingdong.manto.message.c
    public void b() {
        LaunchParam launchParam = new LaunchParam();
        launchParam.appId = this.f13271c;
        launchParam.debugType = this.f13273f;
        ThreadManager.heavy().post(new MantoPreLaunchProcess(launchParam, new b()));
    }

    @Override // com.jingdong.manto.message.c
    public final void c() {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(this.f13272e);
        }
        a();
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f13271c);
        parcel.writeString(this.f13273f);
        parcel.writeParcelable(this.f13272e, i2);
    }
}
