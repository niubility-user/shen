package com.jdpay.membercode.network.a;

import androidx.annotation.Nullable;
import com.jdpay.json.JsonAdapter;
import com.jdpay.lib.annotation.BusinessParam;
import com.jdpay.lib.annotation.Exclusion;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.membercode.Aks;

/* loaded from: classes18.dex */
public class b implements Converter<com.jdpay.membercode.bean.a, String> {
    @Override // com.jdpay.lib.converter.Converter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public String convert(@Nullable com.jdpay.membercode.bean.a aVar) {
        if (aVar == null) {
            return null;
        }
        String stringSafety = JsonAdapter.stringSafety(aVar, new Class[]{BusinessParam.class});
        aVar.setBusinessData(stringSafety);
        JDPayLog.i("Bussiness:" + stringSafety);
        String string = JsonAdapter.string(aVar, JsonAdapter.getDefaultNameStrategy(), null, new Class[]{Exclusion.class, BusinessParam.class});
        JDPayLog.i(string);
        return Aks.encode(string);
    }
}
