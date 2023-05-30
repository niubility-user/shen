package com.jd.libs.hybrid.requestpreload.network;

import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.requestpreload.network.RequestHelper$doRequestAsync$2", f = "RequestHelper.kt", i = {0}, l = {55}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
/* loaded from: classes16.dex */
public final class RequestHelper$doRequestAsync$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CacheItem>, Object> {
    final /* synthetic */ String $method;
    final /* synthetic */ HashMap $paramsMap;
    final /* synthetic */ String $url;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestHelper$doRequestAsync$2(String str, String str2, HashMap hashMap, Continuation continuation) {
        super(2, continuation);
        this.$url = str;
        this.$method = str2;
        this.$paramsMap = hashMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        RequestHelper$doRequestAsync$2 requestHelper$doRequestAsync$2 = new RequestHelper$doRequestAsync$2(this.$url, this.$method, this.$paramsMap, continuation);
        requestHelper$doRequestAsync$2.p$ = (CoroutineScope) obj;
        return requestHelper$doRequestAsync$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CacheItem> continuation) {
        return ((RequestHelper$doRequestAsync$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RequestHelper requestHelper = RequestHelper.INSTANCE;
            String str = this.$url;
            String str2 = this.$method;
            HashMap<String, String> hashMap = this.$paramsMap;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = requestHelper.a(str, str2, hashMap, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
