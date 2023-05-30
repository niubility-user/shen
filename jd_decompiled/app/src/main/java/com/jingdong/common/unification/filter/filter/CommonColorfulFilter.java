package com.jingdong.common.unification.filter.filter;

import com.jingdong.common.unification.filter.FilterTools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class CommonColorfulFilter extends CommonFilterGroup {
    private Map<String, CommonFilter> filters;

    public CommonColorfulFilter() {
        this.filters = new HashMap();
        CommonSharpenFilter commonSharpenFilter = new CommonSharpenFilter(0.2f);
        addFilter(commonSharpenFilter);
        this.filters.put("sharpen", commonSharpenFilter);
        CommonSaturationFilter commonSaturationFilter = new CommonSaturationFilter(1.6f);
        addFilter(commonSaturationFilter);
        this.filters.put("saturation", commonSaturationFilter);
        CommonContrastFilter commonContrastFilter = new CommonContrastFilter(1.2f);
        addFilter(commonContrastFilter);
        this.filters.put("contrast", commonContrastFilter);
        CommonBrightnessFilter commonBrightnessFilter = new CommonBrightnessFilter(0.005f);
        addFilter(commonBrightnessFilter);
        this.filters.put("brightness", commonBrightnessFilter);
    }

    public CommonFilter getBrightnessFilter() {
        return this.filters.get("brightness");
    }

    public CommonFilter getContrastFilter() {
        return this.filters.get("contrast");
    }

    public CommonFilter getSaturationFilter() {
        return this.filters.get("saturation");
    }

    public CommonFilter getSharpenFilter() {
        return this.filters.get("sharpen");
    }

    public CommonColorfulFilter(int i2) {
        addFilter(new CommonSharpenFilter(FilterTools.range(i2, 0.0f, 0.2f)));
        addFilter(new CommonSaturationFilter(FilterTools.range(i2, 1.0f, 1.6f)));
        addFilter(new CommonContrastFilter(FilterTools.range(i2, 1.0f, 1.2f)));
        addFilter(new CommonBrightnessFilter(FilterTools.range(i2, 0.0f, 0.005f)));
    }
}
