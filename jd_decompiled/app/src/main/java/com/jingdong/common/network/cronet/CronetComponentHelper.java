package com.jingdong.common.network.cronet;

import com.jingdong.jdsdk.JdSdk;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CronetComponentHelper {
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
    public static final String CLASS_NAME_CRONET_SO_LOADER = "com.jd.lib.cronet.CronetSoLoader";
    public static final String CLASS_NAME_CRONET_STACK_FACTORY = "com.jd.lib.cronet.CronetStackFactory";
    public static final String METHOD_NAME_CRONET_INIT_CRONET_CONTEXT = "initCronetContext";
    public static final String METHOD_NAME_CRONET_LOADER_PLUGIN_SO = "loadPluginSo";
    public static final String METHOD_NAME_NEW_CRONET_ENGINE = "newEngine";
    public static final String METHOD_NAME_NEW_CRONET_ENGINE_BUILDER = "newEngineBuilder";
    public static final String TAG = "CronetComponentProvider";

    public static Class getClassForName(String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class<?> cls = hashMap.get(str);
        if (cls == null && (cls = JdSdk.getInstance().getApplication().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    public static Field getField(String str, String str2) throws ClassNotFoundException, NoSuchFieldException {
        Class classForName = getClassForName(str);
        if (classForName != null) {
            return classForName.getDeclaredField(str2);
        }
        return null;
    }

    public static Method getMethod(String str, String str2, Class<?>... clsArr) throws ClassNotFoundException, NoSuchMethodException {
        Class classForName = getClassForName(str);
        if (classForName != null) {
            return classForName.getMethod(str2, clsArr);
        }
        return null;
    }
}
