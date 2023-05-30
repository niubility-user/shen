package com.facebook.drawee.span;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class SimpleDraweeSpanTextView extends TextView {
    private DraweeSpanStringBuilder mDraweeStringBuilder;
    private boolean mIsAttached;

    public SimpleDraweeSpanTextView(Context context) {
        super(context);
        this.mIsAttached = false;
    }

    public SimpleDraweeSpanTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsAttached = false;
    }

    public SimpleDraweeSpanTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsAttached = false;
    }

    public void detachCurrentDraweeSpanStringBuilder() {
        DraweeSpanStringBuilder draweeSpanStringBuilder = this.mDraweeStringBuilder;
        if (draweeSpanStringBuilder != null) {
            draweeSpanStringBuilder.onDetachFromView(this);
        }
        this.mDraweeStringBuilder = null;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttached = true;
        DraweeSpanStringBuilder draweeSpanStringBuilder = this.mDraweeStringBuilder;
        if (draweeSpanStringBuilder != null) {
            draweeSpanStringBuilder.onAttachToView(this);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mIsAttached = false;
        DraweeSpanStringBuilder draweeSpanStringBuilder = this.mDraweeStringBuilder;
        if (draweeSpanStringBuilder != null) {
            draweeSpanStringBuilder.onDetachFromView(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.mIsAttached = true;
        DraweeSpanStringBuilder draweeSpanStringBuilder = this.mDraweeStringBuilder;
        if (draweeSpanStringBuilder != null) {
            draweeSpanStringBuilder.onAttachToView(this);
        }
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        this.mIsAttached = false;
        DraweeSpanStringBuilder draweeSpanStringBuilder = this.mDraweeStringBuilder;
        if (draweeSpanStringBuilder != null) {
            draweeSpanStringBuilder.onDetachFromView(this);
        }
        super.onStartTemporaryDetach();
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        detachCurrentDraweeSpanStringBuilder();
    }

    public void setDraweeSpanStringBuilder(DraweeSpanStringBuilder draweeSpanStringBuilder) {
        setText(draweeSpanStringBuilder, TextView.BufferType.SPANNABLE);
        this.mDraweeStringBuilder = draweeSpanStringBuilder;
        if (draweeSpanStringBuilder == null || !this.mIsAttached) {
            return;
        }
        draweeSpanStringBuilder.onAttachToView(this);
    }
}
