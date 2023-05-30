package tv.danmaku.ijk.media.ext.cache;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;
import tv.danmaku.ijk.media.ext.config.CacheConfigBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.threadpool.VideoPlayerThreadManager;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.PlayerFileUtils;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class JDPlayerVideoCache {
    private static JDPlayerVideoCache mInstance;
    private VideoCacheConfig mConfig;
    private PreloadManager preloadManager;
    private VideoLRUCache videoLRUCache;
    private boolean hasInit = false;
    private boolean realUseCache = false;
    private boolean realPreLoad = false;

    private JDPlayerVideoCache() {
    }

    private void applyLocalCache(IjkMediaPlayer ijkMediaPlayer, String str) {
        if (checkInit()) {
            checkPath();
            String MD5 = PlayerToolsUtil.MD5(str);
            String str2 = this.mConfig.mCacheDirPath + MD5 + JDPlayerConstant.IJK_CACHE_VIDEO_SUFFIX;
            String str3 = this.mConfig.mCacheDirPath + MD5 + JDPlayerConstant.IJK_CACHE_INDEX_SUFFIX;
            File file = new File(str2);
            this.realUseCache = file.exists();
            if (this.mConfig.mPreLoadEnable) {
                this.realPreLoad = file.length() - PreloadManager.mPreLoadSize < 100;
                if (!this.realUseCache) {
                    DebugLog.d(JDPlayerConstant.JDCacheTag, "file not exit\uff0cload from net, video path: " + str2);
                }
            }
            ijkMediaPlayer.setOption(1, "parse_cache_map", 1L);
            ijkMediaPlayer.setOption(1, "auto_save_map", 1L);
            ijkMediaPlayer.setOption(1, "cache_file_path", str2);
            ijkMediaPlayer.setOption(1, "cache_map_path", str3);
            VideoLRUCache videoLRUCache = this.videoLRUCache;
            if (videoLRUCache != null) {
                videoLRUCache.save(MD5, str2, str3, 0L);
            }
        }
    }

    private void checkPath() {
        File file = new File(this.mConfig.mCacheDirPath);
        if (file.exists()) {
            return;
        }
        String str = "checkPath -- create new file: " + file.getAbsolutePath() + ",result=" + file.mkdirs();
    }

    private boolean checkValid(String str) {
        return checkInit() && PlayerStringUtils.isMp4Url(str);
    }

    public static JDPlayerVideoCache getInstance() {
        if (mInstance == null) {
            synchronized (JDPlayerVideoCache.class) {
                if (mInstance == null) {
                    mInstance = new JDPlayerVideoCache();
                }
            }
        }
        return mInstance;
    }

    private void loadDynamicConfig(VideoCacheConfig videoCacheConfig) {
        PlayerConfigLoader.getInstance();
        CacheConfigBean cacheConfigBean = PlayerConfigLoader.cacheConfigBean;
        if (cacheConfigBean != null) {
            if (cacheConfigBean.getMaxCacheSize() > 0) {
                videoCacheConfig.mMaxCacheSize = PlayerConfigLoader.cacheConfigBean.getMaxCacheSize();
            }
            if (PlayerConfigLoader.cacheConfigBean.getMaxCacheTime() > 0) {
                videoCacheConfig.mMaxCacheTime = PlayerConfigLoader.cacheConfigBean.getMaxCacheTime();
            }
            if (PlayerConfigLoader.cacheConfigBean.isForceClosePreload()) {
                videoCacheConfig.mPreLoadEnable = false;
            }
            if (PlayerConfigLoader.cacheConfigBean.getPreloadSize() > 204800) {
                videoCacheConfig.mPreLoadSize = PlayerConfigLoader.cacheConfigBean.getPreloadSize();
            }
        }
    }

    private void removeErrorUrl(String str) {
        VideoLRUCache videoLRUCache;
        if (!checkInit() || (videoLRUCache = this.videoLRUCache) == null) {
            return;
        }
        videoLRUCache.removeErrorCache(str);
    }

    public boolean checkInit() {
        if (!this.hasInit) {
            DebugLog.e(JDPlayerConstant.JDCacheTag, "JDPlayerVideoCache must be init before used");
        }
        return this.hasInit;
    }

    public void clearCache(final Context context) {
        VideoPlayerThreadManager.getThreadPool().execute(new Runnable() { // from class: tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context context2 = context;
                    if (context2 != null && context2.getExternalCacheDir() != null) {
                        String str = context.getExternalCacheDir().getAbsolutePath() + VideoCacheConfig.DEFAULT_CACHE_PATH;
                        if (JDPlayerVideoCache.this.mConfig != null) {
                            str = JDPlayerVideoCache.this.mConfig.mCacheDirPath;
                        }
                        String str2 = "clear cache, cache path = " + str;
                        PlayerFileUtils.deleteFile(new File(str), false);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public void getRawUri(String str) {
        if (!checkInit() || str == null || TextUtils.isEmpty(str)) {
            return;
        }
        removeErrorUrl(str);
    }

    public void init(VideoCacheConfig videoCacheConfig) {
        if (this.hasInit) {
            return;
        }
        loadDynamicConfig(videoCacheConfig);
        this.mConfig = videoCacheConfig;
        this.videoLRUCache = new VideoLRUCache(videoCacheConfig);
        if (videoCacheConfig.mPreLoadEnable) {
            this.preloadManager = PreloadManager.getInstance().init(videoCacheConfig, this.videoLRUCache);
        }
        this.hasInit = true;
    }

    public boolean isPreLoad() {
        return this.realPreLoad;
    }

    public boolean isRealUseCache() {
        return this.realUseCache;
    }

    public void release() {
        try {
            PreloadManager preloadManager = this.preloadManager;
            if (preloadManager != null) {
                preloadManager.release();
            }
            VideoLRUCache videoLRUCache = this.videoLRUCache;
            if (videoLRUCache != null) {
                videoLRUCache.release();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        mInstance = null;
        this.hasInit = false;
    }

    public Uri setDataSource(IjkMediaPlayer ijkMediaPlayer, Context context, Uri uri, String str, Map<String, String> map) throws IOException, IllegalArgumentException {
        PreloadManager preloadManager;
        if (ijkMediaPlayer != null && str != null) {
            this.realUseCache = false;
            String uri2 = uri.toString();
            if (checkValid(uri2) && !uri2.contains(JDPlayerConstant.IJK_CACHE_HEAD)) {
                uri2 = JDPlayerConstant.IJK_CACHE_HEAD + uri2;
                VideoCacheConfig videoCacheConfig = this.mConfig;
                if (videoCacheConfig != null && videoCacheConfig.mPreLoadEnable && (preloadManager = this.preloadManager) != null) {
                    preloadManager.removePreloadTask(str);
                }
            }
            if (Build.VERSION.SDK_INT >= 14) {
                ijkMediaPlayer.setDataSource(context, Uri.parse(uri2), map);
            } else {
                ijkMediaPlayer.setDataSource(uri2);
            }
            if (checkValid(uri2) && uri2.contains(JDPlayerConstant.IJK_CACHE_HEAD)) {
                applyLocalCache(ijkMediaPlayer, str);
            }
            return Uri.parse(uri2);
        }
        throw new IllegalArgumentException("player is null, can not play");
    }
}
