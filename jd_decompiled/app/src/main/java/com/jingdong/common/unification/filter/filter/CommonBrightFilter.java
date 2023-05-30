package com.jingdong.common.unification.filter.filter;

import com.jingdong.common.unification.filter.FilterTools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class CommonBrightFilter extends CommonFilterGroup {
    private Map<String, CommonFilter> filters;

    public CommonBrightFilter() {
        this.filters = new HashMap();
        CommonFilter commonSharpenFilter = new CommonSharpenFilter(0.2f);
        addFilter(commonSharpenFilter);
        this.filters.put("sharpen", commonSharpenFilter);
        CommonFilter commonExposureFilter = new CommonExposureFilter(0.375f);
        addFilter(commonExposureFilter);
        this.filters.put("exposure", commonExposureFilter);
        CommonFilter commonBrightnessFilter = new CommonBrightnessFilter(0.066f);
        addFilter(commonBrightnessFilter);
        this.filters.put("brightness", commonBrightnessFilter);
        CommonFilter commonContrastFilter = new CommonContrastFilter(1.15f);
        addFilter(commonContrastFilter);
        this.filters.put("contrast", commonContrastFilter);
        CommonFilter commonSaturationFilter = new CommonSaturationFilter(1.15f);
        addFilter(commonSaturationFilter);
        this.filters.put("saturation", commonSaturationFilter);
        CommonLevelsFilter commonLevelsFilter = new CommonLevelsFilter();
        commonLevelsFilter.setMin(0.0f, 0.95f, 1.0f);
        addFilter(commonLevelsFilter);
        this.filters.put("levels", commonLevelsFilter);
    }

    public CommonFilter getBrightnessFilter() {
        return this.filters.get("brightness");
    }

    public CommonFilter getContrastFilter() {
        return this.filters.get("contrast");
    }

    public CommonFilter getExposureFilter() {
        return this.filters.get("exposure");
    }

    public CommonFilter getLevelsFilter() {
        return this.filters.get("levels");
    }

    public CommonFilter getSaturationFilter() {
        return this.filters.get("saturation");
    }

    public CommonFilter getSharpenFilter() {
        return this.filters.get("sharpen");
    }

    public CommonBrightFilter(int i2) {
        addFilter(new CommonSharpenFilter(FilterTools.range(i2, 0.0f, 0.2f)));
        addFilter(new CommonExposureFilter(FilterTools.range(i2, 0.0f, 0.375f)));
        addFilter(new CommonBrightnessFilter(FilterTools.range(i2, 0.0f, 0.066f)));
        addFilter(new CommonContrastFilter(FilterTools.range(i2, 1.0f, 1.15f)));
        addFilter(new CommonSaturationFilter(FilterTools.range(i2, 1.0f, 1.15f)));
        float range = FilterTools.range(i2, 1.0f, 0.95f);
        CommonLevelsFilter commonLevelsFilter = new CommonLevelsFilter();
        commonLevelsFilter.setMin(0.0f, range, 1.0f);
        addFilter(commonLevelsFilter);
    }
}
