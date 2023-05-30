package kotlin.coroutines.experimental;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import jpbury.t;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\u000e\u001a\u00020\u000b8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lkotlin/coroutines/experimental/Continuation;", "T", "", "value", "", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;)V", "", t.f20145j, "resumeWithException", "(Ljava/lang/Throwable;)V", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", AnnoConst.Constructor_Context, "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface Continuation<T> {
    @NotNull
    CoroutineContext getContext();

    void resume(T value);

    void resumeWithException(@NotNull Throwable exception);
}
