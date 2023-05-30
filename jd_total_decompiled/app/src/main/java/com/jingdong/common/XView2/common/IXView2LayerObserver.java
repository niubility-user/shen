package com.jingdong.common.XView2.common;

import com.jingdong.common.XView2.entity.LocationEntity;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class IXView2LayerObserver {
    public abstract void clickClose();

    public void clickClose(String str) {
    }

    public void endCloseAni(String str) {
    }

    public void endPopAni(String str) {
    }

    public LocationEntity getLocationsByType(int i2) {
        return null;
    }

    public LocationEntity getLocationsByType(String str, int i2) {
        return null;
    }

    public LocationEntity getPopLocation(int i2, int i3) {
        return null;
    }

    public LocationEntity getPopLocation(String str, int i2, int i3) {
        return null;
    }

    public abstract void jumpClose();

    public void jumpClose(String str) {
    }

    public abstract void layerAnimateEnd();

    public void layerAnimateEnd(String str) {
    }

    public void layerClose(String str, int i2) {
    }

    public abstract void layerRelease();

    public void layerRelease(String str) {
    }

    public abstract void layerShowError();

    public void layerShowError(String str) {
    }

    public abstract void layerShowSuccess();

    public void layerShowSuccess(String str) {
    }

    public boolean nxviewCanPopStatus(String str, JSONObject jSONObject) {
        return true;
    }

    public void onClick(String str, int i2, String str2) {
    }

    public void startCloseAni(String str, String str2, long j2) {
    }

    public void startLoadingLayer(String str) {
    }

    public void startPopAni(String str, String str2, long j2) {
    }
}
