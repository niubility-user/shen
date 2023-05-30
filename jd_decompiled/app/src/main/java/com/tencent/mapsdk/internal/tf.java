package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.json.JsonParser;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class tf implements JsonParser {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f17279c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    private String f17280e;

    /* renamed from: f  reason: collision with root package name */
    private String[] f17281f;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f17282g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f17283h;

    public String a(int i2, int i3, int i4) {
        String str;
        String valueOf;
        String[] strArr = this.f17281f;
        if (strArr == null || strArr.length == 0) {
            return this.f17280e;
        }
        String replace = this.f17280e.replace("{x}", i2 + "").replace("{y}", i3 + "").replace("{z}", i4 + "");
        for (String str2 : this.f17281f) {
            Object opt = this.f17282g.opt(str2);
            if (opt instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) opt;
                valueOf = jSONArray.optString(wa.a(0, jSONArray.length()), "");
                str = "{" + str2 + "}";
            } else if ((opt instanceof String) || (opt instanceof Number)) {
                str = "{" + str2 + "}";
                valueOf = String.valueOf(opt);
            }
            replace = replace.replace(str, valueOf);
        }
        return replace;
    }

    @Override // com.tencent.map.tools.json.JsonParser
    public void parse(JSONObject jSONObject) {
        this.f17282g = jSONObject;
        if (jSONObject != null) {
            this.a = jSONObject.optString("layerid");
            this.b = jSONObject.optString("version");
            this.f17280e = jSONObject.optString("url");
            this.f17279c = jSONObject.optInt("zoom_max", 20);
            this.d = jSONObject.optInt("zoom_min", 1);
            JSONArray optJSONArray = jSONObject.optJSONArray("params");
            if (optJSONArray != null) {
                this.f17281f = new String[optJSONArray.length()];
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.f17281f[i2] = optJSONArray.optString(i2);
                }
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CustomLayerModel{");
        stringBuffer.append("mLayerId='");
        stringBuffer.append(this.a);
        stringBuffer.append('\'');
        stringBuffer.append(", mVersion='");
        stringBuffer.append(this.b);
        stringBuffer.append('\'');
        stringBuffer.append(", mMaxZoomLevel=");
        stringBuffer.append(this.f17279c);
        stringBuffer.append(", mMinZoomLevel=");
        stringBuffer.append(this.d);
        stringBuffer.append(", mUrl='");
        stringBuffer.append(this.f17280e);
        stringBuffer.append('\'');
        stringBuffer.append(", mParamsHolders=");
        String[] strArr = this.f17281f;
        stringBuffer.append(strArr == null ? DYConstants.DY_NULL_STR : Arrays.asList(strArr).toString());
        stringBuffer.append(", mVersionUpdated=");
        stringBuffer.append(this.f17283h);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
