package com.jingdong.manto.m.n1;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.t.b;
import com.jingdong.manto.utils.MantoLog;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public class o extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<o> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    private String f13478c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f13479e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f13480f;

    /* renamed from: g  reason: collision with root package name */
    private int f13481g;

    /* renamed from: h  reason: collision with root package name */
    private int f13482h;

    /* renamed from: i  reason: collision with root package name */
    private int f13483i;

    /* renamed from: j  reason: collision with root package name */
    public Runnable f13484j;

    /* renamed from: k  reason: collision with root package name */
    public String f13485k;

    /* renamed from: l  reason: collision with root package name */
    private String f13486l;

    /* renamed from: m  reason: collision with root package name */
    private String f13487m;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<o> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final o createFromParcel(Parcel parcel) {
            o oVar = new o();
            oVar.a(parcel);
            return oVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final o[] newArray(int i2) {
            return new o[i2];
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                o.this.h();
                o.this.g();
            }
        }

        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Executor mainThread;
            a aVar;
            b.a a2;
            String str;
            o oVar = o.this;
            try {
                try {
                    a2 = com.jingdong.manto.t.b.a(oVar.d, oVar.f13478c, oVar.f13487m, oVar.f13486l, oVar.f13479e);
                } catch (Exception unused) {
                    oVar.f13485k = "fail";
                    mainThread = com.jingdong.manto.b.d().mainThread();
                    aVar = new a();
                }
                if (a2 == b.a.NONE) {
                    str = IMantoBaseModule.SUCCESS;
                } else if (a2 != b.a.QUOTA_REACHED) {
                    oVar.f13485k = "fail";
                    mainThread = com.jingdong.manto.b.d().mainThread();
                    aVar = new a();
                    mainThread.execute(aVar);
                } else {
                    str = "fail:quota reached";
                }
                oVar.f13485k = str;
                mainThread = com.jingdong.manto.b.d().mainThread();
                aVar = new a();
                mainThread.execute(aVar);
            } catch (Throwable th) {
                com.jingdong.manto.b.d().mainThread().execute(new a());
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f13478c = null;
        this.f13487m = null;
        this.f13486l = null;
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.d = parcel.readString();
        this.f13480f = parcel.readByte() != 0;
        this.f13481g = parcel.readInt();
        this.f13482h = parcel.readInt();
        this.f13483i = parcel.readInt();
        this.f13478c = parcel.readString();
        this.f13487m = parcel.readString();
        this.f13486l = parcel.readString();
        this.f13485k = parcel.readString();
        this.f13479e = parcel.readString();
    }

    public final void a(String str, String str2, String str3) {
        if (p.a(str, str2, str3) <= 102400) {
            this.f13480f = false;
            this.f13478c = str;
            this.f13487m = str2;
            this.f13486l = str3;
            return;
        }
        this.f13481g = p.a(str);
        this.f13482h = p.a(str2);
        this.f13483i = p.a(str3);
        try {
            p.a(this.b, str, str2, str3);
        } catch (Exception e2) {
            MantoLog.e("JsApiSetStorageTask", e2.getMessage());
        }
        this.f13480f = true;
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
        if (this.f13480f) {
            try {
                String b2 = p.b(this.b);
                int length = b2.length();
                int i2 = this.f13481g;
                if (length == this.f13482h + i2 + this.f13483i) {
                    this.f13478c = b2.substring(0, i2);
                    int i3 = this.f13481g;
                    this.f13487m = b2.substring(i3, this.f13482h + i3);
                    int i4 = this.f13481g + this.f13482h;
                    this.f13486l = b2.substring(i4, this.f13483i + i4);
                }
                p.a(this.b);
            } catch (Exception e2) {
                p.a(this.b);
                MantoLog.e("JsApiSetStorageTask", "" + e2);
            }
        }
        com.jingdong.manto.b.d().diskIO().execute(new b());
    }

    @Override // com.jingdong.manto.message.c
    public void c() {
        Runnable runnable = this.f13484j;
        if (runnable != null) {
            runnable.run();
        }
        a();
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeByte(this.f13480f ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f13481g);
        parcel.writeInt(this.f13482h);
        parcel.writeInt(this.f13483i);
        parcel.writeString(this.f13478c);
        parcel.writeString(this.f13487m);
        parcel.writeString(this.f13486l);
        parcel.writeString(this.f13485k);
        parcel.writeString(this.f13479e);
    }
}
