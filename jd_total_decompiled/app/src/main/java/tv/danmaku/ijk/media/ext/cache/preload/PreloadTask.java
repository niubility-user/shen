package tv.danmaku.ijk.media.ext.cache.preload;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.ext.cache.VideoLRUCache;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
import tv.danmaku.ijk.media.player.JDPlayerHelper;
import tv.danmaku.ijk.media.utils.PlayerFileUtils;

/* loaded from: classes11.dex */
public class PreloadTask implements Runnable, Comparable<PreloadTask> {
    private boolean mIsCanceled;
    private boolean mIsSubmit;
    private final ResultCallback resultCallback;
    public final UrlEntity urlEntity;
    private VideoLRUCache videoLRUCache;

    /* loaded from: classes11.dex */
    public interface ResultCallback {
        void onResult(String str, boolean z);

        void onTaskStart(String str);
    }

    public PreloadTask(UrlEntity urlEntity, ResultCallback resultCallback) {
        this.urlEntity = urlEntity;
        this.resultCallback = resultCallback;
    }

    private void addUAHeader(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null || !PlayerNetHeaderUtil.canUse()) {
            return;
        }
        httpURLConnection.addRequestProperty("User-Agent", PlayerNetHeaderUtil.generateUA());
        httpURLConnection.addRequestProperty("Referer", PlayerNetHeaderUtil.generateReferer(PlayerNetHeaderUtil.PlayerType.IJK_SH));
    }

    private File generateCacheFile() {
        UrlEntity urlEntity = this.urlEntity;
        if (urlEntity == null || TextUtils.isEmpty(urlEntity.getOriginUrl())) {
            return null;
        }
        String str = PreloadManager.mCacheDir + this.urlEntity.getKey() + JDPlayerConstant.IJK_CACHE_VIDEO_SUFFIX;
        if (!new File(str).exists()) {
            PlayerFileUtils.createFileDirs(PreloadManager.mCacheDir);
        }
        return new File(str);
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(HttpHeaders.CONTENT_RANGE);
        if (TextUtils.isEmpty(headerField)) {
            return -1L;
        }
        String[] split = headerField.split("/");
        if (split.length > 1) {
            headerField = split[1];
        }
        return Long.parseLong(headerField);
    }

    private String saveCacheMap(long j2, long j3) {
        UrlEntity urlEntity = this.urlEntity;
        if (urlEntity == null || TextUtils.isEmpty(urlEntity.getOriginUrl())) {
            return null;
        }
        String str = "";
        try {
            str = PreloadManager.mCacheDir + this.urlEntity.getKey() + JDPlayerConstant.IJK_CACHE_INDEX_SUFFIX;
            String str2 = "save cache file: " + str + ", cacheSize=" + (j2 / 1024) + "Kb";
            FileWriter fileWriter = new FileWriter(new File(str));
            fileWriter.write("tree_index:0\ntree_physical_init_pos:0\ntree_physical_size:" + j2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + "tree_file_size:" + j3 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + "tree-info-flush\nentry_logical_pos:0\nentry_physical_pos:0\nentry_size:" + j2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + "entry-info-flush");
            fileWriter.flush();
            fileWriter.close();
            return str;
        } catch (IOException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:302:0x0159, code lost:
        tv.danmaku.ijk.media.utils.DebugLog.d(tv.danmaku.ijk.media.JDPlayerConstant.JDCacheTag, "pre load end, is cancel:" + r14.mIsCanceled + ", " + r14.urlEntity.getRawUrl() + " , filename : " + (tv.danmaku.ijk.media.ext.cache.preload.PreloadManager.mCacheDir + r14.urlEntity.getKey() + tv.danmaku.ijk.media.JDPlayerConstant.IJK_CACHE_VIDEO_SUFFIX));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void start() {
        UrlEntity urlEntity = this.urlEntity;
        if (urlEntity == null || TextUtils.isEmpty(urlEntity.getRawUrl())) {
            return;
        }
        File generateCacheFile = generateCacheFile();
        if (generateCacheFile == null) {
            ResultCallback resultCallback = this.resultCallback;
            if (resultCallback != null) {
                resultCallback.onResult(this.urlEntity.getOriginUrl(), false);
            }
        } else if (generateCacheFile.exists() && !this.urlEntity.isFullSize()) {
            String str = "file exit, no need to preload, cache path: " + generateCacheFile;
            ResultCallback resultCallback2 = this.resultCallback;
            if (resultCallback2 != null) {
                resultCallback2.onResult(this.urlEntity.getOriginUrl(), true);
            }
        } else {
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    ResultCallback resultCallback3 = this.resultCallback;
                    if (resultCallback3 != null) {
                        resultCallback3.onTaskStart(this.urlEntity.getOriginUrl());
                    }
                    String generateMcdnUrl = JDPlayerHelper.getInstance().generateMcdnUrl(this.urlEntity.getRawUrl());
                    String str2 = "preLoad url: " + generateMcdnUrl;
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(generateMcdnUrl).openConnection();
                    try {
                        httpURLConnection2.setRequestMethod("GET");
                        httpURLConnection2.setConnectTimeout(5000);
                        httpURLConnection2.setReadTimeout(5000);
                        if (!this.urlEntity.isFullSize()) {
                            httpURLConnection2.setRequestProperty(HttpHeaders.RANGE, "bytes=0-" + PreloadManager.mPreLoadSize);
                        } else {
                            httpURLConnection2.setRequestProperty(HttpHeaders.RANGE, "bytes=0-");
                        }
                        addUAHeader(httpURLConnection2);
                        long contentLength = getContentLength(httpURLConnection2);
                        String contentType = httpURLConnection2.getContentType();
                        if (!JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE.equals(contentType)) {
                            String str3 = "non support cache video type : " + contentType;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                                return;
                            }
                            return;
                        }
                        httpURLConnection2.connect();
                        if (this.urlEntity.isFullSize()) {
                            contentLength = httpURLConnection2.getContentLength();
                            if (generateCacheFile.exists() && generateCacheFile.length() == contentLength) {
                                ResultCallback resultCallback4 = this.resultCallback;
                                if (resultCallback4 != null) {
                                    resultCallback4.onResult(this.urlEntity.getOriginUrl(), true);
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                    return;
                                }
                                return;
                            }
                            generateCacheFile.delete();
                        }
                        if (httpURLConnection2.getResponseCode() == 206) {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                            FileOutputStream fileOutputStream = new FileOutputStream(generateCacheFile);
                            long j2 = 0;
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = bufferedInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                fileOutputStream.flush();
                                j2 += read;
                                if (this.mIsCanceled) {
                                    break;
                                } else if (this.urlEntity.isFullSize() || (!this.mIsCanceled && j2 < PreloadManager.mPreLoadSize)) {
                                }
                            }
                            long j3 = j2;
                            bufferedInputStream.close();
                            fileOutputStream.close();
                            if (j3 < PreloadManager.mPreLoadSize) {
                                String str4 = "pre load error\uff1a" + this.urlEntity.getRawUrl();
                                ResultCallback resultCallback5 = this.resultCallback;
                                if (resultCallback5 != null) {
                                    resultCallback5.onResult(this.urlEntity.getOriginUrl(), false);
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                    return;
                                }
                                return;
                            }
                            String saveCacheMap = saveCacheMap(j3, contentLength);
                            if (this.videoLRUCache != null && !TextUtils.isEmpty(saveCacheMap)) {
                                this.videoLRUCache.save(this.urlEntity.getKey(), generateCacheFile.getAbsolutePath(), saveCacheMap, j3);
                            }
                            ResultCallback resultCallback6 = this.resultCallback;
                            if (resultCallback6 != null) {
                                resultCallback6.onResult(this.urlEntity.getOriginUrl(), true);
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        httpURLConnection = httpURLConnection2;
                        e.printStackTrace();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        httpURLConnection = httpURLConnection2;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
    }

    public void cancel() {
        if (this.mIsSubmit) {
            this.mIsCanceled = true;
        }
    }

    public void executeOn(ExecutorService executorService, VideoLRUCache videoLRUCache) {
        if (this.mIsSubmit) {
            return;
        }
        this.videoLRUCache = videoLRUCache;
        executorService.execute(this);
        this.mIsSubmit = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.mIsCanceled) {
            start();
        }
        this.mIsSubmit = false;
        this.mIsCanceled = false;
    }

    @Override // java.lang.Comparable
    public int compareTo(PreloadTask preloadTask) {
        UrlEntity urlEntity;
        if (preloadTask == null || (urlEntity = preloadTask.urlEntity) == null || this.urlEntity == null) {
            return 0;
        }
        return (int) (urlEntity.getCreateTime() - this.urlEntity.getCreateTime());
    }
}
