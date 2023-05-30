package com.jingdong.sdk.eldermode.helper;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000bJ\u0011\u0010\r\u001a\u0004\u0018\u00010\fH&\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH&\u00a2\u0006\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "isDebug", "()Z", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeHelper extends IElderModeLogger, IElderModeCache, IElderModeExceptionHandler, IElderModeNetworkController, IElderModeMtaSender, IElderModeDarkModeConfig, IElderModeTextSizeConfig, IElderModeOverseasConfig, IElderModeUserInfoConfig, IElderModeDialog, IElderModeToast {
    @Nullable
    Context getContext();

    boolean isDebug();
}
