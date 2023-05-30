package com.jingdong.common.XView2.common;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.LocationEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XView2LayerObservableManager {
    private ArrayList<IXView2LayerObserver> mLayoutObserverList;
    private ConcurrentHashMap<String, IXView2LayerObserver> mLayoutObservers;

    /* loaded from: classes5.dex */
    public static class XView2Instance {
        public static final XView2LayerObservableManager INSTANCE = new XView2LayerObservableManager();

        private XView2Instance() {
        }
    }

    public static XView2LayerObservableManager getManager() {
        return XView2Instance.INSTANCE;
    }

    public void notifyAnimateEnd(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().layerAnimateEnd();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.layerAnimateEnd(str);
                    }
                }
            }
        }
    }

    public void notifyClickClose(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().clickClose();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.clickClose(str);
                    }
                }
            }
        }
    }

    public void notifyEndCloseAni(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || (key.equals(str2) && entry.getValue() != null)) {
                        entry.getValue().endCloseAni(str);
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.endCloseAni(str);
                    }
                }
            }
        }
    }

    public void notifyEndPopAni(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().endPopAni(str);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.endPopAni(str);
                    }
                }
            }
        }
    }

    public void notifyJumpClose(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().jumpClose();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.jumpClose(str);
                    }
                }
            }
        }
    }

    public boolean notifyLayerCanPopStatus(String str, JSONObject jSONObject) throws UnsupportedOperationException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey()) && entry.getKey().equals(str) && entry.getValue() != null) {
                    return entry.getValue().nxviewCanPopStatus(str, jSONObject);
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                for (int size = this.mLayoutObserverList.size() - 1; size >= 0; size--) {
                    IXView2LayerObserver iXView2LayerObserver = this.mLayoutObserverList.get(size);
                    if (iXView2LayerObserver != null) {
                        return iXView2LayerObserver.nxviewCanPopStatus(str, jSONObject);
                    }
                }
            }
            return true;
        }
    }

    public void notifyLayerClose(String str, String str2, @XView2Constants.CloseReason int i2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().layerClose(str, i2);
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, " entry.getValue().layerClose(observerKey, reason);" + str + i2);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.layerClose(str, i2);
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "listener.layerClose" + str + i2);
                    }
                }
            }
        }
    }

    public void notifyLayerShowError(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().layerShowError();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.layerShowError(str);
                    }
                }
            }
        }
    }

    public void notifyLayerShowSuccess(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().layerShowSuccess();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.layerShowSuccess(str);
                    }
                }
            }
        }
    }

    public LocationEntity notifyLocationObserverByType(String str, String str2, int i2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return null;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            return entry.getValue().getLocationsByType(i2);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        return next.getLocationsByType(str, i2);
                    }
                }
            }
            return null;
        }
    }

    public void notifyOnClick(String str, String str2, int i2, String str3) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().onClick(str, i2, str3);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.onClick(str, i2, str3);
                    }
                }
            }
        }
    }

    public LocationEntity notifyPopLocationObserver(String str, String str2, int i2, int i3) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return null;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            return entry.getValue().getPopLocation(i2, i3);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        return next.getPopLocation(str, i2, i3);
                    }
                }
            }
            return null;
        }
    }

    public void notifyRelease(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().layerRelease();
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.layerRelease(str);
                    }
                }
            }
        }
    }

    public void notifyStartCloseAni(String str, String str2, String str3, long j2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().startCloseAni(str, str3, j2);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.startCloseAni(str, str3, j2);
                    }
                }
            }
        }
    }

    public void notifyStartLoadingLayer(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().startLoadingLayer(str);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.startLoadingLayer(str);
                    }
                }
            }
        }
    }

    public void notifyStartPopAni(String str, String str2, String str3, long j2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        ConcurrentHashMap<String, IXView2LayerObserver> concurrentHashMap = this.mLayoutObservers;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
                if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                    String key = entry.getKey();
                    if (key.equals(str) || key.equals(str2)) {
                        if (entry.getValue() != null) {
                            entry.getValue().startPopAni(str, str3, j2);
                        }
                    }
                }
            }
        }
        synchronized (this.mLayoutObserverList) {
            if (!XView2Utils.isListEmpty(this.mLayoutObserverList)) {
                Iterator<IXView2LayerObserver> it = this.mLayoutObserverList.iterator();
                while (it.hasNext()) {
                    IXView2LayerObserver next = it.next();
                    if (next != null) {
                        next.startPopAni(str, str3, j2);
                    }
                }
            }
        }
    }

    public void registerXView2Observer(IXView2LayerObserver iXView2LayerObserver, String str) {
        if (iXView2LayerObserver == null || TextUtils.isEmpty(str)) {
            return;
        }
        for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
            if (entry != null && !TextUtils.isEmpty(entry.getKey()) && entry.getKey().equals(str)) {
                return;
            }
        }
        this.mLayoutObservers.put(str, iXView2LayerObserver);
    }

    public void unregisterXView2Observer(IXView2LayerObserver iXView2LayerObserver) {
        IXView2LayerObserver value;
        if (iXView2LayerObserver == null) {
            return;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "unregisterXView2Observer");
        for (Map.Entry<String, IXView2LayerObserver> entry : this.mLayoutObservers.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && (value = entry.getValue()) != null && value == iXView2LayerObserver) {
                this.mLayoutObservers.remove(iXView2LayerObserver);
            }
        }
        synchronized (this.mLayoutObserverList) {
            ArrayList<IXView2LayerObserver> arrayList = this.mLayoutObserverList;
            if (arrayList != null && arrayList.contains(iXView2LayerObserver)) {
                this.mLayoutObserverList.remove(iXView2LayerObserver);
            }
        }
    }

    private XView2LayerObservableManager() {
        this.mLayoutObservers = new ConcurrentHashMap<>();
        this.mLayoutObserverList = new ArrayList<>();
        this.mLayoutObservers = new ConcurrentHashMap<>();
        this.mLayoutObserverList = new ArrayList<>();
    }

    public void registerXView2Observer(IXView2LayerObserverV2 iXView2LayerObserverV2) {
        if (iXView2LayerObserverV2 == null) {
            return;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "registerXView2Observer");
        synchronized (this.mLayoutObserverList) {
            ArrayList<IXView2LayerObserver> arrayList = this.mLayoutObserverList;
            if (arrayList != null && !arrayList.contains(iXView2LayerObserverV2)) {
                this.mLayoutObserverList.add(iXView2LayerObserverV2);
            }
        }
    }
}
