package com.jingdong.common.XView2.utils.log;

import com.jingdong.common.XView2.utils.log.floatview.XViewFloatManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class XViewLogProxy implements InvocationHandler {
    private Object obj;

    public XViewLogProxy(Object obj) {
        this.obj = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (objArr.length > 1) {
            for (Object obj2 : (Object[]) objArr[1]) {
                XViewFloatManager.getInstance().showXViewLog(String.valueOf(obj2));
            }
        }
        return method.invoke(this.obj, objArr);
    }
}
