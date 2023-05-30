package com.jingdong.common.unification.filter.filter;

import android.graphics.PointF;
import com.jingdong.common.unification.filter.FilterTools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class CommonCartridgeFilter extends CommonFilterGroup {
    private Map<String, CommonFilter> filters;

    public CommonCartridgeFilter() {
        this.filters = new HashMap();
        CommonSharpenFilter commonSharpenFilter = new CommonSharpenFilter(0.2f);
        addFilter(commonSharpenFilter);
        this.filters.put("sharpen", commonSharpenFilter);
        CommonSaturationFilter commonSaturationFilter = new CommonSaturationFilter(0.75f);
        addFilter(commonSaturationFilter);
        this.filters.put("saturation", commonSaturationFilter);
        CommonBrightnessFilter commonBrightnessFilter = new CommonBrightnessFilter(0.1f);
        addFilter(commonBrightnessFilter);
        this.filters.put("brightness", commonBrightnessFilter);
        CommonRGBFilter commonRGBFilter = new CommonRGBFilter(1.02f, 1.04f, 1.02f);
        addFilter(commonRGBFilter);
        this.filters.put("rgb", commonRGBFilter);
        CommonExposureFilter commonExposureFilter = new CommonExposureFilter(-0.038f);
        addFilter(commonExposureFilter);
        this.filters.put("exposure", commonExposureFilter);
        PointF pointF = new PointF();
        pointF.x = 0.5f;
        pointF.y = 0.5f;
        CommonVignetteFilter commonVignetteFilter = new CommonVignetteFilter(pointF, new float[]{0.0f, 0.0f, 0.0f}, 0.45f, 0.85f);
        addFilter(commonVignetteFilter);
        this.filters.put("vignette", commonVignetteFilter);
    }

    public CommonFilter getBrightnessFilter() {
        return this.filters.get("brightness");
    }

    public CommonFilter getExposureFilter() {
        return this.filters.get("exposure");
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

    public CommonFilter getVignetteFilter() {
        return this.filters.get("vignette");
    }

    public CommonCartridgeFilter(int i2) {
        addFilter(new CommonSharpenFilter(FilterTools.range(i2, 0.0f, 0.2f)));
        addFilter(new CommonSaturationFilter(FilterTools.range(i2, 1.0f, 0.75f)));
        addFilter(new CommonBrightnessFilter(FilterTools.range(i2, 0.0f, 0.1f)));
        addFilter(new CommonRGBFilter(FilterTools.range(i2, 1.0f, 1.02f), FilterTools.range(i2, 1.0f, 1.04f), FilterTools.range(i2, 1.0f, 1.02f)));
        addFilter(new CommonExposureFilter(FilterTools.range(i2, 0.0f, -0.038f)));
        PointF pointF = new PointF();
        pointF.x = 0.5f;
        pointF.y = 0.5f;
        addFilter(new CommonVignetteFilter(pointF, new float[]{0.0f, 0.0f, 0.0f}, FilterTools.range(i2, 1.0f, 0.45f), FilterTools.range(i2, 1.0f, 0.85f)));
    }
}
