package com.jingdong.service;

/* loaded from: classes10.dex */
public class BaseService implements Service {
    public BaseService() {
    }

    public BaseService(Point point2) {
        if (point2 != null) {
            point2.registerService(this);
        }
    }
}
