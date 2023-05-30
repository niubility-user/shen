package com.jd.dynamic.lib.expv2;

import android.app.Activity;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.dynamic.parser.DynamicXParser;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes13.dex */
public class MathCalc {
    static {
        try {
            System.loadLibrary("dynamic_math");
        } catch (Throwable th) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "load libdynamic_math.so failed!!!", (String) null, (String) null, (int) R2.attr.loadMax, new RuntimeException(th));
            t.e("MathCalc", "load libdynamic_math.so failed!!!", t.d(th));
        }
    }

    public double caclExpr(Activity activity, String str) {
        try {
            return nativeCalcExpr(str, DPIUtil.getWidthWithDp(activity), DPIUtil.getHeightWithDp(activity), DPIUtil.getStatusBarHeightDP(activity));
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("caclExpr catch exception\uff0cactivity = ");
            sb.append(activity == null ? "" : activity.getClass().getName());
            sb.append(" expression = ");
            sb.append(str);
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, sb.toString(), (String) null, (String) null, (int) R2.attr.liteMode, new Exception(th));
            return 0.0d;
        }
    }

    public double calcExpr(String str) {
        try {
            double nativeCalcExpr = nativeCalcExpr(str, DynamicXParser.width, DynamicXParser.height, DynamicXParser.statusBarHeight);
            double d = (int) nativeCalcExpr;
            Double.isNaN(d);
            return nativeCalcExpr - d == 0.0d ? d : nativeCalcExpr;
        } catch (Throwable th) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "calcExpr catch exception\uff0cexpression = " + str, (String) null, (String) null, (int) R2.attr.liteMode, new Exception(th));
            return 0.0d;
        }
    }

    public native double nativeCalcExpr(String str, double d, double d2, double d3);
}
