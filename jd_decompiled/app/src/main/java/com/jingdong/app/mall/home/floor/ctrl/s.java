package com.jingdong.app.mall.home.floor.ctrl;

import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.common.utils.CommonBase;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes4.dex */
public class s {
    static int a;

    /* loaded from: classes4.dex */
    public static class a {
        public int a = -1;
        public int b = 0;

        /* renamed from: c  reason: collision with root package name */
        public int f9512c = 0;
    }

    public static boolean a(int i2, int i3, a aVar) {
        int i4;
        a = (CommonBase.getIntFromPreference("homewebfloor_triggerwebviewindex", -1) + 1) % i2;
        int intFromPreference = CommonBase.getIntFromPreference("homewebfloor_showMode", -1);
        int i5 = 0;
        if (intFromPreference == 1) {
            i4 = e();
            if (i4 < 0 || i4 >= CommonBase.getIntFromPreference("homewebfloor_dayshowtimes", 0) || i2 == 0) {
                return false;
            }
        } else {
            if (intFromPreference == 0) {
                int intFromPreference2 = CommonBase.getIntFromPreference("homewebfloor_showtimescount", i3);
                int intFromPreference3 = CommonBase.getIntFromPreference("homewebfloor_usedshowtimes", 0);
                if (intFromPreference2 - intFromPreference3 <= 0) {
                    return false;
                }
                i5 = intFromPreference3;
            }
            i4 = 0;
        }
        if (aVar != null) {
            aVar.a = intFromPreference;
            aVar.b = i5;
            aVar.f9512c = i4;
        }
        return true;
    }

    public static void b() {
        CommonBase.putStringToPreference("homewebfloor_floorid", "");
        CommonBase.putIntToPreference("homewebfloor_showtimescount", 0);
        CommonBase.putIntToPreference("homewebfloor_usedshowtimes", 0);
        CommonBase.putIntToPreference("homewebfloor_triggerwebviewindex", -1);
        CommonBase.putIntToPreference("homewebfloor_showMode", -1);
        CommonBase.putIntToPreference("homewebfloor_dayshowtimes", -1);
        CommonBase.putStringToPreference("homewebfloor_todaytimes", "");
    }

    public static void c(int i2, int i3, int i4) {
        CommonBase.putIntToPreference("homewebfloor_triggerwebviewindex", a);
        if (i2 == 0) {
            CommonBase.putIntToPreference("homewebfloor_usedshowtimes", i3 + 1);
        } else {
            g(i4 + 1);
        }
    }

    public static int d(HomeWebFloorEntity homeWebFloorEntity) {
        int intFromPreference;
        if (homeWebFloorEntity == null) {
            return 0;
        }
        int intFromPreference2 = CommonBase.getIntFromPreference("homewebfloor_showMode", -1);
        if (intFromPreference2 == 0) {
            return CommonBase.getIntFromPreference("homewebfloor_showtimescount", homeWebFloorEntity.showTimes) - CommonBase.getIntFromPreference("homewebfloor_usedshowtimes", 0);
        }
        if (intFromPreference2 == 1) {
            int e2 = e();
            if (e2 >= 0 && (intFromPreference = CommonBase.getIntFromPreference("homewebfloor_dayshowtimes", 0)) > 0) {
                return intFromPreference - e2;
            }
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    public static int e() {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String stringFromPreference = CommonBase.getStringFromPreference("homewebfloor_todaytimes", "");
        if (stringFromPreference.startsWith(format) && stringFromPreference.contains("|")) {
            try {
                return com.jingdong.app.mall.home.o.a.f.t0(stringFromPreference.substring(stringFromPreference.lastIndexOf("|") + 1), 0);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static boolean f(HomeWebFloorEntity homeWebFloorEntity) {
        boolean z;
        String str = homeWebFloorEntity.sourceValue;
        boolean z2 = true;
        if (str == null) {
            b();
            z = true;
        } else {
            z = false;
        }
        String stringFromPreference = CommonBase.getStringFromPreference("homewebfloor_floorid", "");
        if (stringFromPreference != null && !stringFromPreference.equals(str)) {
            b();
            z = true;
        }
        int intFromPreference = CommonBase.getIntFromPreference("homewebfloor_showMode", -1);
        if (homeWebFloorEntity.showTimesDaily > 0) {
            int intFromPreference2 = CommonBase.getIntFromPreference("homewebfloor_dayshowtimes", 0);
            if (intFromPreference != 1 || intFromPreference2 != homeWebFloorEntity.showTimesDaily) {
                b();
                z = true;
            }
            CommonBase.putIntToPreference("homewebfloor_dayshowtimes", homeWebFloorEntity.showTimesDaily);
            CommonBase.putIntToPreference("homewebfloor_showMode", 1);
            z2 = z;
        } else if (homeWebFloorEntity.showTimes > 0) {
            int intFromPreference3 = CommonBase.getIntFromPreference("homewebfloor_showtimescount", 0);
            if (intFromPreference == 0 && intFromPreference3 == homeWebFloorEntity.showTimes) {
                z2 = z;
            } else {
                b();
            }
            CommonBase.putIntToPreference("homewebfloor_showtimescount", homeWebFloorEntity.showTimes);
            CommonBase.putIntToPreference("homewebfloor_showMode", 0);
        } else {
            b();
        }
        CommonBase.putStringToPreference("homewebfloor_floorid", homeWebFloorEntity.sourceValue);
        return z2;
    }

    private static void g(int i2) {
        CommonBase.putStringToPreference("homewebfloor_todaytimes", new SimpleDateFormat("yyyyMMdd").format(new Date()) + "|" + i2);
    }

    public static void h() {
        CommonBase.putIntToPreference("homewebfloor_triggerwebviewindex", a);
    }
}
