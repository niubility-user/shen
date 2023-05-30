package com.jdpaysdk.widget.input.fiilter;

import com.jdpaysdk.widget.input.fiilter.abs.SlashFormatFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class ValidDateFormatFilter extends SlashFormatFilter {
    @Override // com.jdpaysdk.widget.input.fiilter.abs.FormatFilter
    protected List<Integer> getFormatIndexList(int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 > 1) {
            arrayList.add(1);
        }
        return arrayList;
    }
}
