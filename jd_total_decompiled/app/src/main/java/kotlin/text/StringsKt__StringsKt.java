package kotlin.text;

import ..;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0012\u001a(\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a(\u0010\u0005\u001a\u00020\u0007*\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\u0005\u0010\b\u001a(\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\t\u0010\u0006\u001a(\u0010\t\u001a\u00020\u0007*\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\t\u0010\b\u001a(\u0010\n\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\n\u0010\u0006\u001a(\u0010\n\u001a\u00020\u0007*\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\b\u00a2\u0006\u0004\b\n\u0010\b\u001a\u001d\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\r\u001a\u001d\u0010\u0005\u001a\u00020\u0007*\u00020\u00072\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u000e\u001a\u001d\u0010\t\u001a\u00020\u0000*\u00020\u00002\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\r\u001a\u001d\u0010\t\u001a\u00020\u0007*\u00020\u00072\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u000e\u001a\u001d\u0010\n\u001a\u00020\u0000*\u00020\u00002\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\r\u001a\u001d\u0010\n\u001a\u00020\u0007*\u00020\u00072\n\u0010\f\u001a\u00020\u000b\"\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000e\u001a\u0011\u0010\u0005\u001a\u00020\u0000*\u00020\u0000\u00a2\u0006\u0004\b\u0005\u0010\u000f\u001a\u0014\u0010\u0005\u001a\u00020\u0007*\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u0010\u001a\u0011\u0010\t\u001a\u00020\u0000*\u00020\u0000\u00a2\u0006\u0004\b\t\u0010\u000f\u001a\u0014\u0010\t\u001a\u00020\u0007*\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\t\u0010\u0010\u001a\u0011\u0010\n\u001a\u00020\u0000*\u00020\u0000\u00a2\u0006\u0004\b\n\u0010\u000f\u001a\u0014\u0010\n\u001a\u00020\u0007*\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\n\u0010\u0010\u001a#\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0014\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0016\u001a#\u0010\u0017\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0015\u001a#\u0010\u0017\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0016\u001a'\u0010\u0018\u001a\u00020\u0003*\u0004\u0018\u00010\u0000H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0003*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u001a\u0010\u0019\u001a\u0014\u0010\u001b\u001a\u00020\u0003*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010\u0019\u001a\u0014\u0010\u001c\u001a\u00020\u0003*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u001c\u0010\u0019\u001a'\u0010\u001d\u001a\u00020\u0003*\u0004\u0018\u00010\u0000H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0019\u001a\u0014\u0010\u001f\u001a\u00020\u001e*\u00020\u0000H\u0086\u0002\u00a2\u0006\u0004\b\u001f\u0010 \u001a\u0016\u0010!\u001a\u00020\u0007*\u0004\u0018\u00010\u0007H\u0087\b\u00a2\u0006\u0004\b!\u0010\u0010\u001a6\u0010&\u001a\u00028\u0001\"\f\b\u0000\u0010\"*\u00020\u0000*\u00028\u0001\"\u0004\b\u0001\u0010#*\u00028\u00002\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010$H\u0087\b\u00a2\u0006\u0004\b&\u0010'\u001a6\u0010(\u001a\u00028\u0001\"\f\b\u0000\u0010\"*\u00020\u0000*\u00028\u0001\"\u0004\b\u0001\u0010#*\u00028\u00002\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010$H\u0087\b\u00a2\u0006\u0004\b(\u0010'\u001a\u0019\u0010*\u001a\u00020\u0003*\u00020\u00002\u0006\u0010)\u001a\u00020\u0011\u00a2\u0006\u0004\b*\u0010+\u001a\u0019\u0010.\u001a\u00020\u0007*\u00020\u00072\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\b.\u0010/\u001a\u0019\u00100\u001a\u00020\u0000*\u00020\u00002\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\b0\u00101\u001a$\u00100\u001a\u00020\u0000*\u00020\u00072\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0004\b0\u00104\u001a&\u0010.\u001a\u00020\u0007*\u00020\u00002\u0006\u00105\u001a\u00020\u00112\b\b\u0002\u00106\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0004\b.\u00107\u001a\u0019\u0010.\u001a\u00020\u0007*\u00020\u00002\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\b.\u00108\u001a#\u0010;\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b;\u0010<\u001a#\u0010;\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b;\u0010=\u001a#\u0010>\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b>\u0010<\u001a#\u0010>\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b>\u0010=\u001a#\u0010?\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b?\u0010<\u001a#\u0010?\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b?\u0010=\u001a#\u0010@\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b@\u0010<\u001a#\u0010@\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b@\u0010=\u001a)\u0010B\u001a\u00020\u0000*\u00020\u00002\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u0000\u00a2\u0006\u0004\bB\u0010C\u001a,\u0010B\u001a\u00020\u0007*\u00020\u00072\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u0000H\u0087\b\u00a2\u0006\u0004\bB\u0010D\u001a!\u0010B\u001a\u00020\u0000*\u00020\u00002\u0006\u0010-\u001a\u00020,2\u0006\u0010A\u001a\u00020\u0000\u00a2\u0006\u0004\bB\u0010E\u001a$\u0010B\u001a\u00020\u0007*\u00020\u00072\u0006\u0010-\u001a\u00020,2\u0006\u0010A\u001a\u00020\u0000H\u0087\b\u00a2\u0006\u0004\bB\u0010F\u001a!\u0010G\u001a\u00020\u0000*\u00020\u00002\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u0011\u00a2\u0006\u0004\bG\u0010H\u001a$\u0010G\u001a\u00020\u0007*\u00020\u00072\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0004\bG\u0010I\u001a\u0019\u0010G\u001a\u00020\u0000*\u00020\u00002\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\bG\u00101\u001a\u001c\u0010G\u001a\u00020\u0007*\u00020\u00072\u0006\u0010-\u001a\u00020,H\u0087\b\u00a2\u0006\u0004\bG\u0010/\u001a\u0019\u0010K\u001a\u00020\u0000*\u00020\u00002\u0006\u0010J\u001a\u00020\u0000\u00a2\u0006\u0004\bK\u0010L\u001a\u0019\u0010K\u001a\u00020\u0007*\u00020\u00072\u0006\u0010J\u001a\u00020\u0000\u00a2\u0006\u0004\bK\u0010M\u001a\u0019\u0010O\u001a\u00020\u0000*\u00020\u00002\u0006\u0010N\u001a\u00020\u0000\u00a2\u0006\u0004\bO\u0010L\u001a\u0019\u0010O\u001a\u00020\u0007*\u00020\u00072\u0006\u0010N\u001a\u00020\u0000\u00a2\u0006\u0004\bO\u0010M\u001a!\u0010P\u001a\u00020\u0000*\u00020\u00002\u0006\u0010J\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0000\u00a2\u0006\u0004\bP\u0010Q\u001a!\u0010P\u001a\u00020\u0007*\u00020\u00072\u0006\u0010J\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0000\u00a2\u0006\u0004\bP\u0010R\u001a\u0019\u0010P\u001a\u00020\u0000*\u00020\u00002\u0006\u00109\u001a\u00020\u0000\u00a2\u0006\u0004\bP\u0010L\u001a\u0019\u0010P\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u0000\u00a2\u0006\u0004\bP\u0010M\u001a+\u0010S\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bS\u0010T\u001a+\u0010S\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bS\u0010U\u001a+\u0010V\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bV\u0010T\u001a+\u0010V\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bV\u0010U\u001a+\u0010W\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bW\u0010U\u001a+\u0010W\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bW\u0010T\u001a+\u0010X\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00022\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bX\u0010T\u001a+\u0010X\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\bX\u0010U\u001a$\u0010[\u001a\u00020\u0007*\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010A\u001a\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b[\u0010\\\u001a2\u0010[\u001a\u00020\u0007*\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0014\b\b\u0010^\u001a\u000e\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020\u00000\u0001H\u0087\b\u00a2\u0006\u0004\b[\u0010_\u001a$\u0010`\u001a\u00020\u0007*\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010A\u001a\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b`\u0010\\\u001a\u001c\u0010a\u001a\u00020\u0003*\u00020\u00002\u0006\u0010Z\u001a\u00020YH\u0087\f\u00a2\u0006\u0004\ba\u0010b\u001a;\u0010g\u001a\u00020\u0003*\u00020\u00002\u0006\u0010c\u001a\u00020\u00112\u0006\u0010d\u001a\u00020\u00002\u0006\u0010e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010f\u001a\u00020\u0003H\u0000\u00a2\u0006\u0004\bg\u0010h\u001a#\u0010j\u001a\u00020\u0003*\u00020\u00002\u0006\u0010i\u001a\u00020\u00022\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bj\u0010k\u001a#\u0010l\u001a\u00020\u0003*\u00020\u00002\u0006\u0010i\u001a\u00020\u00022\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bl\u0010k\u001a#\u0010j\u001a\u00020\u0003*\u00020\u00002\u0006\u0010J\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bj\u0010m\u001a+\u0010j\u001a\u00020\u0003*\u00020\u00002\u0006\u0010J\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bj\u0010n\u001a#\u0010l\u001a\u00020\u0003*\u00020\u00002\u0006\u0010N\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bl\u0010m\u001a#\u0010o\u001a\u00020\u0007*\u00020\u00002\u0006\u0010d\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bo\u0010p\u001a#\u0010q\u001a\u00020\u0007*\u00020\u00002\u0006\u0010d\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bq\u0010p\u001a-\u0010r\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\br\u0010s\u001a-\u0010t\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\bt\u0010s\u001a=\u0010x\u001a\u00020\u0011*\u00020\u00002\u0006\u0010d\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u0010f\u001a\u00020\u00032\b\b\u0002\u0010u\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\bv\u0010w\u001aG\u0010~\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0007\u0018\u00010{*\u00020\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070y2\u0006\u00105\u001a\u00020\u00112\u0006\u0010f\u001a\u00020\u00032\u0006\u0010u\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b|\u0010}\u001aA\u0010~\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0007\u0018\u00010{*\u00020\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070y2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0004\b~\u0010\u007f\u001aC\u0010\u0080\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0007\u0018\u00010{*\u00020\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070y2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0005\b\u0080\u0001\u0010\u007f\u001a4\u0010r\u001a\u00020\u0011*\u00020\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070y2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0005\br\u0010\u0081\u0001\u001a4\u0010t\u001a\u00020\u0011*\u00020\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070y2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0005\bt\u0010\u0081\u0001\u001a.\u0010x\u001a\u00020\u0011*\u00020\u00002\u0006\u0010i\u001a\u00020\u00022\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0005\bx\u0010\u0082\u0001\u001a/\u0010x\u001a\u00020\u0011*\u00020\u00002\u0007\u0010\u0083\u0001\u001a\u00020\u00072\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0005\bx\u0010\u0084\u0001\u001a0\u0010\u0085\u0001\u001a\u00020\u0011*\u00020\u00002\u0006\u0010i\u001a\u00020\u00022\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0006\b\u0085\u0001\u0010\u0082\u0001\u001a1\u0010\u0085\u0001\u001a\u00020\u0011*\u00020\u00002\u0007\u0010\u0083\u0001\u001a\u00020\u00072\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u0003\u00a2\u0006\u0006\b\u0085\u0001\u0010\u0084\u0001\u001a(\u0010\u0086\u0001\u001a\u00020\u0003*\u00020\u00002\u0006\u0010d\u001a\u00020\u00002\b\b\u0002\u0010f\u001a\u00020\u0003H\u0086\u0002\u00a2\u0006\u0005\b\u0086\u0001\u0010m\u001a(\u0010\u0086\u0001\u001a\u00020\u0003*\u00020\u00002\u0006\u0010i\u001a\u00020\u00022\b\b\u0002\u0010f\u001a\u00020\u0003H\u0086\u0002\u00a2\u0006\u0005\b\u0086\u0001\u0010k\u001a\u001e\u0010\u0086\u0001\u001a\u00020\u0003*\u00020\u00002\u0006\u0010Z\u001a\u00020YH\u0087\n\u00a2\u0006\u0005\b\u0086\u0001\u0010b\u001aE\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00020,0\u0089\u0001*\u00020\u00002\u0007\u0010\u0087\u0001\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011H\u0002\u00a2\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001\u001aN\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00020,0\u0089\u0001*\u00020\u00002\u0010\u0010\u0087\u0001\u001a\u000b\u0012\u0006\b\u0001\u0012\u00020\u00070\u008d\u00012\b\b\u0002\u00105\u001a\u00020\u00112\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011H\u0002\u00a2\u0006\u0006\b\u008a\u0001\u0010\u008e\u0001\u001aF\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0089\u0001*\u00020\u00002\u0014\u0010\u0087\u0001\u001a\u000b\u0012\u0006\b\u0001\u0012\u00020\u00070\u008d\u0001\"\u00020\u00072\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011\u00a2\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001\u001aF\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0091\u0001*\u00020\u00002\u0014\u0010\u0087\u0001\u001a\u000b\u0012\u0006\b\u0001\u0012\u00020\u00070\u008d\u0001\"\u00020\u00072\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011\u00a2\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a=\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0089\u0001*\u00020\u00002\u000b\u0010\u0087\u0001\u001a\u00020\u000b\"\u00020\u00022\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011\u00a2\u0006\u0006\b\u008f\u0001\u0010\u0094\u0001\u001a=\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0091\u0001*\u00020\u00002\u000b\u0010\u0087\u0001\u001a\u00020\u000b\"\u00020\u00022\b\b\u0002\u0010f\u001a\u00020\u00032\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011\u00a2\u0006\u0006\b\u0092\u0001\u0010\u0095\u0001\u001a6\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0091\u0001*\u00020\u00002\u0006\u00109\u001a\u00020\u00072\u0006\u0010f\u001a\u00020\u00032\u0007\u0010\u0088\u0001\u001a\u00020\u0011H\u0002\u00a2\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001\u001a1\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0091\u0001*\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0006\b\u0092\u0001\u0010\u0098\u0001\u001a\u001b\u0010\u0099\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0089\u0001*\u00020\u0000\u00a2\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001\u001a\u001b\u0010\u009b\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0091\u0001*\u00020\u0000\u00a2\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001\"\u001a\u0010\u009f\u0001\u001a\u00020\u0011*\u00020\u00008F@\u0006\u00a2\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u001a\u0010\u00a2\u0001\u001a\u00020,*\u00020\u00008F@\u0006\u00a2\u0006\b\u001a\u0006\b\u00a0\u0001\u0010\u00a1\u0001\u00a8\u0006\u00a3\u0001"}, d2 = {"", "Lkotlin/Function1;", "", "", "predicate", "trim", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "trimStart", "trimEnd", "", "chars", "(Ljava/lang/CharSequence;[C)Ljava/lang/CharSequence;", "(Ljava/lang/String;[C)Ljava/lang/String;", "(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;)Ljava/lang/String;", "", CartConstant.KEY_LENGTH, "padChar", "padStart", "(Ljava/lang/CharSequence;IC)Ljava/lang/CharSequence;", "(Ljava/lang/String;IC)Ljava/lang/String;", "padEnd", "isNullOrEmpty", "(Ljava/lang/CharSequence;)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "isNotEmpty", "isNotBlank", "isNullOrBlank", "Lkotlin/collections/CharIterator;", "iterator", "(Ljava/lang/CharSequence;)Lkotlin/collections/CharIterator;", "orEmpty", "C", "R", "Lkotlin/Function0;", "defaultValue", "ifEmpty", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifBlank", "index", "hasSurrogatePairAt", "(Ljava/lang/CharSequence;I)Z", "Lkotlin/ranges/IntRange;", "range", "substring", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)Ljava/lang/String;", "subSequence", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;)Ljava/lang/CharSequence;", "start", "end", "(Ljava/lang/String;II)Ljava/lang/CharSequence;", "startIndex", "endIndex", "(Ljava/lang/CharSequence;II)Ljava/lang/String;", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;)Ljava/lang/String;", "delimiter", "missingDelimiterValue", "substringBefore", "(Ljava/lang/String;CLjava/lang/String;)Ljava/lang/String;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "substringAfter", "substringBeforeLast", "substringAfterLast", "replacement", "replaceRange", "(Ljava/lang/CharSequence;IILjava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;IILjava/lang/CharSequence;)Ljava/lang/String;", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;Ljava/lang/CharSequence;)Ljava/lang/String;", "removeRange", "(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;", "(Ljava/lang/String;II)Ljava/lang/String;", "prefix", "removePrefix", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;", "suffix", "removeSuffix", "removeSurrounding", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "replaceBefore", "(Ljava/lang/String;CLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replaceAfter", "replaceAfterLast", "replaceBeforeLast", "Lkotlin/text/Regex;", "regex", "replace", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;Ljava/lang/String;)Ljava/lang/String;", "Lkotlin/text/MatchResult;", "transform", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "replaceFirst", "matches", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;)Z", "thisOffset", "other", "otherOffset", "ignoreCase", "regionMatchesImpl", "(Ljava/lang/CharSequence;ILjava/lang/CharSequence;IIZ)Z", DYConstants.DY_CHAR, "startsWith", "(Ljava/lang/CharSequence;CZ)Z", "endsWith", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Z", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;IZ)Z", "commonPrefixWith", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Ljava/lang/String;", "commonSuffixWith", "indexOfAny", "(Ljava/lang/CharSequence;[CIZ)I", "lastIndexOfAny", "last", "indexOf$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;IIZZ)I", "indexOf", "", "strings", "Lkotlin/Pair;", "findAnyOf$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZZ)Lkotlin/Pair;", "findAnyOf", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZ)Lkotlin/Pair;", "findLastAnyOf", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZ)I", "(Ljava/lang/CharSequence;CIZ)I", "string", "(Ljava/lang/CharSequence;Ljava/lang/String;IZ)I", "lastIndexOf", "contains", "delimiters", "limit", "Lkotlin/sequences/Sequence;", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[CIZI)Lkotlin/sequences/Sequence;", "rangesDelimitedBy", "", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "", JDReactConstant.BUFF_DIR_SPLIT, "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "(Ljava/lang/CharSequence;[CZI)Lkotlin/sequences/Sequence;", "(Ljava/lang/CharSequence;[CZI)Ljava/util/List;", "split$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/lang/String;ZI)Ljava/util/List;", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;I)Ljava/util/List;", "lineSequence", "(Ljava/lang/CharSequence;)Lkotlin/sequences/Sequence;", "lines", "(Ljava/lang/CharSequence;)Ljava/util/List;", "getLastIndex", "(Ljava/lang/CharSequence;)I", "lastIndex", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "indices", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i2 = 0;
        while (i2 < min && CharsKt__CharKt.equals(charSequence.charAt(i2), charSequence2.charAt(i2), z)) {
            i2++;
        }
        int i3 = i2 - 1;
        if (hasSurrogatePairAt(charSequence, i3) || hasSurrogatePairAt(charSequence2, i3)) {
            i2--;
        }
        return charSequence.subSequence(0, i2).toString();
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        int length = charSequence.length();
        int min = Math.min(length, charSequence2.length());
        int i2 = 0;
        while (i2 < min && CharsKt__CharKt.equals(charSequence.charAt((length - i2) - 1), charSequence2.charAt((r1 - i2) - 1), z)) {
            i2++;
        }
        if (hasSurrogatePairAt(charSequence, (length - i2) - 1) || hasSurrogatePairAt(charSequence2, (r1 - i2) - 1)) {
            i2--;
        }
        return charSequence.subSequence(length - i2, length).toString();
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return commonSuffixWith(charSequence, charSequence2, z);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        int indexOf$default;
        if (charSequence2 instanceof String) {
            indexOf$default = indexOf$default(charSequence, (String) charSequence2, 0, z, 2, (Object) null);
            if (indexOf$default >= 0) {
                return true;
            }
        } else if (indexOf$StringsKt__StringsKt$default(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return contains(charSequence, charSequence2, z);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, char c2, boolean z) {
        int lastIndex;
        if (charSequence.length() > 0) {
            lastIndex = getLastIndex(charSequence);
            if (CharsKt__CharKt.equals(charSequence.charAt(lastIndex), c2, z)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, c2, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i2, boolean z) {
        return findAnyOf$StringsKt__StringsKt(charSequence, collection, i2, z, false);
    }

    public static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(@NotNull CharSequence charSequence, Collection<String> collection, int i2, boolean z, boolean z2) {
        int lastIndex;
        int coerceAtMost;
        IntProgression downTo;
        Object obj;
        Object obj2;
        int coerceAtLeast;
        if (!z && collection.size() == 1) {
            String str = (String) CollectionsKt.single(collection);
            int indexOf$default = !z2 ? indexOf$default(charSequence, str, i2, false, 4, (Object) null) : lastIndexOf$default(charSequence, str, i2, false, 4, (Object) null);
            if (indexOf$default < 0) {
                return null;
            }
            return TuplesKt.to(Integer.valueOf(indexOf$default), str);
        }
        if (z2) {
            lastIndex = getLastIndex(charSequence);
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, lastIndex);
            downTo = RangesKt___RangesKt.downTo(coerceAtMost, 0);
        } else {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i2, 0);
            downTo = new (coerceAtLeast, charSequence.length());
        }
        if (charSequence instanceof String) {
            int first = downTo.getFirst();
            int last = downTo.getLast();
            int step = downTo.getStep();
            if (step < 0 ? first >= last : first <= last) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        String str2 = (String) obj2;
                        if (StringsKt__StringsJVMKt.regionMatches(str2, 0, (String) charSequence, first, str2.length(), z)) {
                            break;
                        }
                    }
                    String str3 = (String) obj2;
                    if (str3 == null) {
                        if (first == last) {
                            break;
                        }
                        first += step;
                    } else {
                        return TuplesKt.to(Integer.valueOf(first), str3);
                    }
                }
            }
        } else {
            int first2 = downTo.getFirst();
            int last2 = downTo.getLast();
            int step2 = downTo.getStep();
            if (step2 < 0 ? first2 >= last2 : first2 <= last2) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it2.next();
                        String str4 = (String) obj;
                        if (regionMatchesImpl(str4, 0, charSequence, first2, str4.length(), z)) {
                            break;
                        }
                    }
                    String str5 = (String) obj;
                    if (str5 == null) {
                        if (first2 == last2) {
                            break;
                        }
                        first2 += step2;
                    } else {
                        return TuplesKt.to(Integer.valueOf(first2), str5);
                    }
                }
            }
        }
        return null;
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return findAnyOf(charSequence, collection, i2, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i2, boolean z) {
        return findAnyOf$StringsKt__StringsKt(charSequence, collection, i2, z, true);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = getLastIndex(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return findLastAnyOf(charSequence, collection, i2, z);
    }

    @NotNull
    public static final  getIndices(@NotNull CharSequence charSequence) {
        return new (0, charSequence.length() - 1);
    }

    public static int getLastIndex(@NotNull CharSequence charSequence) {
        return charSequence.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence charSequence, int i2) {
        return i2 >= 0 && charSequence.length() + (-2) >= i2 && Character.isHighSurrogate(charSequence.charAt(i2)) && Character.isLowSurrogate(charSequence.charAt(i2 + 1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C c2, Function0<? extends R> function0) {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(c2);
        return isBlank ? function0.invoke() : c2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C c2, Function0<? extends R> function0) {
        return c2.length() == 0 ? function0.invoke() : c2;
    }

    public static final int indexOf(@NotNull CharSequence charSequence, char c2, int i2, boolean z) {
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c2, i2);
        }
        return indexOfAny(charSequence, new char[]{c2}, i2, z);
    }

    private static final int indexOf$StringsKt__StringsKt(@NotNull CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z, boolean z2) {
        int lastIndex;
        int coerceAtMost;
        int coerceAtLeast;
        IntProgression downTo;
        int coerceAtLeast2;
        int coerceAtMost2;
        if (!z2) {
            coerceAtLeast2 = RangesKt___RangesKt.coerceAtLeast(i2, 0);
            coerceAtMost2 = RangesKt___RangesKt.coerceAtMost(i3, charSequence.length());
            downTo = new (coerceAtLeast2, coerceAtMost2);
        } else {
            lastIndex = getLastIndex(charSequence);
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, lastIndex);
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i3, 0);
            downTo = RangesKt___RangesKt.downTo(coerceAtMost, coerceAtLeast);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int first = downTo.getFirst();
            int last = downTo.getLast();
            int step = downTo.getStep();
            if (step >= 0) {
                if (first > last) {
                    return -1;
                }
            } else if (first < last) {
                return -1;
            }
            while (!StringsKt__StringsJVMKt.regionMatches((String) charSequence2, 0, (String) charSequence, first, charSequence2.length(), z)) {
                if (first == last) {
                    return -1;
                }
                first += step;
            }
            return first;
        }
        int first2 = downTo.getFirst();
        int last2 = downTo.getLast();
        int step2 = downTo.getStep();
        if (step2 >= 0) {
            if (first2 > last2) {
                return -1;
            }
        } else if (first2 < last2) {
            return -1;
        }
        while (!regionMatchesImpl(charSequence2, 0, charSequence, first2, charSequence2.length(), z)) {
            if (first2 == last2) {
                return -1;
            }
            first2 += step2;
        }
        return first2;
    }

    static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z, boolean z2, int i4, Object obj) {
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i2, i3, z, (i4 & 16) != 0 ? false : z2);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c2, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, c2, i2, z);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i2, boolean z) {
        int coerceAtLeast;
        int lastIndex;
        boolean z2;
        char single;
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            single = ArraysKt___ArraysKt.single(cArr);
            return ((String) charSequence).indexOf(single, i2);
        }
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i2, 0);
        lastIndex = getLastIndex(charSequence);
        if (coerceAtLeast > lastIndex) {
            return -1;
        }
        while (true) {
            char charAt = charSequence.charAt(coerceAtLeast);
            int length = cArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    z2 = false;
                    break;
                } else if (CharsKt__CharKt.equals(cArr[i3], charAt, z)) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z2) {
                return coerceAtLeast;
            }
            if (coerceAtLeast == lastIndex) {
                return -1;
            }
            coerceAtLeast++;
        }
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArr, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, cArr, i2, z);
    }

    @InlineOnly
    private static final boolean isEmpty(@NotNull CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(@NotNull CharSequence charSequence) {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(charSequence);
        return !isBlank;
    }

    @InlineOnly
    private static final boolean isNotEmpty(@NotNull CharSequence charSequence) {
        return charSequence.length() > 0;
    }

    @InlineOnly
    private static final boolean isNullOrBlank(@Nullable CharSequence charSequence) {
        boolean isBlank;
        if (charSequence != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(charSequence);
            if (!isBlank) {
                return false;
            }
        }
        return true;
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    @NotNull
    public static final CharIterator iterator(@NotNull final CharSequence charSequence) {
        return new CharIterator() { // from class: kotlin.text.StringsKt__StringsKt$iterator$1
            private int index;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < charSequence.length();
            }

            @Override // kotlin.collections.CharIterator
            public char nextChar() {
                CharSequence charSequence2 = charSequence;
                int i2 = this.index;
                this.index = i2 + 1;
                return charSequence2.charAt(i2);
            }
        };
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, char c2, int i2, boolean z) {
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(c2, i2);
        }
        return lastIndexOfAny(charSequence, new char[]{c2}, i2, z);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c2, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = getLastIndex(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, c2, i2, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i2, boolean z) {
        int lastIndex;
        int coerceAtMost;
        char single;
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            single = ArraysKt___ArraysKt.single(cArr);
            return ((String) charSequence).lastIndexOf(single, i2);
        }
        lastIndex = getLastIndex(charSequence);
        for (coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, lastIndex); coerceAtMost >= 0; coerceAtMost--) {
            char charAt = charSequence.charAt(coerceAtMost);
            int length = cArr.length;
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if (CharsKt__CharKt.equals(cArr[i3], charAt, z)) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z2) {
                return coerceAtMost;
            }
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArr, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = getLastIndex(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, cArr, i2, z);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence charSequence) {
        return splitToSequence$default(charSequence, new String[]{"\r\n", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\r"}, false, 0, 6, (Object) null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence charSequence) {
        List<String> list;
        list = SequencesKt___SequencesKt.toList(lineSequence(charSequence));
        return list;
    }

    @InlineOnly
    private static final boolean matches(@NotNull CharSequence charSequence, Regex regex) {
        return regex.matches(charSequence);
    }

    @InlineOnly
    private static final String orEmpty(@Nullable String str) {
        return str != null ? str : "";
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence charSequence, int i2, char c2) {
        if (i2 >= 0) {
            if (i2 <= charSequence.length()) {
                return charSequence.subSequence(0, charSequence.length());
            }
            StringBuilder sb = new StringBuilder(i2);
            sb.append(charSequence);
            int length = i2 - charSequence.length();
            int i3 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c2);
                    if (i3 == length) {
                        break;
                    }
                    i3++;
                }
            }
            return sb;
        }
        throw new IllegalArgumentException("Desired length " + i2 + " is less than zero.");
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int i2, char c2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            c2 = ' ';
        }
        return padEnd(charSequence, i2, c2);
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence charSequence, int i2, char c2) {
        if (i2 >= 0) {
            if (i2 <= charSequence.length()) {
                return charSequence.subSequence(0, charSequence.length());
            }
            StringBuilder sb = new StringBuilder(i2);
            int length = i2 - charSequence.length();
            int i3 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c2);
                    if (i3 == length) {
                        break;
                    }
                    i3++;
                }
            }
            sb.append(charSequence);
            return sb;
        }
        throw new IllegalArgumentException("Desired length " + i2 + " is less than zero.");
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int i2, char c2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            c2 = ' ';
        }
        return padStart(charSequence, i2, c2);
    }

    private static final Sequence<> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence charSequence, final char[] cArr, int i2, final boolean z, int i3) {
        if (i3 >= 0) {
            return new DelimitedRangesSequence(charSequence, i2, i3, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence2, Integer num) {
                    return invoke(charSequence2, num.intValue());
                }

                @Nullable
                public final Pair<Integer, Integer> invoke(@NotNull CharSequence charSequence2, int i4) {
                    int indexOfAny = StringsKt__StringsKt.indexOfAny(charSequence2, cArr, i4, z);
                    if (indexOfAny < 0) {
                        return null;
                    }
                    return TuplesKt.to(Integer.valueOf(indexOfAny), 1);
                }
            });
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i3 + OrderISVUtil.MONEY_DECIMAL_CHAR).toString());
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArr, int i2, boolean z, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            z = false;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArr, i2, z, i3);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence charSequence, int i2, @NotNull CharSequence charSequence2, int i3, int i4, boolean z) {
        if (i3 < 0 || i2 < 0 || i2 > charSequence.length() - i4 || i3 > charSequence2.length() - i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (!CharsKt__CharKt.equals(charSequence.charAt(i2 + i5), charSequence2.charAt(i3 + i5), z)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        if (startsWith$default(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, int i2, int i3) {
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("End index (" + i3 + ") is less than start index (" + i2 + ").");
        } else if (i3 == i2) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(charSequence.length() - (i3 - i2));
            sb.append(charSequence, 0, i2);
            Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
            sb.append(charSequence, i3, charSequence.length());
            Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
            return sb;
        }
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        if (endsWith$default(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(0, charSequence.length() - charSequence2.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull CharSequence charSequence3) {
        if (charSequence.length() >= charSequence2.length() + charSequence3.length() && startsWith$default(charSequence, charSequence2, false, 2, (Object) null) && endsWith$default(charSequence, charSequence3, false, 2, (Object) null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length() - charSequence3.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replace(charSequence, str);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String str, char c2, @NotNull String str2, @NotNull String str3) {
        int indexOf$default;
        indexOf$default = indexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        return indexOf$default == -1 ? str3 : replaceRange((CharSequence) str, indexOf$default + 1, str.length(), (CharSequence) str2).toString();
    }

    public static /* synthetic */ String replaceAfter$default(String str, char c2, String str2, String str3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str3 = str;
        }
        return replaceAfter(str, c2, str2, str3);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        int lastIndexOf$default;
        lastIndexOf$default = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
        return lastIndexOf$default == -1 ? str4 : replaceRange((CharSequence) str, lastIndexOf$default + str2.length(), str.length(), (CharSequence) str3).toString();
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str4 = str;
        }
        return replaceAfterLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String str, char c2, @NotNull String str2, @NotNull String str3) {
        int indexOf$default;
        indexOf$default = indexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        return indexOf$default == -1 ? str3 : replaceRange((CharSequence) str, 0, indexOf$default, (CharSequence) str2).toString();
    }

    public static /* synthetic */ String replaceBefore$default(String str, char c2, String str2, String str3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str3 = str;
        }
        return replaceBefore(str, c2, str2, str3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String str, char c2, @NotNull String str2, @NotNull String str3) {
        int lastIndexOf$default;
        lastIndexOf$default = lastIndexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        return lastIndexOf$default == -1 ? str3 : replaceRange((CharSequence) str, 0, lastIndexOf$default, (CharSequence) str2).toString();
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, char c2, String str2, String str3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str3 = str;
        }
        return replaceBeforeLast(str, c2, str2, str3);
    }

    @InlineOnly
    private static final String replaceFirst(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replaceFirst(charSequence, str);
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, int i2, int i3, @NotNull CharSequence charSequence2) {
        if (i3 >= i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence, 0, i2);
            Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
            sb.append(charSequence2);
            sb.append(charSequence, i3, charSequence.length());
            Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
            return sb;
        }
        throw new IndexOutOfBoundsException("End index (" + i3 + ") is less than start index (" + i2 + ").");
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i2) {
        Iterable asIterable;
        int collectionSizeOrDefault;
        if (strArr.length == 1) {
            String str = strArr[0];
            if (!(str.length() == 0)) {
                return split$StringsKt__StringsKt(charSequence, str, z, i2);
            }
        }
        asIterable = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, strArr, 0, z, i2, 2, (Object) null));
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(asIterable, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator it = asIterable.iterator();
        while (it.hasNext()) {
            arrayList.add(substring(charSequence, () it.next()));
        }
        return arrayList;
    }

    private static final List<String> split$StringsKt__StringsKt(@NotNull CharSequence charSequence, String str, boolean z, int i2) {
        List<String> listOf;
        int i3 = 0;
        if (i2 >= 0) {
            int indexOf = indexOf(charSequence, str, 0, z);
            if (indexOf != -1 && i2 != 1) {
                boolean z2 = i2 > 0;
                ArrayList arrayList = new ArrayList(z2 ? RangesKt___RangesKt.coerceAtMost(i2, 10) : 10);
                do {
                    arrayList.add(charSequence.subSequence(i3, indexOf).toString());
                    i3 = str.length() + indexOf;
                    if (z2 && arrayList.size() == i2 - 1) {
                        break;
                    }
                    indexOf = indexOf(charSequence, str, i3, z);
                } while (indexOf != -1);
                arrayList.add(charSequence.subSequence(i3, charSequence.length()).toString());
                return arrayList;
            }
            listOf = CollectionsKt__CollectionsJVMKt.listOf(charSequence.toString());
            return listOf;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + OrderISVUtil.MONEY_DECIMAL_CHAR).toString());
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, String[] strArr, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return split(charSequence, strArr, z, i2);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence charSequence, @NotNull String[] strArr, boolean z, int i2) {
        Sequence<String> map;
        map = SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, strArr, 0, z, i2, 2, (Object) null), new Function1<, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull  Var) {
                return StringsKt__StringsKt.substring(charSequence, Var);
            }
        });
        return map;
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] strArr, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return splitToSequence(charSequence, strArr, z, i2);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, char c2, boolean z) {
        return charSequence.length() > 0 && CharsKt__CharKt.equals(charSequence.charAt(0), c2, z);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, c2, z);
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence charSequence, @NotNull  Var) {
        return charSequence.subSequence(Var.getStart().intValue(), Var.getEndInclusive().intValue() + 1);
    }

    @NotNull
    public static final String substring(@NotNull String str, @NotNull  Var) {
        String substring = str.substring(Var.getStart().intValue(), Var.getEndInclusive().intValue() + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return substring;
    }

    static /* synthetic */ String substring$default(CharSequence charSequence, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = charSequence.length();
        }
        return charSequence.subSequence(i2, i3).toString();
    }

    @NotNull
    public static final String substringAfter(@NotNull String str, char c2, @NotNull String str2) {
        int indexOf$default;
        indexOf$default = indexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            return str2;
        }
        String substring = str.substring(indexOf$default + 1, str.length());
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ String substringAfter$default(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return substringAfter(str, c2, str2);
    }

    @NotNull
    public static String substringAfterLast(@NotNull String str, char c2, @NotNull String str2) {
        int lastIndexOf$default;
        lastIndexOf$default = lastIndexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (lastIndexOf$default == -1) {
            return str2;
        }
        String substring = str.substring(lastIndexOf$default + 1, str.length());
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ String substringAfterLast$default(String str, char c2, String str2, int i2, Object obj) {
        String substringAfterLast;
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        substringAfterLast = substringAfterLast(str, c2, str2);
        return substringAfterLast;
    }

    @NotNull
    public static final String substringBefore(@NotNull String str, char c2, @NotNull String str2) {
        int indexOf$default;
        indexOf$default = indexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            return str2;
        }
        String substring = str.substring(0, indexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ String substringBefore$default(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return substringBefore(str, c2, str2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String str, char c2, @NotNull String str2) {
        int lastIndexOf$default;
        lastIndexOf$default = lastIndexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (lastIndexOf$default == -1) {
            return str2;
        }
        String substring = str.substring(0, lastIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return substringBeforeLast(str, c2, str2);
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int length = charSequence.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean booleanValue = function1.invoke(Character.valueOf(charSequence.charAt(!z ? i2 : length))).booleanValue();
            if (z) {
                if (!booleanValue) {
                    break;
                }
                length--;
            } else if (booleanValue) {
                i2++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i2, length + 1);
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (function1.invoke(Character.valueOf(charSequence.charAt(length))).booleanValue());
        return charSequence.subSequence(0, length + 1);
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i2))).booleanValue()) {
                return charSequence.subSequence(i2, charSequence.length());
            }
        }
        return "";
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return contains(charSequence, c2, z);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        boolean endsWith$default;
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default((String) charSequence, (String) charSequence2, false, 2, null);
            return endsWith$default;
        }
        return regionMatchesImpl(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, charSequence2, z);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, str, i2, z);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, collection, i2, z);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = getLastIndex(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, str, i2, z);
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = getLastIndex(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, collection, i2, z);
    }

    public static /* synthetic */ String padEnd$default(String str, int i2, char c2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            c2 = ' ';
        }
        return padEnd(str, i2, c2);
    }

    public static /* synthetic */ String padStart$default(String str, int i2, char c2, int i3, Object obj) {
        String padStart;
        if ((i3 & 2) != 0) {
            c2 = ' ';
        }
        padStart = padStart(str, i2, c2);
        return padStart;
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i2, boolean z, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            z = false;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, strArr, i2, z, i3);
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence charSequence, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        return regex.replace(charSequence, function1);
    }

    public static /* synthetic */ String replaceAfter$default(String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str4 = str;
        }
        return replaceAfter(str, str2, str3, str4);
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, char c2, String str2, String str3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str3 = str;
        }
        return replaceAfterLast(str, c2, str2, str3);
    }

    public static /* synthetic */ String replaceBefore$default(String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str4 = str;
        }
        return replaceBefore(str, str2, str3, str4);
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str4 = str;
        }
        return replaceBeforeLast(str, str2, str3, str4);
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, char[] cArr, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return split(charSequence, cArr, z, i2);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence charSequence, @NotNull char[] cArr, boolean z, int i2) {
        Sequence<String> map;
        map = SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, cArr, 0, z, i2, 2, (Object) null), new Function1<, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull  Var) {
                return StringsKt__StringsKt.substring(charSequence, Var);
            }
        });
        return map;
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArr, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return splitToSequence(charSequence, cArr, z, i2);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        boolean startsWith$default;
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) charSequence2, false, 2, null);
            return startsWith$default;
        }
        return regionMatchesImpl(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, z);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    private static final CharSequence subSequence(@NotNull String str, int i2, int i3) {
        return str.subSequence(i2, i3);
    }

    @InlineOnly
    private static final String substring(@NotNull CharSequence charSequence, int i2, int i3) {
        return charSequence.subSequence(i2, i3).toString();
    }

    public static /* synthetic */ String substringAfter$default(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return substringAfter(str, str2, str3);
    }

    public static /* synthetic */ String substringAfterLast$default(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return substringAfterLast(str, str2, str3);
    }

    public static /* synthetic */ String substringBefore$default(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return substringBefore(str, str2, str3);
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return substringBeforeLast(str, str2, str3);
    }

    private static final Sequence<> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence charSequence, String[] strArr, int i2, final boolean z, int i3) {
        if (i3 >= 0) {
            ArraysKt___ArraysJvmKt.asList(strArr);
            return new DelimitedRangesSequence(charSequence, i2, i3, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: RETURN 
                  (wrap: kotlin.text.DelimitedRangesSequence : 0x0012: CONSTRUCTOR 
                  (r2v0 'charSequence' java.lang.CharSequence)
                  (r4v0 'i2' int)
                  (r6v0 'i3' int)
                  (wrap: kotlin.jvm.functions.Function2<java.lang.CharSequence, java.lang.Integer, kotlin.Pair<? extends java.lang.Integer, ? extends java.lang.Integer>> : 0x000f: CONSTRUCTOR (r3 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r5v0 'z' boolean A[DONT_INLINE]) A[MD:(java.util.List, boolean):void (m), WRAPPED] call: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4.<init>(java.util.List, boolean):void type: CONSTRUCTOR)
                 A[MD:(java.lang.CharSequence, int, int, kotlin.jvm.functions.Function2<? super java.lang.CharSequence, ? super java.lang.Integer, kotlin.Pair<java.lang.Integer, java.lang.Integer>>):void (m), WRAPPED] (LINE:4) call: kotlin.text.DelimitedRangesSequence.<init>(java.lang.CharSequence, int, int, kotlin.jvm.functions.Function2):void type: CONSTRUCTOR)
                 (LINE:4) in method: kotlin.text.StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(java.lang.CharSequence, java.lang.String[], int, boolean, int):kotlin.sequences.Sequence<kotlin.ranges.IntRange>, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:765)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 23 more
                */
            /*
                if (r6 < 0) goto L4
                r0 = 1
                goto L5
            L4:
                r0 = 0
            L5:
                if (r0 == 0) goto L16
                java.util.List r3 = kotlin.collections.ArraysKt.asList(r3)
                kotlin.text.DelimitedRangesSequence r0 = new kotlin.text.DelimitedRangesSequence
                kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4 r1 = new kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4
                r1.<init>()
                r0.<init>(r2, r4, r6, r1)
                return r0
            L16:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Limit must be non-negative, but was "
                r2.append(r3)
                r2.append(r6)
                r3 = 46
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
                java.lang.String r2 = r2.toString()
                r3.<init>(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(java.lang.CharSequence, java.lang.String[], int, boolean, int):kotlin.sequences.Sequence");
        }

        @NotNull
        public static final String replaceAfter(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            int indexOf$default;
            indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            return indexOf$default == -1 ? str4 : replaceRange((CharSequence) str, indexOf$default + str2.length(), str.length(), (CharSequence) str3).toString();
        }

        @NotNull
        public static final String replaceAfterLast(@NotNull String str, char c2, @NotNull String str2, @NotNull String str3) {
            int lastIndexOf$default;
            lastIndexOf$default = lastIndexOf$default((CharSequence) str, c2, 0, false, 6, (Object) null);
            return lastIndexOf$default == -1 ? str3 : replaceRange((CharSequence) str, lastIndexOf$default + 1, str.length(), (CharSequence) str2).toString();
        }

        @NotNull
        public static final String replaceBefore(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            int indexOf$default;
            indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            return indexOf$default == -1 ? str4 : replaceRange((CharSequence) str, 0, indexOf$default, (CharSequence) str3).toString();
        }

        @NotNull
        public static final String replaceBeforeLast(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            int lastIndexOf$default;
            lastIndexOf$default = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            return lastIndexOf$default == -1 ? str4 : replaceRange((CharSequence) str, 0, lastIndexOf$default, (CharSequence) str3).toString();
        }

        static /* synthetic */ List split$default(CharSequence charSequence, Regex regex, int i2, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i2 = 0;
            }
            return regex.split(charSequence, i2);
        }

        public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                z = false;
            }
            return startsWith(charSequence, charSequence2, i2, z);
        }

        @NotNull
        public static final String substring(@NotNull CharSequence charSequence, @NotNull  Var) {
            return charSequence.subSequence(Var.getStart().intValue(), Var.getEndInclusive().intValue() + 1).toString();
        }

        @NotNull
        public static final String substringAfter(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            int indexOf$default;
            indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                return str3;
            }
            String substring = str.substring(indexOf$default + str2.length(), str.length());
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }

        @NotNull
        public static final String substringAfterLast(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            int lastIndexOf$default;
            lastIndexOf$default = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            if (lastIndexOf$default == -1) {
                return str3;
            }
            String substring = str.substring(lastIndexOf$default + str2.length(), str.length());
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }

        @NotNull
        public static final String substringBefore(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            int indexOf$default;
            indexOf$default = indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                return str3;
            }
            String substring = str.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }

        @NotNull
        public static final String substringBeforeLast(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            int lastIndexOf$default;
            lastIndexOf$default = lastIndexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null);
            if (lastIndexOf$default == -1) {
                return str3;
            }
            String substring = str.substring(0, lastIndexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }

        public static final boolean contains(@NotNull CharSequence charSequence, char c2, boolean z) {
            int indexOf$default;
            indexOf$default = indexOf$default(charSequence, c2, 0, z, 2, (Object) null);
            return indexOf$default >= 0;
        }

        public static final int indexOf(@NotNull CharSequence charSequence, @NotNull String str, int i2, boolean z) {
            if (!z && (charSequence instanceof String)) {
                return ((String) charSequence).indexOf(str, i2);
            }
            return indexOf$StringsKt__StringsKt$default(charSequence, str, i2, charSequence.length(), z, false, 16, null);
        }

        public static final int lastIndexOf(@NotNull CharSequence charSequence, @NotNull String str, int i2, boolean z) {
            if (!z && (charSequence instanceof String)) {
                return ((String) charSequence).lastIndexOf(str, i2);
            }
            return indexOf$StringsKt__StringsKt(charSequence, str, i2, 0, z, true);
        }

        @NotNull
        public static final String removePrefix(@NotNull String str, @NotNull CharSequence charSequence) {
            if (startsWith$default((CharSequence) str, charSequence, false, 2, (Object) null)) {
                String substring = str.substring(charSequence.length());
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
            return str;
        }

        @NotNull
        public static final String removeSuffix(@NotNull String str, @NotNull CharSequence charSequence) {
            if (endsWith$default((CharSequence) str, charSequence, false, 2, (Object) null)) {
                String substring = str.substring(0, str.length() - charSequence.length());
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                return substring;
            }
            return str;
        }

        @NotNull
        public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
            if (str.length() >= charSequence.length() + charSequence2.length() && startsWith$default((CharSequence) str, charSequence, false, 2, (Object) null) && endsWith$default((CharSequence) str, charSequence2, false, 2, (Object) null)) {
                String substring = str.substring(charSequence.length(), str.length() - charSequence2.length());
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                return substring;
            }
            return str;
        }

        @InlineOnly
        private static final String trim(@NotNull String str) {
            CharSequence trim;
            if (str != null) {
                trim = trim((CharSequence) str);
                return trim.toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @InlineOnly
        private static final String trimEnd(@NotNull String str) {
            if (str != null) {
                return trimEnd((CharSequence) str).toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @InlineOnly
        private static final String trimStart(@NotNull String str) {
            CharSequence trimStart;
            if (str != null) {
                trimStart = trimStart((CharSequence) str);
                return trimStart.toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @InlineOnly
        private static final boolean contains(@NotNull CharSequence charSequence, Regex regex) {
            return regex.containsMatchIn(charSequence);
        }

        public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, int i2, boolean z) {
            if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
                return StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) charSequence2, i2, false, 4, null);
            }
            return regionMatchesImpl(charSequence, i2, charSequence2, 0, charSequence2.length(), z);
        }

        @NotNull
        public static final String trim(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
            int length = str.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean booleanValue = function1.invoke(Character.valueOf(str.charAt(!z ? i2 : length))).booleanValue();
                if (z) {
                    if (!booleanValue) {
                        break;
                    }
                    length--;
                } else if (booleanValue) {
                    i2++;
                } else {
                    z = true;
                }
            }
            return str.subSequence(i2, length + 1).toString();
        }

        @NotNull
        public static final String trimEnd(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
            CharSequence charSequence;
            int length = str.length();
            while (true) {
                length--;
                if (length < 0) {
                    charSequence = "";
                    break;
                } else if (!function1.invoke(Character.valueOf(str.charAt(length))).booleanValue()) {
                    charSequence = str.subSequence(0, length + 1);
                    break;
                }
            }
            return charSequence.toString();
        }

        @NotNull
        public static final String trimStart(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
            CharSequence charSequence;
            int length = str.length();
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    charSequence = "";
                    break;
                } else if (!function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
                    charSequence = str.subSequence(i2, str.length());
                    break;
                } else {
                    i2++;
                }
            }
            return charSequence.toString();
        }

        @InlineOnly
        private static final String removeRange(@NotNull String str, int i2, int i3) {
            if (str != null) {
                return removeRange((CharSequence) str, i2, i3).toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @NotNull
        public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
            return removeSurrounding(charSequence, charSequence2, charSequence2);
        }

        @InlineOnly
        private static final String replaceRange(@NotNull String str, int i2, int i3, CharSequence charSequence) {
            if (str != null) {
                return replaceRange((CharSequence) str, i2, i3, charSequence).toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @NotNull
        public static final CharSequence removeRange(@NotNull CharSequence charSequence, @NotNull  Var) {
            return removeRange(charSequence, Var.getStart().intValue(), Var.getEndInclusive().intValue() + 1);
        }

        @NotNull
        public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence charSequence) {
            return removeSurrounding(str, charSequence, charSequence);
        }

        @NotNull
        public static final CharSequence replaceRange(@NotNull CharSequence charSequence, @NotNull  Var, @NotNull CharSequence charSequence2) {
            return replaceRange(charSequence, Var.getStart().intValue(), Var.getEndInclusive().intValue() + 1, charSequence2);
        }

        public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i2, boolean z) {
            Integer first;
            Pair<Integer, String> findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(charSequence, collection, i2, z, false);
            if (findAnyOf$StringsKt__StringsKt == null || (first = findAnyOf$StringsKt__StringsKt.getFirst()) == null) {
                return -1;
            }
            return first.intValue();
        }

        public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i2, boolean z) {
            Integer first;
            Pair<Integer, String> findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(charSequence, collection, i2, z, true);
            if (findAnyOf$StringsKt__StringsKt == null || (first = findAnyOf$StringsKt__StringsKt.getFirst()) == null) {
                return -1;
            }
            return first.intValue();
        }

        @NotNull
        public static final String padEnd(@NotNull String str, int i2, char c2) {
            return padEnd((CharSequence) str, i2, c2).toString();
        }

        @NotNull
        public static String padStart(@NotNull String str, int i2, char c2) {
            return padStart((CharSequence) str, i2, c2).toString();
        }

        @InlineOnly
        private static final String removeRange(@NotNull String str,  Var) {
            if (str != null) {
                return removeRange((CharSequence) str, Var).toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @InlineOnly
        private static final String replaceRange(@NotNull String str,  Var, CharSequence charSequence) {
            if (str != null) {
                return replaceRange((CharSequence) str, Var, charSequence).toString();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }

        @NotNull
        public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull char... cArr) {
            boolean contains;
            int length = charSequence.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                contains = ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(!z ? i2 : length));
                if (z) {
                    if (!contains) {
                        break;
                    }
                    length--;
                } else if (contains) {
                    i2++;
                } else {
                    z = true;
                }
            }
            return charSequence.subSequence(i2, length + 1);
        }

        @NotNull
        public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull char... cArr) {
            boolean contains;
            int length = charSequence.length();
            do {
                length--;
                if (length < 0) {
                    return "";
                }
                contains = ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(length));
            } while (contains);
            return charSequence.subSequence(0, length + 1);
        }

        @NotNull
        public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull char... cArr) {
            boolean contains;
            int length = charSequence.length();
            for (int i2 = 0; i2 < length; i2++) {
                contains = ArraysKt___ArraysKt.contains(cArr, charSequence.charAt(i2));
                if (!contains) {
                    return charSequence.subSequence(i2, charSequence.length());
                }
            }
            return "";
        }

        @NotNull
        public static final List<String> split(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i2) {
            Iterable asIterable;
            int collectionSizeOrDefault;
            if (cArr.length == 1) {
                return split$StringsKt__StringsKt(charSequence, String.valueOf(cArr[0]), z, i2);
            }
            asIterable = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, cArr, 0, z, i2, 2, (Object) null));
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(asIterable, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator it = asIterable.iterator();
            while (it.hasNext()) {
                arrayList.add(substring(charSequence, () it.next()));
            }
            return arrayList;
        }

        @NotNull
        public static final String trim(@NotNull String str, @NotNull char... cArr) {
            boolean contains;
            int length = str.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                contains = ArraysKt___ArraysKt.contains(cArr, str.charAt(!z ? i2 : length));
                if (z) {
                    if (!contains) {
                        break;
                    }
                    length--;
                } else if (contains) {
                    i2++;
                } else {
                    z = true;
                }
            }
            return str.subSequence(i2, length + 1).toString();
        }

        @NotNull
        public static final String trimEnd(@NotNull String str, @NotNull char... cArr) {
            CharSequence charSequence;
            boolean contains;
            int length = str.length();
            while (true) {
                length--;
                if (length < 0) {
                    charSequence = "";
                    break;
                }
                contains = ArraysKt___ArraysKt.contains(cArr, str.charAt(length));
                if (!contains) {
                    charSequence = str.subSequence(0, length + 1);
                    break;
                }
            }
            return charSequence.toString();
        }

        @NotNull
        public static final String trimStart(@NotNull String str, @NotNull char... cArr) {
            CharSequence charSequence;
            boolean contains;
            int length = str.length();
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    charSequence = "";
                    break;
                }
                contains = ArraysKt___ArraysKt.contains(cArr, str.charAt(i2));
                if (!contains) {
                    charSequence = str.subSequence(i2, str.length());
                    break;
                }
                i2++;
            }
            return charSequence.toString();
        }

        @InlineOnly
        private static final List<String> split(@NotNull CharSequence charSequence, Regex regex, int i2) {
            return regex.split(charSequence, i2);
        }

        @NotNull
        public static CharSequence trim(@NotNull CharSequence charSequence) {
            int length = charSequence.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean isWhitespace = CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(!z ? i2 : length));
                if (z) {
                    if (!isWhitespace) {
                        break;
                    }
                    length--;
                } else if (isWhitespace) {
                    i2++;
                } else {
                    z = true;
                }
            }
            return charSequence.subSequence(i2, length + 1);
        }

        @NotNull
        public static final CharSequence trimEnd(@NotNull CharSequence charSequence) {
            int length = charSequence.length();
            do {
                length--;
                if (length < 0) {
                    return "";
                }
            } while (CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(length)));
            return charSequence.subSequence(0, length + 1);
        }

        @NotNull
        public static CharSequence trimStart(@NotNull CharSequence charSequence) {
            int length = charSequence.length();
            for (int i2 = 0; i2 < length; i2++) {
                if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i2))) {
                    return charSequence.subSequence(i2, charSequence.length());
                }
            }
            return "";
        }
    }
