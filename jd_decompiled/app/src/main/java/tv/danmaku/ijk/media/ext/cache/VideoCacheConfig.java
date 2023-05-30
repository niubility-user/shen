package tv.danmaku.ijk.media.ext.cache;

import android.content.Context;
import android.os.Environment;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class VideoCacheConfig {
    public static String DEFAULT_CACHE_DB_NAME = "JDVideoCacheDB";
    public static String DEFAULT_CACHE_PATH = "/JDVideoCacheDir/";
    public static long DEFAULT_CACHE_SIZE = 524288000;
    public static long DEFAULT_CACHE_TIME = 604800000;
    Context appContext;
    public final String mCacheDirPath;
    long mMaxCacheSize;
    long mMaxCacheTime;
    boolean mPreLoadEnable;
    public long mPreLoadSize;
    public final int mPreMaxSync;
    final String mVideoCacheDbName;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private Context appContext;
        private String cacheDirPath = VideoCacheConfig.DEFAULT_CACHE_PATH;
        private long maxCacheSize = VideoCacheConfig.DEFAULT_CACHE_SIZE;
        private long maxCacheTime = VideoCacheConfig.DEFAULT_CACHE_TIME;
        private String videoCacheDbName = VideoCacheConfig.DEFAULT_CACHE_DB_NAME;
        private boolean preLoadEnable = false;
        private int preMaxSync = 1;
        private long preLoadSize = 358400;

        public Builder appContext(Context context) {
            this.appContext = context;
            return this;
        }

        public VideoCacheConfig build() {
            return new VideoCacheConfig(this);
        }

        public Builder cacheDirPath(String str) {
            this.cacheDirPath = str;
            return this;
        }

        public Builder enablePreload(boolean z) {
            this.preLoadEnable = z;
            return this;
        }

        public Builder maxCacheSize(long j2) {
            this.maxCacheSize = j2;
            return this;
        }

        public Builder maxCacheTime(long j2) {
            this.maxCacheTime = j2;
            return this;
        }

        public Builder maxSyncPreload(int i2) {
            this.preMaxSync = i2;
            return this;
        }

        public Builder preloadSize(long j2) {
            this.preLoadSize = j2;
            return this;
        }

        public Builder videoCacheDbName(String str) {
            this.videoCacheDbName = str;
            return this;
        }
    }

    private VideoCacheConfig(Builder builder) {
        String str;
        if (builder.appContext != null && builder.appContext.getExternalCacheDir() != null) {
            str = builder.appContext.getExternalCacheDir().getAbsolutePath() + builder.cacheDirPath;
        } else {
            str = Environment.getExternalStorageState() + builder.cacheDirPath;
        }
        this.appContext = builder.appContext;
        this.mCacheDirPath = str;
        this.mMaxCacheSize = builder.maxCacheSize;
        this.mMaxCacheTime = builder.maxCacheTime;
        this.mVideoCacheDbName = builder.videoCacheDbName;
        this.mPreLoadEnable = builder.preLoadEnable;
        this.mPreMaxSync = builder.preMaxSync;
        this.mPreLoadSize = builder.preLoadSize;
        DebugLog.i(JDPlayerConstant.JDCacheTag, "video cache maxCacheSize: " + ((this.mMaxCacheSize / 1024) / 1024) + "MB ,maxCacheTime: " + ((((this.mMaxCacheTime / 24) / 60) / 60) / 1000) + "day ,file path: " + str);
    }
}
