package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010(\n\u0002\b\u0006\bg\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0000H&\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lkotlin/collections/Grouping;", "T", "K", "", "", "sourceIterator", "()Ljava/util/Iterator;", "element", "keyOf", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface Grouping<T, K> {
    K keyOf(T element);

    @NotNull
    Iterator<T> sourceIterator();
}
