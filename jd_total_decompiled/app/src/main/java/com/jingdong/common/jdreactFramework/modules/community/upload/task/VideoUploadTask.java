package com.jingdong.common.jdreactFramework.modules.community.upload.task;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;
import com.jingdong.common.jdreactFramework.modules.community.upload.VideoUploadStatus;
import com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask;
import com.jingdong.common.jdreactFramework.modules.community.upload.utils.Utils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.SimpleFileUploader;
import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class VideoUploadTask extends BaseUploadTask {
    public VideoUploadTask(BaseUploadTask baseUploadTask) {
        super(baseUploadTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        BaseUploadTask.Listener listener = this.callback;
        if (listener != null) {
            listener.onExecuted();
        }
    }

    private void uploading(@NonNull final UploadMedia uploadMedia) {
        SimpleFileUploader.getInstance().upload(uploadMedia.getVideoUploadUrl(), uploadMedia.getVideoPath(), new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jdreactFramework.modules.community.upload.task.VideoUploadTask.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Utils.log("uploadVideo", httpResponse.getJSONObject().toString());
                String parseUploadVideoResult = VideoUploadTask.this.parseUploadVideoResult(httpResponse.getJSONObject());
                Utils.log("uploadVideo", parseUploadVideoResult);
                if (TextUtils.equals("success", parseUploadVideoResult)) {
                    Utils.log("uploadVideo", "success");
                    uploadMedia.VIDEO_UPLOAD_STATUS = VideoUploadStatus.SUCCESS;
                } else {
                    Utils.log("uploadVideo", "failure");
                    uploadMedia.setVideoUploadUrl(null);
                    uploadMedia.setVideoId(null);
                    uploadMedia.setError(true);
                }
                VideoUploadTask.this.finish();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                uploadMedia.setVideoUploadUrl(null);
                uploadMedia.setVideoId(null);
                uploadMedia.setError(true);
                VideoUploadTask.this.finish();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask
    public void exec(@NonNull UploadMedia uploadMedia) {
        super.exec(uploadMedia);
        if (uploadMedia.isError()) {
            Utils.log("uploadVideo", "media error");
            uploadMedia.setVideoUploadUrl(null);
            uploadMedia.setVideoId(null);
            uploadMedia.setError(true);
            finish();
            return;
        }
        uploadMedia.VIDEO_UPLOAD_STATUS = VideoUploadStatus.UPLOADING;
        uploading(uploadMedia);
    }

    public String parseUploadVideoResult(JSONObjectProxy jSONObjectProxy) {
        String optString;
        if (jSONObjectProxy == null || !"0".equals(jSONObjectProxy.optString("code")) || (optString = jSONObjectProxy.optString("message")) == null || TextUtils.isEmpty(optString)) {
            return null;
        }
        return optString;
    }
}
