package com.jingdong.common.weight.personal.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.weight.personal.base.TypeIdentifier;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000e\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u00020\u0005:\u0001.B\u0007\u00a2\u0006\u0004\b,\u0010-J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH&\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u001bJ'\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u001f\u0010 J?\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u000b2\u0010\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010!2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0016\u00a2\u0006\u0004\b\u001f\u0010#J-\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0016\u00a2\u0006\u0004\b\u001f\u0010$J\u0017\u0010&\u001a\u00020\u001e2\u0006\u0010%\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u001e2\u0006\u0010%\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b(\u0010'J\u0017\u0010)\u001a\u00020\u001e2\u0006\u0010%\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b)\u0010'J\u0017\u0010*\u001a\u00020\b2\u0006\u0010%\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b*\u0010+\u00a8\u0006/"}, d2 = {"Lcom/jingdong/common/weight/personal/base/AdapterDelegate;", "Lcom/jingdong/common/weight/personal/base/TypeIdentifier;", "T", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "", "", DYConstants.DY_IDENTIFIER, "", "handles", "(Ljava/lang/String;)Z", "", "getViewType", "()I", "totalSpanCount", "position", "itemCount", "getSpanSize", "(III)I", "Landroid/view/ViewGroup;", "parent", "Landroid/view/LayoutInflater;", "layoutInflater", "onCreateViewHolder", "(Landroid/view/ViewGroup;Landroid/view/LayoutInflater;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;", "modelFinder", "(Landroid/view/ViewGroup;Landroid/view/LayoutInflater;Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "holder", CustomThemeConstance.NAVI_MODEL, "", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Lcom/jingdong/common/weight/personal/base/TypeIdentifier;I)V", "", "payloads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)V", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;)V", "viewHolder", "onViewRecycled", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V", "onViewAttachedToWindow", "onViewDetachedFromWindow", "onFailedToRecycleView", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z", "<init>", "()V", "ModelFinder", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class AdapterDelegate<T extends TypeIdentifier, VH extends RecyclerView.ViewHolder> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002J\u0017\u0010\u0005\u001a\u00028\u00022\u0006\u0010\u0004\u001a\u00020\u0003H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/common/weight/personal/base/AdapterDelegate$ModelFinder;", "T", "", "", "adapterPosition", "findByAdapterPosition", "(I)Ljava/lang/Object;", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public interface ModelFinder<T> {
        T findByAdapterPosition(int adapterPosition);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    public abstract int getViewType();

    public abstract boolean handles(@Nullable String r1);

    public void onBindViewHolder(@NotNull VH holder, int position, @Nullable List<? extends Object> payloads, @NotNull ModelFinder<T> modelFinder) {
        onBindViewHolder((AdapterDelegate<T, VH>) holder, position, modelFinder);
    }

    public void onBindViewHolder(@NotNull VH holder, @NotNull T r2, int position) {
    }

    @NotNull
    public VH onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LayoutInflater layoutInflater) {
        return new EmptyViewHolder(new TextView(parent.getContext()));
    }

    public boolean onFailedToRecycleView(@NotNull VH viewHolder) {
        return false;
    }

    public void onViewAttachedToWindow(@NotNull VH viewHolder) {
    }

    public void onViewDetachedFromWindow(@NotNull VH viewHolder) {
    }

    public void onViewRecycled(@NotNull VH viewHolder) {
    }

    public void onBindViewHolder(@NotNull VH holder, int position, @NotNull ModelFinder<T> modelFinder) {
        onBindViewHolder((AdapterDelegate<T, VH>) holder, (VH) modelFinder.findByAdapterPosition(position), position);
    }

    @NotNull
    public VH onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LayoutInflater layoutInflater, @NotNull ModelFinder<T> modelFinder) {
        return onCreateViewHolder(parent, layoutInflater);
    }
}
