package com.jd.dynamic.lib.utils;

import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class u {
    private final ArrayMap<Integer, a> a = new ArrayMap<>();

    /* loaded from: classes13.dex */
    public static final class a {
        private final ArrayList<Integer> a = new ArrayList<>();
        private final RecyclerView.RecycledViewPool b = new RecyclerView.RecycledViewPool();

        private final boolean c(int i2) {
            Iterator<Integer> it = this.a.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if (next != null && i2 == next.intValue()) {
                    return true;
                }
            }
            this.a.add(Integer.valueOf(i2));
            return false;
        }

        @NotNull
        public final RecyclerView.RecycledViewPool a() {
            return this.b;
        }

        public final void b(int i2) {
            if (c(i2)) {
                return;
            }
            this.b.setMaxRecycledViews(i2, 30);
        }
    }

    private final a a(int i2) {
        a aVar = this.a.get(Integer.valueOf(i2));
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a();
        this.a.put(Integer.valueOf(i2), aVar2);
        return aVar2;
    }

    @Nullable
    public final a b(@Nullable Object obj) {
        if (obj instanceof Integer) {
            return a(((Number) obj).intValue());
        }
        return null;
    }

    public final void c() {
        this.a.clear();
    }
}
