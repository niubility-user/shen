package com.jd.lib.productdetail.mainimage.holder.gift;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerGiftsAdapter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ILogin;

/* loaded from: classes15.dex */
public class b implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ HeadPicGiftInfoEntity.GiftLayerInfosEntity f4848g;

    /* renamed from: h */
    public final /* synthetic */ PdMImageGiftLayerGiftsAdapter.GiftsItemHolder f4849h;

    /* loaded from: classes15.dex */
    public class a implements ILogin {
        public a() {
            b.this = r1;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            b bVar = b.this;
            if (PdMImageGiftLayerGiftsAdapter.this.a == null || TextUtils.isEmpty(bVar.f4848g.jumplink)) {
                return;
            }
            b bVar2 = b.this;
            PDBaseDeepLinkHelper.gotoMWithUrl(PdMImageGiftLayerGiftsAdapter.this.a, bVar2.f4848g.jumplink);
        }
    }

    public b(PdMImageGiftLayerGiftsAdapter.GiftsItemHolder giftsItemHolder, HeadPicGiftInfoEntity.GiftLayerInfosEntity giftLayerInfosEntity) {
        this.f4849h = giftsItemHolder;
        this.f4848g = giftLayerInfosEntity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        DeepLinkLoginHelper.startLoginActivity(PdMImageGiftLayerGiftsAdapter.this.a, null, new a(), "");
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (!TextUtils.isEmpty(this.f4848g.businessType)) {
            jDJSONObject.put("business_type", (Object) this.f4848g.businessType);
        }
        PdMainImagePresenter pdMainImagePresenter = PdMImageGiftLayerGiftsAdapter.this.f4824c;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mtaClick("Productdetail_ZPSpecOpen", jDJSONObject.toJSONString());
        }
    }
}
