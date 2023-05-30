package com.jd.jdsec.a.k;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class k {
    private static long a;

    public static String a(Context context) {
        try {
            Iterator<Sensor> it = ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).iterator();
            while (it.hasNext()) {
                switch (it.next().getType()) {
                    case 1:
                        a |= 1;
                        break;
                    case 2:
                        a |= 64;
                        break;
                    case 4:
                        a |= 16;
                        break;
                    case 5:
                        a |= 32;
                        break;
                    case 6:
                        a |= 128;
                        break;
                    case 8:
                        a |= 256;
                        break;
                    case 9:
                        a |= 8;
                        break;
                    case 10:
                        a |= 2;
                        break;
                    case 11:
                        a |= 1024;
                        break;
                    case 12:
                        a |= 512;
                        break;
                    case 13:
                        a |= 4;
                        break;
                }
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.RiskLabel", "\u4f20\u611f\u5668\u68c0\u6d4b\u6a21\u5757\u5f02\u5e38" + e2.getMessage());
        }
        return Long.toBinaryString(a);
    }
}
