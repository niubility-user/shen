package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.comps.offlinemap.OfflineCity;
import com.tencent.map.sdk.comps.offlinemap.OfflineNation;
import com.tencent.map.sdk.comps.offlinemap.OfflineProvince;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.List;

/* loaded from: classes9.dex */
public class y1 extends JsonComposer {
    @Json(name = "name")
    public String a;
    @Json(name = "pinyin")
    public String b;
    @Json(name = "cityList")

    /* renamed from: c  reason: collision with root package name */
    public List<y1> f17486c;

    public OfflineCity a(OfflineProvince offlineProvince) {
        OfflineCity offlineCity = new OfflineCity();
        offlineCity.setName(this.a);
        offlineCity.setPinyin(this.b);
        offlineCity.setProvince(offlineProvince);
        return offlineCity;
    }

    public OfflineNation a() {
        OfflineNation offlineNation = new OfflineNation();
        offlineNation.setName(this.a);
        offlineNation.setPinyin(this.b);
        return offlineNation;
    }

    public OfflineProvince a(List<OfflineCity> list) {
        OfflineProvince offlineProvince = new OfflineProvince();
        offlineProvince.setName(this.a);
        offlineProvince.setPinyin(this.b);
        offlineProvince.setCities(list);
        return offlineProvince;
    }
}
