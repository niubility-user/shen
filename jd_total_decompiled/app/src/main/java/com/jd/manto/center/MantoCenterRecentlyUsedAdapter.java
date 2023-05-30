package com.jd.manto.center;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.MantoCenterMineEntity;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes17.dex */
public class MantoCenterRecentlyUsedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context a;
    List<MantoCenterMineEntity.AppInfo> b;

    /* renamed from: c  reason: collision with root package name */
    private e f6345c;

    /* loaded from: classes17.dex */
    class a extends RecyclerView.ViewHolder {
        a(MantoCenterRecentlyUsedAdapter mantoCenterRecentlyUsedAdapter, View view) {
            super(view);
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6346g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6347h;

        b(MantoCenterMineEntity.AppInfo appInfo, RecyclerView.ViewHolder viewHolder) {
            this.f6346g = appInfo;
            this.f6347h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoCenterRecentlyUsedAdapter.this.f6345c != null) {
                MantoCenterRecentlyUsedAdapter.this.f6345c.c(this.f6346g, this.f6347h.getAdapterPosition());
            }
        }
    }

    /* loaded from: classes17.dex */
    class c implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6349g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6350h;

        c(MantoCenterMineEntity.AppInfo appInfo, RecyclerView.ViewHolder viewHolder) {
            this.f6349g = appInfo;
            this.f6350h = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (MantoCenterRecentlyUsedAdapter.this.f6345c != null) {
                MantoCenterRecentlyUsedAdapter.this.f6345c.b(this.f6349g, this.f6350h.getAdapterPosition());
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes17.dex */
    class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6352g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6353h;

        d(MantoCenterMineEntity.AppInfo appInfo, RecyclerView.ViewHolder viewHolder) {
            this.f6352g = appInfo;
            this.f6353h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoCenterRecentlyUsedAdapter.this.f6345c != null) {
                MantoCenterRecentlyUsedAdapter.this.f6345c.a(this.f6352g, this.f6353h.getAdapterPosition());
            }
        }
    }

    /* loaded from: classes17.dex */
    interface e {
        void a(MantoCenterMineEntity.AppInfo appInfo, int i2);

        void b(MantoCenterMineEntity.AppInfo appInfo, int i2);

        void c(MantoCenterMineEntity.AppInfo appInfo, int i2);
    }

    /* loaded from: classes17.dex */
    static class f extends RecyclerView.ViewHolder {
        TextView a;
        ImageView b;

        /* renamed from: c  reason: collision with root package name */
        private ImageView f6355c;
        ImageView d;

        f(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.manto_center_recent_name);
            this.b = (ImageView) view.findViewById(R.id.manto_center_recent_icon);
            this.d = (ImageView) view.findViewById(R.id.manto_center_recent_follow);
            this.f6355c = (ImageView) view.findViewById(R.id.iv_trial_icon);
        }
    }

    public MantoCenterRecentlyUsedAdapter(Context context, List<MantoCenterMineEntity.AppInfo> list, e eVar) {
        this.a = context;
        this.b = list;
        this.f6345c = eVar;
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
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder viewHolder, int i2) {
        MantoCenterMineEntity.AppInfo appInfo = this.b.get(i2);
        if (getItemViewType(i2) == 0 && (viewHolder instanceof f)) {
            f fVar = (f) viewHolder;
            fVar.a.setText(appInfo.appName);
            JDImageUtils.displayImage(appInfo.logoUrl, fVar.b);
            if (TextUtils.equals(appInfo.appType, "2")) {
                h.l(fVar.f6355c);
            } else {
                h.b(fVar.f6355c);
            }
            if ("1".equals(appInfo.isFollow)) {
                fVar.d.setImageResource(R.drawable.manto_center_followed);
            } else {
                fVar.d.setImageResource(R.drawable.manto_center_not_followed);
            }
            fVar.d.setOnClickListener(new b(appInfo, viewHolder));
            fVar.itemView.setOnLongClickListener(new c(appInfo, viewHolder));
            fVar.itemView.setOnClickListener(new d(appInfo, viewHolder));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (i2 == 0) {
            return new f(from.inflate(R.layout.manto_center_recent_item, viewGroup, false));
        }
        return new a(this, from.inflate(R.layout.manto_center_recent_no_more, viewGroup, false));
    }
}
