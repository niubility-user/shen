package com.jingdong.sdk.eldermode.helper;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&\u00a2\u0006\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "", "", "getTextSizeScaleMode", "()Ljava/lang/String;", "mode", "", "setTextSizeScaleMode", "(Ljava/lang/String;)V", "", "isLargeSizeMode", "()Z", "setTextSizeToLargeMode", "()V", "", "normalSize", "getScaleSize", "(F)F", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeTextSizeConfig {
    float getScaleSize(float normalSize);

    @NotNull
    String getTextSizeScaleMode();

    boolean isLargeSizeMode();

    void setTextSizeScaleMode(@NotNull String mode);

    void setTextSizeToLargeMode();
}
