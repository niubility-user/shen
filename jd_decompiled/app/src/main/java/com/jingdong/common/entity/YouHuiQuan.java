package com.jingdong.common.entity;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class YouHuiQuan implements Serializable {
    private static final int DONG_QUAN = 2;
    private static final int JING_QUAN = 1;

    /* loaded from: classes5.dex */
    static class dongQuan {
        public static JSONObject jbDongQuanInfo;
        public static int nTotalCount;
        public static int nUsedCount;

        public dongQuan() {
            jbDongQuanInfo = new JSONObject();
        }
    }

    /* loaded from: classes5.dex */
    static class jingQuan {
        public static JSONObject jbJingQuanInfo;
        public static int nTotalCount;
        public static int nUsedCount;

        public jingQuan() {
            jbJingQuanInfo = new JSONObject();
        }
    }

    /* loaded from: classes5.dex */
    static class liPinKa {
        public static JSONObject jbLiPinKaInfo;
        public static double nTotalBalance;
        public static double nUsedBalance;

        public liPinKa() {
            jbLiPinKaInfo = new JSONObject();
        }
    }

    public synchronized JSONObject getJingDongQuanDetailInfo(int i2) {
        if (i2 == 1) {
            return jingQuan.jbJingQuanInfo;
        } else if (i2 != 2) {
            return null;
        } else {
            return dongQuan.jbDongQuanInfo;
        }
    }

    public synchronized int getJingDongQuanTotalCount(int i2) {
        if (i2 == 1) {
            return jingQuan.nTotalCount;
        } else if (i2 != 2) {
            return 0;
        } else {
            return dongQuan.nTotalCount;
        }
    }

    public synchronized int getJingDongQuanUsedCount(int i2) {
        if (i2 == 1) {
            return jingQuan.nUsedCount;
        } else if (i2 != 2) {
            return -1;
        } else {
            return dongQuan.nUsedCount;
        }
    }

    public synchronized JSONObject getLiPinKaInfo() {
        return liPinKa.jbLiPinKaInfo;
    }

    public synchronized double getLiPinKaTotalMoney() {
        return liPinKa.nTotalBalance;
    }

    public synchronized double getLiPinKaUsedBalance() {
        return liPinKa.nUsedBalance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0005, code lost:
        if (r2 != 2) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void setJingDongQuanDetailInfo(int r2, org.json.JSONObject r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            r0 = 1
            if (r2 == r0) goto L8
            r0 = 2
            if (r2 == r0) goto La
            goto Lc
        L8:
            com.jingdong.common.entity.YouHuiQuan.jingQuan.jbJingQuanInfo = r3     // Catch: java.lang.Throwable -> Le
        La:
            com.jingdong.common.entity.YouHuiQuan.dongQuan.jbDongQuanInfo = r3     // Catch: java.lang.Throwable -> Le
        Lc:
            monitor-exit(r1)
            return
        Le:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.YouHuiQuan.setJingDongQuanDetailInfo(int, org.json.JSONObject):void");
    }

    public synchronized void setJingDongQuanTotalCount(int i2, int i3) {
        if (i2 == 1) {
            jingQuan.nTotalCount = i3;
        } else if (i2 == 2) {
            dongQuan.nTotalCount = i3;
        }
    }

    public synchronized void setJingDongQuanUsedCount(int i2, int i3) {
        if (i2 == 1) {
            jingQuan.nUsedCount = i3;
        } else if (i2 == 2) {
            dongQuan.nUsedCount = i3;
        }
    }

    public synchronized void setLiPinKaInfo(JSONObject jSONObject) {
        liPinKa.jbLiPinKaInfo = jSONObject;
    }

    public synchronized void setLiPinKaTotalMoney(double d) {
        liPinKa.nTotalBalance = d;
    }

    public synchronized void setLiPinKaUsedBalance(double d) {
        liPinKa.nUsedBalance = d;
    }
}
