package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class UiModeLinearLayout extends LinearLayout {
    private static final int SELECTOR_NUMBER = 1;
    private static final int[] STATE_UI_MODE = {R.attrprivate.da};
    private int uiMode;

    public UiModeLinearLayout(Context context) {
        this(context, null);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UiModeLinearLayout);
        this.uiMode = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }

    public int getUiMode() {
        return this.uiMode;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.uiMode == 1) {
            LinearLayout.mergeDrawableStates(onCreateDrawableState, STATE_UI_MODE);
        }
        return onCreateDrawableState;
    }

    public void setUiMode(int i2) {
        if (this.uiMode != i2) {
            this.uiMode = i2;
            refreshDrawableState();
        }
    }

    public UiModeLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public UiModeLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public UiModeLinearLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.uiMode = -1;
        initView(context, attributeSet);
    }
}
