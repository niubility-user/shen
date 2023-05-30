package com.jingdong.common.sample.jshopmember.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.TwoProduct;
import com.jingdong.common.sample.jshopmember.JshopMemberActivity;
import com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter;
import com.jingdong.common.sample.jshopmember.entity.JshopCustomer;
import com.jingdong.common.sample.jshopmember.entity.JshopProduct;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopMemberView extends LinearLayout {
    public static final int END_TYPE = 2;
    public static final int ERR_TYPE = 4;
    public static final int LOADING_TYPE = 1;
    public static final int NONE_TYPE = 3;
    public static final String TAG = "JshopMemberRuleView";
    private View endBtootmView;
    private LinearLayout endLayout;
    private LinearLayout errorLayout;
    private LinearLayout footerLayout;
    public boolean isMember;
    public boolean isNeedLoadNext;
    private LinearLayout loadingLayout;
    private JShopMemberRecommendAdapter mAdapter;
    public Context mContext;
    public boolean mHasNext;
    private JshopMemberHeaderView mHeader;
    private ListView mListView;
    private View.OnClickListener mMainOnClickListener;
    public int mPage;
    public int mPageSize;
    public JSONObject mParam;
    public AbsListView.OnScrollListener mScrollListner;
    public int saleType;

    public JshopMemberView(Context context) {
        super(context);
        this.mHasNext = false;
        this.mPage = 1;
        this.mPageSize = 20;
        this.isMember = false;
        this.saleType = 0;
        this.mContext = context;
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoading() {
        LinearLayout linearLayout = this.loadingLayout;
        return linearLayout != null && linearLayout.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Product> parseDataFromJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.saleType = jDJSONObject.optInt("saleType");
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("wareInfo");
            if (optJSONArray != null && optJSONArray.size() > 0) {
                ArrayList<Product> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                    JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        JshopProduct jshopProduct = new JshopProduct();
                        jshopProduct.setName(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME));
                        jshopProduct.setId(Long.valueOf(parseLong(optJSONObject.optString("wareId"))));
                        jshopProduct.setImage(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH), "");
                        jshopProduct.setJdPrice(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                        jshopProduct.setMarketPrice(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_MPRICE));
                        jshopProduct.setCustomerPrice(optJSONObject.optString("customerPrice"));
                        jshopProduct.setMiaoSha(Boolean.valueOf(optJSONObject.optInt(JshopConst.JSKEY_PRODUCT_FLASHSALE) == 1));
                        arrayList.add(jshopProduct);
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    public JshopMemberHeaderView getMemberHeaderView() {
        return this.mHeader;
    }

    public void gotoTop() {
        if (this.mListView.getSelectedItemPosition() != 0) {
            this.mListView.setSelection(0);
        }
    }

    public void hiddenGuildTip() {
        JShopMemberRecommendAdapter jShopMemberRecommendAdapter = this.mAdapter;
        if (jShopMemberRecommendAdapter != null) {
            jShopMemberRecommendAdapter.hiddenGuildTip();
        }
    }

    public void initClick() {
        JshopMemberHeaderView jshopMemberHeaderView;
        View.OnClickListener onClickListener = this.mMainOnClickListener;
        if (onClickListener == null || (jshopMemberHeaderView = this.mHeader) == null) {
            return;
        }
        jshopMemberHeaderView.setMainClickListener(onClickListener);
    }

    public void initView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.jshop_member_recommend, this);
        this.mAdapter = new JShopMemberRecommendAdapter(this.mContext);
        Log.d(TAG, "context  = " + this.mContext.getClass().toString());
        this.mListView = (ListView) findViewById(R.id.member_recommend_list);
        JshopMemberHeaderView jshopMemberHeaderView = new JshopMemberHeaderView(this.mContext);
        this.mHeader = jshopMemberHeaderView;
        jshopMemberHeaderView.setVisibility(4);
        this.mListView.addHeaderView(this.mHeader);
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.jshop_member_recommend_footer, null);
        this.footerLayout = linearLayout;
        this.loadingLayout = (LinearLayout) linearLayout.findViewById(R.id.recommend_loading_layout);
        this.errorLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_error_layout);
        this.endLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_end_layout);
        this.endBtootmView = this.footerLayout.findViewById(R.id.end_bottom);
        this.mListView.addFooterView(this.footerLayout);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberView.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                JshopMemberView jshopMemberView = JshopMemberView.this;
                jshopMemberView.isNeedLoadNext = i2 + i3 == i4;
                AbsListView.OnScrollListener onScrollListener = jshopMemberView.mScrollListner;
                if (onScrollListener != null) {
                    onScrollListener.onScroll(absListView, i2, i3, i4);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                if (JshopMemberView.this.isNeedLoadNext) {
                    Log.d(JshopMemberView.TAG, "isNeedLoadNext = " + JshopMemberView.this.isNeedLoadNext);
                    if (!JshopMemberView.this.isLoading()) {
                        JshopMemberView jshopMemberView = JshopMemberView.this;
                        if (!jshopMemberView.mHasNext) {
                            if (jshopMemberView.mAdapter != null && JshopMemberView.this.mAdapter.getCount() > 0) {
                                JshopMemberView.this.setFooterState(2);
                            } else {
                                JshopMemberView.this.setFooterState(3);
                            }
                        } else {
                            jshopMemberView.setFooterState(1);
                            try {
                                JshopMemberView jshopMemberView2 = JshopMemberView.this;
                                JSONObject jSONObject = jshopMemberView2.mParam;
                                int i3 = jshopMemberView2.mPage + 1;
                                jshopMemberView2.mPage = i3;
                                jSONObject.put("pageIdx", i3);
                                JshopMemberView jshopMemberView3 = JshopMemberView.this;
                                jshopMemberView3.mParam.put("pageSize", jshopMemberView3.mPageSize);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            JshopMemberView jshopMemberView4 = JshopMemberView.this;
                            jshopMemberView4.sendRecommendRequest((JshopMemberActivity) jshopMemberView4.mContext, jshopMemberView4.mParam, false);
                        }
                    }
                }
                AbsListView.OnScrollListener onScrollListener = JshopMemberView.this.mScrollListner;
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(absListView, i2);
                }
            }
        });
    }

    public void onDestory() {
        hiddenGuildTip();
    }

    public long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public void removeFooter() {
        if (this.mListView.getFooterViewsCount() > 0) {
            this.mListView.removeFooterView(this.footerLayout);
        }
    }

    public void reset() {
        JShopMemberRecommendAdapter jShopMemberRecommendAdapter = this.mAdapter;
        if (jShopMemberRecommendAdapter != null) {
            jShopMemberRecommendAdapter.clear();
        }
    }

    public void sendRecommendRequest(MyActivity myActivity, JSONObject jSONObject, boolean z) {
        this.mParam = jSONObject;
        JshopMemberUtils.sendHttpRequest(myActivity, "getCustomerSku", jSONObject, false, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberView.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JshopMemberView.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JshopMemberView.this.mHeader.getVisibility() != 0) {
                            JshopMemberView.this.mHeader.setVisibility(0);
                        }
                        JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("result");
                        ArrayList<TwoProduct> list = TwoProduct.toList(JshopMemberView.this.parseDataFromJson(optJSONObject));
                        Context context = JshopMemberView.this.mContext;
                        if (context instanceof JshopMemberActivity) {
                            ((JshopMemberActivity) context).showBottomFloatView(optJSONObject);
                            ((JshopMemberActivity) JshopMemberView.this.mContext).setShopVIP_VIPParam(list != null ? !list.isEmpty() : false);
                        }
                        JshopMemberView jshopMemberView = JshopMemberView.this;
                        if (jshopMemberView.saleType == 1) {
                            jshopMemberView.mAdapter.guildIconId = R.drawable.jshop_pop_text_04;
                            JshopMemberView.this.mAdapter.fansIconId = R.drawable.jshop_text_exclusive_price;
                        } else {
                            jshopMemberView.mAdapter.guildIconId = R.drawable.jshop_pop_text_03;
                            JshopMemberView.this.mAdapter.fansIconId = R.drawable.jshop_text_fans_price;
                        }
                        JShopMemberRecommendAdapter jShopMemberRecommendAdapter = JshopMemberView.this.mAdapter;
                        JshopMemberView jshopMemberView2 = JshopMemberView.this;
                        jShopMemberRecommendAdapter.isMember = jshopMemberView2.isMember;
                        JShopMemberRecommendAdapter jShopMemberRecommendAdapter2 = jshopMemberView2.mAdapter;
                        JshopMemberView jshopMemberView3 = JshopMemberView.this;
                        jShopMemberRecommendAdapter2.saleType = jshopMemberView3.saleType;
                        jshopMemberView3.mAdapter.setData(list);
                        if (optJSONObject != null) {
                            JshopMemberView.this.mHasNext = optJSONObject.optBoolean("hasNext");
                        }
                        JshopMemberView.this.setFooterState(3);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JshopMemberView jshopMemberView = JshopMemberView.this;
                int i2 = jshopMemberView.mPage;
                if (i2 == 1) {
                    Context context = jshopMemberView.mContext;
                    if (context instanceof JshopMemberActivity) {
                        ((JshopMemberActivity) context).toShowErrView();
                        return;
                    }
                    return;
                }
                jshopMemberView.mPage = i2 - 1;
                jshopMemberView.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberView.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JshopMemberView.this.setFooterState(4);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    public void setEndBottomVisiability(int i2) {
        View view = this.endBtootmView;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public void setFooterState(int i2) {
        LinearLayout linearLayout;
        if (this.mListView.getFooterViewsCount() == 0 || this.footerLayout == null || (linearLayout = this.loadingLayout) == null || this.endLayout == null) {
            return;
        }
        linearLayout.setVisibility(8);
        this.endLayout.setVisibility(8);
        this.errorLayout.setVisibility(8);
        if (i2 == 1) {
            this.loadingLayout.setVisibility(0);
        } else if (i2 == 2) {
            this.endLayout.setVisibility(0);
        } else if (i2 != 4) {
        } else {
            this.errorLayout.setVisibility(0);
        }
    }

    public void setHeaderData(JshopCustomer jshopCustomer) {
        if (jshopCustomer != null) {
            this.isMember = jshopCustomer.isShopCustomer;
        }
        this.mHeader.updateUI(jshopCustomer);
    }

    public void setListScrollListner(AbsListView.OnScrollListener onScrollListener) {
        this.mScrollListner = onScrollListener;
    }

    public void setMainClickListener(View.OnClickListener onClickListener) {
        this.mMainOnClickListener = onClickListener;
        initClick();
    }

    public void setSelection(int i2) {
        ListView listView = this.mListView;
        if (listView != null) {
            listView.setSelection(i2);
        }
    }

    public void updateScore(String str) {
        JshopMemberHeaderView jshopMemberHeaderView = this.mHeader;
        if (jshopMemberHeaderView != null) {
            jshopMemberHeaderView.updateScore(str);
        }
    }

    public JshopMemberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHasNext = false;
        this.mPage = 1;
        this.mPageSize = 20;
        this.isMember = false;
        this.saleType = 0;
        this.mContext = context;
        initView();
    }
}
