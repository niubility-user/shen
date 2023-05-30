package com.jingdong.sdk.platform.widget;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/* loaded from: classes10.dex */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    /* loaded from: classes10.dex */
    public interface ViewHolderClickListener {
        void onClick(View view, int i2);
    }

    public BaseViewHolder(View view) {
        this(view, null);
    }

    public void setHolderClickListener(@Nullable final ViewHolderClickListener viewHolderClickListener) {
        if (viewHolderClickListener != null) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.widget.BaseViewHolder.1
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
        ButterKnife.bind(this, view);
        setHolderClickListener(viewHolderClickListener);
    }
}
