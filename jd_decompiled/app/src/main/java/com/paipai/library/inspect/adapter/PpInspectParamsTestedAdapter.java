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
import com.paipai.library.inspect.dataclass.PpBaseOutlines;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectParamsTestedAdapter extends RecyclerView.Adapter<a> {
    private final List<PpBaseOutlines> a;
    private final boolean b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class a extends RecyclerView.ViewHolder {
        private final TextView a;
        private final TextView b;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_tested_title);
            this.b = (TextView) view.findViewById(R.id.tv_tested_value);
        }
    }

    public PpInspectParamsTestedAdapter(List<PpBaseOutlines> list, boolean z) {
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
        PpBaseOutlines ppBaseOutlines = this.a.get(i2);
        aVar.a.setText(ppBaseOutlines.getAttrName());
        aVar.b.setText(ppBaseOutlines.getAttrValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        a aVar = new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inspect_params_tested_item, viewGroup, false));
        if (this.b) {
            aVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_848383));
            aVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
        } else {
            aVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
            aVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        }
        return aVar;
    }
}
