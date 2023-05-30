package com.jingdong.common.sample.jshop.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.sample.jshop.adapter.JshopSearchListAdapter;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopClearFocusListener;
import com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader;
import com.jingdong.common.sample.jshop.utils.JshopSearchListListener;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.common.sample.json.JshopSearch;
import com.jingdong.common.sample.json.JshopSearchItem;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.JShopUtils;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopSearchListFragment extends BaseFragment implements JshopSearchListListener, JShopUtils.JShopStartRequestListener, JShopUtils.JShopFragmentStateListener {
    private static final String ERROR_TYPE_NETWORK = "1";
    private static final String ERROR_TYPE_NO_DATA = "2";
    private static final int PAGE_SIZE = 20;
    private static final String TAG = "JshopSearchListFragment";
    private BaseActivity mActivity;
    private JshopClearFocusListener mClearFocusListener;
    private Button mErrorLoadingButton;
    private TextView mErrorTv1;
    private TextView mErrorTv2;
    private TextView mErrorTv3;
    private boolean mFragmentIsShow;
    private String mHighRelateCatid;
    private boolean mIsCancelInitRequest;
    private JShopUtils mJShopUtils;
    private int mLastDarkModel;
    private int mMaxVisiblePos;
    private int mMinVisiblePos;
    private ImageView mNoDataImage;
    private LinearLayout mNoDataLayout;
    private String mPvid;
    private View mRootView;
    private JShopUtils.JShopListScrollListener mScrollListener;
    private SourceEntity mSource;
    private boolean mStartRequestMActivityIsNull;
    private String mTest;
    private JshopFastjsonNextPageLoader nextPageLoader;
    private int previousPosition;
    private ListView shopList;
    private ImageView toTopView;
    public int imageWidth = (DPIUtil.getWidth() - DPIUtil.dip2px(40.0f)) / 3;
    Handler handler = new Handler();
    private String mErrorType = "";
    private String mKeyWord = "";
    private String mKeyType = "";
    private String mCategoryId = "";
    private String mDeviceId = "";
    private String mTestId = "";
    private String mSourcePage = "1";
    private int mHeadHeight = -1;
    private ArrayList<Long> adKeys = new ArrayList<>();
    private DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener = new DeepDarkChangeManager.OnUIModeChangeListener() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.1
        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            Log.d(JshopSearchListFragment.TAG, "uiMode = " + i2);
            if (i2 == -1 || i2 == JshopSearchListFragment.this.mLastDarkModel) {
                return;
            }
            JshopSearchListFragment.this.updateBackgroundColor();
            JshopSearchListFragment.this.mLastDarkModel = i2;
        }
    };

    private void createHeadView() {
        if (this.shopList.getHeaderViewsCount() > 0 || this.mHeadHeight <= 0) {
            return;
        }
        View view = new View(getActivity());
        view.setBackgroundColor(getResources().getColor(R.color.lu));
        view.setLayoutParams(new AbsListView.LayoutParams(1, this.mHeadHeight));
        this.shopList.addHeaderView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void errorClickMta() {
        JDMtaUtils.sendCommonData(getContext(), "Searchlist_Retry", this.mErrorType, "", this, "", "", "", "SearchShop_ResultMain", "");
    }

    private void errorExportMta(String str) {
        this.mErrorType = str;
        JDMtaUtils.sendCommonData(getContext(), "Searchlist_RetryExpo", str, "", this, "", "", "", "SearchShop_ResultMain", "");
    }

    private void initErrorView(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_empty);
        this.mNoDataLayout = linearLayout;
        this.mNoDataImage = (ImageView) linearLayout.findViewById(R.id.bz);
        TextView textView = (TextView) this.mNoDataLayout.findViewById(R.id.c0);
        this.mErrorTv1 = textView;
        textView.setTextColor(getResources().getColor(R.color.e0));
        TextView textView2 = (TextView) this.mNoDataLayout.findViewById(R.id.c1);
        this.mErrorTv2 = textView2;
        textView2.setVisibility(8);
        TextView textView3 = (TextView) this.mNoDataLayout.findViewById(R.id.c2);
        this.mErrorTv3 = textView3;
        textView3.setVisibility(8);
        Button button = (Button) this.mNoDataLayout.findViewById(R.id.bw);
        this.mErrorLoadingButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                JshopSearchListFragment.this.showJdNewsList();
                JshopSearchListFragment.this.errorClickMta();
            }
        });
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(this.mErrorTv1, this.mErrorTv2, this.mErrorTv3, this.mErrorLoadingButton);
        }
    }

    private void obtainVisiblePos() {
        ListView listView = this.shopList;
        if (listView != null) {
            this.mMinVisiblePos = listView.getFirstVisiblePosition();
            this.mMaxVisiblePos = this.shopList.getLastVisiblePosition();
        }
    }

    private void onFragmentShow() {
        obtainVisiblePos();
        sendUrlMta();
    }

    private void sendExposureMTA() {
        JshopFastjsonNextPageLoader jshopFastjsonNextPageLoader = this.nextPageLoader;
        if (jshopFastjsonNextPageLoader != null && jshopFastjsonNextPageLoader.getAllProductList() != null && !this.nextPageLoader.getAllProductList().isEmpty()) {
            int size = this.nextPageLoader.getAllProductList().size();
            int i2 = this.mMaxVisiblePos;
            if (i2 >= size) {
                return;
            }
            if (this.mMinVisiblePos == 0 && i2 == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i3 = this.mMinVisiblePos; i3 <= this.mMaxVisiblePos; i3++) {
                JshopSearchItem jshopSearchItem = (JshopSearchItem) this.nextPageLoader.getAllProductList().get(i3);
                if (jshopSearchItem != null) {
                    boolean z = jshopSearchItem.adShop;
                    sb.append(jshopSearchItem.shopId + "-" + i3 + "-" + this.mSourcePage + "-" + JShopUtil.getMtaString(this.mPvid) + "-" + JShopUtil.getMtaString(jshopSearchItem.logid) + "-" + JShopUtil.getMtaString(jshopSearchItem.p13nFeature1) + "-" + (z ? 1 : 0));
                    if (i3 < this.mMaxVisiblePos) {
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                    }
                    if (jshopSearchItem.getProducts() != null && jshopSearchItem.getProducts().size() > 0) {
                        int size2 = jshopSearchItem.getProducts().size();
                        for (int i4 = 0; i4 < size2; i4++) {
                            Product product = jshopSearchItem.getProducts().get(i4);
                            if (product != null) {
                                sb2.append(jshopSearchItem.shopId + "-" + product.getId() + "-" + i4 + "-" + i3 + "-" + this.mSourcePage + "-" + product.getJdPrice() + "-" + JShopUtil.getMtaString(this.mPvid) + "-" + JShopUtil.getMtaString(jshopSearchItem.logid) + "-" + (z ? 1 : 0));
                                if (i3 != this.mMaxVisiblePos || i4 != size2 - 1) {
                                    sb2.append(CartConstant.KEY_YB_INFO_LINK);
                                }
                            }
                        }
                    }
                }
            }
            if (TextUtils.isEmpty(sb)) {
                return;
            }
            String str = this.mKeyWord + "@@@" + JShopUtil.getMtaString(this.mTest);
            JDMtaUtils.sendExposureData(this.thisActivity, this, "SearchShop_ResultMain", str, "Searchlist_ShopidExpo", sb.toString(), "", "", "");
            JDMtaUtils.sendExposureData(this.thisActivity, this, "SearchShop_ResultMain", str, "Searchlist_ShopProductExpo", sb2.toString(), "", "", "");
        }
        this.mMaxVisiblePos = 0;
        this.mMinVisiblePos = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUrlMta() {
        JshopFastjsonNextPageLoader jshopFastjsonNextPageLoader = this.nextPageLoader;
        if (jshopFastjsonNextPageLoader == null || jshopFastjsonNextPageLoader.getAllProductList() == null || this.nextPageLoader.getAllProductList().isEmpty()) {
            return;
        }
        int size = this.nextPageLoader.getAllProductList().size();
        int i2 = this.mMaxVisiblePos;
        if (i2 < size) {
            int i3 = this.mMinVisiblePos;
            if (i3 == 0 && i2 == 0) {
                return;
            }
            while (i3 <= this.mMaxVisiblePos) {
                JshopSearchItem jshopSearchItem = (JshopSearchItem) this.nextPageLoader.getAllProductList().get(i3);
                if (jshopSearchItem != null && jshopSearchItem.adShop && i3 < this.mMaxVisiblePos && this.adKeys.indexOf(jshopSearchItem.shopId) == -1) {
                    this.adKeys.add(jshopSearchItem.shopId);
                    JShopUtil.mtaUploadWithRequestUrl(jshopSearchItem.exposalUrl);
                }
                i3++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmpty() {
        if (this.mNoDataLayout.getVisibility() == 0 && "2".equals(this.mErrorType)) {
            return;
        }
        this.mNoDataLayout.setVisibility(0);
        this.shopList.setVisibility(8);
        this.mNoDataImage.setBackgroundResource(R.drawable.y_04);
        this.mErrorTv1.setVisibility(0);
        this.mErrorTv1.setText(R.string.jshop_no_search_result);
        this.mErrorTv2.setVisibility(8);
        this.mErrorTv3.setVisibility(8);
        this.mErrorLoadingButton.setVisibility(0);
        this.mErrorLoadingButton.setText(getResources().getString(R.string.jshop_data_load));
        errorExportMta("2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError1() {
        if (this.mNoDataLayout.getVisibility() == 0 && "1".equals(this.mErrorType)) {
            return;
        }
        this.mNoDataLayout.setVisibility(0);
        this.shopList.setVisibility(8);
        this.mErrorTv1.setVisibility(0);
        this.mErrorTv2.setVisibility(0);
        this.mErrorTv3.setVisibility(8);
        this.mErrorLoadingButton.setVisibility(0);
        this.mNoDataImage.setBackgroundResource(R.drawable.y_03);
        this.mErrorTv1.setText(getResources().getString(R.string.jshop_net_fail));
        this.mErrorTv2.setText(getResources().getString(R.string.jshop_net_check));
        this.mErrorLoadingButton.setText(getResources().getString(R.string.jshop_net_load));
        errorExportMta("1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showJdNewsList() {
        this.mStartRequestMActivityIsNull = false;
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.loading, null);
        linearLayout.setGravity(17);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("abtest", this.mTestId);
            if (TextUtils.isEmpty(this.mKeyWord)) {
                this.mKeyWord = "";
            }
            if (TextUtils.isEmpty(this.mKeyType)) {
                this.mKeyType = "";
            }
            jSONObject.put(JshopConst.JSHOP_SEARCH_KEYWORD, this.mKeyWord);
            jSONObject.put(JshopConst.JSHOP_SEARCH_LIST_KEYTYPE, this.mKeyType);
            jSONObject.put("sourceRpc", "0".equals(this.mSourcePage) ? "shop_app_searchshop_list" : "shop_app_search_tab3");
            if (!TextUtils.isEmpty(this.mHighRelateCatid)) {
                jSONObject.put("highRelateId", this.mHighRelateCatid);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JshopFastjsonNextPageLoader jshopFastjsonNextPageLoader = new JshopFastjsonNextPageLoader(this.mActivity, this.shopList, linearLayout, "searchShops", jSONObject) { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.5
            private boolean hasNext = true;
            private String testId = DYConstants.DY_NULL_STR;

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            protected MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList) {
                JshopSearchListAdapter jshopSearchListAdapter = new JshopSearchListAdapter(iMyActivity, arrayList, R.layout.jshop_searchlist_item_v50, new String[0], new int[0]);
                jshopSearchListAdapter.keyword = JshopSearchListFragment.this.mKeyWord;
                jshopSearchListAdapter.mSource = JshopSearchListFragment.this.mSource;
                jshopSearchListAdapter.mCategoryId = JshopSearchListFragment.this.mCategoryId;
                jshopSearchListAdapter.mDeviceId = JshopSearchListFragment.this.mDeviceId;
                jshopSearchListAdapter.mSourcePage = JshopSearchListFragment.this.mSourcePage;
                jshopSearchListAdapter.mTestId = JshopSearchListFragment.this.mTestId;
                jshopSearchListAdapter.mTestIdV665 = this.testId;
                jshopSearchListAdapter.mPvid = JshopSearchListFragment.this.mPvid;
                return jshopSearchListAdapter;
            }

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            protected boolean judgeIsLastPage(ArrayList<?> arrayList) {
                return !this.hasNext;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                super.onScroll(absListView, i2, i3, i4);
                if (JshopSearchListFragment.this.mScrollListener != null) {
                    JshopSearchListFragment.this.mScrollListener.onScroll(absListView, i2, i3, i4);
                }
                if (JshopSearchListFragment.this.toTopView != null) {
                    if (i2 > 12) {
                        JshopSearchListFragment.this.toTopView.setVisibility(0);
                    } else {
                        JshopSearchListFragment.this.toTopView.setVisibility(4);
                    }
                }
                if (absListView.getFirstVisiblePosition() < JshopSearchListFragment.this.mMinVisiblePos) {
                    JshopSearchListFragment.this.mMinVisiblePos = absListView.getFirstVisiblePosition();
                }
                if (absListView.getLastVisiblePosition() > JshopSearchListFragment.this.mMaxVisiblePos) {
                    JshopSearchListFragment.this.mMaxVisiblePos = absListView.getLastVisiblePosition();
                }
                Log.d(JshopSearchListFragment.TAG, "onScroll (mMinVisiblePos:" + JshopSearchListFragment.this.mMinVisiblePos + " mMaxVisiblePos:" + JshopSearchListFragment.this.mMaxVisiblePos + ")");
                JshopSearchListFragment.this.sendUrlMta();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                super.onScrollStateChanged(absListView, i2);
                if (JshopSearchListFragment.this.mScrollListener != null) {
                    JshopSearchListFragment.this.mScrollListener.onScrollStateChanged(absListView, i2);
                }
            }

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            public void setSelection(int i2) {
            }

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            protected void showEmpty(final boolean z) {
                JshopSearchListFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            if (getAllProductList() == null || getAllProductList().size() < 1) {
                                JshopSearchListFragment.this.showEmpty();
                            }
                        } else if (getAllProductList() == null || getAllProductList().size() < 1) {
                        } else {
                            JshopSearchListFragment.this.mNoDataLayout.setVisibility(8);
                            JshopSearchListFragment.this.shopList.setVisibility(0);
                        }
                    }
                });
            }

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            protected void showError(final boolean z) {
                if (Log.D) {
                    Log.d("JdNewsListActivity", "showError() -->> ?");
                }
                JshopSearchListFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            if (getAllProductList() == null || getAllProductList().size() < 1) {
                                JshopSearchListFragment.this.showError1();
                            }
                        } else if (getAllProductList() == null || getAllProductList().size() < 1) {
                        } else {
                            JshopSearchListFragment.this.mNoDataLayout.setVisibility(8);
                            JshopSearchListFragment.this.shopList.setVisibility(0);
                        }
                    }
                });
            }

            @Override // com.jingdong.common.sample.jshop.utils.JshopFastjsonNextPageLoader
            protected ArrayList<?> toList(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                try {
                    fastJsonObject.optInt("code");
                    if (fastJsonObject.optBoolean("isSuccess")) {
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("result");
                        if (optJSONObject != null) {
                            optJSONObject.optInt("pageIdx");
                            optJSONObject.optInt("pageSize");
                            optJSONObject.optInt("totalCount");
                            optJSONObject.optInt("totalPage");
                            this.hasNext = optJSONObject.optBoolean("hasNext");
                            this.testId = optJSONObject.optString(NotificationMessageSummary.TEST_ID);
                            JshopSearchListFragment.this.mPvid = optJSONObject.optString("pvid");
                            JshopSearchListFragment.this.mTest = optJSONObject.optString("mtest");
                            if (getParams() != null && !TextUtils.isEmpty(JshopSearchListFragment.this.mPvid)) {
                                getParams().put("pvid", JshopSearchListFragment.this.mPvid);
                            }
                        }
                        final JshopSearch jshopSearch = new JshopSearch(optJSONObject);
                        JshopSearchListFragment.this.handler.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (getAllProductList() != null && getAllProductList().size() >= 1) {
                                    if (JshopSearchListFragment.this.mClearFocusListener != null) {
                                        JshopSearchListFragment.this.mClearFocusListener.onClearFocus();
                                    }
                                    JshopSearchListFragment.this.shopList.requestFocus();
                                }
                                ArrayList<JshopSearchItem> arrayList = jshopSearch.shopList;
                                if (arrayList == null || arrayList.size() < 1) {
                                    if (getAllProductList() == null || getAllProductList().size() < 1) {
                                        JshopSearchListFragment.this.showEmpty();
                                    }
                                }
                            }
                        });
                        return jshopSearch.shopList;
                    }
                    return null;
                } catch (Exception e3) {
                    if (Log.D) {
                        Log.d(JshopSearchListFragment.TAG, "JSONException -->> ", e3);
                    }
                    return null;
                }
            }
        };
        this.nextPageLoader = jshopFastjsonNextPageLoader;
        jshopFastjsonNextPageLoader.setPageSize(20);
        this.nextPageLoader.setHttpNotifyUser(false);
        this.nextPageLoader.setHost(Configuration.getJshopHost());
        this.nextPageLoader.showPageOne(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBackgroundColor() {
        if (1 == DeepDarkChangeManager.getInstance().getUIMode()) {
            Log.d(TAG, "####DeepDarkChangeManager.MODE_DARK##");
            this.mRootView.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
            this.shopList.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
            this.mRootView.setBackgroundColor(getResources().getColor(R.color.lu));
            this.shopList.setBackgroundColor(getResources().getColor(R.color.lu));
        }
    }

    void initView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.jshop_shop_list);
        this.shopList = listView;
        listView.requestFocus();
        this.shopList.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                Log.d(JshopSearchListFragment.TAG, "onScroll toTopView = ");
                JshopSearchListFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JshopSearchListFragment.this.mClearFocusListener != null) {
                            JshopSearchListFragment.this.mClearFocusListener.onClearFocus();
                        }
                    }
                });
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
        createHeadView();
        initErrorView(view);
        this.toTopView = (ImageView) view.findViewById(R.id.home_to_top);
        Log.d(TAG, "toTopView = " + this.toTopView);
        this.toTopView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JshopSearchListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (JshopSearchListFragment.this.shopList != null) {
                    JshopSearchListFragment.this.shopList.setSelection(0);
                }
            }
        });
        if (!this.mIsCancelInitRequest || this.mStartRequestMActivityIsNull) {
            showJdNewsList();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            this.mActivity = (BaseActivity) activity;
        }
        this.mSourcePage = "com.jd.lib.searchshop.JshopSearchListActivity".equals(activity.getClass().getName()) ? "0" : "1";
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this.onUIModeChangeListener);
        setIsUseBasePV(false);
        if (getArguments() != null) {
            SourceEntity sourceEntity = (SourceEntity) getArguments().getSerializable("source");
            if (sourceEntity != null) {
                this.mSource = sourceEntity;
                if (Log.D) {
                    System.out.println(sourceEntity);
                }
            } else if (Log.D) {
                System.err.println("JshopSearchListFragment SourceEntity = null");
            }
            this.mKeyWord = getArguments().getString("keyword");
            this.mKeyType = getArguments().getString(JshopConst.JSHOP_SEARCH_LIST_KEYTYPE);
            this.mCategoryId = getArguments().getString("categoryId");
            this.mDeviceId = CommonBase.getStringFromPreference("searchDeviceId", "");
            this.mHeadHeight = (int) getArguments().getFloat(JshopConst.JSHOP_SEARCH_LIST_HEAD_HEIGHT, -1.0f);
            this.mTestId = getArguments().getString(JshopConst.KEY_TEST_ID);
            this.mIsCancelInitRequest = getArguments().getBoolean(JshopConst.JSHOP_KEY_IS_CANCEL_INIT_REQUEST);
            this.mHighRelateCatid = getArguments().getString("highRelateCatid");
            Log.d(TAG, " mKeyWord  ==  " + this.mKeyWord + " , mKeyType == " + this.mKeyType + " , mCategoryId == " + this.mCategoryId + " , mDeviceId == " + this.mDeviceId + " , mHeadHeight == " + this.mHeadHeight + " , mTestId == " + this.mTestId);
        }
        JShopUtils jShopUtilsInstance = JShopUtils.getJShopUtilsInstance();
        this.mJShopUtils = jShopUtilsInstance;
        this.mScrollListener = jShopUtilsInstance.getScrollListener();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            View inflate = layoutInflater.inflate(R.layout.jshop_search_list_fragment, (ViewGroup) null);
            this.mRootView = inflate;
            initView(inflate);
            updateBackgroundColor();
        }
        return this.mRootView;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this.onUIModeChangeListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.adKeys.clear();
    }

    @Override // com.jingdong.common.sample.jshop.utils.JshopSearchListListener
    public void onFragmentPause() {
        this.previousPosition = this.shopList.getFirstVisiblePosition();
    }

    @Override // com.jingdong.common.sample.jshop.utils.JshopSearchListListener
    public void onFragmentResume() {
        ArrayList<?> allProductList;
        JshopFastjsonNextPageLoader jshopFastjsonNextPageLoader = this.nextPageLoader;
        if (jshopFastjsonNextPageLoader == null || (allProductList = jshopFastjsonNextPageLoader.getAllProductList()) == null || allProductList.size() <= 0) {
            return;
        }
        this.shopList.setSelection(this.previousPosition);
    }

    @Override // com.jingdong.jdsdk.utils.JShopUtils.JShopFragmentStateListener
    public void onFragmentStateChanged(boolean z) {
        if (!z) {
            if (this.mFragmentIsShow) {
                sendExposureMTA();
                this.mFragmentIsShow = false;
                return;
            }
            return;
        }
        this.mFragmentIsShow = true;
        onFragmentShow();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        onFragmentShow();
    }

    @Override // com.jingdong.common.sample.jshop.utils.JshopSearchListListener
    public void onShowJDShopList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mKeyWord = str;
        showJdNewsList();
        this.shopList.requestFocus();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if ("0".equals(this.mSourcePage)) {
            sendExposureMTA();
        } else if ("1".equals(this.mSourcePage) && this.mFragmentIsShow) {
            sendExposureMTA();
        }
    }

    public void setJshopClearFocusListener(JshopClearFocusListener jshopClearFocusListener) {
        this.mClearFocusListener = jshopClearFocusListener;
    }

    @Override // com.jingdong.jdsdk.utils.JShopUtils.JShopStartRequestListener
    public void startRequest() {
        JshopFastjsonNextPageLoader jshopFastjsonNextPageLoader = this.nextPageLoader;
        if (jshopFastjsonNextPageLoader == null || jshopFastjsonNextPageLoader.getAllProductList() == null || this.nextPageLoader.getAllProductList().size() < 1) {
            if (this.mActivity == null) {
                this.mStartRequestMActivityIsNull = true;
            } else {
                showJdNewsList();
            }
        }
    }
}
