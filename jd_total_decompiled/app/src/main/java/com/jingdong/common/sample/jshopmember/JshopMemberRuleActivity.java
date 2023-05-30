package com.jingdong.common.sample.jshopmember;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.sample.jshopmember.adapter.JShopMemberRuleAdapter;
import com.jingdong.common.sample.jshopmember.entity.JshopCustomer;
import com.jingdong.common.sample.jshopmember.entity.ShopRulesBean;
import com.jingdong.corelib.utils.Log;
import java.util.List;

/* loaded from: classes6.dex */
public class JshopMemberRuleActivity extends MyActivity implements View.OnClickListener {
    private static final String TAG = "JshopMemberRuleActivity";
    public View commonTitle;
    private RelativeLayout errorView;
    public JShopMemberRuleAdapter mAdapter;
    private View mErrorView;
    public JshopCustomer mJshopCustomer;
    public ListView mListView;
    public ImageView mRightIcon;
    private String mShopId;
    public TextView mTitle;
    private JshopShowErrorViewUtils utils = null;

    private void initUI() {
        List<ShopRulesBean> list;
        this.errorView = (RelativeLayout) findViewById(R.id.error_view);
        this.mListView = (ListView) findViewById(R.id.member_rule_list);
        JShopMemberRuleAdapter jShopMemberRuleAdapter = new JShopMemberRuleAdapter(this);
        this.mAdapter = jShopMemberRuleAdapter;
        this.mListView.setAdapter((ListAdapter) jShopMemberRuleAdapter);
        this.mListView.setVisibility(0);
        JshopCustomer jshopCustomer = this.mJshopCustomer;
        if (jshopCustomer != null && (list = jshopCustomer.shopRules) != null && !list.isEmpty()) {
            this.mAdapter.setData(this.mJshopCustomer.shopRules);
        } else {
            toShowEmptyView();
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private void showError() {
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        return this.mShopId;
    }

    public void initTitle() {
        TextView textView = (TextView) findViewById(R.id.fd);
        this.mTitle = textView;
        textView.setText(getString(R.string.jshop_rule_title));
        setTitleBack((ImageView) findViewById(R.id.fe));
        ImageView imageView = (ImageView) findViewById(R.id.b8a);
        this.mRightIcon = imageView;
        imageView.setImageResource(R.drawable.jshop_title_icon_more_selector);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.jshop_member_score) {
            Log.d(TAG, "\u66f4\u591a\u4f18\u60e0\u5238");
        } else if (id == R.id.jshop_scroe_layout) {
            Log.d(TAG, "\u79ef\u5206\u70b9\u51fb");
        } else if (id != R.id.to_rule_detail) {
        } else {
            Log.d(TAG, "\u89c4\u5219\u8be6\u60c5");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        setContentView(R.layout.jshop_member_rule_activity);
        if (getIntent() != null && (extras = getIntent().getExtras()) != null) {
            this.mJshopCustomer = (JshopCustomer) extras.getParcelable("memberRule");
            this.mShopId = extras.getString("shopId");
        }
        initTitle();
        initUI();
        setShopId(this.mShopId);
        setPageId("Shop_VIPRule");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public void toShowEmptyView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberRuleActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (JshopMemberRuleActivity.this.utils == null) {
                    JshopMemberRuleActivity jshopMemberRuleActivity = JshopMemberRuleActivity.this;
                    jshopMemberRuleActivity.utils = new JshopShowErrorViewUtils(jshopMemberRuleActivity, jshopMemberRuleActivity.errorView);
                }
                JshopMemberRuleActivity jshopMemberRuleActivity2 = JshopMemberRuleActivity.this;
                jshopMemberRuleActivity2.mErrorView = jshopMemberRuleActivity2.utils.getErrorViewHasRetry(null);
                JshopMemberRuleActivity.this.utils.setMessageInfo(JshopMemberRuleActivity.this.getString(R.string.jshop_no_data), JshopMemberRuleActivity.this.getString(R.string.jshop_member_other), "");
                JshopMemberRuleActivity.this.utils.setButtonText(JshopMemberRuleActivity.this.getString(R.string.jshop_retry_button));
                JshopMemberRuleActivity.this.mErrorView.setVisibility(0);
                JshopMemberRuleActivity.this.mListView.setVisibility(8);
            }
        });
    }
}
