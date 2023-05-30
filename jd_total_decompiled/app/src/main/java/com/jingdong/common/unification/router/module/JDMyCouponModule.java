package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkMyCouponHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDMyCouponModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkMyCouponHelper.startMyCouponActivityForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkMyCouponHelper.startMyCouponActivity(context, bundle);
        }
        JDRouterUtil.callBackComplete(callBackListener);
    }
}
