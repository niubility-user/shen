package com.tencent.smtt.utils;

import android.os.Handler;
import android.os.HandlerThread;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class m implements Runnable {
    public static String a = "TBSFileLock";

    /* renamed from: f  reason: collision with root package name */
    private static Object f17972f = new Object();

    /* renamed from: g  reason: collision with root package name */
    private static Object f17973g = new Object();

    /* renamed from: h  reason: collision with root package name */
    private static HashMap<m, Object> f17974h;

    /* renamed from: i  reason: collision with root package name */
    private static Handler f17975i;
    File b;

    /* renamed from: c  reason: collision with root package name */
    RandomAccessFile f17976c = null;
    FileLock d = null;

    /* renamed from: e  reason: collision with root package name */
    long f17977e = 0;

    public m(File file, String str) {
        this.b = null;
        this.b = new File(file, OrderISVUtil.MONEY_DECIMAL + str + ".lock");
    }

    Handler a() {
        if (f17975i == null) {
            synchronized (m.class) {
                if (f17975i == null) {
                    HandlerThread handlerThread = new HandlerThread("QBFileLock.Thread");
                    handlerThread.start();
                    f17975i = new Handler(handlerThread.getLooper());
                }
            }
        }
        return f17975i;
    }

    public synchronized void a(boolean z) {
        String str = ">>> release lock: " + this.b.getName();
        FileLock fileLock = this.d;
        if (fileLock != null) {
            try {
                fileLock.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.d = null;
        }
        RandomAccessFile randomAccessFile = this.f17976c;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f17976c = null;
        }
        Handler handler = f17975i;
        if (handler != null && this.f17977e > 0) {
            handler.removeCallbacks(this);
        }
        if (z) {
            d();
        }
    }

    public synchronized void b() {
        FileChannel channel;
        try {
            this.f17976c = new RandomAccessFile(this.b, "rw");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        RandomAccessFile randomAccessFile = this.f17976c;
        if (randomAccessFile != null && (channel = randomAccessFile.getChannel()) != null) {
            if (this.f17977e > 0) {
                a().postDelayed(this, this.f17977e);
            }
            FileLock fileLock = null;
            long currentTimeMillis = System.currentTimeMillis();
            do {
                try {
                    fileLock = channel.lock();
                    if (fileLock != null) {
                        break;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
            } while (Math.abs(System.currentTimeMillis() - currentTimeMillis) < 1000);
            this.d = fileLock;
            String str = ">>> lock [" + this.b.getName() + "] cost: " + (System.currentTimeMillis() - currentTimeMillis);
        }
        if (this.d != null) {
            c();
        }
    }

    void c() {
        synchronized (f17973g) {
            if (f17974h == null) {
                f17974h = new HashMap<>();
            }
            f17974h.put(this, f17972f);
        }
    }

    void d() {
        synchronized (f17973g) {
            HashMap<m, Object> hashMap = f17974h;
            if (hashMap == null) {
                return;
            }
            hashMap.remove(this);
        }
    }

    public void e() {
        a(true);
    }

    @Override // java.lang.Runnable
    public void run() {
        e();
    }
}
