package com.huawei.hms.opendevice;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.android.hms.openid.R;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public abstract class o {
    private static final String a = "o";
    private static Map<String, String> b = new HashMap();

    /* renamed from: c */
    private static final Object f1442c = new Object();

    private static String a() {
        return "2A57086C86EF54970C1E6EB37BFC72B1";
    }

    private static byte[] a(String str, String str2, String str3, String str4) {
        if (Build.VERSION.SDK_INT >= 26) {
            return com.huawei.secure.android.common.encrypt.d.a.e(str, str2, str3, str4, 32, true);
        }
        return com.huawei.secure.android.common.encrypt.d.a.e(str, str2, str3, str4, 32, false);
    }

    private static byte[] b() {
        return a(d(), e(), c(), g());
    }

    public static void c(Context context) {
        synchronized (f1442c) {
            d(context.getApplicationContext());
            if (i()) {
                HMSLog.i(a, "The local secret is already in separate file mode.");
                return;
            }
            File file = new File(d.c(context.getApplicationContext()) + "/shared_prefs/LocalAvengers.xml");
            if (file.exists()) {
                com.huawei.secure.android.common.util.a.d(file);
                HMSLog.i(a, "destroy C, delete file LocalAvengers.xml.");
            }
            byte[] c2 = com.huawei.secure.android.common.encrypt.d.b.c(32);
            byte[] c3 = com.huawei.secure.android.common.encrypt.d.b.c(32);
            byte[] c4 = com.huawei.secure.android.common.encrypt.d.b.c(32);
            byte[] c5 = com.huawei.secure.android.common.encrypt.d.b.c(32);
            String a2 = c.a(c2);
            String a3 = c.a(c3);
            String a4 = c.a(c4);
            String a5 = c.a(c5);
            a(a2, a3, a4, a5, com.huawei.secure.android.common.encrypt.d.e.c(c.a(com.huawei.secure.android.common.encrypt.d.b.c(32)), a(a2, a3, a4, a5)), context);
            HMSLog.i(a, "generate D.");
        }
    }

    private static void d(Context context) {
        if (i()) {
            HMSLog.i(a, "secretKeyCache not empty.");
            return;
        }
        b.clear();
        String c2 = d.c(context);
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        String a2 = p.a(c2 + "/files/math/m");
        String a3 = p.a(c2 + "/files/panda/p");
        String a4 = p.a(c2 + "/files/panda/d");
        String a5 = p.a(c2 + "/files/math/t");
        String a6 = p.a(c2 + "/files/s");
        if (q.a(a2, a3, a4, a5, a6)) {
            b.put("m", a2);
            b.put("p", a3);
            b.put("d", a4);
            b.put("t", a5);
            b.put("s", a6);
        }
    }

    private static synchronized String e(Context context) {
        synchronized (o.class) {
            String b2 = com.huawei.secure.android.common.encrypt.d.e.b(f(), b());
            if (q.a(b2)) {
                HMSLog.i(a, "keyS has been upgraded, no require operate again.");
                return b2;
            }
            String a2 = com.huawei.secure.android.common.encrypt.d.e.a(f(), h());
            if (q.a(a2)) {
                HMSLog.i(a, "keyS is encrypt by RootKeyUtil, upgrade encrypt mode.");
                a(com.huawei.secure.android.common.encrypt.d.e.c(a2, b()), context);
                return a2;
            }
            String b3 = com.huawei.secure.android.common.encrypt.d.e.b(f(), com.huawei.secure.android.common.encrypt.d.a.e(d(), e(), c(), g(), 32, false));
            if (q.a(b3)) {
                HMSLog.i(a, "keyS is encrypt by ExportRootKey with sha1, upgrade encrypt mode to sha256.");
                a(com.huawei.secure.android.common.encrypt.d.e.c(b3, b()), context);
                return b3;
            }
            HMSLog.e(a, "all mode unable to decrypt root key.");
            return "";
        }
    }

    private static String f() {
        return a("s");
    }

    private static String g() {
        return a("t");
    }

    private static com.huawei.secure.android.common.encrypt.d.d h() {
        return com.huawei.secure.android.common.encrypt.d.d.d(d(), e(), c(), g());
    }

    private static boolean i() {
        return !TextUtils.isEmpty(f());
    }

    public static String b(Context context) {
        if (!i()) {
            HMSLog.i(a, "work key is empty, execute init.");
            c(context);
        }
        String b2 = com.huawei.secure.android.common.encrypt.d.e.b(f(), b());
        return q.a(b2) ? b2 : e(context);
    }

    public static byte[] a(Context context) {
        byte[] a2 = c.a(context.getString(R.string.push_cat_head));
        byte[] a3 = c.a(context.getString(R.string.push_cat_body));
        return a(a(a(a2, a3), c.a(a())));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length == 0 || bArr2.length == 0) {
            return new byte[0];
        }
        int length = bArr.length;
        if (length != bArr2.length) {
            return new byte[0];
        }
        byte[] bArr3 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] >> 2);
        }
        return bArr;
    }

    private static void a(String str, String str2, String str3, String str4, String str5, Context context) {
        String c2 = d.c(context.getApplicationContext());
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        try {
            a("m", str, c2 + "/files/math/m");
            a("p", str2, c2 + "/files/panda/p");
            a("d", str3, c2 + "/files/panda/d");
            a("t", str4, c2 + "/files/math/t");
            a("s", str5, c2 + "/files/s");
        } catch (IOException unused) {
            HMSLog.e(a, "save key IOException.");
        }
    }

    private static String d() {
        return a("m");
    }

    private static String e() {
        return a("p");
    }

    private static String c() {
        return a("d");
    }

    private static void a(String str, Context context) {
        String c2 = d.c(context.getApplicationContext());
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        try {
            a("s", str, c2 + "/files/s");
        } catch (IOException unused) {
            HMSLog.e(a, "save keyS IOException.");
        }
    }

    private static void a(String str, String str2, String str3) throws IOException {
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        HMSLog.i(a, "save local secret key.");
        BufferedWriter bufferedWriter2 = null;
        try {
            File file = new File(str3);
            p.a(file);
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            try {
                bufferedWriter = new BufferedWriter(outputStreamWriter);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStreamWriter = null;
        }
        try {
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            b.put(str, str2);
            IOUtils.closeQuietly((Writer) outputStreamWriter);
            IOUtils.closeQuietly((Writer) bufferedWriter);
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = bufferedWriter;
            IOUtils.closeQuietly((Writer) outputStreamWriter);
            IOUtils.closeQuietly((Writer) bufferedWriter2);
            throw th;
        }
    }

    private static String a(String str) {
        String str2 = b.get(str);
        return TextUtils.isEmpty(str2) ? "" : str2;
    }
}
