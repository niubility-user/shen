package com.jingdong.aura.a.a;

import android.os.Build;
import android.text.TextUtils;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.a.c.l;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d {
    private static List<String> b;
    private static String[] a = {"arm64-v8a", "armeabi-v7a", "armeabi"};

    /* renamed from: c */
    private static List<String> f12083c = new ArrayList();
    private static List<ZipFile> d = new ArrayList();

    /* renamed from: e */
    private static com.jingdong.aura.core.util.l.b f12084e = com.jingdong.aura.core.util.l.c.a("InternalBundleInfo");

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        f();
        List<String> list = b;
        return list != null && list.contains(str);
    }

    private static String b(String str) {
        return str.substring(str.lastIndexOf(File.separatorChar) + 4, str.lastIndexOf(".so"));
    }

    public static InputStream c(String str) {
        for (ZipFile zipFile : d) {
            for (String str2 : a) {
                StringBuilder sb = new StringBuilder();
                sb.append("lib");
                String str3 = File.separator;
                sb.append(str3);
                sb.append(str2);
                sb.append(str3);
                sb.append(str);
                String sb2 = sb.toString();
                if (zipFile.getEntry(sb2) != null) {
                    return zipFile.getInputStream(zipFile.getEntry(sb2));
                }
            }
        }
        return null;
    }

    public static String d() {
        String name;
        File file = new File(com.jingdong.aura.core.util.d.a().getParentFile(), "lib");
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            StringBuilder sb = new StringBuilder("LibsInfo[");
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                if (listFiles[i2] != null && listFiles[i2].isFile() && (name = listFiles[i2].getName()) != null && name.startsWith("libcom.") && name.endsWith(".so")) {
                    sb.append(name);
                    if (i2 < listFiles.length - 1) {
                        sb.append(";");
                    }
                }
            }
            sb.append("]");
            return sb.toString();
        }
        return "";
    }

    public static String e() {
        f();
        List<ZipFile> list = d;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("internalSourceApks[");
        for (ZipFile zipFile : d) {
            sb.append(zipFile.getName());
            sb.append(":");
            sb.append(new File(zipFile.getName()).length());
            sb.append(";");
        }
        sb.append("]");
        return sb.toString();
    }

    public static synchronized void f() {
        String[] strArr;
        synchronized (d.class) {
            List<String> list = b;
            if (list == null || list.size() <= 0) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                try {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(l.a.getApplicationInfo().sourceDir);
                    if (Build.VERSION.SDK_INT >= 21 && (strArr = l.a.getApplicationInfo().splitSourceDirs) != null && strArr.length > 0) {
                        arrayList3.addAll(Arrays.asList(strArr));
                    }
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        ZipFile zipFile = new ZipFile((String) it.next());
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        while (entries.hasMoreElements()) {
                            String name = entries.nextElement().getName();
                            for (String str : a) {
                                if (name.startsWith("lib/" + str + "/libcom.") && name.endsWith(".so")) {
                                    f12083c.add(name);
                                    String b2 = b(name);
                                    if (!TextUtils.isEmpty(b2)) {
                                        arrayList.add(b2);
                                    }
                                }
                            }
                        }
                        arrayList2.add(zipFile);
                    }
                    b = arrayList;
                    d = arrayList2;
                } catch (Exception e2) {
                    f12084e.a("Exception while get bundles in assets or lib", e2);
                    e.a("", "resolveInternalBundles", "", "resolveInternalBundles exception:mInternalBundles:" + b, "InternalBundleInfo_resolveInternalBundles", e2);
                    e.a("resolveInternalBundles", "resolveInternalBundles failed!", "resolveInternalBundles", e2);
                }
            }
        }
    }

    public static String b() {
        f();
        List<String> list = b;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("internalBundles[");
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(";");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String a() {
        List<l.b.a.d> c2 = com.jingdong.aura.a.b.k.b.c();
        if (c2.isEmpty()) {
            f12084e.a("dumpInternalBundleImpl: bundles is empty");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<l.b.a.d> it = c2.iterator();
            while (it.hasNext()) {
                h hVar = (h) it.next();
                if (hVar != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("bundle", hVar.b());
                    jSONObject.put("bundleDexPath", hVar.e().e().f().getAbsolutePath());
                    h a2 = ((com.jingdong.aura.a.b.j.c) hVar.g()).a();
                    if (a2 != null) {
                        jSONObject.put("classLoaderDexPath", a2.e().e().f().getAbsolutePath());
                        jSONArray.put(jSONObject);
                    }
                }
            }
            String jSONArray2 = jSONArray.toString();
            f12084e.a("dumpInternalBundleImpl: " + jSONArray2);
            return jSONArray2;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String c() {
        f();
        List<String> list = f12083c;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("internalBundleSoPath[");
        Iterator<String> it = f12083c.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(";");
        }
        sb.append("]");
        return sb.toString();
    }
}
