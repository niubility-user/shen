package com.jd.lib.cashier.sdk.b.c;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class d {
    private final String a;

    public d(String str) {
        this.a = str;
    }

    private boolean a(CouponEntity couponEntity, CouponEntity couponEntity2) {
        return (couponEntity == null || couponEntity2 == null || TextUtils.isEmpty(couponEntity.getCouponId()) || TextUtils.isEmpty(couponEntity.getActivityId()) || !TextUtils.equals(couponEntity.getCouponId(), couponEntity2.getCouponId()) || !TextUtils.equals(couponEntity.getActivityId(), couponEntity2.getActivityId())) ? false : true;
    }

    private List<String> c(List<CouponEntity> list, List<CouponEntity> list2) {
        List<Integer> list3;
        Map<String, List<Integer>> map = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        CouponEntity couponEntity = list.get(0);
        CouponEntity couponEntity2 = list.get(list.size() - 1);
        if (list2 != null && !list2.isEmpty()) {
            map = d(couponEntity2, list2);
        }
        if (couponEntity != null && couponEntity2 != null && map != null && !map.isEmpty() && (list3 = map.get(couponEntity.getActUuId())) != null && !list3.isEmpty()) {
            for (int i2 = 0; i2 < list3.size(); i2++) {
                arrayList.add(String.valueOf(list3.get(i2)));
            }
        }
        return arrayList;
    }

    private Map<String, List<Integer>> d(CouponEntity couponEntity, List<CouponEntity> list) {
        ArrayList arrayList;
        if (couponEntity == null || list == null || list.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<CouponEntity> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CouponEntity next = it.next();
            if (next != null && a(couponEntity, next) && next.getCanCombinationActUuidList() != null) {
                String valueOf = String.valueOf(next.getCanCombinationActUuidList());
                if (!TextUtils.isEmpty(valueOf)) {
                    try {
                        JSONObject jSONObject = new JSONObject(valueOf);
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next2 = keys.next();
                            JSONArray optJSONArray = jSONObject.optJSONArray(next2);
                            if (optJSONArray == null || optJSONArray.length() == 0) {
                                arrayList = null;
                            } else {
                                arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    arrayList.add(Integer.valueOf(optJSONArray.optInt(i2)));
                                }
                            }
                            if (arrayList != null && !arrayList.isEmpty()) {
                                hashMap.put(next2, arrayList);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return hashMap;
    }

    private CouponEntity e(List<CouponEntity> list, List<CouponEntity> list2) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        boolean z = false;
        if (list.size() > 1) {
            CouponEntity couponEntity = new CouponEntity();
            couponEntity.setCouponInfo(this.a);
            couponEntity.setCouponId("combination");
            couponEntity.setCouponType("COMBINETYPE");
            couponEntity.setCombinationCouponList(list);
            for (CouponEntity couponEntity2 : list) {
                if (couponEntity2 != null && couponEntity2.getCanUse()) {
                    z = true;
                }
            }
            List<String> c2 = c(list, list2);
            couponEntity.setPlans(c2);
            couponEntity.setCanUse(z);
            if (c2 != null && c2.size() > 0) {
                couponEntity.setDefaultPlan(c2.get(c2.size() - 1));
            } else {
                couponEntity.setDefaultPlan("1");
            }
            return couponEntity;
        }
        return list.get(0);
    }

    private List<CouponEntity> f(String str, CouponEntity couponEntity, CouponEntity couponEntity2, List<CouponEntity> list) {
        Map<String, List<Integer>> d;
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<CouponEntity> g2 = g(couponEntity2);
        if (TextUtils.equals(str, "1")) {
            if (g2 != null && g2.size() > 0) {
                Iterator<CouponEntity> it = g2.iterator();
                while (it.hasNext()) {
                    CouponEntity h2 = h(it.next(), list);
                    if (h2 != null) {
                        arrayList.add(h2);
                    }
                }
            }
        } else if (TextUtils.equals(str, "2")) {
            arrayList.add(h(couponEntity, list));
            if (g2 != null && g2.size() > 0 && (d = d(couponEntity, list)) != null && d.size() > 0) {
                Iterator<CouponEntity> it2 = g2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    CouponEntity next = it2.next();
                    if (d.containsKey(next.getActUuId())) {
                        CouponEntity h3 = h(next, list);
                        if (h3 != null) {
                            arrayList.add(h3);
                        }
                    }
                }
            }
        } else if (TextUtils.equals(str, "3")) {
            if (couponEntity != null && g2 != null && g2.size() > 0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= g2.size()) {
                        break;
                    } else if (a(g2.get(i2), couponEntity)) {
                        g2.remove(i2);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            if (g2 != null && g2.size() > 0) {
                Iterator<CouponEntity> it3 = g2.iterator();
                while (it3.hasNext()) {
                    CouponEntity h4 = h(it3.next(), list);
                    if (h4 != null) {
                        arrayList.add(h4);
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private List<CouponEntity> g(CouponEntity couponEntity) {
        if (couponEntity == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (couponEntity.getCombinationCouponList() != null && couponEntity.getCombinationCouponList().size() > 0) {
            return couponEntity.getCombinationCouponList();
        }
        arrayList.add(couponEntity);
        return arrayList;
    }

    private CouponEntity h(CouponEntity couponEntity, List<CouponEntity> list) {
        for (CouponEntity couponEntity2 : list) {
            if (a(couponEntity, couponEntity2)) {
                couponEntity2.setSelected(Boolean.TRUE);
                return couponEntity2;
            }
        }
        return null;
    }

    private void i(List<CouponEntity> list, List<CouponEntity> list2) {
        if (list == null || list.size() == 0 || list2 == null || list2.size() == 0) {
            return;
        }
        if (list.size() == 2) {
            for (CouponEntity couponEntity : list2) {
                if (couponEntity != null) {
                    couponEntity.setStackableTag(Boolean.FALSE);
                }
            }
            return;
        }
        HashMap hashMap = new HashMap();
        for (CouponEntity couponEntity2 : list) {
            if (couponEntity2 != null && couponEntity2.getCanCombinationActUuidList() != null) {
                String valueOf = String.valueOf(couponEntity2.getCanCombinationActUuidList());
                if (!TextUtils.isEmpty(valueOf)) {
                    try {
                        JSONObject jSONObject = new JSONObject(valueOf);
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            ArrayList arrayList = null;
                            JSONArray optJSONArray = jSONObject.optJSONArray(next);
                            if (optJSONArray != null && optJSONArray.length() != 0) {
                                arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    arrayList.add(Integer.valueOf(optJSONArray.getInt(i2)));
                                }
                            }
                            if (arrayList != null && !arrayList.isEmpty()) {
                                hashMap.put(next, arrayList);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        Iterator<CouponEntity> it = list2.iterator();
        while (it.hasNext()) {
            CouponEntity next2 = it.next();
            boolean booleanValue = (next2 == null || next2.getSelected() == null) ? false : next2.getSelected().booleanValue();
            if (next2 != null && !booleanValue && hashMap.containsKey(next2.getActUuId())) {
                next2.setStackableTag(Boolean.TRUE);
            }
        }
    }

    public Pair<CouponEntity, List<CouponEntity>> b(String str, CouponEntity couponEntity, CouponEntity couponEntity2, List<CouponEntity> list) {
        List<CouponEntity> f2 = f(str, couponEntity, couponEntity2, list);
        i(f2, list);
        CouponEntity e2 = e(f2, list);
        if (e2 == null) {
            if (list != null && list.size() > 0) {
                e2 = c.a();
            } else {
                e2 = c.b();
            }
        }
        return new Pair<>(e2, list);
    }
}
