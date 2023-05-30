package com.jingdong.app.mall.bundle.jdweather.action;

import com.jingdong.app.mall.bundle.jdweather.network.d;
import com.jingdong.app.mall.bundle.jdweather.network.h;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends h<com.jingdong.app.mall.bundle.jdweather.d.a, com.jingdong.app.mall.bundle.jdweather.a.a> implements JDWeatherActionKey {
    @Override // com.jingdong.app.mall.bundle.jdweather.network.h
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void a(d dVar, com.jingdong.app.mall.bundle.jdweather.d.a aVar) {
        if (dVar == null || aVar == null) {
            return;
        }
        dVar.k(JDWeatherActionKey.FUNCTION_ID);
        dVar.m(1000);
        dVar.j(1000);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.bundle.jdweather.network.h
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public com.jingdong.app.mall.bundle.jdweather.a.a b() {
        return new com.jingdong.app.mall.bundle.jdweather.a.a();
    }

    @Override // com.jingdong.app.mall.bundle.jdweather.network.h
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public com.jingdong.app.mall.bundle.jdweather.a.a e(String str) {
        com.jingdong.app.mall.bundle.jdweather.a.a aVar = new com.jingdong.app.mall.bundle.jdweather.a.a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.getString("code");
            aVar.a = jSONObject.getString(JDWeatherActionKey.WEATHER_URL);
        } catch (Exception e2) {
            com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
        }
        return aVar;
    }
}
