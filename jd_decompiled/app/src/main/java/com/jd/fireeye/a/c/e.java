package com.jd.fireeye.a.c;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Environment;
import android.os.StatFs;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class e {
    public static int a(Context context) {
        try {
            return ((AudioManager) context.getSystemService("audio")).getStreamVolume(1);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static List<Sensor> b(Context context) {
        try {
            return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1);
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static String c(Context context) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Long.toString(statFs.getBlockCount() * statFs.getBlockSize());
        } catch (Exception unused) {
            return "";
        }
    }
}
