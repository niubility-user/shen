package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkLocationAddressHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;

/* loaded from: classes6.dex */
public class JDLocationAddressModule extends AbsJDModule {
    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            if (routerEntry.requestCode != -1 && (context instanceof Activity)) {
                DeepLinkLocationAddressHelper.startLocationAddressActivity((Activity) context);
            }
            CallBackListener callBackListener = routerEntry.callBackListener;
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        CallBackListener callBackListener2 = routerEntry.callBackListener;
        if (callBackListener2 != null) {
            JDRouterUtil.callBackError(callBackListener2, 703);
        }
    }
}
