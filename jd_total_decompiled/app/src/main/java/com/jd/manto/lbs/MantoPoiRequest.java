package com.jd.manto.lbs;

import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoCommonRequest;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoPoiRequest extends MantoCommonRequest {
    static final int PAGE_SIZE = 20;
    private String key;
    private double lat;
    private double lng;
    private int page_index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MantoPoiRequest(double d, double d2, int i2, String str) {
        this.lat = d;
        this.lng = d2;
        this.page_index = i2;
        this.key = str;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return null;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return "https://apis.map.qq.com/ws/geocoder/v1/";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getRequestParams() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, String.format("%s,%s", Double.valueOf(this.lat), Double.valueOf(this.lng)));
            jSONObject.put("get_poi", 1);
            jSONObject.put("poi_options", "radius=1000;page_size=20;page_index=" + this.page_index);
            jSONObject.put("key", this.key);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
