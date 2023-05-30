package com.jd.libs.hybrid.offlineload.jdcache;

import androidx.annotation.Keep;
import com.jd.jdcache.entity.JDCacheDataSource;
import com.jd.jdcache.entity.JDCacheFileDetail;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.libs.hybrid.offlineload.entity.LocalFileEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\u0004\u001a\u00020\u0003*\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\b"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/DataSourceHelper;", "", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "Lcom/jd/jdcache/entity/JDCacheDataSource;", "convertToDataSource", "(Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;)Lcom/jd/jdcache/entity/JDCacheDataSource;", "<init>", "()V", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class DataSourceHelper {
    public static final DataSourceHelper INSTANCE = new DataSourceHelper();

    private DataSourceHelper() {
    }

    @NotNull
    public final JDCacheDataSource convertToDataSource(@NotNull OfflineFiles offlineFiles) {
        JDCacheFileDetail jDCacheFileDetail = new JDCacheFileDetail(new File(offlineFiles.getFileRootPath()));
        Map<String, LocalFileEntity> localFileMap = offlineFiles.getLocalFileMap();
        HashMap hashMap = localFileMap == null || localFileMap.isEmpty() ? null : new HashMap(offlineFiles.getLocalFileMap().size());
        if (hashMap != null) {
            for (LocalFileEntity localFileEntity : offlineFiles.getLocalFileMap().values()) {
                String str = localFileEntity.url;
                Intrinsics.checkExpressionValueIsNotNull(str, "it.url");
                String str2 = localFileEntity.type;
                Intrinsics.checkExpressionValueIsNotNull(str2, "it.type");
                JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(str, str2, localFileEntity.header, localFileEntity.fileName, null, false, 16, null);
                String str3 = localFileEntity.url;
                Intrinsics.checkExpressionValueIsNotNull(str3, "it.url");
                hashMap.put(str3, jDCacheLocalResp);
            }
        }
        return new JDCacheDataSource(jDCacheFileDetail, hashMap);
    }
}
