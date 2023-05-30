package com.jingdong.manto.m.n1;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<g> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public int f13461c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f13462e;

    /* renamed from: f  reason: collision with root package name */
    public Runnable f13463f;

    /* renamed from: g  reason: collision with root package name */
    public ArrayList<String> f13464g;

    /* renamed from: h  reason: collision with root package name */
    public int f13465h;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<g> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g createFromParcel(Parcel parcel) {
            g gVar = new g();
            gVar.a(parcel);
            return gVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final g[] newArray(int i2) {
            return new g[i2];
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        final /* synthetic */ CountDownLatch a;

        b(CountDownLatch countDownLatch) {
            this.a = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            g gVar = g.this;
            Object[] c2 = com.jingdong.manto.t.b.c(gVar.d, gVar.f13462e);
            g gVar2 = g.this;
            gVar2.f13464g = (ArrayList) c2[0];
            gVar2.f13465h = (int) Math.ceil(((Integer) c2[1]).doubleValue() / 1000.0d);
            g.this.f13461c = (int) Math.ceil(((Integer) c2[2]).doubleValue() / 1000.0d);
            this.a.countDown();
        }
    }

    @Override // com.jingdong.manto.message.c
    public void a(Parcel parcel) {
        this.f13461c = parcel.readInt();
        this.d = parcel.readString();
        this.f13464g = parcel.createStringArrayList();
        this.f13465h = parcel.readInt();
        this.f13462e = parcel.readString();
    }

    @Override // com.jingdong.manto.message.c
    public void b() {
        MantoLog.d("JsApiGetStorageInfoTask", "runInMain");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        com.jingdong.manto.b.d().diskIO().execute(new b(countDownLatch));
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            g();
        }
        g();
    }

    @Override // com.jingdong.manto.message.c
    public void c() {
        MantoLog.d("JsApiGetStorageInfoTask", "runInSub");
        a();
        Runnable runnable = this.f13463f;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f13461c);
        parcel.writeString(this.d);
        parcel.writeStringList(this.f13464g);
        parcel.writeInt(this.f13465h);
        parcel.writeString(this.f13462e);
    }
}
