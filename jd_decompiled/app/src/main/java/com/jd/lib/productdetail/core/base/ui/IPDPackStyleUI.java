package com.jd.lib.productdetail.core.base.ui;

import android.os.Bundle;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackSuitPriceEntity;
import com.jingdong.cleanmvp.presenter.IBaseUI;

/* loaded from: classes15.dex */
public interface IPDPackStyleUI extends IBaseUI {
    void hiddenStyleLayer();

    void loadPackPrice(String str, String str2, Bundle bundle);

    void refreshPackProduct(PDPackSuitPriceEntity pDPackSuitPriceEntity, Bundle bundle);
}
