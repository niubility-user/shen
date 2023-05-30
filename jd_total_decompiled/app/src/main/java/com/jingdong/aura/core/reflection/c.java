package com.jingdong.aura.core.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes4.dex */
public class c {

    /* loaded from: classes4.dex */
    public static abstract class a<T> implements InvocationHandler {
        private T a;

        protected T a() {
            return this.a;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                return method.invoke(a(), objArr);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                return null;
            } catch (InvocationTargetException e4) {
                throw e4.getTargetException();
            }
        }

        void a(T t) {
            this.a = t;
        }
    }

    private c() {
    }

    public static Object a(Object obj, a aVar, Class<?>... clsArr) {
        aVar.a(obj);
        return Proxy.newProxyInstance(c.class.getClassLoader(), clsArr, aVar);
    }
}
