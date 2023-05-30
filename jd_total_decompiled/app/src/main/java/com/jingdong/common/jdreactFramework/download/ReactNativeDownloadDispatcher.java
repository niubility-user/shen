package com.jingdong.common.jdreactFramework.download;

import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/* loaded from: classes5.dex */
public final class ReactNativeDownloadDispatcher {
    private static final int HIGH_PRIORITY = Integer.MAX_VALUE;
    private static final String TAG = "RNDispatcher";
    private static volatile ReactNativeDownloadDispatcher sInstance;
    private long endTime;
    private Runnable mIdleRunnable;
    private int mMaxDownloadRequest = 2;
    private Object wait = new Object();
    private final PriorityQueue<PluginDownloadInfo> mReadyInfos = new PriorityQueue<>(96, new Comparator<PluginDownloadInfo>() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeDownloadDispatcher.1
        @Override // java.util.Comparator
        public int compare(PluginDownloadInfo pluginDownloadInfo, PluginDownloadInfo pluginDownloadInfo2) {
            if (pluginDownloadInfo == null) {
                return 1;
            }
            return -pluginDownloadInfo.compareTo(pluginDownloadInfo2);
        }
    });
    private final Deque<PluginDownloadInfo> mDownloadingInfos = new ArrayDeque();

    private ReactNativeDownloadDispatcher() {
    }

    public static ReactNativeDownloadDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (ReactNativeDownloadDispatcher.class) {
                if (sInstance == null) {
                    sInstance = new ReactNativeDownloadDispatcher();
                }
            }
        }
        return sInstance;
    }

    private void performReadyRequest() {
        if (this.mDownloadingInfos.size() >= this.mMaxDownloadRequest) {
            return;
        }
        while (this.mDownloadingInfos.size() < this.mMaxDownloadRequest && this.mReadyInfos.size() > 0) {
            PluginDownloadInfo poll = this.mReadyInfos.poll();
            if (poll != null && poll.getHttpSetting() != null) {
                this.mDownloadingInfos.add(poll);
                JLog.e("RNLifeCycleObserver", "performReadyRequest " + poll.getPluginResult().pluginUpdateName);
                poll.getHttpSetting().download();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void enqueue(final PluginDownloadInfo pluginDownloadInfo, boolean z) {
        if (pluginDownloadInfo != null) {
            if (pluginDownloadInfo.getHttpSetting() != null) {
                pluginDownloadInfo.regisiterListener(new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeDownloadDispatcher.2
                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onDownloadProgressChanged(int i2) {
                    }

                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onFailure(String str, String str2, boolean z2, String str3) {
                        PluginDownloadInfo pluginDownloadInfo2 = pluginDownloadInfo;
                        if (pluginDownloadInfo2 != null) {
                            pluginDownloadInfo2.unregisiterListener(this);
                        }
                        ReactNativeDownloadDispatcher.this.finished(pluginDownloadInfo);
                    }

                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                        PluginDownloadInfo pluginDownloadInfo2 = pluginDownloadInfo;
                        if (pluginDownloadInfo2 != null) {
                            pluginDownloadInfo2.unregisiterListener(this);
                        }
                        ReactNativeDownloadDispatcher.this.finished(pluginDownloadInfo);
                    }
                });
                if (this.mDownloadingInfos.contains(pluginDownloadInfo)) {
                    return;
                }
                if (this.mDownloadingInfos.size() < this.mMaxDownloadRequest) {
                    this.mDownloadingInfos.add(pluginDownloadInfo);
                    pluginDownloadInfo.getHttpSetting().download();
                    JLog.e("RNLifeCycleObserver", "performReadyRequest " + pluginDownloadInfo.getPluginResult().pluginUpdateName);
                } else if (z) {
                    pluginDownloadInfo.setPriority(Integer.MAX_VALUE);
                    if (this.mReadyInfos.contains(pluginDownloadInfo)) {
                        this.mReadyInfos.remove(pluginDownloadInfo);
                        this.mReadyInfos.offer(pluginDownloadInfo);
                    } else {
                        this.mReadyInfos.offer(pluginDownloadInfo);
                    }
                } else if (!this.mReadyInfos.contains(pluginDownloadInfo)) {
                    this.mReadyInfos.offer(pluginDownloadInfo);
                }
            }
        }
    }

    public void finished(PluginDownloadInfo pluginDownloadInfo) {
        int size;
        Runnable runnable;
        if (pluginDownloadInfo == null) {
            return;
        }
        if (this.endTime > 0 && System.currentTimeMillis() > this.endTime) {
            synchronized (this.wait) {
                try {
                    JLog.e("RNLifeCycleObserver", "wait " + System.currentTimeMillis());
                    this.wait.wait();
                    JLog.e("RNLifeCycleObserver", "finished wait ");
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        synchronized (this) {
            this.mDownloadingInfos.remove(pluginDownloadInfo);
            performReadyRequest();
            size = this.mDownloadingInfos.size();
            runnable = this.mIdleRunnable;
        }
        if (size != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    public void notifyDequeCountDown() {
        int netCountDownMills = JDReactHelper.newInstance().getNetCountDownMills();
        if (netCountDownMills > 0) {
            this.endTime = System.currentTimeMillis() + netCountDownMills;
        } else {
            this.endTime = 0L;
        }
        JLog.e("RNLifeCycleObserver", "endTime is " + this.endTime);
    }

    public void notifyDequeStart() {
        this.endTime = 0L;
        try {
            synchronized (this.wait) {
                this.wait.notifyAll();
                JLog.e("RNLifeCycleObserver", "notifyAll ");
            }
        } catch (Throwable unused) {
        }
    }

    public void setIdleRunabble(Runnable runnable) {
        this.mIdleRunnable = runnable;
    }

    public synchronized void setMaxDownloadRequest(int i2) {
        if (i2 < 1) {
            return;
        }
        this.mMaxDownloadRequest = i2;
        performReadyRequest();
    }
}
