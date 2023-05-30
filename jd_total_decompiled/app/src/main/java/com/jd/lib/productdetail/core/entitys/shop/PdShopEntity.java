package com.jd.lib.productdetail.core.entitys.shop;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.PDShopTagEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdShopEntity {
    public PdShopServiceEntity customerService;
    public PdShopJumpEntity livingJumpParam;
    public PdShopInfoEntity shop;
    public boolean shop12Style;
    public PDShopTagEntity shopTag;
    public VerderRevert verderRevert;

    /* loaded from: classes15.dex */
    public class VerderRevert {
        public String arrow;
        public String backGround;
        public String czIcon;
        public String icon;
        public String manage_mode;
        public String officialStr;
        public String rankId;
        public String rankNum;
        public String rankUrl;
        public String rank_type;

        public VerderRevert() {
        }
    }

    public String getBubbleImage() {
        PdShopChatInfoEntity pdShopChatInfoEntity;
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        return (pdShopServiceEntity == null || (pdShopChatInfoEntity = pdShopServiceEntity.chatInfo) == null) ? "" : pdShopChatInfoEntity.bubbleImg;
    }

    public String getEventParam(boolean z) {
        String str;
        List<PdShopGoodNewItemEntity> list;
        List<PdShopCardItemEntity> list2;
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
        if (pdShopInfoEntity2.cardType == 3 && (list2 = pdShopInfoEntity2.hotcates) != null && !list2.isEmpty()) {
            return str + "_2";
        }
        PdShopInfoEntity pdShopInfoEntity3 = this.shop;
        if (pdShopInfoEntity3.cardType == 5 && (list = pdShopInfoEntity3.shopGoods) != null && !list.isEmpty()) {
            return str + "_3";
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

    public boolean isGovernorCustomerService() {
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        if (pdShopServiceEntity != null) {
            return pdShopServiceEntity.governorCustomerService;
        }
        return false;
    }

    public boolean isShowBubble() {
        PdShopChatInfoEntity pdShopChatInfoEntity;
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        if (pdShopServiceEntity == null || (pdShopChatInfoEntity = pdShopServiceEntity.chatInfo) == null) {
            return false;
        }
        return pdShopChatInfoEntity.isBubble;
    }

    public boolean isShowBubbleAboveInternetHospital() {
        PdShopServiceEntity pdShopServiceEntity = this.customerService;
        if (pdShopServiceEntity != null) {
            return pdShopServiceEntity.showBubbleAboveInternetHospital;
        }
        return false;
    }
}
