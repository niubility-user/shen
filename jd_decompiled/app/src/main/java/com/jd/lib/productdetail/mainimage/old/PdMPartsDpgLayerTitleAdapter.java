package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.mainimage.R;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMPartsDpgLayerTitleAdapter extends RecyclerView.Adapter<PDDPpgTitleHolder> {
    public List<String> a;
    public int b = 0;

    /* renamed from: c */
    public boolean f5067c;
    public boolean d;

    /* renamed from: e */
    public Context f5068e;

    /* renamed from: f */
    public a f5069f;

    /* renamed from: g */
    public RecyclerView f5070g;

    /* loaded from: classes15.dex */
    public class PDDPpgTitleHolder extends RecyclerView.ViewHolder {
        public TextView a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PDDPpgTitleHolder(@NonNull View view) {
            super(view);
            PdMPartsDpgLayerTitleAdapter.this = r3;
            this.a = (TextView) view.findViewById(R.id.pd_parts_dpg_select_title);
            if (r3.f5067c) {
                this.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(r3.f5068e, R.color.lib_pd_image_color_1A1A1A, r3.d));
                this.a.setTypeface(Typeface.defaultFromStyle(1));
                this.a.setTextSize(2, 18.0f);
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface a {
    }

    public PdMPartsDpgLayerTitleAdapter(RecyclerView recyclerView, Context context) {
        this.f5068e = context;
        this.f5070g = recyclerView;
    }

    @NonNull
    public PDDPpgTitleHolder a(@NonNull ViewGroup viewGroup) {
        return new PDDPpgTitleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_parts_dpg_layer_title, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void h(@NonNull PDDPpgTitleHolder pDDPpgTitleHolder) {
        List<String> list = this.a;
        if (list == null || list.isEmpty() || this.f5068e == null) {
            return;
        }
        String str = this.a.get(pDDPpgTitleHolder.getAdapterPosition());
        boolean z = this.b == pDDPpgTitleHolder.getAdapterPosition();
        TextView textView = pDDPpgTitleHolder.a;
        if (textView != null) {
            textView.setText(str);
            pDDPpgTitleHolder.a.setTextSize(2, (PdMPartsDpgLayerTitleAdapter.this.f5067c || z) ? 18 : 16);
            PdMPartsDpgLayerTitleAdapter pdMPartsDpgLayerTitleAdapter = PdMPartsDpgLayerTitleAdapter.this;
            if (!pdMPartsDpgLayerTitleAdapter.f5067c) {
                if (z) {
                    pDDPpgTitleHolder.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMPartsDpgLayerTitleAdapter.f5068e, R.color.lib_pd_image_color_1A1A1A, pdMPartsDpgLayerTitleAdapter.d));
                    pDDPpgTitleHolder.a.setTypeface(Typeface.defaultFromStyle(1));
                } else {
                    pDDPpgTitleHolder.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMPartsDpgLayerTitleAdapter.f5068e, R.color.lib_pd_image_color_4D4D4D, pdMPartsDpgLayerTitleAdapter.d));
                    pDDPpgTitleHolder.a.setTypeface(Typeface.defaultFromStyle(0));
                }
            }
        }
        pDDPpgTitleHolder.a.setOnClickListener(new c0(this, pDDPpgTitleHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(@NonNull PDDPpgTitleHolder pDDPpgTitleHolder, int i2) {
        h(pDDPpgTitleHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ PDDPpgTitleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
