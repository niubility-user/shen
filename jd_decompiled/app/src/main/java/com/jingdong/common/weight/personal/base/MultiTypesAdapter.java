package com.jingdong.common.weight.personal.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jingdong.common.weight.personal.base.AdapterDelegate;
import com.jingdong.common.weight.personal.base.MultiTypesAdapter$modelFinder$2;
import com.jingdong.common.weight.personal.base.TypeIdentifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\b\u0012\u0004\u0012\u00028\u00010\u0005B#\u0012\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100\u0012\u0006\u0010A\u001a\u00020@\u00a2\u0006\u0004\bG\u0010HJ+\u0010\n\u001a\u00020\t2\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u00012\u0006\u0010\u0019\u001a\u00020\f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001fJ\u0017\u0010 \u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b%\u0010!J\u0017\u0010&\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b&\u0010!J\u0017\u0010'\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\fH$\u00a2\u0006\u0004\b)\u0010*J+\u0010+\u001a\u00020\t2\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007H\u0016\u00a2\u0006\u0004\b+\u0010\u000bJ\u001f\u0010-\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010,H\u0016\u00a2\u0006\u0004\b-\u0010.J\u001f\u0010/\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010,H\u0016\u00a2\u0006\u0004\b/\u0010.R%\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001008\u0006@\u0006\u00a2\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R#\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c8D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R#\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000;8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b<\u00106\u001a\u0004\b=\u0010>R\u0019\u0010A\u001a\u00020@8\u0006@\u0006\u00a2\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010F\u00a8\u0006I"}, d2 = {"Lcom/jingdong/common/weight/personal/base/MultiTypesAdapter;", "Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "T", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "models", "", "filterModels", "(Ljava/util/ArrayList;)V", "", "count", "setSpanCount", "(I)V", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSizeLookup", "()Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewHolder", "position", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "", "", "payloads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;)V", "onViewRecycled", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V", "", "onFailedToRecycleView", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z", "onViewAttachedToWindow", "onViewDetachedFromWindow", "getItemViewType", "(I)I", "getModelByPosition", "(I)Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "swapModels", "", "swapModelsNew", "(Ljava/util/List;)V", "filterModelsNew", "Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;", "delegatesManager", "Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;", "getDelegatesManager", "()Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;", "models$delegate", "Lkotlin/Lazy;", "getModels", "()Ljava/util/List;", "spanSizeLookup", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;", "modelFinder$delegate", "getModelFinder", "()Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;", "modelFinder", "Landroid/view/LayoutInflater;", "layoutInflater", "Landroid/view/LayoutInflater;", "getLayoutInflater", "()Landroid/view/LayoutInflater;", "spanCount", "I", "<init>", "(Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;Landroid/view/LayoutInflater;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class MultiTypesAdapter<T extends TypeIdentifier, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    @NotNull
    private final AdapterDelegatesManager<T, VH> delegatesManager;
    @NotNull
    private final LayoutInflater layoutInflater;

    /* renamed from: modelFinder$delegate  reason: from kotlin metadata */
    private final Lazy modelFinder;
    @NotNull

    /* renamed from: models$delegate  reason: from kotlin metadata */
    private final Lazy models;
    private int spanCount;
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup;

    public MultiTypesAdapter(@NotNull AdapterDelegatesManager<T, VH> adapterDelegatesManager, @NotNull LayoutInflater layoutInflater) {
        Lazy lazy;
        Lazy lazy2;
        this.delegatesManager = adapterDelegatesManager;
        this.layoutInflater = layoutInflater;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<ArrayList<T>>() { // from class: com.jingdong.common.weight.personal.base.MultiTypesAdapter$models$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ArrayList<T> invoke() {
                return new ArrayList<>();
            }
        });
        this.models = lazy;
        this.spanCount = 1;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<MultiTypesAdapter$modelFinder$2.AnonymousClass1>() { // from class: com.jingdong.common.weight.personal.base.MultiTypesAdapter$modelFinder$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.jingdong.common.weight.personal.base.MultiTypesAdapter$modelFinder$2$1] */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final AnonymousClass1 invoke() {
                return new AdapterDelegate.ModelFinder<T>() { // from class: com.jingdong.common.weight.personal.base.MultiTypesAdapter$modelFinder$2.1
                    /* JADX WARN: Incorrect return type in method signature: (I)TT; */
                    @Override // com.jingdong.common.weight.personal.base.AdapterDelegate.ModelFinder
                    @NotNull
                    public TypeIdentifier findByAdapterPosition(int adapterPosition) {
                        return MultiTypesAdapter.this.getModelByPosition(adapterPosition);
                    }
                };
            }
        });
        this.modelFinder = lazy2;
        this.spanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: com.jingdong.common.weight.personal.base.MultiTypesAdapter$spanSizeLookup$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                Integer num;
                AdapterDelegate findDelegateByViewType;
                int i2;
                try {
                    int itemViewType = MultiTypesAdapter.this.getItemViewType(position);
                    AdapterDelegatesManager delegatesManager = MultiTypesAdapter.this.getDelegatesManager();
                    if (delegatesManager == null || (findDelegateByViewType = delegatesManager.findDelegateByViewType(itemViewType)) == null) {
                        num = null;
                    } else {
                        i2 = MultiTypesAdapter.this.spanCount;
                        num = Integer.valueOf(findDelegateByViewType.getSpanSize(i2, position, MultiTypesAdapter.this.getItemCount()));
                    }
                    return num.intValue();
                } catch (IndexOutOfBoundsException unused) {
                    return 1;
                }
            }
        };
    }

    private final void filterModels(ArrayList<T> models) {
        Iterator<T> it;
        AdapterDelegatesManager<T, VH> adapterDelegatesManager;
        if (models == null || (it = models.iterator()) == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(it, "models?.iterator() ?: return");
        while (true) {
            if (!(it != null ? Boolean.valueOf(it.hasNext()) : null).booleanValue()) {
                return;
            }
            T next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
            T t = next;
            if (t == null || ((adapterDelegatesManager = this.delegatesManager) != null && adapterDelegatesManager.getViewType(t) == -1)) {
                it.remove();
            }
        }
    }

    private final AdapterDelegate.ModelFinder<T> getModelFinder() {
        return (AdapterDelegate.ModelFinder) this.modelFinder.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r3 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList((java.util.Collection) r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void filterModelsNew(@org.jetbrains.annotations.Nullable java.util.List<? extends T> r3) {
        /*
            r2 = this;
            if (r3 == 0) goto Ld
            java.util.List r3 = kotlin.collections.CollectionsKt.toMutableList(r3)
            if (r3 == 0) goto Ld
            java.util.ListIterator r3 = r3.listIterator()
            goto Le
        Ld:
            r3 = 0
        Le:
            if (r3 == 0) goto L2d
        L10:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L2d
            java.lang.Object r0 = r3.next()
            com.jingdong.common.weight.personal.base.TypeIdentifier r0 = (com.jingdong.common.weight.personal.base.TypeIdentifier) r0
            if (r0 == 0) goto L29
            com.jingdong.common.weight.personal.base.AdapterDelegatesManager<T extends com.jingdong.common.weight.personal.base.TypeIdentifier, VH extends androidx.recyclerview.widget.RecyclerView$ViewHolder> r1 = r2.delegatesManager
            if (r1 == 0) goto L10
            int r0 = r1.getViewType(r0)
            r1 = -1
            if (r0 != r1) goto L10
        L29:
            r3.remove()
            goto L10
        L2d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.weight.personal.base.MultiTypesAdapter.filterModelsNew(java.util.List):void");
    }

    @NotNull
    public final AdapterDelegatesManager<T, VH> getDelegatesManager() {
        return this.delegatesManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.delegatesManager.getViewType(getModelByPosition(position));
    }

    @NotNull
    public final LayoutInflater getLayoutInflater() {
        return this.layoutInflater;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract T getModelByPosition(int position);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final List<T> getModels() {
        return (List) this.models.getValue();
    }

    @Nullable
    public final GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return this.spanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull VH viewHolder, int position) {
        this.delegatesManager.onBindViewHolder(viewHolder, position, getModelFinder());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public VH onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return this.delegatesManager.onCreateViewHolder(parent, this.layoutInflater, viewType, getModelFinder());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(@NotNull VH viewHolder) {
        return this.delegatesManager.onFailedToRecycleView(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(@NotNull VH viewHolder) {
        this.delegatesManager.onViewAttachedToWindow(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(@NotNull VH viewHolder) {
        this.delegatesManager.onViewDetachedFromWindow(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NotNull VH viewHolder) {
        this.delegatesManager.onViewRecycled(viewHolder);
    }

    public final void setSpanCount(int count) {
        this.spanCount = count;
    }

    public void swapModels(@Nullable ArrayList<T> models) {
        filterModels(models);
        if (models != null) {
            getModels().clear();
            getModels().addAll(models);
            notifyDataSetChanged();
        }
    }

    public void swapModelsNew(@Nullable List<? extends T> models) {
        filterModelsNew(models);
        if (models != null) {
            getModels().clear();
            getModels().addAll(models);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull VH viewHolder, int position, @NotNull List<Object> payloads) {
        this.delegatesManager.onBindViewHolder(viewHolder, position, payloads, getModelFinder());
    }
}
