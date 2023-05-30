package com.jingdong.common.jdreactFramework.modules.community.upload.task;

import androidx.annotation.NonNull;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;
import com.jingdong.common.jdreactFramework.modules.community.upload.VideoUploadStatus;
import com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask;
import com.jingdong.common.jdreactFramework.modules.community.upload.utils.MediaUtils;
import com.jingdong.common.jdreactFramework.modules.community.upload.utils.Utils;
import com.jingdong.common.jdreactFramework.modules.community.upload.utils.VideoCompressUtils;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;
import tv.danmaku.ijk.media.example.widget.media.FFmpegInvoke;

/* loaded from: classes5.dex */
public class VideoCompressTask extends BaseUploadTask {
    File compressFile;
    UploadMedia media;
    FFmpegInvoke.OnExecListener onExecListener;

    public VideoCompressTask(BaseUploadTask baseUploadTask) {
        super(baseUploadTask);
        this.onExecListener = new FFmpegInvoke.OnExecListener() { // from class: com.jingdong.common.jdreactFramework.modules.community.upload.task.VideoCompressTask.1
            @Override // tv.danmaku.ijk.media.example.widget.media.FFmpegInvoke.OnExecListener
            public void onExecuted(int i2) {
                if (i2 == 0 && UploadMedia.isAvailable(VideoCompressTask.this.compressFile)) {
                    Utils.log("compress", "\u538b\u7f29\u6210\u529f");
                    VideoCompressTask videoCompressTask = VideoCompressTask.this;
                    videoCompressTask.media.setUploadFile(videoCompressTask.compressFile);
                } else {
                    Utils.log("compress", "\u538b\u7f29\u5931\u8d25");
                    MediaUtils.deleteFile(VideoCompressTask.this.compressFile);
                    VideoCompressTask.this.media.setError(true);
                }
                BaseUploadTask.Listener listener = VideoCompressTask.this.callback;
                if (listener != null) {
                    listener.onExecuted();
                }
            }
        };
        this.media = null;
        this.compressFile = null;
    }

    private void videoCompress() {
        Utils.log("compress", "\u538b\u7f29\u7801\u7387\uff1a" + VideoCompressUtils.getRate(this.media.getCompressScaleIndex()));
        FFmpegInvoke.loadLibrariesOnce(JdSdk.getInstance().getApplicationContext());
        FFmpegInvoke.exec(FFmpegInvoke.buildCmd(MediaUtils.getVideoFile(this.media).getPath(), this.compressFile.getPath(), 25, VideoCompressUtils.getRate(this.media.getCompressScaleIndex())), this.onExecListener);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask
    public void exec(@NonNull UploadMedia uploadMedia) {
        super.exec(uploadMedia);
        this.media = uploadMedia;
        if (uploadMedia.isError()) {
            return;
        }
        uploadMedia.VIDEO_UPLOAD_STATUS = VideoUploadStatus.COMPRESS;
        if (MediaUtils.getVideoFile(uploadMedia).length() <= VideoCompressUtils.VIDEO_BYTE_COUNT) {
            Utils.log("compress", "\u4e0d\u9700\u8981\u538b\u7f29");
            uploadMedia.setUploadFile(MediaUtils.getVideoFile(uploadMedia));
            BaseUploadTask.Listener listener = this.callback;
            if (listener != null) {
                listener.onExecuted();
                return;
            }
            return;
        }
        File compressVideoFile = MediaUtils.getCompressVideoFile(uploadMedia);
        this.compressFile = compressVideoFile;
        if (UploadMedia.isAvailable(compressVideoFile)) {
            Utils.log("compress", "\u538b\u7f29\u6587\u4ef6\u5df2\u5b58\u5728\uff0c\u4e0d\u9700\u8981\u538b\u7f29");
            uploadMedia.setUploadFile(this.compressFile);
            BaseUploadTask.Listener listener2 = this.callback;
            if (listener2 != null) {
                listener2.onExecuted();
                return;
            }
            return;
        }
        videoCompress();
    }
}
