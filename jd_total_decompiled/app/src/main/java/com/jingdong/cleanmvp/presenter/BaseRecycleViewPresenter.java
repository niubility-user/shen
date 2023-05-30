package com.jingdong.cleanmvp.presenter;

import com.jingdong.cleanmvp.common.BaseListConstans;
import com.jingdong.cleanmvp.presenter.BaseListUI;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class BaseRecycleViewPresenter<T, P extends BaseListUI> extends BaseListPresenter<T, P> {
    private void handleFooterState(ArrayList arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            if (this.hasNextPage) {
                ((BaseListUI) getUI()).setFooterState(BaseListConstans.LOADING_SUCCESS);
                return;
            } else {
                reachEnd();
                return;
            }
        }
        reachEnd();
    }

    protected abstract ArrayList<?> getArrayFormData(T t);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BaseListPresenter
    public void reachEnd() {
        super.reachEnd();
        ((BaseListUI) getUI()).setFooterState(BaseListConstans.REACH_END);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BaseListPresenter
    public void showData() {
        super.showData();
        handleFooterState(getArrayFormData(getBaseListState().data));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BaseListPresenter
    public void showNetError() {
        super.showNetError();
        if (this.currentPage > 1) {
            ((BaseListUI) getUI()).setFooterState(BaseListConstans.LOADING_FAILED);
        }
    }
}
