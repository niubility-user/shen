package com.jingdong.common.entity.settlement;

/* loaded from: classes5.dex */
public class EasyBuyAddAddressAllInfo extends AddressAllInfo {
    private AddAddressResponseResult addAddress;

    public AddAddressResponseResult getAddAddress() {
        AddAddressResponseResult addAddressResponseResult = this.addAddress;
        return addAddressResponseResult == null ? new AddAddressResponseResult() : addAddressResponseResult;
    }

    public void setAddAddress(AddAddressResponseResult addAddressResponseResult) {
        this.addAddress = addAddressResponseResult;
    }
}
