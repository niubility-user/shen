package com.tencent.smtt.sdk.stat;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: classes9.dex */
public class MttLoader {
    public static final String CHANNEL_ID = "ChannelID";
    public static final String ENTRY_ID = "entryId";
    @Deprecated
    public static final String KEY_ACTIVITY_NAME = "KEY_ACT";
    @Deprecated
    public static final String KEY_APP_NAME = "KEY_APPNAME";
    public static final String KEY_EUSESTAT = "KEY_EUSESTAT";
    @Deprecated
    public static final String KEY_PACKAGE = "KEY_PKG";
    public static final String KEY_PID = "KEY_PID";
    public static final String MTT_ACTION = "com.tencent.QQBrowser.action.VIEW";
    public static final String MTT_ACTION_SP = "com.tencent.QQBrowser.action.VIEWSP";
    public static final String PID_ARTICLE_NEWS = "21272";
    public static final String PID_MOBILE_QQ = "50079";
    public static final String PID_QQPIM = "50190";
    public static final String PID_QZONE = "10494";
    public static final String PID_WECHAT = "10318";
    public static final String POS_ID = "PosID";
    public static final String QQBROWSER_DIRECT_DOWNLOAD_URL = "https://mdc.html5.qq.com/d/directdown.jsp?channel_id=50079";
    public static final String QQBROWSER_DOWNLOAD_URL = "https://mdc.html5.qq.com/mh?channel_id=50079&u=";
    public static final String QQBROWSER_PARAMS_FROME = ",from=";
    public static final String QQBROWSER_PARAMS_PACKAGENAME = ",packagename=";
    public static final String QQBROWSER_PARAMS_PD = ",product=";
    public static final String QQBROWSER_PARAMS_VERSION = ",version=";
    public static final String QQBROWSER_SCHEME = "mttbrowser://url=";
    public static final int RESULT_INVALID_CONTEXT = 3;
    public static final int RESULT_INVALID_URL = 2;
    public static final int RESULT_NOT_INSTALL_QQBROWSER = 4;
    public static final int RESULT_OK = 0;
    public static final int RESULT_QQBROWSER_LOW = 5;
    public static final int RESULT_UNKNOWN = 1;
    public static final String STAT_KEY = "StatKey";

    /* loaded from: classes9.dex */
    public static class BrowserInfo {
        public int browserType = -1;
        public int ver = -1;
        public String quahead = "";
        public String vn = "0";
        public String packageName = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class a {
        public String a;
        public String b;

        private a() {
            this.a = "";
            this.b = "";
        }
    }

    private static int a(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals("com.tencent.mobileqq")) {
            return 13;
        }
        if (str.equals("com.qzone")) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        return str.equals("com.tencent.mm") ? 24 : 26;
    }

    private static Uri a(Context context, String str) {
        return Uri.fromFile(new File(str));
    }

