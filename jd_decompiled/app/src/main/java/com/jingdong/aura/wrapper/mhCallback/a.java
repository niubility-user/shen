package com.jingdong.aura.wrapper.mhCallback;

import android.os.Message;

/* loaded from: classes4.dex */
public class a {
    private static a b = new a();
    private ImHCallBack a = new b();

    private a() {
    }

    public static a a() {
        return b;
    }

    public void a(ImHCallBack imHCallBack) {
        if (imHCallBack == null) {
            return;
        }
        this.a = imHCallBack;
    }

    public void a(Message message) {
        if (message == null) {
            return;
        }
        int i2 = message.what;
        if (i2 == 115) {
            this.a.onServiceArgs();
        } else if (i2 == 116) {
            this.a.onStopService();
        } else if (i2 != 137) {
            switch (i2) {
                case 101:
                    this.a.onPauseActivity();
                    return;
                case 102:
                    this.a.onPauseActivityFinishing();
                    return;
                case 103:
                    this.a.onStopActivityShow();
                    return;
                case 104:
                    this.a.onStopActivityHide();
                    return;
                default:
                    return;
            }
        } else {
            this.a.onSleeping();
        }
    }
}
