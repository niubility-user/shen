package com.jingdong.common.weight.personal.base;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;

/* loaded from: classes12.dex */
public class DiffCallback<T> extends DiffUtil.Callback {
    private static final String TAG = "DiffCallback";
    private final List<T> newModels;
    private final List<T> oldModels;

    public DiffCallback(List<T> list, List<T> list2) {
        this.oldModels = list;
        this.newModels = list2;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i2, int i3) {
        List<T> list = this.oldModels;
        return (list == null || this.newModels == null || !list.get(i2).equals(this.newModels.get(i3))) ? false : true;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i2, int i3) {
        T t = this.oldModels.get(i2);
        T t2 = this.newModels.get(i3);
        return (t == null || t2 == null || !t.equals(t2)) ? false : true;
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
