package com.jingdong.common.ntask;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes.dex */
public class NTask implements LifecycleObserver {
    private BaseActivity mActivity;
    private Lifecycle mLifecycle;
    private NTaskViewPullListener mListener;
    private Observer<NTaskEvent> modelObserver = new Observer<NTaskEvent>() { // from class: com.jingdong.common.ntask.NTask.1
        @Override // androidx.lifecycle.Observer
        public void onChanged(NTaskEvent nTaskEvent) {
            NTask.this.handlerData(nTaskEvent);
        }
    };
    private NTaskVM nTaskVM;

    public NTask(BaseActivity baseActivity) {
        this.mActivity = baseActivity;
        if (baseActivity == null) {
            return;
        }
        Lifecycle lifecycle = baseActivity.getLifecycle();
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
        NTaskVM nTaskVM = new NTaskVM(this.mActivity);
        this.nTaskVM = nTaskVM;
        nTaskVM.taskLiveData.observe(this.mActivity, this.modelObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerData(NTaskEvent nTaskEvent) {
        boolean z = nTaskEvent.isSuccess;
        NTaskModel nTaskModel = nTaskEvent.nTaskModel;
        NTaskView nTaskView = new NTaskView(this.mActivity);
        NTaskViewPullListener nTaskViewPullListener = this.mListener;
        if (nTaskViewPullListener != null) {
            nTaskViewPullListener.pull(z, nTaskModel, nTaskView);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        this.nTaskVM.taskLiveData.removeObserver(this.modelObserver);
    }

    public void request(JDJSONObject jDJSONObject, NTaskViewPullListener nTaskViewPullListener) {
        this.mListener = nTaskViewPullListener;
        this.nTaskVM.taskReq(jDJSONObject);
    }

    public void showH5(String str, NXViewListener nXViewListener) {
        if (!LoginUserBase.hasLogin()) {
            ToastUtils.showToastInCenter(this.mActivity, "\u672a\u767b\u5f55\u65e0\u6cd5\u5b8c\u6210\u52a9\u529b, \u8bf7\u5148\u767b\u5f55");
        } else {
            new NTaskXView(this.mActivity, nXViewListener).openXViewH5(str);
        }
    }
}
