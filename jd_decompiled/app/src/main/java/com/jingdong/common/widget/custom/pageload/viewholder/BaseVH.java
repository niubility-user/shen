package com.jingdong.common.widget.custom.pageload.viewholder;

import android.view.View;
import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;

/* loaded from: classes12.dex */
public abstract class BaseVH extends RecyclerView.ViewHolder {
    public BaseVH(View view) {
        super(view);
    }

    public abstract void bind(IFloorEntity iFloorEntity);

    public final <T extends View> T find(@IdRes int i2) {
        return (T) this.itemView.findViewById(i2);
    }
}
