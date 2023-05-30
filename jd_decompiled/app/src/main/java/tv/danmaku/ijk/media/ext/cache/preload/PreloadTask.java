package tv.danmaku.ijk.media.ext.cache.preload;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.ext.cache.VideoLRUCache;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
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

    /* JADX WARN: Code restructure failed: missing block: B:183:0x0159, code lost:
        tv.danmaku.ijk.media.utils.DebugLog.d(tv.danmaku.ijk.media.JDPlayerConstant.JDCacheTag, "pre load end, is cancel:" + r14.mIsCanceled + ", " + r14.urlEntity.getRawUrl() + " , filename : " + (tv.danmaku.ijk.media.ext.cache.preload.PreloadManager.mCacheDir + r14.urlEntity.getKey() + tv.danmaku.ijk.media.JDPlayerConstant.IJK_CACHE_VIDEO_SUFFIX));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void start() {
        /*
            Method dump skipped, instructions count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.ext.cache.preload.PreloadTask.start():void");
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
