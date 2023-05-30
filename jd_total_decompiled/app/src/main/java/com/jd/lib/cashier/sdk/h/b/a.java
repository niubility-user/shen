package com.jd.lib.cashier.sdk.h.b;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class a {
    public synchronized void a(Payment payment) {
        DigitalMoneyPayEntity digitalMoneyPayEntity;
        List<DigitalMoneyBankCard> list;
        com.jd.lib.cashier.sdk.pay.dialog.e d = com.jd.lib.cashier.sdk.h.h.e.d(payment);
        if (d != null) {
            payment.selectedCouponEntity = d;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(payment.code)) {
            payment.currentCreditCardBank = com.jd.lib.cashier.sdk.h.h.e.c(payment);
        } else if ("cyberMoney".equals(payment.code) && (digitalMoneyPayEntity = payment.virtualPayModel) != null && (list = digitalMoneyPayEntity.bankCardList) != null && list.size() > 0) {
            for (DigitalMoneyBankCard digitalMoneyBankCard : list) {
                boolean z = digitalMoneyBankCard.defaultSelected;
                digitalMoneyBankCard.selected = z;
                CyberMoneyCouponEntity cyberMoneyCouponEntity = digitalMoneyBankCard.defaultCouponEntity;
                digitalMoneyBankCard.selectedCoupon = cyberMoneyCouponEntity;
                if (z) {
                    payment.selectedCouponEntity = cyberMoneyCouponEntity;
                }
            }
        }
    }
}
