package com.paipai.library.inspect.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.common.DpiUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.paipai.library.inspect.R;
import com.paipai.library.inspect.dataclass.PpInspectParamsAware;
import com.paipai.library.inspect.dataclass.PpInspectParamsCode;
import com.paipai.library.inspect.dataclass.PpInspectParamsDesc;
import com.paipai.library.inspect.dataclass.PpInspectParamsItems;
import com.paipai.library.inspect.dataclass.PpInspectParamsQuailty;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PpInspectParamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<PpInspectParamsAware> a;
    private final boolean b;

    /* loaded from: classes9.dex */
    class a extends RecyclerView.ItemDecoration {
        a(PpInspectParamsAdapter ppInspectParamsAdapter) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            if (recyclerView.getChildAdapterPosition(view) % 2 == 0) {
                rect.right = DpiUtil.dip2px(recyclerView.getContext(), 6.0f);
            }
        }
    }

    /* loaded from: classes9.dex */
    class b extends RecyclerView.ViewHolder {
        b(PpInspectParamsAdapter ppInspectParamsAdapter, View view) {
            super(view);
        }
    }

    /* loaded from: classes9.dex */
    static final class c extends RecyclerView.ViewHolder {
        private final TextView a;

        public c(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_code);
        }
    }

    /* loaded from: classes9.dex */
    static final class d extends RecyclerView.ViewHolder {
        private final TextView a;
        private final RecyclerView b;

        public d(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_title_params);
            this.b = (RecyclerView) view.findViewById(R.id.rv_report_outlines);
        }
    }

    /* loaded from: classes9.dex */
    static final class e extends RecyclerView.ViewHolder {
        private final TextView a;
        private final RecyclerView b;

        public e(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_title_params);
            this.b = (RecyclerView) view.findViewById(R.id.rv_params_items);
        }
    }

    /* loaded from: classes9.dex */
    static final class f extends RecyclerView.ViewHolder {
        private final TextView a;
        private final TextView b;

        /* renamed from: c  reason: collision with root package name */
        private final TextView f16048c;

        public f(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_quailty);
            this.b = (TextView) view.findViewById(R.id.tv_quailty_label);
            this.f16048c = (TextView) view.findViewById(R.id.tv_tested_ret);
        }
    }

    public PpInspectParamsAdapter(List<PpInspectParamsAware> list, boolean z) {
        this.a = list;
        this.b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(PpInspectParamsCode ppInspectParamsCode, View view) {
        try {
            ((ClipboardManager) view.getContext().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText("pp_inspect_code", ppInspectParamsCode.getCode()));
            PDUtils.showToastShortNormal(view.getContext(), "\u590d\u5236\u6210\u529f");
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        PpInspectParamsAware ppInspectParamsAware = this.a.get(i2);
        if (ppInspectParamsAware instanceof PpInspectParamsQuailty) {
            return 0;
        }
        if (ppInspectParamsAware instanceof PpInspectParamsItems) {
            return 1;
        }
        if (ppInspectParamsAware instanceof PpInspectParamsDesc) {
            return 2;
        }
        return ppInspectParamsAware instanceof PpInspectParamsCode ? 3 : 4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        int i3;
        PpInspectParamsAware ppInspectParamsAware = this.a.get(i2);
        if (viewHolder instanceof f) {
            f fVar = (f) viewHolder;
            PpInspectParamsQuailty ppInspectParamsQuailty = (PpInspectParamsQuailty) ppInspectParamsAware;
            int qualityId = ppInspectParamsQuailty.getQualityId();
            if (qualityId == 100) {
                fVar.a.setText((CharSequence) null);
                fVar.b.setText("\u51c6\u65b0\u673a");
            } else if (qualityId % 10 == 0) {
                fVar.a.setText(String.valueOf(qualityId / 10));
                fVar.b.setText("\u6210\u65b0");
            } else {
                fVar.a.setText(String.valueOf(qualityId));
                fVar.b.setText("\u65b0");
            }
            SpannableString spannableString = new SpannableString(String.format(Locale.getDefault(), "%d %s", Integer.valueOf(qualityId), ppInspectParamsQuailty.getInspectDesc()));
            if (this.b) {
                i3 = R.drawable.ic_tested_ret_dark_pp;
            } else {
                i3 = R.drawable.ic_tested_ret_pp;
            }
            Drawable drawable = ContextCompat.getDrawable(fVar.itemView.getContext(), i3);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                spannableString.setSpan(new com.paipai.library.inspect.utility.a(drawable), 0, String.valueOf(qualityId).length(), 17);
            }
            fVar.f16048c.setText(spannableString);
        } else if (viewHolder instanceof e) {
            ((e) viewHolder).b.setAdapter(new PpInspectParamsTestedAdapter(((PpInspectParamsItems) ppInspectParamsAware).getData(), this.b));
        } else if (viewHolder instanceof d) {
            ((d) viewHolder).b.setAdapter(new PpInspectParamsReportOutlinAdapter(((PpInspectParamsDesc) ppInspectParamsAware).getData(), this.b));
        } else if (viewHolder instanceof c) {
            c cVar = (c) viewHolder;
            final PpInspectParamsCode ppInspectParamsCode = (PpInspectParamsCode) ppInspectParamsAware;
            cVar.a.setText(String.format("%s: %s", ppInspectParamsCode.getCodeName(), ppInspectParamsCode.getCode()));
            cVar.a.setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PpInspectParamsAdapter.a(PpInspectParamsCode.this, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i2 == 0) {
            f fVar = new f(from.inflate(R.layout.layout_inspect_params_quailty, viewGroup, false));
            FontsUtil.changeTextFont(fVar.a, 4097);
            if (this.b) {
                fVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_dark_pp);
                fVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
                fVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
                fVar.f16048c.setTextColor(Color.parseColor(JDDarkUtil.COLOR_848383));
            } else {
                fVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_pp);
                fVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
                fVar.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
                fVar.f16048c.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
            }
            return fVar;
        } else if (i2 == 1) {
            e eVar = new e(from.inflate(R.layout.layout_inspect_params_items, viewGroup, false));
            if (this.b) {
                eVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_dark_pp);
                eVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            } else {
                eVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_pp);
                eVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
            }
            if (eVar.b.getItemDecorationCount() == 0) {
                eVar.b.addItemDecoration(new a(this));
            }
            eVar.b.setHasFixedSize(true);
            eVar.b.setLayoutManager(new GridLayoutManager(viewGroup.getContext(), 2));
            return eVar;
        } else if (i2 == 2) {
            d dVar = new d(from.inflate(R.layout.layout_inspect_params_desc, viewGroup, false));
            if (this.b) {
                dVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_dark_pp);
                dVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            } else {
                dVar.itemView.setBackgroundResource(R.drawable.bg_inspect_params_item_pp);
                dVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
            }
            dVar.b.setHasFixedSize(true);
            dVar.b.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
            return dVar;
        } else if (i2 == 3) {
            c cVar = new c(from.inflate(R.layout.layout_inspect_params_code, viewGroup, false));
            if (this.b) {
                cVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_848383));
                cVar.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_copy_dark_pp, 0);
            } else {
                cVar.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
                cVar.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_copy_pp, 0);
            }
            return cVar;
        } else {
            return new b(this, new View(viewGroup.getContext()));
        }
    }
}
