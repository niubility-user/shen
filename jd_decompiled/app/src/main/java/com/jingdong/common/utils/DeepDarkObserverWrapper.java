package com.jingdong.common.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class DeepDarkObserverWrapper<T> implements Observer<T> {
    private int lastVersion;
    private LiveData<T> liveData;
    private Observer<T> observer;

    public DeepDarkObserverWrapper(Observer<T> observer, LiveData<T> liveData) {
        this.lastVersion = -1;
        this.observer = observer;
        this.liveData = liveData;
        this.lastVersion = getLiveDataVersion(liveData);
    }

    private int getLiveDataVersion(LiveData<T> liveData) {
        if (OKLog.D) {
            OKLog.d("DeepDarkChangeManager", "liveData=" + liveData);
        }
        if (liveData != null) {
            try {
                Field declaredField = LiveData.class.getDeclaredField("mVersion");
                declaredField.setAccessible(true);
                if (OKLog.D) {
                    OKLog.d("DeepDarkChangeManager", "getLiveDataVersion-" + declaredField.get(liveData));
                }
                return ((Integer) declaredField.get(liveData)).intValue();
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("DeepDarkChangeManager", "getLiveDataVersion-" + e2);
                }
            }
        }
        return -1;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(T t) {
        if (OKLog.D) {
            OKLog.d("DeepDarkChangeManager", "DeepDarkObserverWrapper-onChanged");
        }
        int liveDataVersion = getLiveDataVersion(this.liveData);
        if (OKLog.D) {
            OKLog.d("DeepDarkChangeManager", "onChanged-version=" + liveDataVersion + " lastVersion=" + this.lastVersion);
        }
        if (this.lastVersion >= liveDataVersion) {
            return;
        }
        this.lastVersion = liveDataVersion;
        Observer<T> observer = this.observer;
        if (observer != null) {
            observer.onChanged(t);
        }
    }
}
