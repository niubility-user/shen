package com.jdcloud.vsr.visual;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes18.dex */
public class Animator {
    public static final int INVALID_TICKET = -123456789;
    private static final long TIME_TOLERANCE = 3;
    private static Animator instance;
    private static final Object lock = new Object();
    private final Thread thread;
    private Map<Integer, Event> events = new HashMap();
    private Map<Integer, Long> eventTimes = new HashMap();
    private boolean threadRunning = true;
    private int ticketCounter = 0;
    private long nextWakeUpTime = 0;

    /* loaded from: classes18.dex */
    public static abstract class Event {
        public abstract long run();
    }

    private Animator() {
        Thread thread = new Thread(new Runnable() { // from class: com.jdcloud.vsr.visual.Animator.1
            @Override // java.lang.Runnable
            public void run() {
                while (Animator.this.threadRunning) {
                    try {
                        Animator.this.threadBody();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }, "jdtvsr display animator");
        this.thread = thread;
        thread.start();
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static Animator getInstance() {
        Animator animator;
        synchronized (lock) {
            if (instance == null) {
                instance = new Animator();
            }
            animator = instance;
        }
        return animator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void threadBody() throws InterruptedException {
        if (this.events.isEmpty()) {
            synchronized (this.thread) {
                this.thread.wait();
            }
        }
        Event event = null;
        int i2 = INVALID_TICKET;
        long currentTime = getCurrentTime();
        long j2 = Long.MAX_VALUE;
        synchronized (lock) {
            Iterator<Map.Entry<Integer, Long>> it = this.eventTimes.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Integer, Long> next = it.next();
                long longValue = next.getValue().longValue();
                j2 = Math.min(j2, longValue);
                if (longValue <= currentTime + 3) {
                    i2 = next.getKey().intValue();
                    event = this.events.get(Integer.valueOf(i2));
                    break;
                }
            }
        }
        if (event == null) {
            this.nextWakeUpTime = j2;
            long currentTime2 = j2 - getCurrentTime();
            if (currentTime2 > 0) {
                synchronized (this.thread) {
                    this.thread.wait(currentTime2);
                }
                return;
            }
            return;
        }
        long run = event.run();
        while (0 <= run && run <= 3) {
            run = event.run();
        }
        synchronized (lock) {
            if (run > 0) {
                this.eventTimes.put(Integer.valueOf(i2), Long.valueOf(getCurrentTime() + run));
            } else {
                this.events.remove(Integer.valueOf(i2));
                this.eventTimes.remove(Integer.valueOf(i2));
            }
        }
    }

    public boolean cancel(Event event, int i2) {
        if (i2 == -123456789 || event == null) {
            return false;
        }
        synchronized (lock) {
            if (this.events.remove(Integer.valueOf(i2)) == event) {
                this.eventTimes.remove(Integer.valueOf(i2));
                return true;
            }
            return false;
        }
    }

    public int planify(Event event, int i2) {
        int i3;
        long currentTime = getCurrentTime();
        if (i2 >= 0) {
            synchronized (lock) {
                long j2 = i2 + currentTime;
                this.events.put(Integer.valueOf(this.ticketCounter), event);
                this.eventTimes.put(Integer.valueOf(this.ticketCounter), Long.valueOf(j2));
                long j3 = this.nextWakeUpTime;
                if (j2 < j3 || j3 < currentTime) {
                    synchronized (this.thread) {
                        this.thread.notify();
                    }
                }
                i3 = this.ticketCounter;
                this.ticketCounter = i3 + 1;
            }
            return i3;
        }
        return INVALID_TICKET;
    }
}
