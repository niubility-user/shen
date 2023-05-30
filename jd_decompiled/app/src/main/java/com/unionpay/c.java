package com.unionpay;

import android.os.Bundle;
import android.os.Handler;
import com.huawei.nfc.sdk.service.HwOpenPayTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class c implements HwOpenPayTask.IHwResultCallBack {
    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwResultCallBack
    public final void onResult(int i2, Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        Handler.Callback callback;
        handler = UPPayAssistEx.W;
        if (handler == null) {
            callback = UPPayAssistEx.ac;
            Handler unused = UPPayAssistEx.W = new Handler(callback);
        }
        handler2 = UPPayAssistEx.W;
        handler3 = UPPayAssistEx.W;
        handler2.sendMessage(handler3.obtainMessage(1003, Integer.valueOf(i2)));
    }
}
