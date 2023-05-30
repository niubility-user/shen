package com.jingdong.amon.router;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.amon.router.annotation.RouteMethod;
import com.jingdong.amon.router.callback.InterceptorCallback;
import com.jingdong.amon.router.module.DynamicLetter;
import com.jingdong.amon.router.module.Letter;
import com.jingdong.amon.router.module.MethodLetter;
import com.jingdong.amon.router.module.RouteMeta;
import com.jingdong.amon.router.template.IInterceptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class a {
    public static String a = "";
    public static String b = "";

    /* renamed from: c */
    private static volatile boolean f7581c;
    private static volatile a d;

    a() {
    }

    public static a a() {
        if (f7581c) {
            if (d == null) {
                synchronized (a.class) {
                    if (d == null) {
                        d = new a();
                    }
                }
            }
            return d;
        }
        throw new RuntimeException("AmonRouter \u672a\u521d\u59cb\u5316!");
    }

    private Object a(DynamicLetter dynamicLetter, final Letter letter) {
        if (letter != null && dynamicLetter != null) {
            letter.applyDynamicLetter(dynamicLetter);
            boolean z = false;
            if (dynamicLetter.getUseIntercept() >= 0 ? dynamicLetter.getUseIntercept() == 1 : com.jingdong.amon.router.a.a.a()) {
                z = true;
            }
            if (z) {
                a(letter, b.b().iterator(), new InterceptorCallback() { // from class: com.jingdong.amon.router.a.2
                    {
                        a.this = this;
                    }

                    @Override // com.jingdong.amon.router.callback.InterceptorCallback
                    public void onContinue(Letter letter2) {
                        a.this.b(letter2);
                    }

                    @Override // com.jingdong.amon.router.callback.InterceptorCallback
                    public void onInterrupt(Throwable th) {
                        if (letter.getOnInterruptCallback() != null) {
                            letter.getOnInterruptCallback().onInterrupt(letter);
                        }
                    }
                });
            } else {
                b(letter);
            }
        }
        return null;
    }

    private Object a(Object obj, MethodLetter methodLetter) {
        Method method;
        try {
            Method[] methods = obj.getClass().getMethods();
            ArrayList arrayList = new ArrayList();
            int length = methods.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    method = null;
                    break;
                }
                method = methods[i2];
                RouteMethod routeMethod = (RouteMethod) method.getAnnotation(RouteMethod.class);
                if (routeMethod != null) {
                    String name = routeMethod.name();
                    if (!TextUtils.isEmpty(name) && name.equals(methodLetter.getMethodName())) {
                        break;
                    }
                }
                if (methodLetter.getMethodName().equals(method.getName())) {
                    arrayList.add(method);
                }
                i2++;
            }
            if (method == null && arrayList.size() <= 0) {
                if (methodLetter.getOnErrorCallback() != null) {
                    methodLetter.getOnErrorCallback().onError(methodLetter, new Exception(String.format("can not find method[%s] in class [%s]", methodLetter.getMethodName(), obj.getClass().getSimpleName())));
                }
                return null;
            }
            if (method == null) {
                method = (Method) arrayList.get(0);
            }
            Object invoke = method.invoke(obj, methodLetter.getParameters());
            if (methodLetter.getOnCompleteCallback() != null) {
                methodLetter.getOnCompleteCallback().onComplete(methodLetter);
            }
            return invoke;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (methodLetter.getOnErrorCallback() != null) {
                methodLetter.getOnErrorCallback().onError(methodLetter, e2);
            }
            return null;
        }
    }

    public static void a(Application application, String str, String str2) {
        if (f7581c) {
            return;
        }
        a = str;
        b = str2;
        b.a();
        com.jingdong.amon.router.b.a.a(application);
        f7581c = true;
    }

    public void a(Letter letter, final Iterator<IInterceptor> it, final InterceptorCallback interceptorCallback) {
        if (it.hasNext()) {
            it.next().intercept(letter, new InterceptorCallback() { // from class: com.jingdong.amon.router.a.3
                {
                    a.this = this;
                }

                @Override // com.jingdong.amon.router.callback.InterceptorCallback
                public void onContinue(Letter letter2) {
                    a.this.a(letter2, it, interceptorCallback);
                }

                @Override // com.jingdong.amon.router.callback.InterceptorCallback
                public void onInterrupt(Throwable th) {
                    interceptorCallback.onInterrupt(th);
                }
            });
        } else {
            interceptorCallback.onContinue(letter);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(com.jingdong.amon.router.module.Letter r7) {
        /*
            r6 = this;
            android.content.Context r0 = r7.getFrom()
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class r2 = r7.getDestination()
            r1.<init>(r0, r2)
            android.os.Bundle r2 = r7.getExtras()
            if (r2 == 0) goto L1a
            android.os.Bundle r2 = r7.getExtras()
            r1.putExtras(r2)
        L1a:
            int r2 = r7.getFlags()
            r3 = -1
            if (r3 == r2) goto L25
        L21:
            r1.setFlags(r2)
            goto L2c
        L25:
            boolean r2 = r0 instanceof android.app.Activity
            if (r2 != 0) goto L2c
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L21
        L2c:
            java.lang.String r2 = r7.getAction()
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L39
            r1.setAction(r2)
        L39:
            int r2 = r7.getRequestCode()
            r4 = 16
            if (r2 <= 0) goto L5f
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 < r4) goto L54
            r2 = r0
            android.app.Activity r2 = (android.app.Activity) r2
            int r4 = r7.getRequestCode()
            android.os.Bundle r5 = r7.getOptionsCompat()
            r2.startActivityForResult(r1, r4, r5)
            goto L6e
        L54:
            r2 = r0
            android.app.Activity r2 = (android.app.Activity) r2
            int r4 = r7.getRequestCode()
            r2.startActivityForResult(r1, r4)
            goto L6e
        L5f:
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 < r4) goto L6b
            android.os.Bundle r2 = r7.getOptionsCompat()
            r0.startActivity(r1, r2)
            goto L6e
        L6b:
            r0.startActivity(r1)
        L6e:
            int r1 = r7.getEnterAnim()
            if (r3 == r1) goto L8b
            int r1 = r7.getExitAnim()
            if (r3 == r1) goto L8b
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L8b
            android.app.Activity r0 = (android.app.Activity) r0
            int r1 = r7.getEnterAnim()
            int r2 = r7.getExitAnim()
            r0.overridePendingTransition(r1, r2)
        L8b:
            com.jingdong.amon.router.callback.NavigationCallback$OnCompleteCallback r0 = r7.getOnCompleteCallback()
            if (r0 == 0) goto L98
            com.jingdong.amon.router.callback.NavigationCallback$OnCompleteCallback r0 = r7.getOnCompleteCallback()
            r0.onComplete(r7)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.amon.router.a.b(com.jingdong.amon.router.module.Letter):void");
    }

    public Letter a(Context context, String str) {
        return new Letter(context, str);
    }

    public MethodLetter a(String str, String str2) {
        return new MethodLetter(str, str2);
    }

    Object a(Uri uri) {
        return com.jingdong.amon.router.b.a.a(uri);
    }

    public Object a(final Letter letter) {
        DynamicLetter a2 = com.jingdong.amon.router.a.b.a(com.jingdong.amon.router.a.b.a(letter.getUri()));
        if (a2 != null) {
            return a(a2, letter);
        }
        RouteMeta a3 = b.a(letter.getUri());
        if (a3 == null) {
            if (letter.getOnLostCallBack() != null) {
                letter.getOnLostCallBack().onLost(letter);
            }
            return null;
        }
        letter.setDestination(a3.annotatedClass);
        if (letter.getOnFoundCallback() != null) {
            letter.getOnFoundCallback().onFound(letter);
        }
        a(letter, b.b().iterator(), new InterceptorCallback() { // from class: com.jingdong.amon.router.a.1
            {
                a.this = this;
            }

            @Override // com.jingdong.amon.router.callback.InterceptorCallback
            public void onContinue(Letter letter2) {
                a.this.b(letter2);
            }

            @Override // com.jingdong.amon.router.callback.InterceptorCallback
            public void onInterrupt(Throwable th) {
                if (letter.getOnInterruptCallback() != null) {
                    letter.getOnInterruptCallback().onInterrupt(letter);
                }
            }
        });
        return null;
    }

    public Object a(MethodLetter methodLetter) {
        Object a2 = a(methodLetter.getUri());
        if (a2 == null) {
            if (methodLetter.getOnErrorCallback() != null) {
                methodLetter.getOnErrorCallback().onError(methodLetter, new Exception("can not find service:" + methodLetter.getUri().toString()));
                return null;
            }
            return null;
        }
        return a(a2, methodLetter);
    }

    public <T> T a(Class<T> cls) {
        return (T) com.jingdong.amon.router.b.a.a(cls);
    }

    public <T> T a(Class<T> cls, String str) {
        return (T) com.jingdong.amon.router.b.a.a((Class) cls, Uri.parse(str));
    }

    public Object a(String str) {
        return com.jingdong.amon.router.b.a.a(Uri.parse(str));
    }

    public <T> void a(Class<T> cls, T t) {
        com.jingdong.amon.router.b.a.a(cls, t);
    }

    public Class b(String str) {
        return com.jingdong.amon.router.b.a.b(Uri.parse(str));
    }
}
