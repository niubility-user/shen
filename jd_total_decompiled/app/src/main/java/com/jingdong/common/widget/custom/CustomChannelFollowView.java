package com.jingdong.common.widget.custom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.sec.LogoManager;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.R;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import de.greenrobot.event.EventBus;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class CustomChannelFollowView extends RelativeLayout {
    private static final String FOLLOW_TYPE_AUTO = "FOLLOW_TYPE_AUTO";
    private static final String FOLLOW_TYPE_BTN = "FOLLOW_TYPE_BTN";
    private static final String FOLLOW_TYPE_FLOAT = "FOLLOW_TYPE_FLOAT";
    private static final String SP_FOLLOW_BEAN = "channel_follow_bean";
    private static final String SP_FOLLOW_COUPON = "channel_follow_coupon";
    private static final String SP_FOLLOW_TIPS = "channel_follow_tips";
    private static final String SP_IS_FIRST_FOLLOW = "is_first_follow";
    private static final String TAG = "CustomChannelFollowView";
    @SuppressLint({"HandlerLeak"})
    private static JDHandler handler = new JDHandler();
    private boolean bAttachToWindow;
    private boolean bIsFetching;
    private boolean bShowBlack;
    private boolean bShowFollow;
    private boolean bShowFollowTips;
    private boolean bShowNormalPopTips;
    private boolean bShowRightPopTips;
    private String batchId;
    private RelativeLayout container;
    private RelativeLayout.LayoutParams containerLp;
    private boolean firstLogined;
    public boolean isShowWindow;
    private boolean isUpdateLayout;
    private IsUserFollowEntity isUserFollowEntity;
    private RelativeLayout.LayoutParams ivFollowLp;
    private RelativeLayout.LayoutParams ivGiftLp;
    private View.OnLayoutChangeListener layoutChangeListener;
    private String mActivityType;
    int mBottom;
    ViewGroup mContainer;
    Context mContext;
    private PopupWindow mFollowGiftPopupWindow;
    private PopupWindow mFollowGiftSuccessWindow;
    private PopupWindow mFollowTipsPopupWindow;
    private boolean mIsFloatBarModel;
    public AtomicBoolean mIsShowing;
    private ImageView mIvFollow;
    private SimpleDraweeView mIvGift;
    private String mThemeId;
    int mTime;
    private String roleId;
    private String screenWH;
    private ChannelFollowSuccessPopupWindow successPopupWindow;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.widget.custom.CustomChannelFollowView$5  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass5 implements HttpGroup.OnCommonListener {
        final /* synthetic */ boolean val$follow;

        AnonymousClass5(boolean z) {
            this.val$follow = z;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            CustomChannelFollowView.this.bIsFetching = false;
            final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                return;
            }
            CustomChannelFollowView.this.post(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.5.1
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                    CustomChannelFollowView.this.clickBtnEvent(anonymousClass5.val$follow, true);
                    if (CustomChannelFollowView.this.mIsFloatBarModel) {
                        AnonymousClass5 anonymousClass52 = AnonymousClass5.this;
                        CustomChannelFollowView.this.followGuidBtnEvent(anonymousClass52.val$follow, true);
                    }
                    String optString = fastJsonObject.optString("themeText", "");
                    fastJsonObject.optString("guideText", "");
                    fastJsonObject.optString("guideFollowText", "");
                    fastJsonObject.optString("showGuide", "");
                    String optString2 = fastJsonObject.optString("resultKey", "");
                    String optString3 = fastJsonObject.optString(CartConstant.KEY_CART_RESULTMSG, "");
                    String optString4 = fastJsonObject.optString("resultCode", "0");
                    String optString5 = fastJsonObject.optString(JshopConst.JSHOP_ACTIVITY_TYPE, "0");
                    String activityType = CustomChannelFollowView.this.getActivityType(optString5);
                    AnonymousClass5 anonymousClass53 = AnonymousClass5.this;
                    if (anonymousClass53.val$follow) {
                        CustomChannelFollowView.this.bShowFollow = false;
                        CustomChannelFollowView.this.showUnFollowIcon();
                        if (!"0".equals(optString5)) {
                            if ("1".equals(activityType) && "1".equals(optString4) && CustomChannelFollowView.this.isUserFollowEntity != null && CustomChannelFollowView.this.isUserFollowEntity.coupon != null && !TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.coupon.encryptedKey) && !TextUtils.isEmpty(optString2) && CustomChannelFollowView.this.isUserFollowEntity.coupon.encryptedKey.equals(optString2)) {
                                if (!SwitchQueryFetcher.getSwitchBooleanValue("follow_degraded", false) && !"2".equals(JDBModeUtils.getCurrentMode())) {
                                    String format = String.format("\u606d\u559c\u83b7\u5f971\u5f20\u6ee1%s\u51cf%s\u5238", CustomChannelFollowView.this.isUserFollowEntity.coupon.quotaValue, CustomChannelFollowView.this.isUserFollowEntity.coupon.discount);
                                    CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                                    customChannelFollowView.startXView2(customChannelFollowView.mContext, "showLayer", "hasFollowGift", format);
                                    CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatSuccessExpo");
                                } else {
                                    CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                                    customChannelFollowView2.showFollowCouponSuccessWindow(customChannelFollowView2.isUserFollowEntity.coupon);
                                }
                            } else if ("2".equals(activityType) && "1".equals(optString4)) {
                                if (!SwitchQueryFetcher.getSwitchBooleanValue("follow_degraded", false) && !"2".equals(JDBModeUtils.getCurrentMode())) {
                                    CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatSuccessExpo");
                                    String format2 = CustomChannelFollowView.this.isUserFollowEntity.jingBeanResult != null ? String.format("\u606d\u559c\u60a8\uff0c\u83b7\u5f97%s\u4eac\u8c46", Integer.valueOf(CustomChannelFollowView.this.isUserFollowEntity.jingBeanResult.amount)) : "";
                                    CustomChannelFollowView customChannelFollowView3 = CustomChannelFollowView.this;
                                    customChannelFollowView3.startXView2(customChannelFollowView3.mContext, "showLayer", "hasFollowBeans", format2);
                                } else {
                                    CustomChannelFollowView customChannelFollowView4 = CustomChannelFollowView.this;
                                    customChannelFollowView4.showFollowBeansSuccessWindow(customChannelFollowView4.isUserFollowEntity.jingBeanResult, optString3);
                                }
                            } else if (!TextUtils.isEmpty(optString3)) {
                                CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatFailExpo");
                                ToastUtils.showToastInCenter(CustomChannelFollowView.this.getContext(), optString3);
                            }
                        } else if (SwitchQueryFetcher.getSwitchBooleanValue("follow_degraded", false) || "2".equals(JDBModeUtils.getCurrentMode())) {
                            CustomChannelFollowView.this.showFollowToast();
                        } else {
                            CustomChannelFollowView customChannelFollowView5 = CustomChannelFollowView.this;
                            customChannelFollowView5.startXView2(customChannelFollowView5.mContext, "showLayer", "unHasFollowGift", "");
                        }
                    } else {
                        CustomChannelFollowView.this.bShowFollow = true;
                        if (CustomChannelFollowView.this.isUserFollowEntity == null) {
                            CustomChannelFollowView.this.showFollowIcon();
                        } else if ("1".equals(activityType) && CustomChannelFollowView.this.isUserFollowEntity.coupon != null && !TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.coupon.encryptedKey) && CustomChannelFollowView.this.isUserFollowEntity.coupon.encryptedKey.equals(optString2)) {
                            CustomChannelFollowView.this.showFollowGitIcon(activityType);
                        } else if ("2".equals(activityType)) {
                            CustomChannelFollowView.this.showFollowGitIcon(activityType);
                        } else {
                            CustomChannelFollowView.this.showFollowIcon();
                        }
                        if (!TextUtils.isEmpty(optString)) {
                            ToastUtils.showToastInCenter(CustomChannelFollowView.this.getContext(), optString);
                        }
                    }
                    FollowBaseEvent followBaseEvent = new FollowBaseEvent(FollowBaseEvent.TYPE_FLOATMODE);
                    Bundle bundle = new Bundle();
                    bundle.putString("themeId", CustomChannelFollowView.this.mThemeId);
                    bundle.putBoolean("followStatus", CustomChannelFollowView.this.bShowFollow);
                    bundle.putBoolean("channelGuidMode", CustomChannelFollowView.this.mIsFloatBarModel);
                    followBaseEvent.setBundle(bundle);
                    EventBus.getDefault().post(followBaseEvent);
                    CustomChannelFollowView.this.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.5.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CustomChannelFollowView.this.bShowFollow) {
                                CustomChannelFollowView.this.setContentDescription("\u5173\u6ce8");
                            } else {
                                CustomChannelFollowView.this.setContentDescription("\u5df2\u5173\u6ce8");
                            }
                        }
                    }, 110L);
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            CustomChannelFollowView.this.bIsFetching = false;
            Context context = CustomChannelFollowView.this.getContext();
            StringBuilder sb = new StringBuilder();
            sb.append(this.val$follow ? "\u5173\u6ce8" : "\u53d6\u6d88\u5173\u6ce8");
            sb.append("\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u5c1d\u8bd5");
            ToastUtils.showToastInCenter(context, sb.toString());
            if (CustomChannelFollowView.this.mIsFloatBarModel) {
                CustomChannelFollowView.this.followGuidBtnEvent(this.val$follow, false);
            }
            CustomChannelFollowView.this.clickBtnEvent(this.val$follow, false);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes12.dex */
    public static class CouponEntity {
        public String batchId;
        public String couponIcon;
        public String discount;
        public String encryptedKey;
        public String limitStr;
        public String msg;
        public String quota;
        public String quotaValue;
        public String roleId;
    }

    /* loaded from: classes12.dex */
    public static class IsUserFollowEntity {
        public String activityType;
        public String code;
        public CouponEntity coupon;
        public String followStatus;
        public JingBeanResultEntity jingBeanResult;
        public int maxToast;
        public String showFirstToast;
        public String showFollow;
        public String showTips;
        public String themeIcon;
        public String themeName;
        public String themeText;
    }

    /* loaded from: classes12.dex */
    public static class JingBeanResultEntity {
        public int amount;
        public String jingBeanIcon;
        public int jingBeanId;
        public String jingBeanUrl;
        public String msg;
    }

    public CustomChannelFollowView(@NonNull Context context) {
        this(context, null);
    }

    private boolean canShowTips() {
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        int i2 = rect.left;
        int i3 = i2 - 4;
        this.bShowNormalPopTips = i3 <= getScreenWidth() - get750(218);
        this.bShowRightPopTips = i3 <= getScreenWidth() - get750(30);
        Log.d(TAG, "x is " + i2 + " getScreenWidth: " + getScreenWidth() + " and bShowNormalPop is " + this.bShowNormalPopTips + " and bShowRightPopTips is " + this.bShowRightPopTips);
        return this.bShowNormalPopTips || this.bShowRightPopTips;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsFollow(final boolean z) {
        if (TextUtils.isEmpty(this.mThemeId)) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("eid", (Object) getFinger());
        jDJSONObject.put("referUrl", (Object) (-1));
        jDJSONObject.put("shshshfp", (Object) (-1));
        jDJSONObject.put("openId", (Object) (-1));
        jDJSONObject.put("isRvc", (Object) 0);
        jDJSONObject.put(WebPerfManager.FP, (Object) 0);
        jDJSONObject.put("shshshfpa", (Object) (-1));
        jDJSONObject.put("userAgent", (Object) (-1));
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("isUserFollow");
        httpSetting.putJsonParam("businessId", "1");
        httpSetting.putJsonParam("themeId", this.mThemeId);
        httpSetting.putJsonParam("informationParam", jDJSONObject);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d(CustomChannelFollowView.TAG, "onEnd");
                CustomChannelFollowView.this.isUserFollowEntity = (IsUserFollowEntity) JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), IsUserFollowEntity.class);
                Log.d(CustomChannelFollowView.TAG, "isUserFollowEntity" + CustomChannelFollowView.this.isUserFollowEntity);
                if (CustomChannelFollowView.this.isUserFollowEntity == null) {
                    return;
                }
                CustomChannelFollowView.handler.post(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(CustomChannelFollowView.TAG, "isUserFollowEntity.showFollow" + CustomChannelFollowView.this.isUserFollowEntity.showFollow);
                        if (!TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.showFollow) && "0".equals(CustomChannelFollowView.this.isUserFollowEntity.showFollow)) {
                            Log.d(CustomChannelFollowView.TAG, "showFollow is 0,\u6ca1\u6709\u88ab\u7070\u5230");
                            return;
                        }
                        Log.d(CustomChannelFollowView.TAG, "isUserFollowEntity.followStatus" + CustomChannelFollowView.this.isUserFollowEntity.followStatus);
                        if (TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.followStatus)) {
                            return;
                        }
                        if (!"1".equals(CustomChannelFollowView.this.isUserFollowEntity.followStatus) && CustomChannelFollowView.this.mIsFloatBarModel) {
                            CustomChannelFollowView.this.refreshGuidFollowView();
                        }
                        CustomChannelFollowView.this.mIvFollow.setVisibility(0);
                        CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                        customChannelFollowView.exposureFollowBtn("1".equals(customChannelFollowView.isUserFollowEntity.followStatus));
                        CustomChannelFollowView.this.onLayoutChanged();
                        Log.d(CustomChannelFollowView.TAG, "checkIsFollow onLayoutChanged");
                        CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                        customChannelFollowView2.mActivityType = customChannelFollowView2.getActivityType(customChannelFollowView2.isUserFollowEntity.activityType);
                        if ("1".equals(CustomChannelFollowView.this.isUserFollowEntity.followStatus)) {
                            CustomChannelFollowView.this.bShowFollow = false;
                            CustomChannelFollowView.this.showUnFollowIcon();
                        } else {
                            CustomChannelFollowView.this.bShowFollow = true;
                            if (!"1".equals(CustomChannelFollowView.this.mActivityType) && !"2".equals(CustomChannelFollowView.this.mActivityType)) {
                                CustomChannelFollowView.this.showFollowIcon();
                            } else {
                                CustomChannelFollowView customChannelFollowView3 = CustomChannelFollowView.this;
                                customChannelFollowView3.showFollowGitIcon(customChannelFollowView3.mActivityType);
                            }
                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                            if (z) {
                                CustomChannelFollowView.this.startFollow(true, CustomChannelFollowView.FOLLOW_TYPE_AUTO);
                            } else if ("0".equals(CustomChannelFollowView.this.isUserFollowEntity.showFirstToast)) {
                                return;
                            } else {
                                CustomChannelFollowView customChannelFollowView4 = CustomChannelFollowView.this;
                                customChannelFollowView4.showFollowTips(customChannelFollowView4.mActivityType);
                            }
                        }
                        if (CustomChannelFollowView.this.bShowFollow) {
                            CustomChannelFollowView.this.setContentDescription("\u5173\u6ce8");
                        } else {
                            CustomChannelFollowView.this.setContentDescription("\u5df2\u5173\u6ce8");
                        }
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(CustomChannelFollowView.TAG, "onError");
                if (CustomChannelFollowView.this.mIsFloatBarModel) {
                    CustomChannelFollowView.this.mIsShowing.set(false);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickBtnEvent(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(z2 ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append((this.bShowFollowTips && z) ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMtaActivityId());
        JDMtaUtils.onClickWithPageId(getContext(), "Focus_FocusBtn", TAG, sb.toString(), this.mThemeId, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickFollowTipClose() {
        JDMtaUtils.onClickWithPageId(getContext(), "FocusFloatBar_FocusClose", TAG, getMtaActivityId(), this.mThemeId, null);
    }

    private double div(double d, double d2, int i2) {
        if (i2 >= 0) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i2, 4).doubleValue();
        }
        throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }

    private void exposeGuidFollowTips() {
        JDMtaUtils.sendExposureData(getContext(), TAG, null, this.mThemeId, "FocusFloatBar_FocusExpo", null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exposureFollowBtn(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(TextUtils.isEmpty(this.roleId) ? "" : this.roleId);
        JDMtaUtils.sendExposureData(getContext(), TAG, null, this.mThemeId, "Focus_FocusBtnExpo", sb.toString(), null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exposureFollowTips() {
        JDMtaUtils.sendExposureData(getContext(), TAG, null, this.mThemeId, "Focus_GuideFloatLayerExpo", null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void followGuidBtnEvent(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(z2 ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append((this.bShowFollowTips && z) ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMtaActivityId());
        JDMtaUtils.onClickWithPageId(getContext(), "FocusFloatBar_FocusBtn", TAG, sb.toString(), this.mThemeId, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int get750(int i2) {
        if (getContext() != null) {
            Activity activity = null;
            if (getContext() instanceof ThemedReactContext) {
                activity = ((ThemedReactContext) getContext()).getCurrentActivity();
            } else if (getContext() instanceof IMyActivity) {
                activity = (Activity) getContext();
            }
            if (activity != null) {
                return DPIUtil.getWidthByDesignValue750(activity, i2);
            }
        }
        return DPIUtil.getWidthByDesignValue750(getContext(), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getActivityType(String str) {
        JingBeanResultEntity jingBeanResultEntity;
        CouponEntity couponEntity;
        if (TextUtils.isEmpty(str) || this.isUserFollowEntity == null) {
            return "0";
        }
        if (!"1".equals(str) || (couponEntity = this.isUserFollowEntity.coupon) == null || !this.firstLogined) {
            return (!"2".equals(str) || (jingBeanResultEntity = this.isUserFollowEntity.jingBeanResult) == null || jingBeanResultEntity.amount <= 0 || TextUtils.isEmpty(jingBeanResultEntity.msg) || TextUtils.isEmpty(jingBeanResultEntity.jingBeanIcon) || TextUtils.isEmpty(jingBeanResultEntity.jingBeanUrl)) ? "0" : "2";
        }
        this.roleId = couponEntity.roleId;
        this.batchId = couponEntity.batchId;
        return "1";
    }

    private String getFinger() {
        String str = "";
        try {
            LogoManager.getInstance(JdSdk.getInstance().getApplicationContext()).init();
            str = LogoManager.getInstance(JdSdk.getInstance().getApplicationContext()).getLogo();
            return TextUtils.isEmpty(str) ? "-1" : str;
        } catch (Exception e2) {
            Log.e(TAG, "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-EID\u5931\u8d25\uff1a" + e2.getMessage());
            e2.printStackTrace();
            return str;
        }
    }

    private String getMtaActivityId() {
        IsUserFollowEntity isUserFollowEntity;
        JingBeanResultEntity jingBeanResultEntity;
        String valueOf;
        IsUserFollowEntity isUserFollowEntity2;
        CouponEntity couponEntity;
        if ("1".equals(this.mActivityType) && (isUserFollowEntity2 = this.isUserFollowEntity) != null && (couponEntity = isUserFollowEntity2.coupon) != null) {
            if (TextUtils.isEmpty(couponEntity.roleId)) {
                return "";
            }
            valueOf = this.isUserFollowEntity.coupon.roleId;
        } else if (!"2".equals(this.mActivityType) || (isUserFollowEntity = this.isUserFollowEntity) == null || (jingBeanResultEntity = isUserFollowEntity.jingBeanResult) == null || TextUtils.isEmpty(String.valueOf(jingBeanResultEntity.jingBeanId))) {
            return "";
        } else {
            valueOf = String.valueOf(this.isUserFollowEntity.jingBeanResult.jingBeanId);
        }
        return valueOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getScreenHeight() {
        if (getContext() != null) {
            if (getContext() instanceof ThemedReactContext) {
                return DPIUtil.getAppHeight(((ThemedReactContext) getContext()).getCurrentActivity());
            }
            if (getContext() instanceof IMyActivity) {
                return DPIUtil.getAppHeight((Activity) getContext());
            }
            return 0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getScreenWidth() {
        if (getContext() != null) {
            if (getContext() instanceof ThemedReactContext) {
                return DPIUtil.getAppWidth(((ThemedReactContext) getContext()).getCurrentActivity());
            }
            if (getContext() instanceof IMyActivity) {
                return DPIUtil.getAppWidth((Activity) getContext());
            }
            return 0;
        }
        return 0;
    }

    private boolean getShowedTipsFromSP(String str, String str2) {
        Set<String> stringSet = CommonBase.getJdSharedPreferences().getStringSet(str, null);
        return stringSet != null && stringSet.contains(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSafe() {
        if (getContext() != null) {
            Activity activity = null;
            if (getContext() instanceof ThemedReactContext) {
                activity = ((ThemedReactContext) getContext()).getCurrentActivity();
            } else if (getContext() instanceof Activity) {
                activity = (Activity) getContext();
            }
            return (activity == null || activity.isFinishing()) ? false : true;
        }
        return false;
    }

    private boolean isShowGuide() {
        if (getContext() != null) {
            if (getContext() instanceof ThemedReactContext) {
                Activity currentActivity = ((ThemedReactContext) getContext()).getCurrentActivity();
                double div = div(DPIUtil.getAppWidth(currentActivity), DPIUtil.getAppHeight(currentActivity), 2);
                Log.d(TAG, "rn scale: " + div);
                return div <= 0.77d;
            } else if (getContext() instanceof IMyActivity) {
                Activity activity = (Activity) getContext();
                double div2 = div(DPIUtil.getAppWidth(activity), DPIUtil.getAppHeight(activity), 2);
                Log.d(TAG, "native scale: " + div2);
                return div2 <= 0.77d;
            } else {
                return true;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLayoutChanged() {
        Log.d(TAG, "onLayoutChanged isUpdateLayout:" + this.isUpdateLayout);
        closeAllPopWindow();
        IsUserFollowEntity isUserFollowEntity = this.isUserFollowEntity;
        String activityType = isUserFollowEntity != null ? getActivityType(isUserFollowEntity.activityType) : "0";
        if (this.isUpdateLayout) {
            return;
        }
        if (this.container != null && this.containerLp != null) {
            if (!"1".equals(activityType) && !"2".equals(activityType)) {
                this.containerLp.width = get750(118);
                this.containerLp.height = get750(44);
            } else {
                this.containerLp.width = get750(127);
                this.containerLp.height = get750(53);
            }
            this.container.setLayoutParams(this.containerLp);
        }
        ImageView imageView = this.mIvFollow;
        if (imageView != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            this.ivFollowLp = layoutParams;
            layoutParams.width = get750(118);
            this.ivFollowLp.height = get750(44);
            this.mIvFollow.setLayoutParams(this.ivFollowLp);
        }
        SimpleDraweeView simpleDraweeView = this.mIvGift;
        if (simpleDraweeView != null) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) simpleDraweeView.getLayoutParams();
            this.ivGiftLp = layoutParams2;
            layoutParams2.width = get750(28);
            this.ivGiftLp.height = get750(28);
            this.mIvGift.setLayoutParams(this.ivGiftLp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshGuidFollowView() {
        Log.d(TAG, "refreshGuidFollowView");
        ViewGroup viewGroup = this.mContainer;
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeAllViews();
        RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, get750(80));
        layoutParams.leftMargin = get750(18);
        layoutParams.rightMargin = get750(18);
        layoutParams.bottomMargin = get750(this.mBottom * 2);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundResource(R.drawable.shape_channel_guide_background);
        TextView textView = new TextView(this.mContext);
        textView.setGravity(17);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextSize(0, get750(26));
        textView.setTextColor(-1);
        textView.setSingleLine(true);
        textView.setTypeface(Typeface.defaultFromStyle(0));
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("followTipContent", "\u60f3\u770b\u66f4\u591a\u9891\u9053\u52a8\u6001\uff0c\u8bf7\u5173\u6ce8\u6211\u5427\uff5e");
        if (!TextUtils.isEmpty(switchStringValue)) {
            textView.setText(switchStringValue);
        } else {
            textView.setText("\u60f3\u770b\u66f4\u591a\u9891\u9053\u52a8\u6001\uff0c\u8bf7\u5173\u6ce8\u6211\u5427\uff5e");
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.leftMargin = get750(28);
        layoutParams2.rightMargin = get750(210);
        relativeLayout.addView(textView, layoutParams2);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setBackgroundResource(R.drawable.follow_hint_close);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(get750(20), get750(20));
        layoutParams3.rightMargin = get750(28);
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        relativeLayout.addView(imageView, layoutParams3);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomChannelFollowView.this.mIsShowing.set(false);
                CustomChannelFollowView.this.mContainer.removeAllViews();
                CustomChannelFollowView.safeUnRegisterEventBus(this);
                CustomChannelFollowView.this.clickFollowTipClose();
            }
        });
        imageView.setId(R.id.hint_close);
        resetFollowViewWidthAndHeight(get750(112), get750(44));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.rightMargin = get750(30);
        layoutParams4.addRule(0, imageView.getId());
        layoutParams4.addRule(15);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        Log.d(TAG, "mIsShowing" + this.mIsShowing.get());
        relativeLayout.addView(this, layoutParams4);
        this.mContainer.addView(relativeLayout);
        Log.d(TAG, "mContainer" + this.mContainer.getChildCount());
        Runnable runnable = new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.20
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomChannelFollowView.this.mIsShowing.set(false);
                    CustomChannelFollowView.this.mContainer.removeAllViews();
                    CustomChannelFollowView.safeUnRegisterEventBus(this);
                    Log.d(CustomChannelFollowView.TAG, "postDelayed");
                } catch (Exception unused) {
                }
            }
        };
        int i2 = this.mTime;
        postDelayed(runnable, i2 == 0 ? Final.FIVE_SECOND : i2);
        exposeGuidFollowTips();
    }

    public static void safeRegisterEventBus(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                return;
            }
            EventBus.getDefault().register(obj);
        } catch (Exception unused) {
        }
    }

    public static void safeUnRegisterEventBus(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                EventBus.getDefault().unregister(obj);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendClickTipsMta() {
        Context context = getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(getMtaActivityId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(this.mActivityType);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(TextUtils.isEmpty(this.batchId) ? "" : this.batchId);
        JDMtaUtils.onClickWithPageId(context, "Focus_FocusFloat", TAG, sb.toString(), this.mThemeId, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendExpTipsMta(String str) {
        Context context = getContext();
        String str2 = this.mThemeId;
        StringBuilder sb = new StringBuilder();
        sb.append(getMtaActivityId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(this.mActivityType);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(TextUtils.isEmpty(this.batchId) ? "" : this.batchId);
        JDMtaUtils.sendExposureData(context, TAG, null, str2, str, sb.toString(), null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowTipsValueToSP(String str, String str2) {
        HashSet hashSet;
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        Set<String> stringSet = jdSharedPreferences.getStringSet(str, null);
        if (stringSet == null) {
            hashSet = new HashSet();
        } else {
            hashSet = new HashSet(stringSet);
        }
        hashSet.add(str2);
        SharedPreferences.Editor edit = jdSharedPreferences.edit();
        if (edit != null) {
            edit.putStringSet(str, hashSet);
            edit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowBeansSuccessWindow(JingBeanResultEntity jingBeanResultEntity, String str) {
        if (jingBeanResultEntity == null) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(get750(300), get750(300));
        linearLayout.setBackgroundResource(R.drawable.shape_gift_success_background);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setId(R.id.channel_gift_coupon_bg);
        linearLayout.addView(linearLayout2);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setImageResource(R.drawable.channel_follow_bean_icon);
        simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(get750(50), get750(40)));
        simpleDraweeView.setId(R.id.channel_gift_label);
        linearLayout2.addView(simpleDraweeView);
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor("#ffeb29"));
        textView.setTextSize(0, get750(26));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        SpannableString spannableString = new SpannableString(" +" + jingBeanResultEntity.amount + "\u4eac\u8c46");
        spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, String.valueOf(jingBeanResultEntity.amount).length() + 2, 17);
        textView.setText(spannableString);
        textView.setIncludeFontPadding(false);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = get750(5);
        textView.setLayoutParams(layoutParams3);
        textView.setId(R.id.channel_gift_price);
        FontsUtil.changeTextFont(textView);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setGravity(17);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setTextSize(0, get750(30));
        textView2.setTextColor(-1);
        textView2.setText(str);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.topMargin = get750(20);
        layoutParams4.gravity = 1;
        linearLayout.addView(textView2, layoutParams4);
        TextView textView3 = new TextView(getContext());
        textView3.setGravity(17);
        textView3.setEllipsize(TextUtils.TruncateAt.END);
        textView3.setTextSize(0, get750(26));
        textView3.setTextColor(-1);
        textView3.setText("\u5728\"\u6211\u7684\u4eac\u4e1c\"\u53ef\u67e5\u770b");
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.topMargin = get750(25);
        layoutParams5.gravity = 1;
        linearLayout.addView(textView3, layoutParams5);
        PopupWindow popupWindow = new PopupWindow(linearLayout, get750(300), get750(300));
        this.mFollowGiftSuccessWindow = popupWindow;
        popupWindow.setClippingEnabled(false);
        this.mFollowGiftSuccessWindow.setSoftInputMode(16);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.bAttachToWindow) {
                        CustomChannelFollowView.this.mFollowGiftSuccessWindow.showAtLocation((View) CustomChannelFollowView.this.getParent(), 17, 0, 0);
                        CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatSuccessExpo");
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5f39\u51fa\u4eac\u8c46window\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftSuccessWindow != null && CustomChannelFollowView.this.mFollowGiftSuccessWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftSuccessWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u4eac\u8c46window\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 3000L);
    }

    private void showFollowBeansWindow() {
        JingBeanResultEntity jingBeanResultEntity;
        IsUserFollowEntity isUserFollowEntity = this.isUserFollowEntity;
        if (isUserFollowEntity == null || (jingBeanResultEntity = isUserFollowEntity.jingBeanResult) == null) {
            return;
        }
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(get750(300), get750(150)));
        relativeLayout.setBackgroundResource(R.drawable.channel_follow_gift_tips_bg_right);
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView.setTextSize(0, get750(26));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setGravity(17);
        textView.setText("\u5173\u6ce8\u9886\u53d6\u4e13\u5c5e\u4f18\u60e0");
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = get750(30);
        layoutParams.addRule(14);
        textView.setLayoutParams(layoutParams);
        textView.setId(R.id.channel_bean_title);
        relativeLayout.addView(textView);
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, textView.getId());
        layoutParams2.addRule(14);
        layoutParams2.setMargins(0, get750(15), 0, 0);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams2);
        linearLayout.setId(R.id.channel_bean_bg);
        relativeLayout.addView(linearLayout);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setImageResource(R.drawable.channel_follow_bean_icon);
        simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(get750(50), get750(40)));
        simpleDraweeView.setId(R.id.channel_bean_icon);
        linearLayout.addView(simpleDraweeView);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(Color.parseColor("#ffeb29"));
        textView2.setTextSize(0, get750(30));
        textView2.setTypeface(Typeface.defaultFromStyle(1));
        SpannableString spannableString = new SpannableString(" +" + jingBeanResultEntity.amount + "\u4eac\u8c46");
        spannableString.setSpan(new RelativeSizeSpan(1.2f), 0, String.valueOf(jingBeanResultEntity.amount).length() + 2, 17);
        textView2.setText(spannableString);
        textView2.setIncludeFontPadding(false);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setGravity(17);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = get750(5);
        textView2.setLayoutParams(layoutParams3);
        textView2.setId(R.id.channel_bean_count_tv);
        FontsUtil.changeTextFont(textView2);
        linearLayout.addView(textView2);
        this.mFollowGiftPopupWindow = new PopupWindow(relativeLayout, get750(300), get750(150));
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.bAttachToWindow) {
                        CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatExpo");
                        PopupWindow popupWindow = CustomChannelFollowView.this.mFollowGiftPopupWindow;
                        CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                        popupWindow.showAsDropDown(customChannelFollowView, -customChannelFollowView.get750(140), CustomChannelFollowView.this.get750(10));
                        CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                        customChannelFollowView2.setShowTipsValueToSP(CustomChannelFollowView.SP_FOLLOW_BEAN, String.valueOf(customChannelFollowView2.isUserFollowEntity.jingBeanResult.jingBeanId));
                        CustomChannelFollowView.this.bShowFollowTips = true;
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5f39\u51fa\u4eac\u8c46Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftPopupWindow != null && CustomChannelFollowView.this.mFollowGiftPopupWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftPopupWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u4eac\u8c46Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 3000L);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    CustomChannelFollowView.this.sendClickTipsMta();
                    if (LoginUserBase.hasLogin()) {
                        CustomChannelFollowView.this.startFollow(true, CustomChannelFollowView.FOLLOW_TYPE_FLOAT);
                    }
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftPopupWindow != null && CustomChannelFollowView.this.mFollowGiftPopupWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftPopupWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u70b9\u51fb\u4eac\u8c46Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowCouponSuccessWindow(CouponEntity couponEntity) {
        if (couponEntity == null) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(get750(R2.attr.behavior_saveFlags), get750(307));
        layoutParams.gravity = 1;
        linearLayout.setBackgroundResource(R.drawable.shape_gift_success_background);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setBackgroundResource(R.drawable.channel_follow_coupon_bg);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(get750(R2.attr.arrowHeight), get750(116));
        layoutParams2.topMargin = get750(40);
        layoutParams2.leftMargin = get750(30);
        layoutParams2.rightMargin = get750(30);
        relativeLayout.setLayoutParams(layoutParams2);
        linearLayout.addView(relativeLayout);
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor("#ffeb29"));
        textView.setTextSize(0, get750(40));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        SpannableString spannableString = new SpannableString("\u00a5" + couponEntity.discount);
        spannableString.setSpan(new RelativeSizeSpan(0.6f), 0, 1, 17);
        textView.setText(spannableString);
        textView.setIncludeFontPadding(false);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(get750(150), -2);
        layoutParams3.topMargin = get750(20);
        layoutParams3.leftMargin = get750(8);
        textView.setLayoutParams(layoutParams3);
        FontsUtil.changeTextFont(textView);
        relativeLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView2.setTextSize(0, get750(20));
        textView2.setIncludeFontPadding(false);
        textView2.setGravity(17);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(get750(150), -2);
        layoutParams4.topMargin = get750(66);
        layoutParams4.leftMargin = get750(8);
        textView2.setLayoutParams(layoutParams4);
        textView2.setText(couponEntity.quota);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setId(R.id.channel_bean_success_tv);
        relativeLayout.addView(textView2);
        TextView textView3 = new TextView(getContext());
        textView3.setTextColor(-1);
        textView3.setTextSize(0, get750(22));
        textView3.setMaxWidth(get750(162));
        textView3.setMaxLines(2);
        textView3.setGravity(17);
        textView3.setEllipsize(TextUtils.TruncateAt.END);
        textView3.setText(couponEntity.limitStr);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(get750(162), -2);
        layoutParams5.addRule(11);
        layoutParams5.addRule(15);
        layoutParams5.rightMargin = get750(25);
        relativeLayout.addView(textView3, layoutParams5);
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.channel_follow_coupon_label);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(get750(50), get750(50));
        layoutParams6.addRule(11);
        layoutParams6.addRule(10);
        relativeLayout.addView(imageView, layoutParams6);
        TextView textView4 = new TextView(getContext());
        textView4.setGravity(17);
        textView4.setEllipsize(TextUtils.TruncateAt.END);
        textView4.setTextSize(0, get750(30));
        textView4.setTextColor(-1);
        textView4.setText("\u4f18\u60e0\u5238\u5df2\u5165\u8d26");
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams7.topMargin = get750(30);
        layoutParams7.gravity = 1;
        linearLayout.addView(textView4, layoutParams7);
        TextView textView5 = new TextView(getContext());
        textView5.setGravity(17);
        textView5.setEllipsize(TextUtils.TruncateAt.END);
        textView5.setTextSize(0, get750(26));
        textView5.setTextColor(-1);
        textView5.setText("\u5728\"\u6211\u7684\u4eac\u4e1c\"\u53ef\u67e5\u770b");
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams8.topMargin = get750(15);
        layoutParams8.gravity = 1;
        linearLayout.addView(textView5, layoutParams8);
        PopupWindow popupWindow = new PopupWindow(linearLayout, get750(R2.attr.behavior_saveFlags), get750(307));
        this.mFollowGiftSuccessWindow = popupWindow;
        popupWindow.setClippingEnabled(false);
        this.mFollowGiftSuccessWindow.setSoftInputMode(16);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.bAttachToWindow) {
                        CustomChannelFollowView.this.mFollowGiftSuccessWindow.showAtLocation((View) CustomChannelFollowView.this.getParent(), 17, 0, 0);
                        CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatSuccessExpo");
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5f39\u51fa\u4f18\u60e0\u5238window\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftSuccessWindow != null && CustomChannelFollowView.this.mFollowGiftSuccessWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftSuccessWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u4f18\u60e0\u5238window\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 3000L);
    }

    private void showFollowCouponWindow() {
        IsUserFollowEntity isUserFollowEntity = this.isUserFollowEntity;
        if (isUserFollowEntity == null || isUserFollowEntity.coupon == null) {
            return;
        }
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(get750(R2.attr.behavior_saveFlags), -2));
        relativeLayout.setBackgroundResource(R.drawable.channel_follow_gift_tips_bg_right);
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView.setTextSize(0, get750(26));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setGravity(17);
        textView.setText(this.isUserFollowEntity.coupon.msg);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(get750(R2.attr.arrowHeight), -2);
        layoutParams.topMargin = get750(40);
        layoutParams.addRule(14);
        textView.setLayoutParams(layoutParams);
        textView.setId(R.id.channel_gift_title);
        relativeLayout.addView(textView);
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(get750(R2.attr.arrowHeight), get750(116));
        layoutParams2.addRule(3, textView.getId());
        layoutParams2.setMargins(get750(30), get750(30), get750(30), get750(30));
        relativeLayout2.setLayoutParams(layoutParams2);
        relativeLayout2.setBackgroundResource(R.drawable.channel_follow_coupon_bg);
        relativeLayout2.setId(R.id.channel_gift_coupon_bg);
        relativeLayout.addView(relativeLayout2);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(Color.parseColor("#ffeb29"));
        textView2.setTextSize(0, get750(40));
        textView2.setTypeface(Typeface.defaultFromStyle(1));
        SpannableString spannableString = new SpannableString("\u00a5" + this.isUserFollowEntity.coupon.discount);
        spannableString.setSpan(new RelativeSizeSpan(0.6f), 0, 1, 17);
        textView2.setText(spannableString);
        textView2.setIncludeFontPadding(false);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setGravity(17);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(get750(160), -2);
        layoutParams3.topMargin = get750(15);
        layoutParams3.leftMargin = get750(5);
        textView2.setLayoutParams(layoutParams3);
        textView2.setId(R.id.channel_gift_price);
        FontsUtil.changeTextFont(textView2);
        relativeLayout2.addView(textView2);
        TextView textView3 = new TextView(getContext());
        textView3.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView3.setTextSize(0, get750(22));
        textView3.setIncludeFontPadding(false);
        textView3.setGravity(17);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(get750(160), -2);
        layoutParams4.leftMargin = get750(5);
        layoutParams4.addRule(5);
        layoutParams4.addRule(3, textView2.getId());
        textView3.setLayoutParams(layoutParams4);
        textView3.setText(this.isUserFollowEntity.coupon.quota);
        textView3.setSingleLine(true);
        textView3.setEllipsize(TextUtils.TruncateAt.END);
        textView3.setId(R.id.channel_gift_des);
        relativeLayout2.addView(textView3);
        TextView textView4 = new TextView(getContext());
        textView4.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView4.setTextSize(0, get750(22));
        textView4.setGravity(19);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(get750(R2.anim.slide_in_from_top), get750(116));
        layoutParams5.addRule(11);
        layoutParams5.rightMargin = get750(10);
        textView4.setLayoutParams(layoutParams5);
        textView4.setText(this.isUserFollowEntity.coupon.limitStr);
        textView4.setLineSpacing(get750(6), 1.0f);
        textView4.setMaxLines(2);
        textView4.setEllipsize(TextUtils.TruncateAt.END);
        textView4.setId(R.id.channel_gift_shop);
        relativeLayout2.addView(textView4);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setImageResource(R.drawable.channel_follow_coupon_label);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(get750(50), get750(50));
        layoutParams6.addRule(11);
        simpleDraweeView.setLayoutParams(layoutParams6);
        simpleDraweeView.setId(R.id.channel_gift_label);
        relativeLayout2.addView(simpleDraweeView);
        this.mFollowGiftPopupWindow = new PopupWindow(relativeLayout, get750(R2.attr.behavior_saveFlags), get750(241));
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.bAttachToWindow) {
                        PopupWindow popupWindow = CustomChannelFollowView.this.mFollowGiftPopupWindow;
                        CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                        popupWindow.showAsDropDown(customChannelFollowView, -customChannelFollowView.get750(240), CustomChannelFollowView.this.get750(10));
                        CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                        customChannelFollowView2.setShowTipsValueToSP(CustomChannelFollowView.SP_FOLLOW_COUPON, customChannelFollowView2.isUserFollowEntity.coupon.encryptedKey);
                        CustomChannelFollowView.this.sendExpTipsMta("Focus_FocusFloatExpo");
                        CustomChannelFollowView.this.bShowFollowTips = true;
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5f39\u51fa\u4f18\u60e0\u5238Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftPopupWindow != null && CustomChannelFollowView.this.mFollowGiftPopupWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftPopupWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u4f18\u60e0\u5238Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 3000L);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    CustomChannelFollowView.this.sendClickTipsMta();
                    if (LoginUserBase.hasLogin()) {
                        CustomChannelFollowView.this.startFollow(true, CustomChannelFollowView.FOLLOW_TYPE_FLOAT);
                    }
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowGiftPopupWindow != null && CustomChannelFollowView.this.mFollowGiftPopupWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowGiftPopupWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u70b9\u51fb\u4f18\u60e0\u5238Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowGitIcon(String str) {
        JingBeanResultEntity jingBeanResultEntity;
        CouponEntity couponEntity;
        Log.d(TAG, "showFollowGitIcon");
        if (this.isUserFollowEntity == null) {
            return;
        }
        this.mIvFollow.setImageResource(this.bShowBlack ? R.drawable.follow_black : R.drawable.follow_white);
        this.mIvGift.setVisibility(0);
        if ("1".equals(str) && (couponEntity = this.isUserFollowEntity.coupon) != null && !TextUtils.isEmpty(couponEntity.couponIcon)) {
            JDImageUtils.displayImage(this.isUserFollowEntity.coupon.couponIcon, (ImageView) this.mIvGift, (JDDisplayImageOptions) null, false);
        } else if (!"2".equals(str) || (jingBeanResultEntity = this.isUserFollowEntity.jingBeanResult) == null || TextUtils.isEmpty(jingBeanResultEntity.jingBeanIcon)) {
        } else {
            JDImageUtils.displayImage(this.isUserFollowEntity.jingBeanResult.jingBeanIcon, (ImageView) this.mIvGift, (JDDisplayImageOptions) null, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowIcon() {
        Log.d(TAG, "showFollowIcon");
        this.mIvFollow.setImageResource(this.bShowBlack ? R.drawable.follow_black : R.drawable.follow_white);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowTips(String str) {
        JingBeanResultEntity jingBeanResultEntity;
        CouponEntity couponEntity;
        if (canShowTips() && this.isUserFollowEntity != null) {
            if (this.isShowWindow) {
                if ("1".equals(str) && (couponEntity = this.isUserFollowEntity.coupon) != null) {
                    if (getShowedTipsFromSP(SP_FOLLOW_COUPON, couponEntity.encryptedKey)) {
                        return;
                    }
                    showFollowCouponWindow();
                    return;
                } else if ("2".equals(str) && (jingBeanResultEntity = this.isUserFollowEntity.jingBeanResult) != null) {
                    if (getShowedTipsFromSP(SP_FOLLOW_BEAN, String.valueOf(jingBeanResultEntity.jingBeanId))) {
                        return;
                    }
                    showFollowBeansWindow();
                    return;
                } else if (getShowedTipsFromSP(SP_FOLLOW_TIPS, this.mThemeId) || TextUtils.isEmpty(this.isUserFollowEntity.themeName) || TextUtils.isEmpty(this.isUserFollowEntity.themeText)) {
                    return;
                } else {
                    Set<String> stringSet = CommonBase.getJdSharedPreferences().getStringSet(SP_FOLLOW_TIPS, null);
                    IsUserFollowEntity isUserFollowEntity = this.isUserFollowEntity;
                    if (isUserFollowEntity == null || isUserFollowEntity.maxToast <= 0) {
                        return;
                    }
                    if (stringSet == null || stringSet.size() < this.isUserFollowEntity.maxToast) {
                        showFollowTipsWindow();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        Log.d(TAG, "canShowTips is false");
    }

    private void showFollowTipsWindow() {
        if (this.isUserFollowEntity == null) {
            return;
        }
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(get750(300), get750(310)));
        relativeLayout.setBackgroundResource(this.bShowNormalPopTips ? R.drawable.channel_follow_tips_bg : R.drawable.channel_follow_tips_bg_right);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(get750(60), get750(60));
        simpleDraweeView.setLayoutParams(layoutParams);
        layoutParams.topMargin = get750(60);
        JDImageUtils.displayImage(this.isUserFollowEntity.themeIcon, simpleDraweeView);
        layoutParams.addRule(14);
        simpleDraweeView.setId(R.id.channel_tips_icon);
        relativeLayout.addView(simpleDraweeView);
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView.setTextSize(0, get750(24));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setText(this.isUserFollowEntity.themeName);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.topMargin = get750(20);
        layoutParams2.addRule(14);
        layoutParams2.addRule(3, simpleDraweeView.getId());
        textView.setLayoutParams(layoutParams2);
        textView.setId(R.id.channel_tips_title);
        relativeLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(Color.parseColor("#aaaaaa"));
        textView2.setTextSize(0, get750(26));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(get750(230), -2);
        layoutParams3.topMargin = get750(24);
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, textView.getId());
        textView2.setLayoutParams(layoutParams3);
        textView2.setGravity(1);
        textView2.setText(this.isUserFollowEntity.themeText);
        textView2.setMaxLines(2);
        relativeLayout.addView(textView2);
        this.mFollowTipsPopupWindow = new PopupWindow(relativeLayout, get750(300), get750(310));
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.bAttachToWindow) {
                        PopupWindow popupWindow = CustomChannelFollowView.this.mFollowTipsPopupWindow;
                        CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                        popupWindow.showAsDropDown(customChannelFollowView, -(customChannelFollowView.bShowNormalPopTips ? CustomChannelFollowView.this.get750(91) : CustomChannelFollowView.this.get750(162)), CustomChannelFollowView.this.get750(10));
                        CustomChannelFollowView.this.bShowFollowTips = true;
                        CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                        customChannelFollowView2.setShowTipsValueToSP(CustomChannelFollowView.SP_FOLLOW_TIPS, customChannelFollowView2.mThemeId);
                        CustomChannelFollowView.this.exposureFollowTips();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5f39\u51fa\u5173\u6ce8Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe() && CustomChannelFollowView.this.mFollowTipsPopupWindow != null && CustomChannelFollowView.this.mFollowTipsPopupWindow.isShowing()) {
                        CustomChannelFollowView.this.mFollowTipsPopupWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u5173\u6ce8Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowToast() {
        SpannableString spannableString = new SpannableString("\u5173\u6ce8\u6210\u529f");
        spannableString.setSpan(new AbsoluteSizeSpan(15, true), 0, 4, 33);
        spannableString.setSpan(new StyleSpan(1), 0, 4, 33);
        JDToast jDToast = new JDToast(getContext(), (byte) 1);
        jDToast.setImage((byte) 2);
        jDToast.setText(spannableString);
        jDToast.setDuration(0);
        jDToast.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnFollowIcon() {
        Log.d(TAG, "showUnFollowIcon");
        this.mIvFollow.setImageResource(this.bShowBlack ? R.drawable.unfollow_black : R.drawable.unfollow_white);
        SimpleDraweeView simpleDraweeView = this.mIvGift;
        if (simpleDraweeView == null || simpleDraweeView.getVisibility() != 0) {
            return;
        }
        this.mIvGift.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFollow(boolean z, String str) {
        IsUserFollowEntity isUserFollowEntity;
        CouponEntity couponEntity;
        this.bIsFetching = true;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("userFollow");
        httpSetting.putJsonParam("type", z ? "1" : "0");
        httpSetting.putJsonParam("businessId", "1");
        httpSetting.putJsonParam("themeId", this.mThemeId);
        if (this.firstLogined && "1".equals(this.mActivityType) && (isUserFollowEntity = this.isUserFollowEntity) != null && (couponEntity = isUserFollowEntity.coupon) != null) {
            httpSetting.putJsonParam("encryptedKey", couponEntity.encryptedKey);
            httpSetting.putJsonParam("roleId", this.isUserFollowEntity.coupon.roleId);
        }
        if ("1".equals(this.mActivityType)) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("eid", (Object) getFinger());
            jDJSONObject.put("referUrl", (Object) (-1));
            jDJSONObject.put("shshshfp", (Object) (-1));
            jDJSONObject.put("openId", (Object) (-1));
            jDJSONObject.put("isRvc", (Object) 0);
            jDJSONObject.put(WebPerfManager.FP, (Object) 0);
            jDJSONObject.put("shshshfpa", (Object) (-1));
            jDJSONObject.put("userAgent", (Object) (-1));
            httpSetting.putJsonParam("informationParam", jDJSONObject);
        }
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new AnonymousClass5(z));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle startXView2(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moduleName", JumpUtil.XVIEW2_NXVIEW);
            jSONObject.put("methodName", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(XView2Constants.LOGIC_KEY, str2);
            if (!TextUtils.isEmpty(str3)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("text1", str3);
                jSONObject2.put(XView2Constants.STATE, jSONObject3);
            }
            jSONObject.put("params", jSONObject2);
            bundle.putString("params", jSONObject.toString());
            JumpUtil.execJumpByDes(JumpUtil.XVIEW2_NXVIEW, context, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bundle;
    }

    private void writeToSp(SharedPreferences sharedPreferences, Set<String> set, String str) {
        HashSet hashSet;
        JingBeanResultEntity jingBeanResultEntity;
        CouponEntity couponEntity;
        if (set == null) {
            hashSet = new HashSet();
        } else {
            hashSet = new HashSet(set);
        }
        if (this.isUserFollowEntity == null) {
            return;
        }
        if (SP_FOLLOW_TIPS.equals(str)) {
            hashSet.add(this.mThemeId);
        } else if (SP_FOLLOW_COUPON.equals(str) && (couponEntity = this.isUserFollowEntity.coupon) != null) {
            hashSet.add(couponEntity.encryptedKey);
        } else if (!SP_FOLLOW_BEAN.equals(str) || (jingBeanResultEntity = this.isUserFollowEntity.jingBeanResult) == null) {
            return;
        } else {
            hashSet.add(String.valueOf(jingBeanResultEntity.jingBeanId));
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (edit != null) {
            edit.putStringSet(str, hashSet);
            edit.commit();
        }
    }

    public void changeIcon(boolean z) {
        this.bShowBlack = z;
        if (this.bShowFollow) {
            showFollowIcon();
        } else {
            showUnFollowIcon();
        }
    }

    public void closeAllPopWindow() {
        try {
            PopupWindow popupWindow = this.mFollowTipsPopupWindow;
            if (popupWindow != null && popupWindow.isShowing()) {
                this.mFollowTipsPopupWindow.dismiss();
            }
            PopupWindow popupWindow2 = this.mFollowGiftPopupWindow;
            if (popupWindow2 != null && popupWindow2.isShowing()) {
                this.mFollowGiftPopupWindow.dismiss();
            }
            PopupWindow popupWindow3 = this.mFollowGiftSuccessWindow;
            if (popupWindow3 != null && popupWindow3.isShowing()) {
                this.mFollowGiftSuccessWindow.dismiss();
            }
            ChannelFollowSuccessPopupWindow channelFollowSuccessPopupWindow = this.successPopupWindow;
            if (channelFollowSuccessPopupWindow == null || channelFollowSuccessPopupWindow.getPopupWindow() == null || !this.successPopupWindow.getPopupWindow().isShowing()) {
                return;
            }
            this.successPopupWindow.getPopupWindow().dismiss();
        } catch (Exception e2) {
            Log.e(TAG, "\u5173\u95ed\u4eac\u8c46\u5f39\u7a97\u5931\u8d25\uff1a" + e2.getMessage());
        }
    }

    public void hideFollowWindow() {
        this.isShowWindow = false;
        postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (CustomChannelFollowView.this.isSafe()) {
                        if (CustomChannelFollowView.this.mFollowTipsPopupWindow != null && CustomChannelFollowView.this.mFollowTipsPopupWindow.isShowing()) {
                            CustomChannelFollowView.this.mFollowTipsPopupWindow.dismiss();
                        }
                        if (CustomChannelFollowView.this.mFollowGiftPopupWindow != null && CustomChannelFollowView.this.mFollowGiftPopupWindow.isShowing()) {
                            CustomChannelFollowView.this.mFollowGiftPopupWindow.dismiss();
                        }
                        if (CustomChannelFollowView.this.mFollowGiftSuccessWindow == null || !CustomChannelFollowView.this.mFollowGiftSuccessWindow.isShowing()) {
                            return;
                        }
                        CustomChannelFollowView.this.mFollowGiftSuccessWindow.dismiss();
                    }
                } catch (Exception e2) {
                    Log.e(CustomChannelFollowView.TAG, "\u5173\u95ed\u5173\u6ce8Tips\u5931\u8d25\uff1a" + e2.getMessage());
                }
            }
        }, 100L);
    }

    public void init() {
        Log.d(TAG, "init ");
        removeAllViews();
        closeAllPopWindow();
        this.container = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.containerLp = layoutParams;
        layoutParams.width = get750(127);
        this.containerLp.height = get750(53);
        this.container.setLayoutParams(this.containerLp);
        this.mIvFollow = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(get750(118), get750(44));
        this.ivFollowLp = layoutParams2;
        layoutParams2.addRule(12);
        this.ivFollowLp.addRule(9);
        this.mIvFollow.setLayoutParams(this.ivFollowLp);
        this.mIvFollow.setVisibility(4);
        this.mIvFollow.setScaleType(ImageView.ScaleType.FIT_XY);
        this.container.addView(this.mIvFollow);
        this.mIvGift = new SimpleDraweeView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(get750(28), get750(28));
        this.ivGiftLp = layoutParams3;
        layoutParams3.addRule(11);
        this.mIvGift.setLayoutParams(this.ivGiftLp);
        this.mIvGift.setVisibility(4);
        this.mIvGift.setScaleType(ImageView.ScaleType.FIT_XY);
        this.container.addView(this.mIvGift);
        addView(this.container);
        this.firstLogined = LoginUserBase.hasLogin();
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setClassName(Button.class.getName());
            }
        });
        this.screenWH = getScreenWidth() + "-" + getScreenHeight();
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.2
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (CustomChannelFollowView.this.screenWH.equals(CustomChannelFollowView.this.getScreenWidth() + "-" + CustomChannelFollowView.this.getScreenHeight())) {
                    return;
                }
                CustomChannelFollowView.this.onLayoutChanged();
                CustomChannelFollowView.this.screenWH = CustomChannelFollowView.this.getScreenWidth() + "-" + CustomChannelFollowView.this.getScreenHeight();
                StringBuilder sb = new StringBuilder();
                sb.append("screenWH: ");
                sb.append(CustomChannelFollowView.this.screenWH);
                Log.d(CustomChannelFollowView.TAG, sb.toString());
            }
        };
        this.layoutChangeListener = onLayoutChangeListener;
        addOnLayoutChangeListener(onLayoutChangeListener);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.d(CustomChannelFollowView.TAG, "CustomChannelFollowView onclick");
                if (CustomChannelFollowView.this.mIvFollow.getVisibility() != 0) {
                    return;
                }
                CustomChannelFollowView.this.closeAllPopWindow();
                if (CustomChannelFollowView.this.bIsFetching) {
                    return;
                }
                if (!LoginUserBase.hasLogin()) {
                    if (CustomChannelFollowView.this.getContext() != null) {
                        IMyActivity iMyActivity = null;
                        if (CustomChannelFollowView.this.getContext() instanceof ThemedReactContext) {
                            iMyActivity = (IMyActivity) ((ThemedReactContext) CustomChannelFollowView.this.getContext()).getCurrentActivity();
                        } else if (CustomChannelFollowView.this.getContext() instanceof IMyActivity) {
                            iMyActivity = (IMyActivity) CustomChannelFollowView.this.getContext();
                        }
                        if (iMyActivity == null) {
                            Log.d(CustomChannelFollowView.TAG, "activity is null");
                            return;
                        }
                        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CustomChannelFollowView.this.checkIsFollow(true);
                            }
                        });
                    }
                } else {
                    CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                    customChannelFollowView.startFollow(customChannelFollowView.bShowFollow, CustomChannelFollowView.FOLLOW_TYPE_BTN);
                }
                if (CustomChannelFollowView.this.bShowFollow) {
                    CustomChannelFollowView.this.setContentDescription("\u5173\u6ce8");
                } else {
                    CustomChannelFollowView.this.setContentDescription("\u5df2\u5173\u6ce8");
                }
            }
        });
        safeRegisterEventBus(this);
    }

    public void initParams(int i2, int i3, ViewGroup viewGroup, Context context) {
        this.mBottom = i2;
        this.mTime = i3;
        this.mContainer = viewGroup;
        this.mContext = context;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachToWindow");
        this.bAttachToWindow = true;
        if (this.isUserFollowEntity == null) {
            checkIsFollow(false);
        }
        safeRegisterEventBus(this);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged ->> onLayoutChanged");
        onLayoutChanged();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        this.bAttachToWindow = false;
        closeAllPopWindow();
        safeUnRegisterEventBus(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (!(baseEvent instanceof FollowBaseEvent) || baseEvent == null || baseEvent.getBundle() == null) {
            return;
        }
        Bundle bundle = baseEvent.getBundle();
        String string = bundle.getString("themeId");
        boolean z = bundle.getBoolean("followStatus");
        boolean z2 = bundle.getBoolean("channelGuidMode");
        Log.d(TAG, "channelGuidMode" + z2);
        Log.d(TAG, "themeId" + string);
        if (TextUtils.isEmpty(string) || !string.equals(this.mThemeId)) {
            return;
        }
        if (z2) {
            if (z) {
                this.bShowFollow = true;
                showFollowIcon();
                return;
            }
            this.bShowFollow = false;
            showUnFollowIcon();
        } else if (z) {
            if (this.mIsFloatBarModel) {
                this.mIsShowing.set(false);
            }
            this.bShowFollow = true;
            showFollowIcon();
        } else {
            this.bShowFollow = false;
            showUnFollowIcon();
            if (!this.mIsFloatBarModel || this.mContainer == null) {
                return;
            }
            this.mIsShowing.set(false);
            this.mContainer.removeAllViews();
            safeUnRegisterEventBus(this);
        }
    }

    @Deprecated
    public void refreshFollowStatus(String str) {
        ImageView imageView;
        if (!LoginUserBase.hasLogin() || TextUtils.isEmpty(str) || !str.equals(this.mThemeId) || (imageView = this.mIvFollow) == null || imageView.getParent() == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("isUserFollow");
        httpSetting.putJsonParam("businessId", "1");
        httpSetting.putJsonParam("themeId", str);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.21
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                Log.d(CustomChannelFollowView.TAG, "onEnd");
                if (httpResponse.getFastJsonObject() == null) {
                    return;
                }
                CustomChannelFollowView.this.post(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomChannelFollowView.21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        CustomChannelFollowView.this.isUserFollowEntity = (IsUserFollowEntity) JDJSON.parseObject(fastJsonObject.toString(), IsUserFollowEntity.class);
                        if (CustomChannelFollowView.this.isUserFollowEntity == null) {
                            return;
                        }
                        if (TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.showFollow) || !"0".equals(CustomChannelFollowView.this.isUserFollowEntity.showFollow)) {
                            if (TextUtils.isEmpty(CustomChannelFollowView.this.isUserFollowEntity.followStatus)) {
                                return;
                            }
                            CustomChannelFollowView.this.mIvFollow.setVisibility(0);
                            if ("1".equals(CustomChannelFollowView.this.isUserFollowEntity.followStatus)) {
                                CustomChannelFollowView.this.bShowFollow = false;
                                CustomChannelFollowView.this.showUnFollowIcon();
                                return;
                            }
                            CustomChannelFollowView.this.bShowFollow = true;
                            CustomChannelFollowView.this.onLayoutChanged();
                            Log.d(CustomChannelFollowView.TAG, "refreshFollowStatus onLayoutChanged");
                            CustomChannelFollowView customChannelFollowView = CustomChannelFollowView.this;
                            customChannelFollowView.mActivityType = customChannelFollowView.getActivityType(customChannelFollowView.isUserFollowEntity.activityType);
                            if (!"1".equals(CustomChannelFollowView.this.mActivityType) && !"2".equals(CustomChannelFollowView.this.mActivityType)) {
                                CustomChannelFollowView.this.showFollowIcon();
                            } else {
                                CustomChannelFollowView customChannelFollowView2 = CustomChannelFollowView.this;
                                customChannelFollowView2.showFollowGitIcon(customChannelFollowView2.mActivityType);
                            }
                            if (CustomChannelFollowView.this.isUserFollowEntity != null) {
                                CustomChannelFollowView customChannelFollowView3 = CustomChannelFollowView.this;
                                customChannelFollowView3.showFollowTips(customChannelFollowView3.mActivityType);
                                return;
                            }
                            return;
                        }
                        Log.d(CustomChannelFollowView.TAG, "showFollow is 0,\u6ca1\u6709\u88ab\u7070\u5230");
                        CustomChannelFollowView.this.mIvFollow.setVisibility(4);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(CustomChannelFollowView.TAG, "onError");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void resetFollowViewWidthAndHeight(int i2, int i3) {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout.LayoutParams layoutParams2;
        Log.d(TAG, "resetFollowViewWidthAndHeight isUpdateLayout:" + this.isUpdateLayout);
        IsUserFollowEntity isUserFollowEntity = this.isUserFollowEntity;
        if (isUserFollowEntity == null) {
            return;
        }
        String activityType = getActivityType(isUserFollowEntity.activityType);
        if (this.isUpdateLayout) {
            return;
        }
        if (this.container != null && this.containerLp != null) {
            Log.d(TAG, "resetFollowViewWidthAndHeight activityType: " + activityType);
            if (!"1".equals(activityType) && !"2".equals(activityType)) {
                RelativeLayout.LayoutParams layoutParams3 = this.containerLp;
                layoutParams3.width = i2;
                layoutParams3.height = i3;
            } else {
                RelativeLayout.LayoutParams layoutParams4 = this.containerLp;
                layoutParams4.width = (int) (i2 * 1.08f);
                layoutParams4.height = (int) (i3 * 1.2f);
            }
            this.container.setLayoutParams(this.containerLp);
        }
        ImageView imageView = this.mIvFollow;
        if (imageView != null && (layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams()) != null) {
            layoutParams2.width = i2;
            layoutParams2.height = i3;
            this.mIvFollow.setLayoutParams(layoutParams2);
        }
        SimpleDraweeView simpleDraweeView = this.mIvGift;
        if (simpleDraweeView != null && (layoutParams = (RelativeLayout.LayoutParams) simpleDraweeView.getLayoutParams()) != null) {
            int i4 = (int) (i2 * 0.24f);
            layoutParams.width = i4;
            layoutParams.height = i4;
            this.mIvGift.setLayoutParams(layoutParams);
        }
        this.isUpdateLayout = true;
    }

    public void setChannelId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setChannelId(str, false);
    }

    public void setChannelIdForRN(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mThemeId = str;
        if (this.bAttachToWindow) {
            checkIsFollow(false);
        }
    }

    public void setFollowChannelId(String str, boolean z) {
        Log.d(TAG, "setChannelId and id  is " + str + " and showBlack is " + z);
        if (TextUtils.isEmpty(str) || this.mIsShowing.get()) {
            return;
        }
        this.mIsShowing.set(true);
        this.mThemeId = str;
        this.bShowBlack = z;
        this.mIsFloatBarModel = true;
        checkIsFollow(false);
        safeRegisterEventBus(this);
    }

    /* loaded from: classes12.dex */
    public class FollowBaseEvent extends BaseEvent {
        public static final String TYPE_FLOATMODE = "type_floatMode";

        public FollowBaseEvent(String str) {
            super(str);
        }

        public FollowBaseEvent(String str, String str2) {
            super(str, str2);
        }
    }

    public CustomChannelFollowView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomChannelFollowView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.bShowFollow = true;
        this.bIsFetching = false;
        this.bAttachToWindow = false;
        this.bShowFollowTips = false;
        this.bShowNormalPopTips = false;
        this.bShowRightPopTips = false;
        this.mActivityType = "0";
        this.batchId = "";
        this.mIsFloatBarModel = false;
        this.mIsShowing = new AtomicBoolean(false);
        this.mTime = 0;
        this.isShowWindow = true;
        this.mContext = context;
        init();
    }

    public void setChannelId(String str, boolean z) {
        Log.d(TAG, "setChannelId and id  is " + str + " and showBlack is " + z);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mThemeId = str;
        this.bShowBlack = z;
        if (this.bAttachToWindow) {
            checkIsFollow(false);
        }
    }
}
