package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class a0 {
    private static final String a = "UserInfoStoreUtil";
    private static SharedPreferences b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Context f19913c = null;
    private static final String d = "wj_shpunx_v2.jd";

    private static String a(Context context) {
        String encrypt16 = MD5.encrypt16("wjlogin_spf_v2" + g.a(context) + g.c() + g.f());
        if (p.b) {
            p.b(a, "name = " + encrypt16);
        }
        return encrypt16;
    }

    public static void b(Context context) {
        f19913c = jd.wjlogin_sdk.common.b.a();
    }

    public static synchronized SharedPreferences c() {
        SharedPreferences sharedPreferences;
        synchronized (a0.class) {
            if (b == null) {
                b = jd.wjlogin_sdk.common.b.a().getSharedPreferences(a(jd.wjlogin_sdk.common.b.a()), 0);
            }
            sharedPreferences = b;
        }
        return sharedPreferences;
    }

    public static String d() {
        p.b(a, "readFile begin");
        if (jd.wjlogin_sdk.common.b.a() == null) {
            return "";
        }
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                p.b(a, "readFile fileName =wj_shpunx_v2.jd");
                fileInputStream = jd.wjlogin_sdk.common.b.a().openFileInput(d);
                int available = fileInputStream.available();
                p.b(a, "readFile fis size =" + available);
                if (available == 0) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            p.a("UserInfoStoreUtil readFile ", e2);
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
                        p.a("UserInfoStoreUtil readFile ", e3);
                        return "";
                    }
                }
                String stringBuffer2 = stringBuffer.toString();
                p.b(a, "readFile data =" + stringBuffer2);
                return stringBuffer2;
            } catch (Exception e4) {
                p.a(a, "readFile  Exception=" + e4.getMessage());
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        p.a("UserInfoStoreUtil readFile ", e5);
                    }
                }
                return "";
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    p.a("UserInfoStoreUtil readFile ", e6);
                    return "";
                }
            }
            throw th;
        }
    }

    public static void b(String str, boolean z) {
        c().edit().putBoolean(str, z).apply();
    }

    public static void b(String str) {
        c().edit().remove(str).apply();
    }

    public static boolean b() {
        try {
            p.b(a, "fileIsExist fileName =wj_shpunx_v2.jd");
            if (jd.wjlogin_sdk.common.b.a().getFileStreamPath(d).exists()) {
                p.b(a, "fileIsExist fileName = true");
                return true;
            }
            p.b(a, "fileIsExist fileName = false");
            return false;
        } catch (Exception e2) {
            p.a(a, "fileIsExist fileName = false" + e2.getMessage());
            return false;
        }
    }

    public static void c(String str, String str2) {
        c().edit().putString(str, str2).apply();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006d -> B:29:0x0070). Please submit an issue!!! */
    public static void c(String str) {
        p.b(a, "writeFile begin fileName=wj_shpunx_v2.jd");
        p.b(a, "writeFile begin =" + str);
        if (jd.wjlogin_sdk.common.b.a() == null) {
            return;
        }
        if (str == null) {
            str = "";
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = jd.wjlogin_sdk.common.b.a().openFileOutput(d, 0);
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
                            p.a("UserInfoStoreUtil writeFile ", e2);
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
            p.a("UserInfoStoreUtil writeFile ", e4);
        }
    }

    public static String a(String str, String str2) {
        String string = c().getString(str, str2);
        if (TextUtils.isEmpty(string) && e.f19941n.equals(str) && jd.wjlogin_sdk.config.a.c().j()) {
            string = d();
        }
        return TextUtils.isEmpty(string) ? str2 : string;
    }

    public static String b(String str, String str2) {
        String d2 = (TextUtils.isEmpty(str2) && e.f19941n.equals(str) && jd.wjlogin_sdk.config.a.c().j()) ? d() : str2;
        return TextUtils.isEmpty(d2) ? str2 : d2;
    }

    public static boolean a(String str, boolean z) {
        return c().getBoolean(str, z);
    }

    public static boolean a(String str) {
        return c().contains(str);
    }

    public static void a() {
        File fileStreamPath = jd.wjlogin_sdk.common.b.a().getFileStreamPath(d);
        if (fileStreamPath.exists()) {
            p.b(a, "fileIsExist fileName = true");
            if (fileStreamPath.isFile()) {
                fileStreamPath.delete();
            }
        }
    }
}
