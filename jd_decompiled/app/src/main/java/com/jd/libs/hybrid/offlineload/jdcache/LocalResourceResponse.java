package com.jd.libs.hybrid.offlineload.jdcache;

import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import cn.com.union.fido.common.MIMEType;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@RequiresApi(21)
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012B%\b\u0016\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0018BO\b\u0016\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0013\u0012\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001c\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0011\u0010\u001eR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/LocalResourceResponse;", "Landroid/webkit/WebResourceResponse;", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "localFile", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "getLocalFile", "()Lcom/jd/jdcache/entity/JDCacheLocalResp;", "setLocalFile", "(Lcom/jd/jdcache/entity/JDCacheLocalResp;)V", "Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "fromType", "Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "getFromType", "()Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "setFromType", "(Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;)V", "response", "<init>", "(Landroid/webkit/WebResourceResponse;)V", "", "mimeType", "encoding", "Ljava/io/InputStream;", "data", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V", "", HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "reasonPhrase", "", "responseHeaders", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/io/InputStream;)V", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class LocalResourceResponse extends WebResourceResponse {
    @NotNull
    private LocalFileType fromType;
    @Nullable
    private JDCacheLocalResp localFile;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public LocalResourceResponse(@org.jetbrains.annotations.NotNull android.webkit.WebResourceResponse r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getMimeType()
            if (r0 == 0) goto L7
            goto L9
        L7:
            java.lang.String r0 = "text/html"
        L9:
            r2 = r0
            java.lang.String r3 = r9.getEncoding()
            int r0 = r9.getStatusCode()
            if (r0 == 0) goto L1a
            int r0 = r9.getStatusCode()
            r4 = r0
            goto L1e
        L1a:
            r0 = 200(0xc8, float:2.8E-43)
            r4 = 200(0xc8, float:2.8E-43)
        L1e:
            java.lang.String r0 = r9.getReasonPhrase()
            if (r0 == 0) goto L25
            goto L27
        L25:
            java.lang.String r0 = "OK"
        L27:
            r5 = r0
            java.util.Map r6 = r9.getResponseHeaders()
            java.io.InputStream r7 = r9.getData()
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            com.jd.libs.hybrid.offlineload.entity.LocalFileType r9 = com.jd.libs.hybrid.offlineload.entity.LocalFileType.FILE_TYPE_UNKNOWN
            r8.fromType = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.jdcache.LocalResourceResponse.<init>(android.webkit.WebResourceResponse):void");
    }

    @NotNull
    public final LocalFileType getFromType() {
        return this.fromType;
    }

    @Nullable
    public final JDCacheLocalResp getLocalFile() {
        return this.localFile;
    }

    public final void setFromType(@NotNull LocalFileType localFileType) {
        this.fromType = localFileType;
    }

    public final void setLocalFile(@Nullable JDCacheLocalResp jDCacheLocalResp) {
        this.localFile = jDCacheLocalResp;
    }

    public LocalResourceResponse(@Nullable String str, @Nullable String str2, @NotNull InputStream inputStream) {
        super(str, str2, inputStream);
        this.fromType = LocalFileType.FILE_TYPE_UNKNOWN;
    }

    public /* synthetic */ LocalResourceResponse(String str, String str2, int i2, String str3, Map map, InputStream inputStream, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? MIMEType.MIME_TYPE_HTML : str, str2, (i3 & 4) != 0 ? 200 : i2, (i3 & 8) != 0 ? "OK" : str3, map, inputStream);
    }

    public LocalResourceResponse(@NotNull String str, @Nullable String str2, int i2, @NotNull String str3, @Nullable Map<String, String> map, @NotNull InputStream inputStream) {
        super(str, str2, i2, str3, map, inputStream);
        this.fromType = LocalFileType.FILE_TYPE_UNKNOWN;
    }
}
