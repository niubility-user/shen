package com.jingdong.common.nytask;

import com.jingdong.common.BaseActivity;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.discovertask.model.inter.HttpCallback;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class NYTaskHttp {
    private WeakReference<BaseActivity> mReference;

    public NYTaskHttp(BaseActivity baseActivity) {
        if (baseActivity != null) {
            this.mReference = new WeakReference<>(baseActivity);
        }
    }

    public void finishTaskReq(NYTaskParams nYTaskParams, HttpCallback httpCallback) {
        WeakReference<BaseActivity> weakReference;
        if (nYTaskParams == null || (weakReference = this.mReference) == null || weakReference.get() == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discDoFamilyTask");
        httpSetting.putJsonParam("monitorSource", "NYShoppingFestival");
        httpSetting.putJsonParam(NotificationMessageSummary.TASK_ID, nYTaskParams.taskId);
        httpSetting.putJsonParam("nypoolId", nYTaskParams.nyPoolId);
        httpSetting.putJsonParam("taskType", nYTaskParams.taskType);
        httpSetting.putJsonParam("materialId", nYTaskParams.materialId);
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }
}
