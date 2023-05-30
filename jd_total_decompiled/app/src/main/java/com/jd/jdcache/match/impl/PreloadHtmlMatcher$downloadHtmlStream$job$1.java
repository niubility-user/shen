package com.jd.jdcache.match.impl;

import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.JDCacheSetting;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.match.PreReadInputStream;
import com.jd.jdcache.service.base.FileRequestOption;
import com.jd.jdcache.service.base.InputStreamState;
import com.jd.jdcache.service.base.JDCacheFileRepoDelegate;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.common.XView2.common.XView2Constants;
import java.io.BufferedInputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.match.impl.PreloadHtmlMatcher$downloadHtmlStream$job$1", f = "PreloadHtmlMatcher.kt", i = {0, 1, 1, 1, 1}, l = {76, 89}, m = "invokeSuspend", n = {"saveOption", "saveOption", XView2Constants.STATE, "stream", "localResp"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes13.dex */
public final class PreloadHtmlMatcher$downloadHtmlStream$job$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Map $header;
    final /* synthetic */ String $url;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PreloadHtmlMatcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreloadHtmlMatcher$downloadHtmlStream$job$1(PreloadHtmlMatcher preloadHtmlMatcher, Map map, String str, Continuation continuation) {
        super(1, continuation);
        this.this$0 = preloadHtmlMatcher;
        this.$header = map;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new PreloadHtmlMatcher$downloadHtmlStream$job$1(this.this$0, this.$header, this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((PreloadHtmlMatcher$downloadHtmlStream$job$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b6  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FileRequestOption fileRequestOption;
        InputStreamState inputStreamState;
        Object inputStreamFromNet;
        AtomicBoolean destroyed;
        PreReadInputStream preReadInputStream;
        PreReadInputStream preReadInputStream2;
        Map<String, String> convertHeader;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        Map<String, String> map = null;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            String str = null;
            Map map2 = this.$header;
            JDCacheSetting jDCacheSetting = JDCacheSetting.INSTANCE;
            JDCacheParamsProvider paramsProvider = jDCacheSetting.getParamsProvider();
            String userAgent = paramsProvider != null ? paramsProvider.getUserAgent(this.$url) : null;
            JDCacheParamsProvider paramsProvider2 = jDCacheSetting.getParamsProvider();
            fileRequestOption = new FileRequestOption(str, map2, userAgent, paramsProvider2 != null ? paramsProvider2.getCookie(this.$url) : null, 1, null);
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.d(this.this$0.getName(), "Starting pre-download html(" + this.$url + ')');
            }
            JDCacheFileRepoDelegate fileRepo = this.this$0.getFileRepo();
            if (fileRepo == null) {
                inputStreamState = null;
                destroyed = this.this$0.getDestroyed();
                if (!destroyed.get()) {
                    return Unit.INSTANCE;
                }
                if (inputStreamState instanceof InputStreamState.Connected) {
                    JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                    if (jDCacheLog2.getCanLog()) {
                        jDCacheLog2.d(this.this$0.getName(), "The pre-downloading html can be read now(" + this.$url + ')');
                    }
                    InputStreamState.Connected connected = (InputStreamState.Connected) inputStreamState;
                    preReadInputStream = new PreReadInputStream(new BufferedInputStream(connected.getData()));
                    this.this$0.saveCookieFromRespHeaders(this.$url, connected.getHeaders());
                    JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(this.$url, "html", null, null, null, false, 60, null);
                    jDCacheLocalResp.setFileStream(preReadInputStream);
                    Map<String, List<String>> headers = connected.getHeaders();
                    if (headers != null && (convertHeader = UrlHelper.INSTANCE.convertHeader(headers)) != null) {
                        map = MapsKt__MapsKt.toMutableMap(convertHeader);
                    }
                    jDCacheLocalResp.header = map;
                    Channel<JDCacheLocalResp> waitingChannel = this.this$0.getWaitingChannel();
                    if (waitingChannel != null) {
                        this.L$0 = fileRequestOption;
                        this.L$1 = inputStreamState;
                        this.L$2 = preReadInputStream;
                        this.L$3 = jDCacheLocalResp;
                        this.label = 2;
                        if (waitingChannel.send(jDCacheLocalResp, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        preReadInputStream2 = preReadInputStream;
                        preReadInputStream = preReadInputStream2;
                    }
                    preReadInputStream.startPreRead();
                    return Unit.INSTANCE;
                }
                if (inputStreamState instanceof InputStreamState.Error) {
                    JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                    if (jDCacheLog3.getCanLog()) {
                        String name = this.this$0.getName();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Fail pre-downloading html, ");
                        sb.append("code=");
                        InputStreamState.Error error = (InputStreamState.Error) inputStreamState;
                        sb.append(error.getCode());
                        sb.append(", exception=");
                        sb.append(error.getThrowable());
                        jDCacheLog3.e(name, sb.toString());
                    }
                }
                return Unit.INSTANCE;
            }
            String str2 = this.$url;
            this.L$0 = fileRequestOption;
            this.label = 1;
            inputStreamFromNet = fileRepo.getInputStreamFromNet(str2, fileRequestOption, this);
            if (inputStreamFromNet == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            JDCacheLocalResp jDCacheLocalResp2 = (JDCacheLocalResp) this.L$3;
            preReadInputStream2 = (PreReadInputStream) this.L$2;
            InputStreamState inputStreamState2 = (InputStreamState) this.L$1;
            FileRequestOption fileRequestOption2 = (FileRequestOption) this.L$0;
            ResultKt.throwOnFailure(obj);
            preReadInputStream = preReadInputStream2;
            preReadInputStream.startPreRead();
            return Unit.INSTANCE;
        } else {
            fileRequestOption = (FileRequestOption) this.L$0;
            ResultKt.throwOnFailure(obj);
            inputStreamFromNet = obj;
        }
        inputStreamState = (InputStreamState) inputStreamFromNet;
        destroyed = this.this$0.getDestroyed();
        if (!destroyed.get()) {
        }
    }
}
