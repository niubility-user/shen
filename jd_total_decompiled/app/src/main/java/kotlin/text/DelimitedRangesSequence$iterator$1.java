package kotlin.text;

import ..;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u0096\u0002\u00a2\u0006\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R\"\u0010\u001d\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\r\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011\u00a8\u0006 "}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "", "calcNext", "()V", "next", "()Lkotlin/ranges/IntRange;", "", "hasNext", "()Z", "", "nextState", "I", "getNextState", "()I", "setNextState", "(I)V", "counter", "getCounter", "setCounter", "nextItem", "Lkotlin/ranges/IntRange;", "getNextItem", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<>, KMappedMarker {
    private int counter;
    private int currentStartIndex;
    @Nullable
    private  nextItem;
    private int nextSearchIndex;
    private int nextState = -1;
    final /* synthetic */ DelimitedRangesSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        int i2;
        CharSequence charSequence;
        int coerceIn;
        this.this$0 = delimitedRangesSequence;
        i2 = delimitedRangesSequence.startIndex;
        charSequence = delimitedRangesSequence.input;
        coerceIn = RangesKt___RangesKt.coerceIn(i2, 0, charSequence.length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r0 < r4) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void calcNext() {
        int i2;
        CharSequence charSequence;
        Function2 function2;
        CharSequence charSequence2;
         until;
        CharSequence charSequence3;
        int lastIndex;
        CharSequence charSequence4;
        int lastIndex2;
        int i3;
        if (this.nextSearchIndex >= 0) {
            i2 = this.this$0.limit;
            if (i2 > 0) {
                int i4 = this.counter + 1;
                this.counter = i4;
                i3 = this.this$0.limit;
            }
            int i5 = this.nextSearchIndex;
            charSequence = this.this$0.input;
            if (i5 <= charSequence.length()) {
                function2 = this.this$0.getNextMatch;
                charSequence2 = this.this$0.input;
                Pair pair = (Pair) function2.invoke(charSequence2, Integer.valueOf(this.nextSearchIndex));
                if (pair == null) {
                    int i6 = this.currentStartIndex;
                    charSequence3 = this.this$0.input;
                    lastIndex = StringsKt__StringsKt.getLastIndex(charSequence3);
                    this.nextItem = new (i6, lastIndex);
                    this.nextSearchIndex = -1;
                } else {
                    int intValue = ((Number) pair.component1()).intValue();
                    int intValue2 = ((Number) pair.component2()).intValue();
                    until = RangesKt___RangesKt.until(this.currentStartIndex, intValue);
                    this.nextItem = until;
                    int i7 = intValue + intValue2;
                    this.currentStartIndex = i7;
                    this.nextSearchIndex = i7 + (intValue2 == 0 ? 1 : 0);
                }
                this.nextState = 1;
                return;
            }
            int i8 = this.currentStartIndex;
            charSequence4 = this.this$0.input;
            lastIndex2 = StringsKt__StringsKt.getLastIndex(charSequence4);
            this.nextItem = new (i8, lastIndex2);
            this.nextSearchIndex = -1;
            this.nextState = 1;
            return;
        }
        this.nextState = 0;
        this.nextItem = null;
    }

    public final int getCounter() {
        return this.counter;
    }

    public final int getCurrentStartIndex() {
        return this.currentStartIndex;
    }

    @Nullable
    public final  getNextItem() {
        return this.nextItem;
    }

    public final int getNextSearchIndex() {
        return this.nextSearchIndex;
    }

    public final int getNextState() {
        return this.nextState;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setCounter(int i2) {
        this.counter = i2;
    }

    public final void setCurrentStartIndex(int i2) {
        this.currentStartIndex = i2;
    }

    public final void setNextItem(@Nullable  Var) {
        this.nextItem = Var;
    }

    public final void setNextSearchIndex(int i2) {
        this.nextSearchIndex = i2;
    }

    public final void setNextState(int i2) {
        this.nextState = i2;
    }

    @Override // java.util.Iterator
    @NotNull
    public  next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
             Var = this.nextItem;
            if (Var != null) {
                this.nextItem = null;
                this.nextState = -1;
                return Var;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        throw new NoSuchElementException();
    }
}
