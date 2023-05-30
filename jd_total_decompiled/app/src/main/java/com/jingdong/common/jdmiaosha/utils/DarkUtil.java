package com.jingdong.common.jdmiaosha.utils;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jingdong.common.utils.DeepDarkChangeManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class DarkUtil {
    public static int PIC_FOREGROUND_COLOR;
    public static int RED_CIRCLE_FOREGROUND_COLOR;
    private static boolean isDark;
    private static List<IThemeListener> sThemeListenerList;

    /* loaded from: classes5.dex */
    public interface IThemeListener {
        void onChanged(boolean z);
    }

    static {
        isDark = 1 == DeepDarkChangeManager.getInstance().getUIMode();
        sThemeListenerList = new ArrayList();
        PIC_FOREGROUND_COLOR = 858795566;
        RED_CIRCLE_FOREGROUND_COLOR = 858795566;
    }

    public static void debugChangeTheme(LifecycleOwner lifecycleOwner) {
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(lifecycleOwner, new Observer<Integer>() { // from class: com.jingdong.common.jdmiaosha.utils.DarkUtil.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Integer num) {
                boolean unused = DarkUtil.isDark = 1 == num.intValue();
                if (DarkUtil.sThemeListenerList == null || DarkUtil.sThemeListenerList.size() <= 0) {
                    return;
                }
                Iterator it = DarkUtil.sThemeListenerList.iterator();
                while (it.hasNext()) {
                    ((IThemeListener) it.next()).onChanged(DarkUtil.isDark);
                }
            }
        });
    }

    public static void invalidateView(View view) {
        if (view == null) {
            return;
        }
        view.postInvalidate();
    }

    public static boolean isDarkTheme() {
        return isDark;
    }

    public static void registerThemeChangeListener(IThemeListener iThemeListener) {
        if (iThemeListener != null) {
            sThemeListenerList.add(iThemeListener);
        }
    }

    public static void reset() {
        sThemeListenerList.clear();
    }

    public static void setBackgroundColor(View view, int i2, int i3) {
        if (view == null) {
            return;
        }
        Resources resources = view.getResources();
        if (isDark) {
            i2 = i3;
        }
        view.setBackgroundColor(resources.getColor(i2));
    }

    public static void setBackgroundResource(View view, int i2, int i3) {
        if (view == null) {
            return;
        }
        if (isDark) {
            i2 = i3;
        }
        view.setBackgroundResource(i2);
    }

    public static void setTextColor(TextView textView, int i2, int i3) {
        if (textView == null) {
            return;
        }
        Resources resources = textView.getResources();
        if (isDark) {
            i2 = i3;
        }
        textView.setTextColor(resources.getColor(i2));
    }
}
