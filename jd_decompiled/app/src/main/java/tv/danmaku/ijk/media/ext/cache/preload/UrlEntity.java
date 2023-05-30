package tv.danmaku.ijk.media.ext.cache.preload;

import android.net.Uri;
import java.io.Serializable;
import tv.danmaku.ijk.media.example.widget.media.IjkUtils;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class UrlEntity implements Serializable, Comparable<UrlEntity> {
    private final PreloadManager.PreloadCallback callback;
    private final long createTime;
    private boolean fullSize;
    private String key;
    private final String originUrl;
    private String rawUrl;

    public UrlEntity(String str) {
        this(str, false, null);
    }

    public PreloadManager.PreloadCallback getCallback() {
        return this.callback;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getKey() {
        return this.key;
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    public String getRawUrl() {
        return this.rawUrl;
    }

    public boolean isFullSize() {
        return this.fullSize;
    }

    public UrlEntity(String str, boolean z, PreloadManager.PreloadCallback preloadCallback) {
        this.createTime = System.nanoTime();
        this.originUrl = str;
        this.fullSize = z;
        Uri parseVideoPath = IjkUtils.parseVideoPath(str);
        if (parseVideoPath != null) {
            this.rawUrl = parseVideoPath.toString();
            this.key = PlayerToolsUtil.MD5(str);
        }
        this.callback = preloadCallback;
    }

    @Override // java.lang.Comparable
    public int compareTo(UrlEntity urlEntity) {
        if (urlEntity != null) {
            return (int) (urlEntity.getCreateTime() - getCreateTime());
        }
        return 0;
    }
}
