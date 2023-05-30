package com.jingdong.amon.router.template;

import com.jingdong.amon.router.callback.InterceptorCallback;
import com.jingdong.amon.router.module.Letter;

/* loaded from: classes18.dex */
public interface IInterceptor {
    void intercept(Letter letter, InterceptorCallback interceptorCallback);
}
