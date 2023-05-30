package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ChannelsKt {
    @NotNull
    public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object all(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.all(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object any(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V> Object associate(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associate(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, M extends Map<? super K, ? super E>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m2, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m2, @NotNull Function1<? super E, ? extends Pair<? extends K, ? extends V>> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateTo(receiveChannel, m2, function1, continuation);
    }

    @PublishedApi
    public static final void cancelConsumed(@NotNull ReceiveChannel<?> receiveChannel, @Nullable Throwable th) {
        ChannelsKt__Channels_commonKt.cancelConsumed(receiveChannel, th);
    }

    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return (R) ChannelsKt__Channels_commonKt.consume(broadcastChannel, function1);
    }

    @ObsoleteCoroutinesApi
    @Nullable
    public static final <E> Object consumeEach(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(broadcastChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object consumeEachIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super IndexedValue<? extends E>, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEachIndexed(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        return ChannelsKt__Channels_commonKt.consumes(receiveChannel);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        return ChannelsKt__Channels_commonKt.consumesAll(receiveChannelArr);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object count(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> distinct(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.distinct(receiveChannel);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> drop(@NotNull ReceiveChannel<? extends E> receiveChannel, int i2, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.drop(receiveChannel, i2, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> dropWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.dropWhile(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object elementAt(@NotNull ReceiveChannel<? extends E> receiveChannel, int i2, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAt(receiveChannel, i2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object elementAtOrElse(@NotNull ReceiveChannel<? extends E> receiveChannel, int i2, @NotNull Function1<? super Integer, ? extends E> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrElse(receiveChannel, i2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object elementAtOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, int i2, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.elementAtOrNull(receiveChannel, i2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.filter(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> filterIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends Collection<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> filterNot(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.filterNot(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.filterNotNull(receiveChannel);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends Collection<? super E>> Object filterNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotNullTo(receiveChannel, c2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends Collection<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends Collection<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object find(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.find(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object findLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.findLast(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object first(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object firstOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<R> flatMap(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.flatMap(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R> Object fold(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function2<? super R, ? super E, ? extends R> function2, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.fold(receiveChannel, r, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R> Object foldIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, R r, @NotNull Function3<? super Integer, ? super R, ? super E, ? extends R> function3, @NotNull Continuation<? super R> continuation) {
        return ChannelsKt__Channels_commonKt.foldIndexed(receiveChannel, r, function3, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super Map<K, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, M extends Map<? super K, List<E>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m2, @NotNull Function1<? super E, ? extends K> function1, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object indexOf(@NotNull ReceiveChannel<? extends E> receiveChannel, E e2, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOf(receiveChannel, e2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object indexOfFirst(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfFirst(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object indexOfLast(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.indexOfLast(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object last(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object lastIndexOf(@NotNull ReceiveChannel<? extends E> receiveChannel, E e2, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.lastIndexOf(receiveChannel, e2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object lastOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.map(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapIndexedNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends Collection<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends Collection<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends Collection<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R extends Comparable<? super R>> Object maxBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.maxBy(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object maxWith(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Comparator<? super E> comparator, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.maxWith(receiveChannel, comparator, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R extends Comparable<? super R>> Object minBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.minBy(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object minWith(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Comparator<? super E> comparator, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.minWith(receiveChannel, comparator, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object none(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, continuation);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <E> SelectClause1<E> onReceiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.onReceiveOrNull(receiveChannel);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object partition(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Pair<? extends List<? extends E>, ? extends List<? extends E>>> continuation) {
        return ChannelsKt__Channels_commonKt.partition(receiveChannel, function1, continuation);
    }

    @ExperimentalCoroutinesApi
    @Nullable
    public static final <E> Object receiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.receiveOrNull(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <S, E extends S> Object reduce(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function2<? super S, ? super E, ? extends S> function2, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduce(receiveChannel, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <S, E extends S> Object reduceIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function3<? super Integer, ? super S, ? super E, ? extends S> function3, @NotNull Continuation<? super S> continuation) {
        return ChannelsKt__Channels_commonKt.reduceIndexed(receiveChannel, function3, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> requireNoNulls(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.requireNoNulls(receiveChannel);
    }

    public static final <E> void sendBlocking(@NotNull SendChannel<? super E> sendChannel, E e2) {
        ChannelsKt__ChannelsKt.sendBlocking(sendChannel, e2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object single(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object singleOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object sumBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Integer> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.sumBy(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object sumByDouble(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Double> function1, @NotNull Continuation<? super Double> continuation) {
        return ChannelsKt__Channels_commonKt.sumByDouble(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> take(@NotNull ReceiveChannel<? extends E> receiveChannel, int i2, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.take(receiveChannel, i2, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<E> takeWhile(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__Channels_commonKt.takeWhile(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object toChannel(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.toChannel(receiveChannel, c2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends Collection<? super E>> Object toCollection(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.toCollection(receiveChannel, c2, continuation);
    }

    @Nullable
    public static final <E> Object toList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.toList(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull M m2, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.toMap(receiveChannel, m2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toMutableList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<E>> continuation) {
        return ChannelsKt__Channels_commonKt.toMutableList(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt__Channels_commonKt.toMutableSet(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object toSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.toSet(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E> ReceiveChannel<IndexedValue<E>> withIndex(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext) {
        return ChannelsKt__Channels_commonKt.withIndex(receiveChannel, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R> ReceiveChannel<Pair<E, R>> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2) {
        return ChannelsKt__Channels_commonKt.zip(receiveChannel, receiveChannel2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object any(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.any(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V> Object associateBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.associateBy(receiveChannel, function1, function12, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V, M extends Map<? super K, ? super V>> Object associateByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m2, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.associateByTo(receiveChannel, m2, function1, function12, continuation);
    }

    @ExperimentalCoroutinesApi
    public static final <E, R> R consume(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return (R) ChannelsKt__Channels_commonKt.consume(receiveChannel, function1);
    }

    @ExperimentalCoroutinesApi
    @Nullable
    public static final <E> Object consumeEach(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object count(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Integer> continuation) {
        return ChannelsKt__Channels_commonKt.count(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object filterIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, Boolean> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterIndexedTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object filterNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotNullTo(receiveChannel, c2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object filterNotTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterNotTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object filterTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.filterTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object first(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.first(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object firstOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.firstOrNull(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V> Object groupBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super Map<K, ? extends List<? extends V>>> continuation) {
        return ChannelsKt__Channels_commonKt.groupBy(receiveChannel, function1, function12, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, K, V, M extends Map<? super K, List<V>>> Object groupByTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull M m2, @NotNull Function1<? super E, ? extends K> function1, @NotNull Function1<? super E, ? extends V> function12, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__Channels_commonKt.groupByTo(receiveChannel, m2, function1, function12, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object last(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.last(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object lastOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.lastOrNull(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends SendChannel<? super R>> Object mapIndexedTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function2<? super Integer, ? super E, ? extends R> function2, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapIndexedTo(receiveChannel, c2, function2, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends SendChannel<? super R>> Object mapNotNullTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapNotNullTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E, R, C extends SendChannel<? super R>> Object mapTo(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c2, @NotNull Function1<? super E, ? extends R> function1, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__Channels_commonKt.mapTo(receiveChannel, c2, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object none(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super Boolean> continuation) {
        return ChannelsKt__Channels_commonKt.none(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object single(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.single(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <E> Object singleOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Boolean> function1, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.singleOrNull(receiveChannel, function1, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @Nullable
    public static final <K, V> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt__Channels_commonKt.toMap(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4")
    @NotNull
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        return ChannelsKt__Channels_commonKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }
}
