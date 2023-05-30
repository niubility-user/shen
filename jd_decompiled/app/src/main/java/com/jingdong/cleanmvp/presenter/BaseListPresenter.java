package com.jingdong.cleanmvp.presenter;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.common.BaseListEvent;
import com.jingdong.cleanmvp.common.NormalListener;
import com.jingdong.cleanmvp.engine.BaseListState;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes4.dex */
public abstract class BaseListPresenter<T, P extends IBaseUI> extends BasePresenter<P> {
    protected BaseListInteractor<T> baseListInteractor;
    private HashMap hashMap;
    protected int currentPage = 1;
    protected boolean hasNextPage = false;
    protected boolean isLoadMoreFailed = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    protected int pageSize = 10;
    protected boolean isReachEnd = false;
    private ArrayList<Runnable> runnables = new ArrayList<>();
    private final Object synObj = new Object();

    public BaseListPresenter() {
        BaseListInteractor<T> createInteractor = createInteractor();
        this.baseListInteractor = createInteractor;
        if (createInteractor != null) {
            createInteractor.setListener(new NormalListener() { // from class: com.jingdong.cleanmvp.presenter.BaseListPresenter.1
                @Override // com.jingdong.cleanmvp.common.NormalListener
                public void onResult(BaseEvent baseEvent) {
                    String type = baseEvent.getType();
                    type.hashCode();
                    if (!type.equals(BaseListEvent.SHOW_NET_ERROR)) {
                        if (type.equals(BaseListEvent.SHOW_DATA) && BaseListPresenter.this.handler != null) {
                            synchronized (BaseListPresenter.this.synObj) {
                                if (BaseListPresenter.this.handler != null) {
                                    Runnable runnable = new Runnable() { // from class: com.jingdong.cleanmvp.presenter.BaseListPresenter.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            BaseListPresenter.this.showData();
                                        }
                                    };
                                    BaseListPresenter.this.runnables.add(runnable);
                                    BaseListPresenter.this.handler.post(runnable);
                                }
                            }
                        }
                    } else if (BaseListPresenter.this.handler != null) {
                        synchronized (BaseListPresenter.this.synObj) {
                            if (BaseListPresenter.this.handler != null) {
                                Runnable runnable2 = new Runnable() { // from class: com.jingdong.cleanmvp.presenter.BaseListPresenter.1.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        BaseListPresenter.this.showNetError();
                                    }
                                };
                                BaseListPresenter.this.runnables.add(runnable2);
                                BaseListPresenter.this.handler.post(runnable2);
                            }
                        }
                    }
                }
            });
            return;
        }
        throw new NullPointerException("createInteractor() can not return null");
    }

    protected abstract BaseListInteractor<T> createInteractor();

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseListState<T> getBaseListState() {
        return this.baseListInteractor.getState();
    }

    public void getFirstPageData() {
        getFirstPageData(null);
    }

    public HashMap getHashMap() {
        return this.hashMap;
    }

    public void getNextPageData() {
        if (this.hasNextPage) {
            if (!this.isLoadMoreFailed) {
                this.currentPage++;
            }
            this.baseListInteractor.getData(this.currentPage, this.pageSize, this.hashMap);
        } else if (this.isReachEnd) {
        } else {
            reachEnd();
        }
    }

    protected abstract void handleData(T t, int i2);

    protected abstract void handleNetError(HttpError httpError, int i2);

    protected abstract boolean judgeHasNextPage(T t);

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    protected void onDetach(P p) {
        synchronized (this.synObj) {
            Iterator<Runnable> it = this.runnables.iterator();
            while (it.hasNext()) {
                this.handler.removeCallbacks(it.next());
            }
            this.runnables.clear();
            this.handler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reachEnd() {
        this.isReachEnd = true;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    public void setPageSize(int i2) {
        this.pageSize = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showData() {
        this.isLoadMoreFailed = false;
        this.isReachEnd = false;
        this.hasNextPage = judgeHasNextPage(getBaseListState().data);
        handleData(getBaseListState().data, this.currentPage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showNetError() {
        this.isReachEnd = false;
        if (this.currentPage != 1) {
            this.isLoadMoreFailed = true;
        }
        handleNetError(getBaseListState().httpError, this.currentPage);
    }

    public void getFirstPageData(HashMap hashMap) {
        this.currentPage = 1;
        this.hashMap = hashMap;
        this.baseListInteractor.getData(1, this.pageSize, hashMap);
    }
}
