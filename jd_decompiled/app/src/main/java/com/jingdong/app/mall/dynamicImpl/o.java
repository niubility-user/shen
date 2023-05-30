package com.jingdong.app.mall.dynamicImpl;

import android.content.Context;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class o {
    private static float a(float f2) {
        if (-1.0f == f2) {
            return 14.0f;
        }
        return f2;
    }

    private static float b(Context context, float f2, float f3) {
        return -1.0f != f3 ? f3 : TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(context, f2);
    }

    public static String c(List<String> list) {
        if (g()) {
            return list.size() > 1 ? list.get(1) : "";
        }
        return list.get(0);
    }

    private static float d(float f2, float f3) {
        return -1.0f != f3 ? f3 : JDElderModeUtils.getElderTextSize(f2);
    }

    public static float e(Context context, List<Float> list) {
        List<Float> h2 = h(context, list);
        if (g()) {
            return h2.get(1).floatValue();
        }
        if (f()) {
            return h2.get(2).floatValue();
        }
        return h2.get(0).floatValue();
    }

    public static boolean f() {
        return TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode().equals(ScaleModeConstants.TEXT_SCALE_MODE_LARGE);
    }

    private static boolean g() {
        return JDElderModeUtils.isElderMode();
    }

    private static List<Float> h(Context context, List<Float> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            float a = a(-1.0f);
            arrayList.add(Float.valueOf(a));
            arrayList.add(Float.valueOf(d(a, -1.0f)));
            arrayList.add(Float.valueOf(b(context, a, -1.0f)));
            return arrayList;
        }
        int size = list.size();
        if (size == 1) {
            float a2 = a(list.get(0).floatValue());
            arrayList.add(Float.valueOf(a2));
            arrayList.add(Float.valueOf(d(a2, -1.0f)));
            arrayList.add(Float.valueOf(b(context, a2, -1.0f)));
        } else if (size == 2) {
            float a3 = a(list.get(0).floatValue());
            arrayList.add(Float.valueOf(a3));
            arrayList.add(Float.valueOf(d(a3, list.get(1).floatValue())));
            arrayList.add(Float.valueOf(b(context, a3, -1.0f)));
        } else if (size != 3) {
            float a4 = a(-1.0f);
            arrayList.add(Float.valueOf(a4));
            arrayList.add(Float.valueOf(d(a4, -1.0f)));
            arrayList.add(Float.valueOf(b(context, a4, -1.0f)));
        } else {
            float a5 = a(list.get(0).floatValue());
            arrayList.add(Float.valueOf(a5));
            arrayList.add(Float.valueOf(d(a5, list.get(1).floatValue())));
            arrayList.add(Float.valueOf(b(context, a5, list.get(2).floatValue())));
        }
        return arrayList;
    }
}
