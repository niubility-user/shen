package com.jd.lib.productdetail.mainimage.holder.ask;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageQaEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMAskViewHolder extends PdMainImageBaseHolder {
    public PdMAskView B;

    public PdMAskViewHolder(@NonNull View view, PdMAskView pdMAskView) {
        super(view, pdMAskView);
        this.B = pdMAskView;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void f(PdCommentInfo pdCommentInfo) {
        MutableLiveData<PdCommentInfo> mutableLiveData;
        if (this.f4651k || this.r == null || (mutableLiveData = this.f4654n.pdCommentInfo) == null) {
            return;
        }
        PdCommentInfo value = mutableLiveData.getValue();
        WareImageQaEntity wareImageQaEntity = this.r.bizData.drugQuestionBizData;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
        PdMAskView pdMAskView = this.B;
        if (pdMAskView != null) {
            PdMainImagePresenter pdMainImagePresenter = this.f4654n;
            b bVar = new b(this);
            pdMAskView.f4671m = wareBusinessUnitMainImageEntity;
            pdMAskView.f4665g = bVar;
            pdMAskView.A = pdMainImagePresenter;
            int i2 = PdMAskView.B;
            pdMAskView.g(2);
            this.B.c(value, wareImageQaEntity);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder, android.view.View.OnClickListener
    public void onClick(View view) {
        PdMAskView pdMAskView;
        if (PDUtils.repeatClick() && (pdMAskView = this.B) != null) {
            pdMAskView.e();
        }
    }
}
