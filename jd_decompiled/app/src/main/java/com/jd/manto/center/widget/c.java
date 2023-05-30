package com.jd.manto.center.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.h;
import java.util.List;

/* loaded from: classes17.dex */
public class c extends com.jd.manto.center.widget.a<String> {

    /* renamed from: j  reason: collision with root package name */
    private e f6602j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6603g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ b f6604h;

        a(String str, b bVar) {
            this.f6603g = str;
            this.f6604h = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (h.d() || c.this.f6602j == null) {
                return;
            }
            com.jd.manto.center.h.b.i(c.this.f6598g, this.f6603g);
            c.this.f6602j.c(this.f6603g, String.valueOf(this.f6604h.c() + 1));
        }
    }

    public c(Context context, List<String> list, int i2) {
        super(context, list, i2);
    }

    @Override // com.jd.manto.center.widget.a
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void a(b bVar, String str) {
        if (com.jd.manto.center.k.b.a(bVar, str)) {
            return;
        }
        TextView textView = (TextView) bVar.d(R.id.txt_discover_name);
        h.i(textView, str);
        h.j(textView, 12.0f);
        ((LinearLayout) bVar.d(R.id.ll_discover_container)).setOnClickListener(new a(str, bVar));
    }

    public void f(e eVar) {
        this.f6602j = eVar;
    }
}
