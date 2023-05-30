package com.jingdong.common.jdreactFramework.modules.community.upload.task;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;

/* loaded from: classes5.dex */
public abstract class BaseUploadTask {
    protected BaseUploadTask before;
    protected Listener callback;

    /* loaded from: classes5.dex */
    public interface Listener {
        void onExecuted();
    }

    public BaseUploadTask(BaseUploadTask baseUploadTask) {
        this.before = baseUploadTask;
    }

    @CallSuper
    public void exec(@NonNull UploadMedia uploadMedia) {
        BaseUploadTask baseUploadTask = this.before;
        if (baseUploadTask != null) {
            baseUploadTask.exec(uploadMedia);
        }
    }

    public void setCallback(Listener listener) {
        this.callback = listener;
    }
}
