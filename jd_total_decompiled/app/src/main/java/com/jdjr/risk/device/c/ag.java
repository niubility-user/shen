package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class ag {
    public static CellLocation a(Context context) {
        boolean z = true;
        try {
            Class.forName("com.jingdong.common.lbs.jdlocation.JDLocationCache").getMethod("getBSInfo", Class.forName("com.jingdong.common.lbs.jdlocation.JDLocationCacheOption"));
        } catch (Throwable unused) {
            z = false;
        }
        CellLocation cellLocation = null;
        try {
            if (z) {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId("969f8a4c33e098979ce372b8d40630e2");
                cellLocation = JDLocationCache.getInstance().getBSInfo(jDLocationCacheOption);
            } else {
                BaseInfo.getCellLocationForDeviceFinger();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return cellLocation;
    }

    public static String a() {
        try {
            return BaseInfo.getSimCountryIso();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context, int i2, int i3) {
        try {
            String networkOperator = BaseInfo.getNetworkOperator(context);
            return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < i3) ? "" : networkOperator.substring(i2, i3);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(CellLocation cellLocation) {
        String str;
        str = "";
        if (cellLocation != null) {
            try {
                str = cellLocation instanceof CdmaCellLocation ? "cdma" : "";
                return cellLocation instanceof GsmCellLocation ? "gsm" : str;
            } catch (Throwable unused) {
                return str;
            }
        }
        return "";
    }

    public static String a(CellLocation cellLocation, Context context) {
        if (cellLocation != null) {
            try {
                return cellLocation instanceof GsmCellLocation ? a(context, 3, 5) : cellLocation instanceof CdmaCellLocation ? String.valueOf(((CdmaCellLocation) cellLocation).getSystemId()) : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    public static String b() {
        try {
            return BaseInfo.getSimSerialNo();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || packageManager.checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0) {
                return "";
            }
            String subscriberIdForDeviceFinger = BaseInfo.getSubscriberIdForDeviceFinger();
            return subscriberIdForDeviceFinger == null ? "" : subscriberIdForDeviceFinger;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(CellLocation cellLocation) {
        if (cellLocation != null) {
            try {
                return cellLocation instanceof GsmCellLocation ? String.valueOf(((GsmCellLocation) cellLocation).getLac()) : cellLocation instanceof CdmaCellLocation ? String.valueOf(((CdmaCellLocation) cellLocation).getNetworkId()) : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    public static String c(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || packageManager.checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0) ? "" : BaseInfo.getDeviceIdForDeviceFinger();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String c(CellLocation cellLocation) {
        if (cellLocation != null) {
            try {
                return cellLocation instanceof GsmCellLocation ? String.valueOf(((GsmCellLocation) cellLocation).getCid()) : cellLocation instanceof CdmaCellLocation ? String.valueOf(((CdmaCellLocation) cellLocation).getBaseStationId()) : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }
}
