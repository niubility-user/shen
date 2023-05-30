package tv.danmaku.ijk.media.ext.cache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.ext.cache.storage.DBSourceInfoStorage;
import tv.danmaku.ijk.media.ext.cache.storage.SourceInfo;
import tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage;
import tv.danmaku.ijk.media.utils.PlayerFileUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class VideoLRUCache {
    private static final int MSG_CLEAR_CACHE = 5;
    private static final int MSG_CREATE_DB = 3;
    private static final int MSG_RELEASE = 4;
    private static final int MSG_REMOVE_CACHE = 2;
    private static final int MSG_SAVE_CACHE = 1;
    private final HandlerThread cacheHandlerThread;
    private final VideoCacheConfig config;
    private SourceInfoStorage infoStorage;
    private final AtomicBoolean isChecking = new AtomicBoolean(false);
    private final AtomicBoolean isRelease = new AtomicBoolean(false);
    private final Handler workHandler;

    public VideoLRUCache(VideoCacheConfig videoCacheConfig) {
        this.config = videoCacheConfig;
        HandlerThread handlerThread = new HandlerThread(VideoLRUCache.class.getSimpleName());
        this.cacheHandlerThread = handlerThread;
        handlerThread.start();
        this.workHandler = new Handler(handlerThread.getLooper()) { // from class: tv.danmaku.ijk.media.ext.cache.VideoLRUCache.1
            {
                VideoLRUCache.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(@NonNull Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    Object obj = message.obj;
                    if (obj instanceof SourceInfo) {
                        SourceInfo sourceInfo = (SourceInfo) obj;
                        VideoLRUCache.this.save(sourceInfo.key, sourceInfo.videoPath, sourceInfo.indexPath, sourceInfo.fileSize);
                    }
                } else if (i2 == 2) {
                    Object obj2 = message.obj;
                    if (obj2 instanceof String) {
                        VideoLRUCache.this.removeErrorCache((String) obj2);
                    }
                } else if (i2 == 3) {
                    VideoLRUCache.this.createDb();
                } else if (i2 == 4) {
                    VideoLRUCache.this.release();
                } else if (i2 != 5) {
                } else {
                    VideoLRUCache.this.clearCache();
                }
            }
        };
        createDb();
    }

    private void checkCacheSize(String str) {
        SourceInfoStorage sourceInfoStorage;
        ArrayList<SourceInfo> arrayList;
        if (this.isRelease.get() || this.isChecking.get() || (sourceInfoStorage = this.infoStorage) == null || (arrayList = sourceInfoStorage.get()) == null || arrayList.isEmpty()) {
            return;
        }
        this.isChecking.set(true);
        Iterator<SourceInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            SourceInfo next = it.next();
            File file = new File(next.videoPath);
            if (file.exists()) {
                long length = file.length();
                if (next.fileSize != length) {
                    next.fileSize = length;
                    this.infoStorage.save(next);
                }
            } else {
                this.infoStorage.delete(next);
            }
        }
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<SourceInfo> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            SourceInfo next2 = it2.next();
            VideoCacheConfig videoCacheConfig = this.config;
            if (currentTimeMillis - next2.playTime > videoCacheConfig.mMaxCacheTime) {
                realDelete(next2);
            } else if (next2.fileSize + j2 > videoCacheConfig.mMaxCacheSize && !next2.key.equals(str)) {
                realDelete(next2);
            } else {
                j2 += next2.fileSize;
            }
        }
        this.isChecking.set(false);
    }

    public void createDb() {
        if (!isCacheThread()) {
            Message obtainMessage = this.workHandler.obtainMessage();
            obtainMessage.what = 3;
            this.workHandler.sendMessage(obtainMessage);
            return;
        }
        VideoCacheConfig videoCacheConfig = this.config;
        this.infoStorage = new DBSourceInfoStorage(videoCacheConfig.appContext, videoCacheConfig.mVideoCacheDbName);
        this.isRelease.set(false);
    }

    private boolean isCacheThread() {
        HandlerThread handlerThread = this.cacheHandlerThread;
        return handlerThread != null && handlerThread.getLooper() == Looper.myLooper();
    }

    private void realDelete(SourceInfo sourceInfo) {
        SourceInfoStorage sourceInfoStorage;
        if (sourceInfo == null || (sourceInfoStorage = this.infoStorage) == null) {
            return;
        }
        sourceInfoStorage.delete(sourceInfo);
        new File(sourceInfo.videoPath).deleteOnExit();
        new File(sourceInfo.indexPath).deleteOnExit();
        String str = "delete video cache = " + sourceInfo.videoPath;
        String str2 = "delete video index = " + sourceInfo.indexPath;
    }

    public void clearCache() {
        if (!isCacheThread()) {
            Message obtainMessage = this.workHandler.obtainMessage();
            obtainMessage.what = 5;
            this.workHandler.sendMessage(obtainMessage);
            return;
        }
        try {
            PlayerFileUtils.deleteFile(new File(this.config.mCacheDirPath), false);
            SourceInfoStorage sourceInfoStorage = this.infoStorage;
            if (sourceInfoStorage != null) {
                sourceInfoStorage.clearCache();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void release() {
        if (this.isRelease.get()) {
            return;
        }
        if (!isCacheThread()) {
            Message obtainMessage = this.workHandler.obtainMessage();
            obtainMessage.what = 4;
            this.workHandler.sendMessage(obtainMessage);
            return;
        }
        SourceInfoStorage sourceInfoStorage = this.infoStorage;
        if (sourceInfoStorage != null) {
            sourceInfoStorage.release();
            this.infoStorage = null;
        }
        this.workHandler.removeCallbacksAndMessages(null);
        this.cacheHandlerThread.quit();
        this.isRelease.set(true);
    }

    public void removeErrorCache(String str) {
        if (this.isRelease.get() || this.infoStorage == null) {
            return;
        }
        if (!isCacheThread()) {
            Message obtainMessage = this.workHandler.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = str;
            this.workHandler.sendMessage(obtainMessage);
            return;
        }
        SourceInfo sourceInfo = this.infoStorage.get(PlayerToolsUtil.MD5(str));
        if (sourceInfo != null) {
            realDelete(sourceInfo);
        }
    }

    public void save(String str, String str2, String str3, long j2) {
        if (this.isRelease.get()) {
            return;
        }
        if (!isCacheThread()) {
            SourceInfo sourceInfo = new SourceInfo();
            sourceInfo.key = str;
            sourceInfo.indexPath = str3;
            sourceInfo.videoPath = str2;
            Message obtainMessage = this.workHandler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = sourceInfo;
            this.workHandler.sendMessage(obtainMessage);
            return;
        }
        checkCacheSize(str);
        SourceInfoStorage sourceInfoStorage = this.infoStorage;
        if (sourceInfoStorage != null) {
            sourceInfoStorage.save(str, str2, str3, j2);
        }
    }
}
