package com.jingdong.common.widget.custom.pageload.viewholder;

import android.view.View;
import com.jingdong.common.widget.custom.CustomListFooterView;
import com.jingdong.common.widget.custom.pageload.entity.FooterEntity;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;

/* loaded from: classes12.dex */
public class FooterVH extends BaseVH {
    public FooterVH(View view) {
        super(view);
    }

    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
        View view = this.itemView;
        if ((view instanceof CustomListFooterView) && (iFloorEntity instanceof FooterEntity)) {
            CustomListFooterView customListFooterView = (CustomListFooterView) view;
            FooterEntity footerEntity = (FooterEntity) iFloorEntity;
            customListFooterView.setFooterState(footerEntity.state);
            customListFooterView.setRetryListener(footerEntity.retryListener);
        }
    }
}
