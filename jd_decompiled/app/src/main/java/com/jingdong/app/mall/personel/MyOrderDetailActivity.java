package com.jingdong.app.mall.personel;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;

/* loaded from: classes4.dex */
public class MyOrderDetailActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("orderId");
        if (!TextUtils.isEmpty(stringExtra)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("orderId", stringExtra);
            DeepLinkOrderCenterHelper.startOrderDetail(this, bundle2);
        }
        finish();
    }
}
