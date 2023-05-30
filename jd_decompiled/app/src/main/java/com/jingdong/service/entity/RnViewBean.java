package com.jingdong.service.entity;

import android.os.Bundle;
import java.io.Serializable;

/* loaded from: classes10.dex */
public class RnViewBean implements Serializable {
    private Bundle initProps;
    private boolean loaded;
    private String moduleName;

    public RnViewBean(String str, Bundle bundle) {
        this.moduleName = str;
        this.initProps = bundle;
    }

    public Bundle getInitProps() {
        return this.initProps;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public boolean isLoaded() {
        return this.loaded;
    }
}
