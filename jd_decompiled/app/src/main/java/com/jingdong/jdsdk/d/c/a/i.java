package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.database.table.DB_CartTable;
import com.jingdong.common.database.table.DB_PacksTable;
import com.jingdong.common.entity.Pack;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.sdk.platform.lib.entity.product.AwareInfo;
import com.jingdong.sdk.platform.lib.entity.product.PackInfo;
import com.jingdong.sdk.platform.lib.entity.product.SourceEntityInfo;
import com.jingdong.sdk.platform.lib.openapi.db.ICartTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class i implements ICartTable {
    private static i a;

    private i() {
    }

    private Pack a(PackInfo packInfo) {
        if (packInfo == null) {
            return null;
        }
        Pack pack = new Pack();
        pack.setId(packInfo.getId());
        pack.setName(packInfo.getName());
        pack.setNum(packInfo.getNum());
        pack.setProductCount(packInfo.getProductCount());
        if (packInfo.getSourceEntity() != null) {
            pack.setSourceEntity(new SourceEntity(packInfo.getSourceEntity().getSourceType(), packInfo.getSourceEntity().getSourceValue()));
        }
        return pack;
    }

    private Product b(AwareInfo awareInfo) {
        if (awareInfo == null) {
            return null;
        }
        Product product = new Product();
        product.setId(awareInfo.getId());
        product.setName(awareInfo.getName());
        product.setNum(awareInfo.getNum());
        if (awareInfo.getSourceEntity() != null) {
            product.setSourceEntity(new SourceEntity(awareInfo.getSourceEntity().getSourceType(), awareInfo.getSourceEntity().getSourceValue()));
        }
        return product;
    }

    public static synchronized i c() {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i();
            }
            iVar = a;
        }
        return iVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void delAllCart() {
        DB_CartTable.delAllCart();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void delAllPacksCart() {
        DB_PacksTable.delAllPacksCart();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public HashMap<String, AwareInfo> getCartListForProduct() {
        HashMap<String, AwareInfo> hashMap = new HashMap<>();
        for (Map.Entry<String, Product> entry : DB_CartTable.getCartListForProduct().entrySet()) {
            Product value = entry.getValue();
            AwareInfo awareInfo = new AwareInfo();
            awareInfo.setId(value.getId());
            awareInfo.setName(value.getName());
            awareInfo.setNum(value.getNum());
            if (value.getSourceEntity() != null) {
                awareInfo.setSourceEntity(new SourceEntityInfo(value.getSourceEntity().getSourceType(), value.getSourceEntity().getSourceValue()));
            }
            hashMap.put(entry.getKey(), awareInfo);
        }
        return hashMap;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public HashMap<String, PackInfo> getPacksListForPack() {
        HashMap<String, Pack> packsListForPack = DB_PacksTable.getPacksListForPack();
        if (packsListForPack == null) {
            return null;
        }
        HashMap<String, PackInfo> hashMap = new HashMap<>();
        for (Map.Entry<String, Pack> entry : packsListForPack.entrySet()) {
            Pack value = entry.getValue();
            PackInfo packInfo = new PackInfo();
            packInfo.setId(value.getId());
            packInfo.setName(value.getName());
            packInfo.setNum(value.getNum());
            packInfo.setProductCount(value.getProductCount());
            if (value.getSourceEntity() != null) {
                packInfo.setSourceEntity(new SourceEntityInfo(value.getSourceEntity().getSourceType(), value.getSourceEntity().getSourceValue()));
            }
            hashMap.put(entry.getKey(), packInfo);
        }
        return hashMap;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertAllCart(List<AwareInfo> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AwareInfo> it = list.iterator();
        while (it.hasNext()) {
            Product b = b(it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        DB_CartTable.insertAllCart(arrayList);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertAllPacksCart(List<PackInfo> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<PackInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        DB_PacksTable.insertAllPacksCart(arrayList);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertSingletonCart(AwareInfo awareInfo) {
        if (awareInfo == null) {
            return;
        }
        DB_CartTable.insertSingletonCart(b(awareInfo));
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertSingletonPacksCart(PackInfo packInfo) {
        if (packInfo == null) {
            return;
        }
        Pack pack = new Pack();
        pack.setId(packInfo.getId());
        pack.setName(packInfo.getName());
        pack.setNum(packInfo.getNum());
        pack.setProductCount(packInfo.getProductCount());
        if (packInfo.getSourceEntity() != null) {
            pack.setSourceEntity(new SourceEntity(packInfo.getSourceEntity().getSourceType(), packInfo.getSourceEntity().getSourceValue()));
        }
        DB_PacksTable.insertSingletonPacksCart(pack);
    }
}
