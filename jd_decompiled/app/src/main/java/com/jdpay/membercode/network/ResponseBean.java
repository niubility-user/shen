package com.jdpay.membercode.network;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Exclusion;
import com.jdpay.lib.annotation.Name;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes18.dex */
public class ResponseBean<DATA> implements Bean {
    public static final int CODE_SUCCESS = 0;
    @Exclusion
    public String clientKey;
    @Name("resultCode")
    public int code;
    @Name("resultCtrl")
    public JDPayResultCtrlBean ctrl;
    @Name("resultData")
    public DATA data;
    @Name(CartConstant.KEY_CART_RESULTMSG)
    public String msg;

    public boolean isSuccessful() {
        return this.code == 0;
    }
}
