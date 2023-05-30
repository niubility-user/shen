package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class h implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMImageRecommendView f4937g;

    public h(PdMImageRecommendView pdMImageRecommendView) {
        this.f4937g = pdMImageRecommendView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMImageRecommendView pdMImageRecommendView = this.f4937g;
        WareImageRecommendEntity wareImageRecommendEntity = pdMImageRecommendView.f4927m;
        if (wareImageRecommendEntity != null) {
            PDBaseDeepLinkHelper.gotoMWithUrl(pdMImageRecommendView.f4921g, wareImageRecommendEntity.jump_url);
        }
        if (this.f4937g.o != null) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("touchstone_expids", (Object) new ArrayList());
            this.f4937g.p.mtaClick("Productdetail_MainPhotoDD", jDJSONObject.toJSONString());
        }
    }
}
