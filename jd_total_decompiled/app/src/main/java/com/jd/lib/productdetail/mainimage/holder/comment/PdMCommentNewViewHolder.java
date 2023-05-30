package com.jd.lib.productdetail.mainimage.holder.comment;

import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMCommentNewViewHolder extends PdMainImageBaseHolder {
    public boolean B;
    public PdMImageCommentNewRootView C;

    public PdMCommentNewViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.C = (PdMImageCommentNewRootView) view;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void f(PdCommentInfo pdCommentInfo) {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        String str;
        String str2;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdCommentBizData pdCommentBizData;
        WareBuinessUnitMainImageBizDataEntity.PdCommentBizData pdCommentBizData2;
        if (this.f4651k || (wareBusinessMagicHeadPicInfoEntity = this.r) == null) {
            return;
        }
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData;
        if (wareBuinessUnitMainImageBizDataEntity2 != null && (pdCommentBizData2 = wareBuinessUnitMainImageBizDataEntity2.commentBizData) != null) {
            this.B = pdCommentBizData2.commentPriorityFlag;
        }
        PdMImageCommentNewRootView pdMImageCommentNewRootView = this.C;
        if (pdMImageCommentNewRootView == null || (wareBusinessUnitMainImageEntity = this.v) == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null) {
            return;
        }
        int i2 = extMapEntity.magicHeadPicType;
        pdMImageCommentNewRootView.b(true, i2);
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
        if (wareBusinessMagicHeadPicInfoEntity2 == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity2.bizData) == null || (pdCommentBizData = wareBuinessUnitMainImageBizDataEntity.commentBizData) == null) {
            str = "";
            str2 = str;
        } else {
            String str3 = pdCommentBizData.defaultImageUrl;
            str = pdCommentBizData.buyersIcon;
            str2 = str3;
        }
        this.C.a(pdCommentInfo, str, this.B, str2, i2);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        super.j(pdMainImagePresenter);
        PdMImageCommentNewRootView pdMImageCommentNewRootView = this.C;
        if (pdMImageCommentNewRootView != null) {
            pdMImageCommentNewRootView.d(pdMainImagePresenter);
        }
    }
}
