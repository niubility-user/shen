package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ExpoData {
    public JSONObject appendJson;
    public String exposureSourceValue;
    public String flow;
    public int isBackUp = -1;
    public String plus;
    public RecommendProduct productRef;
    public String reqsign;
    public String sid;
    public String sku;
    public String source;
    public String time;

    public ExpoData() {
        getnowtime();
    }

    public String getExposureSourceValue() {
        JSONObject jSONObject = this.appendJson;
        if (jSONObject != null && jSONObject.length() > 0) {
            this.exposureSourceValue = RecommendMtaUtils.addKeyToMtaJson(this.appendJson, this.exposureSourceValue);
        }
        return this.exposureSourceValue;
    }

    public String getSource() {
        return TextUtils.isEmpty(this.source) ? "" : this.source;
    }

    public void getnowtime() {
        this.time = String.valueOf(System.currentTimeMillis() / 1000);
    }

    public ExpoData(boolean z) {
    }
}
