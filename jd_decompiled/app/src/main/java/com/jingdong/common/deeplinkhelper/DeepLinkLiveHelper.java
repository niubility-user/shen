package com.jingdong.common.deeplinkhelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.harmony.HarmonyHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class DeepLinkLiveHelper {
    public static final String HOST_LIVE = "mylive";
    public static final String HOST_LIVE_LIST = "jdlivelist";
    private static final String HOST_LIVE_NEW_PREDICT = "livenewpredict";
    private static final String HOST_LIVE_PREVIEW_LIST = "LivePredictList";
    private static final String HOST_LIVE_SMALLVIDEO = "LiveSmallVideo";
    public static final String HOST_LIVE_VIDEO_IMMERSION = "VideoImmersionStyleB";
    private static final String HOST_VIDEO_PRODUCT_RECOMMEND = "productRecommendVideoList";
    private static final String HOST_VIDEO_RANK = "VideoRank";

    private static void jump2AndroidLivePage(IMyActivity iMyActivity, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE).toString(), bundle);
    }

    private static void jump2HarmonyLivePage(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(f.f19954c, "com.jd.jdlive.view.LiveRoomAbility"));
        intent.putExtras(bundle);
        HarmonyHelper.startHarmonyAbility(context, intent);
    }

    public static void startFxLiveListActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(106))) {
            if (OKLog.D) {
                OKLog.d("HHH", "=> MASK_AURA_SWITCH_JDLIVELIST close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE_LIST).toString(), bundle);
    }

    public static void startLivePredictActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(106))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE_NEW_PREDICT).toString(), bundle);
        }
    }

    public static void startLivePreviewListActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(106))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE_PREVIEW_LIST).toString(), bundle);
        }
    }

    public static void startSmallVideoActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(44))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("LiveSmallVideo").toString(), bundle);
        }
    }

    public static void startVideoImmersionActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(44))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE_VIDEO_IMMERSION).toString(), bundle);
        }
    }

    public static void startVideoLiveRoomActivity(IMyActivity iMyActivity, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(33))) {
            if (OKLog.D) {
                OKLog.d("MMM", "=> MASK_AURA_SWITCH_LIVE close");
                return;
            }
            return;
        }
        jump2AndroidLivePage(iMyActivity, bundle);
    }

    public static void startVideoProductRecommendActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(44))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("productRecommendVideoList").toString(), bundle);
        }
    }

    private static void jump2AndroidLivePage(Context context, Bundle bundle) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE);
        Bundle bundle2 = bundle != null ? bundle.getBundle("activityOptionsBundle") : null;
        if (bundle2 != null) {
            DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle, bundle2);
        } else {
            DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
        }
    }

    public static void startVideoImmersionActivity(Context context, Bundle bundle, Bundle bundle2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(44))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIVE_VIDEO_IMMERSION).toString(), bundle, bundle2);
        }
    }

    public static void startVideoLiveRoomActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(33))) {
            if (OKLog.D) {
                OKLog.d("MMM", "=> MASK_AURA_SWITCH_LIVE close");
                return;
            }
            return;
        }
        jump2AndroidLivePage(context, bundle);
    }
}
