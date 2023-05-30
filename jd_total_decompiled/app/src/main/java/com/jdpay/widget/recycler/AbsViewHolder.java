package com.jdpay.widget.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes18.dex */
public abstract class AbsViewHolder<D> extends RecyclerView.ViewHolder {
    public AbsViewHolder(@NonNull View view) {
        super(view);
    }

    public void setSize(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        boolean z = true;
        if (layoutParams == null) {
            layoutParams = new RecyclerView.LayoutParams(i2, i3);
        } else if (layoutParams.width == i2 && layoutParams.height == i3) {
            z = false;
        } else {
            layoutParams.width = i2;
            layoutParams.height = i3;
        }
        if (z) {
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public abstract void update(@Nullable D d, int i2, int i3);

    public AbsViewHolder(@NonNull Context context, int i2) {
        this(View.inflate(context, i2, null));
    }

    public AbsViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }
}
