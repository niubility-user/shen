package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jd.jdcache.service.DelegateManager;
import com.jd.jdcache.util.JDCacheLog;
import java.io.File;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__CollectionKt;
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
    */
    public static /* synthetic */ Object getInputStreamFromNet$suspendImpl(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, FileRequestOption fileRequestOption, Continuation continuation) {
        JDCacheFileRepoDelegate$getInputStreamFromNet$1 jDCacheFileRepoDelegate$getInputStreamFromNet$1;
        Object coroutine_suspended;
        int i2;
        List list;
        try {
            if (continuation instanceof JDCacheFileRepoDelegate$getInputStreamFromNet$1) {
                jDCacheFileRepoDelegate$getInputStreamFromNet$1 = (JDCacheFileRepoDelegate$getInputStreamFromNet$1) continuation;
                int i3 = jDCacheFileRepoDelegate$getInputStreamFromNet$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    jDCacheFileRepoDelegate$getInputStreamFromNet$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = jDCacheFileRepoDelegate$getInputStreamFromNet$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = jDCacheFileRepoDelegate$getInputStreamFromNet$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow<InputStreamState> inputStreamFromNetFlow = jDCacheFileRepoDelegate.getInputStreamFromNetFlow(str, fileRequestOption);
                        if (inputStreamFromNetFlow == null) {
                            return null;
                        }
                        jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$0 = jDCacheFileRepoDelegate;
                        jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$1 = str;
                        jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$2 = fileRequestOption;
                        jDCacheFileRepoDelegate$getInputStreamFromNet$1.label = 1;
                        obj = FlowKt__CollectionKt.toList$default(inputStreamFromNetFlow, null, jDCacheFileRepoDelegate$getInputStreamFromNet$1, 1, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        FileRequestOption fileRequestOption2 = (FileRequestOption) jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$2;
                        String str2 = (String) jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$1;
                        jDCacheFileRepoDelegate = (JDCacheFileRepoDelegate) jDCacheFileRepoDelegate$getInputStreamFromNet$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    list = (List) obj;
                    if (list == null) {
                        return (InputStreamState) CollectionsKt.last(list);
                    }
                    return null;
                }
            }
            if (i2 != 0) {
            }
            list = (List) obj;
            if (list == null) {
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(jDCacheFileRepoDelegate.getName(), e2);
                return null;
            }
            return null;
        }
        jDCacheFileRepoDelegate$getInputStreamFromNet$1 = new JDCacheFileRepoDelegate$getInputStreamFromNet$1(jDCacheFileRepoDelegate, continuation);
        Object obj2 = jDCacheFileRepoDelegate$getInputStreamFromNet$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jDCacheFileRepoDelegate$getInputStreamFromNet$1.label;
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
    */
    public static /* synthetic */ Object saveFileFromNet$suspendImpl(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, String str, String str2, FileSaveOption fileSaveOption, Continuation continuation) {
        JDCacheFileRepoDelegate$saveFileFromNet$1 jDCacheFileRepoDelegate$saveFileFromNet$1;
        Object coroutine_suspended;
        int i2;
        List list;
        try {
            if (continuation instanceof JDCacheFileRepoDelegate$saveFileFromNet$1) {
                jDCacheFileRepoDelegate$saveFileFromNet$1 = (JDCacheFileRepoDelegate$saveFileFromNet$1) continuation;
                int i3 = jDCacheFileRepoDelegate$saveFileFromNet$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    jDCacheFileRepoDelegate$saveFileFromNet$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = jDCacheFileRepoDelegate$saveFileFromNet$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = jDCacheFileRepoDelegate$saveFileFromNet$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow<FileState> saveFileFromNetFlow = jDCacheFileRepoDelegate.saveFileFromNetFlow(str, str2, fileSaveOption);
                        if (saveFileFromNetFlow == null) {
                            return null;
                        }
                        jDCacheFileRepoDelegate$saveFileFromNet$1.L$0 = jDCacheFileRepoDelegate;
                        jDCacheFileRepoDelegate$saveFileFromNet$1.L$1 = str;
                        jDCacheFileRepoDelegate$saveFileFromNet$1.L$2 = str2;
                        jDCacheFileRepoDelegate$saveFileFromNet$1.L$3 = fileSaveOption;
                        jDCacheFileRepoDelegate$saveFileFromNet$1.label = 1;
                        obj = FlowKt__CollectionKt.toList$default(saveFileFromNetFlow, null, jDCacheFileRepoDelegate$saveFileFromNet$1, 1, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        FileSaveOption fileSaveOption2 = (FileSaveOption) jDCacheFileRepoDelegate$saveFileFromNet$1.L$3;
                        String str3 = (String) jDCacheFileRepoDelegate$saveFileFromNet$1.L$2;
                        String str4 = (String) jDCacheFileRepoDelegate$saveFileFromNet$1.L$1;
                        jDCacheFileRepoDelegate = (JDCacheFileRepoDelegate) jDCacheFileRepoDelegate$saveFileFromNet$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    list = (List) obj;
                    if (list == null) {
                        return (FileState) CollectionsKt.last(list);
                    }
                    return null;
                }
            }
            if (i2 != 0) {
            }
            list = (List) obj;
            if (list == null) {
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(jDCacheFileRepoDelegate.getName(), e2);
                return null;
            }
            return null;
        }
        jDCacheFileRepoDelegate$saveFileFromNet$1 = new JDCacheFileRepoDelegate$saveFileFromNet$1(jDCacheFileRepoDelegate, continuation);
        Object obj2 = jDCacheFileRepoDelegate$saveFileFromNet$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jDCacheFileRepoDelegate$saveFileFromNet$1.label;
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
