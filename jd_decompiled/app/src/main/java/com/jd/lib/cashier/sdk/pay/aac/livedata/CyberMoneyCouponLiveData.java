package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.m;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void c(com.jd.lib.cashier.sdk.pay.dialog.e r6, com.jd.lib.cashier.sdk.pay.aac.livedata.a.m r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L3d
            if (r7 == 0) goto L3d
            java.util.List<com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity> r7 = r7.d     // Catch: java.lang.Throwable -> L3a
            if (r7 == 0) goto L3d
            boolean r0 = r7.isEmpty()     // Catch: java.lang.Throwable -> L3a
            if (r0 != 0) goto L3d
            int r0 = r7.size()     // Catch: java.lang.Throwable -> L3a
            r1 = 0
        L14:
            if (r1 >= r0) goto L3d
            java.lang.Object r2 = r7.get(r1)     // Catch: java.lang.Throwable -> L3a
            com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity r2 = (com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity) r2     // Catch: java.lang.Throwable -> L3a
            if (r2 == 0) goto L37
            boolean r3 = r2.canUse()     // Catch: java.lang.Throwable -> L3a
            if (r3 == 0) goto L37
            java.lang.String r3 = r6.getPayMarketingUUID()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r4 = r2.getPayMarketingUUID()     // Catch: java.lang.Throwable -> L3a
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Throwable -> L3a
            if (r3 == 0) goto L37
            r6 = 1
            r2.setChecked(r6)     // Catch: java.lang.Throwable -> L3a
            goto L3d
        L37:
            int r1 = r1 + 1
            goto L14
        L3a:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L3d:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.livedata.CyberMoneyCouponLiveData.c(com.jd.lib.cashier.sdk.pay.dialog.e, com.jd.lib.cashier.sdk.pay.aac.livedata.a.m):void");
    }
}
