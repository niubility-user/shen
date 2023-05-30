package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.comps.offlinemap.OfflineItem;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.List;

/* loaded from: classes9.dex */
public class c2 extends JsonComposer {
    @Json(name = "domain1")
    public String a;
    @Json(name = "dirNew")
    public String b;
    @Json(name = "domain")

    /* renamed from: c  reason: collision with root package name */
    public String f16357c;
    @Json(name = "fileversion")
    public int d;
    @Json(name = "updateData")

    /* renamed from: e  reason: collision with root package name */
    public List<a2> f16358e;

    public a2 a(OfflineItem offlineItem) {
        List<a2> list = this.f16358e;
        if (list != null) {
            for (a2 a2Var : list) {
                if (a2Var.a(offlineItem)) {
                    a2Var.a = "https://" + this.f16357c + this.b;
                    return a2Var;
                }
            }
        }
        return null;
    }
}
