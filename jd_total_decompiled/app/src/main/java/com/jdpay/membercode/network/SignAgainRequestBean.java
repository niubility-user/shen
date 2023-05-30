package com.jdpay.membercode.network;

import com.jdpay.lib.annotation.BusinessParam;
import com.jdpay.lib.annotation.Name;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;

/* loaded from: classes18.dex */
public class SignAgainRequestBean extends EncryptRequestBean {
    @Name(JshopConst.JSHOP_COUPON_IDS)
    @BusinessParam
    public List<String> couponIds;
    @Name("isSafe")
    @BusinessParam
    public String isSafe;
}
