package com.jingdong.sdk.eldermode.util;

import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b'\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0004\u001a'\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u000f\u0010\u000e\u001a\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0010\u0010\u000e\u001a\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0011\u0010\u000e\u001a\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0012\u0010\u000e\u001a\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0013\u0010\u000e\u001a\r\u0010\u0014\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0015\u001a\r\u0010\u0017\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0015\u001a\r\u0010\u0018\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0015\u001a\r\u0010\u0019\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0019\u0010\u0015\u001a\r\u0010\u001a\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0015\u001a\r\u0010\u001b\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001b\u0010\u0015\u001a\r\u0010\u001c\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001c\u0010\u0015\u001a\r\u0010\u001d\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0015\u001a\r\u0010\u001e\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001e\u0010\u0015\u001a\r\u0010\u001f\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0015\u001a\r\u0010 \u001a\u00020\u0000\u00a2\u0006\u0004\b \u0010\u0015\"\u0016\u0010!\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\"\"\u0016\u0010#\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\"\"\u0016\u0010$\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\"\"\u0016\u0010%\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\"\"\u0016\u0010&\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b&\u0010\"\"\u0016\u0010'\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b'\u0010\"\"\u0016\u0010(\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b(\u0010\"\"\u0016\u0010)\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b)\u0010\"\"\u0016\u0010*\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b*\u0010\"\"\u0016\u0010+\u001a\u00020\u00008\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b+\u0010\"\u00a8\u0006,"}, d2 = {"", "colorModeIndex", "colorIndex", "getColorByColorMode", "(II)I", "", "supportElderMode", "supportDarkMode", "getCurrentColorModeBySupportMode", "(ZZ)I", "supportMode", "getColorBySupportMode", "(IZZ)I", "getColorBrBySupportMode", "(I)I", "getColorC1BySupportMode", "getColorC2BySupportMode", "getColorBg1BySupportMode", "getColorBg2BySupportMode", "getColorBg3BySupportMode", "getElderColorBr", "()I", "getElderColorC1", "getElderColorC2", "getElderColorBg1", "getElderColorBg2", "getElderColorBg3", "getElderDarkColorBr", "getElderDarkColorC1", "getElderDarkColorC2", "getElderDarkColorBg1", "getElderDarkColorBg2", "getElderDarkColorBg3", "COLOR_MODE_ELDER", "I", "COLOR_MODE_NORMAL_DARK", "COLOR_MODE_NORMAL", "COLOR_MODE_ELDER_DARK", "COLOR_INDEX_BG1", "COLOR_INDEX_BG2", "COLOR_INDEX_BG3", "COLOR_INDEX_BR", "COLOR_INDEX_C1", "COLOR_INDEX_C2", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeColorUtilsKt {
    private static final int COLOR_MODE_ELDER = 2;
    private static final int COLOR_MODE_ELDER_DARK = 3;
    private static final int COLOR_MODE_NORMAL = 0;
    private static final int COLOR_MODE_NORMAL_DARK = 1;

    public static final int getColorBg1BySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(3, i2);
    }

    public static final int getColorBg2BySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(4, i2);
    }

    public static final int getColorBg3BySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(5, i2);
    }

    public static final int getColorBrBySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(0, i2);
    }

    public static final int getColorByColorMode(int i2, int i3) {
        return JDElderModeColors.MODE_COLOR_ARRAY[i2][i3];
    }

    public static final int getColorBySupportMode(int i2, int i3) {
        return JDElderModeUtils.getColorBySupportMode(i2, JDElderModeUtils.hasFlag(i3, 1), JDElderModeUtils.hasFlag(i3, 2));
    }

    public static final int getColorC1BySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(1, i2);
    }

    public static final int getColorC2BySupportMode(int i2) {
        return JDElderModeUtils.getColorBySupportMode(2, i2);
    }

    public static final int getCurrentColorModeBySupportMode(boolean z, boolean z2) {
        JDElderModeManager jDElderModeManager;
        IElderModeHelper helper;
        return (z2 && (helper = (jDElderModeManager = JDElderModeManager.INSTANCE).getHelper()) != null && helper.isDarkMode()) ? (z && jDElderModeManager.isElderMode()) ? 3 : 1 : (z && JDElderModeManager.INSTANCE.isElderMode()) ? 2 : 0;
    }

    public static final int getElderColorBg1() {
        return JDElderModeUtils.getColorByColorMode(2, 3);
    }

    public static final int getElderColorBg2() {
        return JDElderModeUtils.getColorByColorMode(2, 4);
    }

    public static final int getElderColorBg3() {
        return JDElderModeUtils.getColorByColorMode(2, 5);
    }

    public static final int getElderColorBr() {
        return JDElderModeUtils.getColorByColorMode(2, 0);
    }

    public static final int getElderColorC1() {
        return JDElderModeUtils.getColorByColorMode(2, 1);
    }

    public static final int getElderColorC2() {
        return JDElderModeUtils.getColorByColorMode(2, 2);
    }

    public static final int getElderDarkColorBg1() {
        return JDElderModeUtils.getColorByColorMode(3, 3);
    }

    public static final int getElderDarkColorBg2() {
        return JDElderModeUtils.getColorByColorMode(3, 4);
    }

    public static final int getElderDarkColorBg3() {
        return JDElderModeUtils.getColorByColorMode(3, 5);
    }

    public static final int getElderDarkColorBr() {
        return JDElderModeUtils.getColorByColorMode(3, 0);
    }

    public static final int getElderDarkColorC1() {
        return JDElderModeUtils.getColorByColorMode(3, 1);
    }

    public static final int getElderDarkColorC2() {
        return JDElderModeUtils.getColorByColorMode(3, 2);
    }

    public static final int getColorBySupportMode(int i2, boolean z, boolean z2) {
        return JDElderModeUtils.getColorByColorMode(JDElderModeUtils.getCurrentColorModeBySupportMode(z, z2), i2);
    }
}
