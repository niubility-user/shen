package com.jdjr.risk.device.c;

import android.content.Context;
import android.hardware.Camera;
import android.os.storage.StorageManager;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class s {
    public static int a() {
        try {
            return Camera.getNumberOfCameras();
        } catch (Exception unused) {
            return -1;
        }
    }

    public static List<Map> a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<com.jdjr.risk.device.entity.p> it = g(context).iterator();
            while (it.hasNext()) {
                com.jdjr.risk.device.entity.p next = it.next();
                HashMap hashMap = new HashMap();
                hashMap.put("id", next.a());
                hashMap.put("path", next.b());
                hashMap.put(XView2Constants.STATE, next.d());
                hashMap.put("isRemovable", Boolean.valueOf(next.c()));
                arrayList.add(hashMap);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private static boolean a(int i2) {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i3 = 0; i3 < numberOfCameras; i3++) {
            Camera.getCameraInfo(i3, cameraInfo);
            if (i2 == cameraInfo.facing) {
                return true;
            }
        }
        return false;
    }

    public static boolean b() {
        try {
            return a(1);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c() {
        try {
            return a(0);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            return CoreInfo.Device.isNFCAvailable(context);
        } catch (Exception unused) {
            return false;
        }
    }

    public static int d() {
        try {
            return BaseInfo.isBluetoothAvailabel() ? 1 : 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static e d(Context context) {
        try {
            e e2 = e.e();
            e2.a(context);
            return e2;
        } catch (Exception unused) {
            return e.e();
        }
    }

    public static String e(Context context) {
        return "";
    }

    public static String f(Context context) {
        return "";
    }

    private static ArrayList<com.jdjr.risk.device.entity.p> g(Context context) {
        ArrayList<com.jdjr.risk.device.entity.p> arrayList = new ArrayList<>();
        StorageManager storageManager = (StorageManager) context.getSystemService(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE);
        try {
            Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
            method.setAccessible(true);
            Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    com.jdjr.risk.device.entity.p pVar = new com.jdjr.risk.device.entity.p();
                    pVar.b((String) obj.getClass().getMethod("getPath", new Class[0]).invoke(obj, new Object[0]));
                    pVar.a(((Boolean) obj.getClass().getMethod("isRemovable", new Class[0]).invoke(obj, new Object[0])).booleanValue());
                    pVar.c((String) obj.getClass().getMethod("getState", new Class[0]).invoke(obj, new Object[0]));
                    pVar.a((String) obj.getClass().getMethod("getId", new Class[0]).invoke(obj, new Object[0]));
                    arrayList.add(pVar);
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return arrayList;
    }
}
