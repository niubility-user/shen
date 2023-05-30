package com.jingdong.common.controller;

import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.OrderQueueHttpSetting;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes5.dex */
public class SettlementAuthController {

    /* loaded from: classes5.dex */
    public interface InternationalAuthListener {
        void onAuthEnd(boolean z, String str);

        void onError();
    }

    public static void isInternationalAuthWithFastjson(IMyActivity iMyActivity, HttpGroup.OnAllListener onAllListener) {
        if (iMyActivity == null || onAllListener == null) {
            return;
        }
        OrderQueueHttpSetting orderQueueHttpSetting = new OrderQueueHttpSetting();
        orderQueueHttpSetting.setFunctionId("getInternationalAuthInfo");
        orderQueueHttpSetting.setPost(true);
        orderQueueHttpSetting.setListener(onAllListener);
        orderQueueHttpSetting.setNotifyUser(true);
        orderQueueHttpSetting.setEffect(1);
        orderQueueHttpSetting.setUseFastJsonParser(true);
        iMyActivity.getHttpGroupaAsynPool().add(orderQueueHttpSetting);
    }

    public static void queryInternationalAuth(final BaseActivity baseActivity, final boolean z, final Bundle bundle, final InternationalAuthListener internationalAuthListener) {
        isInternationalAuthWithFastjson(baseActivity, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.controller.SettlementAuthController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null && fastJsonObject.optJSONObject("authInfo") != null) {
                    JDJSONObject optJSONObject = fastJsonObject.optJSONObject("authInfo");
                    boolean optBoolean = optJSONObject.optBoolean("isAuth");
                    String optString = optJSONObject.optString("authHref");
                    InternationalAuthListener internationalAuthListener2 = InternationalAuthListener.this;
                    if (internationalAuthListener2 != null) {
                        internationalAuthListener2.onAuthEnd(optBoolean, optString);
                    }
                    if (optBoolean || !z) {
                        return;
                    }
                    Bundle bundle2 = bundle;
                    if (bundle2 == null) {
                        bundle2 = new Bundle();
                    }
                    bundle2.putString("url", optString);
                    DeepLinkMHelper.startWebActivity(baseActivity, bundle2);
                    return;
                }
                InternationalAuthListener internationalAuthListener3 = InternationalAuthListener.this;
                if (internationalAuthListener3 != null) {
                    internationalAuthListener3.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                InternationalAuthListener internationalAuthListener2 = InternationalAuthListener.this;
                if (internationalAuthListener2 != null) {
                    internationalAuthListener2.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }
}
