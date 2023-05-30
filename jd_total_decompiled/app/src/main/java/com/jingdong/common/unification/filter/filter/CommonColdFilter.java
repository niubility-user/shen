package com.jingdong.common.unification.filter.filter;

import com.jingdong.common.unification.filter.FilterTools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class CommonColdFilter extends CommonFilterGroup {
    private Map<String, CommonFilter> filters;

    public CommonColdFilter() {
        this.filters = new HashMap();
        CommonSharpenFilter commonSharpenFilter = new CommonSharpenFilter(0.2f);
        addFilter(commonSharpenFilter);
        this.filters.put("sharpen", commonSharpenFilter);
        CommonRGBFilter commonRGBFilter = new CommonRGBFilter(1.0f, 1.04f, 1.2f);
        addFilter(commonRGBFilter);
        this.filters.put("rgb", commonRGBFilter);
        CommonSaturationFilter commonSaturationFilter = new CommonSaturationFilter(1.05f);
        addFilter(commonSaturationFilter);
        this.filters.put("saturation", commonSaturationFilter);
        CommonBrightnessFilter commonBrightnessFilter = new CommonBrightnessFilter(0.02f);
        addFilter(commonBrightnessFilter);
        this.filters.put("brightness", commonBrightnessFilter);
    }

    public CommonFilter getBrightnessFilter() {
        return this.filters.get("brightness");
    }

    public CommonFilter getRGBFilter() {
        return this.filters.get("rgb");
    }

    public CommonFilter getSaturationFilter() {
        return this.filters.get("saturation");
    }

    public CommonFilter getSharpenFilter() {
        return this.filters.get("sharpen");
    }

    public CommonColdFilter(int i2) {
        addFilter(new CommonSharpenFilter(FilterTools.range(i2, 0.0f, 0.2f)));
        addFilter(new CommonRGBFilter(1.0f, FilterTools.range(i2, 1.0f, 1.04f), FilterTools.range(i2, 1.0f, 1.2f)));
        addFilter(new CommonSaturationFilter(FilterTools.range(i2, 1.0f, 1.05f)));
        addFilter(new CommonBrightnessFilter(FilterTools.range(i2, 0.0f, 0.02f)));
    }
}
