package com.jd.lib.cashier.sdk.pay.aac.livedata;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.h.a.b.c;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;

/* loaded from: classes14.dex */
public class PayShowDialogLiveData extends LiveData<c> {
    public void a(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (cashierGetSuccessUrlEntity == null || (cashierCommonPopConfig = cashierGetSuccessUrlEntity.globalPresaleCombinedPayPopup) == null) {
            return;
        }
        c cVar = new c();
        cVar.d = cashierCommonPopConfig;
        cashierCommonPopConfig.cancelable = false;
        postValue(cVar);
    }

    public void b(CashierPayEntity cashierPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (cashierPayEntity == null || (cashierCommonPopConfig = cashierPayEntity.commonPopupInfo) == null) {
            return;
        }
        c cVar = new c();
        cVar.b = cashierCommonPopConfig;
        cashierCommonPopConfig.cancelable = false;
        postValue(cVar);
    }

    public void c(CashierPayEntity cashierPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (cashierPayEntity == null || (cashierCommonPopConfig = cashierPayEntity.orderExceptionInfo) == null) {
            return;
        }
        c cVar = new c();
        cVar.a = cashierCommonPopConfig;
        postValue(cVar);
    }

    public void d(FragmentActivity fragmentActivity, CashierPayEntity cashierPayEntity) {
        if (cashierPayEntity != null) {
            c cVar = new c();
            CashierCommonPopConfig cashierCommonPopConfig = new CashierCommonPopConfig();
            cashierCommonPopConfig.cancelable = true;
            cashierCommonPopConfig.message = cashierPayEntity.errorMsg;
            if (g0.a(fragmentActivity) && TextUtils.isEmpty(cashierCommonPopConfig.message)) {
                cashierCommonPopConfig.message = fragmentActivity.getString(R.string.lib_cashier_sdk_pay_pin_incorrect_title);
            }
            if (g0.a(fragmentActivity)) {
                cashierCommonPopConfig.cancelBtn = fragmentActivity.getString(R.string.lib_cashier_sdk_common_dialog_btn_cancel);
                cashierCommonPopConfig.confirmBtn = fragmentActivity.getString(R.string.lib_cashier_sdk_common_dialog_btn_confirm);
            }
            cVar.f3508c = cashierCommonPopConfig;
            postValue(cVar);
        }
    }

    public void e(CashierPayEntity cashierPayEntity) {
        if (cashierPayEntity != null) {
            c cVar = new c();
            cVar.f3509e = cashierPayEntity.dfExceptionInfo;
            postValue(cVar);
        }
    }
}
