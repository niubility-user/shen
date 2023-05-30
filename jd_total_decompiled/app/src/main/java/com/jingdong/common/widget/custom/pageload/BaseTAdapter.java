package com.jingdong.common.widget.custom.pageload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class BaseTAdapter extends RecyclerView.Adapter<BaseVH> {
    public LayoutInflater inflater;
    private final boolean isAppContext;
    public final List<IFloorEntity> list;

    public BaseTAdapter(List<IFloorEntity> list) {
        this(list, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.list.get(i2).getFloorType();
    }

    public BaseTAdapter(List<IFloorEntity> list, boolean z) {
        this.list = list;
        this.isAppContext = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseVH baseVH, int i2) {
        baseVH.bind(this.list.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseVH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (this.inflater == null) {
            boolean z = this.isAppContext;
            Context context = viewGroup.getContext();
            if (z) {
                context = context.getApplicationContext();
            }
            this.inflater = LayoutInflater.from(context);
            return null;
        }
        return null;
    }
}
