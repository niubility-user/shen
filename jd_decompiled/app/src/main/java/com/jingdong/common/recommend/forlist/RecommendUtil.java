package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecomPerformanceData;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendMaterialData;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendPromotion;
import com.jingdong.common.recommend.entity.RecommendShop;
import com.jingdong.common.recommend.entity.RecommendType;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.WareInfoReason;
import com.jingdong.common.recommend.ui.RecommendHeaderViewHolder;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import jpbury.t;

/* loaded from: classes6.dex */
public class RecommendUtil {
    public static final int FOOTER_END = 2;
    public static final int FOOTER_ERROR = 1;
    public static final int FOOTER_NODATA = 3;
    public static final int FOOTER_NORMAL = 0;
    public static boolean dotClick;
    private Activity activity;
    private String bgColor;
    private OnRecommendClickedListener clickedListener;
    private int currentFooterState;
    private String darkHeaderTitleUrl;
    private LinearLayout endLayout;
    private LinearLayout errorLayout;
    private ExpoDataStore expoDataAggreation;
    private ExpoDataStore expoDataCart;
    private ExpoDataStore expoDataDetails;
    private ExpoDataStore expoDataPromotion;
    private ExpoDataStore expoDataQuesitionNair;
    private ExpoDataStore expoDataShop;
    private ExpoDataStore expoDataStore;
    private ExpoDataStore expoDataStoreRecycling;
    private String feedBackImmediateEffect;
    public int floorMinWareNum;
    private TextView footerTestinBtn;
    private int from;
    private String headerTitleUrl;
    private IRecommendHomePageTestPlanLoader homePageTestPlanLoader;
    private ArrayList<RecommendItem> interActiveRecommendList;
    public boolean isAdAllPositionExpo;
    public boolean isAdRealExpo;
    private boolean isSingleRow;
    private boolean isVisible;
    private JDDisplayImageOptions jdDisplayImageOptions;
    private LinearLayout loadingLayout;
    private ArrayList<RecommendItem> mRecommendItemList;
    private List<WareInfoReason> mWareInfoReasons;
    private RecommendProductManager manager;
    private SimpleDraweeView onlineImg;
    private ExpoDataStore performanceDataStore;
    private ExpoDataStore realExpoDataAggreation;
    private ExpoDataStore realExpoDataStore;
    public HashSet<String> realExpoHashSet;
    private RecommendConfig recommendConfig;
    private LinearLayout recommendFooterView;
    private View recommendHeaderView;
    private RecommendOtherData recommendOtherData;
    public boolean serviceDarkModeEnable;
    public boolean serviceElderModeEnable;
    private TextView testin;
    private LinearLayout testinLayout;

    /* loaded from: classes6.dex */
    public interface IRecommendHomePageTestPlanLoader {
        boolean checkHomePageTestPlanIsA();

        String getHomePageTestPlan();
    }

    /* loaded from: classes6.dex */
    public interface OnRecommendClickedListener {
        void onAddCarClick(RecommendProduct recommendProduct);

        void onAddCarClick(RecommendProduct recommendProduct, String str);

        void onAggregationClick(RecommendDna recommendDna);

        void onChannelUnderJump(String str, String str2, String str3);

        void onDotMoreMta(int i2, String str);

        void onEnterPromotionClick(RecommendPromotion recommendPromotion);

        void onEnterShopClick(RecommendShop recommendShop);

        void onGeneClick(RecommendDna recommendDna);

        void onJumpPublicTest(String str);

        void onNoRecommendClick(RecommendProduct recommendProduct, int i2, String str, ArrayList<Integer> arrayList);

        void onNoRecommendMaterialClick(RecommendMaterialData recommendMaterialData, int i2, String str, ArrayList<Integer> arrayList, String str2, String str3);

        void onProductClick(RecommendProduct recommendProduct);

        void onProductClick(RecommendProduct recommendProduct, RecommendItem recommendItem);

        void onProductClick(RecommendProduct recommendProduct, String str);

        void onRecommendChannelJump(RecommendDna recommendDna);

        void onRecommendDetalis(RecommendDetails recommendDetails);

        void onRecommendJump(String str, String str2);

        void onRecommendMoneyExpo(String str);

        void onRecommendReasonMta(String str);

        void onRecommendVideoClick(RecommendVideo recommendVideo);

        void onRefresh();

        void onShowFeedbackUi(RecommendProduct recommendProduct, int i2);

        void onSimilarClick(RecommendProduct recommendProduct);
    }

    public RecommendUtil() {
        this.mWareInfoReasons = new ArrayList();
        this.isVisible = true;
        this.currentFooterState = 0;
        this.feedBackImmediateEffect = "0";
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.interActiveRecommendList = null;
        this.serviceDarkModeEnable = false;
        this.serviceElderModeEnable = false;
        this.isAdAllPositionExpo = false;
        this.floorMinWareNum = 0;
    }

    private void callRefreshListData() {
        int i2 = this.from;
        if (i2 == 9 || i2 == 36) {
            return;
        }
        this.manager.onRefreshListData();
    }

