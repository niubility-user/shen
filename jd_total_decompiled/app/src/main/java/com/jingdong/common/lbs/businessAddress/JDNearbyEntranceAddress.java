package com.jingdong.common.lbs.businessAddress;

/* loaded from: classes5.dex */
public class JDNearbyEntranceAddress {
    private JDBusinessAddress defaultAddress;
    private JDBusinessAddress globalAddress;

    public JDBusinessAddress getDefaultAddress() {
        JDBusinessAddress jDBusinessAddress = this.defaultAddress;
        return jDBusinessAddress == null ? new JDBusinessAddress() : jDBusinessAddress;
    }

    public JDBusinessAddress getGlobalAddress() {
        JDBusinessAddress jDBusinessAddress = this.globalAddress;
        return jDBusinessAddress == null ? new JDBusinessAddress() : jDBusinessAddress;
    }

    public void setDefaultAddress(JDBusinessAddress jDBusinessAddress) {
        this.defaultAddress = jDBusinessAddress;
    }

    public void setGlobalAddress(JDBusinessAddress jDBusinessAddress) {
        this.globalAddress = jDBusinessAddress;
    }
}
