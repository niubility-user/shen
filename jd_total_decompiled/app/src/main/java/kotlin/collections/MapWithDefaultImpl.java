package kotlin.collections;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B>\u0012\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*\u0012!\u0010(\u001a\u001d\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00028\u00010%\u00a2\u0006\u0004\b/\u00100J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0012\u0010\bJ\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b\u0014\u0010\bJ\u001a\u0010\u0015\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0016R(\u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00190\u00188V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001e\u001a\u00020\t8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u000bR\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010\u001f8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u00188V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u001bR1\u0010(\u001a\u001d\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00028\u00010%8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R(\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*8\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\u00a8\u00061"}, d2 = {"Lkotlin/collections/MapWithDefaultImpl;", "K", "V", "Lkotlin/collections/MapWithDefault;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "key", "containsKey", "value", "containsValue", IMantoServerRequester.GET, "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrImplicitDefault", "", "", "getEntries", "()Ljava/util/Set;", "entries", "getSize", ApkDownloadTable.FIELD_SIZE, "", "getValues", "()Ljava/util/Collection;", "values", "getKeys", "keys", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "default", "Lkotlin/jvm/functions/Function1;", "", "map", "Ljava/util/Map;", "getMap", "()Ljava/util/Map;", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class MapWithDefaultImpl<K, V> implements MapWithDefault<K, V> {
    private final Function1<K, V> default;
    @NotNull
    private final Map<K, V> map;

    /* JADX WARN: Multi-variable type inference failed */
    public MapWithDefaultImpl(@NotNull Map<K, ? extends V> map, @NotNull Function1<? super K, ? extends V> function1) {
        this.map = map;
        this.default = function1;
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return getMap().containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return getMap().containsValue(value);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object other) {
        return getMap().equals(other);
    }

    @Override // java.util.Map
    @Nullable
    public V get(Object key) {
        return getMap().get(key);
    }

    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return getMap().entrySet();
    }

    @NotNull
    public Set<K> getKeys() {
        return getMap().keySet();
    }

    @Override // kotlin.collections.MapWithDefault
    @NotNull
    public Map<K, V> getMap() {
        return this.map;
    }

    @Override // kotlin.collections.MapWithDefault
    public V getOrImplicitDefault(K key) {
        Map<K, V> map = getMap();
        V v = map.get(key);
        return (v != null || map.containsKey(key)) ? v : this.default.invoke(key);
    }

    public int getSize() {
        return getMap().size();
    }

    @NotNull
    public Collection<V> getValues() {
        return getMap().values();
    }

    @Override // java.util.Map
    public int hashCode() {
        return getMap().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return getMap().isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public V put(K k2, V v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public String toString() {
        return getMap().toString();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }
}
