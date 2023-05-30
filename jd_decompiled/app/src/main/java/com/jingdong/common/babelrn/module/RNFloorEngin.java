package com.jingdong.common.babelrn.module;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.babelrn.BabelRNManager;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class RNFloorEngin {
    private static RNFloorEngin mInstance;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ConcurrentHashMap<String, IRNFloorBridge> mHashMap = new ConcurrentHashMap<>();

    /* loaded from: classes5.dex */
    public interface IRNFloorBridge {
        BabelRNManager getBabelRNManager();

        void setFloorHeight(int i2, int i3);

        void setForceRecreate(boolean z);

        void updateScreenSize();
    }

    public static synchronized RNFloorEngin getInstance() {
        RNFloorEngin rNFloorEngin;
        synchronized (RNFloorEngin.class) {
            if (mInstance == null) {
                mInstance = new RNFloorEngin();
            }
            rNFloorEngin = mInstance;
        }
        return rNFloorEngin;
    }

    public void forceAllRecreate() {
        Iterator<String> it = this.mHashMap.keySet().iterator();
        while (it.hasNext()) {
            IRNFloorBridge iRNFloorBridge = this.mHashMap.get(it.next());
            if (iRNFloorBridge != null) {
                iRNFloorBridge.setForceRecreate(true);
            }
        }
    }

    public void onDestory() {
        this.mHashMap.clear();
        mInstance = null;
    }

    public void refreshPage(String str) {
        final BabelRNManager babelRNManager;
        IRNFloorBridge iRNFloorBridge = this.mHashMap.get(str);
        if (iRNFloorBridge == null || (babelRNManager = iRNFloorBridge.getBabelRNManager()) == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.jingdong.common.babelrn.module.RNFloorEngin.1
            @Override // java.lang.Runnable
            public void run() {
                babelRNManager.onRefresh();
            }
        });
    }

    public void regiseter(String str, IRNFloorBridge iRNFloorBridge) {
        this.mHashMap.put(str, iRNFloorBridge);
    }

    public void setFloorHeight(String str, int i2, int i3) {
        IRNFloorBridge iRNFloorBridge = this.mHashMap.get(str);
        if (iRNFloorBridge != null) {
            iRNFloorBridge.setFloorHeight(i2, i3);
        }
    }

    public void updateAllScreenSize() {
        Iterator<String> it = this.mHashMap.keySet().iterator();
        while (it.hasNext()) {
            IRNFloorBridge iRNFloorBridge = this.mHashMap.get(it.next());
            if (iRNFloorBridge != null) {
                iRNFloorBridge.updateScreenSize();
            }
        }
    }
}
