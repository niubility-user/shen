package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class v extends MantoJDApiRequest {
    private String[] a;

    public v(String[] strArr) {
        this.a = strArr;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaDownMiniInfo";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            JSONArray jSONArray = new JSONArray();
            String[] strArr = this.a;
            if (strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    jSONArray.put(str);
                }
            }
            postBody.put("appIds", jSONArray);
            postBody.put("supportSub", 2);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
