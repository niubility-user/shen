package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.SSLHandshakeException;

/* loaded from: classes9.dex */
public class k {
    private static int d = 5;

    /* renamed from: e */
    private static int f17835e = 1;

    /* renamed from: f */
    private static final String[] f17836f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private boolean C;

    /* renamed from: g */
    private Context f17838g;

    /* renamed from: h */
    private String f17839h;

    /* renamed from: i */
    private String f17840i;

    /* renamed from: j */
    private String f17841j;

    /* renamed from: k */
    private File f17842k;

    /* renamed from: l */
    private long f17843l;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private HttpURLConnection t;
    private String u;
    private final TbsLogReport.TbsLogInfo v;
    private String w;
    private int x;
    private boolean y;
    private Handler z;

    /* renamed from: c */
    private boolean f17837c = false;

    /* renamed from: m */
    private int f17844m = 30000;

    /* renamed from: n */
    private int f17845n = 20000;
    private boolean A = false;
    private int B = d;
    String[] a = null;
    int b = 0;

    public k(Context context) throws NullPointerException {
        Context applicationContext = context.getApplicationContext();
        this.f17838g = applicationContext;
        this.v = TbsLogReport.getInstance(applicationContext).tbsLogInfo();
        this.u = "tbs_downloading_" + this.f17838g.getPackageName();
        File o = m.o(this.f17838g);
        this.f17842k = o;
        if (o == null) {
            throw new NullPointerException("TbsCorePrivateDir is null!");
        }
        f();
        this.w = null;
        this.x = -1;
    }

