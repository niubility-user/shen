package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.jd.dynamic.DYConstants;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ic {
    private static final String a = "tencentmap";
    private static final String b = "/mapsdk_vector/";

    /* renamed from: c  reason: collision with root package name */
    private static String f16682c = null;
    private static String d = null;

    /* renamed from: e  reason: collision with root package name */
    private static String f16683e = "resourceVersion.dat";

    public static final InputStream a(Context context, String str) {
        AssetManager assets;
        if (context == null || (assets = context.getAssets()) == null) {
            return null;
        }
        try {
            return assets.open(str);
        } catch (IOException e2) {
            ma.f(UriUtil.LOCAL_ASSET_SCHEME, e2.getMessage(), e2);
            return null;
        }
    }

    public static final InputStream a(Context context, String str, String str2) {
        return a(context, str + str2);
    }

    public static String a() {
        return f16682c;
    }

    public static void a(Context context, hc hcVar, String str, String str2) {
        InputStream inputStream;
        Throwable th;
        IOException e2;
        File file = new File(str, str2);
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = ga.c(lc.b(context).a((String) null) + str2);
            if (inputStream == null) {
                try {
                    if (f16682c != null) {
                        if (!file.exists() || a(context, hcVar, f16682c, str2, true)) {
                            inputStream = a(context, f16682c + str2);
                        }
                    } else if (d != null && (!file.exists() || a(context, hcVar, d, str2, false))) {
                        inputStream = ga.c(d + str2);
                    }
                } catch (IOException e3) {
                    e = e3;
                    e2 = e;
                    try {
                        e2.printStackTrace();
                        ga.a(fileOutputStream);
                        ga.a((Closeable) inputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        ga.a(fileOutputStream);
                        ga.a((Closeable) inputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    th = th;
                    ga.a(fileOutputStream);
                    ga.a((Closeable) inputStream);
                    throw th;
                }
            }
            if (inputStream == null) {
                String a2 = lc.b(context).a();
                if (!file.exists() || a(context, hcVar, a2, str2, false)) {
                    inputStream = ga.c(a2 + str2);
                }
            }
            if (inputStream == null && (!file.exists() || a(context, hcVar, b7.f16301m, str2, true))) {
                inputStream = b(context, str2);
            }
        } catch (IOException e4) {
            e = e4;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
        }
        if (inputStream == null) {
            ga.a((Closeable) null);
            ga.a((Closeable) inputStream);
            return;
        }
        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
        try {
            ga.a(inputStream, fileOutputStream2);
            ga.a(fileOutputStream2);
        } catch (IOException e5) {
            e2 = e5;
            fileOutputStream = fileOutputStream2;
            e2.printStackTrace();
            ga.a(fileOutputStream);
            ga.a((Closeable) inputStream);
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = fileOutputStream2;
            ga.a(fileOutputStream);
            ga.a((Closeable) inputStream);
            throw th;
        }
        ga.a((Closeable) inputStream);
    }

    private static void a(hc hcVar, String str) {
        File file = new File(str, j4.f16725e);
        String d2 = hcVar.d(l4.y);
        if (TextUtils.isEmpty(d2)) {
            return;
        }
        try {
            if (TextUtils.equals(va.a(file), d2)) {
                return;
            }
            file.delete();
            hcVar.a(new String[]{l4.s, l4.y});
        } catch (FileNotFoundException e2) {
            ma.b("config error: ", e2);
            hcVar.a(new String[]{l4.s, l4.y});
        }
    }

    public static void a(TencentMapOptions tencentMapOptions) {
        if (tencentMapOptions != null) {
            if (tencentMapOptions.getCustomAssetsPath() != null) {
                a(tencentMapOptions.getCustomAssetsPath());
            } else if (tencentMapOptions.getCustomLocalPath() != null) {
                b(tencentMapOptions.getCustomLocalPath());
            }
        }
    }

    public static void a(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        String str2 = File.separator;
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        f16682c = str;
    }

    private static boolean a(Context context, hc hcVar, String str, String str2, boolean z) {
        String str3;
        if (hcVar == null || (hcVar instanceof jc)) {
            return false;
        }
        String str4 = j4.f16729i;
        InputStream inputStream = null;
        if (str2.startsWith(j4.f16729i)) {
            str3 = l4.a;
        } else if (str2.startsWith(j4.f16730j)) {
            str3 = l4.d;
            str4 = j4.f16730j;
        } else if (str2.startsWith("poi_icon")) {
            str3 = l4.f16790c;
            str4 = "poi_icon";
        } else {
            str3 = "";
            str4 = null;
        }
        if (str4 == null) {
            return false;
        }
        int b2 = hcVar.b(str3);
        String str5 = str + f16683e;
        try {
            inputStream = z ? a(context, str5) : ga.c(str5);
            if (inputStream == null) {
                return false;
            }
            byte[] b3 = ga.b(inputStream);
            if (b3 == null) {
                return false;
            }
            int optInt = new JSONObject(new String(b3)).optInt(str4, -1);
            if (optInt == -1) {
                return false;
            }
            if (optInt > b2) {
                hcVar.a(new String[]{str3});
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            ga.a((Closeable) inputStream);
        }
    }

    public static final InputStream b(Context context, String str) {
        return a(context, b7.f16301m, str);
    }

    public static String b() {
        return d;
    }

    private static void b(hc hcVar, String str) {
        String str2;
        File file = new File(str, j4.b);
        String d2 = hcVar.d("indoormap_style_md5");
        String str3 = null;
        if (!TextUtils.isEmpty(d2)) {
            ma.a(la.f16819f, "\u6821\u9a8c\u6587\u4ef6:indoor_style.dat");
            try {
                str2 = va.a(file);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                str2 = null;
            }
            if (!TextUtils.equals(str2, d2)) {
                ma.a(la.f16819f, "\u6587\u4ef6md5\u6821\u9a8c\u5931\u8d25:" + str2 + DYConstants.DY_REGEX_COMMA + str2);
                file.delete();
                hcVar.a(new String[]{"indoormap_style_version", "indoormap_style_md5"});
            }
        }
        File file2 = new File(str, j4.f16724c);
        String d3 = hcVar.d("indoormap_style_night_md5");
        if (TextUtils.isEmpty(d3)) {
            return;
        }
        ma.a(la.f16819f, "\u6821\u9a8c\u6587\u4ef6:indoor_style_night.dat");
        try {
            str3 = va.a(file2);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        if (TextUtils.equals(str3, d3)) {
            return;
        }
        ma.a(la.f16819f, "\u6587\u4ef6md5\u6821\u9a8c\u5931\u8d25:" + str3 + DYConstants.DY_REGEX_COMMA + d3);
        file2.delete();
        hcVar.a(new String[]{"indoormap_style_night_version", "indoormap_style_night_md5"});
    }

    public static void b(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        String str2 = File.separator;
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        d = str;
    }

    public static void c(hc hcVar, String str) {
        if (d(hcVar, str)) {
            b(hcVar, str);
            a(hcVar, str);
        }
    }

    private static boolean d(hc hcVar, String str) {
        File file = new File(str, j4.a);
        String d2 = hcVar.d(l4.t);
        if (file.exists() || !TextUtils.isEmpty(d2)) {
            try {
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            if (TextUtils.equals(va.a(file), d2)) {
                return true;
            }
            file.delete();
            hcVar.b();
            return false;
        }
        return true;
    }
}
