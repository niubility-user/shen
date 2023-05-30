package com.tencent.smtt.sdk.stat;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsCoreLoadStat;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.h;
import com.tencent.smtt.utils.l;
import com.tencent.smtt.utils.o;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import jd.wjlogin_sdk.util.e;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class b {
    public static byte[] a;
    private static ThirdAppInfoNew b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, String> f17875c;

    static {
        try {
            a = "65dRa93L".getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(Context context) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        try {
            jSONObject = new JSONObject();
        } catch (Throwable unused) {
        }
        try {
            jSONObject.put("cpuabi", com.tencent.smtt.utils.b.c() ? "64" : "32");
        } catch (Throwable unused2) {
            jSONObject2 = jSONObject;
            jSONObject = jSONObject2;
            if (jSONObject != null) {
            }
        }
        return jSONObject != null ? "" : jSONObject.toString();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:3|4|(14:(1:70)|10|11|12|13|14|(2:15|(1:17)(1:18))|19|(1:(1:36)(2:37|38))(1:21)|22|23|(2:25|26)|28|29)(1:8)|9|10|11|12|13|14|(3:15|(0)(0)|17)|19|(0)(0)|22|23|(0)|28|29) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0085, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0086, code lost:
        com.tencent.smtt.utils.TbsLog.i(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0094, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0095, code lost:
        r2 = r7;
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0098, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0099, code lost:
        r2 = r7;
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009c, code lost:
        r7 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a3, code lost:
        com.tencent.smtt.utils.TbsLog.i(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a6, code lost:
        if (r2 != null) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a8, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ac, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ad, code lost:
        com.tencent.smtt.utils.TbsLog.i(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b0, code lost:
        if (r3 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b2, code lost:
        r3.close();
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d3, code lost:
        if (r2 != null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d5, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d9, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00da, code lost:
        com.tencent.smtt.utils.TbsLog.i(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00dd, code lost:
        if (r3 != null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00df, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00e3, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00e4, code lost:
        com.tencent.smtt.utils.TbsLog.i(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e8, code lost:
        throw r7;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0044 A[Catch: all -> 0x0094, Exception -> 0x0098, LOOP:0: B:17:0x003d->B:19:0x0044, LOOP_END, TryCatch #11 {Exception -> 0x0098, all -> 0x0094, blocks: (B:16:0x003b, B:17:0x003d, B:19:0x0044, B:20:0x005d, B:24:0x0068, B:25:0x0077), top: B:85:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008b A[Catch: IOException -> 0x008f, TRY_ENTER, TRY_LEAVE, TryCatch #7 {IOException -> 0x008f, blocks: (B:32:0x008b, B:53:0x00b2), top: B:81:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x005d A[EDGE_INSN: B:86:0x005d->B:20:0x005d BREAK  A[LOOP:0: B:17:0x003d->B:19:0x0044], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(HttpURLConnection httpURLConnection, String str, boolean z) {
        InputStream inputStream;
        String contentEncoding;
        InputStream inflaterInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        int read;
        String str2 = "";
        byte[] bArr2 = 0;
        try {
        } catch (IOException e2) {
            TbsLog.i(e2);
        }
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                contentEncoding = httpURLConnection.getContentEncoding();
            } catch (Exception e3) {
                e = e3;
                inputStream = null;
            } catch (Throwable th) {
                th = th;
                inputStream = null;
            }
            if (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip")) {
                if (contentEncoding != null && contentEncoding.equalsIgnoreCase("deflate")) {
                    inflaterInputStream = new InflaterInputStream(inputStream, new Inflater(true));
                }
                byteArrayOutputStream = new ByteArrayOutputStream();
                bArr = new byte[128];
                while (true) {
                    read = inputStream.read(bArr);
                    if (read != -1) {
                        break;
                    }
                    TbsLog.i("HttpUtils", "getResponseFromConnection len is " + read);
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                bArr2 = byteArrayOutputStream.toByteArray();
                if (bArr2.length != 7) {
                    if (z) {
                        bArr2 = h.a().c(bArr2);
                        str2 = new String(bArr2);
                    } else {
                        str2 = new String(h.b(bArr2, str));
                    }
                }
                byteArrayOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                    bArr2 = bArr2;
                }
                TbsLog.i("HttpUtils", "getResponseFromConnection,response=" + str2 + ";isUseRSA=" + z);
                return str2;
            }
            inflaterInputStream = new GZIPInputStream(inputStream);
            inputStream = inflaterInputStream;
            byteArrayOutputStream = new ByteArrayOutputStream();
            bArr = new byte[128];
            while (true) {
                read = inputStream.read(bArr);
                if (read != -1) {
                }
                TbsLog.i("HttpUtils", "getResponseFromConnection len is " + read);
                byteArrayOutputStream.write(bArr, 0, read);
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            if (bArr2.length != 7) {
            }
            byteArrayOutputStream.close();
            if (inputStream != null) {
            }
            TbsLog.i("HttpUtils", "getResponseFromConnection,response=" + str2 + ";isUseRSA=" + z);
            return str2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void a(ThirdAppInfoNew thirdAppInfoNew, Context context) {
        String str;
        String str2;
        StringBuilder sb;
        String str3;
        String sb2;
        String str4;
        com.tencent.smtt.utils.b.b(context, thirdAppInfoNew.sGuid);
        thirdAppInfoNew.sCpu = com.tencent.smtt.utils.b.a();
        if (Build.VERSION.SDK_INT < 8) {
            return;
        }
        JSONObject jSONObject = null;
        if (a == null) {
            try {
                a = "65dRa93L".getBytes("utf-8");
            } catch (UnsupportedEncodingException unused) {
                a = null;
                TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
            }
        }
        if (a == null) {
            sb2 = "Post failed -- POST_DATA_KEY is null!";
        } else {
            String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
            if (TextUtils.isEmpty(string)) {
                str = "";
                str2 = str;
            } else {
                str2 = string.substring(0, string.indexOf(ContainerUtils.FIELD_DELIMITER));
                str = string.substring(string.indexOf(ContainerUtils.FIELD_DELIMITER) + 1, string.length());
            }
            boolean z = TextUtils.isEmpty(str2) || str2.length() != 96 || TextUtils.isEmpty(str) || str.length() != 24;
            try {
                o a2 = o.a();
                if (z) {
                    str4 = a2.b() + h.a().b();
                } else {
                    str4 = a2.e() + str2;
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str4).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", "close");
                }
                try {
                    jSONObject = b(thirdAppInfoNew, context);
                } catch (Exception e2) {
                    TbsLog.i(e2);
                }
                if (jSONObject != null) {
                    try {
                        TbsLog.i("sdkreport", "Post jsonData.toString() is " + jSONObject.toString());
                        byte[] bytes = jSONObject.toString().getBytes("utf-8");
                        byte[] a3 = z ? h.a().a(bytes) : h.a(bytes, str);
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(a3.length));
                        try {
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            outputStream.write(a3);
                            outputStream.flush();
                            if (httpURLConnection.getResponseCode() == 200) {
                                TbsLog.i("SDKPVReport", "Post successful!");
                                a(context, a(httpURLConnection, str, z));
                            } else {
                                TbsLog.e("SDKPVReport", "Post failed -- not 200 code is " + httpURLConnection.getResponseCode());
                                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo.setErrorCode(126);
                                tbsLogInfo.setFailDetail("" + httpURLConnection.getResponseCode());
                                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_PV_UPLOAD_STAT, tbsLogInfo);
                            }
                            return;
                        } catch (Throwable th) {
                            TbsLog.e("SDKPVReport", "Post failed -- exceptions:" + th.getMessage());
                            TbsLogReport.TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(context).tbsLogInfo();
                            tbsLogInfo2.setErrorCode(126);
                            tbsLogInfo2.setFailDetail(th);
                            TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_PV_UPLOAD_STAT, tbsLogInfo2);
                            return;
                        }
                    } catch (Throwable unused2) {
                        return;
                    }
                }
                sb2 = "post -- jsonData is null!";
            } catch (IOException e3) {
                e = e3;
                sb = new StringBuilder();
                str3 = "Post failed -- IOException:";
                sb.append(str3);
                sb.append(e);
                sb2 = sb.toString();
                TbsLog.e("sdkreport", sb2);
            } catch (AssertionError e4) {
                e = e4;
                sb = new StringBuilder();
                str3 = "Post failed -- AssertionError:";
                sb.append(str3);
                sb.append(e);
                sb2 = sb.toString();
                TbsLog.e("sdkreport", sb2);
            } catch (Exception e5) {
                e = e5;
                sb = new StringBuilder();
                str3 = "Post failed -- Exception:";
                sb.append(str3);
                sb.append(e);
                sb2 = sb.toString();
                TbsLog.e("sdkreport", sb2);
            } catch (NoClassDefFoundError e6) {
                e = e6;
                sb = new StringBuilder();
                str3 = "Post failed -- NoClassDefFoundError:";
                sb.append(str3);
                sb.append(e);
                sb2 = sb.toString();
                TbsLog.e("sdkreport", sb2);
            }
        }
        TbsLog.e("sdkreport", sb2);
    }

    private static void a(Context context, int i2) {
        TbsLog.i("HttpUtils", "updatePVConfig command is " + i2);
        if (i2 == 1) {
            TbsPVConfig.getInstance(context).clear();
        }
        if (i2 == 2) {
            TbsPVConfig.getInstance(context).update(f17875c);
        }
    }

    private static void a(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                a(context, 1);
                return;
            }
            f17875c = new HashMap();
            for (String str2 : str.split(DYConstants.DY_REGEX_VERTICAL_LINE)) {
                try {
                    String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split.length == 2) {
                        a(context, split[0], split[1]);
                    }
                } catch (Exception e2) {
                    TbsLog.i(e2);
                }
            }
            int c2 = c(context);
            TbsLog.i("HttpUtils", "readResponse, after processSwitchKeyValue mMapFromResponse is " + f17875c.toString() + " commandForUpdatePVC is " + c2);
            a(context, c2);
        } catch (Exception e3) {
            TbsLog.i(e3);
        }
    }

    private static void a(Context context, String str, String str2) {
        TbsLog.i("HttpUtils", "PV Config Receive. Key: " + str + ", value: " + str2);
        if ("reset".equals(str) && DYConstants.DY_TRUE.equals(str2)) {
            QbSdk.reset(context);
        } else if ("resetCfg24".equals(str) && DYConstants.DY_TRUE.equals(str2)) {
            d(context);
        } else {
            f17875c.put(str, str2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x010f, code lost:
        if (r14 != false) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b7 A[Catch: all -> 0x013d, TryCatch #2 {all -> 0x013d, blocks: (B:24:0x0073, B:26:0x00b7, B:27:0x00b9, B:29:0x00bd, B:31:0x00c8, B:33:0x00dc, B:35:0x00e2, B:37:0x00e6, B:39:0x00ec, B:40:0x00ee, B:42:0x00f4, B:44:0x00f8, B:46:0x00fe, B:47:0x0100, B:50:0x010d, B:52:0x0111, B:60:0x0121, B:62:0x012b, B:63:0x0133, B:53:0x0114, B:56:0x011a, B:30:0x00c2), top: B:72:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd A[Catch: all -> 0x013d, TryCatch #2 {all -> 0x013d, blocks: (B:24:0x0073, B:26:0x00b7, B:27:0x00b9, B:29:0x00bd, B:31:0x00c8, B:33:0x00dc, B:35:0x00e2, B:37:0x00e6, B:39:0x00ec, B:40:0x00ee, B:42:0x00f4, B:44:0x00f8, B:46:0x00fe, B:47:0x0100, B:50:0x010d, B:52:0x0111, B:60:0x0121, B:62:0x012b, B:63:0x0133, B:53:0x0114, B:56:0x011a, B:30:0x00c2), top: B:72:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c2 A[Catch: all -> 0x013d, TryCatch #2 {all -> 0x013d, blocks: (B:24:0x0073, B:26:0x00b7, B:27:0x00b9, B:29:0x00bd, B:31:0x00c8, B:33:0x00dc, B:35:0x00e2, B:37:0x00e6, B:39:0x00ec, B:40:0x00ee, B:42:0x00f4, B:44:0x00f8, B:46:0x00fe, B:47:0x0100, B:50:0x010d, B:52:0x0111, B:60:0x0121, B:62:0x012b, B:63:0x0133, B:53:0x0114, B:56:0x011a, B:30:0x00c2), top: B:72:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f4 A[Catch: all -> 0x013d, TryCatch #2 {all -> 0x013d, blocks: (B:24:0x0073, B:26:0x00b7, B:27:0x00b9, B:29:0x00bd, B:31:0x00c8, B:33:0x00dc, B:35:0x00e2, B:37:0x00e6, B:39:0x00ec, B:40:0x00ee, B:42:0x00f4, B:44:0x00f8, B:46:0x00fe, B:47:0x0100, B:50:0x010d, B:52:0x0111, B:60:0x0121, B:62:0x012b, B:63:0x0133, B:53:0x0114, B:56:0x011a, B:30:0x00c2), top: B:72:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012b A[Catch: all -> 0x013d, TryCatch #2 {all -> 0x013d, blocks: (B:24:0x0073, B:26:0x00b7, B:27:0x00b9, B:29:0x00bd, B:31:0x00c8, B:33:0x00dc, B:35:0x00e2, B:37:0x00e6, B:39:0x00ec, B:40:0x00ee, B:42:0x00f4, B:44:0x00f8, B:46:0x00fe, B:47:0x0100, B:50:0x010d, B:52:0x0111, B:60:0x0121, B:62:0x012b, B:63:0x0133, B:53:0x0114, B:56:0x011a, B:30:0x00c2), top: B:72:0x0073 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(Context context, String str, String str2, String str3, int i2, boolean z, long j2, boolean z2) {
        String str4;
        ThirdAppInfoNew thirdAppInfoNew;
        String a2;
        String f2;
        String d;
        String e2;
        String g2;
        if (QbSdk.getSettings() != null && QbSdk.getSettings().containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) && QbSdk.getSettings().get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals(DYConstants.DY_FALSE)) {
            TbsLog.i("sdkreport", "[HttpUtils.doReport] -- SET_SENDREQUEST_AND_UPLOAD is false");
            return;
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if ("com.tencent.mobileqq".equals(applicationInfo.packageName)) {
                str4 = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0).versionName;
                try {
                    if (!TextUtils.isEmpty(QbSdk.getQQBuildNumber())) {
                        str4 = str4 + OrderISVUtil.MONEY_DECIMAL + QbSdk.getQQBuildNumber();
                    }
                } catch (Exception e3) {
                    e = e3;
                    TbsLog.i(e);
                    thirdAppInfoNew = new ThirdAppInfoNew();
                    thirdAppInfoNew.sAppName = context.getApplicationContext().getApplicationInfo().packageName;
                    o.a(context);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                    thirdAppInfoNew.sTime = simpleDateFormat.format(Calendar.getInstance().getTime());
                    thirdAppInfoNew.sVersionCode = com.tencent.smtt.utils.b.b(context);
                    a2 = com.tencent.smtt.utils.b.a(context, TbsDownloader.TBS_METADATA);
                    if (!TextUtils.isEmpty(a2)) {
                    }
                    thirdAppInfoNew.sGuid = str;
                    if (z) {
                    }
                    thirdAppInfoNew.sLc = str3;
                    f2 = com.tencent.smtt.utils.b.f(context);
                    d = com.tencent.smtt.utils.b.d(context);
                    e2 = com.tencent.smtt.utils.b.e(context);
                    g2 = com.tencent.smtt.utils.b.g(context);
                    if (d != null) {
                        thirdAppInfoNew.sImei = d;
                    }
                    if (e2 != null) {
                        thirdAppInfoNew.sImsi = e2;
                    }
                    if (!TextUtils.isEmpty(g2)) {
                    }
                    if (f2 != null) {
                        thirdAppInfoNew.sMac = f2;
                    }
                    thirdAppInfoNew.iPv = i2;
                    if (TbsShareManager.isThirdPartyApp(context)) {
                    }
                    TbsLog.i(th);
                }
            } else {
                str4 = "";
            }
        } catch (Exception e4) {
            e = e4;
            str4 = "";
        }
        try {
            thirdAppInfoNew = new ThirdAppInfoNew();
            thirdAppInfoNew.sAppName = context.getApplicationContext().getApplicationInfo().packageName;
            o.a(context);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            thirdAppInfoNew.sTime = simpleDateFormat2.format(Calendar.getInstance().getTime());
            thirdAppInfoNew.sVersionCode = com.tencent.smtt.utils.b.b(context);
            a2 = com.tencent.smtt.utils.b.a(context, TbsDownloader.TBS_METADATA);
            if (!TextUtils.isEmpty(a2)) {
                thirdAppInfoNew.sMetaData = a2;
            }
            thirdAppInfoNew.sGuid = str;
            if (z) {
                thirdAppInfoNew.sQua2 = l.a(context);
            } else {
                thirdAppInfoNew.sQua2 = str2;
                thirdAppInfoNew.bIsSandboxMode = z2;
            }
            thirdAppInfoNew.sLc = str3;
            f2 = com.tencent.smtt.utils.b.f(context);
            d = com.tencent.smtt.utils.b.d(context);
            e2 = com.tencent.smtt.utils.b.e(context);
            g2 = com.tencent.smtt.utils.b.g(context);
            if (d != null && !"".equals(d)) {
                thirdAppInfoNew.sImei = d;
            }
            if (e2 != null && !"".equals(e2)) {
                thirdAppInfoNew.sImsi = e2;
            }
            if (!TextUtils.isEmpty(g2)) {
                thirdAppInfoNew.sAndroidID = g2;
            }
            if (f2 != null && !"".equals(f2)) {
                thirdAppInfoNew.sMac = f2;
            }
            thirdAppInfoNew.iPv = i2;
            if (TbsShareManager.isThirdPartyApp(context)) {
                thirdAppInfoNew.iCoreType = z ? 1 : 0;
                if (z && z2) {
                    thirdAppInfoNew.iCoreType = 3;
                }
                thirdAppInfoNew.sAppVersionName = str4;
                thirdAppInfoNew.sAppSignature = b(context);
                if (!z) {
                }
                b = thirdAppInfoNew;
                a(thirdAppInfoNew, context.getApplicationContext());
                return;
            }
            if (z) {
                thirdAppInfoNew.iCoreType = 1;
            } else {
                thirdAppInfoNew.iCoreType = 0;
            }
            thirdAppInfoNew.sAppVersionName = str4;
            thirdAppInfoNew.sAppSignature = b(context);
            if (!z) {
                thirdAppInfoNew.sWifiConnectedTime = j2;
                thirdAppInfoNew.localCoreVersion = QbSdk.getTbsVersion(context);
            }
            b = thirdAppInfoNew;
            a(thirdAppInfoNew, context.getApplicationContext());
            return;
        } catch (Throwable th) {
            TbsLog.i(th);
        }
        TbsLog.i(th);
    }

    private static boolean a(Map<String, String> map, Map<String, String> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!(entry.getValue() == null ? "" : entry.getValue()).equals(map2.get(entry.getKey()) != null ? map2.get(entry.getKey()) : "")) {
                return false;
            }
        }
        return true;
    }

    private static String b(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray != null) {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
                messageDigest.update(byteArray);
                byte[] digest = messageDigest.digest();
                if (digest != null) {
                    StringBuilder sb = new StringBuilder("");
                    if (digest != null && digest.length > 0) {
                        for (int i2 = 0; i2 < digest.length; i2++) {
                            String upperCase = Integer.toHexString(digest[i2] & 255).toUpperCase();
                            if (i2 > 0) {
                                sb.append(":");
                            }
                            if (upperCase.length() < 2) {
                                sb.append(0);
                            }
                            sb.append(upperCase);
                        }
                        return sb.toString();
                    }
                    return null;
                }
            }
        } catch (Exception e2) {
            TbsLog.i(e2);
        }
        return null;
    }

    private static JSONObject b(ThirdAppInfoNew thirdAppInfoNew, Context context) {
        String tid;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("APPNAME", thirdAppInfoNew.sAppName);
            jSONObject.put("TIME", thirdAppInfoNew.sTime);
            jSONObject.put("QUA2", thirdAppInfoNew.sQua2);
            jSONObject.put("LC", thirdAppInfoNew.sLc);
            jSONObject.put(e.d, thirdAppInfoNew.sGuid);
            jSONObject.put("IMEI", thirdAppInfoNew.sImei);
            jSONObject.put("IMSI", thirdAppInfoNew.sImsi);
            jSONObject.put("MAC", thirdAppInfoNew.sMac);
            jSONObject.put("PV", thirdAppInfoNew.iPv);
            jSONObject.put("CORETYPE", thirdAppInfoNew.iCoreType);
            jSONObject.put("APPVN", thirdAppInfoNew.sAppVersionName);
            jSONObject.put("APPMETADATA", thirdAppInfoNew.sMetaData);
            jSONObject.put("VERSION_CODE", thirdAppInfoNew.sVersionCode);
            jSONObject.put("CPU", thirdAppInfoNew.sCpu);
            String str = thirdAppInfoNew.sAppSignature;
            if (str == null) {
                str = "0";
            }
            jSONObject.put("SIGNATURE", str);
            String a2 = a(context);
            TbsLog.i("sdkreport", "addInfo is " + a2);
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("EXT_INFO", a2);
            }
            jSONObject.put("PROTOCOL_VERSION", 3);
            jSONObject.put("ANDROID_ID", thirdAppInfoNew.sAndroidID);
            jSONObject.put("HOST_COREVERSION", 0);
            jSONObject.put("DECOUPLE_COREVERSION", 0);
            jSONObject.put("WIFICONNECTEDTIME", thirdAppInfoNew.sWifiConnectedTime);
            jSONObject.put("CORE_EXIST", thirdAppInfoNew.localCoreVersion);
            int loadErrorCode = TbsCoreLoadStat.getLoadErrorCode();
            if (thirdAppInfoNew.localCoreVersion <= 0) {
                jSONObject.put("TBS_ERROR_CODE", TbsDownloadConfig.getInstance(context).getDownloadInterruptCode());
            } else {
                jSONObject.put("TBS_ERROR_CODE", loadErrorCode);
            }
            if (loadErrorCode == -1) {
                TbsLog.e("sdkreport", "ATTENTION: Load errorCode missed!");
            }
            try {
                if (QbSdk.getTID() != null) {
                    if (thirdAppInfoNew.sAppName.equals("com.tencent.mobileqq")) {
                        tid = QbSdk.getTID();
                    } else if (thirdAppInfoNew.sAppName.equals("com.tencent.mm")) {
                        tid = QbSdk.getTID();
                    }
                    jSONObject.put("TID", tid);
                    jSONObject.put("TIDTYPE", 0);
                }
            } catch (Exception unused) {
            }
            return jSONObject;
        } catch (Exception unused2) {
            TbsLog.e("sdkreport", "getPostData exception!");
            return null;
        }
    }

    private static int c(Context context) {
        Map<String, String> map = f17875c;
        if (map == null || map.size() == 0) {
            return 1;
        }
        Map<String, String> pVCLocal = TbsPVConfig.getInstance(context).getPVCLocal();
        TbsLog.i("HttpUtils", "getCommandForUpdatePVC, mMapPVCLocal is " + pVCLocal.toString());
        return (pVCLocal == null || pVCLocal.size() == 0 || !a(f17875c, pVCLocal)) ? 2 : 0;
    }

    private static void d(Context context) {
        SharedPreferences.Editor edit = TbsDownloadConfig.getInstance(context).mPreferences.edit();
        edit.putLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, 0L);
        edit.apply();
    }
}
