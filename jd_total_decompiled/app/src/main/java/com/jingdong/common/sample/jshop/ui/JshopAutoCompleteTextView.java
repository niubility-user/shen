package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class JshopAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private static final int SELECTOR_NUMBER = 1;
    private static final int[] STATE_UI_MODE = {R.attrprivate.ui_mode};
    private int uiMode;

    public JshopAutoCompleteTextView(Context context) {
        this(context, null);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JshopAutoCompleteTextView);
        this.uiMode = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }

    public int getUiMode() {
        return this.uiMode;
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (this.uiMode == 1) {
            AutoCompleteTextView.mergeDrawableStates(onCreateDrawableState, STATE_UI_MODE);
        }
        return onCreateDrawableState;
    }

    public void setUiMode(int i2) {
        if (this.uiMode != i2) {
            this.uiMode = i2;
            refreshDrawableState();
        }
    }

    public JshopAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.uiMode = -1;
        initView(context, attributeSet);
    }
}
