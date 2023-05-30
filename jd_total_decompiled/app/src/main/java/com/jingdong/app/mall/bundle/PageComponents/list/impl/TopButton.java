package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import android.content.Context;
import android.view.View;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IView;
import com.jingdong.common.widget.custom.CustomTopButton;

/* loaded from: classes19.dex */
public class TopButton implements IView {
    private final CustomTopButton customTopButton;

    /* loaded from: classes19.dex */
    private static class TopCLick implements View.OnClickListener {
        private final IListManager iListManager;

        public TopCLick(IListManager iListManager) {
            this.iListManager = iListManager;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.iListManager.actionTop();
        }
    }

    public TopButton(Context context) {
        this.customTopButton = new CustomTopButton(context);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IView
    public void dismiss() {
        this.customTopButton.setVisibility(8);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListPart
    public View getRootView() {
        return this.customTopButton;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.ISetting
    public void setIListManager(IListManager iListManager) {
        this.customTopButton.setOnClickListener(new TopCLick(iListManager));
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IView
    public void show() {
        this.customTopButton.setVisibility(0);
    }
}
