package com.jingdong.app.mall.bundle.PageComponents.view.clicktab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes19.dex */
public abstract class AbsTab extends ConstraintLayout implements ITab {
    private IFragmentContainer iFragmentContainer;
    private int position;
    private boolean selected;

    public AbsTab(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(View view) {
        click();
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITab
    public void click() {
        IFragmentContainer iFragmentContainer;
        if (this.selected || (iFragmentContainer = this.iFragmentContainer) == null) {
            return;
        }
        iFragmentContainer.selectedTab(this);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITab
    public int getPosition() {
        return this.position;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITab
    public View getView() {
        return this;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITabState
    public void selected() {
        this.selected = true;
    }

    public void setFragmentContainerIml(IFragmentContainer iFragmentContainer, int i2) {
        this.iFragmentContainer = iFragmentContainer;
        this.position = i2;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.ITabState
    public void unSelect() {
        this.selected = false;
    }

    public AbsTab(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.view.clicktab.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AbsTab.this.b(view);
            }
        });
    }
}
