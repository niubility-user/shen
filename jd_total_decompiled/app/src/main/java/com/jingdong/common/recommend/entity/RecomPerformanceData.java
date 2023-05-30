package com.jingdong.common.recommend.entity;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecomPerformanceData {
    public int count;
    public int page;
    public long renderEndTime;
    public long requestEndTime;
    public long requestStartTime;
    public int source;
    public int tabid = -1;

    public JSONObject toExpoJson() {
        long j2 = this.requestEndTime;
        if (j2 > 0) {
            long j3 = this.renderEndTime;
            if (j3 > 0) {
                long j4 = this.requestStartTime;
                if (j4 <= 0 || j2 <= j4 || j3 <= j2 || this.count <= 0) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("requestTime", this.requestStartTime + "");
                    jSONObject.put("request", (this.requestEndTime - this.requestStartTime) + "");
                    jSONObject.put("tabid", this.tabid + "");
                    jSONObject.put("page", this.page + "");
                    jSONObject.put("total", (this.renderEndTime - this.requestStartTime) + "");
                    jSONObject.put("count", this.count + "");
                    jSONObject.put("source", this.source + "");
                } catch (JSONException unused) {
                }
                return jSONObject;
            }
            return null;
        }
        return null;
    }
}
