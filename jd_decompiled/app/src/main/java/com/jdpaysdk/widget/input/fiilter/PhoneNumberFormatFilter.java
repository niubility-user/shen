package com.jdpaysdk.widget.input.fiilter;

import com.jdpaysdk.widget.input.fiilter.abs.SpacingFormatFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class PhoneNumberFormatFilter extends SpacingFormatFilter {
    @Override // com.jdpaysdk.widget.input.fiilter.abs.FormatFilter
    protected List<Integer> getFormatIndexList(int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 > 2) {
            arrayList.add(2);
            if (i2 > 6) {
                arrayList.add(6);
            }
        }
        return arrayList;
    }
}
