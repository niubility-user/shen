package kotlinx.coroutines.channels;

import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ChannelIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\"\u0006\b\u0000\u0010\u0000 \u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0097@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"E", "Lkotlin/coroutines/Continuation;", "continuation", "", "next0", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelIterator$DefaultImpls", f = "Channel.kt", i = {0}, l = {R2.attr.blurOverlayColor}, m = "next", n = {"$this"}, s = {"L$0"})
/* loaded from: classes11.dex */
public final class ChannelIterator$next0$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ChannelIterator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelIterator$next0$1(ChannelIterator channelIterator, Continuation continuation) {
        super(continuation);
        this.this$0 = channelIterator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelIterator.DefaultImpls.next(null, this);
    }
}
