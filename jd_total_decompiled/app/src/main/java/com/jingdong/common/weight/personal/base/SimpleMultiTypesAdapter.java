package com.jingdong.common.weight.personal.base;

import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jingdong.common.weight.personal.base.TypeIdentifier;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005B#\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/common/weight/personal/base/SimpleMultiTypesAdapter;", "Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "T", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "Lcom/jingdong/common/weight/personal/base/MultiTypesAdapter;", "", "getItemCount", "()I", "position", "getModelByPosition", "(I)Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;", "delegatesManager", "Landroid/view/LayoutInflater;", "layoutInflater", "<init>", "(Lcom/jingdong/common/weight/personal/base/AdapterDelegatesManager;Landroid/view/LayoutInflater;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class SimpleMultiTypesAdapter<T extends TypeIdentifier, VH extends RecyclerView.ViewHolder> extends MultiTypesAdapter<T, VH> {
    public SimpleMultiTypesAdapter(@NotNull AdapterDelegatesManager<T, VH> adapterDelegatesManager, @NotNull LayoutInflater layoutInflater) {
        super(adapterDelegatesManager, layoutInflater);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getModels().size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.weight.personal.base.MultiTypesAdapter
    @NotNull
    public T getModelByPosition(int position) {
        return getModels().get(position);
    }
}
