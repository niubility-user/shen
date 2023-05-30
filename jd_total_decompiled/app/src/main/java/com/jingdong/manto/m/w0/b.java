package com.jingdong.manto.m.w0;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.manto.R;
import com.jingdong.manto.pkg.db.entity.AppCommonKVDataEntity;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes15.dex */
public class b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Toast.makeText(this.a, this.b, 0).show();
            ((Activity) this.a).finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.w0.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0631b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f13812c;
        final /* synthetic */ CountDownLatch d;

        RunnableC0631b(String str, String str2, AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
            this.a = str;
            this.b = str2;
            this.f13812c = atomicBoolean;
            this.d = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCommonKVDataEntity appCommonKVDataEntity = (AppCommonKVDataEntity) com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(AppCommonKVDataEntity.class, "appType=? AND key=?", new String[]{this.a, this.b + "_AppDebugEnabled"}, null);
            if (appCommonKVDataEntity != null) {
                this.f13812c.set(Boolean.parseBoolean(appCommonKVDataEntity.value));
            } else {
                this.f13812c.set(false);
            }
            this.d.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static class c extends com.jingdong.manto.message.c {
        public static final Parcelable.Creator<c> CREATOR = new C0632b();

        /* renamed from: c  reason: collision with root package name */
        public String f13813c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f13814e;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ AppCommonKVDataEntity b;

            a(c cVar, String str, AppCommonKVDataEntity appCommonKVDataEntity) {
                this.a = str;
                this.b = appCommonKVDataEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(this.a)) {
                    com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(this.b);
                } else {
                    com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(this.b);
                }
            }
        }

        /* renamed from: com.jingdong.manto.m.w0.b$c$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        static class C0632b implements Parcelable.Creator<c> {
            C0632b() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final c createFromParcel(Parcel parcel) {
                c cVar = new c(null);
                cVar.a(parcel);
                return cVar;
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public final c[] newArray(int i2) {
                return new c[i2];
            }
        }

        private c() {
        }

        /* synthetic */ c(a aVar) {
            this();
        }

        @Override // com.jingdong.manto.message.c
        public final void a(Parcel parcel) {
            this.f13813c = parcel.readString();
            this.f13814e = parcel.readByte() != 0;
            this.d = parcel.readString();
        }

        @Override // com.jingdong.manto.message.c
        public final void b() {
            String str = this.f13813c;
            String valueOf = String.valueOf(this.f13814e);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            com.jingdong.manto.b.d().diskIO().execute(new a(this, valueOf, new AppCommonKVDataEntity(str + "_AppDebugEnabled", valueOf, this.d)));
        }

        @Override // com.jingdong.manto.message.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f13813c);
            parcel.writeByte(this.f13814e ? (byte) 1 : (byte) 0);
            parcel.writeString(this.d);
        }
    }

    public static void a(Context context, String str, boolean z, String str2) {
        c cVar = new c(null);
        cVar.f13813c = str;
        cVar.f13814e = z;
        cVar.d = str2;
        cVar.e();
        String string = context.getString(cVar.f13814e ? R.string.manto_app_enable_debug : R.string.manto_app_disable_debug);
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new a(context, string));
        }
    }

    public static final boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if ("5" == str2) {
            return true;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        com.jingdong.manto.b.d().diskIO().execute(new RunnableC0631b(str2, str, atomicBoolean, countDownLatch));
        try {
            countDownLatch.await();
            return atomicBoolean.get();
        } catch (InterruptedException unused) {
            return false;
        }
    }
}
