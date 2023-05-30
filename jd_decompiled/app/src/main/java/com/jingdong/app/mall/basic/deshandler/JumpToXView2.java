package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Des(des = JumpUtil.XVIEW2_NXVIEW)
/* loaded from: classes19.dex */
public class JumpToXView2 extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        ConcurrentHashMap<Integer, XView2> concurrentHashMap;
        XView2 xView2;
        Log.d(this.a, "forwardXView2");
        Activity activity = (Activity) context;
        if (!(activity instanceof BaseActivity) || (concurrentHashMap = XView2Manager.getInstance().getConcurrentHashMap()) == null || concurrentHashMap.size() == 0 || (xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        try {
            Method method = XView2.class.getMethod("dispatchPop", Context.class, Bundle.class);
            if (method != null) {
                method.invoke(xView2, context, bundle);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
