package com.jingdong.app.mall.home.deploy.view.layout.coreimg;

import android.content.Context;
import android.view.View;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.p.b.d.a;
import com.jingdong.app.mall.home.p.b.d.b;
import com.jingdong.app.mall.home.p.b.d.c;

/* loaded from: classes4.dex */
public class DCoreImg extends CoreBaseView implements b {
    private DCoreImgModel p;
    private a q;
    private FitTopImage r;
    private f s;

    public DCoreImg(Context context) {
        super(context);
        this.r = new FitTopImage(context);
        f fVar = new f(-1, -1);
        this.s = fVar;
        FitTopImage fitTopImage = this.r;
        addView(fitTopImage, fVar.u(fitTopImage));
    }

    @Override // com.jingdong.app.mall.home.p.b.d.b
    public void a(View view) {
        if (JDHomeFragment.O0()) {
            a aVar = new a(view, this);
            this.q = aVar;
            view.setAnimation(aVar);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DCoreImgModel) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        e.d(this.r, s());
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.r, this.p.y0());
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.coreimg.DCoreImg.1
            {
                DCoreImg.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreImg.this.p.v0(DCoreImg.this.r, 0, false);
                c.g().f(DCoreImg.this.p, DCoreImg.this);
            }
        });
    }
}
