package com.jingdong.app.mall.home.floor.ctrl;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class k {
    private static SharedPreferences a = JdSdk.getInstance().getApplicationContext().getSharedPreferences("HomeLaunchXView", 0);

    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean a(HomeWebFloorViewEntity homeWebFloorViewEntity, HomeWebFloorEntity homeWebFloorEntity) {
        String str;
        int i2;
        String[] split;
        String[] split2;
        if (homeWebFloorViewEntity != null && !homeWebFloorViewEntity.isErrorGuid(homeWebFloorEntity)) {
            String V = com.jingdong.app.mall.home.o.a.f.V();
            String string = a.getString("LAUNCH_XVIEW_DAY_TIMES", "");
            if (TextUtils.isEmpty(string) || (split2 = TextUtils.split(string, "#")) == null || split2.length <= 1) {
                str = "";
            } else {
                str = split2[0];
                if (V.equals(str)) {
                    i2 = com.jingdong.app.mall.home.n.h.c.h(split2[1], 0);
                    if (OKLog.D) {
                        com.jingdong.app.mall.home.o.a.f.r0("LaunchXViewTimes", "AllDayTimes: current= " + str + ContainerUtils.KEY_VALUE_DELIMITER + i2 + " max= " + homeWebFloorViewEntity.totalShowTimesDaily);
                    }
                    if (i2 < homeWebFloorViewEntity.totalShowTimesDaily) {
                        return false;
                    }
                    String str2 = homeWebFloorViewEntity.sourceValue;
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "LaunchXView_Default";
                    }
                    String concat = "LXVIEW_OATIMS".concat(str2);
                    int h2 = com.jingdong.app.mall.home.n.h.c.h(a.getString(concat, "0"), 0);
                    if (OKLog.D) {
                        com.jingdong.app.mall.home.o.a.f.r0("LaunchXViewTimes", "OneAllTimes: current= " + concat + ContainerUtils.KEY_VALUE_DELIMITER + h2 + " max= " + homeWebFloorViewEntity.showTimes);
                    }
                    if (h2 >= homeWebFloorViewEntity.showTimes) {
                        return false;
                    }
                    String concat2 = "LXVIEW_ODTIMS".concat(str2);
                    String string2 = a.getString(concat2, "");
                    int h3 = (TextUtils.isEmpty(string2) || (split = TextUtils.split(string2, "#")) == null || split.length <= 1 || !V.equals(split[0])) ? 0 : com.jingdong.app.mall.home.n.h.c.h(split[1], 0);
                    if (OKLog.D) {
                        com.jingdong.app.mall.home.o.a.f.r0("LaunchXViewTimes", "OneDayTimes: current= " + concat2 + ContainerUtils.KEY_VALUE_DELIMITER + h3 + " max= " + homeWebFloorViewEntity.showTimesDaily);
                    }
                    return h3 < homeWebFloorViewEntity.showTimesDaily;
                }
            }
            i2 = 0;
            if (OKLog.D) {
            }
            if (i2 < homeWebFloorViewEntity.totalShowTimesDaily) {
            }
        } else {
            com.jingdong.app.mall.home.floor.ctrl.t.m.e(homeWebFloorViewEntity, "6");
            return false;
        }
    }

    public static void b(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        String str;
        int i2;
        String[] split;
        String[] split2;
        if (homeWebFloorViewEntity == null) {
            return;
        }
        String V = com.jingdong.app.mall.home.o.a.f.V();
        String str2 = "";
        String string = a.getString("LAUNCH_XVIEW_DAY_TIMES", "");
        int i3 = 0;
        if (TextUtils.isEmpty(string) || (split2 = TextUtils.split(string, "#")) == null || split2.length <= 1) {
            str = "";
            i2 = 0;
        } else {
            str = split2[0];
            i2 = com.jingdong.app.mall.home.n.h.c.h(split2[1], 0);
        }
        int i4 = V.equals(str) ? i2 + 1 : 1;
        SharedPreferences.Editor edit = a.edit();
        edit.putString("LAUNCH_XVIEW_DAY_TIMES", V.concat("#").concat(String.valueOf(i4)));
        String str3 = homeWebFloorViewEntity.sourceValue;
        if (TextUtils.isEmpty(str3)) {
            str3 = "LaunchXView_Default";
        }
        String concat = "LXVIEW_OATIMS".concat(str3);
        edit.putString(concat, String.valueOf(com.jingdong.app.mall.home.n.h.c.h(a.getString(concat, "0"), 0) + 1));
        String concat2 = "LXVIEW_ODTIMS".concat(str3);
        String string2 = a.getString(concat2, "");
        if (!TextUtils.isEmpty(string2) && (split = TextUtils.split(string2, "#")) != null && split.length > 1) {
            str2 = split[0];
            i3 = com.jingdong.app.mall.home.n.h.c.h(split[1], 0);
        }
        edit.putString(concat2, V.concat("#").concat(String.valueOf(V.equals(str2) ? 1 + i3 : 1)));
        edit.apply();
    }
}
