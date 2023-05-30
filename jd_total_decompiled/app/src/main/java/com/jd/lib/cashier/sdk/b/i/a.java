package com.jd.lib.cashier.sdk.b.i;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    public static <T extends ICheckNullObj> void a(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        g0.f(list);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            it.next().checkNullObjAndInit();
        }
    }
}
