package com.jingdong.sdk.eldermode.util;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0015\u00a2\u0006\u0004\b\"\u0010#B\u0011\b\u0016\u0012\u0006\u0010$\u001a\u00020\u0002\u00a2\u0006\u0004\b\"\u0010%J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0004J\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0004J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\u0004J\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0004J\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u0004J\u0015\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b2\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\"\u0010\u001f\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0017\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001b\u00a8\u0006&"}, d2 = {"Lcom/jingdong/sdk/eldermode/util/JDElderModeUiHelper;", "", "", "getCurrentColorMode", "()I", "getColorBr", "getColorC1", "getColorC2", "getColorBg1", "getColorBg2", "getColorBg3", "", "normalSize", "getTextSize", "(F)F", "", "Landroid/widget/TextView;", "textViews", "", "setTextSize", "(F[Landroid/widget/TextView;)V", "", "supportElderMode", "Z", "getSupportElderMode", "()Z", "setSupportElderMode", "(Z)V", "supportDarkMode", "getSupportDarkMode", "setSupportDarkMode", "supportLargeSize", "getSupportLargeSize", "setSupportLargeSize", "<init>", "(ZZZ)V", "supportMode", "(I)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class JDElderModeUiHelper {
    private boolean supportDarkMode;
    private boolean supportElderMode;
    private boolean supportLargeSize;

    public JDElderModeUiHelper() {
        this(false, false, false, 7, null);
    }

    public JDElderModeUiHelper(boolean z, boolean z2, boolean z3) {
        this.supportElderMode = z;
        this.supportDarkMode = z2;
        this.supportLargeSize = z3;
    }

    private final int getCurrentColorMode() {
        return JDElderModeUtils.getCurrentColorModeBySupportMode(this.supportElderMode, this.supportDarkMode);
    }

    public final int getColorBg1() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 3);
    }

    public final int getColorBg2() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 4);
    }

    public final int getColorBg3() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 5);
    }

    public final int getColorBr() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 0);
    }

    public final int getColorC1() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 1);
    }

    public final int getColorC2() {
        return JDElderModeUtils.getColorByColorMode(getCurrentColorMode(), 2);
    }

    public final boolean getSupportDarkMode() {
        return this.supportDarkMode;
    }

    public final boolean getSupportElderMode() {
        return this.supportElderMode;
    }

    public final boolean getSupportLargeSize() {
        return this.supportLargeSize;
    }

    public final float getTextSize(float normalSize) {
        return JDElderModeUtils.getTextSizeBySupportMode(normalSize, this.supportElderMode, this.supportLargeSize);
    }

    public final void setSupportDarkMode(boolean z) {
        this.supportDarkMode = z;
    }

    public final void setSupportElderMode(boolean z) {
        this.supportElderMode = z;
    }

    public final void setSupportLargeSize(boolean z) {
        this.supportLargeSize = z;
    }

    public final void setTextSize(float normalSize, @NotNull TextView... textViews) {
        float textSize = getTextSize(normalSize);
        for (TextView textView : textViews) {
            if (textView != null) {
                textView.setTextSize(textSize);
            }
        }
    }

    public /* synthetic */ JDElderModeUiHelper(boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? false : z3);
    }

    public JDElderModeUiHelper(int i2) {
        this(JDElderModeUtils.hasFlag(i2, 1), JDElderModeUtils.hasFlag(i2, 2), JDElderModeUtils.hasFlag(i2, 4));
    }
}
