package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.xiaomi.push.m9;
import com.xiaomi.push.z6;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized com.xiaomi.push.service.s2 c(android.content.Context r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 816
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.t2.c(android.content.Context, java.lang.String, java.lang.String, java.lang.String):com.xiaomi.push.service.s2");
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
