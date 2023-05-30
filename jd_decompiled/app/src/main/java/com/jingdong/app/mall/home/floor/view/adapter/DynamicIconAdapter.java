package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.view.widget.DynamicIconLottieView;
import com.jingdong.app.mall.home.floor.view.widget.IconLabel;
import com.jingdong.app.mall.home.r.e.k.b;
import com.jingdong.app.mall.home.r.f.a.e;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;

/* loaded from: classes4.dex */
public class DynamicIconAdapter extends RecyclerView.Adapter<IconViewHolder> {
    private final Context a;
    private final e b;

    /* renamed from: c  reason: collision with root package name */
    private int f9693c = 0;

    /* loaded from: classes4.dex */
    public class IconViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout a;
        private final f b;

        /* renamed from: c  reason: collision with root package name */
        private final SimpleDraweeView f9694c;
        private final f d;

        /* renamed from: e  reason: collision with root package name */
        private final TextView f9695e;

        /* renamed from: f  reason: collision with root package name */
        private final f f9696f;

        /* renamed from: g  reason: collision with root package name */
        private final SimpleDraweeView f9697g;

        /* renamed from: h  reason: collision with root package name */
        private final f f9698h;

        /* renamed from: i  reason: collision with root package name */
        private RelativeLayout f9699i;

        /* renamed from: j  reason: collision with root package name */
        private final f f9700j;

        /* renamed from: k  reason: collision with root package name */
        private IconLabel f9701k;

        /* renamed from: l  reason: collision with root package name */
        private DynamicIconLottieView f9702l;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ b f9704g;

