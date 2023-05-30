package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageRcServiceIconAdapterB extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context a;
    public List<WareBusinessServiceIconEntity> b;

    /* renamed from: c */
    public final LayoutInflater f5049c;
    public d d;

    /* renamed from: e */
    public boolean f5050e;

    /* renamed from: f */
    public boolean f5051f;

    /* renamed from: g */
    public boolean f5052g;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessServiceIconEntity f5053g;

        public a(WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
            PdMImageRcServiceIconAdapterB.this = r1;
            this.f5053g = wareBusinessServiceIconEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = PdMImageRcServiceIconAdapterB.this.d;
            if (dVar != null) {
                dVar.a(this.f5053g);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessServiceIconEntity f5055g;

        public b(WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
            PdMImageRcServiceIconAdapterB.this = r1;
            this.f5055g = wareBusinessServiceIconEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = PdMImageRcServiceIconAdapterB.this.d;
            if (dVar != null) {
                dVar.a(this.f5055g);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessServiceIconEntity f5057g;

        public c(WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
            PdMImageRcServiceIconAdapterB.this = r1;
            this.f5057g = wareBusinessServiceIconEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PDBaseDeepLinkHelper.gotoMWithUrl(PdMImageRcServiceIconAdapterB.this.a, "", this.f5057g.jumpUrl);
        }
    }

    /* loaded from: classes15.dex */
    public interface d {
        void a(WareBusinessServiceIconEntity wareBusinessServiceIconEntity);
    }

    public PdMImageRcServiceIconAdapterB(Context context, boolean z, boolean z2) {
        this.a = context;
        this.f5050e = z;
        this.f5051f = z2;
        this.f5049c = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WareBusinessServiceIconEntity> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        WareBusinessServiceIconEntity wareBusinessServiceIconEntity = this.b.get(i2);
        if (wareBusinessServiceIconEntity != null) {
            return wareBusinessServiceIconEntity.serviceType;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        WareBusinessServiceIconEntity wareBusinessServiceIconEntity = this.b.get(i2);
        if (wareBusinessServiceIconEntity == null) {
            return;
        }
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            if (viewHolder instanceof PdMIconListViewHolder) {
                PdMIconListViewHolder pdMIconListViewHolder = (PdMIconListViewHolder) viewHolder;
                pdMIconListViewHolder.f5035h.setText(wareBusinessServiceIconEntity.text);
                if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.imageUrl)) {
                    JDImageUtils.displayImage(wareBusinessServiceIconEntity.imageUrl, (ImageView) pdMIconListViewHolder.f5034g, (JDDisplayImageOptions) null, false);
                }
                pdMIconListViewHolder.f5036i.setText(wareBusinessServiceIconEntity.tip);
                pdMIconListViewHolder.f5037j.setVisibility(8);
                if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.jumpUrl)) {
                    pdMIconListViewHolder.f5037j.setVisibility(0);
                    viewHolder.itemView.setOnClickListener(new a(wareBusinessServiceIconEntity));
                } else if (TextUtils.isEmpty(wareBusinessServiceIconEntity.explainText)) {
                } else {
                    pdMIconListViewHolder.f5037j.setVisibility(0);
                    viewHolder.itemView.setOnClickListener(new b(wareBusinessServiceIconEntity));
                }
            }
        } else if (itemViewType != 2) {
            if (itemViewType == 3) {
                if (viewHolder instanceof PdMIconTitleViewHolderB) {
                    PdMIconTitleViewHolderB pdMIconTitleViewHolderB = (PdMIconTitleViewHolderB) viewHolder;
                    pdMIconTitleViewHolderB.c(wareBusinessServiceIconEntity, this.a.getString(R.string.lib_pd_image_guide_text));
                    pdMIconTitleViewHolderB.itemView.setOnClickListener(new c(wareBusinessServiceIconEntity));
                }
            } else if (TextUtils.isEmpty(wareBusinessServiceIconEntity.text) || !(viewHolder instanceof PdMIconTitleViewHolderB)) {
            } else {
                ((PdMIconTitleViewHolderB) viewHolder).c(wareBusinessServiceIconEntity, "");
            }
        } else if (viewHolder instanceof PdMTrustTitleHolderB) {
            PdMTrustTitleHolderB pdMTrustTitleHolderB = (PdMTrustTitleHolderB) viewHolder;
            if (TextUtils.isEmpty(wareBusinessServiceIconEntity.imageUrl) && !this.f5052g) {
                pdMTrustTitleHolderB.a();
                return;
            }
            pdMTrustTitleHolderB.f5126c.setVisibility(8);
            pdMTrustTitleHolderB.d.setVisibility(8);
            if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.jumpUrl)) {
                if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.guideText)) {
                    pdMTrustTitleHolderB.f5126c.setVisibility(0);
                    pdMTrustTitleHolderB.f5126c.setText(wareBusinessServiceIconEntity.guideText);
                    pdMTrustTitleHolderB.d.setVisibility(0);
                }
                pdMTrustTitleHolderB.f5126c.setOnClickListener(new i0(pdMTrustTitleHolderB, wareBusinessServiceIconEntity));
            }
            if (pdMTrustTitleHolderB.f5128f && !TextUtils.isEmpty(wareBusinessServiceIconEntity.guideLeft)) {
                PdAutoChangeTextSize pdAutoChangeTextSize = pdMTrustTitleHolderB.f5127e;
                if (pdAutoChangeTextSize != null) {
                    pdAutoChangeTextSize.setVisibility(0);
                    pdMTrustTitleHolderB.f5127e.setText(wareBusinessServiceIconEntity.guideLeft);
                }
                pdMTrustTitleHolderB.b.setVisibility(8);
                return;
            }
            JDImageUtils.displayImage(wareBusinessServiceIconEntity.iconInDialog, (ImageView) pdMTrustTitleHolderB.b, (JDDisplayImageOptions) null, false);
            pdMTrustTitleHolderB.b.setVisibility(0);
            PdAutoChangeTextSize pdAutoChangeTextSize2 = pdMTrustTitleHolderB.f5127e;
            if (pdAutoChangeTextSize2 != null) {
                pdAutoChangeTextSize2.setVisibility(8);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View inflate;
        View inflate2;
        View inflate3;
        if (i2 == 0) {
            return new PdMIconListViewHolder(this.a, this.f5049c.inflate(this.f5051f ? R.layout.lib_pd_mainimage_service_layer_item_b_elder : R.layout.lib_pd_mainimage_service_layer_item_b, viewGroup, false), this.f5050e);
        } else if (i2 == 1) {
            if (this.f5051f) {
                inflate = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_title_b_elder, viewGroup, false);
            } else {
                inflate = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_title_b, viewGroup, false);
            }
            return new PdMIconTitleViewHolderB(this.a, inflate, this.f5050e);
        } else if (i2 == 2) {
            if (this.f5051f) {
                inflate2 = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_top_b_elder, viewGroup, false);
            } else {
                inflate2 = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_top_b, viewGroup, false);
            }
            return new PdMTrustTitleHolderB(this.a, inflate2, this.f5050e, this.d, this.f5052g);
        } else if (i2 != 3) {
            return null;
        } else {
            if (this.f5051f) {
                inflate3 = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_title_b_elder, viewGroup, false);
            } else {
                inflate3 = this.f5049c.inflate(R.layout.lib_pd_mainimage_service_layer_title_b, viewGroup, false);
            }
            return new PdMIconTitleViewHolderB(this.a, inflate3, this.f5050e);
        }
    }
}
