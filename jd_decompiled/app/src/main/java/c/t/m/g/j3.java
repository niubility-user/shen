package c.t.m.g;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class j3 {
    public static Object a(Class<?> cls, Object obj, String str, Class[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = t2.i(clsArr) ? cls.getDeclaredMethod(str, new Class[0]) : cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod != null) {
                boolean isAccessible = declaredMethod.isAccessible();
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(obj, objArr);
                declaredMethod.setAccessible(isAccessible);
                return invoke;
            } else if (z0.e()) {
                StringBuilder sb = new StringBuilder("method ");
                sb.append(str);
                sb.append("() is null.");
                return null;
            } else {
                return null;
            }
        } catch (Throwable unused) {
            if (z0.e()) {
                StringBuilder sb2 = new StringBuilder("invoke ");
                sb2.append(str);
                sb2.append(" error.");
                return null;
            }
            return null;
        }
    }

    public static Object b(Object obj, String str, Class[] clsArr, Object[] objArr) {
        return a(obj.getClass(), obj, str, clsArr, objArr);
    }
}
