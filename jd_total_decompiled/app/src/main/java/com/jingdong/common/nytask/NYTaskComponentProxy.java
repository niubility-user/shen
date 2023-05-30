package com.jingdong.common.nytask;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.nytask.NYIconEntity;
import com.jingdong.common.nytask.NYTaskFinishEntity;
import com.jingdong.common.nytask.inter.ComponentLifecycleObserver;
import com.jingdong.common.nytask.inter.INYView;
import com.jingdong.common.nytask.inter.NYTaskExternalCallback;
import com.jingdong.common.nytask.widget.NYTaskRootView;
import com.jingdong.common.nytask.widget.NYTimeDownView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes5.dex */
public class NYTaskComponentProxy implements ComponentLifecycleObserver, INYView {
    public static final String TAG = "NYTaskComponentProxy";
    private Context mContext;
    private NYTaskExternalCallback mExternalCallback;
    private Lifecycle mLifecycle;
    private NYTaskParams mNYTaskParams;
    private NYTaskPresenter mPresenter;
    private ViewGroup mRootView;
    private NYTaskRootView mTaskRootView;

    public NYTaskComponentProxy(Context context, ViewGroup viewGroup, @NYTaskStyle int i2, NYTaskParams nYTaskParams) {
        this.mContext = context;
        this.mRootView = viewGroup;
        this.mNYTaskParams = nYTaskParams;
        this.mTaskRootView = new NYTaskRootView(context);
        if (context instanceof BaseActivity) {
            Lifecycle lifecycle = ((BaseActivity) context).getLifecycle();
            this.mLifecycle = lifecycle;
            lifecycle.addObserver(this);
        }
        attachView2Root();
        initWithStyle(i2, nYTaskParams);
    }

    private void attachView2Root() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.addView(this.mTaskRootView, layoutParams);
            return;
        }
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.mTaskRootView.setPadding(0, baseActivity.getStatusHeight(), 0, 0);
            ((ViewGroup) ((ViewGroup) baseActivity.getWindow().getDecorView()).findViewById(16908290)).addView(this.mTaskRootView, layoutParams);
        }
    }

    private void initWithStyle(@NYTaskStyle int i2, NYTaskParams nYTaskParams) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.initWithStyle(i2, nYTaskParams);
            setTimeDownListener(new NYTimeDownView.TimeDownListener() { // from class: com.jingdong.common.nytask.NYTaskComponentProxy.1
                @Override // com.jingdong.common.nytask.widget.NYTimeDownView.TimeDownListener
                public void onFinish() {
                    if (NYTaskComponentProxy.this.mPresenter == null && (NYTaskComponentProxy.this.mContext instanceof BaseActivity)) {
                        NYTaskComponentProxy nYTaskComponentProxy = NYTaskComponentProxy.this;
                        nYTaskComponentProxy.mPresenter = new NYTaskPresenter(nYTaskComponentProxy, (BaseActivity) nYTaskComponentProxy.mContext);
                    }
                    if (NYTaskComponentProxy.this.mPresenter != null) {
                        NYTaskComponentProxy.this.mPresenter.finishTask(NYTaskComponentProxy.this.mNYTaskParams);
                    }
                }
            });
        }
    }

    private void setTimeDownClickJump(final String str) {
        NYTaskRootView nYTaskRootView;
        if (TextUtils.isEmpty(str) || (nYTaskRootView = this.mTaskRootView) == null) {
            return;
        }
        nYTaskRootView.setTimeDownClickToJump(new View.OnClickListener() { // from class: com.jingdong.common.nytask.NYTaskComponentProxy.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NYTaskComponentProxy.this.mExternalCallback != null) {
                    NYTaskComponentProxy.this.mExternalCallback.onTimeDownClick();
                }
                JumpEntity jumpEntity = new JumpEntity();
                jumpEntity.des = "m";
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) str);
                jumpEntity.params = jDJSONObject.toJSONString();
                JumpUtil.execJump(NYTaskComponentProxy.this.mContext, jumpEntity, 1);
            }
        });
    }

    @Override // com.jingdong.common.nytask.inter.INYView
    public void finishTaskFail(NYTaskFinishEntity nYTaskFinishEntity) {
        NYTaskFinishEntity.Data data;
        NYTaskFinishEntity.Data data2;
        if (nYTaskFinishEntity != null && (data2 = nYTaskFinishEntity.data) != null) {
            setTimeDownClickJump(data2.familyTaskRoom);
        }
        if (nYTaskFinishEntity == null || (data = nYTaskFinishEntity.data) == null || TextUtils.isEmpty(data.toast)) {
            return;
        }
        ToastUtils.showToastInCenter(this.mContext, nYTaskFinishEntity.data.toast);
    }

    @Override // com.jingdong.common.nytask.inter.INYView
    public void finishTaskOkay(NYTaskFinishEntity nYTaskFinishEntity) {
        NYTaskFinishEntity.Data data;
        if (nYTaskFinishEntity != null && (data = nYTaskFinishEntity.data) != null) {
            setTimeDownClickJump(data.familyTaskRoom);
        }
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.finishTaskOkay(nYTaskFinishEntity);
        }
        NYTaskExternalCallback nYTaskExternalCallback = this.mExternalCallback;
        if (nYTaskExternalCallback != null) {
            nYTaskExternalCallback.onTaskFinish();
        }
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onCreate() {
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onDestroy() {
        if (Log.D) {
            Log.e(TAG, "activity \u9500\u6bc1");
        }
        Lifecycle lifecycle = this.mLifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.releaseResource();
        }
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onPause() {
        if (Log.D) {
            Log.e(TAG, "activity pause");
        }
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.pauseTimeDown(false);
        }
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onResume() {
        if (Log.D) {
            Log.e(TAG, "activity resume");
        }
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.resumeTimeDown(false);
        }
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onStart() {
    }

    @Override // com.jingdong.common.nytask.inter.ComponentLifecycleObserver
    public void onStop() {
    }

    public void pauseTimeDown() {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.pauseTimeDown(true);
        }
    }

    public void resumeTimeDown() {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.resumeTimeDown(true);
        }
    }

    public void setExternalListener(NYTaskExternalCallback nYTaskExternalCallback) {
        this.mExternalCallback = nYTaskExternalCallback;
    }

    public void setFloatViewGravity(int i2) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.setFloatViewGravity(i2);
        }
    }

    public void setIconData(NYIconEntity.Data data) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.setIconData(data);
        }
    }

    public void setInitPoint(float f2, float f3) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.setInitPoint(f2, f3);
        }
    }

    public void setTimeDownListener(NYTimeDownView.TimeDownListener timeDownListener) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.setTimeDownListener(timeDownListener);
        }
    }

    public void startTimeDown(long j2) {
        NYTaskRootView nYTaskRootView = this.mTaskRootView;
        if (nYTaskRootView != null) {
            nYTaskRootView.startTimeDown(j2);
            NYTaskExternalCallback nYTaskExternalCallback = this.mExternalCallback;
            if (nYTaskExternalCallback != null) {
                nYTaskExternalCallback.onTimeDownStart();
            }
        }
    }
}
