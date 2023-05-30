package com.huawei.hms.hatool;

import java.io.File;
import java.io.IOException;

/* loaded from: classes12.dex */
public class x {
    private String a = q0.i().getFilesDir().getPath();

    private String a(String str) {
        return this.a + "/hms/component/".replace("component", str);
    }

    private void a(String str, String str2) {
        File file = new File(a(str));
        File file2 = new File(a(str), "hianalytics_" + str);
        if (!file.exists() && file.mkdirs()) {
            v.c("hmsSdk", "file directory is mkdirs");
        }
        if (a(file2)) {
            k1.a(file2, str2);
        } else {
            v.f("hmsSdk", "refreshComponent():file is not found,and file is create failed");
        }
    }

    private boolean a(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            v.f("hmsSdk", "create new file error!");
            return false;
        }
    }

    private char[] a(String str, String str2, String str3, String str4) {
        byte[] b = com.huawei.secure.android.common.encrypt.d.c.b(str);
        byte[] b2 = com.huawei.secure.android.common.encrypt.d.c.b(str2);
        byte[] b3 = com.huawei.secure.android.common.encrypt.d.c.b(str3);
        byte[] b4 = com.huawei.secure.android.common.encrypt.d.c.b(str4);
        int length = b.length;
        if (length > b2.length) {
            length = b2.length;
        }
        if (length > b3.length) {
            length = b3.length;
        }
        if (length > b4.length) {
            length = b4.length;
        }
        char[] cArr = new char[length];
        for (int i2 = 0; i2 < length; i2++) {
            cArr[i2] = (char) (((b[i2] ^ b2[i2]) ^ b3[i2]) ^ b4[i2]);
        }
        return cArr;
    }

    private String b(String str) {
        File file = new File(a(str), "hianalytics_" + str);
        if (a(file)) {
            return k1.a(file);
        }
        String d = com.huawei.secure.android.common.encrypt.d.b.d(128);
        k1.a(file, d);
        return d;
    }

    private boolean b() {
        long a = d.a(q0.i(), "Privacy_MY", "assemblyFlash", -1L);
        if (-1 != a) {
            return System.currentTimeMillis() - a > 31536000000L;
        }
        v.c("hmsSdk", "First init components");
        return true;
    }

    private static boolean b(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return false;
        }
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                if (!file2.delete()) {
                    v.c("hmsSdk", "delete file failed : " + file2.getName());
                }
            } else if (file2.isDirectory()) {
                b(file2);
            }
        }
        return file.delete();
    }

    public static boolean c() {
        return b(new File(q0.i().getFilesDir().getPath() + "/hms"));
    }

    private String d() {
        return "f6040d0e807aaec325ecf44823765544e92905158169f694b282bf17388632cf95a83bae7d2d235c1f039b0df1dcca5fda619b6f7f459f2ff8d70ddb7b601592fe29fcae58c028f319b3b12495e67aa5390942a997a8cb572c8030b2df5c2b622608bea02b0c3e5d4dff3f72c9e3204049a45c0760cd3604af8d57f0e0c693cc";
    }

    public String a() {
        String b;
        String b2;
        String b3;
        String b4;
        String d = d();
        if (b()) {
            v.c("hmsSdk", "refresh components");
            b = com.huawei.secure.android.common.encrypt.d.b.d(128);
            a("aprpap", b);
            b2 = com.huawei.secure.android.common.encrypt.d.b.d(128);
            a("febdoc", b2);
            b3 = com.huawei.secure.android.common.encrypt.d.b.d(128);
            a("marfil", b3);
            b4 = com.huawei.secure.android.common.encrypt.d.b.d(128);
            a("maywnj", b4);
            d.b(q0.i(), "Privacy_MY", "assemblyFlash", System.currentTimeMillis());
        } else {
            b = b("aprpap");
            b2 = b("febdoc");
            b3 = b("marfil");
            b4 = b("maywnj");
        }
        return com.huawei.secure.android.common.encrypt.d.c.a(com.huawei.secure.android.common.encrypt.b.a.b(a(b, b2, b3, d), com.huawei.secure.android.common.encrypt.d.c.b(b4), 10000, 16));
    }
}
