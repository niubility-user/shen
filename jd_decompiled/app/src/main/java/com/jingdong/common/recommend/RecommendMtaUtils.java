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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void aggregationClickMtaRealize(android.content.Context r14, java.lang.String r15, int r16, java.lang.String r17) {
        /*
            r0 = r16
            java.lang.String r1 = "PdInfoRecommendFragment"
            java.lang.String r2 = "Productdetail_MainPage"
            r3 = 0
            if (r0 == 0) goto L53
            r4 = 6
            if (r0 == r4) goto L4c
            r4 = 9
            if (r0 == r4) goto L45
            r4 = 18
            if (r0 == r4) goto L53
            r4 = 24
            if (r0 == r4) goto L42
            r4 = 35
            if (r0 == r4) goto L3d
            r2 = 10024(0x2728, float:1.4047E-41)
            if (r0 == r2) goto L38
            r1 = 3
            if (r0 == r1) goto L31
            r1 = 4
            if (r0 == r1) goto L2a
            r5 = r3
            r8 = r5
            r12 = r8
            goto L5c
        L2a:
            java.lang.String r3 = "OrderFollow_AggregationAccess"
            java.lang.String r2 = "OrderCenter_Follow"
            java.lang.String r1 = "LogisticsOrderDetail"
            goto L59
        L31:
            java.lang.String r3 = "OrderDetail_AggregationAccess"
            java.lang.String r2 = "OrderCenter_Detail"
            java.lang.String r1 = "OrderDetailActivity"
            goto L59
        L38:
            java.lang.String r3 = "ProductdetailFeeds_aggregation"
            java.lang.String r2 = "ProductdetailFeeds_MainPage"
            goto L59
        L3d:
            java.lang.String r3 = "Productdetail_Aggregation"
            java.lang.String r1 = "ProductDetailActivity"
            goto L59
        L42:
            java.lang.String r3 = "Productdetail_AggregationAccess"
            goto L59
        L45:
            java.lang.String r3 = "Home_AggregationAccess"
            java.lang.String r2 = "Home_Main"
            java.lang.String r1 = "JDHomeFragment"
            goto L59
        L4c:
            java.lang.String r3 = "Shopcart_AggregationAccess"
            java.lang.String r2 = "Shopcart_Main"
            java.lang.String r1 = "JDShoppingCartFragment"
            goto L59
        L53:
            java.lang.String r3 = "MyJD_AggregationAccess"
            java.lang.String r2 = "MyJD_Main"
            java.lang.String r1 = "JDPersonalFragment"
        L59:
            r8 = r1
            r12 = r2
            r5 = r3
        L5c:
            boolean r0 = android.text.TextUtils.isEmpty(r17)
            if (r0 == 0) goto L70
            java.lang.String r7 = ""
            java.lang.String r9 = ""
            java.lang.String r10 = ""
            java.lang.String r11 = ""
            r4 = r14
            r6 = r15
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonData(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            goto L89
        L70:
            java.util.HashMap r13 = new java.util.HashMap
            r13.<init>()
            java.lang.String r0 = "extension_id"
            r1 = r17
            r13.put(r0, r1)
            java.lang.String r7 = ""
            java.lang.String r9 = ""
            java.lang.String r10 = ""
            java.lang.String r11 = ""
            r4 = r14
            r6 = r15
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonDataWithExt(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.aggregationClickMtaRealize(android.content.Context, java.lang.String, int, java.lang.String):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void jumpGeneClickMta(android.app.Activity r12, com.jingdong.common.recommend.entity.RecommendDna r13, int r14, java.lang.String r15) {
        /*
            r0 = 0
            if (r14 == 0) goto L48
            r1 = 1
            if (r14 == r1) goto L41
            r1 = 2
            if (r14 == r1) goto L3a
            r1 = 3
            if (r14 == r1) goto L33
            r1 = 4
            if (r14 == r1) goto L2c
            r1 = 6
            if (r14 == r1) goto L25
            r1 = 9
            if (r14 == r1) goto L1e
            r1 = 18
            if (r14 == r1) goto L48
            r3 = r0
            r6 = r3
            r10 = r6
            goto L51
        L1e:
            java.lang.String r0 = "Home_ShoppingGene"
            java.lang.String r14 = "Home_Main"
            java.lang.String r1 = "JDHomeFragment"
            goto L4e
        L25:
            java.lang.String r0 = "Shopcart_Recommend_dna"
            java.lang.String r14 = "Shopcart_Main"
            java.lang.String r1 = "JDShoppingCartFragment"
            goto L4e
        L2c:
            java.lang.String r0 = "OrderFollow_Recommend_dna"
            java.lang.String r14 = "OrderCenter_Follow"
            java.lang.String r1 = "LogisticsOrderDetail"
            goto L4e
        L33:
            java.lang.String r0 = "OrderDetail_Recommend_dna"
            java.lang.String r14 = "OrderCenter_Detail"
            java.lang.String r1 = "OrderDetailActivity"
            goto L4e
        L3a:
            java.lang.String r0 = "OrderFinish_Recommend_dna"
            java.lang.String r14 = "Neworder_Success"
            java.lang.String r1 = "CompleteOrderActivity"
            goto L4e
        L41:
            java.lang.String r0 = "MyFollow_Recommenddna"
            java.lang.String r14 = "MyFollow_Main"
            java.lang.String r1 = "FavoListFragmentActivity"
            goto L4e
        L48:
            java.lang.String r0 = "MyJD_Recommend_dna"
            java.lang.String r14 = "MyJD_Main"
            java.lang.String r1 = "JDPersonalFragment"
        L4e:
            r10 = r14
            r3 = r0
            r6 = r1
        L51:
            boolean r14 = android.text.TextUtils.isEmpty(r15)     // Catch: java.lang.Exception -> L7f
            if (r14 == 0) goto L66
            java.lang.String r4 = r13.sourceValue     // Catch: java.lang.Exception -> L7f
            java.lang.String r5 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r2 = r12
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonData(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L7f
            goto L89
        L66:
            java.util.HashMap r11 = new java.util.HashMap     // Catch: java.lang.Exception -> L7f
            r11.<init>()     // Catch: java.lang.Exception -> L7f
            java.lang.String r14 = "extension_id"
            r11.put(r14, r15)     // Catch: java.lang.Exception -> L7f
            java.lang.String r4 = r13.sourceValue     // Catch: java.lang.Exception -> L7f
            java.lang.String r5 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r2 = r12
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonDataWithExt(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L7f
            goto L89
        L7f:
            r12 = move-exception
            boolean r13 = com.jingdong.sdk.oklog.OKLog.E
            if (r13 == 0) goto L89
            java.lang.String r13 = "RecommendMtaUtils"
            com.jingdong.sdk.oklog.OKLog.e(r13, r12)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.jumpGeneClickMta(android.app.Activity, com.jingdong.common.recommend.entity.RecommendDna, int, java.lang.String):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void lookSimilarClickMta(android.app.Activity r15, com.jingdong.common.recommend.entity.RecommendProduct r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.lookSimilarClickMta(android.app.Activity, com.jingdong.common.recommend.entity.RecommendProduct, int, int):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void newFloatLayerMta(android.app.Activity r20, int r21, int r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.newFloatLayerMta(android.app.Activity, int, int, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void productAddCarMta(android.app.Activity r18, com.jingdong.common.recommend.entity.RecommendProduct r19, int r20) {
        /*
            r0 = r19
            r1 = r20
            r2 = 6
            java.lang.String r3 = "PdInfoRecommendFragment"
            r4 = 10036(0x2734, float:1.4063E-41)
            r5 = 24
            java.lang.String r6 = "Productdetail_MainPage"
            r7 = 0
            if (r1 == r2) goto L57
            r2 = 10
            if (r1 == r2) goto L50
            r2 = 13
            if (r1 == r2) goto L49
            r2 = 15
            if (r1 == r2) goto L42
            if (r1 == r5) goto L3f
            r2 = 35
            if (r1 == r2) goto L3a
            r2 = 37
            if (r1 == r2) goto L33
            r2 = 48
            if (r1 == r2) goto L3a
            if (r1 == r4) goto L30
            r9 = r7
            r12 = r9
            r13 = r12
            goto L60
        L30:
            java.lang.String r7 = "Productdetail_RecommendToastAddtoCart"
            goto L5d
        L33:
            java.lang.String r7 = "RecomDetail_SceneTagsAddToCart"
            java.lang.String r6 = "RecomDetail_SceneTags"
            java.lang.String r3 = "RecommendSceneActivity"
            goto L5d
        L3a:
            java.lang.String r7 = "Productdetail_AccessoriesCartIcon"
            java.lang.String r3 = "ProductDetailActivity"
            goto L5d
        L3f:
            java.lang.String r7 = "Productdetail_rec4tab_addcart"
            goto L5d
        L42:
            java.lang.String r7 = "MyHistory_GuessYouLike_AddtoCart"
            java.lang.String r6 = "MyHistory_Main"
            java.lang.String r3 = "HistoryListActivity"
            goto L5d
        L49:
            java.lang.String r7 = "OrderSuccess_Recommend_ProductAddtoCart"
            java.lang.String r6 = "OrderCenter_ReceiveSuccess"
            java.lang.String r3 = "AffirmReceivingSuccessActivity"
            goto L5d
        L50:
            java.lang.String r7 = "OrderPurchase_AddShoppingCartRecommend"
            java.lang.String r6 = "OrderCenter_MyPurchase"
            java.lang.String r3 = "AlwaysBuyActivity"
            goto L5d
        L57:
            java.lang.String r7 = "Shopcart_GuessYouLike_AddtoCart"
            java.lang.String r6 = "Shopcart_Main"
            java.lang.String r3 = "JDShoppingCartFragment"
        L5d:
            r13 = r3
            r12 = r6
            r9 = r7
        L60:
            if (r1 != r4) goto L78
            java.lang.String r14 = r19.getMainSku()
            java.lang.String r0 = r0.sourceValue
            r17 = 0
            java.lang.String r10 = ""
            java.lang.String r11 = ""
            java.lang.String r15 = ""
            r8 = r18
            r16 = r0
            com.jingdong.jdsdk.mta.JDMtaUtils.sendClickDataWithExt(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            goto L91
        L78:
            if (r1 != r5) goto L7d
            java.lang.String r1 = r0.cartSourceValue
            goto L7f
        L7d:
            java.lang.String r1 = r0.sourceValue
        L7f:
            r10 = r1
            java.lang.String r0 = r0.cartSourceValue
            r17 = 0
            java.lang.String r11 = ""
            java.lang.String r14 = ""
            java.lang.String r15 = ""
            r8 = r18
            r16 = r0
            com.jingdong.jdsdk.mta.JDMtaUtils.sendClickDataWithExt(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.productAddCarMta(android.app.Activity, com.jingdong.common.recommend.entity.RecommendProduct, int):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void promotionClickMta(android.app.Activity r12, com.jingdong.common.recommend.entity.RecommendPromotion r13, int r14, java.lang.String r15) {
        /*
            r0 = 0
            if (r14 == 0) goto L48
            r1 = 1
            if (r14 == r1) goto L41
            r1 = 2
            if (r14 == r1) goto L3a
            r1 = 3
            if (r14 == r1) goto L33
            r1 = 4
            if (r14 == r1) goto L2c
            r1 = 6
            if (r14 == r1) goto L25
            r1 = 9
            if (r14 == r1) goto L1e
            r1 = 18
            if (r14 == r1) goto L48
            r3 = r0
            r6 = r3
            r10 = r6
            goto L51
        L1e:
            java.lang.String r0 = "Home_Url"
            java.lang.String r14 = "Home_Main"
            java.lang.String r1 = "JDHomeFragment"
            goto L4e
        L25:
            java.lang.String r0 = "Shopcart_Recommend_Activity"
            java.lang.String r14 = "Shopcart_Main"
            java.lang.String r1 = "JDShoppingCartFragment"
            goto L4e
        L2c:
            java.lang.String r0 = "OrderFollow_Url"
            java.lang.String r14 = "OrderCenter_Follow"
            java.lang.String r1 = "LogisticsOrderDetail"
            goto L4e
        L33:
            java.lang.String r0 = "OrderDetail_Url"
            java.lang.String r14 = "OrderCenter_Detail"
            java.lang.String r1 = "OrderDetailActivity"
            goto L4e
        L3a:
            java.lang.String r0 = "OrderFinish_Recommend_Activity"
            java.lang.String r14 = "Neworder_Success"
            java.lang.String r1 = "CompleteOrderActivity"
            goto L4e
        L41:
            java.lang.String r0 = "MyFollow_RecommendActivity"
            java.lang.String r14 = "MyFollow_Main"
            java.lang.String r1 = "FavoListFragmentActivity"
            goto L4e
        L48:
            java.lang.String r0 = "MyJD_Recommend_Activity"
            java.lang.String r14 = "MyJD_Main"
            java.lang.String r1 = "JDPersonalFragment"
        L4e:
            r10 = r14
            r3 = r0
            r6 = r1
        L51:
            boolean r14 = android.text.TextUtils.isEmpty(r15)     // Catch: java.lang.Exception -> L7f
            if (r14 == 0) goto L66
            java.lang.String r4 = r13.sourceValue     // Catch: java.lang.Exception -> L7f
            java.lang.String r5 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r2 = r12
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonData(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L7f
            goto L89
        L66:
            java.util.HashMap r11 = new java.util.HashMap     // Catch: java.lang.Exception -> L7f
            r11.<init>()     // Catch: java.lang.Exception -> L7f
            java.lang.String r14 = "extension_id"
            r11.put(r14, r15)     // Catch: java.lang.Exception -> L7f
            java.lang.String r4 = r13.sourceValue     // Catch: java.lang.Exception -> L7f
            java.lang.String r5 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r2 = r12
            com.jingdong.jdsdk.mta.JDMtaUtils.sendCommonDataWithExt(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L7f
            goto L89
        L7f:
            r12 = move-exception
            boolean r13 = com.jingdong.sdk.oklog.OKLog.E
            if (r13 == 0) goto L89
            java.lang.String r13 = "RecommendMtaUtils"
            com.jingdong.sdk.oklog.OKLog.e(r13, r12)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.promotionClickMta(android.app.Activity, com.jingdong.common.recommend.entity.RecommendPromotion, int, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033 A[Catch: Exception -> 0x0057, TRY_LEAVE, TryCatch #0 {Exception -> 0x0057, blocks: (B:16:0x002d, B:18:0x0033), top: B:21:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void realAggreatioExpo(int r13, android.content.Context r14, com.jingdong.common.recommend.entity.ExpoData r15) {
        /*
            if (r14 == 0) goto L57
            if (r15 != 0) goto L5
            goto L57
        L5:
            r0 = 6
            java.lang.String r1 = ""
            if (r13 == r0) goto L24
            r0 = 9
            if (r13 == r0) goto L1d
            r0 = 24
            if (r13 == r0) goto L16
            r3 = r1
            r5 = r3
            r6 = r5
            goto L2d
        L16:
            java.lang.String r1 = "Productdetail_AggregationAccessExpo_real"
            java.lang.String r13 = "Productdetail_MainPage"
            java.lang.String r0 = "ProductDetailActivity"
            goto L2a
        L1d:
            java.lang.String r1 = "Home_RecomResearchExpo"
            java.lang.String r13 = "Home_Main"
            java.lang.String r0 = "JDHomeFragment"
            goto L2a
        L24:
            java.lang.String r1 = "Shopcart_AggregationAccessExpo_real"
            java.lang.String r13 = "Shopcart_Main"
            java.lang.String r0 = "JDShoppingCartFragment"
        L2a:
            r5 = r13
            r6 = r0
            r3 = r1
        L2d:
            boolean r13 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L57
            if (r13 != 0) goto L57
            r15.getnowtime()     // Catch: java.lang.Exception -> L57
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch: java.lang.Exception -> L57
            java.lang.String r0 = r15.exposureSourceValue     // Catch: java.lang.Exception -> L57
            r13.<init>(r0)     // Catch: java.lang.Exception -> L57
            java.lang.String r0 = "skutime"
            java.lang.String r15 = r15.time     // Catch: java.lang.Exception -> L57
            r13.put(r0, r15)     // Catch: java.lang.Exception -> L57
            java.lang.String r4 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = r13.toString()     // Catch: java.lang.Exception -> L57
            java.lang.String r9 = ""
            java.lang.String r10 = ""
            java.lang.String r11 = ""
            r12 = 0
            r2 = r14
            com.jingdong.jdsdk.mta.JDMtaUtils.sendExposureDataWithExt(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L57
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.realAggreatioExpo(int, android.content.Context, com.jingdong.common.recommend.entity.ExpoData):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void productClickMta(android.content.Context r18, com.jingdong.common.recommend.entity.RecommendProduct r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendMtaUtils.productClickMta(android.content.Context, com.jingdong.common.recommend.entity.RecommendProduct, int, int):void");
    }

    public static void productAddCarMta(Activity activity, RecommendProduct recommendProduct, String str) {
        JDMtaUtils.sendCommonData(activity, str, recommendProduct.cartSourceValue, "", Shopcart_Page_Name, "", "", "", Shopcart_PageId);
    }

    public static String dealRecommendProductExpoData(String str) {
        return dealRecommendProductExpoData(str, false);
    }
}
