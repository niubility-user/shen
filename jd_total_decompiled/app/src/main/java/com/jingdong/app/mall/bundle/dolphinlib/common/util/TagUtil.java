package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.span.RadiusBackgroundColorSpan;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTagEntity;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes19.dex */
public class TagUtil {
    private static final int MAX_TAGS = 2;
    private static final List<String> TAG_LEVEL_LIST = Arrays.asList("\u53cc11", "\u9ed15", "\u53cc12", "\u5e74\u8d27\u8282", "618", "\u81ea\u8425", "\u4eac\u4e1c\u56fd\u9645", "\u653e\u5fc3\u8d2d", "\u591a\u4e70\u4f18\u60e0", "\u5546\u5bb6\u514d\u90ae");

    public static void addTagsForSkuTitle(Context context, @NonNull TextView textView, @NonNull String str, @NonNull List<DolphinTagEntity> list) {
        List<String> seniorTag = getSeniorTag(list);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (int size = seniorTag.size() - 1; size >= 0; size--) {
            str = seniorTag.get(size) + LangUtils.SINGLE_SPACE + str;
        }
        SpannableString spannableString = new SpannableString(str);
        int i2 = 0;
        int i3 = 0;
        while (i2 < seniorTag.size()) {
            int i4 = i3 + (i2 != 0 ? 1 : 0);
            int length = seniorTag.get(i2).length() + i4;
            spannableString.setSpan(new RelativeSizeSpan(0.8f), i4, length, 33);
            spannableString.setSpan(new RadiusBackgroundColorSpan(new int[]{ColorUtil.parseColor("#F2140C", -1), ColorUtil.parseColor("#E8220E", -1), ColorUtil.parseColor("#F24D0C", -1)}, ColorUtil.parseColor("#FFFFFF", -1), DPIUtil.dip2px(context, 2.0f)), i4, length, 33);
            i2++;
            i3 = length;
        }
        textView.setText(spannableString);
    }

    public static List<String> getSeniorTag(List<DolphinTagEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < TAG_LEVEL_LIST.size(); i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= list.size()) {
                    break;
                } else if (TAG_LEVEL_LIST.get(i2).equals(list.get(i3).tagName)) {
                    arrayList.add(list.get(i3).tagName);
                    break;
                } else {
                    i3++;
                }
            }
            if (arrayList.size() >= 2) {
                break;
            }
        }
        return arrayList;
    }
}
