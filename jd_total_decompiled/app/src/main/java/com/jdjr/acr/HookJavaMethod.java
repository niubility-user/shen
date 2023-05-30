package com.jdjr.acr;

import android.os.Build;
import com.jdjr.tools.JDJRLog;

/* loaded from: classes18.dex */
public class HookJavaMethod {
    private static final String TAG = "HookJavaMethod";
    public String className;
    private int hooked = 0;
    public int index;
    public String methodName;
    public Class<?>[] parameterTypes;

    public HookJavaMethod(int i2, String str, String str2, Class<?>[] clsArr) {
        this.index = i2;
        this.className = str;
        this.methodName = str2;
        this.parameterTypes = clsArr;
    }

    public int getHooked() {
        JDJRLog.d(TAG, "getHooked:" + this.index + ":" + this.hooked);
        if (Build.VERSION.SDK_INT >= 26) {
            return this.hooked & 3;
        }
        return this.hooked & 7;
    }

    public void setHooked(int i2) {
        this.hooked = i2;
    }

    public HookJavaMethod(JavaMethod javaMethod) {
        this.index = javaMethod.index;
        this.className = javaMethod.className;
        this.methodName = javaMethod.methodName;
        this.parameterTypes = javaMethod.parameterTypes;
    }
}
