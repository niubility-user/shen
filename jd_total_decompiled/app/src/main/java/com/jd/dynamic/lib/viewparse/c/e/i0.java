package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.lib.views.RichTextViewContainer;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class i0 extends p0<RichTextViewContainer> implements q0<RichTextViewContainer> {
    private float f(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return f2;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, RichTextViewContainer richTextViewContainer) {
        String str;
        Typeface a;
        String str2;
        String str3;
        String str4;
        String str5;
        if (hashMap.containsKey(DYConstants.DY_TEXT_COLOR) && (str5 = hashMap.get(DYConstants.DY_TEXT_COLOR)) != null) {
            richTextViewContainer.setRichTextColor(Color.parseColor(str5));
        }
        if (hashMap.containsKey("expendable") && (str4 = hashMap.get("expendable")) != null) {
            richTextViewContainer.setExpendable(str4.equals("1"));
        }
        if (hashMap.containsKey(DYConstants.DY_TEXT_MAXLINES) && (str3 = hashMap.get(DYConstants.DY_TEXT_MAXLINES)) != null) {
            richTextViewContainer.setMaxLines(Integer.parseInt(str3));
        }
        if (hashMap.containsKey(DYConstants.DY_TEXT_SIZE) && (str2 = hashMap.get(DYConstants.DY_TEXT_SIZE)) != null) {
            richTextViewContainer.setRichTextSize(f(str2, 14.0f));
        }
        if (hashMap.containsKey(DYConstants.DY_TEXT_STYLE) && (str = hashMap.get(DYConstants.DY_TEXT_STYLE)) != null) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -2111534369:
                    if (str.equals(DYConstants.DY_JD_BOLD)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1616224575:
                    if (str.equals(DYConstants.DY_JD_NORMAL)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1310119795:
                    if (str.equals(DYConstants.DY_JD_ZH_BOLD)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1039745817:
                    if (str.equals("normal")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1024004100:
                    if (str.equals(DYConstants.DY_JD_LIGHT)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -255964945:
                    if (str.equals(DYConstants.DY_JD_ZH_NORMAL)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 3029637:
                    if (str.equals("bold")) {
                        c2 = 6;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    a = com.jd.dynamic.lib.utils.i.a(richTextViewContainer.getContext(), DYConstants.DY_JD_BOLD);
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 1:
                    a = com.jd.dynamic.lib.utils.i.a(richTextViewContainer.getContext(), DYConstants.DY_JD_NORMAL);
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 2:
                    a = com.jd.dynamic.lib.utils.i.a(richTextViewContainer.getContext(), DYConstants.DY_JD_ZH_BOLD);
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 3:
                    a = Typeface.DEFAULT;
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 4:
                    a = com.jd.dynamic.lib.utils.i.a(richTextViewContainer.getContext(), DYConstants.DY_JD_LIGHT);
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 5:
                    a = com.jd.dynamic.lib.utils.i.a(richTextViewContainer.getContext(), DYConstants.DY_JD_ZH_NORMAL);
                    richTextViewContainer.setRichTextStyle(a);
                    break;
                case 6:
                    a = Typeface.DEFAULT_BOLD;
                    richTextViewContainer.setRichTextStyle(a);
                    break;
            }
        }
        richTextViewContainer.parseAttribute();
    }
}
