package com.jingdong.common.network;

/* loaded from: classes5.dex */
public interface CommandCallBack<T> {
    void executeErrorCommand(T t);

    void executeSucCommand(T t);
}
