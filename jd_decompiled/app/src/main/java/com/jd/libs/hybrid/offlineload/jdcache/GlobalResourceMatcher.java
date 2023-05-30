package com.jd.libs.hybrid.offlineload.jdcache;

import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0013J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\n2\u0010\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013R \u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0014R\u001c\u0010\u0016\u001a\u00020\u00158\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalResourceMatcher;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "Landroid/net/Uri;", "loadedUri", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "getGlobalFile", "(Landroid/net/Uri;)Lcom/jd/jdcache/entity/JDCacheLocalResp;", "", "Lcom/jd/libs/hybrid/offlineload/entity/CommonFile;", "fileList", "", "onConfig", "(Ljava/util/List;)V", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "onDestroy", "()V", "Ljava/util/List;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class GlobalResourceMatcher extends JDCacheResourceMatcher {
    private volatile List<? extends CommonFile> fileList;
    @NotNull
    private final String name = "GlobalResourceMatcher";

    private final JDCacheLocalResp getGlobalFile(Uri loadedUri) {
        boolean equals;
        List split$default;
        List split$default2;
        List<? extends CommonFile> list = this.fileList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (CommonFile commonFile : list) {
            if (commonFile != null) {
                Uri matchedUri = Uri.parse(commonFile.getUrl());
                String excludeSchemeMatchUrl = commonFile.getUrl();
                String uri = loadedUri.toString();
                Intrinsics.checkExpressionValueIsNotNull(uri, "loadedUri.toString()");
                Intrinsics.checkExpressionValueIsNotNull(matchedUri, "matchedUri");
                String scheme = matchedUri.getScheme();
                if (scheme != null) {
                    if (scheme.length() > 0) {
                        Intrinsics.checkExpressionValueIsNotNull(excludeSchemeMatchUrl, "excludeSchemeMatchUrl");
                        String scheme2 = matchedUri.getScheme();
                        if (scheme2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int length = scheme2.length();
                        if (excludeSchemeMatchUrl == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        excludeSchemeMatchUrl = excludeSchemeMatchUrl.substring(length);
                        Intrinsics.checkExpressionValueIsNotNull(excludeSchemeMatchUrl, "(this as java.lang.String).substring(startIndex)");
                    }
                }
                String scheme3 = loadedUri.getScheme();
                if (scheme3 != null) {
                    if (scheme3.length() > 0) {
                        String scheme4 = loadedUri.getScheme();
                        if (scheme4 == null) {
                            Intrinsics.throwNpe();
                        }
                        int length2 = scheme4.length();
                        if (uri == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        uri = uri.substring(length2);
                        Intrinsics.checkExpressionValueIsNotNull(uri, "(this as java.lang.String).substring(startIndex)");
                    }
                }
                if (!OfflineSwitchSetting.TYPE_4_PIC_COMPRESS_OFF) {
                    Intrinsics.checkExpressionValueIsNotNull(excludeSchemeMatchUrl, "excludeSchemeMatchUrl");
                    split$default = StringsKt__StringsKt.split$default((CharSequence) excludeSchemeMatchUrl, new String[]{"!"}, false, 0, 6, (Object) null);
                    split$default2 = StringsKt__StringsKt.split$default((CharSequence) uri, new String[]{"!"}, false, 0, 6, (Object) null);
                    equals = StringsKt__StringsJVMKt.equals((String) split$default.get(0), (String) split$default2.get(0), true);
                } else {
                    equals = StringsKt__StringsJVMKt.equals(excludeSchemeMatchUrl, uri, true);
                }
                if (equals) {
                    String url = commonFile.getUrl();
                    Intrinsics.checkExpressionValueIsNotNull(url, "commonFile.url");
                    JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(url, "", null, null, null, false, 28, null);
                    jDCacheLocalResp.setFilename(commonFile.getFilePath());
                    jDCacheLocalResp.header = commonFile.getHeaderParams();
                    return jDCacheLocalResp;
                }
            }
        }
        return null;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        WebResourceResponse createResponse;
        if (!getDestroyed().get() && Build.VERSION.SDK_INT >= 21) {
            Uri url = request.getUrl();
            Intrinsics.checkExpressionValueIsNotNull(url, "request.url");
            JDCacheLocalResp globalFile = getGlobalFile(url);
            if (globalFile == null || (createResponse = JDCacheLocalRespKt.createResponse(globalFile, null)) == null) {
                return null;
            }
            LocalResourceResponse localResourceResponse = new LocalResourceResponse(createResponse);
            localResourceResponse.setFromType(LocalFileType.FILE_TYPE_GLOBAL);
            localResourceResponse.setLocalFile(globalFile);
            return localResourceResponse;
        }
        return null;
    }

    public final void onConfig(@Nullable List<? extends CommonFile> fileList) {
        if (getDestroyed().get()) {
            return;
        }
        this.fileList = fileList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void onDestroy() {
        super.onDestroy();
        this.fileList = null;
    }
}
