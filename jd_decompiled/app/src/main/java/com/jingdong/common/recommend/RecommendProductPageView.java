package com.jingdong.common.recommend;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.alibaba.fastjson.parser.Feature;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.TypeToken;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.entity.RecommendData;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendGuide;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendMaterialData;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendPromotion;
import com.jingdong.common.recommend.entity.RecommendShop;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.forlist.RecommendViewHolder;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendProductPageView extends ListView {
    public static final String EMPTY_REC_INVISIBLE = "emptyRecInvisible";
    public static final String EMPTY_REC_VISIBLE = "emptyRecVisible";
    public static final int FROM_BROWSERHISTORY = 15;
    public static final int FROM_CONFIRMGOODSSUCCESS = 13;
    public static final int FROM_HOME_PAGE = 9;
    public static final int FROM_MYCONCERN = 1;
    public static final int FROM_MYJD = 0;
    public static final int FROM_ORDER_DETAIL = 3;
    public static final int FROM_ORDER_TRACE = 4;
    public static final int FROM_PAYSUCCESS = 2;
    public static final int FROM_SHOPPINGCAR = 6;
    public static final int FROM_VIRTUAL_ORDERS = 17;
    protected final int FOOTER_END;
    protected final int FOOTER_ERROR;
    protected final int FOOTER_NODATA;
    protected final int FOOTER_NORMAL;
    private final String TAG;
    private LinearLayout endLayout;
    private String expid;
    private RecommendProduct feedbackProduct;
    private LinearLayout footerLayout;
    private TextView footerTestinBtn;
    private int lastPost;
    private LinearLayout loadingLayout;
    private BaseActivity mActivity;
    private RecommendProductAdapter mAdapter;
    private RecommendDataLoader mDataLoader;
    private int mFrom;
    private boolean mHasData;
    private View mHead;
    private TextView mHeadText;
    private boolean mIsPaging;
    private float mLastY;
    private Handler mMainHandler;
    private String[] mSkus;
    private int maxHeight;
    private int minHeight;
    private SimpleDraweeView onlineImg;
    private ScrollView parentScrollView;
    private String publicTest;
    private String rid;
    private TextView testin;
    private LinearLayout testinLayout;

    public RecommendProductPageView(Context context) {
        super(context);
        this.TAG = RecommendProductPageView.class.getSimpleName();
        this.minHeight = DPIUtil.dip2px(120.0f);
        this.mHasData = false;
        this.mLastY = -1.0f;
        this.FOOTER_NORMAL = 0;
        this.FOOTER_ERROR = 1;
        this.FOOTER_END = 2;
        this.FOOTER_NODATA = 3;
        this.lastPost = -99;
        setFocusable(false);
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealExpoView() {
        try {
            if (RecommendUtils.isRealExpo2() && this.mFrom == 13) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    Object tag = childAt.getTag(R.id.recommend_item_bean);
                    if (tag instanceof RecommendViewHolder.RecommendLineTwoBean) {
                        RecommendViewHolder.RecommendLineTwoBean recommendLineTwoBean = (RecommendViewHolder.RecommendLineTwoBean) tag;
                        if (!recommendLineTwoBean.hasRecommendExpo && RecommendViewUtil.getCurrentExpoPercent(childAt) >= 0.5d) {
                            Iterator<RecommendItem> it = recommendLineTwoBean.recommendItemBeans.iterator();
                            while (it.hasNext()) {
                                RecommendItem next = it.next();
                                next.expoData.getnowtime();
                                RecommendMtaUtils.realProductExpo(this.mFrom, getContext(), next.expoData);
                            }
                            recommendLineTwoBean.hasRecommendExpo = true;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    private String getHeadText(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 20) {
                return str.substring(0, 19) + "...";
            }
            return str;
        }
        return getResources().getString(R.string.recommend_head_text);
    }

    private void initPageDataLoader() {
        this.mDataLoader = new RecommendDataLoader(this.mActivity, this) { // from class: com.jingdong.common.recommend.RecommendProductPageView.3
            boolean hasInit = false;

            @Override // com.jingdong.common.recommend.RecommendDataLoader, com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                super.onEnd(httpResponse);
                if (this.hasInit && !RecommendProductPageView.this.mHasData) {
                    RecommendProductPageView.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.3.5
                        @Override // java.lang.Runnable
                        public void run() {
                            if (RecommendProductPageView.this.mIsPaging) {
                                if (RecommendProductPageView.this.parentScrollView != null) {
                                    RecommendProductPageView recommendProductPageView = RecommendProductPageView.this;
                                    recommendProductPageView.setHeight(recommendProductPageView.getMaxHeight());
                                }
                            } else {
                                RecommendProductPageView.this.setListViewHeightBasedOnChildren();
                            }
                            RecommendProductPageView.this.mHasData = true;
                        }
                    });
                }
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageEnd(boolean z) {
                if (z) {
                    if (RecommendProductPageView.this.mAdapter == null || RecommendProductPageView.this.mAdapter.getCount() <= 0) {
                        RecommendProductPageView.this.noData();
                        return;
                    }
                    if (OKLog.D) {
                        OKLog.d(RecommendProductPageView.this.TAG, "mAdapter.getCount():" + RecommendProductPageView.this.mAdapter.getCount());
                    }
                    RecommendProductPageView.this.setFootState(2);
                }
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageErr() {
                if (RecommendProductPageView.this.mAdapter != null && RecommendProductPageView.this.mAdapter.getCount() == 0) {
                    RecommendProductPageView.this.noData();
                } else {
                    RecommendProductPageView.this.setFootState(1);
                }
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected void onOnePageLoading() {
                RecommendProductPageView.this.setFootState(0);
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected boolean showNextPageData(ArrayList<?> arrayList, RecommendOtherData recommendOtherData) {
                if (RecommendProductPageView.this.mAdapter != null) {
                    if (RecommendProductPageView.this.mDataLoader == null || this.pageNo != 1) {
                        RecommendProductPageView.this.mAdapter.addList(arrayList, false);
                    } else {
                        RecommendProductPageView.this.mAdapter.addList(arrayList, true);
                    }
                    RecommendProductPageView.this.mAdapter.notifyDataSetChanged();
                } else {
                    RecommendProductPageView.this.mAdapter = new RecommendProductAdapter(RecommendProductPageView.this.mActivity, arrayList, RecommendProductPageView.this.mFrom);
                    if (recommendOtherData != null) {
                        if (recommendOtherData.getExposeNum() != 0) {
                            RecommendProductPageView.this.setExpoNum(recommendOtherData.getExposeNum());
                        }
                        if (RecommendProductPageView.this.mAdapter.getRecommendUtil() != null) {
                            RecommendProductPageView.this.mAdapter.getRecommendUtil().setRecommendGuide(recommendOtherData);
                            RecommendProductPageView.this.publicTest = recommendOtherData.getPublicTest();
                        }
                        if (recommendOtherData.getTitleUrl() != null) {
                            RecommendProductPageView.this.setHeader(recommendOtherData.getTitleUrl(), recommendOtherData.getPublicTest());
                        }
                    }
                    RecommendProductPageView.this.mAdapter.setOnRecommendClickedListener(new RecommendUtil.OnRecommendClickedListener() { // from class: com.jingdong.common.recommend.RecommendProductPageView.3.3
                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onAddCarClick(RecommendProduct recommendProduct) {
                            RecommendProductPageView.this.onRecommendAddCar(recommendProduct);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onAggregationClick(RecommendDna recommendDna) {
                            RecommendJumpUtils.onJumpAggregation(recommendDna, RecommendProductPageView.this.mActivity, RecommendProductPageView.this.mFrom);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendDna.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onChannelUnderJump(String str, String str2, String str3) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onDotMoreMta(int i2, String str) {
                            RecommendMtaUtils.newFloatLayerMta(RecommendProductPageView.this.mActivity, RecommendProductPageView.this.mFrom, i2, str);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onEnterPromotionClick(RecommendPromotion recommendPromotion) {
                            RecommendJumpUtils.onEnterPromotion(RecommendProductPageView.this.mActivity, recommendPromotion, RecommendProductPageView.this.mFrom, recommendPromotion.extension_id);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendPromotion.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onEnterShopClick(RecommendShop recommendShop) {
                            RecommendJumpUtils.onEnterShop(RecommendProductPageView.this.mActivity, recommendShop, RecommendProductPageView.this.mFrom);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendShop.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onGeneClick(RecommendDna recommendDna) {
                            RecommendJumpUtils.onJumpGene(RecommendProductPageView.this.mActivity, recommendDna, RecommendProductPageView.this.mFrom);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendDna.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onJumpPublicTest(String str) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onNoRecommendClick(RecommendProduct recommendProduct, int i2, String str, ArrayList<Integer> arrayList2) {
                            RecommendProductPageView.this.onRecommendLongClick(recommendProduct, i2, str);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onNoRecommendMaterialClick(RecommendMaterialData recommendMaterialData, int i2, String str, ArrayList<Integer> arrayList2, String str2, String str3) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onProductClick(RecommendProduct recommendProduct) {
                            RecommendJumpUtils.onRecommendStartProductDetailActivity(RecommendProductPageView.this.mActivity, recommendProduct, RecommendProductPageView.this.mFrom, 0);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onProductClick(RecommendProduct recommendProduct, RecommendItem recommendItem) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendChannelJump(RecommendDna recommendDna) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendDetalis(RecommendDetails recommendDetails) {
                            RecommendJumpUtils.onJumpDetalis(recommendDetails, RecommendProductPageView.this.mActivity, RecommendProductPageView.this.mFrom);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendDetails.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendJump(String str, String str2) {
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendMoneyExpo(String str) {
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(str);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendReasonMta(String str) {
                            RecommendMtaUtils.deleteProductResonClickMta(RecommendProductPageView.this.mActivity, str, RecommendProductPageView.this.mFrom);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRecommendVideoClick(RecommendVideo recommendVideo) {
                            RecommendMtaUtils.aggregationClickMtaRealize(RecommendProductPageView.this.mActivity, recommendVideo.sourceValue, RecommendProductPageView.this.mFrom, recommendVideo.extension_id);
                            if (RecommendProductPageView.this.mDataLoader != null) {
                                RecommendProductPageView.this.mDataLoader.recommendClickRequest(recommendVideo.client_click_url);
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onRefresh() {
                            if (RecommendProductPageView.this.mAdapter != null) {
                                RecommendProductPageView.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onShowFeedbackUi(RecommendProduct recommendProduct, int i2) {
                            if (RecommendProductPageView.this.mAdapter != null) {
                                if (RecommendProductPageView.this.feedbackProduct != null) {
                                    RecommendProductPageView.this.feedbackProduct.isShowFeedbackUi = false;
                                }
                                if (recommendProduct != null) {
                                    recommendProduct.isShowFeedbackUi = true;
                                    RecommendProductPageView.this.feedbackProduct = recommendProduct;
                                }
                                RecommendProductPageView.this.lastPost = i2;
                                RecommendProductPageView.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onSimilarClick(RecommendProduct recommendProduct) {
                            RecommendJumpUtils.onRecommendStartSimilarActivity(RecommendProductPageView.this.mActivity, recommendProduct, RecommendProductPageView.this.mFrom, 0);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onAddCarClick(RecommendProduct recommendProduct, String str) {
                            RecommendProductPageView.this.onRecommendAddCar(recommendProduct, str);
                        }

                        @Override // com.jingdong.common.recommend.forlist.RecommendUtil.OnRecommendClickedListener
                        public void onProductClick(RecommendProduct recommendProduct, String str) {
                            RecommendJumpUtils.onRecommendStartProductDetailActivity(RecommendProductPageView.this.mActivity, recommendProduct, RecommendProductPageView.this.mFrom, str);
                        }
                    });
                    RecommendProductPageView recommendProductPageView = RecommendProductPageView.this;
                    recommendProductPageView.setAdapter((ListAdapter) recommendProductPageView.mAdapter);
                    RecommendProductPageView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            RecommendProductPageView.this.dealExpoView();
                        }
                    });
                }
                return true;
            }

            @Override // com.jingdong.common.recommend.RecommendDataLoader
            protected RecommendData toList(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null) {
                    try {
                        RecommendData recommendData = new RecommendData();
                        RecommendOtherData recommendOtherData = new RecommendOtherData();
                        int optInt = fastJsonObject.optInt("filteredPages", 0);
                        int optInt2 = fastJsonObject.optInt("nextPage", 0);
                        recommendOtherData.filteredPages = optInt;
                        recommendOtherData.nextPage = optInt2;
                        if (RecommendProductPageView.this.mDataLoader != null && RecommendProductPageView.this.mDataLoader.getPageNo() == 1) {
                            recommendOtherData.setExposeNum(fastJsonObject.optInt("exposeNum", 100));
                            String optString = fastJsonObject.optString("fbWizard");
                            if (!TextUtils.isEmpty(optString)) {
                                recommendOtherData.setRecommendGuide((RecommendGuide) JDJSON.parseObject(optString, new TypeToken<RecommendGuide>() { // from class: com.jingdong.common.recommend.RecommendProductPageView.3.1
                                }.getType(), new Feature[0]));
                            }
                            recommendOtherData.setPublicTest(fastJsonObject.optString("publicTest", "0"));
                            recommendOtherData.setPublicTestBubble(fastJsonObject.optString("publicTestBubble", "0"));
                        }
                        String optString2 = fastJsonObject.optString("title");
                        String optString3 = fastJsonObject.optString("dmTitle");
                        recommendOtherData.setTitleUrl(optString2);
                        recommendOtherData.setDarkHeaderTitleUrl(optString3);
                        RecommendProductPageView.this.expid = fastJsonObject.optString(PDConstant.EXTRA_EXPID);
                        RecommendProductPageView.this.rid = fastJsonObject.optString("p");
                        JDJSONArray jSONArray = fastJsonObject.getJSONArray("wareInfoList");
                        if (jSONArray != null && jSONArray.size() > 0) {
                            RecommendProductPageView.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (RecommendProductPageView.this.getVisibility() != 0) {
                                        RecommendProductPageView.this.setVisibility(0);
                                        EventBus.getDefault().post(new BaseEvent(RecommendProductPageView.EMPTY_REC_VISIBLE));
                                    }
                                }
                            });
                            this.hasInit = true;
                            recommendData.setRecommendOtherData(recommendOtherData);
                            recommendData.setRecommendList(RecommendProductPageView.this.toRecomendList(jSONArray));
                            return recommendData;
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(RecommendProductPageView.this.TAG, e2);
                        }
                    }
                }
                if (this.hasInit) {
                    RecommendData recommendData2 = new RecommendData();
                    recommendData2.setRecommendList(new ArrayList<>());
                    return recommendData2;
                }
                return null;
            }
        };
        setScrollListenerCallback(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.recommend.RecommendProductPageView.4
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                RecommendProductPageView.this.onRecommendScroll(i2, i3, i4);
                if (RecommendProductPageView.this.lastPost == -99) {
                    return;
                }
                if (RecommendProductPageView.this.lastPost >= (i2 - 1) * 2) {
                    double d = RecommendProductPageView.this.lastPost;
                    Double.isNaN(d);
                    if (d / 2.0d <= ((double) ((i2 + i3) - 1))) {
                        return;
                    }
                }
                if (RecommendProductPageView.this.feedbackProduct != null) {
                    RecommendProductPageView.this.feedbackProduct.isShowFeedbackUi = false;
                    if (RecommendProductPageView.this.mAdapter != null) {
                        RecommendProductPageView.this.lastPost = -99;
                        RecommendProductPageView.this.mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                if (i2 == 0) {
                    RecommendProductPageView.this.dealExpoView();
                }
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("source", this.mFrom);
            this.mDataLoader.setBusinessFrom(this.mFrom);
            if (this.mFrom == 9) {
                jSONObject.put("needRecomTips", 1);
            }
            jSONObject.put("eventId", JDMtaUtils.getLastEventId());
            String[] strArr = this.mSkus;
            if (strArr != null && strArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                int i2 = 0;
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
            this.mDataLoader.setParams(jSONObject);
            this.mDataLoader.showPageOne();
        } catch (JSONException unused) {
        }
    }

    private void initView() {
        this.mMainHandler = new Handler(Looper.getMainLooper());
        setFadingEdgeLength(0);
        setCacheColorHint(0);
        setScrollingCacheEnabled(false);
        setDivider(new ColorDrawable(0));
        setVerticalScrollBarEnabled(false);
        setSelector(R.color.transparent);
        setLayoutParams(new AbsListView.LayoutParams(-1, this.minHeight));
        addHeader();
        addFooter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noData() {
        setFootState(3);
    }

    private synchronized void onBottomPullUp() {
        if (!this.mHasData) {
            RecommendDataLoader recommendDataLoader = this.mDataLoader;
            if (recommendDataLoader == null) {
                initPageDataLoader();
            } else if (recommendDataLoader != null) {
                recommendDataLoader.showPageOne();
            }
        }
    }

    private void setParentScrollAble(boolean z) {
        ScrollView scrollView = this.parentScrollView;
        if (scrollView == null) {
            return;
        }
        scrollView.requestDisallowInterceptTouchEvent(!z);
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

    public void addFooter() {
        if (this.footerLayout != null) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.new_recommend_footer, null);
        this.footerLayout = linearLayout;
        this.loadingLayout = (LinearLayout) linearLayout.findViewById(R.id.recommend_footer_loading_layout);
        this.endLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_end_ll_layout);
        this.testinLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_testin_ll_layout);
        this.footerTestinBtn = (TextView) this.footerLayout.findViewById(R.id.recommend_footer_test_inbtn);
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        textView.setText("\u52a0\u8f7d\u4e2d...");
        textView.setTextColor(Color.parseColor("#848689"));
        layoutParams.leftMargin = DPIUtil.dip2px(4.0f);
        layoutParams.gravity = 16;
        this.loadingLayout.addView(textView, layoutParams);
        TextView textView2 = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        textView2.setText("\u6ca1\u6709\u66f4\u591a\u4e86~");
        textView2.setTextSize(11.0f);
        textView2.setTextColor(Color.parseColor("#BFBFBF"));
        layoutParams2.topMargin = DPIUtil.dip2px(5.0f);
        this.endLayout.addView(textView2, layoutParams2);
        this.footerTestinBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.RecommendProductPageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        addFooterView(this.footerLayout, null, false);
    }

    public void addHeader() {
        if (getHeaderViewsCount() == 0) {
            View inflate = ImageUtil.inflate(R.layout.recommend_head, null);
            this.mHead = inflate;
            this.onlineImg = (SimpleDraweeView) inflate.findViewById(R.id.online_layout_img);
            this.testin = (TextView) this.mHead.findViewById(R.id.recommend_testin_btn);
            addHeaderView(this.mHead, null, false);
        }
    }

    public int getMaxHeight() {
        ScrollView scrollView;
        if (this.maxHeight == 0 && (scrollView = this.parentScrollView) != null) {
            this.maxHeight = scrollView.getHeight();
        }
        return this.maxHeight;
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public boolean hasData() {
        return this.mHasData;
    }

    public synchronized void init(BaseActivity baseActivity, ScrollView scrollView, int i2, String[] strArr) {
        init(baseActivity, scrollView, i2, strArr, true);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ScrollView scrollView;
        int action = motionEvent.getAction();
        if (action != 0) {
            if ((action == 1 || action == 3) && getLayoutParams().height < getMaxHeight()) {
                setParentScrollAble(true);
            }
        } else if (getLayoutParams().height == getMaxHeight() && (scrollView = this.parentScrollView) != null && scrollView.getScrollY() >= getTop() - 1) {
            setParentScrollAble(false);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected void onRecommendAddCar(RecommendProduct recommendProduct) {
        RecommendMtaUtils.productAddCarMta(this.mActivity, recommendProduct, this.mFrom);
        RecommendAddCartUtils.addCart(this.mActivity, recommendProduct.wareId, this.mFrom);
    }

    protected void onRecommendAddCar(RecommendProduct recommendProduct, String str) {
    }

    protected void onRecommendLongClick(RecommendProduct recommendProduct, int i2, String str) {
        if (this.mAdapter.getList().size() - i2 < 4) {
            this.mDataLoader.tryShowNextPage();
        }
        if (i2 < this.mAdapter.getList().size()) {
            this.mAdapter.getList().remove(i2);
        }
        this.mAdapter.notifyDataSetChanged();
        BaseActivity baseActivity = this.mActivity;
        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, baseActivity.getResources().getString(R.string.no_recommend_string), 0);
        if (this.mAdapter.getList().size() == 0) {
            reSet();
            onBottomPullUp();
        }
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            recommendDataLoader.recommendFeedBack(recommendProduct.wareId, 0, 2, str, "", "", "", recommendProduct.source, "", recommendProduct.p);
        }
        RecommendMtaUtils.deleteProductClickMta(this.mActivity, recommendProduct, this.mFrom);
    }

    protected void onRecommendScroll(int i2, int i3, int i4) {
    }

    public void onResume() {
        if (this.mAdapter == null || getHeight() != getMaxHeight()) {
            return;
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != 3) goto L31;
     */
    @Override // android.widget.AbsListView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r4.mLastY
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto Le
            float r0 = r5.getRawY()
            r4.mLastY = r0
        Le:
            int r0 = r5.getAction()
            if (r0 == 0) goto L5e
            r2 = 1
            if (r0 == r2) goto L5b
            r3 = 2
            if (r0 == r3) goto L1e
            r2 = 3
            if (r0 == r2) goto L5b
            goto L64
        L1e:
            float r0 = r5.getRawY()
            float r1 = r4.mLastY
            float r0 = r0 - r1
            int r0 = (int) r0
            float r1 = r5.getRawY()
            r4.mLastY = r1
            boolean r1 = r4.hasData()
            if (r1 == 0) goto L53
            com.jingdong.common.recommend.RecommendProductAdapter r1 = r4.mAdapter
            if (r1 == 0) goto L53
            int r1 = r4.getFirstVisiblePosition()
            if (r1 != 0) goto L64
            int r1 = r4.getChildCount()
            if (r1 <= 0) goto L64
            r1 = 0
            android.view.View r1 = r4.getChildAt(r1)
            int r1 = r1.getTop()
            if (r1 != 0) goto L64
            if (r0 < 0) goto L64
            r4.setParentScrollAble(r2)
            goto L64
        L53:
            com.jingdong.common.recommend.RecommendProductAdapter r0 = r4.mAdapter
            if (r0 != 0) goto L64
            r4.setParentScrollAble(r2)
            goto L64
        L5b:
            r4.mLastY = r1
            goto L64
        L5e:
            float r0 = r5.getRawY()
            r4.mLastY = r0
        L64:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendProductPageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void reSet() {
        setHeight(getMinHeight());
        this.mAdapter = null;
        setAdapter((ListAdapter) null);
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            recommendDataLoader.onDestroy();
            this.mDataLoader = null;
        }
        setFootState(0);
        this.mHasData = false;
    }

    public void refresh() {
        reSet();
        onBottomPullUp();
    }

    public void sendExposureMta() {
        RecommendUtil recommendUtil;
        RecommendProductAdapter recommendProductAdapter = this.mAdapter;
        if (recommendProductAdapter == null || (recommendUtil = recommendProductAdapter.getRecommendUtil()) == null) {
            return;
        }
        recommendUtil.sendExposureMta(this.mActivity);
    }

    public void setBitmapConfig(Bitmap.Config config) {
        RecommendUtil recommendUtil;
        RecommendProductAdapter recommendProductAdapter = this.mAdapter;
        if (recommendProductAdapter == null || (recommendUtil = recommendProductAdapter.getRecommendUtil()) == null) {
            return;
        }
        recommendUtil.setBitmapConfig(config);
    }

    public void setExpoNum(int i2) {
        RecommendUtil recommendUtil;
        ExpoDataStore expoDataStore;
        RecommendProductAdapter recommendProductAdapter = this.mAdapter;
        if (recommendProductAdapter == null || (recommendUtil = recommendProductAdapter.getRecommendUtil()) == null || (expoDataStore = recommendUtil.getExpoDataStore()) == null) {
            return;
        }
        expoDataStore.setExpoNum(i2);
    }

    public void setFootState(final int i2) {
        this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.6
            @Override // java.lang.Runnable
            public void run() {
                if (RecommendProductPageView.this.getFooterViewsCount() == 0 || RecommendProductPageView.this.footerLayout == null || RecommendProductPageView.this.loadingLayout == null || RecommendProductPageView.this.endLayout == null) {
                    return;
                }
                int i3 = i2;
                if (i3 == 0) {
                    RecommendProductPageView.this.loadingLayout.setVisibility(0);
                    RecommendProductPageView.this.endLayout.setVisibility(8);
                    RecommendProductPageView.this.testinLayout.setVisibility(8);
                } else if (i3 == 1) {
                    if (RecommendProductPageView.this.mAdapter != null && RecommendProductPageView.this.mAdapter.getCount() != 0) {
                        RecommendProductPageView.this.loadingLayout.setVisibility(8);
                        RecommendProductPageView.this.endLayout.setVisibility(8);
                        RecommendProductPageView.this.testinLayout.setVisibility(8);
                        return;
                    }
                    RecommendProductPageView.this.setVisibility(8);
                } else if (i3 != 2) {
                    if (i3 != 3) {
                        return;
                    }
                    RecommendProductPageView.this.setVisibility(8);
                } else {
                    RecommendProductPageView.this.loadingLayout.setVisibility(8);
                    if (TextUtils.equals("1", RecommendProductPageView.this.publicTest)) {
                        RecommendProductPageView.this.endLayout.setVisibility(8);
                        RecommendProductPageView.this.testinLayout.setVisibility(0);
                        return;
                    }
                    RecommendProductPageView.this.endLayout.setVisibility(0);
                    RecommendProductPageView.this.testinLayout.setVisibility(8);
                }
            }
        });
    }

    public void setHeadText(String str) {
        TextView textView = this.mHeadText;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setHeader(final String str, final String str2) {
        this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.5
            @Override // java.lang.Runnable
            public void run() {
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                int i2 = R.drawable.recommend_headimg;
                JDDisplayImageOptions showImageForEmptyUri = createSimple.showImageOnFail(i2).showImageForEmptyUri(i2);
                if (!TextUtils.isEmpty(str)) {
                    JDImageUtils.displayImage(str, RecommendProductPageView.this.onlineImg, showImageForEmptyUri, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.RecommendProductPageView.5.1
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str3, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                            JDImageUtils.displayImage("res:///" + R.drawable.recommend_headimg, RecommendProductPageView.this.onlineImg);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str3, View view) {
                        }
                    });
                } else {
                    JDImageUtils.displayImage("res:///" + i2, RecommendProductPageView.this.onlineImg);
                }
                if (TextUtils.equals("1", str2)) {
                    RecommendProductPageView.this.testin.setVisibility(0);
                } else {
                    RecommendProductPageView.this.testin.setVisibility(8);
                }
            }
        });
    }

    public void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    public void setListViewHeightBasedOnChildren() {
        if (this.mAdapter == null) {
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAdapter.getCount(); i3++) {
            View view = this.mAdapter.getView(i3, null, this);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            i2 += view.getMeasuredHeight();
        }
        setHeight(i2 + (getDividerHeight() * (this.mAdapter.getCount() - 1)) + DPIUtil.dip2px(138.0f));
    }

    @Override // android.view.View
    @TargetApi(9)
    public void setOverScrollMode(int i2) {
        super.setOverScrollMode(2);
    }

    public void setScrollListenerCallback(AbsListView.OnScrollListener onScrollListener) {
        RecommendDataLoader recommendDataLoader = this.mDataLoader;
        if (recommendDataLoader != null) {
            recommendDataLoader.setScrollListenerCallback(onScrollListener);
        }
    }

    public void setSelectionTop() {
        this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendProductPageView.2
            @Override // java.lang.Runnable
            public void run() {
                RecommendProductPageView.this.setSelection(0);
            }
        });
    }

    public synchronized void setSkus(String[] strArr) {
        this.mSkus = strArr;
        updateDataLoaderSkus();
    }

    public void setSource(int i2) {
        this.mFrom = i2;
    }

    public ArrayList<RecommendItem> toRecomendList(JDJSONArray jDJSONArray) {
        ArrayList<RecommendItem> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            RecommendItem recommendItem = new RecommendItem();
            recommendItem.setData(jDJSONArray.getJSONObject(i2));
            if (recommendItem.isShow) {
                arrayList.add(recommendItem);
            }
        }
        return arrayList;
    }

    public synchronized void init(BaseActivity baseActivity, ScrollView scrollView, int i2, String[] strArr, boolean z) {
        if (scrollView == null && z) {
            setLayoutParams(new AbsListView.LayoutParams(-1, -1));
        }
        if (i2 == 1) {
            setBackgroundColor(baseActivity.getResources().getColor(R.color.recommend_bg_color));
        }
        this.mActivity = baseActivity;
        this.parentScrollView = scrollView;
        this.mFrom = i2;
        this.mSkus = strArr;
        this.mIsPaging = z;
        if (this.mDataLoader == null) {
            setAdapter((ListAdapter) null);
        }
        onBottomPullUp();
    }

    public RecommendProductPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = RecommendProductPageView.class.getSimpleName();
        this.minHeight = DPIUtil.dip2px(120.0f);
        this.mHasData = false;
        this.mLastY = -1.0f;
        this.FOOTER_NORMAL = 0;
        this.FOOTER_ERROR = 1;
        this.FOOTER_END = 2;
        this.FOOTER_NODATA = 3;
        this.lastPost = -99;
        setFocusable(false);
        initView();
    }
}
