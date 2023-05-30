package com.jd.manto.lbs;

import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoCommonRequest;
import java.net.URLEncoder;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoPoiSuggestRequest extends MantoCommonRequest {
    static final int PAGE_SIZE = 20;
    private String key;
    private String keyword;
    private double lat;
    private double lng;
    private int page_index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MantoPoiSuggestRequest(double d, double d2, int i2, String str, String str2) {
        this.lat = d;
        this.lng = d2;
        this.page_index = i2;
        this.key = str;
        this.keyword = str2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return null;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return "https://apis.map.qq.com/ws/place/v1/suggestion";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getRequestParams() {
        JSONObject jSONObject = new JSONObject();
        try {
            double d = this.lat;
            if (Double.MIN_VALUE != d && Double.MIN_VALUE != this.lng) {
                jSONObject.put(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, String.format("%s,%s", Double.valueOf(d), Double.valueOf(this.lng)));
            }
            jSONObject.put("keyword", URLEncoder.encode(this.keyword, "utf-8"));
            jSONObject.put("page_size", 20);
            jSONObject.put("page_index", this.page_index);
            jSONObject.put("key", this.key);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
