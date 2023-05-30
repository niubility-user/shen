package com.jd.jdcache.service.impl.net;

import androidx.annotation.Keep;
import com.jd.jdcache.service.base.JDCacheNetDelegate;
import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.service.impl.net.CallbackInputStream;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0017\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJw\u0010\u000e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJy\u0010\u0011\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u000fJa\u0010\u0014\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u00020\u00028\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/jdcache/service/impl/net/NetConnection;", "Lcom/jd/jdcache/service/base/JDCacheNetDelegate;", "", "url", "method", "", "header", "userAgent", "cookie", "body", "", "followRedirect", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jd/jdcache/service/base/NetState;", "requestFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)Lkotlinx/coroutines/flow/Flow;", "Ljava/io/InputStream;", "connectFlow", "savePath", "Ljava/io/File;", "downloadFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class NetConnection extends JDCacheNetDelegate {
    @NotNull
    private final String name = "NetConnection";

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<InputStream>> connectFlow(@NotNull final String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect) {
        if (url.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), "Cannot start network connection, because url is empty.");
                return null;
            }
            return null;
        }
        BaseRequest<InputStream> baseRequest = new BaseRequest<InputStream>(url) { // from class: com.jd.jdcache.service.impl.net.NetConnection$connectFlow$request$1
            {
                String str = null;
                String str2 = null;
                String str3 = null;
                Map map = null;
                Map map2 = null;
                Map map3 = null;
                boolean z = false;
                String str4 = null;
                int i2 = 0;
                int i3 = 0;
                int i4 = R2.attr.textRightColor;
                DefaultConstructorMarker defaultConstructorMarker = null;
            }

            @Override // com.jd.jdcache.service.impl.net.BaseRequest
            @NotNull
            public String getTAG() {
                return "InputStreamRequest";
            }

            @Override // com.jd.jdcache.service.impl.net.BaseRequest
            @Nullable
            protected Object parseData(int i2, @Nullable Map<String, ? extends List<String>> map, long j2, @Nullable final InputStream inputStream, @NotNull Continuation<? super NetState<InputStream>> continuation) {
                if (i2 == 200) {
                    return new NetState.Complete(i2, map, j2, inputStream != null ? new CallbackInputStream(inputStream, new CallbackInputStream.StreamCallback() { // from class: com.jd.jdcache.service.impl.net.NetConnection$connectFlow$request$1$parseData$$inlined$let$lambda$1
                        {
                            NetConnection$connectFlow$request$1.this = this;
                        }

                        @Override // com.jd.jdcache.service.impl.net.CallbackInputStream.StreamCallback
                        public void onClose() {
                            disconnect();
                        }
                    }) : null);
                }
                return new NetState.Error(i2, new Exception("Net Error code = " + i2));
            }
        };
        baseRequest.setMethod(method);
        baseRequest.setHeader(header);
        baseRequest.setUserAgent(userAgent);
        baseRequest.setCookies(cookie);
        baseRequest.setBody(body);
        baseRequest.setAllowRedirect(followRedirect);
        return baseRequest.connectFlow();
    }

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<File>> downloadFlow(@NotNull String url, @NotNull String savePath, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie) {
        if (url.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), "Cannot download file, because url is empty.");
            }
            return null;
        }
        if (savePath.length() == 0) {
            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
            if (jDCacheLog2.getCanLog()) {
                jDCacheLog2.e(getName(), "Cannot download file[" + url + "], because savePath is empty.");
            }
            return null;
        }
        FileRequest fileRequest = new FileRequest(url, savePath);
        fileRequest.setMethod(method);
        return fileRequest.connectFlow();
    }

    @Override // com.jd.jdcache.service.base.AbstractDelegate
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<String>> requestFlow(@NotNull String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect) {
        if (url.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), "Cannot start network request, because url is empty.");
                return null;
            }
            return null;
        }
        HttpRequest httpRequest = new HttpRequest(url);
        httpRequest.setMethod(method);
        httpRequest.setHeader(header);
        httpRequest.setUserAgent(userAgent);
        httpRequest.setCookies(cookie);
        httpRequest.setBody(body);
        httpRequest.setAllowRedirect(followRedirect);
        return httpRequest.connectFlow();
    }
}
