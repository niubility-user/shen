package com.jd.lib.productdetail.mainimage.holder.video;

import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class h implements k.l {
    public final /* synthetic */ PdMVideoViewHolder a;

    public h(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.l
    public void a() {
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k || pdMVideoViewHolder.E == null) {
            return;
        }
        pdMVideoViewHolder.K = false;
        PdMVideoViewHolder pdMVideoViewHolder2 = this.a;
        com.jd.lib.productdetail.mainimage.old.k kVar = pdMVideoViewHolder2.E;
        if (kVar.f5168l == 2) {
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = pdMVideoViewHolder2.v;
            WareBusinessTopVideoControl wareBusinessTopVideoControl = wareBusinessUnitMainImageEntity != null ? pdMVideoViewHolder2.r.bizData.videoBizData.videoControl : null;
            if (wareBusinessUnitMainImageEntity != null) {
                String str = wareBusinessUnitMainImageEntity.extMap.skuId;
            }
            if (wareBusinessUnitMainImageEntity != null) {
                String str2 = pdMVideoViewHolder2.f4654n.getMainImageParams().mSkuTag;
            }
            PdMVideoViewHolder pdMVideoViewHolder3 = this.a;
            kVar.i(wareBusinessTopVideoControl, kVar, false, pdMVideoViewHolder3.B, pdMVideoViewHolder3.C, pdMVideoViewHolder3.D, pdMVideoViewHolder3.H);
            if (this.a.E.F()) {
                this.a.K();
            } else {
                this.a.R();
                this.a.I.setVisibility(0);
                this.a.E.y(true);
                this.a.T();
            }
            this.a.f4654n.mtaClick("Productdetail_MainPicVideoSMClose");
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.l
    public void b() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null || kVar.f5168l != 3) {
            return;
        }
        pdMVideoViewHolder.N();
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.l
    public void onClose() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null || kVar.f5168l != 1) {
            return;
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = pdMVideoViewHolder.v;
        WareBusinessTopVideoControl wareBusinessTopVideoControl = wareBusinessUnitMainImageEntity != null ? pdMVideoViewHolder.r.bizData.videoBizData.videoControl : null;
        if (wareBusinessUnitMainImageEntity != null) {
            String str = wareBusinessUnitMainImageEntity.extMap.skuId;
        }
        if (wareBusinessUnitMainImageEntity != null) {
            String str2 = pdMVideoViewHolder.f4654n.getMainImageParams().mSkuTag;
        }
        PdMVideoViewHolder pdMVideoViewHolder2 = this.a;
        kVar.i(wareBusinessTopVideoControl, kVar, false, pdMVideoViewHolder2.B, pdMVideoViewHolder2.C, pdMVideoViewHolder2.D, pdMVideoViewHolder2.H);
        this.a.K();
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.l
    public void b(boolean z) {
        if (z) {
            return;
        }
        this.a.P();
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.l
    public void a(boolean z) {
        if (z) {
            this.a.N();
        } else {
            PdMVideoViewHolder.G(this.a);
        }
    }
}
