package com.wjlogin.onekey.sdk.common.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.common.net.HttpHeaders;
import com.wjlogin.onekey.sdk.util.LogUtil;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import java.net.HttpURLConnection;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class g implements Runnable {
    private static final String a = "WJLogin.OneKey.TelecomHttpExecut";
    public static final int b = 12;

    /* renamed from: c  reason: collision with root package name */
    public static final int f18327c = 0;
    private ConnectivityManager.NetworkCallback d;

    /* renamed from: e  reason: collision with root package name */
    private ConnectivityManager f18328e;

    /* renamed from: f  reason: collision with root package name */
    private String f18329f;

    /* renamed from: g  reason: collision with root package name */
    private Context f18330g;

    /* renamed from: h  reason: collision with root package name */
    private b f18331h;

    /* renamed from: i  reason: collision with root package name */
    private int f18332i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f18333j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f18334k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f18335l;

    /* renamed from: m  reason: collision with root package name */
    private Handler f18336m;

    public g() {
        this.f18332i = 10000;
        this.f18336m = new Handler(Looper.getMainLooper());
    }

    private boolean d() {
        boolean simiState = MobileDeviceUtil.getSimiState(this.f18330g);
        boolean equals = "CT".equals(MobileDeviceUtil.getOperateType(this.f18330g));
        boolean dataEnable = MobileDeviceUtil.dataEnable(this.f18330g);
        boolean isNetworkAvailable = MobileDeviceUtil.isNetworkAvailable(this.f18330g);
        int intValue = MobileDeviceUtil.getDefaultDataSubId(this.f18330g).intValue();
        boolean z = intValue != -1;
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " isTelecomOperaterType=" + equals + " isOpenNetData = " + dataEnable + " isNetworkAvailable = " + isNetworkAvailable + " dataId =" + intValue + " stateGood = " + simiState);
        }
        return simiState && equals && dataEnable && z;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e() {
        /*
            r8 = this;
            java.lang.String r0 = "network type_mobile disable"
            java.lang.String r1 = "WJLogin.OneKey.TelecomHttpExecut"
            java.lang.String r2 = "CT"
            java.lang.String r3 = "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e"
            java.lang.String r4 = "-100"
            r8.f()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = r8.f18329f     // Catch: java.lang.Exception -> Lc2
            boolean r6 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog     // Catch: java.lang.Exception -> Lc2
            if (r6 == 0) goto L29
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc2
            r6.<init>()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r7 = "url="
            r6.append(r7)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r7 = r8.f18329f     // Catch: java.lang.Exception -> Lc2
            r6.append(r7)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> Lc2
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r1, r6)     // Catch: java.lang.Exception -> Lc2
        L29:
            java.net.URL r6 = new java.net.URL     // Catch: java.lang.Exception -> Lc2
            r6.<init>(r5)     // Catch: java.lang.Exception -> Lc2
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Lc2
            r7 = 21
            if (r5 < r7) goto L83
            android.net.ConnectivityManager$NetworkCallback r5 = r8.d     // Catch: java.lang.Exception -> Lc2
            if (r5 == 0) goto L5b
            boolean r5 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog     // Catch: java.lang.Exception -> L49
            if (r5 == 0) goto L41
            java.lang.String r5 = "exeRequest unregisterNetworkCallback"
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r1, r5)     // Catch: java.lang.Exception -> L49
        L41:
            android.net.ConnectivityManager r5 = r8.f18328e     // Catch: java.lang.Exception -> L49
            android.net.ConnectivityManager$NetworkCallback r7 = r8.d     // Catch: java.lang.Exception -> L49
            r5.unregisterNetworkCallback(r7)     // Catch: java.lang.Exception -> L49
            goto L5b
        L49:
            r5 = move-exception
            r7 = 0
            r8.d = r7     // Catch: java.lang.Exception -> Lc2
            r5.printStackTrace()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Exception -> Lc2
            org.json.JSONObject r5 = com.wjlogin.onekey.sdk.util.a.a(r4, r3, r2, r5)     // Catch: java.lang.Exception -> Lc2
            r8.a(r5)     // Catch: java.lang.Exception -> Lc2
        L5b:
            android.net.NetworkRequest$Builder r5 = new android.net.NetworkRequest$Builder     // Catch: java.lang.Exception -> Lc2
            r5.<init>()     // Catch: java.lang.Exception -> Lc2
            r7 = 12
            r5.addCapability(r7)     // Catch: java.lang.Exception -> Lc2
            r7 = 0
            r5.addTransportType(r7)     // Catch: java.lang.Exception -> Lc2
            android.net.NetworkRequest r5 = r5.build()     // Catch: java.lang.Exception -> Lc2
            boolean r7 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog     // Catch: java.lang.Exception -> Lc2
            if (r7 == 0) goto L76
            java.lang.String r7 = "request network"
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r7)     // Catch: java.lang.Exception -> Lc2
        L76:
            com.wjlogin.onekey.sdk.common.a.a.f r7 = new com.wjlogin.onekey.sdk.common.a.a.f     // Catch: java.lang.Exception -> Lc2
            r7.<init>(r8, r6)     // Catch: java.lang.Exception -> Lc2
            r8.d = r7     // Catch: java.lang.Exception -> Lc2
            android.net.ConnectivityManager r6 = r8.f18328e     // Catch: java.lang.Exception -> Lc2
            r6.requestNetwork(r5, r7)     // Catch: java.lang.Exception -> Lc2
            goto Le9
        L83:
            boolean r5 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog     // Catch: java.lang.Exception -> Lc2
            if (r5 == 0) goto L8c
            java.lang.String r5 = " post android5.0 \u4e0b"
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r1, r5)     // Catch: java.lang.Exception -> Lc2
        L8c:
            android.net.ConnectivityManager r5 = r8.f18328e     // Catch: java.lang.Exception -> Lc2
            android.net.NetworkInfo r5 = r5.getActiveNetworkInfo()     // Catch: java.lang.Exception -> Lc2
            if (r5 == 0) goto Lba
            int r7 = r5.getType()     // Catch: java.lang.Exception -> Lc2
            if (r7 != 0) goto Lba
            boolean r5 = r5.isConnected()     // Catch: java.lang.Exception -> Lc2
            if (r5 == 0) goto Lba
            java.net.URLConnection r5 = r6.openConnection()     // Catch: java.lang.Exception -> Laa
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Exception -> Laa
            r8.a(r5)     // Catch: java.lang.Exception -> Laa
            goto Le9
        Laa:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Exception -> Lc2
            org.json.JSONObject r5 = com.wjlogin.onekey.sdk.util.a.a(r4, r3, r2, r5)     // Catch: java.lang.Exception -> Lc2
            r8.a(r5)     // Catch: java.lang.Exception -> Lc2
            goto Le9
        Lba:
            org.json.JSONObject r5 = com.wjlogin.onekey.sdk.util.a.a(r4, r3, r2, r0)     // Catch: java.lang.Exception -> Lc2
            r8.a(r5)     // Catch: java.lang.Exception -> Lc2
            goto Le9
        Lc2:
            r5 = move-exception
            r5.printStackTrace()
            boolean r6 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog
            if (r6 == 0) goto Le2
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Exception ="
            r6.append(r7)
            java.lang.String r5 = r5.toString()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.wjlogin.onekey.sdk.util.LogUtil.LogE(r1, r5)
        Le2:
            org.json.JSONObject r0 = com.wjlogin.onekey.sdk.util.a.a(r4, r3, r2, r0)
            r8.a(r0)
        Le9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wjlogin.onekey.sdk.common.a.a.g.e():void");
    }

    private void f() {
        if (this.f18331h != null) {
            this.f18336m.postDelayed(new c(this), this.f18332i);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        a();
    }

    private String b(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("?");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public int c() {
        return this.f18332i;
    }

    public g(Context context) {
        this.f18332i = 10000;
        this.f18336m = new Handler(Looper.getMainLooper());
        this.f18330g = context;
        this.f18328e = (ConnectivityManager) context.getSystemService("connectivity");
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " TelecomHttpExecut new=");
        }
    }

    public void a(String str, String str2) {
        this.f18329f = b(str, str2);
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " initRequest=");
        }
    }

    public void a(int i2) {
        this.f18332i = i2;
    }

    public b b() {
        return this.f18331h;
    }

    public void a(b bVar) {
        this.f18331h = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        if (Build.VERSION.SDK_INT >= 21 && this.d != null) {
            try {
                if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "performOnError unregisterNetworkCallback");
                }
                this.f18328e.unregisterNetworkCallback(this.d);
            } catch (Exception e2) {
                this.d = null;
                e2.printStackTrace();
            }
        }
        if (this.f18333j) {
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, "isTimeOut = true \u5df2\u8d85\u65f6\uff0c\u7ed9\u51fa\u8d85\u65f6\u7684\u63d0\u793a\uff0c\u4e0d\u518d\u8fd4\u56de\u5176\u4ed6\u7684\u9519\u8bef\u56de\u8c03\u4e86");
            }
        } else if (this.f18331h != null) {
            this.f18336m.post(new d(this, jSONObject));
        }
    }

    public g(Context context, String str, String str2) {
        this.f18332i = 10000;
        this.f18336m = new Handler(Looper.getMainLooper());
        this.f18333j = false;
        this.f18334k = false;
        this.f18335l = false;
        this.f18330g = context;
        this.f18328e = (ConnectivityManager) context.getSystemService("connectivity");
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " TelecomHttpExecut new=");
        }
        a(str, str2);
    }

    private void a(String str) {
        if (Build.VERSION.SDK_INT >= 21 && this.d != null) {
            try {
                if (LogUtil.enableLog) {
                    LogUtil.LogI(a, "performOnSuccess unregisterNetworkCallback");
                }
                this.f18328e.unregisterNetworkCallback(this.d);
            } catch (Exception e2) {
                this.d = null;
                e2.printStackTrace();
            }
        }
        if (this.f18333j) {
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, "isTimeOut = true \u5df2\u8d85\u65f6\uff0c\u7ed9\u51fa\u8d85\u65f6\u7684\u63d0\u793a\uff0c\u4e0d\u518d\u8fd4\u56de\u8bf7\u6c42\u6210\u529f\u56de\u8c03\u4e86");
            }
        } else if (this.f18331h != null) {
            this.f18336m.post(new e(this, str));
        }
    }

    public void a() {
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " Execut=");
        }
        if (this.f18328e != null && d()) {
            e();
            return;
        }
        if (LogUtil.enableLog) {
            LogUtil.LogE(a, "can't not Execut=");
        }
        a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", "network type_mobile disable"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HttpURLConnection httpURLConnection) throws Exception {
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, " post=");
        }
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(c());
        httpURLConnection.setReadTimeout(c());
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, responseCode + "");
        }
        if (200 == responseCode) {
            String a2 = a(httpURLConnection.getInputStream());
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, " post HTTP_OK result=" + a2);
            }
            a(a2);
            return;
        }
        if (LogUtil.enableLog) {
            LogUtil.LogE(a, "\u8bf7\u6c42\u5931\u8d25 responseCode=" + responseCode);
        }
        a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.d, "\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427", "CT", "response not 200"));
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.io.InputStream r10) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wjlogin.onekey.sdk.common.a.a.g.a(java.io.InputStream):java.lang.String");
    }
}
