package com.jingdong.common.broadcastReceiver;

import com.jingdong.common.BaseFrameUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class KillSelfManager {
    protected static final String TAG = "KillSelfManager";
    private static Timer killSelfTimer;
    private static Set<Object> synchronizTokenSet = new HashSet();

    public static synchronized Object StartKillSelfTimer() {
        Object obj;
        synchronized (KillSelfManager.class) {
            if (OKLog.D) {
                OKLog.d(TAG, " StartKillSelfTimer -->> ");
            }
            obj = new Object();
            synchronizTokenSet.add(obj);
            if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
                Timer timer = killSelfTimer;
                if (timer != null) {
                    timer.cancel();
                }
                Timer timer2 = new Timer();
                killSelfTimer = timer2;
                timer2.schedule(new TimerTask() { // from class: com.jingdong.common.broadcastReceiver.KillSelfManager.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (OKLog.D) {
                            OKLog.d(KillSelfManager.TAG, " StartKillSelfTimer -->> 60 s timeout kill!");
                        }
                        KillSelfManager.killSelfMethod();
                    }
                }, 60000L);
            }
        }
        return obj;
    }

    public static synchronized void killSelf(Object obj) {
        synchronized (KillSelfManager.class) {
            if (OKLog.D) {
                OKLog.d(TAG, " killSelf -->> token:" + obj + " synchronizTokenSet.size():" + synchronizTokenSet.size());
            }
            synchronizTokenSet.remove(obj);
            if (synchronizTokenSet.size() == 0) {
                killSelfMethod();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void killSelfMethod() {
        synchronized (KillSelfManager.class) {
            if (OKLog.D) {
                OKLog.d(TAG, " killSelfMethod -->> ");
            }
            Timer timer = killSelfTimer;
            if (timer != null) {
                timer.cancel();
                killSelfTimer = null;
            }
            new Thread() { // from class: com.jingdong.common.broadcastReceiver.KillSelfManager.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    setName(KillSelfManager.TAG);
                    BaseFrameUtil.killStageNoUI();
                }
            }.start();
        }
    }
}
