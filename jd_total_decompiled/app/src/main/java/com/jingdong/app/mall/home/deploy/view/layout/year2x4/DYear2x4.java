package com.jingdong.app.mall.home.deploy.view.layout.year2x4;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class DYear2x4 extends YearBaseView {
    private DYear2x4Model p;
    private Context q;
    private IconImageText r;
    private f s;
    private DYear2x4Item[] t;
    private f[] u;

    public DYear2x4(Context context) {
        super(context);
        this.t = new DYear2x4Item[4];
        this.u = new f[4];
        setClipChildren(false);
        this.q = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DYear2x4Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
        this.r = new IconImageText(this.q);
        f fVar = new f(-2, 52);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.r;
        addView(view, this.s.u(view));
        int i2 = 0;
        while (i2 < 4) {
            View dYear2x4Item = new DYear2x4Item(getContext());
            f fVar2 = new f(160, 200);
            boolean z = i2 % 2 == 0;
            fVar2.E(z ? 16 : 0, i2 < 2 ? 46 : 246, z ? 0 : 16, 0);
            RelativeLayout.LayoutParams u = fVar2.u(dYear2x4Item);
            u.addRule(z ? 9 : 11);
            this.t[i2] = dYear2x4Item;
            this.u[i2] = fVar2;
            addView(dYear2x4Item, u);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.r.i(this.p.l0());
        for (final int i2 = 0; i2 < 4; i2++) {
            this.t[i2].a(this.p, i2);
            this.t[i2].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x4.DYear2x4.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DYear2x4Model dYear2x4Model = DYear2x4.this.p;
                    DYear2x4 dYear2x4 = DYear2x4.this;
                    int i3 = i2;
                    dYear2x4Model.g0(dYear2x4, i3, i3 + 1);
                }
            });
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x4.DYear2x4.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear2x4.this.p.e0(DYear2x4.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        for (int i2 = 0; i2 < 4; i2++) {
            f.c(this.t[i2], this.u[i2]);
        }
    }
}
