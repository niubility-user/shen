package com.huawei.secure.android.common.ssl.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public class a {
    private static final Uri a = Uri.parse("content://com.huawei.hwid");
    private static final String[] b = {"B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539"};

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        File file = new File(str);
        if (file.exists()) {
            f.f("BksUtil", "The directory  has already exists");
            return 1;
        } else if (file.mkdirs()) {
            f.b("BksUtil", "create directory  success");
            return 0;
        } else {
            f.d("BksUtil", "create directory  failed");
            return -1;
        }
    }

    private static String b(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext().getFilesDir() + File.separator + "aegis";
        }
        return context.getApplicationContext().getFilesDir() + File.separator + "aegis";
    }

    private static String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static void d(InputStream inputStream, Context context) {
        if (inputStream == null || context == null) {
            return;
        }
        String b2 = b(context);
        if (!new File(b2).exists()) {
            a(b2);
        }
        File file = new File(b2, "hmsrootcas.bks");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                f.e("BksUtil", "write output stream ");
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 2048);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            e.c(fileOutputStream2);
                            return;
                        }
                    }
                } catch (IOException unused) {
                    fileOutputStream = fileOutputStream2;
                    f.d("BksUtil", " IOException");
                    e.c(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    e.c(fileOutputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static byte[] e(Context context, String str) {
        PackageInfo packageInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                return packageInfo.signatures[0].toByteArray();
            }
        } catch (PackageManager.NameNotFoundException e2) {
            String str2 = "PackageManager.NameNotFoundException : " + e2.getMessage();
        } catch (Exception e3) {
            String str3 = "get pm exception : " + e3.getMessage();
        }
        return new byte[0];
    }

    private static String f(Context context) {
        return b(context) + File.separator + "hmsrootcas.bks";
    }

    private static String g(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
            messageDigest.update(bArr);
            return c(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            f.d("BksUtil", "inputstraem exception");
            return "";
        }
    }

    private static boolean h(Context context, String str) {
        return "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539".equalsIgnoreCase(j(e(context, str)));
    }

    private static boolean i(String str) {
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        f.e("BksUtil", "hms version code is : " + str);
        String[] split = str.split("\\.");
        String[] split2 = "4.0.2.300".split("\\.");
        int length = split.length;
        int length2 = split2.length;
        int max = Math.max(length, length2);
        int i2 = 0;
        while (i2 < max) {
            if (i2 < length) {
                try {
                    parseInt = Integer.parseInt(split[i2]);
                } catch (Exception e2) {
                    f.d("BksUtil", " exception : " + e2.getMessage());
                    return i2 >= length2;
                }
            } else {
                parseInt = 0;
            }
            int parseInt2 = i2 < length2 ? Integer.parseInt(split2[i2]) : 0;
            if (parseInt < parseInt2) {
                return false;
            }
            if (parseInt > parseInt2) {
                return true;
            }
            i2++;
        }
        return true;
    }

    private static String j(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return c(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(bArr));
        } catch (NoSuchAlgorithmException e2) {
            String str = "NoSuchAlgorithmException" + e2.getMessage();
            return "";
        }
    }

    private static boolean k(Context context) {
        return new File(b(context) + File.separator + "hmsrootcas.bks").exists();
    }

    private static boolean l(Context context, String str) {
        byte[] e2 = e(context, str);
        for (String str2 : b) {
            if (str2.equalsIgnoreCase(j(e2))) {
                return true;
            }
        }
        return false;
    }

    /*  JADX ERROR: Type inference failed with exception
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:418)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:215)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:196)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdateForSsaVar(TypeUpdate.java:172)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:166)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:149)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:82)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:55)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryPossibleTypes(TypeInferenceVisitor.java:414)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.deduceType(TypeInferenceVisitor.java:472)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryDeduceTypes(TypeInferenceVisitor.java:452)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:109)
        */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0116: MOVE (r8 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:59:0x0116 */
    public static synchronized java.io.InputStream m(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.ssl.g.a.m(android.content.Context):java.io.InputStream");
    }

    public static InputStream n(Context context) {
        if (k(context)) {
            f.e("BksUtil", "getFilesBksIS ");
            try {
                return new FileInputStream(f(context));
            } catch (FileNotFoundException unused) {
                f.d("BksUtil", "FileNotFoundExceptio: ");
                return null;
            }
        }
        return null;
    }
}
