package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.map.tools.net.http.HttpProxyRule;
import java.util.List;

/* loaded from: classes9.dex */
public class m2 extends JsonComposer {
    @Json(name = "privacy_protection")
    public boolean a;
    @Json(name = "http_proxy")
    public List<HttpProxyRule> b;
}
