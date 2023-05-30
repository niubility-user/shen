package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.k.c;
import com.jingdong.app.mall.home.r.f.a.o;
import com.jingdong.common.recommend.RecommendMtaUtils;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class MallIconFloorElderAdapter extends MallIconBaseAdapter<IconViewHolder> {

    /* loaded from: classes4.dex */
    public class IconViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private SimpleDraweeView b;

        /* renamed from: c  reason: collision with root package name */
        private final RelativeLayout f9708c;

        IconViewHolder(MallIconFloorElderAdapter mallIconFloorElderAdapter, View view, int i2) {
            super(view);
            f.n(view);
            RelativeLayout relativeLayout = (RelativeLayout) view;
            this.f9708c = relativeLayout;
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mallIconFloorElderAdapter.o(), mallIconFloorElderAdapter.b.n0() + mallIconFloorElderAdapter.p(i2)));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mallIconFloorElderAdapter.b.j0(), mallIconFloorElderAdapter.b.h0());
            layoutParams.addRule(14);
            layoutParams.topMargin = mallIconFloorElderAdapter.b.i0() + mallIconFloorElderAdapter.p(i2);
            HomeDraweeView homeDraweeView = new HomeDraweeView(mallIconFloorElderAdapter.f9707c);
            this.b = homeDraweeView;
            homeDraweeView.setLayoutParams(layoutParams);
            this.b.setId(R.id.home_icon_image);
            relativeLayout.addView(this.b);
            if (n.j()) {
                this.b.setLayerType(1, null);
            }
            this.a = new TextView(mallIconFloorElderAdapter.f9707c);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, mallIconFloorElderAdapter.b.v0());
            layoutParams2.addRule(14);
            layoutParams2.addRule(12);
            this.a.setIncludeFontPadding(false);
            this.a.setLayoutParams(layoutParams2);
            this.a.setGravity(17);
            this.a.setEllipsize(TextUtils.TruncateAt.END);
            this.a.setTextColor(-8092023);
            this.a.setTextSize(0, d.d(mallIconFloorElderAdapter.b.w0()));
            this.a.getPaint().setFakeBoldText(true);
            this.a.setSingleLine();
            relativeLayout.addView(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f9709g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f9710h;

        a(MallIconFloorElderAdapter mallIconFloorElderAdapter, Context context, c cVar) {
            this.f9709g = context;
            this.f9710h = cVar;
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
            l.e(this.f9709g, this.f9710h.getJump());
            this.f9710h.q();
            com.jingdong.app.mall.home.r.c.a.u("Home_Shortcut", "", this.f9710h.o.toString(), RecommendMtaUtils.Home_PageId, null, l.f());
        }
    }

    public MallIconFloorElderAdapter(o oVar, Context context) {
        super(oVar, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int o() {
        return d.d(this.b.m0() == 3 ? 244 : 180);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int p(int i2) {
        if (i2 < this.b.m0()) {
            return d.d(32);
        }
        return 0;
    }

    private void s(IconViewHolder iconViewHolder, c cVar) {
        if (iconViewHolder == null || cVar == null || cVar.getJump() == null) {
            return;
        }
        iconViewHolder.itemView.setOnClickListener(new a(this, iconViewHolder.itemView.getContext(), cVar));
    }

    private void t(IconViewHolder iconViewHolder, c cVar) {
        if (cVar == null) {
            return;
        }
        if (iconViewHolder.a != null && cVar.f10726m != null) {
            iconViewHolder.a.setText(cVar.f10726m);
            iconViewHolder.a.setTextColor(this.b.u0(this.a));
            iconViewHolder.a.setTextSize(0, d.d(this.b.w0()));
        }
        e.i(cVar.f10727n, iconViewHolder.b, null, false);
    }

    private void u(IconViewHolder iconViewHolder, int i2) {
        int h0 = this.b.h0();
        int j0 = this.b.j0();
        ViewGroup.LayoutParams layoutParams = iconViewHolder.b.getLayoutParams();
        f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.height = h0;
        layoutParams2.width = j0;
        layoutParams2.topMargin = this.b.i0() + p(i2);
        iconViewHolder.b.setLayoutParams(layoutParams2);
        int n0 = this.b.n0() + p(i2);
        int o = o();
        ViewGroup.LayoutParams layoutParams3 = iconViewHolder.itemView.getLayoutParams();
        layoutParams3.height = n0;
        layoutParams3.width = o;
        iconViewHolder.itemView.setLayoutParams(layoutParams3);
        iconViewHolder.a.getLayoutParams().height = this.b.v0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull IconViewHolder iconViewHolder, int i2) {
        c d0 = this.b.d0(this.a, i2);
        u(iconViewHolder, i2);
        t(iconViewHolder, d0);
        s(iconViewHolder, d0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public IconViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i2) {
        IconViewHolder iconViewHolder = new IconViewHolder(this, new RelativeLayout(this.f9707c), i2);
        m.f(iconViewHolder);
        return iconViewHolder;
    }
}
