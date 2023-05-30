package com.jingdong.aura.a.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import com.jingdong.aura.core.reflection.c;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class k extends c.a {
    public k(Context context) {
    }

    @Override // com.jingdong.aura.core.reflection.c.a, java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        ActivityInfo activityInfo;
        return (!method.getName().equals("getActivityInfo") || (activityInfo = (ActivityInfo) com.jingdong.aura.core.shadow.b.a().a((ComponentName) objArr[0], ActivityInfo.class)) == null) ? super.invoke(obj, method, objArr) : activityInfo;
    }
}
