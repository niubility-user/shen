package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdSkuInfoListEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendProductRecycleView;
import com.jd.lib.productdetail.mainimage.old.j0;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class f implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMImageRecommendProductRecycleView.RecommendAdapter.RecommendHolder f4933g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdSkuInfoListEntity f4934h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ PdMImageRecommendProductRecycleView.RecommendAdapter f4935i;

    public f(PdMImageRecommendProductRecycleView.RecommendAdapter recommendAdapter, PdMImageRecommendProductRecycleView.RecommendAdapter.RecommendHolder recommendHolder, PdSkuInfoListEntity pdSkuInfoListEntity) {
        this.f4935i = recommendAdapter;
        this.f4933g = recommendHolder;
        this.f4934h = pdSkuInfoListEntity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            Context context = this.f4933g.itemView.getContext();
            PdMImageRecommendProductRecycleView.RecommendAdapter recommendAdapter = this.f4935i;
            int i2 = recommendAdapter.b;
            int i3 = PdMImageRecommendItemView.s;
            if (i2 == 1) {
                if (context instanceof PdBigImageActivity) {
                    ((PdBigImageActivity) context).b();
                    return;
                }
                return;
            }
            PdSkuInfoListEntity pdSkuInfoListEntity = this.f4934h;
            if (pdSkuInfoListEntity != null) {
                PdMImageRecommendProductRecycleView.RecommendAdapter.l(recommendAdapter, pdSkuInfoListEntity.skuId);
                if (TextUtils.equals(PdMImageRecommendProductRecycleView.this.f4915g, this.f4934h.skuId)) {
                    PDUtils.showToastCenterNormal(context, context.getString(R.string.lib_pd_mainimage_current_shop));
                } else {
                    j0.b(PdMImageRecommendProductRecycleView.this.getContext(), Long.valueOf(Long.parseLong(this.f4934h.skuId)), null, null);
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
