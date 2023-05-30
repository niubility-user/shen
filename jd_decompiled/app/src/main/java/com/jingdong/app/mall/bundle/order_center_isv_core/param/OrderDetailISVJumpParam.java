package com.jingdong.app.mall.bundle.order_center_isv_core.param;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class OrderDetailISVJumpParam {
    public boolean canGoToVirtualOrder;
    public boolean isVirtualOrder;
    public String mFromPage;
    public String mTestId;
    public OrderISVRedirectProtocol newOrderISVRedirectProtocol;
    public String orderId;
    public int orderStatusId;
    public int orderType;

    private OrderISVRedirectProtocol getNewOrderISVRedirectProtocol() {
        OrderISVRedirectProtocol orderISVRedirectProtocol = this.newOrderISVRedirectProtocol;
        return orderISVRedirectProtocol == null ? new OrderISVRedirectProtocol() : orderISVRedirectProtocol;
    }

    public boolean equals(Object obj) {
        if (obj instanceof OrderDetailISVJumpParam) {
            OrderDetailISVJumpParam orderDetailISVJumpParam = (OrderDetailISVJumpParam) obj;
            return TextUtils.equals(this.orderId, orderDetailISVJumpParam.orderId) && this.orderType == orderDetailISVJumpParam.orderType && this.isVirtualOrder == orderDetailISVJumpParam.isVirtualOrder && this.canGoToVirtualOrder == orderDetailISVJumpParam.canGoToVirtualOrder && getNewOrderISVRedirectProtocol().equals(orderDetailISVJumpParam.getNewOrderISVRedirectProtocol());
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.orderType + this.orderStatusId + (this.isVirtualOrder ? 1 : 0) + (this.canGoToVirtualOrder ? 1 : 0);
        OrderISVRedirectProtocol orderISVRedirectProtocol = this.newOrderISVRedirectProtocol;
        return i2 + (orderISVRedirectProtocol == null ? 0 : orderISVRedirectProtocol.hashCode());
    }
}
