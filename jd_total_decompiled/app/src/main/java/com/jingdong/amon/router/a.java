package com.jingdong.amon.router;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

    /* JADX WARN: Removed duplicated region for block: B:105:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(Letter letter) {
        String action;
        Context from = letter.getFrom();
        Intent intent = new Intent(from, letter.getDestination());
        if (letter.getExtras() != null) {
            intent.putExtras(letter.getExtras());
        }
        int flags = letter.getFlags();
        if (-1 == flags) {
            if (!(from instanceof Activity)) {
                flags = 268435456;
            }
            action = letter.getAction();
            if (!TextUtils.isEmpty(action)) {
                intent.setAction(action);
            }
            if (letter.getRequestCode() <= 0) {
                if (Build.VERSION.SDK_INT >= 16) {
                    ((Activity) from).startActivityForResult(intent, letter.getRequestCode(), letter.getOptionsCompat());
                } else {
                    ((Activity) from).startActivityForResult(intent, letter.getRequestCode());
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                from.startActivity(intent, letter.getOptionsCompat());
            } else {
                from.startActivity(intent);
            }
            if (-1 != letter.getEnterAnim() && -1 != letter.getExitAnim() && (from instanceof Activity)) {
                ((Activity) from).overridePendingTransition(letter.getEnterAnim(), letter.getExitAnim());
            }
            if (letter.getOnCompleteCallback() == null) {
                letter.getOnCompleteCallback().onComplete(letter);
                return;
            }
            return;
        }
        intent.setFlags(flags);
        action = letter.getAction();
        if (!TextUtils.isEmpty(action)) {
        }
        if (letter.getRequestCode() <= 0) {
        }
        if (-1 != letter.getEnterAnim()) {
            ((Activity) from).overridePendingTransition(letter.getEnterAnim(), letter.getExitAnim());
        }
        if (letter.getOnCompleteCallback() == null) {
        }
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
