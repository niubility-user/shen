package com.jd.jdcache.match.impl;

import com.jingdong.common.XView2.common.XView2Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

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
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.match.impl.PreloadHtmlMatcher$downloadHtmlStream$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
