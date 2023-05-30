package com.paipai.library.inspect.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.paipai.library.inspect.R;
import com.paipai.library.inspect.dataclass.PpReportOutlines;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PpInspectParamsReportOutlinAdapter extends RecyclerView.Adapter<a> {
    private final List<PpReportOutlines> a;
    private final boolean b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class a extends RecyclerView.ViewHolder {
        private final TextView a;
        private final TextView b;

        /* renamed from: c  reason: collision with root package name */
        private final TextView f16049c;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_appear);
            this.b = (TextView) view.findViewById(R.id.tv_good_count);
            this.f16049c = (TextView) view.findViewById(R.id.tv_err_count);
        }
    }

    public PpInspectParamsReportOutlinAdapter(List<PpReportOutlines> list, boolean z) {
        if (list == null) {
            this.a = Collections.emptyList();
        } else {
            this.a = list;
        }
        this.b = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        PpReportOutlines ppReportOutlines = this.a.get(i2);
        aVar.a.setText(ppReportOutlines.getAttrName());
        if (ppReportOutlines.getAbnormalCount() > 0) {
            aVar.b.setText(String.format(Locale.getDefault(), "%d\u9879\u901a\u8fc7\uff0c", Integer.valueOf(ppReportOutlines.getNormalCount())));
            aVar.f16049c.setText(String.format(Locale.getDefault(), "%d\u9879\u5f02\u5e38", Integer.valueOf(ppReportOutlines.getAbnormalCount())));
            return;
        }
        aVar.b.setText(String.format(Locale.getDefault(), "%d\u9879\u5168\u901a\u8fc7", Integer.valueOf(ppReportOutlines.getNormalCount())));
        aVar.f16049c.setText((CharSequence) null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        a aVar = new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inspect_params_report_outline_item, viewGroup, false));
        if (this.b) {
            aVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_dark_pp);
            aVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_848383));
            aVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            aVar.f16049c.setTextColor(Color.parseColor("#F27724"));
        } else {
            aVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_pp);
            aVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
            aVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
            aVar.f16049c.setTextColor(Color.parseColor("#F27724"));
        }
        return aVar;
    }
}
