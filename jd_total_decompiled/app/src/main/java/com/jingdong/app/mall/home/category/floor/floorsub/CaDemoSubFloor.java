package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.widget.CaProgress;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.b;
import com.jingdong.app.mall.home.n.g.x.d;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaDemoSubFloor extends BaseCaRecycleItem<d> {
    Random q;
    private CaProgress r;
    private f s;

    public CaDemoSubFloor(Context context) {
        super(context);
        Random random = new Random();
        this.q = random;
        setBackgroundColor(Color.argb(255, random.nextInt(255), this.q.nextInt(255), this.q.nextInt(255)));
        CaProgress caProgress = new CaProgress(context);
        this.r = caProgress;
        caProgress.a(200, 12);
        this.r.d(-7829368);
        this.r.f(-6749953, -39424, -65434);
        f fVar = new f(200, 12);
        this.s = fVar;
        fVar.F(new Rect(0, 0, 0, 20));
        RelativeLayout.LayoutParams u = this.s.u(this.r);
        u.addRule(12);
        u.addRule(14);
        addView(this.r, u);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull d dVar) {
        if (dVar.l() == b.S_HORIZONTAL) {
            this.r.h(this.q.nextFloat(), 2000L);
        } else {
            this.r.setVisibility(8);
        }
    }
}
