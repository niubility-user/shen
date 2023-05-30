package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Color;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class s0 extends p0<YogaLayout> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, YogaLayout yogaLayout) {
        if (hashMap.containsKey(DYConstants.DY_SHADOW_OPACITY)) {
            try {
                yogaLayout.setShadowAlpha(Float.parseFloat(hashMap.get(DYConstants.DY_SHADOW_OPACITY)));
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowAlpha error", null, null, e2);
            }
        }
        if (hashMap.containsKey(DYConstants.DY_SHADOW_COLOR)) {
            try {
                yogaLayout.setShadowColor(Color.parseColor(hashMap.get(DYConstants.DY_SHADOW_COLOR)));
            } catch (Exception e3) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowColor error", null, null, e3);
            }
        }
        if (hashMap.containsKey(DYConstants.DY_SHADOW_OFFSET)) {
            String str = hashMap.get(DYConstants.DY_SHADOW_OFFSET);
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(DYConstants.DY_REGEX_COMMA);
                try {
                    if (split.length >= 2) {
                        yogaLayout.setShadowOffsetDx(DPIUtil.dip2px(Float.parseFloat(split[0])));
                        yogaLayout.setShadowOffsetDy(DPIUtil.dip2px(Float.parseFloat(split[1])));
                    } else if (split.length == 1) {
                        yogaLayout.setShadowOffsetDx(DPIUtil.dip2px(Float.parseFloat(split[0])));
                    }
                } catch (Exception e4) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowOffsetDx error", null, null, e4);
                }
            }
        }
        if (hashMap.containsKey(DYConstants.DY_SHADOW_RADIUS)) {
            try {
                yogaLayout.setShadowRadius(DPIUtil.dip2px(Float.parseFloat(hashMap.get(DYConstants.DY_SHADOW_RADIUS))));
            } catch (Exception e5) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowRadius error", null, null, e5);
            }
        }
        if (hashMap.containsKey(DYConstants.DY_SHADOW_CORNER)) {
            try {
                yogaLayout.setCornerRadii(DPIUtil.dip2px(Float.parseFloat(hashMap.get(DYConstants.DY_SHADOW_CORNER))));
            } catch (Exception e6) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowCorner error", null, null, e6);
            }
        }
    }
}
