package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.union.common.config.UnionConstants;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDIndividuationModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void showBrowsingHistory(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (context instanceof IMyActivity) {
            DeepLinkBrowserHistoryHelper.startBrowserHistoryActivity((IMyActivity) context, bundle);
        } else {
            DeepLinkBrowserHistoryHelper.startBrowserHistoryActivity2(context, bundle);
        }
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showGeneInfo(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        if (context instanceof BaseActivity) {
            DeepLinkMyStreetHelper.startMyStreetGeneRecom((BaseActivity) context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showRecommendInfo(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (CommonBase.getJdSharedPreferences().getBoolean("Recommend_isFirst_startMyStreet", true)) {
            CommonBase.getJdSharedPreferences().edit().putBoolean("Recommend_isFirst_startMyStreet", false).apply();
            bundle.putBoolean("isGoToGene", true);
        } else {
            bundle.putBoolean("isGoToGene", false);
        }
        if (context instanceof BaseActivity) {
            DeepLinkMyStreetHelper.startMyStreet((BaseActivity) context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showStow(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        if (context instanceof BaseActivity) {
            DeepLinkFavouritesHelper.startFavouritesActivity((BaseActivity) context, bundle);
        }
        JDRouterUtil.callBackComplete(callBackListener);
    }

    public void showStowSimilarity(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && !TextUtils.isEmpty(jSONObject.optString("wareId"))) {
            String optString = jSONObject.optString("wareId");
            String optString2 = jSONObject.optString("fromType");
            bundle.putString(UnionConstants.BUNDLE_SKUID, optString);
            bundle.putString("form", optString2);
            DeepLinkMyStreetHelper.startSimilarRecom(context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }
}
