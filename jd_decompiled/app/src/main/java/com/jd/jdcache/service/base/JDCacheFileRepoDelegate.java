package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jd.jdcache.service.DelegateManager;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b#\u0010$J+\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ3\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\rH&\u00a2\u0006\u0004\b\u000f\u0010\u0010J1\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J1\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u001c\u0010\u0017R\u001f\u0010\"\u001a\u0004\u0018\u00010\u001d8D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/jd/jdcache/service/base/JDCacheFileRepoDelegate;", "Lcom/jd/jdcache/service/base/AbstractDelegate;", "", "url", "Lcom/jd/jdcache/service/base/FileRequestOption;", "option", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jd/jdcache/service/base/InputStreamState;", "getInputStreamFromNetFlow", "(Ljava/lang/String;Lcom/jd/jdcache/service/base/FileRequestOption;)Lkotlinx/coroutines/flow/Flow;", "getInputStreamFromNet", "(Ljava/lang/String;Lcom/jd/jdcache/service/base/FileRequestOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "relativeFilePath", "Lcom/jd/jdcache/service/base/FileSaveOption;", "Lcom/jd/jdcache/service/base/FileState;", "saveFileFromNetFlow", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/jdcache/service/base/FileSaveOption;)Lkotlinx/coroutines/flow/Flow;", "saveFileFromNet", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/jdcache/service/base/FileSaveOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assetFilePath", "saveFileFromAsset", "", "deleteRelativeFile", "(Ljava/lang/String;)Z", "Ljava/io/File;", "getRelativeFile", "(Ljava/lang/String;)Ljava/io/File;", "absoluteFilePath", "deleteFile", "Lcom/jd/jdcache/service/base/JDCacheNetDelegate;", "netDelegate$delegate", "Lkotlin/Lazy;", "getNetDelegate", "()Lcom/jd/jdcache/service/base/JDCacheNetDelegate;", "netDelegate", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class JDCacheFileRepoDelegate extends AbstractDelegate {
    @Nullable

    /* renamed from: netDelegate$delegate  reason: from kotlin metadata */
    private final Lazy netDelegate;

    public JDCacheFileRepoDelegate() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDCacheNetDelegate>() { // from class: com.jd.jdcache.service.base.JDCacheFileRepoDelegate$netDelegate$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @Nullable
            public final JDCacheNetDelegate invoke() {
                return (JDCacheNetDelegate) DelegateManager.INSTANCE.getDelegate(JDCacheNetDelegate.class);
            }
        });
        this.netDelegate = lazy;
    }

    public static /* synthetic */ Object getInputStreamFromNet$default(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, FileRequestOption fileRequestOption, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                fileRequestOption = null;
            }
            return jDCacheFileRepoDelegate.getInputStreamFromNet(str, fileRequestOption, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInputStreamFromNet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005a A[Catch: Exception -> 0x0062, TRY_LEAVE, TryCatch #0 {Exception -> 0x0062, blocks: (B:12:0x0032, B:22:0x0056, B:24:0x005a, B:17:0x0041, B:19:0x0047), top: B:31:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object getInputStreamFromNet$suspendImpl(com.jd.jdcache.service.base.JDCacheFileRepoDelegate r5, java.lang.String r6, com.jd.jdcache.service.base.FileRequestOption r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof com.jd.jdcache.service.base.JDCacheFileRepoDelegate$getInputStreamFromNet$1
            if (r0 == 0) goto L13
            r0 = r8
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate$getInputStreamFromNet$1 r0 = (com.jd.jdcache.service.base.JDCacheFileRepoDelegate$getInputStreamFromNet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate$getInputStreamFromNet$1 r0 = new com.jd.jdcache.service.base.JDCacheFileRepoDelegate$getInputStreamFromNet$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r5 = r0.L$2
            com.jd.jdcache.service.base.FileRequestOption r5 = (com.jd.jdcache.service.base.FileRequestOption) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate r5 = (com.jd.jdcache.service.base.JDCacheFileRepoDelegate) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> L62
            goto L56
        L36:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.flow.Flow r8 = r5.getInputStreamFromNetFlow(r6, r7)     // Catch: java.lang.Exception -> L62
            if (r8 == 0) goto L72
            r0.L$0 = r5     // Catch: java.lang.Exception -> L62
            r0.L$1 = r6     // Catch: java.lang.Exception -> L62
            r0.L$2 = r7     // Catch: java.lang.Exception -> L62
            r0.label = r3     // Catch: java.lang.Exception -> L62
            java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.toList$default(r8, r4, r0, r3, r4)     // Catch: java.lang.Exception -> L62
            if (r8 != r1) goto L56
            return r1
        L56:
            java.util.List r8 = (java.util.List) r8     // Catch: java.lang.Exception -> L62
            if (r8 == 0) goto L72
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r8)     // Catch: java.lang.Exception -> L62
            com.jd.jdcache.service.base.InputStreamState r6 = (com.jd.jdcache.service.base.InputStreamState) r6     // Catch: java.lang.Exception -> L62
            r4 = r6
            goto L72
        L62:
            r6 = move-exception
            com.jd.jdcache.util.JDCacheLog r7 = com.jd.jdcache.util.JDCacheLog.INSTANCE
            boolean r8 = r7.getCanLog()
            if (r8 == 0) goto L72
            java.lang.String r5 = r5.getName()
            r7.e(r5, r6)
        L72:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.base.JDCacheFileRepoDelegate.getInputStreamFromNet$suspendImpl(com.jd.jdcache.service.base.JDCacheFileRepoDelegate, java.lang.String, com.jd.jdcache.service.base.FileRequestOption, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Flow getInputStreamFromNetFlow$default(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, FileRequestOption fileRequestOption, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                fileRequestOption = null;
            }
            return jDCacheFileRepoDelegate.getInputStreamFromNetFlow(str, fileRequestOption);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInputStreamFromNetFlow");
    }

    public static /* synthetic */ Object saveFileFromAsset$default(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, String str2, FileSaveOption fileSaveOption, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                fileSaveOption = null;
            }
            return jDCacheFileRepoDelegate.saveFileFromAsset(str, str2, fileSaveOption, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: saveFileFromAsset");
    }

    public static /* synthetic */ Object saveFileFromNet$default(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, String str2, FileSaveOption fileSaveOption, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                fileSaveOption = null;
            }
            return jDCacheFileRepoDelegate.saveFileFromNet(str, str2, fileSaveOption, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: saveFileFromNet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060 A[Catch: Exception -> 0x0068, TRY_LEAVE, TryCatch #0 {Exception -> 0x0068, blocks: (B:12:0x0036, B:22:0x005c, B:24:0x0060, B:17:0x0045, B:19:0x004b), top: B:31:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object saveFileFromNet$suspendImpl(com.jd.jdcache.service.base.JDCacheFileRepoDelegate r5, java.lang.String r6, java.lang.String r7, com.jd.jdcache.service.base.FileSaveOption r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof com.jd.jdcache.service.base.JDCacheFileRepoDelegate$saveFileFromNet$1
            if (r0 == 0) goto L13
            r0 = r9
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate$saveFileFromNet$1 r0 = (com.jd.jdcache.service.base.JDCacheFileRepoDelegate$saveFileFromNet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate$saveFileFromNet$1 r0 = new com.jd.jdcache.service.base.JDCacheFileRepoDelegate$saveFileFromNet$1
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r5 = r0.L$3
            com.jd.jdcache.service.base.FileSaveOption r5 = (com.jd.jdcache.service.base.FileSaveOption) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.jd.jdcache.service.base.JDCacheFileRepoDelegate r5 = (com.jd.jdcache.service.base.JDCacheFileRepoDelegate) r5
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L68
            goto L5c
        L3a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.flow.Flow r9 = r5.saveFileFromNetFlow(r6, r7, r8)     // Catch: java.lang.Exception -> L68
            if (r9 == 0) goto L78
            r0.L$0 = r5     // Catch: java.lang.Exception -> L68
            r0.L$1 = r6     // Catch: java.lang.Exception -> L68
            r0.L$2 = r7     // Catch: java.lang.Exception -> L68
            r0.L$3 = r8     // Catch: java.lang.Exception -> L68
            r0.label = r3     // Catch: java.lang.Exception -> L68
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.toList$default(r9, r4, r0, r3, r4)     // Catch: java.lang.Exception -> L68
            if (r9 != r1) goto L5c
            return r1
        L5c:
            java.util.List r9 = (java.util.List) r9     // Catch: java.lang.Exception -> L68
            if (r9 == 0) goto L78
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r9)     // Catch: java.lang.Exception -> L68
            com.jd.jdcache.service.base.FileState r6 = (com.jd.jdcache.service.base.FileState) r6     // Catch: java.lang.Exception -> L68
            r4 = r6
            goto L78
        L68:
            r6 = move-exception
            com.jd.jdcache.util.JDCacheLog r7 = com.jd.jdcache.util.JDCacheLog.INSTANCE
            boolean r8 = r7.getCanLog()
            if (r8 == 0) goto L78
            java.lang.String r5 = r5.getName()
            r7.e(r5, r6)
        L78:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.base.JDCacheFileRepoDelegate.saveFileFromNet$suspendImpl(com.jd.jdcache.service.base.JDCacheFileRepoDelegate, java.lang.String, java.lang.String, com.jd.jdcache.service.base.FileSaveOption, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Flow saveFileFromNetFlow$default(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, String str2, FileSaveOption fileSaveOption, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                fileSaveOption = null;
            }
            return jDCacheFileRepoDelegate.saveFileFromNetFlow(str, str2, fileSaveOption);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: saveFileFromNetFlow");
    }

    public abstract boolean deleteFile(@NotNull String absoluteFilePath);

    public abstract boolean deleteRelativeFile(@NotNull String relativeFilePath);

    @Nullable
    public Object getInputStreamFromNet(@NotNull String str, @Nullable FileRequestOption fileRequestOption, @NotNull Continuation<? super InputStreamState> continuation) {
        return getInputStreamFromNet$suspendImpl(this, str, fileRequestOption, continuation);
    }

    @Nullable
    public abstract Flow<InputStreamState> getInputStreamFromNetFlow(@NotNull String url, @Nullable FileRequestOption option);

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final JDCacheNetDelegate getNetDelegate() {
        return (JDCacheNetDelegate) this.netDelegate.getValue();
    }

    @NotNull
    public abstract File getRelativeFile(@NotNull String relativeFilePath);

    @Nullable
    public abstract Object saveFileFromAsset(@NotNull String str, @NotNull String str2, @Nullable FileSaveOption fileSaveOption, @NotNull Continuation<? super FileState> continuation);

    @Nullable
    public Object saveFileFromNet(@NotNull String str, @NotNull String str2, @Nullable FileSaveOption fileSaveOption, @NotNull Continuation<? super FileState> continuation) {
        return saveFileFromNet$suspendImpl(this, str, str2, fileSaveOption, continuation);
    }

    @Nullable
    public abstract Flow<FileState> saveFileFromNetFlow(@NotNull String url, @NotNull String relativeFilePath, @Nullable FileSaveOption option);
}
