package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.request.JDFastJsonArrayRequest;
import com.jd.framework.network.request.JDJsonArrayRequest;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import org.json.JSONArray;

/* loaded from: classes14.dex */
public class JDJsonArrayRequestFactory extends JDJsonRequestFactory {

    /* loaded from: classes14.dex */
    public static class FastJsonArrayResponseListener extends JDResponseBaseListener<JDJSONArray> {
        public FastJsonArrayResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<JDJSONArray> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<JDJSONArray> jDResponse) throws Exception {
            JDJSONArray data = jDResponse.getData();
            httpResponse.setFastJsonArray(data);
            httpResponse.setString(data != null ? data.toString() : "");
            httpResponse.setHeader(jDResponse.getHeaders());
        }
    }

    /* loaded from: classes14.dex */
    public static class JsonArrayResponseListener extends JDResponseBaseListener<JSONArray> {
        public JsonArrayResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<JSONArray> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<JSONArray> jDResponse) throws Exception {
            JSONArrayPoxy jSONArrayPoxy = new JSONArrayPoxy(jDResponse.getData());
            httpResponse.setJsonArray(jSONArrayPoxy);
            httpResponse.setString(jSONArrayPoxy.toString());
            httpResponse.setHeader(jDResponse.getHeaders());
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.JDJsonRequestFactory, com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        JDRequest jDFastJsonArrayRequest;
        if (!httpSetting.isUseFastJsonParser()) {
            jDFastJsonArrayRequest = new JDJsonArrayRequest(httpSetting.isPost() ? 1 : 0, str, null, null);
        } else {
            jDFastJsonArrayRequest = new JDFastJsonArrayRequest(httpSetting.isPost() ? 1 : 0, str, null, null);
        }
        initJDRequest(httpRequest, httpSetting, str, jDFastJsonArrayRequest, createResponseListener(httpGroup, httpSetting, httpRequest, jDFastJsonArrayRequest));
        return jDFastJsonArrayRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.JDJsonRequestFactory, com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDResponseListener createResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest) {
        return !httpSetting.isUseFastJsonParser() ? new JsonArrayResponseListener(httpGroup, httpSetting, httpRequest, jDRequest) : new FastJsonArrayResponseListener(httpGroup, httpSetting, httpRequest, jDRequest);
    }
}
