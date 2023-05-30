package com.jingdong.aura.core.reflection;

import com.jingdong.aura.core.reflection.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class Hack {
    private static a a;
    private static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("Hack");

    /* loaded from: classes.dex */
    public static abstract class HackDeclaration {

        /* loaded from: classes4.dex */
        public static class HackAssertionException extends Throwable {
            private static final long serialVersionUID = 1;
            private Class<?> mHackedClass;
            private String mHackedFieldName;
            private String mHackedMethodName;

            public HackAssertionException(String str) {
                super(str);
            }

            public Class<?> getHackedClass() {
                return this.mHackedClass;
            }

            public String getHackedFieldName() {
                return this.mHackedFieldName;
            }

            public String getHackedMethodName() {
                return this.mHackedMethodName;
            }

            public void setHackedClass(Class<?> cls) {
                this.mHackedClass = cls;
            }

            public void setHackedFieldName(String str) {
                this.mHackedFieldName = str;
            }

            public void setHackedMethodName(String str) {
                this.mHackedMethodName = str;
            }

            @Override // java.lang.Throwable
            public String toString() {
                if (getCause() != null) {
                    return getClass().getName() + ": " + getCause();
                }
                return super.toString();
            }

            public HackAssertionException(Exception exc) {
                super(exc);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface a {
        boolean a(HackDeclaration.HackAssertionException hackAssertionException);
    }

    /* loaded from: classes4.dex */
    public static class b<C> {
        protected Class<C> a;

        public b(Class<C> cls) {
            this.a = cls;
        }

        public d<C, Object> a(String str) {
            return new d<>(this.a, str, 0);
        }

        public d<C, Object> b(String str) {
            return new d<>(this.a, str, 8);
        }

        public e a(String str, Class<?>... clsArr) {
            return new e(this.a, str, clsArr, 0);
        }

        public c a(Class<?>... clsArr) {
            return new c(this.a, clsArr);
        }

        public Class<C> a() {
            return this.a;
        }
    }

    /* loaded from: classes4.dex */
    public static class c {
        protected Constructor<?> a;

        c(Class<?> cls, Class<?>[] clsArr) {
            if (cls != null) {
                try {
                    this.a = cls.getDeclaredConstructor(clsArr);
                } catch (Exception e2) {
                    HackDeclaration.HackAssertionException hackAssertionException = new HackDeclaration.HackAssertionException(e2);
                    hackAssertionException.setHackedClass(cls);
                    Hack.b(hackAssertionException);
                }
            }
        }

        public Object a(Object... objArr) {
            this.a.setAccessible(true);
            try {
                return this.a.newInstance(objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(HackDeclaration.HackAssertionException hackAssertionException) {
        b.d("hack fail:" + hackAssertionException.toString());
        a aVar = a;
        if (aVar == null || !aVar.a(hackAssertionException)) {
            throw hackAssertionException;
        }
    }

    /* loaded from: classes.dex */
    public static class d<C, T> {
        private final Field a;

        d(Class<C> cls, String str, int i2) {
            Field field = null;
            if (cls == null) {
                return;
            }
            try {
                try {
                    field = cls.getDeclaredField(str);
                    if (i2 > 0 && (field.getModifiers() & i2) != i2) {
                        Hack.b(new HackDeclaration.HackAssertionException(field + " does not match modifiers: " + i2));
                    }
                    field.setAccessible(true);
                } catch (Exception e2) {
                    HackDeclaration.HackAssertionException hackAssertionException = new HackDeclaration.HackAssertionException(e2);
                    hackAssertionException.setHackedClass(cls);
                    hackAssertionException.setHackedFieldName(str);
                    Hack.b(hackAssertionException);
                }
            } finally {
                this.a = field;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <T2> d<C, T2> a(Class<?> cls) {
            Field field = this.a;
            if (field != null && !cls.isAssignableFrom(field.getType())) {
                Hack.b(new HackDeclaration.HackAssertionException(new ClassCastException(this.a + " is not of type " + cls)));
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <T2> d<C, T2> b(Class<T2> cls) {
            Field field = this.a;
            if (field != null && !cls.isAssignableFrom(field.getType())) {
                Hack.b(new HackDeclaration.HackAssertionException(new ClassCastException(this.a + " is not of type " + cls)));
            }
            return this;
        }

        public T a(C c2) {
            try {
                return (T) this.a.get(c2);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public void a(C c2, Object obj) {
            boolean z;
            RuntimeException runtimeException;
            try {
                this.a.set(c2, obj);
            } finally {
                if (!z) {
                }
            }
        }

        public void a(C c2, c.a<?> aVar) {
            T a = a((d<C, T>) c2);
            if (a != null) {
                a((d<C, T>) c2, com.jingdong.aura.core.reflection.c.a(a, aVar, a.getClass().getInterfaces()));
                return;
            }
            throw new IllegalStateException("Cannot hijack null");
        }

        public Field a() {
            return this.a;
        }
    }

    /* loaded from: classes4.dex */
    public static class e {
        protected final Method a;

        e(Class<?> cls, String str, Class<?>[] clsArr, int i2) {
            Method method = null;
            if (cls == null) {
                return;
            }
            try {
                try {
                    method = cls.getDeclaredMethod(str, clsArr);
                    if (i2 > 0 && (method.getModifiers() & i2) != i2) {
                        Hack.b(new HackDeclaration.HackAssertionException(method + " does not match modifiers: " + i2));
                    }
                    method.setAccessible(true);
                } catch (Exception e2) {
                    HackDeclaration.HackAssertionException hackAssertionException = new HackDeclaration.HackAssertionException(e2);
                    hackAssertionException.setHackedClass(cls);
                    hackAssertionException.setHackedMethodName(str);
                    Hack.b(hackAssertionException);
                }
            } finally {
                this.a = method;
            }
        }

        public Object a(Object obj, Object... objArr) {
            try {
                return this.a.invoke(obj, objArr);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public Method a() {
            return this.a;
        }
    }

    public static <T> b<T> a(Class<T> cls) {
        return new b<>(cls);
    }

    public static <T> b<T> a(String str) {
        try {
            return new b<>(Class.forName(str));
        } catch (Exception e2) {
            b(new HackDeclaration.HackAssertionException(e2));
            return new b<>(null);
        }
    }

    public static void a(a aVar) {
        a = aVar;
    }
}
