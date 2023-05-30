package com.jingdong.discovertask.model.net;

import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.discovertask.ITaskContract;
import com.jingdong.discovertask.model.inter.HttpCallback;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class TaskHttp implements ITaskContract.IModel {
    private WeakReference<BaseActivity> mReference;

    public TaskHttp(BaseActivity baseActivity) {
        this.mReference = new WeakReference<>(baseActivity);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IModel
    public void requestClaimTask(Bundle bundle, HttpCallback httpCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discAcceptTask");
        httpSetting.putJsonParam("itemId", bundle.getString("itemId", ""));
        httpSetting.putJsonParam("mType", Integer.valueOf(bundle.getInt("mType")));
        httpSetting.putJsonParam(NotificationMessageSummary.TASK_ID, bundle.getString(NotificationMessageSummary.TASK_ID, ""));
        httpSetting.putJsonParam("referPageId", "discTask");
        httpSetting.putJsonParam("bizType", "1");
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IModel
    public void requestFinishTask(Bundle bundle, HttpCallback httpCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discDoTask");
        httpSetting.putJsonParam("itemId", bundle.getString("itemId", ""));
        httpSetting.putJsonParam("ruid", bundle.getString("ruid", ""));
        httpSetting.putJsonParam(NotificationMessageSummary.TASK_ID, bundle.getString(NotificationMessageSummary.TASK_ID, ""));
        httpSetting.putJsonParam("bizType", "1");
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IModel
    public void requestSignInfo(Bundle bundle, HttpCallback httpCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discTaskEntrance");
        httpSetting.putJsonParam("referPageId", "discTask");
        httpSetting.putJsonParam("bizType", "1");
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IModel
    public void requestStoreAward(Bundle bundle, HttpCallback httpCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discReceiveTaskAward");
        httpSetting.putJsonParam(NotificationMessageSummary.TASK_ID, bundle.getString(NotificationMessageSummary.TASK_ID, ""));
        httpSetting.putJsonParam("ruid", bundle.getString("ruid", ""));
        httpSetting.putJsonParam("bizType", "1");
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IModel
    public void requestTaskList(Bundle bundle, HttpCallback httpCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discTaskList");
        httpSetting.putJsonParam("needJump", Integer.valueOf(bundle.getInt("needJump", 1)));
        httpSetting.putJsonParam("referPageId", "discTask");
        httpSetting.putJsonParam("bizType", "1");
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setListener(httpCallback);
        this.mReference.get().getHttpGroupaAsynPool().add(httpSetting);
    }
}
