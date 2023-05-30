package com.jd.lib.flexcube.iwidget.entity.material;

import android.os.Bundle;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class MaterialModel {
    public ClickEvent clickEvent;
    public ExposureInfo exposureInfo;
    public HashMap extraParam;
    public HashMap<String, String> flexData;
    public int p_position;
    private Bundle stateBundle;

    public Bundle getStateBundle() {
        if (this.stateBundle == null) {
            this.stateBundle = new Bundle();
        }
        return this.stateBundle;
    }
}
