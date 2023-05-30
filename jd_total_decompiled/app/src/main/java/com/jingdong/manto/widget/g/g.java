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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.List;

/* loaded from: classes16.dex */
public class g extends PopupWindow {
    protected Activity a;
    private View b;

    /* renamed from: c  reason: collision with root package name */
    private RelativeLayout f14383c;
    private RelativeLayout d;

    /* renamed from: e  reason: collision with root package name */
    private RecyclerView f14384e;

    /* renamed from: f  reason: collision with root package name */
    private d f14385f;

    /* renamed from: g  reason: collision with root package name */
    private List<com.jingdong.manto.widget.j.d> f14386g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f14387h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.dismiss();
        }
    }

    /* loaded from: classes16.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.e();
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a(com.jingdong.manto.widget.j.d dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class d extends RecyclerView.Adapter<b> {
        private Activity a;
        private List<com.jingdong.manto.widget.j.d> b;

        /* renamed from: c  reason: collision with root package name */
        private c f14388c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class a implements View.OnClickListener {
            final /* synthetic */ com.jingdong.manto.widget.j.d a;

            a(com.jingdong.manto.widget.j.d dVar) {
                this.a = dVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                d.this.f14388c.a(this.a);
                g.this.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class b extends RecyclerView.ViewHolder {
            RelativeLayout a;
            TextView b;

            /* renamed from: c  reason: collision with root package name */
            ImageView f14389c;
            ImageView d;

            /* renamed from: e  reason: collision with root package name */
            TextView f14390e;

            private b(d dVar, View view) {
                super(view);
                this.a = (RelativeLayout) view.findViewById(R.id.item_layout);
                this.f14389c = (ImageView) view.findViewById(R.id.imageView);
                this.b = (TextView) view.findViewById(R.id.f12994tv);
                this.d = (ImageView) view.findViewById(R.id.red);
                this.f14390e = (TextView) view.findViewById(R.id.count);
            }

            /* synthetic */ b(d dVar, View view, a aVar) {
                this(dVar, view);
            }
        }

        private d(Activity activity, List<com.jingdong.manto.widget.j.d> list) {
            this.a = activity;
            this.b = list;
        }

        /* synthetic */ d(g gVar, Activity activity, List list, a aVar) {
            this(activity, list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(c cVar) {
            this.f14388c = cVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return new b(this, LayoutInflater.from(this.a).inflate(R.layout.manto_top_popupwindow_list_item, viewGroup, false), null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull b bVar, int i2) {
            TextView textView;
            Resources resources;
            int i3;
            com.jingdong.manto.widget.j.d dVar;
            TextView textView2;
            String str;
            if (g.this.c()) {
                bVar.a.setBackgroundResource(R.drawable.manto_top_popup_item_bg_dark);
                bVar.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.manto_top_popup_item_red_dark));
                bVar.f14390e.setBackgroundResource(R.drawable.manto_top_popup_item_count_dark);
                textView = bVar.b;
                resources = this.a.getResources();
                i3 = R.color.manto_un_content_level_1_dark;
            } else {
                bVar.a.setBackgroundResource(R.drawable.manto_top_popup_item_bg);
                bVar.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.manto_top_popup_item_red));
                bVar.f14390e.setBackgroundResource(R.drawable.manto_top_popup_item_count);
                textView = bVar.b;
                resources = this.a.getResources();
                i3 = R.color.manto_un_content_level_1;
            }
            textView.setTextColor(resources.getColor(i3));
            List<com.jingdong.manto.widget.j.d> list = this.b;
            if (list == null || list.size() <= i2 || (dVar = this.b.get(i2)) == null) {
                return;
            }
            if (dVar.c() != null) {
                bVar.f14389c.setImageDrawable(dVar.c());
                g.this.a(bVar.f14389c, dVar.getTitle().toString());
            } else {
                bVar.f14389c.setImageDrawable(null);
            }
            bVar.f14389c.setVisibility(0);
            bVar.b.setText(dVar.getTitle());
            if (dVar.getCount() > 0) {
                bVar.f14390e.setVisibility(0);
                bVar.d.setVisibility(8);
                if (dVar.getCount() > 99) {
                    textView2 = bVar.f14390e;
                    str = "99+";
                } else {
                    textView2 = bVar.f14390e;
                    str = dVar.getCount() + "";
                }
                textView2.setText(str);
            } else {
                bVar.f14390e.setVisibility(8);
                if (dVar.a()) {
                    bVar.d.setVisibility(0);
                } else {
                    bVar.d.setVisibility(8);
                }
            }
            bVar.itemView.setOnClickListener(new a(dVar));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<com.jingdong.manto.widget.j.d> list = this.b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    public g(Activity activity) {
        super(activity);
        this.f14387h = false;
        this.a = activity;
        a(activity);
        b();
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.manto_top_popupwindow, (ViewGroup) null);
        this.b = inflate;
        this.f14383c = (RelativeLayout) inflate.findViewById(R.id.jd_top_popup_back);
        this.f14384e = (RecyclerView) this.b.findViewById(R.id.jd_top_popup_recycle);
        RelativeLayout relativeLayout = (RelativeLayout) this.b.findViewById(R.id.im_top_popup_close);
        this.d = relativeLayout;
        relativeLayout.setOnClickListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ImageView imageView, String str) {
        Resources resources;
        int i2;
        if (imageView == null) {
            return;
        }
        if (TextUtils.equals(str, this.a.getString(R.string.manto_page_menu_un_favor))) {
            imageView.setColorFilter(this.a.getResources().getColor(R.color.manto_unfavo_color_red));
            return;
        }
        if (c()) {
            resources = this.a.getResources();
            i2 = R.color.manto_un_content_level_1_dark;
        } else {
            resources = this.a.getResources();
            i2 = R.color.manto_un_content_level_1;
        }
        imageView.setColorFilter(resources.getColor(i2));
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

    public g a() {
        this.f14383c.setBackgroundResource(R.drawable.manto_top_popup_bg_dark);
        return this;
    }

    public void a(c cVar) {
        d dVar;
        if (cVar == null || (dVar = this.f14385f) == null) {
            return;
        }
        dVar.a(cVar);
    }

    public void a(List<com.jingdong.manto.widget.j.d> list) {
        this.f14386g = list;
        this.f14385f = new d(this, this.a, list, null);
        this.f14384e.setLayoutManager(new GridLayoutManager(getContentView().getContext(), 4));
        this.f14384e.setAdapter(this.f14385f);
    }

    public void b(List<com.jingdong.manto.widget.j.d> list) {
        this.f14386g = list;
        MantoThreadUtils.runOnUIThread(new b());
    }

    public boolean c() {
        boolean z = com.jingdong.manto.k.a.b().a() == 1;
        this.f14387h = z;
        return z;
    }

    public g d() {
        this.f14383c.setBackgroundResource(R.drawable.manto_top_popup_bg);
        return this;
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
    }

    public void e() {
        d dVar = this.f14385f;
        if (dVar != null) {
            dVar.notifyDataSetChanged();
        }
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
