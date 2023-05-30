package com.jingdong.pdj.libcore.net;

import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
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
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse r3) {
                /*
                    r2 = this;
                    com.jd.framework.json.JDJSONObject r3 = r3.getFastJsonObject()
                    if (r3 == 0) goto L3c
                    java.lang.String r0 = "code"
                    java.lang.String r0 = r3.optString(r0)
                    java.lang.String r1 = "0"
                    boolean r0 = r1.equals(r0)
                    if (r0 == 0) goto L3c
                    java.lang.String r0 = "result"
                    com.jd.framework.json.JDJSONObject r3 = r3.optJSONObject(r0)
                    if (r3 == 0) goto L3c
                    java.lang.String r0 = "data"
                    com.jd.framework.json.JDJSONArray r3 = r3.optJSONArray(r0)
                    if (r3 == 0) goto L3c
                    int r0 = r3.size()
                    if (r0 <= 0) goto L3c
                    r0 = 0
                    java.lang.Object r3 = r3.get(r0)
                    boolean r0 = r3 instanceof com.jd.framework.json.JDJSONObject
                    if (r0 == 0) goto L3c
                    com.jd.framework.json.JDJSONObject r3 = (com.jd.framework.json.JDJSONObject) r3
                    java.lang.String r0 = "homePageName"
                    java.lang.String r3 = r3.optString(r0)
                    goto L3d
                L3c:
                    r3 = 0
                L3d:
                    com.jingdong.pdj.libcore.net.HourlyGoTabNameTask$HourlyGoOnTabNameListener r0 = com.jingdong.pdj.libcore.net.HourlyGoTabNameTask.HourlyGoOnTabNameListener.this
                    if (r0 == 0) goto L44
                    r0.onSuccess(r3)
                L44:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.net.HourlyGoTabNameTask.AnonymousClass1.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
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
