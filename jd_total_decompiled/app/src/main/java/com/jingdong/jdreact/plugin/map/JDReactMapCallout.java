package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes13.dex */
public class JDReactMapCallout extends ReactViewGroup {
    public int height;
    private boolean tooltip;
    public int width;

    public JDReactMapCallout(Context context) {
        super(context);
        this.tooltip = false;
    }

    public boolean getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(boolean z) {
        this.tooltip = z;
    }
}
