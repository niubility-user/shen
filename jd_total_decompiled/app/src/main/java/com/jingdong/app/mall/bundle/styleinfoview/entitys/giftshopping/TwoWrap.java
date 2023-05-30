package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TwoWrap {
    private static final int INT_2 = 2;
    private final Wrap productOne;
    private final Wrap productTwo;

    private TwoWrap(Wrap wrap, Wrap wrap2) {
        this.productOne = wrap;
        this.productTwo = wrap2;
    }

    public static ArrayList<TwoWrap> toList(ArrayList<Wrap> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            ArrayList<TwoWrap> arrayList2 = new ArrayList<>();
            int size = arrayList.size();
            int i2 = size / 2;
            if (i2 > 0) {
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = i3 * 2;
                    arrayList2.add(new TwoWrap(arrayList.get(i4), arrayList.get(i4 + 1)));
                }
            }
            if (size % 2 == 1) {
                arrayList2.add(new TwoWrap(arrayList.get(size - 1), null));
            }
            return arrayList2;
        }
        return new ArrayList<>();
    }

    public Wrap getProductOne() {
        return this.productOne;
    }

    public Wrap getProductTwo() {
        return this.productTwo;
    }
}
