package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class WareBusinessOnebox {
    public static final String TYPE_1 = "1";
    public static final String TYPE_2 = "2";
    public static final String TYPE_3 = "3";
    public String entityId;
    public String oneboxKeyword;
    public HashMap oneboxMta;
    public String oneboxSource;
    public HashMap<String, String> passThroughMap;
    public String type;
    public String url;

    public boolean isOneBoxEnable() {
        return (TextUtils.isEmpty(this.oneboxSource) || TextUtils.isEmpty(this.oneboxKeyword) || TextUtils.isEmpty(this.entityId)) ? false : true;
    }
}
