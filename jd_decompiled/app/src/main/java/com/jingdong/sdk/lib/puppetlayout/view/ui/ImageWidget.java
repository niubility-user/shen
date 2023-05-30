package com.jingdong.sdk.lib.puppetlayout.view.ui;

import android.content.Context;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class ImageWidget extends SimpleDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private static Map<String, ImageView.ScaleType> f15227g;

    static {
        HashMap hashMap = new HashMap();
        f15227g = hashMap;
        hashMap.put(DYConstants.DY_STRETCH, ImageView.ScaleType.FIT_XY);
        f15227g.put("fitStart", ImageView.ScaleType.FIT_START);
        f15227g.put("fitEnd", ImageView.ScaleType.FIT_END);
        f15227g.put("fitCenter", ImageView.ScaleType.FIT_CENTER);
        f15227g.put(DYConstants.DY_CENTER, ImageView.ScaleType.CENTER);
        f15227g.put("centerCrop", ImageView.ScaleType.CENTER_CROP);
        f15227g.put("centerInside", ImageView.ScaleType.CENTER_INSIDE);
    }

    public ImageWidget(Context context) {
        super(context);
    }

    public void a(String str) {
        if (f15227g.containsKey(str)) {
            setScaleType(f15227g.get(str));
        }
    }

    public void b(String str) {
        String str2 = "";
        if (str.startsWith("app://")) {
            String[] split = str.replaceFirst("app://", "").split(":");
            String packageName = getContext().getPackageName();
            if (split.length >= 2) {
                str2 = split[1];
                packageName = split[0];
            } else if (split.length == 1) {
                str2 = split[0];
            }
            setImageResource(getContext().getResources().getIdentifier(str2, "drawable", packageName));
        } else if (str.startsWith("u://")) {
            str.replaceFirst("u://", "");
        }
    }
}
