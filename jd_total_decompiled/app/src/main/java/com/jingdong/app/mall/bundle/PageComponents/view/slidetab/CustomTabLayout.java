package com.jingdong.app.mall.bundle.PageComponents.view.slidetab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.List;

/* loaded from: classes19.dex */
public class CustomTabLayout extends TabLayout {
    private List<ITabHolder> tabViewList;

    /* loaded from: classes19.dex */
    private static class ThisTabSelectedListener implements TabLayout.OnTabSelectedListener {
        private ThisTabSelectedListener() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            View customView = tab.getCustomView();
            if (customView instanceof AbsTabHolder) {
                ((AbsTabHolder) customView).selected();
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            View customView = tab.getCustomView();
            if (customView instanceof AbsTabHolder) {
                ((AbsTabHolder) customView).unSelect();
            }
        }
    }

    public CustomTabLayout(Context context) {
        this(context, null);
    }

    private void setClick(View view, final ITabHolder iTabHolder) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            ((View) parent).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.view.slidetab.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ITabHolder.this.onClick();
                }
            });
        }
    }

    public void setTabList(ViewPager viewPager, List<ITabHolder> list) {
        this.tabViewList = list;
        setupWithViewPager(viewPager);
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean z) {
        super.setupWithViewPager(viewPager, z);
        List<ITabHolder> list = this.tabViewList;
        if (list != null && list.size() != 0) {
            setTabMode(getTabCount() > 3 ? 0 : 1);
            addOnTabSelectedListener(new ThisTabSelectedListener());
            for (int i2 = 0; i2 < getTabCount(); i2++) {
                ITabHolder iTabHolder = this.tabViewList.get(i2);
                iTabHolder.setPosition(i2);
                View tabHolderView = iTabHolder.getTabHolderView();
                getTabAt(i2).setCustomView(tabHolderView);
                setClick(tabHolderView, iTabHolder);
                if (i2 == getSelectedTabPosition()) {
                    iTabHolder.selected();
                }
            }
            return;
        }
        setVisibility(8);
    }

    public CustomTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSelectedTabIndicator(0);
    }
}
