package com.jingdong.common.XView2.container;

import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes5.dex */
public class XView2LevelManager {
    public ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class XView2LevelManagerInstance {
        public static final XView2LevelManager INSTANCE = new XView2LevelManager();

        private XView2LevelManagerInstance() {
        }
    }

    public static XView2LevelManager getManager() {
        return XView2LevelManagerInstance.INSTANCE;
    }

    public void sortContainerLevel(View view, XView2BaseContainer xView2BaseContainer, ViewGroup.LayoutParams layoutParams) {
        boolean z;
        int indexOfChild;
        this.mLock.writeLock().lock();
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u9501\u5f00\u59cb");
        try {
            if (!(view instanceof ViewGroup)) {
                this.mLock.writeLock().unlock();
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u9501\u7ed3\u675f");
                return;
            }
            if (view != null && xView2BaseContainer != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= ((ViewGroup) view).getChildCount()) {
                        z = false;
                        break;
                    }
                    View childAt = ((ViewGroup) view).getChildAt(i2);
                    if (childAt instanceof XView2Container) {
                        XView2Container xView2Container = (XView2Container) childAt;
                        if (xView2BaseContainer.getZIndex() < xView2Container.getZIndex() && (indexOfChild = ((ViewGroup) view).indexOfChild(xView2Container)) != -1) {
                            ((ViewGroup) view).addView(xView2BaseContainer, indexOfChild, layoutParams);
                            z = true;
                            break;
                        }
                    }
                    i2++;
                }
                if (!z) {
                    ((ViewGroup) view).addView(xView2BaseContainer, layoutParams);
                }
            }
            this.mLock.writeLock().unlock();
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u9501\u7ed3\u675f");
        } catch (Throwable th) {
            this.mLock.writeLock().unlock();
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u9501\u7ed3\u675f");
            throw th;
        }
    }
}
