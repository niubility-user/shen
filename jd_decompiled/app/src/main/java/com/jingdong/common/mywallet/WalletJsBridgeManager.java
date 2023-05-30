package com.jingdong.common.mywallet;

import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.personal.AmountValueManager;
import com.jingdong.common.web.IRouterParams;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class WalletJsBridgeManager {
    public static void changeAmountValueStateForH5(IRouterParams iRouterParams) {
        if (AmountValueManager.getInstance().isShowAmountValue()) {
            AmountValueManager.getInstance().changeAmountValueState(false);
            AmountValueManager.getInstance().notifyChangeList(false);
            return;
        }
        AmountValueManager.getInstance().changeAmountValueState(true);
        AmountValueManager.getInstance().notifyChangeList(true);
    }

    public static void isShowAmountValueForH5(IRouterParams iRouterParams) {
        boolean isShowAmountValue = AmountValueManager.getInstance().isShowAmountValue();
        if (iRouterParams != null) {
            iRouterParams.onCallBack(Integer.valueOf(isShowAmountValue ? 1 : 0));
        }
    }

    public static void navigatePageForH5(IRouterParams iRouterParams) {
        String str;
        if (iRouterParams == null) {
            return;
        }
        try {
            str = PasteStateRouterImpl.pasteState(iRouterParams).getString("functionId");
        } catch (JSONException e2) {
            e2.printStackTrace();
            str = "";
        }
        WalletUtils.dealJumpLogic((BaseActivity) iRouterParams.getContext(), str);
    }
}
