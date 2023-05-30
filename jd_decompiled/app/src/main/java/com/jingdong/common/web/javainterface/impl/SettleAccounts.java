package com.jingdong.common.web.javainterface.impl;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.jingdong.common.cart.SettleAccountsImpl;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.IWebBusinessParams;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes12.dex */
public final class SettleAccounts extends BaseWebComponent implements IJavaInterface {
    private SettleAccountsImpl settleAccountsImpl;

    public SettleAccounts(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.settleAccountsImpl = new SettleAccountsImpl((IWebBusinessParams) iWebUiBinder);
    }

    public void getDataFromBundle(Bundle bundle) {
        this.settleAccountsImpl.getDataFromBundle(bundle);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.SETTLE_ACCOUNTS;
    }

    @Override // com.jingdong.common.web.BaseWebComponent
    public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
        super.setWebUiBinder(iWebUiBinder);
        this.settleAccountsImpl = new SettleAccountsImpl((IWebBusinessParams) iWebUiBinder);
        getDataFromBundle(iWebUiBinder.getWebEntity().mBundle);
    }

    @JavascriptInterface
    public void startOrderPage() {
        this.settleAccountsImpl.startOrderPage();
    }

    public SettleAccounts() {
    }
}
