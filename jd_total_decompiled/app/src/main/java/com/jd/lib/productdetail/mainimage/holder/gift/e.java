package com.jd.lib.productdetail.mainimage.holder.gift;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;

/* loaded from: classes15.dex */
public class e implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImageGiftView f4855g;

    public e(PdMImageGiftView pdMImageGiftView) {
        this.f4855g = pdMImageGiftView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMImageGiftView pdMImageGiftView = this.f4855g;
        PdMainImagePresenter pdMainImagePresenter = pdMImageGiftView.w;
        if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
            return;
        }
        pdMImageGiftView.getClass();
        if (!TextUtils.isEmpty("gift")) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("type", (Object) "gift");
            jDJSONObject.put("frame_info", (Object) "-100");
            jDJSONObject.put("QuesNum", (Object) "");
            jDJSONObject.put("LableNum", (Object) "");
            jDJSONObject.put("isPhoto", (Object) "0");
            jDJSONObject.put("rankid", (Object) "");
            jDJSONObject.put("touchstone_expids", (Object) "");
            jDJSONObject.put("click_pos", (Object) "-100");
            PdMainImagePresenter pdMainImagePresenter2 = pdMImageGiftView.w;
            if (pdMainImagePresenter2 != null) {
                pdMainImagePresenter2.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
            }
        }
        com.jd.lib.productdetail.mainimage.dialog.a aVar = this.f4855g.v;
        if (aVar != null) {
            aVar.a(null);
        }
    }
}