    private long a(long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.setDownConsumeTime(currentTimeMillis - j2);
        this.v.setDownloadSize(j3);
        return currentTimeMillis;
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private void a(int i2, String str, boolean z) {
        if (z || this.p > this.B) {
            this.v.setErrorCode(i2);
            this.v.setFailDetail(str);
        }
    }

    private void a(long j2) {
        this.p++;
        if (j2 <= 0) {
            try {
                j2 = m();
            } catch (Exception unused) {
                return;
            }
        }
        Thread.sleep(j2);
    }

    public static void a(Context context) {
        try {
            TbsLog.i(TbsDownloader.LOGTAG, "clearDecoupleDirOld #00");
            File a = m.a().a(context, context.getDir("tbs_64", 0));
            FileUtil.b(a);
            if (a != null) {
                TbsLog.i(TbsDownloader.LOGTAG, "clearDecoupleDirOld dir is " + a.getAbsolutePath());
            }
            File a2 = m.a().a(context, context.getDir("tbs", 0));
            FileUtil.b(a2);
            if (a2 != null) {
                TbsLog.i(TbsDownloader.LOGTAG, "clearDecoupleDirOld dir is " + a2.getAbsolutePath());
            }
        } catch (Throwable th) {
            TbsLog.i(TbsDownloader.LOGTAG, "clearDecoupleDirOld stack is " + Log.getStackTraceString(th));
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private void a(String str) throws Exception {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = this.t;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
        this.t = httpURLConnection2;
        httpURLConnection2.setRequestProperty("User-Agent", TbsDownloader.b(this.f17838g));
        this.t.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.f17845n);
        this.t.setReadTimeout(this.f17844m);
    }

    public static void b(Context context) {
        try {
            m.a();
            File o = m.o(context);
            new File(o, "x5.tbs").delete();
            new File(o, "x5.tbs.temp").delete();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:149:0x0085, code lost:
        if (r10 != r8) goto L150;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean b(boolean z, boolean z2) {
        boolean z3;
        int i2;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z);
        File file = new File(this.f17842k, !z ? "x5.tbs" : "x5.tbs.temp");
        boolean z4 = false;
        if (file.exists()) {
            Exception exc = null;
            String string = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPK_MD5, null);
            String a = com.tencent.smtt.utils.a.a(file);
            if (string == null || !string.equals(a)) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " md5 failed");
                if (z) {
                    this.v.setCheckErrorDetail("fileMd5 not match");
                }
                return false;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] md5(" + a + ") successful!");
            long j2 = 0;
            if (z) {
                long j3 = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
                if (file.exists()) {
                    if (j3 > 0) {
                        j2 = file.length();
                    }
                }
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " filelength failed");
                this.v.setCheckErrorDetail("fileLength:" + j2 + ",contentLength:" + j3);
                return false;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] length(" + j2 + ") successful!");
            int i3 = -1;
            if (z2 && !z && (i2 = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)) != (i3 = com.tencent.smtt.utils.a.a(this.f17838g, file))) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " versionCode failed");
                if (z) {
                    this.v.setCheckErrorDetail("fileVersion:" + i3 + ",configVersion:" + i2);
                }
                return false;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode(" + i3 + ") successful!");
            if (z2 && !z) {
                String a2 = com.tencent.smtt.utils.b.a(this.f17838g, false, file);
                if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a2)) {
                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " signature failed");
                    if (z) {
                        TbsLogReport.TbsLogInfo tbsLogInfo = this.v;
                        StringBuilder sb = new StringBuilder();
                        sb.append("signature:");
                        sb.append(a2 == null ? DYConstants.DY_NULL_STR : Integer.valueOf(a2.length()));
                        tbsLogInfo.setCheckErrorDetail(sb.toString());
                    }
                    return false;
                }
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] signature successful!");
            if (z) {
                try {
                    z3 = file.renameTo(new File(this.f17842k, "x5.tbs"));
                } catch (Exception e2) {
                    exc = e2;
                    z3 = false;
                }
                if (!z3) {
                    a(109, a(exc), true);
                    return false;
                }
                z4 = z3;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] rename(" + z4 + ") successful!");
            return true;
        }
        return false;
    }

    private void c(boolean z) {
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(this.f17838g);
        Map<String, Object> map = tbsDownloadConfig.mSyncMap;
        Boolean bool = Boolean.FALSE;
        map.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, bool);
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, bool);
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        tbsDownloadConfig.commit();
        this.v.a = 100;
        int i2 = tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        TbsDownloader.a(this.f17838g);
        if (i2 == 5 || i2 == 3) {
            Bundle a = a(i2);
            if (a == null) {
                TbsLog.i(TbsDownloader.LOGTAG, "downloadSuccess RESPONSECODE_TPATCH bundle is null ");
            } else {
                TbsLog.i(TbsDownloader.LOGTAG, "downloadSuccess RESPONSECODE_TPATCH bundle is " + a);
                m.a().b(this.f17838g, a);
            }
        } else if (i2 > 10000) {
            b();
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.TRUE);
            tbsDownloadConfig.commit();
        } else {
            m.a().a(this.f17838g, new File(this.f17842k, "x5.tbs").getAbsolutePath(), tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
        }
        a(this.f17838g);
    }

    private boolean d(boolean z) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z);
        File file = z ? new File(this.f17842k, "x5.tbs") : new File(this.f17842k, "x5.tbs.temp");
        if (file.exists()) {
            FileUtil.b(file);
        }
        return true;
    }

    private void f() {
        this.p = 0;
        this.q = 0;
        this.f17843l = -1L;
        this.f17841j = null;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
    }

    private void g() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        HttpURLConnection httpURLConnection = this.t;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.t = null;
        }
        int i2 = this.v.a;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest] download finish code: " + i2);
        if (!this.r && this.y) {
            h();
        } else if (!this.f17837c) {
            TbsDownloader.a = false;
        }
        TbsDownloadConfig.getInstance(this.f17838g).saveDownloadInterruptCode();
        QbSdk.f17732n.onDownloadFinish(i2);
    }

    private void h() {
        this.v.setEventTime(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.f17838g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.f17838g);
        this.v.setApn(apnInfo);
        this.v.setNetworkType(apnType);
        if (apnType != this.x || !apnInfo.equals(this.w)) {
            this.v.setNetworkChange(0);
        }
        TbsLogReport.TbsLogInfo tbsLogInfo = this.v;
        int i2 = tbsLogInfo.a;
        if ((i2 == 0 || i2 == 107) && tbsLogInfo.getDownFinalFlag() == 0 && (!Apn.isNetworkAvailable(this.f17838g) || !l())) {
            a(101, null, true);
        }
        TbsLogReport.getInstance(this.f17838g).eventReport(TbsLogReport.EventType.TYPE_CDN_DOWNLOAD_STAT, this.v);
        this.v.resetArgs();
    }

    private void i() {
        int apnType = Apn.getApnType(this.f17838g);
        String apnInfo = Apn.getApnInfo(this.f17838g);
        String str = this.w;
        if (str != null || this.x != -1) {
            if (apnType == this.x && apnInfo.equals(str)) {
                return;
            }
            this.v.setNetworkChange(0);
        }
        this.w = apnInfo;
        this.x = apnType;
    }

    private boolean j() {
        return new File(this.f17842k, "x5.tbs.temp").exists();
    }

    private long k() {
        File file = new File(this.f17842k, "x5.tbs.temp");
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    private boolean l() {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Throwable th;
        InputStream inputStream;
        boolean z = false;
        try {
            inputStream = Runtime.getRuntime().exec("ping www.qq.com").getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    int i2 = 0;
                    do {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (readLine.contains("TTL") || readLine.contains(RemoteMessageConst.TTL)) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                th.printStackTrace();
                                return z;
                            } finally {
                                a(inputStream);
                                a(inputStreamReader);
                                a(bufferedReader);
                            }
                        }
                    } while (i2 < 5);
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                inputStreamReader = null;
            }
        } catch (Throwable th5) {
            inputStreamReader = null;
            bufferedReader = null;
            th = th5;
            inputStream = null;
        }
        return z;
    }

    private long m() {
        int i2 = this.p;
        return (i2 == 1 || i2 == 2) ? i2 * 20000 : (i2 == 3 || i2 == 4) ? 100000L : 200000L;
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean n() {
        HttpURLConnection httpURLConnection;
        boolean z;
        boolean z2 = Apn.getApnType(this.f17838g) == 3;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi=" + z2);
        if (z2) {
            try {
                httpURLConnection = (HttpURLConnection) new URL("https://pms.mb.qq.com/rsp204").openConnection();
            } catch (Throwable th) {
                th = th;
                httpURLConnection = null;
            }
            try {
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.getInputStream();
                int responseCode = httpURLConnection.getResponseCode();
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode=" + responseCode);
                z = responseCode == 204;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused2) {
                        }
                    }
                    z = false;
                    if (z) {
                    }
                    return z;
                } catch (Throwable th3) {
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th3;
                }
            }
            if (z) {
                this.A = true;
            } else {
                this.A = false;
                this.z.sendMessageDelayed(this.z.obtainMessage(150, null), 120000L);
            }
            return z;
        }
        z = false;
        if (z) {
        }
        return z;
    }

    public Bundle a(int i2) {
        File n2 = m.a().n(this.f17838g);
        int g2 = m.a().g(this.f17838g);
        File file = new File(this.f17842k, "x5.tbs");
        String absolutePath = file.exists() ? file.getAbsolutePath() : null;
        if (absolutePath == null) {
            TbsLog.i(TbsDownloader.LOGTAG, "getTpatchBundle tbsApkFile is " + file.getAbsolutePath());
            return null;
        }
        int i3 = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        File e2 = m.a().e(this.f17838g, 5);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i2);
        bundle.putInt("old_core_ver", g2);
        bundle.putInt("new_core_ver", i3);
        bundle.putString("old_apk_location", n2.getAbsolutePath());
        bundle.putString("new_apk_location", e2.getAbsolutePath());
        bundle.putString("diff_file_location", absolutePath);
        bundle.putString("core_type_tpatch", "not_stable");
        bundle.putInt("for_self_core", 1);
        return bundle;
    }

    public void a() {
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.f17838g)) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(this.f17838g).tbsLogInfo();
            tbsLogInfo.setErrorCode(-309);
            tbsLogInfo.setFailDetail(new Exception());
            TbsLogReport.getInstance(this.f17838g).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
        }
    }

    public void a(boolean z) {
        a(z, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:1096:0x0145, code lost:
        r2.setDownloadInterruptCode(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1200:0x044d, code lost:
        if (r42 == false) goto L1111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1204:0x0455, code lost:
        if (r42 == false) goto L1205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1241:0x04ed, code lost:
        if (r42 != false) goto L1467;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1263:0x0581, code lost:
        a(113, "tbsApkFileSize=" + r8 + "  but contentLength=" + r41.f17843l, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-310);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1293:0x0661, code lost:
        if (r41.a == null) goto L1301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1295:0x0668, code lost:
        if (b(true, r4) != false) goto L1301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1296:0x066a, code lost:
        if (r42 != false) goto L1300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1298:0x0670, code lost:
        if (b(false) == false) goto L1300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1299:0x0672, code lost:
        r33 = r5;
        r27 = r9;
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1300:0x0679, code lost:
        r41.s = true;
        r33 = r5;
        r27 = r9;
        r5 = false;
        r16 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1301:0x0685, code lost:
        r41.s = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1302:0x068a, code lost:
        if (r41.a == null) goto L1304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1303:0x068c, code lost:
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1304:0x068e, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-311);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1305:0x0699, code lost:
        r33 = r5;
        r27 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1318:0x06c2, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, r26, true);
        r7 = new java.lang.StringBuilder();
        r7.append("downloadFlow=");
        r7.append(r5);
        r7.append(" downloadMaxflow=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:1319:0x06d7, code lost:
        r8 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1320:0x06d9, code lost:
        r7.append(r8);
        a(112, r7.toString(), true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-307);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1321:0x06f1, code lost:
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1322:0x06f4, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1323:0x06f5, code lost:
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1324:0x06fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1325:0x06fe, code lost:
        r21 = r8;
        r9 = r12;
        r7 = r13;
        r13 = r31;
        r8 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1331:0x0757, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1332:0x0758, code lost:
        r8 = r0;
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1333:0x075d, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1334:0x075e, code lost:
        r8 = r0;
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1407:0x08c4, code lost:
        a(r12);
        a(r3);
        a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1408:0x08cf, code lost:
        if (r41.s != false) goto L1410;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1409:0x08d1, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-319);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1410:0x08dc, code lost:
        if (r42 != false) goto L1467;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1428:0x0949, code lost:
        if (r42 == false) goto L1111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1438:0x0974, code lost:
        if (r42 != false) goto L1261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1443:0x0994, code lost:
        if (r42 != false) goto L1261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1465:0x0a0b, code lost:
        if (r42 == false) goto L1111;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1420:0x08f3 A[Catch: all -> 0x0998, TryCatch #22 {all -> 0x0998, blocks: (B:1418:0x08ec, B:1420:0x08f3, B:1424:0x08fb, B:1426:0x0903, B:1431:0x094f, B:1433:0x0958, B:1434:0x095f, B:1435:0x0963, B:1441:0x097a), top: B:1519:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:1433:0x0958 A[Catch: all -> 0x0998, TryCatch #22 {all -> 0x0998, blocks: (B:1418:0x08ec, B:1420:0x08f3, B:1424:0x08fb, B:1426:0x0903, B:1431:0x094f, B:1433:0x0958, B:1434:0x095f, B:1435:0x0963, B:1441:0x097a), top: B:1519:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:1435:0x0963 A[Catch: all -> 0x0998, TRY_LEAVE, TryCatch #22 {all -> 0x0998, blocks: (B:1418:0x08ec, B:1420:0x08f3, B:1424:0x08fb, B:1426:0x0903, B:1431:0x094f, B:1433:0x0958, B:1434:0x095f, B:1435:0x0963, B:1441:0x097a), top: B:1519:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x09ae A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1500:0x0ad2  */
    /* JADX WARN: Removed duplicated region for block: B:1574:0x0a00 A[EDGE_INSN: B:1574:0x0a00->B:1464:0x0a00 BREAK  A[LOOP:0: B:1093:0x0137->B:1575:0x0137], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r3v12, types: [com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo] */
    /* JADX WARN: Type inference failed for: r41v0, types: [com.tencent.smtt.sdk.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(boolean z, boolean z2) {
        TbsDownloadConfig tbsDownloadConfig;
        int i2;
        boolean z3;
        int i3;
        String str;
        int i4;
        ?? r2;
        String str2;
        long j2;
        Throwable th;
        String str3;
        long j3;
        String str4;
        long j4;
        String str5;
        long j5;
        Throwable th2;
        InputStream inputStream;
        InputStream inputStream2;
        IOException iOException;
        FileOutputStream fileOutputStream;
        boolean z4;
        int i5;
        String a;
        FileOutputStream fileOutputStream2;
        long j6;
        InputStream inputStream3;
        String str6;
        InputStream inputStream4;
        String str7;
        InputStream inputStream5;
        InputStream inputStream6;
        long j7;
        TbsListener tbsListener;
        int i6;
        String str8 = TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME;
        if (!m.a().c(this.f17838g) || z) {
            int i7 = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            boolean z5 = true;
            boolean z6 = i7 == 1 || i7 == 2 || i7 == 4;
            this.C = z;
            String str9 = null;
            this.f17839h = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOADURL, null);
            String string = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADURL_LIST, null);
            TbsLog.i(TbsDownloader.LOGTAG, "backupUrlStrings:" + string, true);
            this.a = null;
            this.b = 0;
            if (!z && string != null && !"".equals(string.trim())) {
                this.a = string.trim().split(";");
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] mDownloadUrl=" + this.f17839h + " backupUrlStrings=" + string + " mLocation=" + this.f17841j + " mCanceled=" + this.r + " mHttpRequest=" + this.t);
            if (this.f17839h == null && this.f17841j == null) {
                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-302);
                tbsListener = QbSdk.f17732n;
                i6 = 132;
            } else if (this.t == null || this.r) {
                f();
                TbsLog.i(TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...", true);
                long downloadMaxflow = TbsDownloadConfig.getInstance(this.f17838g).getDownloadMaxflow();
                SharedPreferences sharedPreferences = TbsDownloadConfig.getInstance(this.f17838g).mPreferences;
                String str10 = TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW;
                long j8 = sharedPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0L);
                this.B = z ? f17835e : d;
                long j9 = j8;
                boolean z7 = false;
                while (true) {
                    if (this.p > this.B) {
                        tbsDownloadConfig = TbsDownloadConfig.getInstance(this.f17838g);
                        i2 = -305;
                        break;
                    } else if (this.q > 8) {
                        a(123, str9, z5);
                        tbsDownloadConfig = TbsDownloadConfig.getInstance(this.f17838g);
                        i2 = -306;
                        break;
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (z) {
                            j2 = downloadMaxflow;
                        } else {
                            try {
                                j2 = downloadMaxflow;
                            } catch (Throwable th3) {
                                th = th3;
                                str2 = str8;
                                j2 = downloadMaxflow;
                            }
                            try {
                                if (currentTimeMillis - TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getLong(str8, 0L) > 86400000) {
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] OVER DOWNLOAD_PERIOD");
                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str8, Long.valueOf(currentTimeMillis));
                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, 0L);
                                    TbsDownloadConfig.getInstance(this.f17838g).commit();
                                    j9 = 0;
                                } else {
                                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] downloadFlow=" + j9);
                                    if (j9 >= j2) {
                                        TbsLog.i(TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...failed because you exceeded max flow!", true);
                                        a(112, null, true);
                                        TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-307);
                                    }
                                }
                                if (FileUtil.b(this.f17838g)) {
                                    z5 = true;
                                } else {
                                    TbsLog.w(TbsDownloader.LOGTAG, "DownloadBegin FreeSpace too small", true);
                                    a(105, null, true);
                                    TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-308);
                                    if (!z) {
                                    }
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                str2 = str8;
                                th = th;
                                try {
                                    if (th instanceof SSLHandshakeException) {
                                    }
                                    str3 = null;
                                    th.printStackTrace();
                                    j3 = 0;
                                    a(0L);
                                    a(107, a(th), false);
                                    if (!this.r) {
                                    }
                                } finally {
                                    if (!z) {
                                        TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                        TbsDownloadConfig.getInstance(this.f17838g).commit();
                                    }
                                }
                            }
                        }
                        this.y = z5;
                        String str11 = this.f17841j;
                        if (str11 == null) {
                            str11 = this.f17839h;
                        }
                        TbsLog.i(TbsDownloader.LOGTAG, "try url:" + str11 + ",mRetryTimes:" + this.p, true);
                        if (!str11.equals(this.f17840i)) {
                            this.v.setDownloadUrl(str11);
                        }
                        this.f17840i = str11;
                        a(str11);
                        if (this.o) {
                            str2 = str8;
                            str4 = "STEP 1/2 begin downloading...failed because you exceeded max flow!";
                            j4 = currentTimeMillis;
                            str5 = "/";
                            j5 = 0;
                        } else {
                            j4 = currentTimeMillis;
                            long k2 = k();
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] range=" + k2);
                            str2 = str8;
                            if (this.f17843l <= 0) {
                                try {
                                    TbsLog.i(TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...current" + k2, true);
                                    this.t.setRequestProperty(HttpHeaders.RANGE, "bytes=" + k2 + "-");
                                    str4 = "STEP 1/2 begin downloading...failed because you exceeded max flow!";
                                    str5 = "/";
                                    j5 = k2;
                                } catch (Throwable th5) {
                                    th = th5;
                                    th = th;
                                    if ((th instanceof SSLHandshakeException) || z || this.a == null || !b(false)) {
                                        str3 = null;
                                        th.printStackTrace();
                                        j3 = 0;
                                        a(0L);
                                        a(107, a(th), false);
                                        if (!this.r) {
                                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-309);
                                        }
                                    } else {
                                        TbsLog.e(TbsDownloader.LOGTAG, "[startdownload]url:" + this.f17841j + " download exception\uff1a" + th.toString());
                                        str3 = null;
                                        a(125, null, true);
                                        j3 = 0;
                                    }
                                    TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-316);
                                    if (!z) {
                                        TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                        TbsDownloadConfig.getInstance(this.f17838g).commit();
                                    }
                                    str9 = str3;
                                    downloadMaxflow = j2;
                                    str8 = str2;
                                    z5 = true;
                                }
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("#1 STEP 1/2 begin downloading...current/total=");
                                sb.append(k2);
                                sb.append("/");
                                str4 = "STEP 1/2 begin downloading...failed because you exceeded max flow!";
                                str5 = "/";
                                sb.append(this.f17843l);
                                TbsLog.i(TbsDownloader.LOGTAG, sb.toString(), true);
                                HttpURLConnection httpURLConnection = this.t;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("bytes=");
                                sb2.append(k2);
                                sb2.append("-");
                                j5 = k2;
                                sb2.append(this.f17843l);
                                httpURLConnection.setRequestProperty(HttpHeaders.RANGE, sb2.toString());
                            }
                        }
                        this.v.setDownloadCancel(j5 == 0 ? 0 : 1);
                        i();
                        if (this.p >= 1) {
                            this.t.addRequestProperty("Referer", this.f17839h);
                        }
                        int responseCode = this.t.getResponseCode();
                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] responseCode=" + responseCode);
                        this.v.setHttpCode(responseCode);
                        if (!z && !TbsDownloader.getOverSea(this.f17838g) && Apn.getApnType(this.f17838g) != 3 && !QbSdk.canDownloadWithoutWifi()) {
                            a();
                            a(111, null, true);
                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-324);
                            TbsListener tbsListener2 = QbSdk.f17732n;
                            if (tbsListener2 != null) {
                                tbsListener2.onDownloadFinish(111);
                            }
                            TbsLog.w(TbsDownloader.LOGTAG, "Download is canceled due to NOT_WIFI error!", false);
                        }
                        if (this.r) {
                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-309);
                            if (!z) {
                            }
                        } else {
                            if (responseCode != 200 && responseCode != 206) {
                                if (responseCode < 300 || responseCode > 307) {
                                    a(102, String.valueOf(responseCode), false);
                                    if (responseCode == 416) {
                                        if (b(true, z6)) {
                                            try {
                                                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-323);
                                                if (!z) {
                                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                                    TbsDownloadConfig.getInstance(this.f17838g).commit();
                                                }
                                                z7 = true;
                                            } catch (Throwable th6) {
                                                th = th6;
                                                z7 = true;
                                                if (th instanceof SSLHandshakeException) {
                                                }
                                                str3 = null;
                                                th.printStackTrace();
                                                j3 = 0;
                                                a(0L);
                                                a(107, a(th), false);
                                                if (!this.r) {
                                                }
                                            }
                                        } else {
                                            d(false);
                                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-313);
                                            if (!z) {
                                            }
                                        }
                                    } else if ((responseCode == 403 || responseCode == 406) && this.f17843l == -1) {
                                        TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-314);
                                    } else if (responseCode != 202) {
                                        int i8 = this.p;
                                        int i9 = this.B;
                                        if (i8 < i9 && responseCode == 503) {
                                            a(Long.parseLong(this.t.getHeaderField(HttpHeaders.RETRY_AFTER)));
                                            if (this.r) {
                                                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-309);
                                                if (!z) {
                                                }
                                            } else {
                                                if (!z) {
                                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                                    TbsDownloadConfig.getInstance(this.f17838g).commit();
                                                }
                                                downloadMaxflow = j2;
                                                str8 = str2;
                                            }
                                        } else if (i8 < i9 && (responseCode == 408 || responseCode == 504 || responseCode == 502)) {
                                            a(0L);
                                            if (this.r) {
                                                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-309);
                                                if (!z) {
                                                }
                                            } else {
                                                if (!z) {
                                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                                    TbsDownloadConfig.getInstance(this.f17838g).commit();
                                                }
                                                downloadMaxflow = j2;
                                                str8 = str2;
                                            }
                                        } else if (k() > 0 || this.o || responseCode == 410) {
                                            break;
                                        } else {
                                            this.o = true;
                                            if (!z) {
                                                TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                                TbsDownloadConfig.getInstance(this.f17838g).commit();
                                            }
                                            downloadMaxflow = j2;
                                            str8 = str2;
                                        }
                                    }
                                } else {
                                    String headerField = this.t.getHeaderField(HttpHeaders.LOCATION);
                                    if (TextUtils.isEmpty(headerField)) {
                                        a(124, null, true);
                                        TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-312);
                                        if (!z) {
                                        }
                                    } else {
                                        this.f17841j = headerField;
                                        this.q++;
                                        if (!z) {
                                            TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                            TbsDownloadConfig.getInstance(this.f17838g).commit();
                                        }
                                        downloadMaxflow = j2;
                                        str8 = str2;
                                    }
                                }
                                z5 = true;
                                str9 = null;
                            }
                            this.f17843l = this.t.getContentLength() + j5;
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] mContentLength=" + this.f17843l);
                            this.v.setPkgSize(this.f17843l);
                            long j10 = TbsDownloadConfig.getInstance(this.f17838g).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
                            if (j10 == 0 || this.f17843l == j10) {
                                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.startDownload] begin readResponse");
                                try {
                                    InputStream inputStream7 = this.t.getInputStream();
                                    if (inputStream7 == null) {
                                        inputStream = null;
                                        fileOutputStream2 = null;
                                        break;
                                    }
                                    try {
                                        String contentEncoding = this.t.getContentEncoding();
                                        inputStream = (contentEncoding == null || !contentEncoding.contains("gzip")) ? (contentEncoding == null || !contentEncoding.contains("deflate")) ? inputStream7 : new InflaterInputStream(inputStream7, new Inflater(true)) : new GZIPInputStream(inputStream7);
                                    } catch (IOException e2) {
                                        iOException = e2;
                                        inputStream2 = inputStream7;
                                        inputStream = null;
                                    } catch (Throwable th7) {
                                        th2 = th7;
                                        inputStream2 = inputStream7;
                                        inputStream = null;
                                    }
                                    try {
                                        byte[] bArr = new byte[8192];
                                        File file = new File(this.f17842k, "x5.tbs.temp");
                                        TbsLog.i(TbsDownloader.LOGTAG, "fileDownloadApk is " + file.getAbsolutePath(), true);
                                        fileOutputStream2 = new FileOutputStream(file, true);
                                        try {
                                            long j11 = j5;
                                            long j12 = j4;
                                            long currentTimeMillis2 = System.currentTimeMillis();
                                            while (true) {
                                                if (this.r) {
                                                    TbsLog.i(TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...Canceled!", true);
                                                    TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-309);
                                                    j6 = j9;
                                                    inputStream3 = inputStream7;
                                                    str6 = str10;
                                                    break;
                                                }
                                                str6 = str10;
                                                try {
                                                    int read = inputStream.read(bArr, 0, 8192);
                                                    if (read <= 0) {
                                                        try {
                                                            break;
                                                        } catch (IOException e3) {
                                                            iOException = e3;
                                                            inputStream2 = inputStream7;
                                                            fileOutputStream = fileOutputStream2;
                                                            str10 = str6;
                                                            try {
                                                                iOException.printStackTrace();
                                                                if (!(iOException instanceof SocketTimeoutException)) {
                                                                }
                                                                this.f17844m = 100000;
                                                                a(0L);
                                                                a(103, a(iOException), false);
                                                                a(fileOutputStream);
                                                                a(inputStream);
                                                                a(inputStream2);
                                                            } catch (Throwable th8) {
                                                                Throwable th9 = th8;
                                                                th2 = th9;
                                                                a(fileOutputStream);
                                                                a(inputStream);
                                                                a(inputStream2);
                                                                throw th2;
                                                            }
                                                        } catch (Throwable th10) {
                                                            th2 = th10;
                                                            inputStream2 = inputStream7;
                                                            fileOutputStream = fileOutputStream2;
                                                            a(fileOutputStream);
                                                            a(inputStream);
                                                            a(inputStream2);
                                                            throw th2;
                                                        }
                                                    } else {
                                                        fileOutputStream2.write(bArr, 0, read);
                                                        fileOutputStream2.flush();
                                                        byte[] bArr2 = bArr;
                                                        inputStream4 = inputStream7;
                                                        if (!z) {
                                                            j9 += read;
                                                            if (j9 < j2) {
                                                                str7 = str4;
                                                                if (!FileUtil.b(this.f17838g)) {
                                                                    TbsLog.i(TbsDownloader.LOGTAG, "DownloadEnd FreeSpace too small ", true);
                                                                    a(105, "freespace=" + com.tencent.smtt.utils.s.a() + ",and minFreeSpace=" + TbsDownloadConfig.getInstance(this.f17838g).getDownloadMinFreeSpace(), true);
                                                                    TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-308);
                                                                    break;
                                                                }
                                                            } else {
                                                                break;
                                                            }
                                                        } else {
                                                            str7 = str4;
                                                        }
                                                        j6 = j9;
                                                        long j13 = read;
                                                        try {
                                                            long a2 = a(j12, j13);
                                                            long currentTimeMillis3 = System.currentTimeMillis();
                                                            long j14 = j5 + j13;
                                                            if (currentTimeMillis3 - currentTimeMillis2 > 1000) {
                                                                StringBuilder sb3 = new StringBuilder();
                                                                sb3.append("#2 STEP 1/2 begin downloading...current/total=");
                                                                sb3.append(j14);
                                                                sb3.append(str5);
                                                                str4 = str7;
                                                                sb3.append(this.f17843l);
                                                                TbsLog.i(TbsDownloader.LOGTAG, sb3.toString(), true);
                                                                TbsListener tbsListener3 = QbSdk.f17732n;
                                                                if (tbsListener3 != null) {
                                                                    double d2 = j14;
                                                                    inputStream3 = inputStream4;
                                                                    j7 = a2;
                                                                    try {
                                                                        double d3 = this.f17843l;
                                                                        Double.isNaN(d2);
                                                                        Double.isNaN(d3);
                                                                        tbsListener3.onDownloadProgress((int) ((d2 / d3) * 100.0d));
                                                                    } catch (IOException e4) {
                                                                        iOException = e4;
                                                                        fileOutputStream = fileOutputStream2;
                                                                        inputStream2 = inputStream3;
                                                                        str10 = str6;
                                                                        j9 = j6;
                                                                        iOException.printStackTrace();
                                                                        if (!(iOException instanceof SocketTimeoutException)) {
                                                                        }
                                                                        this.f17844m = 100000;
                                                                        a(0L);
                                                                        a(103, a(iOException), false);
                                                                        a(fileOutputStream);
                                                                        a(inputStream);
                                                                        a(inputStream2);
                                                                    } catch (Throwable th11) {
                                                                        th2 = th11;
                                                                        fileOutputStream = fileOutputStream2;
                                                                        inputStream2 = inputStream3;
                                                                        a(fileOutputStream);
                                                                        a(inputStream);
                                                                        a(inputStream2);
                                                                        throw th2;
                                                                    }
                                                                } else {
                                                                    inputStream3 = inputStream4;
                                                                    j7 = a2;
                                                                }
                                                                if (z || j14 - j11 <= 1048576) {
                                                                    inputStream7 = inputStream3;
                                                                    str10 = str6;
                                                                } else {
                                                                    try {
                                                                        if (!TbsDownloader.getOverSea(this.f17838g)) {
                                                                            if (Apn.getApnType(this.f17838g) != 3 && !QbSdk.canDownloadWithoutWifi()) {
                                                                                a();
                                                                                TbsLog.i(TbsDownloader.LOGTAG, "Download is paused due to NOT_WIFI error!", false);
                                                                                a(111, null, true);
                                                                                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-304);
                                                                                break;
                                                                            }
                                                                            inputStream7 = inputStream3;
                                                                            str10 = str6;
                                                                        } else {
                                                                            inputStream7 = inputStream3;
                                                                            str10 = str6;
                                                                        }
                                                                        j11 = j14;
                                                                    } catch (IOException e5) {
                                                                        e = e5;
                                                                        inputStream6 = inputStream3;
                                                                        str10 = str6;
                                                                        iOException = e;
                                                                        inputStream2 = inputStream6;
                                                                        fileOutputStream = fileOutputStream2;
                                                                        j9 = j6;
                                                                        iOException.printStackTrace();
                                                                        if (!(iOException instanceof SocketTimeoutException) && !(iOException instanceof SocketException)) {
                                                                            if (!z || FileUtil.b(this.f17838g)) {
                                                                                a(0L);
                                                                                if (j()) {
                                                                                    i5 = 106;
                                                                                    a = a(iOException);
                                                                                    z4 = false;
                                                                                } else {
                                                                                    z4 = false;
                                                                                    i5 = 104;
                                                                                    a = a(iOException);
                                                                                }
                                                                                a(i5, a, z4);
                                                                                a(fileOutputStream);
                                                                                a(inputStream);
                                                                                a(inputStream2);
                                                                            } else {
                                                                                a(105, "freespace=" + com.tencent.smtt.utils.s.a() + ",and minFreeSpace=" + TbsDownloadConfig.getInstance(this.f17838g).getDownloadMinFreeSpace(), true);
                                                                                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-308);
                                                                                QbSdk.f17732n.onDownloadFinish(105);
                                                                                a(fileOutputStream);
                                                                                a(inputStream);
                                                                                a(inputStream2);
                                                                            }
                                                                        }
                                                                        this.f17844m = 100000;
                                                                        a(0L);
                                                                        a(103, a(iOException), false);
                                                                        a(fileOutputStream);
                                                                        a(inputStream);
                                                                        a(inputStream2);
                                                                    } catch (Throwable th12) {
                                                                        th = th12;
                                                                        inputStream5 = inputStream3;
                                                                        th2 = th;
                                                                        inputStream2 = inputStream5;
                                                                        fileOutputStream = fileOutputStream2;
                                                                        a(fileOutputStream);
                                                                        a(inputStream);
                                                                        a(inputStream2);
                                                                        throw th2;
                                                                    }
                                                                }
                                                                currentTimeMillis2 = currentTimeMillis3;
                                                            } else {
                                                                str4 = str7;
                                                                inputStream7 = inputStream4;
                                                                j7 = a2;
                                                                str10 = str6;
                                                            }
                                                            j5 = j14;
                                                            bArr = bArr2;
                                                            j9 = j6;
                                                            j12 = j7;
                                                        } catch (IOException e6) {
                                                            e = e6;
                                                            inputStream6 = inputStream4;
                                                        } catch (Throwable th13) {
                                                            th = th13;
                                                            inputStream5 = inputStream4;
                                                        }
                                                    }
                                                } catch (IOException e7) {
                                                    e = e7;
                                                    str10 = str6;
                                                    iOException = e;
                                                    inputStream2 = inputStream7;
                                                    fileOutputStream = fileOutputStream2;
                                                    iOException.printStackTrace();
                                                    if (!(iOException instanceof SocketTimeoutException)) {
                                                    }
                                                    this.f17844m = 100000;
                                                    a(0L);
                                                    a(103, a(iOException), false);
                                                    a(fileOutputStream);
                                                    a(inputStream);
                                                    a(inputStream2);
                                                } catch (Throwable th14) {
                                                    th = th14;
                                                    th2 = th;
                                                    inputStream2 = inputStream7;
                                                    fileOutputStream = fileOutputStream2;
                                                    a(fileOutputStream);
                                                    a(inputStream);
                                                    a(inputStream2);
                                                    throw th2;
                                                }
                                            }
                                            j6 = j9;
                                            inputStream3 = inputStream4;
                                            boolean z8 = false;
                                            if (!z8) {
                                                inputStream7 = inputStream3;
                                                str10 = str6;
                                                j9 = j6;
                                                break;
                                            }
                                            try {
                                                a(fileOutputStream2);
                                                a(inputStream);
                                                a(inputStream3);
                                                if (z) {
                                                    str10 = str6;
                                                } else {
                                                    str10 = str6;
                                                    TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j6));
                                                    TbsDownloadConfig.getInstance(this.f17838g).commit();
                                                }
                                                downloadMaxflow = j2;
                                                str8 = str2;
                                                j9 = j6;
                                            } catch (Throwable th15) {
                                                str10 = str6;
                                                th = th15;
                                                j9 = j6;
                                                if (th instanceof SSLHandshakeException) {
                                                }
                                                str3 = null;
                                                th.printStackTrace();
                                                j3 = 0;
                                                a(0L);
                                                a(107, a(th), false);
                                                if (!this.r) {
                                                }
                                            }
                                        } catch (IOException e8) {
                                            e = e8;
                                        } catch (Throwable th16) {
                                            th = th16;
                                        }
                                    } catch (IOException e9) {
                                        iOException = e9;
                                        inputStream2 = inputStream7;
                                        fileOutputStream = null;
                                        iOException.printStackTrace();
                                        if (!(iOException instanceof SocketTimeoutException)) {
                                            if (z) {
                                            }
                                            a(0L);
                                            if (j()) {
                                            }
                                            a(i5, a, z4);
                                            a(fileOutputStream);
                                            a(inputStream);
                                            a(inputStream2);
                                        }
                                        this.f17844m = 100000;
                                        a(0L);
                                        a(103, a(iOException), false);
                                        a(fileOutputStream);
                                        a(inputStream);
                                        a(inputStream2);
                                    } catch (Throwable th17) {
                                        th2 = th17;
                                        inputStream2 = inputStream7;
                                        fileOutputStream = null;
                                        a(fileOutputStream);
                                        a(inputStream);
                                        a(inputStream2);
                                        throw th2;
                                    }
                                } catch (IOException e10) {
                                    iOException = e10;
                                    inputStream = null;
                                    inputStream2 = null;
                                } catch (Throwable th18) {
                                    th2 = th18;
                                    inputStream = null;
                                    inputStream2 = null;
                                }
                            } else {
                                TbsLog.i(TbsDownloader.LOGTAG, "DownloadBegin tbsApkFileSize=" + j10 + "  but contentLength=" + this.f17843l, true);
                                if (z || !(n() || (QbSdk.canDownloadWithoutWifi() && Apn.isNetworkAvailable(this.f17838g)))) {
                                    break;
                                } else if (this.a == null || !b(false)) {
                                    break;
                                } else {
                                    if (!z) {
                                        TbsDownloadConfig.getInstance(this.f17838g).mSyncMap.put(str10, Long.valueOf(j9));
                                        TbsDownloadConfig.getInstance(this.f17838g).commit();
                                    }
                                    downloadMaxflow = j2;
                                    str8 = str2;
                                }
                                if (!z) {
                                }
                            }
                            z5 = true;
                            str9 = null;
                        }
                    }
                }
                if (this.r) {
                    if (this.f17837c) {
                        i3 = 134;
                        str = "pauseDownload()";
                        z3 = true;
                    } else {
                        z3 = true;
                        i3 = 131;
                        str = "stopDownload()";
                    }
                    a(i3, str, z3);
                } else {
                    if (this.s) {
                        if (this.a == null && !z7) {
                            z7 = b(true, z6);
                        }
                        boolean z9 = z7;
                        this.v.setUnpkgFlag(z9 ? 1 : 0);
                        this.v.setPatchUpdateFlag(!z6 ? z9 ? 1 : 2 : 0);
                        TbsLog.i(TbsDownloader.LOGTAG, "downloadSuccess is " + z9);
                        if (z9) {
                            c(true);
                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-317);
                            a(100, "success", true);
                            i4 = 0;
                            r2 = z9;
                        } else {
                            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-318);
                            i4 = 0;
                            d(false);
                            r2 = z9;
                        }
                    } else {
                        i4 = 0;
                        r2 = z7;
                    }
                    TbsDownloadConfig tbsDownloadConfig2 = TbsDownloadConfig.getInstance(this.f17838g);
                    if (r2 != 0) {
                        tbsDownloadConfig2.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, Integer.valueOf(tbsDownloadConfig2.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, i4) + 1));
                    } else {
                        int i10 = tbsDownloadConfig2.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, i4) + 1;
                        tbsDownloadConfig2.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, Integer.valueOf(i10));
                        if (i10 == tbsDownloadConfig2.getDownloadFailedMaxRetrytimes()) {
                            this.v.setDownloadCancel(2);
                        }
                    }
                    tbsDownloadConfig2.commit();
                    this.v.setDownFinalFlag(r2);
                }
                g();
                return;
            } else {
                TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-303);
                tbsListener = QbSdk.f17732n;
                i6 = 133;
            }
        } else {
            TbsDownloader.a = false;
            i6 = -322;
            TbsDownloadConfig.getInstance(this.f17838g).setDownloadInterruptCode(-322);
            tbsListener = QbSdk.f17732n;
        }
        tbsListener.onDownloadFinish(i6);
    }

    public void b() {
        a();
        d(false);
        d(true);
    }

    public void b(int i2) {
        if (m.a().p(this.f17838g)) {
            m.a().c();
            try {
                File file = new File(this.f17842k, "x5.tbs");
                int a = com.tencent.smtt.utils.a.a(this.f17838g, file);
                if (-1 == a || (i2 > 0 && i2 == a)) {
                    FileUtil.b(file);
                }
            } catch (Exception unused) {
            }
        }
    }

    public boolean b(boolean z) {
        String[] strArr;
        int i2;
        if ((!z || n() || (QbSdk.canDownloadWithoutWifi() && Apn.isNetworkAvailable(this.f17838g))) && (strArr = this.a) != null && (i2 = this.b) >= 0 && i2 < strArr.length) {
            this.b = i2 + 1;
            this.f17841j = strArr[i2];
            this.p = 0;
            this.q = 0;
            this.f17843l = -1L;
            this.o = false;
            this.r = false;
            this.s = false;
            this.y = false;
            return true;
        }
        return false;
    }

    public boolean c() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.C);
        return this.C;
    }

    public void d() {
        TbsLog.i(TbsDownloader.LOGTAG, "pauseDownload,isPause=" + this.f17837c + "isDownloading=" + TbsDownloader.isDownloading());
        if (this.f17837c || !TbsDownloader.isDownloading()) {
            return;
        }
        a();
        this.f17837c = true;
        this.y = false;
    }

    public void e() {
        TbsLog.i(TbsDownloader.LOGTAG, "resumeDownload,isPause=" + this.f17837c + "isDownloading=" + TbsDownloader.isDownloading());
        if (this.f17837c && TbsDownloader.isDownloading()) {
            this.f17837c = false;
            a(false);
        }
    }
}
