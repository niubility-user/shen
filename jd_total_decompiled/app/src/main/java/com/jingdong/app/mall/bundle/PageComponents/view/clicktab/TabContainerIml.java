package com.jingdong.app.mall.bundle.PageComponents.view.clicktab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import java.util.List;

/* loaded from: classes19.dex */
public class TabContainerIml extends LinearLayout implements ITabContainer {
    protected IFragmentContainer iFragmentContainer;

    public TabContainerIml(Context context) {
        this(context, null);
    }

    protected void addTab(List<ITab> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            addView(list.get(i2).getView());
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITabContainer
    public void selectedTab(ITab iTab) {
        IFragmentContainer iFragmentContainer = this.iFragmentContainer;
        if (iFragmentContainer == null) {
            return;
        }
        iFragmentContainer.selectedTab(iTab);
    }

    public void setFragmentContainer(IFragmentContainer iFragmentContainer) {
        this.iFragmentContainer = iFragmentContainer;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITabContainer
    public void setTabList(List<ITab> list, ITab iTab) {
        if (list != null && list.size() != 0) {
            setVisibility(0);
            removeAllViews();
            addTab(list);
            IFragmentContainer iFragmentContainer = this.iFragmentContainer;
            if (iFragmentContainer == null) {
                return;
            }
            iFragmentContainer.reset();
            selectedTab(iTab);
            return;
        }
        setVisibility(8);
    }

    public TabContainerIml(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
