package com.jingdong.sdk.eldermode.helper;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\n\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "", "", "hasLogin", "()Z", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "registerLoginReceiver", "(Landroid/content/Context;)V", "unregisterLoginReceiver", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeUserInfoConfig {
    boolean hasLogin();

    void registerLoginReceiver(@NotNull Context context);

    void unregisterLoginReceiver(@NotNull Context context);
}
