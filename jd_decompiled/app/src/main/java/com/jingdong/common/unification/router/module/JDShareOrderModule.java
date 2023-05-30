package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkShareOrderHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDShareOrderModule implements IJDModule {
    private Bundle obtainBundle(JSONObject jSONObject, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    String optString = jSONObject.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        bundle.putString(next, optString);
                    }
                }
            }
        }
        return bundle;
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void showCommentAndShare(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startEvaluateUniteForResult(context, obtainBundle(jSONObject, bundle), bundle.getInt("requestCode", 404));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showCommentCenter(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startEvaluateCenter(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showCommentDetail(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startEvaluateDetail(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showCommentSecondDetail(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startCommentSecondReplyActivity(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showCommentSelection(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkShareOrderHelper.startCommentSelectionActivity(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showShareOrder(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startEvaluateEdit(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showShareOrderAdditionComment(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof IMyActivity) {
            DeepLinkEvaluateCenterHelper.startEvaluateAgain(context, obtainBundle(jSONObject, bundle));
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }
}
