package com.jingdong.common.recommend.forlist;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
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
import com.jingdong.common.recommend.RecommendConstant;
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
import com.jingdong.common.recommend.entity.RecommendTendency;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.AutoWarpTextView;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class Recommend9ProductViewHolder extends BaseRecommendBeltPriceViewHolder {
    private static final String TAG = "com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder";
    SimpleDraweeView activeButton;
    private BaseActivity activity;
    private ImageView adIconImage;
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
    private ImageView delete;
    public String displayPlan;
    View dot;
    LinearLayout emptyLayout;
    private SimpleDraweeView forecastCircle;
    private Handler handler;
    private int hideIconAfterPrice;
    private ImageView icon618;
    SimpleDraweeView iconCUrlSD;
    private int iconCViewWidth;
    SimpleDraweeView image;
    SimpleDraweeView imageEntrance;
    String imageUrl;
    private boolean isEnableDelete;
    private boolean isShowVideoPlayIcon;
    private RecommendItem item;
    private JDDisplayImageOptions jdDisplayImageOptions;
    View leftBottomDot;
    private SimpleDraweeView liveIconView;
    private int mFrom;
    private int maxTime;
    private SimpleDraweeView nationImg;
    private LinearLayout nationLayout;
    private TextView nationName;
    private SimpleDraweeView newUserbgSD;
    RelativeLayout noRecommendLayout;
    TextView noRecommendTv;
    RelativeLayout parentLayout;
    private AppCompatTextView plusWorth;
    private SimpleDraweeView plusWorthBg;
    private RelativeLayout plusWorthLayout;
    private RecommendAutoFitTextView prePrice;
    private final RelativeLayout prePriceLayout;
    private TextView preTitle;
    TextView price;
    private float priceAvailableWidth;
    private ViewGroup priceContainer;
    TextView priceImage;
    private View productPlusBottomView;
    private SimpleDraweeView promotionIcon;
    public String realSecondPrice;
    public String realSecondPriceType;
    Space recommendInfoIITop;
    TextView recommendInfoTv;
    private TextView recommendMarkText;
    TextView rightSecondPriceTV;
    TextView secondPrice;
    private View shadowView;
    private int similarButtonDrawableId;
    private TextView snapUpTV;
    private ImageView storeBottomArrow;
    private TextView storeBottomEnterView;
    private View storeBottomLayout;
    private TextView storeBottomNameTv;
    private ImageView storeDistanceRightArrow;
    private TextView storeDistanceTv;
    private ViewGroup storeLayout;
    private TextView storeNameTv;
    private ImageView tendencyArrow;
    private SimpleDraweeView tendencyIcon;
    private RelativeLayout tendencyLayout;
    private TextView tendencyTitle;
    TextView tvIconC;
    private int viewType;
    private boolean waterFallStrategy;

    public Recommend9ProductViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.isShowVideoPlayIcon = false;
        this.maxTime = 0;
        this.mFrom = -1;
        this.item = null;
        this.hideIconAfterPrice = 0;
        this.realSecondPriceType = "-100";
        this.realSecondPrice = "-100";
        this.iconCViewWidth = 0;
        this.isEnableDelete = true;
        this.activity = baseActivity;
        this.cardView = view;
        this.handler = baseActivity.getHandler();
        this.parentLayout = (RelativeLayout) view.findViewById(R.id.rp_item);
        this.nationLayout = (LinearLayout) view.findViewById(R.id.recommend_brand_nation_ll);
        this.nationImg = (SimpleDraweeView) view.findViewById(R.id.recommend_brand_nation_img);
        this.nationName = (TextView) view.findViewById(R.id.recommend_brand_nation_name);
        this.emptyLayout = (LinearLayout) view.findViewById(R.id.recommend_product_item_empty);
        this.noRecommendLayout = (RelativeLayout) view.findViewById(R.id.recommend_no_layout);
        this.noRecommendTv = (TextView) view.findViewById(R.id.recommend_no_tv);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_imgview);
        this.image = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.name = (AutoWarpTextView) view.findViewById(R.id.recommend_product_item_name);
        this.priceImage = (TextView) view.findViewById(R.id.recommend_product_item_sams_img);
        this.priceContainer = (ViewGroup) view.findViewById(R.id.recommend_price_container);
        TextView textView = (TextView) view.findViewById(R.id.recommend_product_item_price);
        this.price = textView;
        RecommendFontUtils.changeFont(textView, 4099);
        TextView textView2 = (TextView) view.findViewById(R.id.recommend_product_item_sams_price);
        this.secondPrice = textView2;
        RecommendFontUtils.changeFont(textView2, 4099);
        this.button = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_button);
        this.addCar = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_addcar);
        this.activeButton = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_active_button);
        this.recommendInfoTv = (TextView) view.findViewById(R.id.recommend_product_item_recommendinfo);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.iconCUrlSD = (SimpleDraweeView) view.findViewById(R.id.recommend_img_iconC_url);
        TextView textView3 = (TextView) view.findViewById(R.id.recommend_right_second_price);
        this.rightSecondPriceTV = textView3;
        RecommendFontUtils.changeFont(textView3, 4099);
        TextView textView4 = (TextView) view.findViewById(R.id.recommend_tv_iconC);
        this.tvIconC = textView4;
        RecommendFontUtils.changeFont(textView4, 4099);
        this.icon618 = (ImageView) view.findViewById(R.id.recommend_product_item_icon618);
        this.promotionIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_product_promotion_icon);
        this.delete = (ImageView) view.findViewById(R.id.recommend_product_delete);
        RecomLayoutMaxLines recomLayoutMaxLines = (RecomLayoutMaxLines) view.findViewById(R.id.recommend_product_item_info);
        this.topRecommendInfoLayout = recomLayoutMaxLines;
        recomLayoutMaxLines.setMaxRows(1);
        this.plusWorthLayout = (RelativeLayout) view.findViewById(R.id.recommend_product_item_plus_worth_layout);
        this.plusWorthBg = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_plus_worth_bg);
        this.plusWorth = (AppCompatTextView) view.findViewById(R.id.recommend_product_item_plus_worth);
        this.recommendInfoIITop = (Space) view.findViewById(R.id.recommend_product_item_recomInfo_ll_top);
        RecomLayoutMaxLines recomLayoutMaxLines2 = (RecomLayoutMaxLines) view.findViewById(R.id.recommend_product_item_recomInfo_ll);
        this.recommendInfoLayout = recomLayoutMaxLines2;
        recomLayoutMaxLines2.setMaxRows(1);
        this.snapUpTV = (TextView) view.findViewById(R.id.recommend_snap_up_num);
        this.benefitLyout = view.findViewById(R.id.recommend_benefit);
        this.prePriceLayout = (RelativeLayout) view.findViewById(R.id.recommend_forecast_price_layout);
        this.benefitTitleBg = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_bottom);
        this.benefitCircleBg = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_bottom_bg);
        this.benefitTitle = (RecommendAutoFitTextView) view.findViewById(R.id.recommend_forecast_price_title);
        TextView textView5 = (TextView) view.findViewById(R.id.recommend_forecast_price_date);
        this.benefitTime = textView5;
        RecommendFontUtils.changeFont(textView5, 4097);
        this.forecastCircle = (SimpleDraweeView) view.findViewById(R.id.recommend_forecast_price_circle);
        this.benefitTitle_ll = (LinearLayout) view.findViewById(R.id.recommend_forecast_price_title_ll);
        RecommendAutoFitTextView recommendAutoFitTextView = (RecommendAutoFitTextView) view.findViewById(R.id.recommend_forecast_price_tv);
        this.prePrice = recommendAutoFitTextView;
        RecommendFontUtils.changeFont(recommendAutoFitTextView, 4099);
        this.preTitle = (TextView) view.findViewById(R.id.recommend_forecast_price_name);
        this.tendencyLayout = (RelativeLayout) view.findViewById(R.id.recommend_product_item_tendency_layout);
        this.tendencyIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_tendency_icon);
        this.tendencyTitle = (TextView) view.findViewById(R.id.recommend_product_item_tendency_title);
        this.tendencyArrow = (ImageView) view.findViewById(R.id.recommend_product_item_tendency_arrow);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
        this.cashBackView = view.findViewById(R.id.recommend_cashback_layout);
        this.cashBackTitleView = (TextView) view.findViewById(R.id.recommend_cashback_title);
        this.cashBackContentView = (TextView) view.findViewById(R.id.recommend_cashback_price);
        this.liveIconView = (SimpleDraweeView) view.findViewById(R.id.recommend_product_status_live);
        this.storeLayout = (ViewGroup) view.findViewById(R.id.recommend_product_store_layout);
        this.storeNameTv = (TextView) view.findViewById(R.id.recommend_product_store_name_tv);
        this.storeDistanceTv = (TextView) view.findViewById(R.id.recommend_product_store_distance_tv);
        this.storeDistanceRightArrow = (ImageView) view.findViewById(R.id.recommend_product_store_right_arrow);
        this.recommendMarkText = (TextView) view.findViewById(R.id.recommend_debug_mark);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor("#00000000"), Color.parseColor(JDDarkUtil.COLOR_05000000)});
        this.shadowView = view.findViewById(R.id.recommend_product_item_image_shadow);
        this.adIconImage = (ImageView) view.findViewById(R.id.recommend_ad_icon);
        this.productPlusBottomView = view.findViewById(R.id.recommend_product_plus_bottom_bg);
        if (Build.VERSION.SDK_INT >= 16) {
            this.shadowView.setBackground(gradientDrawable);
        } else {
            this.shadowView.setBackgroundDrawable(gradientDrawable);
        }
        this.newUserbgSD = (SimpleDraweeView) view.findViewById(R.id.recommend_new_user_bg);
        this.recommendVideoWidget = (RecommendVideoWidget) this.itemView.findViewById(R.id.recommend_widget_new);
    }

    static /* synthetic */ float access$224(Recommend9ProductViewHolder recommend9ProductViewHolder, float f2) {
        float f3 = recommend9ProductViewHolder.priceAvailableWidth - f2;
        recommend9ProductViewHolder.priceAvailableWidth = f3;
        return f3;
    }

    private void addBenefitPoint(RecommendProduct recommendProduct) {
        TextView textView = getTextView(recommendProduct.benefitPointTab, recommendProduct.benefitPoint);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        textView.setLines(1);
        this.topRecommendInfoLayout.addView(textView);
        this.topRecommendInfoLayout.setVisibility(0);
    }

    private void addFreeShipping(RecommendProduct recommendProduct) {
        TextView textView = getTextView(recommendProduct.freeShippingTab, recommendProduct.freeShipping);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        textView.setLines(1);
        this.topRecommendInfoLayout.addView(textView);
        this.topRecommendInfoLayout.setVisibility(0);
    }

    private void addFreeShippingOrBenefitPoint(RecommendProduct recommendProduct) {
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
        TextView textView = getTextView(recommendProduct.lowestPriceDaysTab, recommendProduct.lowestPriceDays);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        textView.setLines(1);
        this.topRecommendInfoLayout.addView(textView);
        this.topRecommendInfoLayout.setVisibility(0);
    }

    private void addNewStyleRecommend(final RecommendProduct recommendProduct) {
        Drawable drawable;
        if (recommendProduct == null) {
            return;
        }
        TextView recommendTextView = getRecommendTextView(recommendProduct);
        recommendTextView.setGravity(17);
        if ("1".equals(recommendProduct.recomReasonFontStyle)) {
            recommendTextView.setTextSize(1, 12.0f);
            recommendTextView.setPadding(DPIUtil.dip2px(1.0f), DPIUtil.dip2px(1.0f), DPIUtil.dip2px(1.0f), DPIUtil.dip2px(1.0f));
        }
        if (!TextUtils.isEmpty(recommendProduct.recomReasonIcon) && (drawable = UnIconConfigHelper.getDrawable(recommendProduct.recomReasonIcon)) != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            recommendTextView.setCompoundDrawablePadding(com.jingdong.sdk.utils.DPIUtil.dip2px(1.0f));
            recommendTextView.setCompoundDrawables(drawable, null, null, null);
        }
        RecomLayoutMaxLines recomLayoutMaxLines = recommendProduct.isRecomInfoDown ? this.recommendInfoLayout : this.topRecommendInfoLayout;
        recomLayoutMaxLines.addView(recommendTextView);
        recomLayoutMaxLines.setVisibility(0);
        if (RecommendUtils.getRecommendTestSwitch()) {
            recomLayoutMaxLines.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.15
                {
                    Recommend9ProductViewHolder.this = this;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    try {
                        ((ClipboardManager) Recommend9ProductViewHolder.this.activity.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, recommendProduct.recomReason));
                        ToastUtils.showToastInCenter(Recommend9ProductViewHolder.this.activity, "\u63a8\u8350\u7406\u7531\u5df2\u7ecf\u590d\u5236\u5230\u526a\u5207\u677f");
                        return false;
                    } catch (Throwable th) {
                        if (Log.E) {
                            th.printStackTrace();
                        }
                        ToastUtils.showToastInCenter(Recommend9ProductViewHolder.this.activity, "\u7cfb\u7edf\u7248\u672c\u6682\u4e0d\u652f\u6301\u8be5\u529f\u80fd");
                        return false;
                    }
                }
            });
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
                TextView textView = getTextView(recommendProduct.symbolTab, recommendTag.name);
                RecommendFontUtils.changeFont(textView, 4099);
                textView.setPadding(DPIUtil.dip2px(4.0f), DPIUtil.dip2px(3.0f), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(3.0f));
                textView.setLines(1);
                dip2px -= getTextWidth(recommendProduct.symbolTab, textView, RecommendFontUtils.convertRecommendLabelFont(9.0f, this.recommendUIMode)) + DPIUtil.dip2px(15.0f);
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

    /* renamed from: b */
    public /* synthetic */ void c(RecommendTendency recommendTendency, View view) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            onRecommendClickedListener.onRecommendJump(recommendTendency.url, recommendTendency.isOpenApp);
            RecommendMtaUtils.recommendTendencyClickMta(this.itemView.getContext(), recommendTendency.sourceValue);
        }
    }

    public void callRefreshListData() {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (this.mFrom == 9 || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRefresh();
    }

    private boolean canFeedBack(RecommendProduct recommendProduct) {
        return recommendProduct != null && recommendProduct.isCanNegFeedback() && this.isEnableDelete;
    }

    private void configImage(RecommendProduct recommendProduct) {
        SimpleDraweeView simpleDraweeView = this.image;
        if (simpleDraweeView == null || simpleDraweeView.getVisibility() == 8) {
            return;
        }
        if (checkHomePageTestPlanIsA()) {
            if (this.image.getAspectRatio() != 1.0f) {
                this.image.setAspectRatio(1.0f);
                return;
            }
            return;
        }
        float viewRatio = getViewRatio(recommendProduct);
        if (this.image.getAspectRatio() != viewRatio) {
            this.image.setAspectRatio(viewRatio);
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
    }

    private float getAvailableWidth(RecommendProduct recommendProduct) {
        float dip2px;
        float f2 = this.recommendItemWidth;
        if (isUrlIconC()) {
            dip2px = recommendProduct.afterPriceIconWidth + (DPIUtil.dip2px(8.0f) * 2) + (DPIUtil.dip2px(12.0f) * 2);
        } else {
            f2 = (f2 - DPIUtil.dip2px(12.0f)) - getButtonWidth(recommendProduct);
            if (!hasIconC(recommendProduct)) {
                return f2;
            }
            int i2 = 0;
            Drawable drawable = null;
            if (recommendProduct.firstPriceShowDoubleUp()) {
                if ("1".equals(recommendProduct.doublePriceUpMap.doublePriceResourceCodeDegrade)) {
                    setTextIconC(recommendProduct);
                    i2 = RecommendViewUtil.getViewMeasureWidth(this.tvIconC);
                    this.tvIconC.setVisibility(8);
                } else if (!TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceResourceCode)) {
                    if (!TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText)) {
                        RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
                        drawable = UnIconConfigHelper.getTextDrawable(doublePriceUp.doublePriceResourceCode, doublePriceUp.doublePriceText, null, isElder());
                    } else {
                        drawable = UnIconConfigHelper.getDrawable(recommendProduct.doublePriceUpMap.doublePriceResourceCode, isElder());
                    }
                }
            } else if (recommendProduct.isNewPlusCard() && !TextUtils.isEmpty(recommendProduct.icon3)) {
                drawable = UnIconConfigHelper.getDrawable(recommendProduct.icon3);
            } else {
                drawable = UnIconConfigHelper.getDrawable(recommendProduct.iconC, isElder());
            }
            if (drawable != null) {
                i2 = drawable.getIntrinsicWidth();
            }
            this.iconCViewWidth = i2;
            dip2px = i2 + DPIUtil.dip2px(3.0f);
        }
        return f2 - dip2px;
    }

    private int getButtonWidth(RecommendProduct recommendProduct) {
        int dip2px;
        int dip2px2;
        if (recommendProduct.showCartButton()) {
            dip2px = DPIUtil.dip2px(12.0f) + DPIUtil.dip2px(12.0f);
            dip2px2 = DPIUtil.dip2px(24.0f);
        } else if (recommendProduct.showSimilarButton()) {
            dip2px = DPIUtil.dip2px(12.0f);
            dip2px2 = DPIUtil.dip2px(isElder() ? 55.0f : 39.0f);
        } else {
            return DPIUtil.dip2px(12.0f);
        }
        return dip2px + dip2px2;
    }

    private float getPriceSize() {
        if (this.viewType == 37) {
            return 23.0f;
        }
        if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
            return 22.0f;
        }
        return ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode()) ? 19.0f : 18.0f;
    }

    private float getPriceTextSizeScale(RecommendProduct recommendProduct) {
        float f2 = this.priceAvailableWidth;
        float viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.price);
        if (!isUrlIconC()) {
            if (RecommendViewUtil.isVisible(this.rightSecondPriceTV)) {
                int viewMeasureWidth2 = RecommendViewUtil.getViewMeasureWidth(this.rightSecondPriceTV) + DPIUtil.dip2px(8.0f);
                this.rightSecondPriceTV.setVisibility(8);
                f2 += viewMeasureWidth2;
                if (viewMeasureWidth < f2) {
                    return 1.0f;
                }
            }
            this.hideIconAfterPrice = 1;
            f2 += getButtonWidth(recommendProduct) - DPIUtil.dip2px(12.0f);
            if (viewMeasureWidth < f2) {
                return 1.0f;
            }
        }
        return RecommendViewUtil.getTextSizeScale(f2, viewMeasureWidth);
    }

    private float getSecondPriceAvailableWidth(RecommendProduct recommendProduct) {
        int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(28);
        int drawableWidth = RecommendViewUtil.getDrawableWidth(recommendProduct.icon3);
        if (drawableWidth >= widthByDesignValue750) {
            widthByDesignValue750 = drawableWidth;
        }
        return (this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(27)) - widthByDesignValue750;
    }

    private float getSecondPriceTextSize() {
        if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
            return RecommendFontUtils.convertFontSize(11);
        }
        return ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode()) ? 13.0f : 11.0f;
    }

    private float getTextWidth(TextView textView, float f2) {
        Paint paint = new Paint();
        paint.set(textView.getPaint());
        paint.setTextSize(DpiUtil.sp2px(this.activity, f2));
        return paint.measureText(textView.getText().toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float getViewRatio(com.jingdong.common.recommend.entity.RecommendProduct r4) {
        /*
            r3 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r4 != 0) goto L5
            return r0
        L5:
            java.lang.String r1 = r4.longImageScale
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            if (r1 != 0) goto L15
            java.lang.String r1 = r4.longImageScale     // Catch: java.lang.NumberFormatException -> L15
            float r1 = java.lang.Float.parseFloat(r1)     // Catch: java.lang.NumberFormatException -> L15
            goto L16
        L15:
            r1 = 0
        L16:
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 > 0) goto L23
            int r4 = r4.imageurlType
            if (r4 != 0) goto L1f
            goto L22
        L1f:
            r0 = 1061662228(0x3f47ae14, float:0.78)
        L22:
            r1 = r0
        L23:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.getViewRatio(com.jingdong.common.recommend.entity.RecommendProduct):float");
    }

    public void handlePopupWindowProblem(RecommendFeedBackManger recommendFeedBackManger) {
        recommendFeedBackManger.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.8
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                Recommend9ProductViewHolder.this.parentLayout.requestDisallowInterceptTouchEvent(false);
            }
        });
        this.parentLayout.requestDisallowInterceptTouchEvent(true);
    }

    private void initAnimation(RelativeLayout relativeLayout) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(relativeLayout, "scaleX", 0.0f, 1.0f);
        ofFloat.setDuration(200L);
        ofFloat.start();
    }

    public boolean isCacheData(RecommendProduct recommendProduct) {
        return recommendProduct.isBackUp == 1;
    }

    private boolean isEnable() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "liveLottie", "enable", "1"));
    }

    private boolean isExceededAvailableWidth(RecommendProduct recommendProduct) {
        return ((float) RecommendViewUtil.getViewMeasureWidth(this.price)) > this.priceAvailableWidth;
    }

    private boolean isNoShow(int i2) {
        return i2 == 0 || i2 == 9 || i2 == 6;
    }

    private boolean isNormalProduct() {
        return this.viewType == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002a A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isPrice(java.lang.String r7) {
        /*
            r6 = this;
            com.jingdong.common.BaseActivity r0 = r6.activity
            int r1 = com.jingdong.common.recommend.R.string.recommend_product_no_price
            java.lang.String r0 = r0.getString(r1)
            boolean r0 = android.text.TextUtils.equals(r0, r7)
            r1 = 0
            if (r0 == 0) goto L10
            return r1
        L10:
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r2 = 0
            if (r0 != 0) goto L23
            double r4 = java.lang.Double.parseDouble(r7)     // Catch: java.lang.Exception -> L1d
            goto L24
        L1d:
            r7 = move-exception
            java.lang.String r0 = com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.TAG
            com.jingdong.sdk.oklog.OKLog.e(r0, r7)
        L23:
            r4 = r2
        L24:
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 <= 0) goto L2a
            r7 = 1
            return r7
        L2a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.isPrice(java.lang.String):boolean");
    }

    private boolean isShowBenefit(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.benefitTitle)) {
            return (TextUtils.isEmpty(recommendProduct.benefitTime) || TextUtils.isEmpty(recommendProduct.prePrice) || TextUtils.isEmpty(recommendProduct.preTitle)) ? false : true;
        }
        return true;
    }

    private boolean isShowJdPrice(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            return isPrice(recommendProduct.jdPrice);
        }
        return false;
    }

    private boolean isUrlIconC() {
        return this.iconCUrlSD.getVisibility() == 0;
    }

    private void jumpProductDetail(final RecommendProduct recommendProduct, final int i2, final int i3) {
        this.parentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.9
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecommendUtils.isTooFastClick()) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(recommendProduct.sourceValue);
                    if (!TextUtils.isEmpty(recommendProduct.playUrl)) {
                        RecommendVideoWidget recommendVideoWidget = Recommend9ProductViewHolder.this.recommendVideoWidget;
                        if (recommendVideoWidget != null && recommendVideoWidget.isPlaying()) {
                            jSONObject.put("videoplay", 1);
                        } else {
                            jSONObject.put("videoplay", 0);
                        }
                    } else {
                        jSONObject.put("videoplay", "-100");
                    }
                    if (recommendProduct.rootUETJson != null) {
                        String productClickEventId = RecommendMtaUtils.getProductClickEventId(i3);
                        RecommendProduct recommendProduct2 = recommendProduct;
                        RecommendMtaUtils.handleTrackingNode(productClickEventId, recommendProduct2.jdjsonObject, jSONObject, recommendProduct2.rootUETJson, true, true);
                    }
                    recommendProduct.sourceValue = jSONObject.toString();
                    Recommend9ProductViewHolder.this.clickMtaJson(recommendProduct);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
                if (Recommend9ProductViewHolder.this.clickedListener != null) {
                    if (!TextUtils.isEmpty(recommendProduct.client_click_url) && !Recommend9ProductViewHolder.this.isCacheData(recommendProduct)) {
                        Recommend9ProductViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendProduct.client_click_url);
                    }
                    if (recommendProduct.isO2OProduct()) {
                        RecommendMtaUtils.productClickMta(Recommend9ProductViewHolder.this.itemView.getContext(), recommendProduct, Recommend9ProductViewHolder.this.mFrom, 0);
                        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
                        RecommendProduct recommendProduct3 = recommendProduct;
                        onRecommendClickedListener.onRecommendJump(recommendProduct3.channelJumpUrl, recommendProduct3.isOpenApp);
                        return;
                    }
                    String str = null;
                    if (!TextUtils.isEmpty(recommendProduct.popUnit)) {
                        RecommendJumpUtils.gotoMWithUrl(Recommend9ProductViewHolder.this.activity, null, recommendProduct.popUrl);
                    } else if (i2 == -1) {
                        int i4 = i3;
                        if (i4 == 6) {
                            str = RecommendMtaUtils.Shopcart_Compare_Productid;
                        } else if (i4 == 10) {
                            str = RecommendMtaUtils.OrderCenterMyPurchase_FloorProduct;
                        }
                        Recommend9ProductViewHolder.this.clickedListener.onProductClick(recommendProduct, str);
                    } else if (i3 == 9) {
                        Recommend9ProductViewHolder recommend9ProductViewHolder = Recommend9ProductViewHolder.this;
                        recommend9ProductViewHolder.clickedListener.onProductClick(recommendProduct, recommend9ProductViewHolder.item);
                    } else {
                        Recommend9ProductViewHolder.this.clickedListener.onProductClick(recommendProduct);
                    }
                }
            }
        });
    }

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
        this.realSecondPrice = "-100";
        this.realSecondPriceType = "-100";
        this.price.setTextSize(12.0f);
        this.price.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
        this.rightSecondPriceTV.setVisibility(8);
        this.hideIconAfterPrice = 0;
        this.iconCViewWidth = 0;
        this.secondPrice.setTextSize(11.0f);
        RecommendFontUtils.changeFont(this.secondPrice, 4099);
        this.noRecommendTv.setVisibility(0);
        this.secondPrice.setVisibility(8);
        this.priceImage.setVisibility(8);
        this.button.setVisibility(8);
        this.addCar.setVisibility(8);
        this.activeButton.setVisibility(8);
        this.tvIconC.setVisibility(8);
        this.tvIconC.setText("");
        this.tvIconC.setPadding(0, 0, 0, 0);
        this.tvIconC.setBackground(null);
        this.icon618.setVisibility(8);
        this.promotionIcon.setVisibility(8);
        this.delete.setVisibility(8);
        this.benefitLyout.setVisibility(8);
        this.isShowVideoPlayIcon = false;
        this.tendencyLayout.setVisibility(8);
        this.recommendInfoLayout.setVisibility(8);
        this.recommendInfoLayout.removeAllViews();
        this.topRecommendInfoLayout.setVisibility(8);
        this.topRecommendInfoLayout.removeAllViews();
        this.recommendInfoIITop.setVisibility(8);
        this.plusWorthLayout.setVisibility(8);
        this.snapUpTV.setVisibility(8);
        this.cashBackView.setVisibility(8);
        this.recommendInfoTv.setVisibility(8);
        RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
        if (recommendVideoWidget != null) {
            recommendVideoWidget.setVisibility(8);
        }
        this.storeLayout.setVisibility(8);
        this.liveIconView.setVisibility(8);
        this.secondPrice.setTextColor(Color.parseColor("#BFBFBF"));
        this.storeNameTv.setTextColor(isDeepDark() ? Color.parseColor("#ececec") : Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
        this.storeDistanceTv.setTextColor(isDeepDark() ? Color.parseColor("#ececec") : Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
        this.nationLayout.setVisibility(8);
        resetBeltView();
        resetBeltBenefitView();
        if (isDeepDark()) {
            this.similarButtonDrawableId = R.drawable.dyn_uniformrecommend_item_bg_similar_new_dark;
            this.addCarDrawableId = R.drawable.dyn_pd_addshoppingcart_recommend_gray_dark;
        } else {
            this.similarButtonDrawableId = R.drawable.dyn_uniformrecommend_item_bg_similar_new;
            this.addCarDrawableId = R.drawable.dyn_pd_addshoppingcart_recommend_gray;
        }
        this.topRecommendInfoLayout.setOnLongClickListener(null);
        View view = this.itemView;
        if (view instanceof CardView) {
            ((CardView) view).setCardBackgroundColor(getColor_FFFFFF());
        }
        View view2 = this.storeBottomLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    public void setJdPrice(RecommendProduct recommendProduct, boolean z) {
        String showPrice;
        String str;
        String str2;
        if (!TextUtils.isEmpty(recommendProduct.nonJdPriceText)) {
            this.price.setText(recommendProduct.nonJdPriceText);
        } else if (!TextUtils.isEmpty(recommendProduct.presaleText)) {
            String str3 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.presaleText;
            SpannableString spannableString = new SpannableString(str3);
            if (this.viewType == 37) {
                int indexOf = str3.indexOf(OrderISVUtil.MONEY_DECIMAL);
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
                if (indexOf != -1) {
                    spannableString.setSpan(new AbsoluteSizeSpan(23, true), 1, indexOf, 33);
                    spannableString.setSpan(new AbsoluteSizeSpan(15, true), indexOf, spannableString.length(), 33);
                }
            } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), 1, spannableString.length(), 33);
            } else {
                spannableString.setSpan(new AbsoluteSizeSpan(11, true), 0, 1, 33);
            }
            this.price.setText(spannableString);
            if (TextUtils.isEmpty(recommendProduct.presaleTextColor)) {
                return;
            }
            this.price.setTextColor(Color.parseColor("#" + recommendProduct.presaleTextColor));
        } else if (!TextUtils.isEmpty(recommendProduct.hyjPrice)) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.hyjPrice);
            if (this.viewType == 37) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(13, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)), true), 0, 1, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(23, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)), true), 1, spannableStringBuilder.length(), 33);
            } else if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(12), true), 0, 1, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(18), true), 1, spannableStringBuilder.length(), 33);
            } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(19, true), 1, spannableStringBuilder.length(), 33);
            } else {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(18, true), 1, spannableStringBuilder.length(), 33);
            }
            this.price.setText(spannableStringBuilder);
            if (isExceededAvailableWidth(recommendProduct)) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) (getPriceSize() * getPriceTextSizeScale(recommendProduct)), true), 1, spannableStringBuilder.length(), 33);
                this.price.setText(spannableStringBuilder);
            }
        } else {
            RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
            if (doublePriceUp != null) {
                if ("1".equals(doublePriceUp.notSfStyle)) {
                    str2 = recommendProduct.doublePriceUpMap.secondPrice;
                    str = JDDarkUtil.COLOR_FA2C19;
                } else {
                    RecommendProduct.DoublePriceUp doublePriceUp2 = recommendProduct.doublePriceUpMap;
                    String str4 = doublePriceUp2.doublePrice;
                    str = doublePriceUp2.doublePriceColor;
                    str2 = str4;
                }
                showPrice = RecommendUtils.getShowPrice(str2);
                this.price.setTextColor(RecommendViewUtil.dealColor(str, JDDarkUtil.COLOR_FA2C19));
            } else if (recommendProduct.isNewPlusCard()) {
                if (isPrice(recommendProduct.getSecondPrice())) {
                    showPrice = recommendProduct.getSecondPrice();
                } else {
                    showPrice = recommendProduct.getJdPrice();
                }
                this.price.setTextColor(Color.parseColor(isDeepDark() ? JDDarkUtil.COLOR_ECECEC : "#0C246A"));
            } else if (recommendProduct.isNewUserCard()) {
                showPrice = recommendProduct.getSecondPrice();
                this.price.setTextColor(RecommendViewUtil.generateColor("#" + recommendProduct.newCardMap.subPriceColor, "#FFFFFF"));
            } else {
                showPrice = RecommendUtils.getShowPrice(recommendProduct.jdPrice);
            }
            if (!TextUtils.equals(this.activity.getString(R.string.recommend_product_no_price), showPrice)) {
                String str5 = this.activity.getResources().getString(R.string.yangjiao) + showPrice;
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(str5);
                int indexOf2 = str5.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf2 == -1) {
                    indexOf2 = spannableStringBuilder2.length();
                }
                if (this.viewType == 37) {
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(13, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)), true), 0, 1, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(23, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)), true), 1, indexOf2, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(15, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)), true), indexOf2, spannableStringBuilder2.length(), 33);
                } else if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(12), true), 0, 1, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(18), true), 1, indexOf2, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(12), true), indexOf2, spannableStringBuilder2.length(), 33);
                } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(19, true), 1, indexOf2, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(14, true), indexOf2, spannableStringBuilder2.length(), 33);
                } else {
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(18, true), 1, indexOf2, 33);
                }
                this.price.setText(spannableStringBuilder2);
                if (z && isExceededAvailableWidth(recommendProduct)) {
                    int priceSize = (int) (getPriceSize() * getPriceTextSizeScale(recommendProduct));
                    if (priceSize < 14) {
                        priceSize = 14;
                    }
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(priceSize, true), 1, indexOf2, 33);
                    spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(14, true), indexOf2, spannableStringBuilder2.length(), 33);
                    this.price.setText(spannableStringBuilder2);
                    return;
                }
                return;
            }
            if (this.viewType == 37) {
                this.price.setTextSize(15.0f);
            } else {
                this.price.setTextSize(ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode()) ? 14.0f : 12.0f);
            }
            this.price.setText(showPrice);
        }
    }

    private void setLiveStateView(RecommendProduct recommendProduct) {
        if (!TextUtils.isEmpty(recommendProduct.staticIcon)) {
            RecommendViewUtil.visible(this.liveIconView);
            if (this.liveIconView.getLayoutParams() != null) {
                this.liveIconView.getLayoutParams().width = -2;
            }
            JDImageUtils.displayImage(recommendProduct.staticIcon, this.liveIconView, this.jdDisplayImageOptions);
        } else if ("1".equals(recommendProduct.liveSkuStatus)) {
            RecommendViewUtil.visible(this.liveIconView);
            this.liveIconView.setImageResource(R.drawable.recommend_status_live);
        } else {
            RecommendViewUtil.gone(this.liveIconView);
        }
    }

    private void setNameInfo(RecommendProduct recommendProduct) {
        this.name.setMinLines(this.waterFallStrategy ? 1 : 2);
        if (recommendProduct.isSingleRow()) {
            this.name.setMaxLines(1);
        } else {
            this.name.setMaxLines(2);
        }
        if (isElder()) {
            this.name.setTextSize(18.0f);
        } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.name.setTextSize(16.0f);
        } else {
            this.name.setTextSize(13.0f);
        }
        addNameIcons(recommendProduct);
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
        if (canFeedBack(recommendProduct)) {
            this.delete.setVisibility(0);
            this.delete.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.4
                {
                    Recommend9ProductViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty()) {
                        Recommend9ProductViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
                        Recommend9ProductViewHolder.this.handlePopupWindowProblem(recommendFeedBackManger);
                        recommendFeedBackManger.showTipPopupWindow(Recommend9ProductViewHolder.this.activity, Recommend9ProductViewHolder.this.delete, recommendProduct, i2, Recommend9ProductViewHolder.this.clickedListener, 1);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        RecommendProduct recommendProduct2 = recommendProduct;
                        onRecommendClickedListener.onDotMoreMta(1, recommendProduct2 != null ? recommendProduct2.feedbackSourceValue : "");
                    }
                }
            });
        }
        if (canFeedBack(recommendProduct)) {
            this.parentLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.5
                {
                    Recommend9ProductViewHolder.this = this;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return true;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty() || RecommendUtil.dotClick) {
                        Recommend9ProductViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
                        Recommend9ProductViewHolder.this.handlePopupWindowProblem(recommendFeedBackManger);
                        recommendFeedBackManger.showTipPopupWindow(Recommend9ProductViewHolder.this.activity, Recommend9ProductViewHolder.this.delete, recommendProduct, i2, Recommend9ProductViewHolder.this.clickedListener, 2);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
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
        this.noRecommendLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.6
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Recommend9ProductViewHolder.this.noRecommendLayout.setVisibility(8);
                recommendProduct.isShowFeedbackUi = false;
                Recommend9ProductViewHolder.this.callRefreshListData();
            }
        });
        this.noRecommendTv.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.7
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i4;
                Recommend9ProductViewHolder recommend9ProductViewHolder = Recommend9ProductViewHolder.this;
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = recommend9ProductViewHolder.clickedListener;
                if (onRecommendClickedListener != null && (i4 = i2) >= 0) {
                    onRecommendClickedListener.onNoRecommendClick(recommendProduct, i4, "", null);
                    return;
                }
                recommend9ProductViewHolder.noRecommendLayout.setVisibility(8);
                recommendProduct.isShowFeedbackUi = false;
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener2 = Recommend9ProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener2 != null) {
                    onRecommendClickedListener2.onRefresh();
                }
            }
        });
    }

    private void setProductPLUSBottomView(RecommendProduct recommendProduct) {
        if (recommendProduct.isNewPlusCard()) {
            this.productPlusBottomView.setVisibility(0);
            this.productPlusBottomView.setBackgroundResource(isDeepDark() ? R.drawable.recommend_product_plus_bottom_dark_bg : R.drawable.recommend_product_plus_bottom_bg);
            return;
        }
        this.productPlusBottomView.setVisibility(8);
    }

    private void setSecondPrice(float f2, float f3, String str) {
        if (f3 > f2) {
            this.secondPrice.setTextSize(getSecondPriceTextSize() * RecommendViewUtil.getTextSizeScale(f2, f3));
            this.secondPrice.setText(str);
        }
    }

    private void setTextIconC(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText)) {
            return;
        }
        this.tvIconC.setVisibility(0);
        this.tvIconC.setText(recommendProduct.doublePriceUpMap.doublePriceText);
        this.tvIconC.setTextSize(11.0f);
        this.tvIconC.setTextColor(RecommendViewUtil.generateColor(recommendProduct.doublePriceUpMap.doublePriceColor, JDDarkUtil.COLOR_FA2C19));
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
            RecommendVideo videoData = TextUtils.isEmpty(recommendProduct.playUrl) ? null : recommendProduct.getVideoData();
            setVideoViewStubHeight(recommendProduct);
            setVideoData(videoData, recommendProduct.imgUrl);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setVideoViewStubHeight(RecommendProduct recommendProduct) {
        changeVideoViewHeight(getViewRatio(recommendProduct));
    }

    private void showAddCartButton(final RecommendProduct recommendProduct, final int i2, final int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        if (this.hideIconAfterPrice == 1) {
            this.addCar.setVisibility(8);
            return;
        }
        this.addCar.setVisibility(0);
        this.addCar.setContentDescription("\u52a0\u8d2d\u7269\u8f66");
        if (this.addCar.getDrawable() == null || (str = this.addCarUrl) == null || !str.equals(recommendProduct.activityBtnUrl)) {
            String str2 = recommendProduct.activityBtnUrl;
            this.addCarUrl = str2;
            if (TextUtils.isEmpty(str2)) {
                JDImageUtils.displayImage("res:///" + this.addCarDrawableId, this.addCar);
            } else {
                JDImageUtils.displayImage(recommendProduct.activityBtnUrl, this.addCar, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.10
                    {
                        Recommend9ProductViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str3, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.dyn_pd_addshoppingcart_recommend_gray, Recommend9ProductViewHolder.this.addCar);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str3, View view) {
                    }
                });
            }
        }
        this.addCar.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.11
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
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
        if ("1".equals(recommendProduct.hideCart)) {
            this.addCar.setClickable(false);
        } else {
            this.addCar.setClickable(true);
        }
    }

    private void showBenefit(RecommendProduct recommendProduct) {
        boolean z;
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
            layoutParams.width = this.recommendItemWidth - com.jingdong.sdk.utils.DPIUtil.dip2px(55.0f);
            layoutParams.leftMargin = DPIUtil.getWidthByDesignValue750(9);
            layoutParams.rightMargin = DPIUtil.getWidthByDesignValue750(9);
            this.benefitCircleBg.setVisibility(0);
            this.benefitCircleBg.getLayoutParams().width = this.recommendItemWidth;
            this.benefitCircleBg.requestLayout();
            if (TextUtils.isEmpty(recommendProduct.benefitImageUrl)) {
                JDImageUtils.displayImage("res:///" + i3, this.benefitCircleBg);
            } else {
                JDImageUtils.displayImage(recommendProduct.benefitImageUrl, this.benefitCircleBg, showImageOnFail, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.13
                    {
                        Recommend9ProductViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_forecast_price_bg, Recommend9ProductViewHolder.this.benefitCircleBg);
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
                JDImageUtils.displayImage(recommendProduct.benefitImageUrl, this.benefitTitleBg, showImageOnFail2, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.14
                    {
                        Recommend9ProductViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_forecast_price_bottom, Recommend9ProductViewHolder.this.benefitTitleBg);
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

    public void showFeedBackView(RecommendProduct recommendProduct, int i2) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            RecommendUtil.dotClick = true;
            onRecommendClickedListener.onShowFeedbackUi(recommendProduct, i2);
        }
    }

    private void showIcon618(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        if ("2".equals(recommendProduct.icon618Type)) {
            this.promotionIcon.setVisibility(0);
            JDImageUtils.displayImage(recommendProduct.icon618, this.promotionIcon, jDDisplayImageOptions);
            return;
        }
        Bitmap bitmap = UnIconConfigHelper.getBitmap(recommendProduct.icon618);
        if (bitmap != null) {
            this.icon618.setVisibility(0);
            this.icon618.setImageBitmap(bitmap);
        }
    }

    private void showIconC(RecommendProduct recommendProduct) {
        String str;
        String str2;
        if (this.iconCViewWidth > 0) {
            boolean z = false;
            String str3 = null;
            if (recommendProduct.firstPriceShowDoubleUp()) {
                if (!"1".equals(recommendProduct.doublePriceUpMap.doublePriceResourceCodeDegrade) || TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText)) {
                    RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
                    str3 = doublePriceUp.doublePriceResourceCode;
                    str2 = doublePriceUp.doublePriceText;
                } else {
                    z = true;
                    str2 = null;
                }
            } else {
                if (recommendProduct.isNewPlusCard() && isPrice(recommendProduct.getSecondPrice()) && !TextUtils.isEmpty(recommendProduct.icon3)) {
                    str = recommendProduct.icon3;
                } else {
                    str = recommendProduct.iconC;
                }
                str3 = str;
                str2 = null;
            }
            if (!recommendProduct.isNewUserCard()) {
                if (z) {
                    setTextIconC(recommendProduct);
                } else {
                    RecommendViewUtil.showIcon(this.tvIconC, str3, str2, isElder());
                }
            }
        } else {
            this.tvIconC.setVisibility(8);
        }
        if (this.hideIconAfterPrice > 0) {
            this.iconCUrlSD.setVisibility(8);
        }
    }

    private void showNationInfo(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        if (TextUtils.isEmpty(recommendProduct.nationImg) || TextUtils.isEmpty(recommendProduct.nationName)) {
            return;
        }
        this.nationLayout.setVisibility(0);
        if (this.viewType == 0) {
            ((RelativeLayout.LayoutParams) this.nationLayout.getLayoutParams()).topMargin = this.recommendItemWidth - DPIUtil.dip2px(7.0f);
        }
        JDImageUtils.displayImage(recommendProduct.nationImg, this.nationImg, jDDisplayImageOptions);
        if (recommendProduct.nationName.length() > 4) {
            this.nationName.setText(recommendProduct.nationName.substring(0, 4) + "...");
            return;
        }
        this.nationName.setText(recommendProduct.nationName);
    }

    private void showNewUserView(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        int dip2px;
        int i2;
        int i3;
        if (recommendProduct.isNewUserCard()) {
            RecommendViewUtil.visible(this.newUserbgSD);
            dip2px = DPIUtil.dip2px(8.0f);
            i3 = DPIUtil.dip2px(5.0f);
            JDImageUtils.displayImage(recommendProduct.newCardMap.backgroundImg, this.newUserbgSD, jDDisplayImageOptions);
            if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
                this.newUserbgSD.getLayoutParams().height = DPIUtil.dip2px(45.0f);
            } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                this.newUserbgSD.getLayoutParams().height = DPIUtil.dip2px(42.0f);
            } else {
                this.newUserbgSD.getLayoutParams().height = DPIUtil.dip2px(40.0f);
            }
            i2 = dip2px;
        } else {
            dip2px = DPIUtil.dip2px(2.0f);
            RecommendViewUtil.gone(this.newUserbgSD);
            i2 = 0;
            i3 = 0;
        }
        this.priceContainer.setPadding(dip2px, i3, i2, 0);
    }

    private void showPLUSWorthInfo(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        List<RecommendLabel> list;
        if (recommendProduct.tendency != null || (list = recommendProduct.worthList) == null || list.isEmpty()) {
            return;
        }
        this.plusWorthLayout.setVisibility(0);
        RecommendLabel recommendLabel = recommendProduct.worthList.get(0);
        JDImageUtils.displayImage(recommendLabel.resourceUrl, this.plusWorthBg, jDDisplayImageOptions);
        this.plusWorth.setText(recommendLabel.labelTitle);
    }

    private void showRecommendReasonOrTag(RecommendProduct recommendProduct) {
        boolean z;
        List<RecommendTag> list;
        boolean checkHomePageTestPlanIsA = checkHomePageTestPlanIsA();
        int i2 = this.recommendItemWidth;
        if (!TextUtils.isEmpty(recommendProduct.recomReasonTab) && !TextUtils.isEmpty(recommendProduct.recomReason)) {
            if (recommendProduct.isNewRecommendStyle()) {
                addNewStyleRecommend(recommendProduct);
            } else {
                addRecommendReason(recommendProduct);
            }
        } else if (!TextUtils.isEmpty(recommendProduct.symbolTab)) {
            if (!checkHomePageTestPlanIsA && (list = recommendProduct.symbolList) != null && !list.isEmpty()) {
                addRecommendTags(recommendProduct, i2);
            }
        } else {
            List<RecommendLabel> list2 = recommendProduct.labelList;
            if (list2 != null) {
                if (!list2.isEmpty()) {
                    addNRecommendLabel(list2, recommendProduct, i2 - DPIUtil.dip2px(20.0f));
                    z = true;
                    if (!recommendProduct.isMtaValueChanged && !z) {
                        RecommendUtils.handleLabelValueMtaJson("-100", recommendProduct);
                        recommendProduct.isMtaValueChanged = true;
                    }
                    if (RecommendViewUtil.isVisible(this.recommendInfoLayout) || RecommendViewUtil.isVisible(this.secondPrice)) {
                    }
                    this.recommendInfoIITop.setVisibility(0);
                    return;
                }
            } else if (!checkHomePageTestPlanIsA) {
                addFreeShippingOrBenefitPoint(recommendProduct);
            }
        }
        z = false;
        if (!recommendProduct.isMtaValueChanged) {
            RecommendUtils.handleLabelValueMtaJson("-100", recommendProduct);
            recommendProduct.isMtaValueChanged = true;
        }
        if (RecommendViewUtil.isVisible(this.recommendInfoLayout)) {
        }
    }

    private void showRightSecondPrice(RecommendProduct recommendProduct) {
        this.realSecondPrice = RecommendViewUtil.setRightSecondPrice(this.rightSecondPriceTV, recommendProduct, this.priceUnit);
        RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
        if (doublePriceUp != null) {
            this.realSecondPriceType = doublePriceUp.secPriceType;
        }
        if (RecommendViewUtil.isVisible(this.rightSecondPriceTV)) {
            this.priceAvailableWidth -= RecommendViewUtil.getViewMeasureWidth(this.rightSecondPriceTV) + DPIUtil.dip2px(8.0f);
        }
    }

    private void showSecondPrice(RecommendProduct recommendProduct) {
        int viewMeasureWidth;
        String str;
        if (checkHomePageTestPlanIsA() && recommendProduct.hasChannelUnder()) {
            return;
        }
        if (!TextUtils.isEmpty(recommendProduct.secPriceType)) {
            this.realSecondPriceType = recommendProduct.secPriceType;
        }
        this.secondPrice.setTextSize(getSecondPriceTextSize());
        RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
        if (doublePriceUp != null) {
            if (!TextUtils.isEmpty(doublePriceUp.jdPriceColor)) {
                if (!TextUtils.isEmpty(recommendProduct.doublePriceUpMap.secondPrice)) {
                    str = recommendProduct.doublePriceUpMap.secondPrice;
                } else {
                    str = recommendProduct.jdPrice;
                }
                this.realSecondPrice = str;
                this.secondPrice.setText(this.priceUnit + str);
                this.secondPrice.setCompoundDrawables(null, null, null, null);
                this.secondPrice.setVisibility(0);
                this.secondPrice.setTextColor(RecommendViewUtil.generateColor(recommendProduct.doublePriceUpMap.jdPriceColor, JDDarkUtil.COLOR_CCCCCC));
                this.realSecondPriceType = recommendProduct.doublePriceUpMap.secPriceType;
            }
        } else if (recommendProduct.isNewUserCard()) {
            this.realSecondPrice = recommendProduct.getJdPrice();
            this.secondPrice.setText(this.priceUnit + this.realSecondPrice);
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.setVisibility(0);
            this.secondPrice.setTextColor(RecommendViewUtil.generateColor("#" + recommendProduct.newCardMap.jdPriceColor, "#FFFFFF"));
        } else if (isPrice(recommendProduct.getSecondPrice())) {
            if (!recommendProduct.isNewPlusCard()) {
                String showPrice = RecommendUtils.getShowPrice(recommendProduct.icon3Price);
                this.realSecondPrice = showPrice;
                this.secondPrice.getPaint().setStrikeThruText(false);
                this.secondPrice.setText(this.priceUnit + showPrice);
                this.secondPrice.setVisibility(0);
                if (!TextUtils.isEmpty(recommendProduct.priceColor)) {
                    this.secondPrice.setTextColor(Color.parseColor("#" + recommendProduct.priceColor));
                }
                downPriceIcon(recommendProduct);
                if (this.secondPrice.length() > 7) {
                    setSecondPrice(getSecondPriceAvailableWidth(recommendProduct), RecommendViewUtil.getViewMeasureWidth(this.secondPrice), showPrice);
                }
            }
        } else if (isPrice(recommendProduct.getUnderLinePrice())) {
            this.realSecondPrice = recommendProduct.getUnderLinePrice();
            String str2 = this.activity.getResources().getString(R.string.yangjiao) + this.realSecondPrice;
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.setText(str2);
            this.secondPrice.getPaint().setStrikeThruText(true);
            this.secondPrice.setTextColor(Color.parseColor("#BFBFBF"));
            RecommendFontUtils.changeFont(this.secondPrice, 4098);
            this.secondPrice.setVisibility(0);
            if (this.secondPrice.length() > 7) {
                setSecondPrice(this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(27), RecommendViewUtil.getViewMeasureWidth(this.secondPrice), str2);
            }
        } else if (isPrice(recommendProduct.getPrice(recommendProduct.gbPrice))) {
            this.realSecondPrice = recommendProduct.getPrice(recommendProduct.gbPrice);
            String str3 = this.activity.getResources().getString(R.string.yangjiao) + this.realSecondPrice;
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
                if (!TextUtils.isEmpty(recommendProduct.gbMemCount)) {
                    viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.secondPrice) + RecommendViewUtil.getViewMeasureWidth(this.priceImage);
                } else {
                    viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.secondPrice);
                }
                float f2 = viewMeasureWidth;
                float widthByDesignValue750 = this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(27);
                if (f2 > widthByDesignValue750) {
                    this.priceImage.setVisibility(8);
                    setSecondPrice(widthByDesignValue750, f2, str3);
                }
            }
        }
        if (this.secondPrice.getVisibility() == 0 && isDeepDark()) {
            this.secondPrice.setTextColor(RecommendUtils.normalColor_ECECEC);
        }
    }

    private void showSimilarButton(final RecommendProduct recommendProduct) {
        if (this.hideIconAfterPrice == 1) {
            this.button.setVisibility(8);
            return;
        }
        this.button.setVisibility(0);
        if (this.button.getLayoutParams() != null) {
            if (isElder()) {
                this.button.getLayoutParams().height = DpiUtil.dip2px(this.button.getContext(), 30.0f);
                this.button.getLayoutParams().width = DpiUtil.dip2px(this.button.getContext(), 55.0f);
            } else {
                this.button.getLayoutParams().height = DpiUtil.dip2px(this.button.getContext(), 21.0f);
                this.button.getLayoutParams().width = DpiUtil.dip2px(this.button.getContext(), 39.0f);
            }
        }
        this.button.setContentDescription("\u770b\u76f8\u4f3c");
        this.button.setBackgroundResource(this.similarButtonDrawableId);
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.12
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener != null) {
                    onRecommendClickedListener.onSimilarClick(recommendProduct);
                }
            }
        });
    }

    private void showStoreBottomInfo(RecommendProduct recommendProduct) {
        final RecommendTendency recommendTendency;
        if (recommendProduct == null || (recommendTendency = recommendProduct.tendency) == null) {
            return;
        }
        if (this.storeBottomLayout == null) {
            View inflate = ((ViewStub) this.itemView.findViewById(R.id.recommend_store_bottom)).inflate();
            this.storeBottomLayout = inflate;
            this.storeBottomNameTv = (TextView) inflate.findViewById(R.id.recommend_store_bottom_title);
            this.storeBottomArrow = (ImageView) this.storeBottomLayout.findViewById(R.id.recommend_store_bottom_arrow);
            this.storeBottomEnterView = (TextView) this.storeBottomLayout.findViewById(R.id.recommend_store_bottom_enter);
        }
        this.storeBottomLayout.setVisibility(0);
        this.storeBottomNameTv.setText(recommendTendency.title);
        this.storeBottomNameTv.setTextColor(RecommendViewUtil.dealColor(recommendTendency.txtColor, JDDarkUtil.COLOR_FA2C19));
        this.storeBottomEnterView.setTextColor(isDeepDark() ? Color.parseColor("#E6E6E6") : Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        this.storeBottomArrow.setColorFilter(isDeepDark() ? Color.parseColor("#E6E6E6") : Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        this.storeBottomLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.3
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = Recommend9ProductViewHolder.this.clickedListener;
                if (onRecommendClickedListener != null) {
                    RecommendTendency recommendTendency2 = recommendTendency;
                    onRecommendClickedListener.onRecommendJump(recommendTendency2.url, recommendTendency2.isOpenApp);
                    RecommendMtaUtils.recommendTendencyClickMta(Recommend9ProductViewHolder.this.itemView.getContext(), recommendTendency.sourceValue);
                }
            }
        });
    }

    private void showTendencyInfo(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        final RecommendTendency recommendTendency;
        int[] iArr;
        if (recommendProduct == null || (recommendTendency = recommendProduct.tendency) == null) {
            return;
        }
        this.tendencyLayout.setVisibility(0);
        JDImageUtils.displayImage(recommendTendency.icon, this.tendencyIcon, jDDisplayImageOptions, false, null, null);
        this.tendencyTitle.setText(recommendTendency.title);
        this.tendencyTitle.setTextColor(RecommendViewUtil.generateColor(recommendTendency.txtColor, "#4D4D4D"));
        this.tendencyArrow.setColorFilter(RecommendViewUtil.generateColor(recommendTendency.txtColor, "#4D4D4D"));
        ArrayList<String> arrayList = recommendTendency.bgColor;
        if (arrayList == null || arrayList.size() <= 1) {
            iArr = new int[]{Color.parseColor("#F7F7F7"), Color.parseColor("#F7F7F7")};
        } else {
            iArr = new int[recommendTendency.bgColor.size()];
            for (int i2 = 0; i2 < recommendTendency.bgColor.size(); i2++) {
                iArr[i2] = Color.parseColor(recommendTendency.bgColor.get(i2));
            }
        }
        RecommendViewUtil.generateGradientDrawableLR(this.tendencyLayout, com.jingdong.sdk.utils.DPIUtil.dip2px(10.0f), iArr);
        this.tendencyLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.a
            {
                Recommend9ProductViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Recommend9ProductViewHolder.this.c(recommendTendency, view);
            }
        });
    }

    private void showUrlIconC(final RecommendProduct recommendProduct) {
        if (recommendProduct.isNewUserCard()) {
            RecommendViewUtil.gone(this.tvIconC);
            RecommendViewUtil.visible(this.iconCUrlSD);
            JDImageUtils.displayImage(recommendProduct.newCardMap.icon, this.iconCUrlSD, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.2
                {
                    Recommend9ProductViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    recommendProduct.afterPriceIconWidth = (int) ((bitmap.getWidth() / 3) * DPIUtil.getDensity());
                    Recommend9ProductViewHolder.access$224(Recommend9ProductViewHolder.this, recommendProduct.afterPriceIconWidth);
                    Recommend9ProductViewHolder.this.setJdPrice(recommendProduct, true);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            });
            return;
        }
        RecommendViewUtil.visible(this.tvIconC);
        RecommendViewUtil.gone(this.iconCUrlSD);
    }

    public boolean hasIconC(RecommendProduct recommendProduct) {
        return !recommendProduct.isNewUserCard() && ((!TextUtils.isEmpty(recommendProduct.iconC) && TextUtils.isEmpty(recommendProduct.gbPrice)) || ((recommendProduct.firstPriceShowDoubleUp() && !(TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceResourceCode) && TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText))) || (recommendProduct.isNewPlusCard() && !TextUtils.isEmpty(recommendProduct.icon3))));
    }

    public void refreshCountDownTime(int i2) {
    }

    public void setDisplayPlan(String str) {
        this.displayPlan = str;
    }

    public void setEnableDelete(boolean z) {
        this.isEnableDelete = z;
    }

    public void setProduct(RecommendProduct recommendProduct, int i2, ExpoDataStore expoDataStore, final int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        RecommendProduct.DoublePriceUp doublePriceUp;
        String str2;
        String str3;
        this.jdDisplayImageOptions = jDDisplayImageOptions;
        this.mFrom = i3;
        setFrom(i3);
        getRecommendUIMode();
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
            if (this.imageEntrance.getDrawable() == null || (str3 = this.imageUrl) == null || !TextUtils.equals(str3, recommendProduct.imgUrl)) {
                String str4 = recommendProduct.imgUrl;
                this.imageUrl = str4;
                JDImageUtils.displayImage(str4, this.imageEntrance);
            }
            this.imageEntrance.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.1
                {
                    Recommend9ProductViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendMtaUtils.myJDMyStreetClickMta(Recommend9ProductViewHolder.this.activity);
                    if (!JDRouterUtil.isRouterJump()) {
                        Recommend9ProductViewHolder.this.oldJumpMyStreet();
                        return;
                    }
                    final String build = new JDRouterUrlBuilder("JDIndividuationModule", "showRecommendInfo").build();
                    JDRouter.build(Recommend9ProductViewHolder.this.activity, build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.recommend.forlist.Recommend9ProductViewHolder.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // com.jingdong.common.unification.router.CallBackListener
                        public void onComplete() {
                            if (OKLog.D) {
                                OKLog.d(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, "\u8df3\u8f6c\u6210\u529f");
                            }
                        }

                        @Override // com.jingdong.common.unification.router.CallBackListener
                        public void onError(int i4) {
                            Recommend9ProductViewHolder.this.oldJumpMyStreet();
                            RecommendMtaUtils.routerErrorMta(Recommend9ProductViewHolder.this.activity, i3, build, i4);
                        }
                    }).open();
                    RecommendMtaUtils.routerEnterMta(Recommend9ProductViewHolder.this.activity, i3, "JDIndividuationModule_showStowSimilarity");
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
            if (this.image.getWidth() != 0) {
                this.recommendItemWidth = this.image.getWidth();
            } else if (this.mFrom == 9) {
                this.recommendItemWidth = RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext()) + com.jd.lib.un.basewidget.widget.simple.e.a.a(2.0f);
            } else {
                this.recommendItemWidth = RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext());
            }
            this.nameViewWidth = this.recommendItemWidth - com.jd.lib.un.basewidget.widget.simple.e.a.a(20.0f);
            this.cardView.setVisibility(0);
            RecommendUtils.setRecommendDebugMark(this.recommendMarkText, recommendProduct.showAdDot(), recommendProduct.showMonetizedDot());
            configImage(recommendProduct);
            this.image.setVisibility(0);
            if (recommendProduct.canClipTitleImg) {
                this.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                this.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            if (!TextUtils.isEmpty(recommendProduct.mainVideoImg)) {
                str = recommendProduct.mainVideoImg;
            } else {
                str = recommendProduct.imgUrl;
            }
            if (this.image.getDrawable() == null || (str2 = this.imageUrl) == null || !str2.equals(str)) {
                this.imageUrl = str;
                JDImageUtils.displayImage(str, this.image, jDDisplayImageOptions);
            }
            resetInit();
            recommendProduct.productItemImage = this.image;
            setNameInfo(recommendProduct);
            showUrlIconC(recommendProduct);
            this.priceAvailableWidth = getAvailableWidth(recommendProduct);
            showNewUserView(recommendProduct, jDDisplayImageOptions);
            if (isShowJdPrice(recommendProduct) && isNormalProduct()) {
                if (recommendProduct.isSecondPriceNewStyle()) {
                    showRightSecondPrice(recommendProduct);
                } else if (isPrice(recommendProduct.getSecondPrice()) || isPrice(recommendProduct.getUnderLinePrice()) || isPrice(recommendProduct.getPrice(recommendProduct.gbPrice)) || ((recommendProduct.isNewUserCard() && isPrice(recommendProduct.getJdPrice())) || ((doublePriceUp = recommendProduct.doublePriceUpMap) != null && !"1".equals(doublePriceUp.doublePriceUpNewFlag)))) {
                    showSecondPrice(recommendProduct);
                }
            }
            setJdPrice(recommendProduct, !isUrlIconC());
            setProductPLUSBottomView(recommendProduct);
            this.dot.setVisibility((!recommendProduct.isShowDot() || recommendProduct.isMonetized) ? 8 : 0);
            this.leftBottomDot.setVisibility((recommendProduct.isShowDot() && recommendProduct.isMonetized) ? 0 : 8);
            setLiveStateView(recommendProduct);
            if ("1".equals(recommendProduct.isStoreGoods) && !TextUtils.isEmpty(recommendProduct.storeName)) {
                this.storeLayout.setVisibility(0);
                this.storeNameTv.setVisibility(0);
                this.storeNameTv.setText(recommendProduct.storeName);
                if (!TextUtils.isEmpty(recommendProduct.storeDistanceText)) {
                    this.storeDistanceTv.setText(recommendProduct.storeDistanceText);
                    this.storeDistanceRightArrow.setVisibility(0);
                    if (isDeepDark()) {
                        this.storeDistanceRightArrow.setColorFilter(-1);
                    } else {
                        this.storeDistanceRightArrow.setColorFilter(Color.parseColor("#8C8C8C"));
                    }
                    this.storeDistanceTv.setVisibility(0);
                } else {
                    this.storeDistanceTv.setVisibility(8);
                    this.storeDistanceRightArrow.setVisibility(8);
                }
            }
            if (!TextUtils.isEmpty(recommendProduct.buttonAreaImgUrl)) {
                this.activeButton.setVisibility(0);
                JDImageUtils.displayImage(recommendProduct.buttonAreaImgUrl, this.activeButton);
            }
            RecommendTendency recommendTendency = recommendProduct.tendency;
            if (recommendTendency != null) {
                if (recommendTendency.isStore()) {
                    showStoreBottomInfo(recommendProduct);
                } else {
                    showTendencyInfo(recommendProduct, jDDisplayImageOptions);
                }
            }
            if (recommendProduct.showCartButton()) {
                showAddCartButton(recommendProduct, i2, i3, jDDisplayImageOptions);
            } else if (recommendProduct.showSimilarButton()) {
                showSimilarButton(recommendProduct);
            }
            if (isNormalProduct()) {
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
            showPLUSWorthInfo(recommendProduct, jDDisplayImageOptions);
            setNegFeedback(recommendProduct, i2, i3);
            if (isNormalProduct()) {
                setVideoPlayInfo(recommendProduct);
            }
            try {
                if (recommendProduct.rootUETJson != null) {
                    JSONObject jSONObject = new JSONObject(recommendProduct.exposureJsonSourceValue);
                    RecommendMtaUtils.handleTrackingNode(expoDataStore.getEventid(), recommendProduct.jdjsonObject, jSONObject, recommendProduct.rootUETJson, true, true);
                    recommendProduct.exposureJsonSourceValue = jSONObject.toString();
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            jumpProductDetail(recommendProduct, i2, i3);
            if (!isCacheData(recommendProduct)) {
                realExpo(recommendProduct.client_exposal_url, recommendProduct.wareId);
            }
            if (isShowNewBeltBenefit(recommendProduct)) {
                setRecommendItemWidth(this.recommendItemWidth);
                showBeltBenefitView(recommendProduct, jDDisplayImageOptions);
            } else if ("2".equals(recommendProduct.beltType) && isNotEmpty(recommendProduct)) {
                showBeltView(recommendProduct, jDDisplayImageOptions, false);
            } else if ("1".equals(recommendProduct.beltType) && isNotEmpty(recommendProduct)) {
                showBeltView(recommendProduct, jDDisplayImageOptions, true);
            } else if (!TextUtils.isEmpty(recommendProduct.presaleInfo)) {
                this.recommendInfoTv.setVisibility(0);
                this.recommendInfoTv.setText(recommendProduct.presaleInfo);
            } else if (isShowBenefit(recommendProduct)) {
                showBenefit(recommendProduct);
            } else {
                showNationInfo(recommendProduct, jDDisplayImageOptions);
            }
            if (!TextUtils.isEmpty(recommendProduct.icon618)) {
                showIcon618(recommendProduct, jDDisplayImageOptions);
            }
            RecommendViewUtil.showUnIcon(this.adIconImage, recommendProduct.iconAd);
            try {
                if (!RecommendViewUtil.isVisible(this.secondPrice) && !RecommendViewUtil.isVisible(this.rightSecondPriceTV)) {
                    this.realSecondPriceType = "-100";
                    this.realSecondPrice = "-100";
                }
                recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_SUBPRICE_YPE, this.realSecondPriceType);
                recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_SUBREAL_PRICE, this.realSecondPrice);
                JSONObject jSONObject2 = recommendProduct.appendMtaJson;
                String str5 = RecommendConstant.RECOM_TITLE_LINE;
                StringBuilder sb = new StringBuilder();
                sb.append(recommendProduct.isSingleRow() ? 1 : 2);
                sb.append("");
                jSONObject2.put(str5, sb.toString());
                recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_FEEDS_TYPE, "2");
                recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_SKU_HEIGHT, "-100");
                JSONObject jSONObject3 = recommendProduct.appendMtaJson_real;
                String str6 = RecommendConstant.RECOM_TITLE_LINE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(recommendProduct.isSingleRow() ? 1 : 2);
                sb2.append("");
                jSONObject3.put(str6, sb2.toString());
                recommendProduct.appendMtaJson_real.put(RecommendConstant.RECOM_FEEDS_TYPE, "2");
                dealExpoSourceValue(recommendProduct);
            } catch (Exception unused) {
            }
            if ("-1".equals(recommendProduct.wareId) || expoDataStore == null || recommendProduct.wareId == null) {
                return;
            }
            if (reportMtaOnce()) {
                if (recommendProduct.hasBeenExposured) {
                    return;
                }
                recommendProduct.hasBeenExposured = true;
            }
            if (recommendProduct.isBackUp == 1) {
                return;
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

    public void setWaterFallStrategy(boolean z) {
        this.waterFallStrategy = z;
    }

    public void showVideoPlayView(boolean z) {
        try {
            RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
            if (recommendVideoWidget != null) {
                if (z) {
                    recommendVideoWidget.setVisibility(8);
                } else {
                    recommendVideoWidget.setVisibility(0);
                }
                if (isDeepDark()) {
                    this.recommendVideoWidget.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_1D1B1B));
                } else {
                    this.recommendVideoWidget.setBackgroundResource(R.drawable.recommend_product_video_bg);
                }
            }
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

    public Recommend9ProductViewHolder(BaseActivity baseActivity, View view, int i2) {
        this(baseActivity, view);
        this.viewType = i2;
    }
}
