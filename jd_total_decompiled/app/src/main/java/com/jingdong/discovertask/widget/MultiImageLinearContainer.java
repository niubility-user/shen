package com.jingdong.discovertask.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class MultiImageLinearContainer extends HorizontalScrollView {
    private Context mContext;
    private List<ImageView> mImageViews;
    private int mImgHeight;
    private int mImgWidth;
    private int mLeftMargin;
    private List<String> mUrls;

    public MultiImageLinearContainer(Context context) {
        this(context, null);
    }

    private void createAndAddImageView() {
        this.mImageViews.clear();
        removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        addView(linearLayout, new ViewGroup.LayoutParams(-2, -1));
        for (int i2 = 0; i2 < this.mUrls.size(); i2++) {
            JDCircleImageView jDCircleImageView = new JDCircleImageView(this.mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mImgWidth, this.mImgHeight);
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(R2.attr.internalMinHeight));
            if (i2 != 0) {
                layoutParams.setMargins(this.mLeftMargin, 0, 0, 0);
            }
            JDImageUtils.displayImage(this.mUrls.get(i2), jDCircleImageView, jDDisplayImageOptions);
            this.mImageViews.add(jDCircleImageView);
            linearLayout.addView(jDCircleImageView, layoutParams);
        }
    }

    private void init() {
        this.mImageViews = new ArrayList();
        this.mUrls = new ArrayList();
        this.mImgWidth = DPIUtil.dip2px(44.5f);
        this.mImgHeight = DPIUtil.dip2px(44.5f);
        this.mLeftMargin = DPIUtil.dip2px(10.0f);
    }

    public MultiImageLinearContainer setData(List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.mUrls = list;
        }
        return this;
    }

    public MultiImageLinearContainer setHeight(int i2) {
        this.mImgHeight = i2;
        return this;
    }

    public MultiImageLinearContainer setLeftMargin(int i2) {
        this.mLeftMargin = i2;
        return this;
    }

    public MultiImageLinearContainer setWidth(int i2) {
        this.mImgWidth = i2;
        return this;
    }

    public void show() {
        createAndAddImageView();
    }

    public MultiImageLinearContainer(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiImageLinearContainer(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mImgWidth = 0;
        this.mImgHeight = 0;
        this.mLeftMargin = 0;
        this.mContext = context;
        init();
    }
}
