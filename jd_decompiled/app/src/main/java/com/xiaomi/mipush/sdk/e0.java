package com.xiaomi.mipush.sdk;

import com.xiaomi.push.c7;

/* loaded from: classes11.dex */
/* synthetic */ class e0 {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[c7.values().length];
        a = iArr;
        try {
            iArr[c7.SendMessage.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[c7.Registration.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[c7.UnRegistration.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[c7.Subscription.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[c7.UnSubscription.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[c7.Command.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[c7.Notification.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
