package com.jdpay.widget.recycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.jdpay.widget.recycler.AbsViewHolder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes18.dex */
public abstract class AbsRecyclerAdapter<D, VH extends AbsViewHolder<D>> extends RecyclerView.Adapter<VH> implements List<D> {
    private final List<D> dataSource;
    protected RecyclerView.OnScrollListener onScrollListenerInternal;
    protected int scrollState;

    /* loaded from: classes18.dex */
    protected class OnScrollListenerInternal extends RecyclerView.OnScrollListener {
        protected OnScrollListenerInternal() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            AbsRecyclerAdapter.this.scrollState = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
        }
    }

    public AbsRecyclerAdapter() {
        this.dataSource = new CopyOnWriteArrayList();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(D d) {
        return this.dataSource.add(d);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(@NonNull Collection<? extends D> collection) {
        return this.dataSource.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.dataSource.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(@Nullable Object obj) {
        return this.dataSource.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(@NonNull Collection<?> collection) {
        return this.dataSource.containsAll(collection);
    }

    @Override // java.util.List
    public D get(int i2) {
        return this.dataSource.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return size();
    }

    @Override // java.util.List
    public int indexOf(@Nullable Object obj) {
        return this.dataSource.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.dataSource.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    @NonNull
    public Iterator<D> iterator() {
        return this.dataSource.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(@Nullable Object obj) {
        return this.dataSource.lastIndexOf(obj);
    }

    @Override // java.util.List
    @NonNull
    public ListIterator<D> listIterator() {
        return this.dataSource.listIterator();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        if (this.onScrollListenerInternal == null) {
            this.onScrollListenerInternal = new OnScrollListenerInternal();
        }
        this.scrollState = recyclerView.getScrollState();
        recyclerView.addOnScrollListener(this.onScrollListenerInternal);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        onBindViewHolder((AbsRecyclerAdapter<D, VH>) ((AbsViewHolder) viewHolder), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.OnScrollListener onScrollListener = this.onScrollListenerInternal;
        if (onScrollListener != null) {
            recyclerView.removeOnScrollListener(onScrollListener);
        }
        this.scrollState = 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(@Nullable Object obj) {
        return this.dataSource.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(@NonNull Collection<?> collection) {
        return this.dataSource.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(@NonNull Collection<?> collection) {
        return this.dataSource.retainAll(collection);
    }

    @Override // java.util.List
    public D set(int i2, D d) {
        return this.dataSource.set(i2, d);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.dataSource.size();
    }

    @Override // java.util.List
    @NonNull
    public List<D> subList(int i2, int i3) {
        return this.dataSource.subList(i2, i3);
    }

    @Override // java.util.List, java.util.Collection
    @Nullable
    public Object[] toArray() {
        return this.dataSource.toArray();
    }

    @Override // java.util.List
    public void add(int i2, D d) {
        this.dataSource.add(i2, d);
    }

    @Override // java.util.List
    public boolean addAll(int i2, @NonNull Collection<? extends D> collection) {
        return this.dataSource.addAll(i2, collection);
    }

    @Override // java.util.List
    @NonNull
    public ListIterator<D> listIterator(int i2) {
        return this.dataSource.listIterator(i2);
    }

    public void onBindViewHolder(@NonNull VH vh, int i2) {
        vh.update(this.dataSource.get(i2), i2, this.scrollState);
    }

    @Override // java.util.List
    public D remove(int i2) {
        return this.dataSource.remove(i2);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(@Nullable T[] tArr) {
        return (T[]) this.dataSource.toArray(tArr);
    }

    public AbsRecyclerAdapter(@NonNull List<D> list) {
        this.dataSource = list;
    }
}
