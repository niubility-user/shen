package com.jingdong.app.mall.mylib.CouponUnit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.mylib.CouponUnit.Dynamic.DynamicFloor;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.unification.uniwidget.JDLottieAnimationView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.lib.couponunit.R;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.util.MD5;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CommonCouponView extends FrameLayout {
    public static final String ONBTNCLICK = "onBtnClick";
    public static final String ONCOUPONCLICK = "onCouponClick";
    public static final String ONCOUPONLONGCLICK = "onCouponLongClick";
    protected TextView baitiaoTip;
    private TextView brandVipIcon;
    protected View canOverlay;
    protected View canSell;
    protected View canShare;
    protected View circularSeal;
    protected RelativeLayout contentLayout;
    protected Context context;
    protected CouponEntity coupon;
    protected RelativeLayout couponCententLayout;
    protected AppCompatTextView couponDetailTime;
    private LinearLayout couponLimitLayout;
    protected CouponListener couponListener;
    protected TextView couponType;
    protected ImageView couponUnderOverTime;
    protected AppCompatTextView couponValue;
    protected JSONObject currentDynCouponData;
    protected ImageView downArrow;
    protected JSONObject dynCoupon;
    protected DynamicFloor dynamicLayout;
    protected View family;
    private SimpleDraweeView futurePriceIconIv;
    protected boolean isBatchEditMode;
    public boolean isSimple;
    protected View.OnClickListener leftArrowIconClickListner;
    private View.OnClickListener leftArrowOnclickLisener;
    private TextView liveIcon;
    protected AppCompatImageView lottieView;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    protected View.OnClickListener onItemViewClickListener;
    protected View.OnLongClickListener onItemViewLongClickListener;
    private TextView payVipIcon;
    protected ImageView plusImage;
    protected TextView plusLabel;
    private View.OnClickListener rightArrowClickListener;
    protected View.OnClickListener rightArrowIconClickListener;
    public ImageView rightBg;
    protected CompoundButton.OnCheckedChangeListener selecCheckListener;
    protected CheckBox select;
    protected TextView sellStatus;
    protected View septalLine;
    protected LinearLayout shareOrSellStatusLayout;
    private StringBuffer spacingPlaceholder;
    private AppCompatTextView startTime;
    protected TextView startUsingConditionOrExceptionCondition;
    private String tempId;
    protected View timeBtnContainer;
    private ImageView typeImageView;
    protected TextView use;
    private boolean useBind;
    protected TextView useLimitOverview;
    protected ImageView valueBackground;

    /* loaded from: classes4.dex */
    public interface CouponListener {
        void couponBtnClick(JSONObject jSONObject);

        void couponItemClick(JSONObject jSONObject);

        void couponLongClick(JSONObject jSONObject);
    }

    public CommonCouponView(@NonNull Context context) {
        super(context);
        this.isBatchEditMode = false;
        this.isSimple = false;
        this.spacingPlaceholder = new StringBuffer();
        this.useBind = true;
        initView();
        initListener();
        initDynView();
    }

    private void buildPlatformSubsidyCouponView(CouponEntity couponEntity) {
        ((LinearLayout.LayoutParams) this.startUsingConditionOrExceptionCondition.getLayoutParams()).topMargin = DPIUtil.dip2px(0.0f);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.couponValue.getLayoutParams();
        String str = couponEntity.withAvailable;
        if (!TextUtils.isEmpty(str)) {
            layoutParams.bottomMargin = DPIUtil.dip2px(0.0f);
            this.startUsingConditionOrExceptionCondition.setVisibility(0);
            this.startUsingConditionOrExceptionCondition.setText(str);
        } else {
            layoutParams.bottomMargin = DPIUtil.dip2px(5.0f);
            this.startUsingConditionOrExceptionCondition.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.couponBgColorNum) && TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            setValueBackgroundDrawable(getPlatformSubsidyDrawable());
        }
        if (!TextUtils.isEmpty(couponEntity.iconButtonColor) || isTypeShowColorList()) {
            return;
        }
        this.couponType.setBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.coupon_platform_subsidy_btn_shape));
    }

    private String cacaulateSpaceHolder() {
        StringBuilder sb = new StringBuilder();
        int measureWidth = ((int) (CouponUtil.measureWidth(this.couponType) / this.useLimitOverview.getPaint().measureText(getResources().getString(R.string.spacing)))) + 2;
        for (int i2 = 0; i2 < measureWidth; i2++) {
            sb.append(LangUtils.SINGLE_SPACE);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleArrowClick() {
        CouponEntity couponEntity = this.coupon;
        if (couponEntity != null) {
            boolean z = !couponEntity.isOpenDetail;
            couponEntity.isOpenDetail = z;
            if (z) {
                this.downArrow.setImageResource(R.drawable.common_hui_up);
                if (this.coupon.withAvailableMore) {
                    this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_up, 0);
                    return;
                } else {
                    this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    return;
                }
            }
            this.downArrow.setImageResource(R.drawable.common_hui_down);
            if (this.coupon.withAvailableMore) {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_down, 0);
            } else {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    private void handleFaceBg(String str) {
        JDImageUtils.displayImage(str, this.valueBackground, JDDisplayImageOptions.createSimple().setPlaceholder(R.drawable.value_background_dong).showImageOnFail(getDongDrawable()));
        this.valueBackground.setBackgroundDrawable(null);
    }

    private void handleTypeAndUseLimit(CouponEntity couponEntity, int i2) {
        if (!TextUtils.isEmpty(couponEntity.titleImageUrl)) {
            this.typeImageView.setVisibility(0);
            this.couponType.setVisibility(8);
            this.useLimitOverview.setVisibility(4);
            JDImageUtils.displayImage(couponEntity.titleImageUrl, this.typeImageView);
            this.typeImageView.setMaxWidth(i2);
            return;
        }
        this.useLimitOverview.setVisibility(0);
        this.typeImageView.setVisibility(8);
        String typeDescription = couponEntity.getTypeDescription();
        if (TextUtils.isEmpty(typeDescription)) {
            this.couponType.setVisibility(8);
            this.useLimitOverview.setText(couponEntity.getScope());
            return;
        }
        if (isTypeShowColorList()) {
            handleTypeColorList();
        } else if (!TextUtils.isEmpty(couponEntity.iconButtonColor)) {
            try {
                setTypeColor(Color.parseColor(couponEntity.iconButtonColor));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.couponType.setVisibility(0);
        if (couponEntity.getTypeCoupon() == 401) {
            this.couponType.setTextColor(ContextCompat.getColor(this.context, R.color.coupon_C25900));
        } else {
            this.couponType.setTextColor(ContextCompat.getColor(this.context, R.color.coupon_FFFFFF));
        }
        this.couponType.setText(typeDescription);
        this.useLimitOverview.setText(CouponUtil.getIndentString(this.context, (int) this.couponType.getPaint().measureText(typeDescription), couponEntity.getScope()));
    }

    private void handleTypeColorList() {
        int i2 = 0;
        if (this.coupon.iconColorList.size() == 1) {
            List<String> list = this.coupon.iconColorList;
            list.add(list.get(0));
        }
        int[] iArr = new int[this.coupon.iconColorList.size()];
        Iterator<String> it = this.coupon.iconColorList.iterator();
        while (it.hasNext()) {
            try {
                iArr[i2] = Color.parseColor(it.next());
            } catch (Exception unused) {
                iArr[i2] = -65536;
            }
            i2++;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr);
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(7.5f));
        this.couponType.setBackgroundDrawable(gradientDrawable);
    }

    private void initBrandVipIcon(String str, Drawable drawable, int i2) {
        if (this.brandVipIcon != null) {
            if (!TextUtils.isEmpty(str)) {
                this.brandVipIcon.setText(str);
            }
            if (drawable != null) {
                this.brandVipIcon.setCompoundDrawables(drawable, null, null, null);
            }
            if (i2 >= 0) {
                this.brandVipIcon.setCompoundDrawablePadding(i2);
            }
        }
    }

    private void initListener() {
        this.leftArrowOnclickLisener = new View.OnClickListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonCouponView.this.handleArrowClick();
                View.OnClickListener onClickListener = CommonCouponView.this.leftArrowIconClickListner;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        this.rightArrowClickListener = new View.OnClickListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonCouponView.this.handleArrowClick();
                View.OnClickListener onClickListener = CommonCouponView.this.rightArrowIconClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        };
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener2 = CommonCouponView.this.selecCheckListener;
                if (onCheckedChangeListener2 != null) {
                    onCheckedChangeListener2.onCheckedChanged(compoundButton, z);
                }
            }
        };
        this.onCheckedChangeListener = onCheckedChangeListener;
        this.select.setOnCheckedChangeListener(onCheckedChangeListener);
        this.contentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setTag(CommonCouponView.this.coupon);
                View.OnClickListener onClickListener = CommonCouponView.this.onItemViewClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
        this.contentLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.5
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                view.setTag(CommonCouponView.this.coupon);
                View.OnLongClickListener onLongClickListener = CommonCouponView.this.onItemViewLongClickListener;
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(view);
                    return true;
                }
                return true;
            }
        });
    }

    private void initView() {
        this.useBind = TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDCommune", "dynImprove", "couponUnit", "1"), "1");
        this.context = getContext();
        LayoutInflater.from(getContext()).inflate(R.layout.new_coupon_item, (ViewGroup) this, true);
        this.dynamicLayout = (DynamicFloor) findViewById(R.id.lib_mycoupon_dynamic_style_bg);
        this.couponCententLayout = (RelativeLayout) findViewById(R.id.couponCententLayout);
        this.select = (CheckBox) findViewById(R.id.select);
        this.contentLayout = (RelativeLayout) findViewById(R.id.my_coupon_all_use_coupon);
        this.couponType = (TextView) findViewById(R.id.typeName);
        this.couponValue = (AppCompatTextView) findViewById(R.id.value);
        this.startUsingConditionOrExceptionCondition = (TextView) findViewById(R.id.startUsingConditionOrExceptionCondition);
        this.useLimitOverview = (TextView) findViewById(R.id.useLimitOverview);
        this.timeBtnContainer = findViewById(R.id.timeBtnContainer);
        this.septalLine = findViewById(R.id.myCouponUseRightDivideLine);
        this.couponDetailTime = (AppCompatTextView) findViewById(R.id.timeLimit);
        this.use = (TextView) findViewById(R.id.useOrReceive);
        this.couponUnderOverTime = (ImageView) findViewById(R.id.my_coupon_use_right_under_date);
        this.downArrow = (ImageView) findViewById(R.id.downArrow);
        this.shareOrSellStatusLayout = (LinearLayout) findViewById(R.id.shareOrSellStatusLayout);
        this.valueBackground = (ImageView) findViewById(R.id.valueBackground);
        this.canSell = findViewById(R.id.canSell);
        this.canShare = findViewById(R.id.canShare);
        this.canOverlay = findViewById(R.id.canOverlay);
        this.circularSeal = findViewById(R.id.circularSeal);
        this.sellStatus = (TextView) findViewById(R.id.sellAndShareStatus);
        this.baitiaoTip = (TextView) findViewById(R.id.baitiao_tip);
        this.startTime = (AppCompatTextView) findViewById(R.id.hour_coupon_start_time);
        this.plusImage = (ImageView) findViewById(R.id.my_coupon_plus_image);
        this.plusLabel = (TextView) findViewById(R.id.my_coupon_only_plus_label);
        this.lottieView = (AppCompatImageView) findViewById(R.id.couponunit_lottie);
        this.family = findViewById(R.id.family);
        this.rightBg = (ImageView) findViewById(R.id.coupon_unit_bg);
        this.typeImageView = (ImageView) findViewById(R.id.my_coupon_11_img);
        this.liveIcon = (TextView) findViewById(R.id.my_coupon_live_icon);
        this.brandVipIcon = (TextView) findViewById(R.id.my_coupon_brand_icon);
        this.payVipIcon = (TextView) findViewById(R.id.my_coupon_pay_vip_icon);
        this.couponLimitLayout = (LinearLayout) findViewById(R.id.coupon_limit_layout);
        this.futurePriceIconIv = (SimpleDraweeView) findViewById(R.id.coupon_future_price_icon_iv);
        if (CouponUtil.jdBold == null) {
            CouponUtil.jdBold = FontsUtil.getTypeFace(getContext(), 4097);
        }
        if (CouponUtil.jdLight == null) {
            CouponUtil.jdLight = FontsUtil.getTypeFace(getContext(), 4098);
        }
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.couponDetailTime, 2, 10, 1, 1);
        CouponUtil.shareLayoutWidth = DPIUtil.getWidth(getContext()) - DPIUtil.dip2px(167.0f);
    }

    private boolean isTypeShowColorList() {
        List<String> list;
        CouponEntity couponEntity = this.coupon;
        return (couponEntity == null || (list = couponEntity.iconColorList) == null || list.size() <= 0) ? false : true;
    }

    private void setLimitDetailOpenOrClose() {
        CouponEntity couponEntity = this.coupon;
        if (couponEntity == null) {
            return;
        }
        if (couponEntity.isOpenDetail) {
            this.downArrow.setImageResource(R.drawable.common_hui_up);
        } else {
            this.downArrow.setImageResource(R.drawable.common_hui_down);
        }
    }

    private void setShowshareOrSellStatusLayout(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.timeBtnContainer.getLayoutParams();
        if (z) {
            this.septalLine.setVisibility(0);
            this.shareOrSellStatusLayout.setVisibility(0);
            layoutParams.topMargin = DPIUtil.dip2px(0.5f);
            return;
        }
        this.septalLine.setVisibility(8);
        this.shareOrSellStatusLayout.setVisibility(8);
        layoutParams.topMargin = DPIUtil.dip2px(20.0f);
    }

    protected void buildBatchDeleteEditView(CouponEntity couponEntity, boolean z) {
        if (couponEntity == null) {
            return;
        }
        this.downArrow.setVisibility(4);
        this.select.setVisibility(0);
        this.select.setOnCheckedChangeListener(null);
        this.select.setChecked(z);
        Resources resources = this.context.getResources();
        int i2 = R.dimen.coupon_item_margin_left_and_right;
        Resources resources2 = this.context.getResources();
        int i3 = R.dimen.coupon_item_margin_up_and_dows;
        ((RelativeLayout.LayoutParams) this.contentLayout.getLayoutParams()).setMargins((int) resources.getDimension(i2), (int) resources2.getDimension(i3), -((int) this.context.getResources().getDimension(i2)), (int) this.context.getResources().getDimension(i3));
        this.contentLayout.setClickable(false);
        this.shareOrSellStatusLayout.setClickable(false);
        if (this.downArrow.getVisibility() == 0) {
            this.shareOrSellStatusLayout.setClickable(true);
        } else {
            this.shareOrSellStatusLayout.setClickable(false);
        }
    }

    protected void buildCommonView() {
        this.select.setVisibility(8);
        Resources resources = this.context.getResources();
        int i2 = R.dimen.coupon_item_margin_left_and_right;
        Resources resources2 = this.context.getResources();
        int i3 = R.dimen.coupon_item_margin_up_and_dows;
        ((RelativeLayout.LayoutParams) this.contentLayout.getLayoutParams()).setMargins((int) resources.getDimension(i2), (int) resources2.getDimension(i3), (int) this.context.getResources().getDimension(i2), (int) this.context.getResources().getDimension(i3));
        this.contentLayout.setClickable(true);
        if (this.downArrow.getVisibility() == 0) {
            this.shareOrSellStatusLayout.setClickable(true);
        } else {
            this.shareOrSellStatusLayout.setClickable(false);
        }
    }

    protected void buildDongCouponView(CouponEntity couponEntity) {
        ((LinearLayout.LayoutParams) this.startUsingConditionOrExceptionCondition.getLayoutParams()).topMargin = DPIUtil.dip2px(0.0f);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.couponValue.getLayoutParams();
        String str = couponEntity.withAvailable;
        if (!TextUtils.isEmpty(str)) {
            layoutParams.bottomMargin = DPIUtil.dip2px(0.0f);
            this.startUsingConditionOrExceptionCondition.setVisibility(0);
            this.startUsingConditionOrExceptionCondition.setText(str);
        } else {
            layoutParams.bottomMargin = DPIUtil.dip2px(5.0f);
            this.startUsingConditionOrExceptionCondition.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.couponBgColorNum) && TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            setValueBackgroundDrawable(getDongDrawable());
        }
        if (!TextUtils.isEmpty(couponEntity.iconButtonColor) || isTypeShowColorList()) {
            return;
        }
        setTypeColor(getDongColor());
    }

    protected void buildFinanceCouponView(CouponEntity couponEntity) {
        ((LinearLayout.LayoutParams) this.startUsingConditionOrExceptionCondition.getLayoutParams()).topMargin = DPIUtil.dip2px(9.0f);
        ((LinearLayout.LayoutParams) this.couponValue.getLayoutParams()).bottomMargin = 0;
        String str = couponEntity.withAvailable;
        if (!TextUtils.isEmpty(str)) {
            this.startUsingConditionOrExceptionCondition.setVisibility(0);
            this.startUsingConditionOrExceptionCondition.setText(str);
        } else {
            this.startUsingConditionOrExceptionCondition.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.couponBgColorNum) && TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            setValueBackgroundDrawable(getFinanceDrawable());
        }
        if (!TextUtils.isEmpty(couponEntity.iconButtonColor) || isTypeShowColorList()) {
            return;
        }
        setTypeColor(getFinanceColor());
    }

    protected void buildFreeShippingCouponView(CouponEntity couponEntity) {
        ((LinearLayout.LayoutParams) this.couponValue.getLayoutParams()).bottomMargin = 0;
        String str = couponEntity.withAvailable;
        if (!TextUtils.isEmpty(str)) {
            this.startUsingConditionOrExceptionCondition.setVisibility(0);
            this.startUsingConditionOrExceptionCondition.setText(str);
        } else {
            this.startUsingConditionOrExceptionCondition.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.couponBgColorNum) && TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            setValueBackgroundDrawable(R.drawable.value_background_yun);
        }
        if (!TextUtils.isEmpty(couponEntity.iconButtonColor) || isTypeShowColorList()) {
            return;
        }
        setTypeColor(ContextCompat.getColor(this.context, R.color.c_46cbc4));
    }

    protected void buildJingCouponView(CouponEntity couponEntity) {
        ((LinearLayout.LayoutParams) this.couponValue.getLayoutParams()).bottomMargin = DPIUtil.dip2px(5.0f);
        String str = couponEntity.withAvailable;
        if (!TextUtils.isEmpty(str)) {
            this.startUsingConditionOrExceptionCondition.setVisibility(0);
            this.startUsingConditionOrExceptionCondition.setText(str);
        } else {
            this.startUsingConditionOrExceptionCondition.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.couponBgColorNum) && TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            setValueBackgroundDrawable(getJingDrawable());
        }
        if (!TextUtils.isEmpty(couponEntity.iconButtonColor) || isTypeShowColorList()) {
            return;
        }
        setTypeColor(getJingColor());
    }

    public void couponClickListener(String str, JSONObject jSONObject) {
        if (this.useBind) {
            if (jSONObject != null) {
                jSONObject.remove("dynamic_private_data");
                jSONObject.remove("dynamic_item_private_data");
                jSONObject.remove("dynamic_private_js_data");
            }
            JSONObject jSONObject2 = this.currentDynCouponData;
            if (jSONObject2 != null) {
                jSONObject2.remove("dynamic_private_data");
                this.currentDynCouponData.remove("dynamic_item_private_data");
                this.currentDynCouponData.remove("dynamic_private_js_data");
            }
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -2113032797:
                if (str.equals("onCouponClick")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1111154937:
                if (str.equals("onCouponLongClick")) {
                    c2 = 1;
                    break;
                }
                break;
            case 557323147:
                if (str.equals("onBtnClick")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                CouponListener couponListener = this.couponListener;
                if (couponListener != null) {
                    couponListener.couponItemClick(jSONObject);
                    return;
                }
                return;
            case 1:
                CouponListener couponListener2 = this.couponListener;
                if (couponListener2 != null) {
                    couponListener2.couponLongClick(jSONObject);
                    return;
                }
                return;
            case 2:
                CouponListener couponListener3 = this.couponListener;
                if (couponListener3 != null) {
                    couponListener3.couponBtnClick(jSONObject);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public CouponEntity getCoupon() {
        return this.coupon;
    }

    protected int getDongColor() {
        return ContextCompat.getColor(getContext(), R.color.c_5F9BD5);
    }

    protected int getDongDrawable() {
        return R.drawable.value_background_dong;
    }

    protected int getDrawableIdWithCouponBgColorNum(String str) {
        return CouponUtil.getDrawableIdWithCouponBgColorNum(str);
    }

    protected int getFinanceColor() {
        return Color.parseColor("#e6b870");
    }

    protected int getFinanceDrawable() {
        return R.drawable.value_background_finance;
    }

    protected int getJingColor() {
        return Color.parseColor("#ff5c5a");
    }

    protected int getJingDrawable() {
        return R.drawable.value_background_jing;
    }

    protected int getPlatformSubsidyColor() {
        return Color.parseColor("#C25900");
    }

    protected int getPlatformSubsidyDrawable() {
        return R.drawable.value_background_platform_subsidy;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ba, code lost:
        if (r0.equals("0") == false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void handleFaceType(com.jingdong.app.mall.mylib.CouponUnit.CouponEntity r7) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.handleFaceType(com.jingdong.app.mall.mylib.CouponUnit.CouponEntity):void");
    }

    public void initDynView() {
        DynamicFloor dynamicFloor = this.dynamicLayout;
        if (dynamicFloor != null) {
            dynamicFloor.setVisibility(this.isSimple ? 0 : 8);
        }
        RelativeLayout relativeLayout = this.couponCententLayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(this.isSimple ? 8 : 0);
        }
    }

    public void playOrStopAnimation(boolean z) {
        if (Build.VERSION.SDK_INT < 16) {
            return;
        }
        if (z) {
            AppCompatImageView appCompatImageView = this.lottieView;
            if (!(appCompatImageView instanceof JDLottieAnimationView) || ((JDLottieAnimationView) appCompatImageView).isAnimating()) {
                return;
            }
            ((JDLottieAnimationView) this.lottieView).playAnimation();
            return;
        }
        AppCompatImageView appCompatImageView2 = this.lottieView;
        if ((appCompatImageView2 instanceof JDLottieAnimationView) && ((JDLottieAnimationView) appCompatImageView2).isAnimating()) {
            ((JDLottieAnimationView) this.lottieView).cancelAnimation();
        }
    }

    protected void setBtnRecieve(int i2) {
        List<String> list;
        CouponEntity couponEntity = this.coupon;
        if (couponEntity != null) {
            int i3 = 0;
            if (i2 == 401) {
                if (couponEntity != null && (list = couponEntity.receiveColorList) != null && list.size() > 0) {
                    if (this.coupon.receiveColorList.size() == 1) {
                        List<String> list2 = this.coupon.receiveColorList;
                        list2.add(list2.get(0));
                    }
                    int[] iArr = new int[this.coupon.receiveColorList.size()];
                    Iterator<String> it = this.coupon.receiveColorList.iterator();
                    while (it.hasNext()) {
                        try {
                            iArr[i3] = Color.parseColor(it.next());
                        } catch (Exception unused) {
                            iArr[i3] = -65536;
                        }
                        i3++;
                    }
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr);
                    gradientDrawable.setCornerRadius(DPIUtil.dip2px(11.0f));
                    this.use.setBackgroundDrawable(gradientDrawable);
                    this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.coupon_A84D00));
                    return;
                }
            } else if (isTypeShowColorList()) {
                if (this.coupon.iconColorList.size() == 1) {
                    List<String> list3 = this.coupon.iconColorList;
                    list3.add(list3.get(0));
                }
                int[] iArr2 = new int[this.coupon.iconColorList.size()];
                Iterator<String> it2 = this.coupon.iconColorList.iterator();
                while (it2.hasNext()) {
                    try {
                        iArr2[i3] = Color.parseColor(it2.next());
                    } catch (Exception unused2) {
                        iArr2[i3] = -65536;
                    }
                    i3++;
                }
                GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr2);
                gradientDrawable2.setCornerRadius(DPIUtil.dip2px(11.0f));
                this.use.setBackgroundDrawable(gradientDrawable2);
                this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                return;
            }
        }
        if (i2 == 0) {
            this.use.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.common_coupon_jing_recieve_bg));
            this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else if (i2 == 1) {
            this.use.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.common_coupon_dong_recieve_bg));
            this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else if (i2 == 2) {
            this.use.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.common_coupon_yun_recieve_bg));
            this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else if (i2 == 3) {
            this.use.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.common_coupon_finance_recieve_bg));
            this.use.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else if (i2 != 401) {
        } else {
            this.use.setBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.coupon_platform_subsidy_btn_shape));
            this.use.setTextColor(ContextCompat.getColor(this.context, R.color.coupon_A84D00));
        }
    }

    protected void setBtnUseColor(int i2, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                setBtnUseColor(Color.parseColor(str));
            } catch (Exception unused) {
            }
        } else if (i2 == 0) {
            setBtnUseColor(getJingColor());
        } else if (i2 == 1) {
            setBtnUseColor(getDongColor());
        } else if (i2 == 2) {
            setBtnUseColor(ContextCompat.getColor(this.context, R.color.c_46cbc4));
        } else if (i2 == 3) {
            setBtnUseColor(getFinanceColor());
        } else if (i2 != 401) {
        } else {
            setBtnUseColor(getPlatformSubsidyColor());
        }
    }

    public void setBusinessType(String str) {
        boolean equals = TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDCommune", "dynamicCoupon", str), "1");
        this.isSimple = equals;
        DynamicFloor dynamicFloor = this.dynamicLayout;
        if (dynamicFloor != null) {
            dynamicFloor.setVisibility(equals ? 0 : 8);
        }
        RelativeLayout relativeLayout = this.couponCententLayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(this.isSimple ? 8 : 0);
        }
    }

    public void setButtonHidden(boolean z) {
        if (z) {
            this.use.setVisibility(4);
        } else {
            this.use.setVisibility(0);
        }
    }

    public void setButtonText(String str) {
        CouponEntity couponEntity = this.coupon;
        if (couponEntity != null) {
            couponEntity.buttonText = str;
        }
        if (!TextUtils.isEmpty(str)) {
            this.use.setVisibility(0);
            this.use.setText(str);
            return;
        }
        this.use.setVisibility(4);
    }

    public void setCoupon(CouponEntity couponEntity, boolean z, boolean z2) {
        if (couponEntity == null) {
            return;
        }
        this.isBatchEditMode = z;
        setData(couponEntity);
        if (z) {
            buildBatchDeleteEditView(couponEntity, z2);
        } else {
            buildCommonView();
        }
    }

    public void setCouponListener(CouponListener couponListener) {
        this.couponListener = couponListener;
    }

    public void setCouponType(boolean z) {
        this.isSimple = z;
        DynamicFloor dynamicFloor = this.dynamicLayout;
        if (dynamicFloor != null) {
            dynamicFloor.setVisibility(z ? 0 : 8);
        }
        RelativeLayout relativeLayout = this.couponCententLayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 8 : 0);
        }
    }

    public void setData(CouponEntity couponEntity) {
        setData(couponEntity, null);
    }

    public void setData2(final CouponEntity couponEntity, Activity activity) {
        if (couponEntity == null) {
            return;
        }
        if (activity != null) {
            CouponUtil.shareLayoutWidth = DPIUtil.getAppWidth(activity) - DPIUtil.dip2px(activity, 167.0f);
        }
        this.coupon = couponEntity;
        setLimitDetailOpenOrClose();
        this.downArrow.setOnClickListener(this.rightArrowClickListener);
        this.shareOrSellStatusLayout.setOnClickListener(this.rightArrowClickListener);
        handleFaceType(couponEntity);
        if (!TextUtils.isEmpty(couponEntity.withAvailable)) {
            this.couponValue.setContentDescription(((Object) this.couponValue.getText()) + DYConstants.DY_REGEX_COMMA + couponEntity.withAvailable);
        } else {
            AppCompatTextView appCompatTextView = this.couponValue;
            appCompatTextView.setContentDescription(appCompatTextView.getText());
        }
        handleTypeAndUseLimit(couponEntity, CouponUtil.shareLayoutWidth - DPIUtil.dip2px(this.context, 10.0f));
        if (!TextUtils.isEmpty(couponEntity.buttonText)) {
            this.use.setVisibility(0);
            this.use.setText(couponEntity.buttonText);
        } else {
            this.use.setVisibility(4);
        }
        int dip2px = CouponUtil.shareLayoutWidth - DPIUtil.dip2px(this.context, 10.0f);
        if (!TextUtils.isEmpty(couponEntity.markingIconUrl)) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            this.plusImage.setVisibility(0);
            JDImageUtils.displayImage(couponEntity.markingIconUrl, this.plusImage, jDDisplayImageOptions, false, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.7
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                    CommonCouponView.this.plusImage.setVisibility(4);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    CommonCouponView.this.plusImage.setVisibility(0);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    CommonCouponView.this.plusImage.setVisibility(4);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            }, null);
        } else {
            switch (couponEntity.markingType) {
                case 1:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.plus_vip_label));
                    this.plusImage.setVisibility(0);
                    break;
                case 2:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.fans_label));
                    this.plusImage.setVisibility(0);
                    break;
                case 3:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.gift_label));
                    this.plusImage.setVisibility(0);
                    break;
                case 4:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.family_label));
                    this.plusImage.setVisibility(0);
                    break;
                case 5:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.money_label));
                    this.plusImage.setVisibility(0);
                    break;
                case 6:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.coupon_jingtie_label));
                    this.plusImage.setVisibility(0);
                    break;
                default:
                    this.plusImage.setVisibility(8);
                    break;
            }
        }
        List<CouponLabelEntity> list = couponEntity.labelLists;
        if (list != null && list.size() > 0) {
            this.shareOrSellStatusLayout.removeAllViews();
            setShowshareOrSellStatusLayout(true);
            for (CouponLabelEntity couponLabelEntity : couponEntity.labelLists) {
                String str = couponLabelEntity.iconTitle;
                String str2 = couponLabelEntity.iconUrl;
                if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                    CouponLabelIconItem couponLabelIconItem = new CouponLabelIconItem(this.context);
                    couponLabelIconItem.init(str2, str);
                    int measureWidth = CouponUtil.measureWidth(couponLabelIconItem);
                    if (dip2px > measureWidth) {
                        this.shareOrSellStatusLayout.addView(couponLabelIconItem);
                        dip2px -= measureWidth;
                    }
                }
            }
        } else {
            setShowshareOrSellStatusLayout(false);
        }
        if (!TextUtils.isEmpty(couponEntity.bgImgUrl)) {
            String str3 = couponEntity.bgImgUrl;
            ImageView imageView = this.rightBg;
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            int i2 = R.drawable.coupon_unit_white;
            JDImageUtils.displayImage(str3, imageView, createSimple.setPlaceholder(i2).showImageOnFail(i2));
            this.rightBg.setVisibility(0);
        } else {
            this.rightBg.setVisibility(8);
        }
        if (!TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            handleFaceBg(couponEntity.priceBgImgUrl);
        } else if (!TextUtils.isEmpty(couponEntity.couponBgColorNum)) {
            Drawable drawable = UnIconConfigHelper.getDrawable(couponEntity.couponBgColorNum);
            if (drawable != null) {
                setValueBackgroundDrawable(drawable);
            } else {
                setValueBackgroundDrawable(getDrawableIdWithCouponBgColorNum(couponEntity.couponBgColorNum));
            }
        }
        this.couponLimitLayout.getLayoutParams().width = -1;
        LinearLayout linearLayout = this.couponLimitLayout;
        linearLayout.setLayoutParams(linearLayout.getLayoutParams());
        this.couponLimitLayout.setBackground(null);
        this.couponLimitLayout.setPadding(DPIUtil.dip2px(4.0f), 0, DPIUtil.dip2px(4.0f), 0);
        this.futurePriceIconIv.setVisibility(8);
        if (!TextUtils.isEmpty(couponEntity.futureContent)) {
            this.startTime.setVisibility(8);
            this.couponDetailTime.setVisibility(0);
            this.couponDetailTime.setText(couponEntity.futureContent);
            this.futurePriceIconIv.setVisibility(0);
            if (!TextUtils.isEmpty(couponEntity.futureTimeIcon)) {
                this.futurePriceIconIv.setVisibility(0);
                JDImageUtils.displayImage(couponEntity.futureTimeIcon, this.futurePriceIconIv);
            }
            this.couponLimitLayout.getLayoutParams().width = -2;
            LinearLayout linearLayout2 = this.couponLimitLayout;
            linearLayout2.setLayoutParams(linearLayout2.getLayoutParams());
            this.couponLimitLayout.setBackground(ContextCompat.getDrawable(this.context, R.drawable.coupon_future_time_shape));
        } else if (!TextUtils.isEmpty(couponEntity.countDownTimeCharSequence)) {
            this.couponDetailTime.setTypeface(Typeface.DEFAULT);
            this.startTime.setVisibility(8);
            this.couponDetailTime.setVisibility(0);
            this.couponDetailTime.setText(couponEntity.countDownTimeCharSequence);
        } else {
            Typeface typeface = CouponUtil.jdLight;
            if (typeface != null) {
                this.couponDetailTime.setTypeface(typeface);
            } else {
                this.couponDetailTime.setTypeface(Typeface.DEFAULT);
            }
            if (couponEntity.isHourCoupon) {
                if (!TextUtils.isEmpty(couponEntity.startDay) && !TextUtils.isEmpty(couponEntity.startMinute) && !TextUtils.isEmpty(couponEntity.endDay) && !TextUtils.isEmpty(couponEntity.endMinute)) {
                    this.startTime.setVisibility(0);
                    this.couponDetailTime.setVisibility(8);
                    this.startTime.setText("");
                    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.startTime, 2, 10, 1, 1);
                    this.startTime.post(new Runnable() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.8
                        @Override // java.lang.Runnable
                        public void run() {
                            AppCompatTextView appCompatTextView2 = CommonCouponView.this.startTime;
                            String string = CommonCouponView.this.context.getResources().getString(R.string.coupon_unit_hour);
                            CouponEntity couponEntity2 = couponEntity;
                            appCompatTextView2.setText(String.format(string, couponEntity2.startDay, couponEntity2.endDay, couponEntity2.startMinute, couponEntity2.endMinute));
                        }
                    });
                } else {
                    this.startTime.setVisibility(8);
                    this.couponDetailTime.setVisibility(0);
                    this.couponDetailTime.setText(couponEntity.startAndEndTime);
                }
            } else {
                this.startTime.setVisibility(8);
                this.couponDetailTime.setVisibility(0);
                this.couponDetailTime.setText(couponEntity.startAndEndTime);
            }
        }
        setIsShowRightArrow(couponEntity.isShowRightArrow);
        int typeCoupon = couponEntity.getTypeCoupon();
        if (typeCoupon == 0) {
            buildJingCouponView(couponEntity);
        } else if (typeCoupon == 1) {
            buildDongCouponView(couponEntity);
        } else if (typeCoupon == 2) {
            buildFreeShippingCouponView(couponEntity);
        } else if (typeCoupon == 3) {
            buildFinanceCouponView(couponEntity);
        } else if (typeCoupon == 401) {
            buildPlatformSubsidyCouponView(couponEntity);
        }
        if (couponEntity.withAvailableMore) {
            this.startUsingConditionOrExceptionCondition.setOnClickListener(this.leftArrowOnclickLisener);
            if (couponEntity.getTypeCoupon() == 401) {
                if (couponEntity.isOpenDetail) {
                    this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_subsidy_arrow_up, 0);
                } else {
                    this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_subsidy_arrow_down, 0);
                }
            } else if (couponEntity.isOpenDetail) {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_up, 0);
            } else {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_down, 0);
            }
        } else {
            this.startUsingConditionOrExceptionCondition.setOnClickListener(null);
            this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        this.circularSeal.setVisibility(8);
        if (couponEntity.isReceived) {
            this.circularSeal.setVisibility(0);
            this.circularSeal.setBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.already_fetch));
        }
        if (couponEntity.canUse) {
            setBtnUseColor(couponEntity.typeCoupon, couponEntity.iconButtonColor);
        } else if (couponEntity.isReceived) {
            setBtnUseColor(couponEntity.typeCoupon, couponEntity.iconButtonColor);
        } else {
            setBtnRecieve(couponEntity.typeCoupon);
        }
    }

    public void setDynData(JSONObject jSONObject, Activity activity) {
        if (jSONObject == null || !jSONObject.has(DeeplinkProductDetailHelper.LAYER_STYLE) || activity == null) {
            return;
        }
        if (this.useBind) {
            String optString = jSONObject.optString(JshopConst.JSKEY_BATCH_ID);
            String optString2 = jSONObject.optString("couponId");
            JSONObject jSONObject2 = this.dynCoupon;
            if (jSONObject2 != null) {
                String optString3 = jSONObject2.optString(JshopConst.JSKEY_BATCH_ID);
                String optString4 = this.dynCoupon.optString("couponId");
                if (TextUtils.equals(optString, optString3) && TextUtils.equals(optString2, optString4)) {
                    try {
                        JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
                        jSONObject3.remove("dynamic_private_data");
                        jSONObject3.remove("dynamic_item_private_data");
                        jSONObject3.remove("dynamic_private_js_data");
                        JSONObject jSONObject4 = new JSONObject(this.dynCoupon.toString());
                        jSONObject4.remove("dynamic_private_data");
                        jSONObject4.remove("dynamic_item_private_data");
                        jSONObject4.remove("dynamic_private_js_data");
                        if (!TextUtils.equals(MD5.encrypt16(jSONObject3.toString()), MD5.encrypt16(jSONObject4.toString()))) {
                            jSONObject = jSONObject3;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        try {
            this.dynCoupon = new JSONObject(jSONObject.toString());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        this.currentDynCouponData = jSONObject;
        JSONObject optJSONObject = jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE);
        if (this.dynamicLayout == null || optJSONObject == null) {
            return;
        }
        String optString5 = optJSONObject.optString("tempId");
        if (this.useBind) {
            if (!TextUtils.equals(optString5, this.tempId)) {
                this.tempId = optString5;
                this.dynamicLayout.createDynamicView(optString5);
            }
            this.dynamicLayout.bindDynamicData(jSONObject);
            return;
        }
        this.dynamicLayout.showDynamicView(jSONObject, optJSONObject.optString("tempId"));
    }

    public void setIsShowRightArrow(boolean z) {
        CouponEntity couponEntity = this.coupon;
        if (couponEntity != null) {
            couponEntity.isShowRightArrow = z;
        }
        if (!z) {
            this.downArrow.setVisibility(8);
            this.shareOrSellStatusLayout.setClickable(false);
            return;
        }
        this.downArrow.setVisibility(0);
        this.shareOrSellStatusLayout.setClickable(true);
        this.shareOrSellStatusLayout.setOnClickListener(this.rightArrowClickListener);
    }

    public void setLableDrawable(int i2) {
        Drawable drawable = ContextCompat.getDrawable(this.context, i2);
        if (drawable != null) {
            this.circularSeal.setVisibility(0);
            this.circularSeal.setBackgroundDrawable(drawable);
        }
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.selecCheckListener = onCheckedChangeListener;
    }

    public void setOnItemViewClickListener(View.OnClickListener onClickListener) {
        this.onItemViewClickListener = onClickListener;
    }

    public void setOnItemViewLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onItemViewLongClickListener = onLongClickListener;
    }

    public void setOnLeftArrowClickListner(View.OnClickListener onClickListener) {
        this.leftArrowIconClickListner = onClickListener;
    }

    public void setOnRightArrowClickListner(View.OnClickListener onClickListener) {
        this.rightArrowIconClickListener = onClickListener;
    }

    protected void setTypeColor(int i2) {
        GradientDrawable gradientDrawable = (GradientDrawable) this.context.getResources().getDrawable(R.drawable.type_name_background);
        gradientDrawable.setColor(i2);
        this.couponType.setBackgroundDrawable(gradientDrawable);
    }

    public void setUseAndReceive(String str, String str2) {
        JSONObject jSONObject = this.currentDynCouponData;
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put("buttonText", str);
            this.currentDynCouponData.put("couponStatus", str2);
            this.dynamicLayout.refreshDynamicView(this.currentDynCouponData);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    protected void setValueBackgroundDrawable(Drawable drawable) {
        this.valueBackground.setBackgroundDrawable(drawable);
        this.valueBackground.setImageResource(0);
    }

    public void showOrNotLottieView(boolean z) {
        if (z) {
            this.lottieView.setVisibility(0);
        } else {
            this.lottieView.setVisibility(8);
        }
    }

    public void setData(final CouponEntity couponEntity, Activity activity) {
        int i2;
        if (couponEntity == null) {
            return;
        }
        if (activity != null) {
            CouponUtil.shareLayoutWidth = DPIUtil.getAppWidth(activity) - DPIUtil.dip2px(activity, 167.0f);
        }
        this.coupon = couponEntity;
        setLimitDetailOpenOrClose();
        this.downArrow.setOnClickListener(this.rightArrowClickListener);
        this.shareOrSellStatusLayout.setOnClickListener(this.rightArrowClickListener);
        handleFaceType(couponEntity);
        if (!TextUtils.isEmpty(couponEntity.withAvailable)) {
            this.couponValue.setContentDescription(((Object) this.couponValue.getText()) + DYConstants.DY_REGEX_COMMA + couponEntity.withAvailable);
        } else {
            AppCompatTextView appCompatTextView = this.couponValue;
            appCompatTextView.setContentDescription(appCompatTextView.getText());
        }
        handleTypeAndUseLimit(couponEntity, CouponUtil.shareLayoutWidth - DPIUtil.dip2px(this.context, 10.0f));
        if (!TextUtils.isEmpty(couponEntity.buttonText)) {
            this.use.setVisibility(0);
            this.use.setText(couponEntity.buttonText);
        } else {
            this.use.setVisibility(4);
        }
        int dip2px = CouponUtil.shareLayoutWidth - DPIUtil.dip2px(this.context, 10.0f);
        if (!TextUtils.isEmpty(couponEntity.markingIconUrl)) {
            JDImageUtils.displayImage(couponEntity.markingIconUrl, this.plusImage);
            this.plusImage.setVisibility(0);
            int i3 = couponEntity.markingType;
            if (i3 == 1) {
                this.family.setVisibility(8);
                if (CouponUtil.onlyPlusWidth == 0) {
                    CouponUtil.onlyPlusWidth = CouponUtil.measureWidth(this.plusLabel);
                }
                if (dip2px > CouponUtil.onlyPlusWidth) {
                    this.plusLabel.setVisibility(0);
                } else {
                    this.plusLabel.setVisibility(8);
                }
                i2 = CouponUtil.onlyPlusWidth;
            } else if (i3 != 4) {
                this.plusLabel.setVisibility(8);
                this.family.setVisibility(8);
            } else {
                this.plusLabel.setVisibility(8);
                if (CouponUtil.familyWidth == 0) {
                    CouponUtil.familyWidth = CouponUtil.measureWidth(this.family);
                }
                if (dip2px > CouponUtil.familyWidth) {
                    this.family.setVisibility(0);
                } else {
                    this.family.setVisibility(8);
                }
                i2 = CouponUtil.familyWidth;
            }
            dip2px -= i2;
        } else {
            switch (couponEntity.markingType) {
                case 1:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.plus_vip_label));
                    this.plusImage.setVisibility(0);
                    this.family.setVisibility(8);
                    if (CouponUtil.onlyPlusWidth == 0) {
                        CouponUtil.onlyPlusWidth = CouponUtil.measureWidth(this.plusLabel);
                    }
                    if (dip2px > CouponUtil.onlyPlusWidth) {
                        this.plusLabel.setVisibility(0);
                    } else {
                        this.plusLabel.setVisibility(8);
                    }
                    i2 = CouponUtil.onlyPlusWidth;
                    dip2px -= i2;
                    break;
                case 2:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.fans_label));
                    this.plusImage.setVisibility(0);
                    this.plusLabel.setVisibility(8);
                    this.family.setVisibility(8);
                    break;
                case 3:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.gift_label));
                    this.plusImage.setVisibility(0);
                    this.plusLabel.setVisibility(8);
                    this.family.setVisibility(8);
                    break;
                case 4:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.family_label));
                    this.plusImage.setVisibility(0);
                    this.plusLabel.setVisibility(8);
                    if (CouponUtil.familyWidth == 0) {
                        CouponUtil.familyWidth = CouponUtil.measureWidth(this.family);
                    }
                    if (dip2px > CouponUtil.familyWidth) {
                        this.family.setVisibility(0);
                    } else {
                        this.family.setVisibility(8);
                    }
                    i2 = CouponUtil.familyWidth;
                    dip2px -= i2;
                    break;
                case 5:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.money_label));
                    this.plusImage.setVisibility(0);
                    this.plusLabel.setVisibility(8);
                    this.family.setVisibility(8);
                    break;
                case 6:
                    this.plusImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.coupon_jingtie_label));
                    this.plusImage.setVisibility(0);
                    this.plusLabel.setVisibility(8);
                    this.family.setVisibility(8);
                    break;
                default:
                    this.plusImage.setVisibility(8);
                    this.plusLabel.setVisibility(8);
                    this.family.setVisibility(8);
                    break;
            }
        }
        if (couponEntity.isPayVip) {
            int measureWidth = CouponUtil.measureWidth(this.payVipIcon);
            if (dip2px >= measureWidth) {
                this.payVipIcon.setVisibility(0);
            } else {
                this.payVipIcon.setVisibility(8);
            }
            dip2px -= measureWidth;
        } else {
            this.payVipIcon.setVisibility(8);
        }
        if (couponEntity.isBrandVip) {
            int measureWidth2 = CouponUtil.measureWidth(this.brandVipIcon);
            if (dip2px >= measureWidth2) {
                this.brandVipIcon.setVisibility(0);
            } else {
                this.brandVipIcon.setVisibility(8);
            }
            dip2px -= measureWidth2;
        } else {
            this.brandVipIcon.setVisibility(8);
        }
        if (couponEntity.canOverlay) {
            if (CouponUtil.shareOrOverLayWidth == 0) {
                CouponUtil.shareOrOverLayWidth = CouponUtil.measureWidth(this.canOverlay);
            }
            if (dip2px >= CouponUtil.shareOrOverLayWidth) {
                this.canOverlay.setVisibility(0);
            } else {
                this.canOverlay.setVisibility(8);
            }
            dip2px -= CouponUtil.shareOrOverLayWidth;
        } else {
            this.canOverlay.setVisibility(8);
        }
        if (couponEntity.canShare) {
            if (CouponUtil.shareOrOverLayWidth == 0) {
                CouponUtil.shareOrOverLayWidth = CouponUtil.measureWidth(this.canShare);
            }
            if (dip2px >= CouponUtil.shareOrOverLayWidth) {
                this.canShare.setVisibility(0);
            } else {
                this.canShare.setVisibility(8);
            }
            dip2px -= CouponUtil.shareOrOverLayWidth;
        } else {
            this.canShare.setVisibility(8);
        }
        if (couponEntity.haveLiveIcon) {
            this.liveIcon.setText(couponEntity.liveCouponText);
            int measureWidth3 = CouponUtil.measureWidth(this.liveIcon);
            if (dip2px >= measureWidth3) {
                this.liveIcon.setVisibility(0);
            } else {
                this.liveIcon.setVisibility(8);
            }
            dip2px -= measureWidth3;
        } else {
            this.liveIcon.setVisibility(8);
        }
        if (couponEntity.isBaiTiao) {
            if (!TextUtils.isEmpty(couponEntity.getTipContent())) {
                this.baitiaoTip.setText(couponEntity.getTipContent());
            }
            this.baitiaoTip.setVisibility(0);
            if (CouponUtil.baitiaoWidth == 0) {
                CouponUtil.baitiaoWidth = CouponUtil.measureWidth(this.baitiaoTip);
            }
            if (dip2px >= CouponUtil.baitiaoWidth) {
                this.baitiaoTip.setVisibility(0);
            } else {
                this.baitiaoTip.setVisibility(8);
            }
            dip2px -= CouponUtil.baitiaoWidth;
        } else {
            this.baitiaoTip.setVisibility(8);
        }
        if (couponEntity.canSell) {
            this.canSell.setVisibility(0);
            if (CouponUtil.sellWidth == 0) {
                CouponUtil.sellWidth = CouponUtil.measureWidth(this.canSell);
            }
            if (dip2px >= CouponUtil.sellWidth) {
                this.canSell.setVisibility(0);
            } else {
                this.canSell.setVisibility(8);
            }
        } else {
            this.canSell.setVisibility(8);
        }
        if (TextUtils.isEmpty(couponEntity.bottomText)) {
            this.sellStatus.setVisibility(8);
        } else {
            this.sellStatus.setVisibility(0);
            this.sellStatus.setText(couponEntity.bottomText);
        }
        int i4 = couponEntity.markingType;
        if (i4 != 1 && i4 != 4 && !couponEntity.canShare && !couponEntity.canSell && !couponEntity.canOverlay && TextUtils.isEmpty(couponEntity.bottomText) && !couponEntity.isShowRightArrow && !couponEntity.isBaiTiao) {
            setShowshareOrSellStatusLayout(false);
        } else {
            setShowshareOrSellStatusLayout(true);
        }
        if (!TextUtils.isEmpty(couponEntity.bgImgUrl)) {
            String str = couponEntity.bgImgUrl;
            ImageView imageView = this.rightBg;
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            int i5 = R.drawable.coupon_unit_white;
            JDImageUtils.displayImage(str, imageView, createSimple.setPlaceholder(i5).showImageOnFail(i5));
            this.rightBg.setVisibility(0);
        } else {
            this.rightBg.setVisibility(8);
        }
        if (!TextUtils.isEmpty(couponEntity.priceBgImgUrl)) {
            handleFaceBg(couponEntity.priceBgImgUrl);
        } else if (!TextUtils.isEmpty(couponEntity.couponBgColorNum)) {
            Drawable drawable = UnIconConfigHelper.getDrawable(couponEntity.couponBgColorNum);
            if (drawable != null) {
                setValueBackgroundDrawable(drawable);
            } else {
                setValueBackgroundDrawable(getDrawableIdWithCouponBgColorNum(couponEntity.couponBgColorNum));
            }
        }
        if (!TextUtils.isEmpty(couponEntity.countDownTimeCharSequence)) {
            this.couponDetailTime.setTypeface(Typeface.DEFAULT);
            this.startTime.setVisibility(8);
            this.couponDetailTime.setVisibility(0);
            this.couponDetailTime.setText(couponEntity.countDownTimeCharSequence);
        } else {
            Typeface typeface = CouponUtil.jdLight;
            if (typeface != null) {
                this.couponDetailTime.setTypeface(typeface);
            } else {
                this.couponDetailTime.setTypeface(Typeface.DEFAULT);
            }
            if (couponEntity.isHourCoupon) {
                if (!TextUtils.isEmpty(couponEntity.startDay) && !TextUtils.isEmpty(couponEntity.startMinute) && !TextUtils.isEmpty(couponEntity.endDay) && !TextUtils.isEmpty(couponEntity.endMinute)) {
                    this.startTime.setVisibility(0);
                    this.couponDetailTime.setVisibility(8);
                    this.startTime.setText("");
                    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.startTime, 2, 10, 1, 1);
                    this.startTime.post(new Runnable() { // from class: com.jingdong.app.mall.mylib.CouponUnit.CommonCouponView.6
                        @Override // java.lang.Runnable
                        public void run() {
                            AppCompatTextView appCompatTextView2 = CommonCouponView.this.startTime;
                            String string = CommonCouponView.this.context.getResources().getString(R.string.coupon_unit_hour);
                            CouponEntity couponEntity2 = couponEntity;
                            appCompatTextView2.setText(String.format(string, couponEntity2.startDay, couponEntity2.endDay, couponEntity2.startMinute, couponEntity2.endMinute));
                        }
                    });
                } else {
                    this.startTime.setVisibility(8);
                    this.couponDetailTime.setVisibility(0);
                    this.couponDetailTime.setText(couponEntity.startAndEndTime);
                }
            } else {
                this.startTime.setVisibility(8);
                this.couponDetailTime.setVisibility(0);
                this.couponDetailTime.setText(couponEntity.startAndEndTime);
            }
        }
        setIsShowRightArrow(couponEntity.isShowRightArrow);
        int typeCoupon = couponEntity.getTypeCoupon();
        if (typeCoupon == 0) {
            buildJingCouponView(couponEntity);
        } else if (typeCoupon == 1) {
            buildDongCouponView(couponEntity);
        } else if (typeCoupon == 2) {
            buildFreeShippingCouponView(couponEntity);
        } else if (typeCoupon == 3) {
            buildFinanceCouponView(couponEntity);
        } else if (typeCoupon == 401) {
            buildPlatformSubsidyCouponView(couponEntity);
        }
        if (couponEntity.withAvailableMore) {
            this.startUsingConditionOrExceptionCondition.setOnClickListener(this.leftArrowOnclickLisener);
            if (couponEntity.isOpenDetail) {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_up, 0);
            } else {
                this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.coupon_arrow_down, 0);
            }
        } else {
            this.startUsingConditionOrExceptionCondition.setOnClickListener(null);
            this.startUsingConditionOrExceptionCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        this.circularSeal.setVisibility(8);
        if (couponEntity.isReceived) {
            this.circularSeal.setVisibility(0);
            this.circularSeal.setBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.already_fetch));
        }
        if (couponEntity.canUse) {
            setBtnUseColor(couponEntity.typeCoupon, couponEntity.iconButtonColor);
        } else if (!couponEntity.isReceived && couponEntity.futureBtnStatus != 2) {
            setBtnRecieve(couponEntity.typeCoupon);
        } else {
            setBtnUseColor(couponEntity.typeCoupon, couponEntity.iconButtonColor);
        }
    }

    protected void setValueBackgroundDrawable(int i2) {
        this.valueBackground.setBackgroundResource(i2);
        this.valueBackground.setImageResource(0);
    }

    public void setLableDrawable(Drawable drawable) {
        if (drawable != null) {
            this.circularSeal.setVisibility(0);
            this.circularSeal.setBackgroundDrawable(drawable);
        }
    }

    public CommonCouponView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isBatchEditMode = false;
        this.isSimple = false;
        this.spacingPlaceholder = new StringBuffer();
        this.useBind = true;
        initView();
        initListener();
        initDynView();
    }

    protected void setBtnUseColor(int i2) {
        GradientDrawable gradientDrawable = (GradientDrawable) this.context.getResources().getDrawable(R.drawable.use_button_background);
        int dimension = (int) this.context.getResources().getDimension(R.dimen.useButtonFrameWidth);
        if (dimension < 1) {
            dimension = 1;
        }
        gradientDrawable.setStroke(dimension, i2);
        this.use.setBackgroundDrawable(gradientDrawable);
        this.use.setTextColor(i2);
    }

    public CommonCouponView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.isBatchEditMode = false;
        this.isSimple = false;
        this.spacingPlaceholder = new StringBuffer();
        this.useBind = true;
        initView();
        initListener();
        initDynView();
    }
}
