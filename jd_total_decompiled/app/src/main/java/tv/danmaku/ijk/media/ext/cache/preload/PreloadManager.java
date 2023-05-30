package tv.danmaku.ijk.media.ext.cache.preload;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import tv.danmaku.ijk.media.ext.cache.VideoCacheConfig;
import tv.danmaku.ijk.media.ext.cache.VideoLRUCache;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadTask;
import tv.danmaku.ijk.media.player.threadpool.DefaultThreadFactory;

/* loaded from: classes11.dex */
public class PreloadManager {
    private static final int QUEUE_MAX_SIZE = 8;
    public static String mCacheDir;
    public static long mPreLoadSize;
    private static PreloadManager sPreloadManager;
    private String curLoadUrl;
    private ThreadPoolExecutor preLoadProcessor;
    private VideoLRUCache videoLRUCache;
    private boolean hasInit = false;
    private final HashMap<String, PreloadTask> mLoadTasks = new HashMap<>();
    private final ArrayList<UrlEntity> tempVideoInfoList = new ArrayList<>();

    /* loaded from: classes11.dex */
    public interface PreloadCallback {
        void onFullPreloadResult(boolean z);
    }

    private PreloadManager() {
    }

    private boolean checkInit() {
        boolean z = this.hasInit;
        return this.hasInit;
    }

    public static PreloadManager getInstance() {
        if (sPreloadManager == null) {
            synchronized (PreloadManager.class) {
                if (sPreloadManager == null) {
                    sPreloadManager = new PreloadManager();
                }
            }
        }
        return sPreloadManager;
    }

    private void keepQueueSize() {
        Map.Entry<String, PreloadTask> next;
        if (this.preLoadProcessor.getQueue().size() <= 8 || this.mLoadTasks.isEmpty() || (next = this.mLoadTasks.entrySet().iterator().next()) == null) {
            return;
        }
        removePreloadTask(next.getKey());
    }

    public void doPreload(List<String> list) {
        doPreload(list, false);
    }

    public String getCurLoadUrl() {
        return this.curLoadUrl;
    }

    public PreloadManager init(VideoCacheConfig videoCacheConfig, VideoLRUCache videoLRUCache) {
        if (this.hasInit) {
            return this;
        }
        String str = "pre load is running, max sync thread: " + videoCacheConfig.mPreMaxSync + " , preLoad size=" + videoCacheConfig.mPreLoadSize;
        mPreLoadSize = videoCacheConfig.mPreLoadSize;
        mCacheDir = videoCacheConfig.mCacheDirPath;
        int i2 = videoCacheConfig.mPreMaxSync;
        this.preLoadProcessor = new ThreadPoolExecutor(videoCacheConfig.mPreMaxSync, i2 > 6 ? 6 : i2, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(8), new DefaultThreadFactory("Player_Thread_Pool", 2));
        this.videoLRUCache = videoLRUCache;
        this.hasInit = true;
        return this;
    }

    public void release() {
        ThreadPoolExecutor threadPoolExecutor = this.preLoadProcessor;
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            this.preLoadProcessor.shutdown();
        }
        removeAllPreloadTask();
        sPreloadManager = null;
        this.hasInit = false;
    }

    public void removeAllPreloadTask() {
        HashMap<String, PreloadTask> hashMap;
        if (!checkInit() || (hashMap = this.mLoadTasks) == null || hashMap.isEmpty()) {
            return;
        }
        this.mLoadTasks.clear();
    }

    public void removePreloadTask(String str) {
        PreloadTask preloadTask;
        if (!checkInit() || this.mLoadTasks.isEmpty() || (preloadTask = this.mLoadTasks.get(str)) == null) {
            return;
        }
        preloadTask.cancel();
        PreloadTask remove = this.mLoadTasks.remove(str);
        if (remove != null) {
            this.preLoadProcessor.getQueue().remove(remove);
        }
    }

    public void doPreload(List<String> list, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.tempVideoInfoList.clear();
        if (z) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.tempVideoInfoList.add(new UrlEntity(list.get(i2)));
            }
        } else {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.tempVideoInfoList.add(new UrlEntity(list.get(size)));
            }
        }
        if (this.tempVideoInfoList.isEmpty()) {
            return;
        }
        Collections.sort(this.tempVideoInfoList);
        Iterator<UrlEntity> it = this.tempVideoInfoList.iterator();
        while (it.hasNext()) {
            doPreload(it.next());
        }
    }

    public void doPreload(String str, boolean z, PreloadCallback preloadCallback) {
        if (!checkInit() || str == null || TextUtils.isEmpty(str) || this.mLoadTasks.containsKey(str)) {
            return;
        }
        doPreload(new UrlEntity(str, z, preloadCallback));
    }

    public void doPreload(String str) {
        doPreload(str, false, null);
    }

    private void doPreload(final UrlEntity urlEntity) {
        ThreadPoolExecutor threadPoolExecutor;
        if (checkInit() && urlEntity != null && !TextUtils.isEmpty(urlEntity.getOriginUrl()) && !this.mLoadTasks.containsKey(urlEntity.getOriginUrl()) && (threadPoolExecutor = this.preLoadProcessor) != null && !threadPoolExecutor.isShutdown()) {
            try {
                keepQueueSize();
                PreloadTask preloadTask = new PreloadTask(urlEntity, new PreloadTask.ResultCallback() { // from class: tv.danmaku.ijk.media.ext.cache.preload.PreloadManager.1
                    @Override // tv.danmaku.ijk.media.ext.cache.preload.PreloadTask.ResultCallback
                    public void onResult(String str, boolean z) {
                        if (!PreloadManager.this.mLoadTasks.isEmpty()) {
                            PreloadManager.this.mLoadTasks.remove(str);
                        }
                        if (urlEntity.getCallback() != null) {
                            urlEntity.getCallback().onFullPreloadResult(z);
                        }
                        PreloadManager.this.curLoadUrl = null;
                    }

                    @Override // tv.danmaku.ijk.media.ext.cache.preload.PreloadTask.ResultCallback
                    public void onTaskStart(String str) {
                        PreloadManager.this.curLoadUrl = str;
                    }
                });
                preloadTask.executeOn(this.preLoadProcessor, this.videoLRUCache);
                this.mLoadTasks.put(urlEntity.getOriginUrl(), preloadTask);
            } catch (Exception unused) {
            }
        }
    }
}
