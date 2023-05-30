package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.view.View;

/* loaded from: classes19.dex */
public interface IUi<E> {
    E getCustomView();

    View getView();

    void onClick(View view);

    IUi setOnClickListener(View.OnClickListener onClickListener);
}
