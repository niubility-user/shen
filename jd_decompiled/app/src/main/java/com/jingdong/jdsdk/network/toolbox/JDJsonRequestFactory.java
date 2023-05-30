package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.request.JDFastJsonObjectRequest;
import com.jd.framework.network.request.JDJsonObjectRequest;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.common.network.SignatureAlertController;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class JDJsonRequestFactory extends AbstractJDRequestFactory {

    /* loaded from: classes14.dex */
    public static class FastJsonResponseListener extends JDResponseBaseListener<JDJSONObject> {
        public FastJsonResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<JDJSONObject> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<JDJSONObject> jDResponse) throws Exception {
            JDJSONObject handlerEncrypt = SignatureAlertController.handlerEncrypt(jDResponse.getData());
            httpResponse.setFastJsonObject(handlerEncrypt);
            httpResponse.setString(handlerEncrypt != null ? handlerEncrypt.toString() : "");
            httpResponse.setHeader(jDResponse.getHeaders());
            if (this.httpSetting.isEnableBusinessLayerCheck()) {
                httpResponse.setCode(HttpResponseTool.checkSucceed(handlerEncrypt, this.httpSetting, httpResponse));
            }
        }
    }

    /* loaded from: classes14.dex */
    public static class JsonResponseListener extends JDResponseBaseListener<JSONObject> {
        public JsonResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<JSONObject> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<JSONObject> jDResponse) throws Exception {
            JSONObjectProxy handlerEncrypt = HttpResponseTool.handlerEncrypt(new JSONObjectProxy(jDResponse.getData()));
            httpResponse.setJsonObject(handlerEncrypt);
            httpResponse.setString(handlerEncrypt != null ? handlerEncrypt.toString() : "");
            httpResponse.setHeader(jDResponse.getHeaders());
            if (this.httpSetting.isEnableBusinessLayerCheck()) {
                httpResponse.setCode(HttpResponseTool.checkSucceed(handlerEncrypt, this.httpSetting, httpResponse));
            }
        }
    }

    public static Map<String, String> encryptPostParam(HttpSetting httpSetting) {
        Map<String, String> postMapParams = httpSetting.getPostMapParams();
        if (httpSetting.isPost() && postMapParams != null && postMapParams.containsKey("body")) {
            String str = postMapParams.get("body");
            if (httpSetting.isEnableEncryptTransmission() && httpSetting.isEncryptBody()) {
                String encryptBody = JDHttpTookit.getEngine().getStatInfoConfigImpl().encryptBody(str);
                if (!TextUtils.isEmpty(encryptBody)) {
                    postMapParams.put("body", encryptBody);
                }
            }
        }
        return postMapParams;
    }

    private static boolean isUseHttps(String str) {
        return (TextUtils.isEmpty(str) || str.endsWith(".care")) ? false : true;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        JDRequest jDFastJsonObjectRequest;
        if (!httpSetting.isUseFastJsonParser()) {
            jDFastJsonObjectRequest = new JDJsonObjectRequest(httpSetting.isPost() ? 1 : 0, str, null, null);
        } else {
            jDFastJsonObjectRequest = new JDFastJsonObjectRequest(httpSetting.isPost() ? 1 : 0, str, null, null);
        }
        initJDRequest(httpRequest, httpSetting, str, jDFastJsonObjectRequest, createResponseListener(httpGroup, httpSetting, httpRequest, jDFastJsonObjectRequest));
        return jDFastJsonObjectRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDResponseListener createResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest) {
        return !httpSetting.isUseFastJsonParser() ? new JsonResponseListener(httpGroup, httpSetting, httpRequest, jDRequest) : new FastJsonResponseListener(httpGroup, httpSetting, httpRequest, jDRequest);
    }

    public <T> void initJDRequest(HttpRequest httpRequest, HttpSetting httpSetting, String str, JDRequest<T> jDRequest, JDResponseListener jDResponseListener) {
        jDRequest.setResponseListener(jDResponseListener);
        jDRequest.setUseCookies(httpSetting.isUseCookies());
        jDRequest.setParams(encryptPostParam(httpSetting));
        jDRequest.setCacheModel(convertCacheToJDRequestCache(httpSetting.getCacheMode()));
        if (httpSetting.getLocalFileCacheTime() > 0) {
            jDRequest.setCacheTime(httpSetting.getLocalFileCacheTime());
        }
        jDRequest.setCacheKey(httpSetting.getMd5());
        jDRequest.setMaxNumRetries(httpSetting.getAttempts() - 1);
        jDRequest.setConnectTimeoutMs(httpSetting.getConnectTimeout());
        jDRequest.setReadTimeoutMs(httpSetting.getReadTimeout());
        jDRequest.setCallTimeoutMs(httpSetting.getCallTimeout());
        jDRequest.setPriority(convertPriorityToJDRequestPriority(httpSetting.getPriority()));
        jDRequest.setSequence(httpSetting.getId());
        int i2 = 0;
        jDRequest.setUseDomainName(!(JDHttpTookit.getEngine().getHttpDnsControllerImpl().isOpenDnsControl() && JDHttpTookit.getEngine().getHttpDnsControllerImpl().canUseHttpDns(httpSetting.getHost())));
        jDRequest.setNeedRetryOnNetworkLayer(httpSetting.needRetryOnNetworkLayer());
        HashMap hashMap = new HashMap();
        hashMap.putAll(JDHttpTookit.getEngine().getStatInfoConfigImpl().getUniformHeaderField(true, httpSetting.isEnableEncryptTransmission()));
        if (httpSetting.getHeaderMap().containsKey("Cookie") && hashMap.containsKey("J-E-C")) {
            hashMap.remove("J-E-C");
        }
        hashMap.putAll(httpSetting.getHeaderMap());
        jDRequest.setHeader(hashMap);
        String str2 = null;
        if (!TextUtils.isEmpty(httpSetting.getHost()) && !TextUtils.isEmpty(httpSetting.getFunctionId())) {
            str2 = httpSetting.getHost() + CartConstant.KEY_YB_INFO_LINK + httpSetting.getFunctionId();
        } else if (!TextUtils.isEmpty(httpSetting.getUrl())) {
            try {
                str2 = new URL(httpSetting.getUrl()).getHost();
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        jDRequest.setServiceKey(str2);
        if (httpSetting.incompatibleWithOkHttp()) {
            jDRequest.setUseOkhttpFlag(false);
        } else {
            jDRequest.setUseOkhttpFlag(RuntimeConfigHelper.isUseOkhttp());
        }
        if (JDHttpTookit.getEngine().getExternalDebugConfigImpl().isForceHttpDownGrade() || !httpSetting.isUseHttps() || !isUseHttps(httpSetting.getHost())) {
            jDRequest.setForce2HttpFlag(true);
        }
        jDRequest.setCacheResponseChecker(httpSetting.getCacheResponseChecker());
        httpRequest.setJDRequestTag(jDRequest.getTag());
        if (OKLog.D) {
            if (jDRequest.getHeader() != null && jDRequest.getHeader().containsKey("Cookie")) {
                i2 = jDRequest.getHeader().get("Cookie").length();
            }
            OKLog.d("JDNetwork-Cookie", "functionId: " + httpSetting.getFunctionId() + ", Cookie Size: " + i2);
        }
    }
}
