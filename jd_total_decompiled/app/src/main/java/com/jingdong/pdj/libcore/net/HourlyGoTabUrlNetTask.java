package com.jingdong.pdj.libcore.net;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDYFAddress;
import com.jingdong.common.lbs.businessAddress.JDYFAddressListener;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.pdj.libcore.utils.HourlyGoAddressHelper;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;

/* loaded from: classes7.dex */
public class HourlyGoTabUrlNetTask {
    public static final String BUSINESSID = "15f39f78ae41d235baf6dfdc573ccd47";
    public static final String FLOOR_INDEX = "index";
    public static final String SCENEID = "locService";
    public static final int SOURCE = 4;
    public static final int TYPE_SKU_BUBBLE = 3;

    public static /* synthetic */ void a(JDYFAddress jDYFAddress) {
        if (jDYFAddress != null) {
            getTabUrlRequest(jDYFAddress.getLat(), jDYFAddress.getLng(), HourlyGoAddressHelper.generateGeo(jDYFAddress.getLat(), jDYFAddress.getLng()));
        }
    }

    public static void getAddress() {
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption();
        jDLbsHttpOption.setBusinessId("15f39f78ae41d235baf6dfdc573ccd47");
        jDLbsHttpOption.setSceneId("basicShoppingProcess");
        jDLbsHttpOption.setSourceId(4);
        JDBusinessAddressManager.getInstance().getYFAddress(jDLbsHttpOption, new JDYFAddressListener() { // from class: com.jingdong.pdj.libcore.net.a
            @Override // com.jingdong.common.lbs.businessAddress.JDYFAddressListener
            public final void onEnd(JDYFAddress jDYFAddress) {
                HourlyGoTabUrlNetTask.a(jDYFAddress);
            }
        });
    }

    public static void getTabUrlRequest(double d, double d2, String str) {
        if (HourlyGoAddressHelper.isAddressInvalid(d, d2, str) || TextUtils.isEmpty(HourlyGoObserverManager.getInstance().getmFloorId())) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("hours_home_Tag");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("geo", str);
        httpSetting.putJsonParam("floorId", HourlyGoObserverManager.getInstance().getmFloorId());
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.pdj.libcore.net.HourlyGoTabUrlNetTask.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                    return;
                }
                HourlyGoTabUrlNetTask.parseTabData(httpResponse.getFastJsonObject());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
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

    public static void parseTabData(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        if (!"0".equals(jDJSONObject.optString("code"))) {
            HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
            return;
        }
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
            return;
        }
        int optInt = optJSONObject.optInt("index");
        JDJSONArray optJSONArray = optJSONObject.optJSONArray("data");
        if (optJSONArray != null && optJSONArray.size() > 0) {
            Object obj = optJSONArray.get(0);
            if (obj instanceof JDJSONObject) {
                JDJSONObject jDJSONObject2 = (JDJSONObject) obj;
                jDJSONObject2.put("index", (Object) String.valueOf(optInt));
                if (jDJSONObject2.optInt("tagType") == 3) {
                    String optString = jDJSONObject2.optString("tagText");
                    JDJSONObject optJSONObject2 = jDJSONObject2.optJSONObject("tagImage");
                    String optString2 = optJSONObject2 != null ? optJSONObject2.optString("imgUrl") : null;
                    JDJSONArray optJSONArray2 = jDJSONObject2.optJSONArray("productVOList");
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && optJSONArray2 != null && optJSONArray2.size() > 0) {
                        HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, jDJSONObject2);
                        return;
                    } else {
                        HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
                        return;
                    }
                }
                JDJSONObject optJSONObject3 = jDJSONObject2.optJSONObject("tagImage");
                if (optJSONObject3 != null) {
                    String optString3 = optJSONObject3.optString("imgUrl");
                    if (!TextUtils.isEmpty(optString3)) {
                        JDJSONObject jDJSONObject3 = new JDJSONObject();
                        jDJSONObject3.put("tagmoduleid", (Object) jDJSONObject2.optString("id", "-100"));
                        jDJSONObject3.put("tagvenderid", (Object) jDJSONObject2.optString("venderId", "-100"));
                        jDJSONObject3.put("tagadvertid", (Object) jDJSONObject2.optString("advertId", "-100"));
                        HourlyGoObserverManager.getInstance().notifyTabUrlObserver(optString3, jDJSONObject3, null);
                        return;
                    }
                    HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
                    return;
                }
                HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
                return;
            }
            HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
            return;
        }
        HourlyGoObserverManager.getInstance().notifyTabUrlObserver(null, null, null);
    }
}
