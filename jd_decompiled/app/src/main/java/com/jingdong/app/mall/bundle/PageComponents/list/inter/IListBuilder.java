package com.jingdong.app.mall.bundle.PageComponents.list.inter;

import com.jingdong.app.mall.bundle.PageComponents.list.net.INetController;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes19.dex */
public interface IListBuilder extends IListPart {
    IListManager build();

    IList getListView();

    IListBuilder setDistanceBottom(boolean z);

    IListBuilder setDistanceTop(boolean z);

    IListBuilder setListView(@NotNull IList iList);

    IListBuilder setNetController(INetController iNetController);

    IListBuilder setTopButton(IView iView);
}
