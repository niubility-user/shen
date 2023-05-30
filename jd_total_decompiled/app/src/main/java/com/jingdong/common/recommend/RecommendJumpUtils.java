package com.jingdong.common.recommend;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendPromotion;
import com.jingdong.common.recommend.entity.RecommendShop;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.translation.JDTransitionManager;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendJumpUtils {
    private static final String TAG = "RecommendJumpUtils";
    private static final String apprecommend = "apprecommend";

    public static void addJumpParam(JumpEntity jumpEntity, String str, String str2) {
        if (jumpEntity == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        jumpEntity.addParam(str, str2);
    }

    public static Bundle generateProductBundle(RecommendProduct recommendProduct, int i2) {
        String str;
        String str2 = "1";
        Bundle bundle = new Bundle();
        if (recommendProduct != null && (str = recommendProduct.wareId) != null) {
            SourceEntity sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, recommendProduct.sourceValue);
            bundle.putString("id", str);
            bundle.putString("title", recommendProduct.name);
            bundle.putString("image", recommendProduct.imgUrl);
            bundle.putString("price", recommendProduct.getJdPrice());
            bundle.putString(PDConstant.EXTRA_CSKU, recommendProduct.wareId);
            bundle.putString("targetUrl", recommendProduct.targetUrl);
            if (i2 == 9 || i2 == 6 || i2 == 0 || i2 == 24 || i2 == 10024) {
                bundle.putString(PDConstant.EXTRA_PERSONAS, PDConstant.EXTRA_PERSONAS);
            }
            bundle.putSerializable("source", sourceEntity);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("on_site", "1");
                if (!recommendProduct.isAd()) {
                    str2 = "0";
                }
                jSONObject.put("adver", str2);
                jSONObject.put("adsrc", "");
                String[] clickEvent = RecommendMtaUtils.getClickEvent(i2, recommendProduct);
                if (clickEvent != null && clickEvent.length > 2) {
                    jSONObject.put("page_id", clickEvent[1]);
                    jSONObject.put("event_id", clickEvent[0]);
                }
                bundle.putString("recommend_ext", jSONObject.toString());
            } catch (JSONException unused) {
            }
        }
        return bundle;
    }

    public static void gotoMWithUrl(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str2);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("locUrl", str);
        }
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void onEnterPromotion(Activity activity, RecommendPromotion recommendPromotion, int i2, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", recommendPromotion.jumpUrl);
        DeepLinkCommonHelper.startWebActivity(activity, bundle, true);
        RecommendMtaUtils.promotionClickMta(activity, recommendPromotion, i2, str);
    }

    public static void onEnterShop(Activity activity, RecommendShop recommendShop, int i2) {
        String str;
        if (recommendShop == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<RecommendProduct> list = recommendShop.wareList;
        if (list == null || list.size() <= 0) {
            str = "";
        } else {
            str = recommendShop.wareList.get(0).wareId;
            for (RecommendProduct recommendProduct : recommendShop.wareList) {
                if (recommendProduct != null) {
                    arrayList.add(recommendProduct.wareId);
                }
            }
        }
        String str2 = recommendShop.shopId;
        String str3 = recommendShop.venderId;
        DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(str2, str3 != null ? String.valueOf(str3) : "", recommendShop.shopName);
        from.addSource(new SourceEntity(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, null)).addTargetPage("allProduct").addLogoUrl(recommendShop.logoUrl).addSignBoardUrl(recommendShop.shopSignboardurl).addSkuList(str, arrayList);
        DeepLinkJShopHomeHelper.gotoJShopHome(activity, from.build());
        RecommendMtaUtils.enterShopClickMta(activity, recommendShop, i2);
    }

    public static void onJumpAggregation(RecommendDna recommendDna, IMyActivity iMyActivity, int i2) {
        if (recommendDna == null) {
            return;
        }
        if (TextUtils.isEmpty(recommendDna.channelJumpUrl)) {
            Bundle bundle = new Bundle();
            bundle.putString("name", recommendDna.mergePicMainTitle);
            bundle.putString("skus", recommendDna.getSkus());
            bundle.putString("fromGeneId", recommendDna.dnaId);
            bundle.putBoolean("feminine", recommendDna.feminine);
            DeepLinkMyStreetHelper.startMyStreetGeneRecom(iMyActivity, bundle);
        } else {
            try {
                Uri parse = Uri.parse(recommendDna.channelJumpUrl);
                if ("0".equals(recommendDna.isOpenApp)) {
                    new OpenAppJumpBuilder.Builder(parse).build().jump(iMyActivity.getThisActivity());
                } else {
                    DeepLinkMHelper.startWebActivity(iMyActivity.getThisActivity(), recommendDna.channelJumpUrl);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        RecommendMtaUtils.aggregationClickMta(iMyActivity.getThisActivity(), recommendDna, i2, recommendDna.extension_id);
    }

    public static void onJumpDetalis(RecommendDetails recommendDetails, Activity activity, int i2) {
        DeepLinkMyStreetHelper.startRecommendDetail(activity, recommendDetails.wareId);
        RecommendMtaUtils.recommendDetalisClickMta(activity, recommendDetails, i2);
    }

    public static void onJumpGene(final IMyActivity iMyActivity, RecommendDna recommendDna, final int i2) {
        final int i3;
        try {
            i3 = Integer.parseInt(recommendDna.dnaId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            i3 = 0;
        }
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder("JDIndividuationModule", "showGeneInfo").putStringParam("geneString", recommendDna.dnaId).putStringParam("hideTab", "0").build();
            JDRouter.build(iMyActivity.getThisActivity(), build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.recommend.RecommendJumpUtils.2
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                    if (OKLog.D) {
                        OKLog.d(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, "\u8df3\u8f6c\u6210\u529f");
                    }
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i4) {
                    DeepLinkMyStreetHelper.jumpToGeneRecom(iMyActivity, i3, true);
                    RecommendMtaUtils.routerErrorMta(iMyActivity.getThisActivity(), i2, build, i4);
                }
            }).open();
            RecommendMtaUtils.routerEnterMta(iMyActivity.getThisActivity(), i2, "JDIndividuationModule_showStowSimilarity");
        } else {
            DeepLinkMyStreetHelper.jumpToGeneRecom(iMyActivity, i3, true);
        }
        RecommendMtaUtils.jumpGeneClickMta(iMyActivity.getThisActivity(), recommendDna, i2, recommendDna.extension_id);
    }

    public static void onRecommendStartProductDetailActivity(Activity activity, RecommendProduct recommendProduct, int i2, int i3) {
        if (recommendProduct == null || recommendProduct.wareId == null) {
            return;
        }
        Bundle generateProductBundle = generateProductBundle(recommendProduct, i2);
        boolean appendViewDataToBundle = JDTransitionManager.appendViewDataToBundle(generateProductBundle, recommendProduct.productItemImage, recommendProduct.imgUrl);
        generateProductBundle.putString("sourceType", apprecommend);
        if (appendViewDataToBundle) {
            generateProductBundle.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
            DeeplinkProductDetailHelper.startProductDetail(activity, generateProductBundle, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair[0]).toBundle());
        } else {
            DeeplinkProductDetailHelper.startProductDetail(activity, generateProductBundle);
        }
        if (!TextUtils.isEmpty(recommendProduct.isSimilarGoods) && "1".equals(recommendProduct.isSimilarGoods)) {
            RecommendDetails recommendDetails = new RecommendDetails();
            recommendDetails.sourceValue = recommendProduct.sourceValue;
            RecommendMtaUtils.recommendDetalisClickMta((BaseActivity) activity, recommendDetails, i2);
            return;
        }
        RecommendMtaUtils.productClickMta((BaseActivity) activity, recommendProduct, i2, i3);
    }

    public static void onRecommendStartSimilarActivity(final IMyActivity iMyActivity, final RecommendProduct recommendProduct, final int i2, int i3) {
        if (recommendProduct == null || recommendProduct.wareId == null) {
            return;
        }
        String str = i2 == 9 ? "1" : i2 == 0 ? "2" : "";
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder("JDIndividuationModule", "showStowSimilarity").putStringParam("wareId", recommendProduct.wareId).putStringParam("fromType", str).build();
            JDRouter.build(iMyActivity.getThisActivity(), build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.recommend.RecommendJumpUtils.1
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                    if (OKLog.D) {
                        OKLog.d(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, "\u8df3\u8f6c\u6210\u529f");
                    }
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i4) {
                    int i5 = i2;
                    String str2 = i5 == 9 ? "1" : i5 == 0 ? "2" : "";
                    IMyActivity iMyActivity2 = iMyActivity;
                    RecommendProduct recommendProduct2 = recommendProduct;
                    DeepLinkMyStreetHelper.jumpToSimilar(iMyActivity2, recommendProduct2.wareId, recommendProduct2.getJdPrice(), recommendProduct.getName(), recommendProduct.imgUrl, 0, str2);
                    RecommendMtaUtils.routerErrorMta(iMyActivity.getThisActivity(), i2, build, i4);
                }
            }).open();
            RecommendMtaUtils.routerEnterMta(iMyActivity.getThisActivity(), i2, "JDIndividuationModule_showStowSimilarity");
        } else {
            DeepLinkMyStreetHelper.jumpToSimilar(iMyActivity, recommendProduct.wareId, recommendProduct.getJdPrice(), recommendProduct.getName(), recommendProduct.imgUrl, 0, str);
        }
        RecommendMtaUtils.lookSimilarClickMta(iMyActivity.getThisActivity(), recommendProduct, i2, i3);
    }

    public static void onRecommendStartProductDetailActivity(Activity activity, RecommendProduct recommendProduct, int i2, String str) {
        String str2;
        if (recommendProduct == null || (str2 = recommendProduct.wareId) == null) {
            return;
        }
        SourceEntity sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, recommendProduct.sourceValue);
        Bundle bundle = new Bundle();
        bundle.putString("id", str2);
        bundle.putString("sourceType", apprecommend);
        bundle.putString("title", recommendProduct.name);
        bundle.putString("image", recommendProduct.imgUrl);
        bundle.putString("price", recommendProduct.getJdPrice());
        bundle.putString(PDConstant.EXTRA_CSKU, recommendProduct.wareId);
        bundle.putString("targetUrl", recommendProduct.targetUrl);
        if (i2 == 9 || i2 == 6 || i2 == 0) {
            bundle.putString(PDConstant.EXTRA_PERSONAS, PDConstant.EXTRA_PERSONAS);
        }
        bundle.putSerializable("source", sourceEntity);
        if (JDTransitionManager.appendViewDataToBundle(bundle, recommendProduct.productItemImage, recommendProduct.imgUrl)) {
            bundle.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
            DeeplinkProductDetailHelper.startProductDetail(activity, bundle, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair[0]).toBundle());
        } else {
            DeeplinkProductDetailHelper.startProductDetail(activity, bundle);
        }
        if (!TextUtils.isEmpty(recommendProduct.isSimilarGoods) && "1".equals(recommendProduct.isSimilarGoods)) {
            RecommendDetails recommendDetails = new RecommendDetails();
            recommendDetails.sourceValue = recommendProduct.sourceValue;
            RecommendMtaUtils.recommendDetalisClickMta(activity, recommendDetails, i2);
            return;
        }
        RecommendMtaUtils.productClickMta(activity, recommendProduct, i2, str);
    }
}
