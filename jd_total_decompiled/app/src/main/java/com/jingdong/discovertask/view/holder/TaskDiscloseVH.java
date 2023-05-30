package com.jingdong.discovertask.view.holder;

import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;
import com.jingdong.discovertask.model.entity.TaskDiscloseAdapterData;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class TaskDiscloseVH extends BaseVH {
    private ViewGroup layout;

    public TaskDiscloseVH(View view) {
        super(view);
        this.layout = (ViewGroup) find(R.id.layout);
    }

    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
        if (iFloorEntity instanceof TaskDiscloseAdapterData) {
            ViewGroup.LayoutParams layoutParams = this.layout.getLayoutParams();
            if (((TaskDiscloseAdapterData) iFloorEntity).isWholeDisclose) {
                this.layout.setBackgroundResource(0);
                layoutParams.width = DPIUtil.dip2px(350.0f);
                layoutParams.height = (DPIUtil.getHeight(this.itemView.getContext()) / 2) - DPIUtil.dip2px(50.0f);
            } else {
                this.layout.setBackgroundResource(R.drawable.shape_radius_5_fffef3f3);
                layoutParams.width = DPIUtil.dip2px(350.0f);
                layoutParams.height = DPIUtil.dip2px(140.0f);
            }
            this.layout.setLayoutParams(layoutParams);
        }
    }
}
