package com.jingdong.app.mall.home.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFloor;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class HomeDebugItemDecoration extends RecyclerView.ItemDecoration {
    private static Paint a = new Paint(1);
    private static int[] b = {-6750004, -6749953, -6737152, -10053223, -3381760, -13395559};

    private void a(View view, String str, Canvas canvas) {
        int[] c2 = c(str);
        if (c2 == null) {
            return;
        }
        for (int i2 = 0; i2 < c2.length; i2++) {
            a.setColor(b[i2 % 6]);
            int d = d.d(c2[i2]) + view.getTop();
            canvas.drawText(String.valueOf(c2[i2]), 10.0f, (float) (d - 2), a);
            canvas.drawLine(0.0f, d, d.f9279g, d + 1, a);
        }
    }

    private void b(View view, String str, Canvas canvas) {
        int[] c2 = c(str);
        if (c2 == null) {
            return;
        }
        for (int i2 = 0; i2 < c2.length; i2++) {
            a.setColor(b[i2 % 6]);
            int top = view.getTop();
            int d = d.d(c2[i2]);
            canvas.drawText(String.valueOf(c2[i2]), d + 2, top + 10, a);
            canvas.drawLine(d, top, d + 1, top + view.getHeight(), a);
        }
    }

    private int[] c(String str) {
        int[] iArr = null;
        try {
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = TextUtils.split(str, DYConstants.DY_REGEX_COMMA);
        iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = Integer.valueOf(split[i2]).intValue();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        String str;
        h hVar;
        h hVar2;
        super.onDrawOver(canvas, recyclerView, state);
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            String str2 = null;
            if (childAt instanceof BaseMallColorFloor) {
                com.jingdong.app.mall.home.r.e.d bindItem = ((BaseMallColorFloor) childAt).getBindItem();
                str2 = bindItem.getJsonString("vLine");
                if (TextUtils.isEmpty(str2) && (hVar2 = bindItem.mParentModel) != null) {
                    str2 = hVar2.getJsonString("vLine");
                }
                String jsonString = bindItem.getJsonString("hLine");
                str = (!TextUtils.isEmpty(jsonString) || (hVar = bindItem.mParentModel) == null) ? jsonString : hVar.getJsonString("hLine");
            } else if (childAt instanceof BaseCaFloor) {
                c n2 = ((BaseCaFloor) childAt).n();
                str2 = n2.getJsonString("vLine");
                str = n2.getJsonString("hLine");
            } else {
                str = null;
            }
            if (str2 != null) {
                b(childAt, str2, canvas);
            }
            if (str != null) {
                a(childAt, str, canvas);
            }
        }
    }
}
