package com.jingdong.common.weight.personal.base;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes12.dex */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    /* loaded from: classes12.dex */
    public interface ViewHolderClickListener {
        void onClick(View view, int i2);
    }

    public BaseViewHolder(View view) {
        this(view, null);
    }

    public void setHolderClickListener(@Nullable final ViewHolderClickListener viewHolderClickListener) {
        if (viewHolderClickListener != null) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.weight.personal.base.BaseViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BaseViewHolder.this.getAdapterPosition() != -1) {
                        ViewHolderClickListener viewHolderClickListener2 = viewHolderClickListener;
                        BaseViewHolder baseViewHolder = BaseViewHolder.this;
                        viewHolderClickListener2.onClick(baseViewHolder.itemView, baseViewHolder.getAdapterPosition());
                    }
                }
            });
        }
    }

    public BaseViewHolder(View view, @Nullable ViewHolderClickListener viewHolderClickListener) {
        super(view);
        setHolderClickListener(viewHolderClickListener);
    }
}
