package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.view.widget.IconLabel;
import com.jingdong.app.mall.home.floor.view.widget.IconLottieView;
import com.jingdong.app.mall.home.r.e.k.c;
import com.jingdong.app.mall.home.r.f.a.o;
import com.jingdong.common.recommend.RecommendMtaUtils;

/* loaded from: classes4.dex */
public class MallIconFloorNewAdapter extends MallIconBaseAdapter<IconViewHolder> {

    /* loaded from: classes4.dex */
    public class IconViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private SimpleDraweeView b;

        /* renamed from: c  reason: collision with root package name */
        private SimpleDraweeView f9711c;
        private final RelativeLayout d;

        /* renamed from: e  reason: collision with root package name */
        private IconLottieView f9712e;

        /* renamed from: f  reason: collision with root package name */
        private RelativeLayout f9713f;

        /* renamed from: g  reason: collision with root package name */
        private f f9714g;

        /* renamed from: h  reason: collision with root package name */
        private IconLabel f9715h;

        IconViewHolder(View view) {
            super(view);
            this.f9714g = new f(86, 28);
            com.jingdong.app.mall.home.o.a.f.n(view);
            RelativeLayout relativeLayout = (RelativeLayout) view;
            this.d = relativeLayout;
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(MallIconFloorNewAdapter.this.b.p0(), MallIconFloorNewAdapter.this.b.n0()));
            int j0 = MallIconFloorNewAdapter.this.b.j0();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(j0, MallIconFloorNewAdapter.this.b.h0());
            layoutParams.addRule(14);
            layoutParams.topMargin = MallIconFloorNewAdapter.this.b.i0();
            HomeDraweeView homeDraweeView = new HomeDraweeView(MallIconFloorNewAdapter.this.f9707c);
            this.b = homeDraweeView;
            homeDraweeView.setLayoutParams(layoutParams);
            this.b.setId(R.id.home_icon_image);
            relativeLayout.addView(this.b);
            if (n.j()) {
                this.b.setLayerType(1, null);
            }
            this.a = new TextView(MallIconFloorNewAdapter.this.f9707c);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, MallIconFloorNewAdapter.this.b.v0());
            layoutParams2.addRule(14);
            layoutParams2.addRule(12);
            this.a.setIncludeFontPadding(false);
            if (MallIconFloorNewAdapter.this.b.y0() || MallIconFloorNewAdapter.this.b.J0()) {
                this.a.setPadding(0, -3, 0, -3);
                this.a.setIncludeFontPadding(true);
            }
            this.a.setLayoutParams(layoutParams2);
            this.a.setGravity(17);
            this.a.setEllipsize(TextUtils.TruncateAt.END);
            this.a.setTextColor(-8092023);
            this.a.setTextSize(0, j());
            this.a.setSingleLine();
            relativeLayout.addView(this.a);
            int d = d.d(28);
            int d2 = d.d(28);
            int o0 = (((MallIconFloorNewAdapter.this.b.o0() / 2) + (j0 / 2)) - d) + d.d(7);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(d, d2);
            layoutParams3.addRule(8, this.b.getId());
            layoutParams3.leftMargin = o0;
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(MallIconFloorNewAdapter.this.f9707c);
            this.f9711c = homeDraweeView2;
            homeDraweeView2.setLayoutParams(layoutParams3);
            this.f9711c.setImageResource(R.drawable.home_icon_focus);
            this.f9711c.setVisibility(8);
            relativeLayout.addView(this.f9711c);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int j() {
            int w0 = MallIconFloorNewAdapter.this.b.w0();
            if (!MallIconFloorNewAdapter.this.b.z0() && !MallIconFloorNewAdapter.this.b.K0() && !MallIconFloorNewAdapter.this.b.y0() && !MallIconFloorNewAdapter.this.b.J0()) {
                return d.d(e.b().c(w0));
            }
            return d.d(w0);
        }

        private void k(int i2) {
            IconLottieView iconLottieView = this.f9712e;
            if (iconLottieView != null) {
                iconLottieView.setVisibility(i2);
            }
        }

        void h(c cVar) {
            if (!cVar.i()) {
                RelativeLayout relativeLayout = this.f9713f;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.f9713f == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(MallIconFloorNewAdapter.this.f9707c);
                this.f9713f = relativeLayout2;
                RelativeLayout.LayoutParams u = this.f9714g.u(relativeLayout2);
                u.addRule(11);
                this.d.addView(this.f9713f, u);
            }
            ViewGroup.LayoutParams layoutParams = this.f9713f.getLayoutParams();
            com.jingdong.app.mall.home.o.a.f.n(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (layoutParams2 != null) {
                layoutParams2.topMargin = MallIconFloorNewAdapter.this.b.q0();
                layoutParams2.rightMargin = ((MallIconFloorNewAdapter.this.b.o0() / 2) - d.d(4)) - this.f9714g.v();
                this.f9713f.setLayoutParams(layoutParams2);
            }
            this.f9713f.setVisibility(0);
            f fVar = new f(-2, -1);
            IconLabel iconLabel = this.f9715h;
            if (iconLabel == null) {
                IconLabel iconLabel2 = new IconLabel(MallIconFloorNewAdapter.this.f9707c);
                this.f9715h = iconLabel2;
                this.f9713f.addView(this.f9715h, fVar.u(iconLabel2));
            } else {
                f.c(iconLabel, fVar);
            }
            this.f9715h.c(cVar.d(), "", 18, -1, this.f9714g.v());
        }

        void i(c cVar) {
            if (!cVar.j()) {
                k(8);
                return;
            }
            IconLottieView iconLottieView = this.f9712e;
            if (iconLottieView == null) {
                MallIconFloorNewAdapter mallIconFloorNewAdapter = MallIconFloorNewAdapter.this;
                IconLottieView iconLottieView2 = new IconLottieView(mallIconFloorNewAdapter.f9707c, mallIconFloorNewAdapter.b);
                this.f9712e = iconLottieView2;
                this.d.addView(iconLottieView2);
            } else {
                this.f9712e.setLayoutParams(iconLottieView.getLayoutParams());
            }
            k(0);
            this.f9712e.i(cVar);
            this.f9712e.h(cVar, MallIconFloorNewAdapter.this.h());
        }

        public void l(int i2) {
            this.a.setTextSize(0, d.d(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f9717g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f9718h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ IconViewHolder f9719i;

        a(MallIconFloorNewAdapter mallIconFloorNewAdapter, Context context, c cVar, IconViewHolder iconViewHolder) {
            this.f9717g = context;
            this.f9718h = cVar;
            this.f9719i = iconViewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (l.k()) {
                return;
            }
            View findViewById = view.findViewById(R.id.home_icon_reddot);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            l.e(this.f9717g, this.f9718h.getJump());
            this.f9718h.q();
            com.jingdong.app.mall.home.r.c.a.u("Home_Shortcut", this.f9718h.getJump().getSrv(), this.f9718h.o.toString(), RecommendMtaUtils.Home_PageId, null, !TextUtils.isEmpty(l.a) ? l.a : "");
            if (!this.f9718h.p || this.f9719i.f9712e == null) {
                return;
            }
            this.f9718h.l();
            this.f9719i.f9712e.f();
        }
    }

    public MallIconFloorNewAdapter(o oVar, Context context) {
        super(oVar, context);
    }

    private void o(IconViewHolder iconViewHolder, c cVar) {
        if (iconViewHolder == null || cVar == null || cVar.getJump() == null) {
            return;
        }
        iconViewHolder.itemView.setOnClickListener(new a(this, iconViewHolder.itemView.getContext(), cVar, iconViewHolder));
    }

    private void p(IconViewHolder iconViewHolder, c cVar) {
        o oVar;
        if (cVar == null || (oVar = this.b) == null) {
            return;
        }
        if (!oVar.y0() && !this.b.J0()) {
            iconViewHolder.b.setPadding(0, 0, 0, 0);
        } else {
            int d = d.d(this.b.e0());
            iconViewHolder.b.setPadding(d, d, d, d);
        }
        iconViewHolder.i(cVar);
        iconViewHolder.h(cVar);
        if (iconViewHolder.d != null && iconViewHolder.a != null && cVar.f10726m != null) {
            iconViewHolder.a.setTextSize(0, iconViewHolder.j());
            iconViewHolder.a.setText(com.jingdong.app.mall.home.o.a.f.l(iconViewHolder.a, this.b.o0() - 10, cVar.f10726m));
            iconViewHolder.a.setTextColor(this.b.u0(this.a));
        }
        if (iconViewHolder.f9711c != null) {
            if ("1".equals(cVar.n())) {
                iconViewHolder.f9711c.setVisibility(0);
            } else {
                iconViewHolder.f9711c.setVisibility(8);
            }
        }
        if (iconViewHolder.b == null) {
            return;
        }
        if (!this.b.y0() && !this.b.J0()) {
            iconViewHolder.b.setPadding(0, 0, 0, 0);
        } else {
            int d2 = d.d(this.b.e0());
            iconViewHolder.b.setPadding(d2, d2, d2, d2);
        }
        iconViewHolder.b.setScaleType(ImageView.ScaleType.FIT_XY);
        com.jingdong.app.mall.home.floor.ctrl.e.u(iconViewHolder.b, cVar.f10727n);
    }

    private void q(IconViewHolder iconViewHolder) {
        ViewGroup.LayoutParams layoutParams = iconViewHolder.b.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2 != null) {
            layoutParams2.width = this.b.j0();
            layoutParams2.height = this.b.h0();
            layoutParams2.topMargin = this.b.i0();
            iconViewHolder.b.setLayoutParams(layoutParams2);
        }
        int n0 = this.b.n0();
        int p0 = this.b.p0();
        ViewGroup.LayoutParams layoutParams3 = iconViewHolder.itemView.getLayoutParams();
        layoutParams3.height = n0;
        layoutParams3.width = p0;
        iconViewHolder.itemView.setLayoutParams(layoutParams3);
        ViewGroup.LayoutParams layoutParams4 = iconViewHolder.a.getLayoutParams();
        if (layoutParams4 != null) {
            layoutParams4.height = this.b.v0();
            iconViewHolder.a.setLayoutParams(layoutParams4);
        }
        if (this.b.y0() || this.b.J0()) {
            iconViewHolder.a.setPadding(0, -3, 0, -3);
            iconViewHolder.a.setIncludeFontPadding(true);
        } else {
            iconViewHolder.a.setIncludeFontPadding(false);
        }
        iconViewHolder.a.setTextSize(0, iconViewHolder.j());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002b, code lost:
        if ((r7 % r5.b.m0()) == 4) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r7 == (r3 - 1)) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0055, code lost:
        if (r7 == (r3 - 2)) goto L11;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBindViewHolder(com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter.IconViewHolder r6, int r7) {
        /*
            r5 = this;
            com.jingdong.app.mall.home.r.f.a.o r0 = r5.b
            boolean r0 = r0.J0()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L2f
            com.jingdong.app.mall.home.r.f.a.o r0 = r5.b
            boolean r0 = r0.A0()
            if (r0 == 0) goto L13
            goto L2f
        L13:
            com.jingdong.app.mall.home.r.f.a.o r0 = r5.b
            int r3 = r5.a
            com.jingdong.app.mall.home.r.e.k.c r0 = r0.d0(r3, r7)
            com.jingdong.app.mall.home.r.f.a.o r3 = r5.b
            boolean r3 = r3.M0()
            if (r3 == 0) goto L58
            com.jingdong.app.mall.home.r.f.a.o r3 = r5.b
            int r3 = r3.m0()
            int r7 = r7 % r3
            r3 = 4
            if (r7 != r3) goto L58
        L2d:
            r1 = 1
            goto L58
        L2f:
            com.jingdong.app.mall.home.r.f.a.o r0 = r5.b
            com.jingdong.app.mall.home.r.e.k.c r0 = r0.U(r7)
            com.jingdong.app.mall.home.r.f.a.o r3 = r5.b
            int r3 = r3.f0()
            com.jingdong.app.mall.home.r.f.a.o r4 = r5.b
            boolean r4 = r4.J0()
            if (r4 == 0) goto L47
            int r4 = r3 + (-1)
            if (r7 == r4) goto L2d
        L47:
            com.jingdong.app.mall.home.r.f.a.o r4 = r5.b
            boolean r4 = r4.A0()
            if (r4 == 0) goto L58
            int r4 = r3 + (-1)
            if (r7 == r4) goto L2d
            int r3 = r3 + (-2)
            if (r7 != r3) goto L58
            goto L2d
        L58:
            if (r0 == 0) goto L5d
            r0.m(r1)
        L5d:
            r5.q(r6)
            r5.p(r6, r0)
            r5.o(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter.onBindViewHolder(com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter$IconViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public IconViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        IconViewHolder iconViewHolder = new IconViewHolder(new RelativeLayout(this.f9707c));
        m.f(iconViewHolder);
        return iconViewHolder;
    }
}
