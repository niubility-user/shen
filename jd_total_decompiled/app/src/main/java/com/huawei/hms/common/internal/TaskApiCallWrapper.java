package com.huawei.hms.common.internal;

import g.e.c.a.g;

/* loaded from: classes12.dex */
public class TaskApiCallWrapper<TResult> extends BaseContentWrapper {
    private final TaskApiCall<? extends AnyClient, TResult> a;
    private final g<TResult> b;

    public TaskApiCallWrapper(TaskApiCall<? extends AnyClient, TResult> taskApiCall, g<TResult> gVar) {
        super(1);
        this.a = taskApiCall;
        this.b = gVar;
    }

    public TaskApiCall<? extends AnyClient, TResult> getTaskApiCall() {
        return this.a;
    }

    public g<TResult> getTaskCompletionSource() {
        return this.b;
    }
}
