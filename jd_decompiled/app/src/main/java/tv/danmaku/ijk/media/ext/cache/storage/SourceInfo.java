package tv.danmaku.ijk.media.ext.cache.storage;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class SourceInfo implements Serializable {
    public long fileSize;
    public String indexPath;
    public String key;
    public int playCount;
    public long playTime;
    public String videoPath;

    public SourceInfo() {
    }

    public SourceInfo(String str, long j2, int i2, String str2, String str3, long j3) {
        this.key = str;
        this.playTime = j2;
        this.playCount = i2;
        this.videoPath = str2;
        this.indexPath = str3;
        this.fileSize = j3;
    }
}
