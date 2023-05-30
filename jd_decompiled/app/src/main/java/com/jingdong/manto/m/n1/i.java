package com.jingdong.manto.m.n1;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.manto.t.b;
import com.jingdong.manto.utils.MantoLog;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public class i extends com.jingdong.manto.message.c {
    public static final Parcelable.Creator<i> CREATOR = new a();

    /* renamed from: c  reason: collision with root package name */
    public String f13466c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f13467e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f13468f;

    /* renamed from: g  reason: collision with root package name */
    private int f13469g;

    /* renamed from: h  reason: collision with root package name */
    private int f13470h;

    /* renamed from: i  reason: collision with root package name */
    public Runnable f13471i;

    /* renamed from: j  reason: collision with root package name */
    public String f13472j;

    /* renamed from: k  reason: collision with root package name */
    public String f13473k;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<i> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final i createFromParcel(Parcel parcel) {
            i iVar = new i();
            iVar.a(parcel);
            return iVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final i[] newArray(int i2) {
            return new i[i2];
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
            try {
                try {
                    i iVar = i.this;
                    Object[] a = com.jingdong.manto.t.b.a(iVar.d, iVar.f13466c, iVar.f13467e);
                    if (a[0] == b.a.NONE) {
                        if (com.jingdong.manto.t.b.a((String) a[1], (String) a[2]) > 102400) {
                            iVar.f13469g = com.jingdong.manto.t.b.a((String) a[1]);
                            iVar.f13470h = com.jingdong.manto.t.b.a((String) a[2]);
                            try {
                                p.a(iVar.b, (String) a[1], (String) a[2]);
                            } catch (Exception e2) {
                                MantoLog.e("JsApiGetStorageTask", e2.getMessage());
                            }
                            iVar.f13468f = true;
                        } else {
                            iVar.f13468f = false;
                            iVar.f13473k = (String) a[1];
                            iVar.f13472j = (String) a[2];
                        }
                    }
                } catch (Exception unused) {
                }
            } finally {
                this.a.countDown();
            }
        }
    }

    @Override // com.jingdong.manto.message.c
    public final void a(Parcel parcel) {
        this.d = parcel.readString();
        this.f13468f = parcel.readByte() != 0;
        this.f13469g = parcel.readInt();
        this.f13470h = parcel.readInt();
        this.f13466c = parcel.readString();
        this.f13473k = parcel.readString();
        this.f13472j = parcel.readString();
        this.f13467e = parcel.readString();
    }

    @Override // com.jingdong.manto.message.c
    public final void b() {
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
        super.c();
        if (this.f13468f) {
            try {
                String b2 = p.b(this.b);
                int length = b2.length();
                int i2 = this.f13469g;
                if (length == this.f13470h + i2) {
                    this.f13473k = b2.substring(0, i2);
                    int i3 = this.f13469g;
                    this.f13472j = b2.substring(i3, this.f13470h + i3);
                }
                p.a(this.b);
            } catch (Exception e2) {
                MantoLog.e("JsApiGetStorageTask", e2.getMessage());
            } catch (Throwable unused) {
                p.a(this.b);
            }
        }
        Runnable runnable = this.f13471i;
        if (runnable != null) {
            runnable.run();
        }
        a();
    }

    @Override // com.jingdong.manto.message.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeByte(this.f13468f ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f13469g);
        parcel.writeInt(this.f13470h);
        parcel.writeString(this.f13466c);
        parcel.writeString(this.f13473k);
        parcel.writeString(this.f13472j);
        parcel.writeString(this.f13467e);
    }
}
