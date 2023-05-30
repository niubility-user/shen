package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileLock;

/* loaded from: classes9.dex */
public abstract class X5Downloader implements ProgressListener {
    private final Context a;
    private final QbSdk.PrivateCDNMode b;

    /* renamed from: c  reason: collision with root package name */
    private String f17806c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private File f17807e;

    /* renamed from: f  reason: collision with root package name */
    private File f17808f;

    /* renamed from: g  reason: collision with root package name */
    private long f17809g;

    /* renamed from: h  reason: collision with root package name */
    private String f17810h;

    /* renamed from: i  reason: collision with root package name */
    private int f17811i;

    /* renamed from: l  reason: collision with root package name */
    private DownloadDelegate f17814l;

    /* renamed from: n  reason: collision with root package name */
    private FileLock f17816n;
    private FileOutputStream o;
    private HttpURLConnection t;
    private int v;
    private String w;

    /* renamed from: j  reason: collision with root package name */
    private int f17812j = 4;

    /* renamed from: k  reason: collision with root package name */
    private int f17813k = 10000;

    /* renamed from: m  reason: collision with root package name */
    private volatile boolean f17815m = false;
    private int p = 0;
    private int q = 0;
    private int r = 30000;
    private boolean s = false;
    private long u = -1;
    private boolean x = false;
    private boolean y = false;
    private final StringBuilder z = new StringBuilder();
    private int A = 0;
    private String B = "";

    /* loaded from: classes9.dex */
    public interface DownloadDelegate {
        void download(X5Downloader x5Downloader, String str, File file);
    }

