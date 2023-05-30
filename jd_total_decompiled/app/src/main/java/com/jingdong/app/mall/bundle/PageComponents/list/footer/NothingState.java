package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.view.View;
import androidx.constraintlayout.widget.Guideline;

/* loaded from: classes19.dex */
public class NothingState extends State<View> {
    public NothingState(BaseFooter baseFooter) {
        super(baseFooter);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getView() {
        return getCustomView();
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getCustomView() {
        return new Guideline(this.footer.getContext());
    }
}
