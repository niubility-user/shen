package com.jingdong.manto.widget.input.autofill;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.jingdong.manto.R;
import com.jingdong.manto.widget.input.a0.h;
import com.jingdong.manto.widget.input.autofill.d;

/* loaded from: classes16.dex */
public final class a extends ArrayAdapter<h> implements c {
    private final LayoutInflater a;
    private e b;

    /* renamed from: c */
    d f14431c;
    private boolean d;

    /* renamed from: com.jingdong.manto.widget.input.autofill.a$a */
    /* loaded from: classes16.dex */
    class C0695a implements PopupWindow.OnDismissListener {
        C0695a() {
            a.this = r1;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            a aVar = a.this;
            if (aVar.f14431c == null || aVar.d) {
                return;
            }
            a.this.f14431c.a("", d.a.CANCEL);
        }
    }

    /* loaded from: classes16.dex */
    private final class b implements View.OnClickListener {
        View a;
        View b;

        /* renamed from: c */
        final a f14432c;
        TextView d;

        /* renamed from: e */
        TextView f14433e;

        /* renamed from: f */
        View f14434f;

        /* renamed from: g */
        h f14435g;

        b(a aVar, a aVar2, View view) {
            this.f14432c = aVar2;
            this.a = view;
            this.d = (TextView) view.findViewById(R.id.title);
            this.f14433e = (TextView) view.findViewById(R.id.content);
            this.f14434f = view.findViewById(R.id.close);
            this.b = view.findViewById(R.id.divider);
            view.setBackgroundResource(R.drawable.manto_pop_menu_selector);
            view.setOnClickListener(this);
            this.f14434f.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            d dVar;
            if (this.f14435g == null) {
                return;
            }
            if (view.getId() == R.id.close) {
                this.f14432c.remove(this.f14435g);
                d dVar2 = this.f14432c.f14431c;
                if (dVar2 != null) {
                    dVar2.a(this.f14435g.b, d.a.DELETE);
                }
            } else if (view != this.a || (dVar = this.f14432c.f14431c) == null) {
            } else {
                h hVar = this.f14435g;
                if (hVar != null) {
                    dVar.a(hVar.b, d.a.SELECT);
                }
                this.f14432c.d = true;
                if (this.f14432c.b != null) {
                    this.f14432c.b.a.getView().clearFocus();
                }
            }
        }
    }

    public final void a() {
        this.b.a((PopupWindow.OnDismissListener) null);
        this.b = null;
    }

    @Override // com.jingdong.manto.widget.input.autofill.c
    public final void a(e eVar) {
        this.b = eVar;
        eVar.a(new C0695a());
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public final Filter getFilter() {
        return super.getFilter();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.a.inflate(R.layout.manto_input_autofill_item, viewGroup, false);
        }
        b bVar = (b) view.getTag();
        if (bVar == null) {
            bVar = new b(this, this, view);
            view.setTag(bVar);
        }
        h item = getItem(i2);
        bVar.f14435g = item;
        bVar.d.setText(item.f14419c);
        bVar.f14433e.setText(item.a);
        bVar.f14433e.setVisibility(TextUtils.isEmpty(item.a) ? 8 : 0);
        bVar.b.setVisibility(i2 == getCount() + (-1) ? 8 : 0);
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
