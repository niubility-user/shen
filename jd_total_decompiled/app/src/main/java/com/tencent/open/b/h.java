package com.tencent.open.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.adapter.internal.CommonCode;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.i;
import com.tencent.open.utils.l;
import com.tencent.open.utils.m;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes9.dex */
public class h {
    protected static h a;

    /* renamed from: e */
    protected HandlerThread f17651e;

    /* renamed from: f */
    protected Handler f17652f;
    protected Random b = new SecureRandom();
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());

    /* renamed from: c */
    protected List<Serializable> f17650c = Collections.synchronizedList(new ArrayList());

    /* renamed from: g */
    protected Executor f17653g = l.b();

    /* renamed from: h */
    protected Executor f17654h = l.b();

    private h() {
        this.f17651e = null;
        if (this.f17651e == null) {
            HandlerThread handlerThread = new HandlerThread("opensdk.report.handlerthread", 10);
            this.f17651e = handlerThread;
            handlerThread.start();
        }
        if (!this.f17651e.isAlive() || this.f17651e.getLooper() == null) {
            return;
        }
        this.f17652f = new Handler(this.f17651e.getLooper()) { // from class: com.tencent.open.b.h.1
            {
                h.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    h.this.b();
                } else if (i2 == 1001) {
                    h.this.d();
                }
                super.handleMessage(message);
            }
        };
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h();
            }
            hVar = a;
        }
        return hVar;
    }

    protected void b() {
    }

    protected Map<String, String> c() {
        List<Serializable> b = g.b("report_via");
        if (b != null) {
            this.d.addAll(b);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<Serializable> it = this.d.iterator();
        while (it.hasNext()) {
            JSONObject jSONObject = new JSONObject();
            c cVar = (c) it.next();
            for (String str : cVar.a.keySet()) {
                try {
                    String str2 = cVar.a.get(str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                } catch (JSONException e2) {
                    SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
                }
            }
            jSONArray.put(jSONObject);
        }
        SLog.v("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject2.toString());
            return hashMap;
        } catch (JSONException e3) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e3);
            return null;
        }
    }

    protected void d() {
        if (m.b(com.tencent.open.utils.g.a())) {
            this.f17653g.execute(new Runnable() { // from class: com.tencent.open.b.h.3
                {
                    h.this = this;
                }

                /* JADX WARN: Code restructure failed: missing block: B:163:0x008e, code lost:
                    r18 = r5;
                    r22 = r9;
                    r20 = r14;
                    r7 = true;
                 */
                /* JADX WARN: Removed duplicated region for block: B:204:0x00bf A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:208:? A[LOOP:0: B:148:0x002f->B:208:?, LOOP_END, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void run() {
                    int i2;
                    long j2;
                    long j3;
                    long j4;
                    long j5;
                    long j6;
                    com.tencent.open.a.g b;
                    JSONObject d;
                    long c2;
                    int i3;
                    try {
                        Map<String, String> c3 = h.this.c();
                        if (c3 == null) {
                            return;
                        }
                        SLog.d("openSDK_LOG.ReportManager", "-->doReportVia, params: " + c3.toString());
                        int a2 = f.a();
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        int i4 = 0;
                        int i5 = 0;
                        while (true) {
                            int i6 = i4 + 1;
                            try {
                                try {
                                    b = com.tencent.open.a.f.a().b("https://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report", c3);
                                    SLog.i("openSDK_LOG.ReportManager", "-->reportVia: statusCode " + b.d());
                                    d = m.d(b.a());
                                    c2 = (long) b.c();
                                    i2 = i6;
                                    j3 = b.b();
                                } catch (SocketTimeoutException unused) {
                                    i2 = i6;
                                } catch (IOException e2) {
                                    e = e2;
                                    i2 = i6;
                                } catch (JSONException unused2) {
                                    i2 = i6;
                                }
                                try {
                                    try {
                                    } catch (SocketTimeoutException unused3) {
                                        j2 = 0;
                                        elapsedRealtime = SystemClock.elapsedRealtime();
                                        i5 = -8;
                                        j3 = j2;
                                        i4 = i2;
                                        if (i4 < a2) {
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        j2 = 0;
                                        i5 = HttpUtils.getErrorCodeFromException(e);
                                        j3 = j2;
                                        i4 = i2;
                                        if (i4 < a2) {
                                        }
                                    }
                                } catch (JSONException unused4) {
                                    j2 = 0;
                                    j3 = 0;
                                    i4 = i2;
                                    i5 = -4;
                                    if (i4 < a2) {
                                    }
                                }
                            } catch (Exception unused5) {
                                j2 = 0;
                                i5 = -6;
                                i4 = a2;
                                j3 = 0;
                            }
                            if (b.d() != 200) {
                                i5 = b.d();
                                j4 = elapsedRealtime;
                                j5 = j3;
                                j6 = c2;
                                break;
                            }
                            try {
                                i3 = d.getInt("ret");
                            } catch (JSONException unused6) {
                                i3 = -4;
                            }
                            if (i3 == 0 || j3 != 0) {
                                break;
                            }
                            j2 = c2;
                            i4 = i2;
                            if (i4 < a2) {
                                j4 = elapsedRealtime;
                                j5 = j3;
                                j6 = j2;
                                break;
                            }
                        }
                        boolean z = false;
                        h.this.a("mapp_apptrace_sdk", j4, j6, j5, i5, null, false);
                        if (z) {
                            g.a("report_via");
                        } else {
                            g.a("report_via", h.this.d);
                        }
                        h.this.d.clear();
                        SLog.i("openSDK_LOG.ReportManager", "-->doReportVia, uploadSuccess: " + z + " resultCode: " + i5);
                    } catch (Exception e4) {
                        SLog.e("openSDK_LOG.ReportManager", "-->doReportVia, exception in serial executor.", e4);
                    }
                }
            });
        }
    }

    public void a(final Bundle bundle, String str, final boolean z) {
        if (bundle == null) {
            return;
        }
        SLog.v("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
        if (a("report_via", str) || z) {
            this.f17653g.execute(new Runnable() { // from class: com.tencent.open.b.h.2
                {
                    h.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("uin", Constants.DEFAULT_UIN);
                        bundle2.putString(Constants.PARAM_PLATFORM, "1");
                        bundle2.putString("os_ver", Build.VERSION.RELEASE);
                        bundle2.putString("position", "");
                        bundle2.putString("network", a.a(com.tencent.open.utils.g.a()));
                        bundle2.putString(IjkMediaMeta.IJKM_KEY_LANGUAGE, d.a());
                        bundle2.putString(CommonCode.MapKey.HAS_RESOLUTION, d.a(com.tencent.open.utils.g.a()));
                        bundle2.putString("apn", a.b(com.tencent.open.utils.g.a()));
                        bundle2.putString(Constants.PARAM_MODEL_NAME, com.tencent.open.utils.f.a().c(com.tencent.open.utils.g.a()));
                        bundle2.putString("timezone", TimeZone.getDefault().getID());
                        bundle2.putString("sdk_ver", Constants.SDK_VERSION);
                        bundle2.putString("qz_ver", m.d(com.tencent.open.utils.g.a(), "com.qzone"));
                        bundle2.putString(Constants.PARAM_QQ_VER, m.c(com.tencent.open.utils.g.a(), "com.tencent.mobileqq"));
                        bundle2.putString("qua", m.e(com.tencent.open.utils.g.a(), com.tencent.open.utils.g.b()));
                        bundle2.putString("packagename", com.tencent.open.utils.g.b());
                        bundle2.putString(Constants.PARAM_APP_VER, m.d(com.tencent.open.utils.g.a(), com.tencent.open.utils.g.b()));
                        Bundle bundle3 = bundle;
                        if (bundle3 != null) {
                            bundle2.putAll(bundle3);
                        }
                        h.this.d.add(new c(bundle2));
                        int size = h.this.d.size();
                        int a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Agent_ReportTimeInterval");
                        if (a2 == 0) {
                            a2 = 10000;
                        }
                        if (!h.this.a("report_via", size) && !z) {
                            if (h.this.f17652f.hasMessages(1001)) {
                                return;
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 1001;
                            h.this.f17652f.sendMessageDelayed(obtain, a2);
                            return;
                        }
                        h.this.d();
                        h.this.f17652f.removeMessages(1001);
                    } catch (Exception e2) {
                        SLog.e("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e2);
                    }
                }
            });
        }
    }

    public void a(String str, long j2, long j3, long j4, int i2) {
        a(str, j2, j3, j4, i2, "", false);
    }

    public void a(String str, long j2, long j3, long j4, int i2, String str2, boolean z) {
        SLog.v("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j2 + " | reqSize:" + j3 + " | rspSize: " + j4 + " | responseCode: " + i2 + " | detail: " + str2);
    }

    protected boolean a(String str, String str2) {
        int a2;
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i2 = 100;
        if (str.equals("report_cgi")) {
            try {
                a2 = a(Integer.parseInt(str2));
                if (this.b.nextInt(100) < a2) {
                    z = true;
                }
            } catch (Exception unused) {
                return false;
            }
        } else {
            if (str.equals("report_via")) {
                a2 = f.a(str2);
                if (this.b.nextInt(100) < a2) {
                    i2 = a2;
                    z = true;
                }
            }
            SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i2);
            return z;
        }
        i2 = a2;
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i2);
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0019, code lost:
        if (r0 == 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x001c, code lost:
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0034, code lost:
        if (r0 == 0) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected boolean a(String str, int i2) {
        int a2;
        int i3 = 5;
        if (str.equals("report_cgi")) {
            a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_CGIReportMaxcount");
        } else if (str.equals("report_via")) {
            a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Agent_ReportBatchCount");
        } else {
            i3 = 0;
        }
        SLog.d("openSDK_LOG.ReportManager", "-->availableCount, report: " + str + " | dataSize: " + i2 + " | maxcount: " + i3);
        return i2 >= i3;
    }

    protected int a(int i2) {
        if (i2 == 0) {
            int a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_CGIReportFrequencySuccess");
            if (a2 == 0) {
                return 10;
            }
            return a2;
        }
        int a3 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_CGIReportFrequencyFailed");
        if (a3 == 0) {
            return 100;
        }
        return a3;
    }

    public void a(final String str, final Map<String, String> map) {
        if (m.b(com.tencent.open.utils.g.a())) {
            l.b(new Runnable() { // from class: com.tencent.open.b.h.4
                {
                    h.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    int i2 = 0;
                    try {
                        int a2 = f.a();
                        if (a2 == 0) {
                            a2 = 3;
                        }
                        SLog.d("openSDK_LOG.ReportManager", "-->httpRequest, retryCount: " + a2);
                        do {
                            i2++;
                            try {
                                SLog.i("openSDK_LOG.ReportManager", "-->httpRequest, statusCode: " + com.tencent.open.a.f.a().a(str, map).d());
                            } catch (SocketTimeoutException e2) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException:", e2);
                            } catch (Exception e3) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception:", e3);
                            }
                        } while (i2 < a2);
                    } catch (Exception e4) {
                        SLog.e("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor:", e4);
                    }
                }
            });
        }
    }
}
