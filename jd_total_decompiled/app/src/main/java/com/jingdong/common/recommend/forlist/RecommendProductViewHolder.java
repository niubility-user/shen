package com.jingdong.common.recommend.forlist;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendAutoFitTextView;
import com.jingdong.common.recommend.RecommendFeedBackManger;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.FeedBackReason;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendTag;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.unification.video.player.AVideoViewBtClickListener;
import com.jingdong.common.unification.video.player.IVideoViewOnTouchListener;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendProductViewHolder extends BaseRecommendViewHolder {
    private static final String TAG = "com.jingdong.common.recommend.forlist.RecommendProductViewHolder";
    private BaseActivity activity;
    SimpleDraweeView addCar;
    private int addCarDrawableId;
    private String addCarUrl;
    private SimpleDraweeView benefitCircleBg;
    private View benefitLyout;
    private TextView benefitTime;
    private RecommendAutoFitTextView benefitTitle;
    private SimpleDraweeView benefitTitleBg;
    private final LinearLayout benefitTitle_ll;
    SimpleDraweeView button;
    private String buttonUrl;
    private final View cardView;
    private TextView cashBackContentView;
    private TextView cashBackTitleView;
    private View cashBackView;
    private SimpleDraweeView channelUnderIcon;
    private TextView channelUnderName;
    private View channelUnderView;
    private ImageView delete;
    View dot;
    LinearLayout emptyLayout;
    private SimpleDraweeView forecastCircle;
    private Handler handler;
    private ImageView icon618;
    ImageView iconC;
    SimpleDraweeView image;
    SimpleDraweeView imageEntrance;
    String imageUrl;
    private boolean isLottiePlay;
    private RecommendItem item;
    private int labelAvailableWidth;
    View leftBottomDot;
    private ViewGroup liveStateContainer;
    private LottieAnimationView liveStateView;
    private int mFrom;
    private int maxTime;
    TextView name;
    RelativeLayout noRecommendLayout;
    TextView noRecommendTv;
    RelativeLayout parentLayout;
    private RecommendAutoFitTextView prePrice;
    private final RelativeLayout prePriceLayout;
    private TextView preTitle;
    TextView price;
    TextView priceImage;
    private LinearLayout recommendInfoLayout;
    TextView recommendInfoTv;
    private int recommendItemWidth;
    TextView secondPrice;
    private int similarButtonDrawableId;
    private TextView snapUpTV;
    private ImageView storeDistanceRightArrow;
    private TextView storeDistanceTv;
    private RelativeLayout storeLayout;
    private TextView storeNameTv;
    private LinearLayout topRecommendInfoLayout;
    private View videoContainer;
    private TextView videoCountDownTime;
    private final SimpleDraweeView videoPlay;
    private VideoPlayView videoPlayView;
    private TextView videoTotalTime;
    private ViewStub videoViewStub;
    private int viewType;

    public RecommendProductViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.recommendItemWidth = 0;
        this.maxTime = 0;
        this.mFrom = -1;
        this.item = null;
        this.isLottiePlay = false;
        this.activity = baseActivity;
        this.cardView = view;
        this.handler = baseActivity.getHandler();
        this.parentLayout = (RelativeLayout) view.findViewById(R.id.rp_item);
        this.emptyLayout = (LinearLayout) view.findViewById(R.id.recommend_product_item_empty);
        this.noRecommendLayout = (RelativeLayout) view.findViewById(R.id.recommend_no_layout);
        this.noRecommendTv = (TextView) view.findViewById(R.id.recommend_no_tv);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_imgview);
        this.image = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.name = (TextView) view.findViewById(R.id.recommend_product_item_name);
        this.priceImage = (TextView) view.findViewById(R.id.recommend_product_item_sams_img);
        TextView textView = (TextView) view.findViewById(R.id.recommend_product_item_price);
        this.price = textView;
        RecommendFontUtils.changeFont(textView, 4099);
        TextView textView2 = (TextView) view.findViewById(R.id.recommend_product_item_sams_price);
        this.secondPrice = textView2;
        RecommendFontUtils.changeFont(textView2, 4099);
        this.button = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_button);
        this.addCar = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_addcar);
        this.recommendInfoTv = (TextView) view.findViewById(R.id.recommend_product_item_recommendinfo);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.iconC = (ImageView) view.findViewById(R.id.recommend_img_iconC);
        this.icon618 = (ImageView) view.findViewById(R.id.recommend_product_item_icon618);
        this.delete = (ImageView) view.findViewById(R.id.recommend_product_delete);
        this.topRecommendInfoLayout = (LinearLayout) view.findViewById(R.id.recommend_product_item_info);
        this.recommendInfoLayout = (LinearLayout) view.findViewById(R.id.recommend_product_item_recomInfo_ll);
        this.snapUpTV = (TextView) view.findViewById(R.id.recommend_snap_up_num);
        this.benefitLyout = view.findViewById(R.id.recommend_benefit);
        this.prePriceLayout = (RelativeLayout) view.findViewById(R.id.recommend_forecast_price_layout);
        this.benefitTitleBg = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_bottom);
        this.benefitCircleBg = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_bottom_bg);
        this.benefitTitle = (RecommendAutoFitTextView) view.findViewById(R.id.recommend_forecast_price_title);
        TextView textView3 = (TextView) view.findViewById(R.id.recommend_forecast_price_date);
        this.benefitTime = textView3;
        RecommendFontUtils.changeFont(textView3, 4097);
        this.forecastCircle = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_circle);
        this.benefitTitle_ll = (LinearLayout) view.findViewById(R.id.recommend_forecast_price_title_ll);
        this.prePrice = (RecommendAutoFitTextView) view.findViewById(R.id.recommend_forecast_price_tv);
        this.preTitle = (TextView) view.findViewById(R.id.recommend_forecast_price_name);
        this.videoPlay = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_img_play);
        this.channelUnderView = view.findViewById(R.id.recommend_product_item_channelUnder);
        this.channelUnderIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_channelUnder_icon);
        this.channelUnderName = (TextView) view.findViewById(R.id.recommend_product_item_channelUnder_name);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
        this.cashBackView = view.findViewById(R.id.recommend_cashback_layout);
        this.cashBackTitleView = (TextView) view.findViewById(R.id.recommend_cashback_title);
        this.cashBackContentView = (TextView) view.findViewById(R.id.recommend_cashback_price);
        this.liveStateContainer = (ViewGroup) view.findViewById(R.id.recommend_live_state_container);
        this.storeLayout = (RelativeLayout) view.findViewById(R.id.recommend_product_store_layout);
        this.storeNameTv = (TextView) view.findViewById(R.id.recommend_product_store_name_tv);
        this.storeDistanceTv = (TextView) view.findViewById(R.id.recommend_product_store_distance_tv);
        this.storeDistanceRightArrow = (ImageView) view.findViewById(R.id.recommend_product_store_right_arrow);
        setLiveAnimation();
    }

    private void addBenefitPoint(RecommendProduct recommendProduct) {
        TextView textView = UnIconConfigHelper.getTextView(recommendProduct.benefitPointTab, recommendProduct.benefitPoint);
        int textWidth = (int) (this.labelAvailableWidth - (getTextWidth(textView, 9.0f) + DPIUtil.dip2px(18.0f)));
        this.labelAvailableWidth = textWidth;
        if (textWidth > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(9.0f);
            textView.setLines(1);
            this.topRecommendInfoLayout.addView(textView);
            this.topRecommendInfoLayout.setVisibility(0);
        }
    }

    private void addFreeShipping(RecommendProduct recommendProduct) {
        TextView textView = UnIconConfigHelper.getTextView(recommendProduct.freeShippingTab, recommendProduct.freeShipping);
        int textWidth = (int) (this.labelAvailableWidth - (getTextWidth(textView, 9.0f) + DPIUtil.dip2px(18.0f)));
        this.labelAvailableWidth = textWidth;
        if (textWidth > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
            layoutParams.gravity = 16;
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(9.0f);
            textView.setLines(1);
            this.topRecommendInfoLayout.addView(textView);
            this.topRecommendInfoLayout.setVisibility(0);
        }
    }

    private void addFreeShippingOrBenefitPoint(RecommendProduct recommendProduct, int i2) {
        this.labelAvailableWidth = i2 - DPIUtil.dip2px(25.0f);
        if (!TextUtils.isEmpty(recommendProduct.lowestPriceDaysTab) && !TextUtils.isEmpty(recommendProduct.lowestPriceDays)) {
            addLowestPriceTag(recommendProduct);
        }
        if (!TextUtils.isEmpty(recommendProduct.freeShippingTab) && !TextUtils.isEmpty(recommendProduct.freeShipping)) {
            addFreeShipping(recommendProduct);
        }
        if (TextUtils.isEmpty(recommendProduct.benefitPointTab) || TextUtils.isEmpty(recommendProduct.benefitPoint)) {
            return;
        }
        addBenefitPoint(recommendProduct);
    }

    private void addLowestPriceTag(RecommendProduct recommendProduct) {
        TextView textView = UnIconConfigHelper.getTextView(recommendProduct.lowestPriceDaysTab, recommendProduct.lowestPriceDays);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        textView.setLines(1);
        textView.setTextSize(9.0f);
        this.labelAvailableWidth = (int) (this.labelAvailableWidth - (DPIUtil.dip2px(23.0f) + getTextWidth(textView, 9.0f)));
        this.topRecommendInfoLayout.addView(textView);
        this.topRecommendInfoLayout.setVisibility(0);
    }

    private void addRecommendLabel(List<RecommendLabel> list, RecommendProduct recommendProduct, int i2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.labelAvailableWidth = i2 - DPIUtil.dip2px(25.0f);
        int i3 = 0;
        while (true) {
            if (i3 < list.size()) {
                RecommendLabel recommendLabel = list.get(i3);
                if (recommendLabel != null && !TextUtils.isEmpty(recommendLabel.resourceCode) && !TextUtils.isEmpty(recommendLabel.key)) {
                    TextView textView = UnIconConfigHelper.getTextView(recommendLabel.resourceCode, recommendLabel.labelTitle);
                    float f2 = 0.0f;
                    if (TextUtils.isEmpty(recommendLabel.labelTitle)) {
                        Bitmap bitmap = UnIconConfigHelper.getBitmap(recommendLabel.resourceCode);
                        if (bitmap != null) {
                            f2 = bitmap.getWidth();
                        }
                    } else {
                        f2 = getTextWidth(recommendLabel.resourceCode, textView, 9.0f) + textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight();
                    }
                    if (i3 == 0) {
                        this.labelAvailableWidth = (int) (this.labelAvailableWidth - f2);
                    } else {
                        this.labelAvailableWidth = (int) (this.labelAvailableWidth - (DPIUtil.dip2px(5.0f) + f2));
                    }
                    if (this.labelAvailableWidth > 0) {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
                        layoutParams.gravity = 16;
                        textView.setLayoutParams(layoutParams);
                        textView.setLines(1);
                        textView.setTextSize(9.0f);
                        this.topRecommendInfoLayout.addView(textView);
                    }
                    RecommendUtils.handleLableMtaJson(recommendProduct, recommendLabel, this.labelAvailableWidth > 0);
                }
                i3++;
            } else {
                recommendProduct.isMtaValueChanged = true;
                this.topRecommendInfoLayout.setVisibility(0);
                return;
            }
        }
    }

    private void addRecommendReason(RecommendProduct recommendProduct) {
        this.recommendInfoLayout.addView(getRecommendTextView(recommendProduct));
        this.recommendInfoLayout.setVisibility(0);
    }

    private void addRecommendTags(RecommendProduct recommendProduct, float f2) {
        List<RecommendTag> list = recommendProduct.symbolList;
        StringBuilder sb = new StringBuilder();
        float dip2px = f2 - DPIUtil.dip2px(24.0f);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            RecommendTag recommendTag = list.get(i2);
            if (recommendTag != null && !TextUtils.isEmpty(recommendTag.name) && !TextUtils.isEmpty(recommendTag.id)) {
                TextView textView = UnIconConfigHelper.getTextView(recommendProduct.symbolTab, recommendTag.name);
                RecommendFontUtils.changeFont(textView, 4099);
                textView.setPadding(DPIUtil.dip2px(4.0f), DPIUtil.dip2px(3.0f), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(3.0f));
                textView.setTextSize(10.0f);
                textView.setLines(1);
                dip2px -= getTextWidth(recommendProduct.symbolTab, textView, 10.0f) + DPIUtil.dip2px(15.0f);
                if (dip2px > 0.0f) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    if (i2 != 0) {
                        layoutParams.leftMargin = DPIUtil.dip2px(7.0f);
                    }
                    textView.setLayoutParams(layoutParams);
                    this.topRecommendInfoLayout.addView(textView);
                    this.topRecommendInfoLayout.setVisibility(0);
                    sb.append(recommendTag.id);
                    if (i2 != size - 1) {
                        sb.append(DYConstants.DY_REGEX_AT);
                    }
                }
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            recommendProduct.tagIds = "-100";
            return;
        }
        if (sb2.endsWith(DYConstants.DY_REGEX_AT)) {
            sb2 = sb2.substring(0, sb2.length() - 1);
        }
        recommendProduct.tagIds = sb2;
    }

    private TextView addTimeView() {
        TextView textView = new TextView(this.activity);
        textView.setTextSize(11.0f);
        textView.setPadding(DPIUtil.dip2px(6.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(6.0f), DPIUtil.dip2px(2.0f));
        textView.setGravity(17);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setBackgroundResource(R.drawable.recommend_video_time_bg);
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callRefreshListData() {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (this.mFrom == 9 || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRefresh();
    }

    private void configImage(RecommendProduct recommendProduct) {
        SimpleDraweeView simpleDraweeView = this.image;
        if (simpleDraweeView == null || simpleDraweeView.getVisibility() == 8 || this.mFrom != 9) {
            return;
        }
        if (checkHomePageTestPlanIsA()) {
            if (this.image.getAspectRatio() != 1.0f) {
                this.image.setAspectRatio(1.0f);
                this.image.requestLayout();
                return;
            }
            return;
        }
        float f2 = (recommendProduct == null ? 0 : recommendProduct.imageurlType) != 0 ? 0.78f : 1.0f;
        if (this.image.getAspectRatio() != f2) {
            this.image.setAspectRatio(f2);
            this.image.requestLayout();
        }
    }

    private void downPriceIcon(RecommendProduct recommendProduct) {
        Drawable drawable;
        if (TextUtils.isEmpty(recommendProduct.icon3) || (drawable = UnIconConfigHelper.getDrawable(recommendProduct.icon3)) == null) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.secondPrice.setCompoundDrawables(null, null, drawable, null);
    }

    private void fixNoRecommendLayoutBug() {
        ViewGroup.LayoutParams layoutParams;
        RelativeLayout relativeLayout = this.parentLayout;
        if (relativeLayout == null || this.noRecommendLayout == null) {
            return;
        }
        int height = relativeLayout.getHeight();
        if (OKLog.D) {
            OKLog.d(TAG, "parentHeight:-->" + height);
        }
        if (height <= 0 || (layoutParams = this.noRecommendLayout.getLayoutParams()) == null || layoutParams.height == height) {
            return;
        }
        layoutParams.height = height;
        this.noRecommendLayout.requestLayout();
    }

    private void formatTime(int i2) {
        if (this.videoCountDownTime == null) {
            this.videoCountDownTime = addTimeView();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
            layoutParams.bottomMargin = DPIUtil.dip2px(5.0f);
            VideoPlayView videoPlayView = this.videoPlayView;
            if (videoPlayView != null) {
                videoPlayView.addViewAlignVideoRightEnd(this.videoCountDownTime, layoutParams);
            }
        }
        if (i2 >= 0) {
            String format = new SimpleDateFormat("mm:ss").format(Integer.valueOf(i2));
            TextView textView = this.videoCountDownTime;
            if (textView != null) {
                textView.setText(format);
                this.videoCountDownTime.setVisibility(0);
            }
        }
    }

    private TextView getRecommendTextView(RecommendProduct recommendProduct) {
        TextView textView = UnIconConfigHelper.getTextView(recommendProduct.recomReasonTab, recommendProduct.recomReason);
        RecommendFontUtils.changeFont(textView, 4099);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextSize(10.0f);
        textView.setLines(1);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        return textView;
    }

    private float getTextWidth(TextView textView, float f2) {
        Paint paint = new Paint();
        paint.set(textView.getPaint());
        paint.setTextSize(DpiUtil.sp2px(this.activity, f2));
        return paint.measureText(textView.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePopupWindowProblem(RecommendFeedBackManger recommendFeedBackManger) {
        recommendFeedBackManger.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.10
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                RecommendProductViewHolder.this.parentLayout.requestDisallowInterceptTouchEvent(false);
            }
        });
        this.parentLayout.requestDisallowInterceptTouchEvent(true);
    }

    private void initAnimation(RelativeLayout relativeLayout) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(relativeLayout, "scaleX", 0.0f, 1.0f);
        ofFloat.setDuration(200L);
        ofFloat.start();
    }

    private void initVideoPlayView(RecommendProduct recommendProduct) {
        try {
            if (this.videoViewStub == null) {
                this.videoViewStub = (ViewStub) this.cardView.findViewById(R.id.recommend_product_video);
                this.videoTotalTime = (TextView) this.cardView.findViewById(R.id.recommend_product_video_total_time);
            }
            if (this.videoContainer == null) {
                this.videoContainer = this.videoViewStub.inflate();
            }
            if (this.videoPlayView == null) {
                this.videoPlayView = (VideoPlayView) this.videoContainer.findViewById(R.id.recommend_vp_left);
            }
            setVideoViewStubHeight(recommendProduct);
            setVideoPlayListener();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean isEnable() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "liveLottie", "enable", "1"));
    }

    private boolean isNoShow(int i2) {
        return i2 == 0 || i2 == 9 || i2 == 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002a A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean isPrice(String str) {
        double parseDouble;
        if (TextUtils.equals(this.activity.getString(R.string.recommend_product_no_price), str)) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                parseDouble = Double.parseDouble(str);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
            return parseDouble <= 0.0d;
        }
        parseDouble = 0.0d;
        if (parseDouble <= 0.0d) {
        }
    }

    private boolean isShowJdPrice(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            return isPrice(recommendProduct.jdPrice);
        }
        return false;
    }

    private void jumpProductDetail(final RecommendProduct recommendProduct, final int i2, final int i3) {
        this.parentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecommendUtils.isTooFastClick() || RecommendProductViewHolder.this.clickedListener == null) {
                    return;
                }
                if ("1".equals(recommendProduct.isStoreGoods)) {
                    RecommendMtaUtils.productClickMta(RecommendProductViewHolder.this.itemView.getContext(), recommendProduct, RecommendProductViewHolder.this.mFrom, 0);
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                    RecommendProduct recommendProduct2 = recommendProduct;
                    onRecommendClickedListener.onRecommendJump(recommendProduct2.channelJumpUrl, recommendProduct2.isOpenApp);
                    return;
                }
                String str = null;
                if (!TextUtils.isEmpty(recommendProduct.popUnit)) {
                    RecommendJumpUtils.gotoMWithUrl(RecommendProductViewHolder.this.activity, null, recommendProduct.popUrl);
                } else if (i2 == -1) {
                    int i4 = i3;
                    if (i4 == 6) {
                        str = RecommendMtaUtils.Shopcart_Compare_Productid;
                    } else if (i4 == 10) {
                        str = RecommendMtaUtils.OrderCenterMyPurchase_FloorProduct;
                    }
                    RecommendProductViewHolder.this.clickedListener.onProductClick(recommendProduct, str);
                } else if (i3 == 9) {
                    RecommendProductViewHolder recommendProductViewHolder = RecommendProductViewHolder.this;
                    recommendProductViewHolder.clickedListener.onProductClick(recommendProduct, recommendProductViewHolder.item);
                } else {
                    RecommendProductViewHolder.this.clickedListener.onProductClick(recommendProduct);
                }
                if (TextUtils.isEmpty(recommendProduct.client_click_url)) {
                    return;
                }
                RecommendProductViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendProduct.client_click_url);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void oldJumpMyStreet() {
        Bundle bundle = new Bundle();
        if (CommonBase.getJdSharedPreferences().getBoolean("Recommend_isFirst_startMyStreet", true)) {
            CommonBase.getJdSharedPreferences().edit().putBoolean("Recommend_isFirst_startMyStreet", false).apply();
            bundle.putBoolean("isGoToGene", true);
        } else {
            bundle.putBoolean("isGoToGene", false);
        }
        DeepLinkMyStreetHelper.startMyStreet(this.activity, bundle);
    }

    private synchronized void refitText(TextView textView, int i2, boolean z, boolean z2) {
        int i3;
        int widthByDesignValue750;
        String charSequence = textView.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        if (i2 > 0) {
            float f2 = i2;
            if (z2) {
                if (z) {
                    i3 = i2 / 2;
                    widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(9);
                } else {
                    i3 = i2 / 2;
                    widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(3);
                }
                f2 = i3 - widthByDesignValue750;
            }
            float textSize = textView.getTextSize();
            Paint paint = new Paint();
            paint.set(textView.getPaint());
            paint.setTextSize(textSize);
            float measureText = paint.measureText(charSequence);
            while (true) {
                float f3 = 5;
                if (textSize <= f3 || measureText <= f2) {
                    break;
                }
                textSize -= 1.0f;
                if (textSize <= f3) {
                    textSize = f3;
                    break;
                } else {
                    measureText = paint.measureText(charSequence);
                    paint.setTextSize(textSize);
                }
            }
            textView.setTextSize(DPIUtil.px2sp(this.activity, textSize) - 0.5f);
        }
    }

    private boolean reportMtaOnce() {
        int i2 = this.mFrom;
        return i2 == 37 || i2 == 0 || i2 == 18 || i2 == 6;
    }

    private void resetInit() {
        this.price.setTextSize(12.0f);
        this.secondPrice.setTextSize(11.0f);
        RecommendFontUtils.changeFont(this.secondPrice, 4099);
        this.noRecommendTv.setVisibility(0);
        this.secondPrice.setVisibility(8);
        this.priceImage.setVisibility(8);
        this.button.setVisibility(8);
        this.addCar.setVisibility(8);
        this.iconC.setVisibility(8);
        this.icon618.setVisibility(8);
        this.delete.setVisibility(8);
        this.benefitLyout.setVisibility(8);
        this.videoPlay.setVisibility(8);
        this.channelUnderView.setVisibility(8);
        this.recommendInfoLayout.setVisibility(8);
        this.recommendInfoLayout.removeAllViews();
        this.topRecommendInfoLayout.setVisibility(8);
        this.topRecommendInfoLayout.removeAllViews();
        this.snapUpTV.setVisibility(8);
        this.cashBackView.setVisibility(8);
        View view = this.videoContainer;
        if (view != null) {
            view.setVisibility(8);
        }
        TextView textView = this.videoTotalTime;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.storeLayout.setVisibility(8);
        this.liveStateContainer.setVisibility(8);
        this.secondPrice.setTextColor(Color.parseColor("#BFBFBF"));
        this.storeDistanceTv.setTextColor(getColor_2E2D2D());
        this.name.setTextColor(getColor_262626());
        this.channelUnderName.setTextColor(getColor_2E2D2D());
        if (isDeepDark()) {
            this.channelUnderView.setBackgroundResource(0);
            this.similarButtonDrawableId = R.drawable.button_similar_dark;
            this.addCarDrawableId = R.drawable.dyn_pd_addshoppingcart_recommend_gray_dark;
        } else {
            this.channelUnderView.setBackgroundColor(Color.parseColor("#FCFCFC"));
            this.similarButtonDrawableId = R.drawable.button_similar;
            this.addCarDrawableId = R.drawable.dyn_pd_addshoppingcart_recommend_gray;
        }
        View view2 = this.itemView;
        if (view2 instanceof CardView) {
            ((CardView) view2).setCardBackgroundColor(getColor_FFFFFF());
        }
    }

    private void setJdPrice(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            String jdPrice = recommendProduct.getJdPrice();
            if (!TextUtils.equals(this.activity.getString(R.string.recommend_product_no_price), jdPrice)) {
                String str = this.activity.getResources().getString(R.string.yangjiao) + jdPrice;
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf != -1) {
                    if (this.viewType == 37) {
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(23, true), 1, indexOf, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(15, true), indexOf, spannableStringBuilder.length(), 33);
                    } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(19, true), 1, indexOf, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), indexOf, spannableStringBuilder.length(), 33);
                    } else {
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(16, true), 1, indexOf, 33);
                    }
                }
                if (!TextUtils.isEmpty(recommendProduct.popUnit)) {
                    SpannableString spannableString = new SpannableString(recommendProduct.popUnit);
                    if (this.viewType == 37) {
                        spannableString.setSpan(new AbsoluteSizeSpan(15, true), 0, spannableString.length(), 33);
                    } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                        spannableString.setSpan(new AbsoluteSizeSpan(13, true), 0, spannableString.length(), 33);
                    } else {
                        spannableString.setSpan(new AbsoluteSizeSpan(11, true), 0, spannableString.length(), 33);
                    }
                    spannableStringBuilder.append((CharSequence) spannableString);
                    this.price.setText(spannableStringBuilder);
                    return;
                } else if (!TextUtils.isEmpty(recommendProduct.stagesKinds)) {
                    setStagesKindsInfo(recommendProduct, spannableStringBuilder);
                    return;
                } else {
                    this.price.setText(spannableStringBuilder);
                    return;
                }
            }
            if (this.viewType == 37) {
                this.price.setTextSize(15.0f);
            } else {
                this.price.setTextSize(ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode()) ? 14.0f : 12.0f);
            }
            this.price.setText(jdPrice);
            return;
        }
        String str2 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.presaleText;
        SpannableString spannableString2 = new SpannableString(str2);
        if (this.viewType == 37) {
            int indexOf2 = str2.indexOf(OrderISVUtil.MONEY_DECIMAL);
            spannableString2.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
            if (indexOf2 != -1) {
                spannableString2.setSpan(new AbsoluteSizeSpan(23, true), 1, indexOf2, 33);
                spannableString2.setSpan(new AbsoluteSizeSpan(15, true), indexOf2, spannableString2.length(), 33);
            }
        } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            spannableString2.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
            spannableString2.setSpan(new AbsoluteSizeSpan(14, true), 1, spannableString2.length(), 33);
        } else {
            spannableString2.setSpan(new AbsoluteSizeSpan(11, true), 0, 1, 33);
        }
        this.price.setText(spannableString2);
        if (TextUtils.isEmpty(recommendProduct.presaleTextColor)) {
            return;
        }
        this.price.setTextColor(Color.parseColor("#" + recommendProduct.presaleTextColor));
    }

    private void setLiveAnimation() {
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable() && isEnable()) {
            LottieAnimationView lottieAnimationView = new LottieAnimationView(this.itemView.getContext());
            this.liveStateView = lottieAnimationView;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("live.json");
                this.liveStateView.setSpeed(1.5f);
                this.liveStateView.setRepeatCount(-1);
                this.liveStateView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.2
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                        if (!RecommendProductViewHolder.this.isLottiePlay || RecommendProductViewHolder.this.liveStateView.isAnimating()) {
                            return;
                        }
                        RecommendProductViewHolder.this.liveStateView.playAnimation();
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                    }
                });
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(9.0f));
            layoutParams.leftMargin = DPIUtil.dip2px(2.0f);
            RecommendViewUtil.addView(this.liveStateContainer, this.liveStateView, 1, layoutParams);
        }
    }

    private void setNameInfo(RecommendProduct recommendProduct) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendProduct.icon1);
        arrayList.add(recommendProduct.icon2);
        this.name.setText(UnIconConfigHelper.getSpanableString(arrayList, recommendProduct.getName(), this.name));
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.name.setTextSize(16.0f);
        } else {
            this.name.setTextSize(13.0f);
        }
    }

    private void setNegFeedback(final RecommendProduct recommendProduct, final int i2, int i3) {
        if (recommendProduct.isCanNegFeedback() && recommendProduct.isShowFeedbackUi) {
            if (!RecommendUtil.dotClick) {
                if (CommonBase.getJdSharedPreferences().getInt("Recommend_productHash", 0) == recommendProduct.hashCode() && isNoShow(i3)) {
                    this.noRecommendLayout.setVisibility(8);
                    recommendProduct.isShowFeedbackUi = false;
                    callRefreshListData();
                    CommonBase.getJdSharedPreferences().edit().putInt("Recommend_productHash", 0).apply();
                }
            } else {
                this.noRecommendLayout.setVisibility(0);
                fixNoRecommendLayoutBug();
                initAnimation(this.noRecommendLayout);
                CommonBase.getJdSharedPreferences().edit().putInt("Recommend_productHash", recommendProduct.hashCode()).apply();
                RecommendUtil.dotClick = false;
            }
        } else {
            this.noRecommendLayout.setVisibility(8);
        }
        if (recommendProduct.isCanNegFeedback()) {
            this.delete.setVisibility(0);
            this.delete.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty()) {
                        RecommendProductViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
                        RecommendProductViewHolder.this.handlePopupWindowProblem(recommendFeedBackManger);
                        recommendFeedBackManger.showTipPopupWindow(RecommendProductViewHolder.this.activity, RecommendProductViewHolder.this.delete, recommendProduct, i2, RecommendProductViewHolder.this.clickedListener, 1);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        RecommendProduct recommendProduct2 = recommendProduct;
                        onRecommendClickedListener.onDotMoreMta(1, recommendProduct2 != null ? recommendProduct2.feedbackSourceValue : "");
                    }
                }
            });
        }
        if (recommendProduct.isCanNegFeedback()) {
            this.parentLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.7
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return true;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty() || RecommendUtil.dotClick) {
                        RecommendProductViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
                        RecommendProductViewHolder.this.handlePopupWindowProblem(recommendFeedBackManger);
                        recommendFeedBackManger.showTipPopupWindow(RecommendProductViewHolder.this.activity, RecommendProductViewHolder.this.delete, recommendProduct, i2, RecommendProductViewHolder.this.clickedListener, 2);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        RecommendProduct recommendProduct2 = recommendProduct;
                        onRecommendClickedListener.onDotMoreMta(2, recommendProduct2 != null ? recommendProduct2.feedbackSourceValue : "");
                    }
                    return true;
                }
            });
        } else {
            this.parentLayout.setOnLongClickListener(null);
        }
        this.noRecommendLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendProductViewHolder.this.noRecommendLayout.setVisibility(8);
                recommendProduct.isShowFeedbackUi = false;
                RecommendProductViewHolder.this.callRefreshListData();
            }
        });
        this.noRecommendTv.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i4;
                RecommendProductViewHolder recommendProductViewHolder = RecommendProductViewHolder.this;
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = recommendProductViewHolder.clickedListener;
                if (onRecommendClickedListener != null && (i4 = i2) >= 0) {
                    onRecommendClickedListener.onNoRecommendClick(recommendProduct, i4, "", null);
                    return;
                }
                recommendProductViewHolder.noRecommendLayout.setVisibility(8);
                recommendProduct.isShowFeedbackUi = false;
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener2 = RecommendProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener2 != null) {
                    onRecommendClickedListener2.onRefresh();
                }
            }
        });
    }

    private void setStagesKindsInfo(RecommendProduct recommendProduct, SpannableStringBuilder spannableStringBuilder) {
        int i2;
        try {
            i2 = Integer.parseInt(recommendProduct.stagesKinds);
        } catch (NumberFormatException e2) {
            OKLog.e(TAG, e2);
            i2 = 1;
        }
        if (i2 <= 0) {
            this.price.setText(spannableStringBuilder);
        } else {
            SpannableString spannableString = new SpannableString(this.activity.getString(R.string.recommend_stagesKinds));
            if (this.viewType == 37) {
                spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(15, true), 1, spannableString.length(), 33);
            } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), 1, spannableString.length(), 33);
            } else {
                spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(11, true), 1, spannableString.length(), 33);
            }
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F23030")), 0, spannableString.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
            if (i2 > 1) {
                SpannableString spannableString2 = new SpannableString(this.activity.getString(R.string.recommend_stagesKinds_up));
                if (this.viewType == 37) {
                    spannableString2.setSpan(new AbsoluteSizeSpan(15, true), 0, spannableString.length(), 33);
                } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                    spannableString2.setSpan(new AbsoluteSizeSpan(13, true), 0, spannableString.length(), 33);
                } else {
                    spannableString2.setSpan(new AbsoluteSizeSpan(11, true), 0, spannableString.length(), 33);
                }
                spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#686868")), 0, spannableString.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString2);
            }
            this.price.setText(spannableStringBuilder);
        }
        if (TextUtils.isEmpty(recommendProduct.stageDescription)) {
            return;
        }
        TextUtils.isEmpty(recommendProduct.iconC);
    }

    private void setTextSize() {
        int measuredWidth = this.priceImage.getMeasuredWidth();
        int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(28);
        if (measuredWidth < widthByDesignValue750) {
            measuredWidth = widthByDesignValue750;
        }
        int widthByDesignValue7502 = (this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(24)) - measuredWidth;
        refitText(this.price, (this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(24)) - DPIUtil.getWidthByDesignValue750(37), false, false);
        refitText(this.secondPrice, widthByDesignValue7502, false, false);
    }

    private void setVideoPlayInfo(RecommendProduct recommendProduct) {
        try {
            if (TextUtils.isEmpty(recommendProduct.playUrl) || !NetUtils.isWifi()) {
                return;
            }
            if (recommendProduct.isCanNegFeedback()) {
                this.delete.setVisibility(8);
            }
            initVideoPlayView(recommendProduct);
            int i2 = recommendProduct.duration;
            if (i2 > 0) {
                this.maxTime = i2 * 1000;
                String formatTime = RecommendUtils.getFormatTime(i2);
                this.videoTotalTime.setVisibility(0);
                this.videoTotalTime.setText(formatTime);
            } else {
                this.videoTotalTime.setVisibility(8);
            }
            RecommendVideo recommendVideo = new RecommendVideo();
            recommendVideo.wareId = recommendProduct.wareId;
            recommendVideo.reqsig = recommendProduct.reqsig;
            recommendVideo.playUrl = recommendProduct.playUrl;
            recommendVideo.videoDuration = String.valueOf(i2);
            this.videoPlayView.setTag(recommendVideo);
            if (this.videoPlayView.isPlaying()) {
                return;
            }
            this.videoPlayView.setShowVoice(false, true).changeToScreen(4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setVideoPlayListener() {
        VideoPlayView videoPlayView = this.videoPlayView;
        if (videoPlayView != null) {
            videoPlayView.setVideoViewBtClickListener(new AVideoViewBtClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.3
                @Override // com.jingdong.common.unification.video.player.AVideoViewBtClickListener
                public boolean centerPlayClick() {
                    return false;
                }
            });
            this.videoPlayView.setVideoViewOnTouchListener(new IVideoViewOnTouchListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.4
                @Override // com.jingdong.common.unification.video.player.IVideoViewOnTouchListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoViewOnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }
    }

    private void setVideoViewStubHeight(RecommendProduct recommendProduct) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.videoViewStub.getLayoutParams();
        layoutParams.height = (int) (((DPIUtil.getWidth() - DPIUtil.dip2px(29.0f)) / 2) / ((recommendProduct == null ? 0 : recommendProduct.imageurlType) == 0 ? 1.0f : 0.78f));
        this.videoViewStub.setLayoutParams(layoutParams);
    }

    private void showAddCartButton(final RecommendProduct recommendProduct, final int i2, final int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        this.addCar.setVisibility(0);
        this.addCar.setContentDescription("\u52a0\u8d2d\u7269\u8f66");
        if (this.addCar.getDrawable() == null || (str = this.addCarUrl) == null || !str.equals(recommendProduct.activityBtnUrl)) {
            String str2 = recommendProduct.activityBtnUrl;
            this.addCarUrl = str2;
            if (TextUtils.isEmpty(str2)) {
                JDImageUtils.displayImage("res:///" + this.addCarDrawableId, this.addCar);
            } else {
                JDImageUtils.displayImage(recommendProduct.activityBtnUrl, this.addCar, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.12
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str3, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + RecommendProductViewHolder.this.addCarDrawableId, RecommendProductViewHolder.this.addCar);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str3, View view) {
                    }
                });
            }
        }
        this.addCar.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener != null) {
                    if (i2 == -1) {
                        String str3 = null;
                        int i4 = i3;
                        if (i4 == 6) {
                            str3 = RecommendMtaUtils.Shopcart_Compare_AddtoCart;
                        } else if (i4 == 10) {
                            str3 = RecommendMtaUtils.OrderCenterMyPurchase_FloorAddToCart;
                        }
                        onRecommendClickedListener.onAddCarClick(recommendProduct, str3);
                        return;
                    }
                    onRecommendClickedListener.onAddCarClick(recommendProduct);
                }
            }
        });
    }

    private void showBenefit(RecommendProduct recommendProduct) {
        boolean z;
        if (TextUtils.isEmpty(recommendProduct.benefitTitle) && (TextUtils.isEmpty(recommendProduct.benefitTime) || TextUtils.isEmpty(recommendProduct.prePrice) || TextUtils.isEmpty(recommendProduct.preTitle))) {
            return;
        }
        this.benefitLyout.setVisibility(0);
        this.benefitTitleBg.setVisibility(8);
        this.benefitCircleBg.setVisibility(8);
        this.forecastCircle.setVisibility(8);
        this.prePriceLayout.setVisibility(8);
        this.benefitTitle_ll.setVisibility(8);
        if (TextUtils.isEmpty(recommendProduct.benefitTime) || TextUtils.isEmpty(recommendProduct.prePrice) || TextUtils.isEmpty(recommendProduct.preTitle)) {
            z = false;
        } else {
            z = true;
            this.benefitTime.setText(recommendProduct.benefitTime);
            this.prePrice.setText(this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.prePrice);
            this.preTitle.setText(recommendProduct.preTitle);
            this.prePriceLayout.setVisibility(0);
        }
        if (TextUtils.isEmpty(recommendProduct.benefitTitle) || TextUtils.isEmpty(recommendProduct.benefitTime)) {
            if (z) {
                this.prePriceLayout.setVisibility(0);
                this.prePriceLayout.getLayoutParams().width = DPIUtil.dip2px(51.0f);
                this.prePriceLayout.requestLayout();
                this.forecastCircle.setVisibility(0);
                JDDisplayImageOptions showImageOnLoading = new JDDisplayImageOptions().showImageOnLoading((Drawable) null);
                int i2 = R.drawable.recommend_forecast_price_circle;
                JDImageUtils.displayImage(recommendProduct.benefitImageUrl, (ImageView) this.forecastCircle, showImageOnLoading.showImageForEmptyUri(i2).showImageOnFail(i2), false);
                return;
            }
            this.prePriceLayout.setVisibility(8);
            return;
        }
        if (z) {
            this.prePriceLayout.getLayoutParams().width = (int) (((this.recommendItemWidth * 1.0f) * 104.0f) / 372.0f);
            this.prePriceLayout.requestLayout();
            JDDisplayImageOptions showImageOnLoading2 = new JDDisplayImageOptions().showImageOnLoading((Drawable) null);
            int i3 = R.drawable.recommend_forecast_price_bg;
            JDDisplayImageOptions showImageOnFail = showImageOnLoading2.showImageForEmptyUri(i3).showImageOnFail(i3);
            this.benefitTitle.setText(recommendProduct.benefitTitle);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.benefitTitle.getLayoutParams();
            layoutParams.width = this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(55);
            layoutParams.leftMargin = DPIUtil.getWidthByDesignValue750(9);
            layoutParams.rightMargin = DPIUtil.getWidthByDesignValue750(9);
            this.benefitCircleBg.setVisibility(0);
            this.benefitCircleBg.getLayoutParams().width = this.recommendItemWidth;
            this.benefitCircleBg.requestLayout();
            if (TextUtils.isEmpty(recommendProduct.benefitImageUrl)) {
                JDImageUtils.displayImage("res:///" + i3, this.benefitCircleBg);
            } else {
                JDImageUtils.displayImage(recommendProduct.benefitImageUrl, this.benefitCircleBg, showImageOnFail, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.16
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_forecast_price_bg, RecommendProductViewHolder.this.benefitCircleBg);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                }, null);
            }
        } else {
            JDDisplayImageOptions showImageOnLoading3 = new JDDisplayImageOptions().showImageOnLoading((Drawable) null);
            int i4 = R.drawable.recommend_forecast_price_bottom;
            JDDisplayImageOptions showImageOnFail2 = showImageOnLoading3.showImageForEmptyUri(i4).showImageOnFail(i4);
            RecommendFontUtils.changeFont(this.benefitTitle, 4097);
            this.benefitTitle.setText(recommendProduct.benefitTitle + "(" + recommendProduct.benefitTime + ")");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.benefitTitle.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.leftMargin = DPIUtil.getWidthByDesignValue750(9);
            layoutParams2.rightMargin = DPIUtil.getWidthByDesignValue750(9);
            this.benefitTitleBg.setVisibility(0);
            this.benefitTitleBg.getLayoutParams().width = this.recommendItemWidth;
            this.benefitTitleBg.requestLayout();
            if (TextUtils.isEmpty(recommendProduct.benefitImageUrl)) {
                JDImageUtils.displayImage("res:///" + i4, this.benefitTitleBg);
            } else {
                JDImageUtils.displayImage(recommendProduct.benefitImageUrl, this.benefitTitleBg, showImageOnFail2, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.17
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_forecast_price_bottom, RecommendProductViewHolder.this.benefitTitleBg);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                }, null);
            }
        }
        this.benefitTitle_ll.setVisibility(0);
    }

    private void showCashBack(RecommendProduct recommendProduct) {
        float f2;
        int dip2px;
        if (TextUtils.isEmpty(recommendProduct.fxlabel) || TextUtils.isEmpty(recommendProduct.exFxamount)) {
            return;
        }
        this.cashBackView.setVisibility(0);
        this.cashBackTitleView.setText(recommendProduct.fxlabel);
        this.cashBackContentView.setText(recommendProduct.exFxamount);
        if (this.price.length() > 6) {
            this.cashBackView.measure(0, 0);
            int measuredWidth = this.cashBackView.getMeasuredWidth();
            float textWidth = getTextWidth("0", this.price, 19.0f);
            if (recommendProduct.hasAddCartButton()) {
                f2 = (this.recommendItemWidth - textWidth) - DPIUtil.dip2px(29.0f);
                dip2px = DPIUtil.dip2px(25.0f);
            } else if (recommendProduct.hasButton()) {
                f2 = (this.recommendItemWidth - textWidth) - DPIUtil.dip2px(29.0f);
                dip2px = DPIUtil.dip2px(37.0f);
            } else {
                f2 = this.recommendItemWidth - textWidth;
                dip2px = DPIUtil.dip2px(29.0f);
            }
            if (f2 - dip2px < measuredWidth) {
                this.cashBackView.setVisibility(8);
            }
        }
    }

    private void showChannelUnder(final RecommendProduct recommendProduct) {
        if (recommendProduct == null || !recommendProduct.hasChannelUnder()) {
            return;
        }
        this.channelUnderView.setVisibility(0);
        JDImageUtils.displayImage(recommendProduct.channelUnderIcon, this.channelUnderIcon, new JDDisplayImageOptions().showImageOnLoading((Drawable) null).showImageOnFail(R.drawable.recommend_channelunder_icon), false, null, null);
        this.channelUnderName.setText(recommendProduct.channelUnderName);
        this.channelUnderView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener != null) {
                    RecommendProduct recommendProduct2 = recommendProduct;
                    onRecommendClickedListener.onChannelUnderJump(recommendProduct2.channelUnderUrl, recommendProduct2.channelUnderSourceValue, recommendProduct2.isOpenApp);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFeedBackView(RecommendProduct recommendProduct, int i2) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            RecommendUtil.dotClick = true;
            onRecommendClickedListener.onShowFeedbackUi(recommendProduct, i2);
        }
    }

    private void showIconC(RecommendProduct recommendProduct) {
        Bitmap bitmap;
        float textWidth;
        float f2;
        int widthByDesignValue750;
        if (TextUtils.isEmpty(recommendProduct.iconC) || !TextUtils.isEmpty(recommendProduct.stagesKinds) || !TextUtils.isEmpty(recommendProduct.gbPrice) || (bitmap = UnIconConfigHelper.getBitmap(recommendProduct.iconC)) == null) {
            return;
        }
        this.iconC.setImageBitmap(bitmap);
        this.iconC.setVisibility(0);
        if (this.price.length() > 7) {
            if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                textWidth = getTextWidth("0", this.price, 19.0f);
            } else {
                textWidth = getTextWidth("0", this.price, 16.0f);
            }
            if (recommendProduct.hasButton()) {
                f2 = (this.recommendItemWidth - textWidth) - DPIUtil.getWidthByDesignValue750(24);
                widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(37);
            } else {
                f2 = this.recommendItemWidth - textWidth;
                widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(24);
            }
            if (f2 - widthByDesignValue750 < bitmap.getWidth()) {
                this.iconC.setVisibility(8);
            }
        }
    }

    private void showRecommendReasonOrTag(RecommendProduct recommendProduct) {
        List<RecommendTag> list;
        boolean checkHomePageTestPlanIsA = checkHomePageTestPlanIsA();
        int i2 = this.recommendItemWidth;
        boolean z = true;
        if ("1".equals(recommendProduct.isStoreGoods) && !TextUtils.isEmpty(recommendProduct.storeTimeText) && !TextUtils.isEmpty(recommendProduct.storeTimeTab)) {
            TextView textView = UnIconConfigHelper.getTextView(recommendProduct.storeTimeTab, recommendProduct.storeTimeText);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
            layoutParams.gravity = 16;
            textView.setLayoutParams(layoutParams);
            textView.setLines(1);
            textView.setTextSize(9.0f);
            this.topRecommendInfoLayout.setVisibility(0);
            this.topRecommendInfoLayout.addView(textView);
            i2 = (int) (i2 - (((getTextWidth(recommendProduct.storeTimeTab, textView, 9.0f) + textView.getCompoundPaddingRight()) + textView.getCompoundPaddingLeft()) + DPIUtil.dip2px(5.0f)));
        }
        if (TextUtils.isEmpty(recommendProduct.recomReasonTab) || TextUtils.isEmpty(recommendProduct.recomReason)) {
            if (!TextUtils.isEmpty(recommendProduct.symbolTab)) {
                if (checkHomePageTestPlanIsA || (list = recommendProduct.symbolList) == null || list.isEmpty()) {
                    return;
                }
                addRecommendTags(recommendProduct, i2);
                return;
            }
            List<RecommendLabel> list2 = recommendProduct.labelList;
            if (list2 != null) {
                if (list2.isEmpty()) {
                    return;
                }
                addRecommendLabel(list2, recommendProduct, i2);
            } else if (checkHomePageTestPlanIsA) {
            } else {
                addFreeShippingOrBenefitPoint(recommendProduct, i2);
            }
        } else if (checkHomePageTestPlanIsA) {
            if (!isShowJdPrice(recommendProduct) || (!isPrice(recommendProduct.getSecondPrice()) && !isPrice(recommendProduct.getUnderLinePrice()) && !isPrice(recommendProduct.getPrice(recommendProduct.gbPrice)))) {
                z = false;
            }
            if (z || recommendProduct.hasChannelUnder()) {
                return;
            }
            addRecommendReason(recommendProduct);
        } else if (recommendProduct.isNewRecommendStyle()) {
            TextView recommendTextView = getRecommendTextView(recommendProduct);
            if (i2 >= getTextWidth(recommendProduct.recomReasonTab, recommendTextView, 10.0f)) {
                this.topRecommendInfoLayout.addView(recommendTextView);
                this.topRecommendInfoLayout.setVisibility(0);
            }
        } else {
            addRecommendReason(recommendProduct);
        }
    }

    private void showSecondPrice(RecommendProduct recommendProduct) {
        if (checkHomePageTestPlanIsA() && recommendProduct.hasChannelUnder()) {
            return;
        }
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.secondPrice.setTextSize(13.0f);
        } else {
            this.secondPrice.setTextSize(11.0f);
        }
        if (isPrice(recommendProduct.getSecondPrice())) {
            String str = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getSecondPrice();
            this.secondPrice.getPaint().setStrikeThruText(false);
            this.secondPrice.setText(str);
            this.secondPrice.setVisibility(0);
            if (!TextUtils.isEmpty(recommendProduct.priceColor)) {
                this.secondPrice.setTextColor(Color.parseColor("#" + recommendProduct.priceColor));
            }
            downPriceIcon(recommendProduct);
            if (this.secondPrice.length() > 7) {
                setTextSize();
            }
        } else if (isPrice(recommendProduct.getUnderLinePrice())) {
            String str2 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getUnderLinePrice();
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.setText(str2);
            this.secondPrice.getPaint().setStrikeThruText(true);
            this.secondPrice.setTextColor(Color.parseColor("#BFBFBF"));
            RecommendFontUtils.changeFont(this.secondPrice, 4098);
            this.secondPrice.setVisibility(0);
            if (this.secondPrice.length() > 7) {
                setTextSize();
            }
        } else if (isPrice(recommendProduct.getPrice(recommendProduct.gbPrice))) {
            String str3 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getPrice(recommendProduct.gbPrice);
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.getPaint().setStrikeThruText(false);
            this.secondPrice.setText(str3);
            this.secondPrice.setVisibility(0);
            if (!TextUtils.isEmpty(recommendProduct.priceColor)) {
                this.secondPrice.setTextColor(Color.parseColor("#" + recommendProduct.priceColor));
            }
            if (!TextUtils.isEmpty(recommendProduct.gbMemCount)) {
                this.priceImage.setText(recommendProduct.gbMemCount);
                UnIconConfigHelper.setTextViewProperties(recommendProduct.icon3, this.priceImage);
                this.priceImage.setVisibility(0);
            }
            if (this.secondPrice.length() > 7) {
                setTextSize();
            }
        }
        if (this.secondPrice.getVisibility() == 0 && isDeepDark()) {
            this.secondPrice.setTextColor(RecommendUtils.normalColor_ECECEC);
        }
    }

    private void showSimilarButton(final RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        float textWidth;
        String str;
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            textWidth = getTextWidth("0", this.price, 19.0f);
        } else {
            textWidth = getTextWidth("0", this.price, 16.0f);
        }
        float widthByDesignValue750 = this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(24);
        if ((widthByDesignValue750 - textWidth) - DPIUtil.getWidthByDesignValue750(37) > 0.0f) {
            this.button.setVisibility(0);
            this.button.setContentDescription("\u770b\u76f8\u4f3c");
            if (this.button.getDrawable() == null || (str = this.buttonUrl) == null || !str.equals(recommendProduct.activityBtnUrl)) {
                String str2 = recommendProduct.activityBtnUrl;
                this.buttonUrl = str2;
                if (TextUtils.isEmpty(str2)) {
                    JDImageUtils.displayImage("res:///" + this.similarButtonDrawableId, this.button);
                } else {
                    JDImageUtils.displayImage(recommendProduct.activityBtnUrl, this.button, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.14
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str3, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                            JDImageUtils.displayImage("res:///" + RecommendProductViewHolder.this.similarButtonDrawableId, RecommendProductViewHolder.this.button);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str3, View view) {
                        }
                    });
                }
            }
            this.button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.15
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        onRecommendClickedListener.onSimilarClick(recommendProduct);
                    }
                }
            });
            return;
        }
        this.button.setVisibility(8);
        if (textWidth > widthByDesignValue750) {
            refitText(this.price, (int) widthByDesignValue750, false, false);
        }
    }

    private void stopLiveAnimation() {
        this.isLottiePlay = false;
        LottieAnimationView lottieAnimationView = this.liveStateView;
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }

    public void refreshCountDownTime(int i2) {
        int currentPosition;
        if (i2 == 0) {
            currentPosition = this.maxTime;
        } else {
            currentPosition = this.maxTime - this.videoPlayView.getCurrentPosition();
        }
        formatTime(currentPosition);
    }

    public void setProduct(RecommendProduct recommendProduct, int i2, ExpoDataStore expoDataStore, final int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        Bitmap bitmap;
        String str;
        String str2;
        if (OKLog.D) {
            OKLog.d(TAG, "setProduct:-->" + i2);
        }
        setFrom(i3);
        this.mFrom = i3;
        if (recommendProduct == null) {
            this.emptyLayout.setBackgroundColor(getColor_FFFFFF());
            this.emptyLayout.setVisibility(0);
            SimpleDraweeView simpleDraweeView = this.imageEntrance;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
            this.noRecommendLayout.setVisibility(8);
            if (i3 == 9) {
                this.cardView.setVisibility(8);
            }
            this.emptyLayout.setOnClickListener(null);
        } else if ("-1".equals(recommendProduct.wareId)) {
            SimpleDraweeView simpleDraweeView2 = this.imageEntrance;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.setVisibility(0);
            } else {
                SimpleDraweeView simpleDraweeView3 = new SimpleDraweeView(this.activity);
                this.imageEntrance = simpleDraweeView3;
                simpleDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
                this.parentLayout.addView(this.imageEntrance, new RelativeLayout.LayoutParams(-1, -1));
            }
            this.emptyLayout.setVisibility(8);
            this.noRecommendLayout.setVisibility(8);
            if (this.imageEntrance.getDrawable() == null || (str2 = this.imageUrl) == null || !TextUtils.equals(str2, recommendProduct.imgUrl)) {
                String str3 = recommendProduct.imgUrl;
                this.imageUrl = str3;
                JDImageUtils.displayImage(str3, this.imageEntrance);
            }
            this.imageEntrance.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendMtaUtils.myJDMyStreetClickMta(RecommendProductViewHolder.this.activity);
                    if (!JDRouterUtil.isRouterJump()) {
                        RecommendProductViewHolder.this.oldJumpMyStreet();
                        return;
                    }
                    final String build = new JDRouterUrlBuilder("JDIndividuationModule", "showRecommendInfo").build();
                    JDRouter.build(RecommendProductViewHolder.this.activity, build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductViewHolder.1.1
                        @Override // com.jingdong.common.unification.router.CallBackListener
                        public void onComplete() {
                            if (OKLog.D) {
                                OKLog.d(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, "\u8df3\u8f6c\u6210\u529f");
                            }
                        }

                        @Override // com.jingdong.common.unification.router.CallBackListener
                        public void onError(int i4) {
                            RecommendProductViewHolder.this.oldJumpMyStreet();
                            RecommendMtaUtils.routerErrorMta(RecommendProductViewHolder.this.activity, i3, build, i4);
                        }
                    }).open();
                    RecommendMtaUtils.routerEnterMta(RecommendProductViewHolder.this.activity, i3, "JDIndividuationModule_showStowSimilarity");
                }
            });
            this.image.setVisibility(8);
        } else {
            this.emptyLayout.setVisibility(8);
            SimpleDraweeView simpleDraweeView4 = this.imageEntrance;
            if (simpleDraweeView4 != null) {
                simpleDraweeView4.setVisibility(8);
            }
            this.noRecommendLayout.setVisibility(8);
            int i4 = RecommendUtils.windowWidthSize;
            if (i4 == 0) {
                this.recommendItemWidth = ((com.jingdong.sdk.utils.DPIUtil.getAppWidth(this.activity) - (DPIUtil.dip2px(12.0f) * 2)) - DPIUtil.dip2px(6.0f)) / 2;
            } else {
                this.recommendItemWidth = ((i4 - (DPIUtil.dip2px(12.0f) * 2)) - DPIUtil.dip2px(6.0f)) / 2;
            }
            this.cardView.setVisibility(0);
            configImage(recommendProduct);
            this.image.setVisibility(0);
            if (recommendProduct.canClipTitleImg) {
                this.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                this.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            if (this.image.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl)) {
                String str4 = recommendProduct.imgUrl;
                this.imageUrl = str4;
                JDImageUtils.displayImage(str4, this.image, jDDisplayImageOptions);
            }
            resetInit();
            if (!TextUtils.isEmpty(recommendProduct.mainVideoId)) {
                JDDisplayImageOptions jDDisplayImageOptions2 = new JDDisplayImageOptions();
                int i5 = R.drawable.recommend_play;
                JDImageUtils.displayImage(recommendProduct.videoBtnUrl3X, this.videoPlay, jDDisplayImageOptions2.showImageForEmptyUri(i5).showImageOnFail(i5));
                this.videoPlay.setVisibility(0);
            }
            recommendProduct.productItemImage = this.image;
            setNameInfo(recommendProduct);
            setJdPrice(recommendProduct);
            if (!TextUtils.isEmpty(recommendProduct.presaleInfo)) {
                this.recommendInfoTv.setVisibility(0);
                this.recommendInfoTv.setText(recommendProduct.presaleInfo);
            } else {
                this.recommendInfoTv.setVisibility(8);
            }
            this.dot.setVisibility((!recommendProduct.isShowDot() || recommendProduct.isMonetized) ? 8 : 0);
            this.leftBottomDot.setVisibility((recommendProduct.isShowDot() && recommendProduct.isMonetized) ? 0 : 8);
            if (!TextUtils.isEmpty(recommendProduct.icon618) && (bitmap = UnIconConfigHelper.getBitmap(recommendProduct.icon618)) != null) {
                this.icon618.setVisibility(0);
                this.icon618.setImageBitmap(bitmap);
            }
            if ("1".equals(recommendProduct.isStoreGoods) && !TextUtils.isEmpty(recommendProduct.storeName)) {
                this.storeLayout.setVisibility(0);
                this.storeNameTv.setVisibility(0);
                this.storeNameTv.setText(recommendProduct.storeName);
                if (!TextUtils.isEmpty(recommendProduct.storeDistanceText)) {
                    this.storeDistanceTv.setText(recommendProduct.storeDistanceText);
                    this.storeDistanceRightArrow.setVisibility(0);
                    this.storeDistanceTv.setVisibility(0);
                } else {
                    this.storeDistanceTv.setVisibility(8);
                    this.storeDistanceRightArrow.setVisibility(8);
                }
            }
            showChannelUnder(recommendProduct);
            if (TextUtils.isEmpty(recommendProduct.popUnit) && TextUtils.isEmpty(recommendProduct.stagesKinds) && isShowJdPrice(recommendProduct) && this.viewType == 0 && (isPrice(recommendProduct.getSecondPrice()) || isPrice(recommendProduct.getUnderLinePrice()) || isPrice(recommendProduct.getPrice(recommendProduct.gbPrice)))) {
                showSecondPrice(recommendProduct);
            }
            if (TextUtils.isEmpty(recommendProduct.stagesKinds)) {
                if (recommendProduct.hasAddCartButton()) {
                    showAddCartButton(recommendProduct, i2, i3, jDDisplayImageOptions);
                } else if (recommendProduct.hasButton()) {
                    showSimilarButton(recommendProduct, jDDisplayImageOptions);
                }
            }
            if (this.viewType == 0) {
                showIconC(recommendProduct);
            }
            if (this.viewType == 37) {
                if (!TextUtils.isEmpty(recommendProduct.numOfSnapingup)) {
                    this.snapUpTV.setVisibility(0);
                    this.snapUpTV.setText(recommendProduct.numOfSnapingup);
                }
                showCashBack(recommendProduct);
            }
            showRecommendReasonOrTag(recommendProduct);
            showBenefit(recommendProduct);
            setNegFeedback(recommendProduct, i2, i3);
            if (this.viewType == 0) {
                setVideoPlayInfo(recommendProduct);
            }
            jumpProductDetail(recommendProduct, i2, i3);
            realExpo(recommendProduct.client_exposal_url, recommendProduct.wareId);
            if ("-1".equals(recommendProduct.wareId) || expoDataStore == null || recommendProduct.wareId == null) {
                return;
            }
            if (reportMtaOnce()) {
                if (recommendProduct.hasBeenExposured) {
                    return;
                }
                recommendProduct.hasBeenExposured = true;
            }
            if (!TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendProduct.exposureJsonSourceValue, recommendProduct.tagIds, this.mFrom, recommendProduct.isBackUp);
            } else if (TextUtils.isEmpty(recommendProduct.exposureSourceValue)) {
            } else {
                expoDataStore.putExpoData(recommendProduct.exposureSourceValue, recommendProduct.isBackUp);
            }
        }
    }

    public void setRecommendItem(RecommendItem recommendItem) {
        this.item = recommendItem;
    }

    public void showVideoPlayView() {
        RelativeLayout relativeLayout;
        try {
            VideoPlayView videoPlayView = this.videoPlayView;
            if (videoPlayView != null && (relativeLayout = videoPlayView.rootLayout) != null) {
                relativeLayout.setBackgroundResource(R.drawable.recommend_product_video_bg);
            }
            this.videoContainer.setVisibility(0);
            this.videoCountDownTime.setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private float getTextWidth(String str, TextView textView, float f2) {
        CharSequence text;
        if (textView == null || (text = textView.getText()) == null) {
            return 0.0f;
        }
        String str2 = str + "#" + text.length() + "#" + f2;
        if (RecommendUtils.textWidthArrayMap.containsKey(str2)) {
            Float f3 = RecommendUtils.textWidthArrayMap.get(str2);
            if (f3 != null) {
                return f3.floatValue();
            }
            return 0.0f;
        }
        float textWidth = getTextWidth(textView, f2);
        RecommendUtils.textWidthArrayMap.put(str2, Float.valueOf(textWidth));
        return textWidth;
    }

    public RecommendProductViewHolder(BaseActivity baseActivity, View view, int i2) {
        this(baseActivity, view);
        this.viewType = i2;
    }
}
