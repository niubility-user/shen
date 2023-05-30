package com.jd.lib.cashier.sdk.d.f;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes14.dex */
public class c {
    private WeakReference<FragmentActivity> mActivity;
    public String appId = "";
    public String orderId = "";
    public String orderType = "";
    public String orderPrice = "";
    public String paySign = "";
    public String orderTypeCode = "";
    public String groupOrders = "";
    public String combinedOrderId = "";

    public c() {
    }

    private boolean hasActivity() {
        WeakReference<FragmentActivity> weakReference = this.mActivity;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    public FragmentActivity getActivity() {
        if (hasActivity()) {
            return this.mActivity.get();
        }
        return null;
    }

    public void setActivity(FragmentActivity fragmentActivity) {
        WeakReference<FragmentActivity> weakReference = this.mActivity;
        if (weakReference == null || weakReference.get() == null) {
            this.mActivity = new WeakReference<>(fragmentActivity);
        }
    }

    public String toString() {
        return "BaseParam{appId='" + this.appId + "', orderId='" + this.orderId + "', orderType='" + this.orderType + "', orderPrice='" + this.orderPrice + "', paySign='" + this.paySign + "', groupOrders='" + this.groupOrders + "', combinedOrderId='" + this.combinedOrderId + "'}";
    }

    public c(WeakReference<FragmentActivity> weakReference) {
        this.mActivity = weakReference;
    }
}
