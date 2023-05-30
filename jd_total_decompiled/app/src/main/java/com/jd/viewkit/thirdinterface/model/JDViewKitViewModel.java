package com.jd.viewkit.thirdinterface.model;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;

/* loaded from: classes18.dex */
public class JDViewKitViewModel {
    private String bgImageUrl;
    private JDViewKitVirtualView virtualView;

    public JDViewKitViewModel(JDViewKitVirtualView jDViewKitVirtualView) {
        this.virtualView = jDViewKitVirtualView;
        if (jDViewKitVirtualView != null) {
            this.bgImageUrl = jDViewKitVirtualView.getBackgroundImage();
        }
    }

    public String getBgImageUrl() {
        return this.bgImageUrl;
    }

    public JDViewKitVirtualView getVirtualView() {
        return this.virtualView;
    }

    public void setBgImageUrl(String str) {
        this.bgImageUrl = str;
    }

    public void setVirtualView(JDViewKitVirtualView jDViewKitVirtualView) {
        this.virtualView = jDViewKitVirtualView;
    }
}
