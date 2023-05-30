package com.jingdong.app.mall.bundle.PageComponents.list.test;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Random;

/* loaded from: classes19.dex */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<VH> {
    private Random random = new Random(System.currentTimeMillis());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public static class VH extends RecyclerView.ViewHolder {
        public VH(@NonNull View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 100;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VH vh, int i2) {
        vh.itemView.setBackgroundColor(Color.argb(255, this.random.nextInt(), this.random.nextInt(), this.random.nextInt()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        View view = new View(viewGroup.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, 300));
        return new VH(view);
    }
}
