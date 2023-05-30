package com.jingdong.common.recommend;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.entity.ExpoData;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendHomeCardBean;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendPromotion;
import com.jingdong.common.recommend.entity.RecommendShop;
import com.jingdong.common.unification.router.JDRouterMtaUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendMtaUtils {
    public static final String Card_Member_Nearbysale = "Card_Member_Nearbysale";
    public static final String Card_Member_Nearbysale_Expo = "Card_Member_Nearbysale_Expo";
    public static final String Card_Member_Store_Service = "Card_Member_Store_Service";
    public static final String Card_Member_Store_Service_Expo = "Card_Member_Store_Service_Expo";
    public static final String Card_member = "Card_member";
    public static final String HOME_RECBUYNOW = "Home_recbuynow";
    public static final String Home_AggregationAccess = "Home_AggregationAccess";
    public static final String Home_AggregationReal_Expo = "Home_AggregationReal_Expo";
    private static final String Home_Coupon = "Home_Coupon";
    private static final String Home_DeleteSku = "Home_DeleteSku";
    public static final String Home_DeleteSkuReson = "Home_DeleteSkuReson";
    public static final String Home_LiveProduct_MaskShow = "Home_LiveProduct_MaskShow";
    public static final String Home_Mask_LiveProduct = "Home_Mask_LiveProduct";
    public static final String Home_Mask_LiveProductClose = "Home_Mask_LiveProductClose";
    public static final String Home_Mask_LiveProductExpo = "Home_Mask_LiveProductExpo";
    public static final String Home_Mask_LiveProductMore = "Home_Mask_LiveProductMore";
    public static final String Home_NPublicTestAccessExpo = "Home_NPublicTestAccessExpo";
    public static final String Home_PageId = "Home_Main";
    public static final String Home_Page_Name = "JDHomeFragment";
    public static final String Home_Personalization_PromotionTab = "Home_Personalization_PromotionTab";
    public static final String Home_Personalization_PromotionTabExpo = "Home_Personalization_PromotionTabExpo";
    public static final String Home_ProductReal_Expo = "Home_ProductReal_Expo";
    public static final String Home_ProductVideoTime = "Home_ProductVideoTime";
    private static final String Home_Productid = "Home_Productid";
    public static final String Home_ProductidFoot = "Home_ProductidFoot";
    private static final String Home_Productid_Backup = "Home_Productid_Backup";
    public static final String Home_Productid_Backup_Expo = "Home_Productid_Backup_Expo";
    public static final String Home_Promotion_Product = "Home_Promotion_Product";
    public static final String Home_Promotion_Product_Expo = "Home_Promotion_Product_Expo";
    public static final String Home_PublicTestAccess = "Home_PublicTestAccess";
    public static final String Home_PublicTestAccessExpo = "Home_PublicTestAccessExpo";
    public static final String Home_RecomAggregationExpo = "Home_RecomAggregationExpo";
    public static final String Home_RecomResearch = "Home_RecomResearch";
    public static final String Home_RecomResearchExpo = "Home_RecomResearchExpo";
    public static final String Home_RecomSku_Expo = "Home_RecomSkuExpo";
    public static final String Home_RecommendMore = "Home_RecommendMore";
    public static final String Home_ShopAccessExpo = "Home_ShopAccessExpo";
    private static final String Home_Shopid = "Home_Shopid";
    private static final String Home_ShoppingGene = "Home_ShoppingGene";
    public static final String Home_SimilarGoodsAccess = "Home_SimilarGoodsAccess";
    public static final String Home_SimilarGoodsAccessExpo = "Home_SimilarGoodsAccessExpo";
    private static final String Home_SimilarView = "Home_SimilarView";
    private static final String Home_Url = "Home_Url";
    public static final String MessageCenter_DeleteSku = "MessageCenter_DeleteSku";
    public static final String MessageCenter_DeleteSkuReson = "MessageCenter_DeleteSkuReson";
    public static final String MessageCenter_PageId = "MessageCenter_Home";
    public static final String MessageCenter_Page_Name = "MessageCenterFragment";
    public static final String MessageCenter_Productid = "MessageCenter_Productid";
    public static final String MessageCenter_RecomSimilar = "MessageCenter_RecomSimilar";
    public static final String MessageCenter_RecomSkuExpo = "MessageCenter_RecomSkuExpo";
    public static final String MessageCenter_RecommendMore = "MessageCenter_RecommendMore";
    private static final String MyFollow_DeleteRecommendProduct = "MyFollow_DeleteRecommendProduct";
    public static final String MyFollow_DeleteSkuReson = "MyFollow_DeleteSkuReson";
    public static final String MyFollow_PageId = "MyFollow_Main";
    private static final String MyFollow_Page_Name = "FavoListFragmentActivity";
    public static final String MyFollow_RecomSku_Expo = "MyFollow_RecomSkuExpo";
    private static final String MyFollow_RecommendActivity = "MyFollow_RecommendActivity";
    private static final String MyFollow_RecommendCoupon = "MyFollow_RecommendCoupon";
    public static final String MyFollow_RecommendMore = "MyFollow_RecommendMore";
    private static final String MyFollow_RecommendProduct = "MyFollow_RecommendProduct";
    private static final String MyFollow_RecommendSimilar = "MyFollow_RecommendSimilar";
    private static final String MyFollow_Recommenddna = "MyFollow_Recommenddna";
    private static final String MyFollow_Recommendshopid = "MyFollow_Recommendshopid";
    public static final String MyHistory_DeleteSkuReson = "MyHistory_DeleteSkuReson";
    private static final String MyHistory_GuessYouLike = "MyHistory_GuessYouLike";
    private static final String MyHistory_GuessYouLike_AddtoCart = "MyHistory_GuessYouLike_AddtoCart";
    public static final String MyHistory_PageName = "HistoryListActivity";
    public static final String MyHistory_Pageid = "MyHistory_Main";
    private static final String MyHistory_RecomDelete = "MyHistory_RecomDelete";
    private static final String MyHistory_RecomSimilar = "MyHistory_RecomSimilar";
    public static final String MyHistory_RecomSkuExpo = "MyHistory_RecomSkuExpo";
    private static final String MyHistory_RecommendMore = "MyHistory_RecommendMore";
    public static final String MyJD_AggregationAccess = "MyJD_AggregationAccess";
    public static final String MyJD_AggregationAccessExpo = "MyJD_AggregationAccessExpo";
    public static final String MyJD_AggregationReal_Expo = "MyJD_AggregationReal_Expo";
    public static final String MyJD_DeleteSkuReson = "MyJD_DeleteSkuReson";
    public static final String MyJD_GeneTabClick = "MyJD_GeneTabClick";
    public static final String MyJD_GeneTabSlide = "MyJD_GeneTabSlide";
    private static final String MyJD_GuessYouLike = "MyJD_GuessYouLike";
    private static final String MyJD_GuessYouLike_Similar = "MyJD_GuessYouLike_Similar";
    private static final String MyJD_MyStreet = "MyJD_MyStreet";
    public static final String MyJD_NPublicTestAccessExpo = "MyJD_NPublicTestAccessExpo";
    public static final String MyJD_PageId = "MyJD_Main";
    public static final String MyJD_Page_Name = "JDPersonalFragment";
    public static final String MyJD_ProductReal_Expo = "MyJD_ProductReal_Expo";
    public static final String MyJD_PublicTestAccess = "MyJD_PublicTestAccess";
    public static final String MyJD_PublicTestAccessExpo = "MyJD_PublicTestAccessExpo";
    public static final String MyJD_RecomSku_Expo = "MyJD_RecomSkuExpo";
    private static final String MyJD_Recommend_Activity = "MyJD_Recommend_Activity";
    private static final String MyJD_Recommend_Coupon = "MyJD_Recommend_Coupon";
    private static final String MyJD_Recommend_Delete = "MyJD_Recommend_Delete";
    public static final String MyJD_Recommend_More = "MyJD_Recommend_More";
    private static final String MyJD_Recommend_dna = "MyJD_Recommend_dna";
    private static final String MyJD_Recommend_shopid = "MyJD_Recommend_shopid";
    public static final String MyJD_ShopAccessExpo = "MyJD_ShopAccessExpo";
    public static final String MyJD_SimilarGoodsAccess = "MyJD_SimilarGoodsAccess";
    public static final String MyJD_SimilarGoodsAccessExpo = "MyJD_SimilarGoodsAccessExpo";
    public static final String New_FocusPic = "New_FocusPic";
    public static final String New_FocusPic_Expo = "New_FocusPic_Expo";
    public static final String OrderCancelFinish_recproduct = "OrderCancelFinish_recproduct";
    public static final String OrderCancelFinish_recproductexpo = "OrderCancelFinish_recproductexpo";
    public static final String OrderCancelFinish_similarsku = "OrderCancelFinish_similarsku";
    public static final String OrderCenterMyPurchase_FloorAddToCart = "OrderCenterMyPurchase_FloorAddToCart";
    public static final String OrderCenterMyPurchase_FloorProduct = "OrderCenterMyPurchase_FloorProduct";
    public static final String OrderCenterMyPurchase_FloorProductExpo = "OrderCenterMyPurchase_FloorProductExpo";
    public static final String OrderCenter_CancelFinish_Page_Id = "OrderCenter_CancelFinish";
    public static final String OrderCenter_CancelFinish_Page_Name = "CancelSuccessActivity";
    public static final String OrderCenter_List = "OrderCenter_List";
    public static final String OrderCenter_ReceiveSuccess_Pageid = "OrderCenter_ReceiveSuccess";
    public static final String OrderDetail_AggregationAccess = "OrderDetail_AggregationAccess";
    public static final String OrderDetail_AggregationAccessExpo = "OrderDetail_AggregationAccessExpo";
    private static final String OrderDetail_Coupon = "OrderDetail_Coupon";
    private static final String OrderDetail_DeleteSku = "OrderDetail_DeleteSku";
    public static final String OrderDetail_DeleteSkuReson = "OrderDetail_DeleteSkuReson";
    public static final String OrderDetail_PageId = "OrderCenter_Detail";
    private static final String OrderDetail_Page_Name = "OrderDetailActivity";
    private static final String OrderDetail_ProductRecommend = "OrderDetail_ProductRecommend";
    public static final String OrderDetail_RecommendMore = "OrderDetail_RecommendMore";
    private static final String OrderDetail_Recommend_dna = "OrderDetail_Recommend_dna";
    private static final String OrderDetail_Recommend_shopid = "OrderDetail_Recommend_shopid";
    private static final String OrderDetail_SimilarProduct = "OrderDetail_SimilarProduct";
    private static final String OrderDetail_Url = "OrderDetail_Url";
    public static final String OrderFinish_PageId = "Neworder_Success";
    private static final String OrderFinish_Page_Name = "CompleteOrderActivity";
    private static final String OrderFinish_Recommend_Activity = "OrderFinish_Recommend_Activity";
    private static final String OrderFinish_Recommend_Coupon = "OrderFinish_Recommend_Coupon";
    private static final String OrderFinish_Recommend_Delete = "OrderFinish_Recommend_Delete";
    private static final String OrderFinish_Recommend_Product = "OrderFinish_Recommend_Product";
    private static final String OrderFinish_Recommend_Similar = "OrderFinish_Recommend_Similar";
    public static final String OrderFinish_Recommend_SkuExpo = "OrderFinish_Recommend_SkuExpo";
    private static final String OrderFinish_Recommend_dna = "OrderFinish_Recommend_dna";
    private static final String OrderFinish_Recommend_shopid = "OrderFinish_Recommend_shopid";
    public static final String OrderFollow_AggregationAccess = "OrderFollow_AggregationAccess";
    public static final String OrderFollow_AggregationAccessExpo = "OrderFollow_AggregationAccessExpo";
    private static final String OrderFollow_Coupon = "OrderFollow_Coupon";
    private static final String OrderFollow_DeleteSku = "OrderFollow_DeleteSku";
    public static final String OrderFollow_DeleteSkuReason = "OrderFollow_DeleteSkuReson";
    public static final String OrderFollow_PageId = "OrderCenter_Follow";
    private static final String OrderFollow_Page_Name = "LogisticsOrderDetail";
    private static final String OrderFollow_ProductRecommend = "OrderFollow_ProductRecommend";
    public static final String OrderFollow_RecomSku_Expo = "OrderFollow_RecomSkuExpo";
    public static final String OrderFollow_RecommendMore = "OrderFollow_RecommendMore";
    private static final String OrderFollow_Recommend_dna = "OrderFollow_Recommend_dna";
    private static final String OrderFollow_Recommend_shopid = "OrderFollow_Recommend_shopid";
    private static final String OrderFollow_SimilarProduct = "OrderFollow_SimilarProduct";
    private static final String OrderFollow_Url = "OrderFollow_Url";
    public static final String OrderListActivity = "MyOrderListActivity";
    private static final String OrderList_AllProductRecom = "OrderList_AllProductRecom";
    private static final String OrderList_AllRecomSimilar = "OrderList_AllRecomSimilar";
    public static final String OrderList_AllRecomSkuExpo = "OrderList_AllRecomSkuExpo";
    private static final String OrderList_CanceledProductRecom = "OrderList_CanceledProductRecom";
    private static final String OrderList_CanceledRecomSimilar = "OrderList_CanceledRecomSimilar";
    public static final String OrderList_CanceledRecomSkuExpo = "OrderList_CanceledRecomSkuExpo";
    public static final String OrderList_DeleteSku = "OrderList_DeleteSku";
    public static final String OrderList_DeleteSkuReason = "OrderList_DeleteSkuReason";
    private static final String OrderList_DispatchedProductRecom = "OrderList_DispatchedProductRecom";
    private static final String OrderList_DispatchedRecomSimilar = "OrderList_DispatchedRecomSimilar";
    public static final String OrderList_DispatchedRecomSkuExpo = "OrderList_DispatchedRecomSkuExpo";
    private static final String OrderList_FinishedProductRecom = "OrderList_FinishedProductRecom";
    private static final String OrderList_FinishedRecomSimilar = "OrderList_FinishedRecomSimilar";
    public static final String OrderList_FinishedRecomSkuExpo = "OrderList_FinishedRecomSkuExpo";
    public static final String OrderList_RecommendMore = "OrderList_RecommendMore";
    private static final String OrderList_UnpaidProductRecom = "OrderList_UnpaidProductRecom";
    private static final String OrderList_UnpaidRecomSimilar = "OrderList_UnpaidRecomSimilar";
    public static final String OrderList_UnpaidRecomSkuExpo = "OrderList_UnpaidRecomSkuExpo";
    public static final String OrderPurchase_DeleteSkuReson = "OrderPurchase_DeleteSkuReson";
    private static final String OrderPurchase_PageName = "AlwaysBuyActivity";
    public static final String OrderPurchase_Pageid = "OrderCenter_MyPurchase";
    private static final String OrderPurchase_ProductRecommend = "OrderPurchase_ProductRecommend";
    public static final String OrderPurchase_ProductRecommendExpo = "OrderPurchase_ProductRecommendExpo";
    public static final String OrderPurchase_RecomDelete = "OrderPurchase_RecomDelete";
    public static final String OrderPurchase_RecommendMore = "OrderPurchase_RecommendMore";
    private static final String OrderPurchase_Recommend_SimilarProduct = "OrderPurchase_Recommend_SimilarProduct";
    public static final String OrderSuccess_DeleteSkuReson = "OrderSuccess_DeleteSkuReson";
    public static final String OrderSuccess_PageName = "AffirmReceivingSuccessActivity";
    private static final String OrderSuccess_Productid = "OrderSuccess_Productid";
    private static final String OrderSuccess_RecomDelete = "OrderSuccess_RecomDelete";
    private static final String OrderSuccess_RecomSimilar = "OrderSuccess_RecomSimilar";
    private static final String OrderSuccess_RecommendMore = "OrderSuccess_RecommendMore";
    private static final String OrderSuccess_Recommend_ProductAddtoCart = "OrderSuccess_Recommend_ProductAddtoCart";
    public static final String OrderSuccess_Recommend_ProductExpo = "OrderSuccess_Recommend_ProductExpo";
    public static final String Orderdetail_RecomSku_Expo = "Orderdetail_RecomSkuExpo";
    private static final String PRV_KEYS = "prv_keys";
    private static final String PUB_KEYS = "pub_keys";
    public static final String PdInfoRecommend_Page_Name = "PdInfoRecommendFragment";
    public static final String PhoneChargeOrder_DeleteSkuReson = "PhoneChargeOrder_DeleteSkuReson";
    public static final String PhoneChargeOrder_GuessYouLike = "PhoneChargeOrder_GuessYouLike";
    public static final String PhoneChargeOrder_PageId = "PhoneCharge_OrderDetailMain";
    public static final String PhoneChargeOrder_PageName = "PhoneFlowChargeFragment";
    public static final String PhoneChargeOrder_RecomSkuExpo = "PhoneChargeOrder_RecomSkuExpo";
    public static final String PhoneChargeOrder_Recommed_Delete = "PhoneChargeOrder_Recommed_Delete";
    public static final String PhoneChargeOrder_RecommendMore = "PhoneChargeOrder_RecommendMore";
    public static final String PhoneChargeOrder_SimilarProduct = "PhoneChargeOrder_SimilarProduct";
    public static final String ProductArchive_Fcommit = "ProductArchive_Fcommit";
    public static final String ProductArchive_Feedback = "ProductArchive_Feedback";
    public static final String ProductArchive_Product = "ProductArchive_Product";
    public static final String ProductArchive_ProductExpo = "ProductArchive_ProductExpo";
    public static final String ProductArchive_ProductSimilar = "ProductArchive_ProductSimilar";
    public static final String ProductDetailActivity = "ProductDetailActivity";
    public static final String ProductdetailFeeds_Fcmt = "ProductdetailFeeds_Fcmt";
    public static final String ProductdetailFeeds_MainPage = "ProductdetailFeeds_MainPage";
    public static final String ProductdetailFeeds_aggregation = "ProductdetailFeeds_aggregation";
    public static final String ProductdetailFeeds_aggregationexpo = "ProductdetailFeeds_aggregationexpo";
    public static final String ProductdetailFeeds_feedback = "ProductdetailFeeds_feedback";
    public static final String ProductdetailFeeds_product = "ProductdetailFeeds_product";
    public static final String ProductdetailFeeds_productexpo = "ProductdetailFeeds_productexpo";
    private static final String Productdetail_AccessoriesCartIcon = "Productdetail_AccessoriesCartIcon";
    public static final String Productdetail_AccessoriesProduct = "Productdetail_AccessoriesProduct";
    public static final String Productdetail_AccessoriesProductExpo = "Productdetail_AccessoriesProductExpo";
    public static final String Productdetail_AddcartRecycling = "Productdetail_AddcartRecycling";
    public static final String Productdetail_AddcartRecyclingExpo = "Productdetail_AddcartRecyclingExpo";
    public static final String Productdetail_Aggregation = "Productdetail_Aggregation";
    public static final String Productdetail_AggregationAccess = "Productdetail_AggregationAccess";
    public static final String Productdetail_AggregationAccessExpo = "Productdetail_AggregationAccessExpo";
    public static final String Productdetail_Aggregationexpo = "Productdetail_Aggregationexpo";
    public static final String Productdetail_MainPage = "Productdetail_MainPage";
    public static final String Productdetail_ProductRecomTab = "Productdetail_ProductRecomTab";
    public static final String Productdetail_ProductRecomTab_Fcommit = "Productdetail_ProductRecomTab_Fcommit";
    public static final String Productdetail_ProductRecomTab_Feedback = "Productdetail_ProductRecomTab_Feedback";
    public static final String Productdetail_ProductVideoTime = "Productdetail_ProductVideoTime";
    public static final String Productdetail_RecommendToastAddtoCart = "Productdetail_RecommendToastAddtoCart";
    public static final String Productdetail_RecommendToastProductClick = "Productdetail_RecommendToastProductClick";
    public static final String Productdetail_ShopRecomTab = "Productdetail_ShopRecomTab";
    public static final String Productdetail_SimilarRecomTab = "Productdetail_SimilarRecomTab";
    public static final String Productdetail_SkuExpoRecomTab = "Productdetail_SkuExpoRecomTab";
    public static final String Productdetail_rec4tab_addcart = "Productdetail_rec4tab_addcart";
    public static final String Productdetail_similar = "ProductdetailFeeds_similar";
    public static final String RecomDetail_SceneTags = "RecomDetail_SceneTags";
    public static final String RecomDetail_SceneTagsAddToCart = "RecomDetail_SceneTagsAddToCart";
    public static final String RecomDetail_SceneTagsProduct = "RecomDetail_SceneTagsProduct";
    public static final String RecomDetail_SceneTagsProductExpo = "RecomDetail_SceneTagsProductExpo";
    public static final String SHOPCART_GUESSYOULIKE_ADDTOCART = "Shopcart_GuessYouLike_AddtoCart";
    public static final String Scene_Detail_Page_Name = "RecommendSceneActivity";
    public static final String Shopcart_AggregationAccess = "Shopcart_AggregationAccess";
    public static final String Shopcart_AggregationAccessExpo = "Shopcart_AggregationAccessExpo";
    public static final String Shopcart_Compare_AddtoCart = "Shopcart_Compare_AddtoCart";
    public static final String Shopcart_Compare_Productid = "Shopcart_Compare_Productid";
    public static final String Shopcart_Compare_SkuExpo = "Shopcart_Compare_SkuExpo";
    public static final String Shopcart_DeleteSkuReson = "Shopcart_DeleteSkuReson";
    private static final String Shopcart_GuessYouLike = "Shopcart_GuessYouLike";
    public static final String Shopcart_NPublicTestAccessExpo = "Shopcart_NPublicTestAccessExpo";
    public static final String Shopcart_PageId = "Shopcart_Main";
    private static final String Shopcart_Page_Name = "JDShoppingCartFragment";
    public static final String Shopcart_ProductVideoTime = "Shopcart_ProductVideoTime";
    public static final String Shopcart_PublicTestAccess = "Shopcart_PublicTestAccess";
    public static final String Shopcart_PublicTestAccessExpo = "Shopcart_PublicTestAccessExpo";
    public static final String Shopcart_RecomSku_Expo = "Shopcart_RecomSkuExpo";
    private static final String Shopcart_Recommend_Activity = "Shopcart_Recommend_Activity";
    private static final String Shopcart_Recommend_Coupon = "Shopcart_Recommend_Coupon";
    private static final String Shopcart_Recommend_Delete = "Shopcart_Recommend_Delete";
    public static final String Shopcart_Recommend_More = "Shopcart_Recommend_More";
    private static final String Shopcart_Recommend_Similar = "Shopcart_Recommend_Similar";
    private static final String Shopcart_Recommend_dna = "Shopcart_Recommend_dna";
    private static final String Shopcart_Recommend_shopid = "Shopcart_Recommend_shopid";
    public static final String Shopcart_SimilarGoodsAccess = "Shopcart_SimilarGoodsAccess";
    public static final String Shopcart_SimilarGoodsAccessExpo = "Shopcart_SimilarGoodsAccessExpo";
    private static final String TAG = "RecommendMtaUtils";
    public static final String TRACKING = "tracking";
    public static final String UET = "uet";
    public static boolean enableRightExpo = false;
    private static final String orderPurchase_AddShoppingCartRecommend = "OrderPurchase_AddShoppingCartRecommend";

    public static void GeneTabClickMta(Activity activity, String str) {
        JDMtaUtils.sendCommonData(activity, MyJD_GeneTabClick, str, "", MyJD_Page_Name, "", "", "", MyJD_PageId);
    }

    public static void GeneTabSlideMta(Activity activity) {
        JDMtaUtils.sendCommonData(activity, MyJD_GeneTabSlide, "", "", MyJD_Page_Name, "", "", "", MyJD_PageId);
    }

    public static void PublicNewTestExpoMta(Activity activity, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (i2 != 0) {
            if (i2 == 6) {
                str = Shopcart_NPublicTestAccessExpo;
                str2 = Shopcart_PageId;
                str3 = Shopcart_Page_Name;
            } else if (i2 == 9) {
                str = Home_NPublicTestAccessExpo;
                str2 = Home_PageId;
                str3 = "JDHomeFragment";
            } else if (i2 != 18) {
                str6 = null;
                str4 = null;
                str5 = null;
                JDMtaUtils.sendExposureData(activity, str6, str4, "", str5, "", "", "", "");
            }
            str4 = str2;
            str5 = str;
            str6 = str3;
            JDMtaUtils.sendExposureData(activity, str6, str4, "", str5, "", "", "", "");
        }
        str = MyJD_NPublicTestAccessExpo;
        str2 = MyJD_PageId;
        str3 = MyJD_Page_Name;
        str4 = str2;
        str5 = str;
        str6 = str3;
        JDMtaUtils.sendExposureData(activity, str6, str4, "", str5, "", "", "", "");
    }

    public static void PublicTestExpoMta(Activity activity, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (i2 != 0) {
            if (i2 == 6) {
                str = Shopcart_PublicTestAccessExpo;
                str2 = Shopcart_PageId;
                str3 = Shopcart_Page_Name;
            } else if (i2 == 9) {
                str = Home_PublicTestAccessExpo;
                str2 = Home_PageId;
                str3 = "JDHomeFragment";
            } else if (i2 != 18) {
                str5 = null;
                str6 = null;
                str4 = null;
                JDMtaUtils.sendCommonData(activity, str5, "", "", str6, "", "", "", str4);
            }
            str4 = str2;
            str5 = str;
            str6 = str3;
            JDMtaUtils.sendCommonData(activity, str5, "", "", str6, "", "", "", str4);
        }
        str = MyJD_PublicTestAccessExpo;
        str2 = MyJD_PageId;
        str3 = MyJD_Page_Name;
        str4 = str2;
        str5 = str;
        str6 = str3;
        JDMtaUtils.sendCommonData(activity, str5, "", "", str6, "", "", "", str4);
    }

    public static void addCartRecyclingClickMta(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        Object obj;
        if (i2 != 35) {
            str4 = null;
            obj = null;
            str3 = null;
        } else {
            str3 = Productdetail_MainPage;
            str4 = Productdetail_AddcartRecycling;
            obj = "ProductDetailActivity";
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                JDMtaUtils.sendCommonData(context, str4, str, "", obj, "", "", "", str3);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("extension_id", str2);
            JDMtaUtils.sendCommonDataWithExt(context, str4, str, "", obj, "", "", "", str3, hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static String addKeyToMtaJson(JSONObject jSONObject, String str) {
        if (!RecommendUtils.downgradeExpoAdd() && !TextUtils.isEmpty(str) && jSONObject != null) {
            try {
                if (jSONObject.length() > 0 && !TextUtils.isEmpty(str)) {
                    StringBuilder sb = new StringBuilder(str);
                    sb.insert(1, jSONObject.toString().replace("{", "").replace("}", "") + DYConstants.DY_REGEX_COMMA);
                    return sb.toString();
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static void aggregationClickMta(Activity activity, RecommendDna recommendDna, int i2, String str) {
        aggregationClickMtaRealize(activity, recommendDna.sourceValue, i2, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void aggregationClickMtaRealize(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = PdInfoRecommend_Page_Name;
        String str8 = Productdetail_MainPage;
        if (i2 != 0) {
            if (i2 == 6) {
                str3 = Shopcart_AggregationAccess;
                str8 = Shopcart_PageId;
                str7 = Shopcart_Page_Name;
            } else if (i2 == 9) {
                str3 = Home_AggregationAccess;
                str8 = Home_PageId;
                str7 = "JDHomeFragment";
            } else if (i2 != 18) {
                if (i2 == 24) {
                    str3 = Productdetail_AggregationAccess;
                } else if (i2 == 35) {
                    str3 = Productdetail_Aggregation;
                    str7 = "ProductDetailActivity";
                } else if (i2 == 10024) {
                    str3 = ProductdetailFeeds_aggregation;
                    str8 = ProductdetailFeeds_MainPage;
                } else if (i2 == 3) {
                    str3 = OrderDetail_AggregationAccess;
                    str8 = OrderDetail_PageId;
                    str7 = OrderDetail_Page_Name;
                } else if (i2 != 4) {
                    str6 = null;
                    str4 = null;
                    str5 = null;
                    if (TextUtils.isEmpty(str2)) {
                        JDMtaUtils.sendCommonData(context, str6, str, "", str4, "", "", "", str5);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("extension_id", str2);
                    JDMtaUtils.sendCommonDataWithExt(context, str6, str, "", str4, "", "", "", str5, hashMap);
                    return;
                } else {
                    str3 = OrderFollow_AggregationAccess;
                    str8 = OrderFollow_PageId;
                    str7 = OrderFollow_Page_Name;
                }
            }
            str4 = str7;
            str5 = str8;
            str6 = str3;
            if (TextUtils.isEmpty(str2)) {
            }
        }
        str3 = MyJD_AggregationAccess;
        str8 = MyJD_PageId;
        str7 = MyJD_Page_Name;
        str4 = str7;
        str5 = str8;
        str6 = str3;
        if (TextUtils.isEmpty(str2)) {
        }
    }

    public static void appHeightToExpo(RecommendItem recommendItem, int i2) {
        if (recommendItem != null) {
            try {
                RecommendProduct recommendProduct = recommendItem.product;
                if (recommendProduct != null) {
                    appHeightToExpo_product(recommendProduct, i2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void appHeightToExpo_product(RecommendProduct recommendProduct, int i2) {
        if (recommendProduct != null) {
            try {
                recommendProduct.appendMtaJson_real.put(RecommendConstant.RECOM_SKU_HEIGHT, DPIUtil.px2dip(i2) + "");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void bannerViewClickMta(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        String str5;
        if (i2 != 9) {
            str4 = null;
            str3 = null;
            str5 = null;
        } else {
            str3 = Home_PageId;
            str4 = New_FocusPic;
            str5 = "JDHomeFragment";
        }
        if (TextUtils.isEmpty(str2)) {
            JDMtaUtils.sendClickDataWithExt(context, str4, str, "", str3, str5, "", "", str, null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", str2);
        JDMtaUtils.sendClickDataWithExt(context, str4, str, "", str3, str5, "", "", str, hashMap);
    }

    public static String dealRecommendProductExpoData(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("labellist", "-100");
            if (z) {
                jSONObject.put("videoplay", "0");
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return str;
            }
            return str;
        }
    }

    public static void deleteProductClickMta(Activity activity, RecommendProduct recommendProduct, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str = MyFollow_DeleteRecommendProduct;
                    str2 = MyFollow_PageId;
                    str3 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str = OrderFinish_Recommend_Delete;
                    str2 = OrderFinish_PageId;
                    str3 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str = OrderDetail_DeleteSku;
                    str2 = OrderDetail_PageId;
                    str3 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str = OrderFollow_DeleteSku;
                    str2 = OrderFollow_PageId;
                    str3 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str = Shopcart_Recommend_Delete;
                    str2 = Shopcart_PageId;
                    str3 = Shopcart_Page_Name;
                } else if (i2 == 13) {
                    str = OrderSuccess_RecomDelete;
                    str2 = OrderCenter_ReceiveSuccess_Pageid;
                    str3 = OrderSuccess_PageName;
                } else if (i2 == 15) {
                    str = MyHistory_RecomDelete;
                    str2 = MyHistory_Pageid;
                    str3 = MyHistory_PageName;
                } else if (i2 == 34) {
                    str = MessageCenter_DeleteSku;
                    str2 = MessageCenter_PageId;
                    str3 = MessageCenter_Page_Name;
                } else if (i2 == 9) {
                    str = Home_DeleteSku;
                    str2 = Home_PageId;
                    str3 = "JDHomeFragment";
                } else if (i2 == 10) {
                    str = OrderPurchase_RecomDelete;
                    str2 = OrderPurchase_Pageid;
                    str3 = OrderPurchase_PageName;
                } else if (i2 == 17) {
                    str = PhoneChargeOrder_Recommed_Delete;
                    str2 = PhoneChargeOrder_PageId;
                    str3 = PhoneChargeOrder_PageName;
                } else if (i2 != 18) {
                    switch (i2) {
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                            str = OrderList_DeleteSku;
                            str2 = OrderCenter_List;
                            str3 = "MyOrderListActivity";
                            break;
                        default:
                            str5 = null;
                            str6 = null;
                            str4 = null;
                            break;
                    }
                    JDMtaUtils.sendCommonData(activity, str5, recommendProduct.feedbackSourceValue, "", str6, "", "", "", str4);
                    return;
                }
                str4 = str2;
                str5 = str;
                str6 = str3;
                JDMtaUtils.sendCommonData(activity, str5, recommendProduct.feedbackSourceValue, "", str6, "", "", "", str4);
                return;
            }
            JDMtaUtils.sendCommonData(activity, str5, recommendProduct.feedbackSourceValue, "", str6, "", "", "", str4);
            return;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str = MyJD_Recommend_Delete;
        str2 = MyJD_PageId;
        str3 = MyJD_Page_Name;
        str4 = str2;
        str5 = str;
        str6 = str3;
    }

    public static void deleteProductResonClickMta(Activity activity, String str, int i2) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = Productdetail_MainPage;
        String str7 = PdInfoRecommend_Page_Name;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str2 = MyFollow_DeleteSkuReson;
                    str6 = MyFollow_PageId;
                    str7 = MyFollow_Page_Name;
                } else if (i2 == 3) {
                    str2 = OrderDetail_DeleteSkuReson;
                    str6 = OrderDetail_PageId;
                    str7 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str2 = OrderFollow_DeleteSkuReason;
                    str6 = OrderFollow_PageId;
                    str7 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str2 = Shopcart_DeleteSkuReson;
                    str6 = Shopcart_PageId;
                    str7 = Shopcart_Page_Name;
                } else if (i2 == 13) {
                    str2 = OrderSuccess_DeleteSkuReson;
                    str6 = OrderCenter_ReceiveSuccess_Pageid;
                    str7 = OrderSuccess_PageName;
                } else if (i2 == 15) {
                    str2 = MyHistory_DeleteSkuReson;
                    str6 = MyHistory_Pageid;
                    str7 = MyHistory_PageName;
                } else if (i2 == 24) {
                    str2 = Productdetail_ProductRecomTab_Fcommit;
                } else if (i2 == 34) {
                    str2 = MessageCenter_DeleteSkuReson;
                    str6 = MessageCenter_PageId;
                    str7 = MessageCenter_Page_Name;
                } else if (i2 == 49) {
                    str2 = ProductArchive_Fcommit;
                } else if (i2 == 10024) {
                    str2 = ProductdetailFeeds_Fcmt;
                    str6 = ProductdetailFeeds_MainPage;
                } else if (i2 == 9) {
                    str2 = Home_DeleteSkuReson;
                    str6 = Home_PageId;
                    str7 = "JDHomeFragment";
                } else if (i2 == 10) {
                    str2 = OrderPurchase_DeleteSkuReson;
                    str6 = OrderPurchase_Pageid;
                    str7 = OrderPurchase_PageName;
                } else if (i2 == 17) {
                    str2 = PhoneChargeOrder_DeleteSkuReson;
                    str6 = PhoneChargeOrder_PageId;
                    str7 = PhoneChargeOrder_PageName;
                } else if (i2 != 18) {
                    switch (i2) {
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                            str2 = OrderList_DeleteSkuReason;
                            str6 = OrderCenter_List;
                            str7 = "MyOrderListActivity";
                            break;
                        default:
                            str5 = null;
                            str4 = null;
                            str3 = null;
                            break;
                    }
                    JDMtaUtils.sendCommonData(activity, str5, str, "", str4, "", "", "", str3);
                    return;
                }
                str3 = str6;
                str4 = str7;
                str5 = str2;
                JDMtaUtils.sendCommonData(activity, str5, str, "", str4, "", "", "", str3);
                return;
            }
            JDMtaUtils.sendCommonData(activity, str5, str, "", str4, "", "", "", str3);
            return;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str2 = MyJD_DeleteSkuReson;
        str6 = MyJD_PageId;
        str7 = MyJD_Page_Name;
        str3 = str6;
        str4 = str7;
        str5 = str2;
    }

    public static void doExpo(String str, String str2, String str3, ExpoData expoData, Context context) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            expoData.getnowtime();
            JSONObject jSONObject = new JSONObject(expoData.getExposureSourceValue());
            jSONObject.put("skutime", expoData.time);
            JDMtaUtils.sendExposureDataWithExt(context, str, "", str2, str3, "", jSONObject.toString(), "", "", "", null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void doHomeChannelCardClick(Context context, int i2, JumpEntity jumpEntity, RecommendHomeCardBean.SubWareList subWareList) {
        if (subWareList == null || TextUtils.isEmpty(subWareList.sourceValue) || jumpEntity == null) {
            return;
        }
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(subWareList.sourceValue);
            jDJSONObject.put("skuposition", (Object) Integer.valueOf(i2));
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "Home_Floor", jumpEntity.getSrv(), "", Home_PageId, "JDHomeFragment", i2 == 0 ? subWareList.getAllSkuId() : subWareList.getSkuId(i2 - 1), "", jDJSONObject.toString(), null);
        } catch (Exception unused) {
        }
    }

    public static void doHomeLiveVideoCardClick(Context context, JumpEntity jumpEntity, RecommendHomeCardBean.SubWareList subWareList) {
        if (subWareList == null || TextUtils.isEmpty(subWareList.sourceValue) || jumpEntity == null) {
            return;
        }
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(subWareList.sourceValue);
            jDJSONObject.put("skuposition", (Object) 0);
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "Home_Floor", jumpEntity.getSrv(), "", Home_PageId, "JDHomeFragment", "", "", jDJSONObject.toString(), null);
        } catch (Exception unused) {
        }
    }

    public static void enterShopClickMta(Activity activity, RecommendShop recommendShop, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str = MyFollow_Recommendshopid;
                    str2 = MyFollow_PageId;
                    str3 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str = OrderFinish_Recommend_shopid;
                    str2 = OrderFinish_PageId;
                    str3 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str = OrderDetail_Recommend_shopid;
                    str2 = OrderDetail_PageId;
                    str3 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str = OrderFollow_Recommend_shopid;
                    str2 = OrderFollow_PageId;
                    str3 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str = Shopcart_AggregationAccess;
                    str2 = Shopcart_PageId;
                    str3 = Shopcart_Page_Name;
                } else if (i2 == 9) {
                    str = Home_AggregationAccess;
                    str2 = Home_PageId;
                    str3 = "JDHomeFragment";
                } else if (i2 != 18) {
                    if (i2 != 24) {
                        str5 = null;
                        str6 = null;
                        str4 = null;
                        JDMtaUtils.sendCommonData(activity, str5, recommendShop.sourceValue, "", str6, "", "", "", str4);
                        return;
                    }
                    str = Productdetail_AggregationAccess;
                    str2 = Productdetail_MainPage;
                    str3 = PdInfoRecommend_Page_Name;
                }
                str4 = str2;
                str5 = str;
                str6 = str3;
                JDMtaUtils.sendCommonData(activity, str5, recommendShop.sourceValue, "", str6, "", "", "", str4);
                return;
            }
            JDMtaUtils.sendCommonData(activity, str5, recommendShop.sourceValue, "", str6, "", "", "", str4);
            return;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str = MyJD_AggregationAccess;
        str2 = MyJD_PageId;
        str3 = MyJD_Page_Name;
        str4 = str2;
        str5 = str;
        str6 = str3;
    }

    public static void festivalPromotionClickMta(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        Object obj;
        if (i2 != 9) {
            str4 = null;
            obj = null;
            str3 = null;
        } else {
            str3 = Home_PageId;
            str4 = Home_Promotion_Product;
            obj = "JDHomeFragment";
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                JDMtaUtils.sendCommonData(context, str4, str, "", obj, "", "", "", str3);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("extension_id", str2);
            JDMtaUtils.sendCommonDataWithExt(context, str4, str, "", obj, "", "", "", str3, hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static String[] getClickEvent(int i2, RecommendProduct recommendProduct) {
        String str = "JDHomeFragment";
        String str2 = Home_PageId;
        String str3 = "";
        if (i2 != 0) {
            if (i2 == 1) {
                str3 = MyFollow_RecommendProduct;
                str2 = MyFollow_PageId;
                str = MyFollow_Page_Name;
            } else if (i2 == 2) {
                str3 = OrderFinish_Recommend_Product;
                str2 = OrderFinish_PageId;
                str = OrderFinish_Page_Name;
            } else if (i2 == 3) {
                str3 = OrderDetail_ProductRecommend;
                str2 = OrderDetail_PageId;
                str = OrderDetail_Page_Name;
            } else if (i2 == 4) {
                str3 = OrderFollow_ProductRecommend;
                str2 = OrderFollow_PageId;
                str = OrderFollow_Page_Name;
            } else if (i2 == 6) {
                str3 = "Shopcart_GuessYouLike";
                str2 = Shopcart_PageId;
                str = Shopcart_Page_Name;
            } else if (i2 == 13) {
                str3 = OrderSuccess_Productid;
                str2 = OrderCenter_ReceiveSuccess_Pageid;
                str = OrderSuccess_PageName;
            } else if (i2 != 15) {
                if (i2 == 24) {
                    str3 = Productdetail_ProductRecomTab;
                } else if (i2 == 51) {
                    str3 = OrderCancelFinish_recproduct;
                    str2 = OrderCenter_CancelFinish_Page_Id;
                    str = OrderCenter_CancelFinish_Page_Name;
                } else if (i2 == 10024) {
                    str3 = ProductdetailFeeds_product;
                    str2 = ProductdetailFeeds_MainPage;
                    str = PdInfoRecommend_Page_Name;
                } else if (i2 == 10036) {
                    str3 = Productdetail_RecommendToastProductClick;
                } else if (i2 == 9) {
                    str3 = (recommendProduct == null || recommendProduct.isBackUp != 1) ? Home_Productid : Home_Productid_Backup;
                } else if (i2 == 10) {
                    str3 = OrderPurchase_ProductRecommend;
                    str2 = OrderPurchase_Pageid;
                    str = OrderPurchase_PageName;
                } else if (i2 == 17) {
                    str3 = PhoneChargeOrder_GuessYouLike;
                    str2 = PhoneChargeOrder_PageId;
                    str = PhoneChargeOrder_PageName;
                } else if (i2 != 18) {
                    if (i2 != 48) {
                        if (i2 != 49) {
                            if (i2 == 63) {
                                str3 = Card_Member_Nearbysale;
                            } else if (i2 != 64) {
                                switch (i2) {
                                    case 27:
                                        str3 = OrderList_AllProductRecom;
                                        str = "MyOrderListActivity";
                                        str2 = OrderCenter_List;
                                        break;
                                    case 28:
                                        str3 = OrderList_UnpaidProductRecom;
                                        str = "MyOrderListActivity";
                                        str2 = OrderCenter_List;
                                        break;
                                    case 29:
                                        str3 = OrderList_DispatchedProductRecom;
                                        str = "MyOrderListActivity";
                                        str2 = OrderCenter_List;
                                        break;
                                    case 30:
                                        str3 = OrderList_FinishedProductRecom;
                                        str = "MyOrderListActivity";
                                        str2 = OrderCenter_List;
                                        break;
                                    case 31:
                                        str3 = OrderList_CanceledProductRecom;
                                        str = "MyOrderListActivity";
                                        str2 = OrderCenter_List;
                                        break;
                                    default:
                                        switch (i2) {
                                            case 34:
                                                str3 = MessageCenter_Productid;
                                                str2 = MessageCenter_PageId;
                                                str = MessageCenter_Page_Name;
                                                break;
                                            case 35:
                                                break;
                                            case 36:
                                                str3 = Home_AggregationAccess;
                                                break;
                                            case 37:
                                                str3 = RecomDetail_SceneTagsProduct;
                                                str2 = RecomDetail_SceneTags;
                                                str = Scene_Detail_Page_Name;
                                                break;
                                            default:
                                                str = "";
                                                str2 = str;
                                                break;
                                        }
                                }
                            } else {
                                str3 = Card_Member_Store_Service;
                            }
                            str = Card_member;
                            str2 = str;
                        } else {
                            str3 = ProductArchive_Product;
                        }
                    }
                    str3 = Productdetail_AccessoriesProduct;
                    str = "ProductDetailActivity";
                    str2 = Productdetail_MainPage;
                }
                str = PdInfoRecommend_Page_Name;
                str2 = Productdetail_MainPage;
            } else {
                str3 = MyHistory_GuessYouLike;
                str2 = MyHistory_Pageid;
                str = MyHistory_PageName;
            }
            return new String[]{str3, str2, str};
        }
        str3 = MyJD_GuessYouLike;
        str2 = MyJD_PageId;
        str = MyJD_Page_Name;
        return new String[]{str3, str2, str};
    }

    public static String getProductClickEventId(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return MyFollow_RecommendProduct;
            }
            if (i2 == 2) {
                return OrderFinish_Recommend_Product;
            }
            if (i2 == 3) {
                return OrderDetail_ProductRecommend;
            }
            if (i2 == 4) {
                return OrderFollow_ProductRecommend;
            }
            if (i2 == 6) {
                return "Shopcart_GuessYouLike";
            }
            if (i2 == 13) {
                return OrderSuccess_Productid;
            }
            if (i2 == 15) {
                return MyHistory_GuessYouLike;
            }
            if (i2 == 24) {
                return Productdetail_ProductRecomTab;
            }
            if (i2 == 51) {
                return OrderCancelFinish_recproduct;
            }
            if (i2 == 10024) {
                return ProductdetailFeeds_product;
            }
            if (i2 == 10036) {
                return Productdetail_RecommendToastProductClick;
            }
            if (i2 == 9) {
                return Home_Productid;
            }
            if (i2 == 10) {
                return OrderPurchase_ProductRecommend;
            }
            if (i2 == 17) {
                return PhoneChargeOrder_GuessYouLike;
            }
            if (i2 != 18) {
                if (i2 != 48) {
                    if (i2 != 49) {
                        switch (i2) {
                            case 27:
                                return OrderList_AllProductRecom;
                            case 28:
                                return OrderList_UnpaidProductRecom;
                            case 29:
                                return OrderList_DispatchedProductRecom;
                            case 30:
                                return OrderList_FinishedProductRecom;
                            case 31:
                                return OrderList_CanceledProductRecom;
                            default:
                                switch (i2) {
                                    case 34:
                                        return MessageCenter_Productid;
                                    case 35:
                                        break;
                                    case 36:
                                        return Home_AggregationAccess;
                                    case 37:
                                        return RecomDetail_SceneTagsProduct;
                                    default:
                                        return "";
                                }
                        }
                    } else {
                        return ProductArchive_Product;
                    }
                }
                return Productdetail_AccessoriesProduct;
            }
        }
        return MyJD_GuessYouLike;
    }

    private static void handleKeys(JSONArray jSONArray, Object obj, JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONArray == null || obj == null || jSONObject == null) {
            return;
        }
        try {
            if (obj instanceof JDJSONObject) {
                jSONObject2 = new JSONObject(((JDJSONObject) obj).toJSONString());
            } else if (obj instanceof JSONObject) {
                jSONObject2 = (JSONObject) obj;
            } else {
                jSONObject2 = new JSONObject(JDJSON.toJSONString(obj));
            }
        } catch (JSONException e2) {
            jSONObject2 = new JSONObject();
            e2.printStackTrace();
        }
        RecommendMtaELParser recommendMtaELParser = new RecommendMtaELParser();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            recommendMtaELParser.compile(optString);
            Object valueFromEl = recommendMtaELParser.getValueFromEl(jSONObject2);
            try {
                String replace = optString.replace(OrderISVUtil.MONEY_DECIMAL, "-");
                if (valueFromEl == null) {
                    valueFromEl = "-100";
                }
                jSONObject.put(replace, valueFromEl);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void handleTrackingNode(String str, Object obj, JSONObject jSONObject, JDJSONObject jDJSONObject, boolean z, boolean z2) {
        JSONObject jSONObject2;
        JSONObject optJSONObject;
        if (str == null || obj == null || jSONObject == null || jDJSONObject == null) {
            return;
        }
        try {
            try {
                jSONObject2 = new JSONObject(jDJSONObject.getInnerMap());
            } catch (Exception unused) {
                jSONObject2 = new JSONObject();
            }
            if (jSONObject2.optJSONObject(UET) == null || jSONObject2.optJSONObject(UET).optJSONObject(TRACKING) == null || (optJSONObject = jSONObject2.optJSONObject(UET).optJSONObject(TRACKING).optJSONObject(str)) == null) {
                return;
            }
            if (z2 && optJSONObject.optJSONArray(PRV_KEYS) != null) {
                JSONObject jSONObject3 = new JSONObject();
                handleKeys(optJSONObject.optJSONArray(PRV_KEYS), obj, jSONObject3);
                jSONObject.put(VideoPerfEntity.FIELD_PRV, jSONObject3);
            }
            if (!z || optJSONObject.optJSONArray(PUB_KEYS) == null) {
                return;
            }
            handleKeys(optJSONObject.optJSONArray(PUB_KEYS), jSONObject2, jSONObject);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0057 A[Catch: Exception -> 0x007f, TryCatch #0 {Exception -> 0x007f, blocks: (B:27:0x0051, B:29:0x0057, B:30:0x0066), top: B:37:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0066 A[Catch: Exception -> 0x007f, TRY_LEAVE, TryCatch #0 {Exception -> 0x007f, blocks: (B:27:0x0051, B:29:0x0057, B:30:0x0066), top: B:37:0x0051 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void jumpGeneClickMta(Activity activity, RecommendDna recommendDna, int i2, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str2 = MyFollow_Recommenddna;
                    str3 = MyFollow_PageId;
                    str4 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str2 = OrderFinish_Recommend_dna;
                    str3 = OrderFinish_PageId;
                    str4 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str2 = OrderDetail_Recommend_dna;
                    str3 = OrderDetail_PageId;
                    str4 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str2 = OrderFollow_Recommend_dna;
                    str3 = OrderFollow_PageId;
                    str4 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str2 = Shopcart_Recommend_dna;
                    str3 = Shopcart_PageId;
                    str4 = Shopcart_Page_Name;
                } else if (i2 == 9) {
                    str2 = Home_ShoppingGene;
                    str3 = Home_PageId;
                    str4 = "JDHomeFragment";
                } else if (i2 != 18) {
                    str6 = null;
                    str7 = null;
                    str5 = null;
                    if (TextUtils.isEmpty(str)) {
                        JDMtaUtils.sendCommonData(activity, str6, recommendDna.sourceValue, "", str7, "", "", "", str5);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("extension_id", str);
                    JDMtaUtils.sendCommonDataWithExt(activity, str6, recommendDna.sourceValue, "", str7, "", "", "", str5, hashMap);
                    return;
                }
                str5 = str3;
                str6 = str2;
                str7 = str4;
                if (TextUtils.isEmpty(str)) {
                }
            }
            if (TextUtils.isEmpty(str)) {
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str2 = MyJD_Recommend_dna;
        str3 = MyJD_PageId;
        str4 = MyJD_Page_Name;
        str5 = str3;
        str6 = str2;
        str7 = str4;
    }

    public static void jumpPublicTestClickMta(Activity activity, int i2, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        try {
            if (i2 != 0) {
                if (i2 == 6) {
                    str2 = Shopcart_PublicTestAccess;
                    str3 = Shopcart_PageId;
                    str4 = Shopcart_Page_Name;
                } else if (i2 == 9) {
                    str2 = Home_PublicTestAccess;
                    str3 = Home_PageId;
                    str4 = "JDHomeFragment";
                } else if (i2 != 18) {
                    str6 = null;
                    str7 = null;
                    str5 = null;
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("entersource", str);
                    JDMtaUtils.sendCommonData(activity, str6, jSONObject.toString(), "", str7, "", "", "", str5);
                    return;
                }
                str5 = str3;
                str6 = str2;
                str7 = str4;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("entersource", str);
                JDMtaUtils.sendCommonData(activity, str6, jSONObject2.toString(), "", str7, "", "", "", str5);
                return;
            }
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("entersource", str);
            JDMtaUtils.sendCommonData(activity, str6, jSONObject22.toString(), "", str7, "", "", "", str5);
            return;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return;
        }
        str2 = MyJD_PublicTestAccess;
        str3 = MyJD_PageId;
        str4 = MyJD_Page_Name;
        str5 = str3;
        str6 = str2;
        str7 = str4;
    }

    public static void liveProductMaskProductClick(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        Object obj;
        if (i2 != 9) {
            str4 = null;
            obj = null;
            str3 = null;
        } else {
            str3 = Home_PageId;
            str4 = Home_Mask_LiveProduct;
            obj = "JDHomeFragment";
        }
        if (TextUtils.isEmpty(str2)) {
            JDMtaUtils.sendCommonData(context, str4, str, "", obj, "", "", "", str3);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", str2);
        JDMtaUtils.sendCommonDataWithExt(context, str4, str, "", obj, "", "", "", str3, hashMap);
    }

    public static void liveProductMaskShow(Context context, String str, int i2, String str2) {
        String str3;
        String str4;
        Object obj;
        if (i2 != 9) {
            str4 = null;
            obj = null;
            str3 = null;
        } else {
            str3 = Home_PageId;
            str4 = Home_LiveProduct_MaskShow;
            obj = "JDHomeFragment";
        }
        if (TextUtils.isEmpty(str2)) {
            JDMtaUtils.sendCommonData(context, str4, str, "", obj, "", "", "", str3);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", str2);
        JDMtaUtils.sendCommonDataWithExt(context, str4, str, "", obj, "", "", "", str3, hashMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00ce A[Catch: Exception -> 0x00fd, TRY_ENTER, TryCatch #0 {Exception -> 0x00fd, blocks: (B:62:0x00ce, B:63:0x00ee), top: B:71:0x00cc }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ee A[Catch: Exception -> 0x00fd, TRY_LEAVE, TryCatch #0 {Exception -> 0x00fd, blocks: (B:62:0x00ce, B:63:0x00ee), top: B:71:0x00cc }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void lookSimilarClickMta(Activity activity, RecommendProduct recommendProduct, int i2, int i3) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = Productdetail_MainPage;
        String str6 = PdInfoRecommend_Page_Name;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str = MyFollow_RecommendSimilar;
                    str5 = MyFollow_PageId;
                    str6 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str = OrderFinish_Recommend_Similar;
                    str5 = OrderFinish_PageId;
                    str6 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str = OrderDetail_SimilarProduct;
                    str5 = OrderDetail_PageId;
                    str6 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str = OrderFollow_SimilarProduct;
                    str5 = OrderFollow_PageId;
                    str6 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str = Shopcart_Recommend_Similar;
                    str5 = Shopcart_PageId;
                    str6 = Shopcart_Page_Name;
                } else if (i2 == 13) {
                    str = OrderSuccess_RecomSimilar;
                    str5 = OrderCenter_ReceiveSuccess_Pageid;
                    str6 = OrderSuccess_PageName;
                } else if (i2 == 15) {
                    str = MyHistory_RecomSimilar;
                    str5 = MyHistory_Pageid;
                    str6 = MyHistory_PageName;
                } else if (i2 == 24) {
                    str = Productdetail_SimilarRecomTab;
                } else if (i2 == 34) {
                    str = MessageCenter_RecomSimilar;
                    str5 = MessageCenter_PageId;
                    str6 = MessageCenter_Page_Name;
                } else if (i2 == 49) {
                    str = ProductArchive_ProductSimilar;
                } else if (i2 == 51) {
                    str = OrderCancelFinish_similarsku;
                    str5 = OrderCenter_CancelFinish_Page_Id;
                    str6 = OrderCenter_CancelFinish_Page_Name;
                } else if (i2 == 10024) {
                    str = Productdetail_similar;
                    str5 = ProductdetailFeeds_MainPage;
                } else if (i2 == 9) {
                    str = Home_SimilarView;
                    str5 = Home_PageId;
                    str6 = "JDHomeFragment";
                } else if (i2 == 10) {
                    str = OrderPurchase_Recommend_SimilarProduct;
                    str5 = OrderPurchase_Pageid;
                    str6 = OrderPurchase_PageName;
                } else if (i2 == 17) {
                    str = PhoneChargeOrder_SimilarProduct;
                    str5 = PhoneChargeOrder_PageId;
                    str6 = PhoneChargeOrder_PageName;
                } else if (i2 != 18) {
                    switch (i2) {
                        case 27:
                            str = OrderList_AllRecomSimilar;
                            str3 = "MyOrderListActivity";
                            str2 = OrderCenter_List;
                            str4 = str;
                            break;
                        case 28:
                            str = OrderList_UnpaidRecomSimilar;
                            str3 = "MyOrderListActivity";
                            str2 = OrderCenter_List;
                            str4 = str;
                            break;
                        case 29:
                            str = OrderList_DispatchedRecomSimilar;
                            str3 = "MyOrderListActivity";
                            str2 = OrderCenter_List;
                            str4 = str;
                            break;
                        case 30:
                            str = OrderList_FinishedRecomSimilar;
                            str3 = "MyOrderListActivity";
                            str2 = OrderCenter_List;
                            str4 = str;
                            break;
                        case 31:
                            str = OrderList_CanceledRecomSimilar;
                            str3 = "MyOrderListActivity";
                            str2 = OrderCenter_List;
                            str4 = str;
                            break;
                        default:
                            str4 = null;
                            str3 = null;
                            str2 = null;
                            break;
                    }
                    if (i2 == 9) {
                        JDMtaUtils.sendCommonData(activity, str4, recommendProduct.similarSourceValue, "", str3, i3 + "", "", "", str2);
                    } else {
                        JDMtaUtils.sendCommonData(activity, str4, recommendProduct.similarSourceValue, "", str3, "", "", "", str2);
                    }
                    return;
                }
                str2 = str5;
                str3 = str6;
                str4 = str;
                if (i2 == 9) {
                }
                return;
            }
            if (i2 == 9) {
            }
            return;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str = MyJD_GuessYouLike_Similar;
        str5 = MyJD_PageId;
        str6 = MyJD_Page_Name;
        str2 = str5;
        str3 = str6;
        str4 = str;
    }

    public static void myJDMyStreetClickMta(BaseActivity baseActivity) {
        try {
            boolean isMyStreetNew = CommonBase.isMyStreetNew();
            if (isMyStreetNew) {
                CommonBase.setMyStreetNew(false);
            }
            JDMtaUtils.sendCommonData(baseActivity, MyJD_MyStreet, isMyStreetNew ? "new" : FontsUtil.KEY_MULTI_REGULAR, "", MyJD_Page_Name, "", "", "", MyJD_PageId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(16:3|(2:5|(1:(2:8|(2:10|(2:12|(15:(2:15|(1:(12:18|(2:20|(2:22|(2:24|(2:26|(1:28))(1:73))(1:74))(1:75))(1:76)|30|(3:68|69|70)(1:43)|44|45|46|47|48|(1:50)(1:61)|51|60)(1:77))(1:79))(1:80)|78|30|(1:32)|68|69|70|44|45|46|47|48|(0)(0)|51|60)(1:81))(1:82))(1:83))(1:84))(1:85))(1:86)|72|30|(0)|68|69|70|44|45|46|47|48|(0)(0)|51|60)|68|69|70|44|45|46|47|48|(0)(0)|51|60) */
    /* JADX WARN: Can't wrap try/catch for region: R(18:1|(16:3|(2:5|(1:(2:8|(2:10|(2:12|(15:(2:15|(1:(12:18|(2:20|(2:22|(2:24|(2:26|(1:28))(1:73))(1:74))(1:75))(1:76)|30|(3:68|69|70)(1:43)|44|45|46|47|48|(1:50)(1:61)|51|60)(1:77))(1:79))(1:80)|78|30|(1:32)|68|69|70|44|45|46|47|48|(0)(0)|51|60)(1:81))(1:82))(1:83))(1:84))(1:85))(1:86)|72|30|(0)|68|69|70|44|45|46|47|48|(0)(0)|51|60)|87|72|30|(0)|68|69|70|44|45|46|47|48|(0)(0)|51|60|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0100, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0101, code lost:
        r8 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0103, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0104, code lost:
        r0.printStackTrace();
        r3 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0111 A[Catch: Exception -> 0x011f, TryCatch #1 {Exception -> 0x011f, blocks: (B:76:0x0115, B:75:0x0111, B:71:0x0104, B:63:0x00df), top: B:85:0x00df }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void newFloatLayerMta(Activity activity, int i2, int i3, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String jSONObject;
        JSONObject jSONObject2;
        String str8;
        StringBuilder sb = new StringBuilder();
        sb.append(i3);
        String str9 = "";
        sb.append("");
        String sb2 = sb.toString();
        JSONObject jSONObject3 = null;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str2 = MyFollow_RecommendMore;
                    str3 = MyFollow_PageId;
                    str4 = MyFollow_Page_Name;
                } else if (i2 == 3) {
                    str2 = OrderDetail_RecommendMore;
                    str3 = OrderDetail_PageId;
                    str4 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str2 = OrderFollow_RecommendMore;
                    str3 = OrderFollow_PageId;
                    str4 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str2 = Shopcart_Recommend_More;
                    str3 = Shopcart_PageId;
                    str4 = Shopcart_Page_Name;
                } else if (i2 == 13) {
                    str2 = OrderSuccess_RecommendMore;
                    str3 = OrderCenter_ReceiveSuccess_Pageid;
                    str4 = OrderSuccess_PageName;
                } else if (i2 != 15) {
                    if (i2 == 24) {
                        str8 = Productdetail_ProductRecomTab_Feedback;
                    } else if (i2 == 34) {
                        str2 = MessageCenter_RecommendMore;
                        str3 = MessageCenter_PageId;
                        str4 = MessageCenter_Page_Name;
                    } else if (i2 != 49) {
                        if (i2 == 10024) {
                            str5 = ProductdetailFeeds_feedback;
                            str7 = PdInfoRecommend_Page_Name;
                            str6 = ProductdetailFeeds_MainPage;
                        } else if (i2 == 9) {
                            str2 = Home_RecommendMore;
                            str3 = Home_PageId;
                            str4 = "JDHomeFragment";
                        } else if (i2 == 10) {
                            str2 = OrderPurchase_RecommendMore;
                            str3 = OrderPurchase_Pageid;
                            str4 = OrderPurchase_PageName;
                        } else if (i2 == 17) {
                            str2 = PhoneChargeOrder_RecommendMore;
                            str3 = PhoneChargeOrder_PageId;
                            str4 = PhoneChargeOrder_PageName;
                        } else if (i2 != 18) {
                            switch (i2) {
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                case 31:
                                    str2 = OrderList_RecommendMore;
                                    str3 = OrderCenter_List;
                                    str4 = "MyOrderListActivity";
                                    break;
                                default:
                                    str5 = null;
                                    str6 = null;
                                    str7 = null;
                                    break;
                            }
                        }
                        if (i2 != 27 || i2 == 28 || i2 == 29 || i2 == 30 || i2 == 31 || i2 == 24 || i2 == 49 || i2 == 4) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("trigger", sb2);
                            jSONObject4.put("psource", String.valueOf(i2));
                            jSONObject = jSONObject4.toString();
                        } else {
                            jSONObject = sb2;
                        }
                        jSONObject2 = new JSONObject(str);
                        jSONObject2.put("trigger", sb2);
                        if (jSONObject2 != null) {
                            str9 = jSONObject2.toString();
                        }
                        JDMtaUtils.sendClickDataWithExt(activity, str5, jSONObject, "", str6, str7, "", "", str9, null);
                        return;
                    } else {
                        str8 = ProductArchive_Feedback;
                    }
                    str6 = Productdetail_MainPage;
                    str7 = PdInfoRecommend_Page_Name;
                    str5 = str8;
                    if (i2 != 27) {
                    }
                    JSONObject jSONObject42 = new JSONObject();
                    jSONObject42.put("trigger", sb2);
                    jSONObject42.put("psource", String.valueOf(i2));
                    jSONObject = jSONObject42.toString();
                    jSONObject2 = new JSONObject(str);
                    jSONObject2.put("trigger", sb2);
                    if (jSONObject2 != null) {
                    }
                    JDMtaUtils.sendClickDataWithExt(activity, str5, jSONObject, "", str6, str7, "", "", str9, null);
                    return;
                } else {
                    str2 = MyHistory_RecommendMore;
                    str3 = MyHistory_Pageid;
                    str4 = MyHistory_PageName;
                }
                str5 = str2;
                str6 = str3;
                str7 = str4;
                if (i2 != 27) {
                }
                JSONObject jSONObject422 = new JSONObject();
                jSONObject422.put("trigger", sb2);
                jSONObject422.put("psource", String.valueOf(i2));
                jSONObject = jSONObject422.toString();
                jSONObject2 = new JSONObject(str);
                jSONObject2.put("trigger", sb2);
                if (jSONObject2 != null) {
                }
                JDMtaUtils.sendClickDataWithExt(activity, str5, jSONObject, "", str6, str7, "", "", str9, null);
                return;
            }
            JSONObject jSONObject4222 = new JSONObject();
            jSONObject4222.put("trigger", sb2);
            jSONObject4222.put("psource", String.valueOf(i2));
            jSONObject = jSONObject4222.toString();
            jSONObject2 = new JSONObject(str);
            jSONObject2.put("trigger", sb2);
            if (jSONObject2 != null) {
            }
            JDMtaUtils.sendClickDataWithExt(activity, str5, jSONObject, "", str6, str7, "", "", str9, null);
            return;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str2 = MyJD_Recommend_More;
        str3 = MyJD_PageId;
        str4 = MyJD_Page_Name;
        str5 = str2;
        str6 = str3;
        str7 = str4;
        if (i2 != 27) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void productAddCarMta(Activity activity, RecommendProduct recommendProduct, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = PdInfoRecommend_Page_Name;
        String str7 = Productdetail_MainPage;
        if (i2 == 6) {
            str = SHOPCART_GUESSYOULIKE_ADDTOCART;
            str7 = Shopcart_PageId;
            str6 = Shopcart_Page_Name;
        } else if (i2 == 10) {
            str = orderPurchase_AddShoppingCartRecommend;
            str7 = OrderPurchase_Pageid;
            str6 = OrderPurchase_PageName;
        } else if (i2 == 13) {
            str = OrderSuccess_Recommend_ProductAddtoCart;
            str7 = OrderCenter_ReceiveSuccess_Pageid;
            str6 = OrderSuccess_PageName;
        } else if (i2 == 15) {
            str = MyHistory_GuessYouLike_AddtoCart;
            str7 = MyHistory_Pageid;
            str6 = MyHistory_PageName;
        } else if (i2 != 24) {
            if (i2 != 35) {
                if (i2 == 37) {
                    str = RecomDetail_SceneTagsAddToCart;
                    str7 = RecomDetail_SceneTags;
                    str6 = Scene_Detail_Page_Name;
                } else if (i2 != 48) {
                    if (i2 != 10036) {
                        str4 = null;
                        str3 = null;
                        str2 = null;
                        if (i2 != 10036) {
                            JDMtaUtils.sendClickDataWithExt(activity, str4, "", "", str3, str2, recommendProduct.getMainSku(), "", recommendProduct.sourceValue, null);
                            return;
                        }
                        if (i2 == 24) {
                            str5 = recommendProduct.cartSourceValue;
                        } else {
                            str5 = recommendProduct.sourceValue;
                        }
                        JDMtaUtils.sendClickDataWithExt(activity, str4, str5, "", str3, str2, "", "", recommendProduct.cartSourceValue, null);
                        return;
                    }
                    str = Productdetail_RecommendToastAddtoCart;
                }
            }
            str = Productdetail_AccessoriesCartIcon;
            str6 = "ProductDetailActivity";
        } else {
            str = Productdetail_rec4tab_addcart;
        }
        str2 = str6;
        str3 = str7;
        str4 = str;
        if (i2 != 10036) {
        }
    }

    public static void productClickMta(Activity activity, RecommendProduct recommendProduct, int i2, String str) {
        try {
            JDMtaUtils.sendCommonData(activity, str, recommendProduct.sourceValue, "", Shopcart_Page_Name, "", "", "", Shopcart_PageId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0057 A[Catch: Exception -> 0x007f, TryCatch #0 {Exception -> 0x007f, blocks: (B:27:0x0051, B:29:0x0057, B:30:0x0066), top: B:37:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0066 A[Catch: Exception -> 0x007f, TRY_LEAVE, TryCatch #0 {Exception -> 0x007f, blocks: (B:27:0x0051, B:29:0x0057, B:30:0x0066), top: B:37:0x0051 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void promotionClickMta(Activity activity, RecommendPromotion recommendPromotion, int i2, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str2 = MyFollow_RecommendActivity;
                    str3 = MyFollow_PageId;
                    str4 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str2 = OrderFinish_Recommend_Activity;
                    str3 = OrderFinish_PageId;
                    str4 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str2 = OrderDetail_Url;
                    str3 = OrderDetail_PageId;
                    str4 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str2 = OrderFollow_Url;
                    str3 = OrderFollow_PageId;
                    str4 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str2 = Shopcart_Recommend_Activity;
                    str3 = Shopcart_PageId;
                    str4 = Shopcart_Page_Name;
                } else if (i2 == 9) {
                    str2 = Home_Url;
                    str3 = Home_PageId;
                    str4 = "JDHomeFragment";
                } else if (i2 != 18) {
                    str6 = null;
                    str7 = null;
                    str5 = null;
                    if (TextUtils.isEmpty(str)) {
                        JDMtaUtils.sendCommonData(activity, str6, recommendPromotion.sourceValue, "", str7, "", "", "", str5);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("extension_id", str);
                    JDMtaUtils.sendCommonDataWithExt(activity, str6, recommendPromotion.sourceValue, "", str7, "", "", "", str5, hashMap);
                    return;
                }
                str5 = str3;
                str6 = str2;
                str7 = str4;
                if (TextUtils.isEmpty(str)) {
                }
            }
            if (TextUtils.isEmpty(str)) {
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str2 = MyJD_Recommend_Activity;
        str3 = MyJD_PageId;
        str4 = MyJD_Page_Name;
        str5 = str3;
        str6 = str2;
        str7 = str4;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033 A[Catch: Exception -> 0x0057, TRY_LEAVE, TryCatch #0 {Exception -> 0x0057, blocks: (B:16:0x002d, B:18:0x0033), top: B:21:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void realAggreatioExpo(int i2, Context context, ExpoData expoData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (context == null || expoData == null) {
            return;
        }
        try {
            if (i2 == 6) {
                str = "Shopcart_AggregationAccessExpo_real";
                str2 = Shopcart_PageId;
                str3 = Shopcart_Page_Name;
            } else if (i2 == 9) {
                str = Home_RecomResearchExpo;
                str2 = Home_PageId;
                str3 = "JDHomeFragment";
            } else if (i2 != 24) {
                str6 = "";
                str4 = str6;
                str5 = str4;
                if (TextUtils.isEmpty(str6)) {
                    expoData.getnowtime();
                    JSONObject jSONObject = new JSONObject(expoData.exposureSourceValue);
                    jSONObject.put("skutime", expoData.time);
                    JDMtaUtils.sendExposureDataWithExt(context, str6, "", str4, str5, "", jSONObject.toString(), "", "", "", null);
                    return;
                }
                return;
            } else {
                str = "Productdetail_AggregationAccessExpo_real";
                str2 = Productdetail_MainPage;
                str3 = "ProductDetailActivity";
            }
            if (TextUtils.isEmpty(str6)) {
            }
        } catch (Exception unused) {
            return;
        }
        str4 = str2;
        str5 = str3;
        str6 = str;
    }

    public static void realProductExpo(int i2, Context context, ExpoData expoData) {
        String str;
        if (context == null || expoData == null) {
            return;
        }
        String str2 = Productdetail_MainPage;
        String str3 = "";
        if (i2 == 3) {
            str3 = "Orderdetail_RecomSkuExpo_real";
            str2 = OrderDetail_PageId;
            str = OrderDetail_Page_Name;
        } else if (i2 == 4) {
            str3 = "OrderFollow_RecomSkuExpo_real";
            str2 = OrderFollow_PageId;
            str = OrderFollow_Page_Name;
        } else if (i2 == 6) {
            str3 = "Shopcart_RecomSkuExpo_real";
            str2 = Shopcart_PageId;
            str = Shopcart_Page_Name;
        } else if (i2 == 13) {
            str3 = "OrderSuccess_RecProductExpo_real";
            str2 = OrderCenter_ReceiveSuccess_Pageid;
            str = OrderSuccess_PageName;
        } else if (i2 == 15) {
            str3 = "MyHistory_RecomSkuExpo_real";
            str2 = MyHistory_Pageid;
            str = MyHistory_PageName;
        } else if (i2 == 24) {
            str3 = "Productdetail_SkuExpoRecomTab_real";
            str = "ProductDetailActivity";
        } else if (i2 == 37) {
            str3 = "RecomDetail_SceneTagsProductExpo_real";
            str2 = RecomDetail_SceneTags;
            str = Scene_Detail_Page_Name;
        } else if (i2 == 49) {
            str3 = "ProductArchive_ProductExpo_real";
            str = PdInfoRecommend_Page_Name;
        } else if (i2 == 51) {
            str3 = "OrderCancelFinish_recproductexpo_real";
            str2 = OrderCenter_CancelFinish_Page_Id;
            str = OrderCenter_CancelFinish_Page_Name;
        } else if (i2 == 9) {
            str3 = Home_Productid_Backup_Expo;
            str2 = Home_PageId;
            str = "JDHomeFragment";
        } else if (i2 != 10) {
            switch (i2) {
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                    str3 = "OrderList_RecomSkuExpo_real";
                    str2 = OrderCenter_List;
                    str = "MyOrderListActivity";
                    break;
                default:
                    str = "";
                    str2 = str;
                    break;
            }
        } else {
            str3 = "OrderPurchase_RecProductRecExpo_real";
            str2 = OrderPurchase_Pageid;
            str = OrderPurchase_PageName;
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        doExpo(str3, str2, str, expoData, context);
    }

    public static void recommendBuyNowClickMta(Context context, RecommendProduct recommendProduct, int i2) {
        if (recommendProduct == null) {
            return;
        }
        JDMtaUtils.sendClickDataWithExt(context, HOME_RECBUYNOW, "", "", Home_PageId, "JDHomeFragment", "", "", recommendProduct.quickBuySourceValue, null);
    }

    public static void recommendChannelUnderClickMta(Activity activity, String str, int i2) {
        if (i2 == 9) {
            JDMtaUtils.sendCommonData(activity, Home_ProductidFoot, str, "", "JDHomeFragment", "", "", "", Home_PageId);
        }
    }

    public static void recommendDetalisClickMta(Activity activity, RecommendDetails recommendDetails, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (i2 != 0) {
            if (i2 == 6) {
                str = Shopcart_SimilarGoodsAccess;
                str2 = Shopcart_PageId;
                str3 = Shopcart_Page_Name;
            } else if (i2 == 9) {
                str = Home_SimilarGoodsAccess;
                str2 = Home_PageId;
                str3 = "JDHomeFragment";
            } else if (i2 != 18) {
                str5 = null;
                str6 = null;
                str4 = null;
                JDMtaUtils.sendCommonData(activity, str5, recommendDetails.sourceValue, "", str6, "", "", "", str4);
            }
            str4 = str2;
            str5 = str;
            str6 = str3;
            JDMtaUtils.sendCommonData(activity, str5, recommendDetails.sourceValue, "", str6, "", "", "", str4);
        }
        str = MyJD_SimilarGoodsAccess;
        str2 = MyJD_PageId;
        str3 = MyJD_Page_Name;
        str4 = str2;
        str5 = str;
        str6 = str3;
        JDMtaUtils.sendCommonData(activity, str5, recommendDetails.sourceValue, "", str6, "", "", "", str4);
    }

    public static void recommendQuestionnairClickMta(Context context, RecommendDna recommendDna) {
        if (recommendDna != null) {
            JDMtaUtils.sendCommonData(context, Home_RecomResearch, recommendDna.sourceValue, "", "JDHomeFragment", "", "", "", Home_PageId);
        }
    }

    public static void recommendTendencyClickMta(Context context, String str) {
        JDMtaUtils.sendCommonData(context, Home_ProductidFoot, str, "", "JDHomeFragment", "", "", "", Home_PageId);
    }

    public static void routerEnterMta(Activity activity, int i2, String str) {
        String str2;
        if (i2 != 0) {
            if (i2 == 1) {
                str2 = MyFollow_Page_Name;
            } else if (i2 == 2) {
                str2 = OrderFinish_Page_Name;
            } else if (i2 == 3) {
                str2 = OrderDetail_Page_Name;
            } else if (i2 == 4) {
                str2 = OrderFollow_Page_Name;
            } else if (i2 == 6) {
                str2 = Shopcart_Page_Name;
            } else if (i2 != 18) {
                str2 = i2 != 9 ? i2 != 10 ? null : OrderPurchase_PageName : "JDHomeFragment";
            }
            JDRouterMtaUtil.routerEnterMta(activity, str2 + "_Recommend", str);
        }
        str2 = MyJD_Page_Name;
        JDRouterMtaUtil.routerEnterMta(activity, str2 + "_Recommend", str);
    }

    public static void routerErrorMta(Activity activity, int i2, String str, int i3) {
        String str2;
        if (i2 != 0) {
            if (i2 == 1) {
                str2 = MyFollow_Page_Name;
            } else if (i2 == 2) {
                str2 = OrderFinish_Page_Name;
            } else if (i2 == 3) {
                str2 = OrderDetail_Page_Name;
            } else if (i2 == 4) {
                str2 = OrderFollow_Page_Name;
            } else if (i2 == 6) {
                str2 = Shopcart_Page_Name;
            } else if (i2 != 18) {
                str2 = i2 != 9 ? i2 != 10 ? null : OrderPurchase_PageName : "JDHomeFragment";
            }
            JDRouterMtaUtil.routerErrorMta(activity, str2 + "_Recommend", str + CartConstant.KEY_YB_INFO_LINK + Integer.toString(i3));
        }
        str2 = MyJD_Page_Name;
        JDRouterMtaUtil.routerErrorMta(activity, str2 + "_Recommend", str + CartConstant.KEY_YB_INFO_LINK + Integer.toString(i3));
    }

    public static void sendHomeChannelCardExpo(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(context, "Home_FloorIDExpo_Json", "", Home_PageId, "", "", str, "", "", "", null);
    }

    public static void shopNameClickMat(Activity activity, String str) {
        JDMtaUtils.onClickWithPageId(activity, Productdetail_ShopRecomTab, PdInfoRecommend_Page_Name, str, Productdetail_MainPage);
    }

    public static void videoPlayTimeExpoMat(Activity activity, String str, String str2, int i2) {
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        if (i2 == 6) {
            str3 = Shopcart_ProductVideoTime;
            str4 = Shopcart_PageId;
            str5 = Shopcart_Page_Name;
        } else if (i2 == 9) {
            str3 = Home_ProductVideoTime;
            str4 = Home_PageId;
            str5 = "JDHomeFragment";
        } else if (i2 != 24) {
            str8 = null;
            str6 = null;
            str7 = null;
            JDMtaUtils.sendExposureDataWithExt(activity, str8, "", str6, str7, str, str2, null);
        } else {
            str3 = Productdetail_ProductVideoTime;
            str4 = Productdetail_MainPage;
            str5 = PdInfoRecommend_Page_Name;
        }
        str6 = str4;
        str7 = str5;
        str8 = str3;
        JDMtaUtils.sendExposureDataWithExt(activity, str8, "", str6, str7, str, str2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x0122 A[Catch: Exception -> 0x01bc, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0133 A[Catch: Exception -> 0x01bc, TRY_ENTER, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0139 A[Catch: Exception -> 0x01bc, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0147 A[Catch: Exception -> 0x01bc, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0154 A[Catch: Exception -> 0x01bc, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0161 A[Catch: Exception -> 0x01bc, TryCatch #0 {Exception -> 0x01bc, blocks: (B:78:0x0115, B:80:0x0122, B:81:0x0129, B:84:0x0133, B:86:0x013e, B:88:0x0147, B:90:0x014d, B:92:0x0154, B:93:0x015c, B:95:0x0161, B:97:0x018e, B:98:0x01a7, B:85:0x0139), top: B:105:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x018c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void productClickMta(Context context, RecommendProduct recommendProduct, int i2, int i3) {
        String str;
        String str2;
        String str3;
        StringBuilder sb;
        String str4;
        String str5;
        String str6;
        String str7 = "JDHomeFragment";
        String str8 = Home_PageId;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str = MyFollow_RecommendProduct;
                    str8 = MyFollow_PageId;
                    str7 = MyFollow_Page_Name;
                } else if (i2 == 2) {
                    str = OrderFinish_Recommend_Product;
                    str8 = OrderFinish_PageId;
                    str7 = OrderFinish_Page_Name;
                } else if (i2 == 3) {
                    str = OrderDetail_ProductRecommend;
                    str8 = OrderDetail_PageId;
                    str7 = OrderDetail_Page_Name;
                } else if (i2 == 4) {
                    str = OrderFollow_ProductRecommend;
                    str8 = OrderFollow_PageId;
                    str7 = OrderFollow_Page_Name;
                } else if (i2 == 6) {
                    str = "Shopcart_GuessYouLike";
                    str8 = Shopcart_PageId;
                    str7 = Shopcart_Page_Name;
                } else if (i2 == 13) {
                    str = OrderSuccess_Productid;
                    str8 = OrderCenter_ReceiveSuccess_Pageid;
                    str7 = OrderSuccess_PageName;
                } else if (i2 != 15) {
                    if (i2 == 24) {
                        str = Productdetail_ProductRecomTab;
                    } else if (i2 == 51) {
                        str = OrderCancelFinish_recproduct;
                        str8 = OrderCenter_CancelFinish_Page_Id;
                        str7 = OrderCenter_CancelFinish_Page_Name;
                    } else if (i2 == 10024) {
                        str = ProductdetailFeeds_product;
                        str2 = ProductdetailFeeds_MainPage;
                        str7 = PdInfoRecommend_Page_Name;
                        str3 = str;
                        HashMap hashMap = new HashMap();
                        if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                        }
                        if (TextUtils.isEmpty(recommendProduct.abt)) {
                        }
                        sb = new StringBuilder();
                        str4 = recommendProduct.sourceValue;
                        if (str4 != null) {
                        }
                        if (i2 != 9) {
                        }
                    } else if (i2 == 10036) {
                        str = Productdetail_RecommendToastProductClick;
                    } else if (i2 == 9) {
                        str = (recommendProduct == null || recommendProduct.isBackUp != 1) ? Home_Productid : Home_Productid_Backup;
                    } else if (i2 == 10) {
                        str = OrderPurchase_ProductRecommend;
                        str8 = OrderPurchase_Pageid;
                        str7 = OrderPurchase_PageName;
                    } else if (i2 == 17) {
                        str = PhoneChargeOrder_GuessYouLike;
                        str8 = PhoneChargeOrder_PageId;
                        str7 = PhoneChargeOrder_PageName;
                    } else if (i2 != 18) {
                        if (i2 != 48) {
                            if (i2 != 49) {
                                if (i2 == 63) {
                                    str5 = Card_Member_Nearbysale;
                                } else if (i2 != 64) {
                                    switch (i2) {
                                        case 27:
                                            str6 = OrderList_AllProductRecom;
                                            str3 = str6;
                                            str7 = "MyOrderListActivity";
                                            str2 = OrderCenter_List;
                                            break;
                                        case 28:
                                            str6 = OrderList_UnpaidProductRecom;
                                            str3 = str6;
                                            str7 = "MyOrderListActivity";
                                            str2 = OrderCenter_List;
                                            break;
                                        case 29:
                                            str6 = OrderList_DispatchedProductRecom;
                                            str3 = str6;
                                            str7 = "MyOrderListActivity";
                                            str2 = OrderCenter_List;
                                            break;
                                        case 30:
                                            str6 = OrderList_FinishedProductRecom;
                                            str3 = str6;
                                            str7 = "MyOrderListActivity";
                                            str2 = OrderCenter_List;
                                            break;
                                        case 31:
                                            str6 = OrderList_CanceledProductRecom;
                                            str3 = str6;
                                            str7 = "MyOrderListActivity";
                                            str2 = OrderCenter_List;
                                            break;
                                        default:
                                            switch (i2) {
                                                case 34:
                                                    str = MessageCenter_Productid;
                                                    str8 = MessageCenter_PageId;
                                                    str7 = MessageCenter_Page_Name;
                                                    break;
                                                case 35:
                                                    break;
                                                case 36:
                                                    str = Home_AggregationAccess;
                                                    break;
                                                case 37:
                                                    str = RecomDetail_SceneTagsProduct;
                                                    str8 = RecomDetail_SceneTags;
                                                    str7 = Scene_Detail_Page_Name;
                                                    break;
                                                default:
                                                    str7 = null;
                                                    str3 = null;
                                                    str2 = null;
                                                    break;
                                            }
                                    }
                                    HashMap hashMap2 = new HashMap();
                                    if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                                        hashMap2.put("extension_id", recommendProduct.extension_id);
                                    }
                                    if (TextUtils.isEmpty(recommendProduct.abt)) {
                                        hashMap2.put(JDMtaUtils.ABTKEY, DYConstants.DY_NULL_STR);
                                    } else {
                                        hashMap2.put(JDMtaUtils.ABTKEY, recommendProduct.abt);
                                    }
                                    sb = new StringBuilder();
                                    str4 = recommendProduct.sourceValue;
                                    if (str4 != null && str4.length() > 0) {
                                        StringBuilder sb2 = new StringBuilder(str4);
                                        if (i2 == 9) {
                                            sb2.insert(1, (CharSequence) new StringBuilder());
                                        }
                                        sb.append((CharSequence) sb2);
                                    }
                                    if (i2 != 9) {
                                        if (i2 == 10036) {
                                            JDMtaUtils.sendClickDataWithExt(context, str3, "", "", str2, str7, recommendProduct.getMainSku(), "", recommendProduct.sourceValue, null);
                                            return;
                                        } else {
                                            JDMtaUtils.sendCommonDataWithExt(context, str3, sb.toString(), "", str7, "", "", "", str2, hashMap2);
                                            return;
                                        }
                                    }
                                    JDMtaUtils.sendCommonDataWithExt(context, str3, sb.toString(), "", str7, i3 + CartConstant.KEY_YB_INFO_LINK + recommendProduct.isBackUp, "", "", str2, hashMap2);
                                    return;
                                } else {
                                    str5 = Card_Member_Store_Service;
                                }
                                str3 = str5;
                                str7 = Card_member;
                                str2 = str7;
                                HashMap hashMap22 = new HashMap();
                                if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                                }
                                if (TextUtils.isEmpty(recommendProduct.abt)) {
                                }
                                sb = new StringBuilder();
                                str4 = recommendProduct.sourceValue;
                                if (str4 != null) {
                                    StringBuilder sb22 = new StringBuilder(str4);
                                    if (i2 == 9) {
                                    }
                                    sb.append((CharSequence) sb22);
                                }
                                if (i2 != 9) {
                                }
                            } else {
                                str = ProductArchive_Product;
                            }
                        }
                        str7 = "ProductDetailActivity";
                        str3 = Productdetail_AccessoriesProduct;
                        str2 = Productdetail_MainPage;
                        HashMap hashMap222 = new HashMap();
                        if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                        }
                        if (TextUtils.isEmpty(recommendProduct.abt)) {
                        }
                        sb = new StringBuilder();
                        str4 = recommendProduct.sourceValue;
                        if (str4 != null) {
                        }
                        if (i2 != 9) {
                        }
                    }
                    str7 = PdInfoRecommend_Page_Name;
                    str2 = Productdetail_MainPage;
                    str3 = str;
                    HashMap hashMap2222 = new HashMap();
                    if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                    }
                    if (TextUtils.isEmpty(recommendProduct.abt)) {
                    }
                    sb = new StringBuilder();
                    str4 = recommendProduct.sourceValue;
                    if (str4 != null) {
                    }
                    if (i2 != 9) {
                    }
                } else {
                    str = MyHistory_GuessYouLike;
                    str8 = MyHistory_Pageid;
                    str7 = MyHistory_PageName;
                }
                str2 = str8;
                str3 = str;
                HashMap hashMap22222 = new HashMap();
                if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
                }
                if (TextUtils.isEmpty(recommendProduct.abt)) {
                }
                sb = new StringBuilder();
                str4 = recommendProduct.sourceValue;
                if (str4 != null) {
                }
                if (i2 != 9) {
                }
            }
            HashMap hashMap222222 = new HashMap();
            if (!TextUtils.isEmpty(recommendProduct.extension_id)) {
            }
            if (TextUtils.isEmpty(recommendProduct.abt)) {
            }
            sb = new StringBuilder();
            str4 = recommendProduct.sourceValue;
            if (str4 != null) {
            }
            if (i2 != 9) {
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return;
            }
            return;
        }
        str = MyJD_GuessYouLike;
        str8 = MyJD_PageId;
        str7 = MyJD_Page_Name;
        str2 = str8;
        str3 = str;
    }

    public static void productAddCarMta(Activity activity, RecommendProduct recommendProduct, String str) {
        JDMtaUtils.sendCommonData(activity, str, recommendProduct.cartSourceValue, "", Shopcart_Page_Name, "", "", "", Shopcart_PageId);
    }

    public static String dealRecommendProductExpoData(String str) {
        return dealRecommendProductExpoData(str, false);
    }
}
