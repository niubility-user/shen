package com.jingdong.service;

/* loaded from: classes10.dex */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ServiceException() {
    }

    public ServiceException(String str) {
        super(str);
    }

    public ServiceException(String str, Throwable th) {
        super(str, th);
    }

    public ServiceException(Throwable th) {
        super(th);
    }
}
