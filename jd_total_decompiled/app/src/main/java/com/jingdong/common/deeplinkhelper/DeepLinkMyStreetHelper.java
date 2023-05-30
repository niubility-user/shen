package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.union.common.config.UnionConstants;

/* loaded from: classes5.dex */
public class DeepLinkMyStreetHelper {
    public static final String HOST_GENE_RECOM = "mystreetgenerecom";
    public static final String HOST_LIKE_MORE = "mystreetlikemore";
    public static final String HOST_MYSTREET = "mystreet";
    public static final String HOST_RECOMMEND_DETAIL = "recommenddetail";
    public static final String HOST_SIMILAR_RECOM = "mystreetsimilarrecom";

    public static void jumpToGeneRecom(IMyActivity iMyActivity, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("fromGeneId", i2);
        startMyStreetGeneRecom(iMyActivity, bundle);
    }

    public static void jumpToLikeMore(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("sku", str);
        bundle.putString("title", str2);
        startLikeMore(context, bundle);
    }

    public static void jumpToMyStreet(IMyActivity iMyActivity) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isGoToGene", false);
        startMyStreet(iMyActivity, bundle);
    }

    public static void jumpToSimilar(IMyActivity iMyActivity, String str, String str2, String str3, String str4, int i2) {
        jumpToSimilar(iMyActivity, str, str2, str3, str4, i2, "");
    }

    private static void startLikeMore(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(7))) {
            OKLog.d("DeepLinkMyStreetHelper", "\u731c\u4f60\u559c\u6b22\u66f4\u591a\u5f00\u5173\u5173\u95ed\u4e86");
        } else {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_LIKE_MORE).toString(), bundle);
        }
    }

    public static void startMyStreet(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(7))) {
                    OKLog.d("DeepLinkMyStreetHelper", "\u6211\u7684\u8857\u5f00\u5173\u5173\u95ed\u4e86");
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host("mystreet").toString(), bundle);
            }
        });
    }

    public static void startMyStreetGeneRecom(final IMyActivity iMyActivity, final Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper.2
            @Override // java.lang.Runnable
            public void run() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(7))) {
                    OKLog.d("DeepLinkMyStreetHelper", "\u57fa\u56e0\u843d\u5730\u9875\u5f00\u5173\u5173\u95ed\u4e86");
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkMyStreetHelper.HOST_GENE_RECOM).toString(), bundle);
            }
        });
    }

    public static void startRecommendDetail(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, HOST_RECOMMEND_DETAIL, bundle);
    }

    public static void startSimilarRecom(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(7))) {
            OKLog.d("DeepLinkMyStreetHelper", "\u770b\u76f8\u4f3c\u5f00\u5173\u5173\u95ed\u4e86");
        } else {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SIMILAR_RECOM).toString(), bundle);
        }
    }

    public static void jumpToSimilar(IMyActivity iMyActivity, String str, String str2, String str3, String str4, int i2, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString(UnionConstants.BUNDLE_SKUID, str);
        bundle.putString("sku_price", str2);
        bundle.putString("sku_name", str3);
        bundle.putString("sku_img_url", str4);
        bundle.putInt("sku_status", i2);
        bundle.putString("form", str5);
        startSimilarRecom(iMyActivity.getThisActivity(), bundle);
    }

    public static void startRecommendDetail(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("sku", str);
        startRecommendDetail(context, bundle);
    }

    public static void jumpToGeneRecom(IMyActivity iMyActivity, int i2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("fromGeneId", i2);
        bundle.putBoolean("hideTab", z);
        startMyStreetGeneRecom(iMyActivity, bundle);
    }

    public static void jumpToSimilar(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(UnionConstants.BUNDLE_SKUID, str);
        bundle.putString("form", str2);
        startSimilarRecom(context, bundle);
    }
}
