package com.jd.libs.hybrid.offlineload.jdcache;

import com.jd.jdcache.service.base.FileState;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006\u00b8\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<FileState> {
    final /* synthetic */ PreloadMatcher$downloadHtmlFile$job$1 this$0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@\u00a8\u0006\u0006"}, d2 = {"T", "value", "LLkotlin/coroutines/Continuation;;", "L;", "continuation", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "emit"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1", f = "PreloadMatcher.kt", i = {0, 0, 0, 0, 0}, l = {R2.anim.pop_left_top_out}, m = "emit", n = {"this", "value", "continuation", XView2Constants.STATE, "localResp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1$1 */
    /* loaded from: classes16.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Continuation continuation) {
            super(continuation);
            PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1.this = r1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    public PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1(PreloadMatcher$downloadHtmlFile$job$1 preloadMatcher$downloadHtmlFile$job$1) {
        this.this$0 = preloadMatcher$downloadHtmlFile$job$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x004c  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object emit(com.jd.jdcache.service.base.FileState r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r22) {
        /*
            Method dump skipped, instructions count: 785
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
