package kotlin.collections;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt___UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a-\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00010\u0001\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001aI\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0005*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0001\u00a2\u0006\u0004\b\u0007\u0010\b\u001a+\u0010\n\u001a\u00020\t*\b\u0012\u0002\b\u0003\u0018\u00010\u0001H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u001a:\u0010\u000f\u001a\u00028\u0001\"\u0010\b\u0000\u0010\f*\u0006\u0012\u0002\b\u00030\u0001*\u00028\u0001\"\u0004\b\u0001\u0010\u0005*\u00028\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0087\b\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a1\u0010\u0014\u001a\u00020\t\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001H\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a!\u0010\u0018\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001H\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a?\u0010!\u001a\u00020\u001e\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\n\u0010\u001b\u001a\u00060\u0019j\u0002`\u001a2\u0010\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010 \u00a8\u0006\""}, d2 = {"T", "", "", "flatten", "([[Ljava/lang/Object;)Ljava/util/List;", "R", "Lkotlin/Pair;", IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "([Lkotlin/Pair;)Lkotlin/Pair;", "", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "C", "Lkotlin/Function0;", "defaultValue", "ifEmpty", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepEqualsImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringImpl", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "result", "", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "contentDeepToStringInternal", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/ArraysKt")
/* loaded from: classes11.dex */
public class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepEquals")
    public static final <T> boolean contentDeepEquals(@NotNull T[] tArr, @NotNull T[] tArr2) {
        boolean m626contentEqualsus8wMrg;
        boolean m623contentEqualsctEhBpI;
        boolean m625contentEqualsmazbYpA;
        boolean m624contentEqualskdPth3s;
        if (tArr == tArr2) {
            return true;
        }
        if (tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            T t = tArr[i2];
            T t2 = tArr2[i2];
            if (t != t2) {
                if (t == null || t2 == null) {
                    return false;
                }
                if ((t instanceof Object[]) && (t2 instanceof Object[])) {
                    if (!contentDeepEquals((Object[]) t, (Object[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof byte[]) && (t2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) t, (byte[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof short[]) && (t2 instanceof short[])) {
                    if (!Arrays.equals((short[]) t, (short[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof int[]) && (t2 instanceof int[])) {
                    if (!Arrays.equals((int[]) t, (int[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof long[]) && (t2 instanceof long[])) {
                    if (!Arrays.equals((long[]) t, (long[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof float[]) && (t2 instanceof float[])) {
                    if (!Arrays.equals((float[]) t, (float[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof double[]) && (t2 instanceof double[])) {
                    if (!Arrays.equals((double[]) t, (double[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof char[]) && (t2 instanceof char[])) {
                    if (!Arrays.equals((char[]) t, (char[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof boolean[]) && (t2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) t, (boolean[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof UByteArray) && (t2 instanceof UByteArray)) {
                    m624contentEqualskdPth3s = UArraysKt___UArraysKt.m624contentEqualskdPth3s(((UByteArray) t).getStorage(), ((UByteArray) t2).getStorage());
                    if (!m624contentEqualskdPth3s) {
                        return false;
                    }
                } else if ((t instanceof UShortArray) && (t2 instanceof UShortArray)) {
                    m625contentEqualsmazbYpA = UArraysKt___UArraysKt.m625contentEqualsmazbYpA(((UShortArray) t).getStorage(), ((UShortArray) t2).getStorage());
                    if (!m625contentEqualsmazbYpA) {
                        return false;
                    }
                } else if ((t instanceof UIntArray) && (t2 instanceof UIntArray)) {
                    m623contentEqualsctEhBpI = UArraysKt___UArraysKt.m623contentEqualsctEhBpI(((UIntArray) t).getStorage(), ((UIntArray) t2).getStorage());
                    if (!m623contentEqualsctEhBpI) {
                        return false;
                    }
                } else if ((t instanceof ULongArray) && (t2 instanceof ULongArray)) {
                    m626contentEqualsus8wMrg = UArraysKt___UArraysKt.m626contentEqualsus8wMrg(((ULongArray) t).getStorage(), ((ULongArray) t2).getStorage());
                    if (!m626contentEqualsus8wMrg) {
                        return false;
                    }
                } else if ((!Intrinsics.areEqual(t, t2)) != false) {
                    return false;
                }
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "contentDeepToString")
    @NotNull
    @PublishedApi
    public static final <T> String contentDeepToString(@NotNull T[] tArr) {
        int coerceAtMost;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(tArr.length, 429496729);
        StringBuilder sb = new StringBuilder((coerceAtMost * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(tArr, sb, new ArrayList());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).\u2026builderAction).toString()");
        return sb2;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(@NotNull T[] tArr, StringBuilder sb, List<Object[]> list) {
        int lastIndex;
        String m633contentToStringQwZRm1k;
        String m631contentToStringajY9A;
        String m634contentToStringrL5Bavg;
        String m632contentToStringGBYM_sE;
        if (list.contains(tArr)) {
            sb.append("[...]");
            return;
        }
        list.add(tArr);
        sb.append('[');
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(", ");
            }
            T t = tArr[i2];
            if (t == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else if (t instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt((Object[]) t, sb, list);
            } else if (t instanceof byte[]) {
                String arrays = Arrays.toString((byte[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
                sb.append(arrays);
            } else if (t instanceof short[]) {
                String arrays2 = Arrays.toString((short[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays2, "java.util.Arrays.toString(this)");
                sb.append(arrays2);
            } else if (t instanceof int[]) {
                String arrays3 = Arrays.toString((int[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays3, "java.util.Arrays.toString(this)");
                sb.append(arrays3);
            } else if (t instanceof long[]) {
                String arrays4 = Arrays.toString((long[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays4, "java.util.Arrays.toString(this)");
                sb.append(arrays4);
            } else if (t instanceof float[]) {
                String arrays5 = Arrays.toString((float[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays5, "java.util.Arrays.toString(this)");
                sb.append(arrays5);
            } else if (t instanceof double[]) {
                String arrays6 = Arrays.toString((double[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays6, "java.util.Arrays.toString(this)");
                sb.append(arrays6);
            } else if (t instanceof char[]) {
                String arrays7 = Arrays.toString((char[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays7, "java.util.Arrays.toString(this)");
                sb.append(arrays7);
            } else if (t instanceof boolean[]) {
                String arrays8 = Arrays.toString((boolean[]) t);
                Intrinsics.checkExpressionValueIsNotNull(arrays8, "java.util.Arrays.toString(this)");
                sb.append(arrays8);
            } else if (t instanceof UByteArray) {
                m632contentToStringGBYM_sE = UArraysKt___UArraysKt.m632contentToStringGBYM_sE(((UByteArray) t).getStorage());
                sb.append(m632contentToStringGBYM_sE);
            } else if (t instanceof UShortArray) {
                m634contentToStringrL5Bavg = UArraysKt___UArraysKt.m634contentToStringrL5Bavg(((UShortArray) t).getStorage());
                sb.append(m634contentToStringrL5Bavg);
            } else if (t instanceof UIntArray) {
                m631contentToStringajY9A = UArraysKt___UArraysKt.m631contentToStringajY9A(((UIntArray) t).getStorage());
                sb.append(m631contentToStringajY9A);
            } else if (t instanceof ULongArray) {
                m633contentToStringQwZRm1k = UArraysKt___UArraysKt.m633contentToStringQwZRm1k(((ULongArray) t).getStorage());
                sb.append(m633contentToStringQwZRm1k);
            } else {
                sb.append(t.toString());
            }
        }
        sb.append(']');
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        list.remove(lastIndex);
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] tArr) {
        int i2 = 0;
        for (T[] tArr2 : tArr) {
            i2 += tArr2.length;
        }
        ArrayList arrayList = new ArrayList(i2);
        for (T[] tArr3 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, tArr3);
        }
        return arrayList;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Object[] objArr, Function0 function0) {
        return objArr.length == 0 ? function0.invoke() : objArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable Object[] objArr) {
        if (objArr != null) {
            if (!(objArr.length == 0)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] pairArr) {
        ArrayList arrayList = new ArrayList(pairArr.length);
        ArrayList arrayList2 = new ArrayList(pairArr.length);
        for (Pair<? extends T, ? extends R> pair : pairArr) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
