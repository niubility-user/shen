package com.wjlogin.onekey.sdk.common.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.common.net.HttpHeaders;
import com.wjlogin.onekey.sdk.util.LogUtil;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    */
    private void e() {
        try {
            f();
            String str = this.f18329f;
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("url=");
                sb.append(this.f18329f);
                LogUtil.LogI(a, sb.toString());
            }
            URL url = new URL(str);
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.d != null) {
                    try {
                        if (LogUtil.enableLog) {
                            LogUtil.LogI(a, "exeRequest unregisterNetworkCallback");
                        }
                        this.f18328e.unregisterNetworkCallback(this.d);
                    } catch (Exception e2) {
                        this.d = null;
                        e2.printStackTrace();
                        a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", e2.getMessage()));
                    }
                }
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addCapability(12);
                builder.addTransportType(0);
                NetworkRequest build = builder.build();
                if (LogUtil.enableLog) {
                    LogUtil.LogI("request network");
                }
                f fVar = new f(this, url);
                this.d = fVar;
                this.f18328e.requestNetwork(build, fVar);
                return;
            }
            if (LogUtil.enableLog) {
                LogUtil.LogI(a, " post android5.0 \u4e0b");
            }
            NetworkInfo activeNetworkInfo = this.f18328e.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected()) {
                try {
                    a((HttpURLConnection) url.openConnection());
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", e3.getMessage()));
                    return;
                }
            }
            a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", "network type_mobile disable"));
            return;
        } catch (Exception e4) {
            e4.printStackTrace();
            if (LogUtil.enableLog) {
            }
            a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", "network type_mobile disable"));
        }
        e4.printStackTrace();
        if (LogUtil.enableLog) {
            LogUtil.LogE(a, "Exception =" + e4.toString());
        }
        a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", "network type_mobile disable"));
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
    */
    public String a(InputStream inputStream) {
        BufferedReader bufferedReader;
        Exception e2;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                if (LogUtil.enableLog) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("streamToString response = ");
                    sb2.append((Object) sb);
                    LogUtil.LogI(a, sb2.toString());
                }
                String sb3 = sb.toString();
                if (LogUtil.enableLog) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("httppost json = ");
                    sb4.append(sb3);
                    LogUtil.LogI(a, sb4.toString());
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e3.getMessage()));
                    }
                }
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                    a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e4.getMessage()));
                }
                return sb3;
            } catch (Exception e5) {
                e2 = e5;
                try {
                    if (LogUtil.enableLog) {
                        LogUtil.LogE(a, e2.toString());
                    }
                    a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e2.getMessage()));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e6.getMessage()));
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            return "";
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e7.getMessage()));
                            return "";
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    bufferedReader = bufferedReader2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                            a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e8.getMessage()));
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            a(com.wjlogin.onekey.sdk.util.a.a(com.wjlogin.onekey.sdk.common.a.b.c.f18341f, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", "CT", e9.getMessage()));
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                }
                if (bufferedReader != null) {
                }
                throw th;
            }
        } catch (Exception e10) {
            bufferedReader = null;
            e2 = e10;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = bufferedReader2;
            if (inputStream != null) {
            }
            if (bufferedReader != null) {
            }
            throw th;
        }
    }
}
