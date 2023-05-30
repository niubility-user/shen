package com.jingdong.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import com.jingdong.common.widget.PagerSlidingTabStrip;
import java.util.List;

/* loaded from: classes12.dex */
public class PagerSlidingTabStripNoViewPage extends PagerSlidingTabStrip {
    public PagerSlidingTabStripNoViewPage(Context context) {
        super(context);
    }

    @Override // com.jingdong.common.widget.PagerSlidingTabStrip
    protected void addTab(final int i2, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.PagerSlidingTabStripNoViewPage.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PagerSlidingTabStrip.OnTabClickListener onTabClickListener = PagerSlidingTabStripNoViewPage.this.tabClickListener;
                if (onTabClickListener != null) {
                    onTabClickListener.onClick(view2, i2);
                }
                PagerSlidingTabStripNoViewPage.this.setSelect(i2);
            }
        });
        int i3 = this.tabPadding;
        view.setPadding(i3, 0, i3, 0);
        this.tabsContainer.addView(view, i2, this.shouldExpand ? this.expandedTabLayoutParams : this.defaultTabLayoutParams);
    }

    public void addTextTab(List<String> list) {
        if (list == null) {
            return;
        }
        this.tabCount = list.size();
        for (int i2 = 0; i2 < list.size(); i2++) {
            super.addTextTab(i2, list.get(i2));
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.widget.PagerSlidingTabStripNoViewPage.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PagerSlidingTabStripNoViewPage.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                PagerSlidingTabStripNoViewPage pagerSlidingTabStripNoViewPage = PagerSlidingTabStripNoViewPage.this;
                pagerSlidingTabStripNoViewPage.scrollToChild(pagerSlidingTabStripNoViewPage.currentPosition, 0);
            }
        });
    }

    public void setSelect(int i2) {
        this.selectedPosition = i2;
        this.currentPosition = i2;
        scrollToChild(i2, 0);
        updateTabStyles();
        invalidate();
    }

    public PagerSlidingTabStripNoViewPage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PagerSlidingTabStripNoViewPage(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public void addTextTab(List<String> list, int i2) {
        if (list == null) {
            return;
        }
        this.tabCount = list.size();
        for (int i3 = 0; i3 < list.size(); i3++) {
            super.addTextTab(i3, list.get(i3), i2);
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.widget.PagerSlidingTabStripNoViewPage.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PagerSlidingTabStripNoViewPage.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                PagerSlidingTabStripNoViewPage pagerSlidingTabStripNoViewPage = PagerSlidingTabStripNoViewPage.this;
                pagerSlidingTabStripNoViewPage.scrollToChild(pagerSlidingTabStripNoViewPage.currentPosition, 0);
            }
        });
    }
}
