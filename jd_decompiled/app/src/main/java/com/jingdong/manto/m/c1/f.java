package com.jingdong.manto.m.c1;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.URLUtil;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.p.h.c;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.r;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.s;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends com.jingdong.manto.m.c1.a {
    private Handler b = new Handler();

    /* loaded from: classes15.dex */
    class a implements c.a {
        private Runnable a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13311c;
        final /* synthetic */ String d;

        /* renamed from: com.jingdong.manto.m.c1.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0553a implements Runnable {
            final /* synthetic */ com.jingdong.manto.m.d a;

            RunnableC0553a(a aVar, com.jingdong.manto.m.d dVar) {
                this.a = dVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.a();
            }
        }

        a(com.jingdong.manto.h hVar, String str, String str2) {
            this.b = hVar;
            this.f13311c = str;
            this.d = str2;
        }

        @Override // com.jingdong.manto.p.h.c.a
        public final void a(int i2, long j2, long j3) {
            HashMap hashMap = new HashMap();
            hashMap.put("uploadTaskId", this.f13311c);
            hashMap.put(XView2Constants.STATE, "progressUpdate");
            hashMap.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(i2));
            hashMap.put("totalBytesSent", Long.valueOf(j2));
            hashMap.put("totalBytesExpectedToSend", Long.valueOf(j3));
            com.jingdong.manto.m.d a = new b().a(this.b).a(hashMap);
            f.this.b.removeCallbacks(this.a);
            this.a = new RunnableC0553a(this, a);
            f.this.b.post(this.a);
        }

        @Override // com.jingdong.manto.p.h.c.a
        public final void a(int i2, String str, int i3, JSONObject jSONObject) {
            if (i2 != com.jingdong.manto.p.h.c.f13964e) {
                if (f.a(this.b, this.f13311c)) {
                    return;
                }
                MantoLog.e("JsApiCreateUploadTask", String.format("error: file name %s, result %s , errMsg %d", this.d, str, Integer.valueOf(i2)));
                f.b(this.b, this.f13311c, str);
                return;
            }
            MantoLog.d("JsApiCreateUploadTask", String.format("success: file name %s, result %s , errMsg %d", this.d, str, Integer.valueOf(i2)));
            HashMap hashMap = new HashMap();
            hashMap.put("data", str);
            hashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, Integer.valueOf(i3));
            hashMap.put("uploadTaskId", this.f13311c);
            if (jSONObject != null) {
                hashMap.put("header", jSONObject);
            }
            hashMap.put(XView2Constants.STATE, "success");
            new b().a(this.b).a(hashMap).a();
        }

        @Override // com.jingdong.manto.p.h.c.a
        public final void a(String str) {
            if (f.a(this.b, this.f13311c)) {
                return;
            }
            f.b(this.b, this.f13311c, str);
        }
    }

    /* loaded from: classes15.dex */
    public static class b extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onUploadTaskStateChange";
        }
    }

    static boolean a(com.jingdong.manto.h hVar, String str) {
        com.jingdong.manto.p.h.c a2 = com.jingdong.manto.p.h.a.b().a(hVar.c());
        if (a2 == null || !a2.b(str)) {
            return false;
        }
        MantoLog.i("JsApiCreateUploadTask", String.format("upload abort %s", str));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.jingdong.manto.h hVar, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("uploadTaskId", str);
        hashMap.put(XView2Constants.STATE, "fail");
        hashMap.put("errMsg", str2);
        new b().a(hVar).a(hashMap).a();
    }

    @Override // com.jingdong.manto.m.c1.a
    public final void a(com.jingdong.manto.h hVar, JSONObject jSONObject, String str) {
        com.jingdong.manto.p.h.c cVar;
        String str2;
        r rVar;
        r rVar2;
        MantoLog.d("JsApiCreateUploadTask", "JsApiCreateUploadTask");
        String c2 = hVar.c();
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
        if (TextUtils.isEmpty(optString)) {
            b(hVar, str, "filePath is null");
            return;
        }
        MantoLog.i("JsApiCreateUploadTask", "tempFilePath " + optString);
        com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(c2, optString);
        if (g2 == null) {
            b(hVar, str, "fail:file doesn't exist");
            return;
        }
        String str3 = g2.b;
        String str4 = g2.f14209c;
        MantoLog.i("JsApiCreateUploadTask", String.format("filePath(%s)", str3));
        a aVar = new a(hVar, str, str3);
        com.jingdong.manto.i.e eVar = hVar.h().s;
        Map<String, String> a2 = com.jingdong.manto.p.c.a(jSONObject, eVar);
        String optString2 = jSONObject.optString("url");
        if (TextUtils.isEmpty(optString2)) {
            MantoLog.i("JsApiCreateUploadTask", "url is null");
            b(hVar, str, "url is null or nil");
        } else if (!com.jingdong.manto.p.c.a(eVar, jSONObject.optBoolean("__skipDomainCheck__", false)) && !com.jingdong.manto.p.c.a(eVar.f13103m, optString2)) {
            MantoLog.i("JsApiCreateUploadTask", String.format("not in domain url %s", optString2));
            b(hVar, str, "url not in domain list");
        } else {
            if (eVar.f13096f <= 0) {
                MantoLog.i("JsApiCreateUploadTask", "concurrent <= 0 ");
            }
            int a3 = com.jingdong.manto.p.c.a(eVar, hVar, com.jingdong.manto.p.c.UPLOAD);
            int i2 = a3 <= 0 ? 60000 : a3;
            com.jingdong.manto.p.h.c a4 = com.jingdong.manto.p.h.a.b().a(c2);
            if (a4 == null) {
                n pageView = c0.getPageView(hVar);
                cVar = new com.jingdong.manto.p.h.c(c2, (pageView == null || (rVar2 = pageView.t) == null) ? null : rVar2.getSettings().getUserAgentString(), hVar.h().s);
                com.jingdong.manto.p.h.a b2 = com.jingdong.manto.p.h.a.b();
                if (!b2.a.containsKey(c2)) {
                    b2.a.put(c2, cVar);
                }
            } else {
                cVar = a4;
            }
            if (optString.startsWith("jdfile://")) {
                str2 = s.f(optString);
            } else {
                str2 = "jd-file." + g2.d;
            }
            String optString3 = jSONObject.optString("name");
            n pageView2 = c0.getPageView(hVar);
            com.jingdong.manto.p.h.c cVar2 = cVar;
            com.jingdong.manto.p.h.b bVar = new com.jingdong.manto.p.h.b(c2, optString2, optString3, str2, (pageView2 == null || (rVar = pageView2.t) == null) ? null : rVar.getSettings().getUserAgentString(), i2, str4, aVar);
            bVar.f13957i = str;
            bVar.f13952c = a2;
            bVar.f13953e = str3;
            ArrayList<String> arrayList = eVar.f13103m;
            bVar.f13955g = com.jingdong.manto.p.c.a(jSONObject);
            if (!URLUtil.isHttpsUrl(optString2) && !URLUtil.isHttpUrl(optString2)) {
                bVar.f13956h.a("request protocol must be http or https");
                return;
            }
            synchronized (cVar2.a) {
                if (cVar2.a.size() >= cVar2.b) {
                    bVar.f13956h.a("max_connected");
                    MantoLog.i("NetworkDownload", "max connected");
                    return;
                }
                cVar2.a.add(bVar);
                com.jingdong.manto.b.d().networkIO().execute(bVar);
            }
        }
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String b() {
        return "uploadTaskId";
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String c() {
        com.jingdong.manto.p.h.a.b();
        return String.valueOf(com.jingdong.manto.p.h.a.a());
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createUploadTask";
    }
}
