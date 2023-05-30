package com.jd.aips.tools.linker;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class MissingLibraryException extends RuntimeException {
    public MissingLibraryException(String str, String[] strArr, String[] strArr2) {
        super("Could not find '" + str + "'. Looked for: " + Arrays.toString(strArr) + ", but only found: " + Arrays.toString(strArr2) + OrderISVUtil.MONEY_DECIMAL);
    }
}
