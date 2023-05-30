package com.jdjr.risk.device.c;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes18.dex */
public class q {
    private static List<String> a = new ArrayList();
    private static ReadWriteLock b = new ReentrantReadWriteLock();

    public static void a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                DisplayManager.DisplayListener displayListener = new DisplayManager.DisplayListener() { // from class: com.jdjr.risk.device.c.q.1
                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayAdded(int i2) {
                        q.b.writeLock().lock();
                        try {
                            if (q.a.size() > 10) {
                                q.a.remove(0);
                            }
                            q.a.add("1," + System.currentTimeMillis() + ";");
                        } catch (Throwable unused) {
                        }
                        q.b.writeLock().unlock();
                    }

                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayChanged(int i2) {
                    }

                    @Override // android.hardware.display.DisplayManager.DisplayListener
                    public void onDisplayRemoved(int i2) {
                        q.b.writeLock().lock();
                        try {
                            if (q.a.size() > 10) {
                                q.a.remove(0);
                            }
                            q.a.add("0," + System.currentTimeMillis() + ";");
                        } catch (Throwable unused) {
                        }
                        q.b.writeLock().unlock();
                    }
                };
                HandlerThread handlerThread = new HandlerThread("jr-risk-displayListener");
                handlerThread.start();
                ((DisplayManager) context.getSystemService(ViewProps.DISPLAY)).registerDisplayListener(displayListener, new Handler(handlerThread.getLooper()));
            }
        } catch (Throwable unused) {
        }
    }
}
