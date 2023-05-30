package com.jingdong.app.mall.bundle.styleinfoview.callback.listener;

import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.common.entity.cart.methodEntity.CartForRefreshPdEntity;

/* loaded from: classes3.dex */
public interface LibPdStyleInfoViewBottomBtnClickListener {
    void libPdBottomDismissDlg();

    void libPdBottomRefreshPDView(CartForRefreshPdEntity cartForRefreshPdEntity, String str, boolean z, PdBottomButtonInfo pdBottomButtonInfo, boolean z2);

    void libPdBottomShowDlg();
}
