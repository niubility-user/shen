package com.jingdong.common.web;

import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes6.dex */
public class BaseWebComponent {
    protected IWebUiBinder webUiBinder;

    public BaseWebComponent() {
    }

    public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
        this.webUiBinder = iWebUiBinder;
    }

    public BaseWebComponent(IWebUiBinder iWebUiBinder) {
        this.webUiBinder = iWebUiBinder;
    }
}
