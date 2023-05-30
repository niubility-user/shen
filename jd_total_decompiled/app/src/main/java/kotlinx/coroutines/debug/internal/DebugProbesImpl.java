package kotlinx.coroutines.debug.internal;

import com.jd.libs.hybrid.HybridSDK;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.AgentPremain;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u00c0\u0002\u0018\u00002\u00020\u0001:\u0002\u008d\u0001B\n\b\u0002\u00a2\u0006\u0005\b\u008c\u0001\u0010\bJ\u001d\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ;\u0010\u0013\u001a\u00020\u0004*\u00020\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u000b2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J>\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a\"\b\b\u0000\u0010\u0015*\u00020\u00012\u001c\u0010\u0019\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00028\u00000\u0016H\u0082\b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J%\u0010#\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001d2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u001aH\u0002\u00a2\u0006\u0004\b#\u0010$J5\u0010)\u001a\b\u0012\u0004\u0012\u00020!0\u001a2\u0006\u0010%\u001a\u00020\u00112\b\u0010'\u001a\u0004\u0018\u00010&2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020!0\u001aH\u0002\u00a2\u0006\u0004\b)\u0010*J?\u00100\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00030/2\u0006\u0010,\u001a\u00020+2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020!0-2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020!0\u001aH\u0002\u00a2\u0006\u0004\b0\u00101J3\u00103\u001a\u00020+2\u0006\u00102\u001a\u00020+2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020!0-2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020!0\u001aH\u0002\u00a2\u0006\u0004\b3\u00104J#\u00107\u001a\u00020\u00042\n\u00106\u001a\u0006\u0012\u0002\b\u0003052\u0006\u0010%\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b7\u00108J\u001f\u0010:\u001a\u00020\u00042\u0006\u00106\u001a\u0002092\u0006\u0010%\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b:\u0010;J\u0016\u0010<\u001a\u0004\u0018\u000109*\u000209H\u0082\u0010\u00a2\u0006\u0004\b<\u0010=J/\u00107\u001a\u00020\u00042\n\u0010>\u001a\u0006\u0012\u0002\b\u00030\u00172\n\u00106\u001a\u0006\u0012\u0002\b\u0003052\u0006\u0010%\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b7\u0010?J\u001d\u0010>\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017*\u0006\u0012\u0002\b\u000305H\u0002\u00a2\u0006\u0004\b>\u0010@J\u001a\u0010>\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017*\u000209H\u0082\u0010\u00a2\u0006\u0004\b>\u0010AJ\u001b\u0010C\u001a\u0004\u0018\u00010B*\b\u0012\u0004\u0012\u00020!0\u001aH\u0002\u00a2\u0006\u0004\bC\u0010DJ3\u0010G\u001a\b\u0012\u0004\u0012\u00028\u000005\"\u0004\b\u0000\u0010E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00028\u0000052\b\u00106\u001a\u0004\u0018\u00010BH\u0002\u00a2\u0006\u0004\bG\u0010HJ\u001b\u0010I\u001a\u00020\u00042\n\u0010>\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002\u00a2\u0006\u0004\bI\u0010JJ'\u0010M\u001a\b\u0012\u0004\u0012\u00020!0\u001a\"\b\b\u0000\u0010E*\u00020K2\u0006\u0010L\u001a\u00028\u0000H\u0002\u00a2\u0006\u0004\bM\u0010NJ\r\u0010O\u001a\u00020\u0004\u00a2\u0006\u0004\bO\u0010\bJ\r\u0010P\u001a\u00020\u0004\u00a2\u0006\u0004\bP\u0010\bJ\u0015\u0010R\u001a\u00020\u00112\u0006\u0010Q\u001a\u00020\n\u00a2\u0006\u0004\bR\u0010SJ\u0013\u0010U\u001a\b\u0012\u0004\u0012\u00020T0\u001a\u00a2\u0006\u0004\bU\u0010VJ\u0013\u0010X\u001a\b\u0012\u0004\u0012\u00020W0\u001a\u00a2\u0006\u0004\bX\u0010VJ\u0015\u0010Y\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\bY\u0010 J)\u0010[\u001a\b\u0012\u0004\u0012\u00020!0\u001a2\u0006\u0010Z\u001a\u00020T2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020!0\u001a\u00a2\u0006\u0004\b[\u0010\\J\u001b\u0010_\u001a\u00020\u00042\n\u00106\u001a\u0006\u0012\u0002\b\u000305H\u0000\u00a2\u0006\u0004\b]\u0010^J\u001b\u0010a\u001a\u00020\u00042\n\u00106\u001a\u0006\u0012\u0002\b\u000305H\u0000\u00a2\u0006\u0004\b`\u0010^J)\u0010d\u001a\b\u0012\u0004\u0012\u00028\u000005\"\u0004\b\u0000\u0010E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0000\u00a2\u0006\u0004\bb\u0010cR\u0016\u0010f\u001a\u00020e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010gR \u0010k\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170h8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bi\u0010jR$\u0010l\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR \u0010s\u001a\u00020\u0011*\u00020\n8B@\u0002X\u0082\u0004\u00a2\u0006\f\u0012\u0004\bq\u0010r\u001a\u0004\bp\u0010SR\"\u0010u\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\f0t8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010vR\"\u0010w\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bw\u0010x\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u0016\u0010~\u001a\u00020}8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010\u007fR\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u001d\u0010\u0082\u0001\u001a\u00020\u0003*\u00020!8B@\u0002X\u0082\u0004\u00a2\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0085\u0001\u001a\u00020\u00038@@\u0000X\u0080\u0004\u00a2\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010zR&\u0010\u0086\u0001\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0086\u0001\u0010x\u001a\u0005\b\u0087\u0001\u0010z\"\u0005\b\u0088\u0001\u0010|R(\u0010\u0089\u0001\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u00030t8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010vR\u0019\u0010\u008a\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u00a8\u0006\u008e\u0001"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "", "Lkotlin/Function1;", "", "", "getDynamicAttach", "()Lkotlin/jvm/functions/Function1;", "startWeakRefCleanerThread", "()V", "stopWeakRefCleanerThread", "Lkotlinx/coroutines/Job;", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "map", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "", "indent", HybridSDK.APP_VERSION_CODE, "(Lkotlinx/coroutines/Job;Ljava/util/Map;Ljava/lang/StringBuilder;Ljava/lang/String;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "Lkotlin/coroutines/CoroutineContext;", "create", "", "dumpCoroutinesInfoImpl", "(Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "Ljava/io/PrintStream;", "out", "dumpCoroutinesSynchronized", "(Ljava/io/PrintStream;)V", "Ljava/lang/StackTraceElement;", "frames", "printStackTrace", "(Ljava/io/PrintStream;Ljava/util/List;)V", XView2Constants.STATE, "Ljava/lang/Thread;", "thread", "coroutineTrace", "enhanceStackTraceWithThreadDumpImpl", "(Ljava/lang/String;Ljava/lang/Thread;Ljava/util/List;)Ljava/util/List;", "", "indexOfResumeWith", "", "actualTrace", "Lkotlin/Pair;", "findContinuationStartIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "frameIndex", "findIndexOfFrame", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "Lkotlin/coroutines/Continuation;", "frame", "updateState", "(Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateRunningState", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Ljava/lang/String;)V", "realCaller", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", BankCardConstants.KEY_OWNER, "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "(Lkotlin/coroutines/Continuation;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "toStackTraceFrame", "(Ljava/util/List;)Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "T", "completion", "createOwner", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/StackTraceFrame;)Lkotlin/coroutines/Continuation;", "probeCoroutineCompleted", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)V", "", "throwable", "sanitizeStackTrace", "(Ljava/lang/Throwable;)Ljava/util/List;", "install", "uninstall", "job", "hierarchyToString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfo", "()Ljava/util/List;", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "dumpDebuggerInfo", "dumpCoroutines", "info", "enhanceStackTraceWithThreadDump", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;Ljava/util/List;)Ljava/util/List;", "probeCoroutineResumed$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)V", "probeCoroutineResumed", "probeCoroutineSuspended$kotlinx_coroutines_core", "probeCoroutineSuspended", "probeCoroutineCreated$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "probeCoroutineCreated", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "coroutineStateLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "", "getCapturedCoroutines", "()Ljava/util/Set;", "capturedCoroutines", "dynamicAttach", "Lkotlin/jvm/functions/Function1;", "installations", "I", "getDebugString", "debugString$annotations", "(Lkotlinx/coroutines/Job;)V", "debugString", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "callerInfoCache", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "enableCreationStackTraces", "Z", "getEnableCreationStackTraces", "()Z", "setEnableCreationStackTraces", "(Z)V", "Ljava/text/SimpleDateFormat;", "dateFormat", "Ljava/text/SimpleDateFormat;", "weakRefCleanerThread", "Ljava/lang/Thread;", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "isInstalled$kotlinx_coroutines_core", "isInstalled", "sanitizeStackTraces", "getSanitizeStackTraces", "setSanitizeStackTraces", "capturedCoroutinesMap", "ARTIFICIAL_FRAME_MESSAGE", "Ljava/lang/String;", "<init>", "CoroutineOwner", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DebugProbesImpl {
    private static final String ARTIFICIAL_FRAME_MESSAGE = "Coroutine creation stacktrace";
    public static final DebugProbesImpl INSTANCE;
    private static final ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfo> callerInfoCache;
    private static final ConcurrentWeakMap<CoroutineOwner<?>, Boolean> capturedCoroutinesMap;
    private static final ReentrantReadWriteLock coroutineStateLock;
    private static final SimpleDateFormat dateFormat;
    static final /* synthetic */ DebugProbesImplSequenceNumberRefVolatile debugProbesImplSequenceNumberRefVolatile;
    private static final Function1<Boolean, Unit> dynamicAttach;
    private static boolean enableCreationStackTraces;
    private static volatile int installations;
    private static boolean sanitizeStackTraces;
    static final /* synthetic */ AtomicLongFieldUpdater sequenceNumber$FU;
    private static Thread weakRefCleanerThread;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0003B'\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J \u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001c\u001a\u00020\u00198\u0016@\u0016X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lkotlin/Result;", "result", "", "resumeWith", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "delegate", "Lkotlin/coroutines/Continuation;", "frame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "<init>", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {
        @JvmField
        @NotNull
        public final Continuation<T> delegate;
        private final CoroutineStackFrame frame;
        @JvmField
        @NotNull
        public final DebugCoroutineInfo info;

        /* JADX WARN: Multi-variable type inference failed */
        public CoroutineOwner(@NotNull Continuation<? super T> continuation, @NotNull DebugCoroutineInfo debugCoroutineInfo, @Nullable CoroutineStackFrame coroutineStackFrame) {
            this.delegate = continuation;
            this.info = debugCoroutineInfo;
            this.frame = coroutineStackFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public CoroutineStackFrame getCallerFrame() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.getCallerFrame();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        @NotNull
        public CoroutineContext getContext() {
            return this.delegate.getContext();
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public StackTraceElement getStackTraceElement() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.getStackTraceElement();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(@NotNull Object result) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(result);
        }

        @NotNull
        public String toString() {
            return this.delegate.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.debug.internal.DebugProbesImplSequenceNumberRefVolatile] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        INSTANCE = debugProbesImpl;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        capturedCoroutinesMap = new ConcurrentWeakMap<>(false, 1, null);
        debugProbesImplSequenceNumberRefVolatile = new Object(0L) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImplSequenceNumberRefVolatile
            volatile long sequenceNumber;

            {
                this.sequenceNumber = r1;
            }
        };
        coroutineStateLock = new ReentrantReadWriteLock();
        sanitizeStackTraces = true;
        enableCreationStackTraces = true;
        dynamicAttach = debugProbesImpl.getDynamicAttach();
        callerInfoCache = new ConcurrentWeakMap<>(true);
        sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(DebugProbesImplSequenceNumberRefVolatile.class, "sequenceNumber");
    }

    private DebugProbesImpl() {
    }

    public static final /* synthetic */ ReentrantReadWriteLock access$getCoroutineStateLock$p(DebugProbesImpl debugProbesImpl) {
        return coroutineStateLock;
    }

    private final void build(@NotNull Job job, Map<Job, DebugCoroutineInfo> map, StringBuilder sb, String str) {
        DebugCoroutineInfo debugCoroutineInfo = map.get(job);
        if (debugCoroutineInfo == null) {
            if (!(job instanceof ScopeCoroutine)) {
                sb.append(str + getDebugString(job) + '\n');
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("\t");
                str = sb2.toString();
            }
        } else {
            sb.append(str + getDebugString(job) + ", continuation is " + debugCoroutineInfo.get_state() + " at line " + ((StackTraceElement) CollectionsKt.firstOrNull((List) debugCoroutineInfo.lastObservedStackTrace())) + '\n');
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("\t");
            str = sb3.toString();
        }
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            build(it.next(), map, sb, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> Continuation<T> createOwner(Continuation<? super T> completion, StackTraceFrame frame) {
        if (isInstalled$kotlinx_coroutines_core()) {
            CoroutineOwner<?> coroutineOwner = new CoroutineOwner<>(completion, new DebugCoroutineInfo(completion.getContext(), frame, sequenceNumber$FU.incrementAndGet(debugProbesImplSequenceNumberRefVolatile)), frame);
            ConcurrentWeakMap<CoroutineOwner<?>, Boolean> concurrentWeakMap = capturedCoroutinesMap;
            concurrentWeakMap.put(coroutineOwner, Boolean.TRUE);
            if (!isInstalled$kotlinx_coroutines_core()) {
                concurrentWeakMap.clear();
            }
            return coroutineOwner;
        }
        return completion;
    }

    private static /* synthetic */ void debugString$annotations(Job job) {
    }

    private final <R> List<R> dumpCoroutinesInfoImpl(Function2<? super CoroutineOwner<?>, ? super CoroutineContext, ? extends R> create) {
        List<CoroutineOwner> sortedWith;
        ReentrantReadWriteLock access$getCoroutineStateLock$p = access$getCoroutineStateLock$p(this);
        ReentrantReadWriteLock.ReadLock readLock = access$getCoroutineStateLock$p.readLock();
        int i2 = 0;
        int readHoldCount = access$getCoroutineStateLock$p.getWriteHoldCount() == 0 ? access$getCoroutineStateLock$p.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = access$getCoroutineStateLock$p.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                sortedWith = CollectionsKt___CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                        return compareValues;
                    }
                });
                ArrayList arrayList = new ArrayList();
                for (CoroutineOwner coroutineOwner : sortedWith) {
                    CoroutineContext context = coroutineOwner.info.getContext();
                    R invoke = context != null ? create.invoke(coroutineOwner, context) : null;
                    if (invoke != null) {
                        arrayList.add(invoke);
                    }
                }
                return arrayList;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            InlineMarker.finallyStart(1);
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    private final void dumpCoroutinesSynchronized(PrintStream out) {
        List<CoroutineOwner> sortedWith;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                out.print("Coroutines dump " + dateFormat.format(Long.valueOf(System.currentTimeMillis())));
                sortedWith = CollectionsKt___CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$4
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                        return compareValues;
                    }
                });
                for (CoroutineOwner coroutineOwner : sortedWith) {
                    DebugCoroutineInfo debugCoroutineInfo = coroutineOwner.info;
                    List<StackTraceElement> lastObservedStackTrace = debugCoroutineInfo.lastObservedStackTrace();
                    DebugProbesImpl debugProbesImpl2 = INSTANCE;
                    List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl = debugProbesImpl2.enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfo.get_state(), debugCoroutineInfo.lastObservedThread, lastObservedStackTrace);
                    out.print("\n\nCoroutine " + coroutineOwner.delegate + ", state: " + ((Intrinsics.areEqual(debugCoroutineInfo.get_state(), DebugCoroutineInfoImplKt.RUNNING) && enhanceStackTraceWithThreadDumpImpl == lastObservedStackTrace) ? debugCoroutineInfo.get_state() + " (Last suspension stacktrace, not an actual stacktrace)" : debugCoroutineInfo.get_state()));
                    if (lastObservedStackTrace.isEmpty()) {
                        out.print("\n\tat " + StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
                        debugProbesImpl2.printStackTrace(out, debugCoroutineInfo.getCreationStackTrace());
                    } else {
                        debugProbesImpl2.printStackTrace(out, enhanceStackTraceWithThreadDumpImpl);
                    }
                }
                Unit unit = Unit.INSTANCE;
                return;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    private final List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl(String r8, Thread thread, List<StackTraceElement> coroutineTrace) {
        Object m200constructorimpl;
        if ((!Intrinsics.areEqual(r8, DebugCoroutineInfoImplKt.RUNNING)) == false && thread != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m200constructorimpl = Result.m200constructorimpl(thread.getStackTrace());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m206isFailureimpl(m200constructorimpl)) {
                m200constructorimpl = null;
            }
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) m200constructorimpl;
            if (stackTraceElementArr != null) {
                int length = stackTraceElementArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        i2 = -1;
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (Intrinsics.areEqual(stackTraceElement.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(stackTraceElement.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement.getFileName(), "ContinuationImpl.kt")) {
                        break;
                    }
                    i2++;
                }
                Pair<Integer, Boolean> findContinuationStartIndex = findContinuationStartIndex(i2, stackTraceElementArr, coroutineTrace);
                int intValue = findContinuationStartIndex.component1().intValue();
                boolean booleanValue = findContinuationStartIndex.component2().booleanValue();
                if (intValue == -1) {
                    return coroutineTrace;
                }
                ArrayList arrayList = new ArrayList((((coroutineTrace.size() + i2) - intValue) - 1) - (booleanValue ? 1 : 0));
                int i3 = i2 - (booleanValue ? 1 : 0);
                for (int i4 = 0; i4 < i3; i4++) {
                    arrayList.add(stackTraceElementArr[i4]);
                }
                int size = coroutineTrace.size();
                for (int i5 = intValue + 1; i5 < size; i5++) {
                    arrayList.add(coroutineTrace.get(i5));
                }
                return arrayList;
            }
        }
        return coroutineTrace;
    }

    private final Pair<Integer, Boolean> findContinuationStartIndex(int indexOfResumeWith, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        int findIndexOfFrame = findIndexOfFrame(indexOfResumeWith - 1, actualTrace, coroutineTrace);
        if (findIndexOfFrame == -1) {
            return TuplesKt.to(Integer.valueOf(findIndexOfFrame(indexOfResumeWith - 2, actualTrace, coroutineTrace)), Boolean.TRUE);
        }
        return TuplesKt.to(Integer.valueOf(findIndexOfFrame), Boolean.FALSE);
    }

    private final int findIndexOfFrame(int frameIndex, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.getOrNull(actualTrace, frameIndex);
        if (stackTraceElement != null) {
            int i2 = 0;
            for (StackTraceElement stackTraceElement2 : coroutineTrace) {
                if (Intrinsics.areEqual(stackTraceElement2.getFileName(), stackTraceElement.getFileName()) && Intrinsics.areEqual(stackTraceElement2.getClassName(), stackTraceElement.getClassName()) && Intrinsics.areEqual(stackTraceElement2.getMethodName(), stackTraceElement.getMethodName())) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        return -1;
    }

    public final Set<CoroutineOwner<?>> getCapturedCoroutines() {
        return capturedCoroutinesMap.keySet();
    }

    private final String getDebugString(@NotNull Job job) {
        return job instanceof JobSupport ? ((JobSupport) job).toDebugString() : job.toString();
    }

    private final Function1<Boolean, Unit> getDynamicAttach() {
        Object m200constructorimpl;
        Object newInstance;
        try {
            Result.Companion companion = Result.INSTANCE;
            newInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th));
        }
        if (newInstance != null) {
            m200constructorimpl = Result.m200constructorimpl((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1));
            if (Result.m206isFailureimpl(m200constructorimpl)) {
                m200constructorimpl = null;
            }
            return (Function1) m200constructorimpl;
        }
        throw new TypeCastException("null cannot be cast to non-null type (kotlin.Boolean) -> kotlin.Unit");
    }

    private final boolean isInternalMethod(@NotNull StackTraceElement stackTraceElement) {
        boolean startsWith$default;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(stackTraceElement.getClassName(), "kotlinx.coroutines", false, 2, null);
        return startsWith$default;
    }

    private final CoroutineOwner<?> owner(@NotNull Continuation<?> continuation) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) continuation;
        if (coroutineStackFrame != null) {
            return owner(coroutineStackFrame);
        }
        return null;
    }

    private final void printStackTrace(PrintStream out, List<StackTraceElement> frames) {
        Iterator<T> it = frames.iterator();
        while (it.hasNext()) {
            out.print("\n\tat " + ((StackTraceElement) it.next()));
        }
    }

    public final void probeCoroutineCompleted(CoroutineOwner<?> r2) {
        CoroutineStackFrame realCaller;
        capturedCoroutinesMap.remove(r2);
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = r2.info.getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null || (realCaller = realCaller(lastObservedFrame$kotlinx_coroutines_core)) == null) {
            return;
        }
        callerInfoCache.remove(realCaller);
    }

    private final CoroutineStackFrame realCaller(@NotNull CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.getStackTraceElement() == null);
        return coroutineStackFrame;
    }

    private final <T extends Throwable> List<StackTraceElement> sanitizeStackTrace(T throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        int length2 = stackTrace.length - 1;
        while (true) {
            if (length2 < 0) {
                break;
            } else if (Intrinsics.areEqual(stackTrace[length2].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                i2 = length2;
                break;
            } else {
                length2--;
            }
        }
        int i3 = 0;
        if (!sanitizeStackTraces) {
            int i4 = length - i2;
            ArrayList arrayList = new ArrayList(i4);
            while (i3 < i4) {
                arrayList.add(i3 == 0 ? StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE) : stackTrace[i3 + i2]);
                i3++;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList((length - i2) + 1);
        arrayList2.add(StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
        int i5 = length - 1;
        boolean z = true;
        for (int i6 = i2 + 1; i6 < i5; i6++) {
            StackTraceElement stackTraceElement = stackTrace[i6];
            if (isInternalMethod(stackTraceElement)) {
                if (z) {
                    arrayList2.add(stackTraceElement);
                    z = false;
                } else if (!isInternalMethod(stackTrace[i6 + 1])) {
                    arrayList2.add(stackTraceElement);
                }
            } else {
                arrayList2.add(stackTraceElement);
            }
            z = true;
        }
        arrayList2.add(stackTrace[i5]);
        return arrayList2;
    }

    private final void startWeakRefCleanerThread() {
        Thread thread;
        thread = ThreadsKt.thread((r12 & 1) != 0, (r12 & 2) != 0 ? false : true, (r12 & 4) != 0 ? null : null, (r12 & 8) != 0 ? null : "Coroutines Debugger Cleaner", (r12 & 16) != 0 ? -1 : 0, new Function0<Unit>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$startWeakRefCleanerThread$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2() {
                ConcurrentWeakMap concurrentWeakMap;
                DebugProbesImpl debugProbesImpl = DebugProbesImpl.INSTANCE;
                concurrentWeakMap = DebugProbesImpl.callerInfoCache;
                concurrentWeakMap.runWeakRefQueueCleaningLoopUntilInterrupted();
            }
        });
        weakRefCleanerThread = thread;
    }

    private final void stopWeakRefCleanerThread() {
        Thread thread = weakRefCleanerThread;
        if (thread != null) {
            thread.interrupt();
        }
        weakRefCleanerThread = null;
    }

    private final StackTraceFrame toStackTraceFrame(@NotNull List<StackTraceElement> list) {
        StackTraceFrame stackTraceFrame = null;
        if (!list.isEmpty()) {
            ListIterator<StackTraceElement> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                stackTraceFrame = new StackTraceFrame(stackTraceFrame, listIterator.previous());
            }
        }
        return stackTraceFrame;
    }

    private final void updateRunningState(CoroutineStackFrame frame, String r7) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfo> concurrentWeakMap = callerInfoCache;
                DebugCoroutineInfo remove = concurrentWeakMap.remove(frame);
                if (remove == null) {
                    CoroutineOwner<?> owner = debugProbesImpl.owner(frame);
                    if (owner == null || (remove = owner.info) == null) {
                        return;
                    }
                    CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = remove.getLastObservedFrame$kotlinx_coroutines_core();
                    CoroutineStackFrame realCaller = lastObservedFrame$kotlinx_coroutines_core != null ? debugProbesImpl.realCaller(lastObservedFrame$kotlinx_coroutines_core) : null;
                    if (realCaller != null) {
                        concurrentWeakMap.remove(realCaller);
                    }
                }
                if (frame != null) {
                    remove.updateState$kotlinx_coroutines_core(r7, (Continuation) frame);
                    CoroutineStackFrame realCaller2 = debugProbesImpl.realCaller(frame);
                    if (realCaller2 != null) {
                        concurrentWeakMap.put(realCaller2, remove);
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.Continuation<*>");
            }
        } finally {
            readLock.unlock();
        }
    }

    private final void updateState(Continuation<?> frame, String r6) {
        if (isInstalled$kotlinx_coroutines_core()) {
            if (Intrinsics.areEqual(r6, DebugCoroutineInfoImplKt.RUNNING) && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
                if (!(frame instanceof CoroutineStackFrame)) {
                    frame = null;
                }
                CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) frame;
                if (coroutineStackFrame != null) {
                    updateRunningState(coroutineStackFrame, r6);
                    return;
                }
                return;
            }
            CoroutineOwner<?> owner = owner(frame);
            if (owner != null) {
                updateState(owner, frame, r6);
            }
        }
    }

    public final void dumpCoroutines(@NotNull PrintStream out) {
        synchronized (out) {
            INSTANCE.dumpCoroutinesSynchronized(out);
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        List<CoroutineOwner> sortedWith;
        ReentrantReadWriteLock access$getCoroutineStateLock$p = access$getCoroutineStateLock$p(this);
        ReentrantReadWriteLock.ReadLock readLock = access$getCoroutineStateLock$p.readLock();
        int i2 = 0;
        int readHoldCount = access$getCoroutineStateLock$p.getWriteHoldCount() == 0 ? access$getCoroutineStateLock$p.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = access$getCoroutineStateLock$p.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                sortedWith = CollectionsKt___CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$2
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                        return compareValues;
                    }
                });
                ArrayList arrayList = new ArrayList();
                for (CoroutineOwner coroutineOwner : sortedWith) {
                    CoroutineContext context = coroutineOwner.info.getContext();
                    DebugCoroutineInfo debugCoroutineInfo = context != null ? new DebugCoroutineInfo(coroutineOwner.info, context) : null;
                    if (debugCoroutineInfo != null) {
                        arrayList.add(debugCoroutineInfo);
                    }
                }
                return arrayList;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final List<DebuggerInfo> dumpDebuggerInfo() {
        List<CoroutineOwner> sortedWith;
        ReentrantReadWriteLock access$getCoroutineStateLock$p = access$getCoroutineStateLock$p(this);
        ReentrantReadWriteLock.ReadLock readLock = access$getCoroutineStateLock$p.readLock();
        int i2 = 0;
        int readHoldCount = access$getCoroutineStateLock$p.getWriteHoldCount() == 0 ? access$getCoroutineStateLock$p.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = access$getCoroutineStateLock$p.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                sortedWith = CollectionsKt___CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$3
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                        return compareValues;
                    }
                });
                ArrayList arrayList = new ArrayList();
                for (CoroutineOwner coroutineOwner : sortedWith) {
                    CoroutineContext context = coroutineOwner.info.getContext();
                    DebuggerInfo debuggerInfo = context != null ? new DebuggerInfo(coroutineOwner.info, context) : null;
                    if (debuggerInfo != null) {
                        arrayList.add(debuggerInfo);
                    }
                }
                return arrayList;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final List<StackTraceElement> enhanceStackTraceWithThreadDump(@NotNull DebugCoroutineInfo info, @NotNull List<StackTraceElement> coroutineTrace) {
        return enhanceStackTraceWithThreadDumpImpl(info.getState(), info.getLastObservedThread(), coroutineTrace);
    }

    public final boolean getEnableCreationStackTraces() {
        return enableCreationStackTraces;
    }

    public final boolean getSanitizeStackTraces() {
        return sanitizeStackTraces;
    }

    @NotNull
    public final String hierarchyToString(@NotNull Job job) {
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                Set<CoroutineOwner<?>> capturedCoroutines = debugProbesImpl.getCapturedCoroutines();
                ArrayList arrayList = new ArrayList();
                for (Object obj : capturedCoroutines) {
                    if (((CoroutineOwner) obj).delegate.getContext().get(Job.INSTANCE) != null) {
                        arrayList.add(obj);
                    }
                }
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
                LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
                for (Object obj2 : arrayList) {
                    CoroutineContext.Element element = ((CoroutineOwner) obj2).delegate.getContext().get(Job.INSTANCE);
                    if (element == null) {
                        Intrinsics.throwNpe();
                    }
                    linkedHashMap.put((Job) element, ((CoroutineOwner) obj2).info);
                }
                StringBuilder sb = new StringBuilder();
                INSTANCE.build(job, linkedHashMap, sb, "");
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    public final void install() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            installations++;
            if (installations > 1) {
                return;
            }
            INSTANCE.startWeakRefCleanerThread();
            if (AgentPremain.INSTANCE.isInstalledStatically()) {
                while (i2 < readHoldCount) {
                    readLock.lock();
                    i2++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(Boolean.TRUE);
            }
            Unit unit = Unit.INSTANCE;
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    public final boolean isInstalled$kotlinx_coroutines_core() {
        return installations > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(@NotNull Continuation<? super T> completion) {
        if (isInstalled$kotlinx_coroutines_core() && owner(completion) == null) {
            return createOwner(completion, enableCreationStackTraces ? toStackTraceFrame(sanitizeStackTrace(new Exception())) : null);
        }
        return completion;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(@NotNull Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoImplKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(@NotNull Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoImplKt.SUSPENDED);
    }

    public final void setEnableCreationStackTraces(boolean z) {
        enableCreationStackTraces = z;
    }

    public final void setSanitizeStackTraces(boolean z) {
        sanitizeStackTraces = z;
    }

    public final void uninstall() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                installations--;
                if (installations != 0) {
                    return;
                }
                debugProbesImpl.stopWeakRefCleanerThread();
                capturedCoroutinesMap.clear();
                callerInfoCache.clear();
                if (AgentPremain.INSTANCE.isInstalledStatically()) {
                    while (i2 < readHoldCount) {
                        readLock.lock();
                        i2++;
                    }
                    writeLock.unlock();
                    return;
                }
                Function1<Boolean, Unit> function1 = dynamicAttach;
                if (function1 != null) {
                    function1.invoke(Boolean.FALSE);
                }
                Unit unit = Unit.INSTANCE;
                while (i2 < readHoldCount) {
                    readLock.lock();
                    i2++;
                }
                writeLock.unlock();
                return;
            }
            throw new IllegalStateException("Agent was not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
        }
    }

    private final CoroutineOwner<?> owner(@NotNull CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof CoroutineOwner)) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        }
        return (CoroutineOwner) coroutineStackFrame;
    }

    private final void updateState(CoroutineOwner<?> r3, Continuation<?> frame, String r5) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                r3.info.updateState$kotlinx_coroutines_core(r5, frame);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }
}
