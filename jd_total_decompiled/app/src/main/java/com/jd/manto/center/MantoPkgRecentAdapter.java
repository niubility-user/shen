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
import com.jd.manto.center.model.AdapterBeanWrapper;
import com.jd.manto.center.widget.recycler.BaseRecyclerAdapter;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.sdk.api.IImageLoader;
import java.util.List;

/* loaded from: classes17.dex */
public final class MantoPkgRecentAdapter extends BaseRecyclerAdapter<AdapterBeanWrapper<PkgHistoryEntity>, RecyclerView.ViewHolder> {

    /* renamed from: c  reason: collision with root package name */
    private IImageLoader f6377c;
    private c d;

    /* loaded from: classes17.dex */
    public static class ViewHolderItem extends BaseRecyclerAdapter.ViewHolder {
        final View a;
        final View b;

        /* renamed from: c  reason: collision with root package name */
        final View f6378c;
        final View d;

        /* renamed from: e  reason: collision with root package name */
        final TextView f6379e;

        /* renamed from: f  reason: collision with root package name */
        final TextView f6380f;

        /* renamed from: g  reason: collision with root package name */
        final ImageView f6381g;

        public ViewHolderItem(View view) {
            super(view);
            this.a = view.findViewById(R.id.manto_item_container);
            this.f6379e = (TextView) view.findViewById(R.id.app_name);
            this.f6381g = (ImageView) view.findViewById(R.id.app_icon);
            this.b = view.findViewById(R.id.favo_icon);
            this.f6378c = view.findViewById(R.id.divider);
            this.d = view.findViewById(R.id.debug_type);
            this.f6380f = (TextView) view.findViewById(R.id.app_detail);
        }
    }

    /* loaded from: classes17.dex */
    public static class ViewHolderTitle extends BaseRecyclerAdapter.ViewHolder {
        final TextView a;
        final View b;

        public ViewHolderTitle(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.title);
            this.b = view.findViewById(R.id.icon);
        }
    }

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgHistoryEntity f6382g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6383h;

        a(PkgHistoryEntity pkgHistoryEntity, RecyclerView.ViewHolder viewHolder) {
            this.f6382g = pkgHistoryEntity;
            this.f6383h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoPkgRecentAdapter.this.d != null) {
                MantoPkgRecentAdapter.this.d.b(this.f6382g, this.f6383h.getAdapterPosition());
            }
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgHistoryEntity f6385g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6386h;

        b(PkgHistoryEntity pkgHistoryEntity, RecyclerView.ViewHolder viewHolder) {
            this.f6385g = pkgHistoryEntity;
            this.f6386h = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (MantoPkgRecentAdapter.this.d != null) {
                c cVar = MantoPkgRecentAdapter.this.d;
                PkgHistoryEntity pkgHistoryEntity = this.f6385g;
                RecyclerView.ViewHolder viewHolder = this.f6386h;
                cVar.a(pkgHistoryEntity, viewHolder.itemView, viewHolder.getAdapterPosition());
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes17.dex */
    public interface c<T> {
        void a(T t, View view, int i2);

        void b(T t, int i2);
    }

    public MantoPkgRecentAdapter(Context context, List<AdapterBeanWrapper<PkgHistoryEntity>> list, c<PkgHistoryEntity> cVar) {
        super(context, list);
        this.f6377c = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
        this.d = cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (i2 < getData().size()) {
            return getData().get(i2).type;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        PkgHistoryEntity pkgHistoryEntity;
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            ViewHolderTitle viewHolderTitle = (ViewHolderTitle) viewHolder;
            viewHolderTitle.a.setText(getItem(i2).title);
            viewHolderTitle.itemView.setBackgroundColor(-1);
        } else if (1 != itemViewType || (pkgHistoryEntity = getItem(i2).entity) == null) {
        } else {
            ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
            viewHolderItem.b.setVisibility(pkgHistoryEntity.favorite ? 0 : 4);
            viewHolderItem.f6378c.setVisibility(i2 == getData().size() ? 4 : 0);
            viewHolderItem.d.setVisibility("2".endsWith(pkgHistoryEntity.type) ? 0 : 4);
            viewHolderItem.f6379e.setText(pkgHistoryEntity.name);
            if (TextUtils.isEmpty(pkgHistoryEntity.logo)) {
                viewHolderItem.f6381g.setImageResource(com.jingdong.manto.R.drawable.manto_icon_default);
            } else {
                IImageLoader iImageLoader = this.f6377c;
                if (iImageLoader != null) {
                    iImageLoader.loadImage(viewHolderItem.f6381g, pkgHistoryEntity.logo);
                } else {
                    viewHolderItem.f6381g.setImageResource(com.jingdong.manto.R.drawable.manto_icon_default);
                }
            }
            viewHolderItem.a.setOnClickListener(new a(pkgHistoryEntity, viewHolder));
            viewHolderItem.a.setOnLongClickListener(new b(pkgHistoryEntity, viewHolder));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(l());
        if (i2 == 0) {
            return new ViewHolderTitle(from.inflate(R.layout.manto_center_item_nav_title, viewGroup, false));
        }
        return new ViewHolderItem(from.inflate(R.layout.manto_center_pkg_recent_item, viewGroup, false));
    }
}
