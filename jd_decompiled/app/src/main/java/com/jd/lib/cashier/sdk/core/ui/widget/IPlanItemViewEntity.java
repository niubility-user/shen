package com.jd.lib.cashier.sdk.core.ui.widget;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0003\u001a\u00020\u00028&@&X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u00020\u00078&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\u00020\u00078&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\u0010\u001a\u00020\r8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\u00078&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0016\u0010\u0014\u001a\u00020\u00078&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u0016\u0010\u0015\u001a\u00020\u00028&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0004\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "", "", "isChecked", "()Z", "setChecked", "(Z)V", "", "getTopText", "()Ljava/lang/String;", "topText", "getBottomText", "bottomText", "", "getSelectorType", "()I", "selectorType", "getPlanNum", "planNum", "getFlagText", "flagText", "isCheckable", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public interface IPlanItemViewEntity {
    @NotNull
    String getBottomText();

    @NotNull
    String getFlagText();

    @NotNull
    String getPlanNum();

    int getSelectorType();

    @NotNull
    String getTopText();

    boolean isCheckable();

    boolean isChecked();

    void setChecked(boolean z);
}
