package com.jingdong.app.mall.bundle.PageComponents.view.slidetab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* loaded from: classes19.dex */
public abstract class AbsTabHolder<TabView extends View, Entity> extends LinearLayout implements ITabHolder<TabView, Entity> {
    private View indicator;
    protected int position;
    private TabView tabView;

    public AbsTabHolder(Context context) {
        this(context, null);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabHolder
    public ImageView createIndicator() {
        ImageView imageView = new ImageView(getContext());
        imageView.setVisibility(4);
        return imageView;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabHolder
    public View getTabHolderView() {
        return this;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabHolder
    public TabView getTabView() {
        return this.tabView;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabState
    public void onClick() {
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabState
    public void selected() {
        View view = this.indicator;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabHolder
    public ITabHolder<TabView, Entity> setPosition(int i2) {
        this.position = i2;
        return this;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabHolder
    public ITabHolder<TabView, Entity> setupTabHolder(Entity entity) {
        removeAllViews();
        TabView createTabView = createTabView(entity);
        this.tabView = createTabView;
        addView(createTabView);
        ImageView createIndicator = createIndicator();
        this.indicator = createIndicator;
        if (createIndicator != null) {
            addView(createIndicator);
        }
        return this;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.slidetab.ITabState
    public void unSelect() {
        View view = this.indicator;
        if (view != null) {
            view.setVisibility(4);
        }
    }

    public AbsTabHolder(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        setGravity(1);
    }
}
