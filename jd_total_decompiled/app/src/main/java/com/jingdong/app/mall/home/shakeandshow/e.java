package com.jingdong.app.mall.home.shakeandshow;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class e {

    /* renamed from: c  reason: collision with root package name */
    private String f10847c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f10848e;

    /* renamed from: h  reason: collision with root package name */
    public boolean f10851h;
    public long a = 3000;
    public int b = 1;

    /* renamed from: f  reason: collision with root package name */
    public boolean f10849f = false;

    /* renamed from: g  reason: collision with root package name */
    public boolean f10850g = true;

    /* renamed from: i  reason: collision with root package name */
    public float f10852i = 28.0f;

    /* renamed from: j  reason: collision with root package name */
    public JumpEntity f10853j = null;

    public static e b(com.jingdong.app.mall.home.r.e.h hVar) {
        JDJSONObject jDJSONObject;
        if (hVar == null || (jDJSONObject = hVar.srcJson) == null) {
            return null;
        }
        e eVar = new e();
        int optInt = jDJSONObject.optInt("countDown", 3) <= 0 ? 3 : jDJSONObject.optInt("countDown", 3);
        long optLong = jDJSONObject.optLong("countDownSecond");
        if (optLong <= 0) {
            optLong = optInt * 1000;
        }
        eVar.a = optLong;
        jDJSONObject.optInt("sensitivity", 2);
        if (2 != l.n()) {
            l.n();
        }
        eVar.b = jDJSONObject.optInt("displayButton", 1);
        eVar.f10850g = 1 != jDJSONObject.optInt("hideView", 0);
        jDJSONObject.optString("img");
        eVar.d = jDJSONObject.optString("loadingImg");
        eVar.f10848e = jDJSONObject.optString("id");
        eVar.f10853j = hVar.getJump();
        eVar.f10849f = eVar.b == 1;
        eVar.f10851h = 1 == jDJSONObject.optInt("closeTipsXview", 0);
        eVar.f10852i = com.jingdong.app.mall.home.n.h.c.e(jDJSONObject.optString("shakeBorder"), 28.0f);
        eVar.f10847c = jDJSONObject.optString("textImg");
        jDJSONObject.optString("closeImg");
        return eVar;
    }

    public String a() {
        return this.f10847c;
    }
}
