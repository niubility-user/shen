package com.jingdong.discovertask.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.discovertask.ITaskContract;
import com.jingdong.discovertask.model.SingletonWholeParams;
import com.jingdong.discovertask.model.entity.BaseTaskEntity;
import com.jingdong.discovertask.model.entity.DiscDoTaskEntity;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.entity.TaskFinishEntity;
import com.jingdong.discovertask.model.inter.HttpCallback;
import com.jingdong.discovertask.model.inter.OnTaskStatusListener;
import com.jingdong.discovertask.model.net.TaskHttp;
import com.jingdong.jdsdk.widget.JDToast;

/* loaded from: classes12.dex */
public class TaskPresenter implements ITaskContract.IPresenter {
    public static final String TAG = "TaskPresenter";
    private boolean isClickSign;
    private Context mContext;
    private ITaskContract.IModel mModel;
    private OnTaskStatusListener<BaseTaskEntity> mOnTaskStatusListener;
    private ITaskContract.IView mView;

    public TaskPresenter(ITaskContract.IView iView, Context context) {
        this.mView = iView;
        this.mContext = context;
        if (context instanceof BaseActivity) {
            this.mModel = new TaskHttp((BaseActivity) context);
        }
    }

    public void apiDownCommonToast() {
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText("\u554a\u54e6\uff0c\u6d3b\u52a8\u592a\u706b\u7206\uff5e");
        jDToast.show();
    }

    public void defeatToast(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity == null || TextUtils.equals("0000", baseTaskEntity.busiCode) || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
    }

