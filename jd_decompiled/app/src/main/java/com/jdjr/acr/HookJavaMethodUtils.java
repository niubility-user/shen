package com.jdjr.acr;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jingdong.common.utils.LangUtils;
import com.wangyin.platform.ACMUtil;
import com.wangyin.platform.NativeACMUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes18.dex */
public class HookJavaMethodUtils {
    private static final String TAG = "HookJavaMethodUtils";
    private static int iAccessFlagsOffset;
    private static int iMethodSize;
    private static HookJavaMethodUtils instance;
    private static final Object lock = new Object();
    private final Context context;
    private ACMUtil mACMUtil;

    private HookJavaMethodUtils(Context context) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.context = context.getApplicationContext();
        this.mACMUtil = ACMUtil.newInstance(context);
        iMethodSize = -1;
    }

    private synchronized boolean IsNativeJava(String str, String str2, Class<?>[] clsArr) {
        try {
            try {
            } catch (NoSuchMethodException unused) {
                JDJRLog.e(TAG, "methodName " + str2 + LangUtils.SINGLE_SPACE + clsArr + " is not exits");
                return false;
            }
        } catch (ClassNotFoundException unused2) {
            JDJRLog.e(TAG, "className " + str + "is not exits");
            return false;
        }
        return Modifier.isNative(Class.forName(str).getDeclaredMethod(str2, clsArr).getModifiers());
    }

    private synchronized int IsNativeOrXposed(String str, String str2, Class<?>[] clsArr) {
        int accessFlagsOffset = getAccessFlagsOffset();
        int i2 = 0;
        if (accessFlagsOffset == -1) {
            JDJRLog.e(TAG, "getAccessFlagsOffset return -1");
            return 0;
        }
        try {
            try {
                byte[] accessFlags = this.mACMUtil.getAccessFlags(Class.forName(str).getDeclaredMethod(str2, clsArr), accessFlagsOffset);
                if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(accessFlags)))) {
                    byte[] retData = JDJRSecureUtils.getRetData(accessFlags);
                    int i3 = ((retData[3] & 255) << 24) | (retData[0] & 255) | ((retData[1] & 255) << 8) | ((retData[2] & 255) << 16);
                    if ((i3 & 256) != 0) {
                        JDJRLog.d(TAG, "IsNativeOrXposed:" + str + ":" + str2 + " is native");
                        i2 = 2;
                    }
                    if ((268435456 & i3) != 0) {
                        i2 |= 4;
                        JDJRLog.d(TAG, "IsNativeOrXposed:" + str + ":" + str2 + " has been hooked by xposed");
                    }
                    if ((536870912 & i3) != 0) {
                        i2 |= 8;
                        JDJRLog.d(TAG, "IsNativeOrXposed:" + str + ":" + str2 + " is backe of a methed that has been hooked by xposed");
                    }
                    if ((67108864 & i3) != 0) {
                        JDJRLog.d(TAG, "IsNativeOrXposed:" + str + ":" + str2 + " is kAccIgnoreAotCode");
                        i2 |= 16;
                    }
                    JDJRLog.d(TAG, "IsNativeOrXposed:" + str + ":" + str2 + " Modifiers:" + i3);
                } else {
                    JDJRLog.e(TAG, "IsNativeOrXposed error. class:" + str + " method:" + str2);
                }
                return i2;
            } catch (NoSuchMethodException unused) {
                JDJRLog.e(TAG, str2 + " not found");
                return 0;
            }
        } catch (ClassNotFoundException unused2) {
            JDJRLog.e(TAG, str + " not found");
            return 0;
        }
    }

    private int getAccessFlagsOffset() {
        Method method;
        int i2;
        int i3 = iAccessFlagsOffset;
        if (i3 <= 0 || i3 >= 100) {
            try {
                Method declaredMethod = NativeACMUtil.class.getDeclaredMethod("NativeGetAccessFlags", Object.class, Integer.TYPE);
                Method declaredMethod2 = HookJavaMethodDummy.class.getDeclaredMethod("testOffset2", new Class[0]);
                Method declaredMethod3 = HookJavaMethodDummy.class.getDeclaredMethod("testOffset3", new Class[0]);
                int methodSize = methodSize();
                byte[] accessFlagsOffset = this.mACMUtil.getAccessFlagsOffset(declaredMethod, methodSize, 265, 0);
                if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(accessFlagsOffset)))) {
                    byte[] retData = JDJRSecureUtils.getRetData(accessFlagsOffset);
                    int i4 = ((retData[3] & 255) << 24) | (retData[0] & 255) | ((retData[1] & 255) << 8) | ((retData[2] & 255) << 16);
                    JDJRLog.d(TAG, "offset1:" + i4);
                    if (i4 == -1) {
                        return -1;
                    }
                    byte[] accessFlagsOffset2 = this.mACMUtil.getAccessFlagsOffset(declaredMethod2, methodSize, 25, 0);
                    if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(accessFlagsOffset2)))) {
                        byte[] retData2 = JDJRSecureUtils.getRetData(accessFlagsOffset2);
                        int i5 = ((retData2[3] & 255) << 24) | (retData2[0] & 255) | ((retData2[1] & 255) << 8) | ((retData2[2] & 255) << 16);
                        JDJRLog.d(TAG, "offset2:" + i5);
                        if (i4 == -1) {
                            return -1;
                        }
                        if (i4 != i5) {
                            if (i4 > i5) {
                                method = declaredMethod2;
                                i2 = 25;
                            } else if (i4 < i5) {
                                i4 = i5;
                                method = declaredMethod;
                                i2 = 265;
                            } else {
                                method = null;
                                i4 = -1;
                                i2 = -1;
                            }
                            byte[] accessFlagsOffset3 = this.mACMUtil.getAccessFlagsOffset(method, methodSize, i2, i4);
                            if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(accessFlagsOffset3)))) {
                                byte[] retData3 = JDJRSecureUtils.getRetData(accessFlagsOffset3);
                                byte b = retData3[0];
                                byte b2 = retData3[1];
                                byte b3 = retData3[2];
                                byte b4 = retData3[3];
                                JDJRLog.d(TAG, "offset:" + i4);
                            } else {
                                JDJRLog.e(TAG, "again:getoffset error");
                            }
                        }
                        byte[] accessFlagsOffset4 = this.mACMUtil.getAccessFlagsOffset(declaredMethod3, methodSize, 18, i4);
                        if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(accessFlagsOffset4)))) {
                            byte[] retData4 = JDJRSecureUtils.getRetData(accessFlagsOffset4);
                            int i6 = ((retData4[3] & 255) << 24) | (retData4[0] & 255) | ((retData4[1] & 255) << 8) | ((retData4[2] & 255) << 16);
                            JDJRLog.d(TAG, "offset4:" + i6);
                            if (i6 != i4) {
                                return -1;
                            }
                            iAccessFlagsOffset = i4;
                            return i4;
                        }
                        JDJRLog.e(TAG, "4:getoffset error");
                        return -1;
                    }
                    JDJRLog.e(TAG, "2:getoffset error");
                    return -1;
                }
                JDJRLog.e(TAG, "1:getoffset error");
                return -1;
            } catch (NoSuchMethodException e2) {
                JDJRLog.e(TAG, e2.toString());
                return -1;
            }
        }
        return i3;
    }

    private int methodSize() {
        int i2 = iMethodSize;
        if (i2 <= 0 || i2 >= 100) {
            int i3 = -1;
            try {
                byte[] methodSize = this.mACMUtil.methodSize(HookJavaMethodDummy.class.getDeclaredMethod("testOffset2", new Class[0]), HookJavaMethodDummy.class.getDeclaredMethod("testOffset3", new Class[0]));
                if ("00000".equals(new String(JDJRSecureUtils.getErrorCode(methodSize)))) {
                    byte[] retData = JDJRSecureUtils.getRetData(methodSize);
                    i3 = (retData[0] & 255) | ((retData[1] & 255) << 8) | ((retData[2] & 255) << 16) | ((retData[3] & 255) << 24);
                    JDJRLog.d(TAG, "methodSize:" + i3);
                } else {
                    JDJRLog.e(TAG, "methodSize error");
                }
                iMethodSize = i3;
            } catch (NoSuchMethodException unused) {
            }
            return i3;
        }
        return i2;
    }

    public static HookJavaMethodUtils newInstance(Context context) {
        JDJRLog.i(TAG, "newInstance");
        JDJRLog.d(TAG, "apiLevel:" + Build.VERSION.SDK_INT);
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HookJavaMethodUtils(context);
                }
            }
        }
        return instance;
    }

    public synchronized int IsHooked(String str, String str2, Class<?>[] clsArr) {
        int i2 = IsNativeJava(str, str2, clsArr) ? 1 : 0;
        int IsNativeOrXposed = IsNativeOrXposed(str, str2, clsArr);
        if (IsNativeOrXposed == 0) {
            return i2;
        }
        int i3 = IsNativeOrXposed | i2;
        JDJRLog.i(TAG, "className=" + str + ", methodName=" + str2 + ", iret=" + i3);
        return i3;
    }
}
