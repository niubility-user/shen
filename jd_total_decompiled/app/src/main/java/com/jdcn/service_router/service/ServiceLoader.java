package com.jdcn.service_router.service;

import android.text.TextUtils;
import com.jdcn.service_router.interfaces.JdcnBridgeService;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes18.dex */
public class ServiceLoader<T> {
    private static Map<String, d> a = new HashMap();
    private static Map<String, e> b = new HashMap();

    static {
        a.put("person_verify", new d(JdcnBridgeService.class, "com.jdjr.risk.identity.verify.IdentityVerifyBridgeService"));
        a.put("biometric", new d(JdcnBridgeService.class, "com.jdjr.risk.BiometricBridgeServiceImpl"));
        a.put("videosignature", new d(JdcnBridgeService.class, "com.jdjr.risk.jdcn.avsig.JdcnVideoSignatureBridgeServiceImpl"));
    }

    ServiceLoader() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T getService(String str, b bVar) throws JdcnServiceNotRegistException, ClassNotFoundException, ClassCastException {
        if (a.containsKey(str)) {
            d dVar = a.get(str);
            if (dVar != null && !TextUtils.isEmpty(dVar.a())) {
                Class<?> cls = Class.forName(dVar.a());
                if (dVar.b().isAssignableFrom(cls)) {
                    if (bVar == null) {
                        return (T) dVar.b().cast(cls.newInstance());
                    }
                    a aVar = (a) bVar;
                    return (T) dVar.b().cast(cls.newInstance());
                }
                throw new ClassCastException(cls.getCanonicalName() + " \u4e0d\u662f " + dVar.b().getCanonicalName() + " \u7684\u5b50\u7c7b ");
            }
            throw new JdcnServiceNotRegistException("\u670d\u52a1 " + str + " ServiceImpl \u6ce8\u518c\u4fe1\u606f\u4e0d\u5168");
        }
        throw new JdcnServiceNotRegistException("\u6ca1\u6709\u67e5\u627e\u5230 " + str + " \u7684\u670d\u52a1");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object invokeServiceMethod(String str, c cVar, Object... objArr) throws Exception {
        Method method;
        if (b.containsKey(str)) {
            if (b.get(str) != null && !TextUtils.isEmpty(null) && !TextUtils.isEmpty(null)) {
                Class<?> cls = Class.forName(null);
                if (cVar == null) {
                    Method[] methods = cls.getMethods();
                    int length = methods.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            method = null;
                            break;
                        }
                        method = methods[i2];
                        if (TextUtils.equals(method.getName(), null)) {
                            break;
                        }
                        i2++;
                    }
                    if (method != null) {
                        return method.invoke(null, objArr);
                    }
                    throw new NoSuchMethodException("\u6ca1\u6709 " + str + " \u7684\u670d\u52a1");
                }
                return null;
            }
            throw new NoSuchMethodException("\u6ca1\u6709 " + str + " \u7684\u670d\u52a1");
        }
        throw new JdcnServiceNotRegistException("\u6ca1\u6709\u67e5\u627e\u5230 " + str + " \u7684\u670d\u52a1");
    }
}
