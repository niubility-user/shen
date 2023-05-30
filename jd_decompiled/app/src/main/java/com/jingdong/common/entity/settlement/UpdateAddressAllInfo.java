package com.jingdong.common.entity.settlement;

/* loaded from: classes5.dex */
public class UpdateAddressAllInfo extends AddressAllInfo {
    private AddressResultResponse updateAddress;

    public AddressResultResponse getUpdateAddress() {
        AddressResultResponse addressResultResponse = this.updateAddress;
        return addressResultResponse == null ? new AddressResultResponse() : addressResultResponse;
    }

    public void setUpdateAddress(AddressResultResponse addressResultResponse) {
        this.updateAddress = addressResultResponse;
    }
}
