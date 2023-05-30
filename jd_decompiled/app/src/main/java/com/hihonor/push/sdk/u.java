package com.hihonor.push.sdk;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.entity.RequestHeader;
import com.hihonor.push.sdk.common.data.ApiException;

/* loaded from: classes12.dex */
public abstract class u<TResult> {
    public j0<TResult> a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final IMessageEntity f1125c;
    public final x0 d;

    /* renamed from: e  reason: collision with root package name */
    public RequestHeader f1126e;

    public u(String str, IMessageEntity iMessageEntity) {
        this.b = str;
        this.f1125c = iMessageEntity;
        this.d = x0.b(str);
    }

    public abstract void a(ApiException apiException, Object obj);

    public final void b(ApiException apiException, Object obj) {
        if (this.a != null) {
            a(apiException, obj);
            return;
        }
        String str = "This Task has been canceled, uri:" + this.b;
    }
}
