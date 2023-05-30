package com.jingdong.common.utils;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CacheConstant;
import com.jingdong.jdsdk.network.db.entry.CacheFile;
import com.jingdong.jdsdk.network.db.entry.CacheFileTable;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class CacheTimeUtil {
    private static final String TAG = "CacheTimeUtil";

    public static void initCacheTime(Integer num, Integer num2, Integer num3, Integer num4, Integer num5) {
        if (OKLog.D) {
            OKLog.d(TAG, " -->> indexOfAll : " + num + " , miaoShaAreaList : " + num2 + " , catelogy : " + num3 + " , myJdHome : " + num4 + " , productDetail : " + num5);
        }
        CommonBase.putLongToPreference(CacheConstant.ID_HOME_INDEX, num.intValue() * 1000);
        CommonBase.putLongToPreference(CacheConstant.ID_MIAO_SHA, num2.intValue() * 1000);
        CommonBase.putLongToPreference("catelogy", num3.intValue() * 1000);
        CommonBase.putLongToPreference(CacheConstant.ID_MY_PERSONAL, num4.intValue() * 1000);
        CommonBase.putLongToPreference("productDetail", num5.intValue() * 1000);
        ArrayList arrayList = new ArrayList();
        CacheFile cacheFile = new CacheFile("", num.intValue() * 1000);
        cacheFile.setBussinessId(500);
        arrayList.add(cacheFile);
        CacheFile cacheFile2 = new CacheFile("", num2.intValue() * 1000);
        cacheFile2.setBussinessId(200);
        arrayList.add(cacheFile2);
        CacheFile cacheFile3 = new CacheFile("", num3.intValue() * 1000);
        cacheFile3.setBussinessId(300);
        arrayList.add(cacheFile3);
        CacheFile cacheFile4 = new CacheFile("", num4.intValue() * 1000);
        cacheFile4.setBussinessId(100);
        arrayList.add(cacheFile4);
        CacheFile cacheFile5 = new CacheFile("", num5.intValue() * 1000);
        cacheFile5.setBussinessId(400);
        arrayList.add(cacheFile5);
        CacheFileTable.updateAllCacheTime(arrayList);
    }

    public static void queryCacheTime() {
        if (!SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.REQUEST_CACHE_TIME, false)) {
            initCacheTime(600, 60, Integer.valueOf((int) RemoteMessageConst.DEFAULT_TTL), Integer.valueOf((int) R2.color.c_f2f3f3), 0);
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getCacheTime");
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.CacheTimeUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObjectOrNull;
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject == null || (jSONObjectOrNull = jSONObject.getJSONObjectOrNull("cacheTimeMap")) == null) {
                    return;
                }
                int intOrNull = jSONObjectOrNull.getIntOrNull(CacheConstant.ID_HOME_INDEX);
                int intOrNull2 = jSONObjectOrNull.getIntOrNull(CacheConstant.ID_MIAO_SHA);
                int intOrNull3 = jSONObjectOrNull.getIntOrNull("catelogy");
                int intOrNull4 = jSONObjectOrNull.getIntOrNull(CacheConstant.ID_MY_PERSONAL);
                int intOrNull5 = jSONObjectOrNull.getIntOrNull("productDetail");
                if (intOrNull == null) {
                    intOrNull = 0;
                }
                if (intOrNull2 == null) {
                    intOrNull2 = 0;
                }
                if (intOrNull3 == null) {
                    intOrNull3 = 0;
                }
                if (intOrNull4 == null) {
                    intOrNull4 = 0;
                }
                if (intOrNull5 == null) {
                    intOrNull5 = 0;
                }
                CacheTimeUtil.initCacheTime(intOrNull, intOrNull2, intOrNull3, intOrNull4, intOrNull5);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        httpSetting.setNotifyUser(false);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
