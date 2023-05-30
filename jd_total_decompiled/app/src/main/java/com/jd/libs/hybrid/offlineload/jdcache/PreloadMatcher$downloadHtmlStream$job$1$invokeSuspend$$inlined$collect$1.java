package com.jd.libs.hybrid.offlineload.jdcache;

import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.match.PreReadInputStream;
import com.jd.jdcache.service.base.InputStreamState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.xdog.b;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedInputStream;
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
public final class PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<InputStreamState> {
    final /* synthetic */ PreloadMatcher$downloadHtmlStream$job$1 this$0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@\u00a8\u0006\u0006"}, d2 = {"T", "value", "LLkotlin/coroutines/Continuation;;", "L;", "continuation", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1", "emit"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1", f = "PreloadMatcher.kt", i = {0, 0, 0, 0, 0, 0}, l = {R2.anim.pop_left_top_in}, m = "emit", n = {"this", "value", "continuation", XView2Constants.STATE, "stream", "localResp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    /* renamed from: com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1$1 */
    /* loaded from: classes16.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Continuation continuation) {
            super(continuation);
            PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1.this = r1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    public PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1(PreloadMatcher$downloadHtmlStream$job$1 preloadMatcher$downloadHtmlStream$job$1) {
        this.this$0 = preloadMatcher$downloadHtmlStream$job$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0050  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(InputStreamState inputStreamState, @NotNull Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        AtomicBoolean destroyed;
        long j2;
        PreReadInputStream preReadInputStream;
        Channel waitingChannel;
        PreReadInputStream preReadInputStream2;
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
                    InputStreamState inputStreamState2 = inputStreamState;
                    if (inputStreamState2 instanceof InputStreamState.OnStart) {
                        this.this$0.this$0.start = System.currentTimeMillis();
                        b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(System.currentTimeMillis()));
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.d(this.this$0.this$0.getName(), "Start to download html stream (" + this.this$0.$url + ')');
                            Log.xLogDForDev(this.this$0.this$0.getName(), "Html\u9884\u4e0b\u8f7d\u5f00\u59cb\uff0c\u4e0b\u8f7dUrl: " + this.this$0.$url + ", cookie= " + this.this$0.$cookie);
                        }
                    } else {
                        r10 = null;
                        Map<String, String> map = null;
                        if (inputStreamState2 instanceof InputStreamState.Connected) {
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
                                jDCacheLog2.d(this.this$0.this$0.getName(), "Connected html, code " + ((InputStreamState.Connected) inputStreamState2).getCode() + ", start to pre-read.");
                            }
                            j2 = this.this$0.this$0.end;
                            b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_END, String.valueOf(j2));
                            String name = this.this$0.this$0.getName();
                            StringBuilder sb = new StringBuilder();
                            sb.append("\u9879\u76ee(id:");
                            OfflineFiles offlineFiles = this.this$0.this$0.getOfflineFiles();
                            sb.append(offlineFiles != null ? offlineFiles.getAppId() : null);
                            sb.append(")\u7684HTML\u9884\u4e0b\u8f7d\u8fde\u63a5\u6210\u529f\uff0c\u5f00\u59cb\u9884\u8bfb\u3002");
                            Log.xLogD(name, sb.toString());
                            InputStreamState.Connected connected = (InputStreamState.Connected) inputStreamState2;
                            preReadInputStream = new PreReadInputStream(new BufferedInputStream(connected.getData()));
                            PreloadMatcher$downloadHtmlStream$job$1 preloadMatcher$downloadHtmlStream$job$1 = this.this$0;
                            preloadMatcher$downloadHtmlStream$job$1.this$0.saveCookieFromRespHeaders(preloadMatcher$downloadHtmlStream$job$1.$url, connected.getHeaders());
                            JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(this.this$0.$url, "html", null, null, null, false, 60, null);
                            jDCacheLocalResp.setFileStream(preReadInputStream);
                            Map<String, List<String>> headers = connected.getHeaders();
                            if (headers != null && (convertHeader = UrlHelper.INSTANCE.convertHeader(headers)) != null) {
                                map = MapsKt__MapsKt.toMutableMap(convertHeader);
                            }
                            jDCacheLocalResp.header = map;
                            waitingChannel = this.this$0.this$0.getWaitingChannel();
                            if (waitingChannel != null) {
                                anonymousClass1.L$0 = this;
                                anonymousClass1.L$1 = inputStreamState;
                                anonymousClass1.L$2 = anonymousClass1;
                                anonymousClass1.L$3 = inputStreamState2;
                                anonymousClass1.L$4 = preReadInputStream;
                                anonymousClass1.L$5 = jDCacheLocalResp;
                                anonymousClass1.label = 1;
                                if (waitingChannel.send(jDCacheLocalResp, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                preReadInputStream2 = preReadInputStream;
                            }
                            preReadInputStream.startPreRead();
                        } else if (inputStreamState2 instanceof InputStreamState.Error) {
                            this.this$0.this$0.end = System.currentTimeMillis();
                            PreloadMatcher preloadMatcher2 = this.this$0.this$0;
                            unused2 = PreloadMatcher.INSTANCE;
                            preloadMatcher2.state = -1;
                            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                            if (jDCacheLog3.getCanLog()) {
                                String name2 = this.this$0.this$0.getName();
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Fail pre-downloading html, code=");
                                InputStreamState.Error error = (InputStreamState.Error) inputStreamState2;
                                sb2.append(error.getCode());
                                sb2.append(", exception=");
                                sb2.append(error.getThrowable());
                                jDCacheLog3.e(name2, sb2.toString());
                                String name3 = this.this$0.this$0.getName();
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("\u9879\u76ee(id:");
                                OfflineFiles offlineFiles2 = this.this$0.this$0.getOfflineFiles();
                                sb3.append(offlineFiles2 != null ? offlineFiles2.getAppId() : null);
                                sb3.append(")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=");
                                sb3.append(error.getCode());
                                sb3.append(", exception=");
                                sb3.append(error.getThrowable());
                                Log.xLogE(name3, sb3.toString());
                            }
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("html\u9884\u4e0b\u8f7d\u5931\u8d25, code=");
                            InputStreamState.Error error2 = (InputStreamState.Error) inputStreamState2;
                            sb4.append(error2.getCode());
                            sb4.append(", exception=");
                            sb4.append(error2.getThrowable());
                            String sb5 = sb4.toString();
                            if (error2.getCode() == 400 || error2.getCode() == 431) {
                                try {
                                    sb5 = sb5 + ", UA Size=" + StringUtils.getByteSize(this.this$0.$userAgent) + ", Cookie Size=" + StringUtils.getByteSize(this.this$0.$cookie) + ", UA=" + this.this$0.$userAgent + ", Cookie=" + this.this$0.$cookie;
                                } catch (Throwable unused3) {
                                }
                            }
                            String str = sb5;
                            int code = error2.getCode();
                            OfflineFiles offlineFiles3 = this.this$0.this$0.getOfflineFiles();
                            OfflineExceptionUtils.reportDownloadError(code, OfflineExceptionUtils.ERR_MSG_NET, "downloadHtmlStream-Error", offlineFiles3 != null ? offlineFiles3.getAppId() : null, this.this$0.$url, str);
                        }
                    }
                    return Unit.INSTANCE;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    JDCacheLocalResp jDCacheLocalResp2 = (JDCacheLocalResp) anonymousClass1.L$5;
                    preReadInputStream2 = (PreReadInputStream) anonymousClass1.L$4;
                    InputStreamState inputStreamState3 = (InputStreamState) anonymousClass1.L$3;
                    Continuation continuation2 = (Continuation) anonymousClass1.L$2;
                    Object obj2 = anonymousClass1.L$1;
                    PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1 preloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1 = (PreloadMatcher$downloadHtmlStream$job$1$invokeSuspend$$inlined$collect$1) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                preReadInputStream = preReadInputStream2;
                preReadInputStream.startPreRead();
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj3 = anonymousClass1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.label;
        if (i2 != 0) {
        }
        preReadInputStream = preReadInputStream2;
        preReadInputStream.startPreRead();
        return Unit.INSTANCE;
    }
}
