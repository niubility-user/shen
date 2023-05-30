package com.jd.jdcache.service.impl.net;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

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
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parseData(int r25, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>> r26, long r27, @org.jetbrains.annotations.Nullable java.io.InputStream r29, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.jd.jdcache.service.base.NetState<java.io.File>> r30) {
        /*
            Method dump skipped, instructions count: 639
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.impl.net.FileRequest.parseData(int, java.util.Map, long, java.io.InputStream, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
