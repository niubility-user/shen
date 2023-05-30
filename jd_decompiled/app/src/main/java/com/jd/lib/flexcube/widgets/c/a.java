package com.jd.lib.flexcube.widgets.c;

import android.content.Context;
import android.view.View;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;

/* loaded from: classes15.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    private Context f4508g;

    /* renamed from: h */
    private ClickEvent f4509h;

    /* renamed from: i */
    private BabelScope f4510i;

    /* loaded from: classes15.dex */
    public static class b {
        private a a;

        public b(Context context, ClickEvent clickEvent) {
            a aVar = new a();
            this.a = aVar;
            aVar.f4508g = context;
            this.a.f4509h = clickEvent;
        }

        public b a(BabelScope babelScope) {
            this.a.f4510i = babelScope;
            return this;
        }

        public a b() {
            return this.a;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.jd.lib.flexcube.widgets.b.a.a(this.f4508g, view, this.f4509h, this.f4510i);
    }

    private a() {
    }
}
