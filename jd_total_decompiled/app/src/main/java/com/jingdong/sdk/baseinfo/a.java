package com.jingdong.sdk.baseinfo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.room.Room;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jingdong.sdk.baseinfo.db.BaseInfoDB;
import com.jingdong.sdk.baseinfo.db.PrivacyInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a {
    private static BaseInfoDB a;
    private static com.jingdong.sdk.baseinfo.db.b b;

    /* renamed from: c  reason: collision with root package name */
    private static Context f14676c;
    private static ExecutorService d;

    /* renamed from: e  reason: collision with root package name */
    private static Handler f14677e;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f14678f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final long j2, final long j3, final ResultCallback<List<PrivacyInfo>> resultCallback, final boolean z) {
        d.execute(new Runnable() { // from class: com.jingdong.sdk.baseinfo.c
            @Override // java.lang.Runnable
            public final void run() {
                a.b(j2, j3, resultCallback, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (f14678f) {
                return;
            }
            if (context == null) {
                throw new NullPointerException("null context");
            }
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            f14676c = context;
            BaseInfoDB baseInfoDB = (BaseInfoDB) Room.databaseBuilder(f14676c, BaseInfoDB.class, "baseinfo_db").build();
            a = baseInfoDB;
            b = baseInfoDB.a();
            d = Executors.newFixedThreadPool(2);
            f14677e = new Handler(Looper.getMainLooper());
            f14678f = true;
        }
    }

    private static void a(final ResultCallback resultCallback, final Object obj, boolean z) {
        if (z) {
            f14677e.post(new Runnable() { // from class: com.jingdong.sdk.baseinfo.g
                @Override // java.lang.Runnable
                public final void run() {
                    ResultCallback.this.onSucceed(obj);
                }
            });
        } else {
            resultCallback.onSucceed(obj);
        }
    }

    private static void a(final ResultCallback resultCallback, final Throwable th, boolean z) {
        if (z) {
            f14677e.post(new Runnable() { // from class: com.jingdong.sdk.baseinfo.d
                @Override // java.lang.Runnable
                public final void run() {
                    ResultCallback.this.onFailed(th);
                }
            });
        } else {
            resultCallback.onFailed(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final ResultCallback<List<PrivacyInfo>> resultCallback, final boolean z) {
        d.execute(new Runnable() { // from class: com.jingdong.sdk.baseinfo.h
            @Override // java.lang.Runnable
            public final void run() {
                a.b(ResultCallback.this, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, String str2, PrivacyInfo privacyInfo) {
        try {
            Logger.d("BaseInfoDBHelper", "insertPrivacyInfo: " + str + ", " + str2);
            b.a(privacyInfo);
        } catch (Throwable th) {
            Logger.e("BaseInfoDBHelper", "insertPrivacyInfo failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final String str, final String str2, String str3, String str4, long j2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        final PrivacyInfo privacyInfo = new PrivacyInfo();
        privacyInfo.key = str;
        privacyInfo.name = str2;
        privacyInfo.value = com.jingdong.sdk.baseinfo.c.c.a(str3);
        privacyInfo.pin = str4;
        privacyInfo.timestamp = j2;
        d.execute(new Runnable() { // from class: com.jingdong.sdk.baseinfo.f
            @Override // java.lang.Runnable
            public final void run() {
                a.a(str, str2, privacyInfo);
            }
        });
    }

    private static void a(List<PrivacyInfo> list) {
        try {
            for (PrivacyInfo privacyInfo : list) {
                if (!TextUtils.isEmpty(privacyInfo.value) && !TextUtils.isEmpty(privacyInfo.value)) {
                    privacyInfo.value = com.jingdong.sdk.baseinfo.c.c.c(privacyInfo.value);
                }
            }
            if (0 != 0) {
                Logger.e("BaseInfoDBHelper", "failedCount=".concat(String.valueOf(0)));
            }
        } catch (Throwable th) {
            Logger.e("BaseInfoDBHelper", "", th);
        }
    }

    private static void a(PrivacyInfo[] privacyInfoArr) {
        try {
            int i2 = 0;
            for (PrivacyInfo privacyInfo : privacyInfoArr) {
                if (!TextUtils.isEmpty(privacyInfo.value)) {
                    try {
                        privacyInfo.value = com.jingdong.sdk.baseinfo.c.c.a(privacyInfo.value);
                    } catch (Throwable unused) {
                        i2++;
                    }
                }
            }
            if (i2 != 0) {
                Logger.e("BaseInfoDBHelper", "failedCount=".concat(String.valueOf(i2)));
            }
        } catch (Throwable th) {
            Logger.e("BaseInfoDBHelper", "", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final PrivacyInfo[] privacyInfoArr, final ResultCallback<Integer> resultCallback, final boolean z) {
        d.execute(new Runnable() { // from class: com.jingdong.sdk.baseinfo.e
            @Override // java.lang.Runnable
            public final void run() {
                a.b(privacyInfoArr, resultCallback, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(long j2, long j3, ResultCallback resultCallback, boolean z) {
        List<PrivacyInfo> a2;
        try {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            do {
                a2 = b.a(j2, j3, i2);
                a(a2);
                arrayList.addAll(a2);
                i2 += 500;
            } while (a2.size() == 500);
            if (resultCallback != null) {
                a(resultCallback, arrayList, z);
            }
        } catch (Throwable th) {
            if (resultCallback != null) {
                a(resultCallback, th, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(ResultCallback resultCallback, boolean z) {
        List<PrivacyInfo> a2;
        try {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            do {
                a2 = b.a(i2);
                a(a2);
                arrayList.addAll(a2);
                i2 += 500;
            } while (a2.size() == 500);
            if (resultCallback != null) {
                a(resultCallback, arrayList, z);
            }
        } catch (Throwable th) {
            if (resultCallback != null) {
                a(resultCallback, th, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(PrivacyInfo[] privacyInfoArr, ResultCallback resultCallback, boolean z) {
        try {
            Logger.d("BaseInfoDBHelper", "delete " + privacyInfoArr.length + " PrivacyInfos");
            a(privacyInfoArr);
            int b2 = b.b(privacyInfoArr);
            if (resultCallback != null) {
                a(resultCallback, Integer.valueOf(b2), z);
            }
        } catch (Throwable th) {
            if (resultCallback != null) {
                a(resultCallback, th, z);
            }
        }
    }
}
