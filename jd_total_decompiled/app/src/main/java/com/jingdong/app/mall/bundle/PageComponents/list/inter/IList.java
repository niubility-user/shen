package com.jingdong.app.mall.bundle.PageComponents.list.inter;

import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/* loaded from: classes19.dex */
public interface IList extends IListPart<PullToRefreshBase>, ISetting {
    RecyclerView getRecyclerView();
}
