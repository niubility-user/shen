package com.jingdong.jdreact.plugin.jdmodal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;

/* loaded from: classes13.dex */
class JDModalHostHelper {
    private static final Point MIN_POINT = new Point();
    private static final Point MAX_POINT = new Point();
    private static final Point SIZE_POINT = new Point();

    JDModalHostHelper() {
    }

    @TargetApi(16)
    public static Point getModalHostSize(Context context) {
        Display defaultDisplay = ((WindowManager) Assertions.assertNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        Point point2 = MIN_POINT;
        Point point3 = MAX_POINT;
        defaultDisplay.getCurrentSizeRange(point2, point3);
        Point point4 = SIZE_POINT;
        defaultDisplay.getSize(point4);
        int i2 = 0;
        boolean z = context.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (z && identifier > 0) {
            i2 = (int) resources.getDimension(identifier);
        }
        Activity currentActivity = ((ReactContext) context).getCurrentActivity();
        if (point4.x < point4.y) {
            if (currentActivity != null) {
                View findViewById = currentActivity.getWindow().getDecorView().findViewById(16908290);
                return new Point(findViewById.getWidth(), findViewById.getHeight());
            }
            return new Point(point2.x, point3.y + i2);
        }
        return new Point(point3.x, point2.y + i2);
    }
}
