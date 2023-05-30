package com.jingdong.common.widget.shadow.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.widget.shadow.ShadowLayout;

/* loaded from: classes12.dex */
public class JDShadowView<T extends View> extends ShadowLayout {
    private boolean mIsOriginCustom;
    private Drawable mOriginBackground;
    protected T mOriginView;
    private int mOriginViewHeight;
    private int mOriginViewWidth;

    public JDShadowView(Context context) {
        this(context, null);
    }

    private void attachedOriginView() {
        T createOriginView = createOriginView();
        if (createOriginView != null) {
            this.mOriginView = createOriginView;
            removeAllViews();
            addView(this.mOriginView);
        }
    }

    private void init(AttributeSet attributeSet) {
        attachedOriginView();
        parseAttrs(attributeSet);
        tryConfigOriginView();
    }

    private void parseAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout);
        this.mIsOriginCustom = obtainStyledAttributes.getBoolean(R.styleable.ShadowLayout_shadowOriginCustomConfig, false);
        this.mOriginViewWidth = obtainStyledAttributes.getLayoutDimension(R.styleable.ShadowLayout_shadowOriginWidth, -2);
        this.mOriginViewHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.ShadowLayout_shadowOriginHeight, -2);
        this.mOriginBackground = obtainStyledAttributes.getDrawable(R.styleable.ShadowLayout_shadowOriginBackground);
        obtainStyledAttributes.recycle();
    }

    private void tryConfigOriginView() {
        T t = this.mOriginView;
        if (t == null || this.mIsOriginCustom) {
            return;
        }
        t.setLayoutParams(new FrameLayout.LayoutParams(this.mOriginViewWidth, this.mOriginViewHeight));
        this.mOriginView.setBackgroundDrawable(this.mOriginBackground);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        removeAllViews();
        super.addView(view, i2, layoutParams);
        this.mOriginView = (T) getChildAt(0);
        tryConfigOriginView();
    }

    public T createOriginView() {
        return null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() <= 1) {
            if (getChildCount() == 0) {
                return;
            }
            this.mOriginView = (T) getChildAt(0);
            tryConfigOriginView();
            return;
        }
        throw new IllegalArgumentException("Only one sub-layout is allowed!");
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i2) {
        T t = this.mOriginView;
        if (t != null) {
            t.setBackgroundColor(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        T t = this.mOriginView;
        if (t != null) {
            t.setBackgroundDrawable(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@IdRes int i2) {
        T t = this.mOriginView;
        if (t != null) {
            t.setBackgroundResource(i2);
        }
    }

    public JDShadowView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDShadowView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsOriginCustom = false;
        this.mOriginViewWidth = -2;
        this.mOriginViewHeight = -2;
        this.mOriginBackground = null;
        init(attributeSet);
    }
}
