package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;

/* loaded from: classes19.dex */
public class DolphinPageIndicatorView extends LinearLayout {
    private int cursorHeight;
    private int cursorLightWidth;
    private int cursorNormalWidth;
    private int cursorSpace;
    private boolean isFromUserCursor;
    private int lightResource;
    private Context mContext;
    private int normalResource;
    private int oldCursorPosition;

    public DolphinPageIndicatorView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void closeLight(int i2) {
        ImageView imageView = (ImageView) getChildAt(i2);
        if (imageView != null) {
            imageView.setImageResource(this.normalResource);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorNormalWidth, this.cursorHeight);
            layoutParams.setMargins(0, 0, this.cursorSpace, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
        }
    }

    private void init() {
        this.lightResource = R.drawable.dolphin_cursor_white;
        this.normalResource = R.drawable.dolphin_cursor_gray;
        setOrientation(0);
        this.cursorLightWidth = DPIUtil.dip2px(this.mContext, 12.0f);
        this.cursorNormalWidth = DPIUtil.dip2px(this.mContext, 3.0f);
        this.cursorHeight = DPIUtil.dip2px(this.mContext, 1.8f);
        this.cursorSpace = DPIUtil.dip2px(this.mContext, 3.0f);
    }

    public void createCursor(int i2) {
        if (i2 < 2) {
            setVisibility(8);
            return;
        }
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorNormalWidth, this.cursorHeight);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, 0, this.cursorSpace, 0);
        for (int i3 = 0; i3 < i2 && i2 > 1; i3++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(layoutParams);
            if (!this.isFromUserCursor) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
            }
            imageView.setImageResource(this.normalResource);
            addView(imageView);
        }
    }

    public void openLight(int i2) {
        closeLight(this.oldCursorPosition);
        ImageView imageView = (ImageView) getChildAt(i2);
        if (imageView != null) {
            imageView.setImageResource(this.lightResource);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorLightWidth, this.cursorHeight);
            layoutParams.setMargins(0, 0, this.cursorSpace, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
        }
        this.oldCursorPosition = i2;
    }

    public void setCursor(int i2, int i3, int i4, int i5) {
        if (i2 > 0) {
            this.cursorLightWidth = i2;
        }
        if (i3 > 0) {
            this.cursorNormalWidth = i3;
        }
        if (i4 > 0) {
            this.cursorHeight = i4;
        }
        this.cursorSpace = i5;
    }

    public DolphinPageIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    public void setCursor(int i2) {
        this.cursorSpace = i2;
    }

    public DolphinPageIndicatorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }
}
