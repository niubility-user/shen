package com.jd.viewkit.tool.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes18.dex */
public class CountdownTimerUtils {
    private static CountdownTimerUtils sTimerUtils;
    private InfinityCountDownTimer sCountdownTimer;
    private List<CountDownTask> sTaskList;

    private CountdownTimerUtils() {
    }

    static /* synthetic */ boolean access$000() {
        return taskListEmpty();
    }

    public static void addCountDownTask(CountDownTask countDownTask) {
        if (countDownTask == null) {
            return;
        }
        getInstance().addTask(countDownTask);
        startCountDownTimer();
    }

    public static synchronized CountdownTimerUtils getInstance() {
        CountdownTimerUtils countdownTimerUtils;
        synchronized (CountdownTimerUtils.class) {
            if (sTimerUtils == null) {
                sTimerUtils = new CountdownTimerUtils();
            }
            countdownTimerUtils = sTimerUtils;
        }
        return countdownTimerUtils;
    }

    public static void removeCountDownTask(CountDownTask countDownTask) {
        if (getInstance().sTaskList != null && countDownTask != null) {
            getInstance().sTaskList.remove(countDownTask);
        }
        if (!taskListEmpty() || getInstance().sCountdownTimer == null) {
            return;
        }
        getInstance().sCountdownTimer.cancel();
    }

    private static void startCountDownTimer() {
        if (taskListEmpty()) {
            return;
        }
        if (getInstance().sCountdownTimer == null) {
            getInstance().sCountdownTimer = new InfinityCountDownTimer() { // from class: com.jd.viewkit.tool.countdown.CountdownTimerUtils.1
                @Override // com.jd.viewkit.tool.countdown.InfinityCountDownTimer
                public void onTick(long j2) {
                    if (CountdownTimerUtils.access$000()) {
                        CountdownTimerUtils.getInstance().sCountdownTimer.cancel();
                    }
                    ArrayList<CountDownTask> arrayList = new ArrayList();
                    for (CountDownTask countDownTask : CountdownTimerUtils.getInstance().sTaskList) {
                        long j3 = countDownTask.mMillisInFuture - j2;
                        countDownTask.mMillisInFuture = j3;
                        if (j3 <= 0) {
                            countDownTask.mCountDownListener.finish();
                            arrayList.add(countDownTask);
                        } else {
                            countDownTask.mCountDownListener.changed(j3);
                        }
                    }
                    for (CountDownTask countDownTask2 : arrayList) {
                        if (CountdownTimerUtils.getInstance().sTaskList != null && countDownTask2 != null) {
                            CountdownTimerUtils.getInstance().sTaskList.remove(countDownTask2);
                        }
                    }
                }
            };
            getInstance().sCountdownTimer.start();
        } else if (getInstance().sCountdownTimer.isCancelled()) {
            getInstance().sCountdownTimer.start();
        }
    }

    private static boolean taskListEmpty() {
        return getInstance().sTaskList == null || getInstance().sTaskList.isEmpty();
    }

    public void addTask(CountDownTask countDownTask) {
        if (getInstance().sTaskList == null) {
            getInstance().sTaskList = new CopyOnWriteArrayList();
        }
        if (getInstance().sTaskList.contains(countDownTask)) {
            return;
        }
        getInstance().sTaskList.add(countDownTask);
    }
}
