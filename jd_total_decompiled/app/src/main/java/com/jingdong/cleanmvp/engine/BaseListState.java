package com.jingdong.cleanmvp.engine;

import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes4.dex */
public class BaseListState<T> extends BaseState {
    public T data;
    public HttpError httpError;

    @Override // com.jingdong.cleanmvp.engine.BaseState
    public void clearState(int i2) {
    }
}
