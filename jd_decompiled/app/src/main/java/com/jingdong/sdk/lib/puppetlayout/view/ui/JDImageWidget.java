package com.jingdong.sdk.lib.puppetlayout.view.ui;

import android.content.Context;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class JDImageWidget extends SimpleDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private static Map<String, ImageView.ScaleType> f15228g;

    static {
        HashMap hashMap = new HashMap();
        f15228g = hashMap;
        hashMap.put(DYConstants.DY_STRETCH, ImageView.ScaleType.FIT_XY);
        f15228g.put("fitStart", ImageView.ScaleType.FIT_START);
        f15228g.put("fitEnd", ImageView.ScaleType.FIT_END);
        f15228g.put("fitCenter", ImageView.ScaleType.FIT_CENTER);
        f15228g.put(DYConstants.DY_CENTER, ImageView.ScaleType.CENTER);
        f15228g.put("centerCrop", ImageView.ScaleType.CENTER_CROP);
        f15228g.put("centerInside", ImageView.ScaleType.CENTER_INSIDE);
    }

    public JDImageWidget(Context context) {
        super(context);
    }

    public void a(String str) {
        if (f15228g.containsKey(str)) {
            setScaleType(f15228g.get(str));
        }
    }

    public void b(String str) {
        String str2 = "";
        if (str.startsWith("app://")) {
            String[] split = str.replaceFirst("app://", "").split(DYConstants.DY_REGEX_COMMA);
            if (split.length <= 0) {
                return;
            }
            for (String str3 : split) {
                if (str3.startsWith("android:")) {
                    String[] split2 = str3.substring(8, str3.length()).split("/");
                    String packageName = getContext().getPackageName();
                    if (split2.length >= 2) {
                        str2 = split2[1];
                        packageName = split2[0];
                    } else if (split2.length == 1) {
                        str2 = split2[0];
                    }
                    setImageResource(getContext().getResources().getIdentifier(str2, "drawable", packageName));
                    return;
                }
            }
        } else if (str.startsWith("u://")) {
            UnIconConfigHelper.displayIcon(str.replaceFirst("u://", ""), this);
        }
    }
}
