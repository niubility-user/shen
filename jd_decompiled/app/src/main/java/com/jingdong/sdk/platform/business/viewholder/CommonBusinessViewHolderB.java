package com.jingdong.sdk.platform.business.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.adapter.BusinessPagerAdapterB;
import com.jingdong.sdk.platform.business.entity.BusinessEntity;
import com.jingdong.sdk.platform.business.entity.BusinessItemEntity;
import com.jingdong.sdk.platform.business.entity.BusinessSlideStyle;
import com.jingdong.sdk.platform.business.utils.BusinessTools;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.business.views.PageImageIndicator;
import com.jingdong.sdk.platform.business.views.PagerIndicator;
import com.jingdong.sdk.platform.business.views.PdAutoChangeTextSize;
import com.jingdong.sdk.platform.constant.PlatformActionEvents;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes10.dex */
public class CommonBusinessViewHolderB extends BaseViewHolder implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final int CLICK_TYPE_ONE = 1;
    private static final int CLICK_TYPE_THREE = 3;
    private static final int CLICK_TYPE_TWO = 2;
    private String eventId;
    private boolean isDarkTheme;
    private boolean isTopSupport;
    private ViewPager mBusinessViewPager;
    private PageImageIndicator mImageIndicator;
    private PagerIndicator mIndicator;
    private RelativeLayout mMultipleBusinessLayout;
    private ImageView mOneBusinessArrow;
    private ImageView mOneBusinessImg;
    private RelativeLayout mOneBusinessLayout;
    private PdAutoChangeTextSize mOneBusinessName;
    private PdAutoChangeTextSize mOneBusinessSubName;
    private boolean tenthRevision;

    public CommonBusinessViewHolderB(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        super(context, baseData, str, viewGroup);
    }

    private void dealVersion10Ui(BusinessEntity businessEntity) {
        if (businessEntity.tenthRevision) {
            this.mOneBusinessLayout.setPadding(DpiUtil.dip2px(this.mContext, 10.0f), 0, DpiUtil.dip2px(this.mContext, 13.0f), 0);
            ViewGroup.LayoutParams layoutParams = this.mOneBusinessLayout.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = DpiUtil.dip2px(this.mContext, 46.0f);
                layoutParams.width = -1;
                this.mOneBusinessLayout.setLayoutParams(layoutParams);
            }
            if (this.mOneBusinessImg.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mOneBusinessImg.getLayoutParams();
                layoutParams2.width = DPIUtil.dip2px(14.0f);
                layoutParams2.height = DPIUtil.dip2px(12.0f);
                this.mOneBusinessImg.setLayoutParams(layoutParams2);
            }
            if (this.mOneBusinessName.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mOneBusinessName.getLayoutParams();
                layoutParams3.setMargins(DPIUtil.dip2px(4.0f), DPIUtil.dip2px(0.0f), DPIUtil.dip2px(17.0f), DPIUtil.dip2px(0.0f));
                this.mOneBusinessName.setTextSize(2, 14.0f);
                this.mOneBusinessName.setLayoutParams(layoutParams3);
            }
            if (this.mOneBusinessSubName.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mOneBusinessSubName.getLayoutParams();
                layoutParams4.setMargins(0, DPIUtil.dip2px(0.0f), DPIUtil.dip2px(10.0f), 0);
                this.mOneBusinessSubName.setTextSize(2, 12.0f);
                this.mOneBusinessSubName.setLayoutParams(layoutParams4);
                return;
            }
            return;
        }
        this.mOneBusinessLayout.setPadding(DpiUtil.dip2px(this.mContext, 18.0f), 0, DpiUtil.dip2px(this.mContext, 18.0f), 0);
        ViewGroup.LayoutParams layoutParams5 = this.mOneBusinessLayout.getLayoutParams();
        if (layoutParams5 != null) {
            layoutParams5.height = DpiUtil.dip2px(this.mContext, 61.0f);
            layoutParams5.width = -1;
            this.mOneBusinessLayout.setLayoutParams(layoutParams5);
        }
        if (this.mOneBusinessImg.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mOneBusinessImg.getLayoutParams();
            layoutParams6.height = DPIUtil.dip2px(19.0f);
            layoutParams6.width = DPIUtil.dip2px(19.0f);
            this.mOneBusinessImg.setLayoutParams(layoutParams6);
        }
        if (this.mOneBusinessName.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mOneBusinessName.getLayoutParams();
            layoutParams7.setMargins(DPIUtil.dip2px(2.0f), DPIUtil.dip2px(0.0f), DPIUtil.dip2px(13.0f), 0);
            this.mOneBusinessName.setTextSize(2, 13.0f);
            this.mOneBusinessName.setLayoutParams(layoutParams7);
        }
        if (this.mOneBusinessSubName.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) this.mOneBusinessSubName.getLayoutParams();
            layoutParams8.setMargins(0, DPIUtil.dip2px(0.0f), DPIUtil.dip2px(10.0f), 0);
            this.mOneBusinessSubName.setTextSize(2, 11.0f);
            this.mOneBusinessSubName.setLayoutParams(layoutParams8);
        }
    }

    private void setImageDark(ImageView imageView, boolean z, @ColorInt int i2) {
        GenericDraweeHierarchy hierarchy;
        if (!(imageView instanceof SimpleDraweeView) || (hierarchy = ((SimpleDraweeView) imageView).getHierarchy()) == null) {
            return;
        }
        hierarchy.setActualImageColorFilter(new PorterDuffColorFilter(i2, z ? PorterDuff.Mode.SRC_IN : PorterDuff.Mode.DST));
    }

    private void showOneBusiness(boolean z, boolean z2, BusinessItemEntity businessItemEntity) {
        int i2;
        int colorValue;
        int colorValue2;
        String str = z2 ? "#1d1b1b" : businessItemEntity.bgColor;
        if (z) {
            i2 = z2 ? R.color.platform_color_181616 : R.color.platform_color_f9f9f9;
        } else {
            i2 = z2 ? R.color.pd_color_1d1b1b : R.color.platform_color_white;
        }
        if (getBaseEntity() instanceof BaseTemplateEntity) {
            ViewCompat.setBackground(this.mOneBusinessLayout, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), str, ContextCompat.getColor(this.mContext, i2), z2, z));
        } else {
            this.mOneBusinessLayout.setBackgroundColor(PlatformTools.getColorValue(str, i2));
        }
        this.mOneBusinessLayout.setTag(businessItemEntity);
        this.mOneBusinessLayout.setOnClickListener(this);
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.resetViewBeforeLoading(false);
        createSimple.setPlaceholder(3);
        JDImageUtils.displayImage(businessItemEntity.icon, this.mOneBusinessImg, createSimple);
        ImageView imageView = this.mOneBusinessImg;
        Context context = getContext();
        int i3 = R.color.pd_color_ececec;
        setImageDark(imageView, z2, ContextCompat.getColor(context, i3));
        if (z) {
            if (z2) {
                colorValue = ContextCompat.getColor(this.mContext, i3);
            } else {
                colorValue = PlatformTools.getColorValue(businessItemEntity.titleColor, getResources().getColor(R.color.platform_color_1d1e1e));
            }
        } else if (z2) {
            colorValue = ContextCompat.getColor(this.mContext, i3);
        } else {
            colorValue = PlatformTools.getColorValue(businessItemEntity.titleColor, getResources().getColor(R.color.platform_color_1A1A1A));
        }
        this.mOneBusinessName.setTextColor(colorValue);
        this.mOneBusinessName.setText(businessItemEntity.title);
        if (z) {
            if (z2) {
                colorValue2 = ContextCompat.getColor(this.mContext, R.color.platform_color_848383);
            } else {
                colorValue2 = PlatformTools.getColorValue(businessItemEntity.descColor, getResources().getColor(R.color.platform_color_808080));
            }
        } else if (z2) {
            colorValue2 = ContextCompat.getColor(this.mContext, i3);
        } else {
            colorValue2 = PlatformTools.getColorValue(businessItemEntity.descColor, getResources().getColor(R.color.platform_color_1d1e1e));
        }
        this.mOneBusinessSubName.setTextColor(colorValue2);
        this.mOneBusinessSubName.setText(businessItemEntity.desc);
        if (TextUtils.isEmpty(businessItemEntity.tailIcon)) {
            if (!z) {
                this.mOneBusinessArrow.setImageResource(z2 ? R.drawable.platform_right_arrow_dark : R.drawable.platform_arrow_r_b);
            } else if (z2) {
                this.mOneBusinessArrow.setImageResource(R.drawable.platform_right_arrow_dark);
            } else {
                this.mOneBusinessArrow.setImageResource(R.drawable.platfom_arrow_right_v10);
            }
        } else if (!z) {
            JDImageUtils.displayImage(businessItemEntity.tailIcon, this.mOneBusinessArrow);
            setImageDark(this.mOneBusinessArrow, z2, ContextCompat.getColor(getContext(), i3));
        } else if (z2) {
            this.mOneBusinessArrow.setImageResource(R.drawable.platform_right_arrow_dark);
        } else {
            this.mOneBusinessArrow.setImageResource(R.drawable.platfom_arrow_right_v10);
        }
    }

    public void bindDataView(BusinessEntity businessEntity) {
        if (businessEntity != null && businessEntity.isEffective()) {
            this.tenthRevision = businessEntity.tenthRevision;
            dealVersion10Ui(businessEntity);
            this.eventId = businessEntity.eventId;
            this.isDarkTheme = businessEntity.darkModel;
            List<BusinessItemEntity> dealData = businessEntity.dealData();
            businessEntity.bizList = dealData;
            int size = dealData.size();
            this.mOneBusinessLayout.setVisibility(size == 1 ? 0 : 8);
            this.mMultipleBusinessLayout.setVisibility(size > 1 ? 0 : 8);
            if (businessEntity.tenthRevision) {
                this.mImageIndicator.setVisibility(size >= 4 ? 0 : 8);
                this.mIndicator.setVisibility(8);
            } else {
                this.mIndicator.setVisibility(size >= 4 ? 0 : 8);
                this.mImageIndicator.setVisibility(8);
            }
            if (size == 1) {
                showOneBusiness(businessEntity.tenthRevision, businessEntity.darkModel, businessEntity.bizList.get(0));
            } else {
                if (this.isDarkTheme) {
                    int color = ContextCompat.getColor(this.mContext, R.color.pd_color_1d1b1b);
                    if (getBaseEntity() instanceof BaseTemplateEntity) {
                        ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), color, this.isDarkTheme, this.tenthRevision));
                    } else {
                        this.mRootView.setBackgroundColor(color);
                    }
                }
                BusinessPagerAdapterB businessPagerAdapterB = new BusinessPagerAdapterB(this.mContext);
                businessPagerAdapterB.setData(businessEntity.bizList, this.isDarkTheme, businessEntity.tenthRevision);
                businessPagerAdapterB.setItemOnClickListener(this);
                this.mBusinessViewPager.setAdapter(businessPagerAdapterB);
                this.mImageIndicator.setPageCount(businessPagerAdapterB.getCount());
                this.mImageIndicator.setPageIndex(0);
                this.mIndicator.setIndicatorCount(businessPagerAdapterB.getCount());
                BusinessSlideStyle businessSlideStyle = businessEntity.slide;
                if (businessSlideStyle != null) {
                    this.mIndicator.setSelectedIndicatorColor(PlatformTools.getColorValue(businessSlideStyle.slideSelect, getResources().getColor(R.color.platform_color_f23030)));
                    this.mIndicator.setUnSelectedIndicatorColor(this.isDarkTheme ? ContextCompat.getColor(this.mContext, R.color.pd_color_302E2E) : PlatformTools.getColorValue(businessEntity.slide.slideColor, getResources().getColor(R.color.platform_color_e3e5e9)));
                }
                this.mIndicator.setIndicatorIndex(0);
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(getBaseEntity().getMtaParam());
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            if (size != 1 || TextUtils.isEmpty(businessEntity.bizList.get(0).bizKey)) {
                for (int i2 = 0; i2 < size; i2++) {
                    if (!TextUtils.isEmpty(businessEntity.bizList.get(i2).bizKey)) {
                        sb.append(businessEntity.bizList.get(i2).bizKey);
                        if (i2 < size - 1) {
                            sb.append("-");
                        }
                    }
                }
            } else {
                sb.append(businessEntity.bizList.get(0).bizKey);
            }
            runOnAttachToWindow(new Runnable() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonBusinessViewHolderB.1
                @Override // java.lang.Runnable
                public void run() {
                    if (((BaseViewHolder) CommonBusinessViewHolderB.this).mIsDestroy) {
                        return;
                    }
                    if (CommonBusinessViewHolderB.this.isTopSupport) {
                        PlatformEventUtils.sendMtaEvent(CommonBusinessViewHolderB.this.getBaseData().mManageKey, new MtaParams(CommonBusinessViewHolderB.this.eventId + "_bpIndependentBPFloorExpo", sb.toString()));
                        return;
                    }
                    PlatformEventUtils.sendMtaEvent(CommonBusinessViewHolderB.this.getBaseData().mManageKey, new MtaParams(CommonBusinessViewHolderB.this.eventId + "_bpBusinessPlatform_Expo", sb.toString()));
                }
            });
            return;
        }
        hideViewHolder();
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
        this.mOneBusinessLayout = (RelativeLayout) findViewById(R.id.platform_business_one_layout);
        this.mOneBusinessImg = (SimpleDraweeView) findViewById(R.id.platform_business_one_img);
        this.mOneBusinessName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_one_name);
        this.mOneBusinessSubName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_one_subname);
        this.mOneBusinessArrow = (SimpleDraweeView) findViewById(R.id.platform_business_arrow);
        this.mMultipleBusinessLayout = (RelativeLayout) findViewById(R.id.platform_business_multiple_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.platform_business_viewpager);
        this.mBusinessViewPager = viewPager;
        viewPager.setOffscreenPageLimit(3);
        this.mBusinessViewPager.addOnPageChangeListener(this);
        this.mIndicator = (PagerIndicator) findViewById(R.id.platform_business_indicator);
        this.mImageIndicator = (PageImageIndicator) findViewById(R.id.platform_business_image_indicator);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        final BusinessItemEntity businessItemEntity;
        if (!(view.getTag() instanceof BusinessItemEntity) || (businessItemEntity = (BusinessItemEntity) view.getTag()) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getBaseEntity().getMtaParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(businessItemEntity.bizKey);
        if (this.isTopSupport) {
            PlatformEventUtils.sendMtaEvent(getBaseData().mManageKey, new MtaParams(this.eventId + "_bpIndependentBPFloor", sb.toString()));
        } else {
            PlatformEventUtils.sendMtaEvent(getBaseData().mManageKey, new MtaParams(this.eventId + "_bpBusinessPlatform", sb.toString()));
        }
        int i2 = businessItemEntity.type;
        if (i2 == 3) {
            PlatformEventUtils.sendEventToOwner(getBaseData().mManageKey, PlatformActionEvents.EVENT_PLATFORM_SDK_COMMON_BUSINESS_CLICK_TYPE_OTHER, businessItemEntity.jumpData);
        } else if (i2 != 1) {
            if (i2 == 2) {
                JumpHelper.jump(this.mContext, businessItemEntity.url, i2);
            }
        } else if (businessItemEntity.mustLogin && (this.mContext instanceof BaseActivity)) {
            LoginUserHelper.getInstance().executeLoginRunnable((BaseActivity) this.mContext, new Runnable() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonBusinessViewHolderB.2
                @Override // java.lang.Runnable
                public void run() {
                    Context context = ((LifecycleBaseViewHolder) CommonBusinessViewHolderB.this).mContext;
                    BusinessItemEntity businessItemEntity2 = businessItemEntity;
                    JumpHelper.jump(context, businessItemEntity2.url, businessItemEntity2.type);
                }
            }, "", false);
        } else {
            JumpHelper.jump(this.mContext, businessItemEntity.url, i2);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
        this.mLayoutId = R.layout.lib_platform_floor_business_b;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
        this.mOneBusinessLayout = null;
        this.mOneBusinessImg = null;
        this.mOneBusinessArrow = null;
        this.mOneBusinessName = null;
        this.mOneBusinessSubName = null;
        this.mMultipleBusinessLayout = null;
        this.mBusinessViewPager = null;
        this.mIndicator = null;
        this.mImageIndicator = null;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        this.mIndicator.setIndicatorIndex(i2);
        this.mImageIndicator.setPageIndex(i2);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void setBackGroundColor() {
        if (!canSetBg() || this.mRootView == null || isLine()) {
            return;
        }
        V v = this.mFloorEntity;
        int i2 = 0;
        if (v != 0) {
            String str = v.backgroundColor;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i2 = Color.parseColor(str);
                } catch (Exception e2) {
                    PlatformTools.catchException(e2);
                }
            }
        }
        if (i2 == 0) {
            V v2 = this.mFloorEntity;
            if (v2 == 0 || v2.canUseDefaultBgColor) {
                int backGroundColor = getBackGroundColor();
                if (backGroundColor == 0) {
                    backGroundColor = Color.parseColor(JDDarkUtil.COLOR_FFFFFFF);
                }
                if (getBaseEntity() instanceof BaseTemplateEntity) {
                    ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), backGroundColor));
                    return;
                }
                this.mRootView.setBackgroundColor(backGroundColor);
            }
        } else if (getBaseEntity() instanceof BaseTemplateEntity) {
            ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), i2));
        } else {
            this.mRootView.setBackgroundColor(i2);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseEntity baseEntity) {
        if (TextUtils.equals(baseEntity.mId, BaseFloorConstant.PLATFORM_FLOOR_TOP_BUSINESS)) {
            this.isTopSupport = true;
        } else if (TextUtils.equals(baseEntity.mId, BaseFloorConstant.PLATFORM_FLOOR_BUSINESS)) {
            this.isTopSupport = false;
        }
        Object obj = baseEntity.mData;
        if (obj != null) {
            bindDataView((BusinessEntity) JDJSON.parseObject(obj.toString(), BusinessEntity.class));
        }
    }
}
