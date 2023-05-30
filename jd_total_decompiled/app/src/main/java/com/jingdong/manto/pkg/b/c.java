package com.jingdong.manto.pkg.b;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.a;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.pkg.b.i;
import com.jingdong.manto.utils.MantoSharedPrefrenceUtil;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.s;
import com.tencent.smtt.utils.Md5Utils;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c {
    private static i a;
    private static boolean b;

    /* renamed from: c  reason: collision with root package name */
    private static String f13972c;
    private static List<String> d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Context g2 = com.jingdong.a.g();
                String str = c.f13972c + CartConstant.KEY_YB_INFO_LINK + this.a;
                if (MantoSharedPrefrenceUtil.getPrefBoolean(g2, str, false)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ApplicationUpgradeHelper.APP_VERSION, com.jingdong.manto.b.g().b("versionName"));
                jSONObject.put("JS_version", c.f13972c);
                jSONObject.put("JS_file", this.a);
                MantoTrack.sendCommonDataWithExt(g2, "\u4f7f\u7528JS\u5f15\u64ce\u540e\u88c5", "JS_engine_backloading_mp", "", "", "", jSONObject.toString(), "", null);
                MantoSharedPrefrenceUtil.setAndApplyBoolean(g2, str, true);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13973c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ File f13974e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13975f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f13976g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ List f13977h;

        /* loaded from: classes16.dex */
        class a extends com.jingdong.manto.network.common.b {
            a() {
            }

            @Override // com.jingdong.manto.network.common.b
            public void a(com.jingdong.manto.network.mantorequests.b bVar) {
                b bVar2 = b.this;
                if (c.b(bVar2.f13974e, bVar2.f13975f, bVar2.f13976g, bVar2.f13977h, bVar2.f13973c, bVar2.a)) {
                    List unused = c.d = b.this.f13977h;
                    String unused2 = c.f13972c = b.this.a;
                    c.g(b.this.a);
                }
            }

            @Override // com.jingdong.manto.network.common.b
            public void a(Throwable th) {
                super.a(th);
                b.this.f13974e.delete();
            }
        }

        b(String str, String str2, String str3, String str4, File file, String str5, boolean z, List list) {
            this.a = str;
            this.b = str2;
            this.f13973c = str3;
            this.d = str4;
            this.f13974e = file;
            this.f13975f = str5;
            this.f13976g = z;
            this.f13977h = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.f(this.a);
            com.jingdong.manto.network.common.c.a(new com.jingdong.manto.network.mantorequests.a(this.b, this.f13973c, this.d, true), false, "jsupdate", false, new a());
        }
    }

    private static void a(File file, String str) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!TextUtils.equals(str, file2.getName())) {
                s.b(file2);
            }
        }
    }

    public static void a(boolean z, String str) {
        a.C0232a g2;
        b = z;
        if (z && (g2 = com.jingdong.manto.b.g()) != null) {
            try {
                String b2 = g2.b("versionName");
                String b3 = g2.b("versionCode");
                Context g3 = com.jingdong.a.g();
                StringBuilder sb = new StringBuilder();
                sb.append(g3.getCacheDir().getAbsolutePath());
                String str2 = File.separator;
                sb.append(str2);
                sb.append("manto_updates");
                sb.append(str2);
                File file = new File(sb.toString());
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                String str3 = b2 + CartConstant.KEY_YB_INFO_LINK + b3;
                a(file, str3);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                boolean optBoolean = jSONObject.optBoolean("useZip", true);
                String optString = jSONObject.optString("names");
                String optString2 = jSONObject.optString("sign");
                String optString3 = jSONObject.optString("url");
                String optString4 = jSONObject.optString("jsVersion");
                String[] split = optString.split("#");
                if (split != null && split.length != 0) {
                    List<String> asList = Arrays.asList(split);
                    File file2 = new File(file.getAbsolutePath() + str2 + str3 + str2);
                    if (!file2.isDirectory()) {
                        file2.mkdirs();
                    }
                    String absolutePath = file2.getAbsolutePath();
                    String str4 = optString4 + ".zip";
                    File file3 = new File(file2, str4);
                    if (!file3.exists() || !b(file3, optString2, optBoolean, asList, absolutePath, optString4)) {
                        com.jingdong.manto.b.d().networkIO().execute(new b(optString4, optString3, absolutePath, str4, file3, optString2, optBoolean, asList));
                        return;
                    }
                    d = asList;
                    f13972c = optString4;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(File file, String str, boolean z, List<String> list, String str2, String str3) {
        System.currentTimeMillis();
        if (!TextUtils.equals(Md5Utils.getMD5(file), str)) {
            file.delete();
            return false;
        } else if (z) {
            a = new i.c(file, str, list);
            return true;
        } else {
            File file2 = new File(str2, str3);
            s.a(file2);
            f0.a(file, file2.getAbsolutePath(), false);
            a = new i.b(file2, list);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream d(String str) {
        if (b && a != null) {
            try {
                System.currentTimeMillis();
                InputStream a2 = a.a(str);
                e(str);
                return a2;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static void e(String str) {
        List<String> list;
        if (TextUtils.isEmpty(f13972c) || (list = d) == null || !list.contains(str)) {
            return;
        }
        com.jingdong.manto.b.d().networkIO().execute(new a(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(String str) {
        try {
            Context g2 = com.jingdong.a.g();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApplicationUpgradeHelper.APP_VERSION, com.jingdong.manto.b.g().b("versionName"));
            jSONObject.put("JS_version", str);
            MantoTrack.sendCommonDataWithExt(g2, "\u83b7\u53d6JS\u5f15\u64ce\u540e\u88c5", "JS_engine_backloading", "", "", "", jSONObject.toString(), "", null);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(String str) {
        try {
            Context g2 = com.jingdong.a.g();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApplicationUpgradeHelper.APP_VERSION, com.jingdong.manto.b.g().b("versionName"));
            jSONObject.put("JS_version", str);
            MantoTrack.sendCommonDataWithExt(g2, "JS\u5f15\u64ce\u540e\u88c5\u4e0b\u8f7d\u6210\u529f", "JS_engine_backloading_success", "", "", "", jSONObject.toString(), "", null);
        } catch (Throwable unused) {
        }
    }
}
