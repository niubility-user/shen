package com.jingdong.aura.serviceloder;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class AuraServiceLoader {
    private static Map<String, String> serviceInfo;
    private static Map<Class<? extends IServiceProvider>, IServiceProvider> serviceKeeper = new HashMap();

    private static void ensureServiceInfo() {
        if (serviceInfo == null) {
            throw new NullPointerException("should call setServiceInfo before!");
        }
    }

    public static <T extends IServiceProvider> T get(Context context, Class<T> cls) {
        if (cls != null) {
            if (context != null) {
                ensureServiceInfo();
                try {
                    IServiceProvider iServiceProvider = serviceKeeper.get(cls);
                    if (iServiceProvider != null) {
                        return cls.cast(iServiceProvider);
                    }
                    return (T) load(context, cls);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RuntimeException(cls.getName() + " get service failed");
                }
            }
            throw new NullPointerException("context is null!! cannot get interface");
        }
        throw new NullPointerException("service interface cannot be null!");
    }

    private static synchronized <T extends IServiceProvider> T load(Context context, Class<T> cls) {
        synchronized (AuraServiceLoader.class) {
            if (serviceKeeper.get(cls) != null) {
                return cls.cast(serviceKeeper.get(cls));
            }
            String name = cls.getName();
            if (serviceInfo.containsKey(name)) {
                String str = serviceInfo.get(name);
                try {
                    T cast = cls.cast(Class.forName(str, false, context.getClassLoader()).newInstance());
                    cast.init(context);
                    serviceKeeper.put(cls, cast);
                    return cast;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RuntimeException(str + " load failed!!", e2);
                }
            }
            throw new RuntimeException(name + " no service impl found!!");
        }
    }

    public static void setServiceInfo(Map<String, String> map) {
        if (serviceInfo == null) {
            serviceInfo = map;
        }
    }
}
