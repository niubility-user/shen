package com.jd.jdcache.entity;

import androidx.annotation.Keep;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u00a2\u0006\u0004\b#\u0010$B\u0011\b\u0016\u0012\u0006\u0010&\u001a\u00020%\u00a2\u0006\u0004\b#\u0010'J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0006H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\bJ\u0010\u0010\u0014\u001a\u00020\u0013H\u00d6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018R\"\u0010\u000e\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0019\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\u001cR\"\u0010\u000f\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0019\u001a\u0004\b\u001d\u0010\u000b\"\u0004\b\u001e\u0010\u001cR\"\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u001f\u001a\u0004\b \u0010\b\"\u0004\b!\u0010\"\u00a8\u0006("}, d2 = {"Lcom/jd/jdcache/entity/JDCacheFileDetail;", "", "", "exists", "()Z", "hasChanged", "", "component1", "()Ljava/lang/String;", "", "component2", "()J", "component3", "path", "lastModified", "totalSpace", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;JJ)Lcom/jd/jdcache/entity/JDCacheFileDetail;", "toString", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getLastModified", "setLastModified", "(J)V", "getTotalSpace", "setTotalSpace", "Ljava/lang/String;", "getPath", "setPath", "(Ljava/lang/String;)V", "<init>", "(Ljava/lang/String;JJ)V", "Ljava/io/File;", "file", "(Ljava/io/File;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final /* data */ class JDCacheFileDetail {
    private long lastModified;
    @NotNull
    private String path;
    private long totalSpace;

    public JDCacheFileDetail(@NotNull String str, long j2, long j3) {
        this.path = str;
        this.lastModified = j2;
        this.totalSpace = j3;
    }

    public static /* synthetic */ JDCacheFileDetail copy$default(JDCacheFileDetail jDCacheFileDetail, String str, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = jDCacheFileDetail.path;
        }
        if ((i2 & 2) != 0) {
            j2 = jDCacheFileDetail.lastModified;
        }
        long j4 = j2;
        if ((i2 & 4) != 0) {
            j3 = jDCacheFileDetail.totalSpace;
        }
        return jDCacheFileDetail.copy(str, j4, j3);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component2  reason: from getter */
    public final long getLastModified() {
        return this.lastModified;
    }

    /* renamed from: component3  reason: from getter */
    public final long getTotalSpace() {
        return this.totalSpace;
    }

    @NotNull
    public final JDCacheFileDetail copy(@NotNull String path, long lastModified, long totalSpace) {
        return new JDCacheFileDetail(path, lastModified, totalSpace);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof JDCacheFileDetail) {
                JDCacheFileDetail jDCacheFileDetail = (JDCacheFileDetail) other;
                return Intrinsics.areEqual(this.path, jDCacheFileDetail.path) && this.lastModified == jDCacheFileDetail.lastModified && this.totalSpace == jDCacheFileDetail.totalSpace;
            }
            return false;
        }
        return true;
    }

    public final boolean exists() {
        return new File(this.path).exists();
    }

    public final long getLastModified() {
        return this.lastModified;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    public final long getTotalSpace() {
        return this.totalSpace;
    }

    public final boolean hasChanged() {
        File file = new File(this.path);
        return (file.exists() && file.lastModified() == this.lastModified) ? false : true;
    }

    public int hashCode() {
        String str = this.path;
        int hashCode = str != null ? str.hashCode() : 0;
        long j2 = this.lastModified;
        long j3 = this.totalSpace;
        return (((hashCode * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
    }

    public final void setLastModified(long j2) {
        this.lastModified = j2;
    }

    public final void setPath(@NotNull String str) {
        this.path = str;
    }

    public final void setTotalSpace(long j2) {
        this.totalSpace = j2;
    }

    @NotNull
    public String toString() {
        return "JDCacheFileDetail(path=" + this.path + ", lastModified=" + this.lastModified + ", totalSpace=" + this.totalSpace + ")";
    }

    public /* synthetic */ JDCacheFileDetail(String str, long j2, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0L : j2, (i2 & 4) != 0 ? 0L : j3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDCacheFileDetail(@NotNull File file) {
        this(r1, file.lastModified(), file.getTotalSpace());
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
    }
}
