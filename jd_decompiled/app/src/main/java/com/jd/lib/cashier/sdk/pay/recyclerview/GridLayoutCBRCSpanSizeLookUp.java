package com.jd.lib.cashier.sdk.pay.recyclerview;

import androidx.recyclerview.widget.GridLayoutManager;
import java.util.List;

/* loaded from: classes14.dex */
public class GridLayoutCBRCSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {
    private List<com.jd.lib.cashier.sdk.d.a.e.a> a;

    private boolean a() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.a;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                if (this.a.get(i2).getItemType() == 400006) {
                    return true;
                }
            }
        }
        return false;
    }

    public void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        this.a = list;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
    public int getSpanSize(int i2) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.a;
        if (list != null && i2 >= 0 && i2 < list.size()) {
            com.jd.lib.cashier.sdk.d.a.e.a aVar = this.a.get(i2);
            if (aVar != null && aVar.getItemType() == 200001) {
                return 17;
            }
            if (aVar != null && aVar.getItemType() == 400006) {
                return 8;
            }
            if ((aVar == null || aVar.getItemType() != 400005) && aVar != null && aVar.getItemType() == 400004) {
                return a() ? 13 : 17;
            }
        }
        return 34;
    }
}
