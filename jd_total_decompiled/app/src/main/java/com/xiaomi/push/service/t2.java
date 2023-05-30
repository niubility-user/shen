package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.xiaomi.push.a8;
import com.xiaomi.push.m9;
import com.xiaomi.push.z6;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class t2 {
    private static s2 a;
    private static a b;

    /* loaded from: classes11.dex */
    public interface a {
        void a();
    }

    private static int a(Context context) {
        return context.getSharedPreferences("mipush_account", 0).getInt("enc_req_fail_count", 0);
    }

    public static synchronized s2 b(Context context) {
        synchronized (t2.class) {
            s2 s2Var = a;
            if (s2Var != null) {
                return s2Var;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
            String string = sharedPreferences.getString("uuid", null);
            String string2 = sharedPreferences.getString("token", null);
            String string3 = sharedPreferences.getString("security", null);
            String string4 = sharedPreferences.getString("app_id", null);
            String string5 = sharedPreferences.getString("app_token", null);
            String string6 = sharedPreferences.getString("package_name", null);
            String string7 = sharedPreferences.getString(PushConstants.DEVICE_ID, null);
            int i2 = sharedPreferences.getInt("env_type", 1);
            if (!TextUtils.isEmpty(string7) && z6.l(string7)) {
                string7 = z6.y(context);
                sharedPreferences.edit().putString(PushConstants.DEVICE_ID, string7).commit();
            }
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return null;
            }
            String y = z6.y(context);
            if (!"com.xiaomi.xmsf".equals(context.getPackageName()) && !TextUtils.isEmpty(y) && !TextUtils.isEmpty(string7) && !string7.equals(y)) {
                g.j.a.a.a.c.o("read_phone_state permission changes.");
            }
            s2 s2Var2 = new s2(string, string2, string3, string4, string5, string6, i2);
            a = s2Var2;
            return s2Var2;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(27:3|4|(2:8|(24:10|11|(1:13)(1:119)|14|(1:16)(1:118)|17|(1:19)(1:117)|20|21|22|23|(1:25)(1:113)|26|(6:28|(1:30)|31|(1:35)|36|(1:38))|39|(1:41)|42|(6:45|46|47|49|50|43)|54|55|(3:60|61|(2:63|64)(9:(1:67)|68|69|(2:73|(4:75|76|77|(7:79|(1:81)|82|83|84|85|86)(6:88|89|(1:93)|94|95|96)))|107|(2:91|93)|94|95|96))|112|61|(0)(0)))|120|11|(0)(0)|14|(0)(0)|17|(0)(0)|20|21|22|23|(0)(0)|26|(0)|39|(0)|42|(1:43)|54|55|(4:57|60|61|(0)(0))|112|61|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0084, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0085, code lost:
        g.j.a.a.a.c.s(r0);
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0316 A[Catch: all -> 0x032b, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0022, B:10:0x0038, B:12:0x0044, B:16:0x0057, B:20:0x0063, B:24:0x006f, B:25:0x0079, B:31:0x008d, B:33:0x0096, B:35:0x00c0, B:37:0x00cc, B:38:0x00df, B:40:0x00e9, B:42:0x00ef, B:43:0x0103, B:45:0x0109, B:46:0x010e, B:48:0x0131, B:49:0x013a, B:50:0x0173, B:52:0x0179, B:53:0x0180, B:56:0x018f, B:57:0x01c0, B:59:0x01e0, B:62:0x01e7, B:64:0x01fe, B:70:0x020d, B:74:0x0214, B:76:0x022b, B:78:0x0231, B:99:0x02e8, B:100:0x02f9, B:106:0x0316, B:108:0x031c, B:109:0x0324, B:103:0x0300, B:28:0x0085), top: B:119:0x0005, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008d A[Catch: all -> 0x032b, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0022, B:10:0x0038, B:12:0x0044, B:16:0x0057, B:20:0x0063, B:24:0x006f, B:25:0x0079, B:31:0x008d, B:33:0x0096, B:35:0x00c0, B:37:0x00cc, B:38:0x00df, B:40:0x00e9, B:42:0x00ef, B:43:0x0103, B:45:0x0109, B:46:0x010e, B:48:0x0131, B:49:0x013a, B:50:0x0173, B:52:0x0179, B:53:0x0180, B:56:0x018f, B:57:0x01c0, B:59:0x01e0, B:62:0x01e7, B:64:0x01fe, B:70:0x020d, B:74:0x0214, B:76:0x022b, B:78:0x0231, B:99:0x02e8, B:100:0x02f9, B:106:0x0316, B:108:0x031c, B:109:0x0324, B:103:0x0300, B:28:0x0085), top: B:119:0x0005, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c0 A[Catch: all -> 0x032b, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0022, B:10:0x0038, B:12:0x0044, B:16:0x0057, B:20:0x0063, B:24:0x006f, B:25:0x0079, B:31:0x008d, B:33:0x0096, B:35:0x00c0, B:37:0x00cc, B:38:0x00df, B:40:0x00e9, B:42:0x00ef, B:43:0x0103, B:45:0x0109, B:46:0x010e, B:48:0x0131, B:49:0x013a, B:50:0x0173, B:52:0x0179, B:53:0x0180, B:56:0x018f, B:57:0x01c0, B:59:0x01e0, B:62:0x01e7, B:64:0x01fe, B:70:0x020d, B:74:0x0214, B:76:0x022b, B:78:0x0231, B:99:0x02e8, B:100:0x02f9, B:106:0x0316, B:108:0x031c, B:109:0x0324, B:103:0x0300, B:28:0x0085), top: B:119:0x0005, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0131 A[Catch: all -> 0x032b, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0022, B:10:0x0038, B:12:0x0044, B:16:0x0057, B:20:0x0063, B:24:0x006f, B:25:0x0079, B:31:0x008d, B:33:0x0096, B:35:0x00c0, B:37:0x00cc, B:38:0x00df, B:40:0x00e9, B:42:0x00ef, B:43:0x0103, B:45:0x0109, B:46:0x010e, B:48:0x0131, B:49:0x013a, B:50:0x0173, B:52:0x0179, B:53:0x0180, B:56:0x018f, B:57:0x01c0, B:59:0x01e0, B:62:0x01e7, B:64:0x01fe, B:70:0x020d, B:74:0x0214, B:76:0x022b, B:78:0x0231, B:99:0x02e8, B:100:0x02f9, B:106:0x0316, B:108:0x031c, B:109:0x0324, B:103:0x0300, B:28:0x0085), top: B:119:0x0005, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0179 A[Catch: all -> 0x032b, TRY_LEAVE, TryCatch #4 {, blocks: (B:4:0x0005, B:6:0x001a, B:8:0x0022, B:10:0x0038, B:12:0x0044, B:16:0x0057, B:20:0x0063, B:24:0x006f, B:25:0x0079, B:31:0x008d, B:33:0x0096, B:35:0x00c0, B:37:0x00cc, B:38:0x00df, B:40:0x00e9, B:42:0x00ef, B:43:0x0103, B:45:0x0109, B:46:0x010e, B:48:0x0131, B:49:0x013a, B:50:0x0173, B:52:0x0179, B:53:0x0180, B:56:0x018f, B:57:0x01c0, B:59:0x01e0, B:62:0x01e7, B:64:0x01fe, B:70:0x020d, B:74:0x0214, B:76:0x022b, B:78:0x0231, B:99:0x02e8, B:100:0x02f9, B:106:0x0316, B:108:0x031c, B:109:0x0324, B:103:0x0300, B:28:0x0085), top: B:119:0x0005, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0208 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x020a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized s2 c(Context context, String str, String str2, String str3) {
        String str4;
        int c2;
        String a2;
        boolean z;
        String e2;
        com.xiaomi.push.h0 h0Var;
        boolean z2;
        String str5;
        JSONObject jSONObject;
        synchronized (t2.class) {
            TreeMap treeMap = new TreeMap();
            treeMap.put("devid", z6.h(context, false));
            s2 s2Var = a;
            if (s2Var != null && !TextUtils.isEmpty(s2Var.a)) {
                treeMap.put("uuid", a.a);
                int lastIndexOf = a.a.lastIndexOf("/");
                if (lastIndexOf != -1) {
                    str4 = a.a.substring(lastIndexOf + 1);
                    com.xiaomi.push.z.a(context).d(treeMap);
                    String str6 = !k(context) ? "1000271" : str2;
                    String str7 = !k(context) ? "420100086271" : str3;
                    String str8 = !k(context) ? "com.xiaomi.xmsf" : str;
                    treeMap.put("appid", str6);
                    treeMap.put("apptoken", str7);
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str8, 16384);
                    treeMap.put("appversion", packageInfo == null ? String.valueOf(packageInfo.versionCode) : "0");
                    treeMap.put("sdkversion", Integer.toString(50300));
                    treeMap.put("packagename", str8);
                    treeMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
                    treeMap.put("board", Build.BOARD);
                    if (!a8.t()) {
                        String s = z6.s(context);
                        String str9 = TextUtils.isEmpty(s) ? "" : "" + com.xiaomi.push.p0.b(s);
                        String w = z6.w(context);
                        if (!TextUtils.isEmpty(str9) && !TextUtils.isEmpty(w)) {
                            str9 = str9 + DYConstants.DY_REGEX_COMMA + w;
                        }
                        if (!TextUtils.isEmpty(str9)) {
                            treeMap.put("imei_md5", str9);
                        }
                    }
                    treeMap.put("os", Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL);
                    c2 = z6.c();
                    if (c2 >= 0) {
                        treeMap.put("space_id", Integer.toString(c2));
                    }
                    treeMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand() + "");
                    treeMap.put("ram", z6.e());
                    treeMap.put("rom", z6.n());
                    JSONObject jSONObject2 = new JSONObject();
                    for (Map.Entry entry : treeMap.entrySet()) {
                        try {
                            jSONObject2.put((String) entry.getKey(), entry.getValue());
                        } catch (JSONException e3) {
                            g.j.a.a.a.c.D("failed to add data in json format: k=" + ((String) entry.getKey()) + ",v=" + ((String) entry.getValue()) + ". " + e3);
                        }
                    }
                    a2 = w0.a(jSONObject2.toString());
                    TreeMap treeMap2 = new TreeMap();
                    treeMap2.put("requestData", a2);
                    treeMap2.put("keyPairVer", "1");
                    if (a(context) < 2 && !TextUtils.isEmpty(a2)) {
                        g.j.a.a.a.c.o("r.data = " + a2);
                        z = true;
                        e2 = e(context, z);
                        if (TextUtils.isEmpty(e2)) {
                            if (z) {
                                treeMap = treeMap2;
                            }
                            try {
                                h0Var = com.xiaomi.push.j0.d(context, e2, treeMap);
                            } catch (IOException e4) {
                                g.j.a.a.a.c.D("device registration request failed. " + e4);
                                h0Var = null;
                            }
                            if (h0Var != null && h0Var.a == 200) {
                                String a3 = h0Var.a();
                                if (!TextUtils.isEmpty(a3)) {
                                    try {
                                        jSONObject = new JSONObject(a3);
                                    } catch (JSONException e5) {
                                        e = e5;
                                        z2 = z;
                                    } catch (Throwable th) {
                                        th = th;
                                        z2 = z;
                                    }
                                    try {
                                    } catch (JSONException e6) {
                                        e = e6;
                                        str5 = "failed to parse respone json data. " + e;
                                        g.j.a.a.a.c.D(str5);
                                        if (z2) {
                                        }
                                        g.j.a.a.a.c.o("fail to register push account. meet error.");
                                        return null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        str5 = "unknow throwable. " + th;
                                        g.j.a.a.a.c.D(str5);
                                        if (z2) {
                                        }
                                        g.j.a.a.a.c.o("fail to register push account. meet error.");
                                        return null;
                                    }
                                    if (jSONObject.getInt("code") != 0) {
                                        z2 = z;
                                        w2.a(context, jSONObject.getInt("code"), jSONObject.optString("description"));
                                        g.j.a.a.a.c.o("device registration resp: " + a3);
                                        if (z2 && com.xiaomi.push.j0.q(context)) {
                                            h(context, a(context) + 1);
                                        }
                                        g.j.a.a.a.c.o("fail to register push account. meet error.");
                                        return null;
                                    }
                                    JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                                    String string = jSONObject3.getString("ssecurity");
                                    String string2 = jSONObject3.getString("token");
                                    String string3 = jSONObject3.getString("userId");
                                    if (TextUtils.isEmpty(str4)) {
                                        str4 = "an" + com.xiaomi.push.p0.a(6);
                                    }
                                    s2 s2Var2 = new s2(string3 + "@xiaomi.com/" + str4, string2, string, str6, str7, str8, com.xiaomi.push.b.a());
                                    i(context, s2Var2);
                                    a = s2Var2;
                                    h(context, 0);
                                    g.j.a.a.a.c.o("device registration is successful. " + string3);
                                    return s2Var2;
                                }
                            }
                            z2 = z;
                            if (z2) {
                                h(context, a(context) + 1);
                            }
                            g.j.a.a.a.c.o("fail to register push account. meet error.");
                            return null;
                        }
                        return null;
                    }
                    z = false;
                    e2 = e(context, z);
                    if (TextUtils.isEmpty(e2)) {
                    }
                }
            }
            str4 = null;
            com.xiaomi.push.z.a(context).d(treeMap);
            if (!k(context)) {
            }
            if (!k(context)) {
            }
            if (!k(context)) {
            }
            treeMap.put("appid", str6);
            treeMap.put("apptoken", str7);
            PackageInfo packageInfo2 = context.getPackageManager().getPackageInfo(str8, 16384);
            treeMap.put("appversion", packageInfo2 == null ? String.valueOf(packageInfo2.versionCode) : "0");
            treeMap.put("sdkversion", Integer.toString(50300));
            treeMap.put("packagename", str8);
            treeMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
            treeMap.put("board", Build.BOARD);
            if (!a8.t()) {
            }
            treeMap.put("os", Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL);
            c2 = z6.c();
            if (c2 >= 0) {
            }
            treeMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand() + "");
            treeMap.put("ram", z6.e());
            treeMap.put("rom", z6.n());
            JSONObject jSONObject22 = new JSONObject();
            while (r9.hasNext()) {
            }
            a2 = w0.a(jSONObject22.toString());
            TreeMap treeMap22 = new TreeMap();
            treeMap22.put("requestData", a2);
            treeMap22.put("keyPairVer", "1");
            if (a(context) < 2) {
                g.j.a.a.a.c.o("r.data = " + a2);
                z = true;
                e2 = e(context, z);
                if (TextUtils.isEmpty(e2)) {
                }
            }
            z = false;
            e2 = e(context, z);
            if (TextUtils.isEmpty(e2)) {
            }
        }
    }

    public static String d(Context context) {
        s2 b2 = b(context);
        if (b2 != null && !TextUtils.isEmpty(b2.a)) {
            String[] split = b2.a.split(DYConstants.DY_REGEX_AT);
            if (split.length > 0) {
                return split[0];
            }
        }
        return null;
    }

    private static String e(Context context, boolean z) {
        StringBuilder sb;
        String str;
        String b2 = b.a(context).b();
        String str2 = z ? "/pass/v2/register/encrypt" : "/pass/v2/register";
        if (com.xiaomi.push.b.d()) {
            sb = new StringBuilder();
            str = "http://10.38.162.35:9085";
        } else if (!m9.China.name().equals(b2)) {
            return null;
        } else {
            sb = new StringBuilder();
            str = "https://cn.register.xmpush.xiaomi.com";
        }
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static void f() {
        a aVar = b;
        if (aVar != null) {
            aVar.a();
        }
    }

    public static void g(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        f();
    }

    private static void h(Context context, int i2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putInt("enc_req_fail_count", i2);
        edit.commit();
    }

    public static void i(Context context, s2 s2Var) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", s2Var.a);
        edit.putString("security", s2Var.f19187c);
        edit.putString("token", s2Var.b);
        edit.putString("app_id", s2Var.d);
        edit.putString("package_name", s2Var.f19189f);
        edit.putString("app_token", s2Var.f19188e);
        edit.putString(PushConstants.DEVICE_ID, z6.y(context));
        edit.putInt("env_type", s2Var.f19190g);
        edit.commit();
        f();
    }

    public static void j(a aVar) {
        b = aVar;
    }

    private static boolean k(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
