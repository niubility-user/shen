package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMPartsDpgViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<WareBusinessMainPictureDpgPops> a;
    public PdMDpgLayerView b;

    /* renamed from: c */
    public PdMPartsRecommendView f5071c;
    public Context d;

    /* renamed from: e */
    public PDTopReocommendEntity f5072e;

    /* renamed from: f */
    public WareBusinessUnitMainImageEntity f5073f;

    /* renamed from: g */
    public PdMainImagePresenter f5074g;

    /* loaded from: classes15.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter, View view) {
            super(view);
        }
    }

    /* loaded from: classes15.dex */
    public class a implements PdMPartsRecommendView.a {
        public a() {
            PdMPartsDpgViewAdapter.this = r1;
        }

        @Override // com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView.a
        public void a() {
            PdMPartsDpgViewAdapter.this.getClass();
        }
    }

    public PdMPartsDpgViewAdapter(Context context) {
        this.d = context;
    }

    public boolean a(WareBusinessMainPictureDpgPops wareBusinessMainPictureDpgPops) {
        if (wareBusinessMainPictureDpgPops == null) {
            return false;
        }
        return TextUtils.equals(wareBusinessMainPictureDpgPops.type, "vrDPG") || TextUtils.equals(wareBusinessMainPictureDpgPops.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_SUIT) || TextUtils.equals(wareBusinessMainPictureDpgPops.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_DPG_SMALL);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<WareBusinessMainPictureDpgPops> arrayList = this.a;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        ArrayList<WareBusinessMainPictureDpgPops> arrayList = this.a;
        return (arrayList == null || arrayList.get(i2) == null || !a(this.a.get(i2))) ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        WareBusinessMainPictureDpgPops wareBusinessMainPictureDpgPops;
        PDTopReocommendEntity pDTopReocommendEntity;
        String str;
        boolean z;
        PdMDpgLayerView pdMDpgLayerView;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        ArrayList<WareBusinessMainPictureDpgPops> arrayList = this.a;
        if (arrayList == null || (wareBusinessMainPictureDpgPops = arrayList.get(viewHolder.getAdapterPosition())) == null) {
            return;
        }
        if ((TextUtils.equals(wareBusinessMainPictureDpgPops.type, WareBusinessMagicHeadPicInfoEntity.FB_TOP_IMAGE_RECOMMEND) || TextUtils.equals(wareBusinessMainPictureDpgPops.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN)) && this.f5071c != null && (pDTopReocommendEntity = this.f5072e) != null) {
            String str2 = pDTopReocommendEntity.tabId;
            String str3 = pDTopReocommendEntity.layerTitle;
            if (TextUtils.equals(wareBusinessMainPictureDpgPops.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN)) {
                z = true;
                str = this.f5072e.rankId + CartConstant.KEY_YB_INFO_LINK + this.f5072e.typeId;
            } else {
                str = str2;
                z = false;
            }
            this.f5071c.d(this.f5073f, this.f5072e.pid, str, str3, z);
            this.f5071c.n(false);
            if (z) {
                this.f5071c.m(new a());
            }
        } else if (!a(wareBusinessMainPictureDpgPops) || (pdMDpgLayerView = this.b) == null) {
        } else {
            String str4 = wareBusinessMainPictureDpgPops.matchId;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.f5073f;
            PDTopReocommendEntity pDTopReocommendEntity2 = this.f5072e;
            pdMDpgLayerView.x = pDTopReocommendEntity2 != null ? pDTopReocommendEntity2.dpgIntegration : null;
            pdMDpgLayerView.w = wareBusinessUnitMainImageEntity;
            if (pdMDpgLayerView.f5017j != null && wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && !TextUtils.isEmpty(extMapEntity.storeId)) {
                pdMDpgLayerView.f5017j.a = wareBusinessUnitMainImageEntity.extMap.storeId;
            }
            this.b.e(str4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 1) {
            if (this.b == null) {
                PdMDpgLayerView pdMDpgLayerView = (PdMDpgLayerView) LayoutInflater.from(this.d).inflate(R.layout.lib_pd_mainimage_dpg_layer_layout, viewGroup, false);
                this.b = pdMDpgLayerView;
                pdMDpgLayerView.r(this.f5074g);
            }
            return new MyViewHolder(this, this.b);
        }
        if (this.f5071c == null) {
            PdMPartsRecommendView pdMPartsRecommendView = (PdMPartsRecommendView) LayoutInflater.from(this.d).inflate(R.layout.lib_pd_mainimage_parts_recommend_dialog_layout, viewGroup, false);
            this.f5071c = pdMPartsRecommendView;
            pdMPartsRecommendView.l(this.f5074g);
        }
        return new MyViewHolder(this, this.f5071c);
    }
}
