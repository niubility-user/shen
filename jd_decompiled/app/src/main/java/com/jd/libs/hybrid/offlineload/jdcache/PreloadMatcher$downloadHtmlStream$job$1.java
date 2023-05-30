package com.jd.libs.hybrid.offlineload.jdcache;

import com.jd.jdcache.service.base.FileRequestOption;
import com.jd.jdcache.service.base.InputStreamState;
import com.jd.jdcache.service.base.JDCacheFileRepoDelegate;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlStream$job$1", f = "PreloadMatcher.kt", i = {0, 0}, l = {R2.attr.animateCircleAngleTo}, m = "invokeSuspend", n = {"saveOption", "$this$collect$iv"}, s = {"L$0", "L$1"})
/* loaded from: classes16.dex */
public final class PreloadMatcher$downloadHtmlStream$job$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ String $cookie;
    final /* synthetic */ Map $header;
    final /* synthetic */ String $url;
    final /* synthetic */ String $userAgent;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PreloadMatcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreloadMatcher$downloadHtmlStream$job$1(PreloadMatcher preloadMatcher, Map map, String str, String str2, String str3, Continuation continuation) {
        super(1, continuation);
        this.this$0 = preloadMatcher;
        this.$header = map;
        this.$userAgent = str;
        this.$cookie = str2;
        this.$url = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new PreloadMatcher$downloadHtmlStream$job$1(this.this$0, this.$header, this.$userAgent, this.$cookie, this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((PreloadMatcher$downloadHtmlStream$job$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        JDCacheFileRepoDelegate fileRepo;
        Flow<InputStreamState> inputStreamFromNetFlow;
        Flow cancellable;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                FileRequestOption fileRequestOption = new FileRequestOption(null, this.$header, this.$userAgent, this.$cookie, 1, null);
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.d(this.this$0.getName(), "Starting pre-download html(" + this.$url + ')');
                }
                fileRepo = this.this$0.getFileRepo();
                if (fileRepo != null && (inputStreamFromNetFlow = fileRepo.getInputStreamFromNetFlow(this.$url, fileRequestOption)) != null && (cancellable = FlowKt.cancellable(inputStreamFromNetFlow)) != null) {
                    PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1 preloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1 = new PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1(this);
                    this.L$0 = fileRequestOption;
                    this.L$1 = cancellable;
                    this.label = 1;
                    if (cancellable.collect(preloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                Flow flow = (Flow) this.L$1;
                FileRequestOption fileRequestOption2 = (FileRequestOption) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
            if (jDCacheLog2.getCanLog()) {
                jDCacheLog2.e(this.this$0.getName(), e2);
            }
            OfflineFiles offlineFiles = this.this$0.getOfflineFiles();
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "PreloadMatcher#stream#launchCoroutine", offlineFiles != null ? offlineFiles.getAppId() : null, this.$url, e2);
        }
        return Unit.INSTANCE;
    }
}
