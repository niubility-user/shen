package com.jd.libs.hybrid.requestpreload.coroutine;

import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$run$1", f = "RequestWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
public final class RequestWorker$run$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ String $timestamp;
    final /* synthetic */ HashMap $urlQuery;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RequestWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestWorker$run$1(RequestWorker requestWorker, String str, String str2, HashMap hashMap, Continuation continuation) {
        super(2, continuation);
        this.this$0 = requestWorker;
        this.$appId = str;
        this.$timestamp = str2;
        this.$urlQuery = hashMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        RequestWorker$run$1 requestWorker$run$1 = new RequestWorker$run$1(this.this$0, this.$appId, this.$timestamp, this.$urlQuery, continuation);
        requestWorker$run$1.p$ = (CoroutineScope) obj;
        return requestWorker$run$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RequestWorker$run$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ArrayList arrayList;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.$appId;
            if (str != null) {
                this.this$0.appId = str;
                String str2 = this.$timestamp;
                if (str2 != null) {
                    this.this$0.timestamp = str2;
                }
                HashMap hashMap = this.$urlQuery;
                if (hashMap != null) {
                    this.this$0.urlQuery = hashMap;
                }
                this.this$0.requestItems = RequestPreloadSDK.INSTANCE.getInstance().getPolicyManager().getRequestItems(this.$appId);
                arrayList = this.this$0.requestItems;
                if (!arrayList.isEmpty()) {
                    this.this$0.b();
                    this.this$0.f();
                } else {
                    CommonUtil.INSTANCE.logX("[RequestWorker] " + this.$appId + " \u65e0\u6709\u6548\u7684\u8bf7\u6c42\u9879");
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
