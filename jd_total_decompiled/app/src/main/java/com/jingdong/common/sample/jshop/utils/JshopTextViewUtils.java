package com.jingdong.common.sample.jshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JshopTextViewUtils {
    public static final boolean IS_LARGE_MODE;
    public static final String TEXT_SCALE_MODE;

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static JshopTextViewUtils INSTANCE = new JshopTextViewUtils();

        private SingletonHolder() {
        }
    }

    static {
        String textSizeScaleMode = TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode();
        TEXT_SCALE_MODE = textSizeScaleMode;
        IS_LARGE_MODE = ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(textSizeScaleMode);
    }

    public static JshopTextViewUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void doTextFontScaled(View view) {
        if (view == null || !(view instanceof TextView)) {
            return;
        }
        TextScaleModeHelper companion = TextScaleModeHelper.INSTANCE.getInstance();
        Context context = view.getContext();
        Context context2 = view.getContext();
        ((TextView) view).setTextSize(companion.getScaleSize(context, DPIUtil.px2sp(context2, r5.getTextSize())));
    }

    private JshopTextViewUtils() {
    }

    public void doTextFontScaled(View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            doTextFontScaled(view);
        }
    }
}
