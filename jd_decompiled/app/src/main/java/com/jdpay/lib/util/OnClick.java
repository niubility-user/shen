package com.jdpay.lib.util;

import android.view.View;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes18.dex */
public class OnClick {
    public static long CLICK_DURATION = 2000;

    public static View.OnClickListener create(final View.OnClickListener onClickListener) {
        return (View.OnClickListener) Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() { // from class: com.jdpay.lib.util.OnClick.1
            long lastTimestimp;

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (!method.getName().equals("onClick")) {
                    return method.invoke(onClickListener, objArr);
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.lastTimestimp >= OnClick.CLICK_DURATION) {
                    this.lastTimestimp = currentTimeMillis;
                    return method.invoke(onClickListener, objArr);
                }
                return null;
            }
        });
    }
}
