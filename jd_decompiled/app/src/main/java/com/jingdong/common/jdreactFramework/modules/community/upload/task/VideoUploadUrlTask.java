package com.jingdong.common.jdreactFramework.modules.community.upload.task;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;
import com.jingdong.common.jdreactFramework.modules.community.upload.VideoUploadStatus;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class VideoUploadUrlTask extends BaseUploadTask {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class UploadVideoEntity {
        public String uploadUrl;
        public String videoId;

        private UploadVideoEntity() {
        }
    }

    public VideoUploadUrlTask(BaseUploadTask baseUploadTask) {
        super(baseUploadTask);
    }

    private HttpSetting getHttpSetting(String str, String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(str);
        httpSetting.setFunctionId(str2);
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        return httpSetting;
    }

    private void getUploadUrl(@NonNull final UploadMedia uploadMedia) {
        getVideoUploadInfo(uploadMedia.getVideoSize(), uploadMedia.getVideoId(), new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jdreactFramework.modules.community.upload.task.VideoUploadUrlTask.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                UploadVideoEntity parseUploadVideoEntity = VideoUploadUrlTask.this.parseUploadVideoEntity(httpResponse.getFastJsonObject());
                if (parseUploadVideoEntity != null && !TextUtils.isEmpty(parseUploadVideoEntity.uploadUrl) && !TextUtils.isEmpty(parseUploadVideoEntity.videoId)) {
                    uploadMedia.setVideoUploadUrl(parseUploadVideoEntity.uploadUrl);
                    uploadMedia.setVideoId(parseUploadVideoEntity.videoId);
                    return;
                }
                uploadMedia.setVideoUploadUrl(null);
                uploadMedia.setVideoId(null);
                uploadMedia.setError(true);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                uploadMedia.setVideoUploadUrl(null);
                uploadMedia.setVideoId(null);
                uploadMedia.setError(true);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    private void getVideoUploadInfo(long j2, String str, HttpGroup.OnAllListener onAllListener) {
        HttpSetting httpSetting = getHttpSetting(Configuration.getCommentHost(), "getUploadVideoUrl");
        httpSetting.putJsonParam("fileSize", Long.valueOf(j2));
        httpSetting.putJsonParam("videoName", str);
        httpSetting.setListener(onAllListener);
        new HttpGroupUtil().getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask
    public void exec(@NonNull UploadMedia uploadMedia) {
        super.exec(uploadMedia);
        if (uploadMedia.isError()) {
            return;
        }
        uploadMedia.VIDEO_UPLOAD_STATUS = VideoUploadStatus.GET_UPLOAD_URL;
        getUploadUrl(uploadMedia);
    }

    public UploadVideoEntity parseUploadVideoEntity(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject;
        String str;
        if (jDJSONObject == null || !"0".equals(jDJSONObject.optString("code")) || (optJSONObject = jDJSONObject.optJSONObject("data")) == null || TextUtils.isEmpty(optJSONObject.toString())) {
            return null;
        }
        UploadVideoEntity uploadVideoEntity = (UploadVideoEntity) JDJSON.parseObject(optJSONObject.toString(), UploadVideoEntity.class);
        if (Build.VERSION.SDK_INT < 21 && (str = uploadVideoEntity.uploadUrl) != null && str.toLowerCase().startsWith("https")) {
            uploadVideoEntity.uploadUrl = uploadVideoEntity.uploadUrl.replaceFirst("(?i)https", "http");
        }
        return uploadVideoEntity;
    }
}
