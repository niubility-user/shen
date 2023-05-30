package com.jd.jdcache.match.impl;

import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.service.base.FileState;
import com.jd.jdcache.util.UrlHelper;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lkotlin/Pair;", "", "Lcom/jd/jdcache/service/base/FileState$Complete;", "<name for destructuring parameter 0>", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.match.impl.PreloadHtmlMatcher$downloadHtmlFile$flow$2", f = "PreloadHtmlMatcher.kt", i = {0, 0, 0, 0}, l = {144}, m = "invokeSuspend", n = {"$dstr$end$fileState", "end", "fileState", "localResp"}, s = {"L$0", "Z$0", "L$1", "L$2"})
/* loaded from: classes13.dex */
public final class PreloadHtmlMatcher$downloadHtmlFile$flow$2 extends SuspendLambda implements Function2<Pair<? extends Boolean, ? extends FileState.Complete>, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    private Pair p$0;
    final /* synthetic */ PreloadHtmlMatcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreloadHtmlMatcher$downloadHtmlFile$flow$2(PreloadHtmlMatcher preloadHtmlMatcher, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = preloadHtmlMatcher;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        PreloadHtmlMatcher$downloadHtmlFile$flow$2 preloadHtmlMatcher$downloadHtmlFile$flow$2 = new PreloadHtmlMatcher$downloadHtmlFile$flow$2(this.this$0, this.$url, continuation);
        preloadHtmlMatcher$downloadHtmlFile$flow$2.p$0 = (Pair) obj;
        return preloadHtmlMatcher$downloadHtmlFile$flow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Pair<? extends Boolean, ? extends FileState.Complete> pair, Continuation<? super Unit> continuation) {
        return ((PreloadHtmlMatcher$downloadHtmlFile$flow$2) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Map<String, String> convertHeader;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Pair pair = this.p$0;
            boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
            FileState.Complete complete = (FileState.Complete) pair.component2();
            Map<String, String> map = null;
            if (booleanValue) {
                this.this$0.setDownloadTask(null);
            }
            if (complete instanceof FileState.Complete) {
                this.this$0.saveCookieFromRespHeaders(this.$url, complete.getHeaders());
                JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(this.$url, "html", null, null, null, false, 60, null);
                jDCacheLocalResp.setFilename(complete.getData().getAbsolutePath());
                Map<String, List<String>> headers = complete.getHeaders();
                if (headers != null && (convertHeader = UrlHelper.INSTANCE.convertHeader(headers)) != null) {
                    map = MapsKt__MapsKt.toMutableMap(convertHeader);
                }
                jDCacheLocalResp.header = map;
                Channel<JDCacheLocalResp> waitingChannel = this.this$0.getWaitingChannel();
                if (waitingChannel != null) {
                    this.L$0 = pair;
                    this.Z$0 = booleanValue;
                    this.L$1 = complete;
                    this.L$2 = jDCacheLocalResp;
                    this.label = 1;
                    if (waitingChannel.send(jDCacheLocalResp, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            JDCacheLocalResp jDCacheLocalResp2 = (JDCacheLocalResp) this.L$2;
            FileState.Complete complete2 = (FileState.Complete) this.L$1;
            Pair pair2 = (Pair) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
