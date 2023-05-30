package com.jingdong.app.mall.bundle.PageComponents.view.slidetab;

import android.view.View;
import android.widget.ImageView;

/* loaded from: classes19.dex */
public interface ITabHolder<TabView extends View, Entity> extends ITabState {
    ImageView createIndicator();

    TabView createTabView(Entity entity);

    View getTabHolderView();

    TabView getTabView();

    ITabHolder<TabView, Entity> setPosition(int i2);

    ITabHolder<TabView, Entity> setupTabHolder(Entity entity);
}
