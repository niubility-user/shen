package com.jingdong.common.listui.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.listui.AListItem;
import com.jingdong.common.listui.BaseUIRecyleView;
import com.jingdong.common.listui.Constants;
import com.jingdong.common.listui.IDragPullRefresh;
import com.jingdong.common.listui.ILoadMore;
import com.jingdong.common.listui.IPageLoad;
import com.jingdong.common.listui.IRefresh;
import com.jingdong.common.listui.LoadMoreView;
import com.jingdong.common.listui.Observable;
import com.jingdong.common.listui.ReqStatus;
import com.jingdong.common.listui.WrapBundle;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseUIRecyleViewFragment<T> extends BaseFragment implements IRefresh, ILoadMore, IPageLoad, IDragPullRefresh, BaseUIRecyleView.OnScrollListener {
    private boolean isInit;
    protected BaseUIRecyleView mBaseUIRecyleView;
    protected HeadTipView mHeadTipView;
    private LastReadHereItem mLastReadHereItem;
    protected LoadMoreView mLoadMoreView;
    protected Observable mObservable;
    private T mPresenter;

    protected abstract T buildPresenter();

    protected void clickLastReadHereItem() {
        dragPullRefresh();
    }

    @Override // com.jingdong.common.listui.IDragPullRefresh
    public void dragPullRefresh() {
        BaseUIRecyleView baseUIRecyleView = this.mBaseUIRecyleView;
        if (baseUIRecyleView == null) {
            return;
        }
        baseUIRecyleView.gotoTop();
        this.mBaseUIRecyleView.setRefreshing();
    }

    public LastReadHereItem getLastReadHereItem() {
        if (this.mLastReadHereItem == null) {
            LastReadHereItem lastReadHereItem = new LastReadHereItem();
            this.mLastReadHereItem = lastReadHereItem;
            lastReadHereItem.injectData(new WrapBundle().setItemHook(new WrapBundle.ItemHook() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.8
                @Override // com.jingdong.common.listui.WrapBundle.ItemHook
                public boolean itemHook() {
                    BaseUIRecyleViewFragment.this.clickLastReadHereItem();
                    return false;
                }
            }));
            this.mLastReadHereItem.setData(CommonBase.getJdSharedPreferences().getString("browseHere", "\u521a\u521a\u770b\u5230\u8fd9\u91cc \u70b9\u51fb\u5237\u65b0"));
        }
        return this.mLastReadHereItem;
    }

    protected Observable getObservable() {
        Observable observable = this.mObservable;
        if (observable != null) {
            return observable;
        }
        Observable subscribe = new Observable().subscribe(Constants.SHOW_PROGRESS, new Observable.Action<Void>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.7
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(Void r1) {
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                baseUIRecyleView.showProgress();
            }
        }).subscribe("refresh", new Observable.Action<List<AListItem>>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.6
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(List<AListItem> list) {
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                baseUIRecyleView.onRefreshComplete();
                if (list != null) {
                    BaseUIRecyleViewFragment.this.mBaseUIRecyleView.setList(list);
                    BaseUIRecyleViewFragment.this.mBaseUIRecyleView.notifyDataSetChanged();
                }
                BaseUIRecyleViewFragment.this.loadCompleted("refresh");
            }
        }).subscribe(Constants.REFRESH_INSERT, new Observable.Action<List<AListItem>>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.5
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(List<AListItem> list) {
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                baseUIRecyleView.onRefreshComplete();
                if (list == null || list.size() == 0) {
                    return;
                }
                BaseUIRecyleViewFragment baseUIRecyleViewFragment = BaseUIRecyleViewFragment.this;
                baseUIRecyleViewFragment.mBaseUIRecyleView.removeItem(baseUIRecyleViewFragment.getLastReadHereItem());
                list.add(BaseUIRecyleViewFragment.this.getLastReadHereItem());
                BaseUIRecyleViewFragment.this.mBaseUIRecyleView.setAListItemHasDevider(0, false);
                BaseUIRecyleViewFragment.this.mBaseUIRecyleView.addList(0, list);
                BaseUIRecyleViewFragment.this.mBaseUIRecyleView.notifyDataSetChanged();
                BaseUIRecyleViewFragment.this.loadCompleted(Constants.REFRESH_INSERT);
            }
        }).subscribe(Constants.LOADMORE, new Observable.Action<List<AListItem>>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.4
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(List<AListItem> list) {
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                if (list != null) {
                    baseUIRecyleView.addList(list);
                    BaseUIRecyleViewFragment.this.mBaseUIRecyleView.notifyDataSetChanged();
                }
                LoadMoreView loadMoreView = BaseUIRecyleViewFragment.this.mLoadMoreView;
                if (loadMoreView != null) {
                    loadMoreView.setStatus(ReqStatus.NOMORE);
                }
                BaseUIRecyleViewFragment.this.mBaseUIRecyleView.setLoading(false);
                BaseUIRecyleViewFragment.this.loadCompleted(Constants.LOADMORE);
            }
        }).subscribe("error", new Observable.Action<ReqStatus>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.3
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(ReqStatus reqStatus) {
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                baseUIRecyleView.onRefreshComplete();
                if (BaseUIRecyleViewFragment.this.mBaseUIRecyleView.getListSize() == 0) {
                    BaseUIRecyleViewFragment.this.mBaseUIRecyleView.showError(null, reqStatus == ReqStatus.DATA_EMPTY ? 1 : 0, new View.OnClickListener() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            BaseUIRecyleView baseUIRecyleView2 = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                            if (baseUIRecyleView2 != null) {
                                baseUIRecyleView2.removeError();
                            }
                            BaseUIRecyleViewFragment.this.pageLoad();
                        }
                    });
                    return;
                }
                BaseUIRecyleViewFragment.this.mLoadMoreView.setStatus(reqStatus);
                BaseUIRecyleViewFragment.this.mBaseUIRecyleView.setLoading(false);
            }
        }).subscribe(Constants.SHOWTIP, new Observable.Action<String>() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.2
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(String str) {
                HeadTipView headTipView;
                BaseUIRecyleView baseUIRecyleView = BaseUIRecyleViewFragment.this.mBaseUIRecyleView;
                if (baseUIRecyleView == null) {
                    return;
                }
                baseUIRecyleView.onRefreshComplete();
                if (BaseUIRecyleViewFragment.this.getUserVisibleHint() && (headTipView = BaseUIRecyleViewFragment.this.mHeadTipView) != null) {
                    headTipView.showHeadViewTip(str);
                }
            }
        });
        this.mObservable = subscribe;
        return subscribe;
    }

    protected T getPresenter() {
        if (this.mPresenter == null) {
            this.mPresenter = buildPresenter();
        }
        return this.mPresenter;
    }

    protected void loadCompleted(String str) {
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        setIsUseBasePV(false);
        this.mLoadMoreView = new LoadMoreView(layoutInflater.getContext());
        BaseUIRecyleView baseUIRecyleView = new BaseUIRecyleView();
        this.mBaseUIRecyleView = baseUIRecyleView;
        baseUIRecyleView.setBackTopYPos(DPIUtil.dip2px(60.0f));
        this.mBaseUIRecyleView.setIRefresh(this);
        this.mBaseUIRecyleView.setILoadMore(this);
        this.mBaseUIRecyleView.setOnScrollListener(this);
        this.mBaseUIRecyleView.setLoadMoreView(this.mLoadMoreView);
        View onCreateView = this.mBaseUIRecyleView.onCreateView(layoutInflater, null);
        this.mBaseUIRecyleView.getTitleView().setVisibility(8);
        this.mLoadMoreView.setRetry(new View.OnClickListener() { // from class: com.jingdong.common.listui.view.BaseUIRecyleViewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseUIRecyleViewFragment.this.loadMore();
            }
        });
        this.mHeadTipView = new HeadTipView(this.mBaseUIRecyleView.getVsTop());
        if (getUserVisibleHint()) {
            pageLoad();
        }
        return onCreateView;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.common.listui.BaseUIRecyleView.OnScrollListener
    public void onScroll(RecyclerView recyclerView, int i2, int i3) {
        HeadTipView headTipView = this.mHeadTipView;
        if (headTipView == null || i3 <= 0) {
            return;
        }
        headTipView.hideHeadViewTip();
    }

    public void setLastReadHereItem(LastReadHereItem lastReadHereItem) {
        this.mLastReadHereItem = lastReadHereItem;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        BaseUIRecyleView baseUIRecyleView;
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            if (!this.isInit || ((baseUIRecyleView = this.mBaseUIRecyleView) != null && baseUIRecyleView.getItemCount() == 0 && this.mBaseUIRecyleView.getErrorView() == null)) {
                pageLoad();
                this.isInit = true;
            }
        }
    }
}
