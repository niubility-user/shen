package com.jd.jdcache.service.impl.net;

import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018JS\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u001e\u0010\b\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u00068\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u00020\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/jd/jdcache/service/impl/net/FileRequest;", "Lcom/jd/jdcache/service/impl/net/BaseRequest;", "Ljava/io/File;", "", "responseCode", "", "", "", "responseHeaders", "", "contentLength", "Ljava/io/InputStream;", "inputStream", "Lcom/jd/jdcache/service/base/NetState;", "parseData", "(ILjava/util/Map;JLjava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "targetPath", "getTargetPath", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class FileRequest extends BaseRequest<File> {
    @NotNull
    private final String TAG;
    @NotNull
    private final String targetPath;

    public FileRequest(@NotNull String str, @NotNull String str2) {
        super(str, null, null, null, null, null, null, false, null, 0, 0, R2.attr.textRightColor, null);
        this.targetPath = str2;
        this.TAG = "FileRequest";
    }

    @Override // com.jd.jdcache.service.impl.net.BaseRequest
    @NotNull
    public String getTAG() {
        return this.TAG;
    }

    @NotNull
    public final String getTargetPath() {
        return this.targetPath;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(12:(2:3|(15:5|6|7|(1:(1:(8:11|12|13|14|15|16|17|(5:19|20|21|22|(1:24)(5:26|15|16|17|(8:64|65|66|67|(2:69|70)|(2:38|39)|36|37)(0)))(0))(2:90|91))(4:92|93|94|95))(2:116|(4:118|119|120|(1:(2:123|124)(2:125|(1:127)(1:128)))(7:129|66|67|(0)|(0)|36|37))(4:134|(0)|36|37))|96|97|(1:99)|100|101|102|103|104|16|17|(0)(0)))|96|97|(0)|100|101|102|103|104|16|17|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(18:1|(2:3|(15:5|6|7|(1:(1:(8:11|12|13|14|15|16|17|(5:19|20|21|22|(1:24)(5:26|15|16|17|(8:64|65|66|67|(2:69|70)|(2:38|39)|36|37)(0)))(0))(2:90|91))(4:92|93|94|95))(2:116|(4:118|119|120|(1:(2:123|124)(2:125|(1:127)(1:128)))(7:129|66|67|(0)|(0)|36|37))(4:134|(0)|36|37))|96|97|(1:99)|100|101|102|103|104|16|17|(0)(0)))|136|6|7|(0)(0)|96|97|(0)|100|101|102|103|104|16|17|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0097, code lost:
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x018b, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x018c, code lost:
        r20 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0194, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0195, code lost:
        r20 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019c, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x019d, code lost:
        r13 = r1;
        r6 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01a1, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01a2, code lost:
        r13 = r1;
        r8 = r2;
        r6 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01aa, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ab, code lost:
        r13 = r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0264 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0222 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x023d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f4 A[Catch: all -> 0x01a6, Exception -> 0x01aa, TryCatch #2 {Exception -> 0x01aa, blocks: (B:38:0x00e7, B:40:0x00f4, B:41:0x00fb), top: B:121:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0136 A[Catch: all -> 0x018b, Exception -> 0x0194, TRY_LEAVE, TryCatch #16 {Exception -> 0x0194, all -> 0x018b, blocks: (B:45:0x0121, B:47:0x0136), top: B:140:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017a A[Catch: all -> 0x0187, Exception -> 0x0189, TRY_LEAVE, TryCatch #17 {Exception -> 0x0189, all -> 0x0187, blocks: (B:49:0x0167, B:54:0x017a), top: B:138:0x0167 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01f9 A[Catch: all -> 0x0096, TryCatch #13 {all -> 0x0096, blocks: (B:13:0x0053, B:89:0x01f1, B:91:0x01f9, B:92:0x0200, B:20:0x008e), top: B:132:0x002b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x016e -> B:53:0x0177). Please submit an issue!!! */
    @Override // com.jd.jdcache.service.impl.net.BaseRequest
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object parseData(int i2, @Nullable Map<String, ? extends List<String>> map, long j2, @Nullable InputStream inputStream, @NotNull Continuation<? super NetState<File>> continuation) {
        FileRequest$parseData$1 fileRequest$parseData$1;
        Object coroutine_suspended;
        int i3;
        FileRequest fileRequest;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        Object error;
        FileRequest fileRequest2;
        Map<String, ? extends List<String>> map2;
        long j3;
        InputStream inputStream2;
        File parentFile;
        long j4;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        Object obj;
        byte[] bArr;
        FileRequest fileRequest3;
        Ref.IntRef intRef;
        FileRequest$parseData$1 fileRequest$parseData$12;
        JDCacheLog jDCacheLog;
        FileRequest fileRequest4;
        Integer boxInt;
        long j5;
        int i4 = i2;
        InputStream inputStream3 = inputStream;
        try {
            try {
                if (continuation instanceof FileRequest$parseData$1) {
                    fileRequest$parseData$1 = (FileRequest$parseData$1) continuation;
                    int i5 = fileRequest$parseData$1.label;
                    if ((i5 & Integer.MIN_VALUE) != 0) {
                        fileRequest$parseData$1.label = i5 - Integer.MIN_VALUE;
                        FileRequest$parseData$1 fileRequest$parseData$13 = fileRequest$parseData$1;
                        Object obj2 = fileRequest$parseData$13.result;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i3 = fileRequest$parseData$13.label;
                        if (i3 != 0) {
                            ResultKt.throwOnFailure(obj2);
                            if (i4 == 200) {
                                try {
                                } catch (Exception e2) {
                                    e = e2;
                                    fileRequest = this;
                                    bufferedOutputStream = null;
                                    jDCacheLog = JDCacheLog.INSTANCE;
                                    if (jDCacheLog.getCanLog()) {
                                    }
                                    error = new NetState.Error(-1, new Exception("Write file error: " + e.getMessage()));
                                    if (bufferedOutputStream != null) {
                                    }
                                    fileRequest2 = fileRequest;
                                    if (inputStream3 != null) {
                                    }
                                    fileRequest2.disconnect();
                                    return error;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileRequest = this;
                                    bufferedOutputStream = null;
                                    if (bufferedOutputStream != null) {
                                        try {
                                            bufferedOutputStream.close();
                                        } catch (IOException e3) {
                                            if (JDCacheLog.INSTANCE.getCanLog()) {
                                                JDCacheLog.INSTANCE.e(fileRequest.getTAG(), e3);
                                            }
                                        }
                                    }
                                    throw th;
                                }
                                if ((!Intrinsics.areEqual(getMethod(), UrlHelper.METHOD_HEAD)) != true) {
                                    map2 = map;
                                    j3 = j2;
                                    fileRequest2 = this;
                                    bufferedOutputStream = null;
                                    error = new NetState.Complete(i4, map2, j3, new File(fileRequest2.targetPath));
                                    if (bufferedOutputStream != null) {
                                    }
                                    if (inputStream3 != null) {
                                    }
                                    fileRequest2.disconnect();
                                    return error;
                                } else if (inputStream3 == null) {
                                    return new NetState.Error(-1, new Exception("Response stream is null!"));
                                } else {
                                    fileRequest$parseData$13.L$0 = this;
                                    fileRequest$parseData$13.I$0 = i4;
                                    map2 = map;
                                    fileRequest$parseData$13.L$1 = map2;
                                    j3 = j2;
                                    fileRequest$parseData$13.J$0 = j3;
                                    fileRequest$parseData$13.L$2 = inputStream3;
                                    fileRequest$parseData$13.L$3 = null;
                                    fileRequest$parseData$13.label = 1;
                                    if (notifyProgress(0L, j2, fileRequest$parseData$13) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    fileRequest2 = this;
                                    inputStream2 = inputStream3;
                                    bufferedOutputStream = null;
                                }
                            } else {
                                error = new NetState.Error(i4, new Exception("Response code is not 200"));
                                fileRequest2 = this;
                                if (inputStream3 != null) {
                                }
                                fileRequest2.disconnect();
                                return error;
                            }
                        } else if (i3 == 1) {
                            bufferedOutputStream = (BufferedOutputStream) fileRequest$parseData$13.L$3;
                            inputStream2 = (InputStream) fileRequest$parseData$13.L$2;
                            long j6 = fileRequest$parseData$13.J$0;
                            Map<String, ? extends List<String>> map3 = (Map) fileRequest$parseData$13.L$1;
                            int i6 = fileRequest$parseData$13.I$0;
                            fileRequest = (FileRequest) fileRequest$parseData$13.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                map2 = map3;
                                i4 = i6;
                                fileRequest2 = fileRequest;
                                j3 = j6;
                            } catch (Exception e4) {
                                e = e4;
                                inputStream3 = inputStream2;
                                jDCacheLog = JDCacheLog.INSTANCE;
                                if (jDCacheLog.getCanLog()) {
                                }
                                error = new NetState.Error(-1, new Exception("Write file error: " + e.getMessage()));
                                if (bufferedOutputStream != null) {
                                }
                                fileRequest2 = fileRequest;
                                if (inputStream3 != null) {
                                }
                                fileRequest2.disconnect();
                                return error;
                            }
                        } else if (i3 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            Ref.IntRef intRef2 = (Ref.IntRef) fileRequest$parseData$13.L$6;
                            long j7 = fileRequest$parseData$13.J$1;
                            byte[] bArr2 = (byte[]) fileRequest$parseData$13.L$5;
                            FileOutputStream fileOutputStream2 = (FileOutputStream) fileRequest$parseData$13.L$4;
                            bufferedOutputStream = (BufferedOutputStream) fileRequest$parseData$13.L$3;
                            inputStream3 = (InputStream) fileRequest$parseData$13.L$2;
                            long j8 = fileRequest$parseData$13.J$0;
                            Map<String, ? extends List<String>> map4 = (Map) fileRequest$parseData$13.L$1;
                            int i7 = fileRequest$parseData$13.I$0;
                            fileRequest = (FileRequest) fileRequest$parseData$13.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                intRef = intRef2;
                                i4 = i7;
                                map2 = map4;
                                fileOutputStream = fileOutputStream2;
                                fileRequest3 = fileRequest;
                                j3 = j8;
                                bufferedOutputStream2 = bufferedOutputStream;
                                fileRequest$parseData$12 = fileRequest$parseData$13;
                                bArr = bArr2;
                                obj = coroutine_suspended;
                                j4 = j7;
                                boxInt = Boxing.boxInt(inputStream3.read(bArr));
                                intRef.element = boxInt.intValue();
                            } catch (Exception e5) {
                                e = e5;
                                jDCacheLog = JDCacheLog.INSTANCE;
                                if (jDCacheLog.getCanLog()) {
                                    jDCacheLog.e(fileRequest.getTAG(), e);
                                }
                                error = new NetState.Error(-1, new Exception("Write file error: " + e.getMessage()));
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e6) {
                                        if (JDCacheLog.INSTANCE.getCanLog()) {
                                            JDCacheLog.INSTANCE.e(fileRequest.getTAG(), e6);
                                        }
                                    }
                                }
                                fileRequest2 = fileRequest;
                                if (inputStream3 != null) {
                                }
                                fileRequest2.disconnect();
                                return error;
                            }
                            if (boxInt.intValue() == -1) {
                                try {
                                    bufferedOutputStream2.write(bArr, 0, intRef.element);
                                    j5 = j4 + intRef.element;
                                    fileRequest$parseData$12.L$0 = fileRequest3;
                                    fileRequest$parseData$12.I$0 = i4;
                                    fileRequest$parseData$12.L$1 = map2;
                                    fileRequest$parseData$12.J$0 = j3;
                                    fileRequest$parseData$12.L$2 = inputStream3;
                                    fileRequest$parseData$12.L$3 = bufferedOutputStream2;
                                    fileRequest$parseData$12.L$4 = fileOutputStream;
                                    fileRequest$parseData$12.L$5 = bArr;
                                    fileRequest$parseData$12.J$1 = j5;
                                    fileRequest$parseData$12.L$6 = intRef;
                                    fileRequest$parseData$12.label = 2;
                                } catch (Exception e7) {
                                    e = e7;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    fileRequest = fileRequest4;
                                    jDCacheLog = JDCacheLog.INSTANCE;
                                    if (jDCacheLog.getCanLog()) {
                                    }
                                    error = new NetState.Error(-1, new Exception("Write file error: " + e.getMessage()));
                                    if (bufferedOutputStream != null) {
                                    }
                                    fileRequest2 = fileRequest;
                                    if (inputStream3 != null) {
                                    }
                                    fileRequest2.disconnect();
                                    return error;
                                } catch (Throwable th3) {
                                    th = th3;
                                    th = th;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    fileRequest = fileRequest4;
                                    if (bufferedOutputStream != null) {
                                    }
                                    throw th;
                                }
                                int i8 = i4;
                                Object obj3 = obj;
                                fileRequest4 = fileRequest3;
                                FileRequest$parseData$1 fileRequest$parseData$14 = fileRequest$parseData$12;
                                if (fileRequest3.notifyProgress(j5, j3, fileRequest$parseData$12) == obj3) {
                                    return obj3;
                                }
                                obj = obj3;
                                j7 = j5;
                                fileRequest3 = fileRequest4;
                                fileRequest$parseData$12 = fileRequest$parseData$14;
                                i4 = i8;
                                j4 = j7;
                                boxInt = Boxing.boxInt(inputStream3.read(bArr));
                                intRef.element = boxInt.intValue();
                                if (boxInt.intValue() == -1) {
                                    int i9 = i4;
                                    fileRequest4 = fileRequest3;
                                    bufferedOutputStream2.flush();
                                    i4 = i9;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    fileRequest2 = fileRequest4;
                                    try {
                                        error = new NetState.Complete(i4, map2, j3, new File(fileRequest2.targetPath));
                                        if (bufferedOutputStream != null) {
                                            try {
                                                bufferedOutputStream.close();
                                            } catch (IOException e8) {
                                                if (JDCacheLog.INSTANCE.getCanLog()) {
                                                    JDCacheLog.INSTANCE.e(fileRequest2.getTAG(), e8);
                                                }
                                            }
                                        }
                                    } catch (Exception e9) {
                                        e = e9;
                                        fileRequest = fileRequest2;
                                        jDCacheLog = JDCacheLog.INSTANCE;
                                        if (jDCacheLog.getCanLog()) {
                                        }
                                        error = new NetState.Error(-1, new Exception("Write file error: " + e.getMessage()));
                                        if (bufferedOutputStream != null) {
                                        }
                                        fileRequest2 = fileRequest;
                                        if (inputStream3 != null) {
                                        }
                                        fileRequest2.disconnect();
                                        return error;
                                    }
                                    if (inputStream3 != null) {
                                        try {
                                            inputStream3.close();
                                        } catch (IOException e10) {
                                            if (JDCacheLog.INSTANCE.getCanLog()) {
                                                JDCacheLog.INSTANCE.e(fileRequest2.getTAG(), e10);
                                            }
                                        }
                                    }
                                    fileRequest2.disconnect();
                                    return error;
                                }
                            }
                        }
                        parentFile = new File(fileRequest2.targetPath).getParentFile();
                        if (parentFile != null) {
                            Boxing.boxBoolean(parentFile.mkdirs());
                        }
                        FileOutputStream fileOutputStream3 = new FileOutputStream(fileRequest2.targetPath, false);
                        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(fileOutputStream3);
                        byte[] bArr3 = new byte[10240];
                        j4 = 0;
                        Ref.IntRef intRef3 = new Ref.IntRef();
                        intRef3.element = 0;
                        inputStream3 = inputStream2;
                        fileOutputStream = fileOutputStream3;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        obj = coroutine_suspended;
                        bArr = bArr3;
                        fileRequest3 = fileRequest2;
                        intRef = intRef3;
                        fileRequest$parseData$12 = fileRequest$parseData$13;
                        boxInt = Boxing.boxInt(inputStream3.read(bArr));
                        intRef.element = boxInt.intValue();
                        if (boxInt.intValue() == -1) {
                        }
                    }
                }
                parentFile = new File(fileRequest2.targetPath).getParentFile();
                if (parentFile != null) {
                }
                FileOutputStream fileOutputStream32 = new FileOutputStream(fileRequest2.targetPath, false);
                BufferedOutputStream bufferedOutputStream32 = new BufferedOutputStream(fileOutputStream32);
                byte[] bArr32 = new byte[10240];
                j4 = 0;
                Ref.IntRef intRef32 = new Ref.IntRef();
                intRef32.element = 0;
                inputStream3 = inputStream2;
                fileOutputStream = fileOutputStream32;
                bufferedOutputStream2 = bufferedOutputStream32;
                obj = coroutine_suspended;
                bArr = bArr32;
                fileRequest3 = fileRequest2;
                intRef = intRef32;
                fileRequest$parseData$12 = fileRequest$parseData$13;
                boxInt = Boxing.boxInt(inputStream3.read(bArr));
                intRef.element = boxInt.intValue();
                if (boxInt.intValue() == -1) {
                }
            } catch (Throwable th4) {
                th = th4;
                fileRequest = fileRequest2;
            }
            if (i3 != 0) {
            }
        } catch (Throwable th5) {
            th = th5;
        }
        fileRequest$parseData$1 = new FileRequest$parseData$1(this, continuation);
        FileRequest$parseData$1 fileRequest$parseData$132 = fileRequest$parseData$1;
        Object obj22 = fileRequest$parseData$132.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i3 = fileRequest$parseData$132.label;
    }
}
