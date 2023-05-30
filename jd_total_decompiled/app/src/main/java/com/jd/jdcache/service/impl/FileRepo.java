package com.jd.jdcache.service.impl;

import android.content.Context;
import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.JDCacheSetting;
import com.jd.jdcache.service.base.FileRequestOption;
import com.jd.jdcache.service.base.FileSaveOption;
import com.jd.jdcache.service.base.FileState;
import com.jd.jdcache.service.base.InputStreamState;
import com.jd.jdcache.service.base.JDCacheFileRepoDelegate;
import com.jd.jdcache.service.base.JDCacheNetDelegate;
import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.CoroutineHelper;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0017\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b,\u0010-J'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J1\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u001aH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ/\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u001aH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0010\u0010 R\u001d\u0010(\u001a\u00020\u00048D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001c\u0010)\u001a\u00020\u00048\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lcom/jd/jdcache/service/impl/FileRepo;", "Lcom/jd/jdcache/service/base/JDCacheFileRepoDelegate;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "assetFilePath", "destPath", "Lcom/jd/jdcache/service/base/FileState;", "copyFileFromAsset", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Lcom/jd/jdcache/service/base/FileState;", "relativePath", "concretePath", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/io/File;", "file", "", "deleteFile", "(Ljava/io/File;)Z", "url", "Lcom/jd/jdcache/service/base/FileRequestOption;", "option", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jd/jdcache/service/base/InputStreamState;", "getInputStreamFromNetFlow", "(Ljava/lang/String;Lcom/jd/jdcache/service/base/FileRequestOption;)Lkotlinx/coroutines/flow/Flow;", "relativeFilePath", "Lcom/jd/jdcache/service/base/FileSaveOption;", "saveFileFromNetFlow", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/jdcache/service/base/FileSaveOption;)Lkotlinx/coroutines/flow/Flow;", "saveFileFromAsset", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/jdcache/service/base/FileSaveOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRelativeFile", "(Ljava/lang/String;)Z", "getRelativeFile", "(Ljava/lang/String;)Ljava/io/File;", "absoluteFilePath", "rootDirPath$delegate", "Lkotlin/Lazy;", "getRootDirPath", "()Ljava/lang/String;", "rootDirPath", "name", "Ljava/lang/String;", "getName", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class FileRepo extends JDCacheFileRepoDelegate {
    @NotNull
    private final String name = "FileRepo";
    @NotNull

    /* renamed from: rootDirPath$delegate  reason: from kotlin metadata */
    private final Lazy rootDirPath;

    public FileRepo() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.jd.jdcache.service.impl.FileRepo$rootDirPath$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String invoke() {
                String cacheDir;
                JDCacheParamsProvider paramsProvider = JDCacheSetting.INSTANCE.getParamsProvider();
                if (paramsProvider == null || (cacheDir = paramsProvider.getCacheDir()) == null) {
                    throw new RuntimeException("Cache dir need to be set by JDCacheParamsProvider");
                }
                return cacheDir;
            }
        });
        this.rootDirPath = lazy;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0010  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String concretePath(String relativePath) {
        boolean z;
        CharSequence charSequence;
        boolean isBlank;
        if (relativePath != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(relativePath);
            if (!isBlank) {
                z = false;
                if (!z) {
                    return getRootDirPath();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(getRootDirPath());
                sb.append(File.separatorChar);
                int length = relativePath.length();
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        charSequence = "";
                        break;
                    }
                    if (!(relativePath.charAt(i2) == File.separatorChar)) {
                        charSequence = relativePath.subSequence(i2, relativePath.length());
                        break;
                    }
                    i2++;
                }
                sb.append(charSequence.toString());
                return sb.toString();
            }
        }
        z = true;
        if (!z) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.io.InputStream] */
    public final FileState copyFileFromAsset(Context r12, String assetFilePath, String destPath) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                r12 = r12.getAssets().open(assetFilePath);
                try {
                    byte[] bArr = new byte[2048];
                    Ref.IntRef intRef = new Ref.IntRef();
                    File file = new File(destPath);
                    File parentFile = file.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    long j2 = 0;
                    while (true) {
                        try {
                            int read = r12.read(bArr);
                            intRef.element = read;
                            if (read <= -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                            j2 += intRef.element;
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                            if (jDCacheLog.getCanLog()) {
                                jDCacheLog.e(getName(), e);
                            }
                            FileState.Error error = new FileState.Error(-1, e);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                                    if (jDCacheLog2.getCanLog()) {
                                        jDCacheLog2.e(getName(), e3);
                                    }
                                    return error;
                                }
                            }
                            if (r12 != 0) {
                                r12.close();
                            }
                            return error;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                                    if (jDCacheLog3.getCanLog()) {
                                        jDCacheLog3.e(getName(), e4);
                                    }
                                    throw th;
                                }
                            }
                            if (r12 != 0) {
                                r12.close();
                            }
                            throw th;
                        }
                    }
                    FileState.Complete complete = new FileState.Complete(0, j2, null, file);
                    try {
                        fileOutputStream2.close();
                        if (r12 != 0) {
                            r12.close();
                        }
                    } catch (IOException e5) {
                        JDCacheLog jDCacheLog4 = JDCacheLog.INSTANCE;
                        if (jDCacheLog4.getCanLog()) {
                            jDCacheLog4.e(getName(), e5);
                        }
                    }
                    return complete;
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Exception e7) {
                e = e7;
                r12 = 0;
            } catch (Throwable th2) {
                th = th2;
                r12 = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    static /* synthetic */ Object saveFileFromAsset$suspendImpl(FileRepo fileRepo, String str, String str2, FileSaveOption fileSaveOption, Continuation continuation) {
        if (str.length() == 0) {
            return new FileState.Error(-1, new IllegalArgumentException("Asset path is empty."));
        }
        if (str2.length() == 0) {
            return new FileState.Error(-1, new IllegalArgumentException("Destination path is empty."));
        }
        Context appContext = JDCacheSetting.INSTANCE.getAppContext();
        if (appContext != null) {
            return CoroutineHelper.runOnIo$default(CoroutineHelper.INSTANCE, fileRepo, null, new FileRepo$saveFileFromAsset$2(fileRepo, appContext, str, str2, null), continuation, 1, null);
        }
        return new FileState.Error(-1, new RuntimeException("Application context is null."));
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    public boolean deleteFile(@NotNull String absoluteFilePath) {
        if (absoluteFilePath.length() == 0) {
            return false;
        }
        return deleteFile(new File(absoluteFilePath));
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    public boolean deleteRelativeFile(@NotNull String relativeFilePath) {
        return deleteFile(new File(concretePath(relativeFilePath)));
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    @Nullable
    public Flow<InputStreamState> getInputStreamFromNetFlow(@NotNull String url, @Nullable FileRequestOption option) {
        String str;
        JDCacheNetDelegate netDelegate = getNetDelegate();
        if (netDelegate != null) {
            if (option == null || (str = option.getMethod()) == null) {
                str = "GET";
            }
            final Flow connectFlow$default = JDCacheNetDelegate.connectFlow$default(netDelegate, url, str, option != null ? option.getHeader() : null, option != null ? option.getUserAgent() : null, option != null ? option.getCookie() : null, null, false, 96, null);
            if (connectFlow$default != null) {
                return new Flow<InputStreamState>() { // from class: com.jd.jdcache.service.impl.FileRepo$getInputStreamFromNetFlow$$inlined$map$1
                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
                        Object coroutine_suspended;
                        Object collect = connectFlow$default.collect(new FlowCollector<NetState<InputStream>>() { // from class: com.jd.jdcache.service.impl.FileRepo$getInputStreamFromNetFlow$$inlined$map$1.2
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            @Nullable
                            public Object emit(NetState<InputStream> netState, @NotNull Continuation continuation2) {
                                InputStreamState error;
                                Object coroutine_suspended2;
                                FlowCollector flowCollector2 = flowCollector;
                                NetState<InputStream> netState2 = netState;
                                if (netState2 instanceof NetState.Complete) {
                                    NetState.Complete complete = (NetState.Complete) netState2;
                                    error = new InputStreamState.Connected(complete.getCode(), complete.getHeaders(), complete.getData() != null ? new BufferedInputStream((InputStream) complete.getData()) : null);
                                } else if (netState2 instanceof NetState.OnStart) {
                                    error = new InputStreamState.OnStart(((NetState.OnStart) netState2).getUrl());
                                } else if (netState2 instanceof NetState.Error) {
                                    NetState.Error error2 = (NetState.Error) netState2;
                                    error = new InputStreamState.Error(error2.getCode(), error2.getThrowable());
                                } else if (netState2 instanceof NetState.OnProgress) {
                                    error = new InputStreamState.Error(-1, new RuntimeException("This state[NetState.OnProgress] should not show up for InputStreamState"));
                                } else if (!(netState2 instanceof NetState.Redirect)) {
                                    throw new NoWhenBranchMatchedException();
                                } else {
                                    error = new InputStreamState.Error(((NetState.Redirect) netState2).getCode(), new RuntimeException("Connection redirects."));
                                }
                                Object emit = flowCollector2.emit(error, continuation2);
                                coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                return emit == coroutine_suspended2 ? emit : Unit.INSTANCE;
                            }
                        }, continuation);
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        return collect == coroutine_suspended ? collect : Unit.INSTANCE;
                    }
                };
            }
            return null;
        }
        return null;
    }

    @Override // com.jd.jdcache.service.base.AbstractDelegate
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    @NotNull
    public File getRelativeFile(@NotNull String relativeFilePath) {
        return new File(concretePath(relativeFilePath));
    }

    @NotNull
    protected final String getRootDirPath() {
        return (String) this.rootDirPath.getValue();
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    @Nullable
    public Object saveFileFromAsset(@NotNull String str, @NotNull String str2, @Nullable FileSaveOption fileSaveOption, @NotNull Continuation<? super FileState> continuation) {
        return saveFileFromAsset$suspendImpl(this, str, str2, fileSaveOption, continuation);
    }

    @Override // com.jd.jdcache.service.base.JDCacheFileRepoDelegate
    @Nullable
    public Flow<FileState> saveFileFromNetFlow(@NotNull final String url, @NotNull String relativeFilePath, @Nullable FileSaveOption option) {
        String str;
        if (relativeFilePath.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), "Cannot save file to empty path.");
            }
            return null;
        }
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = 0.0f;
        JDCacheNetDelegate netDelegate = getNetDelegate();
        if (netDelegate != null) {
            String concretePath = concretePath(relativeFilePath);
            if (option == null || (str = option.getMethod()) == null) {
                str = "GET";
            }
            final Flow<NetState<File>> downloadFlow = netDelegate.downloadFlow(url, concretePath, str, option != null ? option.getHeader() : null, option != null ? option.getUserAgent() : null, option != null ? option.getCookie() : null);
            if (downloadFlow != null) {
                return new Flow<FileState>() { // from class: com.jd.jdcache.service.impl.FileRepo$saveFileFromNetFlow$$inlined$map$1
                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
                        Object coroutine_suspended;
                        Object collect = downloadFlow.collect(new FlowCollector<NetState<File>>() { // from class: com.jd.jdcache.service.impl.FileRepo$saveFileFromNetFlow$$inlined$map$1.2
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            @Nullable
                            public Object emit(NetState<File> netState, @NotNull Continuation continuation2) {
                                FileState error;
                                FileState error2;
                                Object coroutine_suspended2;
                                FlowCollector flowCollector2 = flowCollector;
                                NetState<File> netState2 = netState;
                                if (netState2 instanceof NetState.OnStart) {
                                    JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                                    if (jDCacheLog2.getCanLog()) {
                                        jDCacheLog2.d(this.getName(), "Starting downloading file[" + url + "].");
                                    }
                                    error = new FileState.OnStart(url);
                                } else {
                                    if (netState2 instanceof NetState.Complete) {
                                        JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                                        if (jDCacheLog3.getCanLog()) {
                                            jDCacheLog3.d(this.getName(), "Complete downloading file[" + url + "] in " + ((File) ((NetState.Complete) netState2).getData()).getPath() + OrderISVUtil.MONEY_DECIMAL_CHAR);
                                        }
                                        NetState.Complete complete = (NetState.Complete) netState2;
                                        error2 = new FileState.Complete(complete.getCode(), complete.getLength(), complete.getHeaders(), (File) complete.getData());
                                    } else if (netState2 instanceof NetState.OnProgress) {
                                        NetState.OnProgress onProgress = (NetState.OnProgress) netState2;
                                        if (onProgress.getMax() > 0) {
                                            JDCacheLog jDCacheLog4 = JDCacheLog.INSTANCE;
                                            if (jDCacheLog4.getCanLog()) {
                                                float progress = ((float) onProgress.getProgress()) / ((float) onProgress.getMax());
                                                if (progress == 1.0f || progress - floatRef.element >= 10.0f) {
                                                    floatRef.element = progress;
                                                }
                                                jDCacheLog4.d(this.getName(), "Downloading file(" + (progress * 100) + "%)");
                                            }
                                        }
                                        error2 = new FileState.OnProgress(onProgress.getProgress(), onProgress.getMax());
                                    } else if (netState2 instanceof NetState.Error) {
                                        JDCacheLog jDCacheLog5 = JDCacheLog.INSTANCE;
                                        if (jDCacheLog5.getCanLog()) {
                                            String name = this.getName();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Error in downloading file[");
                                            sb.append(url);
                                            sb.append("]. ");
                                            sb.append("Code = ");
                                            NetState.Error error3 = (NetState.Error) netState2;
                                            sb.append(error3.getCode());
                                            sb.append(", ");
                                            sb.append("Exception = ");
                                            sb.append(error3.getThrowable());
                                            jDCacheLog5.e(name, sb.toString());
                                        }
                                        NetState.Error error4 = (NetState.Error) netState2;
                                        error2 = new FileState.Error(error4.getCode(), error4.getThrowable());
                                    } else if (netState2 instanceof NetState.Redirect) {
                                        JDCacheLog jDCacheLog6 = JDCacheLog.INSTANCE;
                                        if (jDCacheLog6.getCanLog()) {
                                            jDCacheLog6.e(this.getName(), "Redirect in downloading file[" + url + ']');
                                        }
                                        error = new FileState.Error(-1, new Exception("Redirect in downloading file"));
                                    } else {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    error = error2;
                                }
                                Object emit = flowCollector2.emit(error, continuation2);
                                coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                return emit == coroutine_suspended2 ? emit : Unit.INSTANCE;
                            }
                        }, continuation);
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        return collect == coroutine_suspended ? collect : Unit.INSTANCE;
                    }
                };
            }
            return null;
        }
        return null;
    }

    private final boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return true;
            }
            for (File file2 : listFiles) {
                deleteFile(file2);
            }
        }
        return file.delete();
    }
}
