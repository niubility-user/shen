package com.jd.libs.hybrid.offlineload.loader;

/* loaded from: classes16.dex */
public class OfflineEntityLoader {

    /* loaded from: classes16.dex */
    public interface Callback<T> {

        /* loaded from: classes16.dex */
        public static class NotFoundException extends Exception {
        }

        void onFail(Exception exc);

        void onSuccess(T t);
    }
}
