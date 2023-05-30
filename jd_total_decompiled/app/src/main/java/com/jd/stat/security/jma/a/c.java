package com.jd.stat.security.jma.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.jd.stat.common.MonitorService;
import com.jd.stat.common.TriTouchUtil;
import com.jd.stat.common.c;
import com.jd.stat.common.j;
import com.jd.stat.common.k;
import com.jd.stat.common.n;
import com.jd.stat.common.p;
import com.jd.stat.common.process.Status;
import com.jd.stat.common.u;
import com.jd.stat.common.w;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c extends b {
    private boolean c(String str) {
        com.jd.stat.security.b d;
        if (TextUtils.isEmpty(a())) {
            return false;
        }
        String b = b();
        if (!TextUtils.isEmpty(b) && (d = com.jd.stat.security.d.a().d(b)) != null && TextUtils.equals(d.e(), a()) && d.d() == 2) {
            String str2 = "alter:" + str;
            if (d.a(str2)) {
                return com.jd.stat.security.c.a(str2, a());
            }
            return false;
        }
        return false;
    }

    @Override // com.jd.stat.security.jma.a.g
    public JSONObject a(Context context) {
        com.jd.stat.common.b.e eVar = new com.jd.stat.common.b.e("alter");
        try {
            eVar.put("networkInfo", k.a(context));
            eVar.put("freeDiskSpace", n.a(context));
            eVar.put("ipAddress", n.m());
            eVar.put("currentTime", com.jd.stat.common.b.g.a());
            eVar.put("cpuFrequency", n.i());
            eVar.put("headphoneAttached", n.g(context));
            eVar.put("debug", com.jd.stat.common.e.a() + com.jd.stat.common.e.c() + com.jd.stat.common.e.b() + com.jd.stat.common.e.d() + com.jd.stat.common.e.e() + com.jd.stat.common.e.f());
            eVar.put("ssid", c("ssid") ? BaseInfo.getWifiSSID(context) : com.jingdong.jdsdk.a.a.a);
            c.a f2 = com.jd.stat.common.c.f(context);
            eVar.put("multi_open", f2.a);
            eVar.put("mnbml", f2.f6984c);
            eVar.put("mnbmz", f2.d);
            eVar.put("trpid", Status.a("TracerPid"));
            eVar.put("socket", com.jd.stat.common.process.c.a());
            eVar.put("oaid", com.jd.stat.security.c.i());
            eVar.put("sdkversion", "2.5.8");
            eVar.put("tnt", k.c());
            eVar.put("totalDiskSpace", n.b(context));
            eVar.put("jz", com.jingdong.jdsdk.a.a.a);
            eVar.put("kc", n.m(context));
            eVar.put("jw", com.jingdong.jdsdk.a.a.a);
            com.jd.stat.common.b.c.a(eVar, w.c());
            com.jd.stat.common.b.c.a(eVar, MonitorService.f().a());
            com.jd.stat.common.b.c.a(eVar, com.jd.stat.common.d.a(context).c());
            com.jd.stat.common.b.c.a(eVar, com.jd.stat.common.d.a(context).e());
            eVar.put("pif", n.u().equals("") ? "-1" : n.u());
            eVar.put("aea", com.jd.stat.common.a.b(context));
            eVar.put("esa", com.jd.stat.common.a.c(context));
            eVar.put("atf", com.jd.stat.common.f.a(context));
            eVar.put("aead", com.jd.stat.common.a.a(context, true));
            eVar.put("ptt", com.jd.stat.security.c.k() ? "1" : "0");
            eVar.put("ctp", MonitorService.f().c());
            eVar.put("nsl", k.b(context));
            eVar.put("trit", TriTouchUtil.getInstance().getTriTouch());
            eVar.put("hkd", j.b(context));
            eVar.put("jjk", u.b());
            eVar.put("nver", u.a(context));
            Pair<String, String> g2 = com.jd.stat.common.c.g(context);
            eVar.put("weg", g2.first);
            eVar.put("jkdf", g2.second);
            eVar.put("jms", com.jd.stat.common.h.a());
            eVar.put("sstt", p.d());
            eVar.put("nwej", p.e());
            p.a();
            eVar.put("cctm", eVar.a());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return eVar;
    }
}
