package com.jdjr.risk.device.c;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class ai {
    public static String a() {
        File[] listFiles;
        try {
            ArrayList arrayList = new ArrayList();
            File file = new File("/system/fonts");
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    arrayList.add(file2.getName());
                }
            }
            StringBuilder sb = new StringBuilder();
            Collections.sort(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
            }
            return com.jdjr.risk.util.b.c.a(sb.toString());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        try {
            return context.getResources().getConfiguration().locale.getLanguage();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            Bitmap bitmap = ((BitmapDrawable) WallpaperManager.getInstance(context).getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            StringBuilder sb = new StringBuilder();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.update(byteArray);
                for (byte b : messageDigest.digest()) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(i2));
                }
            } catch (NoSuchAlgorithmException unused) {
            }
            return sb.toString();
        } catch (Exception unused2) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0053 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b() {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r0]
            r2 = 0
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L3e java.io.FileNotFoundException -> L45
            java.lang.String r5 = "/sys/class/switch/h2w/state"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L3e java.io.FileNotFoundException -> L45
            int r0 = r4.read(r1, r2, r0)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            r3.<init>(r1, r2, r0)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            java.lang.String r0 = r3.trim()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            int r0 = r0.intValue()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e java.io.FileNotFoundException -> L30
            r4.close()     // Catch: java.io.IOException -> L26
            goto L51
        L26:
            r1 = move-exception
            r1.printStackTrace()
            goto L51
        L2b:
            r0 = move-exception
            r3 = r4
            goto L33
        L2e:
            r3 = r4
            goto L3f
        L30:
            r3 = r4
            goto L46
        L32:
            r0 = move-exception
        L33:
            if (r3 == 0) goto L3d
            r3.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r1 = move-exception
            r1.printStackTrace()
        L3d:
            throw r0
        L3e:
        L3f:
            if (r3 == 0) goto L50
            r3.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L45:
        L46:
            if (r3 == 0) goto L50
            r3.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r0 = move-exception
            r0.printStackTrace()
        L50:
            r0 = 0
        L51:
            if (r0 == 0) goto L54
            r2 = 1
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.ai.b():boolean");
    }

    public static float c(Context context) {
        try {
            return context.getResources().getConfiguration().fontScale;
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static String c() {
        try {
            return BaseInfo.isFingerprintAvailable() + "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static int d(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException unused) {
            return 0;
        }
    }

    public static String d() {
        try {
            Object invoke = Class.forName("com.jingdong.common.utils.UserUtil").getMethod("getWJLoginHelper", null).invoke(null, null);
            return (String) invoke.getClass().getMethod("getPin", null).invoke(invoke, null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return "";
        }
    }

    public static int e(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode");
        } catch (Settings.SettingNotFoundException unused) {
            return -1;
        }
    }
}
