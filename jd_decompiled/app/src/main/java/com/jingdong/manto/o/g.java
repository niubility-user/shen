package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import com.jingdong.manto.R;
import com.jingdong.manto.o.m;
import com.jingdong.manto.utils.MantoStringUtils;

/* loaded from: classes16.dex */
public class g extends m {

    /* loaded from: classes16.dex */
    class a implements DialogInterface.OnClickListener {
        a(g gVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
        }
    }

    /* loaded from: classes16.dex */
    class b implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;
        final /* synthetic */ com.jingdong.manto.f b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13903c;

        b(g gVar, Activity activity, com.jingdong.manto.f fVar, String str) {
            this.a = activity;
            this.b = fVar;
            this.f13903c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
            com.jingdong.manto.r.b.a(this.a, this.b.f13017i, false, this.f13903c);
        }
    }

    /* loaded from: classes16.dex */
    class c implements DialogInterface.OnClickListener {
        c(g gVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
        }
    }

    /* loaded from: classes16.dex */
    class d implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;
        final /* synthetic */ com.jingdong.manto.f b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13904c;

        d(g gVar, Activity activity, com.jingdong.manto.f fVar, String str) {
            this.a = activity;
            this.b = fVar;
            this.f13904c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
            com.jingdong.manto.r.b.a(this.a, this.b.f13017i, true, this.f13904c);
        }
    }

    public g() {
        super(10);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        DialogInterface.OnClickListener cVar;
        DialogInterface.OnClickListener dVar;
        int i2;
        com.jingdong.manto.f h2 = nVar.h();
        if (h2 == null) {
            return;
        }
        String optional = MantoStringUtils.optional(nVar.h().f13016h == null ? "" : nVar.h().f13016h.type, "");
        com.jingdong.manto.i.e eVar = h2.s;
        if (eVar == null || !eVar.r) {
            cVar = new c(this);
            dVar = new d(this, activity, h2, optional);
            i2 = R.string.manto_perf_switch_dialog_message_open;
        } else {
            cVar = new a(this);
            dVar = new b(this, activity, h2, optional);
            i2 = R.string.manto_perf_switch_dialog_message_close;
        }
        h2.a(com.jingdong.manto.widget.dialog.a.a(activity, null, activity.getString(i2), activity.getString(R.string.manto_confirm), activity.getString(R.string.manto_cancel), dVar, cVar, null, null, null));
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null) {
            return;
        }
        com.jingdong.manto.i.e eVar = h2.s;
        cVar.a(nVar2.f13908c, (eVar == null || !eVar.r) ? R.string.manto_page_menu_open_perf : R.string.manto_page_menu_close_perf, R.drawable.manto_menu_perf).a(true);
    }
}
