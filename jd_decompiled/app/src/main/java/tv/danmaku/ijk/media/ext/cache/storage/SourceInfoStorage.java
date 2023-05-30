package tv.danmaku.ijk.media.ext.cache.storage;

import java.util.ArrayList;

/* loaded from: classes11.dex */
public interface SourceInfoStorage {
    void clearCache();

    void delete(SourceInfo sourceInfo);

    ArrayList<SourceInfo> get();

    SourceInfo get(String str);

    void release();

    void save(String str, String str2, String str3, long j2);

    void save(SourceInfo sourceInfo);
}
