package com.jd.lib.productdetail.mainimage.holder.ypsms;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMYpsmsDetailItemView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f4974g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f4975h;

    public PdMYpsmsDetailItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(PdDrugInfo.DrugDetails drugDetails) {
        if (drugDetails != null) {
            this.f4974g.setText(drugDetails.title);
            this.f4975h.setText(drugDetails.desc);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4974g = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_detail_item_title);
        this.f4975h = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_detail_item_desc);
    }
}
