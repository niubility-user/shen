package com.jingdong.common.sample.jshop.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.XView.IXView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.sample.jshop.Entity.BuyerShowComment;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopProduct;
import com.jingdong.common.sample.jshop.Entity.JShopInfo;
import com.jingdong.common.sample.jshop.JShopDynamicDetailActivity;
import com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter;
import com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailSmallAdapter;
import com.jingdong.common.sample.jshop.ui.XListView;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.shop.JshopCoupon;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JshopNewFavoUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class JShopDynamicNormalDetailFragment extends BaseFragment {
    private static final String TAG = "JShopDynamicNormalDetailFragment";
    private static final String mFunctionId = "getActivityDetail";
    MyActivity mActivity;
    private Bundle mBundle;
    private JshopNewFavoUtils mFavoUtils;
    private JShopDynamicDetailLargeAdapter mLargeAdapter;
    private ArrayList<DynamicShopProduct> mLargeList;
    XListView mListView;
    private JShopDynamicDetailSmallAdapter mSmallAdapter;
    private ArrayList<DynamicShopProduct> mSmallList;
    private int mCurPage = 1;
    private boolean isLastPage = false;
    private boolean isRefreshing = false;
    private boolean isSend = false;
    private boolean isFromShare = false;
    private String mShareUrl = "";
    private long mActivityType = -1;

    /* renamed from: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment$5 */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ boolean val$isFollowGift;
        final /* synthetic */ DynamicShopActivity val$shopActivity;
        final /* synthetic */ ImageView val$v;

        AnonymousClass5(boolean z, DynamicShopActivity dynamicShopActivity, ImageView imageView) {
            JShopDynamicNormalDetailFragment.this = r1;
            this.val$isFollowGift = z;
            this.val$shopActivity = dynamicShopActivity;
            this.val$v = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            JshopFavoListener jshopFavoListener = new JshopFavoListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.5.1
                {
                    AnonymousClass5.this = this;
                }

                @Override // com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener
                public void onError() {
                }

                @Override // com.jingdong.sdk.jshopsdk.common.favo.JshopFavoListener
                public void onFavoStatus(final boolean z) {
                    JShopDynamicNormalDetailFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.5.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                            if (anonymousClass5.val$isFollowGift) {
                                DynamicShopActivity dynamicShopActivity = anonymousClass5.val$shopActivity;
                                boolean z2 = z;
                                dynamicShopActivity.followed = z2;
                                if (!z2) {
                                    if (!"FOLLD".equals(JShopDynamicNormalDetailFragment.this.mFavoUtils.mFollowGiftOptCode)) {
                                        if ("NFOLL".equals(JShopDynamicNormalDetailFragment.this.mFavoUtils.mFollowGiftOptCode)) {
                                            AnonymousClass5 anonymousClass52 = AnonymousClass5.this;
                                            DynamicShopActivity dynamicShopActivity2 = anonymousClass52.val$shopActivity;
                                            dynamicShopActivity2.followAward = -1;
                                            dynamicShopActivity2.followed = false;
                                            anonymousClass52.val$v.setBackgroundResource(R.drawable.jshop_dynamic_concern_no);
                                            return;
                                        }
                                        return;
                                    }
                                    AnonymousClass5 anonymousClass53 = AnonymousClass5.this;
                                    DynamicShopActivity dynamicShopActivity3 = anonymousClass53.val$shopActivity;
                                    dynamicShopActivity3.followAward = -1;
                                    dynamicShopActivity3.followed = true;
                                    anonymousClass53.val$v.setBackgroundResource(R.drawable.jshop_dynamic_concern_yes);
                                    return;
                                }
                                dynamicShopActivity.followAward = -1;
                                dynamicShopActivity.followed = true;
                                anonymousClass5.val$v.setBackgroundResource(R.drawable.jshop_dynamic_concern_yes);
                                return;
                            }
                            if (!anonymousClass5.val$shopActivity.followed) {
                                anonymousClass5.val$v.setBackgroundResource(R.drawable.jshop_dynamic_concern_yes);
                            } else {
                                anonymousClass5.val$v.setBackgroundResource(R.drawable.jshop_dynamic_concern_no);
                            }
                            DynamicShopActivity dynamicShopActivity4 = AnonymousClass5.this.val$shopActivity;
                            dynamicShopActivity4.followed = !dynamicShopActivity4.followed;
                            dynamicShopActivity4.followAward = -1;
                        }
                    });
                }
            };
            if (this.val$isFollowGift) {
                JShopDynamicNormalDetailFragment.this.mFavoUtils.getFavoStatus(this.val$v, this.val$shopActivity.shopId + "", true, this.val$isFollowGift, jshopFavoListener, new XViewCallBack() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.5.2
                    {
                        AnonymousClass5.this = this;
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onCloseButtonClicked() {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onError(int i2) {
                        JShopDynamicNormalDetailFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.5.2.1
                            {
                                AnonymousClass2.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                JShopDynamicNormalDetailFragment jShopDynamicNormalDetailFragment = JShopDynamicNormalDetailFragment.this;
                                ToastUtils.showToastInCenter((Context) jShopDynamicNormalDetailFragment.mActivity, (byte) 2, jShopDynamicNormalDetailFragment.getString(R.string.g5), 0);
                            }
                        });
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onStart() {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXViewLoadingUrl(String str) {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXViewReady() {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXViewRequest(XViewRequest xViewRequest) {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXViewVisibleChanged(boolean z) {
                    }

                    @Override // com.jingdong.common.XView.XViewCallBack
                    public void onXVivewClosed() {
                    }
                });
                return;
            }
            JShopDynamicNormalDetailFragment.this.mFavoUtils.getFavoStatus(this.val$v, this.val$shopActivity.shopId + "", !this.val$shopActivity.followed, this.val$isFollowGift, jshopFavoListener, null);
        }
    }

    static /* synthetic */ int access$208(JShopDynamicNormalDetailFragment jShopDynamicNormalDetailFragment) {
        int i2 = jShopDynamicNormalDetailFragment.mCurPage;
        jShopDynamicNormalDetailFragment.mCurPage = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$210(JShopDynamicNormalDetailFragment jShopDynamicNormalDetailFragment) {
        int i2 = jShopDynamicNormalDetailFragment.mCurPage;
        jShopDynamicNormalDetailFragment.mCurPage = i2 - 1;
        return i2;
    }

    public void checkBundle() {
        if (this.mBundle == null) {
            showEmptyView();
        } else {
            getActivityDetail(this.mCurPage);
        }
    }

    private View createHeadView(final DynamicShopActivity dynamicShopActivity, JShopInfo jShopInfo) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        JShopDynamicNormalDetailFragment jShopDynamicNormalDetailFragment;
        int i7;
        int i8;
        TextView textView;
        MyActivity myActivity = this.mActivity;
        if (myActivity != null && dynamicShopActivity != null) {
            View inflate = LayoutInflater.from(myActivity).inflate(R.layout.jshop_dynamic_detail_head, (ViewGroup) null);
            TextView textView2 = (TextView) inflate.findViewById(R.id.activity_title);
            TextView textView3 = (TextView) inflate.findViewById(R.id.activity_time);
            TextView textView4 = (TextView) inflate.findViewById(R.id.activity_browser);
            TextView textView5 = (TextView) inflate.findViewById(R.id.activity_description);
            View findViewById = inflate.findViewById(R.id.activity_sign_prize_head);
            View findViewById2 = inflate.findViewById(R.id.activity_time_container);
            TextView textView6 = (TextView) inflate.findViewById(R.id.activity_sign_prize_time_content);
            View findViewById3 = inflate.findViewById(R.id.activity_prize_container);
            TextView textView7 = (TextView) inflate.findViewById(R.id.activity_sign_prize_des_content);
            View findViewById4 = inflate.findViewById(R.id.jshop_dyanmic_detail_header);
            View findViewById5 = inflate.findViewById(R.id.activity_buyer_show_head);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.user_head_pic);
            SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) inflate.findViewById(R.id.user_level_pic);
            TextView textView8 = (TextView) inflate.findViewById(R.id.user_name);
            TextView textView9 = (TextView) inflate.findViewById(R.id.activity_buyer_show_description);
            findViewById5.setVisibility(8);
            textView5.setVisibility(8);
            findViewById.setVisibility(8);
            boolean z = dynamicShopActivity.promotionType != 1;
            long j2 = dynamicShopActivity.activityType;
            if (j2 == 16) {
                if (TextUtils.isEmpty(dynamicShopActivity.activityDesc)) {
                    i7 = 8;
                    textView2.setVisibility(8);
                    textView = textView5;
                    i8 = 0;
                } else {
                    i7 = 8;
                    i8 = 0;
                    textView2.setVisibility(0);
                    textView2.setText(dynamicShopActivity.activityDesc);
                    textView = textView5;
                }
                textView.setVisibility(i7);
                BuyerShowComment buyerShowComment = dynamicShopActivity.comment;
                findViewById5.setVisibility(i8);
                JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                jDDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(DPIUtil.dip2px(11.0f)));
                JDImageUtils.displayImage(buyerShowComment.userImage, simpleDraweeView, jDDisplayImageOptions);
                textView8.setText(!TextUtils.isEmpty(buyerShowComment.nickname) ? buyerShowComment.nickname : buyerShowComment.pin);
                textView9.setText(buyerShowComment.content);
            } else if (3 == j2 || (z && j2 == 12)) {
                if (TextUtils.isEmpty(dynamicShopActivity.activityDesc)) {
                    i2 = 8;
                    textView2.setVisibility(8);
                } else {
                    i2 = 8;
                    textView2.setVisibility(0);
                    textView2.setText(dynamicShopActivity.activityDesc);
                }
                textView5.setVisibility(i2);
            } else if (11 == j2) {
                if (TextUtils.isEmpty(dynamicShopActivity.activityDesc)) {
                    i4 = 8;
                    textView2.setVisibility(8);
                    i5 = 0;
                } else {
                    i4 = 8;
                    i5 = 0;
                    textView2.setVisibility(0);
                    textView2.setText(dynamicShopActivity.activityDesc);
                }
                textView5.setVisibility(i4);
                findViewById.setVisibility(i5);
                if (TextUtils.isEmpty(dynamicShopActivity.signTime)) {
                    findViewById2.setVisibility(i4);
                } else {
                    findViewById2.setVisibility(i5);
                    textView6.setText(dynamicShopActivity.signTime);
                }
                JDJSONArray jDJSONArray = dynamicShopActivity.signAwardDescs;
                if (jDJSONArray != null && jDJSONArray.size() > 0) {
                    findViewById3.setVisibility(i5);
                    StringBuilder sb = new StringBuilder();
                    for (int i9 = 0; i9 < dynamicShopActivity.signAwardDescs.size(); i9++) {
                        sb.append(dynamicShopActivity.signAwardDescs.optString(i9));
                        if (i9 < dynamicShopActivity.signAwardDescs.size() - 1) {
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                    }
                    textView7.setText(sb.toString());
                } else {
                    findViewById3.setVisibility(8);
                }
            } else {
                if (TextUtils.isEmpty(dynamicShopActivity.activityDesc)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(dynamicShopActivity.activityDesc);
                }
                if (TextUtils.isEmpty(dynamicShopActivity.activityDesc)) {
                    textView5.setVisibility(8);
                } else {
                    textView5.setVisibility(8);
                    textView5.setText(dynamicShopActivity.activityDesc);
                }
                if (dynamicShopActivity.activityType == 14) {
                    textView5.setVisibility(8);
                    if (dynamicShopActivity.couponJson != null) {
                        JshopCoupon jshopCoupon = new JshopCoupon(dynamicShopActivity.couponJson);
                        int i10 = jshopCoupon.type;
                        if (i10 == 0) {
                            textView5.setText(String.format(getResources().getString(R.string.jshop_dynamic_detail_coupon_jing_des), JShopUtil.formatReduceDiscount(jshopCoupon.discount)));
                        } else if (i10 == 1) {
                            i3 = 0;
                            textView5.setText(String.format(getResources().getString(R.string.jshop_dynamic_detail_coupon_dong_des), jshopCoupon.quota + "", JShopUtil.formatReduceDiscount(jshopCoupon.discount)));
                            textView5.setVisibility(i3);
                        }
                        i3 = 0;
                        textView5.setVisibility(i3);
                    }
                }
            }
            if (TextUtils.isEmpty(dynamicShopActivity.modified)) {
                textView3.setVisibility(4);
            } else {
                textView3.setVisibility(0);
                textView3.setText(dynamicShopActivity.modified);
            }
            if (TextUtils.isEmpty(dynamicShopActivity.viewCounts)) {
                textView4.setVisibility(8);
                if (textView3.getVisibility() == 4) {
                    textView3.setVisibility(8);
                }
            } else {
                textView4.setVisibility(0);
                textView4.setText(String.format(getResources().getString(R.string.jshop_browser_num), dynamicShopActivity.viewCounts));
            }
            TextView textView10 = (TextView) inflate.findViewById(R.id.jshop_shop_name);
            TextView textView11 = (TextView) inflate.findViewById(R.id.jshop_shop_des);
            final ImageView imageView = (ImageView) inflate.findViewById(R.id.jshop_shop_follow);
            imageView.setVisibility(8);
            JDImageUtils.displayImage(JShopUtil.checkImageUrl(jShopInfo.logoUrl), (ImageView) inflate.findViewById(R.id.jshop_shop_icon), new JDDisplayImageOptions().setPlaceholder(19));
            textView10.setText(TextUtils.isEmpty(jShopInfo.shopName) ? "\u5c0f\u7f16\u63a8\u8350" : jShopInfo.shopName);
            if (jShopInfo.followCountNum <= 0) {
                textView11.setVisibility(8);
                i6 = 0;
                jShopDynamicNormalDetailFragment = this;
            } else {
                i6 = 0;
                textView11.setVisibility(0);
                jShopDynamicNormalDetailFragment = this;
                textView11.setText(String.format("%s\u4eba\u5df2\u7ecf\u5173\u6ce8", jShopDynamicNormalDetailFragment.formatFollowCount(jShopInfo.followCountNum)));
            }
            if (JShopUtil.isFollowGift(dynamicShopActivity.followAward)) {
                imageView.setVisibility(i6);
                imageView.setBackgroundResource(R.drawable.jshop_follow_gift);
            } else if (!dynamicShopActivity.followed) {
                imageView.setVisibility(i6);
                imageView.setBackgroundResource(R.drawable.jshop_dynamic_concern_no);
            } else {
                imageView.setVisibility(8);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.3
                {
                    JShopDynamicNormalDetailFragment.this = jShopDynamicNormalDetailFragment;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JShopUtil.isFollowGift(dynamicShopActivity.followAward)) {
                        MyActivity myActivity2 = JShopDynamicNormalDetailFragment.this.mActivity;
                        JDMtaUtils.sendCommonData(myActivity2, "ShopDynamicStateDetail_FollowShop", "0_0", "", myActivity2, "", "", "", "ShopDynamicStateDetail_Main", dynamicShopActivity.shopId + "");
                        JShopDynamicNormalDetailFragment.this.getFollow(dynamicShopActivity, imageView, true);
                        return;
                    }
                    boolean z2 = dynamicShopActivity.followed;
                    String str = (z2 ? "1" : "0") + CartConstant.KEY_YB_INFO_LINK + (z2 ? DYConstants.DY_NULL_STR : "1");
                    MyActivity myActivity3 = JShopDynamicNormalDetailFragment.this.mActivity;
                    JDMtaUtils.sendCommonData(myActivity3, "ShopDynamicStateDetail_FollowShop", str, "", myActivity3, "", "", "", "ShopDynamicStateDetail_Main", dynamicShopActivity.shopId + "");
                    JShopDynamicNormalDetailFragment.this.getFollow(dynamicShopActivity, imageView, false);
                }
            });
            findViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.4
                {
                    JShopDynamicNormalDetailFragment.this = jShopDynamicNormalDetailFragment;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String activityType = JShopUtil.getActivityType(dynamicShopActivity.activityType + "", dynamicShopActivity.activitySubType);
                    if (TextUtils.isEmpty(activityType)) {
                        activityType = null;
                    }
                    JDMtaUtils.sendCommonData(JShopDynamicNormalDetailFragment.this.mActivity, "ShopDynamicStateDetail_ToShop", activityType + CartConstant.KEY_YB_INFO_LINK + dynamicShopActivity.shopId, "", JShopDynamicNormalDetailFragment.this.mActivity, "", "JshopMainShopActivity", "", "ShopDynamicStateDetail_Main", dynamicShopActivity.shopId + "");
                    DynamicShopActivity dynamicShopActivity2 = dynamicShopActivity;
                    if (dynamicShopActivity2.shopId == 0 && dynamicShopActivity2.venderId == 0) {
                        return;
                    }
                    DeepLinkJShopHomeHelper.gotoJShopHome(JShopDynamicNormalDetailFragment.this.mActivity, dynamicShopActivity.shopId + "", dynamicShopActivity.venderId + "", dynamicShopActivity.shopName, "home", new SourceEntity(JshopConst.SOURCE_ENTITY_SHOP_DYNAMIC, "\u5e97\u94fa\u9996\u9875"));
                }
            });
            return inflate;
        }
        return new View(getContext());
    }

    private String formatFollowCount(int i2) {
        if (i2 < 10000) {
            return i2 + "";
        }
        return (i2 / 10000) + OrderISVUtil.MONEY_DECIMAL + ((i2 % 10000) / 1000) + "\u4e07";
    }

    private void getActivityDetail(final int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        if (i2 <= 1) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        httpSetting.setFunctionId(mFunctionId);
        httpSetting.putJsonParam("page", i2 + "");
        httpSetting.putJsonParam("pageSize", "20");
        httpSetting.setUseFastJsonParser(true);
        if (!TextUtils.isEmpty(this.mBundle.getString("shopId"))) {
            httpSetting.putJsonParam("shopId", this.mBundle.getString("shopId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("venderId"))) {
            httpSetting.putJsonParam("venderId", this.mBundle.getString("venderId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString(JshopConst.JSHOP_ACTIVITY_TYPE))) {
            httpSetting.putJsonParam(JshopConst.JSHOP_ACTIVITY_TYPE, this.mBundle.getString(JshopConst.JSHOP_ACTIVITY_TYPE));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("activityId"))) {
            httpSetting.putJsonParam("activityId", this.mBundle.getString("activityId"));
        }
        if (!TextUtils.isEmpty(this.mBundle.getString("params"))) {
            httpSetting.putJsonParam("params", this.mBundle.getString("params"));
        }
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.2
            {
                JShopDynamicNormalDetailFragment.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JShopDynamicNormalDetailFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.2.2
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicNormalDetailFragment.this.isRefreshing = false;
                        XListView xListView = JShopDynamicNormalDetailFragment.this.mListView;
                        if (xListView != null) {
                            xListView.stopLoadMore();
                        }
                        MyActivity myActivity = JShopDynamicNormalDetailFragment.this.mActivity;
                        String string = myActivity != null ? myActivity.getResources().getString(R.string.jshop_dynamic_detail_error_msg) : "";
                        if (httpResponse == null) {
                            JShopDynamicNormalDetailFragment.this.setEmptyState(string);
                            return;
                        }
                        Log.d(JShopDynamicNormalDetailFragment.TAG, " onEnd , response ===> : " + httpResponse.getJSONObject());
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        if (fastJsonObject == null || fastJsonObject.optJSONObject("activity") == null) {
                            if (fastJsonObject != null) {
                                string = fastJsonObject.optString("errorMsg", JShopDynamicNormalDetailFragment.this.mActivity.getResources().getString(R.string.jshop_dynamic_detail_error_msg));
                            }
                            JShopDynamicNormalDetailFragment.this.setEmptyState(string);
                            return;
                        }
                        if (JShopDynamicNormalDetailFragment.this.mCurPage == 1) {
                            MyActivity myActivity2 = JShopDynamicNormalDetailFragment.this.mActivity;
                            if (myActivity2 instanceof JShopDynamicDetailActivity) {
                                ((JShopDynamicDetailActivity) myActivity2).setDetailRequestSuccess(1);
                            }
                        }
                        JShopDynamicNormalDetailFragment.this.isLastPage = !fastJsonObject.optBoolean("hasNext");
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("activity");
                        try {
                            optJSONObject.put("commentSwitch", (Object) fastJsonObject.optString("commentSwitch"));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        JShopInfo jShopInfo = new JShopInfo();
                        JDJSONObject optJSONObject2 = fastJsonObject.optJSONObject(JshopConst.JSKEY_SHOP_INFO);
                        if (optJSONObject2 != null) {
                            jShopInfo.shopName = optJSONObject2.optString("shopName");
                            jShopInfo.logoUrl = optJSONObject2.optString("logoUrl");
                            jShopInfo.followCountNum = optJSONObject2.optInt(JshopConst.JSKEY_FLW_COUNT);
                        }
                        DynamicShopActivity dynamicShopActivity = new DynamicShopActivity(optJSONObject);
                        JShopDynamicNormalDetailFragment.this.mActivityType = dynamicShopActivity.activityType;
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        MyActivity myActivity3 = JShopDynamicNormalDetailFragment.this.mActivity;
                        if (myActivity3 != null && (myActivity3 instanceof JShopDynamicDetailActivity) && i2 == 1) {
                            ((JShopDynamicDetailActivity) myActivity3).initTitle(dynamicShopActivity.shopName);
                            ((JShopDynamicDetailActivity) JShopDynamicNormalDetailFragment.this.mActivity).setActivityType(dynamicShopActivity);
                            if (dynamicShopActivity.shopId <= 0 && dynamicShopActivity.venderId <= 0) {
                                ((JShopDynamicDetailActivity) JShopDynamicNormalDetailFragment.this.mActivity).setRightIconVisibility(8);
                            }
                        }
                        JShopDynamicNormalDetailFragment.this.parseJSON(dynamicShopActivity, jShopInfo);
                        boolean z = dynamicShopActivity.promotionType != 1;
                        if (JShopDynamicNormalDetailFragment.this.isLastPage) {
                            long j2 = dynamicShopActivity.activityType;
                            if (j2 != 11 && j2 != 3 && ((!z || j2 != 12) && j2 != 13 && j2 != 16)) {
                                JShopDynamicNormalDetailFragment.this.mListView.setFooterHintViewVisibility(true);
                                JShopDynamicNormalDetailFragment.this.mListView.setFooterText(R.string.jshop_dynamic_detail_footer, R.color.jshop_dynamic_detail_footer, true);
                                return;
                            }
                        }
                        JShopDynamicNormalDetailFragment.this.mListView.setFooterHintViewVisibility(false);
                    }
                });
                JShopDynamicNormalDetailFragment.this.sendShareMTA();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(JShopDynamicNormalDetailFragment.TAG, "onError");
                JShopDynamicNormalDetailFragment.this.isRefreshing = false;
                JShopDynamicNormalDetailFragment.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.2.1
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicNormalDetailFragment.this.setErrorState();
                        JShopDynamicNormalDetailFragment.access$210(JShopDynamicNormalDetailFragment.this);
                        XListView xListView = JShopDynamicNormalDetailFragment.this.mListView;
                        if (xListView != null) {
                            xListView.stopLoadMore();
                        }
                    }
                });
                JShopDynamicNormalDetailFragment.this.sendShareMTA();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                JShopDynamicNormalDetailFragment.this.isRefreshing = true;
            }
        });
        MyActivity myActivity = this.mActivity;
        if (myActivity != null) {
            myActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public void getFollow(DynamicShopActivity dynamicShopActivity, ImageView imageView, boolean z) {
        if (this.mFavoUtils == null) {
            JshopNewFavoUtils jshopNewFavoUtils = new JshopNewFavoUtils(this.mActivity, true);
            this.mFavoUtils = jshopNewFavoUtils;
            jshopNewFavoUtils.setSourceRpc(JshopNewFavoUtils.SOURCE_RPC_ACTIVITY_DETAIL);
        }
        LoginUserHelper.getInstance().executeLoginRunnable(this.thisActivity, new AnonymousClass5(z, dynamicShopActivity, imageView));
    }

    public void parseJSON(DynamicShopActivity dynamicShopActivity, JShopInfo jShopInfo) {
        boolean z = dynamicShopActivity.isLargePic;
        boolean z2 = dynamicShopActivity.promotionType != 1;
        ArrayList<DynamicShopProduct> productList = DynamicShopProduct.toProductList(dynamicShopActivity.getProducts());
        Log.d(TAG, "isLargePic  ==  :  " + z + "  ,  shopActivity.activityType  ==  :  " + dynamicShopActivity.activityType);
        if (!z) {
            long j2 = dynamicShopActivity.activityType;
            if (j2 != 11 && j2 != 3 && ((!z2 || j2 != 12) && j2 != 13 && j2 != 16)) {
                Log.d(TAG, "  ===  show small view  ===  ");
                showSmallView(dynamicShopActivity, jShopInfo, productList);
                return;
            }
        }
        Log.d(TAG, "  ===  show large view  ===  ");
        showLargeView(dynamicShopActivity, jShopInfo, productList);
    }

    public void sendShareMTA() {
        if (this.isSend) {
            return;
        }
        this.isSend = true;
        if (this.isFromShare) {
            MyActivity myActivity = this.mActivity;
            JDMtaUtils.sendCommonData(myActivity, "Shop_ShareReturn", "Shop_ShopDynamicStateDetail_Share", "", myActivity, "", "", "", "", "");
        }
    }

    public void setEmptyState(String str) {
        if (this.mCurPage == 1) {
            MyActivity myActivity = this.mActivity;
            if (myActivity instanceof JShopDynamicDetailActivity) {
                ((JShopDynamicDetailActivity) myActivity).initTitle("");
                ((JShopDynamicDetailActivity) this.mActivity).setmDetailRequestStatus(-1, str);
            }
        }
    }

    public void setErrorState() {
        if (this.mCurPage == 1) {
            MyActivity myActivity = this.mActivity;
            if (myActivity instanceof JShopDynamicDetailActivity) {
                ((JShopDynamicDetailActivity) myActivity).initTitle("");
                ((JShopDynamicDetailActivity) this.mActivity).setDetailRequestSuccess(-1);
            }
        }
    }

    private void showEmptyView() {
    }

    private void showLargeView(DynamicShopActivity dynamicShopActivity, JShopInfo jShopInfo, ArrayList<DynamicShopProduct> arrayList) {
        BuyerShowComment buyerShowComment;
        ArrayList<String> arrayList2;
        if (this.mLargeList == null) {
            this.mLargeList = new ArrayList<>();
        }
        if (dynamicShopActivity.activityType == 16) {
            ArrayList arrayList3 = new ArrayList();
            if (dynamicShopActivity != null && (buyerShowComment = dynamicShopActivity.comment) != null && (arrayList2 = buyerShowComment.afterImage) != null) {
                Iterator<String> it = arrayList2.iterator();
                while (it.hasNext()) {
                    DynamicShopProduct dynamicShopProduct = new DynamicShopProduct();
                    dynamicShopProduct.imgPath = it.next();
                    arrayList3.add(dynamicShopProduct);
                }
            }
            this.mLargeList.addAll(arrayList3);
        } else {
            this.mLargeList.addAll(arrayList);
        }
        if (this.mListView.getHeaderViewsCount() <= 1) {
            this.mListView.addHeaderView(createHeadView(dynamicShopActivity, jShopInfo));
        }
        if (this.mLargeList != null) {
            Log.d(TAG, "  mLargeList.size()  ===  " + this.mLargeList.size());
        }
        JShopDynamicDetailLargeAdapter jShopDynamicDetailLargeAdapter = this.mLargeAdapter;
        if (jShopDynamicDetailLargeAdapter == null) {
            JShopDynamicDetailLargeAdapter jShopDynamicDetailLargeAdapter2 = new JShopDynamicDetailLargeAdapter(this.mActivity, dynamicShopActivity, this.mLargeList, this.isLastPage);
            this.mLargeAdapter = jShopDynamicDetailLargeAdapter2;
            this.mListView.setAdapter((ListAdapter) jShopDynamicDetailLargeAdapter2);
            return;
        }
        jShopDynamicDetailLargeAdapter.setList(this.mLargeList, this.isLastPage);
    }

    private void showSmallView(DynamicShopActivity dynamicShopActivity, JShopInfo jShopInfo, ArrayList<DynamicShopProduct> arrayList) {
        if (this.mSmallList == null) {
            this.mSmallList = new ArrayList<>();
        }
        this.mSmallList.addAll(arrayList);
        if (this.mListView.getHeaderViewsCount() <= 1) {
            this.mListView.addHeaderView(createHeadView(dynamicShopActivity, jShopInfo));
        }
        if (this.mSmallList != null) {
            Log.d(TAG, "  mSmallList.size()  ===  " + this.mSmallList.size());
        }
        JShopDynamicDetailSmallAdapter jShopDynamicDetailSmallAdapter = this.mSmallAdapter;
        if (jShopDynamicDetailSmallAdapter == null) {
            JShopDynamicDetailSmallAdapter jShopDynamicDetailSmallAdapter2 = new JShopDynamicDetailSmallAdapter(this.mActivity, dynamicShopActivity, this.mSmallList);
            this.mSmallAdapter = jShopDynamicDetailSmallAdapter2;
            this.mListView.setAdapter((ListAdapter) jShopDynamicDetailSmallAdapter2);
            return;
        }
        jShopDynamicDetailSmallAdapter.setList(this.mSmallList);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mActivity = (MyActivity) activity;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setIsUseBasePV(false);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        this.mBundle = arguments;
        if (arguments.containsKey("source")) {
            this.isFromShare = "activityDetail".equals(this.mBundle.getString("source"));
        }
        return layoutInflater.inflate(R.layout.jshop_normal_dynamic_fragment, (ViewGroup) null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        JshopNewFavoUtils jshopNewFavoUtils;
        IXView iXView;
        if (i2 != 4 || (jshopNewFavoUtils = this.mFavoUtils) == null || (iXView = jshopNewFavoUtils.mIXView) == null || !iXView.isXViewShow()) {
            return false;
        }
        this.mFavoUtils.mIXView.closeXView();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        XListView xListView = (XListView) view.findViewById(R.id.a7r);
        this.mListView = xListView;
        xListView.setPullRefreshEnable(false);
        this.mListView.setPullLoadEnable(true);
        this.mListView.setShowHeader(false);
        this.mListView.setNeedProgressBar(true);
        this.mListView.setFooterHintViewVisibility(false);
        this.mListView.setXListViewListener(new XListView.IXListViewListener() { // from class: com.jingdong.common.sample.jshop.fragment.JShopDynamicNormalDetailFragment.1
            {
                JShopDynamicNormalDetailFragment.this = this;
            }

            @Override // com.jingdong.common.sample.jshop.ui.XListView.IXListViewListener
            public void onLoadMore() {
                if (JShopDynamicNormalDetailFragment.this.isRefreshing) {
                    return;
                }
                if (JShopDynamicNormalDetailFragment.this.isLastPage) {
                    Log.d(JShopDynamicNormalDetailFragment.TAG, "  ===  onLoadMore   isLastPage  ===  ");
                    JShopDynamicNormalDetailFragment.this.mListView.stopLoadMore();
                    if (JShopDynamicNormalDetailFragment.this.mActivity instanceof JShopDynamicDetailActivity) {
                        Log.d(JShopDynamicNormalDetailFragment.TAG, "  ===  pullCommentView  ===  ");
                        ((JShopDynamicDetailActivity) JShopDynamicNormalDetailFragment.this.mActivity).pullCommentView();
                        return;
                    }
                    return;
                }
                JShopDynamicNormalDetailFragment.this.mListView.setFooterHintViewVisibility(false);
                JShopDynamicNormalDetailFragment.access$208(JShopDynamicNormalDetailFragment.this);
                JShopDynamicNormalDetailFragment.this.checkBundle();
            }

            @Override // com.jingdong.common.sample.jshop.ui.XListView.IXListViewListener
            public void onRefresh() {
            }
        });
        checkBundle();
    }

    public void retryGetData() {
        this.mCurPage = 1;
        getActivityDetail(1);
    }
}
