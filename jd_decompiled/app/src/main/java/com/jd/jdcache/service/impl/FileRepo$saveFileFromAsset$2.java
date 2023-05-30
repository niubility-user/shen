package com.jd.jdcache.service.impl;

import android.content.Context;
import com.jd.jdcache.service.base.FileState;
import com.jd.jdcache.util.JDCacheLog;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/jdcache/service/base/FileState;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.impl.FileRepo$saveFileFromAsset$2", f = "FileRepo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class FileRepo$saveFileFromAsset$2 extends SuspendLambda implements Function1<Continuation<? super FileState>, Object> {
    final /* synthetic */ String $assetFilePath;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $relativeFilePath;
    int label;
    final /* synthetic */ FileRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileRepo$saveFileFromAsset$2(FileRepo fileRepo, Context context, String str, String str2, Continuation continuation) {
        super(1, continuation);
        this.this$0 = fileRepo;
        this.$context = context;
        this.$assetFilePath = str;
        this.$relativeFilePath = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new FileRepo$saveFileFromAsset$2(this.this$0, this.$context, this.$assetFilePath, this.$relativeFilePath, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super FileState> continuation) {
        return ((FileRepo$saveFileFromAsset$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String concretePath;
        FileState copyFileFromAsset;
        String concretePath2;
        FileState copyFileFromAsset2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String[] list = this.$context.getAssets().list(this.$assetFilePath);
            boolean z = true;
            if (list != null) {
                if (!(list.length == 0)) {
                    z = false;
                }
            }
            if (!z) {
                concretePath = this.this$0.concretePath(this.$relativeFilePath);
                int i2 = 0;
                for (String str : list) {
                    FileRepo fileRepo = this.this$0;
                    Context context = this.$context;
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.$assetFilePath);
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append(str);
                    copyFileFromAsset = fileRepo.copyFileFromAsset(context, sb.toString(), concretePath + str2 + str);
                    if (copyFileFromAsset instanceof FileState.Error) {
                        i2++;
                    }
                }
                if (i2 == list.length) {
                    return new FileState.Error(-1, new RuntimeException("Fail to copy files from directory."));
                }
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog() && i2 > 0) {
                    jDCacheLog.e(this.this$0.getName(), "Partially succeed to save file(s) from asset, " + i2 + " file(s) fails");
                }
                return new FileState.Complete(0, 0L, null, new File(concretePath));
            }
            FileRepo fileRepo2 = this.this$0;
            Context context2 = this.$context;
            String str3 = this.$assetFilePath;
            concretePath2 = fileRepo2.concretePath(this.$relativeFilePath);
            copyFileFromAsset2 = fileRepo2.copyFileFromAsset(context2, str3, concretePath2);
            return copyFileFromAsset2;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
