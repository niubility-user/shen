package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class v {
    private static final String a = "SaveUtil";
    private static final String b = "gyo8id&ba1hki9oemvet0_wjlogin_users_v6.jd";

    /* renamed from: c  reason: collision with root package name */
    private static SharedPreferences f19992c;
    private static Context d;

    public static void a(Context context) {
        d = context;
    }

    public static void b(String str, String str2) {
        a(d(str), str2);
    }

    private static Context c() {
        Context context = d;
        return context == null ? jd.wjlogin_sdk.common.b.a() : context;
    }

    private static String d() {
        p.b(a, "readFile begin");
        if (c() == null) {
            p.b(a, "readFile getmContext() == null");
            return "";
        }
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                if (a()) {
                    fileInputStream = c().openFileInput(d(b));
                    int available = fileInputStream.available();
                    if (available == 0) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                p.a("SaveUtil readFile ", e2);
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
                        p.b(a, "readFile len =" + read);
                        stringBuffer.append(new String(bArr, 0, read, "UTF-8"));
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            p.a("SaveUtil readFile ", e3);
                            return "";
                        }
                    }
                    return stringBuffer.toString();
                }
                return "";
            } catch (Exception e4) {
                p.a(a, "readFile  Exception=" + e4.getMessage());
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        p.a("SaveUtil readFile ", e5);
                    }
                }
                return "";
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    p.a("SaveUtil readFile ", e6);
                    return "";
                }
            }
            throw th;
        }
    }

    public static String e() {
        return d();
    }

    public static String f(String str) {
        return e(d(str));
    }

    public static void g(String str) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().remove(str).apply();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0061 -> B:30:0x0064). Please submit an issue!!! */
    private static void h(String str) {
        if (c() == null) {
            p.b(a, "writeFile getmContext()== null");
            return;
        }
        if (str == null) {
            p.b(a, "writeFile data == null");
            str = "";
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = c().openFileOutput(d(b), 0);
                    fileOutputStream.write(str.getBytes("UTF-8"));
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            p.a("SaveUtil writeFile ", e2);
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                p.b(a, "writeFile Exception=" + e3.getMessage());
                if (fileOutputStream == null) {
                    return;
                }
                fileOutputStream.close();
            }
        } catch (IOException e4) {
            p.a("SaveUtil writeFile ", e4);
        }
    }

    public static void i(String str) {
        h(str);
    }

    public static void a(String str, String str2) {
        SharedPreferences b2 = b();
        if (b2 == null) {
            return;
        }
        b2.edit().putString(str, str2).apply();
    }

    public static String e(String str) {
        SharedPreferences b2 = b();
        return b2 == null ? "" : b2.getString(str, "");
    }

    public static void b(String str) {
        c(str);
        g(str);
    }

    private static void c(String str) {
        p.b(a, "deleteFile begin");
        if (c() == null) {
            return;
        }
        try {
            p.a(a, "deleteFile  success=" + c().deleteFile(d(b)));
        } catch (Exception e2) {
            p.a(a, "deleteFile  Exception=" + e2.getMessage());
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
        synchronized (v.class) {
            SharedPreferences sharedPreferences = f19992c;
            if (sharedPreferences != null) {
                return sharedPreferences;
            }
            try {
                return c().getSharedPreferences(d(b), 0);
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
            if (c().getFileStreamPath(d(b)).exists()) {
                p.b(a, "fileIsExist FILE_NAME = true");
                return true;
            }
            p.b(a, "fileIsExist FILE_NAME = false");
            return false;
        } catch (Exception e2) {
            p.a(a, "fileIsExist FILE_NAME = false" + e2.getMessage());
            return false;
        }
    }

    private static String d(String str) {
        return MD5.encrypt16(str);
    }
}
