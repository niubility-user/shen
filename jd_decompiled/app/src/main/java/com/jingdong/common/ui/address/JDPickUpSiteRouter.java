package com.jingdong.common.ui.address;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.ui.address.entity.PickUpSiteBean;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.utils.PickUpSiteUtil;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDPickUpSiteRouter {
    private static final String TAG = "JDPickUpSiteRouter";

    public void getSiteAddress(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
            return;
        }
        CallBackWithReturnListener callBackWithReturnListener = (CallBackWithReturnListener) callBackListener;
        PickUpSiteBean pickUpSite = PickUpSiteUtil.getPickUpSite();
        if (pickUpSite != null) {
            callBackWithReturnListener.onComplete(JDJSON.toJSONString(pickUpSite));
        } else {
            callBackWithReturnListener.onComplete("");
        }
    }

    public void saveSiteAddress(Context context, final JSONObject jSONObject, Bundle bundle, final CallBackListener callBackListener) {
        if (jSONObject == null) {
            return;
        }
        new Thread() { // from class: com.jingdong.common.ui.address.JDPickUpSiteRouter.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    PickUpSiteBean pickUpSiteBean = (PickUpSiteBean) JDJSON.parseObject(jSONObject.toString(), PickUpSiteBean.class);
                    String str = "saveSiteAddress = " + JDJSON.toJSONString(pickUpSiteBean);
                    if (pickUpSiteBean != null) {
                        boolean updatePickUpSite = PickUpSiteUtil.updatePickUpSite(pickUpSiteBean);
                        CallBackListener callBackListener2 = callBackListener;
                        if (callBackListener2 != null) {
                            if (updatePickUpSite) {
                                callBackListener2.onComplete();
                            } else {
                                callBackListener2.onError(1);
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
    }
}
