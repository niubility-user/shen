package com.jingdong.common.entity.cart.methodEntity;

import java.util.Map;

/* loaded from: classes5.dex */
public class CartForRefreshPdEntity {
    public static final String PD_KEY_DJBADTEXT = "djBadText";
    public static final String PD_KEY_TYPE = "type";
    public static final String PD_KEY_TYPE_BELOWMONEY = "1";
    public static final String PD_KEY_TYPE_OVERWEIGHT = "2";
    public Map<String, String> djBadInfo;
    public boolean isSuccess;
    public int resultCode;
    public String ybPackId;
}
