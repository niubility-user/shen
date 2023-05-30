package com.jdpay.widget.recycler.indexer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jdpay.widget.recycler.AbsViewHolder;
import com.jdpay.widget.recycler.indexer.JPIndexableBean;

/* loaded from: classes18.dex */
public abstract class JPIndexerViewHolder<D extends JPIndexableBean> extends AbsViewHolder<D> {
    public JPIndexerViewHolder(@NonNull View view) {
        super(view);
    }

    @Deprecated
    public JPIndexerViewHolder(@NonNull Context context, int i2) {
        super(context, i2);
    }

    public JPIndexerViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        super(viewGroup, i2);
    }
}
