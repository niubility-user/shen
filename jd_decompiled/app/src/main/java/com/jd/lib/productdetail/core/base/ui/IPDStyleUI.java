package com.jd.lib.productdetail.core.base.ui;

import com.jd.lib.productdetail.core.entitys.promotion.PdStyleSelectSuitItem;
import com.jd.lib.productdetail.core.entitys.sizehelper.PDSizeHelperEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdGiftShopEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFeeInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessYanBaoItemEntity;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import java.util.List;

/* loaded from: classes15.dex */
public interface IPDStyleUI extends IBaseUI {
    void change3cServerFactoryView(List<WareBusinessJdServerProduct> list);

    void changeBaseInfo(String str);

    void changeCarBundlingView(boolean z);

    void changeDisTributeView(String str);

    void changeFeeInfo(WareBusinessFeeInfo wareBusinessFeeInfo);

    void changeFurnitureView(List<WareBusinessYanBaoItemEntity> list);

    void changeJdServerPlusView(List<WareBusinessJdServerProduct> list);

    void changeServiceInfo(List<WareBusinessYanBaoItemEntity> list);

    void changeSizeHelper(PDSizeHelperEntity pDSizeHelperEntity);

    void changeStockNum(String str);

    void changeSuitView(PdStyleSelectSuitItem pdStyleSelectSuitItem);

    void customizeService(String str);

    void giftShopping(PdGiftShopEntity.SelectEntrance selectEntrance);
}
