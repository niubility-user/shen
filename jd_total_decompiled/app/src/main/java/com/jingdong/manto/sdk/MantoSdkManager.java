package com.jingdong.manto.sdk;

import androidx.annotation.Nullable;
import com.jingdong.manto.sdk.api.IMantoLog;
import com.jingdong.manto.sdk.api.IShortcutManager;
import com.jingdong.manto.sdk.api.ITrackReport;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class MantoSdkManager {
    private static ArrayList<Class<? extends IMantoSdkBase>> globalSdks;
    private static Map<Class<? extends IMantoSdkBase>, Class<? extends IMantoSdkBase>> sdkImplements = new HashMap();
    private static Map<Class<? extends IMantoSdkBase>, IMantoSdkBase> globalObjects = new HashMap();

    static {
        ArrayList<Class<? extends IMantoSdkBase>> arrayList = new ArrayList<>();
        globalSdks = arrayList;
        arrayList.add(ITrackReport.class);
        globalSdks.add(IMantoLog.class);
        globalSdks.add(IShortcutManager.class);
        register(IShortcutManager.class, com.jingdong.manto.sdk.impl.shortcut.a.class);
    }

    @Nullable
    private static <T extends IMantoSdkBase> T createInstance(Class<T> cls) {
        Class<? extends IMantoSdkBase> cls2 = sdkImplements.get(cls);
        IMantoSdkBase iMantoSdkBase = null;
        if (cls2 == null) {
            return null;
        }
        try {
            iMantoSdkBase = cls2.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (Throwable unused) {
        }
        return (T) iMantoSdkBase;
    }

    @Nullable
    private static <T extends IMantoSdkBase> T createInstance(Class<T> cls, Object... objArr) {
        Class<? extends IMantoSdkBase> cls2 = sdkImplements.get(cls);
        IMantoSdkBase iMantoSdkBase = null;
        if (cls2 == null) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            clsArr[i2] = getClass(objArr[i2]);
        }
        try {
            iMantoSdkBase = cls2.getConstructor(clsArr).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        return (T) iMantoSdkBase;
    }

    private static Class getClass(Object obj) {
        Class<?> cls = obj.getClass();
        return cls.equals(Integer.class) ? Integer.TYPE : cls.equals(Boolean.class) ? Boolean.TYPE : cls.equals(Byte.class) ? Byte.TYPE : cls.equals(Character.class) ? Character.TYPE : cls.equals(Double.class) ? Double.TYPE : cls.equals(Float.class) ? Float.TYPE : cls.equals(Short.class) ? Short.TYPE : cls.equals(Long.class) ? Long.TYPE : cls;
    }

    @Nullable
    private static <T extends IMantoSdkBase> T getInstance(Class<T> cls) {
        if (globalObjects.get(cls) == null) {
            synchronized (MantoSdkManager.class) {
                if (globalObjects.get(cls) == null) {
                    globalObjects.put(cls, createInstance(cls));
                }
            }
        }
        return (T) globalObjects.get(cls);
    }

    @Nullable
    public static <T extends IMantoSdkBase> T instanceOf(Class<T> cls) {
        return globalSdks.contains(cls) ? (T) getInstance(cls) : (T) createInstance(cls);
    }

    public static <T extends IMantoSdkBase> void register(Class<T> cls, Class<? extends T> cls2) {
        if (sdkImplements.get(cls) == null) {
            sdkImplements.put(cls, cls2);
        }
    }
}
