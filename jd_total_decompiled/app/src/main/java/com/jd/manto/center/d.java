package com.jd.manto.center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.widget.recycler.BaseRecyclerAdapter;
import java.util.List;

/* loaded from: classes17.dex */
final class d extends BaseRecyclerAdapter<String, b> {

    /* renamed from: c  reason: collision with root package name */
    final BaseRecyclerAdapter.a f6541c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ b f6542g;

        a(b bVar) {
            this.f6542g = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = d.this;
            BaseRecyclerAdapter.a aVar = dVar.f6541c;
            b bVar = this.f6542g;
            aVar.a(dVar, bVar.itemView, bVar.getAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b extends RecyclerView.ViewHolder {
        final TextView a;

        public b(d dVar, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.title);
        }
    }

    public d(Context context, List<String> list, BaseRecyclerAdapter.a aVar) {
        super(context, list);
        this.f6541c = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull b bVar, int i2) {
        bVar.a.setText(getData().get(i2));
        if (this.f6541c != null) {
            bVar.itemView.setOnClickListener(new a(bVar));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new b(this, LayoutInflater.from(l()).inflate(R.layout.manto_center_hot_item, viewGroup, false));
    }
}
