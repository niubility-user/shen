package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.VideoPlayerDeepLinkHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.videoplayer.VideoPlayerConstants;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDPlayerModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        showPlayer(context, jSONObject, bundle, callBackListener);
    }

    public void showPlayer(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        } else if (jSONObject == null) {
            if (callBackListener != null) {
                callBackListener.onError(703);
            }
        } else {
            String optString = jSONObject.optString("videoUrl");
            String optString2 = jSONObject.optString("imageUrl");
            String optString3 = jSONObject.optString("exitOnEnd");
            String optString4 = jSONObject.optString("from");
            String optString5 = jSONObject.optString("skuID");
            String optString6 = jSONObject.optString("videoId");
            Bundle bundle2 = new Bundle();
            bundle2.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_COVER_IMG_URL, optString2);
            bundle2.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, optString);
            bundle2.putString("exitOnEnd", optString3);
            bundle2.putString("sku", optString5);
            bundle2.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_ID, optString6);
            if (TextUtils.equals("pd_h5", optString4)) {
                bundle2.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_JUMP_FROM, 6);
            }
            bundle2.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
            VideoPlayerDeepLinkHelper.startVideoPlayer(context, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        }
    }
}
