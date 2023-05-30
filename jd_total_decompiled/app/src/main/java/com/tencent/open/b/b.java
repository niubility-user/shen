package com.tencent.open.b;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.k;
import com.tencent.open.utils.l;
import com.tencent.open.utils.m;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class b {
    private static b a;
    private String b = "";

    /* renamed from: c */
    private String f17641c = "";
    private String d = "";

    /* renamed from: e */
    private String f17642e = "";

    /* renamed from: f */
    private String f17643f = "";

    /* renamed from: g */
    private String f17644g = "";

    /* renamed from: h */
    private String f17645h = "";

    /* renamed from: i */
    private List<Serializable> f17646i = Collections.synchronizedList(new ArrayList());

    /* renamed from: j */
    private List<Serializable> f17647j = Collections.synchronizedList(new ArrayList());

    /* renamed from: k */
    private Executor f17648k = l.b();

    /* renamed from: l */
    private boolean f17649l;

    private b() {
    }

    private void c() {
        while (!this.f17647j.isEmpty()) {
            c cVar = (c) this.f17647j.remove(0);
            cVar.a.put("appid", this.b);
            cVar.a.put("app_name", this.f17641c);
            cVar.a.put(Constants.PARAM_APP_VER, this.f17642e);
            cVar.a.put(Constants.PARAM_PKG_NAME, this.f17643f);
            cVar.a.put("qq_install", this.f17644g);
            cVar.a.put(Constants.PARAM_QQ_VER, this.f17645h);
            cVar.a.put("openid", this.d);
            cVar.a.put("time_appid_openid", cVar.a.get("time") + CartConstant.KEY_YB_INFO_LINK + this.b + CartConstant.KEY_YB_INFO_LINK + this.d);
            StringBuilder sb = new StringBuilder();
            sb.append("fixDirtyData--------------------------");
            sb.append(cVar);
            SLog.i("AttaReporter", sb.toString());
            this.f17646i.add(cVar);
        }
    }

    public void d() {
        SLog.i("AttaReporter", "attaReportAtSubThread");
        if (!this.f17649l) {
            List<Serializable> b = g.b("report_atta");
            this.f17649l = b.isEmpty();
            this.f17646i.addAll(b);
            Iterator<Serializable> it = b.iterator();
            while (it.hasNext()) {
                SLog.i("AttaReporter", "attaReportAtSubThread from db = " + it.next());
            }
        }
        ArrayList arrayList = new ArrayList();
        while (!this.f17646i.isEmpty()) {
            c cVar = (c) this.f17646i.remove(0);
            if (!b(cVar)) {
                arrayList.add(cVar);
            }
        }
        if (!arrayList.isEmpty()) {
            SLog.i("AttaReporter", "attaReportAtSubThread fail size=" + arrayList.size());
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                SLog.i("AttaReporter", "attaReportAtSubThread fail cache to db, " + ((c) ((Serializable) it2.next())));
            }
            g.a("report_atta", arrayList);
            this.f17649l = false;
        } else if (this.f17649l) {
        } else {
            SLog.i("AttaReporter", "attaReportAtSubThread clear db");
            g.a("report_atta");
            this.f17649l = true;
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    private c b(String str, String str2, Object obj, Map<String, Object> map) {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        hashMap.put("attaid", "09400051119");
        hashMap.put("token", "9389887874");
        hashMap.put("time_appid_openid", currentTimeMillis + CartConstant.KEY_YB_INFO_LINK + this.b + CartConstant.KEY_YB_INFO_LINK + this.d);
        hashMap.put("time", String.valueOf(currentTimeMillis));
        hashMap.put("openid", this.d);
        hashMap.put("appid", this.b);
        hashMap.put("app_name", this.f17641c);
        hashMap.put(Constants.PARAM_APP_VER, this.f17642e);
        hashMap.put(Constants.PARAM_PKG_NAME, this.f17643f);
        hashMap.put("os", "AND");
        hashMap.put("os_ver", Build.VERSION.RELEASE);
        hashMap.put("sdk_ver", Constants.SDK_VERSION);
        hashMap.put(Constants.PARAM_MODEL_NAME, com.tencent.open.utils.f.a().c(com.tencent.open.utils.g.a()));
        hashMap.put("interface_name", str);
        hashMap.put("interface_data", str2);
        hashMap.put("interface_result", obj == null ? "" : obj.toString());
        hashMap.put("qq_install", this.f17644g);
        hashMap.put(Constants.PARAM_QQ_VER, this.f17645h);
        if (map != null && !map.isEmpty()) {
            Object obj2 = map.get("reserve1");
            hashMap.put("reserve1", obj2 == null ? "" : obj2.toString());
            Object obj3 = map.get("reserve2");
            hashMap.put("reserve2", obj3 == null ? "" : obj3.toString());
            Object obj4 = map.get("reserve3");
            hashMap.put("reserve3", obj4 == null ? "" : obj4.toString());
            Object obj5 = map.get("reserve4");
            hashMap.put("reserve4", obj5 != null ? obj5.toString() : "");
        }
        return new c(hashMap);
    }

    public void a(String str, Context context) {
        SLog.i("AttaReporter", XView2Constants.XVIEW2_ACTION_INIT);
        this.b = str;
        this.f17641c = k.a(context);
        this.f17642e = m.d(context, com.tencent.open.utils.g.b());
        this.f17643f = com.tencent.open.utils.g.b();
        this.f17644g = k.b(context) ? "1" : "0";
        this.f17645h = m.c(context, "com.tencent.mobileqq");
        c();
        g.a();
    }

    public void a(String str) {
        SLog.i("AttaReporter", "updateOpenId");
        if (str == null) {
            str = "";
        }
        this.d = str;
    }

    public void a(String str, String str2) {
        a(str, str2, null);
    }

    public void a(String str, String str2, Map<String, Object> map) {
        a(str, str2, "", map);
    }

    public void a(String str, Object obj) {
        a(str, "", obj, null);
    }

    public void a(String str, String str2, Object obj, Map<String, Object> map) {
        c b = b(str, str2, obj, map);
        if (!TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.f17641c) && com.tencent.open.utils.g.a() != null) {
            a(b);
            return;
        }
        SLog.i("AttaReporter", "attaReport cancel appid=" + this.b + ", mAppName=" + this.f17641c + ", context=" + com.tencent.open.utils.g.a() + ", " + b);
        this.f17647j.add(b);
    }

    private void a(final c cVar) {
        this.f17648k.execute(new Runnable() { // from class: com.tencent.open.b.b.1
            {
                b.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f17646i.add(cVar);
                if (m.b(com.tencent.open.utils.g.a())) {
                    try {
                        b.this.d();
                        return;
                    } catch (Exception e2) {
                        SLog.e("AttaReporter", "Exception", e2);
                        return;
                    }
                }
                SLog.i("AttaReporter", "attaReport net disconnect, " + cVar);
            }
        });
    }

    private boolean b(c cVar) {
        int i2 = 0;
        do {
            i2++;
            try {
                SLog.i("AttaReporter", "doAttaReportItem post " + cVar);
                return com.tencent.open.a.f.a().b("https://h.trace.qq.com/kv", cVar.a).d() == 200;
            } catch (Exception e2) {
                SLog.i("AttaReporter", "Exception", e2);
            }
        } while (i2 < 2);
        return false;
    }

    public static String b() {
        return a().b;
    }
}
