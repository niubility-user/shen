package com.jd.jdcache.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u0004\u0018\u00010\u0001H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006"}, d2 = {"Ljava/io/File;", "", "getString", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateFileName", "(Ljava/lang/String;)Ljava/lang/String;", "JDCache_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class FileHelperKt {
    @NotNull
    public static final String generateFileName(@Nullable String str) {
        String fileNameFromUrl = str != null ? UrlHelper.INSTANCE.getFileNameFromUrl(str) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append(Random.INSTANCE.nextInt(900) + 100);
        String sb2 = sb.toString();
        if (fileNameFromUrl == null || fileNameFromUrl.length() == 0) {
            return sb2;
        }
        return fileNameFromUrl + '_' + sb2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object getString(@Nullable File file, @NotNull Continuation<? super String> continuation) {
        FileHelperKt$getString$1 fileHelperKt$getString$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof FileHelperKt$getString$1) {
            fileHelperKt$getString$1 = (FileHelperKt$getString$1) continuation;
            int i3 = fileHelperKt$getString$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                fileHelperKt$getString$1.label = i3 - Integer.MIN_VALUE;
                FileHelperKt$getString$1 fileHelperKt$getString$12 = fileHelperKt$getString$1;
                Object obj = fileHelperKt$getString$12.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = fileHelperKt$getString$12.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (file == null || !file.exists() || !file.isFile()) {
                        return null;
                    }
                    CoroutineHelper coroutineHelper = CoroutineHelper.INSTANCE;
                    FileHelperKt$getString$2$1 fileHelperKt$getString$2$1 = new FileHelperKt$getString$2$1(file, null);
                    fileHelperKt$getString$12.L$0 = file;
                    fileHelperKt$getString$12.L$1 = file;
                    fileHelperKt$getString$12.label = 1;
                    obj = CoroutineHelper.runOnIo$default(coroutineHelper, file, null, fileHelperKt$getString$2$1, fileHelperKt$getString$12, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    File file2 = (File) fileHelperKt$getString$12.L$1;
                    File file3 = (File) fileHelperKt$getString$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return (String) obj;
            }
        }
        fileHelperKt$getString$1 = new FileHelperKt$getString$1(continuation);
        FileHelperKt$getString$1 fileHelperKt$getString$122 = fileHelperKt$getString$1;
        Object obj2 = fileHelperKt$getString$122.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = fileHelperKt$getString$122.label;
        if (i2 != 0) {
        }
        return (String) obj2;
    }
}
