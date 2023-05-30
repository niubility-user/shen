package com.jingdong.common.babelrn.utils;

import android.text.TextUtils;
import androidx.collection.LruCache;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.babelrn.entity.BabelNativeInfo;
import com.jingdong.common.babelrn.entity.BabelResponseEntity;
import com.jingdong.common.babelrn.view.BabelRNFragment;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class M2BabelHelper {
    private static String TAG = "M2BabelHelper";
    private LruCache<String, BabelResponseEntity> map;

    /* loaded from: classes5.dex */
    private static class SingleTonHoler {
        private static M2BabelHelper INSTANCE = new M2BabelHelper();

        private SingleTonHoler() {
        }
    }

    public static M2BabelHelper getInstance() {
        return SingleTonHoler.INSTANCE;
    }

    private void queryNativeSend(final String str, String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(MKeyNames.SWITCH_QUERY_NATIVE);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setConnectTimeout(5000);
        httpSetting.putJsonParam("activityId", str);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("dogeVersion", AbstractJDReactInitialHelper.getJDReactFrameworkVersion());
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.putJsonParam("url", str2);
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.babelrn.utils.M2BabelHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                M2BabelHelper.this.map.put(str, new BabelResponseEntity(System.currentTimeMillis(), httpResponse));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(M2BabelHelper.TAG, "queryNative error:" + httpError);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public BabelNativeInfo getBabelNativeInfo(String str) {
        HttpResponse result = getResult(str);
        if (result == null) {
            return null;
        }
        JDJSONObject fastJsonObject = result.getFastJsonObject();
        BabelNativeInfo babelNativeInfo = new BabelNativeInfo();
        babelNativeInfo.isNative = fastJsonObject.optString("isNative");
        babelNativeInfo.setDoge(fastJsonObject.optString(BabelRNFragment.DOGE));
        if (TextUtils.isEmpty(babelNativeInfo.isNative)) {
            return null;
        }
        return babelNativeInfo;
    }

    public void getQueryNative(String str, String str2) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("ttt_new_link_string", "");
        if (TextUtils.isEmpty(str2) || !str2.contains("useNewLoad=1")) {
            if (!TextUtils.isEmpty(str2) && str2.contains("useNewLoad=0")) {
                queryNativeSend(str, str2);
                return;
            }
            if (!TextUtils.isEmpty(switchStringValue)) {
                if (switchStringValue.contains(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
                    return;
                }
                if (!TextUtils.isEmpty(str) && switchStringValue.contains(str)) {
                    return;
                }
            }
            queryNativeSend(str, str2);
        }
    }

    public void getQueryNativeUrl(String str) {
        String babelActivityId = WebViewHelper.getBabelActivityId(str);
        if (TextUtils.isEmpty(babelActivityId)) {
            return;
        }
        getQueryNative(babelActivityId);
    }

    public HttpResponse getResult(String str) {
        BabelResponseEntity babelResponseEntity = this.map.get(str);
        if (babelResponseEntity == null || System.currentTimeMillis() - babelResponseEntity.time > 3000) {
            return null;
        }
        return babelResponseEntity.response;
    }

    private M2BabelHelper() {
        this.map = new LruCache<>(3);
    }

    public void getQueryNative(String str) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("ttt_new_link_string", "");
        if (!TextUtils.isEmpty(switchStringValue)) {
            if (switchStringValue.contains(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
                return;
            }
            if (!TextUtils.isEmpty(str) && switchStringValue.contains(str)) {
                return;
            }
        }
        queryNativeSend(str, null);
    }
}
