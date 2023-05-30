package com.jingdong.app.mall.utils;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.os.Build;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public class p {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Callable<Object> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f11794g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11795h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f11796i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f11797j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f11798k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f11799l;

        /* renamed from: m  reason: collision with root package name */
        final /* synthetic */ String f11800m;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ String f11801n;
        final /* synthetic */ boolean o;

        a(boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z2) {
            this.f11794g = z;
            this.f11795h = str;
            this.f11796i = str2;
            this.f11797j = str3;
            this.f11798k = str4;
            this.f11799l = str5;
            this.f11800m = str6;
            this.f11801n = str7;
            this.o = z2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            if (this.f11794g) {
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), this.f11795h, 1 + this.f11796i, this.f11797j, "", this.f11798k, this.f11799l, this.f11800m, this.f11801n, null);
            } else {
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), this.f11795h, this.f11796i, this.f11797j, "", this.f11798k, this.f11799l, this.f11800m, this.f11801n, null);
            }
            if (!this.o || "0".equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionSdkABTest", "useUnionSdk", "0"))) {
                return null;
            }
            JDUnionUtils.appStartUp(JdSdk.getInstance().getApplication(), p.c());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Callable<Object> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f11802g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11803h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f11804i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f11805j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f11806k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f11807l;

        /* renamed from: m  reason: collision with root package name */
        final /* synthetic */ String f11808m;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ String f11809n;
        final /* synthetic */ boolean o;

        b(boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z2) {
            this.f11802g = z;
            this.f11803h = str;
            this.f11804i = str2;
            this.f11805j = str3;
            this.f11806k = str4;
            this.f11807l = str5;
            this.f11808m = str6;
            this.f11809n = str7;
            this.o = z2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            if (this.f11802g) {
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), this.f11803h, 1 + this.f11804i, this.f11805j, "", this.f11806k, this.f11807l, this.f11808m, this.f11809n, p.c());
            } else {
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), this.f11803h, this.f11804i, this.f11805j, "", this.f11806k, this.f11807l, this.f11808m, this.f11809n, p.c());
            }
            if (!this.o || "0".equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionSdkABTest", "useUnionSdk", "0"))) {
                return null;
            }
            JDUnionUtils.appStartUp(JdSdk.getInstance().getApplication(), p.c());
            return null;
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2) {
        f.f.c(new a(z, str, str2, str3, str4, str5, str6, str7, z2), f.f.f19368i);
    }

    public static void b(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2, boolean z3) {
        if (!z2) {
            a(str, str2, str3, str4, str5, str6, str7, z, z3);
        } else {
            f.f.c(new b(z, str, str2, str3, str4, str5, str6, str7, z3), f.f.f19368i);
        }
    }

    static /* synthetic */ HashMap c() {
        return d();
    }

    private static HashMap<String, String> d() {
        List<ApplicationExitInfo> historicalProcessExitReasons;
        ApplicationExitInfo applicationExitInfo;
        HashMap<String, String> hashMap = new HashMap<>();
        String str = "unknow";
        try {
            if (Build.VERSION.SDK_INT >= 30 && (historicalProcessExitReasons = ((ActivityManager) JdSdk.getInstance().getApplicationContext().getSystemService("activity")).getHistoricalProcessExitReasons(BaseInfo.getAppPackageName(), 0, 0)) != null && historicalProcessExitReasons.size() > 0 && (applicationExitInfo = historicalProcessExitReasons.get(0)) != null) {
                str = applicationExitInfo.toString();
            }
        } catch (Throwable unused) {
        }
        hashMap.put("lastExitInfo", str);
        return hashMap;
    }
}
