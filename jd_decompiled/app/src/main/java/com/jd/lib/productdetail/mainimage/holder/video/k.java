package com.jd.lib.productdetail.mainimage.holder.video;

import android.widget.ImageView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class k implements k.o {
    public final /* synthetic */ PdMVideoViewHolder a;

    public k(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void a(int i2) {
        if (i2 == 2) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("videoid", (Object) this.a.M());
            this.a.f4654n.mtaExposure("Productdetail_MainPicVideoSMExpo", jDJSONObject.toJSONString());
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void onVideoFinish() {
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k) {
            return;
        }
        com.jd.lib.productdetail.mainimage.old.k kVar = pdMVideoViewHolder.E;
        if (kVar != null) {
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = pdMVideoViewHolder.v;
            WareBusinessTopVideoControl wareBusinessTopVideoControl = wareBusinessUnitMainImageEntity != null ? pdMVideoViewHolder.r.bizData.videoBizData.videoControl : null;
            if (wareBusinessUnitMainImageEntity != null) {
                String str = wareBusinessUnitMainImageEntity.extMap.skuId;
            }
            if (wareBusinessUnitMainImageEntity != null) {
                String str2 = pdMVideoViewHolder.f4654n.getMainImageParams().mSkuTag;
            }
            PdMVideoViewHolder pdMVideoViewHolder2 = this.a;
            kVar.i(wareBusinessTopVideoControl, kVar, true, pdMVideoViewHolder2.B, pdMVideoViewHolder2.C, pdMVideoViewHolder2.D, pdMVideoViewHolder2.H);
        }
        this.a.K();
        this.a.Q();
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void a() {
        ImageView imageView = this.a.I;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }
}
