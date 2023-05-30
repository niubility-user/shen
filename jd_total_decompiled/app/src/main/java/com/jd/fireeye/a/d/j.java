package com.jd.fireeye.a.d;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class j {
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
        } catch (Throwable unused) {
        }
        return Long.toBinaryString(a);
    }
}
