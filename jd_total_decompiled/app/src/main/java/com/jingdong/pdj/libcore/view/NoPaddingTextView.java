package com.jingdong.pdj.libcore.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fB\u001b\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u000e\u0010\u0012B#\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u000e\u0010\u0015J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\n\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/jingdong/pdj/libcore/view/NoPaddingTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "", "text", "Landroid/text/SpannableStringBuilder;", "getCustomText", "(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;", "Landroid/widget/TextView$BufferType;", "type", "", "setText", "(Ljava/lang/CharSequence;Landroid/widget/TextView$BufferType;)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class NoPaddingTextView extends AppCompatTextView {
    public NoPaddingTextView(@NotNull Context context) {
        super(context);
        setIncludeFontPadding(false);
    }

    private final SpannableStringBuilder getCustomText(final CharSequence text) {
        if (text != null) {
            final Rect rect = new Rect();
            getPaint().getTextBounds(text.toString(), 0, text.length(), rect);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.setSpan(new LineHeightSpan() { // from class: com.jingdong.pdj.libcore.view.NoPaddingTextView$getCustomText$$inlined$let$lambda$1
                @Override // android.text.style.LineHeightSpan
                public final void chooseHeight(@NotNull CharSequence charSequence, int i2, int i3, int i4, int i5, @NotNull Paint.FontMetricsInt fontMetricsInt) {
                    int i6 = fontMetricsInt.descent - fontMetricsInt.ascent;
                    Rect rect2 = rect;
                    int max = Math.max((int) this.getTextSize(), rect2.bottom - rect2.top);
                    int abs = Math.abs(fontMetricsInt.ascent - rect.top);
                    int i7 = fontMetricsInt.descent - rect.bottom;
                    int i8 = (i6 - max) / 2;
                    if (i8 < Math.min(abs, i7)) {
                        fontMetricsInt.ascent += i8;
                        fontMetricsInt.descent -= i8;
                    } else if (abs < i7) {
                        int i9 = rect.top;
                        fontMetricsInt.ascent = i9;
                        fontMetricsInt.descent = max + i9;
                    } else {
                        int i10 = rect.bottom;
                        fontMetricsInt.descent = i10;
                        fontMetricsInt.ascent = i10 - max;
                    }
                }
            }, 0, text.length(), 33);
            return spannableStringBuilder;
        }
        return null;
    }

    @Override // android.widget.TextView
    public final void setText(@Nullable CharSequence text, @Nullable TextView.BufferType type) {
        super.setText(getCustomText(text), type);
    }

    public NoPaddingTextView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setIncludeFontPadding(false);
    }

    public NoPaddingTextView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setIncludeFontPadding(false);
    }
}
