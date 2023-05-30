package com.jingdong.common.XView2.container;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.entity.XViewConfigEntity;

/* loaded from: classes5.dex */
public interface IContainer {
    void addContainerView(View view, XViewConfigEntity.ControlEntity controlEntity);

    void addViewToActivity(FrameLayout.LayoutParams layoutParams);

    void addViewToFragmentView(FrameLayout.LayoutParams layoutParams);

    long getZIndex();

    void initXView2Container(XView2 xView2, XViewConfigEntity.LayersEntity layersEntity);

    void setRootView(ViewGroup viewGroup);

    void setZIndex(long j2);
}
