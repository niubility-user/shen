package com.jingdong.manto.n;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class f extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* renamed from: e  reason: collision with root package name */
    public String f13869e;

    /* renamed from: f  reason: collision with root package name */
    public String f13870f;

    /* renamed from: h  reason: collision with root package name */
    public int f13872h;

    /* renamed from: i  reason: collision with root package name */
    public String f13873i;

    /* renamed from: j  reason: collision with root package name */
    public int f13874j;

    /* renamed from: k  reason: collision with root package name */
    public int f13875k;

    /* renamed from: c  reason: collision with root package name */
    public c f13868c = null;
    public b d = null;

    /* renamed from: g  reason: collision with root package name */
    public int f13871g = 0;

    /* loaded from: classes16.dex */
    class a implements Parcelable.Creator<f> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f createFromParcel(Parcel parcel) {
            f fVar = new f();
            fVar.a(parcel);
            return fVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final f[] newArray(int i2) {
            return new f[i2];
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a();
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    public final void a(int i2) {
        MantoLog.d("MantoMPStatusWorker", "notifyBeginPreload " + i2 + "(" + hashCode() + ")");
        this.f13871g = 4;
        this.f13874j = i2;
        d();
    }

    public void a(int i2, int i3, String str, c cVar, b bVar) {
        MantoLog.d("MantoMPStatusWorker", "init taskType " + i3 + ", clsName " + str + ", hashCode " + i2 + "(" + hashCode() + ")");
        this.f13868c = cVar;
        this.f13870f = str;
        this.f13874j = i3;
        this.f13875k = i2;
        this.d = bVar;
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.f13873i = parcel.readString();
        this.f13869e = parcel.readString();
        this.f13870f = parcel.readString();
        this.f13871g = parcel.readInt();
        this.f13872h = parcel.readInt();
        this.f13874j = parcel.readInt();
        this.f13875k = parcel.readInt();
    }

    public final void a(String str) {
        MantoLog.d("MantoMPStatusWorker", "releasePreOne " + str + "(" + hashCode() + ")");
        this.f13871g = 2;
        this.f13873i = str;
        d();
    }

    public final void a(String str, String str2) {
        MantoLog.d("MantoMPStatusWorker", "saveControllerInMainProcess " + str + ", taskType " + this.f13874j + "(" + hashCode() + ")");
        this.f13871g = 1;
        this.f13873i = str;
        this.f13869e = str2;
        d();
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
        MantoLog.e("MantoMPStatusWorker", "runInMain:" + this.f13871g + ", appType=" + this.f13872h + ", appId=" + this.f13873i + "(" + hashCode() + ")");
        int i2 = this.f13871g;
        if (i2 == 1) {
            com.jingdong.manto.n.c.a(this);
        } else if (i2 == 2) {
            com.jingdong.manto.n.c.b(this.f13873i);
        } else if (i2 == 3) {
            com.jingdong.manto.n.c.c(this);
        } else if (i2 != 4) {
        } else {
            com.jingdong.manto.n.c.b(this);
        }
    }

    public final void b(String str) {
        MantoLog.d("MantoMPStatusWorker", "releaseRecord " + str);
        this.f13871g = 3;
        this.f13873i = str;
        d();
    }

    @Override // com.jingdong.manto.message.c
    public final void c() {
        b bVar;
        MantoLog.e("MantoMPStatusWorker", "runInSub: " + this.f13871g + ", appType=" + this.f13872h + ", appId=" + this.f13873i + "(" + hashCode() + ")");
        int i2 = this.f13871g;
        if (i2 == 100) {
            c cVar = this.f13868c;
            if (cVar != null) {
                cVar.a();
            }
        } else if (i2 == 101 && (bVar = this.d) != null) {
            bVar.a();
            if (this.f13874j == 0 || this.f13872h == 1) {
                Process.killProcess(Process.myPid());
            }
        }
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f13873i);
        parcel.writeString(this.f13869e);
        parcel.writeString(this.f13870f);
        parcel.writeInt(this.f13871g);
        parcel.writeInt(this.f13872h);
        parcel.writeInt(this.f13874j);
        parcel.writeInt(this.f13875k);
    }
}
