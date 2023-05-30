package com.jd.lib.un.basewidget.widget.multi.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.basewidget.R;
import java.util.List;

/* loaded from: classes16.dex */
public class MultiContentAdapter extends RecyclerView.Adapter<MultiContentViewHolder> {
    private String a = null;
    private List<String> b = null;

    /* renamed from: c  reason: collision with root package name */
    private int f5724c = -16777216;
    private int d = SupportMenu.CATEGORY_MASK;

    /* renamed from: e  reason: collision with root package name */
    private int f5725e = com.jd.lib.un.basewidget.widget.multi.c.a.b(13.0f);

    /* renamed from: f  reason: collision with root package name */
    private b f5726f;

    /* loaded from: classes16.dex */
    public static class MultiContentViewHolder extends RecyclerView.ViewHolder {
        public TextView a;

        public MultiContentViewHolder(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.content_tv);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MultiContentViewHolder f5727g;

        a(MultiContentViewHolder multiContentViewHolder) {
            this.f5727g = multiContentViewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int adapterPosition;
            if (MultiContentAdapter.this.f5726f == null || (adapterPosition = this.f5727g.getAdapterPosition()) < 0 || adapterPosition > MultiContentAdapter.this.b.size() - 1) {
                return;
            }
            MultiContentAdapter.this.f5726f.a(this.f5727g.getAdapterPosition(), (String) MultiContentAdapter.this.b.get(adapterPosition));
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a(int i2, String str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull MultiContentViewHolder multiContentViewHolder, int i2) {
        List<String> list = this.b;
        String str = list == null ? null : list.get(i2);
        if (str != null) {
            String str2 = this.a;
            if (str2 != null && str2.equals(str)) {
                multiContentViewHolder.a.setTextColor(this.d);
            } else {
                multiContentViewHolder.a.setTextColor(this.f5724c);
            }
            multiContentViewHolder.a.setTextSize(0, this.f5725e);
            multiContentViewHolder.a.setText(str);
        }
        multiContentViewHolder.itemView.setOnClickListener(new a(multiContentViewHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public MultiContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new MultiContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_select_item_layout, viewGroup, false));
    }

    public void n(List<String> list) {
        this.b = list;
    }

    public void o(int i2) {
        this.f5724c = i2;
    }

    public void p(b bVar) {
        this.f5726f = bVar;
    }

    public void q(int i2) {
        this.d = i2;
    }

    public void r(String str) {
        this.a = str;
    }

    public void s(int i2) {
        this.f5725e = i2;
    }
}
