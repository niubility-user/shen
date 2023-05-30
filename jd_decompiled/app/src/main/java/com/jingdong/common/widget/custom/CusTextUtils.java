package com.jingdong.common.widget.custom;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes12.dex */
public class CusTextUtils {
    public static void showAuthor(String str, ImageView imageView, int i2) {
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(i2));
        JDImageUtils.displayImage(str, imageView, jDDisplayImageOptions);
    }

    public static void showOrEmptyGone(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            textView.setVisibility(0);
            textView.setText(str);
            return;
        }
        textView.setVisibility(8);
    }
}
