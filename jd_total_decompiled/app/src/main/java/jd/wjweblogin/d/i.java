package jd.wjweblogin.d;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes11.dex */
public class i {
    private static final String a = "SaveUtil";
    private static final String b = "hg25rewqx0cvbn6mp3pkj609821_wjweblogin_v1.jd";

    /* renamed from: c  reason: collision with root package name */
    private static SharedPreferences f20066c;
    private static Context d;

    public static void a(Context context) {
        d = context;
    }

    public static void b(String str, String str2) {
        a(e(str), str2);
    }

    private static Context c() {
        Context context = d;
        return context == null ? jd.wjweblogin.common.a.a() : context;
    }

    private static String d() {
        g.b(a, "readFile begin");
        if (c() == null) {
            g.b(a, "readFile getmContext() == null");
            return "";
        }
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                if (a()) {
                    fileInputStream = c().openFileInput(e(b));
                    int available = fileInputStream.available();
                    if (available == 0) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                g.a("SaveUtil readFile ", e2);
                            }
                        }
                        return "";
                    }
                    byte[] bArr = new byte[available];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        g.b(a, "readFile len =" + read);
                        stringBuffer.append(new String(bArr, 0, read, "UTF-8"));
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            g.a("SaveUtil readFile ", e3);
                            return "";
                        }
                    }
                    return stringBuffer.toString();
                }
                return "";
            } catch (Throwable th) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        g.a("SaveUtil readFile ", e4);
                        return "";
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            g.a(a, "readFile  Exception=" + e5.getMessage());
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    g.a("SaveUtil readFile ", e6);
                }
            }
            return "";
        }
    }

    public static String e() {
        return d();
    }

    public static String f(String str) {
        SharedPreferences b2 = b();
        return b2 == null ? "" : b2.getString(str, "");
    }

    public static String g(String str) {
        return f(e(str));
    }

    public static void h(String str) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().remove(str).apply();
    }

    private static void i(String str) {
        if (c() == null) {
            g.b(a, "writeFile getmContext()== null");
            return;
        }
        if (str == null) {
            g.b(a, "writeFile data == null");
            str = "";
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = c().openFileOutput(e(b), 0);
                    fileOutputStream.write(str.getBytes("UTF-8"));
                    fileOutputStream.flush();
                } catch (Throwable th) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            g.a("SaveUtil writeFile ", e2);
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                g.b(a, "writeFile Exception=" + e3.getMessage());
                if (fileOutputStream == null) {
                    return;
                }
                fileOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e4) {
            g.a("SaveUtil writeFile ", e4);
        }
    }

    public static void j(String str) {
        i(str);
    }

    public static void a(String str, String str2) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().putString(str, str2).apply();
    }

    private static String e(String str) {
        return d(str);
    }

    public static void b(String str) {
        c(str);
        h(str);
    }

    private static void c(String str) {
        g.b(a, "deleteFile begin");
        if (c() == null) {
            return;
        }
        try {
            g.a(a, "deleteFile  success=" + c().deleteFile(e(b)));
        } catch (Exception e2) {
            g.a(a, "deleteFile  Exception=" + e2.getMessage());
        }
    }

    public static boolean a(String str) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return false;
        }
        return b2.contains(str);
    }

    private static synchronized SharedPreferences b() {
        synchronized (i.class) {
            SharedPreferences sharedPreferences = f20066c;
            if (sharedPreferences != null) {
                return sharedPreferences;
            }
            try {
                return c().getSharedPreferences(e(b), 0);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static int a(String str, int i2) {
        SharedPreferences b2 = b();
        return b2 == null ? i2 : b2.getInt(str, i2);
    }

    public static long a(String str, long j2) {
        SharedPreferences b2 = b();
        return b2 == null ? j2 : b2.getLong(str, j2);
    }

    public static void b(String str, long j2) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().putLong(str, j2).apply();
    }

    public static boolean a(String str, boolean z) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return false;
        }
        return b2.getBoolean(str, z);
    }

    public static void b(String str, boolean z) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().putBoolean(str, z).apply();
    }

    private static boolean a() {
        try {
            if (c() == null) {
                return false;
            }
            if (c().getFileStreamPath(e(b)).exists()) {
                g.b(a, "fileIsExist FILE_NAME = true");
                return true;
            }
            g.b(a, "fileIsExist FILE_NAME = false");
            return false;
        } catch (Exception e2) {
            g.a(a, "fileIsExist FILE_NAME = false" + e2.getMessage());
            return false;
        }
    }

    private static String d(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i2 = 0; i2 < charArray.length; i2++) {
                bArr[i2] = (byte) charArray[i2];
            }
            byte[] digest = messageDigest.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i3 = b2 & 255;
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
            }
            return stringBuffer.toString().substring(8, 24);
        } catch (Throwable unused) {
            return "";
        }
    }
}
