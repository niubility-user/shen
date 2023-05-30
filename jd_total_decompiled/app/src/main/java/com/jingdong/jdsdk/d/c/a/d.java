package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil;

/* loaded from: classes14.dex */
public class d implements IAddressUtil {
    private static d a = new d();

    private d() {
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public AddressGlobal getAddressGlobal() {
        return AddressUtil.getAddressGlobal();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public void onAddressChanged() {
        AddressUtil.onAddressChanged();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public boolean updateAddressGlobal(AddressGlobal addressGlobal) {
        return AddressUtil.updateAddressGlobal(addressGlobal);
    }
}
