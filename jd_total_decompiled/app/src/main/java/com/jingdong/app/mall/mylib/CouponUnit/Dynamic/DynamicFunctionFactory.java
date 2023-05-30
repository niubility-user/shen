package com.jingdong.app.mall.mylib.CouponUnit.Dynamic;

import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.IFunctionFactory;

/* loaded from: classes4.dex */
public class DynamicFunctionFactory implements IFunctionFactory {
    @Override // com.jd.dynamic.base.IFunctionFactory
    public CommFunction createCommFunction(String str) {
        if (FunctionManager.contains(str)) {
            return FunctionManager.get(str);
        }
        return null;
    }
}