            a(b bVar) {
                this.f9704g = bVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpEntity jump;
                if (l.k() || (jump = this.f9704g.getJump()) == null) {
                    return;
                }
                l.e(DynamicIconAdapter.this.a, jump);
                this.f9704g.t();
                com.jingdong.app.mall.home.r.c.a.u("Home_Shortcut", jump.getSrv(), this.f9704g.f10721k.toString(), RecommendMtaUtils.Home_PageId, null, !TextUtils.isEmpty(l.a) ? l.a : "");
                if (this.f9704g.f10723m && IconViewHolder.this.f9699i != null && IconViewHolder.this.f9699i.getVisibility() == 0) {
                    this.f9704g.b();
                    IconViewHolder.this.f9699i.setVisibility(8);
                }
            }
        }

        public IconViewHolder(@NonNull View view) {
            super(view);
            this.f9700j = new f(86, 28);
            DynamicIconEntity.ViewConfig g0 = DynamicIconAdapter.this.b.g0();
            com.jingdong.app.mall.home.o.a.f.n(view);
            RelativeLayout relativeLayout = (RelativeLayout) view;
            this.a = relativeLayout;
            f fVar = new f(g0.iconContainerWidth, g0.iconContainerHeight);
            this.b = fVar;
            f.d(relativeLayout, fVar, true);
            HomeDraweeView homeDraweeView = new HomeDraweeView(DynamicIconAdapter.this.a);
            this.f9694c = homeDraweeView;
            homeDraweeView.setId(R.id.home_icon_image);
            f fVar2 = new f(g0.iconWidth, g0.iconHeight);
            this.d = fVar2;
            fVar2.E(0, g0.iconTopMargin, 0, 0);
            RelativeLayout.LayoutParams u = fVar2.u(homeDraweeView);
            u.addRule(14);
            relativeLayout.addView(homeDraweeView, u);
            if (n.j()) {
                homeDraweeView.setLayerType(1, null);
            }
            g gVar = new g(DynamicIconAdapter.this.a, false);
            gVar.d(17);
            gVar.c(true);
            gVar.h();
            TextView a2 = gVar.a();
            this.f9695e = a2;
            int i2 = g0.iconTextBottomMargin;
            f fVar3 = new f(-1, i2 > 0 ? (i2 * 2) + g0.iconTextSize : -2);
            this.f9696f = fVar3;
            fVar3.E(0, 0, 0, g0.iconTextBottomMargin);
            RelativeLayout.LayoutParams u2 = fVar3.u(a2);
            u2.addRule(14);
            u2.addRule(12);
            relativeLayout.addView(a2, u2);
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(DynamicIconAdapter.this.a);
            this.f9697g = homeDraweeView2;
            homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar4 = new f(28, 28);
            this.f9698h = fVar4;
            fVar4.E((((DynamicIconAdapter.this.b.c0() / 2) + (g0.iconWidth / 2)) + 7) - 28, 0, 0, 0);
            RelativeLayout.LayoutParams u3 = fVar4.u(homeDraweeView2);
            u3.addRule(8, homeDraweeView.getId());
            relativeLayout.addView(homeDraweeView2, u3);
            homeDraweeView2.setImageResource(R.drawable.home_icon_focus);
            homeDraweeView2.setVisibility(8);
        }

        private void d(b bVar, DynamicIconEntity.ViewConfig viewConfig) {
            if (!bVar.p()) {
                RelativeLayout relativeLayout = this.f9699i;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(8);
                }
                IconLabel iconLabel = this.f9701k;
                if (iconLabel != null) {
                    iconLabel.setVisibility(8);
                }
                DynamicIconLottieView dynamicIconLottieView = this.f9702l;
                if (dynamicIconLottieView != null) {
                    dynamicIconLottieView.i();
                    return;
                }
                return;
            }
            boolean m2 = bVar.m();
            this.f9700j.R(86, m2 ? -2 : viewConfig.tagHeight);
            RelativeLayout relativeLayout2 = this.f9699i;
            if (relativeLayout2 == null) {
                RelativeLayout relativeLayout3 = new RelativeLayout(DynamicIconAdapter.this.a);
                this.f9699i = relativeLayout3;
                RelativeLayout.LayoutParams u = this.f9700j.u(relativeLayout3);
                u.addRule(11);
                this.a.addView(this.f9699i, u);
            } else {
                f.c(relativeLayout2, this.f9700j);
            }
            ViewGroup.LayoutParams layoutParams = this.f9699i.getLayoutParams();
            com.jingdong.app.mall.home.o.a.f.n(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int i2 = bVar.l() ? viewConfig.tagTop + 8 : viewConfig.tagTop;
            if (layoutParams2 != null) {
                layoutParams2.topMargin = d.d(i2);
                layoutParams2.rightMargin = ((DynamicIconAdapter.this.b.d0() / 2) - d.d(m2 ? viewConfig.lottieMarginLeft : viewConfig.tagMarginLeft)) - this.f9700j.v();
                this.f9699i.setLayoutParams(layoutParams2);
            }
            this.f9699i.setVisibility(0);
            f(bVar, viewConfig);
            e(bVar);
        }

        private void e(b bVar) {
            if (!bVar.q()) {
                DynamicIconLottieView dynamicIconLottieView = this.f9702l;
                if (dynamicIconLottieView != null) {
                    dynamicIconLottieView.i();
                    return;
                }
                return;
            }
            DynamicIconLottieView dynamicIconLottieView2 = this.f9702l;
            if (dynamicIconLottieView2 == null) {
                DynamicIconLottieView dynamicIconLottieView3 = new DynamicIconLottieView(DynamicIconAdapter.this.a);
                this.f9702l = dynamicIconLottieView3;
                this.f9699i.addView(dynamicIconLottieView3);
            } else {
                this.f9702l.setLayoutParams(dynamicIconLottieView2.getLayoutParams());
            }
            this.f9702l.setVisibility(0);
            this.f9702l.d(bVar);
        }

        private void f(b bVar, DynamicIconEntity.ViewConfig viewConfig) {
            if (!bVar.r()) {
                IconLabel iconLabel = this.f9701k;
                if (iconLabel != null) {
                    iconLabel.setVisibility(8);
                    return;
                }
                return;
            }
            f fVar = new f(-2, -1);
            IconLabel iconLabel2 = this.f9701k;
            if (iconLabel2 == null) {
                IconLabel iconLabel3 = new IconLabel(DynamicIconAdapter.this.a);
                this.f9701k = iconLabel3;
                this.f9699i.addView(this.f9701k, fVar.u(iconLabel3));
            } else {
                f.c(iconLabel2, fVar);
            }
            this.f9701k.setVisibility(0);
            this.f9701k.c(bVar.h(), bVar.d(), viewConfig.tagFont, viewConfig.tagColor, this.f9700j.v());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(b bVar) {
            if (bVar == null || bVar.getJump() == null) {
                return;
            }
            this.itemView.setOnClickListener(new a(bVar));
        }

        public int g(DynamicIconEntity.ViewConfig viewConfig) {
            if (!viewConfig.iconTextScaleSwitch) {
                return viewConfig.iconTextSize;
            }
            return com.jingdong.app.mall.home.e.b().c(viewConfig.iconTextSize);
        }

        public void i(b bVar) {
            DynamicIconEntity.ViewConfig g0 = DynamicIconAdapter.this.b.g0();
            this.d.R(g0.iconWidth, g0.iconHeight);
            this.d.E(0, g0.iconTopMargin, 0, 0);
            f.d(this.f9694c, this.d, true);
            this.f9694c.setScaleType(ImageView.ScaleType.FIT_XY);
            com.jingdong.app.mall.home.floor.ctrl.e.i(bVar.f10715e, this.f9694c, null, false);
            int i2 = g0.iconTextBottomMargin;
            this.f9696f.R(-1, i2 > 0 ? (i2 * 2) + g0.iconTextSize : -2);
            this.f9696f.E(0, 0, 0, Math.min(g0.iconTextBottomMargin, 0));
            f.d(this.f9695e, this.f9696f, true);
            this.f9695e.setTextColor(DynamicIconAdapter.this.b.f0());
            g.k(this.f9695e, g0.iconTextBold);
            g.o(this.f9695e, g(g0));
            this.f9695e.setText(com.jingdong.app.mall.home.o.a.f.l(this.f9695e, DynamicIconAdapter.this.b.d0() - 10, bVar.d));
            if (bVar.f10720j) {
                this.f9698h.E((((DynamicIconAdapter.this.b.c0() / 2) + (g0.iconWidth / 2)) + 7) - 28, 0, 0, 0);
                f.d(this.f9697g, this.f9698h, true);
                this.f9697g.setVisibility(0);
            } else {
                this.f9697g.setVisibility(8);
            }
            d(bVar, g0);
        }

        public void j(int i2) {
            this.f9695e.setTextSize(0, d.d(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class a extends RelativeLayout {

        /* renamed from: g  reason: collision with root package name */
        private final e f9706g;

        public a(Context context, e eVar) {
            super(context);
            this.f9706g = eVar;
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            DynamicIconEntity.ViewConfig g0 = this.f9706g.g0();
            if (g0 != null) {
                layoutParams.height = d.d(g0.iconContainerHeight);
                if (g0.isLineType) {
                    layoutParams.width = d.d(g0.iconContainerWidth);
                } else {
                    layoutParams.width = -1;
                }
            }
            super.setLayoutParams(layoutParams);
        }
    }

    public DynamicIconAdapter(Context context, e eVar) {
        this.a = context;
        this.b = eVar;
    }

    private void o(IconViewHolder iconViewHolder, b bVar) {
        if (this.b == null || iconViewHolder == null || bVar == null) {
            return;
        }
        iconViewHolder.i(bVar);
        iconViewHolder.h(bVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.X(this.f9693c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
        if (r8 == (r4 - 2)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (((r8 + 1) % r0.iconCountPerRow) == 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
        if (r8 == (r4 - 1)) goto L14;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBindViewHolder(@androidx.annotation.NonNull com.jingdong.app.mall.home.floor.view.adapter.DynamicIconAdapter.IconViewHolder r7, int r8) {
        /*
            r6 = this;
            com.jingdong.app.mall.home.r.f.a.e r0 = r6.b
            com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity$ViewConfig r0 = r0.g0()
            boolean r1 = r0.isLineType
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L2c
            com.jingdong.app.mall.home.r.f.a.e r1 = r6.b
            com.jingdong.app.mall.home.r.e.k.b r1 = r1.Z(r8)
            com.jingdong.app.mall.home.r.f.a.e r4 = r6.b
            int r4 = r4.b0()
            int r0 = r0.showLines
            if (r0 != r3) goto L20
            int r5 = r4 + (-1)
            if (r8 == r5) goto L2a
        L20:
            r5 = 2
            if (r0 != r5) goto L3b
            int r0 = r4 + (-1)
            if (r8 == r0) goto L2a
            int r4 = r4 - r5
            if (r8 != r4) goto L3b
        L2a:
            r2 = 1
            goto L3b
        L2c:
            com.jingdong.app.mall.home.r.f.a.e r1 = r6.b
            int r4 = r6.f9693c
            com.jingdong.app.mall.home.r.e.k.b r1 = r1.a0(r4, r8)
            int r8 = r8 + r3
            int r0 = r0.iconCountPerRow
            int r8 = r8 % r0
            if (r8 != 0) goto L3b
            goto L2a
        L3b:
            if (r1 == 0) goto L40
            r1.c(r2)
        L40:
            r6.o(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.adapter.DynamicIconAdapter.onBindViewHolder(com.jingdong.app.mall.home.floor.view.adapter.DynamicIconAdapter$IconViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        IconViewHolder iconViewHolder = new IconViewHolder(new a(this.a, this.b));
        m.f(iconViewHolder);
        return iconViewHolder;
    }

    public void n(int i2) {
        this.f9693c = i2;
    }
}
