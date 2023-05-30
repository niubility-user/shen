package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.entity.BusinessItemEntity;

/* loaded from: classes10.dex */
public class CommonBusinessItemView extends LinearLayout {
    private ImageView mBusinessImg;
    private PdAutoChangeTextSize mBusinessName;
    private PdAutoChangeTextSize mBusinessSubName;

    public CommonBusinessItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bindData2View(BusinessItemEntity businessItemEntity) {
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.resetViewBeforeLoading(false);
        createSimple.setPlaceholder(3);
        JDImageUtils.displayImage(businessItemEntity.icon, this.mBusinessImg, createSimple);
        this.mBusinessName.setText(businessItemEntity.title);
        this.mBusinessSubName.setText(businessItemEntity.desc);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBusinessImg = (SimpleDraweeView) findViewById(R.id.platform_business_item_img);
        this.mBusinessName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_item_name);
        this.mBusinessSubName = (PdAutoChangeTextSize) findViewById(R.id.platform_business_item_subname);
    }
}
