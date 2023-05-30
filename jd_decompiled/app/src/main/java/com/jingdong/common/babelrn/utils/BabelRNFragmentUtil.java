package com.jingdong.common.babelrn.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public class BabelRNFragmentUtil {
    private static BabelRNFragmentUtil mInstance;
    private IBridge mCurrentBridge;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes5.dex */
    public interface IBridge {
        void enableStatusBarTint(boolean z);
    }

    public static synchronized BabelRNFragmentUtil getInstance() {
        BabelRNFragmentUtil babelRNFragmentUtil;
        synchronized (BabelRNFragmentUtil.class) {
            if (mInstance == null) {
                mInstance = new BabelRNFragmentUtil();
            }
            babelRNFragmentUtil = mInstance;
        }
        return babelRNFragmentUtil;
    }

    public void enableStatusBarTint(final boolean z) {
        if (this.mCurrentBridge != null) {
            this.mHandler.post(new Runnable() { // from class: com.jingdong.common.babelrn.utils.BabelRNFragmentUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    if (BabelRNFragmentUtil.this.mCurrentBridge != null) {
                        BabelRNFragmentUtil.this.mCurrentBridge.enableStatusBarTint(z);
                    }
                }
            });
        }
    }

    public void onDestory() {
        this.mCurrentBridge = null;
        mInstance = null;
    }

    public void onResume(IBridge iBridge) {
        this.mCurrentBridge = iBridge;
    }
}
