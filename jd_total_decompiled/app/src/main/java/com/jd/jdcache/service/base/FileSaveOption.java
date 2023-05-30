package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001Bo\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\n\u001a\u00020\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/jd/jdcache/service/base/FileSaveOption;", "Lcom/jd/jdcache/service/base/FileRequestOption;", "", "mergeWithFile", "Ljava/lang/String;", "getMergeWithFile", "()Ljava/lang/String;", "unzipDir", "getUnzipDir", "", "needUnzip", "Z", "getNeedUnzip", "()Z", JDReactConstant.BUFF_DIR_SPLIT, "getSplit", "method", "", "header", "userAgent", "cookie", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class FileSaveOption extends FileRequestOption {
    @Nullable
    private final String mergeWithFile;
    private final boolean needUnzip;
    private final boolean split;
    @Nullable
    private final String unzipDir;

    public FileSaveOption() {
        this(null, null, null, null, false, null, false, null, 255, null);
    }

    public /* synthetic */ FileSaveOption(String str, Map map, String str2, String str3, boolean z, String str4, boolean z2, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "GET" : str, (i2 & 2) != 0 ? null : map, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? null : str4, (i2 & 64) == 0 ? z2 : false, (i2 & 128) == 0 ? str5 : null);
    }

    @Nullable
    public final String getMergeWithFile() {
        return this.mergeWithFile;
    }

    public final boolean getNeedUnzip() {
        return this.needUnzip;
    }

    public final boolean getSplit() {
        return this.split;
    }

    @Nullable
    public final String getUnzipDir() {
        return this.unzipDir;
    }

    public FileSaveOption(@NotNull String str, @Nullable Map<String, String> map, @Nullable String str2, @Nullable String str3, boolean z, @Nullable String str4, boolean z2, @Nullable String str5) {
        super(str, map, str2, str3);
        this.needUnzip = z;
        this.unzipDir = str4;
        this.split = z2;
        this.mergeWithFile = str5;
    }
}
