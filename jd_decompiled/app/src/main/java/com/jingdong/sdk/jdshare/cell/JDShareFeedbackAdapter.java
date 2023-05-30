package com.jingdong.sdk.jdshare.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.appshare.R;
import com.jingdong.c.a.c.d;
import java.util.List;

/* loaded from: classes7.dex */
public class JDShareFeedbackAdapter extends RecyclerView.Adapter<b> {
    private List<d> a;
    private int b = -1;

    /* renamed from: c  reason: collision with root package name */
    private c f14992c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f14993g;

        a(int i2) {
            this.f14993g = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (JDShareFeedbackAdapter.this.b >= 0 && this.f14993g != JDShareFeedbackAdapter.this.b) {
                ((d) JDShareFeedbackAdapter.this.a.get(JDShareFeedbackAdapter.this.b)).f12272c = false;
            }
            JDShareFeedbackAdapter.this.q(this.f14993g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class b extends RecyclerView.ViewHolder {
        private TextView a;
        private ImageView b;

        /* synthetic */ b(View view, a aVar) {
            this(view);
        }

        private b(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.feedback_text);
            this.b = (ImageView) view.findViewById(R.id.feedback_select_button);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface c {
        void a(d dVar);
    }

    public JDShareFeedbackAdapter(Context context, List list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i2) {
        d dVar = this.a.get(i2);
        if (dVar != null) {
            boolean z = !dVar.f12272c;
            dVar.f12272c = z;
            if (z) {
                this.b = i2;
                this.f14992c.a(dVar);
            } else {
                this.b = -1;
            }
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    public d m() {
        int i2 = this.b;
        if (i2 < 0 || i2 >= this.a.size()) {
            return null;
        }
        return this.a.get(this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i2) {
        d dVar = this.a.get(i2);
        if (dVar != null) {
            bVar.a.setText(dVar.b);
            if (dVar.f12272c) {
                bVar.b.setImageResource(R.drawable.share_radio_button);
            } else {
                bVar.b.setImageResource(R.drawable.feedback_image_selected);
            }
            bVar.itemView.setOnClickListener(new a(i2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.share_layout_feedback_item, viewGroup, false), null);
    }

    public void p(c cVar) {
        this.f14992c = cVar;
    }
}
