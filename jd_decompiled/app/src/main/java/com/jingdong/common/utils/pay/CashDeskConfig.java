package com.jingdong.common.utils.pay;

import android.graphics.Color;
import android.text.TextUtils;

/* loaded from: classes6.dex */
public class CashDeskConfig {
    public static final String CLOSE_TYPE_1 = "1";
    public static final String CLOSE_TYPE_2 = "2";
    public static final String CLOSE_TYPE_3 = "3";
    public static final int DIALOG_SWITCH = 1;
    public static final String SOURCE_BANLANCE = "banlance";
    public static final String SOURCE_CART = "cart";
    public static final String SOURCE_NOMAL = "nomal";
    public static final String SOURCE_PRODUCTDETAIL = "productdetail";
    public String closeType;
    public String colorType;
    public String containerType;
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

    public CashDeskConfig(int i2) {
        this.dialogSwitch = i2;
    }

    public static int parseStr2Color(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Color.parseColor(str);
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    public int getColorType() {
        if (this.parseColor == -1 && !TextUtils.isEmpty(this.colorType)) {
            this.parseColor = parseStr2Color(this.colorType);
        }
        return this.parseColor;
    }

    public int getDialogSwitch() {
        return this.dialogSwitch;
    }

    public void setDialogSwitch(int i2) {
        this.dialogSwitch = i2;
    }

    public CashDeskConfig() {
    }
}