    public X5Downloader(Context context) {
        throw new com.tencent.smtt.utils.a.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x02ca, code lost:
        b(r11, "user cancel download");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x019f, code lost:
        b(r0, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002d, code lost:
        r2.append(r3);
        b(r0, r2.toString());
     */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0369 A[Catch: all -> 0x03af, TryCatch #7 {all -> 0x03af, blocks: (B:166:0x0365, B:168:0x0369, B:171:0x036e, B:173:0x0376, B:180:0x0390, B:185:0x03a1), top: B:194:0x0365 }] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0376 A[EDGE_INSN: B:234:0x0376->B:173:0x0376 BREAK  A[LOOP:0: B:5:0x000c->B:239:0x000c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:250:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.X5Downloader.a():void");
    }

    private void a(int i2, String str) {
        this.A = i2;
        this.B = str;
    }

    private void a(long j2) {
        int i2 = this.p + 1;
        this.p = i2;
        if (j2 <= 0) {
            j2 = (i2 == 1 || i2 == 2) ? Final.FIVE_SECOND * i2 : (i2 == 3 || i2 == 4) ? 25000L : 50000L;
        }
        try {
            Thread.sleep(j2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(QbSdk.PrivateCDNMode privateCDNMode) {
        if (this.b == privateCDNMode) {
            return;
        }
        throw new UnsupportedOperationException("you are expected use " + privateCDNMode.name() + OrderISVUtil.MONEY_DECIMAL + "Current mode is " + privateCDNMode.name() + ". Plz check QbSdk.usePrivateCDN(PrivateCDNMode)");
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
                TbsLog.e("X5Downloader", "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
        this.t = httpURLConnection2;
        httpURLConnection2.setRequestProperty("User-Agent", TbsDownloader.b(this.a));
        this.t.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.f17813k);
        this.t.setReadTimeout(this.r);
    }

    private boolean a(boolean z) {
        boolean z2;
        File file = z ? this.f17808f : this.f17807e;
        if (this.f17807e == null || !file.exists()) {
            a(-14, "target file not exist.");
            return false;
        }
        String a = com.tencent.smtt.utils.a.a(file);
        String str = this.f17810h;
        if (str == null || !str.equals(a)) {
            a(-11, "md5 not match");
            return false;
        }
        long j2 = this.f17809g;
        if (j2 == 0 || j2 != file.length()) {
            a(-12, "size not match. current: " + file.length());
            return false;
        }
        int i2 = this.f17811i;
        if (i2 == 0 || i2 != com.tencent.smtt.utils.a.a(this.a, file)) {
            a(-15, "core version not match");
            return false;
        } else if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(com.tencent.smtt.utils.b.a(this.a, false, file))) {
            a(-13, "core sig invalid.");
            return false;
        } else {
            if (!z) {
                Exception e2 = null;
                try {
                    z2 = this.f17807e.renameTo(this.f17808f);
                } catch (Exception e3) {
                    e2 = e3;
                    z2 = false;
                }
                if (!z2) {
                    a(-17, "reason: " + e2);
                    return false;
                }
            }
            i();
            return true;
        }
    }

    private long b() {
        File file = this.f17807e;
        if (file == null || !file.exists()) {
            return 0L;
        }
        return this.f17807e.length();
    }

    private void b(int i2, String str) {
        h();
        TbsLog.i("X5Downloader", "innerOnFailed, code: " + i2 + ", msg: " + str);
        onFailed(i2, str);
    }

    private void c() {
        int apnType = Apn.getApnType(this.a);
        String apnInfo = Apn.getApnInfo(this.a);
        String str = this.w;
        if (!(str == null && this.v == -1) && apnType == this.v && apnInfo.equals(str)) {
            return;
        }
        this.w = apnInfo;
        this.v = apnType;
    }

    private boolean d() {
        int i2;
        String str;
        FileOutputStream b = FileUtil.b(this.a, true, "x5dl.lock");
        this.o = b;
        if (b != null) {
            FileLock a = FileUtil.a(this.a, b);
            this.f17816n = a;
            if (a != null) {
                return true;
            }
            i2 = -2;
            str = "Other Process Downloading";
        } else {
            i2 = -1;
            str = "lock fos is null";
        }
        b(i2, str);
        return false;
    }

    private void e() {
        FileUtil.a(this.f17816n, this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        SharedPreferences sharedPreferences = TbsDownloadConfig.getInstance(this.a).mPreferences;
        if (sharedPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWLOAD_FLOWCTR, 0) == 1) {
            this.f17806c = "";
            b(-20, "meet flow control.");
            return;
        }
        String string = sharedPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOADURL, "");
        this.f17806c = string;
        if ("".equals(string)) {
            b(-20, "meet flow control.");
            return;
        }
        TbsLog.i("X5Downloader", "start delegate download progress...");
        this.f17814l.download(this, this.f17806c, this.f17808f);
    }

    private void g() {
        File file = this.f17807e;
        if (file == null || !file.exists()) {
            return;
        }
        FileUtil.b(file);
    }

    private void h() {
        this.y = false;
        this.f17815m = false;
        this.x = false;
        this.p = 0;
        this.q = 0;
        this.r = 30000;
        this.s = false;
        this.t = null;
        this.u = -1L;
    }

    private void i() {
        TbsDownloadConfig.getInstance(this.a).mPreferences.edit().putString(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOADURL, "").apply();
    }

    private void j() {
        h();
        TbsLog.i("X5Downloader", "download and verify x5 finished, prepare to install");
        if (this.f17808f.exists()) {
            m.a().a(this.a, this.f17808f.getAbsolutePath(), this.f17811i);
        }
        TbsLog.i("X5Downloader", "download and install finished.");
        onFinished();
    }

    public final void notifyDownloadFinished() {
        a(QbSdk.PrivateCDNMode.SELF_IMPL);
        if (a(true)) {
            j();
        } else {
            b(this.A, this.B);
        }
    }

    @Override // com.tencent.smtt.sdk.ProgressListener
    public void onProgress(int i2) {
    }

    public final X5Downloader setConnectTimesOut(int i2) {
        if (i2 > 0) {
            this.f17813k = i2;
            return this;
        }
        throw new IllegalArgumentException("can not set ConnectTimesOut <= 0, input is: " + i2);
    }

    public final X5Downloader setDownloadDelegate(DownloadDelegate downloadDelegate) {
        a(QbSdk.PrivateCDNMode.SELF_IMPL);
        this.f17814l = downloadDelegate;
        return this;
    }

    public final X5Downloader setDownloadUrl(String str) {
        if (str.contains("imtt.qq.com")) {
            throw new IllegalArgumentException("you can't set TBS official domain");
        }
        a(QbSdk.PrivateCDNMode.OFFICIAL_IMPL);
        this.f17806c = str;
        return this;
    }

    public final X5Downloader setMaxRetryTimes(int i2) {
        if (i2 > 0) {
            this.f17812j = i2;
            return this;
        }
        throw new IllegalArgumentException("can not set MaxRetryTimes <= 0, input is: " + i2);
    }

    public final void startDownload() {
        if (this.b == QbSdk.PrivateCDNMode.SELF_IMPL && this.f17814l == null) {
            throw new IllegalStateException("is SELF_IMPL mode, but delegate is null. Please set setDownloadDelegate()");
        }
        String str = this.f17806c;
        if (str == null || str.equals("")) {
            b(-18, "url is empty, [OFFICIAL_IMPL] plz setDownloadUrl. [SELF_IMPL] Maybe flow control.");
        } else if (this.f17811i == 0) {
            b(-19, "No config version, please check whether TbsVersionController.checkVersion is call. Need more help please contact with x5tbs@tencent.com");
        } else if (QbSdk.getTbsVersion(this.a) != this.f17811i && !a(true)) {
            Thread thread = new Thread() { // from class: com.tencent.smtt.sdk.X5Downloader.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (X5Downloader.this.b == QbSdk.PrivateCDNMode.SELF_IMPL) {
                        X5Downloader.this.f();
                        return;
                    }
                    TbsLog.i("X5Downloader", "Delegate is null, try to start default download.");
                    X5Downloader.this.a();
                }
            };
            thread.setName("thread-x5dl");
            thread.start();
        } else {
            j();
            TbsLog.i("X5Downloader", "local version match, no need to download: " + this.f17811i);
        }
    }
}
