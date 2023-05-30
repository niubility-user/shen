package com.jingdong.discovertask.util;

import android.view.View;
import com.jingdong.discovertask.model.inter.SingleClick;
import java.lang.reflect.Method;
import l.a.a.b;
import l.a.a.d.a;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
/* loaded from: classes12.dex */
public class SingleClickAspect {
    private static final long DEFAULT_TIME_INTERVAL = 5000;

    @Around("methodAnnotated()")
    public void aroundJoinPoint(b bVar) throws Throwable {
        View view;
        Object[] args = bVar.getArgs();
        int length = args.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                view = null;
                break;
            }
            Object obj = args[i2];
            if (obj instanceof View) {
                view = (View) obj;
                break;
            }
            i2++;
        }
        if (view == null) {
            return;
        }
        Method method = ((a) bVar.getSignature()).getMethod();
        if (method.isAnnotationPresent(SingleClick.class) && !ClickUtil.isFastDoubleClick(view, ((SingleClick) method.getAnnotation(SingleClick.class)).value())) {
            bVar.proceed();
        }
    }

    @Pointcut("execution(@com.jingdong.discovertask.model.inter.SingleClick * *(..))")
    public void methodAnnotated() {
    }
}
