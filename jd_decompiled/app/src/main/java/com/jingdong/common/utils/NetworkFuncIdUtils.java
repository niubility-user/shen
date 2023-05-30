package com.jingdong.common.utils;

import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jdmiaosha.preload.DoubleTabDataPreloadUtil;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.jdsdk.constant.CacheConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class NetworkFuncIdUtils {
    private static NetworkFuncIdUtils sInstance;
    private Set<String> localFunIds;

    private NetworkFuncIdUtils() {
    }

    public static NetworkFuncIdUtils getInstance() {
        if (sInstance == null) {
            synchronized (NetworkFuncIdUtils.class) {
                if (sInstance == null) {
                    sInstance = new NetworkFuncIdUtils();
                }
            }
        }
        return sInstance;
    }

    private synchronized String[] getLocalFuncIdArray() {
        return new String[]{"recommend", "similarProdList", "uniformRecommend", "recomFeedback", "recomTimeline", "recomWaresByGenes", "moreRecomWares", "getRecWaresBySkus", "getGene", "paritySeckillCategory", "paritySeckillGoods", "seckillBrand", "seckillBrandGoodsSection", "seckillGenericFloors", "seckillNewBrand", "seckillNewBrandGoodsSection", SourceEntity.SOURCE_TYPE_HOME_MIAOSHA, CacheConstant.ID_MIAO_SHA, "homeFeedsList", "getBaitiaoInfo", "getBaitiaoCouponList", "queryRefreshFloor", "newsBrandInfo", "picUploadSubmit", "poolRankvcate", "getOnTrailActivityDetail", "getAdGroupInfo", "getCommonInfo", "ativityListReOrder", "batchGetRanks", "queryBabelSingleFloor", "updateSizeHelperInfo", "getSizeHelperInfo", "oneBoxSearch", "seckillAlmostSoldOut", "seckillNewBrandTabs", "removeGifts", "changePromotion", "addCart", "cart", "cartAdd", "cartChange", "cartChangeGift", "cartCheckAll", "cartCheckSingle", "cartRemove", "cartRemoveGift", CartConstant.FUNCTION_ID_CART_SWITCH, "cartUnCheckAll", "cartUnCheckSingle", "seckillMarketGoodsSection", "hourReachTip", "plusSaveMoney", "queryAllThemeInfo", "queryTabSortInfoAPI", "getSearchDdList", "liveSearchV932", "getLiveListWithGoodStoreV920", "getLiveListWithGoodPriceV920", "discoveryLiveListWithTabV916", "liveSearchV902", "liveSearchAssociationalWordV902", "discoveryLiveListWithTab", "discoveryLiveListWithTabV836", "shareLandPage", "shopNutrientsTask", "shopTaskList", "userDynamicList", "userNutrientsList", "plantBeanIndex", "plantParadiseInfo", "getNewHomeInfo", "getNewProductsList", "jzYouLikeGoods", "smt_index", NetConfig.GET_META_DATA_FUNCTION_ID, "feedsIndex", "photoBuyQueryInfo", "scanQueryInfo", "wareBusiness", "bubbleComponent", "start", "welcomeHome", "newAppCenterInfo", "searchBoxWord", "homeAreaPop", "moreCategory", "categoryFeeds", "categoryHome", "queryThemeInfo", "receiveNutrientsTask", "channelSubTabDetail", "channelTabInfo", "getRankLanding", "tip", "nearbyTab", "search", "searchCatelogy", "searchStatus", "hotRankings", "hotWords", "getCmsPromotionsListByCatelogyID", "adsPromotionLog", "articleTab", "catelogyFilter", "smt_sceneBuyDetail", "smt_sceneBuyTab", "smt_customPopup", "trailNewHomeFeeds", "trailNewHomeInfo", "queryMatProdsForGrps", "queryMaterialAdverts", "qryAppBabelFloors", DoubleTabDataPreloadUtil.FUNCTION_ID, "queryBabelFeeds", "getTrackBabelAdvert", "getBabelProductPaged", "ccartChange", "ccartCheckAll", "cchangePromotion", "ccartReplace", "ccartAdd", "ccartRemove", "ccartUnCheckAll", "ccart", "ccartUnCheckSingle", "ccartCheckSingle", "ccartYBHS", "lite_barcodeRankSku", "queryPanamaFloor", "rec_mixer", "recproxy", "jshopHandleExecute", "newsBrandExecute", "currentOrder", "submitOrder"};
    }

    public synchronized Set<String> getLocalFuncIdSet() {
        Set<String> set = this.localFunIds;
        if (set == null || set.size() == 0) {
            HashSet hashSet = new HashSet();
            this.localFunIds = hashSet;
            hashSet.addAll(Arrays.asList(getLocalFuncIdArray()));
        }
        return this.localFunIds;
    }

    public synchronized void releaseLocalFuncIds() {
        this.localFunIds = null;
    }
}
