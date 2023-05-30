package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class AutoWarpTextView extends AppCompatTextView {
    private ArrayList<RecommendImageSpan> imgSpans;
    private String originalText;

    public AutoWarpTextView(@NonNull Context context) {
        super(context);
    }

    private void addSpanToText(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.imgSpans.size(); i2++) {
            sb.append(1);
            sb.append(LangUtils.SINGLE_SPACE);
        }
        sb.append(str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString());
        for (int i3 = 0; i3 < this.imgSpans.size(); i3++) {
            RecommendImageSpan recommendImageSpan = this.imgSpans.get(i3);
            spannableStringBuilder.setSpan(recommendImageSpan, recommendImageSpan.startPo, recommendImageSpan.endPo, 33);
        }
        setText(spannableStringBuilder);
    }

    private boolean isLetterOrDigit(char c2) {
        return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || Character.isDigit(c2);
    }

    public float getSingleCharWidth(char c2) {
        float[] fArr = new float[1];
        getPaint().getTextWidths(new char[]{c2}, 0, 1, fArr);
        return fArr[0];
    }

    public void setSpannedString(ArrayList<RecommendImageSpan> arrayList, String str) {
        this.imgSpans = arrayList;
        this.originalText = str;
    }

    public String splitText(CharSequence charSequence, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        try {
            if (!RecommendUtils.isSplitText()) {
                return charSequence.toString();
            }
            ArrayList arrayList = new ArrayList();
            if (i2 <= 0) {
                return charSequence.toString();
            }
            StringBuilder sb = new StringBuilder();
            int length = charSequence.length();
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                char charAt = charSequence.charAt(i4);
                i5 = (int) (i5 + getSingleCharWidth(charAt));
                if (i5 > i2) {
                    if ("\uff09".equals(String.valueOf(charAt)) && isLetterOrDigit(charSequence.charAt(i4 - 1))) {
                        sb.insert(sb.length() - 1, LangUtils.SINGLE_SPACE);
                    } else if (isLetterOrDigit(charAt)) {
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    arrayList.add(sb);
                    StringBuilder sb2 = new StringBuilder();
                    i4--;
                    if (arrayList.size() >= 2) {
                        break;
                    }
                    sb = sb2;
                    i2 = i3;
                    i5 = 0;
                } else {
                    sb.append(charAt);
                    if (i4 == length - 1) {
                        arrayList.add(sb);
                    }
                }
                i4++;
            }
            StringBuilder sb3 = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb3.append((CharSequence) ((StringBuilder) it.next()));
            }
            return sb3.toString();
        } catch (Exception unused) {
            return charSequence.toString();
        }
    }

    public AutoWarpTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoWarpTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public void splitText(int i2) {
        float f2;
        if (TextUtils.isEmpty(this.originalText)) {
            setText("");
        } else if (RecommendUtils.downgradeSplitText()) {
            addSpanToText(this.originalText);
        } else {
            try {
                ArrayList arrayList = new ArrayList();
                float f3 = i2;
                float f4 = 0.0f;
                if (f3 <= 0.0f) {
                    addSpanToText(this.originalText);
                    return;
                }
                if (this.imgSpans != null) {
                    for (int i3 = 0; i3 < this.imgSpans.size(); i3++) {
                        RecommendImageSpan recommendImageSpan = this.imgSpans.get(i3);
                        if (recommendImageSpan != null) {
                            if (recommendImageSpan.getDrawable() != null) {
                                f4 += recommendImageSpan.getDrawable().getIntrinsicWidth();
                            }
                            f4 += getSingleCharWidth(' ');
                        }
                    }
                    f2 = f3 - f4;
                } else {
                    f2 = f3;
                }
                StringBuilder sb = new StringBuilder();
                int length = this.originalText.length();
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    char charAt = this.originalText.charAt(i4);
                    i5 = (int) (i5 + getSingleCharWidth(charAt));
                    if (i5 > f2) {
                        if ("\uff09".equals(String.valueOf(charAt)) && isLetterOrDigit(this.originalText.charAt(i4 - 1))) {
                            sb.insert(sb.length() - 1, LangUtils.SINGLE_SPACE);
                        } else if (isLetterOrDigit(charAt)) {
                            sb.append(LangUtils.SINGLE_SPACE);
                        }
                        arrayList.add(sb);
                        StringBuilder sb2 = new StringBuilder();
                        i4--;
                        if (arrayList.size() >= getMaxLines()) {
                            break;
                        }
                        sb = sb2;
                        i5 = 0;
                        f2 = f3;
                    } else {
                        sb.append(charAt);
                        if (i4 == length - 1) {
                            arrayList.add(sb);
                        }
                    }
                    i4++;
                }
                StringBuilder sb3 = new StringBuilder();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    sb3.append((CharSequence) ((StringBuilder) it.next()));
                }
                addSpanToText(sb3.toString());
            } catch (Exception unused) {
                addSpanToText(this.originalText);
            }
        }
    }
}
