package com.jingdong.common.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.entity.cart.CartBalanceInfo;
import com.jingdong.common.entity.cart.CartResponseInfoEntity;
import com.jingdong.common.entity.cart.SubmitOrderProductInfo;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.web.IWebBusinessParams;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;

/* loaded from: classes5.dex */
public final class SettleAccountsImpl {
    private final String TAG = SettleAccountsImpl.class.getSimpleName();
    IWebBusinessParams businessParams;
    private String cartParamMapInfoJson;
    private SubmitOrderProductInfo cartResponseInfo;
    private String internationalAuthAction;
    private boolean isPDAuth;
    private boolean isSpecial;
    private String orderType;
    private int pdNumber;
    private String pdSku;

    public SettleAccountsImpl(IWebBusinessParams iWebBusinessParams) {
        this.businessParams = iWebBusinessParams;
    }

    public void getDataFromBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.cartResponseInfo = (SubmitOrderProductInfo) bundle.getParcelable("adapter");
        this.isSpecial = bundle.getBoolean("isSpecial", false);
        this.isPDAuth = bundle.getBoolean("isPDAuth", false);
        this.pdSku = bundle.getString("pdSku");
        this.pdNumber = bundle.getInt("pdNumber");
        this.cartParamMapInfoJson = bundle.getString("cartParamMapInfo");
        this.orderType = bundle.getString("orderType");
        this.internationalAuthAction = bundle.getString(NewFillOrderConstant.INTERNATIONAL_AUTH);
    }

    public void startOrderPage() {
        BaseActivity baseActivity;
        CartBalanceInfo cartBalanceInfo;
        IWebBusinessParams iWebBusinessParams = this.businessParams;
        if (iWebBusinessParams == null || (baseActivity = iWebBusinessParams.getBaseActivity()) == null) {
            return;
        }
        if (Log.D) {
            Log.d(this.TAG, " goAccount --->  ");
        }
        if (this.isPDAuth && !TextUtils.isEmpty(this.pdSku)) {
            String str = this.pdSku;
            int i2 = this.pdNumber;
            if (i2 <= 0) {
                i2 = 1;
            }
            DeepLinkFillOrderHelper.startFillOrder(baseActivity, str, i2, FillOrder.JDWORLDWIDE, 1);
            baseActivity.finish();
        } else if (this.cartResponseInfo != null) {
            if (Log.D) {
                Log.d(this.TAG, " goAccount ---> in ");
            }
            Constants.bAddEasyBuy = false;
            Constants.bEasyBuy = false;
            Constants.bModifyEasyBuy = false;
            Bundle bundle = new Bundle();
            bundle.putInt("sourceType", 1);
            bundle.putParcelable(NewFillOrderConstant.INTENT_EXTRA_SELECTED_CART, (Parcelable) this.cartResponseInfo);
            bundle.putBoolean("isSpecial", this.isSpecial);
            if (!TextUtils.isEmpty(this.cartParamMapInfoJson)) {
                bundle.putString("cartParamMapInfo", this.cartParamMapInfoJson);
            } else {
                SubmitOrderProductInfo submitOrderProductInfo = this.cartResponseInfo;
                if (submitOrderProductInfo instanceof CartResponseInfoEntity) {
                    CartResponseInfoEntity cartResponseInfoEntity = (CartResponseInfoEntity) submitOrderProductInfo;
                    if (cartResponseInfoEntity.checkedCartState == 5 && (cartBalanceInfo = cartResponseInfoEntity.balanceInfo) != null) {
                        String str2 = cartBalanceInfo.cartParamMapInfoJson;
                        this.cartParamMapInfoJson = str2;
                        if (!TextUtils.isEmpty(str2)) {
                            bundle.putString("cartParamMapInfo", this.cartParamMapInfoJson);
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(this.orderType)) {
                bundle.putString("orderType", this.orderType);
            }
            DeepLinkFillOrderHelper.startFillOrder(baseActivity, bundle);
            baseActivity.finish();
        } else if (TextUtils.isEmpty(this.internationalAuthAction)) {
        } else {
            Intent intent = new Intent();
            intent.setAction(this.internationalAuthAction);
            LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication().getBaseContext()).sendBroadcast(intent);
            baseActivity.finish();
        }
    }
}
