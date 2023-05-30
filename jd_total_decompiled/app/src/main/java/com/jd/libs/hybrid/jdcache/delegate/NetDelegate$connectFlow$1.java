package com.jd.libs.hybrid.jdcache.delegate;

import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.xwin.http.StreamRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/jd/jdcache/service/base/NetState;", "Ljava/io/InputStream;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.jdcache.delegate.NetDelegate$connectFlow$1", f = "NetDelegate.kt", i = {0}, l = {84}, m = "invokeSuspend", n = {"$this$callbackFlow"}, s = {"L$0"})
/* loaded from: classes16.dex */
public final class NetDelegate$connectFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super NetState<InputStream>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map $header;
    final /* synthetic */ String $url;
    Object L$0;
    int label;
    private ProducerScope p$;
    final /* synthetic */ NetDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetDelegate$connectFlow$1(NetDelegate netDelegate, String str, Map map, Continuation continuation) {
        super(2, continuation);
        this.this$0 = netDelegate;
        this.$url = str;
        this.$header = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        NetDelegate$connectFlow$1 netDelegate$connectFlow$1 = new NetDelegate$connectFlow$1(this.this$0, this.$url, this.$header, continuation);
        netDelegate$connectFlow$1.p$ = (ProducerScope) obj;
        return netDelegate$connectFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super NetState<InputStream>> producerScope, Continuation<? super Unit> continuation) {
        return ((NetDelegate$connectFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = this.p$;
            HybridBase.getInstance().downloadStream(this.$url, null, (HashMap) this.$header, true, true, new StreamRequest.StreamListener() { // from class: com.jd.libs.hybrid.jdcache.delegate.NetDelegate$connectFlow$1.1
                {
                    NetDelegate$connectFlow$1.this = this;
                }

                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onConnect(int code, @Nullable Map<String, ? extends List<String>> responseHeaders, @Nullable InputStream inputStream) {
                    try {
                        if (200 == code) {
                            ChannelsKt.sendBlocking(producerScope, new NetState.Complete(code, responseHeaders, -1L, inputStream));
                        } else {
                            ChannelsKt.sendBlocking(producerScope, new NetState.Error(code, new Exception("Net error, code = " + code + ", msg = code is not 200")));
                        }
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$connectFlow$1.this.this$0.getName(), th);
                        }
                    }
                    SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
                }

                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onError(int code, @Nullable Map<String, List<String>> responseHeaders, @Nullable String msg) {
                    try {
                        ChannelsKt.sendBlocking(producerScope, new NetState.Error(code, new Exception("Net error, code = " + code + ", msg = " + msg)));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$connectFlow$1.this.this$0.getName(), th);
                        }
                    }
                    SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
                }

                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onStart() {
                    try {
                        ChannelsKt.sendBlocking(producerScope, new NetState.OnStart(NetDelegate$connectFlow$1.this.$url));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$connectFlow$1.this.this$0.getName(), th);
                        }
                    }
                }
            });
            this.L$0 = producerScope;
            this.label = 1;
            if (ProduceKt.awaitClose$default(producerScope, null, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ProducerScope producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
