package com.jingdong.app.mall.home.widget;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.common.i.u;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class a {
    static int a = 256;
    static Map<Integer, String> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    static Map<String, Integer> f11070c = new HashMap();

    public static RecyclerView.ViewHolder a(Context context, int i2) {
        if (i2 >= 256) {
            if (t.FLOOR_ERROR == u.h(i2)) {
                return null;
            }
            return new HomeRecyclerAdapter.SimpleViewHolder(t.FLEX_CUBE.getFloorViewByCache(context).getContentView());
        }
        return null;
    }

    public static int b(d dVar) {
        if (t.FLEX_CUBE != dVar.q) {
            return 0;
        }
        FlexCubeModel d = dVar.d();
        if (d == null) {
            return -1;
        }
        String style = d.getStyle(0);
        Integer num = f11070c.get(style);
        if (num == null) {
            int i2 = a + 1;
            a = i2;
            b.put(Integer.valueOf(i2), style);
            f11070c.put(style, Integer.valueOf(a));
            return a;
        }
        return num.intValue();
    }
}