    private void createExpoDataStore(int i2) {
        String str = RecommendMtaUtils.Card_member;
        String str2 = "";
        if (i2 == 0) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_RecomSku_Expo, RecommendMtaUtils.MyJD_PageId);
            this.realExpoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_ProductReal_Expo, RecommendMtaUtils.MyJD_PageId);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_AggregationAccessExpo, RecommendMtaUtils.MyJD_PageId);
            this.realExpoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_AggregationReal_Expo, RecommendMtaUtils.MyJD_PageId);
            this.expoDataDetails = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_SimilarGoodsAccessExpo, RecommendMtaUtils.MyJD_PageId);
            this.expoDataShop = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_ShopAccessExpo, RecommendMtaUtils.MyJD_PageId);
        } else if (i2 == 1) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyFollow_RecomSku_Expo, RecommendMtaUtils.MyFollow_PageId);
        } else if (i2 == 2) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderFinish_Recommend_SkuExpo, RecommendMtaUtils.OrderFinish_PageId);
        } else if (i2 == 3) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Orderdetail_RecomSku_Expo, RecommendMtaUtils.OrderDetail_PageId);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderDetail_AggregationAccessExpo, RecommendMtaUtils.OrderDetail_PageId);
        } else if (i2 == 4) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderFollow_RecomSku_Expo, RecommendMtaUtils.OrderFollow_PageId);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderFollow_AggregationAccessExpo, RecommendMtaUtils.OrderFollow_PageId);
        } else if (i2 == 6) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Shopcart_RecomSku_Expo, RecommendMtaUtils.Shopcart_PageId);
            this.expoDataCart = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Shopcart_Compare_SkuExpo, RecommendMtaUtils.Shopcart_PageId);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Shopcart_AggregationAccessExpo, RecommendMtaUtils.Shopcart_PageId);
            this.expoDataDetails = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Shopcart_SimilarGoodsAccessExpo, RecommendMtaUtils.Shopcart_PageId);
        } else if (i2 == 13) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderSuccess_Recommend_ProductExpo, RecommendMtaUtils.OrderCenter_ReceiveSuccess_Pageid);
        } else if (i2 == 15) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyHistory_RecomSkuExpo, RecommendMtaUtils.MyHistory_Pageid);
        } else if (i2 == 24) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_SkuExpoRecomTab, RecommendMtaUtils.Productdetail_MainPage);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_AggregationAccessExpo, RecommendMtaUtils.Productdetail_MainPage);
        } else if (i2 == 51) {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderCancelFinish_recproductexpo, RecommendMtaUtils.OrderCenter_CancelFinish_Page_Id);
        } else if (i2 != 10024) {
            if (i2 != 10035) {
                if (i2 == 9) {
                    this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_RecomSku_Expo, RecommendMtaUtils.Home_PageId);
                    this.realExpoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_ProductReal_Expo, RecommendMtaUtils.Home_PageId);
                    this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_RecomAggregationExpo, RecommendMtaUtils.Home_PageId);
                    this.realExpoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_AggregationReal_Expo, RecommendMtaUtils.Home_PageId);
                    this.expoDataDetails = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_SimilarGoodsAccessExpo, RecommendMtaUtils.Home_PageId);
                    this.expoDataShop = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_ShopAccessExpo, RecommendMtaUtils.Home_PageId);
                    this.expoDataQuesitionNair = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_RecomResearchExpo, RecommendMtaUtils.Home_PageId);
                    this.expoDataPromotion = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_Promotion_Product_Expo, RecommendMtaUtils.Home_PageId);
                    this.performanceDataStore = ExpoDataStore.createExpoDataStpre("Home_RecomPerformance", RecommendMtaUtils.Home_PageId);
                } else if (i2 == 10) {
                    this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderPurchase_ProductRecommendExpo, RecommendMtaUtils.OrderPurchase_Pageid);
                    this.expoDataCart = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderCenterMyPurchase_FloorProductExpo, RecommendMtaUtils.OrderPurchase_Pageid);
                } else if (i2 == 17) {
                    this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.PhoneChargeOrder_RecomSkuExpo, RecommendMtaUtils.PhoneChargeOrder_PageId);
                } else if (i2 == 18) {
                    this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_RecomSku_Expo, RecommendMtaUtils.MyJD_PageId);
                    this.realExpoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_ProductReal_Expo, RecommendMtaUtils.MyJD_PageId);
                    this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_AggregationAccessExpo, RecommendMtaUtils.MyJD_PageId);
                    this.realExpoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MyJD_AggregationReal_Expo, RecommendMtaUtils.MyJD_PageId);
                } else {
                    if (i2 != 48) {
                        if (i2 == 49) {
                            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.ProductArchive_ProductExpo, RecommendMtaUtils.Productdetail_MainPage);
                        } else {
                            if (i2 == 63) {
                                str2 = RecommendMtaUtils.Card_Member_Nearbysale_Expo;
                            } else if (i2 != 64) {
                                switch (i2) {
                                    case 27:
                                        this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderList_AllRecomSkuExpo, RecommendMtaUtils.OrderCenter_List);
                                        break;
                                    case 28:
                                        this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderList_UnpaidRecomSkuExpo, RecommendMtaUtils.OrderCenter_List);
                                        break;
                                    case 29:
                                        this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderList_DispatchedRecomSkuExpo, RecommendMtaUtils.OrderCenter_List);
                                        break;
                                    case 30:
                                        this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderList_FinishedRecomSkuExpo, RecommendMtaUtils.OrderCenter_List);
                                        break;
                                    case 31:
                                        this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.OrderList_CanceledRecomSkuExpo, RecommendMtaUtils.OrderCenter_List);
                                        break;
                                    default:
                                        switch (i2) {
                                            case 34:
                                                this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.MessageCenter_RecomSkuExpo, RecommendMtaUtils.MessageCenter_PageId);
                                                break;
                                            case 36:
                                                this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Home_Personalization_PromotionTabExpo, RecommendMtaUtils.Home_PageId);
                                                break;
                                            case 37:
                                                this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.RecomDetail_SceneTagsProductExpo, RecommendMtaUtils.RecomDetail_SceneTags);
                                                break;
                                        }
                                }
                            } else {
                                str2 = RecommendMtaUtils.Card_Member_Store_Service_Expo;
                            }
                            if (!TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                            }
                            this.expoDataStore = ExpoDataStore.createExpoDataStpre(str2, str);
                            return;
                        }
                    }
                    this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_AccessoriesProductExpo, RecommendMtaUtils.Productdetail_MainPage);
                }
            }
            this.expoDataStoreRecycling = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_AddcartRecyclingExpo, RecommendMtaUtils.Productdetail_MainPage);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_Aggregationexpo, RecommendMtaUtils.Productdetail_MainPage);
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.Productdetail_AccessoriesProductExpo, RecommendMtaUtils.Productdetail_MainPage);
        } else {
            this.expoDataStore = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.ProductdetailFeeds_productexpo, RecommendMtaUtils.ProductdetailFeeds_MainPage);
            this.expoDataAggreation = ExpoDataStore.createExpoDataStpre(RecommendMtaUtils.ProductdetailFeeds_aggregationexpo, RecommendMtaUtils.ProductdetailFeeds_MainPage);
        }
        str = "";
        if (TextUtils.isEmpty(str2)) {
        }
    }

    private static String formatMillis(long j2) {
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf(d / 1000.0d));
    }

    private static String getCurrentMicrosecond() {
        return formatMillis(System.currentTimeMillis());
    }

    private boolean isFooterTransparent() {
        int i2;
        return RecommendUtils.isWaterFull(this.from) || (i2 = this.from) == 24 || i2 == 10024;
    }

    private boolean isNotRecommend(int i2) {
        if (i2 != 0 && i2 != 26 && i2 != 1022 && i2 != 9302 && i2 != 11440 && i2 != 20000 && i2 != 15 && i2 != 16 && i2 != 23 && i2 != 24 && i2 != 36 && i2 != 37 && i2 != 1018 && i2 != 1019) {
            switch (i2) {
                case 18:
                case 19:
                case 20:
                    break;
                default:
                    switch (i2) {
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                            break;
                        default:
                            switch (i2) {
                                case 999:
                                case 1000:
                                case 1001:
                                case 1002:
                                case 1003:
                                case 1004:
                                case 1005:
                                case 1006:
                                case 1007:
                                case 1008:
                                case 1009:
                                case 1010:
                                case 1011:
                                case 1012:
                                case 1013:
                                case 1014:
                                case 1015:
                                    break;
                                default:
                                    switch (i2) {
                                        case 2001:
                                        case 2002:
                                        case 2003:
                                        case 2004:
                                        case 2005:
                                        case 2006:
                                        case 2007:
                                            break;
                                        default:
                                            switch (i2) {
                                                case 10001:
                                                case 10002:
                                                case 10003:
                                                    break;
                                                default:
                                                    return !RecommendType.recom_dynamic_types.contains(Integer.valueOf(i2));
                                            }
                                    }
                            }
                    }
            }
        }
        return false;
    }

    public void onJumpPublicTest(String str) {
        OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            onRecommendClickedListener.onJumpPublicTest(str);
        }
    }

    public static void reportException(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", RecommendConstant.RECOMMEND_FUNCTION_ID);
        hashMap.put("errCode", "945");
        hashMap.put(PerformanceManager.ERR_TYPE, "2");
        hashMap.put("occurTime", getCurrentMicrosecond());
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put(t.f20145j, str2);
        }
        hashMap.put("errMsg", str);
        ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
    }

    protected boolean checkHomePageTestPlanIsA() {
        IRecommendHomePageTestPlanLoader iRecommendHomePageTestPlanLoader = this.homePageTestPlanLoader;
        if (iRecommendHomePageTestPlanLoader != null) {
            return iRecommendHomePageTestPlanLoader.checkHomePageTestPlanIsA();
        }
        return true;
    }

    public void clearRecommendData() {
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList != null && arrayList.size() > 0) {
            this.mRecommendItemList.clear();
        }
        List<WareInfoReason> list = this.mWareInfoReasons;
        if (list != null && list.size() > 0) {
            this.mWareInfoReasons.clear();
        }
        HashSet<String> hashSet = this.realExpoHashSet;
        if (hashSet != null) {
            hashSet.clear();
        }
    }

    public String get924DisplayStyle() {
        RecommendOtherData recommendOtherData = this.recommendOtherData;
        return recommendOtherData != null ? recommendOtherData.get924UIStrategy() : "A";
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public OnRecommendClickedListener getClickedListener() {
        return this.clickedListener;
    }

    public int getDarkBgColor() {
        RecommendConfig recommendConfig = this.recommendConfig;
        if (recommendConfig != null) {
            return recommendConfig.getDarkBgColor();
        }
        return 0;
    }

    public String getDisplayMode() {
        RecommendOtherData recommendOtherData = this.recommendOtherData;
        return recommendOtherData != null ? recommendOtherData.getDisplayMode() : "0";
    }

    public ExpoDataStore getExpoDataStore() {
        return this.expoDataStore;
    }

    public String getFeedBackImmediateEffect() {
        return this.feedBackImmediateEffect;
    }

    public ArrayList<RecommendItem> getInterActiveRecommendList() {
        return this.interActiveRecommendList;
    }

    public boolean getIsSingleRow() {
        return this.isSingleRow;
    }

    public RecommendItem getItem(int i2) {
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList == null || i2 >= arrayList.size()) {
            return null;
        }
        return this.mRecommendItemList.get(i2);
    }

    public int getNewRecommendItemCount() {
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getNewRecommendItemType(int i2, int i3) {
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList == null || arrayList.size() <= i2) {
            return 0;
        }
        int i4 = this.mRecommendItemList.get(i2).type;
        if ("B".equals(getUIStrategy()) && i4 == 33) {
            this.mRecommendItemList.get(i2).type = 9302;
        }
        return i3 + this.mRecommendItemList.get(i2).type;
    }

    public int getNewRecommendTypes() {
        return 12;
    }

    public RecomPerformanceData getPerformanceData(int i2) {
        ExpoDataStore expoDataStore = this.performanceDataStore;
        if (expoDataStore != null) {
            return expoDataStore.getPerformanceItem(i2);
        }
        return null;
    }

    public int getRecommendBuyaSeeCount() {
        List<WareInfoReason> list = this.mWareInfoReasons;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public RecommendConfig getRecommendConfig() {
        return this.recommendConfig;
    }

    public int getRecommendItemCount() {
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList != null) {
            return (arrayList.size() / 2) + (this.mRecommendItemList.size() % 2);
        }
        return 0;
    }

    public ArrayList<RecommendItem> getRecommendItemList() {
        return this.mRecommendItemList;
    }

    public int getRecommendItemType(int i2, int i3) {
        int i4;
        ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
        if (arrayList == null || arrayList.size() <= (i4 = i2 * 2)) {
            return 0;
        }
        int i5 = i4 + 1;
        if (this.mRecommendItemList.size() > i5) {
            return i3 + (this.mRecommendItemList.get(i4).type * 12) + this.mRecommendItemList.get(i5).type;
        }
        return 0 + i3 + (this.mRecommendItemList.get(i4).type * 12);
    }

    public RecommendOtherData getRecommendOtherData() {
        return this.recommendOtherData;
    }

    public int getRecommendTypes() {
        return 144;
    }

    public String getUIStrategy() {
        RecommendOtherData recommendOtherData = this.recommendOtherData;
        return recommendOtherData != null ? recommendOtherData.getUIStrategy() : "A";
    }

    public boolean isDarkEnable() {
        RecommendConfig recommendConfig = this.recommendConfig;
        return recommendConfig != null && recommendConfig.isDarkEnable();
    }

    public boolean isHasRecommend() {
        if (this.isVisible) {
            ArrayList<RecommendItem> arrayList = this.mRecommendItemList;
            if (arrayList == null || arrayList.size() <= 0) {
                List<WareInfoReason> list = this.mWareInfoReasons;
                return list != null && list.size() > 0;
            }
            return true;
        }
        return false;
    }

    public boolean isNewRecommendItemType(int i2, int i3) {
        int i4 = i2 - i3;
        return i4 >= 0 && !isNotRecommend(i4);
    }

    public boolean isRecommendItemType(int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 >= 0) {
            boolean z = !isNotRecommend(i4 / 12);
            if (isNotRecommend(i4 % 12)) {
                return false;
            }
            return z;
        }
        return false;
    }

    public boolean isSixBuyaSee() {
        ArrayList<RecommendProduct> arrayList;
        List<WareInfoReason> list = this.mWareInfoReasons;
        return list != null && list.size() > 0 && (arrayList = this.mWareInfoReasons.get(0).wareInfoList) != null && arrayList.size() >= 6;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public boolean isWaterFallStrategy() {
        int i2 = this.from;
        return "B".equals((i2 == 24 || i2 == 10024) ? "B" : this.manager.getHomePageTestPlan());
    }

    public void onBindNewRecommendViewHolder(RecyclerView.ViewHolder viewHolder, int i2, BaseActivity baseActivity) {
        if (!(viewHolder instanceof RecommendViewHolder)) {
            return;
        }
        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
        recommendViewHolder.setExpoDataShop(this.expoDataShop);
        recommendViewHolder.setRealExpoDataStore(this.realExpoDataStore, this.realExpoDataAggreation);
        try {
            if (!this.isAdAllPositionExpo && i2 > 6) {
                recommendViewHolder.setIsAdRealExpo(false);
                recommendViewHolder.setmRecommendConfig(this.recommendConfig);
                ((RecommendViewHolder) viewHolder).bindNewRecommendViewHolder(this.mRecommendItemList, this.recommendOtherData, i2, this.expoDataStore, this.from, this.manager.tabId, isWaterFallStrategy(), this.jdDisplayImageOptions, this.expoDataAggreation, this.expoDataDetails, this.expoDataQuesitionNair, this.expoDataPromotion, this.expoDataStoreRecycling);
                return;
            }
            ((RecommendViewHolder) viewHolder).bindNewRecommendViewHolder(this.mRecommendItemList, this.recommendOtherData, i2, this.expoDataStore, this.from, this.manager.tabId, isWaterFallStrategy(), this.jdDisplayImageOptions, this.expoDataAggreation, this.expoDataDetails, this.expoDataQuesitionNair, this.expoDataPromotion, this.expoDataStoreRecycling);
            return;
        } catch (Exception e2) {
            JdCrashReport.postCaughtException(e2);
            return;
        }
        recommendViewHolder.setIsAdRealExpo(this.isAdRealExpo);
        recommendViewHolder.setmRecommendConfig(this.recommendConfig);
    }

    public void onBindRecommendBuyaSeeViewHolder(RecyclerView.ViewHolder viewHolder, int i2, BaseActivity baseActivity) {
        if (isSixBuyaSee()) {
            ((RecommendCartFloorViewHolder) viewHolder).bindRecommendViewHolder(this.mWareInfoReasons, i2, this.from, this.jdDisplayImageOptions, this.expoDataCart);
        } else {
            ((RecommendBuyaSeeViewHolder) viewHolder).bindRecommendViewHolder(this.mWareInfoReasons, i2, this.from, this.jdDisplayImageOptions, this.expoDataCart);
        }
    }

    public void onBindRecommendFooterViewHolder() {
        if (this.recommendFooterView != null) {
            RecommendConfig recommendConfig = this.recommendConfig;
            if (recommendConfig != null && (recommendConfig.isDarkEnable() || this.recommendConfig.isDarkTheme())) {
                this.recommendFooterView.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
                return;
            }
            int i2 = this.from;
            if (i2 != 24 && i2 != 10024 && i2 != 37) {
                this.recommendFooterView.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F6F6F6));
            } else {
                this.recommendFooterView.setBackgroundColor(0);
            }
        }
    }

    public void onBindRecommendHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RecommendHeaderViewHolder) {
            RecommendHeaderViewHolder recommendHeaderViewHolder = (RecommendHeaderViewHolder) viewHolder;
            String str = this.headerTitleUrl;
            String str2 = this.darkHeaderTitleUrl;
            RecommendOtherData recommendOtherData = this.recommendOtherData;
            recommendHeaderViewHolder.onBindRecommendHeaderViewHolder(str, str2, recommendOtherData != null ? recommendOtherData.getPublicTest() : "", getRecommendConfig());
            recommendHeaderViewHolder.setOnTestClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.3
                {
                    RecommendUtil.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.this.onJumpPublicTest("1");
                }
            });
        }
    }

    public void onBindRecommendViewHolder(RecyclerView.ViewHolder viewHolder, int i2, BaseActivity baseActivity) {
        if (viewHolder instanceof RecommendViewHolder) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
            recommendViewHolder.setExpoDataShop(this.expoDataShop);
            recommendViewHolder.setmRecommendConfig(this.recommendConfig);
            recommendViewHolder.bindRecommendViewHolder(this.mRecommendItemList, i2, this.expoDataStore, this.from, this.jdDisplayImageOptions, this.expoDataAggreation, this.expoDataDetails);
        }
    }

    public RecyclerView.ViewHolder onCreateNewRecommedHeaderViewHolder(ViewGroup viewGroup) {
        View inflate;
        if (OKLog.D) {
            OKLog.d("RecommendUtil", "onCreateRecommedHeaderViewHolder:");
        }
        if (viewGroup != null) {
            inflate = LayoutInflater.from(this.activity).inflate(R.layout.recommend_head, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(this.activity).inflate(R.layout.recommend_head, (ViewGroup) null);
        }
        return new RecommendHeaderViewHolder(inflate, this.from);
    }

    public RecyclerView.ViewHolder onCreateNewRecommedViewHolder(BaseActivity baseActivity, int i2, int i3) {
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(baseActivity, LayoutInflater.from(baseActivity).inflate(R.layout.new_recommend_item, (ViewGroup) null), i2 - i3, this.recommendOtherData, this.from, this.manager.tabId, this.realExpoHashSet);
        recommendViewHolder.setClickedListener(this.clickedListener);
        recommendViewHolder.setHomePageTestPlanLoader(this.homePageTestPlanLoader);
        return recommendViewHolder;
    }

    public RecyclerView.ViewHolder onCreateRecommedBuyaSeeViewHolder(BaseActivity baseActivity) {
        if (isSixBuyaSee()) {
            RecommendCartFloorViewHolder recommendCartFloorViewHolder = new RecommendCartFloorViewHolder(baseActivity, LayoutInflater.from(baseActivity).inflate(R.layout.recommend_cart_floor, (ViewGroup) null));
            recommendCartFloorViewHolder.setClickedListener(this.clickedListener);
            return recommendCartFloorViewHolder;
        }
        RecommendBuyaSeeViewHolder recommendBuyaSeeViewHolder = new RecommendBuyaSeeViewHolder(baseActivity, LayoutInflater.from(baseActivity).inflate(R.layout.recommend_buyasee, (ViewGroup) null));
        recommendBuyaSeeViewHolder.setClickedListener(this.clickedListener);
        return recommendBuyaSeeViewHolder;
    }

    public RecyclerView.ViewHolder onCreateRecommedFooterViewHolder(ViewGroup viewGroup) {
        if (OKLog.D) {
            OKLog.d("RecommendUtil", "onCreateRecommedFooterViewHolder:");
        }
        if (this.recommendFooterView == null) {
            if (viewGroup != null) {
                this.recommendFooterView = (LinearLayout) LayoutInflater.from(JdSdk.getInstance().getApplicationContext()).inflate(R.layout.new_recommend_footer, viewGroup, false);
            } else {
                this.recommendFooterView = (LinearLayout) LayoutInflater.from(JdSdk.getInstance().getApplicationContext()).inflate(R.layout.new_recommend_footer, (ViewGroup) null, false);
            }
            if (isFooterTransparent()) {
                this.recommendFooterView.setBackgroundResource(R.color.transparent);
            } else {
                this.recommendFooterView.setBackgroundResource(R.color.recommend_bg_color);
            }
            this.loadingLayout = (LinearLayout) this.recommendFooterView.findViewById(R.id.recommend_footer_loading_layout);
            TextView textView = new TextView(this.activity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            textView.setText("\u52a0\u8f7d\u4e2d...");
            textView.setTextColor(Color.parseColor("#848689"));
            layoutParams.leftMargin = DPIUtil.dip2px(4.0f);
            layoutParams.gravity = 16;
            this.loadingLayout.addView(textView, layoutParams);
            this.endLayout = (LinearLayout) this.recommendFooterView.findViewById(R.id.recommend_end_ll_layout);
            TextView textView2 = new TextView(this.activity);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            textView2.setText("\u6ca1\u6709\u66f4\u591a\u4e86~");
            textView2.setTextSize(11.0f);
            textView2.setTextColor(Color.parseColor("#BFBFBF"));
            layoutParams2.topMargin = DPIUtil.dip2px(10.0f);
            this.endLayout.addView(textView2, layoutParams2);
            this.testinLayout = (LinearLayout) this.recommendFooterView.findViewById(R.id.recommend_testin_ll_layout);
            this.footerTestinBtn = (TextView) this.recommendFooterView.findViewById(R.id.recommend_footer_test_inbtn);
            this.errorLayout = (LinearLayout) this.recommendFooterView.findViewById(R.id.recommend_error_ll_layout);
            TextView textView3 = new TextView(this.activity);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            textView3.setText("\u7f51\u7edc\u4e0d\u7ed9\u529b\u54e6\uff0c\u8bf7\u91cd\u8bd5\uff01");
            textView3.setTextColor(Color.parseColor("#848689"));
            layoutParams3.leftMargin = DPIUtil.dip2px(4.0f);
            layoutParams3.gravity = 16;
            this.errorLayout.addView(textView3, layoutParams3);
            this.errorLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.4
                {
                    RecommendUtil.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.this.manager.loadRecommendData();
                }
            });
            this.footerTestinBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.5
                {
                    RecommendUtil.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.this.onJumpPublicTest("2");
                }
            });
            setFootState(this.currentFooterState);
        }
        return new RecommedHeaderAndFooterViewHolder(this.recommendFooterView);
    }

    public RecyclerView.ViewHolder onCreateRecommedHeaderViewHolder(ViewGroup viewGroup) {
        JDDisplayImageOptions showImageForEmptyUri;
        if (OKLog.D) {
            OKLog.d("RecommendUtil", "onCreateRecommedHeaderViewHolder:");
        }
        if (this.recommendHeaderView == null || this.from == 0) {
            if (viewGroup != null) {
                this.recommendHeaderView = LayoutInflater.from(this.activity).inflate(R.layout.recommend_head, viewGroup, false);
            } else {
                this.recommendHeaderView = LayoutInflater.from(this.activity).inflate(R.layout.recommend_head, (ViewGroup) null);
            }
            this.onlineImg = (SimpleDraweeView) this.recommendHeaderView.findViewById(R.id.online_layout_img);
            this.testin = (TextView) this.recommendHeaderView.findViewById(R.id.recommend_testin_btn);
        }
        if (this.from == 9) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            int i2 = R.drawable.recommend_home_headimg;
            showImageForEmptyUri = createSimple.showImageOnFail(i2).showImageForEmptyUri(i2);
        } else {
            JDDisplayImageOptions createSimple2 = JDDisplayImageOptions.createSimple();
            int i3 = R.drawable.recommend_headimg;
            showImageForEmptyUri = createSimple2.showImageOnFail(i3).showImageForEmptyUri(i3);
        }
        if (TextUtils.isEmpty(this.headerTitleUrl)) {
            this.onlineImg.setImageResource(R.drawable.recommend_headimg);
        } else {
            JDImageUtils.displayImage(this.headerTitleUrl, this.onlineImg, showImageForEmptyUri, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.1
                {
                    RecommendUtil.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                    if (Log.D) {
                        Log.e("onlineImg", "onLoadingCancelled");
                    }
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    if (Log.D) {
                        Log.e("onlineImg", "onLoadingComplete");
                    }
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    RecommendUtil.this.onlineImg.setImageResource(R.drawable.recommend_headimg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                    if (Log.D) {
                        Log.e("onlineImg", "onLoadingStarted");
                    }
                }
            });
        }
        this.onlineImg.requestLayout();
        RecommendOtherData recommendOtherData = this.recommendOtherData;
        if (recommendOtherData != null && TextUtils.equals("1", recommendOtherData.getPublicTest())) {
            this.testin.setVisibility(0);
            this.testin.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.2
                {
                    RecommendUtil.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.this.onJumpPublicTest("1");
                }
            });
        } else {
            this.testin.setVisibility(8);
        }
        return new RecommedHeaderAndFooterViewHolder(this.recommendHeaderView);
    }

    public RecyclerView.ViewHolder onCreateRecommedViewHolder(BaseActivity baseActivity, int i2, int i3) {
        int i4 = i2 - i3;
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(baseActivity, ImageUtil.inflate(R.layout.recommend_item, null), i4 / 12, i4 % 12, this.recommendOtherData, this.realExpoHashSet);
        recommendViewHolder.setClickedListener(this.clickedListener);
        return recommendViewHolder;
    }

    public void resetDisplayMode() {
        RecommendOtherData recommendOtherData = this.recommendOtherData;
        if (recommendOtherData != null) {
            recommendOtherData.setDisplayMode("0");
        }
    }

    public void sendExposureMta(Activity activity) {
        ExpoDataStore expoDataStore = this.expoDataStore;
        if (expoDataStore != null) {
            expoDataStore.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore2 = this.expoDataCart;
        if (expoDataStore2 != null) {
            expoDataStore2.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore3 = this.expoDataAggreation;
        if (expoDataStore3 != null) {
            expoDataStore3.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore4 = this.expoDataDetails;
        if (expoDataStore4 != null) {
            expoDataStore4.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore5 = this.expoDataShop;
        if (expoDataStore5 != null) {
            expoDataStore5.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore6 = this.expoDataQuesitionNair;
        if (expoDataStore6 != null) {
            expoDataStore6.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore7 = this.realExpoDataAggreation;
        if (expoDataStore7 != null) {
            expoDataStore7.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore8 = this.realExpoDataStore;
        if (expoDataStore8 != null) {
            expoDataStore8.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore9 = this.expoDataPromotion;
        if (expoDataStore9 != null) {
            expoDataStore9.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore10 = this.expoDataStoreRecycling;
        if (expoDataStore10 != null) {
            expoDataStore10.sendExpoMta(activity, true);
        }
        ExpoDataStore expoDataStore11 = this.performanceDataStore;
        if (expoDataStore11 != null) {
            expoDataStore11.sendPerformanceData(activity);
        }
    }

    public void setAdRealExpo(boolean z, boolean z2) {
        this.isAdRealExpo = z;
        this.isAdAllPositionExpo = z2;
    }

    public void setBgColor(String str) {
        this.bgColor = str;
    }

    public void setBitmapConfig(Bitmap.Config config) {
        if (config != null) {
            this.jdDisplayImageOptions.bitmapConfig(config);
        } else {
            this.jdDisplayImageOptions.bitmapConfig(Bitmap.Config.RGB_565);
        }
    }

    public void setClickedListener(OnRecommendClickedListener onRecommendClickedListener) {
        this.clickedListener = onRecommendClickedListener;
    }

    public void setDarkBgColor(String str) {
        RecommendConfig recommendConfig = this.recommendConfig;
        if (recommendConfig != null) {
            recommendConfig.setDarkBgColor(str);
        }
    }

    public void setDarkHeaderTitleUrl(String str) {
        this.darkHeaderTitleUrl = str;
    }

    public void setFeedBackImmediateEffect(String str) {
        this.feedBackImmediateEffect = str;
    }

    public void setFloorMiniWareNum(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.floorMinWareNum = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    public void setFootState(final int i2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendUtil.6
            {
                RecommendUtil.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                RecommendUtil.this.currentFooterState = i2;
                if (RecommendUtil.this.loadingLayout == null || RecommendUtil.this.endLayout == null || RecommendUtil.this.errorLayout == null) {
                    return;
                }
                int i3 = i2;
                if (i3 == 0) {
                    RecommendUtil.this.loadingLayout.setVisibility(0);
                    RecommendUtil.this.endLayout.setVisibility(8);
                    RecommendUtil.this.errorLayout.setVisibility(8);
                    RecommendUtil.this.testinLayout.setVisibility(8);
                } else if (i3 == 1) {
                    RecommendUtil.this.loadingLayout.setVisibility(8);
                    RecommendUtil.this.endLayout.setVisibility(8);
                    RecommendUtil.this.testinLayout.setVisibility(8);
                    if (RecommendUtil.this.manager.getFrom() == 9 || RecommendUtil.this.manager.getFrom() == 6 || RecommendUtil.this.manager.getFrom() == 36 || RecommendUtil.this.manager.getFrom() == 35 || RecommendUtil.this.manager.getFrom() == 48 || RecommendUtil.this.manager.getFrom() == 24 || RecommendUtil.this.manager.getFrom() == 10024 || RecommendUtil.this.manager.getFrom() == 0 || RecommendUtil.this.manager.getFrom() == 37) {
                        RecommendUtil.this.errorLayout.setVisibility(0);
                    } else {
                        RecommendUtil.this.errorLayout.setVisibility(8);
                    }
                } else if (i3 != 2) {
                    if (i3 != 3) {
                        return;
                    }
                    RecommendUtil.this.loadingLayout.setVisibility(8);
                    RecommendUtil.this.endLayout.setVisibility(8);
                    RecommendUtil.this.testinLayout.setVisibility(8);
                    if (RecommendUtil.this.manager.getFrom() == 9 || RecommendUtil.this.manager.getFrom() == 36) {
                        RecommendUtil.this.errorLayout.setVisibility(0);
                    } else {
                        RecommendUtil.this.errorLayout.setVisibility(8);
                    }
                } else {
                    RecommendUtil.this.loadingLayout.setVisibility(8);
                    if (RecommendUtil.this.recommendOtherData == null || !TextUtils.equals("1", RecommendUtil.this.recommendOtherData.getPublicTest())) {
                        RecommendUtil.this.endLayout.setVisibility(0);
                        RecommendUtil.this.testinLayout.setVisibility(8);
                    } else {
                        RecommendUtil.this.endLayout.setVisibility(8);
                        RecommendUtil.this.testinLayout.setVisibility(0);
                        if (RecommendUtil.this.manager.getmActivity() != null) {
                            RecommendMtaUtils.PublicNewTestExpoMta(RecommendUtil.this.manager.getmActivity(), RecommendUtil.this.from);
                        }
                    }
                    RecommendUtil.this.errorLayout.setVisibility(8);
                }
            }
        });
    }

    public void setFrom(int i2) {
        this.from = i2;
        createExpoDataStore(i2);
    }

    public void setHeader(String str) {
        this.headerTitleUrl = str;
    }

    public void setHomePageTestPlanLoader(IRecommendHomePageTestPlanLoader iRecommendHomePageTestPlanLoader) {
        this.homePageTestPlanLoader = iRecommendHomePageTestPlanLoader;
    }

    public void setInterActiveRecommendList(ArrayList<RecommendItem> arrayList) {
        this.interActiveRecommendList = arrayList;
    }

    public void setIsSingleRow(boolean z) {
        this.isSingleRow = z;
    }

    public void setOnRecommendFooterErrorClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.errorLayout;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRecommendBuyaSeeList(List<WareInfoReason> list) {
        this.mWareInfoReasons = list;
    }

    public void setRecommendConfig(RecommendConfig recommendConfig) {
        this.recommendConfig = recommendConfig;
    }

    public void setRecommendGuide(RecommendOtherData recommendOtherData) {
        this.recommendOtherData = recommendOtherData;
    }

    public void setRecommendProductItemList(ArrayList<RecommendItem> arrayList) {
        this.mRecommendItemList = arrayList;
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBindNewRecommendViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder r19, int r20, com.jingdong.common.BaseActivity r21, java.util.List r22) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            boolean r2 = r0 instanceof com.jingdong.common.recommend.forlist.RecommendViewHolder
            if (r2 == 0) goto L84
            r2 = r0
            com.jingdong.common.recommend.forlist.RecommendViewHolder r2 = (com.jingdong.common.recommend.forlist.RecommendViewHolder) r2
            com.jingdong.common.recommend.ExpoDataStore r3 = r1.expoDataShop
            r2.setExpoDataShop(r3)
            com.jingdong.common.recommend.ExpoDataStore r3 = r1.realExpoDataStore
            com.jingdong.common.recommend.ExpoDataStore r4 = r1.realExpoDataAggreation
            r2.setRealExpoDataStore(r3, r4)
            com.jingdong.common.recommend.RecommendConfig r3 = r1.recommendConfig
            r2.setmRecommendConfig(r3)
            boolean r3 = r1.isAdAllPositionExpo
            if (r3 != 0) goto L2a
            r3 = 6
            r7 = r20
            if (r7 <= r3) goto L2c
            r3 = 0
            r2.setIsAdRealExpo(r3)
            goto L31
        L2a:
            r7 = r20
        L2c:
            boolean r3 = r1.isAdRealExpo
            r2.setIsAdRealExpo(r3)
        L31:
            if (r22 == 0) goto L57
            boolean r2 = r22.isEmpty()     // Catch: java.lang.Exception -> L80
            if (r2 == 0) goto L3a
            goto L57
        L3a:
            r4 = r0
            com.jingdong.common.recommend.forlist.RecommendViewHolder r4 = (com.jingdong.common.recommend.forlist.RecommendViewHolder) r4     // Catch: java.lang.Exception -> L80
            java.util.ArrayList<com.jingdong.common.recommend.entity.RecommendItem> r6 = r1.mRecommendItemList     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r8 = r1.expoDataStore     // Catch: java.lang.Exception -> L80
            int r9 = r1.from     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.forlist.RecommendProductManager r0 = r1.manager     // Catch: java.lang.Exception -> L80
            int r10 = r0.tabId     // Catch: java.lang.Exception -> L80
            com.jingdong.app.util.image.JDDisplayImageOptions r11 = r1.jdDisplayImageOptions     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r12 = r1.expoDataAggreation     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r13 = r1.expoDataDetails     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r14 = r1.expoDataQuesitionNair     // Catch: java.lang.Exception -> L80
            r5 = r22
            r7 = r20
            r4.bindNewRecommendViewHolder(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Exception -> L80
            goto L84
        L57:
            r4 = r0
            com.jingdong.common.recommend.forlist.RecommendViewHolder r4 = (com.jingdong.common.recommend.forlist.RecommendViewHolder) r4     // Catch: java.lang.Exception -> L80
            java.util.ArrayList<com.jingdong.common.recommend.entity.RecommendItem> r5 = r1.mRecommendItemList     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.entity.RecommendOtherData r6 = r1.recommendOtherData     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r8 = r1.expoDataStore     // Catch: java.lang.Exception -> L80
            int r9 = r1.from     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.forlist.RecommendProductManager r0 = r1.manager     // Catch: java.lang.Exception -> L80
            int r10 = r0.tabId     // Catch: java.lang.Exception -> L80
            boolean r11 = r18.isWaterFallStrategy()     // Catch: java.lang.Exception -> L80
            com.jingdong.app.util.image.JDDisplayImageOptions r12 = r1.jdDisplayImageOptions     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r13 = r1.expoDataAggreation     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r14 = r1.expoDataDetails     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r15 = r1.expoDataQuesitionNair     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r0 = r1.expoDataPromotion     // Catch: java.lang.Exception -> L80
            com.jingdong.common.recommend.ExpoDataStore r2 = r1.expoDataStoreRecycling     // Catch: java.lang.Exception -> L80
            r7 = r20
            r16 = r0
            r17 = r2
            r4.bindNewRecommendViewHolder(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Exception -> L80
            goto L84
        L80:
            r0 = move-exception
            r0.printStackTrace()
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.forlist.RecommendUtil.onBindNewRecommendViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder, int, com.jingdong.common.BaseActivity, java.util.List):void");
    }

    public RecommendUtil(int i2) {
        this.mWareInfoReasons = new ArrayList();
        this.isVisible = true;
        this.currentFooterState = 0;
        this.feedBackImmediateEffect = "0";
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.interActiveRecommendList = null;
        this.serviceDarkModeEnable = false;
        this.serviceElderModeEnable = false;
        this.isAdAllPositionExpo = false;
        this.floorMinWareNum = 0;
        this.from = i2;
        createExpoDataStore(i2);
        this.realExpoHashSet = new HashSet<>();
    }

    public RecommendUtil(Activity activity, RecommendProductManager recommendProductManager) {
        this.mWareInfoReasons = new ArrayList();
        this.isVisible = true;
        this.currentFooterState = 0;
        this.feedBackImmediateEffect = "0";
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendConstant.HTTP_REFER);
        this.interActiveRecommendList = null;
        this.serviceDarkModeEnable = false;
        this.serviceElderModeEnable = false;
        this.isAdAllPositionExpo = false;
        this.floorMinWareNum = 0;
        this.activity = activity;
        this.manager = recommendProductManager;
        this.realExpoHashSet = new HashSet<>();
        int from = recommendProductManager.getFrom();
        this.from = from;
        createExpoDataStore(from);
    }
}
