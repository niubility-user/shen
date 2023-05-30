package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.TextView;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class UnUtils {
    public static void setSpanText(String str, String str2, TextView textView) {
        SpannableString spanableString;
        ArrayList arrayList = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split("#");
                if (split.length > 0) {
                    arrayList = new ArrayList();
                    for (String str3 : split) {
                        if (!TextUtils.isEmpty(str3)) {
                            arrayList.add(str3);
                        }
                    }
                }
            }
            if (arrayList == null || (spanableString = UnIconConfigHelper.getSpanableString(arrayList, str2, textView)) == null) {
                return;
            }
            textView.setText(spanableString);
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
            }
        }
    }
}
