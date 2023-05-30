package com.jingdong.common.ui.homemix;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.homemix.entity.Coverage;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class HomeMixHelper {
    private static final String FUNCTION_ID_COVERAGE = "coverage";

    public void requestAddressFence(List<Map<String, Double>> list, ShopParam shopParam, final OnRequestCoverageListener onRequestCoverageListener) {
        if (onRequestCoverageListener == null || shopParam == null || list == null || list.size() <= 0) {
            return;
        }
        if (!UnAddressSelectUtils.canReqeustCoverage()) {
            onRequestCoverageListener.onError();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(shopParam);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(FUNCTION_ID_COVERAGE);
        httpSetting.putJsonParam("coordinateParamList", list);
        httpSetting.putJsonParam("shopParamList", arrayList);
        httpSetting.setHost(HostUtils.getNgwHost());
        httpSetting.setEncryptBody(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.ui.homemix.HomeMixHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject jSONObject;
                JDJSONArray jSONArray;
                if (httpResponse == null) {
                    OnRequestCoverageListener onRequestCoverageListener2 = onRequestCoverageListener;
                    if (onRequestCoverageListener2 != null) {
                        onRequestCoverageListener2.onError();
                        return;
                    }
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null && (jSONObject = fastJsonObject.getJSONObject("data")) != null && (jSONArray = jSONObject.getJSONArray("coverageList")) != null) {
                    List<Coverage> parseArray = JDJSON.parseArray(jSONArray.toJSONString(), Coverage.class);
                    OnRequestCoverageListener onRequestCoverageListener3 = onRequestCoverageListener;
                    if (onRequestCoverageListener3 != null) {
                        onRequestCoverageListener3.onResult(parseArray);
                        return;
                    }
                }
                OnRequestCoverageListener onRequestCoverageListener4 = onRequestCoverageListener;
                if (onRequestCoverageListener4 != null) {
                    onRequestCoverageListener4.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnRequestCoverageListener onRequestCoverageListener2 = onRequestCoverageListener;
                if (onRequestCoverageListener2 != null) {
                    onRequestCoverageListener2.onError();
                }
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
