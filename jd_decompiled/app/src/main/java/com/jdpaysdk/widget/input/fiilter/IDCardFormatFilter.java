package com.jdpaysdk.widget.input.fiilter;

import com.jdpaysdk.widget.input.fiilter.abs.SpacingFormatFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class IDCardFormatFilter extends SpacingFormatFilter {
    @Override // com.jdpaysdk.widget.input.fiilter.abs.FormatFilter
    protected List<Integer> getFormatIndexList(int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 5; i3 < i2; i3 += 8) {
            arrayList.add(Integer.valueOf(i3));
        }
        return arrayList;
    }
}
