package com.getkeepsafe.relinker;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class b extends RuntimeException {
    public b(String str, String[] strArr, String[] strArr2) {
        super("Could not find '" + str + "'. Looked for: " + Arrays.toString(strArr) + ", but only found: " + Arrays.toString(strArr2) + OrderISVUtil.MONEY_DECIMAL);
    }
}
