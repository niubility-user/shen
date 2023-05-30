package com.jingdong.common.utils;

import android.content.res.Configuration;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class ConfigurationChangedManager {
    private CopyOnWriteArrayList<OnConfigurationChangeListener> onConfigurationChangeListeners;

    /* loaded from: classes6.dex */
    public interface OnConfigurationChangeListener {
        void onConfigurationChanged(Configuration configuration);
    }

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final ConfigurationChangedManager INSTANCE = new ConfigurationChangedManager();

        private SingletonHolder() {
        }
    }

    public static ConfigurationChangedManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addConfigurationChangeListener(OnConfigurationChangeListener onConfigurationChangeListener) {
        if (this.onConfigurationChangeListeners == null) {
            this.onConfigurationChangeListeners = new CopyOnWriteArrayList<>();
        }
        this.onConfigurationChangeListeners.add(onConfigurationChangeListener);
    }

    public void clearConfigurationChangeListeners() {
        CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList = this.onConfigurationChangeListeners;
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.clear();
    }

    public void notifyConfigurationChanged(Configuration configuration) {
        CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList = this.onConfigurationChangeListeners;
        if (copyOnWriteArrayList == null) {
            return;
        }
        int size = copyOnWriteArrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            OnConfigurationChangeListener onConfigurationChangeListener = this.onConfigurationChangeListeners.get(i2);
            if (onConfigurationChangeListener != null) {
                onConfigurationChangeListener.onConfigurationChanged(configuration);
            }
        }
    }

    public void removeConfigurationChangeListener(OnConfigurationChangeListener onConfigurationChangeListener) {
        CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList = this.onConfigurationChangeListeners;
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.remove(onConfigurationChangeListener);
    }

    private ConfigurationChangedManager() {
    }
}
