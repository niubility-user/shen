package com.jd.lib.cashier.sdk.core.ui.entity;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.s;

/* loaded from: classes14.dex */
public class CashierConfig {
    public static final String CLOSE_TYPE_1 = "1";
    public static final String CLOSE_TYPE_2 = "2";
    public static final String CLOSE_TYPE_3 = "3";
    public static final int DIALOG_SWITCH = 1;
    public static final String SOURCE_BANLANCE = "banlance";
    public static final String SOURCE_CART = "cart";
    public static final String SOURCE_NOMAL = "nomal";
    public static final String SOURCE_PRODUCTDETAIL = "productdetail";
    public String abTestParamMap;
    public String closeType;
    public String colorType;
    private int dialogSwitch;
    public String finishBtn;
    public String finishUrl;
    public String leftBtn;
    public String rightBtn;
    public String rightBtnUrl;
    public String title;
    public String source = "";
    public String sourceType = "";
    public String leaveto = "";
    public int parseColor = -1;
    public String orderId = "";
    public String notifyFlag = "";

    public CashierConfig(int i2) {
        this.dialogSwitch = i2;
    }

    public int getColorType() {
        if (this.parseColor == -1 && !TextUtils.isEmpty(this.colorType)) {
            this.parseColor = s.a(this.colorType);
        }
        return this.parseColor;
    }

    public int getDialogSwitch() {
        return this.dialogSwitch;
    }

    public void setDialogSwitch(int i2) {
        this.dialogSwitch = i2;
    }

    public CashierConfig() {
    }
}
