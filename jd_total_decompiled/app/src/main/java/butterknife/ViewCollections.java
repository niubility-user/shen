package butterknife;

import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.util.List;

/* loaded from: classes.dex */
public final class ViewCollections {
    private ViewCollections() {
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull List<T> list, @NonNull Action<? super T>... actionArr) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            for (Action<? super T> action : actionArr) {
                action.apply(list.get(i2), i2);
            }
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull List<T> list, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            setter.set(list.get(i2), v, i2);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T[] tArr, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            setter.set(tArr[i2], v, i2);
        }
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull T[] tArr, @NonNull Action<? super T>... actionArr) {
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            for (Action<? super T> action : actionArr) {
                action.apply(tArr[i2], i2);
            }
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T t, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        setter.set(t, v, 0);
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull List<T> list, @NonNull Property<? super T, V> property, @Nullable V v) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            property.set(list.get(i2), v);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull List<T> list, @NonNull Action<? super T> action) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            action.apply(list.get(i2), i2);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T[] tArr, @NonNull Property<? super T, V> property, @Nullable V v) {
        for (T t : tArr) {
            property.set(t, v);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull T[] tArr, @NonNull Action<? super T> action) {
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            action.apply(tArr[i2], i2);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T t, @NonNull Property<? super T, V> property, @Nullable V v) {
        property.set(t, v);
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull T t, @NonNull Action<? super T>... actionArr) {
        for (Action<? super T> action : actionArr) {
            action.apply(t, 0);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull T t, @NonNull Action<? super T> action) {
        action.apply(t, 0);
    }
}
