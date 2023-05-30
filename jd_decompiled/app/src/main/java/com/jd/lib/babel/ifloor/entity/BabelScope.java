package com.jd.lib.babel.ifloor.entity;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.jd.lib.babel.ifloor.ui.IFloorUI;
import com.jd.lib.babel.ifloor.utils.FlexActionListener;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes13.dex */
public class BabelScope {
    private ArrayList<FlexActionListener> actionListeners;
    public IFloorUI iFloorUI;
    public boolean isDark;
    private Map<Class<?>, Object> mServices = new ArrayMap();
    public PageInfo pageInfo;

    public boolean addActionListener(FlexActionListener flexActionListener) {
        if (flexActionListener == null) {
            return false;
        }
        if (this.actionListeners == null) {
            this.actionListeners = new ArrayList<>();
        }
        if (this.actionListeners.contains(flexActionListener)) {
            return false;
        }
        return this.actionListeners.add(flexActionListener);
    }

    public void clearDeepDarkChangeListeners() {
        ArrayList<FlexActionListener> arrayList = this.actionListeners;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public String getPageName() {
        PageInfo pageInfo = this.pageInfo;
        return pageInfo != null ? pageInfo.pageName : "";
    }

    public String getPageParam() {
        PageInfo pageInfo = this.pageInfo;
        return pageInfo != null ? pageInfo.pageParam : "";
    }

    public <S> S getService(Class<S> cls) {
        Object obj;
        if (cls == null || (obj = this.mServices.get(cls)) == null) {
            return null;
        }
        return cls.cast(obj);
    }

    public boolean isDark() {
        return this.isDark;
    }

    public <S> void register(@NonNull Class<S> cls, @NonNull S s) {
        this.mServices.put(cls, cls.cast(s));
    }

    public boolean removeActionListener(FlexActionListener flexActionListener) {
        ArrayList<FlexActionListener> arrayList = this.actionListeners;
        return (arrayList == null || flexActionListener == null || !arrayList.remove(flexActionListener)) ? false : true;
    }

    public void setDark(boolean z) {
        this.isDark = z;
        ArrayList<FlexActionListener> arrayList = this.actionListeners;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        for (int size = this.actionListeners.size() - 1; size >= 0; size--) {
            FlexActionListener flexActionListener = this.actionListeners.get(size);
            if (flexActionListener != null) {
                try {
                    flexActionListener.onDarkModeChange(this.isDark);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
