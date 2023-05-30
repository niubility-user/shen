package com.jd.lib.cashier.sdk.b.c;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class e {
    private boolean a(CouponEntity couponEntity, CouponEntity couponEntity2) {
        return (couponEntity == null || couponEntity2 == null || TextUtils.isEmpty(couponEntity.getCouponId()) || TextUtils.isEmpty(couponEntity.getActivityId()) || !TextUtils.equals(couponEntity.getCouponId(), couponEntity2.getCouponId()) || !TextUtils.equals(couponEntity.getActivityId(), couponEntity2.getActivityId())) ? false : true;
    }

    public static CouponEntity c(List<CouponEntity> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private List<CouponEntity> d(String str, CouponEntity couponEntity, CouponEntity couponEntity2, List<CouponEntity> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<CouponEntity> e2 = e(couponEntity2);
        if (TextUtils.equals(str, "1")) {
            if (e2 != null && e2.size() > 0) {
                Iterator<CouponEntity> it = e2.iterator();
                while (it.hasNext()) {
                    CouponEntity f2 = f(it.next(), list);
                    if (f2 != null) {
                        arrayList.add(f2);
                    }
                }
            }
        } else if (TextUtils.equals(str, "2")) {
            arrayList.add(f(couponEntity, list));
        } else if (TextUtils.equals(str, "3")) {
            if (couponEntity != null && e2 != null && e2.size() > 0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= e2.size()) {
                        break;
                    } else if (a(e2.get(i2), couponEntity)) {
                        e2.remove(i2);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            if (e2 != null && e2.size() > 0) {
                Iterator<CouponEntity> it2 = e2.iterator();
                while (it2.hasNext()) {
                    CouponEntity f3 = f(it2.next(), list);
                    if (f3 != null) {
                        arrayList.add(f3);
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private List<CouponEntity> e(CouponEntity couponEntity) {
        if (couponEntity == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(couponEntity);
        return arrayList;
    }

    private CouponEntity f(CouponEntity couponEntity, List<CouponEntity> list) {
        for (CouponEntity couponEntity2 : list) {
            if (a(couponEntity, couponEntity2)) {
                couponEntity2.setSelected(Boolean.TRUE);
                return couponEntity2;
            }
        }
        return null;
    }

    public Pair<CouponEntity, List<CouponEntity>> b(String str, CouponEntity couponEntity, CouponEntity couponEntity2, List<CouponEntity> list) {
        CouponEntity c2 = c(d(str, couponEntity, couponEntity2, list));
        if (c2 == null) {
            if (list != null && list.size() > 0) {
                c2 = c.a();
            } else {
                c2 = c.b();
            }
        }
        return new Pair<>(c2, list);
    }
}
