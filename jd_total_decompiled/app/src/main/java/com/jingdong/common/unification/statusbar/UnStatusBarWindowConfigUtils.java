package com.jingdong.common.unification.statusbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class UnStatusBarWindowConfigUtils {
    private static final String FALSE = "false";
    private static final String TRUE = "true";
    private static Map<Object, String> cacheStateMap = new HashMap();

    private static void changeWindowMode(Object obj, boolean z) {
        if (obj instanceof Dialog) {
            UnStatusBarWindowUtils.changeWindowMode((Dialog) obj, z);
        } else if (obj instanceof PopupWindow) {
            UnStatusBarWindowUtils.changeWindowMode((PopupWindow) obj, z);
        }
    }

    private static Activity getActivity(Object obj) {
        Context context;
        if (obj instanceof Dialog) {
            context = ((Dialog) obj).getWindow().getContext();
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
        } else {
            context = obj instanceof PopupWindow ? ((PopupWindow) obj).getContentView().getContext() : null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    private static View getActivityDecorView(Object obj) {
        Activity activity = getActivity(obj);
        if (activity == null) {
            return null;
        }
        return activity.getWindow().getDecorView();
    }

    private static boolean isFocusable(Object obj) {
        if (obj instanceof Dialog) {
            return UnStatusBarWindowUtils.isFocusable((Dialog) obj);
        }
        if (obj instanceof PopupWindow) {
            return UnStatusBarWindowUtils.isFocusable((PopupWindow) obj);
        }
        return false;
    }

    private static boolean isNeedConfig(Object obj) {
        Activity activity;
        Window window;
        WindowManager.LayoutParams attributes;
        WindowManager.LayoutParams attributes2;
        if (!(obj instanceof Dialog)) {
            return (!(obj instanceof PopupWindow) || (activity = getActivity(obj)) == null || (window = activity.getWindow()) == null || (attributes = window.getAttributes()) == null || attributes.alpha != 1.0f) ? false : true;
        }
        Window window2 = ((Dialog) obj).getWindow();
        if (window2 == null || (attributes2 = window2.getAttributes()) == null) {
            return false;
        }
        return !((attributes2.flags & 2) == 2) || attributes2.dimAmount == 0.0f;
    }

    private static void makeConfigTakeEffect(Object obj) {
        if (obj instanceof PopupWindow) {
            ((PopupWindow) obj).update();
        }
    }

    public static void onAfterShowConfig(Dialog dialog) {
        onRealAfterShowConfig(dialog);
    }

    public static void onPreShowConfig(Dialog dialog) {
        onRealPreShowConfig(dialog);
    }

    private static void onRealAfterShowConfig(Object obj) {
        if (obj != null && isNeedConfig(obj)) {
            boolean equals = "true".equals(cacheStateMap.get(obj));
            cacheStateMap.remove(obj);
            if (equals) {
                setFocusable(obj, true);
                View activityDecorView = getActivityDecorView(obj);
                if (activityDecorView == null) {
                    return;
                }
                changeWindowMode(obj, UnStatusBarWindowUtils.isLightMode(activityDecorView));
                makeConfigTakeEffect(obj);
            }
        }
    }

    private static void onRealPreShowConfig(Object obj) {
        if (obj != null && isNeedConfig(obj)) {
            boolean isFocusable = isFocusable(obj);
            cacheStateMap.put(obj, isFocusable ? "true" : "false");
            if (isFocusable) {
                setFocusable(obj, false);
            }
        }
    }

    private static void setFocusable(Object obj, boolean z) {
        if (obj instanceof Dialog) {
            UnStatusBarWindowUtils.setFocusable((Dialog) obj, z);
        } else if (obj instanceof PopupWindow) {
            UnStatusBarWindowUtils.setFocusable((PopupWindow) obj, z);
        }
    }

    public static void onAfterShowConfig(PopupWindow popupWindow) {
        onRealAfterShowConfig(popupWindow);
    }

    public static void onPreShowConfig(PopupWindow popupWindow) {
        onRealPreShowConfig(popupWindow);
    }
}
