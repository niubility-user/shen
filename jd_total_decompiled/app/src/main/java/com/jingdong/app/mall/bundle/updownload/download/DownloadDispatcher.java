package com.jingdong.app.mall.bundle.updownload.download;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class DownloadDispatcher {
    private static final String TAG = "DownloadDispatcher";
    private static final String TYPE_COMMON = "common";
    private ArrayMap<String, ExecutorService> mExecutors = new ArrayMap<>();
    private ArrayMap<String, ArrayMap<Object, DownloadTask>> mTaskLists = new ArrayMap<>();

    /* loaded from: classes3.dex */
    public static class DownLoadThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public DownLoadThreadFactory(String str) {
            ThreadGroup threadGroup;
            str = TextUtils.isEmpty(str) ? "" : str;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.group = threadGroup;
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-" + str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public DownloadDispatcher() {
        this.mExecutors.put(TYPE_COMMON, new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new DownLoadThreadFactory("downloaddispatcher")));
        this.mTaskLists.put(TYPE_COMMON, new ArrayMap<>());
    }

    public synchronized void addQueue(@NonNull String str, int i2) throws Exception {
        String str2 = "addQueue() called with: type = [" + str + "]";
        if (!TextUtils.isEmpty(str)) {
            if (!this.mExecutors.containsKey(str)) {
                this.mExecutors.put(str, new ThreadPoolExecutor(i2, i2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new DownLoadThreadFactory("downloaddispatcher")));
                this.mTaskLists.put(str, new ArrayMap<>());
            }
        } else {
            throw new Exception("type should not be empty");
        }
    }

    public synchronized DownloadTask addTask(@NonNull String str, Object obj, @Nullable DownloadTask downloadTask) throws Exception {
        String str2 = "addTask() called with: type = [" + str + "], tag = [" + obj + "], task = [" + downloadTask + "]";
        if (!TextUtils.isEmpty(str)) {
            if (this.mExecutors.containsKey(str)) {
                ArrayMap<Object, DownloadTask> arrayMap = this.mTaskLists.get(str);
                DownloadTask downloadTask2 = arrayMap.get(obj);
                if (downloadTask2 == null) {
                    this.mExecutors.get(str).submit(downloadTask);
                    arrayMap.put(obj, downloadTask);
                    return downloadTask;
                }
                downloadTask2.setListener(downloadTask.getListener());
                return downloadTask2;
            }
            return null;
        }
        throw new Exception("type should not be empty");
    }

    public synchronized void cancel(@NonNull String str, @NonNull Object obj) throws Exception {
        String str2 = "remove() called with: type = [" + str + "], tag = [" + obj + "]";
        if (!TextUtils.isEmpty(str)) {
            ArrayMap<Object, DownloadTask> arrayMap = this.mTaskLists.get(str);
            DownloadTask downloadTask = arrayMap.get(obj);
            if (downloadTask != null) {
                downloadTask.cancel();
                arrayMap.remove(obj);
            }
        } else {
            throw new Exception("type or tag should not be empty");
        }
    }

    public synchronized DownloadTask getTask(@NonNull String str, Object obj) {
        if (this.mExecutors.containsKey(str)) {
            return this.mTaskLists.get(str).get(obj);
        }
        return null;
    }

    public synchronized void pause(@NonNull String str, @NonNull Object obj) throws Exception {
        String str2 = "pause() called with: type = [" + str + "], tag = [" + obj + "]";
        if (!TextUtils.isEmpty(str)) {
            ArrayMap<Object, DownloadTask> arrayMap = this.mTaskLists.get(str);
            DownloadTask downloadTask = arrayMap.get(obj);
            if (downloadTask != null) {
                downloadTask.pause();
                arrayMap.remove(obj);
            }
        } else {
            throw new Exception("type or tag should not be empty");
        }
    }

    public synchronized void taskComplete(String str, Object obj) throws Exception {
        String str2 = "taskComplete() called with: type = [" + str + "], tag = [" + obj + "]";
        if (!TextUtils.isEmpty(str)) {
            ArrayMap<Object, DownloadTask> arrayMap = this.mTaskLists.get(str);
            if (arrayMap != null) {
                arrayMap.remove(obj);
            }
        } else {
            throw new Exception("type or tag should not be empty");
        }
    }
}
