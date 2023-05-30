package com.jingdong.jdma.c;

import android.text.TextUtils;
import com.jingdong.jdma.common.utils.LogUtil;

/* loaded from: classes12.dex */
public final class c {
    public void a(com.jingdong.jdma.bean.b.a aVar, a aVar2) {
        if (aVar != null && aVar2 != null) {
            if (TextUtils.isEmpty(aVar.h())) {
                if (LogUtil.isDebug()) {
                    LogUtil.e("http request's url is null");
                }
            } else if (TextUtils.isEmpty(aVar.f())) {
                if (LogUtil.isDebug()) {
                    LogUtil.e("http request's method is null");
                }
            } else {
                aVar.a(9000);
                aVar.b(5000);
                b bVar = new b();
                String f2 = aVar.f();
                f2.hashCode();
                if (!f2.equals("GET")) {
                    bVar.b(aVar, aVar2);
                } else {
                    bVar.a(aVar, aVar2);
                }
            }
        } else if (LogUtil.isDebug()) {
            LogUtil.e("JDMAHttpClient execute method's  requestParams param is null,or jdHttpCallback param is null");
        }
    }
}
