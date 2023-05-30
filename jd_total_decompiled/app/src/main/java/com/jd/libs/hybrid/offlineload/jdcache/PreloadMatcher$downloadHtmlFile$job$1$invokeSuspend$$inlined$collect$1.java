package com.jd.libs.hybrid.offlineload.jdcache;

import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.service.base.FileState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.xdog.b;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006\u00b8\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<FileState> {
    final /* synthetic */ PreloadMatcher$downloadHtmlFile$job$1 this$0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@\u00a8\u0006\u0006"}, d2 = {"T", "value", "LLkotlin/coroutines/Continuation;;", "L;", "continuation", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "emit"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1", f = "PreloadMatcher.kt", i = {0, 0, 0, 0, 0}, l = {R2.anim.pop_left_top_out}, m = "emit", n = {"this", "value", "continuation", XView2Constants.STATE, "localResp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1$1 */
    /* loaded from: classes16.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Continuation continuation) {
            super(continuation);
            PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1.this = r1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    public PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1(PreloadMatcher$downloadHtmlFile$job$1 preloadMatcher$downloadHtmlFile$job$1) {
        this.this$0 = preloadMatcher$downloadHtmlFile$job$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x004c  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(FileState fileState, @NotNull Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        AtomicBoolean destroyed;
        long j2;
        Channel waitingChannel;
        Map<String, String> convertHeader;
        PreloadMatcher.Companion unused;
        PreloadMatcher.Companion unused2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i3 = anonymousClass1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i3 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    FileState fileState2 = fileState;
                    if (fileState2 instanceof FileState.OnStart) {
                        this.this$0.this$0.start = System.currentTimeMillis();
                        b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(System.currentTimeMillis()));
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.d(this.this$0.this$0.getName(), "Start to download html file (" + this.this$0.$url + ')');
                            Log.xLogDForDev(this.this$0.this$0.getName(), "Html\u9884\u4e0b\u8f7d\u5f00\u59cb\uff0c\u4e0b\u8f7dUrl: " + this.this$0.$url + ", cookie= " + this.this$0.$cookie);
                        }
                    } else if (!(fileState2 instanceof FileState.OnProgress)) {
                        r10 = null;
                        Map<String, String> map = null;
                        if (fileState2 instanceof FileState.Complete) {
                            this.this$0.this$0.end = System.currentTimeMillis();
                            PreloadMatcher preloadMatcher = this.this$0.this$0;
                            unused = PreloadMatcher.INSTANCE;
                            preloadMatcher.state = 200;
                            destroyed = this.this$0.this$0.getDestroyed();
                            if (destroyed.get()) {
                                return Unit.INSTANCE;
                            }
                            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                            if (jDCacheLog2.getCanLog()) {
                                String name = this.this$0.this$0.getName();
                                StringBuilder sb = new StringBuilder();
                                sb.append("Pre-download html file success, code ");
                                FileState.Complete complete = (FileState.Complete) fileState2;
                                sb.append(complete.getCode());
                                sb.append(", save path = ");
                                sb.append(complete.getData().getPath());
                                sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                                jDCacheLog2.d(name, sb.toString());
                            }
                            j2 = this.this$0.this$0.end;
                            b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_END, String.valueOf(j2));
                            String name2 = this.this$0.this$0.getName();
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("\u9879\u76ee(id:");
                            OfflineFiles offlineFiles = this.this$0.this$0.getOfflineFiles();
                            sb2.append(offlineFiles != null ? offlineFiles.getAppId() : null);
                            sb2.append(")\u7684HTML\u9884\u4e0b\u8f7d\u6210\u529f\u5b8c\u6210\u3002");
                            Log.xLogD(name2, sb2.toString());
                            PreloadMatcher$downloadHtmlFile$job$1 preloadMatcher$downloadHtmlFile$job$1 = this.this$0;
                            FileState.Complete complete2 = (FileState.Complete) fileState2;
                            preloadMatcher$downloadHtmlFile$job$1.this$0.saveCookieFromRespHeaders(preloadMatcher$downloadHtmlFile$job$1.$url, complete2.getHeaders());
                            JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(this.this$0.$url, "html", null, null, null, false, 60, null);
                            jDCacheLocalResp.setFilename(complete2.getData().getPath());
                            Map<String, List<String>> headers = complete2.getHeaders();
                            if (headers != null && (convertHeader = UrlHelper.INSTANCE.convertHeader(headers)) != null) {
                                map = MapsKt__MapsKt.toMutableMap(convertHeader);
                            }
                            jDCacheLocalResp.header = map;
                            waitingChannel = this.this$0.this$0.getWaitingChannel();
                            if (waitingChannel != null) {
                                anonymousClass1.L$0 = this;
                                anonymousClass1.L$1 = fileState;
                                anonymousClass1.L$2 = anonymousClass1;
                                anonymousClass1.L$3 = fileState2;
                                anonymousClass1.L$4 = jDCacheLocalResp;
                                anonymousClass1.label = 1;
                                if (waitingChannel.send(jDCacheLocalResp, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        } else if (fileState2 instanceof FileState.Error) {
                            this.this$0.this$0.end = System.currentTimeMillis();
                            PreloadMatcher preloadMatcher2 = this.this$0.this$0;
                            unused2 = PreloadMatcher.INSTANCE;
                            preloadMatcher2.state = -1;
                            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                            if (jDCacheLog3.getCanLog()) {
                                String name3 = this.this$0.this$0.getName();
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Fail pre-downloading html, code=");
                                FileState.Error error = (FileState.Error) fileState2;
                                sb3.append(error.getCode());
                                sb3.append(", exception=");
                                sb3.append(error.getThrowable());
                                jDCacheLog3.e(name3, sb3.toString());
                                String name4 = this.this$0.this$0.getName();
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("\u9879\u76ee(id:");
                                OfflineFiles offlineFiles2 = this.this$0.this$0.getOfflineFiles();
                                sb4.append(offlineFiles2 != null ? offlineFiles2.getAppId() : null);
                                sb4.append(")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=");
                                sb4.append(error.getCode());
                                sb4.append(", exception=");
                                sb4.append(error.getThrowable());
                                Log.xLogE(name4, sb4.toString());
                            }
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("html\u9884\u4e0b\u8f7d\u5931\u8d25, code=");
                            FileState.Error error2 = (FileState.Error) fileState2;
                            sb5.append(error2.getCode());
                            sb5.append(", exception=");
                            sb5.append(error2.getThrowable());
                            String sb6 = sb5.toString();
                            if (error2.getCode() == 400 || error2.getCode() == 431) {
                                try {
                                    sb6 = sb6 + ", UA Size=" + StringUtils.getByteSize(this.this$0.$userAgent) + ", Cookie Size=" + StringUtils.getByteSize(this.this$0.$cookie) + ", UA=" + this.this$0.$userAgent + ", Cookie=" + this.this$0.$cookie;
                                } catch (Throwable unused3) {
                                }
                            }
                            String str = sb6;
                            int code = error2.getCode();
                            OfflineFiles offlineFiles3 = this.this$0.this$0.getOfflineFiles();
                            OfflineExceptionUtils.reportDownloadError(code, OfflineExceptionUtils.ERR_MSG_NET, "downloadHtmlFile-Error", offlineFiles3 != null ? offlineFiles3.getAppId() : null, this.this$0.$url, str);
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    JDCacheLocalResp jDCacheLocalResp2 = (JDCacheLocalResp) anonymousClass1.L$4;
                    FileState fileState3 = (FileState) anonymousClass1.L$3;
                    Continuation continuation2 = (Continuation) anonymousClass1.L$2;
                    Object obj2 = anonymousClass1.L$1;
                    PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1 preloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1 = (PreloadMatcher$downloadHtmlFile$job$1$invokeSuspend$$inlined$collect$1) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj3 = anonymousClass1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.label;
        if (i2 != 0) {
        }
        return Unit.INSTANCE;
    }
}
