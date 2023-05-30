package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.view.widget.TrendLayout;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.k.e;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TrendAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: c  reason: collision with root package name */
    public static int f9732c = 0;
    public static int d = 1;

    /* renamed from: e  reason: collision with root package name */
    public static int f9733e = 2;
    private List<e> a = new ArrayList();
    private Context b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends RecyclerView.ViewHolder {
        private TrendLayout a;

        public a(View view) {
            super(view);
            f.n(view);
            this.a = (TrendLayout) view;
        }

        public void b(e eVar, int i2) {
            int i3 = TrendAdapter.d;
            if (i2 != 0) {
                if (i2 == TrendAdapter.this.a.size() - 1) {
                    i3 = TrendAdapter.f9733e;
                }
            } else {
                i3 = TrendAdapter.f9732c;
            }
            this.a.b(eVar, i3);
        }
    }

    public TrendAdapter(Context context) {
        this.b = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    public e h(int i2) {
        if (i2 < 0 || i2 >= this.a.size()) {
            return null;
        }
        return this.a.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        aVar.b(h(i2), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new a(new TrendLayout(this.b));
    }

    public void setList(List<e> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }
}
