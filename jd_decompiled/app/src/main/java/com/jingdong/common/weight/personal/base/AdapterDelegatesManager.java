package com.jingdong.common.weight.personal.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.weight.personal.base.AdapterDelegate;
import com.jingdong.common.weight.personal.base.TypeIdentifier;
import com.jingdong.jdsdk.config.Configuration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 /*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u00020\u0005:\u0001/B\u0007\u00a2\u0006\u0004\b-\u0010.J#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\u0006\u001a\u00028\u0001H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ%\u0010\f\u001a\u00020\u000b2\u0016\u0010\n\u001a\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u00010\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ+\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u001b\u001a\u00020\u000f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\u00a2\u0006\u0004\b\u001c\u0010\u001dJ;\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u001b\u001a\u00020\u000f2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\u00a2\u0006\u0004\b\u001c\u0010 J\u0015\u0010!\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0001\u00a2\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0001\u00a2\u0006\u0004\b#\u0010\"J\u0015\u0010$\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0001\u00a2\u0006\u0004\b$\u0010\"J\u0015\u0010&\u001a\u00020%2\u0006\u0010\u0006\u001a\u00028\u0001\u00a2\u0006\u0004\b&\u0010'J!\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u000f\u00a2\u0006\u0004\b(\u0010)R(\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070*8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00a8\u00060"}, d2 = {"Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;", "Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "T", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "", "viewHolder", "Lcom/jingdong/common/weight/personal/base/AdapterDelegate;", "findDelegateByViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Lcom/jingdong/common/weight/personal/base/AdapterDelegate;", "delegate", "", "addAdapterDelegate", "(Lcom/jingdong/common/weight/personal/base/AdapterDelegate;)V", CustomThemeConstance.NAVI_MODEL, "", "getViewType", "(Lcom/jingdong/common/weight/personal/base/TypeIdentifier;)I", "Landroid/view/ViewGroup;", "parent", "Landroid/view/LayoutInflater;", "inflater", "viewType", "Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;", "modelFinder", "onCreateViewHolder", "(Landroid/view/ViewGroup;Landroid/view/LayoutInflater;ILcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)V", "", "payLoads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)V", "onViewRecycled", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V", "onViewAttachedToWindow", "onViewDetachedFromWindow", "", "onFailedToRecycleView", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z", "findDelegateByViewType", "(I)Lcom/jingdong/common/weight/personal/base/AdapterDelegate;", "Landroidx/collection/SparseArrayCompat;", "delegateCache", "Landroidx/collection/SparseArrayCompat;", "<init>", "()V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class AdapterDelegatesManager<T extends TypeIdentifier, VH extends RecyclerView.ViewHolder> {
    public static final int INVALID_MODEL = -1;
    private final SparseArrayCompat<AdapterDelegate<T, VH>> delegateCache = new SparseArrayCompat<>();

    private final AdapterDelegate<T, VH> findDelegateByViewHolder(VH viewHolder) {
        return findDelegateByViewType(viewHolder.getItemViewType());
    }

    public final void addAdapterDelegate(@NotNull AdapterDelegate<? extends T, ? extends VH> delegate) {
        int viewType = delegate.getViewType();
        if (this.delegateCache.get(viewType) == null) {
            this.delegateCache.put(viewType, delegate);
            return;
        }
        throw new IllegalStateException("delegateCache already has AdapterDelegate for viewType: " + viewType);
    }

    @NotNull
    public final AdapterDelegate<T, VH> findDelegateByViewType(int viewType) {
        AdapterDelegate<T, VH> adapterDelegate = this.delegateCache.get(viewType);
        if (adapterDelegate != null) {
            return adapterDelegate;
        }
        throw new IllegalStateException("Can't find AdapterDelegate by viewType: " + viewType);
    }

    public final int getViewType(@NotNull T model) {
        int size = this.delegateCache.size();
        for (int i2 = 0; i2 < size; i2++) {
            AdapterDelegate<T, VH> valueAt = this.delegateCache.valueAt(i2);
            Intrinsics.checkExpressionValueIsNotNull(valueAt, "delegateCache.valueAt(i)");
            AdapterDelegate<T, VH> adapterDelegate = valueAt;
            if (adapterDelegate.handles(model.typeIdentifier())) {
                return adapterDelegate.getViewType();
            }
        }
        if (Configuration.isBeta()) {
            throw new IllegalStateException("Did you call addAdapterDelegate()?\nCan't find AdapterDelegate for model: " + model);
        }
        return -1;
    }

    public final void onBindViewHolder(@NotNull VH viewHolder, int position, @NotNull AdapterDelegate.ModelFinder<T> modelFinder) {
        findDelegateByViewHolder(viewHolder).onBindViewHolder((AdapterDelegate<T, VH>) viewHolder, position, modelFinder);
    }

    @NotNull
    public final VH onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LayoutInflater inflater, int viewType, @NotNull AdapterDelegate.ModelFinder<T> modelFinder) {
        return findDelegateByViewType(viewType).onCreateViewHolder(parent, inflater, modelFinder);
    }

    public final boolean onFailedToRecycleView(@NotNull VH viewHolder) {
        return findDelegateByViewHolder(viewHolder).onFailedToRecycleView(viewHolder);
    }

    public final void onViewAttachedToWindow(@NotNull VH viewHolder) {
        findDelegateByViewHolder(viewHolder).onViewAttachedToWindow(viewHolder);
    }

    public final void onViewDetachedFromWindow(@NotNull VH viewHolder) {
        findDelegateByViewHolder(viewHolder).onViewDetachedFromWindow(viewHolder);
    }

    public final void onViewRecycled(@NotNull VH viewHolder) {
        findDelegateByViewHolder(viewHolder).onViewRecycled(viewHolder);
    }

    public final void onBindViewHolder(@NotNull VH viewHolder, int position, @Nullable List<? extends Object> payLoads, @NotNull AdapterDelegate.ModelFinder<T> modelFinder) {
        findDelegateByViewHolder(viewHolder).onBindViewHolder(viewHolder, position, payLoads, modelFinder);
    }
}
