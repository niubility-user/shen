package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class i {
    private static Map<String, i> a = Collections.synchronizedMap(new HashMap());
    private static String b = null;

    /* renamed from: c  reason: collision with root package name */
    private Context f17705c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f17706e = null;

    /* renamed from: f  reason: collision with root package name */
    private long f17707f = 0;

    /* renamed from: g  reason: collision with root package name */
    private int f17708g = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f17709h = true;

    private i(Context context, String str) {
        this.f17705c = null;
        this.d = null;
        this.f17705c = context.getApplicationContext();
        this.d = str;
        a();
        b();
    }

    private void b() {
        if (this.f17708g != 0) {
            d("update thread is running, return");
            return;
        }
        this.f17708g = 1;
        final HashMap hashMap = new HashMap();
        hashMap.put("appid", this.d);
        hashMap.put("status_os", Build.VERSION.RELEASE);
        hashMap.put("status_machine", f.a().c(g.a()));
        hashMap.put("status_version", Build.VERSION.SDK);
        hashMap.put("sdkv", Constants.SDK_VERSION);
        hashMap.put("sdkp", com.jingdong.jdsdk.a.a.a);
        l.a(new Runnable() { // from class: com.tencent.open.utils.i.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.tencent.open.a.g a2 = com.tencent.open.a.f.a().a("https://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", hashMap);
                    String a3 = a2.a();
                    SLog.i("openSDK_LOG.OpenConfig", "update: get config statusCode " + a2.d());
                    i.this.a(m.d(a3));
                } catch (Exception e2) {
                    SLog.e("openSDK_LOG.OpenConfig", "get config error ", e2);
                }
                i.this.f17708g = 0;
            }
        });
    }

    private String c(String str) {
        InputStream open;
        String str2;
        String str3 = "";
        try {
            try {
                if (this.d != null) {
                    str2 = str + OrderISVUtil.MONEY_DECIMAL + this.d;
                } else {
                    str2 = str;
                }
                open = this.f17705c.openFileInput(str2);
            } catch (FileNotFoundException unused) {
                open = this.f17705c.getAssets().open(str);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, Charset.forName("UTF-8")));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    try {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                        } catch (Throwable th) {
                            try {
                                open.close();
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        open.close();
                        bufferedReader.close();
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            str3 = stringBuffer.toString();
            open.close();
            bufferedReader.close();
            return str3;
        } catch (IOException e5) {
            e5.printStackTrace();
            return "";
        }
    }

    private void d(String str) {
        if (this.f17709h) {
            SLog.v("openSDK_LOG.OpenConfig", str + "; appid: " + this.d);
        }
    }

    public static i a(Context context, String str) {
        i iVar;
        synchronized (a) {
            SLog.v("openSDK_LOG.OpenConfig", "getInstance begin");
            if (str != null) {
                b = str;
            }
            if (str == null && (str = b) == null) {
                str = "0";
            }
            iVar = a.get(str);
            if (iVar == null) {
                iVar = new i(context, str);
                a.put(str, iVar);
            }
            SLog.v("openSDK_LOG.OpenConfig", "getInstance end");
        }
        return iVar;
    }

    public boolean b(String str) {
        d("get " + str);
        c();
        Object opt = this.f17706e.opt(str);
        if (opt == null) {
            return false;
        }
        if (opt instanceof Integer) {
            return !opt.equals(0);
        }
        if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        }
        return false;
    }

    private void a() {
        try {
            this.f17706e = new JSONObject(c("com.tencent.open.config.json"));
        } catch (JSONException unused) {
            this.f17706e = new JSONObject();
        }
    }

    private void a(String str, String str2) {
        try {
            if (this.d != null) {
                str = str + OrderISVUtil.MONEY_DECIMAL + this.d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.f17705c.openFileOutput(str, 0), Charset.forName("UTF-8"));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        int optInt = this.f17706e.optInt("Common_frequency");
        if (optInt == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f17707f >= optInt * UnTimeUtils.HOUR) {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        d("cgi back, do update");
        this.f17706e = jSONObject;
        a("com.tencent.open.config.json", jSONObject.toString());
        this.f17707f = SystemClock.elapsedRealtime();
    }

    public int a(String str) {
        d("get " + str);
        c();
        return this.f17706e.optInt(str);
    }
}
