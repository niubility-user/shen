package com.jd.jdcache.match.impl;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.google.common.net.HttpHeaders;
import com.jd.jdcache.JDCacheConstant;
import com.jd.jdcache.JDCacheLoader;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.JDCacheSetting;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.match.PreReadInputStream;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.service.DelegateManager;
import com.jd.jdcache.service.base.FileSaveOption;
import com.jd.jdcache.service.base.FileState;
import com.jd.jdcache.service.base.JDCacheFileRepoDelegate;
import com.jd.jdcache.util.CancellableJob;
import com.jd.jdcache.util.CoroutineHelper;
import com.jd.jdcache.util.FileHelperKt;
import com.jd.jdcache.util.ICancellable;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b:\u0010\u0019J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J1\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ1\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0014\u00a2\u0006\u0004\b\u000b\u0010\nJ7\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u001e\u0010\u000e\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0018\u00010\fH\u0014\u00a2\u0006\u0004\b\u000f\u0010\nJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019R*\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u001a8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u00020\u00028\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\"\u001a\u0004\b&\u0010$\"\u0004\b'\u0010\u0006R\u001f\u0010-\u001a\u0004\u0018\u00010(8D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R$\u0010.\u001a\u0004\u0018\u00010\u00158\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u00102R$\u00104\u001a\u0004\u0018\u0001038\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109\u00a8\u0006;"}, d2 = {"Lcom/jd/jdcache/match/impl/PreloadHtmlMatcher;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "", "url", "", JDReactConstant.PREPARE, "(Ljava/lang/String;)V", "", "header", "downloadHtmlStream", "(Ljava/lang/String;Ljava/util/Map;)V", "downloadHtmlFile", "", "", "headers", "saveCookieFromRespHeaders", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "geDownloadLocalResp", "()Lcom/jd/jdcache/entity/JDCacheLocalResp;", "onDestroy", "()V", "Lkotlinx/coroutines/channels/Channel;", "waitingChannel", "Lkotlinx/coroutines/channels/Channel;", "getWaitingChannel", "()Lkotlinx/coroutines/channels/Channel;", "setWaitingChannel", "(Lkotlinx/coroutines/channels/Channel;)V", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "downloadUrl", "getDownloadUrl", "setDownloadUrl", "Lcom/jd/jdcache/service/base/JDCacheFileRepoDelegate;", "fileRepo$delegate", "Lkotlin/Lazy;", "getFileRepo", "()Lcom/jd/jdcache/service/base/JDCacheFileRepoDelegate;", "fileRepo", "localResp", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "getLocalResp", "setLocalResp", "(Lcom/jd/jdcache/entity/JDCacheLocalResp;)V", "Lcom/jd/jdcache/util/ICancellable;", "downloadTask", "Lcom/jd/jdcache/util/ICancellable;", "getDownloadTask", "()Lcom/jd/jdcache/util/ICancellable;", "setDownloadTask", "(Lcom/jd/jdcache/util/ICancellable;)V", "<init>", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class PreloadHtmlMatcher extends JDCacheResourceMatcher {
    @Nullable
    private ICancellable downloadTask;
    @Nullable
    private String downloadUrl;
    @Nullable

    /* renamed from: fileRepo$delegate  reason: from kotlin metadata */
    private final Lazy fileRepo;
    @Nullable
    private JDCacheLocalResp localResp;
    @NotNull
    private final String name = "PreloadHtmlMatcher";
    @Nullable
    private Channel<JDCacheLocalResp> waitingChannel;

    public PreloadHtmlMatcher() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDCacheFileRepoDelegate>() { // from class: com.jd.jdcache.match.impl.PreloadHtmlMatcher$fileRepo$2
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final JDCacheFileRepoDelegate invoke() {
                return (JDCacheFileRepoDelegate) DelegateManager.INSTANCE.getDelegate(JDCacheFileRepoDelegate.class);
            }
        });
        this.fileRepo = lazy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void downloadHtmlFile$default(PreloadHtmlMatcher preloadHtmlMatcher, String str, Map map, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                map = null;
            }
            preloadHtmlMatcher.downloadHtmlFile(str, map);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: downloadHtmlFile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void downloadHtmlStream$default(PreloadHtmlMatcher preloadHtmlMatcher, String str, Map map, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                map = null;
            }
            preloadHtmlMatcher.downloadHtmlStream(str, map);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: downloadHtmlStream");
    }

    protected void downloadHtmlFile(@NotNull final String url, @Nullable Map<String, String> header) {
        final Flow<FileState> saveFileFromNetFlow;
        Flow filterNotNull;
        this.downloadUrl = url;
        JDCacheSetting jDCacheSetting = JDCacheSetting.INSTANCE;
        JDCacheParamsProvider paramsProvider = jDCacheSetting.getParamsProvider();
        Flow flow = null;
        String userAgent = paramsProvider != null ? paramsProvider.getUserAgent(url) : null;
        JDCacheParamsProvider paramsProvider2 = jDCacheSetting.getParamsProvider();
        FileSaveOption fileSaveOption = new FileSaveOption(null, header, userAgent, paramsProvider2 != null ? paramsProvider2.getCookie(url) : null, false, null, false, null, 241, null);
        String str = HttpDnsConfig.PREDOWNLOAD_PARAMS + File.separatorChar + FileHelperKt.generateFileName(url);
        JDCacheFileRepoDelegate fileRepo = getFileRepo();
        if (fileRepo != null && (saveFileFromNetFlow = fileRepo.saveFileFromNetFlow(url, str, fileSaveOption)) != null && (filterNotNull = FlowKt.filterNotNull(new Flow<Pair<? extends Boolean, ? extends FileState.Complete>>() { // from class: com.jd.jdcache.match.impl.PreloadHtmlMatcher$downloadHtmlFile$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
                Object coroutine_suspended;
                Object collect = saveFileFromNetFlow.collect(new FlowCollector<FileState>() { // from class: com.jd.jdcache.match.impl.PreloadHtmlMatcher$downloadHtmlFile$$inlined$map$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @Nullable
                    public Object emit(FileState fileState, @NotNull Continuation continuation2) {
                        Object coroutine_suspended2;
                        FlowCollector flowCollector2 = flowCollector;
                        FileState fileState2 = fileState;
                        Pair pair = null;
                        if (fileState2 instanceof FileState.OnStart) {
                            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                            if (jDCacheLog.getCanLog()) {
                                jDCacheLog.d(this.getName(), "Starting pre-download html(" + url + ')');
                            }
                        } else if (fileState2 instanceof FileState.Complete) {
                            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                            if (jDCacheLog2.getCanLog()) {
                                jDCacheLog2.d(this.getName(), "Complete pre-downloading html(" + url + ')');
                            }
                            pair = TuplesKt.to(Boxing.boxBoolean(true), fileState2);
                        } else if (fileState2 instanceof FileState.Error) {
                            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                            if (jDCacheLog3.getCanLog()) {
                                String name = this.getName();
                                StringBuilder sb = new StringBuilder();
                                sb.append("Fail pre-downloading html, ");
                                sb.append("code=");
                                FileState.Error error = (FileState.Error) fileState2;
                                sb.append(error.getCode());
                                sb.append(", exception=");
                                sb.append(error.getThrowable());
                                jDCacheLog3.e(name, sb.toString());
                            }
                            pair = TuplesKt.to(Boxing.boxBoolean(true), null);
                        }
                        Object emit = flowCollector2.emit(pair, continuation2);
                        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        return emit == coroutine_suspended2 ? emit : Unit.INSTANCE;
                    }
                }, continuation);
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                return collect == coroutine_suspended ? collect : Unit.INSTANCE;
            }
        })) != null) {
            flow = FlowKt.onEach(filterNotNull, new PreloadHtmlMatcher$downloadHtmlFile$flow$2(this, url, null));
        }
        if (flow != null) {
            this.waitingChannel = ChannelKt.Channel(-1);
            this.downloadTask = new CancellableJob(FlowKt.launchIn(flow, JDCacheConstant.INSTANCE.getApplicationScope$JDCache_release()));
        }
    }

    protected void downloadHtmlStream(@NotNull String url, @Nullable Map<String, String> header) {
        this.downloadUrl = url;
        Job launchCoroutine$default = CoroutineHelper.launchCoroutine$default(CoroutineHelper.INSTANCE, this, null, new PreloadHtmlMatcher$downloadHtmlStream$job$1(this, header, url, null), 1, null);
        this.waitingChannel = ChannelKt.Channel(-1);
        this.downloadTask = new CancellableJob(launchCoroutine$default);
    }

    @Nullable
    protected JDCacheLocalResp geDownloadLocalResp() {
        Object runBlocking$default;
        if (this.waitingChannel != null) {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new PreloadHtmlMatcher$geDownloadLocalResp$$inlined$let$lambda$1(null, this), 1, null);
            return (JDCacheLocalResp) runBlocking$default;
        }
        return null;
    }

    @Nullable
    protected final ICancellable getDownloadTask() {
        return this.downloadTask;
    }

    @Nullable
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    @Nullable
    public final JDCacheFileRepoDelegate getFileRepo() {
        return (JDCacheFileRepoDelegate) this.fileRepo.getValue();
    }

    @Nullable
    public final JDCacheLocalResp getLocalResp() {
        return this.localResp;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @NotNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public final Channel<JDCacheLocalResp> getWaitingChannel() {
        return this.waitingChannel;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        Uri parse;
        WebResourceResponse createResponse$default;
        InputStream fileStream;
        JDCacheLoader loader = getLoader();
        if (loader == null || !loader.getPreloadHtml() || getDestroyed().get() || !request.isForMainFrame()) {
            return null;
        }
        String str = this.downloadUrl;
        if (str != null) {
            try {
                parse = Uri.parse(str);
            } catch (Exception unused) {
            }
            if (parse == null && UrlHelper.INSTANCE.matchHostPath(request.getUrl(), parse)) {
                this.downloadUrl = null;
                if (this.localResp == null) {
                    this.localResp = geDownloadLocalResp();
                    if (getDestroyed().get()) {
                        return null;
                    }
                    JDCacheLocalResp jDCacheLocalResp = this.localResp;
                    if (jDCacheLocalResp != null && (fileStream = jDCacheLocalResp.getFileStream()) != null && (fileStream instanceof PreReadInputStream)) {
                        ((PreReadInputStream) fileStream).finishPreRead();
                    }
                    JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                    if (jDCacheLog.getCanLog() && this.localResp != null) {
                        jDCacheLog.d(getName(), "Received pre-download html file. " + this.localResp);
                    }
                }
                JDCacheLocalResp jDCacheLocalResp2 = this.localResp;
                if (jDCacheLocalResp2 == null || (createResponse$default = JDCacheLocalRespKt.createResponse$default(jDCacheLocalResp2, null, 1, null)) == null) {
                    return null;
                }
                return createResponse(createResponse$default);
            }
        }
        parse = null;
        return parse == null ? null : null;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void onDestroy() {
        String filename;
        JDCacheFileRepoDelegate fileRepo;
        InputStream fileStream;
        super.onDestroy();
        Channel<JDCacheLocalResp> channel = this.waitingChannel;
        if (channel != null) {
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) channel, (CancellationException) null, 1, (Object) null);
        }
        ICancellable iCancellable = this.downloadTask;
        if (iCancellable != null) {
            ICancellable.DefaultImpls.cancel$default(iCancellable, null, 1, null);
        }
        JDCacheLocalResp jDCacheLocalResp = this.localResp;
        if (jDCacheLocalResp != null && (fileStream = jDCacheLocalResp.getFileStream()) != null) {
            fileStream.close();
        }
        JDCacheLocalResp jDCacheLocalResp2 = this.localResp;
        if (jDCacheLocalResp2 == null || (filename = jDCacheLocalResp2.getFilename()) == null || (fileRepo = getFileRepo()) == null) {
            return;
        }
        fileRepo.deleteFile(filename);
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void prepare(@NotNull String str) {
        boolean startsWith$default;
        JDCacheLoader loader = getLoader();
        if (loader == null || !loader.getPreloadHtml()) {
            return;
        }
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "http", false, 2, null);
        if (startsWith$default) {
            downloadHtmlStream$default(this, str, null, 2, null);
            return;
        }
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog()) {
            jDCacheLog.d(getName(), "Will NOT perform preload HTML for non-HTTP url.");
        }
    }

    public void saveCookieFromRespHeaders(@NotNull String url, @Nullable Map<String, ? extends List<String>> headers) {
        List<String> list;
        JDCacheParamsProvider paramsProvider;
        if (headers == null || (list = headers.get(HttpHeaders.SET_COOKIE)) == null || (paramsProvider = JDCacheSetting.INSTANCE.getParamsProvider()) == null) {
            return;
        }
        paramsProvider.saveCookie(url, list);
    }

    public final void setDownloadTask(@Nullable ICancellable iCancellable) {
        this.downloadTask = iCancellable;
    }

    public final void setDownloadUrl(@Nullable String str) {
        this.downloadUrl = str;
    }

    public final void setLocalResp(@Nullable JDCacheLocalResp jDCacheLocalResp) {
        this.localResp = jDCacheLocalResp;
    }

    public final void setWaitingChannel(@Nullable Channel<JDCacheLocalResp> channel) {
        this.waitingChannel = channel;
    }
}
