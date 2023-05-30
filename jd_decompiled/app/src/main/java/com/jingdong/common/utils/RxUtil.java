package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;
import rx.Subscription;
import rx.functions.Action1;

/* loaded from: classes6.dex */
public final class RxUtil {
    private static final Action1<Throwable> LOGGING_ACTION = new Action1<Throwable>() { // from class: com.jingdong.common.utils.RxUtil.1
        @Override // rx.functions.Action1
        public void call(Throwable th) {
            if (OKLog.D) {
                OKLog.d(RxUtil.TAG, String.format("throwable:%s", th));
            }
        }
    };
    private static final String TAG = "om.jingdong.common.utils.RxUtil";

    private RxUtil() {
    }

    public static Action1<Throwable> loggingAction() {
        return LOGGING_ACTION;
    }

    public static void unSubscribeSafely(Subscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }
}
