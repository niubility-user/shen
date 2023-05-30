package com.jingdong.common.web;

import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class WebPerformanceHelper {
    public static final String CORE_TYPE = "kernelType";
    public static final String CORE_VER = "kernelVersion";
    public static final String ERR_MSG = "errMsg";
    public static final String EXTRA = "extra";
    public static final String IS_ERROR = "isError";
    public static final String LOADING_TIME = "mloadingTime";
    public static final String LOAD_TYPE = "mloadType";
    public static final String SECOND_TYPE = "secondType";
    private final String TAG = getClass().getSimpleName();
    private Object performanceInstance;
    private Method reportSimpleDataMethod;

    public WebPerformanceHelper() {
        try {
            Class<?> cls = Class.forName("com.jingdong.common.web.managers.PerformanceManager");
            this.reportSimpleDataMethod = cls.getDeclaredMethod("reportSimpleData", HashMap.class);
            this.performanceInstance = cls.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
    }

    public void reportSimpleData(Map<String, String> map) {
        Object obj;
        Method method;
        if (map == null || (obj = this.performanceInstance) == null || (method = this.reportSimpleDataMethod) == null) {
            return;
        }
        try {
            method.invoke(obj, map);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
    }
}
