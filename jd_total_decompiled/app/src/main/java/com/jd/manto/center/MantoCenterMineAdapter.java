package com.jd.manto.center;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.manto.center.model.MantoCenterMineEntity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoCenterMineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context a;
    List<MantoCenterMineEntity.AppInfo> b;

    /* renamed from: c  reason: collision with root package name */
    private g f6276c;
    private int d = -1;

    /* renamed from: e  reason: collision with root package name */
    private h f6277e;

    /* renamed from: f  reason: collision with root package name */
    private AnimatorSet f6278f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6279g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6280h;

        a(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            this.f6279g = appInfo;
            this.f6280h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!MantoCenterMineAdapter.this.t()) {
                MantoCenterMineAdapter.this.f6276c.a(this.f6279g, this.f6280h);
            } else {
                MantoCenterMineAdapter.this.u();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6282g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6283h;

        b(MantoCenterMineEntity.AppInfo appInfo, RecyclerView.ViewHolder viewHolder) {
            this.f6282g = appInfo;
            this.f6283h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!MantoCenterMineAdapter.this.t()) {
                MantoCenterMineAdapter.this.f6276c.a(this.f6282g, this.f6283h.getAdapterPosition());
            } else {
                MantoCenterMineAdapter.this.u();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6285g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6286h;

        c(RecyclerView.ViewHolder viewHolder, MantoCenterMineEntity.AppInfo appInfo) {
            this.f6285g = viewHolder;
            this.f6286h = appInfo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int adapterPosition = this.f6285g.getAdapterPosition();
            if (adapterPosition >= 0 && adapterPosition < MantoCenterMineAdapter.this.b.size()) {
                MantoCenterMineAdapter.this.f6276c.b(this.f6286h, adapterPosition);
                MantoTrack.sendCommonDataWithExt(MantoCenterMineAdapter.this.a, "\u5c0f\u7a0b\u5e8f\u5220\u9664", "Applets_Center_Delete", this.f6286h.appId, "", "", "", "", null);
                return;
            }
            MantoLog.e(DYConstants.DY_CENTER, "position not valid.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6288g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ h f6289h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6290i;

        d(MantoCenterMineEntity.AppInfo appInfo, h hVar, RecyclerView.ViewHolder viewHolder) {
            this.f6288g = appInfo;
            this.f6289h = hVar;
            this.f6290i = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (MantoCenterMineAdapter.this.f6278f == null || !MantoCenterMineAdapter.this.f6278f.isRunning()) {
                MantoCenterMineAdapter.this.f6278f = null;
                if (MantoCenterMineAdapter.this.d >= 0) {
                    MantoCenterMineAdapter.this.u();
                }
                MantoTrack.sendCommonDataWithExt(MantoCenterMineAdapter.this.a, "\u957f\u6309\u5c0f\u7a0b\u5e8f\u56fe\u6807", "Applets_Center_LongPress", this.f6288g.appId, "", "", "", "", null);
                MantoCenterMineAdapter.this.q(this.f6289h, this.f6290i.getAdapterPosition());
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f6292g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6293h;

        e(h hVar, int i2) {
            this.f6292g = hVar;
            this.f6293h = i2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            MantoCenterMineAdapter.this.f6277e = this.f6292g;
            this.f6292g.d.setVisibility(0);
            MantoCenterMineAdapter.this.v(this.f6293h);
        }
    }

    /* loaded from: classes17.dex */
    static class f extends RecyclerView.ViewHolder {
        public TextView a;

        public f(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.txt_nomore_tip);
        }
    }

    /* loaded from: classes17.dex */
    interface g {
        void a(MantoCenterMineEntity.AppInfo appInfo, int i2);

        void b(MantoCenterMineEntity.AppInfo appInfo, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static class h extends RecyclerView.ViewHolder {
        TextView a;
        ImageView b;

        /* renamed from: c  reason: collision with root package name */
        private ImageView f6295c;
        ImageView d;

        h(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_app_name);
            this.b = (ImageView) view.findViewById(R.id.iv_logo);
            this.f6295c = (ImageView) view.findViewById(R.id.iv_trial_icon);
            this.d = (ImageView) view.findViewById(R.id.iv_del);
        }
    }

    /* loaded from: classes17.dex */
    static class i extends RecyclerView.ViewHolder {
        TextView a;

        public i(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.title);
        }
    }

    public MantoCenterMineAdapter(Context context, List<MantoCenterMineEntity.AppInfo> list, g gVar) {
        this.a = context;
        this.b = list;
        this.f6276c = gVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(h hVar, int i2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(hVar.b, "scaleX", 1.0f, 1.5f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(hVar.b, "scaleY", 1.0f, 1.5f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(hVar.b, "alpha", 1.0f, 0.48f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f6278f = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        this.f6278f.setDuration(250L).start();
        this.f6278f.addListener(new e(hVar, i2));
    }

    private void r(RecyclerView.ViewHolder viewHolder, MantoCenterMineEntity.AppInfo appInfo, int i2) {
        if (!(viewHolder instanceof h) || appInfo == null) {
            return;
        }
        h hVar = (h) viewHolder;
        com.jd.manto.center.k.h.b(hVar.f6295c);
        hVar.a.setText(appInfo.appName);
        int i3 = appInfo.nativeItemType;
        if (i3 == 3) {
            hVar.b.setImageResource(R.drawable.manto_center_mine_more);
        } else if (i3 == 4) {
            hVar.b.setImageResource(R.drawable.manto_center_mine_add);
        }
        viewHolder.itemView.setOnClickListener(new a(appInfo, i2));
    }

    private void s(RecyclerView.ViewHolder viewHolder, MantoCenterMineEntity.AppInfo appInfo) {
        if (!(viewHolder instanceof h) || appInfo == null) {
            return;
        }
        h hVar = (h) viewHolder;
        hVar.a.setText(appInfo.appName);
        hVar.d.setVisibility(4);
        JDImageUtils.displayImage(appInfo.logoUrl, hVar.b);
        if (TextUtils.equals(appInfo.appType, "2")) {
            com.jd.manto.center.k.h.l(hVar.f6295c);
        } else {
            com.jd.manto.center.k.h.b(hVar.f6295c);
        }
        viewHolder.itemView.setOnClickListener(new b(appInfo, viewHolder));
        hVar.d.setOnClickListener(new c(viewHolder, appInfo));
        viewHolder.itemView.setOnLongClickListener(new d(appInfo, hVar, viewHolder));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(int i2) {
        this.d = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.b.get(i2).nativeItemType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        MantoCenterMineEntity.AppInfo appInfo = this.b.get(i2);
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0 && (viewHolder instanceof i)) {
            ((i) viewHolder).a.setText(appInfo.title);
        } else if (itemViewType == 1 || itemViewType == 2) {
            s(viewHolder, appInfo);
        } else if (itemViewType != 3 && itemViewType != 4) {
            if (itemViewType == 5 && (viewHolder instanceof f)) {
                com.jd.manto.center.k.h.c(((f) viewHolder).a);
            }
        } else {
            r(viewHolder, appInfo, viewHolder.getAdapterPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (i2 == 0) {
            return new i(from.inflate(R.layout.manto_center_mine_title_item, viewGroup, false));
        }
        if (i2 == 2 || i2 == 1) {
            return new h(from.inflate(R.layout.manto_center_mine_app_item, viewGroup, false));
        }
        if (i2 == 5) {
            return new f(from.inflate(R.layout.manto_center_bottom_nomore, viewGroup, false));
        }
        return new h(from.inflate(R.layout.manto_center_mine_app_item, viewGroup, false));
    }

    public boolean t() {
        return this.d >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void u() {
        h hVar;
        if (this.d > 0 && (hVar = this.f6277e) != null) {
            hVar.d.setVisibility(4);
        }
        v(-1);
    }
}
