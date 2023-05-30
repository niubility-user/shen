package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDProductDetailModule extends AbsJDModule {
    private static final String TAG = "JDProductDetailModule";

    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            DeeplinkProductDetailHelper.startProductDetailFromJDRouter(context, jDJSONObject, routerEntry.extraBundle, routerEntry.callBackListener);
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

    @Override // com.jingdong.common.unification.router.module.AbsJDModule, com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        DeeplinkProductDetailHelper.startProductDetailFromJDRouter(context, JDJSON.parseObject(jSONObject.toString()), bundle, callBackListener);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }
}
