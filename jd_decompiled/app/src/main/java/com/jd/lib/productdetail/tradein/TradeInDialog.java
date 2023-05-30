package com.jd.lib.productdetail.tradein;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jingdong.common.BaseActivity;

/* loaded from: classes16.dex */
public class TradeInDialog {
    public static final String EXTRA_PARAMS_KEY_ADDRESS = "extra.params.key.address";
    public static final String EXTRA_PARAMS_KEY_BIZ = "biz";
    public static final String EXTRA_PARAMS_KEY_BIZCODE = "bizCode";
    public static final String EXTRA_PARAMS_KEY_BUTTONTYPE = "extra.params.key.button.type";
    public static final String EXTRA_PARAMS_KEY_ESID = "extra.params.key.esid";
    public static final String EXTRA_PARAMS_KEY_EXTENDS_PARAMS = "extra.params.key.extends.params";
    public static final String EXTRA_PARAMS_KEY_HEIGHT_PERCENT = "extra.params.key.height.percent";
    public static final String EXTRA_PARAMS_KEY_INIT_STEP = "extra.params.key.button.init.step";
    public static final String EXTRA_PARAMS_KEY_IS_MIAOSHA = "extra.params.key.is.miaosha";
    public static final String EXTRA_PARAMS_KEY_IS_YOUSHOU = "extra.params.key.is.yushou";
    public static final String EXTRA_PARAMS_KEY_IS_YUYUE = "extra.params.key.is.yuyue";
    public static final String EXTRA_PARAMS_KEY_LAYER_FROM = "extra.params.key.layer.from";
    public static final String EXTRA_PARAMS_KEY_MAXSUBSIDY = "extra.params.key.maxSubsidy";
    public static final String EXTRA_PARAMS_KEY_PRICEMODE = "extra.params.key.pricemode";
    public static final String EXTRA_PARAMS_KEY_QUALIFICATIONID = "qualificationId";
    public static final String EXTRA_PARAMS_KEY_SETTLETYPE = "settleType";
    public static final String EXTRA_PARAMS_KEY_SKUID = "extra.params.key.skuid";
    public static final String EXTRA_PARAMS_KEY_SOURCE = "extra.params.key.source";
    public static final String EXTRA_PARAMS_KEY_TRADE_EXTENSION = "extension";
    public static final String EXTRA_PARAMS_KEY_TRADE_TYPE = "tradeType";
    public static final String EXTRA_PARAMS_RESULT_HAS_BTN_STYLE = "extra.params.key.has.btn.style";
    public static final String REQUEST_CODE = "pd.tradein.request.code";
    public static final String REQUEST_FROM = "pd.tradein.request.from";
    public static final String RESULT_KEY_QFID = "result.key.qfid";
    public BaseActivity mBaseActivity;
    public DialogFragment mInnerFragment;
    public ProductDetailEntity mProduct;

    public TradeInDialog(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    public FragmentManager getChildFragmentManager() {
        DialogFragment dialogFragment = this.mInnerFragment;
        if (dialogFragment != null) {
            return dialogFragment.getChildFragmentManager();
        }
        return null;
    }

    public void setArguments(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.getInt("biz", 4);
            TradeInDialogFragment tradeInDialogFragment = new TradeInDialogFragment(this.mBaseActivity);
            this.mInnerFragment = tradeInDialogFragment;
            tradeInDialogFragment.mProduct = this.mProduct;
        }
        this.mInnerFragment.setArguments(bundle);
    }

    public void setShowsDialog(boolean z) {
        DialogFragment dialogFragment = this.mInnerFragment;
        if (dialogFragment != null) {
            dialogFragment.setShowsDialog(z);
        }
    }

    public void show(@NonNull FragmentManager fragmentManager, @Nullable String str) {
        DialogFragment dialogFragment = this.mInnerFragment;
        if (dialogFragment != null) {
            dialogFragment.show(fragmentManager, str);
        }
    }
}
