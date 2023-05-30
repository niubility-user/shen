package com.jingdong.app.mall.bundle.productdetailcard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.common.utils.ImageUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardPraiseAdapter extends RecyclerView.Adapter {
    private Context a;
    private ArrayList<String> b;

    /* loaded from: classes3.dex */
    static class a extends RecyclerView.ViewHolder {
        private final TextView a;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.productdetailcard_praise_item_text);
        }

        public void b(String str) {
            this.a.setText(str);
        }
    }

    public PdCardPraiseAdapter(Context context) {
        this.a = context;
    }

    public void a(ArrayList<String> arrayList) {
        this.b = arrayList;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<String> arrayList = this.b;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        ((a) viewHolder).b(this.b.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new a(ImageUtil.inflate(this.a, R.layout.productdetailcard_parise_item, viewGroup, false));
    }
}
