package com.jd.lib.productdetail.mainimage.protocol;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class b extends PdBaseProtocolLiveData<PDPackStyleEntity> {
    public a a;

    /* loaded from: classes15.dex */
    public static class a {
        public String a;
        public boolean b;

        /* renamed from: c  reason: collision with root package name */
        public String f5210c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f5211e;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return CartConstant.FUNCTION_ID_MINISKUDETAIL;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            a aVar = this.a;
            if (aVar != null) {
                httpSetting.putJsonParam("skuId", aVar.a);
                httpSetting.putJsonParam(CartConstant.KEY_CART_MINI_NEEDATTR, Boolean.valueOf(this.a.b));
                httpSetting.putJsonParam("requestSource", this.a.f5210c);
                httpSetting.putJsonParam("mainSku", this.a.d);
                httpSetting.putJsonParam("carModelId", "");
                if (!TextUtils.isEmpty(this.a.f5211e)) {
                    httpSetting.putJsonParam("storeId", this.a.f5211e);
                }
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
