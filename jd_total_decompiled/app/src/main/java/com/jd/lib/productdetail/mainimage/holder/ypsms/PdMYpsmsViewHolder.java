package com.jd.lib.productdetail.mainimage.holder.ypsms;

import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMYpsmsViewHolder extends PdMainImageBaseHolder {
    public PdMYpsmsView B;

    public PdMYpsmsViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        PdDrugInfo pdDrugInfo;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity.drugInstructionsBizData) == null || (pdDrugInfo = pdDrugBizData.drugInfo) == null || !pdDrugInfo.showDetail) {
            return true;
        }
        this.B.a();
        return true;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (PdMYpsmsView) view;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        super.j(pdMainImagePresenter);
        PdMYpsmsView pdMYpsmsView = this.B;
        if (pdMYpsmsView != null) {
            pdMYpsmsView.c(pdMainImagePresenter);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity.drugInstructionsBizData) == null) {
            return;
        }
        this.B.f(pdDrugBizData.drugInfo);
        this.B.e(this.r.bizData.drugInstructionsBizData.popData);
    }
}
