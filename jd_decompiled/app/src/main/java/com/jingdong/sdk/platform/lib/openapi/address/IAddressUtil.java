package com.jingdong.sdk.platform.lib.openapi.address;

import com.jingdong.common.entity.AddressGlobal;

/* loaded from: classes10.dex */
public interface IAddressUtil {
    AddressGlobal getAddressGlobal();

    void onAddressChanged();

    boolean updateAddressGlobal(AddressGlobal addressGlobal);
}
