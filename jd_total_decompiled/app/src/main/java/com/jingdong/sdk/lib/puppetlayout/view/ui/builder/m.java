package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class m extends n {
    private SpanWidget q = null;

    public void C(ArrayList<Span> arrayList) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator<Span> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Span next = it.next();
            String str = next.params.get("attribute_text");
            if (!TextUtils.isEmpty(str)) {
                spannableStringBuilder.append((CharSequence) str);
                for (Map.Entry<String, String> entry : next.params.entrySet()) {
                    if ("attribute_fontSize".equals(entry.getKey())) {
                        float dpValue = LayoutUtils.getDpValue(entry.getValue(), -1.0f);
                        if (dpValue != -1.0f) {
                            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) dpValue, false), i2, spannableStringBuilder.length(), 17);
                        }
                    } else if ("attribute_strikethrough".equals(entry.getKey())) {
                        if ("1".equals(entry.getValue())) {
                            spannableStringBuilder.setSpan(new StrikethroughSpan(), i2, spannableStringBuilder.length(), 17);
                        }
                    } else if ("attribute_foregroundColor".equals(entry.getKey())) {
                        if (!TextUtils.isEmpty(entry.getValue())) {
                            try {
                                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(entry.getValue())), i2, spannableStringBuilder.length(), 17);
                            } catch (Exception unused) {
                            }
                        }
                    } else if ("attribute_font".equals(entry.getKey())) {
                        String value = entry.getValue();
                        if ("bold".equals(value)) {
                            spannableStringBuilder.setSpan(new StyleSpan(1), i2, spannableStringBuilder.length(), 17);
                        } else if (FontsUtil.KEY_MULTI_REGULAR.equals(value)) {
                            spannableStringBuilder.setSpan(new StyleSpan(0), i2, spannableStringBuilder.length(), 17);
                        } else if ("JDBBold".equals(value)) {
                            spannableStringBuilder.setSpan(new JDTypefaceSpan(this.q.getContext(), 4097), i2, spannableStringBuilder.length(), 17);
                        } else if ("JDBRegular".equals(value)) {
                            spannableStringBuilder.setSpan(new JDTypefaceSpan(this.q.getContext(), 4099), i2, spannableStringBuilder.length(), 17);
                        } else if ("JDBLight".equals(value)) {
                            spannableStringBuilder.setSpan(new JDTypefaceSpan(this.q.getContext(), 4098), i2, spannableStringBuilder.length(), 17);
                        }
                    } else if ("attribute_backgroundColor".equals(entry.getKey())) {
                        if (!TextUtils.isEmpty(entry.getValue())) {
                            spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor(entry.getValue())), i2, spannableStringBuilder.length(), 17);
                        }
                    } else if (!"attribute_baselineOffset".equals(entry.getKey())) {
                        if ("attribute_underline".equals(entry.getKey())) {
                            if ("1".equals(entry.getValue())) {
                                spannableStringBuilder.setSpan(new UnderlineSpan(), i2, spannableStringBuilder.length(), 17);
                            }
                        } else if (!"attribute_underlineColor".equals(entry.getKey())) {
                            "attribute_space".equals(entry.getKey());
                        }
                    }
                }
                i2 = spannableStringBuilder.length();
            }
        }
        this.q.setText(spannableStringBuilder);
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.n, com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        SpanWidget spanWidget = new SpanWidget(context);
        this.q = spanWidget;
        A(spanWidget);
    }
}
