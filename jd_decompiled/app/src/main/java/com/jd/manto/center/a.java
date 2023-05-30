package com.jd.manto.center;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.manto.center.model.AppItemWrapper;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public final class a extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Context a;
    final List<AppItemWrapper> b;

    /* renamed from: c  reason: collision with root package name */
    private final f f6493c;

    /* renamed from: e  reason: collision with root package name */
    private g f6494e;

    /* renamed from: f  reason: collision with root package name */
    private AnimatorSet f6495f;
    private int d = -1;

    /* renamed from: g  reason: collision with root package name */
    private final List<AppItemWrapper> f6496g = new ArrayList(5);

    /* renamed from: h  reason: collision with root package name */
    private final List<AppItemWrapper> f6497h = new ArrayList(50);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.center.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class ViewOnClickListenerC0181a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AppItemWrapper f6498g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6499h;

        ViewOnClickListenerC0181a(AppItemWrapper appItemWrapper, int i2) {
            this.f6498g = appItemWrapper;
            this.f6499h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!a.this.v()) {
                a.this.f6493c.a(this.f6498g, this.f6499h);
            } else {
                a.this.A();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AppItemWrapper f6501g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6502h;

        b(AppItemWrapper appItemWrapper, RecyclerView.ViewHolder viewHolder) {
            this.f6501g = appItemWrapper;
            this.f6502h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!a.this.v()) {
                a.this.f6493c.a(this.f6501g, this.f6502h.getAdapterPosition());
            } else {
                a.this.A();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6504g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ AppItemWrapper f6505h;

        c(RecyclerView.ViewHolder viewHolder, AppItemWrapper appItemWrapper) {
            this.f6504g = viewHolder;
            this.f6505h = appItemWrapper;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int adapterPosition = this.f6504g.getAdapterPosition();
            if (adapterPosition >= 0 && adapterPosition < a.this.b.size()) {
                a.this.f6493c.b(this.f6505h, adapterPosition);
                MantoTrack.sendCommonDataWithExt(a.this.a, "\u5c0f\u7a0b\u5e8f\u5220\u9664", "Applets_Center_Delete", this.f6505h.entity.appId, "", "", "", "", null);
                return;
            }
            MantoLog.e(DYConstants.DY_CENTER, "position not valid.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AppItemWrapper f6507g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ g f6508h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6509i;

        d(AppItemWrapper appItemWrapper, g gVar, RecyclerView.ViewHolder viewHolder) {
            this.f6507g = appItemWrapper;
            this.f6508h = gVar;
            this.f6509i = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (a.this.f6495f == null || !a.this.f6495f.isRunning()) {
                a.this.f6495f = null;
                if (a.this.d >= 0) {
                    a.this.A();
                }
                MantoTrack.sendCommonDataWithExt(a.this.a, "\u957f\u6309\u5c0f\u7a0b\u5e8f\u56fe\u6807", "Applets_Center_LongPress", this.f6507g.entity.appId, "", "", "", "", null);
                a.this.s(this.f6508h, this.f6509i.getAdapterPosition());
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g f6511g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6512h;

        e(g gVar, int i2) {
            this.f6511g = gVar;
            this.f6512h = i2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            a.this.f6494e = this.f6511g;
            this.f6511g.d.setVisibility(0);
            a.this.B(this.f6512h);
        }
    }

    /* loaded from: classes17.dex */
    interface f {
        void a(AppItemWrapper appItemWrapper, int i2);

        void b(AppItemWrapper appItemWrapper, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes17.dex */
    public class g extends RecyclerView.ViewHolder {
        TextView a;
        TextView b;

        /* renamed from: c  reason: collision with root package name */
        ImageView f6514c;
        ImageView d;

        g(a aVar, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_app_name);
            this.b = (TextView) view.findViewById(R.id.tv_tag);
            this.f6514c = (ImageView) view.findViewById(R.id.iv_logo);
            this.d = (ImageView) view.findViewById(R.id.iv_del);
        }
    }

    /* loaded from: classes17.dex */
    private class h extends RecyclerView.ViewHolder {
        TextView a;

        public h(@NonNull a aVar, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.title);
        }
    }

    public a(Context context, List<AppItemWrapper> list, f fVar) {
        this.a = context;
        this.b = list;
        this.f6493c = fVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void B(int i2) {
        this.d = i2;
    }

    private void C() {
        this.b.clear();
        this.b.addAll(this.f6496g);
        this.b.addAll(this.f6497h);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(g gVar, int i2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(gVar.f6514c, "scaleX", 1.0f, 1.5f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(gVar.f6514c, "scaleY", 1.0f, 1.5f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(gVar.f6514c, "alpha", 1.0f, 0.48f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f6495f = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        this.f6495f.setDuration(250L).start();
        this.f6495f.addListener(new e(gVar, i2));
    }

    private void t(RecyclerView.ViewHolder viewHolder, AppItemWrapper appItemWrapper, int i2) {
        if (!(viewHolder instanceof g) || appItemWrapper == null) {
            return;
        }
        g gVar = (g) viewHolder;
        gVar.a.setText(appItemWrapper.title);
        gVar.b.setVisibility(4);
        gVar.f6514c.setImageResource(appItemWrapper.iconRes);
        viewHolder.itemView.setOnClickListener(new ViewOnClickListenerC0181a(appItemWrapper, i2));
    }

    private void u(RecyclerView.ViewHolder viewHolder, AppItemWrapper appItemWrapper) {
        PkgHistoryEntity pkgHistoryEntity;
        if (!(viewHolder instanceof g) || (pkgHistoryEntity = appItemWrapper.entity) == null) {
            return;
        }
        g gVar = (g) viewHolder;
        gVar.a.setText(pkgHistoryEntity.name);
        gVar.d.setVisibility(4);
        if ("2".equals(appItemWrapper.entity.type)) {
            gVar.b.setText("\u4f53\u9a8c\u7248");
            gVar.b.setVisibility(0);
        } else {
            gVar.b.setVisibility(4);
        }
        IImageLoader iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
        if (iImageLoader != null) {
            iImageLoader.loadImage(gVar.f6514c, appItemWrapper.entity.logo);
        }
        View view = viewHolder.itemView;
        if (view != null) {
            view.setOnClickListener(new b(appItemWrapper, viewHolder));
            gVar.d.setOnClickListener(new c(viewHolder, appItemWrapper));
            viewHolder.itemView.setOnLongClickListener(new d(appItemWrapper, gVar, viewHolder));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void A() {
        g gVar;
        if (this.d > 0 && (gVar = this.f6494e) != null) {
            gVar.d.setVisibility(4);
        }
        B(-1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.b.get(i2).type;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        AppItemWrapper appItemWrapper = this.b.get(i2);
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            ((h) viewHolder).a.setText(appItemWrapper.title);
        } else if (itemViewType == 1 || itemViewType == 2) {
            u(viewHolder, appItemWrapper);
        } else if (itemViewType == 3 || itemViewType == 4) {
            t(viewHolder, appItemWrapper, viewHolder.getAdapterPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (i2 == 0) {
            return new h(this, from.inflate(R.layout.manto_center_item_nav_title, viewGroup, false));
        }
        if (i2 != 2 && i2 != 1) {
            return new g(this, from.inflate(R.layout.manto_center_app_item_layout, viewGroup, false));
        }
        return new g(this, from.inflate(R.layout.manto_center_app_item_layout, viewGroup, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void q(int i2, AppItemWrapper appItemWrapper) {
        int size = this.b.size();
        int i3 = -1;
        for (int i4 = 0; i4 < 10 && i4 < size; i4++) {
            if (this.b.get(i4).type == 3) {
                i3 = i4;
            }
        }
        if (i3 != -1 && appItemWrapper != null) {
            this.b.add(i3, appItemWrapper);
            notifyItemRangeChanged(i2, i3);
        } else {
            MantoLog.d(DYConstants.DY_CENTER, "to del direct: " + i2);
            if (i3 != -1) {
                try {
                    this.b.remove(i3);
                    notifyItemRemoved(i3);
                } catch (Exception unused) {
                }
            }
            notifyItemRemoved(i2);
        }
        B(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(PkgHistoryEntity pkgHistoryEntity) {
        AppItemWrapper appItemWrapper = new AppItemWrapper(2, pkgHistoryEntity);
        this.b.indexOf(new AppItemWrapper("\u6211\u7684\u5173\u6ce8"));
        try {
            if (!this.f6497h.isEmpty()) {
                this.f6497h.add(1, appItemWrapper);
            }
            C();
        } catch (Exception unused) {
        }
    }

    public boolean v() {
        return this.d >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(List<AppItemWrapper> list) {
        this.f6497h.clear();
        this.f6497h.add(new AppItemWrapper("\u6211\u7684\u5173\u6ce8"));
        if (list != null && list.size() > 0) {
            this.f6497h.addAll(list);
        }
        this.f6497h.add(new AppItemWrapper(4, this.a.getString(R.string.manto_center_add), R.drawable.manto_center_add_app));
        C();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(List<AppItemWrapper> list) {
        this.f6496g.clear();
        this.f6496g.add(new AppItemWrapper("\u6700\u8fd1\u4f7f\u7528"));
        if (list != null && list.size() > 0) {
            this.f6496g.addAll(list);
            if (this.f6496g.size() >= 5) {
                this.f6496g.add(new AppItemWrapper(3, "", R.drawable.manto_center_more_app));
            }
        }
        C();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void y(int i2) {
        AppItemWrapper remove = i2 < this.b.size() ? this.b.remove(i2) : null;
        if (!this.f6497h.isEmpty()) {
            this.f6497h.remove(remove);
        }
        notifyItemRemoved(i2);
        B(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(PkgHistoryEntity pkgHistoryEntity) {
        AppItemWrapper appItemWrapper = new AppItemWrapper(2, pkgHistoryEntity);
        int indexOf = this.b.indexOf(appItemWrapper);
        if (indexOf >= 0) {
            try {
                if (!this.f6497h.isEmpty()) {
                    this.f6497h.remove(appItemWrapper);
                }
                this.b.remove(indexOf);
                notifyItemRemoved(indexOf);
            } catch (Exception unused) {
            }
        }
    }
}
