package com.jingdong.app.mall.bundle.order_center_isv_core.param;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class OrderISVRedirectProtocol {
    public static final String DIRECT_M = "M";
    public static final String DIRECT_NATIVE = "Native";
    public static final String DIRECT_OPEN_APP = "openApp";
    public String type = "";
    public String url = "";
    public String param = "";

    public boolean equals(Object obj) {
        if (obj instanceof OrderISVRedirectProtocol) {
            OrderISVRedirectProtocol orderISVRedirectProtocol = (OrderISVRedirectProtocol) obj;
            return TextUtils.equals(this.param, orderISVRedirectProtocol.param) && TextUtils.equals(this.type, orderISVRedirectProtocol.type) && TextUtils.equals(this.url, orderISVRedirectProtocol.url);
        }
        return false;
    }

    public int hashCode() {
        if (TextUtils.isEmpty(this.type)) {
            return 0;
        }
        return (TextUtils.isEmpty(this.param) ? 0 : this.param.hashCode()) + this.type.hashCode() + (TextUtils.isEmpty(this.url) ? 0 : this.url.hashCode());
    }
}
