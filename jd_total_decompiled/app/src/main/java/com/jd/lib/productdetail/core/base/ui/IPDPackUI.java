package com.jd.lib.productdetail.core.base.ui;

import android.view.View;
import com.jd.lib.productdetail.core.entitys.promotion.PdPromotionPackEntry;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import com.jingdong.common.entity.Pack;
import java.util.List;

/* loaded from: classes15.dex */
public interface IPDPackUI<V> extends IBaseUI {
    void bindData2View(List<PdPromotionPackEntry> list);

    void dismissDialog();

    void onAdd2CarClick(Pack pack, String str, String str2);

    void onAdd2DrugListClick(String str);

    void onExpandView(V v);

    void showBottomDialog(View view);
}
