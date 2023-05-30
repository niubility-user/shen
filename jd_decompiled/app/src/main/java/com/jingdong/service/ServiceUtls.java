package com.jingdong.service;

/* loaded from: classes10.dex */
public class ServiceUtls {
    public static void asset(PointServiceEnum pointServiceEnum) {
        if (pointServiceEnum.getNecessary()) {
            throw new ServiceException(pointServiceEnum.getName() + " is not register ");
        }
    }
}
