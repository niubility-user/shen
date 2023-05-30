package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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

    /* JADX WARN: Code restructure failed: missing block: B:85:0x0085, code lost:
        if (r10 != r8) goto L86;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.k.b(boolean, boolean):boolean");
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

    /* JADX WARN: Removed duplicated region for block: B:81:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean n() {
        /*
            r8 = this;
            android.content.Context r0 = r8.f17838g
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 1
            r2 = 0
            r3 = 3
            if (r0 != r3) goto Ld
            r0 = 1
            goto Le
        Ld:
            r0 = 0
        Le:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi="
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "TbsDownload"
            com.tencent.smtt.utils.TbsLog.i(r4, r3)
            r3 = 0
            if (r0 == 0) goto L80
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L6e
            java.lang.String r5 = "https://pms.mb.qq.com/rsp204"
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L6e
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> L6e
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L6e
            r0.setInstanceFollowRedirects(r2)     // Catch: java.lang.Throwable -> L6c
            r5 = 10000(0x2710, float:1.4013E-41)
            r0.setConnectTimeout(r5)     // Catch: java.lang.Throwable -> L6c
            r0.setReadTimeout(r5)     // Catch: java.lang.Throwable -> L6c
            r0.setUseCaches(r2)     // Catch: java.lang.Throwable -> L6c
            r0.getInputStream()     // Catch: java.lang.Throwable -> L6c
            int r5 = r0.getResponseCode()     // Catch: java.lang.Throwable -> L6c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
            r6.<init>()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r7 = "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode="
            r6.append(r7)     // Catch: java.lang.Throwable -> L6c
            r6.append(r5)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L6c
            com.tencent.smtt.utils.TbsLog.i(r4, r6)     // Catch: java.lang.Throwable -> L6c
            r4 = 204(0xcc, float:2.86E-43)
            if (r5 != r4) goto L63
            r4 = 1
            goto L64
        L63:
            r4 = 0
        L64:
            if (r0 == 0) goto L81
            r0.disconnect()     // Catch: java.lang.Exception -> L6a
            goto L81
        L6a:
            goto L81
        L6c:
            r4 = move-exception
            goto L70
        L6e:
            r4 = move-exception
            r0 = r3
        L70:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L79
            if (r0 == 0) goto L80
            r0.disconnect()     // Catch: java.lang.Exception -> L80
            goto L80
        L79:
            r1 = move-exception
            if (r0 == 0) goto L7f
            r0.disconnect()     // Catch: java.lang.Exception -> L7f
        L7f:
            throw r1
        L80:
            r4 = 0
        L81:
            if (r4 == 0) goto L86
            r8.A = r1
            goto L98
        L86:
            r8.A = r2
            android.os.Handler r0 = r8.z
            r1 = 150(0x96, float:2.1E-43)
            android.os.Message r0 = r0.obtainMessage(r1, r3)
            android.os.Handler r1 = r8.z
            r2 = 120000(0x1d4c0, double:5.9288E-319)
            r1.sendMessageDelayed(r0, r2)
        L98:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.k.n():boolean");
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

    /* JADX WARN: Code restructure failed: missing block: B:568:0x0145, code lost:
        r2.setDownloadInterruptCode(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:672:0x044d, code lost:
        if (r42 == false) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:676:0x0455, code lost:
        if (r42 == false) goto L677;
     */
    /* JADX WARN: Code restructure failed: missing block: B:713:0x04ed, code lost:
        if (r42 != false) goto L939;
     */
    /* JADX WARN: Code restructure failed: missing block: B:735:0x0581, code lost:
        a(113, "tbsApkFileSize=" + r8 + "  but contentLength=" + r41.f17843l, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-310);
     */
    /* JADX WARN: Code restructure failed: missing block: B:765:0x0661, code lost:
        if (r41.a == null) goto L773;
     */
    /* JADX WARN: Code restructure failed: missing block: B:767:0x0668, code lost:
        if (b(true, r4) != false) goto L773;
     */
    /* JADX WARN: Code restructure failed: missing block: B:768:0x066a, code lost:
        if (r42 != false) goto L772;
     */
    /* JADX WARN: Code restructure failed: missing block: B:770:0x0670, code lost:
        if (b(false) == false) goto L772;
     */
    /* JADX WARN: Code restructure failed: missing block: B:771:0x0672, code lost:
        r33 = r5;
        r27 = r9;
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:772:0x0679, code lost:
        r41.s = true;
        r33 = r5;
        r27 = r9;
        r5 = false;
        r16 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:773:0x0685, code lost:
        r41.s = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:774:0x068a, code lost:
        if (r41.a == null) goto L776;
     */
    /* JADX WARN: Code restructure failed: missing block: B:775:0x068c, code lost:
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:776:0x068e, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-311);
     */
    /* JADX WARN: Code restructure failed: missing block: B:777:0x0699, code lost:
        r33 = r5;
        r27 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:790:0x06c2, code lost:
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, r26, true);
        r7 = new java.lang.StringBuilder();
        r7.append("downloadFlow=");
        r7.append(r5);
        r7.append(" downloadMaxflow=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:791:0x06d7, code lost:
        r8 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:792:0x06d9, code lost:
        r7.append(r8);
        a(112, r7.toString(), true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-307);
     */
    /* JADX WARN: Code restructure failed: missing block: B:793:0x06f1, code lost:
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:794:0x06f4, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:795:0x06f5, code lost:
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:796:0x06fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:797:0x06fe, code lost:
        r21 = r8;
        r9 = r12;
        r7 = r13;
        r13 = r31;
        r8 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:803:0x0757, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:804:0x0758, code lost:
        r8 = r0;
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:805:0x075d, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:806:0x075e, code lost:
        r8 = r0;
        r9 = r12;
        r7 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:879:0x08c4, code lost:
        a(r12);
        a(r3);
        a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:880:0x08cf, code lost:
        if (r41.s != false) goto L882;
     */
    /* JADX WARN: Code restructure failed: missing block: B:881:0x08d1, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r41.f17838g).setDownloadInterruptCode(-319);
     */
    /* JADX WARN: Code restructure failed: missing block: B:882:0x08dc, code lost:
        if (r42 != false) goto L939;
     */
    /* JADX WARN: Code restructure failed: missing block: B:900:0x0949, code lost:
        if (r42 == false) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:910:0x0974, code lost:
        if (r42 != false) goto L733;
     */
    /* JADX WARN: Code restructure failed: missing block: B:915:0x0994, code lost:
        if (r42 != false) goto L733;
     */
    /* JADX WARN: Code restructure failed: missing block: B:937:0x0a0b, code lost:
        if (r42 == false) goto L583;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1046:0x0a00 A[EDGE_INSN: B:1046:0x0a00->B:936:0x0a00 BREAK  A[LOOP:0: B:565:0x0137->B:1047:0x0137], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:892:0x08f3 A[Catch: all -> 0x0998, TryCatch #22 {all -> 0x0998, blocks: (B:890:0x08ec, B:892:0x08f3, B:896:0x08fb, B:898:0x0903, B:903:0x094f, B:905:0x0958, B:906:0x095f, B:907:0x0963, B:913:0x097a), top: B:991:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:905:0x0958 A[Catch: all -> 0x0998, TryCatch #22 {all -> 0x0998, blocks: (B:890:0x08ec, B:892:0x08f3, B:896:0x08fb, B:898:0x0903, B:903:0x094f, B:905:0x0958, B:906:0x095f, B:907:0x0963, B:913:0x097a), top: B:991:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:907:0x0963 A[Catch: all -> 0x0998, TRY_LEAVE, TryCatch #22 {all -> 0x0998, blocks: (B:890:0x08ec, B:892:0x08f3, B:896:0x08fb, B:898:0x0903, B:903:0x094f, B:905:0x0958, B:906:0x095f, B:907:0x0963, B:913:0x097a), top: B:991:0x08ec }] */
    /* JADX WARN: Removed duplicated region for block: B:928:0x09ae A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:972:0x0ad2  */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r3v12, types: [com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo] */
    /* JADX WARN: Type inference failed for: r41v0, types: [com.tencent.smtt.sdk.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r42, boolean r43) {
        /*
            Method dump skipped, instructions count: 2850
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.k.a(boolean, boolean):void");
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
