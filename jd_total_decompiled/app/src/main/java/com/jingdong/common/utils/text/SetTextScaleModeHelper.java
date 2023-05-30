package com.jingdong.common.utils.text;

import com.jingdong.common.utils.text.TextScaleModeHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/text/SetTextScaleModeHelper;", "", "", "textScaleMode", "", "setTextSizeScaleMode", "(Ljava/lang/String;)V", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class SetTextScaleModeHelper {
    public static final SetTextScaleModeHelper INSTANCE = new SetTextScaleModeHelper();

    private SetTextScaleModeHelper() {
    }

    @JvmStatic
    public static final void setTextSizeScaleMode(@NotNull String textScaleMode) {
        TextSizeSharedPreferences.getSharePreferences().edit().putString(ScaleModeConstants.TEXT_SCALE_MODE_KEY, textScaleMode).apply();
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        companion.getInstance().setLastTextScaleMode$personallib(textScaleMode);
        companion.getInstance().dispatchOnTextSizeChange$personallib();
    }
}
