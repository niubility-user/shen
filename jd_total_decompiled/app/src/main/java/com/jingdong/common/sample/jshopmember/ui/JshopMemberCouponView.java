package com.jingdong.common.sample.jshopmember.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshopmember.adapter.JshopMemberCouponAdapter;
import com.jingdong.common.sample.jshopmember.entity.JshopCustomer;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class JshopMemberCouponView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "JshopMemberCouponView";
    private View closeView;
    private View couponView;
    private JshopMemberCouponAdapter mAdapter;
    private Context mContext;
    private ListView mListView;
    private View maskView;

    public JshopMemberCouponView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public void clear() {
        JshopMemberCouponAdapter jshopMemberCouponAdapter = this.mAdapter;
        if (jshopMemberCouponAdapter != null) {
            jshopMemberCouponAdapter.clear();
        }
    }

    public void initView() {
        LinearLayout.inflate(this.mContext, R.layout.jshop_member_coupon_layout, this);
        this.mListView = (ListView) findViewById(R.id.member_coupon_list);
        JshopMemberCouponAdapter jshopMemberCouponAdapter = new JshopMemberCouponAdapter(this.mContext);
        this.mAdapter = jshopMemberCouponAdapter;
        this.mListView.setAdapter((ListAdapter) jshopMemberCouponAdapter);
        View findViewById = findViewById(R.id.close_icon);
        this.closeView = findViewById;
        findViewById.setOnClickListener(this);
        View findViewById2 = findViewById(R.id.member_mask);
        this.maskView = findViewById2;
        findViewById2.setOnClickListener(this);
        this.couponView = findViewById(R.id.coupon_float_view);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close_icon || id == R.id.member_mask) {
            setVisibility(8);
        }
    }

    public void showCoupView() {
        Log.d(TAG, "11111");
        if (getVisibility() == 8) {
            Log.d(TAG, "222222222222222");
            setVisibility(0);
            this.couponView.setVisibility(0);
            this.couponView.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.gift_cart_wrap_appear));
        }
    }

    public void updateCouponUI(JshopCustomer jshopCustomer) {
        JshopMemberCouponAdapter jshopMemberCouponAdapter = this.mAdapter;
        if (jshopMemberCouponAdapter != null) {
            jshopMemberCouponAdapter.setData(jshopCustomer.points2Coupon);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public JshopMemberCouponView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }
}
