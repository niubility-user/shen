package com.jd.lib.cashier.sdk.pay.aac.livedata;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.m;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class CyberMoneyCouponLiveData extends LiveData<m> {
    public synchronized void a(m mVar) {
        if (mVar != null) {
            mVar.d = new ArrayList();
            List<CyberMoneyCouponEntity> list = mVar.b;
            List<CyberMoneyCouponEntity> list2 = mVar.f3788c;
            if (list != null && !list.isEmpty()) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    CyberMoneyCouponEntity cyberMoneyCouponEntity = list.get(i2);
                    if (cyberMoneyCouponEntity != null) {
                        cyberMoneyCouponEntity.setChecked(false);
                        cyberMoneyCouponEntity.setViewType(1);
                        mVar.d.add(cyberMoneyCouponEntity);
                    }
                }
            }
            if (list2 != null && !list2.isEmpty()) {
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    CyberMoneyCouponEntity cyberMoneyCouponEntity2 = list2.get(i3);
                    if (cyberMoneyCouponEntity2 != null) {
                        cyberMoneyCouponEntity2.setViewType(2);
                        mVar.d.add(cyberMoneyCouponEntity2);
                    }
                }
            }
        }
    }

    public synchronized void b(DigitalMoneyBankCard digitalMoneyBankCard, CyberMoneyCouponResponse cyberMoneyCouponResponse) {
        List<CyberMoneyCouponEntity> list;
        if (cyberMoneyCouponResponse != null) {
            List<CyberMoneyCouponEntity> list2 = cyberMoneyCouponResponse.cantUseCouponList;
            if ((list2 != null && !list2.isEmpty()) || ((list = cyberMoneyCouponResponse.canUseCouponList) != null && !list.isEmpty())) {
                m mVar = new m();
                mVar.a = digitalMoneyBankCard;
                mVar.b = cyberMoneyCouponResponse.canUseCouponList;
                mVar.f3788c = cyberMoneyCouponResponse.cantUseCouponList;
                postValue(mVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0032, code lost:
        r2.setChecked(true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void c(e eVar, m mVar) {
        if (eVar != null && mVar != null) {
            List<CyberMoneyCouponEntity> list = mVar.d;
            if (list != null && !list.isEmpty()) {
                int size = list.size();
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        CyberMoneyCouponEntity cyberMoneyCouponEntity = list.get(i2);
                        if (cyberMoneyCouponEntity != null && cyberMoneyCouponEntity.canUse() && TextUtils.equals(eVar.getPayMarketingUUID(), cyberMoneyCouponEntity.getPayMarketingUUID())) {
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
