package com.jingdong.common.sample.jshopmember;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.sample.jshopmember.adapter.JShopMemberScoreAdapter;
import com.jingdong.common.sample.jshopmember.entity.JshopPointDetailBean;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopMemberScoreActivity extends MyActivity implements View.OnClickListener {
    public static final int END_TYPE = 2;
    public static final int ERR_TYPE = 4;
    public static final int LOADING_TYPE = 1;
    public static final int NONE_TYPE = 3;
    private static final String TAG = "JshopMemberScoreActivity";
    private TextView emptyRuleBtn;
    private TextView emptyScore;
    private LinearLayout endLayout;
    private LinearLayout errorLayout;
    private RelativeLayout errorView;
    private LinearLayout footerLayout;
    private LinearLayout headerView;
    public boolean isNeedLoadNext;
    private LinearLayout loadingLayout;
    private JShopMemberScoreAdapter mAdapter;
    private View mErrorView;
    private ListView mListView;
    private long mPoints;
    private ImageView mRightIcon;
    private View mScoreEmptyHeadView;
    private TextView mTitle;
    private TextView myScore;
    private TextView scoreRuleBtn;
    private String mShopId = "";
    private String mVendorId = "";
    private String pointsRuleExplain = "";
    private JshopShowErrorViewUtils utils = null;
    public boolean mHasNext = false;
    public String nextRowKey = "";
    public int mPage = 1;
    public int pageSize = 20;
    public JSONObject mParam = new JSONObject();
    View.OnClickListener mRetryOnclicklistener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setEnabled(false);
            JshopMemberScoreActivity.this.getDetailPointData(true);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void getDetailPointData(boolean z) {
        try {
            this.mParam.put("venderId", this.mVendorId);
            this.mParam.put("pageIdx", this.mPage);
            this.mParam.put("pageSize", 20);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JshopMemberUtils.sendHttpRequest(this, "pointsDetail", this.mParam, z, new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JshopMemberScoreActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(JshopMemberScoreActivity.TAG, "\u767b\u5f55\u8df3\u8f6c\u5904\u7406\u8fd4\u56de\u7684\u6570\u636e");
                        JshopMemberScoreActivity.this.parseDataFromJson(httpResponse.getFastJsonObject());
                        JshopMemberScoreActivity.this.setFooterState(3);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JshopMemberScoreActivity jshopMemberScoreActivity = JshopMemberScoreActivity.this;
                int i2 = jshopMemberScoreActivity.mPage;
                if (i2 == 1) {
                    jshopMemberScoreActivity.toShowErrView();
                    return;
                }
                jshopMemberScoreActivity.mPage = i2 - 1;
                jshopMemberScoreActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JshopMemberScoreActivity.this.setFooterState(4);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoading() {
        LinearLayout linearLayout = this.loadingLayout;
        return linearLayout != null && linearLayout.getVisibility() == 0;
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        return this.mShopId + CartConstant.KEY_YB_INFO_LINK + this.mPoints;
    }

    public void initTitle() {
        TextView textView = (TextView) findViewById(R.id.fd);
        this.mTitle = textView;
        textView.setText(getString(R.string.jshop_scroe_title));
        setTitleBack((ImageView) findViewById(R.id.fe));
        ImageView imageView = (ImageView) findViewById(R.id.b8a);
        this.mRightIcon = imageView;
        imageView.setImageResource(R.drawable.jshop_title_icon_more_normal);
    }

    public void initUI() {
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.jshop_member_score_header, null);
        this.headerView = linearLayout;
        TextView textView = (TextView) linearLayout.findViewById(R.id.my_score);
        this.myScore = textView;
        textView.setText("" + this.mPoints);
        TextView textView2 = (TextView) this.headerView.findViewById(R.id.score_rule);
        this.scoreRuleBtn = textView2;
        textView2.setOnClickListener(this);
        View findViewById = findViewById(R.id.empty_myscore);
        this.mScoreEmptyHeadView = findViewById;
        findViewById.setVisibility(8);
        TextView textView3 = (TextView) this.mScoreEmptyHeadView.findViewById(R.id.my_score);
        this.emptyScore = textView3;
        textView3.setText("" + this.mPoints);
        TextView textView4 = (TextView) this.mScoreEmptyHeadView.findViewById(R.id.score_rule);
        this.emptyRuleBtn = textView4;
        textView4.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) ImageUtil.inflate(R.layout.jshop_member_recommend_footer, null);
        this.footerLayout = linearLayout2;
        this.loadingLayout = (LinearLayout) linearLayout2.findViewById(R.id.recommend_loading_layout);
        this.errorLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_error_layout);
        this.endLayout = (LinearLayout) this.footerLayout.findViewById(R.id.recommend_end_layout);
        this.errorView = (RelativeLayout) findViewById(R.id.error_view);
        this.mListView = (ListView) findViewById(R.id.score_list);
        this.headerView.setVisibility(8);
        this.mListView.addHeaderView(this.headerView);
        this.mListView.addFooterView(this.footerLayout);
        JShopMemberScoreAdapter jShopMemberScoreAdapter = new JShopMemberScoreAdapter(this);
        this.mAdapter = jShopMemberScoreAdapter;
        this.mListView.setAdapter((ListAdapter) jShopMemberScoreAdapter);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                JshopMemberScoreActivity.this.isNeedLoadNext = i2 + i3 == i4;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                if (JshopMemberScoreActivity.this.isNeedLoadNext) {
                    Log.d(JshopMemberScoreActivity.TAG, "isNeedLoadNext = " + JshopMemberScoreActivity.this.isNeedLoadNext);
                    if (JshopMemberScoreActivity.this.isLoading()) {
                        return;
                    }
                    JshopMemberScoreActivity jshopMemberScoreActivity = JshopMemberScoreActivity.this;
                    if (jshopMemberScoreActivity.mHasNext) {
                        jshopMemberScoreActivity.setFooterState(1);
                        try {
                            JshopMemberScoreActivity jshopMemberScoreActivity2 = JshopMemberScoreActivity.this;
                            JSONObject jSONObject = jshopMemberScoreActivity2.mParam;
                            int i3 = jshopMemberScoreActivity2.mPage + 1;
                            jshopMemberScoreActivity2.mPage = i3;
                            jSONObject.put("pageIdx", i3);
                            JshopMemberScoreActivity jshopMemberScoreActivity3 = JshopMemberScoreActivity.this;
                            jshopMemberScoreActivity3.mParam.put("pageSize", jshopMemberScoreActivity3.pageSize);
                            JshopMemberScoreActivity jshopMemberScoreActivity4 = JshopMemberScoreActivity.this;
                            jshopMemberScoreActivity4.mParam.put("startRowKey", jshopMemberScoreActivity4.nextRowKey);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        JshopMemberScoreActivity.this.getDetailPointData(false);
                        return;
                    }
                    jshopMemberScoreActivity.setFooterState(3);
                }
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.score_rule) {
            return;
        }
        Log.d(TAG, "\u79ef\u5206\u89c4\u5219");
        JDMtaUtils.sendCommonData(this, "ShopPoint_PointRule", "", "", this, "", "", "", "Shop_PointMain", this.mShopId);
        try {
            this.pointsRuleExplain = TextUtils.isEmpty(this.pointsRuleExplain) ? "\u6682\u65e0\u8bf4\u660e" : this.pointsRuleExplain;
            final JDDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this, "\u79ef\u5206\u89c4\u5219\u8bf4\u660e", this.pointsRuleExplain, "\u6211\u77e5\u9053\u4e86");
            createJdDialogWithStyle5.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    createJdDialogWithStyle5.dismiss();
                }
            });
            createJdDialogWithStyle5.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    createJdDialogWithStyle5.dismiss();
                }
            });
            createJdDialogWithStyle5.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jshop_member_score);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.mShopId = intent.getExtras().getString("shopId");
                this.mVendorId = intent.getExtras().getString("vendorId");
                this.mPoints = intent.getExtras().getLong(JshopConst.JSKEY_MEMBER_SCORE);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        initTitle();
        initUI();
        getDetailPointData(true);
        setShopId(this.mShopId);
        setPageId("Shop_PointMain");
    }

    public void parseDataFromJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.optJSONObject("result") == null) {
            return;
        }
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("result");
        this.pointsRuleExplain = optJSONObject.optString("pointsRuleExplain");
        this.mHasNext = optJSONObject.optBoolean("hasNext");
        this.nextRowKey = optJSONObject.optString("nextRowKey");
        JDJSONArray optJSONArray = optJSONObject.optJSONArray("pointsDetailList");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                JshopPointDetailBean jshopPointDetailBean = (JshopPointDetailBean) JDJSON.parseObject(optJSONArray.optJSONObject(i2).toString(), JshopPointDetailBean.class);
                if (jshopPointDetailBean != null) {
                    arrayList.add(jshopPointDetailBean);
                }
            }
            if (this.mAdapter != null) {
                this.headerView.setVisibility(0);
                this.mAdapter.setData(arrayList);
            }
            this.mListView.setVisibility(0);
            View view = this.mErrorView;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        toShowEmptyView();
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

    public void toShowEmptyView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (JshopMemberScoreActivity.this.utils == null) {
                    JshopMemberScoreActivity jshopMemberScoreActivity = JshopMemberScoreActivity.this;
                    jshopMemberScoreActivity.utils = new JshopShowErrorViewUtils(jshopMemberScoreActivity, jshopMemberScoreActivity.errorView);
                }
                JshopMemberScoreActivity jshopMemberScoreActivity2 = JshopMemberScoreActivity.this;
                jshopMemberScoreActivity2.mErrorView = jshopMemberScoreActivity2.utils.getErrorViewHasRetry(null);
                JshopMemberScoreActivity.this.utils.setMessageInfo(JshopMemberScoreActivity.this.getString(R.string.jshop_member_score_empty), JshopMemberScoreActivity.this.getString(R.string.jshop_member_score_tips), "");
                JshopMemberScoreActivity.this.mErrorView.setVisibility(0);
                JshopMemberScoreActivity.this.mScoreEmptyHeadView.setVisibility(0);
                JshopMemberScoreActivity.this.mListView.setVisibility(8);
            }
        });
    }

    public void toShowErrView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberScoreActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (JshopMemberScoreActivity.this.utils == null) {
                    JshopMemberScoreActivity jshopMemberScoreActivity = JshopMemberScoreActivity.this;
                    jshopMemberScoreActivity.utils = new JshopShowErrorViewUtils(jshopMemberScoreActivity, jshopMemberScoreActivity.errorView);
                }
                JshopMemberScoreActivity jshopMemberScoreActivity2 = JshopMemberScoreActivity.this;
                jshopMemberScoreActivity2.mErrorView = jshopMemberScoreActivity2.utils.getErrorViewHasRetry(JshopMemberScoreActivity.this.mRetryOnclicklistener);
                JshopMemberScoreActivity.this.utils.setMessageInfo(JshopMemberScoreActivity.this.getString(R.string.jshop_net_fail), JshopMemberScoreActivity.this.getString(R.string.jshop_net_check), "");
                JshopMemberScoreActivity.this.utils.setErrorImage(R.drawable.y_03);
                JshopMemberScoreActivity.this.mErrorView.setVisibility(0);
                JshopMemberScoreActivity.this.mScoreEmptyHeadView.setVisibility(0);
                JshopMemberScoreActivity.this.mListView.setVisibility(8);
            }
        });
    }
}
