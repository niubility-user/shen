package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.widget.CaBottomLine;
import com.jingdong.app.mall.home.category.widget.CaSelectView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.x.n;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaSelectSubFloor extends BaseCaRecycleItem<n> {
    private TextView q;
    private f r;
    private ImageView s;
    private ImageView t;
    private CaBottomLine u;
    private f v;
    private RelativeLayout w;

    public CaSelectSubFloor(Context context) {
        super(context);
        this.w = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(14);
        addView(this.w, layoutParams);
        HomeImageView homeImageView = new HomeImageView(getContext());
        this.s = homeImageView;
        homeImageView.setId(R.id.mallfloor_item1);
        this.s.setScaleType(ImageView.ScaleType.FIT_XY);
        this.w.addView(this.s, new RelativeLayout.LayoutParams(0, 0));
        TextView textView = new TextView(context);
        this.q = textView;
        textView.setId(R.id.mallfloor_item2);
        this.q.setTextColor(-14277082);
        this.q.setGravity(17);
        this.q.setMaxLines(1);
        f fVar = new f(-2, -1);
        this.r = fVar;
        fVar.K(new Rect(0, 0, 0, 8));
        RelativeLayout.LayoutParams u = this.r.u(this.q);
        u.addRule(1, this.s.getId());
        this.w.addView(this.q, u);
        HomeImageView homeImageView2 = new HomeImageView(getContext());
        this.t = homeImageView2;
        homeImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
        layoutParams2.addRule(1, this.q.getId());
        this.w.addView(this.t, layoutParams2);
        CaBottomLine caBottomLine = new CaBottomLine(context);
        this.u = caBottomLine;
        caBottomLine.setVisibility(8);
        f fVar2 = new f(-1, 6);
        this.v = fVar2;
        fVar2.F(new Rect(0, 72, 0, 0));
        RelativeLayout.LayoutParams u2 = this.v.u(this.u);
        u2.addRule(5, this.q.getId());
        u2.addRule(7, this.q.getId());
        this.w.addView(this.u, u2);
    }

    private void o() {
        p();
        q();
        f.c(this.q, this.r);
        f.c(this.u, this.v);
        this.u.setVisibility((!((n) this.f8679m).E().get() || ((n) this.f8679m).C()) ? 8 : 0);
        this.u.a(d.d(6), d.d(6), d.d(52), -907508, -700358, -339509);
        this.q.setMinWidth(d.d(52));
        this.q.setMaxWidth(d.d(110));
        this.q.setTextSize(0, d.d(26));
        this.q.getPaint().setFakeBoldText(((n) this.f8679m).E().get());
        this.q.setText(((n) this.f8679m).w());
    }

    private void p() {
        f v = ((n) this.f8679m).v();
        if (v == null) {
            this.s.setVisibility(8);
            return;
        }
        int u = ((n) this.f8679m).u();
        if (u < 0) {
            this.s.setVisibility(8);
            return;
        }
        this.s.setVisibility(0);
        ImageView imageView = this.s;
        imageView.setLayoutParams(v.u(imageView));
        this.s.setImageResource(u);
    }

    private void q() {
        f z = ((n) this.f8679m).z();
        if (z == null) {
            this.t.setVisibility(8);
            return;
        }
        int y = ((n) this.f8679m).y();
        if (y < 0) {
            this.t.setVisibility(8);
            return;
        }
        this.t.setVisibility(0);
        ImageView imageView = this.t;
        imageView.setLayoutParams(z.u(imageView));
        this.t.setImageResource(y);
    }

    private void s(n nVar, boolean z) {
        ViewGroup c2 = this.f8680n.c();
        if (c2 instanceof CaSelectView) {
            ((CaSelectView) c2).b(nVar, z, this.o);
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        boolean z = ((n) this.f8679m).E().get();
        if (!z || ((n) this.f8679m).C() || ((n) this.f8679m).D()) {
            ((n) this.f8679m).I(((n) this.f8679m).D() || !z);
            s((n) this.f8679m, true);
            o();
            return true;
        }
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull n nVar) {
        if (nVar.E().get()) {
            s(nVar, false);
        }
        o();
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull n nVar, List<Object> list) {
        super.j(nVar, list);
        o();
    }
}
