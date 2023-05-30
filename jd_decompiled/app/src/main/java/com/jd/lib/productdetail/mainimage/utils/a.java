package com.jd.lib.productdetail.mainimage.utils;

import android.content.Context;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import com.jd.lib.productdetail.mainimage.R;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class a {
    public static HashMap<Integer, Integer> a;

    static {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        a = hashMap;
        Integer valueOf = Integer.valueOf(R.color.lib_pd_image_color_FA2C19);
        int i2 = R.color.lib_pd_image_color_ff3826;
        hashMap.put(valueOf, Integer.valueOf(i2));
        HashMap<Integer, Integer> hashMap2 = a;
        Integer valueOf2 = Integer.valueOf(R.color.lib_pd_image_color_1A1A1A);
        int i3 = R.color.lib_pd_image_color_ececec;
        hashMap2.put(valueOf2, Integer.valueOf(i3));
        HashMap<Integer, Integer> hashMap3 = a;
        Integer valueOf3 = Integer.valueOf(R.color.lib_pd_image_color_808080);
        int i4 = R.color.lib_pd_image_848383;
        hashMap3.put(valueOf3, Integer.valueOf(i4));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_cccccc), Integer.valueOf(R.color.lib_pd_image_color_555353));
        HashMap<Integer, Integer> hashMap4 = a;
        int i5 = R.color.lib_pd_image_color_FF7040;
        hashMap4.put(Integer.valueOf(i5), Integer.valueOf(i5));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_F9F9F9), Integer.valueOf(R.color.lib_pd_image_color_161515));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_FFFFFF), Integer.valueOf(R.color.lib_pd_image_1db1b));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_B3000000), Integer.valueOf(R.color.lib_pd_image_CC848383));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_05FFFFFF), Integer.valueOf(R.color.lib_pd_image_color_03000000));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_C25900), Integer.valueOf(R.color.lib_pd_image_color_FFD866));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_2e2d2d), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_black_8C8C8C), Integer.valueOf(i4));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_FF232326), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_f2270c), Integer.valueOf(i2));
        a.put(Integer.valueOf(R.color.lib_pd_image_black), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_FCF9DA), Integer.valueOf(R.color.lib_pd_image_color_33322B));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_DE6A1C), Integer.valueOf(R.color.lib_pd_image_color_BF6C30));
        HashMap<Integer, Integer> hashMap5 = a;
        Integer valueOf4 = Integer.valueOf(R.color.lib_pd_image_common_f2f2f2);
        int i6 = R.color.lib_pd_image_color_302E2E;
        hashMap5.put(valueOf4, Integer.valueOf(i6));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_F2F2F2), Integer.valueOf(i6));
        a.put(Integer.valueOf(R.color.lib_pd_image_black_25), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_1D1E1E), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_14000000), Integer.valueOf(R.color.lib_pd_image_color_14FFFFFF));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_FF404F), Integer.valueOf(R.color.lib_pd_image_color_FF4D5A));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_262626), Integer.valueOf(i3));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_FFF2F2F2), Integer.valueOf(R.color.lib_pd_image_color_0A0909));
        a.put(Integer.valueOf(R.color.lib_pd_image_color_4D4D4D), Integer.valueOf(i4));
        HashMap<Integer, Integer> hashMap6 = a;
        int i7 = R.color.lib_pd_image_color_CC8014;
        hashMap6.put(Integer.valueOf(i7), Integer.valueOf(i7));
        HashMap<Integer, Integer> hashMap7 = a;
        int i8 = R.color.lib_pd_image_color_FFCA80;
        hashMap7.put(Integer.valueOf(i8), Integer.valueOf(i8));
    }

    public static int a(Context context, @ColorRes int i2, boolean z) {
        if (context == null) {
            return 0;
        }
        if (z && a.containsKey(Integer.valueOf(i2))) {
            i2 = a.get(Integer.valueOf(i2)).intValue();
        }
        return ContextCompat.getColor(context, i2);
    }

    public static int b(boolean z) {
        return z ? com.jd.lib.productdetail.core.R.drawable.lib_pd_core_right_arrow_dark_theme : com.jd.lib.productdetail.core.R.drawable.lib_pd_core_detail_style_content_arrow;
    }
}
