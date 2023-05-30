package com.jingdong.pdj.libcore.screen;

import android.graphics.Point;
import android.util.SparseIntArray;
import android.view.WindowManager;
import com.jingdong.pdj.libdjbasecore.app.BaseCoreHelper;
import com.jingdong.pdj.libdjbasecore.utils.DpiUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes7.dex */
public class HourlyGoDpi750 {
    public static volatile int H_HEIGHT = 0;
    public static volatile int H_WIDTH = 0;
    private static final int MAX_SIZE = 128;
    private static volatile int[] mCacheArr = new int[128];
    private static final ReadWriteLock mSizeLock = new ReentrantReadWriteLock();
    private static final SparseIntArray mCacheSize = new SparseIntArray();
    private static final ConcurrentHashMap<Class, IScreenChangeListener> mChangeListener = new ConcurrentHashMap<>();

    /* loaded from: classes7.dex */
    public interface IScreenChangeListener extends IWidthChanged {
        void clearCache();

        Class getSaveKey();
    }

    /* loaded from: classes7.dex */
    public interface IWidthChanged {
        void onScreenChanged(int i2);
    }

    static {
        screenWidthChanged(getScreen().x, getScreen().y);
    }

    static int get750SizeValue(int i2, int i3) {
        return (int) (((i3 * i2) / 750.0f) + 0.5f);
    }

    public static Point getScreen() {
        try {
            Object systemService = BaseCoreHelper.getInstance().getApplication().getSystemService("window");
            if (systemService instanceof WindowManager) {
                Point point2 = new Point();
                ((WindowManager) systemService).getDefaultDisplay().getSize(point2);
                return point2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return new Point(DpiUtil.getWidth(BaseCoreHelper.getInstance().getApplication()), DpiUtil.getHeight(BaseCoreHelper.getInstance().getApplication()));
    }

    public static int getSizeBy750(int i2) {
        ReadWriteLock readWriteLock;
        Lock writeLock;
        try {
            try {
                readWriteLock = mSizeLock;
                readWriteLock.readLock().lock();
                int i3 = (i2 >= 128 || i2 <= 0) ? mCacheSize.get(i2) : mCacheArr[i2];
                if (i3 > 0) {
                    readWriteLock.readLock().unlock();
                    return i3;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                readWriteLock = mSizeLock;
            }
            readWriteLock.readLock().unlock();
            int i4 = get750SizeValue(i2, H_WIDTH);
            try {
                try {
                    ReadWriteLock readWriteLock2 = mSizeLock;
                    readWriteLock2.writeLock().lock();
                    if (i2 < 128 && i2 > 0) {
                        mCacheArr[i2] = i4;
                    } else {
                        mCacheSize.put(i2, i4);
                    }
                    writeLock = readWriteLock2.writeLock();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    writeLock = mSizeLock.writeLock();
                }
                writeLock.unlock();
                return i4;
            } catch (Throwable th) {
                mSizeLock.writeLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            mSizeLock.readLock().unlock();
            throw th2;
        }
    }

    private static void notifyWidthChanged(int i2) {
        for (Map.Entry<Class, IScreenChangeListener> entry : mChangeListener.entrySet()) {
            if (entry != null) {
                entry.getValue().onScreenChanged(i2);
            }
        }
    }

    public static boolean screenWidthChanged(int i2, int i3) {
        if (i2 == H_WIDTH && i3 == H_HEIGHT) {
            return false;
        }
        try {
            ReadWriteLock readWriteLock = mSizeLock;
            readWriteLock.writeLock().lock();
            boolean z = i2 != H_WIDTH;
            H_WIDTH = i2;
            if (i2 == DpiUtil.getWidth(BaseCoreHelper.getInstance().getApplicationContext())) {
                i3 = DpiUtil.getHeight(BaseCoreHelper.getInstance().getApplicationContext());
            }
            H_HEIGHT = i3;
            if (z) {
                mCacheArr = new int[128];
                mCacheSize.clear();
                notifyWidthChanged(i2);
            }
            readWriteLock.writeLock().unlock();
            return z;
        } catch (Throwable th) {
            mSizeLock.writeLock().unlock();
            throw th;
        }
    }
}
