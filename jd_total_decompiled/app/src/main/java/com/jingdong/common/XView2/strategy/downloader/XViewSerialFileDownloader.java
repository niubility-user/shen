package com.jingdong.common.XView2.strategy.downloader;

import android.content.Context;
import com.jd.hybrid.downloader.FileRequest;
import com.jd.hybrid.downloader.l.a;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class XViewSerialFileDownloader {
    private static final String TAG = "XViewSerialFileDownloader";
    private static XViewSerialFileDownloader instance;
    private final ThreadPoolExecutor downloadExecutor;
    private Context mContext;
    private List<a> mIDownloadCondition;
    public AtomicInteger totalCount = new AtomicInteger(0);
    private volatile ConcurrentLinkedQueue<Integer> downloadWaitingQueue = new ConcurrentLinkedQueue<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class FileDownloadRunnable implements Runnable, Comparable<FileDownloadRunnable> {
        private final FileRequest mJDFileRequest;
        private final int priority;

        public FileDownloadRunnable(FileRequest fileRequest, int i2) {
            this.mJDFileRequest = fileRequest;
            this.priority = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            XViewSerialFileDownloader.this.downloadWaitingQueue.remove(Integer.valueOf(hashCode()));
            if (this.mJDFileRequest != null) {
                XViewLogPrint.JDXLog.d("XViewFileDownloader", "execute file request -> " + this.mJDFileRequest.getUrl());
            }
            XViewDownloader.executeAction(XViewSerialFileDownloader.this.mContext, this.mJDFileRequest);
        }

        @Override // java.lang.Comparable
        public int compareTo(FileDownloadRunnable fileDownloadRunnable) {
            return fileDownloadRunnable.priority - this.priority;
        }
    }

    /* loaded from: classes5.dex */
    public static class XViewFileRequest implements Comparable<XViewFileRequest> {
        int priority;
        FileRequest request;

        public XViewFileRequest(FileRequest fileRequest, int i2) {
            this.request = fileRequest;
            this.priority = i2;
        }

        @Override // java.lang.Comparable
        public int compareTo(XViewFileRequest xViewFileRequest) {
            return xViewFileRequest.priority - this.priority;
        }
    }

    private XViewSerialFileDownloader(Context context) {
        this.mContext = context.getApplicationContext();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(SwitchQueryFetcher.getSwitchBooleanValue("XViewCorePoolSize", true) ? 1 : 3, Integer.MAX_VALUE, 15L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new ThreadPoolExecutor.DiscardOldestPolicy());
        this.downloadExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static XViewSerialFileDownloader getInstance(Context context) {
        if (instance == null) {
            synchronized (XViewSerialFileDownloader.class) {
                if (instance == null) {
                    instance = new XViewSerialFileDownloader(context);
                }
            }
        }
        return instance;
    }

    public void add(XViewFileRequest xViewFileRequest) {
        execute(xViewFileRequest.request, xViewFileRequest.priority);
    }

    public void execute(FileRequest fileRequest, int i2) {
        FileDownloadRunnable fileDownloadRunnable = new FileDownloadRunnable(fileRequest, i2);
        this.downloadWaitingQueue.offer(Integer.valueOf(fileDownloadRunnable.hashCode()));
        this.downloadExecutor.execute(fileDownloadRunnable);
    }

    public void add(List<XViewFileRequest> list, boolean z) {
        if (list.size() > 1 && !z) {
            Collections.sort(list);
        }
        Iterator<XViewFileRequest> it = list.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }
}
