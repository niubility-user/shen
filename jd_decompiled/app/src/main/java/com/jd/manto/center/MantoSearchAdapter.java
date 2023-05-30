package com.jd.manto.center;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.MantoPkgRecentAdapter;
import com.jd.manto.center.model.AdapterBeanWrapper;
import com.jd.manto.center.widget.recycler.BaseRecyclerAdapter;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoSearchAdapter extends BaseRecyclerAdapter<AdapterBeanWrapper<PkgDetailEntity>, RecyclerView.ViewHolder> {

    /* renamed from: f  reason: collision with root package name */
    private static final int f6437f = Color.parseColor("#f0250f");

    /* renamed from: c  reason: collision with root package name */
    private IImageLoader f6438c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private MantoPkgRecentAdapter.c f6439e;

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgDetailEntity f6440g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6441h;

        a(PkgDetailEntity pkgDetailEntity, RecyclerView.ViewHolder viewHolder) {
            this.f6440g = pkgDetailEntity;
            this.f6441h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoSearchAdapter.this.f6439e != null) {
                MantoSearchAdapter.this.f6439e.b(this.f6440g, this.f6441h.getAdapterPosition());
            }
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgDetailEntity f6443g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f6444h;

        b(PkgDetailEntity pkgDetailEntity, RecyclerView.ViewHolder viewHolder) {
            this.f6443g = pkgDetailEntity;
            this.f6444h = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (MantoSearchAdapter.this.f6439e != null) {
                MantoPkgRecentAdapter.c cVar = MantoSearchAdapter.this.f6439e;
                PkgDetailEntity pkgDetailEntity = this.f6443g;
                RecyclerView.ViewHolder viewHolder = this.f6444h;
                cVar.a(pkgDetailEntity, viewHolder.itemView, viewHolder.getAdapterPosition());
                return true;
            }
            return true;
        }
    }

    public MantoSearchAdapter(Context context, List<AdapterBeanWrapper<PkgDetailEntity>> list, MantoPkgRecentAdapter.c<PkgDetailEntity> cVar) {
        super(context, list);
        this.f6438c = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
        this.f6439e = cVar;
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
        PkgDetailEntity pkgDetailEntity;
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            MantoPkgRecentAdapter.ViewHolderTitle viewHolderTitle = (MantoPkgRecentAdapter.ViewHolderTitle) viewHolder;
            viewHolderTitle.a.setText(getItem(i2).title);
            viewHolderTitle.a.setTextColor(l().getResources().getColor(com.jingdong.manto.R.color.manto_ui_txt_84));
            viewHolderTitle.a.setTextSize(1, 13.0f);
            viewHolderTitle.b.setVisibility(4);
            viewHolderTitle.itemView.getLayoutParams().height = MantoDensityUtils.dip2pixel(l(), 36);
        } else if (1 != itemViewType || (pkgDetailEntity = getItem(i2).entity) == null) {
        } else {
            MantoPkgRecentAdapter.ViewHolderItem viewHolderItem = (MantoPkgRecentAdapter.ViewHolderItem) viewHolder;
            viewHolderItem.f6380f.setVisibility(0);
            viewHolderItem.b.setVisibility(4);
            viewHolderItem.f6378c.setVisibility(i2 == getData().size() ? 4 : 0);
            viewHolderItem.d.setVisibility("2".endsWith(pkgDetailEntity.type) ? 0 : 4);
            TextView textView = viewHolderItem.f6379e;
            String str = this.d;
            String str2 = pkgDetailEntity.name;
            int i3 = f6437f;
            textView.setText(MantoStringUtils.getForegroundSpannable(str, str2, i3));
            viewHolderItem.f6380f.setText(MantoStringUtils.getForegroundSpannable(this.d, pkgDetailEntity.description, i3));
            if (TextUtils.isEmpty(pkgDetailEntity.logo)) {
                viewHolderItem.f6381g.setImageResource(com.jingdong.manto.R.drawable.manto_icon_default);
            } else {
                IImageLoader iImageLoader = this.f6438c;
                if (iImageLoader != null) {
                    iImageLoader.loadImage(viewHolderItem.f6381g, pkgDetailEntity.logo);
                } else {
                    viewHolderItem.f6381g.setImageResource(com.jingdong.manto.R.drawable.manto_icon_default);
                }
            }
            viewHolderItem.a.setOnClickListener(new a(pkgDetailEntity, viewHolder));
            viewHolderItem.a.setOnLongClickListener(new b(pkgDetailEntity, viewHolder));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(l());
        if (i2 == 0) {
            return new MantoPkgRecentAdapter.ViewHolderTitle(from.inflate(R.layout.manto_center_item_nav_title, viewGroup, false));
        }
        return new MantoPkgRecentAdapter.ViewHolderItem(from.inflate(R.layout.manto_center_pkg_recent_item, viewGroup, false));
    }

    public void p(String str) {
        this.d = str;
    }
}
