package com.jingdong.common.widget.custom.pageload.viewholder;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.Guideline;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;

/* loaded from: classes12.dex */
public class EmptyVH extends BaseVH {
    public EmptyVH(View view) {
        super(view);
    }

    public static EmptyVH build(Context context) {
        return new EmptyVH(new Guideline(context));
    }

    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
    }
}
