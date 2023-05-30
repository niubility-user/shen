package com.jingdong.common.jdreactFramework.modules.community.upload.utils;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;
import com.jingdong.common.permission.PermissionHelper;
import java.io.File;
import rx.Scheduler;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/* loaded from: classes5.dex */
public class MediaUtils {
    public static final String VIDEO_COMPRESS_SAVE_DIR = "/cVideo";

    public static void deleteFile(final File file) {
        if (file == null || !file.exists()) {
            return;
        }
        Scheduler.Worker createWorker = Schedulers.io().createWorker();
        file.getClass();
        createWorker.schedule(new Action0() { // from class: com.jingdong.common.jdreactFramework.modules.community.upload.utils.a
            @Override // rx.functions.Action0
            public final void call() {
                file.delete();
            }
        });
    }

    public static void deleteVideo() {
        Schedulers.io().createWorker().schedule(new Action0() { // from class: com.jingdong.common.jdreactFramework.modules.community.upload.utils.MediaUtils.1
            @Override // rx.functions.Action0
            public void call() {
                File[] listFiles;
                File externalFilesDir = PermissionHelper.getExternalFilesDir(MediaUtils.VIDEO_COMPRESS_SAVE_DIR);
                if (externalFilesDir == null || !externalFilesDir.exists() || !externalFilesDir.isDirectory() || (listFiles = externalFilesDir.listFiles()) == null || listFiles.length <= 0) {
                    return;
                }
                for (File file : listFiles) {
                    if (file != null && file.exists() && file.isFile()) {
                        file.delete();
                    }
                }
            }
        });
    }

    @NonNull
    public static File getCompressVideoFile(@NonNull UploadMedia uploadMedia) {
        File videoFile = getVideoFile(uploadMedia);
        File file = !TextUtils.isEmpty(videoFile.getName()) ? new File(getOutPutPath(videoFile.getName())) : null;
        return file == null ? new File("") : file;
    }

    public static String getOutPutPath(String str) {
        File externalFilesDir = PermissionHelper.getExternalFilesDir(VIDEO_COMPRESS_SAVE_DIR);
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdir();
        }
        return externalFilesDir.getAbsolutePath() + "/" + str;
    }

    public static long getVideoDuration(String str) {
        File file = new File(str);
        if (file.exists()) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            if (TextUtils.isEmpty(extractMetadata)) {
                return 0L;
            }
            return Long.parseLong(extractMetadata);
        }
        return -100L;
    }

    @NonNull
    public static File getVideoFile(@NonNull UploadMedia uploadMedia) {
        File file = (uploadMedia.getMediaUri() == null || TextUtils.isEmpty(uploadMedia.getMediaUri().getPath())) ? null : new File(uploadMedia.getMediaUri().getPath());
        return file == null ? new File("") : file;
    }
}
