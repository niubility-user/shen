package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/* loaded from: classes6.dex */
public class ColumnHorizontalScrollView extends HorizontalScrollView {
    private Activity activity;
    private ImageView leftImage;
    private View ll_content;
    private View ll_more;
    private int mScreenWitdh;
    private ImageView rightImage;
    private View rl_column;

    public ColumnHorizontalScrollView(Context context) {
        super(context);
        this.mScreenWitdh = 0;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        View view;
        super.onScrollChanged(i2, i3, i4, i5);
        shade_ShowOrHide();
        if (this.activity.isFinishing() || (view = this.ll_content) == null || this.leftImage == null || this.rightImage == null || this.ll_more == null || this.rl_column == null) {
            return;
        }
        if (view.getWidth() <= this.mScreenWitdh) {
            this.leftImage.setVisibility(8);
            this.rightImage.setVisibility(8);
        }
        if (i2 == 0) {
            this.leftImage.setVisibility(8);
            this.rightImage.setVisibility(0);
        } else if ((this.ll_content.getWidth() - i2) + this.ll_more.getWidth() + this.rl_column.getLeft() == this.mScreenWitdh) {
            this.leftImage.setVisibility(0);
            this.rightImage.setVisibility(8);
        } else {
            this.leftImage.setVisibility(0);
            this.rightImage.setVisibility(0);
        }
    }

    public void setParam(Activity activity, int i2, View view, ImageView imageView, ImageView imageView2, View view2, View view3) {
        this.activity = activity;
        this.mScreenWitdh = i2;
        this.ll_content = view;
        this.leftImage = imageView;
        this.rightImage = imageView2;
        this.ll_more = view2;
        this.rl_column = view3;
    }

    public void shade_ShowOrHide() {
        if (this.activity.isFinishing() || this.ll_content == null) {
            return;
        }
        measure(0, 0);
        if (this.mScreenWitdh >= getMeasuredWidth()) {
            this.leftImage.setVisibility(8);
            this.rightImage.setVisibility(8);
        }
        if (getLeft() == 0) {
            this.leftImage.setVisibility(8);
            this.rightImage.setVisibility(0);
        } else if (getRight() == getMeasuredWidth() - this.mScreenWitdh) {
            this.leftImage.setVisibility(0);
            this.rightImage.setVisibility(8);
        } else {
            this.leftImage.setVisibility(0);
            this.rightImage.setVisibility(0);
        }
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScreenWitdh = 0;
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mScreenWitdh = 0;
    }
}
