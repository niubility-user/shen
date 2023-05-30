package com.jingdong.pdj.libcore.net;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes7.dex */
public class HourlyGoTabNameTask {

    /* loaded from: classes7.dex */
    public interface HourlyGoOnTabNameListener {
        void onFailed();

        void onSuccess(String str);
    }

    public static void getTabNameRequest(String str, String str2, final HourlyGoOnTabNameListener hourlyGoOnTabNameListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("hours_home_tab_name");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("geo", str);
        httpSetting.putJsonParam("area", str2);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.pdj.libcore.net.HourlyGoTabNameTask.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
            /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onEnd(HttpResponse httpResponse) {
                String str3;
                HourlyGoOnTabNameListener hourlyGoOnTabNameListener2;
                JDJSONObject optJSONObject;
                JDJSONArray optJSONArray;
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null && "0".equals(fastJsonObject.optString("code")) && (optJSONObject = fastJsonObject.optJSONObject("result")) != null && (optJSONArray = optJSONObject.optJSONArray("data")) != null && optJSONArray.size() > 0) {
                    Object obj = optJSONArray.get(0);
                    if (obj instanceof JDJSONObject) {
                        str3 = ((JDJSONObject) obj).optString("homePageName");
                        hourlyGoOnTabNameListener2 = HourlyGoOnTabNameListener.this;
                        if (hourlyGoOnTabNameListener2 == null) {
                            hourlyGoOnTabNameListener2.onSuccess(str3);
                            return;
                        }
                        return;
                    }
                }
                str3 = null;
                hourlyGoOnTabNameListener2 = HourlyGoOnTabNameListener.this;
                if (hourlyGoOnTabNameListener2 == null) {
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                HourlyGoOnTabNameListener hourlyGoOnTabNameListener2 = HourlyGoOnTabNameListener.this;
                if (hourlyGoOnTabNameListener2 != null) {
                    hourlyGoOnTabNameListener2.onFailed();
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
