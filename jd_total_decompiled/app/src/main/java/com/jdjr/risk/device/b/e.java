package com.jdjr.risk.device.b;

import android.content.Context;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jdjr.risk.device.c.aa;
import com.jdjr.risk.device.c.p;
import com.jdjr.risk.device.c.s;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes18.dex */
public class e extends a {

    /* renamed from: c */
    private String[] f7324c = null;

    public e() {
        this.a = new com.jdjr.risk.device.entity.e();
    }

    private JSONArray a(List<? extends Map> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            Iterator<? extends Map> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(it.next()));
            }
        }
        return jSONArray;
    }

    private void b() {
        if (this.f7324c == null) {
            try {
                this.f7324c = BaseInfo.getMemInfo();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_hardware_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.e eVar = (com.jdjr.risk.device.entity.e) this.a;
            if (jSONObject.optInt("rsl") == 1) {
                eVar.a(p.a(context));
            }
            if (jSONObject.optInt("dip") == 1) {
                eVar.b(p.b(context));
            }
            if (jSONObject.optInt("ppi") == 1) {
                eVar.c(p.b(context));
            }
            if (jSONObject.optInt("hrd") == 1) {
                eVar.d(com.jdjr.risk.device.c.g.o());
            }
            if (jSONObject.optInt("ds") == 1) {
                eVar.e(aa.a(context));
                eVar.a((aa.a() / 1024) / 1024);
            }
            if (jSONObject.optInt("fm") == 1) {
                b();
                String[] strArr = this.f7324c;
                if (strArr != null) {
                    eVar.f(strArr[1]);
                }
            }
            if (jSONObject.optInt("um") == 1) {
                b();
                String[] strArr2 = this.f7324c;
                if (strArr2 != null) {
                    eVar.g(strArr2[2]);
                }
            }
            if (jSONObject.optInt("ms") == 1) {
                b();
                String[] strArr3 = this.f7324c;
                if (strArr3 != null) {
                    eVar.h(strArr3[0]);
                    eVar.b(aa.b());
                }
            }
            if (jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_FD) == 1) {
                eVar.i(aa.b(context));
            }
            if (jSONObject.optInt("blutA") == 1) {
                eVar.j(s.e(context));
            }
            if (jSONObject.optInt("blutN") == 1) {
                eVar.k(s.f(context));
            }
            if (jSONObject.optInt("blut") == 1) {
                eVar.d(s.d());
            }
            if (jSONObject.optInt("rslW") == 1) {
                eVar.e(p.a(0));
            }
            if (jSONObject.optInt("rslH") == 1) {
                eVar.f(p.a(1));
            }
            if (jSONObject.optInt("stg") == 1) {
                eVar.a(a(s.a(context)));
            }
            if (jSONObject.optInt("numC") == 1) {
                eVar.c(s.a());
            }
            if (jSONObject.optInt("backC") == 1) {
                eVar.a(s.c() ? 1 : 0);
            }
            if (jSONObject.optInt("frontC") == 1) {
                eVar.b(s.b() ? 1 : 0);
            }
            if (jSONObject.optInt("sm") == 1) {
                eVar.l("");
            }
            if (jSONObject.optInt(HiAnalyticsConstant.Direction.RESPONSE) == 1) {
                eVar.m(p.c(context));
            }
            if (jSONObject.optInt("realRu") == 1) {
                eVar.n(p.d(context));
            }
        }
    }
}
