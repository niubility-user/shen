package com.jingdong.app.mall.aura.internal;

import android.content.SharedPreferences;
import com.jingdong.app.mall.aura.i;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import java.util.ArrayList;

/* loaded from: classes19.dex */
public class c extends d {
    private static ArrayList<i.c> a = new ArrayList<>();
    private static c b;

    private c() {
    }

    private long c(long j2) {
        String e2 = e(j2);
        long switchDefaultValue = AuraBundleInfos.getSwitchDefaultValue(j2);
        Log.i("AuraSwitchOfNetwork", "getAuraConfigInt:" + e2);
        if (e2 != null && !"".equals(e2)) {
            try {
                switchDefaultValue = Long.parseLong(e2, 16);
            } catch (Exception e3) {
                e3.printStackTrace();
                switchDefaultValue = AuraBundleInfos.getSwitchDefaultValue(j2);
            }
        }
        return (switchDefaultValue < AuraBundleInfos.getSwitchMinValue() || switchDefaultValue > AuraBundleInfos.getSwitchMaxValue()) ? AuraBundleInfos.getSwitchDefaultValue(j2) : switchDefaultValue;
    }

    public static synchronized c f() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    public synchronized void a(i.c cVar) {
        ArrayList<i.c> arrayList = a;
        if (arrayList != null && cVar != null) {
            arrayList.add(cVar);
        }
    }

    public void b() {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.remove("auraSwitch_1");
        edit.remove("auraSwitch_2");
        edit.remove("auraSwitch_3");
        try {
            edit.apply();
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    public String d() {
        return ConfigUtil.getStringFromPreference("auraSwitch_1") + ":" + ConfigUtil.getStringFromPreference("auraSwitch_2") + ":" + ConfigUtil.getStringFromPreference("auraSwitch_3");
    }

    public String e(long j2) {
        String stringFromPreference;
        long switchType = AuraBundleInfos.getSwitchType(j2);
        Log.i("AuraSwitchOfNetwork", "switchType:" + Long.toHexString(switchType));
        String str = "";
        try {
            if (0 == switchType || 1152921504606846976L == switchType) {
                Log.i("AuraSwitchOfNetwork", "AURA_MASK_SWITCH_TYPE_1");
                stringFromPreference = ConfigUtil.getStringFromPreference("auraSwitch_1");
            } else if (4611686018427387904L == switchType) {
                Log.i("AuraSwitchOfNetwork", "AURA_MASK_SWITCH_TYPE_2");
                stringFromPreference = ConfigUtil.getStringFromPreference("auraSwitch_2");
            } else if (AuraBundleInfos.AURA_MASK_SWITCH_TYPE_3 != switchType) {
                return "";
            } else {
                Log.i("AuraSwitchOfNetwork", "AURA_MASK_SWITCH_TYPE_3");
                stringFromPreference = ConfigUtil.getStringFromPreference("auraSwitch_3");
            }
            str = stringFromPreference;
            return str;
        } catch (Throwable unused) {
            Log.e("AuraSwitchOfNetwork", "get config error");
            return str;
        }
    }

    public boolean g() {
        return h(AuraBundleInfos.getSwitchMaskFromBundleId(0));
    }

    public boolean h(long j2) {
        return (j2 & c(j2)) > 0;
    }
}
