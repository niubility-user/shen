package com.jingdong.common.sample.jshopmember;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.n;
import com.jingdong.app.mall.utils.ui.view.JDMultiTextView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.bing.utils.PreferenceUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.MessageRedObserver;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.sample.jshopmember.entity.CouponForPoint;
import com.jingdong.common.sample.jshopmember.entity.CustomerBean;
import com.jingdong.common.sample.jshopmember.entity.JshopCustomer;
import com.jingdong.common.sample.jshopmember.entity.PointsEntrance;
import com.jingdong.common.sample.jshopmember.entity.Privilege2Customer;
import com.jingdong.common.sample.jshopmember.entity.ShopRulesBean;
import com.jingdong.common.sample.jshopmember.ui.JShopHomePopWindow;
import com.jingdong.common.sample.jshopmember.ui.JshopMemberCouponView;
import com.jingdong.common.sample.jshopmember.ui.JshopMemberView;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
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
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopMemberActivity extends MyActivity implements View.OnClickListener, AbsListView.OnScrollListener, MessageRedObserver {
    private static final String GET_SHOP_RULE = "customerCenter";
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final int MSG_UPDATE_MESSAGE = 9999;
    private static final String TAG = "JshopMemberActivity";
    private static final int TYPE_SHOW_NONE = 2;
    private static final int TYPE_SHOW_NUMBER = 0;
    private static final int TYPE_SHOW_NUMBER99 = 4;
    private static final int TYPE_SHOW_REDDOT = 1;
    private View bottomView;
    private RelativeLayout errorView;
    private SimpleDraweeView guideIcon;
    private TextView guideInfo;
    private View imageToTop;
    private boolean isFollowed;
    private RelativeLayout jshop_title_more_layout;
    private ImageView mBackIv;
    private View mErrorView;
    private SimpleDraweeView mJShopProImgSdv;
    private JshopCustomer mJshopCoustomer;
    public JshopMemberView mJshopView;
    public JshopMemberCouponView mMoreCoupon;
    private SimpleDraweeView mMoreSdv;
    private ImageView mTitleImg;
    private RelativeLayout mTitleLayout;
    private View memberShopcart;
    private JDCircleImageView member_img;
    private JDMultiTextView message_door_number;
    private View message_door_number_rl;
    private View message_door_red_dot;
    private JShopHomePopWindow navMenuPopWin;
    private SimpleDraweeView newCustomerIcon;
    private View rightClose;
    public String mShopId = "";
    public String mVendorId = "";
    public String mShopName = "";
    public String mWareId = "";
    private JshopShowErrorViewUtils utils = null;
    private boolean isUploadVipData = true;
    private boolean hasRecommend = false;
    private int followAward = -1;
    private int redPoint_Num = -1;
    private int redPointStyle = 2;
    private boolean isShowRedPoint = false;
    public boolean mMessageFlag = true;
    private View.OnClickListener onBannerMoreClick = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.1
        {
            JshopMemberActivity.this = this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (JshopMemberActivity.this.navMenuPopWin == null) {
                JshopMemberActivity.this.navMenuPopWin = new JShopHomePopWindow(JshopMemberActivity.this);
            }
            JshopMemberActivity.this.navMenuPopWin.showRedPoint(JshopMemberActivity.this.isShowRedPoint, JshopMemberActivity.this.redPoint_Num, JshopMemberActivity.this.redPointStyle);
            JshopMemberActivity.this.navMenuPopWin.setListViewItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.1.1
                {
                    AnonymousClass1.this = this;
                }

                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j2) {
                    if (i2 == 0) {
                        JshopMemberActivity jshopMemberActivity = JshopMemberActivity.this;
                        JDMtaUtils.sendCommonData(jshopMemberActivity, "ShopVIP_GoToMessage", "", "", jshopMemberActivity, jshopMemberActivity.mShopId, JshopMemberActivity.class.getName(), "", "Shop_VIP", JshopMemberActivity.this.mShopId);
                        n.a(JshopMemberActivity.this);
                        JshopMemberActivity.this.navMenuPopWin.dismiss();
                    } else if (i2 != 1) {
                    } else {
                        JshopMemberActivity jshopMemberActivity2 = JshopMemberActivity.this;
                        JDMtaUtils.sendCommonData(jshopMemberActivity2, "ShopVIP_GoToMain", "", "", jshopMemberActivity2, jshopMemberActivity2.mShopId, JshopMemberActivity.class.getName(), "", "Shop_VIP", JshopMemberActivity.this.mShopId);
                        try {
                            JshopMemberUtils.gotoMainFrameClearAllTask(JshopMemberActivity.this);
                        } catch (Throwable th) {
                            if (Log.E) {
                                th.printStackTrace();
                            }
                        }
                        JshopMemberActivity.this.navMenuPopWin.dismiss();
                    }
                }
            });
            JshopMemberActivity.this.navMenuPopWin.showOrClose(JshopMemberActivity.this.findViewById(R.id.jshop_member_title), DPIUtil.getWidth() - DPIUtil.dip2px(161.8f), -DPIUtil.dip2px(25.0f));
            JshopMemberActivity jshopMemberActivity = JshopMemberActivity.this;
            JDMtaUtils.sendCommonData(jshopMemberActivity, "ShopVIP_QuickFunction", "", "", jshopMemberActivity, jshopMemberActivity.mShopId, JshopMemberActivity.class.getName(), "", "Shop_VIP", JshopMemberActivity.this.mShopId);
        }
    };
    public Handler handler = new Handler() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.2
        {
            JshopMemberActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data;
            if (message.what != 9999 || JshopMemberActivity.this.message_door_red_dot == null || JshopMemberActivity.this.message_door_number_rl == null || (data = message.getData()) == null) {
                return;
            }
            int i2 = data.getInt(DeeplinkProductDetailHelper.LAYER_STYLE);
            JshopMemberActivity.this.redPointStyle = i2;
            int i3 = data.getInt("num");
            if (i2 == 1) {
                JshopMemberActivity.this.redPoint_Num = -1;
                JshopMemberActivity.this.isShowRedPoint = true;
                JshopMemberActivity.this.message_door_red_dot.setVisibility(0);
                JshopMemberActivity.this.message_door_number_rl.setVisibility(8);
            } else if (i2 == 0 && i3 > 0) {
                JshopMemberActivity.this.redPoint_Num = i3;
                JshopMemberActivity.this.isShowRedPoint = false;
                JshopMemberActivity.this.message_door_red_dot.setVisibility(8);
                if (i3 <= 9) {
                    JshopMemberActivity.this.message_door_number.setText(i3 + "");
                } else {
                    JshopMemberActivity.this.message_door_number.setText("9+");
                }
                JshopMemberActivity.this.message_door_number_rl.setVisibility(0);
            } else if (i2 != 4 || i3 <= 0) {
                if (i2 == 2) {
                    JshopMemberActivity.this.redPoint_Num = 0;
                    JshopMemberActivity.this.isShowRedPoint = false;
                    JshopMemberActivity.this.message_door_red_dot.setVisibility(8);
                    JshopMemberActivity.this.message_door_number_rl.setVisibility(8);
                }
            } else {
                JshopMemberActivity.this.redPoint_Num = i3;
                JshopMemberActivity.this.isShowRedPoint = false;
                JshopMemberActivity.this.message_door_red_dot.setVisibility(8);
                if (i3 > 99) {
                    JshopMemberActivity.this.message_door_number.setText("99+");
                } else if (i3 > 9) {
                    JshopMemberActivity.this.message_door_number.setText(i3 + "");
                } else {
                    JshopMemberActivity.this.message_door_number.setText(i3 + "");
                }
                JshopMemberActivity.this.message_door_number_rl.setVisibility(0);
            }
        }
    };
    public String mFollowGiftOptCode = "";
    View.OnClickListener mRetryOnclicklistener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.8
        {
            JshopMemberActivity.this = this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setEnabled(false);
            JshopMemberActivity.this.initRequestData();
        }
    };

    /* renamed from: com.jingdong.common.sample.jshopmember.JshopMemberActivity$6 */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 implements HttpGroup.OnAllListener {
        AnonymousClass6() {
            JshopMemberActivity.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            httpResponse.getFastJsonObject();
            JshopMemberActivity jshopMemberActivity = JshopMemberActivity.this;
            jshopMemberActivity.mFollowGiftOptCode = "";
            jshopMemberActivity.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE 
                  (r0v0 'jshopMemberActivity' com.jingdong.common.sample.jshopmember.JshopMemberActivity)
                  (wrap: java.lang.Runnable : 0x000c: CONSTRUCTOR 
                  (r2v0 'this' com.jingdong.common.sample.jshopmember.JshopMemberActivity$6 A[IMMUTABLE_TYPE, THIS])
                  (r3 I:com.jd.framework.json.JDJSONObject A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.common.sample.jshopmember.JshopMemberActivity$6, com.jd.framework.json.JDJSONObject):void (m), WRAPPED] (LINE:3) call: com.jingdong.common.sample.jshopmember.JshopMemberActivity.6.2.<init>(com.jingdong.common.sample.jshopmember.JshopMemberActivity$6, com.jd.framework.json.JDJSONObject):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:3) in method: com.jingdong.common.sample.jshopmember.JshopMemberActivity.6.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                com.jd.framework.json.JDJSONObject r3 = r3.getFastJsonObject()
                com.jingdong.common.sample.jshopmember.JshopMemberActivity r0 = com.jingdong.common.sample.jshopmember.JshopMemberActivity.this
                java.lang.String r1 = ""
                r0.mFollowGiftOptCode = r1
                com.jingdong.common.sample.jshopmember.JshopMemberActivity$6$2 r1 = new com.jingdong.common.sample.jshopmember.JshopMemberActivity$6$2
                r1.<init>()
                r0.post(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.jshopmember.JshopMemberActivity.AnonymousClass6.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            Log.d(JshopMemberActivity.TAG, "======onerror=====");
            JshopMemberActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.6.1
                {
                    AnonymousClass6.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    JshopMemberActivity.this.showFollowToast(JshopMemberActivity.this.getString(R.string.g4), false);
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
            Log.d(JshopMemberActivity.TAG, "onStart");
        }
    }

    public void initRequestData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("venderId", this.mVendorId);
            jSONObject.put("shopId", this.mShopId);
            jSONObject.put("shopName", this.mShopName);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JshopMemberUtils.sendHttpRequest(this, GET_SHOP_RULE, jSONObject, true, new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.3
            {
                JshopMemberActivity.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                JshopMemberActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(JshopMemberActivity.TAG, "\u767b\u5f55\u8df3\u8f6c\u5904\u7406\u8fd4\u56de\u7684\u6570\u636e");
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        JshopMemberActivity.this.resetStatus();
                        JshopMemberActivity.this.parseDataFromJson(fastJsonObject);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JshopMemberActivity.this.toShowErrView();
                JshopMemberActivity.this.setStatusBarLightMode();
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

    private void initUI() {
        this.member_img = (JDCircleImageView) findViewById(R.id.user_img);
        View findViewById = findViewById(R.id.member_list_shopcart);
        this.memberShopcart = findViewById;
        findViewById.setOnClickListener(this);
        JshopMemberView jshopMemberView = (JshopMemberView) findViewById(R.id.member_view);
        this.mJshopView = jshopMemberView;
        jshopMemberView.setMainClickListener(this);
        this.mJshopView.setListScrollListner(this);
        this.mMoreCoupon = (JshopMemberCouponView) findViewById(R.id.jshop_member_coupon);
        View findViewById2 = findViewById(R.id.la);
        this.bottomView = findViewById2;
        findViewById2.setOnClickListener(this);
        View findViewById3 = findViewById(R.id.right_close);
        this.rightClose = findViewById3;
        findViewById3.setOnClickListener(this);
        View findViewById4 = findViewById(R.id.product_list_to_top);
        this.imageToTop = findViewById4;
        findViewById4.setOnClickListener(this);
        this.guideIcon = (SimpleDraweeView) findViewById(R.id.afi);
        this.guideInfo = (TextView) findViewById(R.id.guide_info);
        this.errorView = (RelativeLayout) findViewById(R.id.error_view);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.new_right_float_icon);
        this.newCustomerIcon = simpleDraweeView;
        simpleDraweeView.setOnClickListener(this);
        this.mJShopProImgSdv = (SimpleDraweeView) findViewById(R.id.jshop_big_promotion_img_member);
    }

    private void requestRedPoint() {
        if (LoginUserBase.hasLogin() && this.mMessageFlag) {
            this.mMessageFlag = false;
            NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
            NewMessagRedManager.requestMessage(getHttpGroupaAsynPool());
        }
    }

    public void resetStatus() {
        this.mJshopView.reset();
        this.mMoreCoupon.clear();
    }

    public void setStatusBarLightMode() {
        if (UnStatusBarTintUtil.setStatusBarLightMode(this)) {
            return;
        }
        UnStatusBarTintUtil.setBackgroundColor(this, getResources().getColor(R.color.ln));
    }

    public void showFollowToast(String str, boolean z) {
        if (z) {
            ToastUtils.showToastInCenter((Context) this, (byte) 2, str, 0);
        } else {
            ToastUtils.showToastInCenter((Context) this, (byte) 1, str, 0);
        }
    }

    public void showNewUserAlert() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("followShop");
        httpSetting.putJsonParam("shopId", this.mShopId);
        httpSetting.putJsonParam("follow", Boolean.TRUE);
        httpSetting.putJsonParam("sourceRpc", JshopNewFavoUtils.SOURCE_RPC_CUSTOM_CENTER_COMERGIFT);
        httpSetting.putJsonParam("award", DYConstants.DY_TRUE);
        httpSetting.setEffect(1);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new AnonymousClass6());
        getHttpGroupaAsynPool().add(httpSetting);
    }

    public String getMemberLevel() {
        CustomerBean customerBean;
        JshopCustomer jshopCustomer = this.mJshopCoustomer;
        return (jshopCustomer == null || !jshopCustomer.isShopCustomer || (customerBean = jshopCustomer.customer) == null) ? "0" : customerBean.customerLevel;
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        return this.mShopId;
    }

    public void gotoShopDetail() {
    }

    public void initMemberTitle() {
        ((RelativeLayout) findViewById(R.id.jshop_member_title_layout)).setLayoutParams(new LinearLayout.LayoutParams(-1, com.jingdong.sdk.utils.DPIUtil.dip2px(this, JShopUtil.isNorch(this) ? 90 : 70)));
        this.mTitleLayout = (RelativeLayout) findViewById(R.id.jy);
        ImageView imageView = (ImageView) findViewById(R.id.jshop_mem_title_img);
        this.mTitleImg = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) findViewById(R.id.qc);
        this.mBackIv = imageView2;
        setTitleBack(imageView2);
        this.mMoreSdv = (SimpleDraweeView) findViewById(R.id.jshop_title_more_img);
        this.message_door_red_dot = findViewById(R.id.jshop_home_message_red_point);
        this.message_door_number = (JDMultiTextView) findViewById(R.id.jshop_home_msg_num);
        this.message_door_number_rl = findViewById(R.id.jshop_home_msg_num_rl);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.jshop_title_more_layout);
        this.jshop_title_more_layout = relativeLayout;
        relativeLayout.setOnClickListener(this.onBannerMoreClick);
    }

    public boolean isCanTake(CouponForPoint couponForPoint) {
        CustomerBean customerBean;
        if (couponForPoint == null) {
            return false;
        }
        JshopCustomer jshopCustomer = this.mJshopCoustomer;
        if (jshopCustomer == null || (customerBean = jshopCustomer.customer) == null || customerBean.customerPoint >= couponForPoint.points) {
            return true;
        }
        ToastUtils.showToastInCenter((Context) this, (byte) 1, getString(R.string.jshop_member_take_coupon_score_error), 0);
        return false;
    }

    public boolean isOpenDiscount() {
        List<ShopRulesBean> list;
        int size;
        JshopCustomer jshopCustomer = this.mJshopCoustomer;
        if (jshopCustomer != null && (list = jshopCustomer.shopRules) != null && (size = list.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                ShopRulesBean shopRulesBean = this.mJshopCoustomer.shopRules.get(i2);
                if (shopRulesBean != null && !TextUtils.isEmpty(shopRulesBean.discount)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        JshopMemberCouponView jshopMemberCouponView = this.mMoreCoupon;
        if (jshopMemberCouponView != null && jshopMemberCouponView.getVisibility() == 0) {
            this.mMoreCoupon.setVisibility(8);
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CustomerBean customerBean;
        int i2;
        String str;
        switch (view.getId()) {
            case R.id.la /* 2131689911 */:
                JDMtaUtils.sendCommonData(this, "ShopVIP_SaleProductPop", "", "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                JshopMemberView jshopMemberView = this.mJshopView;
                if (jshopMemberView != null) {
                    jshopMemberView.setSelection(1);
                    return;
                }
                return;
            case R.id.coupon_center /* 2131691193 */:
            case R.id.coupon_left /* 2131691229 */:
            case R.id.coupon_right /* 2131691257 */:
                CouponForPoint couponForPoint = (CouponForPoint) view.getTag();
                if (isCanTake(couponForPoint)) {
                    JshopMemberUtils.takeCoupon(this, couponForPoint, this.mVendorId);
                    return;
                }
                return;
            case R.id.jshop_scroe_layout /* 2131693960 */:
                Log.d(TAG, "\u79ef\u5206\u70b9\u51fb");
                long j2 = 0;
                JshopCustomer jshopCustomer = this.mJshopCoustomer;
                if (jshopCustomer != null && (customerBean = jshopCustomer.customer) != null) {
                    j2 = customerBean.customerPoint;
                }
                Intent intent = new Intent(this, JshopMemberScoreActivity.class);
                intent.putExtra("shopId", this.mShopId);
                intent.putExtra("vendorId", this.mVendorId);
                intent.putExtra(JshopConst.JSKEY_MEMBER_SCORE, j2);
                startActivity(intent);
                JDMtaUtils.sendCommonData(this, "ShopVIP_Point", "" + j2, "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                return;
            case R.id.layout_user_point_get /* 2131694024 */:
                PointsEntrance pointsEntrance = this.mJshopCoustomer.pointsEntrance;
                if (pointsEntrance == null) {
                    return;
                }
                if (!TextUtils.isEmpty(pointsEntrance.earnPointsUrl)) {
                    JShopUtil.toWebWithLogin(this, this.mJshopCoustomer.pointsEntrance.earnPointsUrl);
                }
                boolean isEmpty = TextUtils.isEmpty(this.mJshopCoustomer.pointsEntrance.tag + "");
                String str2 = DYConstants.DY_NULL_STR;
                if (!isEmpty && (i2 = this.mJshopCoustomer.pointsEntrance.tag) != 0) {
                    if (i2 == 1) {
                        str2 = "\u7b7e\u5230";
                    } else if (i2 == 2) {
                        str2 = "\u62bd\u5956";
                    }
                }
                JDMtaUtils.sendCommonData(this, "ShopVIP_GetOrSpendPoint", this.mJshopCoustomer.pointsEntrance.earnPointsContent + CartConstant.KEY_YB_INFO_LINK + this.mJshopCoustomer.pointsEntrance.earnPointsCount + CartConstant.KEY_YB_INFO_LINK + str2, "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                return;
            case R.id.layout_user_point_use /* 2131694025 */:
                PointsEntrance pointsEntrance2 = this.mJshopCoustomer.pointsEntrance;
                if (pointsEntrance2 == null) {
                    return;
                }
                if (!TextUtils.isEmpty(pointsEntrance2.spendPointsUrl)) {
                    JShopUtil.toWebWithLogin(this, this.mJshopCoustomer.pointsEntrance.spendPointsUrl);
                }
                JDMtaUtils.sendCommonData(this, "ShopVIP_GetOrSpendPoint", this.mJshopCoustomer.pointsEntrance.spendPointsContent + CartConstant.KEY_YB_INFO_LINK + this.mJshopCoustomer.pointsEntrance.spendPointsCount + "_null", "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                return;
            case R.id.member_list_shopcart /* 2131694212 */:
                DeepLinkCartHelper.startCartMain(this, null);
                return;
            case R.id.member_more_coupon /* 2131694214 */:
                Log.d(TAG, "\u66f4\u591a\u4f18\u60e0\u5238");
                JshopCustomer jshopCustomer2 = this.mJshopCoustomer;
                JDMtaUtils.sendCommonData(this, "ShopVIP_MoreCoupon", (jshopCustomer2 == null || jshopCustomer2.points2Coupon == null) ? "0" : "" + this.mJshopCoustomer.points2Coupon.size(), "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                JshopMemberCouponView jshopMemberCouponView = this.mMoreCoupon;
                if (jshopMemberCouponView != null) {
                    jshopMemberCouponView.showCoupView();
                    return;
                }
                return;
            case R.id.new_right_float_icon /* 2131694256 */:
                Log.d(TAG, "\u65b0\u4eba\u6709\u793c\u54c1");
                final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this, "\u786e\u8ba4\u9886\u53d6\u60a8\u7684\u65b0\u4eba\u793c\u54c1\u5417\uff1f", "\u6211\u518d\u60f3\u60f3", "\u786e\u8ba4\u9886\u53d6");
                createJdDialogWithStyle2.show();
                createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.4
                    {
                        JshopMemberActivity.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        createJdDialogWithStyle2.dismiss();
                    }
                });
                createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.5
                    {
                        JshopMemberActivity.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        createJdDialogWithStyle2.dismiss();
                        JshopMemberActivity.this.showNewUserAlert();
                    }
                });
                String str3 = this.mShopId;
                JDMtaUtils.sendCommonData(this, "ShopVIP_GiftForNewMan", "", "", this, str3, "", "", "Shop_VIP", str3);
                return;
            case R.id.product_list_to_top /* 2131694347 */:
                this.mJshopView.gotoTop();
                return;
            case R.id.right_close /* 2131694428 */:
                JDMtaUtils.sendCommonData(this, "ShopVIP_CloseSaleProductPop", "", "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                this.mJshopView.setEndBottomVisiability(8);
                this.bottomView.setVisibility(8);
                PreferenceUtil.put(this, "member_float_bottom", "1");
                return;
            case R.id.to_rule_detail /* 2131694541 */:
                Log.d(TAG, "\u89c4\u5219\u8be6\u60c5");
                Intent intent2 = new Intent(this, JshopMemberRuleActivity.class);
                intent2.putExtra("shopId", this.mShopId);
                intent2.putExtra("memberRule", this.mJshopCoustomer);
                startActivity(intent2);
                StringBuilder sb = new StringBuilder();
                JshopCustomer jshopCustomer3 = this.mJshopCoustomer;
                if (jshopCustomer3 == null || jshopCustomer3.customer == null) {
                    str = "0_0_0_0_0";
                } else {
                    List<Privilege2Customer> list = jshopCustomer3.privilege2CustomerList;
                    if (list != null && !list.isEmpty()) {
                        int size = list.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            sb.append(list.get(i3).privilegeName);
                            if (i3 != size - 1) {
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                            }
                        }
                        if (size == 1) {
                            sb.append("_null_null_null_null");
                        } else if (size == 2) {
                            sb.append("_null_null_null");
                        } else if (size == 3) {
                            sb.append("_null_null");
                        } else if (size == 4) {
                            sb.append("_null");
                        }
                    } else {
                        sb.append("0_0_0_0_0");
                    }
                    str = sb.toString();
                }
                Log.d(TAG, "event_param = " + str);
                JDMtaUtils.sendCommonData(this, "ShopVIP_Rule", str, "", this, getMemberLevel(), "", "", "Shop_VIP", this.mShopId);
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        setContentView(R.layout.jshop_member_activity);
        if (Build.VERSION.SDK_INT >= 23) {
            setStatusBarLightMode();
        }
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.mShopId = intent.getExtras().getString("shopId");
                String string = intent.getExtras().getString("venderId");
                this.mVendorId = string;
                if (TextUtils.isEmpty(string)) {
                    this.mVendorId = intent.getExtras().getString("vendorId");
                }
                this.mShopName = intent.getExtras().getString("shopName");
                this.mWareId = intent.getExtras().getString("wareId");
                this.followAward = intent.getExtras().getInt("followAward");
                this.isFollowed = intent.getBooleanExtra(JshopConst.FOLLOWED_KEY, false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        initMemberTitle();
        initUI();
        initRequestData();
        setShopId(this.mShopId);
        setPageId("Shop_VIP");
        if (JshopMemberUtils.isFollowGift(this.followAward)) {
            String str = this.mShopId;
            JDMtaUtils.sendCommonData(this, "ShopVIP_GiftForNewManExp", "", "", this, str, "", "", "Shop_VIP", str);
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        JshopMemberView jshopMemberView = this.mJshopView;
        if (jshopMemberView != null) {
            jshopMemberView.onDestory();
        }
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
        NewMessageRedInfo newMessageRedInfo;
        int i2;
        if (map == null || !map.containsKey(MESSAGE_REDKEY) || (newMessageRedInfo = map.get(MESSAGE_REDKEY)) == null) {
            return;
        }
        if (newMessageRedInfo.isShowRedDot()) {
            i2 = 1;
        } else if (newMessageRedInfo.isShow9Number()) {
            i2 = 0;
        } else {
            i2 = newMessageRedInfo.isShow99Number() ? 4 : 2;
        }
        Message obtain = Message.obtain();
        obtain.what = 9999;
        Bundle bundle = new Bundle();
        bundle.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, i2);
        bundle.putInt("num", newMessageRedInfo.num);
        obtain.setData(bundle);
        this.handler.sendMessage(obtain);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Handler handler = this.handler;
        if (handler != null && handler.hasMessages(9999)) {
            this.handler.removeMessages(9999);
        }
        NewMessagRedManager.deregisterPersonalMessageObserver(this);
        this.mMessageFlag = true;
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
        NewMessagRedManager.registPersonalMessageObserver(this);
        requestRedPoint();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        if (i2 > 5) {
            if (this.imageToTop.getVisibility() != 0) {
                this.imageToTop.setVisibility(0);
            }
        } else if (this.imageToTop.getVisibility() != 4) {
            this.imageToTop.setVisibility(4);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i2) {
    }

    public void parseDataFromJson(JDJSONObject jDJSONObject) {
        CustomerBean customerBean;
        if (jDJSONObject != null) {
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("result");
            JshopCustomer jshopCustomer = new JshopCustomer(optJSONObject);
            this.mJshopCoustomer = jshopCustomer;
            jshopCustomer.mJshopId = this.mShopId;
            jshopCustomer.mVendorId = this.mVendorId;
            this.followAward = optJSONObject.optInt("followAward");
            JshopCustomer jshopCustomer2 = this.mJshopCoustomer;
            if (jshopCustomer2.isShopCustomer && ((customerBean = jshopCustomer2.customer) == null || TextUtils.isEmpty(customerBean.customerLevel))) {
                toShowErrView();
                return;
            }
            CustomerBean customerBean2 = this.mJshopCoustomer.customer;
            if (customerBean2 != null) {
                JshopMemberUtils.setUserImage(this, this.member_img, customerBean2.customerHead);
            }
            JshopMemberUtils.showChannelImage(this, this.mTitleImg, optJSONObject.optString("titleImg"));
            this.mJshopView.setHeaderData(this.mJshopCoustomer);
            this.mJshopView.setVisibility(0);
            View view = this.mErrorView;
            if (view != null) {
                view.setVisibility(8);
            }
            this.mJshopView.mPage = 1;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("venderId", this.mVendorId);
                jSONObject.put("pageIdx", 1);
                jSONObject.put("pageSize", 20);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.mJshopView.sendRecommendRequest(this, jSONObject, true);
            this.mMoreCoupon.updateCouponUI(this.mJshopCoustomer);
            if (optJSONObject.containsKey("lvl2skin") && !TextUtils.isEmpty(optJSONObject.optString("lvl2skin"))) {
                this.mJShopProImgSdv.setVisibility(0);
                JDImageUtils.displayImage(optJSONObject.optString("lvl2skin"), this.mJShopProImgSdv, null, false, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.9
                    {
                        JshopMemberActivity.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view2) {
                        JshopMemberActivity.this.setStatusBarLightMode();
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view2, Bitmap bitmap) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            UnStatusBarTintUtil.setStatusBarDarkMode(JshopMemberActivity.this);
                        }
                        JshopMemberActivity.this.mBackIv.setImageResource(R.drawable.common_title_back_selector_white);
                        JshopMemberActivity.this.mMoreSdv.setImageResource(R.drawable.jshop_home_header_more_icon_white);
                        JshopMemberActivity.this.mTitleLayout.setBackgroundColor(JshopMemberActivity.this.getResources().getColor(R.color.lu));
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view2, JDFailReason jDFailReason) {
                        JshopMemberActivity.this.setStatusBarLightMode();
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view2) {
                    }
                }, null);
                return;
            }
            setStatusBarLightMode();
            this.mJShopProImgSdv.setVisibility(8);
        }
    }

    public void setShopVIP_VIPParam(boolean z) {
        String str;
        int i2;
        JshopCustomer jshopCustomer = this.mJshopCoustomer;
        if (jshopCustomer == null || jshopCustomer.customer == null || !this.isUploadVipData) {
            str = "0_0_0_0_0_0_0";
        } else {
            List<CouponForPoint> list = jshopCustomer.points2Coupon;
            String str2 = (list == null || list.isEmpty()) ? "_0" : "_1";
            String str3 = isOpenDiscount() ? "_1" : "_0";
            String str4 = z ? "_1" : "_0";
            String str5 = this.bottomView.getVisibility() == 0 ? "_1" : "_0";
            StringBuilder sb = new StringBuilder();
            try {
                i2 = Integer.parseInt(String.valueOf(this.mJshopCoustomer.customer.customerPoint));
            } catch (NumberFormatException e2) {
                Log.e(TAG, TAG, e2);
                i2 = 0;
            }
            String str6 = CartConstant.KEY_YB_INFO_LINK + i2;
            sb.append(getMemberLevel());
            sb.append(this.mJshopCoustomer.isPointsEnabled ? "_1" : "_0");
            sb.append(str3);
            sb.append(str2);
            sb.append(str4);
            sb.append(str5);
            sb.append(str6);
            str = sb.toString();
            this.isUploadVipData = false;
        }
        String str7 = str;
        Log.d(TAG, "event_param = " + str7);
        JDMtaUtils.sendCommonData(this, "ShopVIP_VIPParam", str7, "", this, "", "", "", "Shop_VIP", "");
    }

    public void showAddCartMenu() {
        View view = this.memberShopcart;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void showBottomFloatView(JDJSONObject jDJSONObject) {
        showRightFloatIcon();
        if ("1".equals(PreferenceUtil.get(this, "member_float_bottom")) || this.bottomView == null || this.guideInfo == null || jDJSONObject == null) {
            return;
        }
        String optString = jDJSONObject.optString("guideInfo");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        this.bottomView.setVisibility(0);
        this.guideInfo.setText(optString);
        JDImageUtils.displayImage(jDJSONObject.optString("guideImgPath"), this.guideIcon);
        this.mJshopView.setEndBottomVisiability(0);
    }

    public void showRightFloatIcon() {
        if (JshopMemberUtils.isFollowGift(this.followAward)) {
            this.newCustomerIcon.setVisibility(0);
        } else {
            this.newCustomerIcon.setVisibility(8);
        }
    }

    public void toShowErrView() {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.7
            {
                JshopMemberActivity.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JshopMemberActivity.this.utils == null) {
                    JshopMemberActivity jshopMemberActivity = JshopMemberActivity.this;
                    jshopMemberActivity.utils = new JshopShowErrorViewUtils(jshopMemberActivity, jshopMemberActivity.errorView);
                }
                JshopMemberActivity jshopMemberActivity2 = JshopMemberActivity.this;
                jshopMemberActivity2.mErrorView = jshopMemberActivity2.utils.getErrorViewHasRetry(JshopMemberActivity.this.mRetryOnclicklistener);
                JshopMemberActivity.this.utils.setMessageInfo(JshopMemberActivity.this.getString(R.string.jshop_net_fail), JshopMemberActivity.this.getString(R.string.jshop_net_check), "");
                JshopMemberActivity.this.utils.setErrorImage(R.drawable.y_03);
                JshopMemberActivity.this.mErrorView.setVisibility(0);
                JshopMemberActivity.this.mTitleImg.setImageDrawable(JshopMemberActivity.this.getResources().getDrawable(R.drawable.jshop_title_text_member_center));
                JshopMemberActivity.this.mTitleImg.setVisibility(0);
                JshopMemberActivity.this.mJshopView.setVisibility(8);
            }
        });
    }

    public void updateMemberScore(final long j2) {
        post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.JshopMemberActivity.10
            {
                JshopMemberActivity.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j3 = JshopMemberActivity.this.mJshopCoustomer.customer.customerPoint - j2;
                if (j3 > 0) {
                    JshopMemberActivity.this.mJshopView.updateScore("" + j3);
                    JshopMemberActivity.this.mJshopCoustomer.customer.customerPoint = j3;
                    return;
                }
                JshopMemberActivity.this.mJshopView.updateScore("0");
                JshopMemberActivity.this.mJshopCoustomer.customer.customerPoint = 0L;
            }
        });
    }
}
