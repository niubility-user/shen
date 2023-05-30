package com.jdjr.generalKeyboard.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public class UIUtils {
    @RequiresApi(api = 16)
    public static void createSelector(View view, Context context, int i2, int i3, int i4) {
        if (view == null || context == null || i2 <= 0 || i4 <= 0 || i3 <= 0) {
            return;
        }
        Resources resources = context.getResources();
        int i5 = R.dimen.security_keyboard_key_corner_radius;
        view.setBackground(getStateListDrawable(getShapeDrawable(i2, resources.getDimension(i5)), getShapeDrawable(i3, context.getResources().getDimension(i5)), getShapeDrawable(i4, context.getResources().getDimension(i5))));
    }

    public static Drawable getRightCornerDrawable(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int parseColor = Color.parseColor(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        Resources resources = context.getResources();
        int i2 = R.dimen.security_keyboard_countdown_corner_radius;
        gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, resources.getDimension(i2), context.getResources().getDimension(i2), context.getResources().getDimension(i2), context.getResources().getDimension(i2), 0.0f, 0.0f});
        gradientDrawable.setColor(parseColor);
        return gradientDrawable;
    }

    private static Drawable getShapeDrawable(int i2, float f2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(f2);
        gradientDrawable.setColor(i2);
        return gradientDrawable;
    }

    private static Drawable getStateListDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842910, 16842919}, drawable2);
        stateListDrawable.addState(new int[]{16842910, -16842919}, drawable);
        stateListDrawable.addState(new int[]{-16842910, -16842913}, drawable3);
        return DrawableCompat.wrap(stateListDrawable);
    }

    public static void setStatusBarColor(Activity activity, @ColorRes int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(activity.getResources().getColor(i2));
        }
    }

    public static void setStatusBarColorDelay(final Activity activity, @ColorRes final int i2, long j2) {
        if (j2 <= 0) {
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.jdjr.generalKeyboard.common.UIUtils.1
            @Override // java.lang.Runnable
            public void run() {
                UIUtils.setStatusBarColor(activity, i2);
            }
        }, j2);
    }

    @RequiresApi(api = 16)
    public static void createSelector(View view, Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || view == null || context == null) {
            return;
        }
        try {
            int parseColor = Color.parseColor(str);
            int blendARGB = TextUtils.isEmpty(str2) ? ColorUtils.blendARGB(parseColor, -16777216, 0.05f) : Color.parseColor(str2);
            int blendARGB2 = TextUtils.isEmpty(str3) ? ColorUtils.blendARGB(parseColor, -3355444, 0.5f) : Color.parseColor(str3);
            Resources resources = context.getResources();
            int i2 = R.dimen.security_keyboard_key_corner_radius;
            view.setBackground(getStateListDrawable(getShapeDrawable(parseColor, resources.getDimension(i2)), getShapeDrawable(blendARGB, context.getResources().getDimension(i2)), getShapeDrawable(blendARGB2, context.getResources().getDimension(i2))));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
