package com.jd.libs.hybrid.jdcache.delegate;

import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.xwin.http.a;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/jd/jdcache/service/base/NetState;", "Ljava/io/File;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.jdcache.delegate.NetDelegate$downloadFlow$3", f = "NetDelegate.kt", i = {0}, l = {R2.anim.pop_left_top_in}, m = "invokeSuspend", n = {"$this$callbackFlow"}, s = {"L$0"})
/* loaded from: classes16.dex */
public final class NetDelegate$downloadFlow$3 extends SuspendLambda implements Function2<ProducerScope<? super NetState<File>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $fileName;
    final /* synthetic */ Map $header;
    final /* synthetic */ String $relativeDirPath;
    final /* synthetic */ String $savePath;
    final /* synthetic */ String $url;
    Object L$0;
    int label;
    private ProducerScope p$;
    final /* synthetic */ NetDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetDelegate$downloadFlow$3(NetDelegate netDelegate, String str, Map map, String str2, String str3, String str4, Continuation continuation) {
        super(2, continuation);
        this.this$0 = netDelegate;
        this.$url = str;
        this.$header = map;
        this.$relativeDirPath = str2;
        this.$fileName = str3;
        this.$savePath = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        NetDelegate$downloadFlow$3 netDelegate$downloadFlow$3 = new NetDelegate$downloadFlow$3(this.this$0, this.$url, this.$header, this.$relativeDirPath, this.$fileName, this.$savePath, continuation);
        netDelegate$downloadFlow$3.p$ = (ProducerScope) obj;
        return netDelegate$downloadFlow$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super NetState<File>> producerScope, Continuation<? super Unit> continuation) {
        return ((NetDelegate$downloadFlow$3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            HybridBase.getInstance().downloadFile(this.$url, null, (HashMap) this.$header, true, true, this.$relativeDirPath, this.$fileName, new a.InterfaceC0173a() { // from class: com.jd.libs.hybrid.jdcache.delegate.NetDelegate$downloadFlow$3.1
                {
                    NetDelegate$downloadFlow$3.this = this;
                }

                @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                public void onError(int code, @Nullable Map<String, List<String>> responseHeaders, @Nullable String msg) {
                    try {
                        ChannelsKt.sendBlocking(producerScope, new NetState.Error(code, new Exception("Net error, code = " + code + ", msg = " + msg)));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$downloadFlow$3.this.this$0.getName(), th);
                        }
                    }
                    SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
                }

                @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                public void onProgress(int r7) {
                    try {
                        ChannelsKt.sendBlocking(producerScope, new NetState.OnProgress(r7, -1L));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$downloadFlow$3.this.this$0.getName(), th);
                        }
                    }
                }

                @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                public void onStart() {
                    try {
                        ChannelsKt.sendBlocking(producerScope, new NetState.OnStart(NetDelegate$downloadFlow$3.this.$url));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$downloadFlow$3.this.this$0.getName(), th);
                        }
                    }
                }

                @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                public void onSusses(int code, @Nullable Map<String, ? extends List<String>> responseHeaders, @Nullable String r10) {
                    try {
                        File file = new File(NetDelegate$downloadFlow$3.this.$savePath);
                        if (r10 != null) {
                            File file2 = new File(r10);
                            file2.renameTo(file);
                            file2.delete();
                        }
                        ChannelsKt.sendBlocking(producerScope, new NetState.Complete(code, responseHeaders, file.length(), file));
                    } catch (Throwable th) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(NetDelegate$downloadFlow$3.this.this$0.getName(), th);
                        }
                    }
                    SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
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
