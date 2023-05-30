package com.jingdong.manto.m.c1;

import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.p.e.c;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.r;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoMd5Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends com.jingdong.manto.m.c1.a {
    private Handler b = new Handler();

    /* loaded from: classes15.dex */
    class a implements c.a {
        private Runnable a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13309c;

        /* renamed from: com.jingdong.manto.m.c1.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0552a implements Runnable {
            final /* synthetic */ com.jingdong.manto.m.d a;

            RunnableC0552a(a aVar, com.jingdong.manto.m.d dVar) {
                this.a = dVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.a();
            }
        }

        a(com.jingdong.manto.h hVar, String str) {
            this.b = hVar;
            this.f13309c = str;
        }

        @Override // com.jingdong.manto.p.e.c.a
        public final void a(int i2, long j2, long j3) {
            HashMap hashMap = new HashMap();
            hashMap.put("downloadTaskId", this.f13309c);
            hashMap.put(XView2Constants.STATE, "progressUpdate");
            hashMap.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(i2));
            hashMap.put("totalBytesWritten", Long.valueOf(j2));
            hashMap.put("totalBytesExpectedToWrite", Long.valueOf(j3));
            com.jingdong.manto.m.d a = new b().a(this.b).a(hashMap);
            c.this.b.removeCallbacks(this.a);
            this.a = new RunnableC0552a(this, a);
            c.this.b.post(this.a);
        }

        @Override // com.jingdong.manto.p.e.c.a
        public final void a(int i2, String str, String str2, int i3, JSONObject jSONObject) {
            com.jingdong.manto.t.d a;
            MantoLog.i("JsApiCreateDownloadTask", String.format(Locale.getDefault(), "onDownloadResultWithCode errMsg = %d, mimeType = %s, filePath = %s, statusCode = %s", Integer.valueOf(i2), str, str2, Integer.valueOf(i3)));
            if (com.jingdong.manto.p.e.c.f13927h == i2 || (a = com.jingdong.manto.t.c.a(this.b.c(), str2, str, true)) == null) {
                if (c.b(this.b, this.f13309c)) {
                    return;
                }
                c.b(this.b, this.f13309c, "download fail");
                return;
            }
            MantoLog.i("JsApiCreateDownloadTask", String.format("onDownloadResultWithCode localId %s", a.a));
            HashMap hashMap = new HashMap();
            hashMap.put("downloadTaskId", this.f13309c);
            hashMap.put("tempFilePath", a.a);
            hashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, Integer.valueOf(i3));
            if (jSONObject != null) {
                hashMap.put("header", jSONObject);
            }
            hashMap.put(XView2Constants.STATE, "success");
            new b().a(this.b).a(hashMap).a();
        }

        @Override // com.jingdong.manto.p.e.c.a
        public final void a(String str) {
            if (c.b(this.b, this.f13309c)) {
                return;
            }
            c.b(this.b, this.f13309c, str);
        }

        @Override // com.jingdong.manto.p.e.c.a
        public void a(JSONObject jSONObject) {
            HashMap hashMap = new HashMap();
            hashMap.put("downloadTaskId", this.f13309c);
            if (jSONObject != null) {
                hashMap.put("header", jSONObject);
            }
            hashMap.put(XView2Constants.STATE, "headersReceived");
            new b().a(this.b).a(hashMap).a();
        }
    }

    /* loaded from: classes15.dex */
    public static class b extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onDownloadTaskStateChange";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.jingdong.manto.h hVar, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("downloadTaskId", str);
        hashMap.put(XView2Constants.STATE, "fail");
        hashMap.put("errMsg", str2);
        String jSONObject = new JSONObject(hashMap).toString();
        com.jingdong.manto.m.d a2 = new b().a(hVar);
        a2.f13315c = jSONObject;
        a2.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(com.jingdong.manto.h hVar, String str) {
        com.jingdong.manto.p.e.c a2 = com.jingdong.manto.p.e.a.b().a(hVar.c());
        if (a2 == null || !a2.b(str)) {
            return false;
        }
        MantoLog.i("JsApiCreateDownloadTask", String.format("download abort %s", str));
        return true;
    }

    @Override // com.jingdong.manto.m.c1.a
    public final void a(com.jingdong.manto.h hVar, JSONObject jSONObject, String str) {
        r rVar;
        String str2;
        MantoLog.d("JsApiCreateDownloadTask", "JsApiCreateDownloadTask");
        a aVar = new a(hVar, str);
        com.jingdong.manto.i.e eVar = hVar.h().s;
        Map<String, String> a2 = com.jingdong.manto.p.c.a(jSONObject, eVar);
        String optString = jSONObject.optString("url");
        if (TextUtils.isEmpty(optString)) {
            MantoLog.i("JsApiCreateDownloadTask", "url is null");
            str2 = "url is null or nil";
        } else {
            boolean a3 = com.jingdong.manto.p.c.a(eVar, jSONObject.optBoolean("__skipDomainCheck__", false));
            if (a3 || com.jingdong.manto.p.c.a(eVar.f13104n, optString)) {
                if (eVar.f13097g <= 0) {
                    MantoLog.i("JsApiCreateDownloadTask", "maxDownloadConcurrent <= 0 ");
                }
                int a4 = com.jingdong.manto.p.c.a(eVar, hVar, com.jingdong.manto.p.c.DOWNLOAD);
                if (a4 <= 0) {
                    a4 = 60000;
                }
                com.jingdong.manto.p.e.c a5 = com.jingdong.manto.p.e.a.b().a(hVar.c());
                if (a5 == null) {
                    n pageView = c0.getPageView(hVar);
                    String str3 = null;
                    if (pageView != null && (rVar = pageView.t) != null) {
                        str3 = rVar.getSettings().getUserAgentString();
                    }
                    a5 = new com.jingdong.manto.p.e.c(hVar.a(), str3, hVar.h().s);
                    com.jingdong.manto.p.e.a.b().a(hVar.c(), a5);
                }
                if (a5 != null) {
                    MantoLog.i("JsApiCreateDownloadTask", String.format("before do download, checkDomains = %b", Boolean.valueOf(a3)));
                    boolean w = hVar.h().w();
                    com.jingdong.manto.i.b bVar = hVar.h().s.p;
                    if (w) {
                        int i2 = bVar.f13074e;
                    } else {
                        int i3 = bVar.a;
                    }
                    if (a3) {
                        new ArrayList();
                    } else {
                        ArrayList<String> arrayList = eVar.f13104n;
                    }
                    String optString2 = jSONObject.optString("url");
                    synchronized (a5.f13931f) {
                        if (a5.f13931f.size() >= a5.a) {
                            aVar.a("max_connected");
                            MantoLog.i("JsApiCreateDownloadTask", "max connected");
                            return;
                        }
                        new File(a5.b).mkdirs();
                        com.jingdong.manto.p.e.b bVar2 = new com.jingdong.manto.p.e.b(a5.f13932g, optString2, a5.b + MantoMd5Utils.md5OfString(optString2) + "temp", a5.d, new c.b(a5, str, aVar));
                        bVar2.f13921g = a2;
                        bVar2.d = a4;
                        SSLContext sSLContext = a5.f13929c;
                        bVar2.f13923i = str;
                        synchronized (a5.f13931f) {
                            a5.f13931f.add(bVar2);
                        }
                        com.jingdong.manto.b.d().networkIO().execute(bVar2);
                        return;
                    }
                }
                return;
            }
            MantoLog.i("JsApiCreateDownloadTask", String.format("not in domain url %s", optString));
            str2 = "url not in domain list";
        }
        b(hVar, str, str2);
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String b() {
        return "downloadTaskId";
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String c() {
        com.jingdong.manto.p.e.a.b();
        return String.valueOf(com.jingdong.manto.p.e.a.a());
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createDownloadTask";
    }
}
