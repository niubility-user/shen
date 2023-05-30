package com.jd.dynamic.apis;

import android.app.Activity;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.jd.dynamic.base.IFunctionFactory;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdma.minterface.BaseEvent;
import com.tencent.connect.common.Constants;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J#\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0004\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010\t\u001a\u00020\bH&\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\rH&\u00a2\u0006\u0004\b\u000b\u0010\u000fJ#\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0010\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u0014\u001a\u00020\u00022\b\b\u0001\u0010\u0015\u001a\u00020\u00022\b\b\u0001\u0010\u0017\u001a\u00020\u0016H&\u00a2\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u001b\u001a\u0004\u0018\u00010\u00162\b\b\u0001\u0010\u0014\u001a\u00020\u00022\b\b\u0001\u0010\u0015\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00022\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH&\u00a2\u0006\u0004\b\u001f\u0010 J\u0019\u0010\"\u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\"\u0010#J#\u0010\"\u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\u0010$\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\"\u0010\u0007J\u001f\u0010(\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010'\u001a\u00020&H&\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020&2\u0006\u0010$\u001a\u00020%H&\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020&2\u0006\u0010$\u001a\u00020%H&\u00a2\u0006\u0004\b,\u0010+J\u001f\u0010.\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020&H&\u00a2\u0006\u0004\b.\u0010)J\u001f\u0010/\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020&H&\u00a2\u0006\u0004\b/\u0010)J\u0017\u00100\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H&\u00a2\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u0002022\u0006\u0010$\u001a\u00020%H&\u00a2\u0006\u0004\b3\u00104\u00a8\u00065"}, d2 = {"Lcom/jd/dynamic/apis/IDynamicDriver;", "", "", "module", BaseEvent.SCENE, "", JDReactConstant.PREPARE, "(Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/dynamic/apis/DYContainerConfig;", "config", "Lcom/jd/dynamic/apis/DynamicContainer;", "createContainer", "(Lcom/jd/dynamic/apis/DYContainerConfig;)Lcom/jd/dynamic/apis/DynamicContainer;", "Lcom/jd/dynamic/apis/IDYContainerListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "(Lcom/jd/dynamic/apis/DYContainerConfig;Lcom/jd/dynamic/apis/IDYContainerListener;)Lcom/jd/dynamic/apis/DynamicContainer;", "templateId", "Lcom/jd/dynamic/apis/DYTemplateStatus;", "getTemplateStatus", "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/dynamic/apis/DYTemplateStatus;", "sysCode", "biz", "Lcom/jd/dynamic/base/IFunctionFactory;", "factory", "", "registerCustomFunctionFactory", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/dynamic/base/IFunctionFactory;)Z", "getFunctionFactory", "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/dynamic/base/IFunctionFactory;", "", "bizs", "prepareExp", "(Ljava/lang/String;Ljava/util/List;)V", Constants.PARAM_SCOPE, "clearScopedCache", "(Ljava/lang/String;)V", "key", "Landroid/app/Activity;", "", "group", "cacheContextGroup", "(Landroid/app/Activity;J)V", "getContextGroup", "(Landroid/app/Activity;)J", "removeContextGroup", AnnoConst.Constructor_Context, "cacheContext", "removeContext", "cleanContextCache", "(Landroid/app/Activity;)V", "", "getContextCacheSize", "(Landroid/app/Activity;)I", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public interface IDynamicDriver {
    void cacheContext(@NotNull Activity key, long r2);

    void cacheContextGroup(@NotNull Activity key, long group);

    void cleanContextCache(@NotNull Activity key);

    void clearScopedCache(@Nullable String r1);

    void clearScopedCache(@Nullable String r1, @Nullable String key);

    @Nullable
    DynamicContainer createContainer(@NonNull @NotNull DYContainerConfig config);

    @Nullable
    DynamicContainer createContainer(@NonNull @NotNull DYContainerConfig config, @NonNull @NotNull IDYContainerListener r2);

    int getContextCacheSize(@NotNull Activity key);

    long getContextGroup(@NotNull Activity key);

    @Nullable
    IFunctionFactory getFunctionFactory(@NonNull @NotNull String sysCode, @NonNull @NotNull String biz);

    @NotNull
    DYTemplateStatus getTemplateStatus(@NonNull @NotNull String module, @NonNull @NotNull String templateId);

    void prepare(@NonNull @NotNull String str, @NonNull @NotNull String str2);

    void prepareExp(@NotNull String sysCode, @NotNull List<String> bizs);

    boolean registerCustomFunctionFactory(@NonNull @NotNull String sysCode, @NonNull @NotNull String biz, @NonNull @NotNull IFunctionFactory factory);

    void removeContext(@NotNull Activity key, long r2);

    long removeContextGroup(@NotNull Activity key);
}
