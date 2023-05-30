package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDNewCategoryModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("jumpCid", jSONObject.optString("jumpCid", ""));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CATEGORY_ACTIVITY, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }
}
