package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.request.JDAdRequest;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class JDAdRequestFactory extends AbstractJDRequestFactory {
    public static final String TAG = "JDAdRequestFactory";

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        JDAdRequest jDAdRequest = new JDAdRequest(str, null);
        initJDRequest(httpRequest, httpSetting, str, jDAdRequest, createResponseListener(httpGroup, httpSetting, httpRequest, jDAdRequest));
        return jDAdRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDResponseListener createResponseListener(HttpGroup httpGroup, final HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest) {
        return new JDResponseListener<String>() { // from class: com.jingdong.jdsdk.network.toolbox.JDAdRequestFactory.1
            @Override // com.jd.framework.network.JDResponseListener
            public void onCancel() {
                httpSetting.onCancel();
            }

            @Override // com.jd.framework.network.JDResponseListener
            public void onEnd(JDResponse<String> jDResponse) {
                try {
                    HttpResponse httpResponse = new HttpResponse(httpSetting.getMoreParams());
                    httpResponse.setString(jDResponse.getData());
                    httpResponse.setHeader(jDResponse.getHeaders());
                    httpResponse.setStatusCode(jDResponse.getStatusCode());
                    httpSetting.onEnd(httpResponse);
                } catch (Throwable th) {
                    httpSetting.onError(new HttpError(th));
                }
            }

            @Override // com.jd.framework.network.JDResponseListener
            public void onError(JDError jDError) {
                httpSetting.onError(new HttpError(jDError));
            }

            @Override // com.jd.framework.network.JDResponseListener
            public void onStart() {
                httpSetting.onStart();
            }
        };
    }

    public <T> void initJDRequest(HttpRequest httpRequest, HttpSetting httpSetting, String str, JDRequest<T> jDRequest, JDResponseListener jDResponseListener) {
        jDRequest.setResponseListener(jDResponseListener);
        jDRequest.setUseCookies(httpSetting.isUseCookies());
        jDRequest.setParams(httpSetting.getPostMapParams());
        jDRequest.setCacheModel(convertCacheToJDRequestCache(httpSetting.getCacheMode()));
        if (httpSetting.getLocalFileCacheTime() > 0) {
            jDRequest.setCacheTime(httpSetting.getLocalFileCacheTime());
        }
        jDRequest.setCacheKey(httpSetting.getMd5());
        jDRequest.setMaxNumRetries(httpSetting.getAttempts() - 1);
        jDRequest.setConnectTimeoutMs(httpSetting.getConnectTimeout());
        jDRequest.setReadTimeoutMs(httpSetting.getReadTimeout());
        jDRequest.setPriority(convertPriorityToJDRequestPriority(httpSetting.getPriority()));
        jDRequest.setSequence(httpSetting.getId());
        jDRequest.setUseDomainName(!(JDHttpTookit.getEngine().getHttpDnsControllerImpl().isOpenDnsControl() && JDHttpTookit.getEngine().getHttpDnsControllerImpl().canUseHttpDns(httpSetting.getHost())));
        HashMap hashMap = new HashMap();
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
        if (httpSetting.isUseHttps()) {
            jDRequest.setForce2HttpFlag(JDHttpTookit.getEngine().getExternalDebugConfigImpl().isForceHttpDownGrade());
        } else {
            jDRequest.setForce2HttpFlag(true);
        }
        httpRequest.setJDRequestTag(jDRequest.getTag());
    }
}
