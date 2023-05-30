package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkCouponCenterHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDCouponCenterModule implements IJDModule {
    private Bundle obtainBundle(JSONObject jSONObject, Bundle bundle) {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    String optString = jSONObject.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putString(next, optString);
                    }
                }
            }
        }
        return bundle;
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkCouponCenterHelper.startCouponCenter(context, obtainBundle(jSONObject, bundle));
        JDRouterUtil.callBackComplete(callBackListener);
    }
}
