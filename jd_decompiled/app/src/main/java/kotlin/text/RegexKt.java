package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0014\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0082\b\u00a2\u0006\u0004\b\t\u0010\n\u001a%\u0010\u0010\u001a\u0004\u0018\u00010\u000f*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u0013\u0010\u0016\u001a\u00020\u0015*\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\u001b\u0010\u0016\u001a\u00020\u0015*\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"", "Lkotlin/text/FlagEnum;", "", "toInt", "(Ljava/lang/Iterable;)I", "", "T", "value", "", "fromInt", "(I)Ljava/util/Set;", "Ljava/util/regex/Matcher;", "from", "", "input", "Lkotlin/text/MatchResult;", "findNext", "(Ljava/util/regex/Matcher;ILjava/lang/CharSequence;)Lkotlin/text/MatchResult;", "matchEntire", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)Lkotlin/text/MatchResult;", "Ljava/util/regex/MatchResult;", "Lkotlin/ranges/IntRange;", "range", "(Ljava/util/regex/MatchResult;)Lkotlin/ranges/IntRange;", "groupIndex", "(Ljava/util/regex/MatchResult;I)Lkotlin/ranges/IntRange;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class RegexKt {
    public static final /* synthetic */ MatchResult access$findNext(Matcher matcher, int i2, CharSequence charSequence) {
        return findNext(matcher, i2, charSequence);
    }

    public static final /* synthetic */ MatchResult access$matchEntire(Matcher matcher, CharSequence charSequence) {
        return matchEntire(matcher, charSequence);
    }

    public static final /* synthetic */ int access$toInt(Iterable iterable) {
        return toInt(iterable);
    }

    public static final MatchResult findNext(@NotNull Matcher matcher, int i2, CharSequence charSequence) {
        if (matcher.find(i2)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final /* synthetic */ <T extends Enum<T> & FlagEnum> Set<T> fromInt(final int i2) {
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet allOf = EnumSet.allOf(Enum.class);
        CollectionsKt__MutableCollectionsKt.retainAll(allOf, new Function1<T, Boolean>() { // from class: kotlin.text.RegexKt$fromInt$$inlined$apply$lambda$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Enum) obj));
            }

            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            public final boolean invoke(Enum r3) {
                FlagEnum flagEnum = (FlagEnum) r3;
                return (i2 & flagEnum.getMask()) == flagEnum.getValue();
            }
        });
        Set<T> unmodifiableSet = Collections.unmodifiableSet(allOf);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiable\u2026mask == it.value }\n    })");
        return unmodifiableSet;
    }

    public static final MatchResult matchEntire(@NotNull Matcher matcher, CharSequence charSequence) {
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final IntRange range(@NotNull java.util.regex.MatchResult matchResult) {
        IntRange until;
        until = RangesKt___RangesKt.until(matchResult.start(), matchResult.end());
        return until;
    }

    public static final int toInt(@NotNull Iterable<? extends FlagEnum> iterable) {
        Iterator<? extends FlagEnum> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 |= it.next().getValue();
        }
        return i2;
    }

    public static final IntRange range(@NotNull java.util.regex.MatchResult matchResult, int i2) {
        IntRange until;
        until = RangesKt___RangesKt.until(matchResult.start(i2), matchResult.end(i2));
        return until;
    }
}
