package kotlinx.coroutines;

import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import jpbury.t;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00dc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006\u00d4\u0001\u00d5\u0001\u00d6\u0001B\u0012\u0012\u0007\u0010\u00d1\u0001\u001a\u00020\u001b\u00a2\u0006\u0006\b\u00d2\u0001\u0010\u00d3\u0001J&\u0010\n\u001a\u00020\t2\u0014\u0010\b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0082\b\u00a2\u0006\u0004\b\n\u0010\u000bJ#\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ!\u0010\u001e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010#\u001a\u00020\u00072\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u00020\u0007*\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0004\b'\u0010$J2\u0010*\u001a\u00020\u0007\"\u000e\b\u0000\u0010)\u0018\u0001*\u0006\u0012\u0002\b\u00030(2\u0006\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0082\b\u00a2\u0006\u0004\b*\u0010$J\u0019\u0010,\u001a\u00020+2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b,\u0010-JD\u00103\u001a\u0006\u0012\u0002\b\u00030(2'\u00101\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`02\u0006\u00102\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b3\u00104J+\u00107\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u00062\u0006\u0010!\u001a\u00020 2\n\u00106\u001a\u0006\u0012\u0002\b\u00030(H\u0002\u00a2\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u00020\u00072\u0006\u0010\r\u001a\u000209H\u0002\u00a2\u0006\u0004\b:\u0010;J\u001b\u0010<\u001a\u00020\u00072\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030(H\u0002\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0019\u0010B\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u001b\u0010D\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bD\u0010AJ\u0019\u0010E\u001a\u0004\u0018\u00010 2\u0006\u0010\r\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\bE\u0010FJ\u001f\u0010G\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bG\u0010HJ%\u0010I\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bI\u0010JJ#\u0010K\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bK\u0010LJ\u0019\u0010N\u001a\u0004\u0018\u00010M2\u0006\u0010\r\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\bN\u0010OJ*\u0010Q\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010P\u001a\u00020M2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0082\u0010\u00a2\u0006\u0004\bQ\u0010RJ)\u0010T\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\u0006\u0010S\u001a\u00020M2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bT\u0010UJ\u0015\u0010W\u001a\u0004\u0018\u00010M*\u00020VH\u0002\u00a2\u0006\u0004\bW\u0010XJ\u0019\u0010Z\u001a\u00020Y2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\bZ\u0010[J\u0019\u0010_\u001a\u00020\u00072\b\u0010\\\u001a\u0004\u0018\u00010\u0001H\u0000\u00a2\u0006\u0004\b]\u0010^J\r\u0010`\u001a\u00020\u001b\u00a2\u0006\u0004\b`\u0010?J\u000f\u0010c\u001a\u00020\u0007H\u0010\u00a2\u0006\u0004\ba\u0010bJ\u0011\u0010f\u001a\u00060dj\u0002`e\u00a2\u0006\u0004\bf\u0010gJ#\u0010i\u001a\u00060dj\u0002`e*\u00020\u00122\n\b\u0002\u0010h\u001a\u0004\u0018\u00010YH\u0004\u00a2\u0006\u0004\bi\u0010jJ6\u0010l\u001a\u00020k2'\u00101\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`0\u00a2\u0006\u0004\bl\u0010mJF\u0010l\u001a\u00020k2\u0006\u00102\u001a\u00020\u001b2\u0006\u0010n\u001a\u00020\u001b2'\u00101\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`0\u00a2\u0006\u0004\bl\u0010oJ\u0013\u0010p\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\bp\u0010qJ\u0013\u0010r\u001a\u00020\u0007H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\br\u0010qJB\u0010w\u001a\u00020\u0007\"\u0004\b\u0000\u0010s2\f\u0010u\u001a\b\u0012\u0004\u0012\u00028\u00000t2\u001c\u0010\b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000v\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00f8\u0001\u0000\u00a2\u0006\u0004\bw\u0010xJ\u001b\u0010z\u001a\u00020\u00072\n\u00106\u001a\u0006\u0012\u0002\b\u00030(H\u0000\u00a2\u0006\u0004\by\u0010=J\u001f\u0010{\u001a\u00020\u00072\u000e\u0010\"\u001a\n\u0018\u00010dj\u0004\u0018\u0001`eH\u0016\u00a2\u0006\u0004\b{\u0010|J\u000f\u0010}\u001a\u00020YH\u0014\u00a2\u0006\u0004\b}\u0010~J\u0019\u0010{\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0017\u00a2\u0006\u0004\b{\u0010&J\u0018\u0010\u007f\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0012H\u0016\u00a2\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0019\u0010\u0082\u0001\u001a\u00020\u00072\u0007\u0010\u0081\u0001\u001a\u00020\u0003\u00a2\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J\u0019\u0010\u0084\u0001\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0012H\u0016\u00a2\u0006\u0005\b\u0084\u0001\u0010&J\u0019\u0010\u0085\u0001\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0005\b\u0085\u0001\u0010&J\u001c\u0010\u0088\u0001\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u0000\u00a2\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J,\u0010\u008c\u0001\u001a\u00030\u0089\u00012\n\b\u0002\u0010h\u001a\u0004\u0018\u00010Y2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0080\b\u00a2\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u0015\u0010\u008d\u0001\u001a\u00060dj\u0002`eH\u0016\u00a2\u0006\u0005\b\u008d\u0001\u0010gJ\u001c\u0010\u008f\u0001\u001a\u00020\u001b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0000\u00a2\u0006\u0006\b\u008e\u0001\u0010\u0087\u0001J\u001d\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0000\u00a2\u0006\u0005\b\u0090\u0001\u0010AJ\u0019\u0010\u0093\u0001\u001a\u00030\u0092\u00012\u0006\u0010P\u001a\u00020\u0002\u00a2\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u001b\u0010\u0097\u0001\u001a\u00020\u00072\u0007\u0010\u0095\u0001\u001a\u00020\u0012H\u0010\u00a2\u0006\u0006\b\u0096\u0001\u0010\u0080\u0001J\u001a\u00102\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0014\u00a2\u0006\u0005\b2\u0010\u0080\u0001J\u001a\u0010\u0098\u0001\u001a\u00020\u001b2\u0007\u0010\u0095\u0001\u001a\u00020\u0012H\u0014\u00a2\u0006\u0005\b\u0098\u0001\u0010&J\u001c\u0010\u0099\u0001\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0014\u00a2\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u001c\u0010\u009b\u0001\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0014\u00a2\u0006\u0006\b\u009b\u0001\u0010\u009a\u0001J\u0011\u0010\u009c\u0001\u001a\u00020YH\u0016\u00a2\u0006\u0005\b\u009c\u0001\u0010~J\u0011\u0010\u009d\u0001\u001a\u00020YH\u0007\u00a2\u0006\u0005\b\u009d\u0001\u0010~J\u0011\u0010\u009f\u0001\u001a\u00020YH\u0010\u00a2\u0006\u0005\b\u009e\u0001\u0010~J\u0012\u0010\u00a0\u0001\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0006\b\u00a0\u0001\u0010\u00a1\u0001J\u0014\u0010\u00a4\u0001\u001a\u0004\u0018\u00010\u0006H\u0000\u00a2\u0006\u0006\b\u00a2\u0001\u0010\u00a3\u0001J\u0017\u0010\u00a6\u0001\u001a\u0004\u0018\u00010\u0006H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0005\b\u00a5\u0001\u0010qJ\u0017\u0010\u00a7\u0001\u001a\u0004\u0018\u00010\u0006H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0005\b\u00a7\u0001\u0010qJT\u0010\u00ab\u0001\u001a\u00020\u0007\"\u0004\b\u0000\u0010)\"\u0004\b\u0001\u0010s2\f\u0010u\u001a\b\u0012\u0004\u0012\u00028\u00010t2#\u0010\b\u001a\u001f\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010v\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00a8\u0001H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0006\b\u00a9\u0001\u0010\u00aa\u0001JT\u0010\u00ad\u0001\u001a\u00020\u0007\"\u0004\b\u0000\u0010)\"\u0004\b\u0001\u0010s2\f\u0010u\u001a\b\u0012\u0004\u0012\u00028\u00010t2#\u0010\b\u001a\u001f\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010v\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00a8\u0001H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0006\b\u00ac\u0001\u0010\u00aa\u0001R0\u0010\u00b3\u0001\u001a\u0005\u0018\u00010\u0092\u00012\n\u0010\u00ae\u0001\u001a\u0005\u0018\u00010\u0092\u00018@@@X\u0080\u000e\u00a2\u0006\u0010\u001a\u0006\b\u00af\u0001\u0010\u00b0\u0001\"\u0006\b\u00b1\u0001\u0010\u00b2\u0001R\u0015\u0010\u00b4\u0001\u001a\u00020\u001b8F@\u0006\u00a2\u0006\u0007\u001a\u0005\b\u00b4\u0001\u0010?R\u001b\u0010\u00b6\u0001\u001a\u0004\u0018\u00010\u00128D@\u0004X\u0084\u0004\u00a2\u0006\b\u001a\u0006\b\u00b5\u0001\u0010\u00a1\u0001R\u0015\u0010\u00b7\u0001\u001a\u00020\u001b8F@\u0006\u00a2\u0006\u0007\u001a\u0005\b\u00b7\u0001\u0010?R\u0018\u0010\u00b8\u0001\u001a\u00020\u001b8V@\u0016X\u0096\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00b8\u0001\u0010?R\u0018\u0010\u00ba\u0001\u001a\u00020\u001b8P@\u0010X\u0090\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00b9\u0001\u0010?R\u0016\u0010\u00bd\u0001\u001a\u00020\u00048F@\u0006\u00a2\u0006\b\u001a\u0006\b\u00bb\u0001\u0010\u00bc\u0001R\u0018\u0010\u00be\u0001\u001a\u00020\u001b8T@\u0014X\u0094\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00be\u0001\u0010?R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00068@@\u0000X\u0080\u0004\u00a2\u0006\b\u001a\u0006\b\u00bf\u0001\u0010\u00a3\u0001R\u0015\u0010\u00c0\u0001\u001a\u00020\u001b8F@\u0006\u00a2\u0006\u0007\u001a\u0005\b\u00c0\u0001\u0010?R\u0018\u0010\u00c2\u0001\u001a\u00020\u001b8P@\u0010X\u0090\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00c1\u0001\u0010?R\u001d\u0010\u00c6\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010\u00c3\u00018F@\u0006\u00a2\u0006\b\u001a\u0006\b\u00c4\u0001\u0010\u00c5\u0001R\u0018\u0010\u00c8\u0001\u001a\u00020\u001b8D@\u0004X\u0084\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00c7\u0001\u0010?R \u0010\u00ca\u0001\u001a\u0004\u0018\u00010\u0012*\u0004\u0018\u00010\u00068B@\u0002X\u0082\u0004\u00a2\u0006\u0007\u001a\u0005\b\u00c9\u0001\u0010CR\u001b\u0010\u00ce\u0001\u001a\u0007\u0012\u0002\b\u00030\u00cb\u00018F@\u0006\u00a2\u0006\b\u001a\u0006\b\u00cc\u0001\u0010\u00cd\u0001R\u001d\u0010\u00cf\u0001\u001a\u00020\u001b*\u00020\u00198B@\u0002X\u0082\u0004\u00a2\u0006\b\u001a\u0006\b\u00cf\u0001\u0010\u00d0\u0001\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00d7\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/selects/SelectClause0;", "Lkotlin/Function1;", "", "", JDReactConstant.BLOCK, "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "Lkotlinx/coroutines/JobSupport$Finishing;", XView2Constants.STATE, "proposedUpdate", "finalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;)Ljava/lang/Object;", "", "", "exceptions", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "rootCause", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "Lkotlinx/coroutines/Incomplete;", "update", "", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Z", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)V", "Lkotlinx/coroutines/NodeList;", ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, "cause", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", "cancelParent", "(Ljava/lang/Throwable;)Z", "notifyCompletion", "Lkotlinx/coroutines/JobNode;", "T", "notifyHandlers", "", "startInternal", "(Ljava/lang/Object;)I", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "onCancelling", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "expect", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "joinInternal", "()Z", "cancelMakeCompleting", "(Ljava/lang/Object;)Ljava/lang/Object;", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "makeCancelling", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/ChildHandleNode;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "child", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "lastChild", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "parent", "initParentJobInternal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/Job;)V", "initParentJobInternal", "start", "onStartInternal$kotlinx_coroutines_core", "()V", "onStartInternal", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "message", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "invokeImmediately", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinSuspend", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "removeNode$kotlinx_coroutines_core", "removeNode", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "cancellationExceptionMessage", "()Ljava/lang/String;", "cancelInternal", "(Ljava/lang/Throwable;)V", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "childCancelled", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException$kotlinx_coroutines_core", "(Ljava/lang/String;Ljava/lang/Throwable;)Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException", "getChildJobCancellationCause", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "makeCompletingOnce", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", t.f20145j, "handleOnCompletionException$kotlinx_coroutines_core", "handleOnCompletionException", "handleJobException", "onCompletionInternal", "(Ljava/lang/Object;)V", "afterCompletion", "toString", "toDebugString", "nameString$kotlinx_coroutines_core", "nameString", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "awaitInternal$kotlinx_coroutines_core", "awaitInternal", "awaitSuspend", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "value", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parentHandle", "isCompleted", "getCompletionCause", "completionCause", "isCompletedExceptionally", "isActive", "getHandlesException$kotlinx_coroutines_core", "handlesException", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "isScopedCoroutine", "getState$kotlinx_coroutines_core", "isCancelled", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCauseHandled", "completionCauseHandled", "getExceptionOrNull", "exceptionOrNull", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "key", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", AppStateModule.APP_STATE_ACTIVE, "<init>", "(Z)V", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    private volatile Object _parentHandle;
    private volatile Object _state;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001d\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlinx/coroutines/Job;", "parent", "", "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "", "nameString", "()Ljava/lang/String;", "Lkotlinx/coroutines/JobSupport;", "job", "Lkotlinx/coroutines/JobSupport;", "Lkotlin/coroutines/Continuation;", "delegate", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        public AwaitContinuation(@NotNull Continuation<? super T> continuation, @NotNull JobSupport jobSupport) {
            super(continuation, 1);
            this.job = jobSupport;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        @NotNull
        public Throwable getContinuationCancellationCause(@NotNull Job parent) {
            Throwable rootCause;
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            return (!(state$kotlinx_coroutines_core instanceof Finishing[cancelling=) || (rootCause = ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause()) == null) ? state$kotlinx_coroutines_core instanceof CompletedExceptionally ? ((CompletedExceptionally) state$kotlinx_coroutines_core).cause : parent.getCancellationException() : rootCause;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        @NotNull
        protected String nameString() {
            return "AwaitContinuation";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "", "cause", "", "invoke", "(Ljava/lang/Throwable;)V", "", "toString", "()Ljava/lang/String;", "", "proposedUpdate", "Ljava/lang/Object;", "Lkotlinx/coroutines/ChildHandleNode;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "Lkotlinx/coroutines/JobSupport$Finishing;", XView2Constants.STATE, "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/JobSupport;", "parent", "Lkotlinx/coroutines/JobSupport;", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.JobSupport$ChildCompletion  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class ChildCompletion[ extends JobNode<Job> {

        /* renamed from: child  reason: from kotlin metadata and from toString */
        private final ChildHandle[ ChildCompletion[;
        private final JobSupport parent;

        /* renamed from: proposedUpdate  reason: from kotlin metadata and from toString */
        private final Object ;
        private final Finishing[cancelling= state;

        public ChildCompletion[(@NotNull JobSupport jobSupport, @NotNull Finishing[cancelling= r3, @NotNull ChildHandle[ r4, @Nullable Object obj) {
            super(r4.ChildHandle[);
            this.parent = jobSupport;
            this.state = r3;
            this.ChildCompletion[ = r4;
            this. = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ChildCompletion[" + this.ChildCompletion[ + ", " + this. + ']';
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke */
        public void invoke2(@Nullable Throwable cause) {
            this.parent.continueCompleting(this.state, this.ChildCompletion[, this.);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B!\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\"\u001a\u00020\u0014\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b+\u0010,J\u001f\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0015\u001a\u00020\u00148F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R(\u0010\u001b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0010R\u001c\u0010\u001d\u001a\u00020\u001c8\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u00020\u00148F@\u0006\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u0016R$\u0010\"\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00148F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010$R(\u0010)\u001a\u0004\u0018\u00010\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0016\u0010*\u001a\u00020\u00148V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u0016\u00a8\u0006-"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "allocateList", "()Ljava/util/ArrayList;", "proposedException", "", "sealLocked", "(Ljava/lang/Throwable;)Ljava/util/List;", t.f20145j, "", "addExceptionLocked", "(Ljava/lang/Throwable;)V", "", "toString", "()Ljava/lang/String;", "", "isSealed", "()Z", "value", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "rootCause", "Lkotlinx/coroutines/NodeList;", ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "isCancelling", "isCompleting", "setCompleting", "(Z)V", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "exceptionsHolder", "isActive", "<init>", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.JobSupport$Finishing  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class Finishing[cancelling= implements Incomplete {
        private volatile Object _exceptionsHolder = null;
        private volatile int _isCompleting;
        private volatile Object _rootCause;
        @NotNull
        private final NodeList list;

        public Finishing[cancelling=(@NotNull NodeList nodeList, boolean z, @Nullable Throwable th) {
            this.list = nodeList;
            this._isCompleting = z ? 1 : 0;
            this._rootCause = th;
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        /* renamed from: getExceptionsHolder  reason: from getter */
        private final Object get_exceptionsHolder() {
            return this._exceptionsHolder;
        }

        private final void setExceptionsHolder(Object obj) {
            this._exceptionsHolder = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void addExceptionLocked(@NotNull Throwable r3) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                setRootCause(r3);
            } else if (r3 == rootCause) {
            } else {
                Object obj = get_exceptionsHolder();
                if (obj == null) {
                    setExceptionsHolder(r3);
                } else if (obj instanceof Throwable) {
                    if (r3 == obj) {
                        return;
                    }
                    ArrayList<Throwable> allocateList = allocateList();
                    allocateList.add(obj);
                    allocateList.add(r3);
                    setExceptionsHolder(allocateList);
                } else if (obj instanceof ArrayList) {
                    ((ArrayList) obj).add(r3);
                } else {
                    throw new IllegalStateException(("State is " + obj).toString());
                }
            }
        }

        @Override // kotlinx.coroutines.Incomplete
        @NotNull
        public NodeList getList() {
            return this.list;
        }

        @Nullable
        public final Throwable getRootCause() {
            return (Throwable) this._rootCause;
        }

        @Override // kotlinx.coroutines.Incomplete
        /* renamed from: isActive */
        public boolean getIsActive() {
            return getRootCause() == null;
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [int, boolean] */
        public final boolean isCompleting() {
            return this._isCompleting;
        }

        public final boolean isSealed() {
            Symbol symbol;
            Object obj = get_exceptionsHolder();
            symbol = JobSupportKt.SEALED;
            return obj == symbol;
        }

        @NotNull
        public final List<Throwable> sealLocked(@Nullable Throwable proposedException) {
            ArrayList<Throwable> arrayList;
            Symbol symbol;
            Object obj = get_exceptionsHolder();
            if (obj == null) {
                arrayList = allocateList();
            } else if (obj instanceof Throwable) {
                ArrayList<Throwable> allocateList = allocateList();
                allocateList.add(obj);
                arrayList = allocateList;
            } else if (!(obj instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + obj).toString());
            } else {
                arrayList = (ArrayList) obj;
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (proposedException != null && (!Intrinsics.areEqual(proposedException, rootCause)) != false) {
                arrayList.add(proposedException);
            }
            symbol = JobSupportKt.SEALED;
            setExceptionsHolder(symbol);
            return arrayList;
        }

        public final void setCompleting(boolean z) {
            this._isCompleting = z ? 1 : 0;
        }

        public final void setRootCause(@Nullable Throwable th) {
            this._rootCause = th;
        }

        @NotNull
        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + get_exceptionsHolder() + ", list=" + getList() + ']';
        }
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
        this._parentHandle = null;
    }

    private final boolean addLastAtomic(final Object expect, NodeList r4, final JobNode<?> node) {
        int tryCondAddNext;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(node) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            @Nullable
            public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
                if (this.getState$kotlinx_coroutines_core() == expect) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            tryCondAddNext = r4.getPrevNode().tryCondAddNext(node, r4, condAddOp);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    private final void addSuppressedExceptions(Throwable rootCause, List<? extends Throwable> exceptions) {
        if (exceptions.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(exceptions.size()));
        Throwable unwrapImpl = !DebugKt.getRECOVER_STACK_TRACES() ? rootCause : StackTraceRecoveryKt.unwrapImpl(rootCause);
        for (Throwable th : exceptions) {
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                th = StackTraceRecoveryKt.unwrapImpl(th);
            }
            if (th != rootCause && th != unwrapImpl && !(th instanceof CancellationException) && newSetFromMap.add(th)) {
                ExceptionsKt__ExceptionsKt.addSuppressed(rootCause, th);
            }
        }
    }

    private final Object cancelMakeCompleting(Object cause) {
        Symbol symbol;
        Object tryMakeCompleting;
        Symbol symbol2;
        do {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((state$kotlinx_coroutines_core instanceof Finishing[cancelling=) && ((Finishing[cancelling=) state$kotlinx_coroutines_core).isCompleting())) {
                symbol = JobSupportKt.COMPLETING_ALREADY;
                return symbol;
            }
            tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new CompletedExceptionally(createCauseException(cause), false, 2, null));
            symbol2 = JobSupportKt.COMPLETING_RETRY;
        } while (tryMakeCompleting == symbol2);
        return tryMakeCompleting;
    }

    private final boolean cancelParent(Throwable cause) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = cause instanceof CancellationException;
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        return (parentHandle$kotlinx_coroutines_core == null || parentHandle$kotlinx_coroutines_core == NonDisposableHandle.INSTANCE) ? z : parentHandle$kotlinx_coroutines_core.childCancelled(cause) || z;
    }

    private final void completeStateFinalization(Incomplete r4, Object update) {
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            parentHandle$kotlinx_coroutines_core.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
        if (!(update instanceof CompletedExceptionally)) {
            update = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) update;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        if (r4 instanceof JobNode) {
            try {
                ((JobNode) r4).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + r4 + " for " + this, th2));
                return;
            }
        }
        NodeList list = r4.getList();
        if (list != null) {
            notifyCompletion(list, th);
        }
    }

    public final void continueCompleting(Finishing[cancelling= r2, ChildHandle[ lastChild, Object proposedUpdate) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$kotlinx_coroutines_core() == r2)) {
                throw new AssertionError();
            }
        }
        ChildHandle[ nextChild = nextChild(lastChild);
        if (nextChild == null || !tryWaitForChild(r2, nextChild, proposedUpdate)) {
            afterCompletion(finalizeFinishingState(r2, proposedUpdate));
        }
    }

    private final Throwable createCauseException(Object cause) {
        if (cause != null ? cause instanceof Throwable : true) {
            return cause != null ? (Throwable) cause : new ; job=(cancellationExceptionMessage(), null, this);
        } else if (cause != null) {
            return ((ParentJob) cause).getChildJobCancellationCause();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        }
    }

    public static /* synthetic */ ; job= defaultCancellationException$kotlinx_coroutines_core$default(JobSupport jobSupport, String str, Throwable th, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            if ((i2 & 2) != 0) {
                th = null;
            }
            if (str == null) {
                str = jobSupport.cancellationExceptionMessage();
            }
            return new ; job=(str, th, jobSupport);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
    }

    private final Object finalizeFinishingState(Finishing[cancelling= r8, Object proposedUpdate) {
        boolean isCancelling;
        Throwable finalRootCause;
        boolean z = true;
        boolean z2 = false;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$kotlinx_coroutines_core() == r8)) {
                throw new AssertionError();
            }
        }
        if (!DebugKt.getASSERTIONS_ENABLED() || (!r8.isSealed()) == true) {
            if (!DebugKt.getASSERTIONS_ENABLED() || r8.isCompleting()) {
                DefaultConstructorMarker defaultConstructorMarker = null;
                CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(proposedUpdate instanceof CompletedExceptionally) ? null : proposedUpdate);
                Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
                synchronized (r8) {
                    isCancelling = r8.isCancelling();
                    List<Throwable> sealLocked = r8.sealLocked(th);
                    finalRootCause = getFinalRootCause(r8, sealLocked);
                    if (finalRootCause != null) {
                        addSuppressedExceptions(finalRootCause, sealLocked);
                    }
                }
                if (finalRootCause != null && finalRootCause != th) {
                    proposedUpdate = new CompletedExceptionally(finalRootCause, z2, 2, defaultConstructorMarker);
                }
                if (finalRootCause != null) {
                    if (!cancelParent(finalRootCause) && !handleJobException(finalRootCause)) {
                        z = false;
                    }
                    if (z) {
                        if (proposedUpdate == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                        }
                        ((CompletedExceptionally) proposedUpdate).makeHandled();
                    }
                }
                if (!isCancelling) {
                    onCancelling(finalRootCause);
                }
                onCompletionInternal(proposedUpdate);
                boolean compareAndSet = _state$FU.compareAndSet(this, r8, JobSupportKt.boxIncomplete(proposedUpdate));
                if (!DebugKt.getASSERTIONS_ENABLED() || compareAndSet) {
                    completeStateFinalization(r8, proposedUpdate);
                    return proposedUpdate;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private final ChildHandle[ firstChild(Incomplete r3) {
        ChildHandle[ r0 = (ChildHandle[) (!(r3 instanceof ChildHandle[) ? null : r3);
        if (r0 != null) {
            return r0;
        }
        NodeList list = r3.getList();
        if (list != null) {
            return nextChild(list);
        }
        return null;
    }

    private final Throwable getExceptionOrNull(@Nullable Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            obj = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final Throwable getFinalRootCause(Finishing[cancelling= r6, List<? extends Throwable> exceptions) {
        Object obj;
        Object obj2 = null;
        if (exceptions.isEmpty()) {
            if (r6.isCancelling()) {
                return new ; job=(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        Iterator<T> it = exceptions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if ((!(((Throwable) obj) instanceof CancellationException)) != false) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = exceptions.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator<T> it2 = exceptions.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    private final NodeList getOrPromoteCancellingList(Incomplete r3) {
        NodeList list = r3.getList();
        if (list != null) {
            return list;
        }
        if (r3 instanceof Empty) {
            return new NodeList();
        }
        if (r3 instanceof JobNode) {
            promoteSingleToNodeList((JobNode) r3);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + r3).toString());
    }

    private final boolean isCancelling(@NotNull Incomplete incomplete) {
        return (incomplete instanceof Finishing[cancelling=) && ((Finishing[cancelling=) incomplete).isCancelling();
    }

    private final boolean joinInternal() {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return true;
    }

    private final Void loopOnState(Function1<Object, Unit> r2) {
        while (true) {
            r2.invoke(getState$kotlinx_coroutines_core());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r6v0, types: [kotlinx.coroutines.JobSupport] */
    private final Object makeCancelling(Object cause) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Throwable th = null;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Finishing[cancelling=) {
                synchronized (state$kotlinx_coroutines_core) {
                    if (((Finishing[cancelling=) state$kotlinx_coroutines_core).isSealed()) {
                        symbol2 = JobSupportKt.TOO_LATE_TO_CANCEL;
                        return symbol2;
                    }
                    boolean isCancelling = ((Finishing[cancelling=) state$kotlinx_coroutines_core).isCancelling();
                    if (cause != null || !isCancelling) {
                        if (th == null) {
                            th = createCauseException(cause);
                        }
                        ((Finishing[cancelling=) state$kotlinx_coroutines_core).addExceptionLocked(th);
                    }
                    r0 = isCancelling ^ true ? ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause() : 0;
                    if (r0 != 0) {
                        notifyCancelling(((Finishing[cancelling=) state$kotlinx_coroutines_core).getList(), r0);
                    }
                    symbol = JobSupportKt.COMPLETING_ALREADY;
                    return symbol;
                }
            } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                symbol3 = JobSupportKt.TOO_LATE_TO_CANCEL;
                return symbol3;
            } else {
                if (th == null) {
                    th = createCauseException(cause);
                }
                Incomplete incomplete = (Incomplete) state$kotlinx_coroutines_core;
                if (incomplete.getIsActive()) {
                    if (tryMakeCancelling(incomplete, th)) {
                        symbol4 = JobSupportKt.COMPLETING_ALREADY;
                        return symbol4;
                    }
                } else {
                    Object tryMakeCompleting = tryMakeCompleting(state$kotlinx_coroutines_core, new CompletedExceptionally(th, false, 2, r0));
                    symbol5 = JobSupportKt.COMPLETING_ALREADY;
                    if (tryMakeCompleting != symbol5) {
                        symbol6 = JobSupportKt.COMPLETING_RETRY;
                        if (tryMakeCompleting != symbol6) {
                            return tryMakeCompleting;
                        }
                    } else {
                        throw new IllegalStateException(("Cannot happen in " + state$kotlinx_coroutines_core).toString());
                    }
                }
            }
        }
    }

    private final JobNode<?> makeNode(Function1<? super Throwable, Unit> handler, boolean onCancelling) {
        if (onCancelling) {
            JobCancellingNode jobCancellingNode = handler instanceof JobCancellingNode ? handler : null;
            if (jobCancellingNode != null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(jobCancellingNode.job == this)) {
                        throw new AssertionError();
                    }
                }
                if (jobCancellingNode != null) {
                    return jobCancellingNode;
                }
            }
            return new InvokeOnCancelling[(this, handler);
        }
        JobNode<?> jobNode = handler instanceof JobNode ? handler : null;
        if (jobNode != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(jobNode.job == this && !(jobNode instanceof JobCancellingNode))) {
                    throw new AssertionError();
                }
            }
            if (jobNode != null) {
                return jobNode;
            }
        }
        return new InvokeOnCompletion[(this, handler);
    }

    private final ChildHandle[ nextChild(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandle[) {
                    return (ChildHandle[) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    private final void notifyCancelling(NodeList r7, Throwable cause) {
        onCancelling(cause);
        Object next = r7.getNext();
        if (next != null) {
            CompletionHandlerException completionHandlerException = null;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; (!Intrinsics.areEqual(lockFreeLinkedListNode, r7)) != false; lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(cause);
                    } catch (Throwable th) {
                        if (completionHandlerException != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(completionHandlerException, th);
                            if (completionHandlerException != null) {
                            }
                        }
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException);
            }
            cancelParent(cause);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final void notifyCompletion(@NotNull NodeList nodeList, Throwable th) {
        Object next = nodeList.getNext();
        if (next != null) {
            CompletionHandlerException completionHandlerException = null;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; (!Intrinsics.areEqual(lockFreeLinkedListNode, nodeList)) != false; lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th2) {
                        if (completionHandlerException != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(completionHandlerException, th2);
                            if (completionHandlerException != null) {
                            }
                        }
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final /* synthetic */ <T extends JobNode<?>> void notifyHandlers(NodeList r7, Throwable cause) {
        Object next = r7.getNext();
        if (next != null) {
            CompletionHandlerException completionHandlerException = null;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; (!Intrinsics.areEqual(lockFreeLinkedListNode, r7)) != false; lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                Intrinsics.reifiedOperationMarker(3, "T");
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(cause);
                    } catch (Throwable th) {
                        if (completionHandlerException != null) {
                            ExceptionsKt__ExceptionsKt.addSuppressed(completionHandlerException, th);
                            if (completionHandlerException != null) {
                            }
                        }
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.InactiveNodeList] */
    private final void promoteEmptyToNodeList(Empty r3) {
        NodeList nodeList = new NodeList();
        if (!r3.getIsActive()) {
            nodeList = new InactiveNodeList(nodeList);
        }
        _state$FU.compareAndSet(this, r3, nodeList);
    }

    private final void promoteSingleToNodeList(JobNode<?> r3) {
        r3.addOneIfEmpty(new NodeList());
        _state$FU.compareAndSet(this, r3, r3.getNextNode());
    }

    private final int startInternal(Object r5) {
        Empty empty;
        if (r5 instanceof Empty) {
            if (((Empty) r5).getIsActive()) {
                return 0;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            empty = JobSupportKt.EMPTY_ACTIVE;
            if (atomicReferenceFieldUpdater.compareAndSet(this, r5, empty)) {
                onStartInternal$kotlinx_coroutines_core();
                return 1;
            }
            return -1;
        } else if (r5 instanceof InactiveNodeList) {
            if (_state$FU.compareAndSet(this, r5, ((InactiveNodeList) r5).getList())) {
                onStartInternal$kotlinx_coroutines_core();
                return 1;
            }
            return -1;
        } else {
            return 0;
        }
    }

    private final String stateString(Object r3) {
        if (!(r3 instanceof Finishing[cancelling=)) {
            return r3 instanceof Incomplete ? ((Incomplete) r3).getIsActive() ? "Active" : "New" : r3 instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing[cancelling= r32 = (Finishing[cancelling=) r3;
        return r32.isCancelling() ? "Cancelling" : r32.isCompleting() ? "Completing" : "Active";
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            return jobSupport.toCancellationException(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    private final boolean tryFinalizeSimpleState(Incomplete r5, Object update) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!((r5 instanceof Empty) || (r5 instanceof JobNode))) {
                throw new AssertionError();
            }
        }
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(update instanceof CompletedExceptionally)) == true) {
            if (_state$FU.compareAndSet(this, r5, JobSupportKt.boxIncomplete(update))) {
                onCancelling(null);
                onCompletionInternal(update);
                completeStateFinalization(r5, update);
                return true;
            }
            return false;
        }
        throw new AssertionError();
    }

    private final boolean tryMakeCancelling(Incomplete r6, Throwable rootCause) {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(r6 instanceof Finishing[cancelling=)) == true) {
            if (!DebugKt.getASSERTIONS_ENABLED() || r6.getIsActive()) {
                NodeList orPromoteCancellingList = getOrPromoteCancellingList(r6);
                if (orPromoteCancellingList != null) {
                    if (_state$FU.compareAndSet(this, r6, new Finishing[cancelling=(orPromoteCancellingList, false, rootCause))) {
                        notifyCancelling(orPromoteCancellingList, rootCause);
                        return true;
                    }
                    return false;
                }
                return false;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private final Object tryMakeCompleting(Object r2, Object proposedUpdate) {
        Symbol symbol;
        Symbol symbol2;
        if (!(r2 instanceof Incomplete)) {
            symbol2 = JobSupportKt.COMPLETING_ALREADY;
            return symbol2;
        } else if (((r2 instanceof Empty) || (r2 instanceof JobNode)) && !(r2 instanceof ChildHandle[) && !(proposedUpdate instanceof CompletedExceptionally)) {
            if (tryFinalizeSimpleState((Incomplete) r2, proposedUpdate)) {
                return proposedUpdate;
            }
            symbol = JobSupportKt.COMPLETING_RETRY;
            return symbol;
        } else {
            return tryMakeCompletingSlowPath((Incomplete) r2, proposedUpdate);
        }
    }

    private final Object tryMakeCompletingSlowPath(Incomplete r7, Object proposedUpdate) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(r7);
        if (orPromoteCancellingList == null) {
            symbol = JobSupportKt.COMPLETING_RETRY;
            return symbol;
        }
        Finishing[cancelling= r1 = (Finishing[cancelling=) (!(r7 instanceof Finishing[cancelling=) ? null : r7);
        if (r1 == null) {
            r1 = new Finishing[cancelling=(orPromoteCancellingList, false, null);
        }
        synchronized (r1) {
            if (r1.isCompleting()) {
                symbol3 = JobSupportKt.COMPLETING_ALREADY;
                return symbol3;
            }
            r1.setCompleting(true);
            if (r1 != r7 && !_state$FU.compareAndSet(this, r7, r1)) {
                symbol2 = JobSupportKt.COMPLETING_RETRY;
                return symbol2;
            }
            if (DebugKt.getASSERTIONS_ENABLED() && (!r1.isSealed()) == false) {
                throw new AssertionError();
            }
            boolean isCancelling = r1.isCancelling();
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(proposedUpdate instanceof CompletedExceptionally) ? null : proposedUpdate);
            if (completedExceptionally != null) {
                r1.addExceptionLocked(completedExceptionally.cause);
            }
            Throwable rootCause = true ^ isCancelling ? r1.getRootCause() : null;
            Unit unit = Unit.INSTANCE;
            if (rootCause != null) {
                notifyCancelling(orPromoteCancellingList, rootCause);
            }
            ChildHandle[ firstChild = firstChild(r7);
            if (firstChild != null && tryWaitForChild(r1, firstChild, proposedUpdate)) {
                return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }
            return finalizeFinishingState(r1, proposedUpdate);
        }
    }

    private final boolean tryWaitForChild(Finishing[cancelling= r7, ChildHandle[ child, Object proposedUpdate) {
        while (Job.DefaultImpls.invokeOnCompletion$default(child.ChildHandle[, false, false, new ChildCompletion[(this, r7, child, proposedUpdate), 1, null) == NonDisposableHandle.INSTANCE) {
            child = nextChild(child);
            if (child == null) {
                return false;
            }
        }
        return true;
    }

    public void afterCompletion(@Nullable Object r1) {
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final ChildHandle attachChild(@NotNull ChildJob child) {
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(this, true, false, new ChildHandle[(this, child), 2, null);
        if (invokeOnCompletion$default != null) {
            return (ChildHandle) invokeOnCompletion$default;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
    }

    @Nullable
    public final Object awaitInternal$kotlinx_coroutines_core(@NotNull Continuation<Object> continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                    Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
                    if (DebugKt.getRECOVER_STACK_TRACES()) {
                        if (continuation instanceof CoroutineStackFrame) {
                            throw StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
                        }
                        throw th;
                    }
                    throw th;
                }
                return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        return awaitSuspend(continuation);
    }

    @Nullable
    final /* synthetic */ Object awaitSuspend(@NotNull Continuation<Object> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        AwaitContinuation awaitContinuation = new AwaitContinuation(intercepted, this);
        CancellableContinuationKt.disposeOnCancellation(awaitContinuation, invokeOnCompletion(new ResumeAwaitOnCompletion[(this, awaitContinuation)));
        Object result = awaitContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    public final boolean cancelCoroutine(@Nullable Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(@Nullable Object cause) {
        Object obj;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        obj = JobSupportKt.COMPLETING_ALREADY;
        if (getOnCancelComplete$kotlinx_coroutines_core() && (obj = cancelMakeCompleting(cause)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        symbol = JobSupportKt.COMPLETING_ALREADY;
        if (obj == symbol) {
            obj = makeCancelling(cause);
        }
        symbol2 = JobSupportKt.COMPLETING_ALREADY;
        if (obj == symbol2 || obj == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        symbol3 = JobSupportKt.TOO_LATE_TO_CANCEL;
        if (obj == symbol3) {
            return false;
        }
        afterCompletion(obj);
        return true;
    }

    public void cancelInternal(@NotNull Throwable cause) {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    @NotNull
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(@NotNull Throwable cause) {
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(cause) && getHandlesException();
    }

    @NotNull
    public final ; job= defaultCancellationException$kotlinx_coroutines_core(@Nullable String message, @Nullable Throwable cause) {
        if (message == null) {
            message = cancellationExceptionMessage();
        }
        return new ; job=(message, cause, this);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        return (E) Job.DefaultImpls.get(this, key);
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing[cancelling=) {
            Throwable rootCause = ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null) {
                CancellationException cancellationException = toCancellationException(rootCause, DebugStringsKt.getClassSimpleName(this) + " is cancelling");
                if (cancellationException != null) {
                    return cancellationException;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return toCancellationException$default(this, ((CompletedExceptionally) state$kotlinx_coroutines_core).cause, null, 1, null);
            }
            return new ; job=(DebugStringsKt.getClassSimpleName(this) + " has completed normally", null, this);
        } else {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
    }

    @Override // kotlinx.coroutines.ParentJob
    @NotNull
    public CancellationException getChildJobCancellationCause() {
        Throwable th;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing[cancelling=) {
            th = ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$kotlinx_coroutines_core).toString());
        } else {
            th = null;
        }
        CancellationException cancellationException = th instanceof CancellationException ? th : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        return new ; job=("Parent job is " + stateString(state$kotlinx_coroutines_core), th, this);
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final Sequence<Job> getChildren() {
        Sequence<Job> sequence;
        sequence = SequencesKt__SequenceBuilderKt.sequence(new JobSupport$children$1(this, null));
        return sequence;
    }

    @Nullable
    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if ((!(state$kotlinx_coroutines_core instanceof Incomplete)) != false) {
            if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            }
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    @Nullable
    protected final Throwable getCompletionCause() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing[cancelling=) {
            Throwable rootCause = ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null) {
                return rootCause;
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
            return null;
        } else {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
    }

    protected final boolean getCompletionCauseHandled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) && ((CompletedExceptionally) state$kotlinx_coroutines_core).getHandled();
    }

    @Nullable
    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if ((!(state$kotlinx_coroutines_core instanceof Incomplete)) != false) {
            return getExceptionOrNull(state$kotlinx_coroutines_core);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    /* renamed from: getHandlesException$kotlinx_coroutines_core */
    public boolean getHandlesException() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    @NotNull
    public final CoroutineContext.Key<?> getKey() {
        return Job.INSTANCE;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final SelectClause0 getOnJoin() {
        return this;
    }

    @Nullable
    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) this._parentHandle;
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    protected boolean handleJobException(@NotNull Throwable r1) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable r1) {
        throw r1;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(@Nullable Job parent) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle$kotlinx_coroutines_core() == null)) {
                throw new AssertionError();
            }
        }
        if (parent == null) {
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
            return;
        }
        parent.start();
        ChildHandle attachChild = parent.attachChild(this);
        setParentHandle$kotlinx_coroutines_core(attachChild);
        if (isCompleted()) {
            attachChild.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> handler) {
        return invokeOnCompletion(false, true, handler);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).getIsActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) || ((state$kotlinx_coroutines_core instanceof Finishing[cancelling=) && ((Finishing[cancelling=) state$kotlinx_coroutines_core).isCancelling());
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    @Nullable
    public final Object join(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (!joinInternal()) {
            YieldKt.checkCompletion(continuation.getContext());
            return Unit.INSTANCE;
        }
        Object joinSuspend = joinSuspend(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return joinSuspend == coroutine_suspended ? joinSuspend : Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object joinSuspend(@NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl, invokeOnCompletion(new ResumeOnCompletion[(this, cancellableContinuationImpl)));
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(@Nullable Object proposedUpdate) {
        Object tryMakeCompleting;
        Symbol symbol;
        Symbol symbol2;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), proposedUpdate);
            symbol = JobSupportKt.COMPLETING_ALREADY;
            if (tryMakeCompleting == symbol) {
                return false;
            }
            if (tryMakeCompleting == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
            symbol2 = JobSupportKt.COMPLETING_RETRY;
        } while (tryMakeCompleting == symbol2);
        afterCompletion(tryMakeCompleting);
        return true;
    }

    @Nullable
    public final Object makeCompletingOnce$kotlinx_coroutines_core(@Nullable Object proposedUpdate) {
        Object tryMakeCompleting;
        Symbol symbol;
        Symbol symbol2;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), proposedUpdate);
            symbol = JobSupportKt.COMPLETING_ALREADY;
            if (tryMakeCompleting != symbol) {
                symbol2 = JobSupportKt.COMPLETING_RETRY;
            } else {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + proposedUpdate, getExceptionOrNull(proposedUpdate));
            }
        } while (tryMakeCompleting == symbol2);
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.minusKey(this, key);
    }

    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    protected void onCancelling(@Nullable Throwable cause) {
    }

    protected void onCompletionInternal(@Nullable Object r1) {
    }

    public void onStartInternal$kotlinx_coroutines_core() {
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(@NotNull ParentJob parentJob) {
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        return Job.DefaultImpls.plus(this, coroutineContext);
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final <R> void registerSelectClause0(@NotNull SelectInstance<? super R> select, @NotNull Function1<? super Continuation<? super R>, ? extends Object> r4) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (select.trySelect()) {
                    UndispatchedKt.startCoroutineUnintercepted(r4, select.getCompletion());
                    return;
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        select.disposeOnSelect(invokeOnCompletion(new SelectJoinOnCompletion[(this, select, r4)));
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> select, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> r4) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (select.trySelect()) {
                    if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                        select.resumeSelectWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
                        return;
                    } else {
                        UndispatchedKt.startCoroutineUnintercepted(r4, JobSupportKt.unboxState(state$kotlinx_coroutines_core), select.getCompletion());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        select.disposeOnSelect(invokeOnCompletion(new SelectAwaitOnCompletion[(this, select, r4)));
    }

    public final void removeNode$kotlinx_coroutines_core(@NotNull JobNode<?> node) {
        Object state$kotlinx_coroutines_core;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Empty empty;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof JobNode)) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((Incomplete) state$kotlinx_coroutines_core).getList() == null) {
                    return;
                }
                node.remove();
                return;
            } else if (state$kotlinx_coroutines_core != node) {
                return;
            } else {
                atomicReferenceFieldUpdater = _state$FU;
                empty = JobSupportKt.EMPTY_ACTIVE;
            }
        } while (!atomicReferenceFieldUpdater.compareAndSet(this, state$kotlinx_coroutines_core, empty));
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> select, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> r4) {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            select.resumeSelectWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
        } else {
            CancellableKt.startCoroutineCancellable(r4, JobSupportKt.unboxState(state$kotlinx_coroutines_core), select.getCompletion());
        }
    }

    public final void setParentHandle$kotlinx_coroutines_core(@Nullable ChildHandle childHandle) {
        this._parentHandle = childHandle;
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    @NotNull
    protected final CancellationException toCancellationException(@NotNull Throwable th, @Nullable String str) {
        CancellationException cancellationException = (CancellationException) (!(th instanceof CancellationException) ? null : th);
        if (cancellationException == null) {
            if (str == null) {
                str = cancellationExceptionMessage();
            }
            cancellationException = new ; job=(str, th, this);
        }
        return cancellationException;
    }

    @InternalCoroutinesApi
    @NotNull
    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    @NotNull
    public String toString() {
        return toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(@Nullable Throwable cause) {
        Throwable th;
        if (cause == null || (th = toCancellationException$default(this, cause, null, 1, null)) == null) {
            th = new ; job=(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(th);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, @NotNull Function1<? super Throwable, Unit> handler) {
        Throwable th;
        JobNode<?> jobNode = null;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (empty.getIsActive()) {
                    if (jobNode == null) {
                        jobNode = makeNode(handler, onCancelling);
                    }
                    if (_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, jobNode)) {
                        return jobNode;
                    }
                } else {
                    promoteEmptyToNodeList(empty);
                }
            } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
                NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                if (list != null) {
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    if (onCancelling && (state$kotlinx_coroutines_core instanceof Finishing[cancelling=)) {
                        synchronized (state$kotlinx_coroutines_core) {
                            th = ((Finishing[cancelling=) state$kotlinx_coroutines_core).getRootCause();
                            if (th == null || ((handler instanceof ChildHandle[) && !((Finishing[cancelling=) state$kotlinx_coroutines_core).isCompleting())) {
                                if (jobNode == null) {
                                    jobNode = makeNode(handler, onCancelling);
                                }
                                if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                                    if (th == null) {
                                        return jobNode;
                                    }
                                    disposableHandle = jobNode;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    } else {
                        th = null;
                    }
                    if (th != null) {
                        if (invokeImmediately) {
                            handler.invoke(th);
                        }
                        return disposableHandle;
                    }
                    if (jobNode == null) {
                        jobNode = makeNode(handler, onCancelling);
                    }
                    if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                        return jobNode;
                    }
                } else if (state$kotlinx_coroutines_core == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.JobNode<*>");
                } else {
                    promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                }
            } else {
                if (invokeImmediately) {
                    if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                        state$kotlinx_coroutines_core = null;
                    }
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) state$kotlinx_coroutines_core;
                    handler.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public Job plus(@NotNull Job job) {
        return Job.DefaultImpls.plus((Job) this, job);
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(@Nullable CancellationException cause) {
        if (cause == null) {
            cause = new ; job=(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cause);
    }
}
