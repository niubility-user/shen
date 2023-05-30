package com.jingdong.manto.widget.g;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.manto.R;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.sdk.api.IImageLoader;
import java.util.List;

/* loaded from: classes16.dex */
public class f extends PopupWindow {
    protected Activity a;
    private View b;

    /* renamed from: c  reason: collision with root package name */
    private RelativeLayout f14375c;
    private RelativeLayout d;

    /* renamed from: e  reason: collision with root package name */
    private RecyclerView f14376e;

    /* renamed from: f  reason: collision with root package name */
    private b f14377f;

    /* renamed from: g  reason: collision with root package name */
    private List<PkgHistoryEntity> f14378g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f14379h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.dismiss();
        }
    }

    /* loaded from: classes16.dex */
    private class b extends RecyclerView.Adapter<d> {
        private List<PkgHistoryEntity> a;
        private Context b;

        /* renamed from: c  reason: collision with root package name */
        private c f14380c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class a implements View.OnClickListener {
            final /* synthetic */ PkgHistoryEntity a;
            final /* synthetic */ int b;

            a(PkgHistoryEntity pkgHistoryEntity, int i2) {
                this.a = pkgHistoryEntity;
                this.b = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                f.this.dismiss();
                if (b.this.f14380c != null) {
                    b.this.f14380c.a(this.a, this.b);
                }
            }
        }

        public b(Context context, List<PkgHistoryEntity> list) {
            this.b = context;
            this.a = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public d onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new d(f.this, LayoutInflater.from(this.b).inflate(R.layout.manto_nav_drop_list_item_layout, viewGroup, false));
        }

        public void a(c cVar) {
            this.f14380c = cVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(d dVar, int i2) {
            IImageLoader iImageLoader;
            TextView textView;
            int i3;
            TextView textView2;
            Resources resources;
            int i4;
            PkgHistoryEntity pkgHistoryEntity = this.a.get(i2);
            TextView textView3 = dVar.a;
            if (textView3 != null) {
                textView3.setText(pkgHistoryEntity.name);
                if (f.this.c()) {
                    textView2 = dVar.a;
                    resources = f.this.a.getResources();
                    i4 = R.color.manto_un_content_level_1_dark;
                } else {
                    textView2 = dVar.a;
                    resources = f.this.a.getResources();
                    i4 = R.color.manto_un_content_level_1;
                }
                textView2.setTextColor(resources.getColor(i4));
            }
            if (dVar.b != null) {
                if (TextUtils.equals("2", pkgHistoryEntity.type)) {
                    dVar.b.setText("\u4f53\u9a8c\u7248");
                    textView = dVar.b;
                    i3 = 0;
                } else {
                    textView = dVar.b;
                    i3 = 4;
                }
                textView.setVisibility(i3);
            }
            if (dVar.f14382c != null && (iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class)) != null) {
                iImageLoader.loadImage(dVar.f14382c, pkgHistoryEntity.logo);
            }
            View view = dVar.itemView;
            if (view != null) {
                view.setOnClickListener(new a(pkgHistoryEntity, i2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<PkgHistoryEntity> list = this.a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a(PkgHistoryEntity pkgHistoryEntity, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class d extends RecyclerView.ViewHolder {
        TextView a;
        TextView b;

        /* renamed from: c  reason: collision with root package name */
        ImageView f14382c;

        d(f fVar, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_app_name);
            this.b = (TextView) view.findViewById(R.id.tv_tag);
            this.f14382c = (ImageView) view.findViewById(R.id.iv_logo);
        }
    }

    public f(Activity activity) {
        super(activity);
        this.f14379h = false;
        this.a = activity;
        a(activity);
        b();
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.manto_top_popupwindow, (ViewGroup) null);
        this.b = inflate;
        this.f14375c = (RelativeLayout) inflate.findViewById(R.id.jd_top_popup_back);
        this.f14376e = (RecyclerView) this.b.findViewById(R.id.jd_top_popup_recycle);
        RelativeLayout relativeLayout = (RelativeLayout) this.b.findViewById(R.id.im_top_popup_close);
        this.d = relativeLayout;
        relativeLayout.setOnClickListener(new a());
    }

    @SuppressLint({"InlinedApi"})
    private void b() {
        setContentView(this.b);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(R.style.manto_popwin_anim_top_style);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setClippingEnabled(false);
    }

    public f a() {
        this.f14375c.setBackgroundResource(R.drawable.manto_top_popup_bg_dark);
        return this;
    }

    public void a(c cVar) {
        b bVar;
        if (cVar == null || (bVar = this.f14377f) == null) {
            return;
        }
        bVar.a(cVar);
    }

    public void a(List<PkgHistoryEntity> list) {
        this.f14378g = list;
        this.f14377f = new b(this.a, list);
        this.f14376e.setLayoutManager(new GridLayoutManager(getContentView().getContext(), 4));
        this.f14376e.setAdapter(this.f14377f);
    }

    public boolean c() {
        boolean z = com.jingdong.manto.k.a.b().a() == 1;
        this.f14379h = z;
        return z;
    }

    public f d() {
        this.f14375c.setBackgroundResource(R.drawable.manto_top_popup_bg);
        return this;
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view) {
        if (c()) {
            a();
        } else {
            d();
        }
        showAtLocation(view, 49, 0, 0);
    }
}
