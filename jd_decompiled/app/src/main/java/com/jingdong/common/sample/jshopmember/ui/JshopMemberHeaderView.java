package com.jingdong.common.sample.jshopmember.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshopmember.JshopMemberActivity;
import com.jingdong.common.sample.jshopmember.entity.CouponForPoint;
import com.jingdong.common.sample.jshopmember.entity.CustomerBean;
import com.jingdong.common.sample.jshopmember.entity.JshopCustomer;
import com.jingdong.common.sample.jshopmember.entity.PointsEntrance;
import com.jingdong.common.sample.jshopmember.entity.Privilege2Customer;
import com.jingdong.common.sample.jshopmember.entity.ShopRulesBean;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.widget.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopMemberHeaderView extends LinearLayout {
    public static final String TAG = "JshopMemberHeadView";
    public View componContainer;
    public View couponCenter;
    public View couponLeft;
    public View couponRight;
    private List<SimpleDraweeView> imgList;
    public TextView jshopMemberScore;
    public View jshopScroelayout;
    private LinearLayout layoutGetUserPoint;
    private List<View> layoutList;
    private LinearLayout layoutUseUserPoint;
    private LinearLayout layoutUserPoint;
    public TextView levelIcon;
    public TextView levelIconName;
    public Context mContext;
    public JshopCustomer mCustomer;
    public View mLevelLayout;
    private View.OnClickListener mMainOnClickListener;
    public TextView mMemberTips;
    public RatingBar mRatingBar;
    public LinearLayout mRuleContainer;
    public JDCircleImageView mUserIcon;
    public TextView memName;
    public View moreCoupon;
    public TextView noMember;
    private View pLayout1;
    private View pLayout2;
    private View pLayout3;
    private View pLayout4;
    private View pLayout5;
    public View privilegeContainer;
    private SimpleDraweeView privilegeImg1;
    private SimpleDraweeView privilegeImg2;
    private SimpleDraweeView privilegeImg3;
    private SimpleDraweeView privilegeImg4;
    private SimpleDraweeView privilegeImg5;
    private TextView privilegeSingle;
    private TextView privilegeValue1;
    private TextView privilegeValue2;
    private TextView privilegeValue3;
    private TextView privilegeValue4;
    private TextView privilegeValue5;
    public TextView ruleLableName;
    public View toRuleDetail;
    private List<TextView> valueList;
    private TextView viewGetPointHint;
    private TextView viewUsePointHint;
    private TextView viewUserPointGet;
    private ImageView viewUserPointLotteryIcon;
    private TextView viewUserPointUse;

    public JshopMemberHeaderView(Context context) {
        super(context);
        this.layoutList = new ArrayList();
        this.valueList = new ArrayList();
        this.imgList = new ArrayList();
        this.mContext = context;
        initView();
    }

    public static void sendTakeCouponRequest(final MyActivity myActivity, CouponForPoint couponForPoint, String str) {
        Log.d(TAG, "sendTakeCouponRequest coupon = " + couponForPoint.batchId);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("venderId", str);
            jSONObject.put("couponBatchKey", couponForPoint.batchKey);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (myActivity instanceof JshopMemberActivity) {
            JshopMemberUtils.sendHttpRequest(myActivity, "points2Coupon", jSONObject, true, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.3
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    MyActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                            String optString = fastJsonObject != null ? fastJsonObject.optString("message") : "";
                            if (TextUtils.isEmpty(optString)) {
                                optString = "\u5151\u6362\u6210\u529f~";
                            }
                            ToastUtils.showToastInCenter(MyActivity.this.getApplicationContext(), (byte) 2, optString, 0);
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(final HttpError httpError) {
                    MyActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            String message = httpError.getMessage();
                            if (TextUtils.isEmpty(message)) {
                                message = "\u5151\u6362\u5931\u8d25~";
                            }
                            JshopMemberHeaderView.showAlert(MyActivity.this, message);
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
    }

    private void setPrivilegeVisibility(List<Privilege2Customer> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Privilege2Customer privilege2Customer = list.get(i2);
            this.layoutList.get(i2).setVisibility(0);
            this.valueList.get(i2).setText(privilege2Customer.privilegeName);
            int i3 = privilege2Customer.privilegeType;
            if (i3 == 1) {
                this.imgList.get(i2).setImageDrawable(getResources().getDrawable(R.drawable.jshop_right_icon_discount));
            } else if (i3 == 2) {
                this.imgList.get(i2).setImageDrawable(getResources().getDrawable(R.drawable.jshop_right_icon_double));
            } else if (i3 == 3) {
                this.imgList.get(i2).setImageDrawable(getResources().getDrawable(R.drawable.jshop_right_icon_add));
            } else if (i3 == 4) {
                this.imgList.get(i2).setImageDrawable(getResources().getDrawable(R.drawable.jshop_right_icon_exchange));
            } else if (i3 == 5) {
                this.imgList.get(i2).setImageDrawable(getResources().getDrawable(R.drawable.jshop_right_icon_price));
            }
        }
    }

    public static void showAlert(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            final JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(context, str, "\u6211\u77e5\u9053\u4e86");
            createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDialog.this.dismiss();
                }
            });
            createJdDialogWithStyle1.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDialog.this.dismiss();
                }
            });
            createJdDialogWithStyle1.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void takeCoupon(final MyActivity myActivity, final CouponForPoint couponForPoint, final String str) {
        Log.d(TAG, "takeCoupon");
        try {
            final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(myActivity, myActivity.getString(R.string.jshop_coupon_exchange_tips, new Object[]{couponForPoint.points + ""}), "\u53d6\u6d88", "\u786e\u8ba4");
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Log.d(JshopMemberHeaderView.TAG, "left btn");
                    JDDialog.this.dismiss();
                }
            });
            createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.ui.JshopMemberHeaderView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Log.d(JshopMemberHeaderView.TAG, "right btn");
                    JshopMemberHeaderView.sendTakeCouponRequest((JshopMemberActivity) MyActivity.this, couponForPoint, str);
                    createJdDialogWithStyle2.dismiss();
                }
            });
            createJdDialogWithStyle2.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void updateUserPoint(PointsEntrance pointsEntrance) {
        if (pointsEntrance == null) {
            this.layoutUserPoint.setVisibility(8);
            return;
        }
        this.layoutUserPoint.setVisibility(0);
        this.viewUserPointLotteryIcon.setVisibility(8);
        if (!TextUtils.isEmpty(pointsEntrance.earnPointsContent)) {
            this.viewUserPointGet.setText(pointsEntrance.earnPointsContent);
        }
        if (!TextUtils.isEmpty(pointsEntrance.spendPointsContent)) {
            this.viewUserPointUse.setText(pointsEntrance.spendPointsContent);
        }
        if (!TextUtils.isEmpty(pointsEntrance.earnPointsCount)) {
            this.viewGetPointHint.setText(pointsEntrance.earnPointsCount);
        }
        if (TextUtils.isEmpty(pointsEntrance.spendPointsCount)) {
            return;
        }
        this.viewUsePointHint.setText(pointsEntrance.spendPointsCount);
    }

    public void addRuleList(List<ShopRulesBean> list) {
        if (list != null && !list.isEmpty()) {
            this.toRuleDetail.setVisibility(0);
            this.mRuleContainer.removeAllViews();
            for (int i2 = 0; i2 < list.size(); i2++) {
                ShopRulesBean shopRulesBean = list.get(i2);
                if (shopRulesBean != null) {
                    this.mRuleContainer.addView(getRuleItem(shopRulesBean));
                }
            }
            return;
        }
        this.toRuleDetail.setVisibility(8);
    }

    public void bindCoupon(View view, CouponForPoint couponForPoint) {
        if (couponForPoint != null) {
            view.setTag(couponForPoint);
            TextView textView = (TextView) view.findViewById(R.id.quota);
            TextView textView2 = (TextView) view.findViewById(R.id.exchange_score);
            View findViewById = view.findViewById(R.id.coupon_layout);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.exchange_mark);
            String string = this.mContext.getString(R.string.jshop_member_coupon, "" + couponForPoint.condition, "" + couponForPoint.discount);
            if (couponForPoint.couponType == 1) {
                textView.setText(string);
                findViewById.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_small_blue_bg));
            } else {
                textView.setText("\u7acb\u51cf" + couponForPoint.discount + "\u5143");
                findViewById.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_small_red_bg));
            }
            if (couponForPoint.remainingCount <= 0) {
                simpleDraweeView.setVisibility(0);
                simpleDraweeView.setBackgroundResource(R.drawable.jshop_exchange_small_sellout_mark);
                findViewById.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_small_grey_bg));
                view.setEnabled(false);
            } else {
                view.setEnabled(true);
                simpleDraweeView.setVisibility(8);
            }
            textView2.setText(this.mContext.getString(R.string.jshop_member_coupon_exchange, "" + couponForPoint.points));
            if (view.getVisibility() == 4 || view.getVisibility() == 8) {
                view.setVisibility(0);
            }
            if (this.componContainer.getVisibility() == 8 || this.componContainer.getVisibility() == 4) {
                this.componContainer.setVisibility(0);
            }
        }
    }

    public Drawable getMemberLevDrawable(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v1);
                        }
                        return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v5);
                    }
                    return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v4);
                }
                return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v3);
            }
            return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v2);
        }
        return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v1);
    }

    public View getRuleItem(ShopRulesBean shopRulesBean) {
        View view = null;
        if (shopRulesBean != null) {
            view = LinearLayout.inflate(this.mContext, R.layout.jshop_member_rule_item, null);
            TextView textView = (TextView) view.findViewById(R.id.member_level);
            TextView textView2 = (TextView) view.findViewById(R.id.mem_discout);
            TextView textView3 = (TextView) view.findViewById(R.id.mem_tq);
            float itemGrade = shopRulesBean.getItemGrade();
            ((RatingBar) view.findViewById(R.id.member_score)).setRating(itemGrade);
            Drawable memberLevDrawable = getMemberLevDrawable((int) itemGrade);
            memberLevDrawable.setBounds(0, 0, memberLevDrawable.getMinimumWidth(), memberLevDrawable.getMinimumHeight());
            ((TextView) view.findViewById(R.id.member_level_name)).setText(shopRulesBean.curGradeName);
            if (TextUtils.isEmpty(shopRulesBean.discount)) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(this.mContext.getString(R.string.jshop_member_dis, shopRulesBean.discount));
            }
            if (!TextUtils.isEmpty(shopRulesBean.multiplePoints)) {
                textView3.setText(shopRulesBean.multiplePoints + "\u500d\u79ef\u5206");
            } else {
                textView3.setVisibility(8);
            }
            if (TextUtils.isEmpty(shopRulesBean.discount) && TextUtils.isEmpty(shopRulesBean.multiplePoints) && !TextUtils.isEmpty(shopRulesBean.ruleContent)) {
                textView2.setVisibility(8);
                textView3.setVisibility(0);
                textView3.setText(shopRulesBean.ruleContent);
            }
        }
        return view;
    }

    public void initClick() {
        View.OnClickListener onClickListener = this.mMainOnClickListener;
        if (onClickListener != null) {
            this.jshopScroelayout.setOnClickListener(onClickListener);
            this.moreCoupon.setOnClickListener(this.mMainOnClickListener);
            this.couponLeft.setOnClickListener(this.mMainOnClickListener);
            this.couponCenter.setOnClickListener(this.mMainOnClickListener);
            this.couponRight.setOnClickListener(this.mMainOnClickListener);
            this.toRuleDetail.setOnClickListener(this.mMainOnClickListener);
            this.layoutGetUserPoint.setOnClickListener(this.mMainOnClickListener);
            this.layoutUseUserPoint.setOnClickListener(this.mMainOnClickListener);
        }
    }

    public void initView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.jshop_member_header, this);
        this.jshopScroelayout = findViewById(R.id.jshop_scroe_layout);
        this.jshopMemberScore = (TextView) findViewById(R.id.jshop_member_score);
        this.memName = (TextView) findViewById(R.id.user_customer);
        this.noMember = (TextView) findViewById(R.id.non_member);
        this.mUserIcon = (JDCircleImageView) findViewById(R.id.user_img);
        this.mRatingBar = (RatingBar) findViewById(R.id.member_score);
        this.mMemberTips = (TextView) findViewById(R.id.member_tips);
        this.toRuleDetail = findViewById(R.id.to_rule_detail);
        this.mRuleContainer = (LinearLayout) findViewById(R.id.rule_container);
        this.moreCoupon = findViewById(R.id.member_more_coupon);
        this.couponLeft = findViewById(R.id.coupon_left);
        this.couponCenter = findViewById(R.id.coupon_center);
        this.couponRight = findViewById(R.id.coupon_right);
        this.privilegeContainer = findViewById(R.id.privilege_container);
        this.componContainer = findViewById(R.id.compon_container);
        this.mLevelLayout = findViewById(R.id.member_level_layout);
        this.levelIcon = (TextView) findViewById(R.id.user_level_icon);
        this.levelIconName = (TextView) findViewById(R.id.user_level_icon_name);
        this.ruleLableName = (TextView) findViewById(R.id.rule_lable_name);
        this.privilegeValue1 = (TextView) findViewById(R.id.privilege_value1);
        this.privilegeValue2 = (TextView) findViewById(R.id.privilege_value2);
        this.privilegeValue3 = (TextView) findViewById(R.id.privilege_value3);
        this.privilegeValue4 = (TextView) findViewById(R.id.privilege_value4);
        this.privilegeValue5 = (TextView) findViewById(R.id.privilege_value5);
        this.privilegeImg1 = (SimpleDraweeView) findViewById(R.id.privilege_img1);
        this.privilegeImg2 = (SimpleDraweeView) findViewById(R.id.privilege_img2);
        this.privilegeImg3 = (SimpleDraweeView) findViewById(R.id.privilege_img3);
        this.privilegeImg4 = (SimpleDraweeView) findViewById(R.id.privilege_img4);
        this.privilegeImg5 = (SimpleDraweeView) findViewById(R.id.privilege_img5);
        this.privilegeSingle = (TextView) findViewById(R.id.privilege_single_value);
        this.pLayout1 = findViewById(R.id.p_layout1);
        this.pLayout2 = findViewById(R.id.p_layout2);
        this.pLayout3 = findViewById(R.id.p_layout3);
        this.pLayout4 = findViewById(R.id.p_layout4);
        this.pLayout5 = findViewById(R.id.p_layout5);
        this.layoutList.add(this.pLayout1);
        this.layoutList.add(this.pLayout2);
        this.layoutList.add(this.pLayout3);
        this.layoutList.add(this.pLayout4);
        this.layoutList.add(this.pLayout5);
        this.valueList.add(this.privilegeValue1);
        this.valueList.add(this.privilegeValue2);
        this.valueList.add(this.privilegeValue3);
        this.valueList.add(this.privilegeValue4);
        this.valueList.add(this.privilegeValue5);
        this.imgList.add(this.privilegeImg1);
        this.imgList.add(this.privilegeImg2);
        this.imgList.add(this.privilegeImg3);
        this.imgList.add(this.privilegeImg4);
        this.imgList.add(this.privilegeImg5);
        this.layoutUserPoint = (LinearLayout) findViewById(R.id.layout_user_point);
        this.layoutGetUserPoint = (LinearLayout) findViewById(R.id.layout_user_point_get);
        this.layoutUseUserPoint = (LinearLayout) findViewById(R.id.layout_user_point_use);
        this.viewUserPointLotteryIcon = (ImageView) findViewById(R.id.view_user_point_lottery_icon);
        this.viewGetPointHint = (TextView) findViewById(R.id.view_user_point_get_hint);
        this.viewUsePointHint = (TextView) findViewById(R.id.view_user_point_user_hint);
        this.viewUserPointGet = (TextView) findViewById(R.id.view_user_point_get);
        this.viewUserPointUse = (TextView) findViewById(R.id.view_user_point_user);
    }

    public void removeItem(List<Privilege2Customer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Log.d(TAG, "list size = " + list.size());
        int i2 = -1;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if ("\u989d\u5916\u79ef\u5206".equals(list.get(i3).privilegeName)) {
                i2 = i3;
            }
        }
        if (i2 != -1) {
            list.remove(i2);
            Log.d(TAG, "remove index = " + i2);
        }
        Log.d(TAG, "list size = " + list.size());
    }

    public void setMainClickListener(View.OnClickListener onClickListener) {
        this.mMainOnClickListener = onClickListener;
        initClick();
    }

    public void updateCoupon(List<CouponForPoint> list) {
        this.couponLeft.setVisibility(4);
        this.couponCenter.setVisibility(4);
        this.couponRight.setVisibility(4);
        this.componContainer.setVisibility(8);
        this.moreCoupon.setVisibility(4);
        if (list == null) {
            return;
        }
        if (list.size() > 3) {
            this.moreCoupon.setVisibility(0);
        } else {
            this.moreCoupon.setVisibility(4);
        }
        if (list.size() >= 3) {
            bindCoupon(this.couponLeft, list.get(0));
            bindCoupon(this.couponCenter, list.get(1));
            bindCoupon(this.couponRight, list.get(2));
        } else if (list.size() == 2) {
            bindCoupon(this.couponLeft, list.get(0));
            bindCoupon(this.couponCenter, list.get(1));
        } else if (list.size() == 1) {
            bindCoupon(this.couponLeft, list.get(0));
        }
    }

    public void updateMemberInfo(CustomerBean customerBean, boolean z) {
        if (customerBean != null) {
            this.mMemberTips.setText(customerBean.upgradeCondition);
            this.mMemberTips.setVisibility(0);
            if (!TextUtils.isEmpty(customerBean.customerName)) {
                this.memName.setText("\u60a8\u597d\uff0c" + customerBean.customerName);
            } else {
                this.memName.setVisibility(8);
            }
            if (this.mCustomer.isPointsEnabled) {
                this.jshopScroelayout.setVisibility(0);
            } else {
                this.jshopScroelayout.setVisibility(8);
            }
            updateScore("" + customerBean.customerPoint);
            if (z) {
                this.mLevelLayout.setVisibility(0);
                float currentLevel = customerBean.getCurrentLevel();
                this.mRatingBar.setRating(currentLevel);
                getMemberLevDrawable((int) currentLevel);
                this.levelIconName.setText(customerBean.customerLevelName);
                this.noMember.setVisibility(8);
                this.mRuleContainer.setVisibility(8);
                Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.jshop_right_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.ruleLableName.setCompoundDrawables(drawable, null, null, null);
                this.ruleLableName.setText(this.mContext.getString(R.string.jshop_member_tq));
                JshopCustomer jshopCustomer = this.mCustomer;
                if (jshopCustomer != null) {
                    updatePrivilege(jshopCustomer.privilege2CustomerList);
                }
            } else {
                this.noMember.setVisibility(0);
                this.mRuleContainer.setVisibility(0);
                this.mLevelLayout.setVisibility(8);
                this.privilegeContainer.setVisibility(8);
                this.ruleLableName.setCompoundDrawables(null, null, null, null);
                this.ruleLableName.setText(this.mContext.getString(R.string.jshop_member_rule_info));
            }
            if (!z || this.privilegeContainer.getVisibility() == 0 || this.privilegeSingle.getVisibility() == 0) {
                return;
            }
            this.mRuleContainer.setVisibility(0);
            JshopCustomer jshopCustomer2 = this.mCustomer;
            if (jshopCustomer2 != null) {
                addRuleList(jshopCustomer2.shopRules);
            }
            this.ruleLableName.setCompoundDrawables(null, null, null, null);
            this.ruleLableName.setText(this.mContext.getString(R.string.jshop_member_rule_info));
        }
    }

    public void updatePrivilege(List<Privilege2Customer> list) {
        this.pLayout1.setVisibility(8);
        this.pLayout2.setVisibility(8);
        this.pLayout3.setVisibility(8);
        this.pLayout4.setVisibility(8);
        this.pLayout5.setVisibility(8);
        this.privilegeSingle.setVisibility(8);
        this.privilegeContainer.setVisibility(8);
        if (!this.mCustomer.isFollowed) {
            removeItem(list);
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        this.privilegeContainer.setVisibility(0);
        if (list.size() >= 2) {
            setPrivilegeVisibility(list);
        } else if (list.size() == 1) {
            this.privilegeContainer.setVisibility(8);
            this.privilegeSingle.setText(list.get(0).privilegeName);
            this.privilegeSingle.setVisibility(0);
        }
    }

    public void updateScore(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        TextView textView = this.jshopMemberScore;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void updateUI(JshopCustomer jshopCustomer) {
        if (jshopCustomer == null) {
            return;
        }
        this.mCustomer = jshopCustomer;
        if (!jshopCustomer.isShopCustomer) {
            addRuleList(jshopCustomer.shopRules);
        }
        updateMemberInfo(jshopCustomer.customer, jshopCustomer.isShopCustomer);
        updateCoupon(jshopCustomer.points2Coupon);
        updateUserPoint(jshopCustomer.pointsEntrance);
    }

    public JshopMemberHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.layoutList = new ArrayList();
        this.valueList = new ArrayList();
        this.imgList = new ArrayList();
        this.mContext = context;
        initView();
    }
}
