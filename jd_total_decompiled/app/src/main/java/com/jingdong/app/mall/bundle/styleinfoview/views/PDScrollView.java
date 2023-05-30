package com.jingdong.app.mall.bundle.styleinfoview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* loaded from: classes3.dex */
public class PDScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener;

    /* loaded from: classes3.dex */
    public interface ScrollViewListener {
        void onScrollChanged(PDScrollView pDScrollView, int i2, int i3, int i4, int i5);
    }

    public PDScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scrollViewListener = null;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        ScrollViewListener scrollViewListener = this.scrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, i2, i3, i4, i5);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }
}