    private static a a(Context context, Uri uri) {
        Intent intent = new Intent(MTT_ACTION);
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        a aVar = new a();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                aVar.a = activityInfo.name;
                aVar.b = activityInfo.packageName;
                return aVar;
            } else if (str.contains("com.tencent.qbx")) {
                ActivityInfo activityInfo2 = resolveInfo.activityInfo;
                aVar.a = activityInfo2.name;
                aVar.b = activityInfo2.packageName;
            }
        }
        return aVar;
    }

    private static String a(Certificate certificate) throws CertificateEncodingException {
        byte[] encoded = certificate.getEncoded();
        int length = encoded.length;
        char[] cArr = new char[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            byte b = encoded[i2];
            int i3 = (b >> 4) & 15;
            int i4 = i2 * 2;
            cArr[i4] = (char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48);
            int i5 = b & 15;
            cArr[i4 + 1] = (char) (i5 >= 10 ? (i5 + 97) - 10 : i5 + 48);
        }
        return new String(cArr);
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf("://");
        int indexOf2 = trim.toLowerCase().indexOf(46);
        if (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) {
            return trim.toLowerCase().contains("://");
        }
        return false;
    }

    public static BrowserInfo getBrowserInfo(Context context) {
        int i2;
        boolean z = context.getApplicationContext().getSharedPreferences("x5_proxy_setting", 0).getBoolean("qb_install_status", false);
        BrowserInfo browserInfo = new BrowserInfo();
        if (z) {
            return browserInfo;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                browserInfo.browserType = 2;
                browserInfo.packageName = TbsConfig.APP_QB;
                browserInfo.quahead = "ADRQB_";
                if (packageInfo != null && (i2 = packageInfo.versionCode) > 420000) {
                    browserInfo.ver = i2;
                    browserInfo.quahead += packageInfo.versionName.replaceAll("\\.", "");
                    browserInfo.vn = packageInfo.versionName.replaceAll("\\.", "");
                    return browserInfo;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            try {
                try {
                    try {
                        try {
                            try {
                                packageInfo = packageManager.getPackageInfo("com.tencent.qbx", 0);
                                browserInfo.browserType = 0;
                                browserInfo.packageName = "com.tencent.qbx";
                                browserInfo.quahead = "ADRQBX_";
                            } catch (PackageManager.NameNotFoundException unused2) {
                                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                                browserInfo.packageName = TbsConfig.APP_QB;
                                browserInfo.browserType = 2;
                                browserInfo.quahead = "ADRQB_";
                            }
                        } catch (Exception unused3) {
                            a a2 = a(context, Uri.parse(QQBROWSER_DOWNLOAD_URL));
                            if (a2 != null && !TextUtils.isEmpty(a2.b)) {
                                PackageInfo packageInfo2 = packageManager.getPackageInfo(a2.b, 0);
                                try {
                                    browserInfo.packageName = a2.b;
                                    browserInfo.browserType = 2;
                                    browserInfo.quahead = "ADRQB_";
                                } catch (Exception unused4) {
                                }
                                packageInfo = packageInfo2;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException unused5) {
                        packageInfo = packageManager.getPackageInfo("com.tencent.qbx5", 0);
                        browserInfo.browserType = 1;
                        browserInfo.packageName = "com.tencent.qbx5";
                        browserInfo.quahead = "ADRQBX5_";
                    }
                } catch (Exception unused6) {
                }
            } catch (PackageManager.NameNotFoundException unused7) {
                packageInfo = packageManager.getPackageInfo("com.tencent.mtt.x86", 0);
                browserInfo.packageName = "com.tencent.mtt.x86";
                browserInfo.browserType = 2;
                browserInfo.quahead = "ADRQB_";
            }
            if (packageInfo != null) {
                browserInfo.ver = packageInfo.versionCode;
                browserInfo.quahead += packageInfo.versionName.replaceAll("\\.", "");
                browserInfo.vn = packageInfo.versionName.replaceAll("\\.", "");
            }
        } catch (Exception unused8) {
        }
        return browserInfo;
    }

    public static String getDownloadUrlWithQb(String str) {
        try {
            return QQBROWSER_DOWNLOAD_URL + URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return QQBROWSER_DOWNLOAD_URL;
        }
    }

    public static String getValidQBUrl(Context context, String str) {
        if (str.toLowerCase().startsWith("qb://")) {
            boolean z = false;
            BrowserInfo browserInfo = getBrowserInfo(context);
            int i2 = browserInfo.browserType;
            if (i2 == -1 || (i2 == 2 && browserInfo.ver < 33)) {
                z = true;
            }
            if (z) {
                return getDownloadUrlWithQb(str);
            }
        }
        return str;
    }

    public static boolean isBrowserInstalled(Context context) {
        return getBrowserInfo(context).browserType != -1;
    }

    public static boolean isBrowserInstalledEx(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        boolean z = false;
        try {
            if (Long.valueOf(browserInfo.vn).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        if (browserInfo.ver >= 601500) {
            return true;
        }
        return z;
    }

    public static boolean isGreatBrowserVer(Context context, long j2, long j3) {
        boolean z = false;
        try {
            if (Long.valueOf(getBrowserInfo(context).vn).longValue() >= j2) {
                z = true;
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        if (r5.ver >= j3) {
            return true;
        }
        return z;
    }

    public static boolean isSupportQBScheme(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        int i2 = browserInfo.browserType;
        if (i2 == -1) {
            return false;
        }
        return i2 != 2 || browserInfo.ver >= 42;
    }

    public static boolean isSupportingTbsTips(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        return browserInfo.browserType == 2 && browserInfo.ver >= 580000;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0072, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0074, code lost:
        r1 = r0.b;
        r0 = r0.a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b4, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00c8, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0106 A[Catch: ActivityNotFoundException -> 0x012f, TryCatch #1 {ActivityNotFoundException -> 0x012f, blocks: (B:75:0x00f6, B:77:0x0106, B:78:0x012a), top: B:85:0x00f6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int loadUrl(Context context, String str, HashMap<String, String> hashMap, WebView webView) {
        a a2;
        String str2;
        String str3;
        String str4;
        Set<String> keySet;
        if (context == null) {
            return 3;
        }
        if (!a(str)) {
            str = "http://" + str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return 2;
            }
            BrowserInfo browserInfo = getBrowserInfo(context);
            int i2 = browserInfo.browserType;
            if (i2 == -1) {
                return 4;
            }
            if (i2 == 2 && browserInfo.ver < 33) {
                return 5;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            int i3 = browserInfo.browserType;
            try {
                if (i3 != 2) {
                    if (i3 == 1) {
                        int i4 = browserInfo.ver;
                        if (i4 != 1) {
                            str2 = i4 == 2 ? "com.tencent.qbx5.SplashActivity" : "com.tencent.qbx5.MainActivity";
                        }
                        intent.setClassName("com.tencent.qbx5", str2);
                    } else if (i3 == 0) {
                        int i5 = browserInfo.ver;
                        if (i5 >= 4 && i5 <= 6) {
                            intent.setClassName("com.tencent.qbx", "com.tencent.qbx.SplashActivity");
                        } else if (i5 > 6) {
                            intent = new Intent(MTT_ACTION);
                            a2 = a(context, parse);
                            if (a2 != null) {
                            }
                        }
                    } else {
                        intent = new Intent(MTT_ACTION);
                        a2 = a(context, parse);
                        if (a2 != null) {
                        }
                    }
                    intent.setData(parse);
                    if (hashMap != null) {
                        while (r8.hasNext()) {
                        }
                    }
                    intent.putExtra("loginType", a(context));
                    intent.addFlags(268435456);
                    if (webView != null) {
                    }
                    context.startActivity(intent);
                    return 0;
                }
                int i6 = browserInfo.ver;
                str3 = TbsConfig.APP_QB;
                if (i6 >= 33 && i6 <= 39) {
                    str4 = "com.tencent.mtt.MainActivity";
                } else if (i6 < 40 || i6 > 45) {
                    if (i6 >= 46) {
                        intent = new Intent(MTT_ACTION);
                        a2 = a(context, parse);
                        if (a2 != null) {
                        }
                    }
                    intent.setData(parse);
                    if (hashMap != null && (keySet = hashMap.keySet()) != null) {
                        for (String str5 : keySet) {
                            String str6 = hashMap.get(str5);
                            if (!TextUtils.isEmpty(str6)) {
                                intent.putExtra(str5, str6);
                            }
                        }
                    }
                    intent.putExtra("loginType", a(context));
                    intent.addFlags(268435456);
                    if (webView != null) {
                        intent.putExtra("AnchorPoint", new Point(webView.getScrollX(), webView.getScrollY()));
                        intent.putExtra("ContentSize", new Point(webView.getContentWidth(), webView.getContentHeight()));
                    }
                    context.startActivity(intent);
                    return 0;
                } else {
                    str4 = "com.tencent.mtt.SplashActivity";
                }
                intent.putExtra("loginType", a(context));
                intent.addFlags(268435456);
                if (webView != null) {
                }
                context.startActivity(intent);
                return 0;
            } catch (ActivityNotFoundException unused) {
                return 4;
            }
            intent.setClassName(str3, str4);
            intent.setData(parse);
            if (hashMap != null) {
            }
        } catch (Exception unused2) {
            return 2;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|2|3|(3:7|8|(8:10|11|12|(1:14)|15|(1:17)(1:21)|18|19))|25|11|12|(0)|15|(0)(0)|18|19) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int loadUrl(Context context, String str, HashMap<String, String> hashMap, String str2, WebView webView) {
        boolean z;
        PackageManager packageManager;
        PackageInfo packageInfo;
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        try {
            packageManager = context.getPackageManager();
        } catch (Throwable unused) {
        }
        if (packageManager != null && (packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0)) != null) {
            if (packageInfo.versionCode > 601000) {
                z = true;
                String encode = URLEncoder.encode(str, "UTF-8");
                if (z) {
                    str = encode;
                }
                z2 = z;
                String str3 = !z2 ? ",encoded=1" : "";
                sb.append(QQBROWSER_SCHEME);
                sb.append(str);
                sb.append(QQBROWSER_PARAMS_PD);
                sb.append("TBS");
                sb.append(QQBROWSER_PARAMS_PACKAGENAME);
                sb.append(context.getPackageName());
                sb.append(QQBROWSER_PARAMS_FROME);
                sb.append(str2);
                sb.append(QQBROWSER_PARAMS_VERSION);
                sb.append(TbsConfig.TBS_SDK_VERSIONNAME);
                sb.append(str3);
                return loadUrl(context, sb.toString(), hashMap, webView);
            }
        }
        z = false;
        String encode2 = URLEncoder.encode(str, "UTF-8");
        if (z) {
        }
        z2 = z;
        if (!z2) {
        }
        sb.append(QQBROWSER_SCHEME);
        sb.append(str);
        sb.append(QQBROWSER_PARAMS_PD);
        sb.append("TBS");
        sb.append(QQBROWSER_PARAMS_PACKAGENAME);
        sb.append(context.getPackageName());
        sb.append(QQBROWSER_PARAMS_FROME);
        sb.append(str2);
        sb.append(QQBROWSER_PARAMS_VERSION);
        sb.append(TbsConfig.TBS_SDK_VERSIONNAME);
        sb.append(str3);
        return loadUrl(context, sb.toString(), hashMap, webView);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007d A[Catch: Exception -> 0x00a9, TryCatch #0 {Exception -> 0x00a9, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x0010, B:8:0x0014, B:10:0x001a, B:12:0x002c, B:13:0x0030, B:15:0x0046, B:19:0x0050, B:22:0x0059, B:24:0x0060, B:26:0x006a, B:28:0x006e, B:29:0x0071, B:32:0x007d, B:34:0x00a0, B:35:0x00a5), top: B:40:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean openDocWithQb(Context context, String str, int i2, String str2, String str3, HashMap<String, String> hashMap, Bundle bundle) {
        Uri a2;
        String str4;
        Set<String> keySet;
        try {
            Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
            if (hashMap != null && (keySet = hashMap.keySet()) != null) {
                for (String str5 : keySet) {
                    String str6 = hashMap.get(str5);
                    if (!TextUtils.isEmpty(str6)) {
                        intent.putExtra(str5, str6);
                    }
                }
            }
            new File(str);
            intent.putExtra("key_reader_sdk_id", 3);
            intent.putExtra("key_reader_sdk_type", i2);
            if (!TextUtils.isEmpty(str3)) {
                intent.putExtra("big_brother_source_key", str3);
            }
            if (i2 != 0) {
                str4 = i2 == 1 ? "key_reader_sdk_url" : "key_reader_sdk_path";
                intent.putExtra("key_reader_sdk_format", str2);
                if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(1);
                }
                intent.addFlags(268435456);
                a2 = a(context, str);
                if (a2 != null) {
                    return false;
                }
                intent.setDataAndType(a2, "mtt/" + str2);
                intent.putExtra("loginType", a(context.getApplicationContext()));
                if (bundle != null) {
                    intent.putExtra("key_reader_sdk_extrals", bundle);
                }
                context.startActivity(intent);
                return true;
            }
            intent.putExtra(str4, str);
            intent.putExtra("key_reader_sdk_format", str2);
            if (context != null) {
                intent.addFlags(1);
            }
            intent.addFlags(268435456);
            a2 = a(context, str);
            if (a2 != null) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean openDocWithQb(Context context, String str, int i2, String str2, HashMap<String, String> hashMap) {
        return openDocWithQb(context, str, i2, str2, hashMap, null);
    }

    public static boolean openDocWithQb(Context context, String str, int i2, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        return openDocWithQb(context, str, i2, str2, "", hashMap, null);
    }

    public static boolean openVideoWithQb(Context context, String str, HashMap<String, String> hashMap) {
        boolean z;
        Set<String> keySet;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(parse, "video/*");
        if (hashMap != null && (keySet = hashMap.keySet()) != null) {
            for (String str2 : keySet) {
                String str3 = hashMap.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    intent.putExtra(str2, str3);
                }
            }
        }
        try {
            intent.putExtra("loginType", a(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (!z) {
            try {
                intent.setComponent(null);
                context.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
        if (r2 == null) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean verifySignature(File file) {
        JarFile jarFile;
        JarEntry jarEntry;
        InputStream inputStream = null;
        try {
            jarFile = new JarFile(file);
            try {
                jarEntry = jarFile.getJarEntry("AndroidManifest.xml");
            } catch (Throwable unused) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
            }
        } catch (Throwable unused3) {
            jarFile = null;
        }
        if (jarEntry == null) {
            try {
                jarFile.close();
            } catch (IOException unused4) {
            }
            return false;
        }
        byte[] bArr = new byte[8192];
        inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, 8192) != -1);
        inputStream.close();
        Certificate[] certificates = jarEntry.getCertificates();
        if (certificates.length < 1) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused5) {
                }
            }
            try {
                jarFile.close();
            } catch (IOException unused6) {
            }
            return false;
        }
        String a2 = a(certificates[0]);
        if (a2 != null) {
            if (a2.equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused7) {
                    }
                }
                try {
                    jarFile.close();
                } catch (IOException unused8) {
                }
                return true;
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused9) {
            }
        }
        try {
            jarFile.close();
        } catch (IOException unused10) {
            return false;
        }
    }
}
