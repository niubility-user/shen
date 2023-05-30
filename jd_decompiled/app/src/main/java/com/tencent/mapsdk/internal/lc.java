package com.tencent.mapsdk.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import java.io.File;

/* loaded from: classes9.dex */
public class lc {

    /* renamed from: n  reason: collision with root package name */
    public static final String f16829n = "data/";
    private static final String o = "/tencentmapsdk/";
    private static lc p;
    private Context a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f16830c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f16831e;

    /* renamed from: f  reason: collision with root package name */
    private String f16832f;

    /* renamed from: g  reason: collision with root package name */
    private String f16833g;

    /* renamed from: h  reason: collision with root package name */
    private String f16834h;

    /* renamed from: i  reason: collision with root package name */
    private String f16835i;

    /* renamed from: j  reason: collision with root package name */
    private String f16836j;

    /* renamed from: k  reason: collision with root package name */
    private String f16837k;

    /* renamed from: l  reason: collision with root package name */
    private String f16838l;

    /* renamed from: m  reason: collision with root package name */
    private String f16839m;

    private lc(Context context, TencentMapOptions tencentMapOptions) {
        if (context == null) {
            throw new Error("context can not be null");
        }
        this.a = context.getApplicationContext();
        a(tencentMapOptions);
        j();
        k();
    }

    public static lc a(Context context, TencentMapOptions tencentMapOptions) {
        if (p == null) {
            p = new lc(context, tencentMapOptions);
        }
        return p;
    }

    private static String a(Context context) {
        File externalFilesDir = context.getExternalFilesDir(null);
        return externalFilesDir != null ? externalFilesDir.getAbsolutePath() : context.getFilesDir().getPath();
    }

    private void a(TencentMapOptions tencentMapOptions) {
        if (tencentMapOptions != null) {
            String customCacheRootPath = tencentMapOptions.getCustomCacheRootPath();
            if (e(customCacheRootPath)) {
                this.f16839m = customCacheRootPath;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static long b(String str) {
        long blockSizeLong;
        long availableBlocksLong;
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT < 18) {
                blockSizeLong = statFs.getBlockSize();
                availableBlocksLong = statFs.getAvailableBlocks();
            } else {
                blockSizeLong = statFs.getBlockSizeLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            }
            return ((blockSizeLong * availableBlocksLong) / 1024) / 1024;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static lc b(Context context) {
        return a(context, (TencentMapOptions) null);
    }

    private boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.isDirectory() && file.canRead() && file.canWrite() && b(str) > 5;
    }

    private void j() {
        this.f16835i = this.a.getFilesDir().getAbsolutePath();
        this.f16832f = this.f16835i + "/tencentMapSdk/config/";
        this.f16836j = this.f16832f + "temp/";
        this.f16833g = this.f16835i + "/tencentMapSdk/assets/";
        this.f16834h = this.f16835i + "/tencentMapSdk/dynamicAssets/";
        Context context = this.a;
        a(context, kc.a(context).d(l4.f16791e));
    }

    private void k() {
        String str;
        String h2 = h();
        String a = fa.a(this.a);
        if (e7.b(a)) {
            str = h2 + o;
        } else {
            str = h2 + o + a;
        }
        this.b = str;
        this.f16830c = this.b + "/data/v4/render/";
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append("/sat/");
        this.d = sb.toString();
        this.f16831e = this.f16830c + "closeRoadDatas/";
        this.f16837k = this.f16830c + "events/icons";
        this.f16838l = this.f16830c + "offlineMaps/";
    }

    public String a() {
        ga.b(this.f16834h);
        return this.f16834h;
    }

    public String a(String str) {
        String str2;
        if (e7.b(str)) {
            str2 = this.f16833g;
        } else {
            str2 = this.f16835i + "/tencentMapSdk/subKey/" + str + "/assets/";
        }
        ga.b(str2);
        return str2;
    }

    public void a(Context context, String str) {
        if (!e7.b(kc.a(context).d(l4.f16791e)) && b7.b("4.1.0", str) > 0) {
            jc.a(context);
            fa.e(new File(this.f16832f));
            fa.e(new File(this.f16833g));
            fa.e(new File(this.f16835i + "/tencentMapSdk/subKey/"));
        }
    }

    public File b() {
        return new File(this.b);
    }

    public File c() {
        return new File(this.b + "/data/");
    }

    public String c(String str) {
        String str2;
        if (e7.b(str)) {
            str2 = this.f16832f;
        } else {
            str2 = this.f16835i + "/tencentMapSdk/subKey/" + str + "/config/";
        }
        ga.b(str2);
        return str2;
    }

    public String d() {
        ga.b(this.f16830c);
        return this.f16830c;
    }

    public String d(String str) {
        String str2;
        if (e7.b(str)) {
            str2 = this.f16836j;
        } else {
            str2 = c(str) + "temp/";
        }
        ga.b(str2);
        return str2;
    }

    public String e() {
        ga.b(this.f16838l);
        return this.f16838l;
    }

    public String f() {
        ga.b(this.f16831e);
        return this.f16831e;
    }

    public String g() {
        ga.b(this.d);
        return this.d;
    }

    public String h() {
        if (TextUtils.isEmpty(this.f16839m)) {
            Context context = this.a;
            String a = a(context);
            if (b(a) < 5) {
                String path = context.getFilesDir().getPath();
                return b(path) < 5 ? a(context) : path;
            }
            return a;
        }
        return this.f16839m;
    }

    public String i() {
        ga.b(this.f16837k);
        return this.f16837k;
    }
}
