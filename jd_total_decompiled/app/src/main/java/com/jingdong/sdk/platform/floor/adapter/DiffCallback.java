package com.jingdong.sdk.platform.floor.adapter;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;

/* loaded from: classes10.dex */
public class DiffCallback<T> extends DiffUtil.Callback {
    private final List<T> newModels;
    private final List<T> oldModels;

    public DiffCallback(List<T> list, List<T> list2) {
        this.oldModels = list;
        this.newModels = list2;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i2, int i3) {
        return this.oldModels.get(i2).equals(this.newModels.get(i3));
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i2, int i3) {
        return this.oldModels.get(i2).equals(this.newModels.get(i3));
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        List<T> list = this.newModels;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        List<T> list = this.oldModels;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
