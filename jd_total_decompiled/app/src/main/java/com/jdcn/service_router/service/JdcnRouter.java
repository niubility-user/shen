package com.jdcn.service_router.service;

/* loaded from: classes18.dex */
public class JdcnRouter {
    public static <I> I getService(String str, b bVar) throws JdcnServiceNotRegistException, ClassNotFoundException, ClassCastException {
        return (I) ServiceLoader.getService(str, bVar);
    }

    public static Object invokeServiceMethod(String str, Object... objArr) throws Exception {
        return ServiceLoader.invokeServiceMethod(str, null, objArr);
    }

    public static <I> I getService(String str) throws JdcnServiceNotRegistException, ClassNotFoundException, ClassCastException {
        return (I) ServiceLoader.getService(str, null);
    }
}
