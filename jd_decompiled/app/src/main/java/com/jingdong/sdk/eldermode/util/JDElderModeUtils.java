package com.jingdong.sdk.eldermode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jingdong.sdk.eldermode.helper.impl.JDElderModeHelper;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmOverloads;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeColorUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeDialogUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeFloatWindowUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeInitializeUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeOverseasUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeTextSizeUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeUiUtilsKt", "com/jingdong/sdk/eldermode/util/JDElderModeUtils__JDElderModeUtilsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDElderModeUtils {
    public static final int COLOR_INDEX_BG1 = 3;
    public static final int COLOR_INDEX_BG2 = 4;
    public static final int COLOR_INDEX_BG3 = 5;
    public static final int COLOR_INDEX_BR = 0;
    public static final int COLOR_INDEX_C1 = 1;
    public static final int COLOR_INDEX_C2 = 2;
    public static final int MODE_ELDER = 1;
    public static final int MODE_NORMAL = 0;
    public static final int SUPPORT_MODE_DARK_MODE = 2;
    public static final int SUPPORT_MODE_ELDER_DARK_LARGE_SIZE = 7;
    public static final int SUPPORT_MODE_ELDER_MODE = 1;
    public static final int SUPPORT_MODE_LARGE_SIZE = 4;

    public static final void addElderModeChangeListener(@Nullable OnElderModeChangeListener onElderModeChangeListener) {
        JDElderModeUtils__JDElderModeUtilsKt.addElderModeChangeListener(onElderModeChangeListener);
    }

    public static final void addElderModeObserver(@Nullable LifecycleOwner lifecycleOwner, @Nullable Observer<Integer> observer) {
        JDElderModeUtils__JDElderModeUtilsKt.addElderModeObserver(lifecycleOwner, observer);
    }

    public static final int getColorBg1BySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBg1BySupportMode(i2);
    }

    public static final int getColorBg2BySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBg2BySupportMode(i2);
    }

    public static final int getColorBg3BySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBg3BySupportMode(i2);
    }

    public static final int getColorBrBySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBrBySupportMode(i2);
    }

    public static final int getColorByColorMode(int i2, int i3) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorByColorMode(i2, i3);
    }

    public static final int getColorBySupportMode(int i2, int i3) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBySupportMode(i2, i3);
    }

    public static final int getColorC1BySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorC1BySupportMode(i2);
    }

    public static final int getColorC2BySupportMode(int i2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorC2BySupportMode(i2);
    }

    public static final int getCurrentColorModeBySupportMode(boolean z, boolean z2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getCurrentColorModeBySupportMode(z, z2);
    }

    public static final int getElderColorBg1() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorBg1();
    }

    public static final int getElderColorBg2() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorBg2();
    }

    public static final int getElderColorBg3() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorBg3();
    }

    public static final int getElderColorBr() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorBr();
    }

    public static final int getElderColorC1() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorC1();
    }

    public static final int getElderColorC2() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderColorC2();
    }

    public static final int getElderDarkColorBg1() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorBg1();
    }

    public static final int getElderDarkColorBg2() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorBg2();
    }

    public static final int getElderDarkColorBg3() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorBg3();
    }

    public static final int getElderDarkColorBr() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorBr();
    }

    public static final int getElderDarkColorC1() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorC1();
    }

    public static final int getElderDarkColorC2() {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getElderDarkColorC2();
    }

    public static final boolean getElderFloatingWindowSwitch() {
        return JDElderModeUtils__JDElderModeFloatWindowUtilsKt.getElderFloatingWindowSwitch();
    }

    public static final int getElderMode() {
        return JDElderModeUtils__JDElderModeUtilsKt.getElderMode();
    }

    public static final float getElderTextSize(float f2) {
        return JDElderModeUtils__JDElderModeTextSizeUtilsKt.getElderTextSize(f2);
    }

    public static final float getMajorTextSize() {
        return JDElderModeUtils__JDElderModeTextSizeUtilsKt.getMajorTextSize();
    }

    public static final float getSecondaryTextSize() {
        return JDElderModeUtils__JDElderModeTextSizeUtilsKt.getSecondaryTextSize();
    }

    public static final float getTextSizeBySupportMode(float f2, int i2) {
        return JDElderModeUtils__JDElderModeTextSizeUtilsKt.getTextSizeBySupportMode(f2, i2);
    }

    @NotNull
    public static final String getUemps() {
        return JDElderModeUtils__JDElderModeUtilsKt.getUemps();
    }

    public static final boolean hasFlag(int i2, int i3) {
        return JDElderModeUtils__JDElderModeUiUtilsKt.hasFlag(i2, i3);
    }

    public static final void initialize(@NotNull JDElderModeHelper jDElderModeHelper) {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.initialize(jDElderModeHelper);
    }

    public static final boolean isElderMode() {
        return JDElderModeUtils__JDElderModeUtilsKt.isElderMode();
    }

    public static final boolean isElderModeEnable() {
        return JDElderModeUtils__JDElderModeUtilsKt.isElderModeEnable();
    }

    public static final boolean isNeedShowElderModeDialog() {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.isNeedShowElderModeDialog();
    }

    public static final boolean isNeedShowNormalModeDialog() {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.isNeedShowNormalModeDialog();
    }

    @NotNull
    public static final JDElderModeHelper.Builder newBuilder(@NotNull Context context) {
        return JDElderModeUtils__JDElderModeInitializeUtilsKt.newBuilder(context);
    }

    public static final void onConfigurationChanged(@Nullable Configuration configuration) {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.onConfigurationChanged(configuration);
    }

    public static final void onDestroy() {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.onDestroy();
    }

    public static final void onElderOverseasAreaChanged(int i2) {
        JDElderModeUtils__JDElderModeOverseasUtilsKt.onElderOverseasAreaChanged(i2);
    }

    public static final void onLoginIn() {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.onLoginIn();
    }

    public static final void onLoginOut() {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.onLoginOut();
    }

    public static final void registerLoginReceiver(@NotNull Context context) {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.registerLoginReceiver(context);
    }

    public static final void removeElderModeChangeListener(@Nullable OnElderModeChangeListener onElderModeChangeListener) {
        JDElderModeUtils__JDElderModeUtilsKt.removeElderModeChangeListener(onElderModeChangeListener);
    }

    public static final void removeElderModeObserver(@Nullable Observer<Integer> observer) {
        JDElderModeUtils__JDElderModeUtilsKt.removeElderModeObserver(observer);
    }

    public static final void requestElderMode() {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.requestElderMode();
    }

    public static final void setElderFloatingWindowSwitch(boolean z) {
        JDElderModeUtils__JDElderModeFloatWindowUtilsKt.setElderFloatingWindowSwitch(z);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog$default(activity, null, null, 6, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog$default(activity, onClickListener, null, 4, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog(activity, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog$default(activity, str, null, null, 12, null);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog$default(activity, str, onClickListener, null, 8, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog$default(activity, null, null, 6, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog$default(activity, onClickListener, null, 4, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog(activity, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog$default(activity, str, null, null, 12, null);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog$default(activity, str, onClickListener, null, 8, null);
    }

    public static final void tryHandleException(@NotNull Throwable th) {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.tryHandleException(th);
    }

    public static final void unregisterLoginReceiver(@NotNull Context context) {
        JDElderModeUtils__JDElderModeInitializeUtilsKt.unregisterLoginReceiver(context);
    }

    public static final int getColorBySupportMode(int i2, boolean z, boolean z2) {
        return JDElderModeUtils__JDElderModeColorUtilsKt.getColorBySupportMode(i2, z, z2);
    }

    public static final float getTextSizeBySupportMode(float f2, boolean z, boolean z2) {
        return JDElderModeUtils__JDElderModeTextSizeUtilsKt.getTextSizeBySupportMode(f2, z, z2);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showElderModeDialog(activity, str, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils__JDElderModeDialogUtilsKt.showNormalModeDialog(activity, str, onClickListener, onClickListener2);
    }
}
