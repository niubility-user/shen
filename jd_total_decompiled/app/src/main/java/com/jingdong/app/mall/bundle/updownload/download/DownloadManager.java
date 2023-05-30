package com.jingdong.app.mall.bundle.updownload.download;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask;
import com.jingdong.app.mall.bundle.updownload.download.task.StreamDownloadTask;
import java.io.File;

/* loaded from: classes3.dex */
public class DownloadManager {
    private static final String TAG = "DownloadManager";
    public static final String TYPE_EMOJI = "emoji";
    public static final String TYPE_SKIN = "skin";
    public static final String TYPE_SMILEY = "smiley";
    private static volatile DownloadManager sInstance;
    private DownloadDispatcher mDownloadDispatcher = new DownloadDispatcher();

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        if (sInstance == null) {
            synchronized (DownloadManager.class) {
                if (sInstance == null) {
                    sInstance = new DownloadManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized DownloadTask addTask(String str, String str2, String str3, String str4, String str5, IDownloadListener iDownloadListener, Bundle bundle) {
        if (TextUtils.isEmpty(str3)) {
            if (iDownloadListener != null) {
                iDownloadListener.onFailure(str2, -1, "url is null", bundle);
            }
            return null;
        }
        if (!str3.startsWith("http://") && !str3.startsWith("https://")) {
            str3 = "https://" + str3;
        }
        if (TextUtils.isEmpty(str4)) {
            if (iDownloadListener != null) {
                iDownloadListener.onFailure(str2, -1, "do not addPath", bundle);
            }
            return null;
        }
        File file = new File(str4);
        if (!file.exists()) {
            file.mkdirs();
        }
        DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo();
        downloadTaskInfo.type = str;
        downloadTaskInfo.url = str3;
        downloadTaskInfo.tag = str2;
        downloadTaskInfo.filePath = str4;
        downloadTaskInfo.fileName = str5;
        downloadTaskInfo.attachmentBundle = bundle;
        downloadTaskInfo.tempPath = str4 + "/" + downloadTaskInfo.hashCode();
        File file2 = new File(downloadTaskInfo.tempPath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        downloadTaskInfo.tempName = str5.hashCode() + ".t";
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this, downloadTaskInfo);
        streamDownloadTask.setListener(iDownloadListener);
        try {
            return this.mDownloadDispatcher.addTask(str, str2, streamDownloadTask);
        } catch (Exception e2) {
            if (iDownloadListener != null) {
                iDownloadListener.onFailure(str2, -1, e2.getMessage(), bundle);
            }
            return null;
        }
    }

    public synchronized void addType(String str, boolean z, int i2) throws Exception {
        String str2 = "addPath() called with: type = [" + str + "], singleQueue = [" + z + "]";
        if (TextUtils.isEmpty(str)) {
            throw new Exception("key or path should not be empty");
        }
        if (z) {
            this.mDownloadDispatcher.addQueue(str, i2);
        }
    }

    public synchronized void cancel(String str, Object obj) throws Exception {
        this.mDownloadDispatcher.cancel(str, obj);
    }

    public synchronized DownloadTask getTask(String str, String str2) {
        return this.mDownloadDispatcher.getTask(str, str2);
    }

    public void init() {
        try {
            addType(TYPE_EMOJI, true);
            addType(TYPE_SMILEY, true);
            addType(TYPE_SKIN, true, 4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void pause(String str, Object obj) throws Exception {
        this.mDownloadDispatcher.pause(str, obj);
    }

    public synchronized void taskComplete(String str, Object obj) {
        try {
            this.mDownloadDispatcher.taskComplete(str, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void addType(String str, boolean z) throws Exception {
        addType(str, z, 1);
    }
}
