package com.jingdong.jdsdk.utils;

import java.io.Serializable;

/* loaded from: classes14.dex */
public class SerializableContainer implements Serializable {
    private URLParamMap map;

    public URLParamMap getMap() {
        return this.map;
    }

    public void setMap(URLParamMap uRLParamMap) {
        this.map = uRLParamMap;
    }

    public String toString() {
        if (("SerializableContainer[map:" + this.map) == null) {
            return "";
        }
        return this.map.toString() + "]";
    }
}
