package com.jingdong.common.ui.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.deeplinkhelper.DeepLinkLocationAddressHelper;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.event.LocationAddressEvent;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.unification.router.CallBackWithReturnListener;

/* loaded from: classes6.dex */
public class UnAddressMiddleActivity extends Activity {
    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 == -1 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                CallBackWithReturnListener callBackWithReturnListener = UnAddressSelectUtils.mapListener;
                if (callBackWithReturnListener != null) {
                    callBackWithReturnListener.onError(-2);
                }
                finish();
                return;
            }
            AddressGlobal addressGlobal = (AddressGlobal) extras.getParcelable(LocationAddressEvent.LOCATION_ADDRESS_RESULT);
            if (addressGlobal == null) {
                CallBackWithReturnListener callBackWithReturnListener2 = UnAddressSelectUtils.mapListener;
                if (callBackWithReturnListener2 != null) {
                    callBackWithReturnListener2.onError(-2);
                }
                finish();
                return;
            }
            String jSONString = JDJSON.toJSONString(UnAddressSelectUtils.addressGlobalToAddressInfo(addressGlobal));
            CallBackWithReturnListener callBackWithReturnListener3 = UnAddressSelectUtils.mapListener;
            if (callBackWithReturnListener3 != null) {
                callBackWithReturnListener3.onComplete(jSONString);
            }
            finish();
            return;
        }
        CallBackWithReturnListener callBackWithReturnListener4 = UnAddressSelectUtils.mapListener;
        if (callBackWithReturnListener4 != null) {
            callBackWithReturnListener4.onError(-2);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().clearFlags(2);
        DeepLinkLocationAddressHelper.startLocationAddressActivity(this);
    }
}
