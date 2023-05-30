package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.SecKillSkuLabelText;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.w;
import com.jingdong.common.entity.Product;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MallPanicFloorRecyclerAdapter extends BaseHeaderFooterRecyclerAdapter {

    /* renamed from: f  reason: collision with root package name */
    private static Typeface f9720f;

    /* renamed from: g  reason: collision with root package name */
    public static final int[] f9721g = {-855881862, -907508};

    /* renamed from: c  reason: collision with root package name */
    private Context f9722c;
    private w d;

    /* renamed from: e  reason: collision with root package name */
    public List<Product> f9723e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MallPanicFloorRecyclerAdapter.this.d.z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MallPanicFloorRecyclerAdapter.this.d.a(view);
        }
    }

    /* loaded from: classes4.dex */
    class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9726g;

        c(int i2) {
            this.f9726g = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MallPanicFloorRecyclerAdapter.this.d.y0(this.f9726g);
        }
    }

    /* loaded from: classes4.dex */
    static class d extends RecyclerView.ViewHolder {
        private View a;
        private RelativeLayout b;

        /* renamed from: c  reason: collision with root package name */
        private SimpleDraweeView f9728c;
        private SimpleDraweeView d;

        /* renamed from: e  reason: collision with root package name */
        private TextView f9729e;

        /* renamed from: f  reason: collision with root package name */
        private TextView f9730f;

        /* renamed from: g  reason: collision with root package name */
        private SecKillSkuLabelText f9731g;

        public d(View view) {
            super(view);
            this.a = view;
            this.b = (RelativeLayout) view.findViewById(R.id.app_home_limit_buy_wrap);
            this.f9728c = (SimpleDraweeView) view.findViewById(R.id.app_home_limit_buy_star_pic);
            this.d = (SimpleDraweeView) view.findViewById(R.id.app_home_limit_buy_icon);
            this.f9729e = (TextView) view.findViewById(R.id.app_home_jdprice);
            this.f9730f = (TextView) view.findViewById(R.id.app_home_limit_buy_price);
            this.f9731g = (SecKillSkuLabelText) view.findViewById(R.id.tv_discount);
            this.f9730f.setSingleLine();
            this.f9730f.setEllipsize(TextUtils.TruncateAt.END);
            this.f9729e.setSingleLine();
            this.f9729e.setEllipsize(TextUtils.TruncateAt.END);
            try {
                ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
                f.n(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.width = com.jingdong.app.mall.home.floor.common.d.d(140);
                layoutParams2.height = com.jingdong.app.mall.home.floor.common.d.d(140);
                ViewGroup.LayoutParams layoutParams3 = this.f9728c.getLayoutParams();
                f.n(layoutParams3);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams3;
                marginLayoutParams.width = com.jingdong.app.mall.home.floor.common.d.d(140);
                marginLayoutParams.height = com.jingdong.app.mall.home.floor.common.d.d(214);
                ViewGroup.LayoutParams layoutParams4 = this.d.getLayoutParams();
                f.n(layoutParams4);
                RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) layoutParams4;
                layoutParams5.width = com.jingdong.app.mall.home.floor.common.d.d(140);
                layoutParams5.height = com.jingdong.app.mall.home.floor.common.d.d(140);
                this.f9729e.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(32));
                ViewGroup.LayoutParams layoutParams6 = this.f9729e.getLayoutParams();
                f.n(layoutParams6);
                ((ViewGroup.MarginLayoutParams) layoutParams6).height = com.jingdong.app.mall.home.floor.common.d.d(50);
                this.f9730f.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(23));
                this.f9730f.setTypeface(FontsUtil.getTypeFace(this.a.getContext(), 4099));
                ViewGroup.LayoutParams layoutParams7 = this.f9730f.getLayoutParams();
                f.n(layoutParams7);
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams7;
                marginLayoutParams2.topMargin = com.jingdong.app.mall.home.floor.common.d.d(38);
                marginLayoutParams2.height = com.jingdong.app.mall.home.floor.common.d.d(35);
                this.f9731g.setPadding(com.jingdong.app.mall.home.floor.common.d.d(12), 0, com.jingdong.app.mall.home.floor.common.d.d(12), 0);
                if (MallPanicFloorRecyclerAdapter.f9720f != null) {
                    this.f9729e.setTypeface(MallPanicFloorRecyclerAdapter.f9720f);
                    this.f9730f.setTypeface(MallPanicFloorRecyclerAdapter.f9720f);
                }
            } catch (Exception e2) {
                f.s0(this, e2);
            }
        }
    }

    public MallPanicFloorRecyclerAdapter(Context context, w wVar, List<Product> list) {
        this.f9722c = context;
        this.d = wVar;
        f9720f = FontsUtil.getTypeFace(context);
        if (list == null) {
            this.f9723e = new ArrayList();
        } else {
            this.f9723e = list;
        }
        if (this.d.X()) {
            setHeaderView(s());
        }
        if (this.f9723e.size() > 0) {
            p(t());
        }
    }

    private void u(GradientTextView gradientTextView, Product product) {
        if (TextUtils.isEmpty(product.msPromotionTag)) {
            gradientTextView.setVisibility(8);
            return;
        }
        gradientTextView.setVisibility(0);
        float d2 = f.M0() ? com.jingdong.app.mall.home.floor.common.d.d(14) : 0.0f;
        float d3 = f.M0() ? com.jingdong.app.mall.home.floor.common.d.d(20) : 0.0f;
        int[] p0 = this.d.p0() == null ? f9721g : this.d.p0();
        p0[0] = p0[0] & (-855638017);
        gradientTextView.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(20));
        gradientTextView.setTextColor(-1);
        gradientTextView.setText(product.msPromotionTag);
        gradientTextView.setEllipsize(TextUtils.TruncateAt.END);
        float measureText = gradientTextView.getPaint().measureText(product.msPromotionTag);
        ViewGroup.LayoutParams layoutParams = gradientTextView.getLayoutParams();
        f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, p0);
        gradientDrawable.setShape(0);
        if (measureText > com.jingdong.app.mall.home.floor.common.d.d(110)) {
            layoutParams2.width = com.jingdong.app.mall.home.floor.common.d.d(140);
            gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, d3, d3, d3, d3});
        } else {
            layoutParams2.width = -2;
            gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, d2, d2, d2, d2, d3, d3});
        }
        gradientTextView.setBackgroundDrawable(gradientDrawable);
        gradientTextView.setLayoutParams(layoutParams2);
    }

    private void v(SecKillSkuLabelText secKillSkuLabelText, Product product) {
        CharSequence jsonString = com.jingdong.app.mall.home.r.e.b.getJsonString(product.prdObject, "interestPoint", "");
        if (TextUtils.isEmpty(jsonString)) {
            secKillSkuLabelText.setVisibility(8);
            return;
        }
        secKillSkuLabelText.setVisibility(0);
        secKillSkuLabelText.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(18));
        secKillSkuLabelText.setTextColor(this.d.h0());
        secKillSkuLabelText.setText(jsonString);
        secKillSkuLabelText.setEllipsize(TextUtils.TruncateAt.END);
        ViewGroup.LayoutParams layoutParams = secKillSkuLabelText.getLayoutParams();
        f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = com.jingdong.app.mall.home.floor.common.d.d(140);
        layoutParams2.height = com.jingdong.app.mall.home.floor.common.d.d(30);
        secKillSkuLabelText.setLayoutParams(layoutParams2);
        if (secKillSkuLabelText.getPaint().measureText(secKillSkuLabelText.getText().toString()) > com.jingdong.app.mall.home.floor.common.d.d(116)) {
            secKillSkuLabelText.setVisibility(8);
        } else {
            secKillSkuLabelText.g(this.d.t0());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.adapter.BaseHeaderFooterRecyclerAdapter
    public int l() {
        return this.f9723e.size();
    }

    @Override // com.jingdong.app.mall.home.floor.view.adapter.BaseHeaderFooterRecyclerAdapter
    public int m(int i2) {
        return 1;
    }

    @Override // com.jingdong.app.mall.home.floor.view.adapter.BaseHeaderFooterRecyclerAdapter
    public void n(RecyclerView.ViewHolder viewHolder, int i2) {
        Object S = this.d.S(i2);
        f.n(S);
        Product product = (Product) S;
        f.n(viewHolder);
        d dVar = (d) viewHolder;
        dVar.a.setOnClickListener(new c(i2));
        dVar.d.setBackgroundColor(0);
        if (product != null) {
            dVar.f9730f.setTextColor(-6316129);
            dVar.f9731g.h(false);
            dVar.f9730f.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(23));
            if (this.d.n0() == 1) {
                e.l(product.getImageUrl(), dVar.d);
                String jsonString = com.jingdong.app.mall.home.r.e.b.getJsonString(product.prdObject, "finalPrice", "");
                TextView textView = dVar.f9729e;
                if (TextUtils.isEmpty(jsonString)) {
                    jsonString = product.getMiaoShaPrice();
                }
                textView.setText(jsonString);
                dVar.f9730f.setText("\u9884\u4f30\u4ef7");
                dVar.f9730f.getPaint().setFlags(0);
                dVar.f9730f.setTextColor(-381927);
                dVar.f9730f.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(18));
                dVar.f9731g.h(true);
                v(dVar.f9731g, product);
                dVar.f9728c.setVisibility(8);
                dVar.b.setVisibility(0);
                com.jingdong.app.mall.home.n.h.e.d(dVar.b, com.jingdong.app.mall.home.floor.common.d.d(20));
            } else {
                int i3 = product.msItemType;
                if (i3 == 1) {
                    e.l(product.getImageUrl(), dVar.d);
                    dVar.f9729e.setText(product.getMiaoShaPrice());
                    dVar.f9730f.setText(com.jingdong.app.mall.home.category.floor.feedssub.a.e(product.getJdPriceNoNull()));
                    dVar.f9730f.getPaint().setFlags(17);
                    u(dVar.f9731g, product);
                    dVar.f9728c.setVisibility(8);
                    dVar.b.setVisibility(0);
                    com.jingdong.app.mall.home.n.h.e.d(dVar.b, com.jingdong.app.mall.home.floor.common.d.d(20));
                } else if (i3 == 8) {
                    e.l(product.getImageUrl(), dVar.d);
                    dVar.f9730f.setText(TextUtils.isEmpty(product.getProvinceStockContent()) ? "\u62fc\u56e2" : product.getProvinceStockContent());
                    dVar.f9729e.setText(product.getMiaoShaPrice());
                    dVar.f9730f.getPaint().setFlags(0);
                    u(dVar.f9731g, product);
                    dVar.f9728c.setVisibility(8);
                    dVar.b.setVisibility(0);
                } else {
                    e.l(product.getImageUrl(), dVar.f9728c);
                    dVar.b.setVisibility(8);
                    dVar.f9728c.setVisibility(0);
                    com.jingdong.app.mall.home.n.h.e.d(dVar.f9728c, com.jingdong.app.mall.home.floor.common.d.d(20));
                }
            }
            dVar.f9729e.setTextColor(this.d.w0() ? -381927 : -1957094);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.adapter.BaseHeaderFooterRecyclerAdapter
    public RecyclerView.ViewHolder o(ViewGroup viewGroup, int i2) {
        View inflate = ImageUtil.inflate(this.f9722c, R.layout.home_limit_buy_shitem, viewGroup, false);
        inflate.setContentDescription("\u4eac\u4e1c\u79d2\u6740");
        inflate.setLayoutParams(new LinearLayout.LayoutParams(this.d.R(), -1));
        return new d(inflate);
    }

    protected View s() {
        RelativeLayout.LayoutParams layoutParams;
        w wVar = this.d;
        if (wVar == null) {
            return new View(this.f9722c);
        }
        int Q = (wVar.Q() * 216) / 230;
        if (Q < this.d.R()) {
            layoutParams = new RelativeLayout.LayoutParams(Q, this.d.Q());
        } else {
            layoutParams = new RelativeLayout.LayoutParams(this.d.R(), (this.d.R() * 230) / 216);
        }
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9722c);
        Context context = this.f9722c;
        int i2 = R.string.home_obstacle_free;
        homeDraweeView.setContentDescription(context.getString(i2));
        homeDraweeView.setLayoutParams(layoutParams);
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        homeDraweeView.setOnClickListener(new b());
        e.d(homeDraweeView, this.d.P());
        RelativeLayout relativeLayout = new RelativeLayout(this.f9722c);
        relativeLayout.setContentDescription(this.f9722c.getString(i2));
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(this.d.R(), this.d.Q()));
        relativeLayout.setGravity(17);
        relativeLayout.addView(homeDraweeView);
        return relativeLayout;
    }

    protected View t() {
        if (this.d == null) {
            return new View(this.f9722c);
        }
        View inflate = LayoutInflater.from(this.f9722c).inflate(R.layout.home_shop_recycler_view_footer, (ViewGroup) null, false);
        inflate.setContentDescription(this.f9722c.getString(R.string.home_obstacle_free));
        inflate.setLayoutParams(new RecyclerView.LayoutParams(-2, this.d.R()));
        inflate.setOnClickListener(new a());
        return inflate;
    }
}
