package com.jd.cashier.app.jdlibcutter.impl.pay;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.R;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.UnionPayApiKey;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.unionpay.UPPayAssistEx;

/* loaded from: classes13.dex */
public class JDUnionPay implements IUnionPay, UnionPayApiKey {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay
    public void doAndroidPay(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        try {
            String string = bundle.getString(UnionPayApiKey.UN_PAY_TN);
            String string2 = bundle.getString(UnionPayApiKey.UN_PAY_MODE);
            String string3 = bundle.getString(UnionPayApiKey.UN_SE_TYPE);
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string3)) {
                return;
            }
            UPPayAssistEx.startSEPay(context, null, null, string, string2, string3);
        } catch (Throwable unused) {
            ToastUtils.showToastInCenter(context, context.getString(R.string.jdlibcutter_union_pay_exception_toast));
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay
    public void doUnionPay(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        try {
            String string = bundle.getString(UnionPayApiKey.UN_PAY_TN);
            String string2 = bundle.getString(UnionPayApiKey.UN_PAY_MODE);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            UPPayAssistEx.startPay(context, null, null, string, string2);
        } catch (Throwable unused) {
            ToastUtils.showToastInCenter(context, context.getString(R.string.jdlibcutter_union_pay_exception_toast));
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay
    public boolean isUnionWalletInstalled(Context context) {
        return UPPayAssistEx.checkWalletInstalled(context);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay
    public void releaseMemory() {
        UPPayAssistEx.releaseMemory();
    }
}
