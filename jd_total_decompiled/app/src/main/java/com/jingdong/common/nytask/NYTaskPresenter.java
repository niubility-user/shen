package com.jingdong.common.nytask;

import com.jingdong.common.BaseActivity;
import com.jingdong.common.nytask.inter.INYView;
import com.jingdong.discovertask.model.inter.HttpCallback;

/* loaded from: classes5.dex */
public class NYTaskPresenter {
    private BaseActivity mActivity;
    private NYTaskHttp mNYTaskHttp;
    private INYView mView;

    public NYTaskPresenter(INYView iNYView, BaseActivity baseActivity) {
        this.mActivity = baseActivity;
        this.mView = iNYView;
        this.mNYTaskHttp = new NYTaskHttp(baseActivity);
    }

    public void finishTask(NYTaskParams nYTaskParams) {
        BaseActivity baseActivity;
        NYTaskHttp nYTaskHttp = this.mNYTaskHttp;
        if (nYTaskHttp == null || (baseActivity = this.mActivity) == null || this.mView == null) {
            return;
        }
        nYTaskHttp.finishTaskReq(nYTaskParams, new HttpCallback<NYTaskFinishEntity>(baseActivity) { // from class: com.jingdong.common.nytask.NYTaskPresenter.1
            {
                NYTaskPresenter.this = this;
            }

            @Override // com.jingdong.discovertask.model.inter.HttpCallback
            public void handError(NYTaskFinishEntity nYTaskFinishEntity) {
                super.handError((AnonymousClass1) nYTaskFinishEntity);
                NYTaskPresenter.this.mView.finishTaskFail(nYTaskFinishEntity);
            }

            @Override // com.jingdong.discovertask.model.inter.HttpCallback
            public void handSuccess(NYTaskFinishEntity nYTaskFinishEntity) {
                super.handSuccess((AnonymousClass1) nYTaskFinishEntity);
                if (nYTaskFinishEntity == null) {
                    NYTaskPresenter.this.mView.finishTaskFail(nYTaskFinishEntity);
                } else if (!"0".equals(nYTaskFinishEntity.code) || !"0".equals(nYTaskFinishEntity.busiCode)) {
                    NYTaskPresenter.this.mView.finishTaskFail(nYTaskFinishEntity);
                } else {
                    NYTaskPresenter.this.mView.finishTaskOkay(nYTaskFinishEntity);
                }
            }
        });
    }
}
