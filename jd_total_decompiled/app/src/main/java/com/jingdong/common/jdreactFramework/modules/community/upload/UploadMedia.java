package com.jingdong.common.jdreactFramework.modules.community.upload;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.File;

/* loaded from: classes5.dex */
public class UploadMedia {
    private String compressScaleIndex;
    private Uri mediaUri;
    private String videoId;
    private String videoPath;
    private long videoSize;
    private String videoUploadUrl;
    public VideoUploadStatus VIDEO_UPLOAD_STATUS = VideoUploadStatus.INIT;
    private boolean error = false;

    /* loaded from: classes5.dex */
    public interface VideoUploadListener {
        void fileNoExist(@NonNull UploadMedia uploadMedia);

        void preUpload(@NonNull UploadMedia uploadMedia);

        void uploadFailed(@NonNull UploadMedia uploadMedia);

        void uploadStart(@NonNull UploadMedia uploadMedia);
    }

    public UploadMedia() {
    }

    public static boolean isAvailable(File file) {
        return file != null && file.exists() && file.length() > 0 && !TextUtils.isEmpty(file.getName());
    }

    public String getCompressScaleIndex() {
        return this.compressScaleIndex;
    }

    public Uri getMediaUri() {
        return this.mediaUri;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public long getVideoSize() {
        return this.videoSize;
    }

    public String getVideoUploadUrl() {
        return this.videoUploadUrl;
    }

    public boolean isError() {
        return this.error;
    }

    public void setCompressScaleIndex(String str) {
        this.compressScaleIndex = str;
    }

    public void setError(boolean z) {
        this.error = z;
    }

    public void setMediaUri(Uri uri) {
        this.mediaUri = uri;
    }

    public void setUploadFile(File file) {
        if (isAvailable(file)) {
            setVideoPath(file.getPath());
            setVideoSize(file.length());
        }
    }

    public void setVideoId(String str) {
        this.videoId = str;
    }

    public void setVideoPath(String str) {
        this.videoPath = str;
    }

    public void setVideoSize(long j2) {
        this.videoSize = j2;
    }

    public void setVideoUploadUrl(String str) {
        this.videoUploadUrl = str;
    }

    public UploadMedia(Uri uri) {
        this.mediaUri = uri;
    }
}
