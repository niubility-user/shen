package com.handmark.pulltorefresh.library;

/* loaded from: classes12.dex */
public interface ILoadMore {
    void resetFooter();

    void setLoadingMoreFailed();

    void setLoadingMoreSucceed();

    void setOnLoadMoreListener(LoadMoreListener loadMoreListener);

    void setReachEnd();
}
