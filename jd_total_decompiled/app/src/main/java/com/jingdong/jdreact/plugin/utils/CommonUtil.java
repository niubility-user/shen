package com.jingdong.jdreact.plugin.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Base64;
import android.view.WindowManager;
import com.facebook.react.bridge.Callback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes14.dex */
public class CommonUtil {
    static final String TAG = "CommonUtil";
    private static SimpleDateFormat sDateFormat;
    private static int versionCode;
    private static String versionName;

    public static String bytes2hex(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static long dateStrToTimestamp(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sDateFormat.parse(str).getTime();
        } catch (Exception e2) {
            e2.toString();
            return 0L;
        }
    }

    public static String fileToBase64(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        String str = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            closeQuietly(fileInputStream2);
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                str = Base64.encodeToString(bArr, 0, fileInputStream.read(bArr), 0);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                closeQuietly(fileInputStream2);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.toString();
            closeQuietly(fileInputStream);
            return str;
        }
        closeQuietly(fileInputStream);
        return str;
    }

    public static String getAndroidId() {
        return JDReactHelper.newInstance().getAndroidId();
    }

    public static PackageInfo getPackageInfo() {
        try {
            Application application = JDReactHelper.newInstance().getApplication();
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int getScreenHeight() {
        try {
            return ((WindowManager) JDReactHelper.newInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay().getHeight();
        } catch (Exception e2) {
            LogUtil.e(TAG, e2.toString());
            return 0;
        }
    }

    public static int getScreenWidth() {
        try {
            return ((WindowManager) JDReactHelper.newInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay().getWidth();
        } catch (Exception e2) {
            LogUtil.e(TAG, e2.toString());
            return 0;
        }
    }

    public static int getVersionCode() {
        if (versionCode == 0) {
            PackageInfo packageInfo = getPackageInfo();
            versionCode = packageInfo == null ? 0 : packageInfo.versionCode;
        }
        return versionCode;
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            PackageInfo packageInfo = getPackageInfo();
            versionName = packageInfo == null ? "" : packageInfo.versionName;
        }
        return versionName;
    }

    public static int hexStrToInt(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return new BigInteger(str, 16).intValue();
        } catch (Exception e2) {
            e2.toString();
            return i2;
        }
    }

    public static void invokeCallback(Callback callback, Object... objArr) {
        if (callback != null) {
            try {
                callback.invoke(objArr);
            } catch (Exception e2) {
                e2.toString();
            }
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String timestampToDateStr(long j2) {
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sDateFormat.format(new Date(j2));
        } catch (Exception e2) {
            e2.toString();
            return null;
        }
    }
}
