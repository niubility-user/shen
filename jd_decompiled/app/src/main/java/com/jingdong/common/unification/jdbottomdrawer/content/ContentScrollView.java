package com.jingdong.common.unification.jdbottomdrawer.content;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ScrollView;
import com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer;

/* loaded from: classes6.dex */
public class ContentScrollView extends ScrollView {
    private OnScrollChangedListener listener;

    /* loaded from: classes6.dex */
    public interface OnScrollChangedListener {
        void onScrollChanged(int i2, int i3, int i4, int i5);
    }

    public ContentScrollView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof JDBottomDrawer) {
                ((JDBottomDrawer) parent).setAssociatedScrollView(this);
                return;
            }
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        this.listener.onScrollChanged(i2, i3, i4, i5);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if ((parent instanceof JDBottomDrawer) && ((JDBottomDrawer) parent).getCurrentStatus() == JDBottomDrawer.Status.OPENED) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnScrollChangeListener(OnScrollChangedListener onScrollChangedListener) {
        this.listener = onScrollChangedListener;
    }

    public ContentScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ContentScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
