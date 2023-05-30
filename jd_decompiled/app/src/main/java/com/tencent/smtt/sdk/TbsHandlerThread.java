package com.tencent.smtt.sdk;

import android.os.HandlerThread;

/* loaded from: classes9.dex */
public class TbsHandlerThread extends HandlerThread {
    private static TbsHandlerThread a;

    public TbsHandlerThread(String str) {
        super(str);
    }

    public static synchronized TbsHandlerThread getInstance() {
        TbsHandlerThread tbsHandlerThread;
        synchronized (TbsHandlerThread.class) {
            if (a == null) {
                TbsHandlerThread tbsHandlerThread2 = new TbsHandlerThread("TbsHandlerThread");
                a = tbsHandlerThread2;
                tbsHandlerThread2.start();
            }
            tbsHandlerThread = a;
        }
        return tbsHandlerThread;
    }
}
