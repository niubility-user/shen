package com.jdjr.risk.device.c;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes18.dex */
public class ac {
    private static Map<String, List<String>> a = new HashMap();

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add("[ro.huawei.build.display.id]");
        a.put("[HUAWEI]", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("[ro.build.display.id]");
        a.put("[HONOR]", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("[ro.build.display.id]");
        a.put("[GOOGLE]", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("[ro.build.display.id]");
        a.put("[SONY]", arrayList4);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add("[ro.rom.version]");
        a.put("[ONEPLUS]", arrayList5);
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add("[ro.build.version.opporom]");
        arrayList6.add("[sys.build.display.id]");
        a.put("[OPPO]", arrayList6);
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add("[ro.build.display.id]");
        a.put("[MEIZU]", arrayList7);
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add("[ro.build.display.id]");
        a.put("[SAMSUNG]", arrayList8);
        ArrayList arrayList9 = new ArrayList();
        arrayList9.add("[ro.build.version.incremental]");
        a.put("[XIAOMI]", arrayList9);
        ArrayList arrayList10 = new ArrayList();
        arrayList10.add("[ro.vivo.os.build.display.id]");
        arrayList10.add("[ro.build.software.version]");
        a.put("[VIVO]", arrayList10);
        ArrayList arrayList11 = new ArrayList();
        arrayList11.add("[sys.build.display.id]");
        a.put("[REALME]", arrayList11);
        ArrayList arrayList12 = new ArrayList();
        arrayList12.add("[ro.smartisan.version]");
        a.put("[SMARTISAN]", arrayList12);
        ArrayList arrayList13 = new ArrayList();
        arrayList13.add("[ro.build.display.id]");
        a.put("[AMIGO]", arrayList13);
        ArrayList arrayList14 = new ArrayList();
        arrayList14.add("[ro.letv.release.version]");
        a.put("[LETV]", arrayList14);
        ArrayList arrayList15 = new ArrayList();
        arrayList15.add("[ro.build.sense.version]");
        a.put("[HTC]", arrayList15);
        ArrayList arrayList16 = new ArrayList();
        arrayList16.add("[sys.lge.lgmdm_version]");
        a.put("[LGE]", arrayList16);
        ArrayList arrayList17 = new ArrayList();
        arrayList17.add("[ro.build.nubia.rom.code]");
        a.put("[NUBIA]", arrayList17);
        ArrayList arrayList18 = new ArrayList();
        arrayList18.add("[ro.build.version.incremental]");
        a.put("[lenovo]", arrayList18);
        ArrayList arrayList19 = new ArrayList();
        arrayList19.add("[ro.build.nubia.rom.code]");
        a.put("[ZTE]", arrayList19);
        ArrayList arrayList20 = new ArrayList();
        arrayList20.add("[ro.build.display.id]");
        a.put("[TCL]", arrayList20);
    }

    public static String a() {
        List<String> list;
        StringBuilder sb = new StringBuilder();
        try {
            String a2 = j.a("getprop", true);
            if (!TextUtils.isEmpty(a2)) {
                Properties properties = new Properties();
                properties.load(new ByteArrayInputStream(a2.getBytes()));
                String property = properties.getProperty("[ro.product.manufacturer]", "");
                if (!TextUtils.isEmpty(property) && (list = a.get(property.toUpperCase())) != null && list.size() > 0) {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        sb.append(properties.getProperty(it.next(), ""));
                    }
                }
                if (sb.length() == 0) {
                    sb.append(properties.get("[ro.build.display.id]"));
                }
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }
}
