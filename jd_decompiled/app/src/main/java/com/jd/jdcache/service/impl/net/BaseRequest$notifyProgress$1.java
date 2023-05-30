package com.jd.jdcache.service.impl.net;

import androidx.core.app.NotificationCompat;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0084@\u00a2\u0006\u0004\b\b\u0010\t"}, d2 = {"T", "", NotificationCompat.CATEGORY_PROGRESS, CartConstant.KEY_LENGTH, "Lkotlin/coroutines/Continuation;", "", "continuation", "", "notifyProgress", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.impl.net.BaseRequest", f = "BaseRequest.kt", i = {0, 0, 0}, l = {84}, m = "notifyProgress", n = {"this", NotificationCompat.CATEGORY_PROGRESS, CartConstant.KEY_LENGTH}, s = {"L$0", "J$0", "J$1"})
/* loaded from: classes13.dex */
public final class BaseRequest$notifyProgress$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRequest$notifyProgress$1(BaseRequest baseRequest, Continuation continuation) {
        super(continuation);
        this.this$0 = baseRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.notifyProgress(0L, 0L, this);
    }
}
