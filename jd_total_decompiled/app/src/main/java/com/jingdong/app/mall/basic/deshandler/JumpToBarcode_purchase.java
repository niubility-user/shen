package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_BARCODE_PURCHASE)
/* loaded from: classes19.dex */
public class JumpToBarcode_purchase extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "showBarcodeActivity() -->> ");
        }
        if (context instanceof BaseActivity) {
            DeepLinkScanHelper.startCaptureActivityForOpenApp((BaseActivity) context, bundle);
        }
        c(context);
    }
}
