package com.jingdong.discovertask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.discovertask.model.entity.RequestParams;
import com.jingdong.discovertask.model.entity.TaskClickParams;
import com.jingdong.discovertask.model.inter.OnTaskClickListener;
import com.jingdong.discovertask.model.inter.OnTaskMtaListener;
import com.jingdong.discovertask.model.inter.OnTaskStatusListener;
import com.jingdong.discovertask.model.inter.OnTimeStatusChangedListener;

/* loaded from: classes12.dex */
public class TaskComponent {
    private TaskComponentProxy mProxy;

    public TaskComponent(Context context) {
        this(context, null);
    }

    private void init(Context context, ViewGroup viewGroup) {
        this.mProxy = new TaskComponentProxy(context, viewGroup);
    }

    public TaskClickParams getGlobalTaskParams() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            return taskComponentProxy.getGlobalTaskParams();
        }
        return null;
    }

    public void hideHalf() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.hideHalf();
        }
    }

    public void hideTaskListDialog() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.hideTaskListDialog();
        }
    }

    public TaskComponent initWithStyle(int i2, RequestParams requestParams) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.initWithStyle(i2, requestParams);
        }
        return this;
    }

    public void pauseTimeDown() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.pauseTimeDown();
        }
    }

    public void releaseResource() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.releaseResource();
        }
    }

    public void restoreTaskListStatus() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.restoreTaskListStatus();
        }
    }

    public void resumeTimeDown() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.resumeTimeDown();
        }
    }

    public void setFloatIconClickListener(View.OnClickListener onClickListener) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.setTimeFloatClickListener(onClickListener);
        }
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.setOnTaskClickListener(onTaskClickListener);
        }
    }

    public void setOnTaskMtaListener(OnTaskMtaListener onTaskMtaListener) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.setOnTaskMtaListener(onTaskMtaListener);
        }
    }

    public TaskComponent setTaskStatusListener(OnTaskStatusListener onTaskStatusListener) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.setOnTaskStatusListener(onTaskStatusListener);
        }
        return this;
    }

    public TaskComponent setTimeStatusChangedListener(OnTimeStatusChangedListener onTimeStatusChangedListener) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.setOnTimeStatusChangedListener(onTimeStatusChangedListener);
        }
        return this;
    }

    public void showFloatComplete() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.showFloatComplete();
        }
    }

    public void startTask() {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.startTask();
        }
    }

    public void startTimeDown(int i2) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.startTimeDown(i2);
        }
    }

    public void switchScreen(boolean z) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.switchScreen(z);
        }
    }

    public void toggleFloatView(boolean z) {
        TaskComponentProxy taskComponentProxy = this.mProxy;
        if (taskComponentProxy != null) {
            taskComponentProxy.toggleFloatView(z);
        }
    }

    public TaskComponent(Context context, ViewGroup viewGroup) {
        init(context, viewGroup);
    }
}
