package com.jingdong.app.mall.bundle.xanimation.f;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class d {
    public static View a(Context context, String str) {
        if (!TextUtils.isEmpty(str) && (context instanceof Activity)) {
            Window window = ((Activity) context).getWindow();
            if (window.isActive()) {
                for (View view : d(window.getDecorView())) {
                    if (view != null && TextUtils.equals(str, context.getResources().getResourceEntryName(view.getId()))) {
                        return view;
                    }
                }
            }
        }
        return null;
    }

    private static boolean b(int i2) {
        return (i2 == -1 || ((-16777216) & i2) == 0 || (i2 & 16711680) == 0) ? false : true;
    }

    public static boolean c(View view) {
        return (view != null && view.getVisibility() == 0 && b(view.getId())) ? false : true;
    }

    public static List<View> d(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (!c(childAt)) {
                    arrayList.add(childAt);
                }
                arrayList.addAll(d(childAt));
            }
        }
        return arrayList;
    }
}
