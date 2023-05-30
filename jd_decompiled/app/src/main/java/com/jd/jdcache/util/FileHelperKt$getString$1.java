package com.jd.jdcache.util;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u00002\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0086@\u00a2\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Ljava/io/File;", "Lkotlin/coroutines/Continuation;", "", "continuation", "", "getString", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.util.FileHelperKt", f = "FileHelper.kt", i = {0, 0}, l = {16}, m = "getString", n = {"$this$getString", AdvanceSetting.NETWORK_TYPE}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
public final class FileHelperKt$getString$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    public FileHelperKt$getString$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FileHelperKt.getString(null, this);
    }
}
