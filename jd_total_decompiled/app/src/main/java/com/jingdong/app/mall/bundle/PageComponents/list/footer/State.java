package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes19.dex */
public abstract class State<E> implements IUi<E> {
    protected final BaseFooter footer;
    private View.OnClickListener onClickListener;

    public State(@NonNull BaseFooter baseFooter) {
        this.footer = baseFooter;
    }

    public <T> void bind(FooterEntity<T> footerEntity) {
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    /* renamed from: onClick */
    public void a(View view) {
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public State setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
}
