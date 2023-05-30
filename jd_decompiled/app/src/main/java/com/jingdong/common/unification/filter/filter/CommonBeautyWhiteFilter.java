package com.jingdong.common.unification.filter.filter;

import com.jingdong.common.unification.filter.FilterTools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class CommonBeautyWhiteFilter extends CommonFilterGroup {
    private Map<String, CommonFilter> filters;

    public CommonBeautyWhiteFilter() {
        this.filters = new HashMap();
        CommonBeautyFilter commonBeautyFilter = new CommonBeautyFilter();
        addFilter(commonBeautyFilter);
        this.filters.put("beauty", commonBeautyFilter);
    }

    public CommonFilter getBeautyFilter() {
        return this.filters.get("beauty");
    }

    public CommonBeautyWhiteFilter(int i2) {
        addFilter(new CommonBeautyFilter(FilterTools.range(i2, -3.0f, 0.42f), FilterTools.range(i2, -0.67f, 0.47f), FilterTools.range(i2, 0.5f, 0.34f)));
    }
}
