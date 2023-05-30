package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class CursorLayout extends LinearLayout {
    private int cursorHeight;
    private int cursorSpace;
    private int cursorWidth;
    private boolean isFromUserCursor;
    private int lightResource;
    private int normalResource;
    private int oldCursorPosition;

    public CursorLayout(Context context) {
        super(context);
        this.cursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorHeight = DPIUtil.dip2px(13.0f);
        this.cursorSpace = DPIUtil.dip2px(7.0f);
        this.lightResource = R.drawable.icon_gallery_point_white;
        this.normalResource = R.drawable.icon_gallery_point_grey;
        setOrientation(0);
    }

    private void closeLight(int i2) {
        ImageView imageView = (ImageView) getChildAt(i2);
        if (imageView != null) {
            imageView.setImageResource(this.normalResource);
        }
    }

    public void createCursor(int i2) {
        if (OKLog.D) {
            OKLog.d("TAG", " -->> createCursor size = " + i2);
        }
        if (i2 < 2) {
            setVisibility(8);
            return;
        }
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorWidth, this.cursorHeight);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, 0, this.cursorSpace, 0);
        for (int i3 = 0; i3 < i2 && i2 > 1; i3++) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
            simpleDraweeView.setLayoutParams(layoutParams);
            if (!this.isFromUserCursor) {
                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER);
            }
            simpleDraweeView.setImageResource(this.normalResource);
            addView(simpleDraweeView);
        }
    }

    public void openLight(int i2) {
        closeLight(this.oldCursorPosition);
        ImageView imageView = (ImageView) getChildAt(i2);
        if (imageView != null) {
            imageView.setImageResource(this.lightResource);
        }
        this.oldCursorPosition = i2;
    }

    public void setCursor(int i2, int i3, int i4, int i5, int i6) {
        if (i2 > 0) {
            this.cursorWidth = i2;
        }
        if (i3 > 0) {
            this.cursorHeight = i3;
        }
        if (i4 > 0) {
            this.cursorSpace = i4;
        }
        if (i5 > 0) {
            this.isFromUserCursor = true;
            this.lightResource = i5;
        }
        if (i6 > 0) {
            this.isFromUserCursor = true;
            this.normalResource = i6;
        }
    }

    public CursorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorHeight = DPIUtil.dip2px(13.0f);
        this.cursorSpace = DPIUtil.dip2px(7.0f);
        this.lightResource = R.drawable.icon_gallery_point_white;
        this.normalResource = R.drawable.icon_gallery_point_grey;
        setOrientation(0);
    }

    public CursorLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.cursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorHeight = DPIUtil.dip2px(13.0f);
        this.cursorSpace = DPIUtil.dip2px(7.0f);
        this.lightResource = R.drawable.icon_gallery_point_white;
        this.normalResource = R.drawable.icon_gallery_point_grey;
        setOrientation(0);
    }
}