    public void signOkAwardFailToast(TaskEntity taskEntity) {
        if (Log.D) {
            Log.e(TAG, "signOkAwardFailToast: start");
        }
        if (taskEntity != null) {
            TaskEntity.DiscBaseTaskEntity discBaseTaskEntity = taskEntity.data.discTasks.get(0);
            TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity = discBaseTaskEntity.signDetail;
            if (discSignTaskDetailEntity == null || discSignTaskDetailEntity.discTaskItemInfos == null) {
                return;
            }
            for (int i2 = 0; i2 < discBaseTaskEntity.signDetail.discTaskItemInfos.size(); i2++) {
                TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = discBaseTaskEntity.signDetail.discTaskItemInfos.get(i2);
                int i3 = discTaskItemInfoEntity.rewardStatus;
                if ((i3 == 1 || i3 == 3) && discBaseTaskEntity.curSignState == 1 && discTaskItemInfoEntity.curStep == 1) {
                    JDToast jDToast = new JDToast(this.mContext, (byte) 4);
                    jDToast.setText("\u7b7e\u5230\u6210\u529f\uff0c\u6d3b\u52a8\u592a\u706b\u7206\uff0c\u4eac\u8c46\u7a0d\u540e\u5c31\u5230\uff5e");
                    jDToast.show();
                }
            }
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void dealSignInfo(Bundle bundle) {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.mModel.requestSignInfo(bundle, new HttpCallback<TaskEntity>((BaseActivity) context) { // from class: com.jingdong.discovertask.presenter.TaskPresenter.1
                {
                    TaskPresenter.this = this;
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handError(TaskEntity taskEntity) {
                    super.handError((AnonymousClass1) taskEntity);
                    if (TaskPresenter.this.mOnTaskStatusListener != null) {
                        TaskPresenter.this.mOnTaskStatusListener.onSignInfoDefeat(taskEntity);
                    }
                    if (taskEntity != null) {
                        TaskPresenter.this.mView.signDialogDefeat(taskEntity);
                    }
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handSuccess(TaskEntity taskEntity) {
                    super.handSuccess((AnonymousClass1) taskEntity);
                    if (taskEntity != null && TextUtils.equals("0", taskEntity.code)) {
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onSignInfoSuccess(taskEntity);
                        }
                        TaskPresenter.this.mView.initIconEntrance(taskEntity);
                    } else if (taskEntity != null) {
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onSignInfoDefeat(taskEntity);
                        }
                        TaskPresenter.this.mView.signDialogDefeat(taskEntity);
                    }
                }
            });
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void dealStoreAward(Bundle bundle) {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.mModel.requestStoreAward(bundle, new HttpCallback<BaseTaskEntity>((BaseActivity) context) { // from class: com.jingdong.discovertask.presenter.TaskPresenter.3
                {
                    TaskPresenter.this = this;
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handError(BaseTaskEntity baseTaskEntity) {
                    super.handError((AnonymousClass3) baseTaskEntity);
                    if (baseTaskEntity != null) {
                        TaskPresenter.this.mView.storeAwardDefeat(baseTaskEntity);
                        if (TextUtils.equals(baseTaskEntity.code, "0")) {
                            return;
                        }
                        TaskPresenter.this.apiDownCommonToast();
                    }
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handSuccess(BaseTaskEntity baseTaskEntity) {
                    super.handSuccess((AnonymousClass3) baseTaskEntity);
                    if (baseTaskEntity == null || !TextUtils.equals("0", baseTaskEntity.code)) {
                        TaskPresenter.this.mView.storeAwardDefeat(baseTaskEntity);
                    } else {
                        TaskPresenter.this.mView.storeAwardSuccess(baseTaskEntity);
                    }
                }
            });
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void requestClaimTask(Bundle bundle) {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.mModel.requestClaimTask(bundle, new HttpCallback<DiscDoTaskEntity>((BaseActivity) context) { // from class: com.jingdong.discovertask.presenter.TaskPresenter.4
                {
                    TaskPresenter.this = this;
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handError(DiscDoTaskEntity discDoTaskEntity) {
                    super.handError((AnonymousClass4) discDoTaskEntity);
                    if (TaskPresenter.this.mOnTaskStatusListener != null) {
                        TaskPresenter.this.mOnTaskStatusListener.onTaskClaimFailure(discDoTaskEntity);
                    }
                    if (discDoTaskEntity == null || SingletonWholeParams.getNewInstance().mTaskClickParams == null) {
                        return;
                    }
                    TaskPresenter.this.mView.claimTaskDefeat(discDoTaskEntity);
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handSuccess(DiscDoTaskEntity discDoTaskEntity) {
                    super.handSuccess((AnonymousClass4) discDoTaskEntity);
                    if (discDoTaskEntity == null || !TextUtils.equals("0", discDoTaskEntity.code)) {
                        if (discDoTaskEntity != null) {
                            TaskPresenter.this.mView.claimTaskDefeat(discDoTaskEntity);
                        }
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onTaskClaimFailure(discDoTaskEntity);
                        }
                    } else if (!TextUtils.equals("4007", discDoTaskEntity.busiCode)) {
                        TaskPresenter.this.defeatToast(discDoTaskEntity);
                        DiscDoTaskEntity.DiscDoTaskResultEntity discDoTaskResultEntity = discDoTaskEntity.data;
                        if (discDoTaskResultEntity == null || discDoTaskResultEntity.discTimeModelConfigVo == null) {
                            return;
                        }
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onTaskClaimSuccess(discDoTaskEntity);
                        }
                        TaskPresenter.this.mView.claimTaskSuccess(discDoTaskEntity);
                    } else {
                        SingletonWholeParams.getNewInstance().mTaskClickParams = null;
                    }
                }
            });
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void requestFinishTask(Bundle bundle) {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.mModel.requestFinishTask(bundle, new HttpCallback<TaskFinishEntity>((BaseActivity) context) { // from class: com.jingdong.discovertask.presenter.TaskPresenter.5
                {
                    TaskPresenter.this = this;
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handError(TaskFinishEntity taskFinishEntity) {
                    super.handError((AnonymousClass5) taskFinishEntity);
                    if (TaskPresenter.this.mOnTaskStatusListener != null) {
                        TaskPresenter.this.mOnTaskStatusListener.onTaskFinishFailure(taskFinishEntity);
                    }
                    if (taskFinishEntity != null) {
                        TaskPresenter.this.mView.finishTaskDefeat(taskFinishEntity);
                    }
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handSuccess(TaskFinishEntity taskFinishEntity) {
                    super.handSuccess((AnonymousClass5) taskFinishEntity);
                    if (taskFinishEntity == null || !TextUtils.equals("0", taskFinishEntity.code)) {
                        return;
                    }
                    if (TaskPresenter.this.mOnTaskStatusListener != null) {
                        TaskPresenter.this.mOnTaskStatusListener.onTaskFinishSuccess(taskFinishEntity);
                    }
                    TaskPresenter.this.mView.finishTaskSuccess(taskFinishEntity);
                    TaskPresenter.this.defeatToast(taskFinishEntity);
                }
            });
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void requestTaskList(Bundle bundle) {
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.mModel.requestTaskList(bundle, new HttpCallback<TaskEntity>((BaseActivity) context) { // from class: com.jingdong.discovertask.presenter.TaskPresenter.2
                {
                    TaskPresenter.this = this;
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handError(TaskEntity taskEntity) {
                    super.handError((AnonymousClass2) taskEntity);
                    if (TaskPresenter.this.mOnTaskStatusListener != null) {
                        TaskPresenter.this.mOnTaskStatusListener.onTaskListDefeat(taskEntity);
                    }
                    if (taskEntity != null) {
                        TaskPresenter.this.mView.taskListDefeat(taskEntity);
                        if (!TextUtils.equals(taskEntity.code, "0")) {
                            TaskPresenter.this.apiDownCommonToast();
                        }
                    }
                    TaskPresenter.this.setClickSign(false);
                }

                @Override // com.jingdong.discovertask.model.inter.HttpCallback
                public void handSuccess(TaskEntity taskEntity) {
                    super.handSuccess((AnonymousClass2) taskEntity);
                    if (taskEntity != null && TextUtils.equals("0", taskEntity.code)) {
                        if (!TextUtils.equals("0000", taskEntity.busiCode)) {
                            if (TextUtils.equals("10001", taskEntity.busiCode) && TaskPresenter.this.isClickSign) {
                                JDToast jDToast = new JDToast(TaskPresenter.this.mContext, (byte) 4);
                                jDToast.setText(taskEntity.message);
                                jDToast.show();
                            } else if (!TextUtils.equals("10001", taskEntity.busiCode)) {
                                JDToast jDToast2 = new JDToast(TaskPresenter.this.mContext, (byte) 4);
                                jDToast2.setText(taskEntity.message);
                                jDToast2.show();
                            }
                        }
                        if (!TaskPresenter.this.isClickSign) {
                            TaskPresenter.this.signOkAwardFailToast(taskEntity);
                        }
                        TaskPresenter.this.mView.initAndShowTaskDialog(taskEntity);
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onTaskListSuccess(taskEntity);
                        }
                    } else if (taskEntity != null) {
                        ToastUtil.showToast(TaskPresenter.this.mContext, taskEntity.message);
                        if (TaskPresenter.this.mOnTaskStatusListener != null) {
                            TaskPresenter.this.mOnTaskStatusListener.onTaskListDefeat(taskEntity);
                        }
                    }
                    TaskPresenter.this.setClickSign(false);
                }
            });
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void setClickSign(boolean z) {
        this.isClickSign = z;
    }

    @Override // com.jingdong.discovertask.ITaskContract.IPresenter
    public void setOnTaskStatusListener(OnTaskStatusListener<BaseTaskEntity> onTaskStatusListener) {
        this.mOnTaskStatusListener = onTaskStatusListener;
    }
}
