package com.jd.lib.cashier.sdk.pay.aac.impl;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;

/* loaded from: classes14.dex */
public class a implements com.jd.lib.cashier.sdk.pay.aac.impl.e.a {
    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.a
    public boolean b(com.jd.lib.cashier.sdk.h.c.a aVar) {
        CashierPayEntity cashierPayEntity;
        TopFloor topFloor;
        CashierCommonPopConfig cashierCommonPopConfig;
        if (aVar != null && (cashierPayEntity = aVar.K) != null && (topFloor = cashierPayEntity.topFloor) != null && (cashierCommonPopConfig = cashierPayEntity.indexPopupConfig) != null) {
            String str = cashierCommonPopConfig.subTitle;
            String str2 = cashierCommonPopConfig.message;
            String c2 = g0.c(topFloor.countdownTime, aVar.J);
            if (!TextUtils.isEmpty(c2)) {
                if (!TextUtils.isEmpty(str) && str.contains("{$cancelTime}")) {
                    cashierPayEntity.indexPopupConfig.replacedMessage = str.replace("{$cancelTime}", c2);
                    return true;
                } else if (!TextUtils.isEmpty(str2) && str2.contains("{$cancelTime}")) {
                    cashierPayEntity.indexPopupConfig.replacedMessage = str2.replace("{$cancelTime}", c2);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.a
    public boolean e(com.jd.lib.cashier.sdk.h.c.a aVar) {
        TopFloor topFloor;
        long longValue;
        if (aVar == null) {
            return true;
        }
        try {
            CashierPayEntity cashierPayEntity = aVar.K;
            if (cashierPayEntity == null || (topFloor = cashierPayEntity.topFloor) == null) {
                return true;
            }
            String str = topFloor.countdownTime;
            String str2 = topFloor.triggerCountdownTime;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            long parseFloat = Float.parseFloat(str) * 1000;
            if (TextUtils.isEmpty(str2)) {
                longValue = com.jd.lib.cashier.sdk.d.b.a.b.longValue();
            } else {
                try {
                    longValue = Float.parseFloat(str2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    longValue = com.jd.lib.cashier.sdk.d.b.a.b.longValue();
                }
            }
            return parseFloat > longValue * 1000;
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.a
    public void k(CashierCommonPopConfig cashierCommonPopConfig) {
        if (cashierCommonPopConfig == null || TextUtils.isEmpty(cashierCommonPopConfig.title) || TextUtils.isEmpty(cashierCommonPopConfig.highLightString) || !cashierCommonPopConfig.title.contains(cashierCommonPopConfig.highLightString)) {
            return;
        }
        int indexOf = cashierCommonPopConfig.title.indexOf(cashierCommonPopConfig.highLightString);
        int length = cashierCommonPopConfig.highLightString.length() + indexOf;
        if (indexOf < 0 || length < 0 || length < indexOf) {
            return;
        }
        SpannableString spannableString = new SpannableString(cashierCommonPopConfig.title);
        spannableString.setSpan(new ForegroundColorSpan(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C)), indexOf, length, 33);
        cashierCommonPopConfig.highLightTitle = spannableString;
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
    }
}
