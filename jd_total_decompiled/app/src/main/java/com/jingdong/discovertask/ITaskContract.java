package com.jingdong.discovertask;

import android.os.Bundle;
import com.jingdong.discovertask.model.entity.BaseTaskEntity;
import com.jingdong.discovertask.model.entity.DiscDoTaskEntity;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.entity.TaskFinishEntity;
import com.jingdong.discovertask.model.inter.HttpCallback;
import com.jingdong.discovertask.model.inter.OnTaskStatusListener;

/* loaded from: classes12.dex */
public interface ITaskContract {

    /* loaded from: classes12.dex */
    public interface IModel {
        void requestClaimTask(Bundle bundle, HttpCallback httpCallback);

        void requestFinishTask(Bundle bundle, HttpCallback httpCallback);

        void requestSignInfo(Bundle bundle, HttpCallback httpCallback);

        void requestStoreAward(Bundle bundle, HttpCallback httpCallback);

        void requestTaskList(Bundle bundle, HttpCallback httpCallback);
    }

    /* loaded from: classes12.dex */
    public interface IPresenter {
        void dealSignInfo(Bundle bundle);

        void dealStoreAward(Bundle bundle);

        void requestClaimTask(Bundle bundle);

        void requestFinishTask(Bundle bundle);

        void requestTaskList(Bundle bundle);

        void setClickSign(boolean z);

        void setOnTaskStatusListener(OnTaskStatusListener<BaseTaskEntity> onTaskStatusListener);
    }

    /* loaded from: classes12.dex */
    public interface IView {
        void claimTaskDefeat(BaseTaskEntity baseTaskEntity);

        void claimTaskSuccess(DiscDoTaskEntity discDoTaskEntity);

        void finishTaskDefeat(BaseTaskEntity baseTaskEntity);

        void finishTaskSuccess(TaskFinishEntity taskFinishEntity);

        void initAndShowSignDialog(TaskEntity taskEntity);

        void initAndShowTaskDialog(TaskEntity taskEntity);

        void initIconEntrance(TaskEntity taskEntity);

        void setBtnContentAndStatus();

        void signDialogDefeat(BaseTaskEntity baseTaskEntity);

        void startTimeDown(long j2);

        void storeAwardDefeat(BaseTaskEntity baseTaskEntity);

        void storeAwardSuccess(BaseTaskEntity baseTaskEntity);

        void taskListDefeat(BaseTaskEntity baseTaskEntity);
    }
}
