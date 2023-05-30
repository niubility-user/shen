package com.jingdong.manto.network.mantorequests;

import org.json.JSONObject;

/* loaded from: classes16.dex */
public interface MantoBaseRequest {

    /* loaded from: classes16.dex */
    public enum RequestMethod {
        GET,
        POST,
        DELETE,
        PUT
    }

    String getFunctionId();

    String getHost();

    JSONObject getPostBody();

    RequestMethod getRequestMethod();

    JSONObject getRequestParams();

    String getSign(JSONObject jSONObject, String str);

    boolean useJDNet();
}
