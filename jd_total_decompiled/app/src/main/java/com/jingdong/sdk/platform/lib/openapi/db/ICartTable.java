package com.jingdong.sdk.platform.lib.openapi.db;

import com.jingdong.sdk.platform.lib.entity.product.AwareInfo;
import com.jingdong.sdk.platform.lib.entity.product.PackInfo;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes10.dex */
public interface ICartTable {
    void delAllCart();

    void delAllPacksCart();

    HashMap<String, AwareInfo> getCartListForProduct();

    HashMap<String, PackInfo> getPacksListForPack();

    void insertAllCart(List<AwareInfo> list);

    void insertAllPacksCart(List<PackInfo> list);

    void insertSingletonCart(AwareInfo awareInfo);

    void insertSingletonPacksCart(PackInfo packInfo);
}
