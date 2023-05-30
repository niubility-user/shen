package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import com.jingdong.manto.R;
import com.jingdong.manto.o.m;
import com.jingdong.manto.utils.MantoStringUtils;

/* loaded from: classes16.dex */
public class c extends m {

    /* loaded from: classes16.dex */
    class a implements DialogInterface.OnClickListener {
        a(c cVar) {
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
        final /* synthetic */ String f13895c;

        b(c cVar, Activity activity, com.jingdong.manto.f fVar, String str) {
            this.a = activity;
            this.b = fVar;
            this.f13895c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
            com.jingdong.manto.m.w0.b.a(this.a, this.b.f13017i, false, this.f13895c);
        }
    }

    /* renamed from: com.jingdong.manto.o.c$c  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class DialogInterfaceOnClickListenerC0644c implements DialogInterface.OnClickListener {
        DialogInterfaceOnClickListenerC0644c(c cVar) {
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
        final /* synthetic */ String f13896c;

        d(c cVar, Activity activity, com.jingdong.manto.f fVar, String str) {
            this.a = activity;
            this.b = fVar;
            this.f13896c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
            com.jingdong.manto.m.w0.b.a(this.a, this.b.f13017i, true, this.f13896c);
        }
    }

    public c() {
        super(9);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        DialogInterface.OnClickListener dialogInterfaceOnClickListenerC0644c;
        DialogInterface.OnClickListener dVar;
        int i2;
        com.jingdong.manto.f h2 = nVar.h();
        if (h2 == null) {
            return;
        }
        String optional = MantoStringUtils.optional(nVar.h().f13016h == null ? "" : nVar.h().f13016h.type, "");
        if (h2.v()) {
            dialogInterfaceOnClickListenerC0644c = new a(this);
            dVar = new b(this, activity, h2, optional);
            i2 = R.string.manto_debug_switch_dialog_message_close;
        } else {
            dialogInterfaceOnClickListenerC0644c = new DialogInterfaceOnClickListenerC0644c(this);
            dVar = new d(this, activity, h2, optional);
            i2 = R.string.manto_debug_switch_dialog_message_open;
        }
        h2.a(com.jingdong.manto.widget.dialog.a.a(activity, null, activity.getString(i2), activity.getString(R.string.manto_confirm), activity.getString(R.string.manto_cancel), dVar, dialogInterfaceOnClickListenerC0644c, null, null, null));
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null) {
            return;
        }
        cVar.a(nVar2.f13908c, h2.v() ? R.string.manto_page_menu_close_debug : R.string.manto_page_menu_open_debug, R.drawable.manto_menu_debug).a(true);
    }
}
