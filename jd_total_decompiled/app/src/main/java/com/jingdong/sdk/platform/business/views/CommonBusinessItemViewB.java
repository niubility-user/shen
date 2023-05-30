package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.entity.BusinessItemEntity;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class CommonBusinessItemViewB extends LinearLayout {
    private ImageView mBusinessImg;
    private PdAutoChangeTextSize mBusinessName;
    private PdAutoChangeTextSize mBusinessSubName;
    private Context mContext;
    private View mView;
    private boolean tenthRevision;

    public CommonBusinessItemViewB(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private int getColorTheme(boolean z, int i2, int i3) {
        if (!z) {
            return ContextCompat.getColor(this.mContext, i2);
        }
        return ContextCompat.getColor(this.mContext, i3);
    }

    private void setImageDark(ImageView imageView, boolean z, @ColorInt int i2) {
        GenericDraweeHierarchy hierarchy;
        if (!(imageView instanceof SimpleDraweeView) || (hierarchy = ((SimpleDraweeView) imageView).getHierarchy()) == null) {
            return;
        }
        hierarchy.setActualImageColorFilter(new PorterDuffColorFilter(i2, z ? PorterDuff.Mode.SRC_IN : PorterDuff.Mode.DST));
    }

    public void bindData2View(BusinessItemEntity businessItemEntity, boolean z, boolean z2) {
        int i2;
        Context context;
        int i3;
        int color;
        this.tenthRevision = z2;
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.resetViewBeforeLoading(false);
        createSimple.setPlaceholder(3);
        JDImageUtils.displayImage(businessItemEntity.icon, this.mBusinessImg, createSimple);
        if (this.mBusinessImg.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mBusinessImg.getLayoutParams();
            if (z2) {
                layoutParams.width = DPIUtil.dip2px(14.0f);
                layoutParams.height = DPIUtil.dip2px(12.0f);
            } else {
                layoutParams.width = DPIUtil.dip2px(19.0f);
                layoutParams.height = DPIUtil.dip2px(19.0f);
            }
            this.mBusinessImg.setLayoutParams(layoutParams);
        }
        ImageView imageView = this.mBusinessImg;
        Context context2 = this.mContext;
        int i4 = R.color.pd_color_ececec;
        setImageDark(imageView, z, ContextCompat.getColor(context2, i4));
        this.mBusinessName.setText(businessItemEntity.title);
        if (!z2) {
            i2 = getColorTheme(z, R.color.platform_color_262626, i4);
            this.mBusinessName.setTextSize(2, 13.0f);
        } else {
            int colorTheme = getColorTheme(z, R.color.platform_color_1A1A1A, i4);
            this.mBusinessName.setTextSize(2, 14.0f);
            i2 = colorTheme;
        }
        this.mBusinessName.setTextColor(i2);
        if (!z2) {
            color = getColorTheme(z, R.color.platform_color_262626, i4);
            this.mBusinessSubName.setTextSize(2, 11.0f);
        } else {
            if (z) {
                context = this.mContext;
                i3 = R.color.pd_color_848383;
            } else {
                context = this.mContext;
                i3 = R.color.platform_color_808080;
            }
            color = ContextCompat.getColor(context, i3);
            this.mBusinessSubName.setTextSize(2, 10.0f);
        }
        this.mBusinessSubName.setText(businessItemEntity.desc);
        this.mBusinessSubName.setTextColor(color);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBusinessImg = (SimpleDraweeView) findViewById(R.id.platform_business_item_img);
        this.mBusinessName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_item_name);
        this.mBusinessSubName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_item_subname);
        this.mView = findViewById(R.id.platform_business_item_view);
    }

    public void setItemRightViewGone(boolean z) {
        if (z) {
            if (this.mView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mView.getLayoutParams();
                if (this.tenthRevision) {
                    layoutParams.height = DPIUtil.dip2px(24.0f);
                    layoutParams.width = DPIUtil.dip2px(1.0f);
                } else {
                    layoutParams.height = DPIUtil.dip2px(12.0f);
                    layoutParams.width = DPIUtil.dip2px(1.0f);
                }
                this.mView.setLayoutParams(layoutParams);
            }
            this.mView.setVisibility(0);
            return;
        }
        this.mView.setVisibility(8);
    }
}
