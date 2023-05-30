package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.parser.Feature;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.TypeToken;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.CommonListener;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendAddCartUtils;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendDataLoader;
import com.jingdong.common.recommend.RecommendEvent;
import com.jingdong.common.recommend.RecommendExpoHelper;
import com.jingdong.common.recommend.RecommendFeedBackManger;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendNetUtil;
import com.jingdong.common.recommend.RecommendSPUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecomPerformanceData;
import com.jingdong.common.recommend.entity.RecommendData;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendGuide;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendMaterialData;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendPromotion;
import com.jingdong.common.recommend.entity.RecommendShop;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.entity.RecommendTipsEvent;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.WareInfoReason;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.unification.video.player.AVideoPlayStateListener;
import com.jingdong.common.unification.video.player.IProgrssChangeListener;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.JDSettingUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;
import tv.danmaku.ijk.media.pha.JDHPlayerJSEvent;

/* loaded from: classes6.dex */
public abstract class RecommendProductManager {
    public static final int FROM_BROWSERHISTORY = 15;
    public static final int FROM_CARD_MEMBER_NEARBYSALE = 63;
    public static final int FROM_CARD_MEMBER_STORE_SERVICE = 64;
    public static final int FROM_CONFIRMGOODSSUCCESS = 13;
    public static final int FROM_HOME_PAGE = 9;
    public static final int FROM_HOME_TAB_PROMOTION = 36;
    public static final int FROM_MESSAGE_CENTER = 34;
    public static final int FROM_MYCONCERN = 1;
    public static final int FROM_MYJD = 0;
    public static final int FROM_MYJD_GENE = 18;
    public static final int FROM_OFTEN_BUY_LIST = 10;
    public static final int FROM_ORDERCENTER_CANCELFINISH = 51;
    public static final int FROM_ORDER_ALL = 27;
    public static final int FROM_ORDER_CANCEL = 31;
    public static final int FROM_ORDER_COMPLETE = 30;
    public static final int FROM_ORDER_DETAIL = 3;
    public static final int FROM_ORDER_PAYMENT = 28;
    public static final int FROM_ORDER_RECEIVING = 29;
    public static final int FROM_ORDER_TRACE = 4;
    public static final int FROM_PAYSUCCESS = 2;
    public static final int FROM_PRODUCTDETAIL_PHONE_ADD_CAR = 35;
    public static final int FROM_PRODUCTDETAIL_SELF_ADD_CAR = 10035;
    public static final int FROM_PRODUCTDETAIL_SOA_ADD_CAR = 10036;
    public static final int FROM_PRODUCT_DETAIL_4TAB = 24;
    public static final int FROM_PRODUCT_DETAIL_ADD_CAR_FRESH = 5;
    public static final int FROM_PRODUCT_DETAIL_ARCHIVE = 49;
    public static final int FROM_PRODUCT_DETAIL_FEED_4TAB = 10024;
    public static final int FROM_PRODUCT_DETAIL_NO_PRODUCT = 48;
    public static final int FROM_SCENE_DETAIL = 37;
    public static final int FROM_SHOPPINGCAR = 6;
    public static final int FROM_VIRTUAL_ORDERS = 17;
    public static final String HOME_PAGE_TEST_PLAN_A = "A";
    public static final String HOME_PAGE_TEST_PLAN_B = "B";
    private int clickIndex;
    HashMap<String, Object> extentParam;
    private RecommendProduct feedbackProduct;
    private int firstVisible;
    private RecommendHomeData homeData;
    private IMyActivity mActivity;
    private RecommendDataLoader mDataLoader;
    private int mFrom;
    private RecommendExpoHelper mOldRecommendExpoHelper;
    private int mPageSize;
    private int mScrollState;
    private String[] mSkus;
    private VideoPlayView mVideoPlayView;
    public boolean notPlay;
    private String publicTestBottomUrl;
    private String publicTestTopUrl;
    private RecommendUtil recommendUtil;
    protected boolean replay;
    private int scrollOffset;
    private boolean showLaoding;
    private String sourceExt;
    private final int statusBarHeight;
    private RecommendVideo tag;
    private int totalCount;
    private int visibleCount;
    private final String TAG = RecommendProductManager.class.getSimpleName();
    private boolean mHasData = false;
    private ArrayList<RecommendItem> mDataList = new ArrayList<>();
    private int lastPosition = 0;
    public boolean isPageOneError = false;
    private int offset = 2;
    private int titleHeight = DPIUtil.dip2px(49.0f);
    private int bottomHeight = DPIUtil.dip2px(70.0f);
    private int screenHeight = DPIUtil.getHeight();
    private int isFromTips = 0;
    private boolean productClick = false;
    private SparseArray<ArrayList<RecommendItem>> mCacheRecommends = new SparseArray<>();
    private ArrayList<VideoPlayView> cacheVideo = new ArrayList<>();
    private int lastClickFeedBackUiPosition = -1;
    private String homePageTestPlan = "B";
    public int tabId = -1;
    private RecommendTab recommendTab = null;
    private boolean isBackToHome = false;
    private boolean isJumpToProductDetail = false;
    private int insertIndex = -1;
    private RecommendProduct interactiveProduct = null;
    private int isEnableCache = 0;
    public int enableVideoOffset = 0;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public RecommendProductManager(final IMyActivity iMyActivity, final int i2, String[] strArr) {
        this.mFrom = i2;
        this.mSkus = strArr;
        this.mActivity = iMyActivity;
        RecommendUtil recommendUtil = new RecommendUtil(iMyActivity.getThisActivity(), this);
        this.recommendUtil = recommendUtil;
        recommendUtil.setRecommendProductItemList(this.mDataList);
        this.statusBarHeight = getStatusBarHeight();
        this.recommendUtil.setClickedListener(new RecommendUtil.OnRecommendClickedListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.1
            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onAddCarClick(RecommendProduct recommendProduct) {
                RecommendProductManager.this.onRecommendAddCar(recommendProduct);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onAggregationClick(RecommendDna recommendDna) {
                RecommendJumpUtils.onJumpAggregation(recommendDna, RecommendProductManager.this.mActivity, RecommendProductManager.this.mFrom);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendDna.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onChannelUnderJump(String str, String str2, String str3) {
                try {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    if ("0".equals(str3)) {
                        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(RecommendProductManager.this.mActivity.getThisActivity());
                    } else {
                        DeepLinkMHelper.startWebActivity(RecommendProductManager.this.mActivity.getThisActivity(), str);
                    }
                    RecommendMtaUtils.recommendChannelUnderClickMta(RecommendProductManager.this.mActivity.getThisActivity(), str2, i2);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onDotMoreMta(int i3, String str) {
                RecommendMtaUtils.newFloatLayerMta(RecommendProductManager.this.mActivity.getThisActivity(), RecommendProductManager.this.mFrom, i3, str);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onEnterPromotionClick(RecommendPromotion recommendPromotion) {
                RecommendJumpUtils.onEnterPromotion(RecommendProductManager.this.mActivity.getThisActivity(), recommendPromotion, RecommendProductManager.this.mFrom, recommendPromotion.extension_id);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendPromotion.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onEnterShopClick(RecommendShop recommendShop) {
                RecommendJumpUtils.onEnterShop(RecommendProductManager.this.mActivity.getThisActivity(), recommendShop, RecommendProductManager.this.mFrom);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendShop.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onGeneClick(RecommendDna recommendDna) {
                RecommendJumpUtils.onJumpGene(RecommendProductManager.this.mActivity, recommendDna, RecommendProductManager.this.mFrom);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendDna.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onJumpPublicTest(String str) {
                RecommendProductManager.this.jpTest(str);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onNoRecommendClick(RecommendProduct recommendProduct, int i3, String str, ArrayList<Integer> arrayList) {
                if (RecommendProductManager.this.isImmediateDelete(recommendProduct, arrayList)) {
                    RecommendProductManager.this.onRecommendImmediateDelete(recommendProduct, i3, str, arrayList);
                } else {
                    RecommendProductManager.this.onRecommendLongClick(recommendProduct, i3, str);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onNoRecommendMaterialClick(RecommendMaterialData recommendMaterialData, int i3, String str, ArrayList<Integer> arrayList, String str2, String str3) {
                RecommendProductManager.this.onRecommendFeedBackClick(recommendMaterialData, i3, str, str2, str3);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onProductClick(RecommendProduct recommendProduct) {
                if (RecommendProductManager.this.mFrom == 9) {
                    RecommendProductManager.this.productClick = true;
                }
                if (RecommendProductManager.this.isJumpToMini() && recommendProduct != null && !TextUtils.isEmpty(recommendProduct.jumpDesUrl)) {
                    RecommendProductManager.this.productDesJump(recommendProduct);
                    return;
                }
                RecommendProductManager recommendProductManager = RecommendProductManager.this;
                if (recommendProductManager.specialProductClick(recommendProduct, recommendProductManager.mFrom, RecommendProductManager.this.isFromTips)) {
                    return;
                }
                RecommendJumpUtils.onRecommendStartProductDetailActivity(RecommendProductManager.this.mActivity.getThisActivity(), recommendProduct, RecommendProductManager.this.mFrom, RecommendProductManager.this.isFromTips);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendChannelJump(RecommendDna recommendDna) {
                if (TextUtils.isEmpty(recommendDna.channelJumpUrl)) {
                    return;
                }
                Uri parse = Uri.parse(recommendDna.channelJumpUrl);
                if ("0".equals(recommendDna.isOpenApp)) {
                    new OpenAppJumpBuilder.Builder(parse).build().jump(iMyActivity.getThisActivity());
                } else {
                    DeepLinkMHelper.startWebActivity(iMyActivity.getThisActivity(), recommendDna.channelJumpUrl);
                }
                if (!"1".equals(recommendDna.source) || RecommendProductManager.this.mDataLoader == null) {
                    return;
                }
                RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendDna.client_click_url);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendDetalis(RecommendDetails recommendDetails) {
                RecommendJumpUtils.onJumpDetalis(recommendDetails, RecommendProductManager.this.mActivity.getThisActivity(), RecommendProductManager.this.mFrom);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendDetails.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendJump(String str, String str2) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Uri parse = Uri.parse(str);
                if ("0".equals(str2)) {
                    new OpenAppJumpBuilder.Builder(parse).build().jump(iMyActivity.getThisActivity());
                } else {
                    DeepLinkMHelper.startWebActivity(iMyActivity.getThisActivity(), str);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendMoneyExpo(String str) {
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(str);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendReasonMta(String str) {
                RecommendMtaUtils.deleteProductResonClickMta(RecommendProductManager.this.mActivity.getThisActivity(), str, RecommendProductManager.this.mFrom);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRecommendVideoClick(RecommendVideo recommendVideo) {
                if (recommendVideo == null) {
                    return;
                }
                try {
                    if (!TextUtils.isEmpty(recommendVideo.channelJumpUrl)) {
                        Uri parse = Uri.parse(recommendVideo.channelJumpUrl);
                        if ("0".equals(recommendVideo.isOpenApp)) {
                            new OpenAppJumpBuilder.Builder(parse).build().jump(RecommendProductManager.this.mActivity.getThisActivity());
                        } else {
                            DeepLinkMHelper.startWebActivity(RecommendProductManager.this.mActivity.getThisActivity(), recommendVideo.channelJumpUrl);
                        }
                    }
                } catch (Exception e2) {
                    OKLog.e(RecommendProductManager.this.TAG, e2);
                }
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendProductManager.this.mActivity.getThisActivity(), recommendVideo.sourceValue, RecommendProductManager.this.mFrom, recommendVideo.extension_id);
                if (RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.recommendClickRequest(recommendVideo.client_click_url);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onRefresh() {
                RecommendProductManager.this.onRefreshListData();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onShowFeedbackUi(RecommendProduct recommendProduct, int i3) {
                if (RecommendProductManager.this.feedbackProduct != null) {
                    RecommendProductManager.this.feedbackProduct.isShowFeedbackUi = false;
                }
                if (recommendProduct != null) {
                    recommendProduct.isShowFeedbackUi = true;
                    RecommendProductManager.this.feedbackProduct = recommendProduct;
                }
                RecommendProductManager.this.callShowFeedbackUiRefreshListData(i3);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onSimilarClick(RecommendProduct recommendProduct) {
                if (RecommendProductManager.this.mFrom == 9) {
                    RecommendProductManager.this.productClick = true;
                }
                RecommendJumpUtils.onRecommendStartSimilarActivity(RecommendProductManager.this.mActivity, recommendProduct, RecommendProductManager.this.mFrom, RecommendProductManager.this.isFromTips);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onAddCarClick(RecommendProduct recommendProduct, String str) {
                RecommendProductManager.this.onRecommendAddCar(recommendProduct, str);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onProductClick(RecommendProduct recommendProduct, String str) {
                RecommendJumpUtils.onRecommendStartProductDetailActivity(RecommendProductManager.this.mActivity.getThisActivity(), recommendProduct, RecommendProductManager.this.mFrom, str);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
            public void onProductClick(RecommendProduct recommendProduct, RecommendItem recommendItem) {
                if (RecommendProductManager.this.mFrom == 9) {
                    RecommendProductManager.this.productClick = true;
                    if (recommendProduct != null && "1".equals(recommendProduct.interactive) && recommendItem != null && recommendProduct.canInteractive) {
                        RecommendProductManager.this.isJumpToProductDetail = true;
                        ArrayList<RecommendItem> recommendItemList = RecommendProductManager.this.recommendUtil.getRecommendItemList();
                        RecommendProductManager.this.clickIndex = -1;
                        if (recommendItemList != null) {
                            RecommendProductManager.this.clickIndex = recommendItemList.indexOf(recommendItem);
                        }
                        if (RecommendProductManager.this.clickIndex != -1) {
                            RecommendProductManager recommendProductManager = RecommendProductManager.this;
                            RecyclerView.ViewHolder findHolderForAdapterPostion = recommendProductManager.findHolderForAdapterPostion(recommendProductManager.clickIndex);
                            RecommendProductManager recommendProductManager2 = RecommendProductManager.this;
                            recommendProductManager2.insertIndex = recommendProductManager2.getInterActiveInsertIndex(recommendProductManager2.clickIndex, RecommendUtils.isViewInLeft(findHolderForAdapterPostion));
                        }
                        RecommendProductManager.this.interactiveProduct = recommendProduct;
                    }
                }
                if (!RecommendProductManager.this.isJumpToMini() || recommendProduct == null || TextUtils.isEmpty(recommendProduct.jumpDesUrl) || TextUtils.isEmpty(recommendProduct.isJumpApp)) {
                    RecommendJumpUtils.onRecommendStartProductDetailActivity(RecommendProductManager.this.mActivity.getThisActivity(), recommendProduct, RecommendProductManager.this.mFrom, RecommendProductManager.this.isFromTips);
                } else {
                    RecommendProductManager.this.productDesJump(recommendProduct);
                }
            }
        });
        this.recommendUtil.setHomePageTestPlanLoader(new RecommendUtil.IRecommendHomePageTestPlanLoader() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.2
            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.IRecommendHomePageTestPlanLoader
            public boolean checkHomePageTestPlanIsA() {
                return RecommendProductManager.this.checkHomePageTestPlanIsPlanA();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendUtil.IRecommendHomePageTestPlanLoader
            public String getHomePageTestPlan() {
                return RecommendProductManager.this.getHomePageTestPlan();
            }
        });
    }

    private void addNewVideoView(RecyclerView recyclerView) {
        RecommendProduct recommendProduct;
        if (recyclerView == null) {
            return;
        }
        for (int i2 = 0; i2 < this.visibleCount; i2++) {
            int i3 = (this.firstVisible - this.enableVideoOffset) + i2;
            if (i3 >= 0 && getRecommendUtil().getRecommendItemList() != null && getRecommendUtil().getRecommendItemList().size() > i3) {
                int i4 = getRecommendUtil().getRecommendItemList().get(i3).type;
                if (i4 == 1005 || i4 == 1013 || i4 == 1014 || i4 == 1018 || i4 == 1019 || i4 == 1022) {
                    View childAt = recyclerView.getChildAt(i2);
                    int i5 = R.id.recommend_vp_left;
                    View findViewById = childAt.findViewById(i5);
                    if (findViewById instanceof VideoPlayView) {
                        VideoPlayView videoPlayView = (VideoPlayView) findViewById;
                        videoPlayView.setTag(i5, Integer.valueOf(this.enableVideoOffset + i3));
                        if (inScreen(videoPlayView)) {
                            this.cacheVideo.add(videoPlayView);
                        }
                    }
                }
                if (i4 == 0) {
                    try {
                        RecommendItem recommendItem = getRecommendUtil().getRecommendItemList().get(i3);
                        if (recommendItem != null && (recommendProduct = recommendItem.product) != null && !TextUtils.isEmpty(recommendProduct.playUrl)) {
                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) recyclerView.getChildAt(i2).findViewById(R.id.recommend_product_item_imgview);
                            View childAt2 = recyclerView.getChildAt(i2);
                            int i6 = R.id.recommend_vp_left;
                            View findViewById2 = childAt2.findViewById(i6);
                            if (findViewById2 instanceof VideoPlayView) {
                                VideoPlayView videoPlayView2 = (VideoPlayView) findViewById2;
                                videoPlayView2.setTag(i6, Integer.valueOf(i3 + this.enableVideoOffset));
                                if (isNewVideo()) {
                                    if (RecommendViewUtil.getCurrentExpoPercent(simpleDraweeView) >= 0.5d) {
                                        this.cacheVideo.add(videoPlayView2);
                                    }
                                } else if (inScreen(simpleDraweeView)) {
                                    this.cacheVideo.add(videoPlayView2);
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private void addVideoView(AbsListView absListView) {
    }

    private void callRecommendLongClickRefreshListData(int i2, int i3) {
        int i4 = this.mFrom;
        if (i4 != 9 && i4 != 24 && i4 != 10024) {
            onRefreshListData();
            return;
        }
        onRefershListDataRangeRemove(i2, 1);
        onRefershListDataRangeChange(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callShowFeedbackUiRefreshListData(int i2) {
        if (this.mFrom == 9) {
            int i3 = this.lastClickFeedBackUiPosition;
            if (i3 != -1) {
                onRefershListDataRangeChange(i3, 1);
            }
            onRefershListDataRangeChange(i2, 1);
            this.lastClickFeedBackUiPosition = i2;
            return;
        }
        onRefreshListData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callShowNextPageDataRefreshListData(int i2, int i3) {
        int i4;
        if (!RecommendUtils.isWaterFull(this.mFrom) && (i4 = this.mFrom) != 24 && i4 != 10024) {
            onRefreshListData();
        } else {
            onRefreshListDataRangeInsert(i2, i3);
        }
    }

    private void dealFirstPageLogic(RecommendOtherData recommendOtherData, JDJSONObject jDJSONObject) {
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2;
        RecommendUtil recommendUtil;
        List<WareInfoReason> list;
        recommendOtherData.mainBackgroundImg = jDJSONObject.optString("main_background_img");
        recommendOtherData.innerBackgroudImg = jDJSONObject.optString("innerBackgroudImg");
        recommendOtherData.innerDescriptionFont = jDJSONObject.optString("innerDescriptionFont");
        recommendOtherData.innerSubTitleFont = jDJSONObject.optString("innerSubTitleFont");
        recommendOtherData.innerMainTitleFont = jDJSONObject.optString("innerMainTitleFont");
        recommendOtherData.innerBackgroudColor = jDJSONObject.optString("innerBackgroudColor");
        recommendOtherData.categoryGuideAssistant = (RecommendOtherData.CategoryGuideAssistant) jDJSONObject.getObject("categoryGuideAssistant", RecommendOtherData.CategoryGuideAssistant.class);
        if (!TextUtils.isEmpty(jDJSONObject.optString("grayNum"))) {
            recommendOtherData.recommendGrayNumber = RecommendUtils.parseStringToInt(jDJSONObject.optString("grayNum"));
        }
        int optInt = jDJSONObject.optInt("exposeNum", 100);
        parseVideoSwitch(jDJSONObject);
        recommendOtherData.setExposeNum(optInt);
        onSetPreload(jDJSONObject.optInt("isPreload", 0) == 1);
        String optString = jDJSONObject.optString("fbWizard");
        if (!TextUtils.isEmpty(optString)) {
            recommendOtherData.setRecommendGuide((RecommendGuide) JDJSON.parseObject(optString, new TypeToken<RecommendGuide>() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.6
            }.getType(), new Feature[0]));
        }
        recommendOtherData.setPublicTest(jDJSONObject.optString("publicTest", "0"));
        recommendOtherData.setPublicTestBubble(jDJSONObject.optString("publicTestBubble", "0"));
        if (jDJSONObject.containsKey("displayMode")) {
            recommendOtherData.setDisplayMode(jDJSONObject.optString("displayMode", "0"));
        }
        RecommendUtil recommendUtil2 = this.recommendUtil;
        if (recommendUtil2 != null) {
            recommendUtil2.serviceElderModeEnable = "1".equals(jDJSONObject.optString("uempsDegrade", "0"));
            if (this.recommendUtil.getRecommendConfig() != null) {
                this.recommendUtil.getRecommendConfig().setRecommendServiceElderSwitch(this.recommendUtil.serviceElderModeEnable);
            }
            this.recommendUtil.setFloorMiniWareNum(jDJSONObject.getString("floorMinWareNum"));
        }
        if (getFrom() == 9) {
            RecommendUtil recommendUtil3 = this.recommendUtil;
            recommendOtherData.set924UIStrategy((recommendUtil3 == null || recommendUtil3.getRecommendConfig() == null) ? "A" : this.recommendUtil.getRecommendConfig().getDisplayStyle());
            recommendOtherData.setUIStrategy(jDJSONObject.optString("uiStrategy", "A"));
        }
        recommendOtherData.setPublicTestBubbleTimestamp(jDJSONObject.optString("publicTestBubbleTimestamp"));
        if (jDJSONObject.containsKey("publicTestTopUrl")) {
            this.publicTestTopUrl = jDJSONObject.optString("publicTestTopUrl");
        }
        final RecommendHeaderData recommendHeaderData = new RecommendHeaderData();
        recommendHeaderData.headerTitleUrl = jDJSONObject.optString("title");
        recommendHeaderData.darkHeaderTitleUrl = jDJSONObject.optString("dmTitle");
        recommendHeaderData.publicTest = jDJSONObject.optString("publicTest", "0");
        recommendHeaderData.textTile = jDJSONObject.optString("textTitle");
        recommendHeaderData.waterFallStrategy = jDJSONObject.optString("waterFallStrategy", "0");
        this.recommendUtil.setIsSingleRow("1".equals(jDJSONObject.optString("columnFlag")));
        recommendHeaderData.isSingleRow = this.recommendUtil.getIsSingleRow();
        recommendHeaderData.isAdAllPositionExpo = "1".equals(jDJSONObject.optString("tsFlag", "0"));
        recommendHeaderData.publicTestTopUrl = this.publicTestTopUrl;
        this.mActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.7
            @Override // java.lang.Runnable
            public void run() {
                if (RecommendProductManager.this.mDataLoader.isDestoryed) {
                    return;
                }
                RecommendProductManager.this.onRecommendHeader(recommendHeaderData);
            }
        });
        this.publicTestBottomUrl = jDJSONObject.optString("publicTestBottomUrl");
        jDJSONObject.optString("geneInfoList");
        String optString2 = jDJSONObject.optString("reasonFloor");
        if (!TextUtils.isEmpty(optString2) && (list = (List) JDJSON.parseObject(optString2, new TypeToken<ArrayList<WareInfoReason>>() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.8
        }.getType(), new Feature[0])) != null) {
            recommendOtherData.setWareInfoReasons(list);
        }
        if (RecommendUtils.isWaterFull(this.mFrom) && (recommendUtil = this.recommendUtil) != null) {
            recommendUtil.setBgColor(jDJSONObject.optString(DYConstants.DY_BG_COLOR));
            this.recommendUtil.setDarkBgColor(jDJSONObject.optString("darkModeBgColor", JDDarkUtil.COLOR_141212));
            this.recommendUtil.serviceDarkModeEnable = "1".equals(jDJSONObject.optString("isEnableDarkMode", "0"));
        }
        int i2 = this.mFrom;
        if (i2 == 9 || i2 == 0 || i2 == 18 || i2 == 6) {
            String optString3 = jDJSONObject.optString("tabs");
            if (this.mFrom == 9 && jDJSONObject.containsKey("displayMode")) {
                String string = jDJSONObject.getString("displayMode");
                if (!TextUtils.isEmpty(string)) {
                    RecommendSPUtils.putString(RecommendSPUtils.SP_VEROLD, string);
                }
            }
            if (jDJSONObject.containsKey("liveSkuStrategy") && this.recommendUtil.getRecommendConfig() != null) {
                this.recommendUtil.getRecommendConfig().setEnableLiveProductsCover("B".equals(jDJSONObject.optString("liveSkuStrategy")));
            }
            if (!TextUtils.isEmpty(optString3)) {
                try {
                    ArrayList arrayList3 = (ArrayList) JDJSON.parseArray(optString3, RecommendTab.class);
                    if (arrayList3 != null) {
                        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                            RecommendTab recommendTab = (RecommendTab) arrayList3.get(i3);
                            if (recommendTab != null) {
                                recommendTab.setShowDot();
                            }
                        }
                    }
                    String optString4 = jDJSONObject.optString("selectedTabTextColor");
                    boolean optBoolean = jDJSONObject.optBoolean("animationSwitchOfTabs");
                    if (arrayList3 != null) {
                        final RecommendHomeTabs recommendHomeTabs = new RecommendHomeTabs(optString4, arrayList3, optBoolean);
                        String optString5 = jDJSONObject.optString("tabsColor");
                        if (!TextUtils.isEmpty(optString5) && (arrayList2 = (ArrayList) JDJSON.parseArray(optString5, String.class)) != null && arrayList2.size() >= 6) {
                            recommendHomeTabs.setRecommendTabColors(arrayList2);
                        }
                        String optString6 = jDJSONObject.optString("darkModeTabsColor");
                        if (!TextUtils.isEmpty(optString6) && (arrayList = (ArrayList) JDJSON.parseArray(optString6, String.class)) != null && arrayList.size() >= 6) {
                            recommendHomeTabs.setRecommendTabDarkColors(arrayList);
                        }
                        if (this.mFrom == 9) {
                            if (arrayList3.size() == 2) {
                                recommendHomeTabs.setLocalTabAB("B");
                            } else {
                                recommendHomeTabs.setLocalTabAB("A");
                            }
                            recommendHomeTabs.setMutiTabString(jDJSONObject.optString("recomMutiTab0TitleImg"));
                        }
                        recommendHomeTabs.handlePos();
                        this.mActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.9
                            @Override // java.lang.Runnable
                            public void run() {
                                if (RecommendProductManager.this.mDataLoader.isDestoryed) {
                                    return;
                                }
                                RecommendProductManager.this.onRecommendTabs(recommendHomeTabs);
                            }
                        });
                        return;
                    }
                    this.mActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.10
                        @Override // java.lang.Runnable
                        public void run() {
                            if (RecommendProductManager.this.mDataLoader.isDestoryed) {
                                return;
                            }
                            RecommendProductManager.this.onRecommendTabs(null);
                        }
                    });
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            this.mActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.11
                @Override // java.lang.Runnable
                public void run() {
                    if (RecommendProductManager.this.mDataLoader.isDestoryed) {
                        return;
                    }
                    RecommendProductManager.this.onRecommendTabs(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealFistPageRealExpo() {
        RecommendExpoHelper recommendExpoHelper = this.mOldRecommendExpoHelper;
        if (recommendExpoHelper == null || recommendExpoHelper.getNestedView() == null) {
            return;
        }
        this.mOldRecommendExpoHelper.getNestedView().post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.21
            @Override // java.lang.Runnable
            public void run() {
                if (RecommendProductManager.this.recommendUtil.getNewRecommendItemCount() > 0) {
                    RecommendProductManager.this.mOldRecommendExpoHelper.dealExpoView();
                } else {
                    RecommendProductManager.this.mOldRecommendExpoHelper.setCanExpo(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getInterActiveInsertIndex(int i2, boolean z) {
        if (i2 == -1) {
            return -1;
        }
        int i3 = i2 + 1;
        RecyclerView.ViewHolder findHolderForAdapterPostion = findHolderForAdapterPostion(i3);
        return (findHolderForAdapterPostion == null || RecommendUtils.isViewInLeft(findHolderForAdapterPostion) == z) ? i3 : getInterActiveInsertIndex(i3, z);
    }

    private void getPlayUrl(RecommendItem recommendItem) {
        RecommendVideo recommendVideo;
        if (!RecommendConstant.newVideoWidget || recommendItem == null || TextUtils.isEmpty(recommendItem.videoId)) {
            return;
        }
        RecommendProduct recommendProduct = recommendItem.product;
        if (recommendProduct != null) {
            recommendVideo = recommendProduct.getVideoData();
        } else {
            RecommendTemplate recommendTemplate = recommendItem.recommendTemplate;
            if (recommendTemplate != null) {
                recommendVideo = recommendTemplate.getVideoData();
            } else {
                recommendVideo = recommendItem.recommendVideo;
            }
        }
        if (recommendVideo != null) {
            String str = !TextUtils.isEmpty(recommendVideo.playUrl) ? recommendVideo.playUrl : recommendVideo.videoSummaryUrl;
            if (!TextUtils.isEmpty(str)) {
                PreloadManager.getInstance().doPreload(str);
            } else if (TextUtils.isEmpty(recommendVideo.videoId)) {
            } else {
                HttpGroupUtils.getHttpGroupaAsynPool().add(RecommendDataLoader.getVideoPlayUrl(recommendVideo, 0, new RecommendDataLoader.OnRecommendGetVideoUrlListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.12
                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onError() {
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(String str2, int i2) {
                        PreloadManager.getInstance().doPreload(str2);
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(String str2, String str3) {
                    }
                }));
            }
        }
    }

    private int getStatusBarHeight() {
        int identifier = this.mActivity.getThisActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.mActivity.getThisActivity().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private VideoPlayView getVideoAutoPlayPosition() {
        if (this.cacheVideo.size() != 0) {
            VideoPlayView videoPlayView = this.cacheVideo.get(0);
            this.cacheVideo.remove(0);
            return videoPlayView;
        }
        return null;
    }

    private String getVideoPlayExpoParam(RecommendVideo recommendVideo) {
        return String.valueOf(System.currentTimeMillis() / 1000) + CartConstant.KEY_YB_INFO_LINK + recommendVideo.wareId + CartConstant.KEY_YB_INFO_LINK + recommendVideo.reqsig + CartConstant.KEY_YB_INFO_LINK + String.valueOf(recommendVideo.videoDuration) + CartConstant.KEY_YB_INFO_LINK + String.valueOf(this.mVideoPlayView.getCurrentPosition() / 1000);
    }

    private int getViewLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInterActiveRecommendList() {
        RecommendItem recommendItem;
        RecommendProduct recommendProduct;
        RecommendItem recommendItem2;
        RecommendProduct recommendProduct2;
        if (this.insertIndex != -1) {
            final ArrayList<RecommendItem> interActiveRecommendList = this.recommendUtil.getInterActiveRecommendList();
            final ArrayList<RecommendItem> recommendItemList = this.recommendUtil.getRecommendItemList();
            if (recommendItemList == null || interActiveRecommendList == null || this.insertIndex > recommendItemList.size()) {
                return;
            }
            int i2 = this.clickIndex;
            if (i2 != -1) {
                int i3 = i2 - 1;
                int i4 = i2 + 1;
                if (i3 >= 0 && i3 < recommendItemList.size() && (recommendItem2 = recommendItemList.get(i3)) != null && (recommendProduct2 = recommendItem2.product) != null) {
                    recommendProduct2.canInteractive = false;
                }
                if (i4 >= 0 && i4 < recommendItemList.size() && (recommendItem = recommendItemList.get(i4)) != null && (recommendProduct = recommendItem.product) != null) {
                    recommendProduct.canInteractive = false;
                }
                this.clickIndex = -1;
            }
            this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.19
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        recommendItemList.addAll(RecommendProductManager.this.insertIndex, interActiveRecommendList);
                        RecommendProductManager.this.enableAnimator(true, 1);
                        RecommendProductManager recommendProductManager = RecommendProductManager.this;
                        recommendProductManager.onRefreshListDataRangeInsert(recommendProductManager.insertIndex, interActiveRecommendList.size());
                        int size = recommendItemList.size() - RecommendProductManager.this.insertIndex;
                        RecommendProductManager recommendProductManager2 = RecommendProductManager.this;
                        recommendProductManager2.onRefershListDataRangeChange(recommendProductManager2.insertIndex, size);
                        RecommendProductManager.this.isJumpToProductDetail = false;
                        RecommendProductManager.this.insertIndex = -1;
                        RecommendProductManager.this.interactiveProduct = null;
                    } catch (Exception unused) {
                        RecommendProductManager.this.isJumpToProductDetail = false;
                        RecommendProductManager.this.insertIndex = -1;
                        RecommendProductManager.this.interactiveProduct = null;
                    }
                }
            });
            this.mMainHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.20
                @Override // java.lang.Runnable
                public void run() {
                    RecommendProductManager.this.enableAnimator(false, 1);
                }
            }, 800L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTips(ArrayList<RecommendItem> arrayList, RecommendOtherData recommendOtherData) {
        if (recommendOtherData == null || recommendOtherData.tipsIndex <= -1 || arrayList == null) {
            return;
        }
        int size = arrayList.size();
        int i2 = recommendOtherData.tipsIndex;
        if (size > i2) {
            RecommendItem recommendItem = arrayList.get(i2);
            if (recommendItem.type == 0) {
                onRecommendTips(recommendItem.product);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean inScreen(VideoPlayView videoPlayView) {
        int height = videoPlayView.getHeight();
        int viewLocationY = getViewLocationY(videoPlayView);
        if (OKLog.D) {
            OKLog.e(this.TAG, "videoheight3--" + height);
            OKLog.e(this.TAG, "location--" + viewLocationY);
        }
        int i2 = ((viewLocationY + height) - this.statusBarHeight) - this.titleHeight;
        int i3 = height / 2;
        return i2 > i3 && (this.screenHeight - this.bottomHeight) - viewLocationY > i3;
    }

    private void initPageDataLoader() {
        RecommendDataLoader recommendDataLoader = new RecommendDataLoader(this.mActivity, null) { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.3
            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void justShowCacheData() {
                RecommendProductManager recommendProductManager = RecommendProductManager.this;
                recommendProductManager.isPageOneError = true;
                recommendProductManager.onRequestFail(this.pageNo);
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader, com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                super.onEnd(httpResponse);
                if (this.hasInit && !RecommendProductManager.this.mHasData) {
                    RecommendProductManager.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RecommendProductManager.this.mHasData = true;
                        }
                    });
                }
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageEnd(boolean z) {
                RecommendProductManager recommendProductManager = RecommendProductManager.this;
                recommendProductManager.isPageOneError = false;
                if (z) {
                    if (recommendProductManager.mDataList.size() > 0) {
                        RecommendProductManager.this.recommendUtil.setFootState(2);
                    } else {
                        RecommendProductManager.this.noData();
                        RecommendProductManager.this.onRecommendNoData();
                    }
                }
                if (RecommendProductManager.this.mDataLoader == null || RecommendProductManager.this.mDataLoader.getPageNo() != 1) {
                    return;
                }
                RecommendProductManager.this.dealFistPageRealExpo();
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageErr() {
                RecommendProductManager recommendProductManager = RecommendProductManager.this;
                recommendProductManager.isPageOneError = true;
                if (recommendProductManager.mDataList == null || RecommendProductManager.this.recommendUtil == null) {
                    return;
                }
                if (RecommendProductManager.this.mDataLoader != null && RecommendProductManager.this.mDataLoader.getPageNo() == 1 && RecommendProductManager.this.mFrom == 6) {
                    RecommendProductManager.this.callShowNextPageDataRefreshListData(0, 0);
                }
                if (RecommendProductManager.this.mDataList.size() == 0) {
                    RecommendProductManager.this.noData();
                } else {
                    RecommendProductManager.this.recommendUtil.setFootState(1);
                }
                RecommendProductManager.this.onErrorRefreshView();
                if (RecommendProductManager.this.mDataLoader != null && RecommendProductManager.this.mDataLoader.getPageNo() == 1) {
                    RecommendProductManager.this.onRecommendDataError();
                }
                RecommendProductManager.this.onRequestFail(this.pageNo);
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageLoading() {
                if (RecommendProductManager.this.mPageSize > 0 && RecommendProductManager.this.mDataLoader != null) {
                    RecommendProductManager.this.mDataLoader.setPageSize(RecommendProductManager.this.mPageSize);
                }
                RecommendProductManager.this.recommendUtil.setFootState(0);
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected boolean showNextPageData(ArrayList<?> arrayList, RecommendOtherData recommendOtherData) {
                if (RecommendProductManager.this.mDataLoader == null) {
                    return false;
                }
                if (RecommendProductManager.this.mDataList != null) {
                    if (RecommendProductManager.this.mDataLoader.getPageNo() == 1) {
                        RecommendProductManager.this.recommendUtil.clearRecommendData();
                        RecommendProductManager.this.notifyDataChanged(1, 0, 0);
                        if (recommendOtherData != null) {
                            if (recommendOtherData.getExposeNum() != 0) {
                                RecommendProductManager.this.setExpoNum(recommendOtherData.getExposeNum());
                            }
                            RecommendProductManager.this.recommendUtil.setRecommendGuide(recommendOtherData);
                        }
                    }
                    int size = RecommendProductManager.this.mDataList.size();
                    int size2 = arrayList != null ? arrayList.size() : 0;
                    if (arrayList != null) {
                        if (arrayList.size() > 0) {
                            RecommendProductManager.this.insertHomeData(arrayList, recommendOtherData);
                        }
                        RecommendProductManager.this.mDataList.addAll(arrayList);
                    }
                    RecommendProductManager recommendProductManager = RecommendProductManager.this;
                    recommendProductManager.handleTips(recommendProductManager.mDataList, recommendOtherData);
                    if (recommendOtherData != null) {
                        if (recommendOtherData.getWareInfoReasons() != null) {
                            RecommendProductManager.this.recommendUtil.setRecommendBuyaSeeList(recommendOtherData.getWareInfoReasons());
                        }
                        if (recommendOtherData.getTitleUrl() != null) {
                            RecommendProductManager.this.recommendUtil.setHeader(recommendOtherData.getTitleUrl());
                        }
                        if (recommendOtherData.getDarkHeaderTitleUrl() != null) {
                            RecommendProductManager.this.recommendUtil.setDarkHeaderTitleUrl(recommendOtherData.getDarkHeaderTitleUrl());
                        }
                    }
                    if (RecommendProductManager.this.mDataLoader.getPageNo() == 1) {
                        RecommendProductManager.this.onRecommendOnePageFinish();
                    }
                    RecommendProductManager.this.notifyDataChanged(getPageNo(), size, size2);
                    if (arrayList != null && !arrayList.isEmpty()) {
                        RecommendProductManager.this.onRequestSuccess(this.pageNo);
                    } else {
                        RecommendProductManager.this.onRequestFail(this.pageNo);
                    }
                }
                return true;
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            public void startRequestRecommend() {
                RecomPerformanceData performanceData;
                super.startRequestRecommend();
                if (RecommendProductManager.this.recommendUtil == null || (performanceData = RecommendProductManager.this.recommendUtil.getPerformanceData(getPageNo())) == null) {
                    return;
                }
                performanceData.page = getPageNo();
                performanceData.source = RecommendProductManager.this.mFrom;
                performanceData.requestStartTime = System.currentTimeMillis();
                if (RecommendProductManager.this.recommendTab != null) {
                    performanceData.tabid = RecommendProductManager.this.recommendTab.tabId;
                }
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected RecommendData toList(HttpResponse httpResponse) {
                return RecommendProductManager.this.parseRecommendData(httpResponse.getFastJsonObject(), httpResponse);
            }
        };
        this.mDataLoader = recommendDataLoader;
        recommendDataLoader.setBusinessFrom(this.mFrom);
        this.mDataLoader.setEnableCache(this.isEnableCache);
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            this.mDataLoader.setRecommendConfig(recommendUtil.getRecommendConfig());
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("source", this.mFrom);
            if (this.mFrom == 10024) {
                jSONObject.put("source", 24);
            }
            jSONObject.put("newUIStyle", ABTestUtils.is800UIStyle());
            int i2 = this.mFrom;
            boolean z = true;
            if (i2 == 9) {
                jSONObject.put("testPlanType", getHomePageTestPlan());
                jSONObject.put("needRecomTips", 1);
                RecommendTab recommendTab = this.recommendTab;
                if (recommendTab != null) {
                    jSONObject.put("tabId", recommendTab.tabId);
                    jSONObject.put("tabIndex", this.recommendTab.pos);
                }
            } else if (i2 == 0 || i2 == 18) {
                RecommendTab recommendTab2 = this.recommendTab;
                if (recommendTab2 != null) {
                    jSONObject.put("tabId", recommendTab2.tabId);
                    jSONObject.put("tabIndex", this.recommendTab.pos);
                    jSONObject.put("source", this.recommendTab.source);
                } else {
                    jSONObject.put("tabIndex", "0");
                }
            }
            jSONObject.put("eventId", JDMtaUtils.getLastEventId());
            jSONObject.put("curPos", RecommendUtils.getCurrentAddress());
            jSONObject.put("dlvAddr", RecommendUtils.getShippingAddress());
            String[] strArr = this.mSkus;
            if (strArr != null && strArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                int i3 = 0;
                while (true) {
                    String[] strArr2 = this.mSkus;
                    if (i3 >= strArr2.length) {
                        break;
                    }
                    jSONArray.put(i3, strArr2[i3]);
                    i3++;
                }
                jSONObject.put("skus", jSONArray);
            }
            try {
                HashMap<String, Object> hashMap = this.extentParam;
                if (hashMap != null) {
                    for (String str : hashMap.keySet()) {
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put(str, this.extentParam.get(str));
                        }
                    }
                }
                if (!TextUtils.isEmpty(this.sourceExt)) {
                    jSONObject.put("sourceExt", this.sourceExt);
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            if (this.showLaoding) {
                this.mDataLoader.setShowLoading(true);
            }
            if ("1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "homeCacheAB", "homeCacheAB", "1"))) {
                RecommendDataLoader recommendDataLoader2 = this.mDataLoader;
                RecommendUtil recommendUtil2 = this.recommendUtil;
                if (recommendUtil2 != null && recommendUtil2.getNewRecommendItemCount() != 0) {
                    z = false;
                }
                recommendDataLoader2.setUseCacheData(z);
            } else {
                this.mDataLoader.setUseCacheData(true);
            }
            this.mDataLoader.setParams(jSONObject);
            this.mDataLoader.showPageOne();
        } catch (JSONException unused) {
        }
        this.mDataLoader.setCommonListener(new CommonListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.4
            @Override // com.jingdong.common.recommend.CommonListener
            public void onResult(RecommendEvent recommendEvent) {
                String type = recommendEvent.getType();
                type.hashCode();
                if (type.equals(RecommendEvent.onInterActiveEnd)) {
                    RecommendProductManager.this.recommendUtil.setInterActiveRecommendList((ArrayList) recommendEvent.param.get("interActive"));
                    RecommendProductManager.this.handleInterActiveRecommendList();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertHomeData(ArrayList<RecommendItem> arrayList, RecommendOtherData recommendOtherData) {
        RecommendHomeData recommendHomeData;
        RecommendItem recommendItem;
        try {
            if (this.recommendUtil.getIsSingleRow() || this.mDataLoader.getPageNo() != 1 || recommendOtherData == null || (recommendHomeData = this.homeData) == null || this.mDataLoader.isInsertHomeData || !recommendHomeData.isHomeDataInsert(recommendOtherData.isFromCache) || (recommendItem = arrayList.get(0)) == null || recommendItem.type == 1000) {
                return;
            }
            arrayList.addAll(0, this.homeData.getHomeRecommendItems());
            this.mDataLoader.isInsertHomeData = true;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isImmediateDelete(RecommendProduct recommendProduct, ArrayList<Integer> arrayList) {
        RecommendUtil recommendUtil;
        return (this.mFrom != 9 || arrayList == null || arrayList.isEmpty() || (recommendUtil = this.recommendUtil) == null || !"1".equals(recommendUtil.getFeedBackImmediateEffect()) || !arrayList.contains(1) || recommendProduct == null || TextUtils.isEmpty(recommendProduct.category3)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jpTest(String str) {
        if (TextUtils.equals("1", str)) {
            RecommendJumpUtils.gotoMWithUrl(this.mActivity.getThisActivity(), null, this.publicTestTopUrl);
        } else if (!TextUtils.equals("2", str)) {
            return;
        } else {
            RecommendJumpUtils.gotoMWithUrl(this.mActivity.getThisActivity(), null, this.publicTestBottomUrl);
        }
        RecommendMtaUtils.jumpPublicTestClickMta(this.mActivity.getThisActivity(), this.mFrom, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noData() {
        this.recommendUtil.setFootState(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRecommendImmediateDelete(RecommendProduct recommendProduct, int i2, String str, ArrayList<Integer> arrayList) {
        onRefreshListDataImmediateRemove(recommendProduct, i2, arrayList);
        onRefershListDataRangeChange(i2, this.mDataList.size() - i2);
        onRecommendLongClickFeedBack(recommendProduct, i2, str);
    }

    private void onRecommendLongClickFeedBack(RecommendProduct recommendProduct, int i2, String str) {
        loadDataInProcess(i2);
        ToastUtils.showToastInCenter(this.mActivity.getThisActivity(), R.drawable.recommend_feedback_icon, this.mActivity.getThisActivity().getResources().getString(R.string.no_recommend_string), 0);
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            recommendDataLoader.recommendFeedBack(recommendProduct.wareId, 0, 2, str, "", "", "", recommendProduct.source, "", recommendProduct.p);
        }
        RecommendMtaUtils.deleteProductClickMta(this.mActivity.getThisActivity(), recommendProduct, this.mFrom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playVideo(final VideoPlayView videoPlayView, String str) {
        if (videoPlayView != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                final int intValue = ((Integer) videoPlayView.getTag(R.id.recommend_vp_left)).intValue();
                setVideoPlayLogoVisibile(intValue, false);
                videoPlayView.setPlaySource(str);
                videoPlayView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.17
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                        try {
                            VideoPlayView videoPlayView2 = videoPlayView;
                            if (videoPlayView2 == null || !videoPlayView2.isPlaying()) {
                                return;
                            }
                            videoPlayView.resetState();
                            if (OKLog.D) {
                                OKLog.e(RecommendProductManager.this.TAG, "onPause videoAutoPlay 3");
                            }
                        } catch (Exception e2) {
                            if (OKLog.D) {
                                e2.printStackTrace();
                            }
                        }
                    }
                });
                videoPlayView.setOnPlayerStateListener(new AVideoPlayStateListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.18
                    @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public void onCompletion() {
                        super.onCompletion();
                        videoPlayView.setCoverUrl(TextUtils.isEmpty(RecommendProductManager.this.tag.imageurl) ? RecommendProductManager.this.tag.imgUrlLocal : RecommendProductManager.this.tag.imageurl);
                        RecommendProductManager.this.setVideoPlayLogoVisibile(intValue, true);
                        RecommendProductManager.this.videoAutoPlay();
                    }

                    @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public boolean onError(int i2, int i3) {
                        RecommendProductManager.this.videoAutoPlay();
                        return super.onError(i2, i3);
                    }
                });
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e("RECOMMEND", e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void productDesJump(RecommendProduct recommendProduct) {
        Uri parse;
        int indexOf;
        if ("0".equals(recommendProduct.isJumpApp)) {
            String str = "";
            try {
                if (recommendProduct.jumpDesUrl.contains("JDFlutterMiniProduct") && recommendProduct.jumpDesUrl.contains("params") && (indexOf = recommendProduct.jumpDesUrl.indexOf("{")) > -1) {
                    JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(recommendProduct.jumpDesUrl.substring(indexOf));
                    JDJSONObject jDJSONObject2 = new JDJSONObject();
                    jDJSONObject2.put("wname", (Object) Uri.encode(recommendProduct.name));
                    jDJSONObject2.put("imageurl", (Object) recommendProduct.imgUrl);
                    jDJSONObject2.put("wareId", (Object) recommendProduct.wareId);
                    jDJSONObject2.put(JshopConst.JSKEY_PRODUCT_JDPRICE, (Object) recommendProduct.jdPrice);
                    jDJSONObject2.put("innerImg", (Object) recommendProduct.innerImg);
                    jDJSONObject2.put("impStyle", (Object) recommendProduct.impStyle);
                    jDJSONObject2.put("innerWareFont", (Object) recommendProduct.innerWareFont);
                    jDJSONObject2.put("innerWareBgImg", (Object) recommendProduct.innerWareBgImg);
                    jDJSONObject.put("firstItemParam", (Object) jDJSONObject2);
                    str = recommendProduct.jumpDesUrl.substring(0, indexOf) + jDJSONObject.toJSONString();
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            if (TextUtils.isEmpty(str)) {
                parse = Uri.parse(recommendProduct.jumpDesUrl);
            } else {
                parse = Uri.parse(str);
            }
            new OpenAppJumpBuilder.Builder(parse).build().jump(this.mActivity.getThisActivity());
        } else {
            DeepLinkMHelper.startWebActivity(this.mActivity.getThisActivity(), recommendProduct.jumpDesUrl);
        }
        RecommendMtaUtils.productClickMta((BaseActivity) this.mActivity, recommendProduct, this.mFrom, this.isFromTips);
    }

    private void refreshCountDownTime(int i2, int i3) {
        onRefreshListDataChanged(i2, 1, Integer.valueOf(i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendVideoPlayTimeExpoMat() {
        VideoPlayView videoPlayView;
        RecommendVideo recommendVideo = this.tag;
        if (recommendVideo == null || !TextUtils.isEmpty(recommendVideo.videoId) || (videoPlayView = this.mVideoPlayView) == null || videoPlayView.getCurrentPosition() / 1000 < 1) {
            return;
        }
        RecommendMtaUtils.videoPlayTimeExpoMat(this.mActivity.getThisActivity(), getVideoPlayExpoParam(this.tag), "", this.mFrom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoPlayLogoVisibile(int i2, boolean z) {
        if (z) {
            onRefreshListDataChanged(i2, 1, Boolean.TRUE);
        } else {
            onRefreshListDataChanged(i2, 1, Boolean.FALSE);
        }
    }

    private void setVideoPlayState(RecyclerView recyclerView) {
        if (JDSettingUtils.isWifiVideoAutoPlay() && !this.notPlay && NetUtils.isWifi() && this.recommendUtil.isVisible() && this.recommendUtil.isHasRecommend()) {
            VideoPlayView videoPlayView = this.mVideoPlayView;
            if (videoPlayView != null) {
                if (videoPlayView.isPlaying()) {
                    if (inScreen(this.mVideoPlayView)) {
                        return;
                    }
                    this.cacheVideo.clear();
                    this.mVideoPlayView.resetState();
                    if (OKLog.D) {
                        OKLog.e(this.TAG, "onPause setVideoPlayState 1");
                    }
                    addNewVideoView(recyclerView);
                    videoAutoPlay();
                    return;
                }
                this.cacheVideo.clear();
                addNewVideoView(recyclerView);
                videoAutoPlay();
                return;
            }
            this.cacheVideo.clear();
            addNewVideoView(recyclerView);
            videoAutoPlay();
        }
    }

    private void setVideoPlayViewVisible(int i2, boolean z) {
        onRefreshListDataChanged(i2, 1, Boolean.valueOf(z));
    }

    private void updateDataLoaderSkus() {
        if (this.mDataLoader == null) {
            return;
        }
        try {
            String[] strArr = this.mSkus;
            if (strArr == null || strArr.length <= 0) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.mSkus;
                if (i2 < strArr2.length) {
                    jSONArray.put(i2, strArr2[i2]);
                    i2++;
                } else {
                    this.mDataLoader.getParams().put("skus", jSONArray);
                    return;
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
    }

    public boolean checkHomePageTestPlanIsPlanA() {
        return "A".equals(this.homePageTestPlan);
    }

    public void clearRecommendData() {
        if (this.mFrom == 18) {
            int i2 = 0;
            this.mFrom = 0;
            this.mSkus = null;
            this.lastPosition = 0;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("source", this.mFrom);
                jSONObject.put("eventId", JDMtaUtils.getLastEventId());
                String[] strArr = this.mSkus;
                if (strArr != null && strArr.length > 0) {
                    JSONArray jSONArray = new JSONArray();
                    while (true) {
                        String[] strArr2 = this.mSkus;
                        if (i2 >= strArr2.length) {
                            break;
                        }
                        jSONArray.put(i2, strArr2[i2]);
                        i2++;
                    }
                    jSONObject.put("skus", jSONArray);
                }
                RecommendDataLoader recommendDataLoader = this.mDataLoader;
                if (recommendDataLoader != null) {
                    recommendDataLoader.setParams(jSONObject);
                }
            } catch (JSONException e2) {
                OKLog.e(this.TAG, e2);
            }
        }
        this.tabId = -1;
        this.mCacheRecommends.clear();
        getRecommendUtil().clearRecommendData();
    }

    public void enableAnimator(boolean z, int i2) {
    }

    public RecyclerView.ViewHolder findHolderForAdapterPostion(int i2) {
        return null;
    }

    public RecommendDataLoader getDataLoader() {
        return this.mDataLoader;
    }

    public int getFrom() {
        return this.mFrom;
    }

    public String getHomePageTestPlan() {
        return ("A".equals(this.homePageTestPlan) || "B".equals(this.homePageTestPlan)) ? this.homePageTestPlan : "B";
    }

    public RecommendUtil getRecommendUtil() {
        return this.recommendUtil;
    }

    protected int getScrollX() {
        return -1;
    }

    public String getSourceExt() {
        return this.sourceExt;
    }

    public Activity getmActivity() {
        return this.mActivity.getThisActivity();
    }

    public boolean hasData() {
        return this.mHasData;
    }

    public boolean isJumpToMini() {
        return !JDElderModeUtils.isElderMode();
    }

    public boolean isLoadFinish() {
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            return recommendDataLoader.isLoadedLastPage();
        }
        return false;
    }

    public boolean isNewVideo() {
        return getFrom() == 6 || getFrom() == 24 || getFrom() == 10024 || getFrom() == 0 || getFrom() == 3 || getFrom() == 4;
    }

    public void jumpPublicTest() {
        jpTest("1");
    }

    protected void loadDataInProcess(int i2) {
        if (this.mDataList.size() > 0 && this.mDataList.size() - i2 < 4) {
            loadRecommendData();
        }
        if (this.mDataList.size() == 0) {
            reSet();
            loadRecommendData();
        }
    }

    public void loadRecommendData() {
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader == null) {
            initPageDataLoader();
            return;
        }
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            recommendDataLoader.setRecommendConfig(recommendUtil.getRecommendConfig());
        }
        this.mDataLoader.tryShowNextPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyDataChanged(int i2, int i3, int i4) {
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null && recommendDataLoader.getPageNo() == 1) {
            onRefreshListData();
        } else {
            callShowNextPageDataRefreshListData(i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAnimationEnable(boolean z) {
    }

    public void onBackToHome() {
        RecommendProduct recommendProduct;
        RecommendDataLoader recommendDataLoader;
        if (!this.isJumpToProductDetail || (recommendProduct = this.interactiveProduct) == null || (recommendDataLoader = this.mDataLoader) == null) {
            return;
        }
        recommendDataLoader.requestInterActive(recommendProduct, this.mSkus);
        this.isJumpToProductDetail = false;
    }

    public synchronized void onBottomPullUp() {
        if (!this.mHasData) {
            RecommendDataLoader recommendDataLoader = this.mDataLoader;
            if (recommendDataLoader == null) {
                initPageDataLoader();
            } else {
                recommendDataLoader.showPageOne();
            }
        }
    }

    protected void onErrorRefreshView() {
        onRefreshListData();
    }

    protected void onRecommendAddCar(RecommendProduct recommendProduct) {
        RecommendMtaUtils.productAddCarMta(this.mActivity.getThisActivity(), recommendProduct, this.mFrom);
        RecommendAddCartUtils.addCart(this.mActivity, recommendProduct.wareId, this.mFrom);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRecommendDataError() {
    }

    protected void onRecommendFeedBackClick(RecommendMaterialData recommendMaterialData, int i2, String str, String str2, String str3) {
        callRecommendLongClickRefreshListData(i2, removeRecommendData(i2));
        loadDataInProcess(i2);
        ToastUtils.showToastInCenter(this.mActivity.getThisActivity(), R.drawable.recommend_feedback_icon, this.mActivity.getThisActivity().getResources().getString(R.string.no_recommend_string), 0);
        if (this.mDataLoader == null || recommendMaterialData == null) {
            return;
        }
        if (!StringUtil.isEmpty(str3)) {
            RecommendNetUtil.recommendFeedBack(str3);
        } else if (!StringUtil.isEmpty(str2)) {
            this.mDataLoader.recommendClickRequest(str2);
        } else {
            this.mDataLoader.recommendFeedBack(recommendMaterialData.wareId, recommendMaterialData.itemType, 2, str, recommendMaterialData.matrt, recommendMaterialData.itemid, recommendMaterialData.cvgsku, recommendMaterialData.source, recommendMaterialData.matrtType, recommendMaterialData.p);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRecommendHeader(RecommendHeaderData recommendHeaderData) {
    }

    protected void onRecommendLongClick(RecommendProduct recommendProduct, int i2, String str) {
        callRecommendLongClickRefreshListData(i2, removeRecommendData(i2));
        onRecommendLongClickFeedBack(recommendProduct, i2, str);
    }

    protected void onRecommendNoData() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRecommendOnePageFinish() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRecommendTabs(RecommendHomeTabs recommendHomeTabs) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRecommendTips(RecommendProduct recommendProduct) {
    }

    protected void onRefershListDataRangeChange(int i2, int i3) {
    }

    protected void onRefershListDataRangeRemove(int i2, int i3) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onRefreshListData();

    protected void onRefreshListDataChanged(int i2, int i3, Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRefreshListDataImmediateRemove(RecommendProduct recommendProduct, int i2, ArrayList<Integer> arrayList) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRefreshListDataRangeInsert(int i2, int i3) {
    }

    protected void onRequestFail(int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRequestSuccess(int i2) {
    }

    public void onScroll(AbsListView absListView, int i2, int i3, int i4, int i5) {
        this.firstVisible = i2;
        this.visibleCount = i3;
        this.totalCount = i4;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        this.mScrollState = i2;
        if (i2 != 0) {
            return;
        }
        setVideoPlayState(recyclerView);
    }

    protected void onSetPreload(boolean z) {
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0139 A[Catch: Exception -> 0x013f, TryCatch #0 {Exception -> 0x013f, blocks: (B:4:0x0007, B:6:0x000e, B:8:0x0018, B:10:0x0026, B:12:0x002c, B:14:0x0030, B:16:0x0036, B:17:0x003c, B:20:0x0066, B:21:0x0069, B:38:0x00c6, B:40:0x00cc, B:42:0x00d2, B:44:0x00da, B:45:0x00e0, B:47:0x00e4, B:48:0x00e6, B:51:0x00f1, B:54:0x00fe, B:56:0x0110, B:58:0x0114, B:60:0x011a, B:62:0x0120, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x0139, B:70:0x013b, B:34:0x00bd, B:36:0x00c1), top: B:91:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected RecommendData parseRecommendData(final JDJSONObject jDJSONObject, HttpResponse httpResponse) {
        JDJSONObject jDJSONObject2;
        RecommendDataLoader recommendDataLoader;
        RecommendDataLoader recommendDataLoader2;
        RecomPerformanceData performanceData;
        RecommendUtil recommendUtil;
        RecomPerformanceData performanceData2;
        RecommendDataLoader recommendDataLoader3;
        if (jDJSONObject != null) {
            try {
                Map<String, Object> moreParams = httpResponse.getMoreParams();
                int intValue = (moreParams == null || !(moreParams.get(RecommendConstant.RECOM_pageNoParamKey) instanceof Integer)) ? -1 : ((Integer) moreParams.get(RecommendConstant.RECOM_pageNoParamKey)).intValue();
                if (!httpResponse.isCache() && (recommendUtil = this.recommendUtil) != null && (performanceData2 = recommendUtil.getPerformanceData(intValue)) != null) {
                    performanceData2.requestEndTime = System.currentTimeMillis();
                }
                RecommendData recommendData = new RecommendData();
                RecommendOtherData recommendOtherData = new RecommendOtherData();
                boolean z = false;
                int optInt = jDJSONObject.optInt("filteredPages", 0);
                int optInt2 = jDJSONObject.optInt("nextPage", 0);
                recommendOtherData.tipsIndex = jDJSONObject.optInt("tipsIndex", -1);
                recommendOtherData.filteredPages = optInt;
                recommendOtherData.nextPage = optInt2;
                if (this.mDataLoader != null && intValue == 1) {
                    dealFirstPageLogic(recommendOtherData, jDJSONObject);
                }
                this.recommendUtil.setFeedBackImmediateEffect(jDJSONObject.optString("feedBackImmediateEffect", "0"));
                this.mActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (RecommendProductManager.this.mDataLoader.isDestoryed) {
                            return;
                        }
                        try {
                            RecommendProductManager.this.onAnimationEnable(!"0".equals(jDJSONObject.optString("feedBackImmediateEffectAnimate")));
                        } catch (Exception e2) {
                            if (OKLog.D) {
                                e2.printStackTrace();
                            }
                        }
                    }
                });
                JDJSONArray jSONArray = jDJSONObject.getJSONArray("wareInfoList");
                String optString = jDJSONObject.optString("title");
                String optString2 = jDJSONObject.optString("dmTitle");
                recommendOtherData.setTitleUrl(optString);
                recommendOtherData.setDarkHeaderTitleUrl(optString2);
                try {
                    if (jDJSONObject.optJSONObject(RecommendMtaUtils.UET) == null || jDJSONObject.optJSONObject(RecommendMtaUtils.UET).optJSONObject(RecommendMtaUtils.TRACKING) == null) {
                        jDJSONObject2 = null;
                    } else {
                        jDJSONObject2 = new JDJSONObject();
                        try {
                            jDJSONObject2.put(RecommendMtaUtils.UET, (Object) jDJSONObject.optJSONObject(RecommendMtaUtils.UET));
                        } catch (Exception e2) {
                            e = e2;
                            if (OKLog.D) {
                                e.printStackTrace();
                            }
                            if (jSONArray == null) {
                            }
                            recommendDataLoader = this.mDataLoader;
                            if (recommendDataLoader != null) {
                                RecommendUtil.reportException("uniformRecommend.WareInfoListIsEmpty", "");
                            }
                            if (recommendOtherData.getWareInfoReasons() != null) {
                                recommendDataLoader2 = this.mDataLoader;
                                if (recommendDataLoader2 != null) {
                                }
                                recommendData.setRecommendOtherData(recommendOtherData);
                                return recommendData;
                            }
                            recommendDataLoader3 = this.mDataLoader;
                            if (recommendDataLoader3 == null) {
                            }
                            return null;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    jDJSONObject2 = null;
                }
                if (jSONArray == null && jSONArray.size() > 0) {
                    if (!httpResponse.isCache() && (performanceData = this.recommendUtil.getPerformanceData(intValue)) != null) {
                        performanceData.count = jSONArray.size();
                    }
                    RecommendDataLoader recommendDataLoader4 = this.mDataLoader;
                    if (recommendDataLoader4 != null) {
                        recommendDataLoader4.hasInit = true;
                    }
                    recommendData.setRecommendOtherData(recommendOtherData);
                    if (this.mFrom == 9 && "1".equals(jDJSONObject.optString("opmShowTimesSwitch"))) {
                        z = true;
                    }
                    recommendData.setRecommendList(toRecomendList(jSONArray, jDJSONObject2, z, jDJSONObject.optString("wareTsFlag").equals("1")));
                    return recommendData;
                }
                recommendDataLoader = this.mDataLoader;
                if (recommendDataLoader != null && recommendDataLoader.isReportException() && !this.mDataLoader.isRequestInterActive) {
                    RecommendUtil.reportException("uniformRecommend.WareInfoListIsEmpty", "");
                }
                if (recommendOtherData.getWareInfoReasons() != null && recommendOtherData.getWareInfoReasons().size() > 0) {
                    recommendDataLoader2 = this.mDataLoader;
                    if (recommendDataLoader2 != null) {
                        recommendDataLoader2.hasInit = true;
                    }
                    recommendData.setRecommendOtherData(recommendOtherData);
                    return recommendData;
                }
            } catch (Exception e4) {
                RecommendDataLoader recommendDataLoader5 = this.mDataLoader;
                if (recommendDataLoader5 != null && recommendDataLoader5.isReportException() && !this.mDataLoader.isRequestInterActive) {
                    RecommendUtil.reportException("uniformRecommend.ComponentsCannotAnalysis", "");
                }
                if (OKLog.E) {
                    OKLog.e(this.TAG, e4);
                }
            }
        }
        recommendDataLoader3 = this.mDataLoader;
        if (recommendDataLoader3 == null && recommendDataLoader3.hasInit) {
            RecommendData recommendData2 = new RecommendData();
            recommendData2.setRecommendList(new ArrayList<>());
            return recommendData2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseVideoSwitch(JDJSONObject jDJSONObject) {
        String optString = jDJSONObject.optString("cancelPlay");
        String optString2 = jDJSONObject.optString("videoWareWifiPlay");
        String optString3 = jDJSONObject.optString("playCompleteState");
        if (TextUtils.equals("1", optString) || TextUtils.equals("0", optString2)) {
            this.notPlay = true;
        }
        if (TextUtils.equals("1", optString2) && TextUtils.equals("1", optString3)) {
            this.replay = true;
        }
    }

    public void reSet() {
        this.tabId = -1;
        this.recommendTab = null;
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            recommendDataLoader.onDestroy();
            this.mDataLoader = null;
        }
        this.recommendUtil.setFootState(0);
        this.recommendUtil.setBgColor("");
        this.recommendUtil.setFeedBackImmediateEffect("0");
        this.recommendUtil.resetDisplayMode();
        this.mHasData = false;
        this.homeData = null;
        this.lastClickFeedBackUiPosition = -1;
    }

    public void reSetHomeSource() {
        this.mFrom = 9;
        this.showLaoding = false;
    }

    public void refresh() {
        reSet();
        onBottomPullUp();
    }

    public void refreshData() {
        this.mHasData = false;
        onBottomPullUp();
    }

    public void refreshListDataChanged(int i2, int i3, Object obj) {
        onRefreshListDataChanged(i2, i3, obj);
    }

    protected int removeRecommendData(int i2) {
        int size = (this.mDataList.size() - 1) - i2;
        if (size <= 0) {
            size = 0;
        }
        if (i2 < this.mDataList.size()) {
            this.mDataList.remove(i2);
        }
        return size;
    }

    public void sendExposureMta() {
        VideoPlayView videoPlayView = this.mVideoPlayView;
        if (videoPlayView != null && videoPlayView.isPlaying()) {
            this.mVideoPlayView.resetState();
            sendVideoPlayTimeExpoMat();
            if (OKLog.D) {
                OKLog.e(this.TAG, "onPause sendExposureMta 1");
            }
        }
        if (this.mFrom == 9) {
            setTipsEvent(new RecommendTipsEvent(false, 0));
        }
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            recommendUtil.sendExposureMta(this.mActivity.getThisActivity());
        }
        if (RecommendFeedBackManger.isfeedManagerValid()) {
            RecommendFeedBackManger.getInstance().dismissPopupWindow();
        }
    }

    public void setBitmapConfig(Bitmap.Config config) {
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            recommendUtil.setBitmapConfig(config);
        }
    }

    public void setDataLoader(RecommendDataLoader recommendDataLoader) {
        this.mDataLoader = recommendDataLoader;
    }

    public void setEnableCache(int i2) {
        this.isEnableCache = i2;
    }

    public void setEnableVideoOffset(int i2) {
        this.enableVideoOffset = i2;
    }

    public void setExpoNum(int i2) {
        ExpoDataStore expoDataStore;
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil == null || (expoDataStore = recommendUtil.getExpoDataStore()) == null) {
            return;
        }
        expoDataStore.setExpoNum(i2);
    }

    public void setExtentParam(HashMap hashMap) {
        this.extentParam = hashMap;
    }

    protected void setHeadText(String str) {
    }

    public void setHomeJsonData(JDJSONObject jDJSONObject, boolean z) {
        JDJSONArray jSONArray;
        RecommendUtil recommendUtil;
        if (jDJSONObject == null || (jSONArray = jDJSONObject.getJSONArray("recommendFloor")) == null || jSONArray.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.size(); i2++) {
            try {
                RecommendItem recommendItem = new RecommendItem();
                recommendItem.setData(jSONArray.getJSONObject(i2));
                if (recommendItem.isShow) {
                    arrayList.add(recommendItem);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
        RecommendHomeData recommendHomeData = new RecommendHomeData(z, arrayList);
        this.homeData = recommendHomeData;
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader == null || recommendDataLoader.isInsertHomeData || !recommendHomeData.isHomeDataInsert(recommendDataLoader.getFromCache()) || (recommendUtil = this.recommendUtil) == null || recommendUtil.getIsSingleRow()) {
            return;
        }
        ArrayList<RecommendItem> recommendItemList = this.recommendUtil.getRecommendItemList();
        if (!this.homeData.hasData() || recommendItemList.size() <= 0) {
            return;
        }
        recommendItemList.addAll(0, arrayList);
        notifyDataChanged(1, 0, recommendItemList.size());
        this.mDataLoader.isInsertHomeData = true;
    }

    public void setHomePageTestPlan(String str) {
        this.homePageTestPlan = str;
    }

    public void setPageSize(int i2) {
        this.mPageSize = i2;
    }

    public void setRecommendConfig(RecommendConfig recommendConfig) {
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            recommendUtil.setRecommendConfig(recommendConfig);
        }
    }

    public void setRecommendParentView(RecyclerView recyclerView) {
        if (RecommendUtils.isRealExpo2()) {
            RecommendExpoHelper recommendExpoHelper = new RecommendExpoHelper(recyclerView);
            this.mOldRecommendExpoHelper = recommendExpoHelper;
            recommendExpoHelper.setRightExpo(true);
        }
    }

    public void setRecommendVisible(boolean z) {
        RecommendUtil recommendUtil = this.recommendUtil;
        if (recommendUtil != null) {
            recommendUtil.setVisible(z);
        }
    }

    public void setSelectedRecommendTab(RecommendTab recommendTab) {
        this.recommendTab = recommendTab;
    }

    public synchronized void setSkus(String[] strArr) {
        this.mSkus = strArr;
        updateDataLoaderSkus();
    }

    public void setSource(int i2) {
        this.mFrom = i2;
        getRecommendUtil().setFrom(this.mFrom);
    }

    public void setSourceExt(String str) {
        this.sourceExt = str;
    }

    public void setTabId(int i2) {
        this.tabId = i2;
    }

    public void setTipsEvent(RecommendTipsEvent recommendTipsEvent) {
        if (recommendTipsEvent == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(this.TAG, recommendTipsEvent.mKey + "---" + recommendTipsEvent.isClick);
        }
        if (recommendTipsEvent.isClick) {
            this.productClick = true;
        } else if (this.productClick) {
            this.productClick = false;
        } else {
            this.isFromTips = recommendTipsEvent.mKey;
        }
    }

    public void setVideoPlayKey(boolean z, boolean z2) {
        this.notPlay = z;
        this.replay = z2;
    }

    public boolean specialProductClick(RecommendProduct recommendProduct, int i2, int i3) {
        return false;
    }

    public ArrayList<RecommendItem> toRecomendList(JDJSONArray jDJSONArray, JDJSONObject jDJSONObject, boolean z, boolean z2) {
        int i2;
        RecommendDna recommendDna;
        ArrayList<RecommendItem> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        int size = jDJSONArray.size();
        while (i2 < size) {
            RecommendItem recommendItem = new RecommendItem();
            try {
                recommendItem.setData(jDJSONArray.getJSONObject(i2), this.mFrom);
            } catch (Exception e2) {
                JdCrashReport.postCaughtException(e2);
            }
            getPlayUrl(recommendItem);
            RecommendProduct recommendProduct = recommendItem.product;
            if (recommendProduct != null) {
                recommendProduct.rootUETJson = jDJSONObject;
                recommendProduct.relationSkus = this.mSkus;
                recommendProduct.isRecomInfoDown = z2;
            }
            if (z) {
                String[] expoID_Num = RecommendUtils.getExpoID_Num();
                if (recommendItem.type == 26 && (recommendDna = recommendItem.dna) != null && recommendItem.opmShowTimes > 0 && !TextUtils.isEmpty(recommendDna.dnaId) && recommendItem.dna.dnaId.equals(expoID_Num[0])) {
                    try {
                    } catch (Exception e3) {
                        if (OKLog.D) {
                            e3.printStackTrace();
                        }
                    }
                    i2 = Integer.parseInt(expoID_Num[1]) >= recommendItem.opmShowTimes ? i2 + 1 : 0;
                }
            }
            if (this.recommendUtil.getIsSingleRow()) {
                if (recommendItem.type == 0) {
                    recommendItem.type = 11440;
                    arrayList.add(recommendItem);
                }
            } else if (recommendItem.isShow) {
                arrayList.add(recommendItem);
            }
        }
        return arrayList;
    }

    public void videoAutoPlay() {
        if (this.cacheVideo.isEmpty()) {
            return;
        }
        final VideoPlayView videoAutoPlayPosition = getVideoAutoPlayPosition();
        VideoPlayView videoPlayView = this.mVideoPlayView;
        if (videoPlayView != null && videoPlayView.isPlaying()) {
            this.mVideoPlayView.resetState();
        }
        this.mVideoPlayView = videoAutoPlayPosition;
        if (videoAutoPlayPosition == null || videoAutoPlayPosition.getTag() == null || !(videoAutoPlayPosition.getTag() instanceof RecommendVideo)) {
            return;
        }
        RecommendVideo recommendVideo = (RecommendVideo) videoAutoPlayPosition.getTag();
        this.tag = recommendVideo;
        if (this.mDataLoader != null && recommendVideo != null && (!TextUtils.isEmpty(recommendVideo.videoId) || !TextUtils.isEmpty(this.tag.videoSummaryUrl))) {
            if (videoAutoPlayPosition != null) {
                videoAutoPlayPosition.setCoverUrl(TextUtils.isEmpty(this.tag.imageurl) ? this.tag.imgUrlLocal : this.tag.imageurl);
            }
            if (!TextUtils.isEmpty(this.tag.videoSummaryUrl)) {
                playVideo(videoAutoPlayPosition, this.tag.videoSummaryUrl);
            } else {
                this.mDataLoader.addHttpRequest(RecommendDataLoader.getVideoPlayUrl(this.tag, videoAutoPlayPosition.hashCode(), new RecommendDataLoader.OnRecommendGetVideoUrlListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.13
                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onError() {
                        RecommendProductManager.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.13.2
                            @Override // java.lang.Runnable
                            public void run() {
                                RecommendProductManager.this.videoAutoPlay();
                            }
                        });
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(final String str, final int i2) {
                        RecommendProductManager.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.13.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass13 anonymousClass13 = AnonymousClass13.this;
                                if (RecommendProductManager.this.inScreen(videoAutoPlayPosition)) {
                                    RecommendProductManager recommendProductManager = RecommendProductManager.this;
                                    if (recommendProductManager.inScreen(recommendProductManager.mVideoPlayView) && RecommendProductManager.this.mScrollState == 0 && RecommendProductManager.this.mVideoPlayView.hashCode() == i2) {
                                        if (RecommendProductManager.this.mVideoPlayView != null && RecommendProductManager.this.mVideoPlayView.isPlaying()) {
                                            RecommendProductManager.this.mVideoPlayView.resetState();
                                        }
                                        AnonymousClass13 anonymousClass132 = AnonymousClass13.this;
                                        RecommendProductManager.this.playVideo(videoAutoPlayPosition, str);
                                    }
                                }
                            }
                        });
                    }

                    @Override // com.jingdong.common.recommend.RecommendDataLoader.OnRecommendGetVideoUrlListener
                    public void onSucceed(String str, String str2) {
                    }
                }));
            }
        }
        RecommendVideo recommendVideo2 = this.tag;
        if (recommendVideo2 == null || TextUtils.isEmpty(recommendVideo2.playUrl)) {
            return;
        }
        if (isNewVideo()) {
            if (this.mVideoPlayView.getParent() == null || RecommendViewUtil.getCurrentExpoPercent((View) this.mVideoPlayView.getParent()) < 0.5d || this.mScrollState != 0) {
                return;
            }
        } else if (!inScreen(videoAutoPlayPosition) || !inScreen(this.mVideoPlayView) || this.mScrollState != 0) {
            return;
        }
        VideoPlayView videoPlayView2 = this.mVideoPlayView;
        if (videoPlayView2 != null && videoPlayView2.isPlaying()) {
            this.mVideoPlayView.resetState();
            if (OKLog.D) {
                OKLog.e(this.TAG, "onPause videoAutoPlay 4");
            }
        }
        int intValue = ((Integer) videoAutoPlayPosition.getTag(R.id.recommend_vp_left)).intValue();
        videoAutoPlayPosition.setLoop(this.replay);
        videoAutoPlayPosition.setPlaySource(this.tag.playUrl);
        setVideoPlayViewVisible(intValue, true);
        videoAutoPlayPosition.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.14
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                VideoPlayView videoPlayView3 = videoAutoPlayPosition;
                if (videoPlayView3 == null || !videoPlayView3.isPlaying()) {
                    return;
                }
                RecommendProductManager.this.sendVideoPlayTimeExpoMat();
                videoAutoPlayPosition.resetState();
                if (OKLog.D) {
                    OKLog.e(RecommendProductManager.this.TAG, "onPause videoAutoPlay 5");
                }
            }
        });
        videoAutoPlayPosition.setProgrssChangeListener(new IProgrssChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.15
            @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
            public void onProgressChange(int i2, int i3) {
            }

            @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
            public void onProgressPointSelect(int i2) {
            }
        });
        videoAutoPlayPosition.setOnPlayerStateListener(new AVideoPlayStateListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductManager.16
            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                super.onCompletion();
                RecommendProductManager recommendProductManager = RecommendProductManager.this;
                if (recommendProductManager.replay) {
                    return;
                }
                recommendProductManager.videoAutoPlay();
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                RecommendProductManager.this.videoAutoPlay();
                return super.onError(i2, i3);
            }

            @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (i2 == 30003) {
                    RecommendProductManager.this.sendVideoPlayTimeExpoMat();
                }
                return super.onInfo(i2, i3);
            }
        });
    }

    protected void onRecommendAddCar(RecommendProduct recommendProduct, String str) {
        RecommendMtaUtils.productAddCarMta(this.mActivity.getThisActivity(), recommendProduct, str);
        RecommendAddCartUtils.addCart(this.mActivity, recommendProduct.wareId, this.mFrom);
    }

    public void onScrollStateChanged(AbsListView absListView, int i2) {
        this.mScrollState = i2;
        if (i2 != 0) {
            return;
        }
        setVideoPlayState(absListView);
        addVideoView(absListView);
        videoAutoPlay();
    }

    public void onScroll(RecyclerView recyclerView, int i2, int i3, int i4, int i5) {
        this.firstVisible = i2;
        this.visibleCount = i3;
        this.totalCount = i4;
    }

    public void loadRecommendData(String[] strArr) {
        this.mSkus = strArr;
        updateDataLoaderSkus();
        loadRecommendData();
    }

    private boolean inScreen(SimpleDraweeView simpleDraweeView) {
        if (simpleDraweeView != null) {
            int height = simpleDraweeView.getHeight();
            int viewLocationY = getViewLocationY(simpleDraweeView);
            int i2 = ((viewLocationY + height) - this.statusBarHeight) - this.titleHeight;
            int i3 = height / 2;
            return i2 > i3 && (this.screenHeight - this.bottomHeight) - viewLocationY > i3;
        }
        return false;
    }

    private void setVideoPlayState(AbsListView absListView) {
        if (JDSettingUtils.isWifiVideoAutoPlay() && !this.notPlay && NetUtils.isWifi() && this.recommendUtil.isVisible() && this.recommendUtil.isHasRecommend()) {
            VideoPlayView videoPlayView = this.mVideoPlayView;
            if (videoPlayView != null) {
                if (videoPlayView.isPlaying()) {
                    if (OKLog.D) {
                        OKLog.e("isPlaying--", JDHPlayerJSEvent.IS_PLAYING);
                    }
                    if (inScreen(this.mVideoPlayView)) {
                        return;
                    }
                    this.mVideoPlayView.resetState();
                    if (OKLog.D) {
                        OKLog.e(this.TAG, "onPause setVideoPlayState 3");
                    }
                } else {
                    this.mVideoPlayView.resetState();
                    if (OKLog.D) {
                        OKLog.e(this.TAG, "onPause setVideoPlayState 4");
                    }
                }
            }
            this.cacheVideo.clear();
        }
    }
}
