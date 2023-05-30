package com.laser.utils.app;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.laser.utils.common.DataConvertUtil;
import com.laser.utils.common.LogUtil;
import com.laser.utils.common.Utils;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/* loaded from: classes13.dex */
public final class AppUtil {
    private static long lastClickTime;

    private AppUtil() {
        throw new Error("Do not need instantiate!");
    }

    public static String getAppHash(String str) {
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                LogUtil.e("get callAppHash failure");
                return "";
            }
            Signature signature = packageInfo.signatures[0];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(signature.toByteArray());
            return DataConvertUtil.bytesToHexString(messageDigest.digest()).replace(":", "").toUpperCase();
        } catch (Exception e2) {
            LogUtil.e("get callAppHash failure:" + e2.getMessage());
            return "";
        }
    }

    public static String getAppSignatureInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("params must not be null");
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            Signature[] signatureArr = Utils.getApp().getPackageManager().getPackageInfo(str, 64).signatures;
            sb.append("SHA1:" + DataConvertUtil.bytesToHexString(MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray())) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            for (Signature signature : signatureArr) {
                sb.append(signature.toCharsString());
            }
            return sb.toString();
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e2) {
            LogUtil.e("get AppSignatureInfo failure:" + e2.getMessage());
            return null;
        }
    }

    public static String getClientVersionName() {
        try {
            return Utils.getApp().getPackageManager().getPackageInfo(Utils.getApp().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            LogUtil.e("get ClientVersionName failure:" + e2.getMessage());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getDomain(String str) {
        URL url;
        try {
            url = new URI(str).toURL();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            url = null;
            if (url != null) {
            }
        } catch (URISyntaxException e3) {
            e3.printStackTrace();
            url = null;
            if (url != null) {
            }
        }
        return url != null ? url.getHost() : "";
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024;
    }

    public static String getPackageName() {
        try {
            return Utils.getApp().getPackageName();
        } catch (Exception e2) {
            LogUtil.e("get PackageName failure:" + e2.getMessage());
            return "";
        }
    }

    public static int getVersionCode() {
        try {
            return Utils.getApp().getPackageManager().getPackageInfo(Utils.getApp().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            LogUtil.e("get VersionCode failure:" + e2.getMessage());
            return -1;
        }
    }

    public static boolean isDateCorrect(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        simpleDateFormat.setLenient(false);
        ParsePosition parsePosition = new ParsePosition(0);
        simpleDateFormat.parse(str, parsePosition);
        return parsePosition.getIndex() == str2.length();
    }

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - lastClickTime;
        if (0 >= j2 || j2 >= 500) {
            lastClickTime = currentTimeMillis;
            return false;
        }
        return true;
    }

    public static boolean isValidDate(String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3);
        try {
            return simpleDateFormat.parse(str).getTime() <= simpleDateFormat.parse(str2).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
