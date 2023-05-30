package com.jingdong.common.web;

import com.jd.libs.hybrid.adapter.ColorHttpAdapter;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ColorHttpAdapterImpl extends ColorHttpAdapter {
    @Override // com.jd.libs.hybrid.adapter.ColorHttpAdapter
    public void request(String str, String str2, JSONObject jSONObject, final ColorHttpAdapter.Callback callback) {
        Log.d("JDHybrid", "\u63a5\u53e3\u8bf7\u6c42\u6865\u63a5\u5230\u4e3b\u7ad9");
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        httpSetting.setPost(false);
        httpSetting.setBusinessLayerCheckSwitch(false);
        if (HybridSettings.Net.isUseHttp()) {
            httpSetting.setUseHttps(false);
        }
        httpSetting.setFunctionId(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                httpSetting.putJsonParam(next, jSONObject.getString(next));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        httpSetting.setEncryptBody(true);
        httpSetting.setHost(Configuration.getHybridHost());
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.web.ColorHttpAdapterImpl.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                callback.onSuccess(httpResponse.getString());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                callback.onFail(-1, httpError != null ? httpError.getMessage() : "http unknown");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
