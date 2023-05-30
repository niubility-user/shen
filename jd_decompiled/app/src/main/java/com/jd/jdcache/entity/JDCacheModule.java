package com.jd.jdcache.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001d\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0001,B1\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\u0004\b*\u0010+J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J:\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0010H\u00c6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\u0019\u0010\u000bR\u0013\u0010\u001a\u001a\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u0016\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u001c\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0013\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010 \u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010#R$\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010 \u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010#R\"\u0010\u0015\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010&\u001a\u0004\b'\u0010\u000f\"\u0004\b(\u0010)\u00a8\u0006-"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheModule;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "component1", "()Ljava/lang/String;", "component2", "", "component3", "()S", "", "component4", "()J", "configId", "url", "urlType", "createTime", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;SJ)Lcom/jd/jdcache/entity/JDCacheModule;", "toString", "isRegexpUrl", "()Z", "J", "getCreateTime", "setCreateTime", "(J)V", "Ljava/lang/String;", "getConfigId", "setConfigId", "(Ljava/lang/String;)V", "getUrl", "setUrl", "S", "getUrlType", "setUrlType", "(S)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;SJ)V", "Companion", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final /* data */ class JDCacheModule {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final short URL_TYPE_NORMAL = 1;
    public static final short URL_TYPE_REGEXP = 2;
    @NotNull
    private String configId;
    private long createTime;
    @Nullable
    private String url;
    private short urlType;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheModule$Companion;", "", "", "generateRandomId", "()Ljava/lang/String;", "", "URL_TYPE_NORMAL", "S", "URL_TYPE_REGEXP", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String generateRandomId() {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append('-');
            sb.append(Random.INSTANCE.nextInt(100, 1000));
            return sb.toString();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JDCacheModule() {
        this(null, null, (short) 0, 0L, 15, null);
    }

    public JDCacheModule(@NotNull String str, @Nullable String str2, short s, long j2) {
        this.configId = str;
        this.url = str2;
        this.urlType = s;
        this.createTime = j2;
    }

    public static /* synthetic */ JDCacheModule copy$default(JDCacheModule jDCacheModule, String str, String str2, short s, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = jDCacheModule.configId;
        }
        if ((i2 & 2) != 0) {
            str2 = jDCacheModule.url;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            s = jDCacheModule.urlType;
        }
        short s2 = s;
        if ((i2 & 8) != 0) {
            j2 = jDCacheModule.createTime;
        }
        return jDCacheModule.copy(str, str3, s2, j2);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String getConfigId() {
        return this.configId;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component3  reason: from getter */
    public final short getUrlType() {
        return this.urlType;
    }

    /* renamed from: component4  reason: from getter */
    public final long getCreateTime() {
        return this.createTime;
    }

    @NotNull
    public final JDCacheModule copy(@NotNull String configId, @Nullable String url, short urlType, long createTime) {
        return new JDCacheModule(configId, url, urlType, createTime);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof JDCacheModule)) {
            return false;
        }
        return Intrinsics.areEqual(this.configId, ((JDCacheModule) other).configId);
    }

    @NotNull
    public final String getConfigId() {
        return this.configId;
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final short getUrlType() {
        return this.urlType;
    }

    public int hashCode() {
        return this.configId.hashCode();
    }

    public final boolean isRegexpUrl() {
        return 2 == this.urlType && !TextUtils.isEmpty(this.url);
    }

    public final void setConfigId(@NotNull String str) {
        this.configId = str;
    }

    public final void setCreateTime(long j2) {
        this.createTime = j2;
    }

    public final void setUrl(@Nullable String str) {
        this.url = str;
    }

    public final void setUrlType(short s) {
        this.urlType = s;
    }

    @NotNull
    public String toString() {
        return "JDCacheModule(configId=" + this.configId + ", url=" + this.url + ", urlType=" + ((int) this.urlType) + ", createTime=" + this.createTime + ")";
    }

    public /* synthetic */ JDCacheModule(String str, String str2, short s, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? INSTANCE.generateRandomId() : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? (short) 1 : s, (i2 & 8) != 0 ? System.currentTimeMillis() : j2);
    }
}
