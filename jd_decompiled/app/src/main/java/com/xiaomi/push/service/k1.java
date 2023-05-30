package com.xiaomi.push.service;

import com.xiaomi.push.c7;

/* loaded from: classes11.dex */
/* synthetic */ class k1 {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[c7.values().length];
        a = iArr;
        try {
            iArr[c7.Registration.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[c7.UnRegistration.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[c7.Subscription.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[c7.UnSubscription.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[c7.SendMessage.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[c7.AckMessage.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[c7.SetConfig.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[c7.ReportFeedback.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[c7.Notification.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[c7.Command.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
