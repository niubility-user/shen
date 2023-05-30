package com.jd.android.sdk.coreinfo.util;

import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class CommonUtil {
    public static String binderGetHardwareInfo(String str, IBinder iBinder, String str2, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str2);
            if (!TextUtils.isEmpty(str)) {
                obtain.writeString(str);
            }
            iBinder.transact(i2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static String ensureNotEmpty(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : str;
    }

    public static String getInterfaceDescriptor(Class cls) {
        Field declaredField = cls.getDeclaredField("DESCRIPTOR");
        declaredField.setAccessible(true);
        return (String) declaredField.get(null);
    }

    public static int getTransactionId(Object obj, String str) {
        Field declaredField = obj.getClass().getEnclosingClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return ((Integer) declaredField.get(obj)).intValue();
    }

    public static String handlerResult(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!TextUtils.isEmpty(str) && str.startsWith("Result: Parcel")) {
            boolean z = false;
            for (char c2 : str.toCharArray()) {
                if (c2 == '\'') {
                    z = !z;
                }
                if (z && Character.isLetterOrDigit(c2)) {
                    stringBuffer.append(c2);
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String ensureNotEmpty(String str) {
        return ensureNotEmpty(str, "");
    }
}
