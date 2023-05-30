package com.jingdong.app.mall.home.category.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.category.adapter.CaItemAdapter;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.n.g.s;
import com.jingdong.app.mall.home.n.g.x.n;

/* loaded from: classes4.dex */
public class CaSelectView extends RelativeLayout {
    private static GradientDrawable o = new GradientDrawable();

    /* renamed from: g  reason: collision with root package name */
    private CaContentLayout f8845g;

    /* renamed from: h  reason: collision with root package name */
    private RecyclerView f8846h;

    /* renamed from: i  reason: collision with root package name */
    private f f8847i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayoutManager f8848j;

    /* renamed from: k  reason: collision with root package name */
    private CaItemAdapter f8849k;

    /* renamed from: l  reason: collision with root package name */
    private s f8850l;

    /* renamed from: m  reason: collision with root package name */
    private n f8851m;

    /* renamed from: n  reason: collision with root package name */
    private CaDividerDecoration f8852n;

    /* loaded from: classes4.dex */
    class a extends LinearLayoutManager {
        a(CaSelectView caSelectView, Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollHorizontally() {
            return false;
        }
    }

    public CaSelectView(Context context, CaContentLayout caContentLayout) {
        super(context);
        CaDividerDecoration caDividerDecoration = new CaDividerDecoration();
        caDividerDecoration.g(4);
        this.f8852n = caDividerDecoration;
        this.f8845g = caContentLayout;
        RecyclerView recyclerView = new RecyclerView(context);
        this.f8846h = recyclerView;
        recyclerView.addItemDecoration(this.f8852n);
        this.f8846h.setNestedScrollingEnabled(false);
        this.f8846h.setClipToPadding(false);
        a aVar = new a(this, context);
        this.f8848j = aVar;
        aVar.setAutoMeasureEnabled(true);
        this.f8848j.setOrientation(0);
        this.f8846h.setLayoutManager(this.f8848j);
        CaItemAdapter caItemAdapter = new CaItemAdapter(context, this, this.f8846h);
        this.f8849k = caItemAdapter;
        this.f8846h.setAdapter(caItemAdapter);
        f fVar = new f(-1, 96);
        this.f8847i = fVar;
        fVar.K(new Rect(17, 0, 0, 0));
        RecyclerView recyclerView2 = this.f8846h;
        addView(recyclerView2, this.f8847i.u(recyclerView2));
    }

    public void a(s sVar) {
        this.f8852n.j(sVar.G());
        f.c(this.f8846h, this.f8847i);
        o.setColor(-1);
        float d = d.d(24);
        o.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, d, d, d, d});
        this.f8850l = sVar;
        this.f8849k.q(sVar.H());
    }

    public void b(n nVar, boolean z, int i2) {
        boolean z2 = !nVar.C();
        n nVar2 = this.f8851m;
        if (nVar2 != null && nVar2 != nVar && z2) {
            nVar2.I(false);
            this.f8849k.m(this.f8851m);
        }
        if (z2) {
            this.f8851m = nVar;
        }
        if (z) {
            this.f8845g.o(this.f8850l);
        }
    }

    public void c(boolean z) {
        this.f8846h.setBackgroundDrawable(z ? o : e.b);
    }
}
