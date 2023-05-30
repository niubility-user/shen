package com.jd.lib.productdetail.mainimage.old;

import android.view.View;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView;

/* loaded from: classes15.dex */
public class d0 implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMPartsRecommendTabRecyclerView.PartsRecommendTabAdapter f5148g;

    public d0(PdMPartsRecommendTabRecyclerView.PartsRecommendTabAdapter partsRecommendTabAdapter) {
        this.f5148g = partsRecommendTabAdapter;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f5148g.f5114c != null) {
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int intValue = ((Integer) tag).intValue();
                PdMPartsRecommendTabRecyclerView.PartsRecommendTabAdapter partsRecommendTabAdapter = this.f5148g;
                partsRecommendTabAdapter.f5114c.a(intValue, partsRecommendTabAdapter.a.get(intValue));
            }
        }
    }
}
