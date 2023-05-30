package com.jdjr.risk.device.c;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    */
    public static boolean b() {
        int i2;
        char[] cArr = new char[1024];
        FileReader fileReader = null;
        try {
            try {
                FileReader fileReader2 = new FileReader("/sys/class/switch/h2w/state");
                try {
                    i2 = Integer.valueOf(new String(cArr, 0, fileReader2.read(cArr, 0, 1024)).trim()).intValue();
                    try {
                        fileReader2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (FileNotFoundException unused) {
                    fileReader = fileReader2;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    i2 = 0;
                    if (i2 == 0) {
                    }
                } catch (Exception unused2) {
                    fileReader = fileReader2;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    i2 = 0;
                    if (i2 == 0) {
                    }
                } catch (Throwable th) {
                    th = th;
                    fileReader = fileReader2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused3) {
            } catch (Exception unused4) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            i2 = 0;
            if (i2 == 0) {
            }
        }
        return i2 == 0;
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
