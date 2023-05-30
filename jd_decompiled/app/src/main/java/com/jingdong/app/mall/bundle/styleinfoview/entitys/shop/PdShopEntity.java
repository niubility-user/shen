package com.jingdong.app.mall.bundle.styleinfoview.entitys.shop;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDShopTagEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PdShopEntity {
    public PdShopServiceEntity customerService;
    public PdShopInfoEntity shop;
    public PDShopTagEntity shopTag;

    public String getEventParam(boolean z) {
        String str;
        List<PdShopCardItemEntity> list;
        ArrayList<PdShopCategoryInfoEntity> arrayList;
        if (hasChat()) {
            str = "1";
        } else {
            str = hasJimi() ? "2" : "0";
        }
        PdShopInfoEntity pdShopInfoEntity = this.shop;
        if (pdShopInfoEntity == null || !z) {
            return str;
        }
        if (!TextUtils.isEmpty(pdShopInfoEntity.shopImage) && (arrayList = this.shop.categoryInfo) != null && arrayList.size() == 3 && this.shop.cardType != 3) {
            return str + "_1";
        }
        PdShopInfoEntity pdShopInfoEntity2 = this.shop;
        if (pdShopInfoEntity2.cardType == 3 && (list = pdShopInfoEntity2.hotcates) != null && !list.isEmpty()) {
            return str + "_2";
        }
        return str + "_0";
    }

    public String getShopId() {
        PdShopInfoEntity pdShopInfoEntity = this.shop;
        if (pdShopInfoEntity != null) {
            return pdShopInfoEntity.shopId;
        }
        return null;
    }

    public boolean hasChat() {
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        return pdShopServiceEntity != null && pdShopServiceEntity.hasChat;
    }

    public boolean hasJimi() {
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        return pdShopServiceEntity != null && pdShopServiceEntity.hasJimi;
    }

    public boolean hasShop() {
        return this.shop != null;
    }

    public boolean hasShopChatInfo() {
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        return (pdShopServiceEntity == null || pdShopServiceEntity.chatInfo == null) ? false : true;
    }

    public boolean isContainsRecoInfo() {
        if (this.shop != null) {
            return !TextUtils.isEmpty(r0.shopImage);
        }
        return false;
    }
}
